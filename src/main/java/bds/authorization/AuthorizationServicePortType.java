package bds.authorization;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


@WebService(targetNamespace = "http://authorization.bds", name = "AuthorizationServicePortType")
@XmlSeeAlso({bds.authorization.xsd.ObjectFactory.class, ObjectFactory.class})
public interface AuthorizationServicePortType {

    @WebMethod(action = "urn:getAuthSlip")
    @Action(input = "urn:getAuthSlip", output = "urn:getAuthSlipResponse")
    @RequestWrapper(localName = "getAuthSlip", targetNamespace = "http://authorization.bds", className = "bds.authorization.GetAuthSlip")
    @ResponseWrapper(localName = "getAuthSlipResponse", targetNamespace = "http://authorization.bds", className = "bds.authorization.GetAuthSlipResponse")
    @WebResult(name = "return", targetNamespace = "http://authorization.bds")
    public java.util.List<bds.authorization.xsd.AuthorizationSlip> getAuthSlip(
        @WebParam(name = "xml", targetNamespace = "http://authorization.bds")
        java.lang.String xml
    );
}
