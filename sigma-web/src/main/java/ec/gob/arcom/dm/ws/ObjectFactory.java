
package ec.gob.arcom.dm.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.arcom.dm.ws package. 
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

    private final static QName _ConsultarPorCodigo_QNAME = new QName("http://ws.dm.arcom.gob.ec/", "consultarPorCodigo");
    private final static QName _ConsultarPorCodigoResponse_QNAME = new QName("http://ws.dm.arcom.gob.ec/", "consultarPorCodigoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.arcom.dm.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarPorCodigo }
     * 
     */
    public ConsultarPorCodigo createConsultarPorCodigo() {
        return new ConsultarPorCodigo();
    }

    /**
     * Create an instance of {@link ConsultarPorCodigoResponse }
     * 
     */
    public ConsultarPorCodigoResponse createConsultarPorCodigoResponse() {
        return new ConsultarPorCodigoResponse();
    }

    /**
     * Create an instance of {@link DerechoMineroMAEDTO }
     * 
     */
    public DerechoMineroMAEDTO createDerechoMineroMAEDTO() {
        return new DerechoMineroMAEDTO();
    }

    /**
     * Create an instance of {@link Coordenadas }
     * 
     */
    public Coordenadas createCoordenadas() {
        return new Coordenadas();
    }

    /**
     * Create an instance of {@link DerechoMinero }
     * 
     */
    public DerechoMinero createDerechoMinero() {
        return new DerechoMinero();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorCodigo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dm.arcom.gob.ec/", name = "consultarPorCodigo")
    public JAXBElement<ConsultarPorCodigo> createConsultarPorCodigo(ConsultarPorCodigo value) {
        return new JAXBElement<ConsultarPorCodigo>(_ConsultarPorCodigo_QNAME, ConsultarPorCodigo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPorCodigoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dm.arcom.gob.ec/", name = "consultarPorCodigoResponse")
    public JAXBElement<ConsultarPorCodigoResponse> createConsultarPorCodigoResponse(ConsultarPorCodigoResponse value) {
        return new JAXBElement<ConsultarPorCodigoResponse>(_ConsultarPorCodigoResponse_QNAME, ConsultarPorCodigoResponse.class, null, value);
    }

}
