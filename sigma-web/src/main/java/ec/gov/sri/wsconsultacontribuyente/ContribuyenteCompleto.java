
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contribuyenteCompleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contribuyenteCompleto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://sri.gov.ec/wsConsultaContribuyente}contribuyente"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="agenteRetencion" type="{http://sri.gov.ec/wsConsultaContribuyente}agenteRetencion" minOccurs="0"/&gt;
 *         &lt;element name="calificacionArtesanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contador" type="{http://sri.gov.ec/wsConsultaContribuyente}contador" minOccurs="0"/&gt;
 *         &lt;element name="direccionLarga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estructuraOrganizacional" type="{http://sri.gov.ec/wsConsultaContribuyente}estructuraOrganizacional" minOccurs="0"/&gt;
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaAltaParaEspecial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaCalificacionArtesanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaCambioObligado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaInicioActividades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaNotificacionEspeciales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaUltimaDeclaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaBlanca" type="{http://sri.gov.ec/wsConsultaContribuyente}listaBlanca" minOccurs="0"/&gt;
 *         &lt;element name="numeroCalificacionArtesanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="obligadoContabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="representanteLegal" type="{http://sri.gov.ec/wsConsultaContribuyente}representanteLegal" minOccurs="0"/&gt;
 *         &lt;element name="resolucionAltaParaEspecial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoCalificacionArtesanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ultimoPeriodoFiscalCumplido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contribuyenteCompleto", propOrder = {
    "agenteRetencion",
    "calificacionArtesanal",
    "contador",
    "direccionLarga",
    "estructuraOrganizacional",
    "fax",
    "fechaAltaParaEspecial",
    "fechaCalificacionArtesanal",
    "fechaCambioObligado",
    "fechaInicioActividades",
    "fechaNacimiento",
    "fechaNotificacionEspeciales",
    "fechaUltimaDeclaracion",
    "listaBlanca",
    "numeroCalificacionArtesanal",
    "obligadoContabilidad",
    "representanteLegal",
    "resolucionAltaParaEspecial",
    "tipoCalificacionArtesanal",
    "ultimoPeriodoFiscalCumplido"
})
public class ContribuyenteCompleto
    extends Contribuyente
{

    protected AgenteRetencion agenteRetencion;
    protected String calificacionArtesanal;
    protected Contador contador;
    protected String direccionLarga;
    protected EstructuraOrganizacional estructuraOrganizacional;
    protected String fax;
    protected String fechaAltaParaEspecial;
    protected String fechaCalificacionArtesanal;
    protected String fechaCambioObligado;
    protected String fechaInicioActividades;
    protected String fechaNacimiento;
    protected String fechaNotificacionEspeciales;
    protected String fechaUltimaDeclaracion;
    protected ListaBlanca listaBlanca;
    protected String numeroCalificacionArtesanal;
    protected String obligadoContabilidad;
    protected RepresentanteLegal representanteLegal;
    protected String resolucionAltaParaEspecial;
    protected String tipoCalificacionArtesanal;
    protected String ultimoPeriodoFiscalCumplido;

    /**
     * Gets the value of the agenteRetencion property.
     * 
     * @return
     *     possible object is
     *     {@link AgenteRetencion }
     *     
     */
    public AgenteRetencion getAgenteRetencion() {
        return agenteRetencion;
    }

    /**
     * Sets the value of the agenteRetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgenteRetencion }
     *     
     */
    public void setAgenteRetencion(AgenteRetencion value) {
        this.agenteRetencion = value;
    }

    /**
     * Gets the value of the calificacionArtesanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalificacionArtesanal() {
        return calificacionArtesanal;
    }

    /**
     * Sets the value of the calificacionArtesanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalificacionArtesanal(String value) {
        this.calificacionArtesanal = value;
    }

    /**
     * Gets the value of the contador property.
     * 
     * @return
     *     possible object is
     *     {@link Contador }
     *     
     */
    public Contador getContador() {
        return contador;
    }

    /**
     * Sets the value of the contador property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contador }
     *     
     */
    public void setContador(Contador value) {
        this.contador = value;
    }

    /**
     * Gets the value of the direccionLarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionLarga() {
        return direccionLarga;
    }

    /**
     * Sets the value of the direccionLarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionLarga(String value) {
        this.direccionLarga = value;
    }

    /**
     * Gets the value of the estructuraOrganizacional property.
     * 
     * @return
     *     possible object is
     *     {@link EstructuraOrganizacional }
     *     
     */
    public EstructuraOrganizacional getEstructuraOrganizacional() {
        return estructuraOrganizacional;
    }

    /**
     * Sets the value of the estructuraOrganizacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstructuraOrganizacional }
     *     
     */
    public void setEstructuraOrganizacional(EstructuraOrganizacional value) {
        this.estructuraOrganizacional = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the fechaAltaParaEspecial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAltaParaEspecial() {
        return fechaAltaParaEspecial;
    }

    /**
     * Sets the value of the fechaAltaParaEspecial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAltaParaEspecial(String value) {
        this.fechaAltaParaEspecial = value;
    }

    /**
     * Gets the value of the fechaCalificacionArtesanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCalificacionArtesanal() {
        return fechaCalificacionArtesanal;
    }

    /**
     * Sets the value of the fechaCalificacionArtesanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCalificacionArtesanal(String value) {
        this.fechaCalificacionArtesanal = value;
    }

    /**
     * Gets the value of the fechaCambioObligado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCambioObligado() {
        return fechaCambioObligado;
    }

    /**
     * Sets the value of the fechaCambioObligado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCambioObligado(String value) {
        this.fechaCambioObligado = value;
    }

    /**
     * Gets the value of the fechaInicioActividades property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioActividades() {
        return fechaInicioActividades;
    }

    /**
     * Sets the value of the fechaInicioActividades property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioActividades(String value) {
        this.fechaInicioActividades = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the fechaNotificacionEspeciales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNotificacionEspeciales() {
        return fechaNotificacionEspeciales;
    }

    /**
     * Sets the value of the fechaNotificacionEspeciales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNotificacionEspeciales(String value) {
        this.fechaNotificacionEspeciales = value;
    }

    /**
     * Gets the value of the fechaUltimaDeclaracion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltimaDeclaracion() {
        return fechaUltimaDeclaracion;
    }

    /**
     * Sets the value of the fechaUltimaDeclaracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltimaDeclaracion(String value) {
        this.fechaUltimaDeclaracion = value;
    }

    /**
     * Gets the value of the listaBlanca property.
     * 
     * @return
     *     possible object is
     *     {@link ListaBlanca }
     *     
     */
    public ListaBlanca getListaBlanca() {
        return listaBlanca;
    }

    /**
     * Sets the value of the listaBlanca property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaBlanca }
     *     
     */
    public void setListaBlanca(ListaBlanca value) {
        this.listaBlanca = value;
    }

    /**
     * Gets the value of the numeroCalificacionArtesanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCalificacionArtesanal() {
        return numeroCalificacionArtesanal;
    }

    /**
     * Sets the value of the numeroCalificacionArtesanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCalificacionArtesanal(String value) {
        this.numeroCalificacionArtesanal = value;
    }

    /**
     * Gets the value of the obligadoContabilidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    /**
     * Sets the value of the obligadoContabilidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligadoContabilidad(String value) {
        this.obligadoContabilidad = value;
    }

    /**
     * Gets the value of the representanteLegal property.
     * 
     * @return
     *     possible object is
     *     {@link RepresentanteLegal }
     *     
     */
    public RepresentanteLegal getRepresentanteLegal() {
        return representanteLegal;
    }

    /**
     * Sets the value of the representanteLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepresentanteLegal }
     *     
     */
    public void setRepresentanteLegal(RepresentanteLegal value) {
        this.representanteLegal = value;
    }

    /**
     * Gets the value of the resolucionAltaParaEspecial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolucionAltaParaEspecial() {
        return resolucionAltaParaEspecial;
    }

    /**
     * Sets the value of the resolucionAltaParaEspecial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolucionAltaParaEspecial(String value) {
        this.resolucionAltaParaEspecial = value;
    }

    /**
     * Gets the value of the tipoCalificacionArtesanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCalificacionArtesanal() {
        return tipoCalificacionArtesanal;
    }

    /**
     * Sets the value of the tipoCalificacionArtesanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCalificacionArtesanal(String value) {
        this.tipoCalificacionArtesanal = value;
    }

    /**
     * Gets the value of the ultimoPeriodoFiscalCumplido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimoPeriodoFiscalCumplido() {
        return ultimoPeriodoFiscalCumplido;
    }

    /**
     * Sets the value of the ultimoPeriodoFiscalCumplido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimoPeriodoFiscalCumplido(String value) {
        this.ultimoPeriodoFiscalCumplido = value;
    }

}
