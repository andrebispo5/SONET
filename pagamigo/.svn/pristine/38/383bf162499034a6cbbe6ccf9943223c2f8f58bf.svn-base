package pt.pagamigo.ws.handler;



import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import pt.pagamigo.ws.xid.XidImpl;


public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	String xid;
	
	public HeaderHandler(String xid2) {
		// TODO Auto-generated constructor stub
		xid = xid2;
	}

	public boolean handleMessage(SOAPMessageContext context) 
	{
		Boolean outgoing = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outgoing){
			if(xid != null){
				xid = XidImpl.getXid();
				try{
					SOAPMessage soapMsg = context.getMessage();
					SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
					SOAPHeader soapHeader = soapEnv.getHeader();

					if (soapHeader == null){
						soapHeader = soapEnv.addHeader();
					}


					QName qname = new QName("http://ws.pagamigo.pt/gtrid", "GTRID");
					SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(qname);

					soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
					soapHeaderElement.addTextNode(xid);
					soapMsg.saveChanges();

				}catch(SOAPException e){
					System.err.println(e);
				}
			}

		}

		//continue other handler chain
		return true;
	}

	public Set<QName> getHeaders() {
		return Collections.EMPTY_SET;
	}

	public boolean handleFault(SOAPMessageContext messageContext) {
		return true;
	}

	public void close(MessageContext context) {
	}
}