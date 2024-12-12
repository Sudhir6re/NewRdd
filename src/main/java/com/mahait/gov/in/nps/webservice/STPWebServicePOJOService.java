package com.mahait.gov.in.nps.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(
    name = "STPWebServicePOJOService",
    wsdlLocation = "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl",
    targetNamespace = "http://webservice.core.stp.cra.com/"
)
public class STPWebServicePOJOService extends Service {

    public static final URL WSDL_LOCATION;

    public static final QName SERVICE = new QName(
        "http://webservice.core.stp.cra.com/",
        "STPWebServicePOJOService"
    );
    public static final QName STPWebServicePOJOPort = new QName(
        "http://webservice.core.stp.cra.com/",
        "STPWebServicePOJOPort"
    );

    static {
        URL url = null;
        try {
            url = new URL("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(STPWebServicePOJOService.class.getName())
                .log(java.util.logging.Level.SEVERE, 
                     "Cannot initialize the default WSDL from the specified URL.", e);
        }
        WSDL_LOCATION = url;
    }

    // Constructors
    public STPWebServicePOJOService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public STPWebServicePOJOService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public STPWebServicePOJOService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public STPWebServicePOJOService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public STPWebServicePOJOService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public STPWebServicePOJOService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    // Endpoint for STPWebServicePOJOPort
    @WebEndpoint(name = "STPWebServicePOJOPort")
    public STPWebServicePOJO getSTPWebServicePOJOPort() {
        return super.getPort(STPWebServicePOJOPort, STPWebServicePOJO.class);
    }

    @WebEndpoint(name = "STPWebServicePOJOPort")
    public STPWebServicePOJO getSTPWebServicePOJOPort(WebServiceFeature... features) {
        return super.getPort(STPWebServicePOJOPort, STPWebServicePOJO.class, features);
    }
}
