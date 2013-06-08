package pt.pagamigo.ws;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.PasswordAuthentication;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.sql.*;
import javax.sql.*;
import javax.annotation.PostConstruct;
import javax.jws.*;
import javax.transaction.xa.Xid;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.registry.BulkResponse;
import javax.xml.registry.BusinessQueryManager;
import javax.xml.registry.Connection;
import javax.xml.registry.ConnectionFactory;
import javax.xml.registry.FindQualifier;
import javax.xml.registry.JAXRException;
import javax.xml.registry.RegistryService;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.Service;
import javax.xml.registry.infomodel.ServiceBinding;
import javax.xml.ws.*;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import pt.bank.ws.Bank;
import pt.bank.ws.BankImplService;
import security.CertificateAuthorityInterface;
import security.CertificateAuthorityService;
import pt.pagamigo.ws.handler.*;
import pt.pagamigo.ws.xid.XidImpl;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;


@HandlerChain(file="/handler-chain.xml")
@WebService(
    endpointInterface="pt.pagamigo.ws.PagAmigoPortType",
    wsdlLocation="PagAmigo.1_0.wsdl",
    name="PagAmigo",
    portName="PagAmigoPort",
    targetNamespace="http://ws.pagamigo.pt/",
    serviceName="PagAmigoService"
)
public class PagAmigoImpl implements PagAmigoPortType {

	private	TreeMap<String, String> clients = new TreeMap<String, String>();
	private TreeMap<String, ArrayList<MovimentoType> > movements = new TreeMap<String, ArrayList<MovimentoType> >();
	private KeyPair keys;
	
	private CertificateAuthorityInterface ca;

	private Bank toBank = null;
	private Bank fromBank = null;
	private String gtrid;
	byte[] gtridBytes = new byte[] { 0x00, 0x00, 0x03, 0x04 };
	@PostConstruct
    private void init() throws Exception{
		clients.put("alice", "1008");
		clients.put("bruno", "2002");
		clients.put("carlos", "1003");
		clients.put("mariazinha", "1005");
		clients.put("zeninguem", "2005");
		clients.put("ist", "1010");
		clients.put("bn", "1009");

		movements.put("alice", new ArrayList<MovimentoType>());
		movements.put("bruno", new ArrayList<MovimentoType>());
		movements.put("carlos",new ArrayList<MovimentoType>());
		movements.put("mariazinha", new ArrayList<MovimentoType>());
		movements.put("zeninguem", new ArrayList<MovimentoType>());
		movements.put("ist", new ArrayList<MovimentoType>());
		movements.put("bn", new ArrayList<MovimentoType>());
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        keys = keyGen.generateKeyPair();

        
        CertificateAuthorityService service = new CertificateAuthorityService();
        ca = service.getCertificateAuthorityPort();
        ca.setPAPublicKey(keys.getPublic().getEncoded());

    }
	
	private static void writeFile(String path, byte[] content)
    		throws FileNotFoundException, IOException {
    	FileOutputStream fos = new FileOutputStream(path);
    	fos.write(content);
    	fos.close();
    }
	
