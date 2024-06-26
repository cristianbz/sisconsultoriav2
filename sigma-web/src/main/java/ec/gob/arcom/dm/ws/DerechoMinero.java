
package ec.gob.arcom.dm.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for derechoMinero complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="derechoMinero"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="canton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoCanton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoCatastral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoParroquia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="materialInteres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="materialRecuperado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mineral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreDerechoMinero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parroquia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="plazo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regimen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="representanteLegalDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="representanteLegalNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="superficie" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="tipoDerechoMinero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titularDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titularNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "derechoMinero", propOrder = {
    "canton",
    "codigoCanton",
    "codigoCatastral",
    "codigoParroquia",
    "codigoProvincia",
    "estado",
    "fase",
    "fechaInscripcion",
    "materialInteres",
    "materialRecuperado",
    "mineral",
    "nombreDerechoMinero",
    "parroquia",
    "plazo",
    "provincia",
    "regimen",
    "regional",
    "representanteLegalDocumento",
    "representanteLegalNombre",
    "superficie",
    "tipoDerechoMinero",
    "titularDocumento",
    "titularNombre"
})
public class DerechoMinero {

    protected String canton;
    protected String codigoCanton;
    protected String codigoCatastral;
    protected String codigoParroquia;
    protected String codigoProvincia;
    protected String estado;
    protected String fase;
    protected String fechaInscripcion;
    protected String materialInteres;
    protected String materialRecuperado;
    protected String mineral;
    protected String nombreDerechoMinero;
    protected String parroquia;
    protected Long plazo;
    protected String provincia;
    protected String regimen;
    protected String regional;
    protected String representanteLegalDocumento;
    protected String representanteLegalNombre;
    protected Double superficie;
    protected String tipoDerechoMinero;
    protected String titularDocumento;
    protected String titularNombre;

    /**
     * Gets the value of the canton property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Sets the value of the canton property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanton(String value) {
        this.canton = value;
    }

    /**
     * Gets the value of the codigoCanton property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCanton() {
        return codigoCanton;
    }

    /**
     * Sets the value of the codigoCanton property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCanton(String value) {
        this.codigoCanton = value;
    }

    /**
     * Gets the value of the codigoCatastral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCatastral() {
        return codigoCatastral;
    }

    /**
     * Sets the value of the codigoCatastral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCatastral(String value) {
        this.codigoCatastral = value;
    }

    /**
     * Gets the value of the codigoParroquia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    /**
     * Sets the value of the codigoParroquia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquia(String value) {
        this.codigoParroquia = value;
    }

    /**
     * Gets the value of the codigoProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    /**
     * Sets the value of the codigoProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvincia(String value) {
        this.codigoProvincia = value;
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
     * Gets the value of the fase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFase() {
        return fase;
    }

    /**
     * Sets the value of the fase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFase(String value) {
        this.fase = value;
    }

    /**
     * Gets the value of the fechaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Sets the value of the fechaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcion(String value) {
        this.fechaInscripcion = value;
    }

    /**
     * Gets the value of the materialInteres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialInteres() {
        return materialInteres;
    }

    /**
     * Sets the value of the materialInteres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialInteres(String value) {
        this.materialInteres = value;
    }

    /**
     * Gets the value of the materialRecuperado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialRecuperado() {
        return materialRecuperado;
    }

    /**
     * Sets the value of the materialRecuperado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialRecuperado(String value) {
        this.materialRecuperado = value;
    }

    /**
     * Gets the value of the mineral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMineral() {
        return mineral;
    }

    /**
     * Sets the value of the mineral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMineral(String value) {
        this.mineral = value;
    }

    /**
     * Gets the value of the nombreDerechoMinero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    /**
     * Sets the value of the nombreDerechoMinero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDerechoMinero(String value) {
        this.nombreDerechoMinero = value;
    }

    /**
     * Gets the value of the parroquia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquia() {
        return parroquia;
    }

    /**
     * Sets the value of the parroquia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquia(String value) {
        this.parroquia = value;
    }

    /**
     * Gets the value of the plazo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlazo() {
        return plazo;
    }

    /**
     * Sets the value of the plazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlazo(Long value) {
        this.plazo = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the regimen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegimen() {
        return regimen;
    }

    /**
     * Sets the value of the regimen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegimen(String value) {
        this.regimen = value;
    }

    /**
     * Gets the value of the regional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegional() {
        return regional;
    }

    /**
     * Sets the value of the regional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegional(String value) {
        this.regional = value;
    }

    /**
     * Gets the value of the representanteLegalDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentanteLegalDocumento() {
        return representanteLegalDocumento;
    }

    /**
     * Sets the value of the representanteLegalDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentanteLegalDocumento(String value) {
        this.representanteLegalDocumento = value;
    }

    /**
     * Gets the value of the representanteLegalNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentanteLegalNombre() {
        return representanteLegalNombre;
    }

    /**
     * Sets the value of the representanteLegalNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentanteLegalNombre(String value) {
        this.representanteLegalNombre = value;
    }

    /**
     * Gets the value of the superficie property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSuperficie() {
        return superficie;
    }

    /**
     * Sets the value of the superficie property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSuperficie(Double value) {
        this.superficie = value;
    }

    /**
     * Gets the value of the tipoDerechoMinero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDerechoMinero() {
        return tipoDerechoMinero;
    }

    /**
     * Sets the value of the tipoDerechoMinero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDerechoMinero(String value) {
        this.tipoDerechoMinero = value;
    }

    /**
     * Gets the value of the titularDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitularDocumento() {
        return titularDocumento;
    }

    /**
     * Sets the value of the titularDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitularDocumento(String value) {
        this.titularDocumento = value;
    }

    /**
     * Gets the value of the titularNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitularNombre() {
        return titularNombre;
    }

    /**
     * Sets the value of the titularNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitularNombre(String value) {
        this.titularNombre = value;
    }

}
