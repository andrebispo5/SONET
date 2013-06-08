package pt.largacaixa.ws;

import javax.jws.WebService;

@WebService(endpointInterface="pt.largacaixa.ws.Control")
public class ControlImpl implements Control{
	
	private boolean alive = true;
	
	public void setAlive(){
		this.alive = true;
		//System.out.println("IM ON CONTROL IMPL SETALIVE +++++++++++++++++++++++++++++++++ ");
	}
	
	
	public boolean getAlive(){
		
		System.out.println("IM ON CONTROL IMPL GETALIVE +++++++++++++++++++++++++++++++++ ");
		
		boolean temp = this.alive; this.alive = false; return temp;}
	
}
