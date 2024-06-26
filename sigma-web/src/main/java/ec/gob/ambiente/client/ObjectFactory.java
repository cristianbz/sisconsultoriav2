
package ec.gob.ambiente.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.ambiente.client package. 
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

    private final static QName _GetCedula_QNAME = new QName("http://client.ambiente.gob.ec/", "getCedula");
    private final static QName _GetCedulaOld_QNAME = new QName("http://client.ambiente.gob.ec/", "getCedulaOld");
    private final static QName _GetCedulaOldResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getCedulaOldResponse");
    private final static QName _GetCedulaResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getCedulaResponse");
    private final static QName _GetConsultaGuiasPorCedula_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultaGuiasPorCedula");
    private final static QName _GetConsultaGuiasPorCedulaResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultaGuiasPorCedulaResponse");
    private final static QName _GetConsultaGuiasPorFecha_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultaGuiasPorFecha");
    private final static QName _GetConsultaGuiasPorFechaResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultaGuiasPorFechaResponse");
    private final static QName _GetConsultarCatastral_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultarCatastral");
    private final static QName _GetConsultarCatastralResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getConsultarCatastralResponse");
    private final static QName _GetLicencia_QNAME = new QName("http://client.ambiente.gob.ec/", "getLicencia");
    private final static QName _GetLicenciaResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getLicenciaResponse");
    private final static QName _GetMatricula_QNAME = new QName("http://client.ambiente.gob.ec/", "getMatricula");
    private final static QName _GetMatriculaResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getMatriculaResponse");
    private final static QName _GetName_QNAME = new QName("http://client.ambiente.gob.ec/", "getName");
    private final static QName _GetNameOld_QNAME = new QName("http://client.ambiente.gob.ec/", "getNameOld");
    private final static QName _GetNameOldResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getNameOldResponse");
    private final static QName _GetNameResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getNameResponse");
    private final static QName _GetRuc_QNAME = new QName("http://client.ambiente.gob.ec/", "getRuc");
    private final static QName _GetRucResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getRucResponse");
    private final static QName _GetTitulo_QNAME = new QName("http://client.ambiente.gob.ec/", "getTitulo");
    private final static QName _GetTituloResponse_QNAME = new QName("http://client.ambiente.gob.ec/", "getTituloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.ambiente.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCedula }
     * 
     */
    public GetCedula createGetCedula() {
        return new GetCedula();
    }

    /**
     * Create an instance of {@link GetCedulaOld }
     * 
     */
    public GetCedulaOld createGetCedulaOld() {
        return new GetCedulaOld();
    }

    /**
     * Create an instance of {@link GetCedulaOldResponse }
     * 
     */
    public GetCedulaOldResponse createGetCedulaOldResponse() {
        return new GetCedulaOldResponse();
    }

    /**
     * Create an instance of {@link GetCedulaResponse }
     * 
     */
    public GetCedulaResponse createGetCedulaResponse() {
        return new GetCedulaResponse();
    }

    /**
     * Create an instance of {@link GetConsultaGuiasPorCedula }
     * 
     */
    public GetConsultaGuiasPorCedula createGetConsultaGuiasPorCedula() {
        return new GetConsultaGuiasPorCedula();
    }

    /**
     * Create an instance of {@link GetConsultaGuiasPorCedulaResponse }
     * 
     */
    public GetConsultaGuiasPorCedulaResponse createGetConsultaGuiasPorCedulaResponse() {
        return new GetConsultaGuiasPorCedulaResponse();
    }

    /**
     * Create an instance of {@link GetConsultaGuiasPorFecha }
     * 
     */
    public GetConsultaGuiasPorFecha createGetConsultaGuiasPorFecha() {
        return new GetConsultaGuiasPorFecha();
    }

    /**
     * Create an instance of {@link GetConsultaGuiasPorFechaResponse }
     * 
     */
    public GetConsultaGuiasPorFechaResponse createGetConsultaGuiasPorFechaResponse() {
        return new GetConsultaGuiasPorFechaResponse();
    }

    /**
     * Create an instance of {@link GetConsultarCatastral }
     * 
     */
    public GetConsultarCatastral createGetConsultarCatastral() {
        return new GetConsultarCatastral();
    }

    /**
     * Create an instance of {@link GetConsultarCatastralResponse }
     * 
     */
    public GetConsultarCatastralResponse createGetConsultarCatastralResponse() {
        return new GetConsultarCatastralResponse();
    }

    /**
     * Create an instance of {@link GetLicencia }
     * 
     */
    public GetLicencia createGetLicencia() {
        return new GetLicencia();
    }

    /**
     * Create an instance of {@link GetLicenciaResponse }
     * 
     */
    public GetLicenciaResponse createGetLicenciaResponse() {
        return new GetLicenciaResponse();
    }

    /**
     * Create an instance of {@link GetMatricula }
     * 
     */
    public GetMatricula createGetMatricula() {
        return new GetMatricula();
    }

    /**
     * Create an instance of {@link GetMatriculaResponse }
     * 
     */
    public GetMatriculaResponse createGetMatriculaResponse() {
        return new GetMatriculaResponse();
    }

    /**
     * Create an instance of {@link GetName }
     * 
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link GetNameOld }
     * 
     */
    public GetNameOld createGetNameOld() {
        return new GetNameOld();
    }

    /**
     * Create an instance of {@link GetNameOldResponse }
     * 
     */
    public GetNameOldResponse createGetNameOldResponse() {
        return new GetNameOldResponse();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     * 
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link GetRuc }
     * 
     */
    public GetRuc createGetRuc() {
        return new GetRuc();
    }

    /**
     * Create an instance of {@link GetRucResponse }
     * 
     */
    public GetRucResponse createGetRucResponse() {
        return new GetRucResponse();
    }

    /**
     * Create an instance of {@link GetTitulo }
     * 
     */
    public GetTitulo createGetTitulo() {
        return new GetTitulo();
    }

    /**
     * Create an instance of {@link GetTituloResponse }
     * 
     */
    public GetTituloResponse createGetTituloResponse() {
        return new GetTituloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCedula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getCedula")
    public JAXBElement<GetCedula> createGetCedula(GetCedula value) {
        return new JAXBElement<GetCedula>(_GetCedula_QNAME, GetCedula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCedulaOld }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getCedulaOld")
    public JAXBElement<GetCedulaOld> createGetCedulaOld(GetCedulaOld value) {
        return new JAXBElement<GetCedulaOld>(_GetCedulaOld_QNAME, GetCedulaOld.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCedulaOldResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getCedulaOldResponse")
    public JAXBElement<GetCedulaOldResponse> createGetCedulaOldResponse(GetCedulaOldResponse value) {
        return new JAXBElement<GetCedulaOldResponse>(_GetCedulaOldResponse_QNAME, GetCedulaOldResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCedulaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getCedulaResponse")
    public JAXBElement<GetCedulaResponse> createGetCedulaResponse(GetCedulaResponse value) {
        return new JAXBElement<GetCedulaResponse>(_GetCedulaResponse_QNAME, GetCedulaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultaGuiasPorCedula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultaGuiasPorCedula")
    public JAXBElement<GetConsultaGuiasPorCedula> createGetConsultaGuiasPorCedula(GetConsultaGuiasPorCedula value) {
        return new JAXBElement<GetConsultaGuiasPorCedula>(_GetConsultaGuiasPorCedula_QNAME, GetConsultaGuiasPorCedula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultaGuiasPorCedulaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultaGuiasPorCedulaResponse")
    public JAXBElement<GetConsultaGuiasPorCedulaResponse> createGetConsultaGuiasPorCedulaResponse(GetConsultaGuiasPorCedulaResponse value) {
        return new JAXBElement<GetConsultaGuiasPorCedulaResponse>(_GetConsultaGuiasPorCedulaResponse_QNAME, GetConsultaGuiasPorCedulaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultaGuiasPorFecha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultaGuiasPorFecha")
    public JAXBElement<GetConsultaGuiasPorFecha> createGetConsultaGuiasPorFecha(GetConsultaGuiasPorFecha value) {
        return new JAXBElement<GetConsultaGuiasPorFecha>(_GetConsultaGuiasPorFecha_QNAME, GetConsultaGuiasPorFecha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultaGuiasPorFechaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultaGuiasPorFechaResponse")
    public JAXBElement<GetConsultaGuiasPorFechaResponse> createGetConsultaGuiasPorFechaResponse(GetConsultaGuiasPorFechaResponse value) {
        return new JAXBElement<GetConsultaGuiasPorFechaResponse>(_GetConsultaGuiasPorFechaResponse_QNAME, GetConsultaGuiasPorFechaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultarCatastral }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultarCatastral")
    public JAXBElement<GetConsultarCatastral> createGetConsultarCatastral(GetConsultarCatastral value) {
        return new JAXBElement<GetConsultarCatastral>(_GetConsultarCatastral_QNAME, GetConsultarCatastral.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConsultarCatastralResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getConsultarCatastralResponse")
    public JAXBElement<GetConsultarCatastralResponse> createGetConsultarCatastralResponse(GetConsultarCatastralResponse value) {
        return new JAXBElement<GetConsultarCatastralResponse>(_GetConsultarCatastralResponse_QNAME, GetConsultarCatastralResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLicencia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getLicencia")
    public JAXBElement<GetLicencia> createGetLicencia(GetLicencia value) {
        return new JAXBElement<GetLicencia>(_GetLicencia_QNAME, GetLicencia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLicenciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getLicenciaResponse")
    public JAXBElement<GetLicenciaResponse> createGetLicenciaResponse(GetLicenciaResponse value) {
        return new JAXBElement<GetLicenciaResponse>(_GetLicenciaResponse_QNAME, GetLicenciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMatricula }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getMatricula")
    public JAXBElement<GetMatricula> createGetMatricula(GetMatricula value) {
        return new JAXBElement<GetMatricula>(_GetMatricula_QNAME, GetMatricula.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMatriculaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getMatriculaResponse")
    public JAXBElement<GetMatriculaResponse> createGetMatriculaResponse(GetMatriculaResponse value) {
        return new JAXBElement<GetMatriculaResponse>(_GetMatriculaResponse_QNAME, GetMatriculaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameOld }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getNameOld")
    public JAXBElement<GetNameOld> createGetNameOld(GetNameOld value) {
        return new JAXBElement<GetNameOld>(_GetNameOld_QNAME, GetNameOld.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameOldResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getNameOldResponse")
    public JAXBElement<GetNameOldResponse> createGetNameOldResponse(GetNameOldResponse value) {
        return new JAXBElement<GetNameOldResponse>(_GetNameOldResponse_QNAME, GetNameOldResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRuc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getRuc")
    public JAXBElement<GetRuc> createGetRuc(GetRuc value) {
        return new JAXBElement<GetRuc>(_GetRuc_QNAME, GetRuc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRucResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getRucResponse")
    public JAXBElement<GetRucResponse> createGetRucResponse(GetRucResponse value) {
        return new JAXBElement<GetRucResponse>(_GetRucResponse_QNAME, GetRucResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTitulo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getTitulo")
    public JAXBElement<GetTitulo> createGetTitulo(GetTitulo value) {
        return new JAXBElement<GetTitulo>(_GetTitulo_QNAME, GetTitulo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTituloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.ambiente.gob.ec/", name = "getTituloResponse")
    public JAXBElement<GetTituloResponse> createGetTituloResponse(GetTituloResponse value) {
        return new JAXBElement<GetTituloResponse>(_GetTituloResponse_QNAME, GetTituloResponse.class, null, value);
    }

}
