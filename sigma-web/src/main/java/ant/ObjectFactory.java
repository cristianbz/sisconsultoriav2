
package ant;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ant package. 
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

    private final static QName _IngresoCitaciones_QNAME = new QName("http://ant/", "Ingreso_Citaciones");
    private final static QName _IngresoCitacionesResponse_QNAME = new QName("http://ant/", "Ingreso_CitacionesResponse");
    private final static QName _SolicitaLicencia_QNAME = new QName("http://ant/", "Solicita_Licencia");
    private final static QName _SolicitaLicenciaResponse_QNAME = new QName("http://ant/", "Solicita_LicenciaResponse");
    private final static QName _SolicitaMatricula_QNAME = new QName("http://ant/", "Solicita_Matricula");
    private final static QName _SolicitaMatriculaResponse_QNAME = new QName("http://ant/", "Solicita_MatriculaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ant
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IngresoCitaciones }
     * 
     */
    public IngresoCitaciones createIngresoCitaciones() {
        return new IngresoCitaciones();
    }

    /**
     * Create an instance of {@link IngresoCitacionesResponse }
     * 
     */
    public IngresoCitacionesResponse createIngresoCitacionesResponse() {
        return new IngresoCitacionesResponse();
    }

    /**
     * Create an instance of {@link SolicitaLicencia }
     * 
     */
    public SolicitaLicencia createSolicitaLicencia() {
        return new SolicitaLicencia();
    }

    /**
     * Create an instance of {@link SolicitaLicenciaResponse }
     * 
     */
    public SolicitaLicenciaResponse createSolicitaLicenciaResponse() {
        return new SolicitaLicenciaResponse();
    }

    /**
     * Create an instance of {@link SolicitaMatricula }
     * 
     */
    public SolicitaMatricula createSolicitaMatricula() {
        return new SolicitaMatricula();
    }

    /**
     * Create an instance of {@link SolicitaMatriculaResponse }
     * 
     */
    public SolicitaMatriculaResponse createSolicitaMatriculaResponse() {
        return new SolicitaMatriculaResponse();
    }

    /**
     * Create an instance of {@link DatosMatricula }
     * 
     */
    public DatosMatricula createDatosMatricula() {
        return new DatosMatricula();
    }

    /**
     * Create an instance of {@link DatosLicencia }
     * 
     */
    public DatosLicencia createDatosLicencia() {
        return new DatosLicencia();
    }

    /**
     * Create an instance of {@link IngresoCitacion }
     * 
     */
    public IngresoCitacion createIngresoCitacion() {
        return new IngresoCitacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IngresoCitaciones }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Ingreso_Citaciones")
    public JAXBElement<IngresoCitaciones> createIngresoCitaciones(IngresoCitaciones value) {
        return new JAXBElement<IngresoCitaciones>(_IngresoCitaciones_QNAME, IngresoCitaciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IngresoCitacionesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Ingreso_CitacionesResponse")
    public JAXBElement<IngresoCitacionesResponse> createIngresoCitacionesResponse(IngresoCitacionesResponse value) {
        return new JAXBElement<IngresoCitacionesResponse>(_IngresoCitacionesResponse_QNAME, IngresoCitacionesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitaLicencia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Solicita_Licencia")
    public JAXBElement<SolicitaLicencia> createSolicitaLicencia(SolicitaLicencia value) {
        return new JAXBElement<SolicitaLicencia>(_SolicitaLicencia_QNAME, SolicitaLicencia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitaLicenciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Solicita_LicenciaResponse")
    public JAXBElement<SolicitaLicenciaResponse> createSolicitaLicenciaResponse(SolicitaLicenciaResponse value) {
        return new JAXBElement<SolicitaLicenciaResponse>(_SolicitaLicenciaResponse_QNAME, SolicitaLicenciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitaMatricula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Solicita_Matricula")
    public JAXBElement<SolicitaMatricula> createSolicitaMatricula(SolicitaMatricula value) {
        return new JAXBElement<SolicitaMatricula>(_SolicitaMatricula_QNAME, SolicitaMatricula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitaMatriculaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ant/", name = "Solicita_MatriculaResponse")
    public JAXBElement<SolicitaMatriculaResponse> createSolicitaMatriculaResponse(SolicitaMatriculaResponse value) {
        return new JAXBElement<SolicitaMatriculaResponse>(_SolicitaMatriculaResponse_QNAME, SolicitaMatriculaResponse.class, null, value);
    }

}
