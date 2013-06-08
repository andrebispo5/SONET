package pt.ist.sonet;

import pt.ist.sonet.domain.ExternalServiceConfig;
import pt.ist.sonet.domain.Sonet;
import pt.ist.sonet.factory.ExternalServiceFactory;
import pt.ist.sonet.factory.LocalExternalServiceFactory;
import pt.ist.sonet.factory.RemoteExternalServiceFactory;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

public class DataBaseBootstrap {

	private static boolean notInitialized = true;

	public synchronized static void init(String serverType) {
		if (notInitialized) {
			FenixFramework.initialize(new Config() {{
				domainModelPath = "src/main/dml/domain.dml";
				dbAlias = "//localhost:3306/sonetdb";
				dbUsername = "sonet";
				dbPassword = "s0n3t";
				rootClass = Sonet.class;
			}});
		}
		if(serverType.contains("ES+SD")){
			ExternalServiceFactory remoteFactory = new RemoteExternalServiceFactory();
			ExternalServiceConfig.setFactory(remoteFactory);
		} else {
			ExternalServiceFactory localFactory = new LocalExternalServiceFactory();
			ExternalServiceConfig.setFactory(localFactory);
		}
		notInitialized = false;
	}

	public static void setup() {
		try {
			pt.ist.sonet.SonetSetup.populateDomain();
		} catch (pt.ist.sonet.exception.SonetException ex) {
			System.out.println("Error while populating sonet application: " + ex);
		}
	}
}
