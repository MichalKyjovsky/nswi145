
package cz.cuni.mff.kyjovsm.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.cuni.mff.kyjovsm.cxf package. 
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

    private final static QName _Compute_QNAME = new QName("http://ocr.kyjovsm.mff.cuni.cz/", "compute");
    private final static QName _ComputeResponse_QNAME = new QName("http://ocr.kyjovsm.mff.cuni.cz/", "computeResponse");
    private final static QName _GetTaskResult_QNAME = new QName("http://ocr.kyjovsm.mff.cuni.cz/", "getTaskResult");
    private final static QName _GetTaskResultResponse_QNAME = new QName("http://ocr.kyjovsm.mff.cuni.cz/", "getTaskResultResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cuni.mff.kyjovsm.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TaskResult }
     * 
     */
    public TaskResult createTaskResult() {
        return new TaskResult();
    }

    /**
     * Create an instance of {@link TaskResult.ResultSet }
     * 
     */
    public TaskResult.ResultSet createTaskResultResultSet() {
        return new TaskResult.ResultSet();
    }

    /**
     * Create an instance of {@link TaskResult.Metadata }
     * 
     */
    public TaskResult.Metadata createTaskResultMetadata() {
        return new TaskResult.Metadata();
    }

    /**
     * Create an instance of {@link Compute }
     * 
     */
    public Compute createCompute() {
        return new Compute();
    }

    /**
     * Create an instance of {@link ComputeResponse }
     * 
     */
    public ComputeResponse createComputeResponse() {
        return new ComputeResponse();
    }

    /**
     * Create an instance of {@link GetTaskResult }
     * 
     */
    public GetTaskResult createGetTaskResult() {
        return new GetTaskResult();
    }

    /**
     * Create an instance of {@link GetTaskResultResponse }
     * 
     */
    public GetTaskResultResponse createGetTaskResultResponse() {
        return new GetTaskResultResponse();
    }

    /**
     * Create an instance of {@link TaskResult.ResultSet.Entry }
     * 
     */
    public TaskResult.ResultSet.Entry createTaskResultResultSetEntry() {
        return new TaskResult.ResultSet.Entry();
    }

    /**
     * Create an instance of {@link TaskResult.Metadata.Entry }
     * 
     */
    public TaskResult.Metadata.Entry createTaskResultMetadataEntry() {
        return new TaskResult.Metadata.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Compute }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Compute }{@code >}
     */
    @XmlElementDecl(namespace = "http://ocr.kyjovsm.mff.cuni.cz/", name = "compute")
    public JAXBElement<Compute> createCompute(Compute value) {
        return new JAXBElement<Compute>(_Compute_QNAME, Compute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ComputeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ocr.kyjovsm.mff.cuni.cz/", name = "computeResponse")
    public JAXBElement<ComputeResponse> createComputeResponse(ComputeResponse value) {
        return new JAXBElement<ComputeResponse>(_ComputeResponse_QNAME, ComputeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskResult }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTaskResult }{@code >}
     */
    @XmlElementDecl(namespace = "http://ocr.kyjovsm.mff.cuni.cz/", name = "getTaskResult")
    public JAXBElement<GetTaskResult> createGetTaskResult(GetTaskResult value) {
        return new JAXBElement<GetTaskResult>(_GetTaskResult_QNAME, GetTaskResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskResultResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTaskResultResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ocr.kyjovsm.mff.cuni.cz/", name = "getTaskResultResponse")
    public JAXBElement<GetTaskResultResponse> createGetTaskResultResponse(GetTaskResultResponse value) {
        return new JAXBElement<GetTaskResultResponse>(_GetTaskResultResponse_QNAME, GetTaskResultResponse.class, null, value);
    }

}
