
package _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo package. 
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

    private final static QName _ConsWsFault_QNAME = new QName("http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", "ConsWsFault");
    private final static QName _ConsultadeTitulosRequest_QNAME = new QName("http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", "ConsultadeTitulosRequest");
    private final static QName _ConsultadeTitulosRequestResponse_QNAME = new QName("http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", "ConsultadeTitulosRequestResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultadeTitulosRequest }
     * 
     */
    public ConsultadeTitulosRequest createConsultadeTitulosRequest() {
        return new ConsultadeTitulosRequest();
    }

    /**
     * Create an instance of {@link ConsultadeTitulosRequestResponse }
     * 
     */
    public ConsultadeTitulosRequestResponse createConsultadeTitulosRequestResponse() {
        return new ConsultadeTitulosRequestResponse();
    }

    /**
     * Create an instance of {@link ConsWsFault }
     * 
     */
    public ConsWsFault createConsWsFault() {
        return new ConsWsFault();
    }

    /**
     * Create an instance of {@link GraduadoReporteDTO }
     * 
     */
    public GraduadoReporteDTO createGraduadoReporteDTO() {
        return new GraduadoReporteDTO();
    }

    /**
     * Create an instance of {@link NivelTitulosDTO }
     * 
     */
    public NivelTitulosDTO createNivelTitulosDTO() {
        return new NivelTitulosDTO();
    }

    /**
     * Create an instance of {@link TituloRegistradoDTO }
     * 
     */
    public TituloRegistradoDTO createTituloRegistradoDTO() {
        return new TituloRegistradoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", name = "ConsWsFault")
    public JAXBElement<Object> createConsWsFault(Object value) {
        return new JAXBElement<Object>(_ConsWsFault_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultadeTitulosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", name = "ConsultadeTitulosRequest")
    public JAXBElement<ConsultadeTitulosRequest> createConsultadeTitulosRequest(ConsultadeTitulosRequest value) {
        return new JAXBElement<ConsultadeTitulosRequest>(_ConsultadeTitulosRequest_QNAME, ConsultadeTitulosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultadeTitulosRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo", name = "ConsultadeTitulosRequestResponse")
    public JAXBElement<ConsultadeTitulosRequestResponse> createConsultadeTitulosRequestResponse(ConsultadeTitulosRequestResponse value) {
        return new JAXBElement<ConsultadeTitulosRequestResponse>(_ConsultadeTitulosRequestResponse_QNAME, ConsultadeTitulosRequestResponse.class, null, value);
    }

}
