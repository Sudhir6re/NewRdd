
package com.mahait.gov.in.nps.webservice;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cra.stp.core.webservice package. 
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

    private final static QName _PerformFileUpload_QNAME = new QName("http://webservice.core.stp.cra.com/", "performFileUpload");
    private final static QName _PerformFileUploadResponse_QNAME = new QName("http://webservice.core.stp.cra.com/", "performFileUploadResponse");
    private final static QName _PerformFileDownload_QNAME = new QName("http://webservice.core.stp.cra.com/", "performFileDownload");
    private final static QName _PerformFileDownloadResponse_QNAME = new QName("http://webservice.core.stp.cra.com/", "performFileDownloadResponse");
    private final static QName _PerformStatusInquiry_QNAME = new QName("http://webservice.core.stp.cra.com/", "performStatusInquiry");
    private final static QName _PerformStatusInquiryResponse_QNAME = new QName("http://webservice.core.stp.cra.com/", "performStatusInquiryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cra.stp.core.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PerformFileUploadResponse }
     * 
     */
    public PerformFileUploadResponse createPerformFileUploadResponse() {
        return new PerformFileUploadResponse();
    }

    /**
     * Create an instance of {@link PerformFileUpload }
     * 
     */
    public PerformFileUpload createPerformFileUpload() {
        return new PerformFileUpload();
    }

    /**
     * Create an instance of {@link PerformStatusInquiryResponse }
     * 
     */
    public PerformStatusInquiryResponse createPerformStatusInquiryResponse() {
        return new PerformStatusInquiryResponse();
    }

    /**
     * Create an instance of {@link PerformStatusInquiry }
     * 
     */
    public PerformStatusInquiry createPerformStatusInquiry() {
        return new PerformStatusInquiry();
    }

    /**
     * Create an instance of {@link PerformFileDownload }
     * 
     */
    public PerformFileDownload createPerformFileDownload() {
        return new PerformFileDownload();
    }

    /**
     * Create an instance of {@link PerformFileDownloadResponse }
     * 
     */
    public PerformFileDownloadResponse createPerformFileDownloadResponse() {
        return new PerformFileDownloadResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformFileUpload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performFileUpload")
    public JAXBElement<PerformFileUpload> createPerformFileUpload(PerformFileUpload value) {
        return new JAXBElement<PerformFileUpload>(_PerformFileUpload_QNAME, PerformFileUpload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformFileUploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performFileUploadResponse")
    public JAXBElement<PerformFileUploadResponse> createPerformFileUploadResponse(PerformFileUploadResponse value) {
        return new JAXBElement<PerformFileUploadResponse>(_PerformFileUploadResponse_QNAME, PerformFileUploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformFileDownload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performFileDownload")
    public JAXBElement<PerformFileDownload> createPerformFileDownload(PerformFileDownload value) {
        return new JAXBElement<PerformFileDownload>(_PerformFileDownload_QNAME, PerformFileDownload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformFileDownloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performFileDownloadResponse")
    public JAXBElement<PerformFileDownloadResponse> createPerformFileDownloadResponse(PerformFileDownloadResponse value) {
        return new JAXBElement<PerformFileDownloadResponse>(_PerformFileDownloadResponse_QNAME, PerformFileDownloadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformStatusInquiry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performStatusInquiry")
    public JAXBElement<PerformStatusInquiry> createPerformStatusInquiry(PerformStatusInquiry value) {
        return new JAXBElement<PerformStatusInquiry>(_PerformStatusInquiry_QNAME, PerformStatusInquiry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformStatusInquiryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.core.stp.cra.com/", name = "performStatusInquiryResponse")
    public JAXBElement<PerformStatusInquiryResponse> createPerformStatusInquiryResponse(PerformStatusInquiryResponse value) {
        return new JAXBElement<PerformStatusInquiryResponse>(_PerformStatusInquiryResponse_QNAME, PerformStatusInquiryResponse.class, null, value);
    }

}
