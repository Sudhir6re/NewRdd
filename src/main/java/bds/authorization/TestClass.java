package bds.authorization;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import bds.authorization.xsd.AuthorizationSlip;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;

public class TestClass {

    public static void main(String[] args) throws Exception {
        // Define WSDL URL and QName for the service
        URL wsdlURL = new URL("http://testbeams.mahaitgov.in:8080/BeamsWS1/services/AuthorizationService?wsdl");
        QName serviceName = new QName("http://authorization.bds", "AuthorizationService");

        // Create the service instance
        Service service = Service.create(wsdlURL, serviceName);

        // Get the port (this is the generated interface)
        AuthorizationServicePortType port = service.getPort(AuthorizationServicePortType.class);
        // Add logging interceptors for SOAP messages
        
        
        // Add the SOAP logging handler to the port
        Binding binding = ((BindingProvider) port).getBinding();
        binding.setHandlerChain(List.of(new SOAPLoggingHandler()));
    
        String dataXML = "<?xml version='1.0' encoding='UTF-8'?><collection>" +
        	    "<BillType>01</BillType>" +
        	    "<FinYear2>2025</FinYear2>" +
        	    "<FinYear1>2024</FinYear1>" +
        	    "<BeneficiaryCount>11</BeneficiaryCount>" +
        	    "<PayeeCount>1</PayeeCount>" +
        	    "<BifurcatedDedMapInnerMap>" +
        	    "<RC0030045501>9</RC0030045501>" +
        	    "<RC8336510201>22000</RC8336510201>" +
        	    "<RC0028001201>1800</RC0028001201>" +
        	    "</BifurcatedDedMapInnerMap>" +
        	    "<BulkFlag>Y</BulkFlag>" +
        	    "<BillCreationDate>2025-02-12 11:10:22.346</BillCreationDate>" +
        	    "<PayYear>2024</PayYear>" +
        	    "<PaymentFile>Y</PaymentFile>" +
        	    "<BillPortalName>HTESEVAARTH</BillPortalName>" +
        	    "<PaybillId>991000025994</PaybillId>" +
        	    "<DDOCode>7101002070</DDOCode>" +
        	    "<DetailHead>36</DetailHead>" +
        	    "<SchemeCode>22033344</SchemeCode>" +
        	    "<FormId>MTR44A</FormId>" +
        	    "<GrossAmount>1199351</GrossAmount>" +
        	    "<TotalDeduction>23809</TotalDeduction>" +
        	    "<PayMonth>12</PayMonth>" +
        	    "<PaymentMode>CMP</PaymentMode>" +
        	    "</collection>";

        // Call the web service method
        List<AuthorizationSlip> slips = port.getAuthSlip(dataXML);  // Pass the XML data

        // Process the response
        if (slips.size()>0 && !slips.equals("[null]")) {
            for (AuthorizationSlip slip : slips) {
            	if(slip!=null) {
            		   System.out.println("AuthorizationSlip AuthNO: " + slip.getAuthNO());
                       System.out.println("AuthorizationSlip StatusCode: " + slip.getStatusCode());
            	}
            }
        } else {
            System.out.println("No slips returned from the service.");
        }
    }
}

