package pt.pagamigo.ws.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

	private static PrintStream out  = System.out;
    
    public static String getSOAPMessageAsString(SOAPMessage soapMessage) {
        try {
        	

           TransformerFactory tff = TransformerFactory.newInstance();
           Transformer tf = tff.newTransformer();

           // Set formatting
          
           tf.setOutputProperty(OutputKeys.INDENT, "yes");
           tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                 "2");
           
           Source sc = soapMessage.getSOAPPart().getContent();

           ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
           StreamResult result = new StreamResult(streamOut);
           tf.transform(sc, result);

           String strMessage = streamOut.toString();
           return strMessage;
        } catch (Exception e) {
           System.out.println("Exception in getSOAPMessageAsString "
                 + e.getMessage());
           return null;
        }

     }
  


    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        try {
			logToSystemOut(smc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        try {
			logToSystemOut(smc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    private void logToSystemOut(SOAPMessageContext smc) throws IOException {
        Boolean outboundProperty = (Boolean)
            smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (outboundProperty.booleanValue()) {
        	out.println("Outbound SOAP message:");
        } else {
        	out.println("Inbound SOAP message:");
        }
        SOAPMessage message = smc.getMessage();
        try {
            String output=getSOAPMessageAsString(message);
            out.println(output);
        } catch (Exception e) {
            out.println("Exception in handler: " + e);
        }
        
    }

}
