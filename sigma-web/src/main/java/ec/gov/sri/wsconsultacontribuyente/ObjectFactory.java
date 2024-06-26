
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gov.sri.wsconsultacontribuyente package. 
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

    private final static QName _ObtenerCompleto_QNAME = new QName("http://sri.gov.ec/wsConsultaContribuyente", "obtenerCompleto");
    private final static QName _ObtenerCompletoResponse_QNAME = new QName("http://sri.gov.ec/wsConsultaContribuyente", "obtenerCompletoResponse");
    private final static QName _ObtenerSimple_QNAME = new QName("http://sri.gov.ec/wsConsultaContribuyente", "obtenerSimple");
    private final static QName _ObtenerSimpleResponse_QNAME = new QName("http://sri.gov.ec/wsConsultaContribuyente", "obtenerSimpleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gov.sri.wsconsultacontribuyente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerCompleto }
     * 
     */
    public ObtenerCompleto createObtenerCompleto() {
        return new ObtenerCompleto();
    }

    /**
     * Create an instance of {@link ObtenerCompletoResponse }
     * 
     */
    public ObtenerCompletoResponse createObtenerCompletoResponse() {
        return new ObtenerCompletoResponse();
    }

    /**
     * Create an instance of {@link ObtenerSimple }
     * 
     */
    public ObtenerSimple createObtenerSimple() {
        return new ObtenerSimple();
    }

    /**
     * Create an instance of {@link ObtenerSimpleResponse }
     * 
     */
    public ObtenerSimpleResponse createObtenerSimpleResponse() {
        return new ObtenerSimpleResponse();
    }

    /**
     * Create an instance of {@link ContribuyenteCompleto }
     * 
     */
    public ContribuyenteCompleto createContribuyenteCompleto() {
        return new ContribuyenteCompleto();
    }

    /**
     * Create an instance of {@link Contribuyente }
     * 
     */
    public Contribuyente createContribuyente() {
        return new Contribuyente();
    }

    /**
     * Create an instance of {@link AgenteRetencion }
     * 
     */
    public AgenteRetencion createAgenteRetencion() {
        return new AgenteRetencion();
    }

    /**
     * Create an instance of {@link Contador }
     * 
     */
    public Contador createContador() {
        return new Contador();
    }

    /**
     * Create an instance of {@link EstructuraOrganizacional }
     * 
     */
    public EstructuraOrganizacional createEstructuraOrganizacional() {
        return new EstructuraOrganizacional();
    }

    /**
     * Create an instance of {@link ListaBlanca }
     * 
     */
    public ListaBlanca createListaBlanca() {
        return new ListaBlanca();
    }

    /**
     * Create an instance of {@link RepresentanteLegal }
     * 
     */
    public RepresentanteLegal createRepresentanteLegal() {
        return new RepresentanteLegal();
    }

    /**
     * Create an instance of {@link ActividadEconomica }
     * 
     */
    public ActividadEconomica createActividadEconomica() {
        return new ActividadEconomica();
    }

    /**
     * Create an instance of {@link TipoContribuyente }
     * 
     */
    public TipoContribuyente createTipoContribuyente() {
        return new TipoContribuyente();
    }

    /**
     * Create an instance of {@link UbicacionGeografica }
     * 
     */
    public UbicacionGeografica createUbicacionGeografica() {
        return new UbicacionGeografica();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCompleto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sri.gov.ec/wsConsultaContribuyente", name = "obtenerCompleto")
    public JAXBElement<ObtenerCompleto> createObtenerCompleto(ObtenerCompleto value) {
        return new JAXBElement<ObtenerCompleto>(_ObtenerCompleto_QNAME, ObtenerCompleto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCompletoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sri.gov.ec/wsConsultaContribuyente", name = "obtenerCompletoResponse")
    public JAXBElement<ObtenerCompletoResponse> createObtenerCompletoResponse(ObtenerCompletoResponse value) {
        return new JAXBElement<ObtenerCompletoResponse>(_ObtenerCompletoResponse_QNAME, ObtenerCompletoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerSimple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sri.gov.ec/wsConsultaContribuyente", name = "obtenerSimple")
    public JAXBElement<ObtenerSimple> createObtenerSimple(ObtenerSimple value) {
        return new JAXBElement<ObtenerSimple>(_ObtenerSimple_QNAME, ObtenerSimple.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerSimpleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sri.gov.ec/wsConsultaContribuyente", name = "obtenerSimpleResponse")
    public JAXBElement<ObtenerSimpleResponse> createObtenerSimpleResponse(ObtenerSimpleResponse value) {
        return new JAXBElement<ObtenerSimpleResponse>(_ObtenerSimpleResponse_QNAME, ObtenerSimpleResponse.class, null, value);
    }

}
