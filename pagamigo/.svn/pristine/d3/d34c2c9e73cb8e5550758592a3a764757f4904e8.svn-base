package pt.pagamigo.ws.client;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import javax.crypto.Cipher;
import javax.naming.NamingException;
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
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import pt.pagamigo.ws.ClienteInexistente;
import pt.pagamigo.ws.MontanteInvalido;
import pt.pagamigo.ws.MovimentoType;
import pt.pagamigo.ws.PagAmigoPortType;
import pt.pagamigo.ws.PagAmigoService;
import pt.pagamigo.ws.SaldoInsuficiente;
import pt.pagamigo.ws.client.*;
import security.CertificateAuthorityInterface;
import security.CertificateAuthorityService;

public class PagAmigoClient {

	static CertificateAuthorityInterface ca;
	public static void main(String[] args) throws Exception {
		PagAmigoService service = new PagAmigoService();
		PagAmigoPortType port = service.getPagAmigoPort();
		CertificateAuthorityService service2 = new CertificateAuthorityService();
        ca = service2.getCertificateAuthorityPort();
		String endpointAddress = null;
		String organizationName = "SONET";
		System.out.println("    ");
		System.out.println("###############SEARCH IN UDDI##############");
		System.out.println("Searching For " + organizationName +" In UDDI Name Server(...)");
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
			namePatterns.add("%0%");
			// Perform search
			BulkResponse r = businessQueryManager.findOrganizations(findQualifiers, namePatterns, null, null, null, null);
			@SuppressWarnings("unchecked")
			Collection<Organization> orgs = r.getCollection();
			@SuppressWarnings("unchecked")
			Collection<Service> servs;
			@SuppressWarnings("unchecked")
			Collection<ServiceBinding> binds;
			for (Organization o : orgs) {
				servs = o.getServices();
				System.out.println("[ORG:"+ o.getName().getValue() +" ]");
				for (Service s : servs) {
					System.out.println("\t[SER:"+ s.getName().getValue() +" ]");	
				}
			}


			for (Organization o : orgs) {
				if (o.getName().getValue().equals(organizationName)) {
					servs = o.getServices();
					for (Service s : servs) {
						if (s.getName().getValue().equals("PagAmigo")) {
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
		if(endpointAddress==null){
			System.out.println(organizationName +" Not Found! Aborting (...)");
			System.out.println("################################################");
			return;
		}else{
			System.out.println(organizationName +" Found! Setting Endpoint To Target Server (...)");
			System.out.println("################################################");
		}
		BindingProvider bindingProvider = (BindingProvider) port;
		Map<String, Object> requestContext = bindingProvider.getRequestContext();
		
		
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

		
		// TEST TRANSACTIONS
		testEfectuarPagamento(port);
		
		// TEST TRANSACTIONS HISTORY LISTING
		//testListTransactions(port);
	}

	private static void testEfectuarPagamento(PagAmigoPortType port) throws Exception{
		System.out.println("    ");
		System.out.println("################TESTING PAYMENT################");
		
		
		try {
			System.out.print("Saldo do Balelas: ");
			System.out.println(port.consultarSaldo("Balelas"));
		} catch (ClienteInexistente e) {
			System.out.println(e.getLocalizedMessage());
		}

		try {
			System.out.print("Saldo do Alice: ");
			System.out.println(port.consultarSaldo("alice"));
		} catch (ClienteInexistente e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			System.out.print("Saldo do ist: ");
			System.out.println(port.consultarSaldo("ist"));
		} catch (ClienteInexistente e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			System.out.println("Transfer:Alice->ist [10] ");
			Object o = port.efectuarPagamento("alice", "ist", 10, "Doacao Caridosa.");
			
			
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		try {
			System.out.print("Saldo do Alice: ");
			System.out.println(port.consultarSaldo("alice"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		}
		try {
			System.out.print("Saldo do ist: ");
			System.out.println(port.consultarSaldo("ist"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		}

		try {
			System.out.println("Transfer:ist->zeninguem [10] ");
			Object o = port.efectuarPagamento("ist", "zeninguem", 10, "Conta Offshore. shh");
			
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		
		try {
			System.out.print("Transfer:ist->zeninguem [10] ");
			System.out.println(port.efectuarPagamento("ist", "zeninguem", 10, "Conta Offshore. shh"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		
		try {
			System.out.print("Transfer:Carlos->zeninguem [-50]" );
			System.out.println(port.efectuarPagamento("carlos", "zeninguem", -50, "Engano!"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		try {
			System.out.print("Transfer:Balelas->Bruno [15]");
			System.out.println(port.efectuarPagamento("Balelas", "bruno", 15, "eu nao existo"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		try {
			System.out.print("Transfer:Bruno->Balelas [10]");
			System.out.println(port.efectuarPagamento("bruno", "Balelas", 10, "ele nao existe"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		} catch (MontanteInvalido e) {
			System.out.println(e.toString());
		} catch (SaldoInsuficiente e) {
			System.out.println(e.toString());
		}
		System.out.println("################################################");
	}

	private static void testListTransactions(PagAmigoPortType port) {
		System.out.println("    ");
		System.out.println("################TESTING LISTING################");

		System.out.println("====================================");
		System.out.println("-----------Alice Movimentos---------");
		System.out.println("====================================");
		try {
			listAllTransactions(port.consultarMovimentos("alice"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		}
		System.out.println("====================================");
		System.out.println("--------zeninguem Movimentos--------");
		System.out.println("====================================");
		try {
			listAllTransactions(port.consultarMovimentos("zeninguem"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		}
		System.out.println("====================================");
		System.out.println("-----------ist Movimentos-----------");
		System.out.println("====================================");
		try {
			listAllTransactions(port.consultarMovimentos("ist"));
		} catch (ClienteInexistente e) {
			System.out.println(e.toString());
		}
		System.out.println("################################################");
	}

	private static void listAllTransactions(List<MovimentoType> movs) {
		if (movs.size() == 0) {
			System.out.println("------------------------------------");
			System.out.println("-----Sem Movimentos Registados------");
			System.out.println("------------------------------------");
		} else {
			for (MovimentoType m : movs) {
				System.out.println("------------------------------------");
				System.out.println("Data:" + m.getDataHora().toGregorianCalendar().getTime().toString());
				if (m.getMontante() < 0)
					System.out.println("Montante:" + m.getMontante());
				else
					System.out.println("Montante:+" + m.getMontante());
				System.out.println("Descricao:" + m.getDescritivo());
				System.out.println("------------------------------------");
			}
		}

	}
	
	public static String decrypt(byte[] encrypted) throws Exception{
		Object o = ca.getPAPublicKey();
		byte[] pubEncoded = (byte[]) o;
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubEncoded);
        KeyFactory keyFacPub = KeyFactory.getInstance("RSA");
        PublicKey pub = keyFacPub.generatePublic(pubSpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, pub);
        byte[] newPlainBytes = cipher.doFinal(encrypted);
        String newPlainText = new String(newPlainBytes, "UTF-8");
        return newPlainText; 
		
	}
	
	private static byte[] readFile(String path)
			throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(path);
        byte[] content = new byte[fis.available()];
        fis.read(content);
        fis.close();
		return content;
	}
	
	public static void printComprovativo(String comprovativo){
		String datahora, ordenante, beneficiario, montante, descritivo;
		String[] split = comprovativo.split(",");
		datahora = split[0].substring(split[0].lastIndexOf("=")+1);
		ordenante = split[1].substring(split[1].lastIndexOf("=")+1);
		beneficiario = split[2].substring(split[2].lastIndexOf("=")+1);
		montante = split[3].substring(split[3].lastIndexOf("=")+1);
		descritivo = split[4].substring(split[4].lastIndexOf("=")+1, split[4].lastIndexOf("]")-1);
		System.out.println("Data e hora: " + datahora);
		System.out.println("Ordenante: " + ordenante);
		System.out.println("Beneficiario: " + beneficiario);
		System.out.println("Montante: " + montante);
		System.out.println("Descritivo: " + descritivo);
	}

}
