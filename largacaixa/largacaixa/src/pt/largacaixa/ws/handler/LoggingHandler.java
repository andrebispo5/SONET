package pt.largacaixa.ws.handler;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import pt.largacaixa.ws.LargaCaixaImpl;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    // change this to redirect output if desired
    private static PrintStream out = System.out;

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        logToSystemOut(smc);
        Boolean isRequest = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        SOAPMessage soapMessage = smc.getMessage();
                
        
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + LargaCaixaImpl.whoAmI);
        
        //mudar este "secundario" para primario
        if(LargaCaixaImpl.whoAmI.equals("primario") /*&& !isRequest*/) {
        
        try{
                        
             SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
             SOAPConnection soapConnection = soapConnectionFactory.createConnection();   
             SOAPMessage response = soapConnection.call(smc.getMessage(), "http://localhost:8080/largacaixa2/endpoint");
             System.out.println("Resposta do Servidor");
             response.writeTo(System.out);
             System.out.println("");
             soapConnection.close();
        }
                  
        catch(Exception e){
        	
        }
   
        
        return true;
    }
        
        
        //é secundario e está a receber uma mensagem
        
        if(LargaCaixaImpl.whoAmI.equals("secundario") && !isRequest){
     	    try{
	        	SOAPMessage soapMsg = smc.getMessage();  
	     	    SOAPHeader soapHeader;
	
	 			SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
	 			soapHeader = soapEnv.getHeader();
	 			Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
	 	   	    Node idNode = (Node) it.next();
	 	   	    String id = (idNode == null) ? null : idNode.getValue();
	 	   	    
	 	   	    Object o = LargaCaixaImpl.map.get(id);
	 	   	    if(id != null && o != null) {
	 	   	    	
	 	            SOAPBody soapBody1 = ((SOAPMessage) o).getSOAPPart().getEnvelope().getBody();
	 	            SOAPBody soapBody2 = ((SOAPMessage) smc).getSOAPPart().getEnvelope().getBody();
	 	            org.w3c.dom.Document doc2 = soapBody2.getOwnerDocument();

	 	            //
	 	            soapBody2.replaceChild(soapBody1.cloneNode(true), soapBody2.cloneNode(true));

	 	            Iterator it1 = soapBody1.getChildElements();
	 	            while(it1.hasNext()) {
	 	                SOAPElement element1 = (SOAPElement) it1.next();
	 	                org.w3c.dom.Node importedNode = doc2.importNode(element1, true);
	 	                soapBody2.appendChild(importedNode);
	 	            }
	 	   	    		 	   	    	
	 	   	    	return false;
	 	   	    }
	 	   	    
	 	   	    
	 	   	    if(id != null){
	 	   	    	LargaCaixaImpl.map.put(id, null);
	 	   	    	LargaCaixaImpl.lastId = id;
	 	   	    }
	 	   	    
	 	   	    
	 	   	    
     	    }
     	    
            catch(Exception e){}
     	   return true;
 		}
        
        
        
        //é secundario e esta a enviar uma mensagem
        
        if(LargaCaixaImpl.whoAmI.equals("secundario") && isRequest){
     	    try{
	        	SOAPMessage soapMsg = smc.getMessage();  
	        	LargaCaixaImpl.map.put(LargaCaixaImpl.lastId, soapMsg);
	 	   	  	 	   	   	   	    
     	    }
     	    
            catch(Exception e){}
     	   return true;
 		}

        
        
        
        return true;
    }
        
    
    
    public boolean handleFault(SOAPMessageContext smc) {
        logToSystemOut(smc);
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
            smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            out.println("Outbound SOAP message:");
        } else {
            out.println("Inbound SOAP message:");
        }

        SOAPMessage message = smc.getMessage();
        try {
            message.writeTo(out);
            out.println();   // just to add a newline to output
        } catch (Exception e) {
            out.println("Exception in handler: " + e);
        }
    }

}
