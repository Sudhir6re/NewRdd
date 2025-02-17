
package bds.authorization.xsd;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationSlip", propOrder = {
    "additionalFields",
    "authNO",
    "authPdf",
    "budgetYear1",
    "budgetYear2",
    "ddoCode",
    "expTotal",
    "statusCode",
    "totalBudget",
    "transNo",
    "validTo"
})
public class AuthorizationSlip {

    @XmlElement(nillable = true)
    protected List<String> additionalFields;
    @XmlElementRef(name = "authNO", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> authNO;
    @XmlElementRef(name = "authPdf", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> authPdf;
    @XmlElementRef(name = "budgetYear1", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> budgetYear1;
    @XmlElementRef(name = "budgetYear2", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> budgetYear2;
    @XmlElementRef(name = "ddoCode", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ddoCode;
    @XmlElementRef(name = "expTotal", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> expTotal;
    @XmlElementRef(name = "statusCode", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusCode;
    @XmlElementRef(name = "totalBudget", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> totalBudget;
    @XmlElementRef(name = "transNo", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transNo;
    @XmlElementRef(name = "validTo", namespace = "http://authorization.bds/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> validTo;

    /**
     * Gets the value of the additionalFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAdditionalFields() {
        if (additionalFields == null) {
            additionalFields = new ArrayList<String>();
        }
        return this.additionalFields;
    }

    /**
     * Gets the value of the authNO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuthNO() {
        return authNO;
    }

    /**
     * Sets the value of the authNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuthNO(JAXBElement<String> value) {
        this.authNO = value;
    }

    /**
     * Gets the value of the authPdf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getAuthPdf() {
        return authPdf;
    }

    /**
     * Sets the value of the authPdf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setAuthPdf(JAXBElement<byte[]> value) {
        this.authPdf = value;
    }

    /**
     * Gets the value of the budgetYear1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBudgetYear1() {
        return budgetYear1;
    }

    /**
     * Sets the value of the budgetYear1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBudgetYear1(JAXBElement<String> value) {
        this.budgetYear1 = value;
    }

    /**
     * Gets the value of the budgetYear2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBudgetYear2() {
        return budgetYear2;
    }

    /**
     * Sets the value of the budgetYear2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBudgetYear2(JAXBElement<String> value) {
        this.budgetYear2 = value;
    }

    /**
     * Gets the value of the ddoCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDdoCode() {
        return ddoCode;
    }

    /**
     * Sets the value of the ddoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDdoCode(JAXBElement<String> value) {
        this.ddoCode = value;
    }

    /**
     * Gets the value of the expTotal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExpTotal() {
        return expTotal;
    }

    /**
     * Sets the value of the expTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpTotal(JAXBElement<String> value) {
        this.expTotal = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusCode(JAXBElement<String> value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the totalBudget property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalBudget() {
        return totalBudget;
    }

    /**
     * Sets the value of the totalBudget property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalBudget(JAXBElement<String> value) {
        this.totalBudget = value;
    }

    /**
     * Gets the value of the transNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransNo() {
        return transNo;
    }

    /**
     * Sets the value of the transNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransNo(JAXBElement<String> value) {
        this.transNo = value;
    }

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValidTo(JAXBElement<String> value) {
        this.validTo = value;
    }

}
