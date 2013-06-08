package pt.largacaixa.handler;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
//		System.out.println("HANDLER DO CLIENTE111111111111111111111111111111111111111111111111111");
		
		if(isRequest) {
			try {
				SOAPMessage soapMsg = context.getMessage();
	            SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
	            SOAPHeader soapHeader = soapEnv.getHeader();
	            
	            //if no header, add one
	            if (soapHeader == null) {
	            	soapHeader = soapEnv.addHeader();
	            }

	            //get message id
	            String id = getId();
	            
	            //add a soap header, name as "message id"
	            QName qname = new QName("http://ws.largacaixa.pt/", "messageId");
	            SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(qname);

	            soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
	            soapHeaderElement.addTextNode(id);
	            soapMsg.saveChanges();

	            //tracking
//	            soapMsg.writeTo(System.out);    
			}
			catch(SOAPException e) {
				System.err.println(e);
			}
//			catch(IOException e) {
//				System.err.println(e);
//			}   
		}
		//continue other handler chain
		return true;
	}
	
	@Override
	public boolean handleFault(SOAPMessageContext context) {
//		System.out.println("Client : se isto nao funcionar a culpa é do amorim.");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Client : close()......");
		
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("Client : getHeaders()......");
		return null;
	}
	
	private String getId(){
		return new BigInteger(130, new SecureRandom()).toString(32);	
	}
}