
package bds.authorization;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bds.authorization package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAuthSlipXml_QNAME = new QName("http://authorization.bds", "xml");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bds.authorization
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAuthSlip }
     * 
     */
    public GetAuthSlip createGetAuthSlip() {
        return new GetAuthSlip();
    }

    /**
     * Create an instance of {@link GetAuthSlipResponse }
     * 
     */
    public GetAuthSlipResponse createGetAuthSlipResponse() {
        return new GetAuthSlipResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds", name = "xml", scope = GetAuthSlip.class)
    public JAXBElement<String> createGetAuthSlipXml(String value) {
        return new JAXBElement<String>(_GetAuthSlipXml_QNAME, String.class, GetAuthSlip.class, value);
    }

}
