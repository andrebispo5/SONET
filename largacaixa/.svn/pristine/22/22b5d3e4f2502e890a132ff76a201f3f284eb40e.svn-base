package pt.largacaixa.ws;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.ws.BindingProvider;

public class SayAlive extends TimerTask {

	Timer t = new Timer();
	private boolean alive = false;
	private String whoAmI;
	ControlImplService service;
	Control port;
	BindingProvider bindingProvider;
	Map<String, Object> requestContext;
	
	public SayAlive(String name) {
		whoAmI = name;
		service = new ControlImplService();
		port = service.getControlImplPort();
		bindingProvider = (BindingProvider) port;
		requestContext = bindingProvider.getRequestContext();
		requestContext.get(ENDPOINT_ADDRESS_PROPERTY);
	
		
		System.out.println("CRIEI UM SAY ALIVE COM " + name);
	
	}

	public void set(){
		
	//	System.out.println("IM ON SETT +++++++++++++++++++++++++++++++++ " + whoAmI);

			requestContext.put(ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/largacaixa2/ControlImpl");
		port.setAlive();
	}
	
	public boolean get(){

		requestContext.put(ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/largacaixa1/ControlImpl");

		boolean a = port.getAlive();
		
	//	System.out.println("THIS IS THE VALUE SECUNDARIO GOT " + a);

		
		return a;
	}

	public boolean setAlive(){
		return this.alive;
	}
	
	@Override
	public void run() {
	
		//System.out.println("IM ON RUN +++++++++++++++++++++++++++++++++ " + whoAmI);

		
		if(whoAmI.equals("primario")){
			set();
		}
		
		if(whoAmI.equals("secundario")){
			alive = get();
		}
		
		
	}
	

}
