package pt.ist.largacaixa;

import java.net.PasswordAuthentication;
import java.util.*;

import javax.jws.HandlerChain;
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
import pt.largacaixa.ws.*;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

public class LargaCaixaRemote extends LargaCaixa {
	
	private LargaCaixaPortType port;

	@Override
	public PersonalContents getPersonalContents(String sourceBox) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllUsernames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBox(String agent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shareContent(String contentID, String sourceUser,
			String destinationUser, Object paymentProof)
			throws InexistentBoxException, InexistentBoxContentException,
			PaymentProofRejectedException {
		port = searchLargaCaixa();
		try{
			port.partilharConteudo(contentID, sourceUser, destinationUser, paymentProof);
		} catch (CaixaInexistente e){
			throw new InexistentBoxException(e.toString());
		} catch (ConteudoInexistenteNaCaixa e){
			throw new InexistentBoxContentException(contentID);
		} catch (ComprovativoRejeitado e){
			throw new PaymentProofRejectedException();
		}
		
	}
	
	private LargaCaixaPortType searchLargaCaixa(){
		LargaCaixaService service = new LargaCaixaService();
		LargaCaixaPortType port = service.getLargaCaixaPort();		
		String endpointAddress = null;
		String organizationName = "Marbera";
		System.out.println("    ");
		System.out.println("------------------UDDI Search------------------");
		System.out.println("Organization: " + organizationName +" In UDDI Name Server(...)");

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

			// We want to create a new organization if it does not exist
			// First, query for "My Organization"
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
			
			for (Organization o : orgs) {
				servs = o.getServices();
				System.out.println("Organization:" + o.getName().getValue() + "\n");
				for (Service s : servs) {
					System.out.println("Service:" + s.getName().getValue() + "\n");
				}
			}
			
			for (Organization o : orgs) {
				if (o.getName().getValue().equals(organizationName)) {
					servs = o.getServices();
					for (Service s : servs) {
						if (s.getName().getValue().equals("LargaCaixa")) {
							binds = s.getServiceBindings();
							for (ServiceBinding b : binds) {
								if(endpointAddress==null)
									endpointAddress = b.getAccessURI();
							}
						}
					}
				}
			}
		}
		catch (JAXRException e) {
			System.err.println("UDDI Error contacting Registry!");
		}
		
		if(endpointAddress == null) {
			System.err.println(organizationName +" Not Found! Aborting (...)");
			return null;
		}
		else {
			System.err.println(organizationName +" Found! Setting Endpoint To Target Server (...)");
		}
		
		BindingProvider bindingProvider = (BindingProvider) port;
		Map<String, Object> requestContext = bindingProvider.getRequestContext();

		System.out.println("    ");
		System.out.println("Changing endpoint address from:");
		System.out.println(requestContext.get(ENDPOINT_ADDRESS_PROPERTY));
		System.out.println("to:");
		System.out.println(endpointAddress);
		System.out.println();
		requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
		
		return port;
	}

}
