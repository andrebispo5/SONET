package pt.largacaixa.ws;

import java.net.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.xml.registry.*;
import javax.xml.registry.infomodel.*;

/**
 *  Context listener to register Web Service on UDDI Registry
 *
 *  If properly configured in web.xml,
 *  an instance of this class is invoked just after application deploy
 *  and just before application undeploy
 */
public class UDDIContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ////////////////////////////////////////////////////////
            // Read parameters from web.xml
            ////////////////////////////////////////////////////////

            ServletContext servletContext = servletContextEvent.getServletContext();

            String myName = servletContext.getInitParameter("whoAmI");
            String myAdr = servletContext.getInitParameter("adr");
            System.out.println("My project name: " + myName);
            LargaCaixaImpl.setWhoAmI(myName, myAdr);
            if(myName.equals("primario")){
            // UDDI registry server information
            String uddiHost = servletContext.getInitParameter("uddi.server.host");
            String uddiPort = servletContext.getInitParameter("uddi.server.port");
            String uddiURL = "http://" + "localhost" + ":" + uddiPort;
            System.out.println("UDDI Registry server read from web.xml: " + uddiURL);

            // Web Service registration information
            String organizationName = servletContext.getInitParameter("uddi.organization");
            String serviceName = servletContext.getInitParameter("uddi.service");
            String bindingDescription = servletContext.getInitParameter("uddi.binding");
            String bindingURL = servletContext.getInitParameter("uddi.binding.url");
            System.out.println("Binding URL read from web.xml: " + bindingURL);

            // check if replacements are required
            if (bindingURL.indexOf('$') != -1) {
                // replace ${IP} by host address
                InetAddress iAddress = InetAddress.getLocalHost();
                String hostAddress = iAddress.getHostAddress();
                System.out.println("Detected host address: " + hostAddress);
                bindingURL = bindingURL.replace("${IP}", hostAddress);

                // replace ${HOST} by host name
                //String hostName = iAddress.getHostName();
                String canonicalHostName = iAddress.getCanonicalHostName();
                System.out.println("Detected host name: " + canonicalHostName);
                bindingURL = bindingURL.replace("${HOST}", canonicalHostName);

                // replace ${CONTEXT} by application path
                String contextPath = servletContext.getContextPath();
                System.out.println("Detected context: " + contextPath);
                bindingURL = bindingURL.replace("${CONTEXT}", contextPath);
                System.out.println("Binding URL: " + bindingURL);
            }


            ////////////////////////////////////////////////////////
            // Connect to UDDI registry
            ////////////////////////////////////////////////////////

            // retrieve JAX-R Connection Factory using JBoss JNDI context
            InitialContext context = new InitialContext();
            ConnectionFactory connFactory = (ConnectionFactory) context.lookup("java:jboss/jaxr/ConnectionFactory");

            // configure Connection Factory using properties
            Properties props = new Properties();
            // Location of connection configuration file (should be available at WEB-INF/classes on the .war file)
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

            // Finally, establish connection to UDDI registry
            Connection connection = connFactory.createConnection();

            // Define authentication credentials for UDDI registry
            // Note: jUDDI is configured to accept anything as username/password;
            // this would not be the case in a real world deployment
            PasswordAuthentication passwdAuth = new PasswordAuthentication("username", "password".toCharArray());
            Set<PasswordAuthentication> creds = new HashSet<PasswordAuthentication>();
            creds.add(passwdAuth);
            connection.setCredentials(creds);

            // Get RegistryService object
            RegistryService rs = connection.getRegistryService();

            // Get QueryManager object (JAXR Business API)
            // (for queries)
            BusinessQueryManager businessQueryManager = rs.getBusinessQueryManager();
            // Get BusinessLifeCycleManager object (JAXR Business API)
            // (for registrations/changes of information at UDDI registry)
            BusinessLifeCycleManager businessLifeCycleManager = rs.getBusinessLifeCycleManager();


            ////////////////////////////////////////////////////////
            // Search for registered organization
            ////////////////////////////////////////////////////////

            // We want to create a new organization if it does not exist
            // First, query for "My Organization"
            Organization org = null;

            Collection<String> findQualifiers = new ArrayList<String>();
            findQualifiers.add(FindQualifier.SORT_BY_NAME_DESC);

            Collection<String> namePatterns = new ArrayList<String>();
            namePatterns.add("%" + organizationName + "%");

            // Perform search
            BulkResponse r = businessQueryManager.findOrganizations(findQualifiers, namePatterns, null, null, null, null);
            @SuppressWarnings("unchecked")
            Collection<Organization> orgs = r.getCollection();
			Collection<Key> removes = new ArrayList<Key>();
            for (Organization o : orgs) {
                if (o.getName().getValue().equals(organizationName)) {
                    //removes.add(o.getKey());
                    //System.out.println("Added " + o.getName().getValue() + " To remove list!");
                }
            }

            
            ////////////////////////////////////////////////////////
            // Register new entities/change existing entities
            // at UDDI registry
            ////////////////////////////////////////////////////////

            if (org == null) {
                // No organization found, create it
                org = businessLifeCycleManager.createOrganization(organizationName);

                // Create service
                Service service = businessLifeCycleManager.createService(serviceName);
                service.setDescription(businessLifeCycleManager.createInternationalString(serviceName));
                // Add service to organization
                org.addService(service);

                // Create serviceBinding
                if (myName.equals("primario")) {
	                ServiceBinding serviceBinding = businessLifeCycleManager.createServiceBinding();
	                serviceBinding.setDescription(businessLifeCycleManager.createInternationalString(bindingDescription));
	                serviceBinding.setValidateURI(false);
	                // Define the Web Service endpoint address here
	                serviceBinding.setAccessURI(bindingURL);
	                // Add serviceBinding to service
	                service.addServiceBinding(serviceBinding);
	            }
            }

            Collection<Organization> orgs2 = new ArrayList<Organization>();
            orgs2.add(org);

            // At last, register new organization/service/serviceBinding
            // (or the changes) on UDDI registry
            BulkResponse br = businessLifeCycleManager.saveOrganizations(orgs2);

            if (br.getStatus() == JAXRResponse.STATUS_SUCCESS){
                System.out.println("Registration completed successfully for: " + organizationName);
                
            }
            else
                System.out.println("Error during UDDI registration.");
            
        }}
        catch (NamingException e) {
            System.err.println("UDDI Error obtaining ConnectionFactory!");
            e.printStackTrace();
        }
        catch(UnknownHostException e) {
            System.err.println("UDDI Error determining host!");
            e.printStackTrace();
        }
        catch (JAXRException e) {
            System.err.println("UDDI Error contacting Registry!");
            e.printStackTrace();
        }
        LargaCaixaImpl.go();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}