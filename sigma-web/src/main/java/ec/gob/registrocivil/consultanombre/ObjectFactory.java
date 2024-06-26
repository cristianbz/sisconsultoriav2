
package ec.gob.registrocivil.consultanombre;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.registrocivil.consultanombre package. 
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

    private final static QName _WsUp_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaNombre", "WsUp");
    private final static QName _WsUpResponse_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaNombre", "WsUpResponse");
    private final static QName _BusquedaPorNombre_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaNombre", "busquedaPorNombre");
    private final static QName _BusquedaPorNombreResponse_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaNombre", "busquedaPorNombreResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.registrocivil.consultanombre
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WsUp }
     * 
     */
    public WsUp createWsUp() {
        return new WsUp();
    }

    /**
     * Create an instance of {@link WsUpResponse }
     * 
     */
    public WsUpResponse createWsUpResponse() {
        return new WsUpResponse();
    }

    /**
     * Create an instance of {@link BusquedaPorNombre }
     * 
     */
    public BusquedaPorNombre createBusquedaPorNombre() {
        return new BusquedaPorNombre();
    }

    /**
     * Create an instance of {@link BusquedaPorNombreResponse }
     * 
     */
    public BusquedaPorNombreResponse createBusquedaPorNombreResponse() {
        return new BusquedaPorNombreResponse();
    }

    /**
     * Create an instance of {@link Ciudadanos }
     * 
     */
    public Ciudadanos createCiudadanos() {
        return new Ciudadanos();
    }

    /**
     * Create an instance of {@link Cedula }
     * 
     */
    public Cedula createCedula() {
        return new Cedula();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaNombre", name = "WsUp")
    public JAXBElement<WsUp> createWsUp(WsUp value) {
        return new JAXBElement<WsUp>(_WsUp_QNAME, WsUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaNombre", name = "WsUpResponse")
    public JAXBElement<WsUpResponse> createWsUpResponse(WsUpResponse value) {
        return new JAXBElement<WsUpResponse>(_WsUpResponse_QNAME, WsUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNombre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaNombre", name = "busquedaPorNombre")
    public JAXBElement<BusquedaPorNombre> createBusquedaPorNombre(BusquedaPorNombre value) {
        return new JAXBElement<BusquedaPorNombre>(_BusquedaPorNombre_QNAME, BusquedaPorNombre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNombreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaNombre", name = "busquedaPorNombreResponse")
    public JAXBElement<BusquedaPorNombreResponse> createBusquedaPorNombreResponse(BusquedaPorNombreResponse value) {
        return new JAXBElement<BusquedaPorNombreResponse>(_BusquedaPorNombreResponse_QNAME, BusquedaPorNombreResponse.class, null, value);
    }

}
