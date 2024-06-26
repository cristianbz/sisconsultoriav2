
package ec.gob.registrocivil.consultacedula;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.registrocivil.consultacedula package. 
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

    private final static QName _BusquedaPorCedula_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaCedula", "BusquedaPorCedula");
    private final static QName _BusquedaPorCedulaResponse_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaCedula", "BusquedaPorCedulaResponse");
    private final static QName _WsUp_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaCedula", "WsUp");
    private final static QName _WsUpResponse_QNAME = new QName("http://www.registrocivil.gob.ec/ConsultaCedula", "WsUpResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.registrocivil.consultacedula
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusquedaPorCedula }
     * 
     */
    public BusquedaPorCedula createBusquedaPorCedula() {
        return new BusquedaPorCedula();
    }

    /**
     * Create an instance of {@link BusquedaPorCedulaResponse }
     * 
     */
    public BusquedaPorCedulaResponse createBusquedaPorCedulaResponse() {
        return new BusquedaPorCedulaResponse();
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
     * Create an instance of {@link Cedula }
     * 
     */
    public Cedula createCedula() {
        return new Cedula();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorCedula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaCedula", name = "BusquedaPorCedula")
    public JAXBElement<BusquedaPorCedula> createBusquedaPorCedula(BusquedaPorCedula value) {
        return new JAXBElement<BusquedaPorCedula>(_BusquedaPorCedula_QNAME, BusquedaPorCedula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorCedulaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaCedula", name = "BusquedaPorCedulaResponse")
    public JAXBElement<BusquedaPorCedulaResponse> createBusquedaPorCedulaResponse(BusquedaPorCedulaResponse value) {
        return new JAXBElement<BusquedaPorCedulaResponse>(_BusquedaPorCedulaResponse_QNAME, BusquedaPorCedulaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaCedula", name = "WsUp")
    public JAXBElement<WsUp> createWsUp(WsUp value) {
        return new JAXBElement<WsUp>(_WsUp_QNAME, WsUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec/ConsultaCedula", name = "WsUpResponse")
    public JAXBElement<WsUpResponse> createWsUpResponse(WsUpResponse value) {
        return new JAXBElement<WsUpResponse>(_WsUpResponse_QNAME, WsUpResponse.class, null, value);
    }

}
