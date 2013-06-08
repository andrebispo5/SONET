package pt.ist.sonet.domain;

import pt.ist.sonet.factory.ExternalServiceFactory;

public class ExternalServiceConfig {
	
	private static ExternalServiceFactory _factory;
	private static ExternalServiceConfig service = null;
	
	private ExternalServiceConfig(){}
	
	public static ExternalServiceConfig getInstance(){
		if(service == null)
			service = new ExternalServiceConfig();
		return service;
	}

	public static ExternalServiceFactory getFactory(){
		if(service == null)
			service = new ExternalServiceConfig();
		return _factory;
	}

	public static void setFactory(ExternalServiceFactory factory) {
		if(service == null)
			service = new ExternalServiceConfig();
		_factory = factory;
		
	}
}
