package bds.authorization;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bds.authorization.xsd.AuthorizationSlip;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;

@PropertySource(value = { "classpath:Payroll.properties" })
public class BeamsIntegrationWebService {

	@Autowired
	private Environment environment;

	Logger logger = LoggerFactory.getLogger(BeamsIntegrationWebService.class);

	public HashMap forwardPaybillToBeams(HashMap billData,String beamsUrl ) {
		
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("collection", Map.class);
        xStream.registerConverter(new MapConverter());
        String mapToXML = xStream.toXML(billData);
        
      
        
        Pattern pattern = Pattern.compile(">\\s+");
        Matcher matcher = pattern.matcher(mapToXML);
        mapToXML = matcher.replaceAll(">");

        String dataXML = "<?xml version='1.0' encoding='UTF-8' ?>" + mapToXML;
        
        
        logger.info("mapToXML ::: " + mapToXML);

        URL wsdlURL=null;
		try {
			//wsdlURL = new URL("http://testbeams.mahaitgov.in:8080/BeamsWS1/services/AuthorizationService?wsdl");
			wsdlURL = new URL(beamsUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        QName serviceName = new QName("http://authorization.bds", "AuthorizationService");

        Service service = Service.create(wsdlURL, serviceName);

        AuthorizationServicePortType port = service.getPort(AuthorizationServicePortType.class);
        
        
        Binding binding = ((BindingProvider) port).getBinding();
        binding.setHandlerChain(List.of(new SOAPLoggingHandler()));
    
		/*
		 * String dataXML = "<?xml version='1.0' encoding='UTF-8'?><collection>" +
		 * "<BillType>01</BillType>" + "<FinYear2>2025</FinYear2>" +
		 * "<FinYear1>2024</FinYear1>" + "<BeneficiaryCount>11</BeneficiaryCount>" +
		 * "<PayeeCount>1</PayeeCount>" + "<BifurcatedDedMapInnerMap>" +
		 * "<RC0030045501>9</RC0030045501>" + "<RC8336510201>22000</RC8336510201>" +
		 * "<RC0028001201>1800</RC0028001201>" + "</BifurcatedDedMapInnerMap>" +
		 * "<BulkFlag>Y</BulkFlag>" +
		 * "<BillCreationDate>2025-02-12 11:10:22.346</BillCreationDate>" +
		 * "<PayYear>2024</PayYear>" + "<PaymentFile>Y</PaymentFile>" +
		 * "<BillPortalName>HTESEVAARTH</BillPortalName>" +
		 * "<PaybillId>991000025994</PaybillId>" + "<DDOCode>7101002070</DDOCode>" +
		 * "<DetailHead>36</DetailHead>" + "<SchemeCode>22033344</SchemeCode>" +
		 * "<FormId>MTR44A</FormId>" + "<GrossAmount>1199351</GrossAmount>" +
		 * "<TotalDeduction>23809</TotalDeduction>" + "<PayMonth>12</PayMonth>" +
		 * "<PaymentMode>CMP</PaymentMode>" + "</collection>";
		 */

        // Call the web service method
        List<AuthorizationSlip> slips = port.getAuthSlip(dataXML);  // Pass the XML data
		HashMap beamsResponse=new HashMap();
        // Process the response
        if (slips.size()>0 && !slips.equals("null") && slips.get(0)!=null) {
            for (AuthorizationSlip slip : slips) {
            	if(slip!=null) {
            		   System.out.println("AuthorizationSlip AuthNO: " + slip.getAuthNO().getValue());
                       System.out.println("AuthorizationSlip StatusCode: " + slip.getStatusCode().getValue());
                       beamsResponse.put("authNo", slip.getAuthNO().getValue());
                       beamsResponse.put("statusCode", slip.getStatusCode().getValue());
                       beamsResponse.put("pdfData", slip.getAuthPdf().getValue());
                       beamsResponse.put("additionalFields", slip.getAdditionalFields());
            	}
            }
        } else {
        	return  beamsResponse;
        }

		return  beamsResponse;
	}

}
