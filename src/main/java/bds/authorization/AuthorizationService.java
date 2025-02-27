package bds.authorization;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;


@WebServiceClient(name = "AuthorizationService",
                  wsdlLocation = "file:/E:/New_RDD_Workspace/demo/src/main/resources/AuthorizationService.wsdl",
                  targetNamespace = "http://authorization.bds")
public class AuthorizationService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://authorization.bds", "AuthorizationService");
    public final static QName AuthorizationServiceHttpsSoap12Endpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpsSoap12Endpoint");
    public final static QName AuthorizationServiceHttpSoap12Endpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpSoap12Endpoint");
    public final static QName AuthorizationServiceHttpEndpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpEndpoint");
    public final static QName AuthorizationServiceHttpSoap11Endpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpSoap11Endpoint");
    public final static QName AuthorizationServiceHttpsSoap11Endpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpsSoap11Endpoint");
    public final static QName AuthorizationServiceHttpsEndpoint = new QName("http://authorization.bds", "AuthorizationServiceHttpsEndpoint");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/New_RDD_Workspace/demo/src/main/resources/AuthorizationService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AuthorizationService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/E:/New_RDD_Workspace/demo/src/main/resources/AuthorizationService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AuthorizationService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AuthorizationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuthorizationService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public AuthorizationService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AuthorizationService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AuthorizationService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsSoap12Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsSoap12Endpoint() {
        return super.getPort(AuthorizationServiceHttpsSoap12Endpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsSoap12Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpsSoap12Endpoint, AuthorizationServicePortType.class, features);
    }


    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpSoap12Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpSoap12Endpoint() {
        return super.getPort(AuthorizationServiceHttpSoap12Endpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpSoap12Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpSoap12Endpoint, AuthorizationServicePortType.class, features);
    }


    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpEndpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpEndpoint() {
        return super.getPort(AuthorizationServiceHttpEndpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpEndpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpEndpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpEndpoint, AuthorizationServicePortType.class, features);
    }


    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpSoap11Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpSoap11Endpoint() {
        return super.getPort(AuthorizationServiceHttpSoap11Endpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpSoap11Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpSoap11Endpoint, AuthorizationServicePortType.class, features);
    }


    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsSoap11Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsSoap11Endpoint() {
        return super.getPort(AuthorizationServiceHttpsSoap11Endpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsSoap11Endpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpsSoap11Endpoint, AuthorizationServicePortType.class, features);
    }


    /**
     *
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsEndpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsEndpoint() {
        return super.getPort(AuthorizationServiceHttpsEndpoint, AuthorizationServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthorizationServicePortType
     */
    @WebEndpoint(name = "AuthorizationServiceHttpsEndpoint")
    public AuthorizationServicePortType getAuthorizationServiceHttpsEndpoint(WebServiceFeature... features) {
        return super.getPort(AuthorizationServiceHttpsEndpoint, AuthorizationServicePortType.class, features);
    }

}
