
package bds.authorization.xsd;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bds.authorization.xsd package. 
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

    private final static QName _AuthorizationSlipAuthNO_QNAME = new QName("http://authorization.bds/xsd", "authNO");
    private final static QName _AuthorizationSlipAuthPdf_QNAME = new QName("http://authorization.bds/xsd", "authPdf");
    private final static QName _AuthorizationSlipBudgetYear1_QNAME = new QName("http://authorization.bds/xsd", "budgetYear1");
    private final static QName _AuthorizationSlipBudgetYear2_QNAME = new QName("http://authorization.bds/xsd", "budgetYear2");
    private final static QName _AuthorizationSlipDdoCode_QNAME = new QName("http://authorization.bds/xsd", "ddoCode");
    private final static QName _AuthorizationSlipExpTotal_QNAME = new QName("http://authorization.bds/xsd", "expTotal");
    private final static QName _AuthorizationSlipStatusCode_QNAME = new QName("http://authorization.bds/xsd", "statusCode");
    private final static QName _AuthorizationSlipTotalBudget_QNAME = new QName("http://authorization.bds/xsd", "totalBudget");
    private final static QName _AuthorizationSlipTransNo_QNAME = new QName("http://authorization.bds/xsd", "transNo");
    private final static QName _AuthorizationSlipValidTo_QNAME = new QName("http://authorization.bds/xsd", "validTo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bds.authorization.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorizationSlip }
     * 
     */
    public AuthorizationSlip createAuthorizationSlip() {
        return new AuthorizationSlip();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "authNO", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipAuthNO(String value) {
        return new JAXBElement<String>(_AuthorizationSlipAuthNO_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "authPdf", scope = AuthorizationSlip.class)
    public JAXBElement<byte[]> createAuthorizationSlipAuthPdf(byte[] value) {
        return new JAXBElement<byte[]>(_AuthorizationSlipAuthPdf_QNAME, byte[].class, AuthorizationSlip.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "budgetYear1", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipBudgetYear1(String value) {
        return new JAXBElement<String>(_AuthorizationSlipBudgetYear1_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "budgetYear2", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipBudgetYear2(String value) {
        return new JAXBElement<String>(_AuthorizationSlipBudgetYear2_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "ddoCode", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipDdoCode(String value) {
        return new JAXBElement<String>(_AuthorizationSlipDdoCode_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "expTotal", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipExpTotal(String value) {
        return new JAXBElement<String>(_AuthorizationSlipExpTotal_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "statusCode", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipStatusCode(String value) {
        return new JAXBElement<String>(_AuthorizationSlipStatusCode_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "totalBudget", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipTotalBudget(String value) {
        return new JAXBElement<String>(_AuthorizationSlipTotalBudget_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "transNo", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipTransNo(String value) {
        return new JAXBElement<String>(_AuthorizationSlipTransNo_QNAME, String.class, AuthorizationSlip.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://authorization.bds/xsd", name = "validTo", scope = AuthorizationSlip.class)
    public JAXBElement<String> createAuthorizationSlipValidTo(String value) {
        return new JAXBElement<String>(_AuthorizationSlipValidTo_QNAME, String.class, AuthorizationSlip.class, value);
    }

}
