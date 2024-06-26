
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contribuyente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contribuyente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actividadEconomica" type="{http://sri.gov.ec/wsConsultaContribuyente}actividadEconomica" minOccurs="0"/&gt;
 *         &lt;element name="codClaseContrib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="desClaseContrib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="desEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="direccionCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreComercial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroRuc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefonoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefonoTrabajo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoContribuyente" type="{http://sri.gov.ec/wsConsultaContribuyente}tipoContribuyente" minOccurs="0"/&gt;
 *         &lt;element name="ubicacionGeografica" type="{http://sri.gov.ec/wsConsultaContribuyente}ubicacionGeografica" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contribuyente", propOrder = {
    "actividadEconomica",
    "codClaseContrib",
    "codEstado",
    "desClaseContrib",
    "desEstado",
    "direccionCorta",
    "email",
    "nombreComercial",
    "numeroRuc",
    "razonSocial",
    "telefonoDomicilio",
    "telefonoTrabajo",
    "tipoContribuyente",
    "ubicacionGeografica"
})
@XmlSeeAlso({
    ContribuyenteCompleto.class
})
public class Contribuyente {

    protected ActividadEconomica actividadEconomica;
    protected String codClaseContrib;
    protected String codEstado;
    protected String desClaseContrib;
    protected String desEstado;
    protected String direccionCorta;
    protected String email;
    protected String nombreComercial;
    protected String numeroRuc;
    protected String razonSocial;
    protected String telefonoDomicilio;
    protected String telefonoTrabajo;
    protected TipoContribuyente tipoContribuyente;
    protected UbicacionGeografica ubicacionGeografica;

    /**
     * Gets the value of the actividadEconomica property.
     * 
     * @return
     *     possible object is
     *     {@link ActividadEconomica }
     *     
     */
    public ActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Sets the value of the actividadEconomica property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActividadEconomica }
     *     
     */
    public void setActividadEconomica(ActividadEconomica value) {
        this.actividadEconomica = value;
    }

    /**
     * Gets the value of the codClaseContrib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodClaseContrib() {
        return codClaseContrib;
    }

    /**
     * Sets the value of the codClaseContrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodClaseContrib(String value) {
        this.codClaseContrib = value;
    }

    /**
     * Gets the value of the codEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * Sets the value of the codEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEstado(String value) {
        this.codEstado = value;
    }

    /**
     * Gets the value of the desClaseContrib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesClaseContrib() {
        return desClaseContrib;
    }

    /**
     * Sets the value of the desClaseContrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesClaseContrib(String value) {
        this.desClaseContrib = value;
    }

    /**
     * Gets the value of the desEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesEstado() {
        return desEstado;
    }

    /**
     * Sets the value of the desEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesEstado(String value) {
        this.desEstado = value;
    }

    /**
     * Gets the value of the direccionCorta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionCorta() {
        return direccionCorta;
    }

    /**
     * Sets the value of the direccionCorta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionCorta(String value) {
        this.direccionCorta = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the nombreComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Sets the value of the nombreComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComercial(String value) {
        this.nombreComercial = value;
    }

    /**
     * Gets the value of the numeroRuc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRuc() {
        return numeroRuc;
    }

    /**
     * Sets the value of the numeroRuc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRuc(String value) {
        this.numeroRuc = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the telefonoDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    /**
     * Sets the value of the telefonoDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoDomicilio(String value) {
        this.telefonoDomicilio = value;
    }

    /**
     * Gets the value of the telefonoTrabajo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    /**
     * Sets the value of the telefonoTrabajo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoTrabajo(String value) {
        this.telefonoTrabajo = value;
    }

    /**
     * Gets the value of the tipoContribuyente property.
     * 
     * @return
     *     possible object is
     *     {@link TipoContribuyente }
     *     
     */
    public TipoContribuyente getTipoContribuyente() {
        return tipoContribuyente;
    }

    /**
     * Sets the value of the tipoContribuyente property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoContribuyente }
     *     
     */
    public void setTipoContribuyente(TipoContribuyente value) {
        this.tipoContribuyente = value;
    }

    /**
     * Gets the value of the ubicacionGeografica property.
     * 
     * @return
     *     possible object is
     *     {@link UbicacionGeografica }
     *     
     */
    public UbicacionGeografica getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    /**
     * Sets the value of the ubicacionGeografica property.
     * 
     * @param value
     *     allowed object is
     *     {@link UbicacionGeografica }
     *     
     */
    public void setUbicacionGeografica(UbicacionGeografica value) {
        this.ubicacionGeografica = value;
    }

}
