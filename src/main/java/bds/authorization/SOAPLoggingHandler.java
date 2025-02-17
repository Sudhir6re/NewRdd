package bds.authorization;
import java.util.Set;

import javax.xml.namespace.QName;

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage soapMessage = context.getMessage();
        try {
            // Log the SOAP message
            soapMessage.writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // Continue processing the message
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
    	  SOAPMessage soapMessage = context.getMessage();
          try {
              // Log the SOAP message
        	  soapMessage.writeTo(System.out);
              soapMessage.writeTo(System.out);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return true;
    }

    @Override
    public void close(MessageContext context) {
        // Optional clean-up code
    	
    }

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}

