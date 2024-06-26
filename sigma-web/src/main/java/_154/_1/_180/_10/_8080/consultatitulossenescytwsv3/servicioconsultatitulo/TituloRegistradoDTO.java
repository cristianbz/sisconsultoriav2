
package _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tituloRegistradoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tituloRegistradoDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="areaCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="escalafonDocente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaGrado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ies" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iesExtranjeraColegioProfesional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listadoPertenece" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreClasificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreDetalleCampo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreTitulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reconocidoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subarea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subareaCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoExtranjeroColegio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoNivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoTitulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tituloRegistradoDTO", propOrder = {
    "area",
    "areaCodigo",
    "codigo",
    "escalafonDocente",
    "estado",
    "fechaGrado",
    "fechaRegistro",
    "ies",
    "iesExtranjeraColegioProfesional",
    "listadoPertenece",
    "nacionalidad",
    "nivel",
    "nombreClasificacion",
    "nombreDetalleCampo",
    "nombreTitulo",
    "nombres",
    "numeroIdentificacion",
    "numeroRegistro",
    "observacion",
    "reconocidoPor",
    "sexo",
    "subarea",
    "subareaCodigo",
    "tipoExtranjeroColegio",
    "tipoNivel",
    "tipoTitulo"
})
public class TituloRegistradoDTO {

    protected String area;
    protected String areaCodigo;
    protected String codigo;
    protected String escalafonDocente;
    protected String estado;
    protected String fechaGrado;
    protected String fechaRegistro;
    protected String ies;
    protected String iesExtranjeraColegioProfesional;
    protected String listadoPertenece;
    protected String nacionalidad;
    protected String nivel;
    protected String nombreClasificacion;
    protected String nombreDetalleCampo;
    protected String nombreTitulo;
    protected String nombres;
    protected String numeroIdentificacion;
    protected String numeroRegistro;
    protected String observacion;
    protected String reconocidoPor;
    protected String sexo;
    protected String subarea;
    protected String subareaCodigo;
    protected String tipoExtranjeroColegio;
    protected String tipoNivel;
    protected String tipoTitulo;

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCodigo() {
        return areaCodigo;
    }

    /**
     * Sets the value of the areaCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCodigo(String value) {
        this.areaCodigo = value;
    }

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the escalafonDocente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscalafonDocente() {
        return escalafonDocente;
    }

    /**
     * Sets the value of the escalafonDocente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscalafonDocente(String value) {
        this.escalafonDocente = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fechaGrado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaGrado() {
        return fechaGrado;
    }

    /**
     * Sets the value of the fechaGrado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaGrado(String value) {
        this.fechaGrado = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRegistro(String value) {
        this.fechaRegistro = value;
    }

    /**
     * Gets the value of the ies property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIes() {
        return ies;
    }

    /**
     * Sets the value of the ies property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIes(String value) {
        this.ies = value;
    }

    /**
     * Gets the value of the iesExtranjeraColegioProfesional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIesExtranjeraColegioProfesional() {
        return iesExtranjeraColegioProfesional;
    }

    /**
     * Sets the value of the iesExtranjeraColegioProfesional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIesExtranjeraColegioProfesional(String value) {
        this.iesExtranjeraColegioProfesional = value;
    }

    /**
     * Gets the value of the listadoPertenece property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListadoPertenece() {
        return listadoPertenece;
    }

    /**
     * Sets the value of the listadoPertenece property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListadoPertenece(String value) {
        this.listadoPertenece = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Sets the value of the nivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel(String value) {
        this.nivel = value;
    }

    /**
     * Gets the value of the nombreClasificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    /**
     * Sets the value of the nombreClasificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreClasificacion(String value) {
        this.nombreClasificacion = value;
    }

    /**
     * Gets the value of the nombreDetalleCampo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDetalleCampo() {
        return nombreDetalleCampo;
    }

    /**
     * Sets the value of the nombreDetalleCampo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDetalleCampo(String value) {
        this.nombreDetalleCampo = value;
    }

    /**
     * Gets the value of the nombreTitulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTitulo() {
        return nombreTitulo;
    }

    /**
     * Sets the value of the nombreTitulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTitulo(String value) {
        this.nombreTitulo = value;
    }

    /**
     * Gets the value of the nombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets the value of the nombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombres(String value) {
        this.nombres = value;
    }

    /**
     * Gets the value of the numeroIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Sets the value of the numeroIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIdentificacion(String value) {
        this.numeroIdentificacion = value;
    }

    /**
     * Gets the value of the numeroRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    /**
     * Sets the value of the numeroRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRegistro(String value) {
        this.numeroRegistro = value;
    }

    /**
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the reconocidoPor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconocidoPor() {
        return reconocidoPor;
    }

    /**
     * Sets the value of the reconocidoPor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconocidoPor(String value) {
        this.reconocidoPor = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the subarea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubarea() {
        return subarea;
    }

    /**
     * Sets the value of the subarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubarea(String value) {
        this.subarea = value;
    }

    /**
     * Gets the value of the subareaCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubareaCodigo() {
        return subareaCodigo;
    }

    /**
     * Sets the value of the subareaCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubareaCodigo(String value) {
        this.subareaCodigo = value;
    }

    /**
     * Gets the value of the tipoExtranjeroColegio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoExtranjeroColegio() {
        return tipoExtranjeroColegio;
    }

    /**
     * Sets the value of the tipoExtranjeroColegio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoExtranjeroColegio(String value) {
        this.tipoExtranjeroColegio = value;
    }

    /**
     * Gets the value of the tipoNivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNivel() {
        return tipoNivel;
    }

    /**
     * Sets the value of the tipoNivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNivel(String value) {
        this.tipoNivel = value;
    }

    /**
     * Gets the value of the tipoTitulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTitulo() {
        return tipoTitulo;
    }

    /**
     * Sets the value of the tipoTitulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTitulo(String value) {
        this.tipoTitulo = value;
    }

}