	/*Method for accessing to balance from an acount of the client with idciente*/
	public int consultarSaldo(String idcliente) throws ClienteInexistente{
		
		System.out.println("««««««««««««««««««««««««««« CONSULTAR SALDO »»»»»»»»»»»»»»»»»»»»»»»»»»»»");
		gtridBytes[0]++;  XidImpl.setXid(gtrid);
		int balance = -1;
		gtrid = "";
		gtrid = javax.xml.bind.DatatypeConverter.printHexBinary(gtridBytes);
		boolean hasClient = clients.containsKey(idcliente);
		if(!hasClient){
			throw new ClienteInexistente("Client " + idcliente + " does not exist!", new ClienteInexistenteType());
		}
		String cliente = clients.get(idcliente);
		String banco = this.getNumBanco(cliente);
		String conta = this.getNumConta(cliente);
		fromBank = this.getBanco(banco);
		try{
			balance = fromBank.getBalance(conta);
		}
		catch (WebServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return balance;
	}

	/*Method for making an payment from clienteOrdenante to clienteBeneficiario*/
	public Object efectuarPagamento(String clienteOrdenante, String clienteBeneficiario, int montante, String descritivo) throws ClienteInexistente, MontanteInvalido, SaldoInsuficiente{
		System.out.println("««««««««««««««««««««««««««« EFECTUAR PAGAMENTO »»»»»»»»»»»»»»»»»»»»»»»»»»»»");
		/*true if the ClienteOrdenante exists*/
		boolean nClienteOrdenante;
		/*true if the ClienteBeneficiario exists*/
		boolean nClienteBeneficiario;
		MovimentoType transaction1;
		MovimentoType transaction2;
		GregorianCalendar gc; 
		XMLGregorianCalendar xgc;
		Comprovativo comprovativo = null;
		byte[] comprovativoEncriptado = new byte[256];
		Object oComprovativoAssinado = null;
		
		
		if(montante < 0){
			throw new MontanteInvalido("Invalid Amount!", new MontanteInvalidoType());
		}
		
		nClienteOrdenante= clients.containsKey(clienteOrdenante);
		nClienteBeneficiario= clients.containsKey(clienteBeneficiario);
		if(!nClienteOrdenante || !nClienteBeneficiario){
			throw new ClienteInexistente("One or both clients do not exist!", new ClienteInexistenteType());
		}
		
		String cliente1 = clients.get(clienteOrdenante);
		String cliente2 = clients.get(clienteBeneficiario);
		String bank1NUM = this.getNumBanco(cliente1);
		String bank2NUM = this.getNumBanco(cliente2);
		String conta1 = this.getNumConta(cliente1);
		String conta2 = this.getNumConta(cliente2);
		
		gtridBytes[1]++;  
		gtrid = javax.xml.bind.DatatypeConverter.printHexBinary(gtridBytes);
		XidImpl.setXid(gtrid);
		fromBank = this.getBanco(bank1NUM);
		toBank = this.getBanco(bank2NUM);
		
		/*GET BALANCE OF THE WITHDRAW ACCOUNT*/
			if(fromBank.getBalance(conta1) < montante){
				throw new SaldoInsuficiente("Not enough money!", new SaldoInsuficienteType());
			}
		/*SEE IF TRANSACTION OCCURS IN THE SAME BANK*/
		if(bank1NUM.equals(bank2NUM)){
				if(fromBank.startLocalTransfer(conta1, conta2, montante)){
					fromBank.endTransaction(true);
				}else{
					fromBank.endTransaction(false);
				}
		}else{
			/*Variable to count how many banks are ready to commit a transaction*/
			int votes=0;
			int[] timedOut = new int[2];
			boolean fromBankSuccess=false;
			boolean toBankSuccess=false;
			
			
			while(timedOut[0]< 3 && !fromBankSuccess){
				try{
					/*if Bank of clienteOrdenante is ready to commit votes is incremented*/
					votes += fromBank.startWithdraw(conta1, montante);
					fromBankSuccess=true;
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACCAO REALIZADA COM SUCESSO %%%%%%%%%%%%%%%%%%%%%%%%%%");
				}
				catch (WebServiceException e) {
					timedOut[0]++;
					fromBankSuccess=false;
					fromBank.abortTransaction();
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACCAO FALHADA" + timedOut[0] +"vezes %%%%%%%%%%%%%%%%%%%%%%%%%%");
				}


			}
			while(timedOut[1]< 3 && !toBankSuccess){
				try{
					/*if Bank of clienteOrdenante is ready to commit votes is incremented*/
					votes += toBank.startDeposit(conta2, montante);
					toBankSuccess=true;
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACCAO REALIZADA COM SUCESSO %%%%%%%%%%%%%%%%%%%%%%%%%%");
				}
				catch (WebServiceException e) {
					timedOut[1]++;
					toBankSuccess=false;
					toBank.abortTransaction();
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACCAO FALHADA" + timedOut[1] +"vezes %%%%%%%%%%%%%%%%%%%%%%%%%%");
				}


			}
			/*if votes == 2 the two banks are ready to commit and the transaction can be executed*/
			if(votes==2){
				fromBank.endTransaction(true);
				toBank.endTransaction(true);
			}else{
				if(fromBankSuccess)
					fromBank.endTransaction(false);
				if(toBankSuccess)
					toBank.endTransaction(false);
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACCAO FALHADA VOTOS < 2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				return null;
			}
		}
		
		/*MAKE THE PROVE OF PAYMENT */
		try {
			gc=new GregorianCalendar();
			xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			transaction1 = new MovimentoType();
			transaction2 = new MovimentoType();

			transaction1.setDataHora(xgc);
			transaction1.setDescritivo(descritivo);
			transaction1.setMontante(0-montante);
			movements.get(clienteOrdenante).add(transaction1);

			transaction2.setDataHora(xgc);
			transaction2.setDescritivo(descritivo);
			transaction2.setMontante(montante);
			movements.get(clienteBeneficiario).add(transaction2);
			comprovativo = new Comprovativo(xgc, clienteOrdenante, clienteBeneficiario, montante, descritivo);

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		/*ENCRYPT THE PROVE OF PAYMENT TO SEND*/
		try{
			comprovativoEncriptado = comprovativo.encrypt(keys.getPrivate());
			System.out.println(comprovativoEncriptado.length);
			oComprovativoAssinado = ca.makeSignature(comprovativoEncriptado);
		} catch(Exception e){
			e.printStackTrace();
		}
		byte[] comprovativoAssinado = (byte[]) oComprovativoAssinado;
		System.out.println(comprovativoAssinado.length);
		return comprovativoAssinado;

	}
	
	
	public List<MovimentoType> consultarMovimentos(String idCliente) throws ClienteInexistente{
		if(!clients.containsKey(idCliente))
			throw new ClienteInexistente("Client " + idCliente + " does not exist!", new ClienteInexistenteType());
		return movements.get(idCliente);
	}
	
	public Bank getBanco(String nomeBanco){
		BankImplService service = new BankImplService();
		
		service.setHandlerResolver(new PAHandlerResolver());
		Bank port = service.getBankImplPort();
		String endpointAddress = null;
		String organizationName = nomeBanco;
		System.out.println("    ");
		System.out.println("###############SEARCH IN UDDI##############");
		System.out.println("Searching For Bank " + organizationName +" In UDDI Name Server(...)");
		// get endpoint address
		try {
			String uddiURL = "http://localhost:8081";
			ConnectionFactory connFactory = ConnectionFactory.newInstance();
			// configure Connection Factory using properties
			Properties props = new Properties();
			// Location of connection configuration file (should be
			// available at WEB-INF/classes on the .war file)
			props.setProperty("scout.juddi.client.config.file", "uddi.xml");
			// search URL of UDDI registry
			props.setProperty("javax.xml.registry.queryManagerURL", uddiURL + "/juddiv3/services/inquiry");
			// publication URL of UDDI registry
			props.setProperty("javax.xml.registry.lifeCycleManagerURL", uddiURL + "/juddiv3/services/publish");
			// security manager URL of UDDI registry
			props.setProperty("javax.xml.registry.securityManagerURL", uddiURL + "/juddiv3/services/security");
			// version of UDDI registry
			props.setProperty("scout.proxy.uddiVersion", "3.0");
			// transport protocol used for communication with UDDI registry
			props.setProperty("scout.proxy.transportClass", "org.apache.juddi.v3.client.transport.JAXWSTransport");
			connFactory.setProperties(props);
			Connection connection = connFactory.createConnection();

			PasswordAuthentication passwdAuth = new PasswordAuthentication("username", "password".toCharArray());
			Set<PasswordAuthentication> creds = new HashSet<PasswordAuthentication>();
			creds.add(passwdAuth);
			connection.setCredentials(creds);
			RegistryService rs = connection.getRegistryService();

			BusinessQueryManager businessQueryManager = rs.getBusinessQueryManager();

			// //////////////////////////////////////////////////////
			// Search for registered organization
			// //////////////////////////////////////////////////////
			Organization org = null;
			Collection<String> findQualifiers = new ArrayList<String>();
			findQualifiers.add(FindQualifier.SORT_BY_NAME_DESC);

			Collection<String> namePatterns = new ArrayList<String>();
			namePatterns.add(organizationName);
			// Perform search
			BulkResponse r = businessQueryManager.findOrganizations(findQualifiers, namePatterns, null, null, null, null);
			@SuppressWarnings("unchecked")
			Collection<Organization> orgs = r.getCollection();
			@SuppressWarnings("unchecked")
			Collection<Service> servs;
			@SuppressWarnings("unchecked")
			Collection<ServiceBinding> binds;

			//SEARCH FOR nomeBanco
			for (Organization o : orgs) {
				if (o.getName().getValue().equals(organizationName)) {
					servs = o.getServices();
					for (Service s : servs) {
						if (s.getName().getValue().equals(organizationName)) {
							binds = s.getServiceBindings();
							for (ServiceBinding b : binds) {
								if(endpointAddress==null)	
									endpointAddress = b.getAccessURI();
							}
						}
					}
				}
			}
			
		} catch (JAXRException e) {
			System.out.println("UDDI Error contacting Registry!");
			System.out.println("################################################");
		}

		System.out.println("Bank "+organizationName +" Found! Setting Endpoint To Target Server (...)");
		System.out.println("################################################");
		BindingProvider bindingProvider = (BindingProvider) port;
		Map<String, Object> requestContext = bindingProvider.getRequestContext();
		requestContext.put( "javax.xml.ws.client.connectionTimeout", "2000" );
		requestContext.put( "javax.xml.ws.client.receiveTimeout", "2000" );
		
		
		
		
		
		// set endpoint address
		System.out.println("    ");
		System.out.println("###############SETTING ENDPOINT################");
		System.out.println("Changing endpoint address from:");
		System.out.println(requestContext.get(ENDPOINT_ADDRESS_PROPERTY));
		System.out.println("to:");
		System.out.println(endpointAddress);
		System.out.println();
		requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
		System.out.println("################################################");

		
		return port;
	}
	
	public String getNumConta(String c){
		String num = c.substring(1,4);
		return num;
	}
	
	public String getNumBanco(String c){
		String num = c.substring(0,1);
		return num;
	}
	
}
		
	
