
package ec.gob.registrocivil;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.registrocivil package. 
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

    private final static QName _BusquedaPorNombre_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNombre");
    private final static QName _BusquedaPorNombreResponse_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNombreResponse");
    private final static QName _BusquedaPorNui_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNui");
    private final static QName _BusquedaPorNuiIndividual_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNuiIndividual");
    private final static QName _BusquedaPorNuiIndividualResponse_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNuiIndividualResponse");
    private final static QName _BusquedaPorNuiResponse_QNAME = new QName("http://www.registrocivil.gob.ec", "BusquedaPorNuiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.registrocivil
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link BusquedaPorNui }
     * 
     */
    public BusquedaPorNui createBusquedaPorNui() {
        return new BusquedaPorNui();
    }

    /**
     * Create an instance of {@link BusquedaPorNuiIndividual }
     * 
     */
    public BusquedaPorNuiIndividual createBusquedaPorNuiIndividual() {
        return new BusquedaPorNuiIndividual();
    }

    /**
     * Create an instance of {@link BusquedaPorNuiIndividualResponse }
     * 
     */
    public BusquedaPorNuiIndividualResponse createBusquedaPorNuiIndividualResponse() {
        return new BusquedaPorNuiIndividualResponse();
    }

    /**
     * Create an instance of {@link BusquedaPorNuiResponse }
     * 
     */
    public BusquedaPorNuiResponse createBusquedaPorNuiResponse() {
        return new BusquedaPorNuiResponse();
    }

    /**
     * Create an instance of {@link Ciudadanos }
     * 
     */
    public Ciudadanos createCiudadanos() {
        return new Ciudadanos();
    }

    /**
     * Create an instance of {@link Ciudadano }
     * 
     */
    public Ciudadano createCiudadano() {
        return new Ciudadano();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNombre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNombre")
    public JAXBElement<BusquedaPorNombre> createBusquedaPorNombre(BusquedaPorNombre value) {
        return new JAXBElement<BusquedaPorNombre>(_BusquedaPorNombre_QNAME, BusquedaPorNombre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNombreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNombreResponse")
    public JAXBElement<BusquedaPorNombreResponse> createBusquedaPorNombreResponse(BusquedaPorNombreResponse value) {
        return new JAXBElement<BusquedaPorNombreResponse>(_BusquedaPorNombreResponse_QNAME, BusquedaPorNombreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNui }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNui")
    public JAXBElement<BusquedaPorNui> createBusquedaPorNui(BusquedaPorNui value) {
        return new JAXBElement<BusquedaPorNui>(_BusquedaPorNui_QNAME, BusquedaPorNui.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNuiIndividual }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNuiIndividual")
    public JAXBElement<BusquedaPorNuiIndividual> createBusquedaPorNuiIndividual(BusquedaPorNuiIndividual value) {
        return new JAXBElement<BusquedaPorNuiIndividual>(_BusquedaPorNuiIndividual_QNAME, BusquedaPorNuiIndividual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNuiIndividualResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNuiIndividualResponse")
    public JAXBElement<BusquedaPorNuiIndividualResponse> createBusquedaPorNuiIndividualResponse(BusquedaPorNuiIndividualResponse value) {
        return new JAXBElement<BusquedaPorNuiIndividualResponse>(_BusquedaPorNuiIndividualResponse_QNAME, BusquedaPorNuiIndividualResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaPorNuiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.registrocivil.gob.ec", name = "BusquedaPorNuiResponse")
    public JAXBElement<BusquedaPorNuiResponse> createBusquedaPorNuiResponse(BusquedaPorNuiResponse value) {
        return new JAXBElement<BusquedaPorNuiResponse>(_BusquedaPorNuiResponse_QNAME, BusquedaPorNuiResponse.class, null, value);
    }

}
