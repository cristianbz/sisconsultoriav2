
package ec.gob.turismo.ws.catastro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.turismo.ws.catastro package. 
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

    private final static QName _ConsultarPorCedula_QNAME = new QName("http://ws.turismo.gob.ec/Catastro", "ConsultarPorCedula");
    private final static QName _ConsultarPorCedulaResponse_QNAME = new QName("http://ws.turismo.gob.ec/Catastro", "ConsultarPorCedulaResponse");
    private final static QName _ConsultarPorFecha_QNAME = new QName("http://ws.turismo.gob.ec/Catastro", "ConsultarPorFecha");
    private final static QName _ConsultarPorFechaResponse_QNAME = new QName("http://ws.turismo.gob.ec/Catastro", "ConsultarPorFechaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.turismo.ws.catastro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Respuesta }
     * 
     */
    public Respuesta createRespuesta() {
        return new Respuesta();
    }

    /**
     * Create an instance of {@link ConsultarPorCedula }
     * 
     */
    public ConsultarPorCedula createConsultarPorCedula() {
        return new ConsultarPorCedula();
    }

    /**
     * Create an instance of {@link ConsultarPorCedulaResponse }
     * 
     */
    public ConsultarPorCedulaResponse createConsultarPorCedulaResponse() {
        return new ConsultarPorCedulaResponse();
    }

    /**
     * Create an instance of {@link ConsultarPorFecha }
     * 
     */
    public ConsultarPorFecha createConsultarPorFecha() {
        return new ConsultarPorFecha();
    }

    /**
     * Create an instance of {@link ConsultarPorFechaResponse }
     * 
     */
    public ConsultarPorFechaResponse createConsultarPorFechaResponse() {
        return new ConsultarPorFechaResponse();
    }

    /**
     * Create an instance of {@link GuiaCatastro }
     * 
     */
    public GuiaCatastro createGuiaCatastro() {
        return new GuiaCatastro();
    }

    /**
     * Create an instance of {@link Respuesta.GuiasCatastro }
     * 
     */
    public Respuesta.GuiasCatastro createRespuestaGuiasCatastro() {
        return new Respuesta.GuiasCatastro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorCedula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.turismo.gob.ec/Catastro", name = "ConsultarPorCedula")
    public JAXBElement<ConsultarPorCedula> createConsultarPorCedula(ConsultarPorCedula value) {
        return new JAXBElement<ConsultarPorCedula>(_ConsultarPorCedula_QNAME, ConsultarPorCedula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorCedulaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.turismo.gob.ec/Catastro", name = "ConsultarPorCedulaResponse")
    public JAXBElement<ConsultarPorCedulaResponse> createConsultarPorCedulaResponse(ConsultarPorCedulaResponse value) {
        return new JAXBElement<ConsultarPorCedulaResponse>(_ConsultarPorCedulaResponse_QNAME, ConsultarPorCedulaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorFecha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.turismo.gob.ec/Catastro", name = "ConsultarPorFecha")
    public JAXBElement<ConsultarPorFecha> createConsultarPorFecha(ConsultarPorFecha value) {
        return new JAXBElement<ConsultarPorFecha>(_ConsultarPorFecha_QNAME, ConsultarPorFecha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorFechaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.turismo.gob.ec/Catastro", name = "ConsultarPorFechaResponse")
    public JAXBElement<ConsultarPorFechaResponse> createConsultarPorFechaResponse(ConsultarPorFechaResponse value) {
        return new JAXBElement<ConsultarPorFechaResponse>(_ConsultarPorFechaResponse_QNAME, ConsultarPorFechaResponse.class, null, value);
    }

}
