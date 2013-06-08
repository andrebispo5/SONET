package pt.ist.pagamigo;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

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
import pt.pagamigo.ws.*;

public class PagAmigoRemote extends PagAmigo {

	PagAmigoPortType port;
	
	@Override
	public void transfer(String first, String second, int amount,
			String description) throws ClienteInexistente, MontanteInvalido, SaldoInsuficiente {
		port = searchPagAmigo();
		try{
			port.efectuarPagamento(first, second, amount, description);
		} catch (ClienteInexistente e) {
			throw e;
		} catch (MontanteInvalido e) {
			throw e;
		} catch (SaldoInsuficiente e) {
			throw e;
		}
	}

	@Override
	public int getBalance(String user) {
		int amount = 0;
		port = searchPagAmigo();
		try{
			port.consultarSaldo(user);
		} catch (ClienteInexistente e) {
			System.out.println(e.getLocalizedMessage());
		}
		return amount;
	}

	@Override
	protected void setBalance(String user, int amount) {
		// TODO Auto-generated method stub
		
	}
	
	private PagAmigoPortType searchPagAmigo(){
		PagAmigoService service = new PagAmigoService();
		PagAmigoPortType port = service.getPagAmigoPort();
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
			return null;
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
		
		return port;
	}

}
