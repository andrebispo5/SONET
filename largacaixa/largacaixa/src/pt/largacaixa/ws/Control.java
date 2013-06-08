package pt.largacaixa.ws;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Control {

//	 @WebMethod void setAlive();
	 @WebMethod @Oneway void setAlive();
	 @WebMethod boolean getAlive();
}
