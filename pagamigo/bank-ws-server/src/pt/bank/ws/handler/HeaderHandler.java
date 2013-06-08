package pt.bank.ws.handler;



import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import pt.bank.ws.xid.XidImpl;


public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	public boolean handleMessage(SOAPMessageContext context) 
	{
		Boolean outgoing = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(!outgoing){
			String value="SEMVALOR";

			try{
				SOAPMessage soapMsg = context.getMessage();
	            SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
	            SOAPHeader soapHeader = soapEnv.getHeader();
	            Iterator<?> i = soapHeader.getChildElements();
	            while (i.hasNext()) {
	                SOAPElement el = (SOAPElement) i.next(); 
	                value = el.getValue(); 
	            }
	            XidImpl.setXid(value);
	            
			}catch(SOAPException e){
				System.err.println(e);
			}

		}

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