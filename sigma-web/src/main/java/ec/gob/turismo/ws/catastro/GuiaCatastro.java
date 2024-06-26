
package ec.gob.turismo.ws.catastro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GuiaCatastro complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuiaCatastro"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Cedula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ApellidosNombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoGuia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoSangre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonResidenciaActual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaCaducidadLicencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Idiomas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TelefonoConvencional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TelefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CorreoElectronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AccesoAreaProtegida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Modalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuiaCatastro", propOrder = {
    "cedula",
    "apellidosNombres",
    "tipoGuia",
    "nacionalidad",
    "sexo",
    "tipoSangre",
    "lugarNacimiento",
    "cantonResidenciaActual",
    "fechaCaducidadLicencia",
    "idiomas",
    "telefonoConvencional",
    "telefonoCelular",
    "correoElectronico",
    "accesoAreaProtegida",
    "modalidad"
})
public class GuiaCatastro {

    @XmlElement(name = "Cedula")
    protected String cedula;
    @XmlElement(name = "ApellidosNombres")
    protected String apellidosNombres;
    @XmlElement(name = "TipoGuia")
    protected String tipoGuia;
    @XmlElement(name = "Nacionalidad")
    protected String nacionalidad;
    @XmlElement(name = "Sexo")
    protected String sexo;
    @XmlElement(name = "TipoSangre")
    protected String tipoSangre;
    @XmlElement(name = "LugarNacimiento")
    protected String lugarNacimiento;
    @XmlElement(name = "CantonResidenciaActual")
    protected String cantonResidenciaActual;
    @XmlElement(name = "FechaCaducidadLicencia")
    protected String fechaCaducidadLicencia;
    @XmlElement(name = "Idiomas")
    protected String idiomas;
    @XmlElement(name = "TelefonoConvencional")
    protected String telefonoConvencional;
    @XmlElement(name = "TelefonoCelular")
    protected String telefonoCelular;
    @XmlElement(name = "CorreoElectronico")
    protected String correoElectronico;
    @XmlElement(name = "AccesoAreaProtegida")
    protected String accesoAreaProtegida;
    @XmlElement(name = "Modalidad")
    protected String modalidad;

    /**
     * Gets the value of the cedula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Sets the value of the cedula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedula(String value) {
        this.cedula = value;
    }

    /**
     * Gets the value of the apellidosNombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidosNombres() {
        return apellidosNombres;
    }

    /**
     * Sets the value of the apellidosNombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidosNombres(String value) {
        this.apellidosNombres = value;
    }

    /**
     * Gets the value of the tipoGuia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoGuia() {
        return tipoGuia;
    }

    /**
     * Sets the value of the tipoGuia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoGuia(String value) {
        this.tipoGuia = value;
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
     * Gets the value of the tipoSangre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSangre() {
        return tipoSangre;
    }

    /**
     * Sets the value of the tipoSangre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSangre(String value) {
        this.tipoSangre = value;
    }

    /**
     * Gets the value of the lugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Sets the value of the lugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarNacimiento(String value) {
        this.lugarNacimiento = value;
    }

    /**
     * Gets the value of the cantonResidenciaActual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonResidenciaActual() {
        return cantonResidenciaActual;
    }

    /**
     * Sets the value of the cantonResidenciaActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonResidenciaActual(String value) {
        this.cantonResidenciaActual = value;
    }

    /**
     * Gets the value of the fechaCaducidadLicencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCaducidadLicencia() {
        return fechaCaducidadLicencia;
    }

    /**
     * Sets the value of the fechaCaducidadLicencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCaducidadLicencia(String value) {
        this.fechaCaducidadLicencia = value;
    }

    /**
     * Gets the value of the idiomas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdiomas() {
        return idiomas;
    }

    /**
     * Sets the value of the idiomas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdiomas(String value) {
        this.idiomas = value;
    }

    /**
     * Gets the value of the telefonoConvencional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoConvencional() {
        return telefonoConvencional;
    }

    /**
     * Sets the value of the telefonoConvencional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoConvencional(String value) {
        this.telefonoConvencional = value;
    }

    /**
     * Gets the value of the telefonoCelular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * Sets the value of the telefonoCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoCelular(String value) {
        this.telefonoCelular = value;
    }

    /**
     * Gets the value of the correoElectronico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Sets the value of the correoElectronico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

    /**
     * Gets the value of the accesoAreaProtegida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccesoAreaProtegida() {
        return accesoAreaProtegida;
    }

    /**
     * Sets the value of the accesoAreaProtegida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccesoAreaProtegida(String value) {
        this.accesoAreaProtegida = value;
    }

    /**
     * Gets the value of the modalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Sets the value of the modalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidad(String value) {
        this.modalidad = value;
    }

}
