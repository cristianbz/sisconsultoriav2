
package ant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosLicencia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosLicencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caducidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cod_error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaPrimeraVez" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="infracciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="puntos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="restriccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo_Licen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo_sangre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosLicencia", propOrder = {
    "apellido1",
    "apellido2",
    "apellidos",
    "caducidad",
    "codError",
    "direccion",
    "fechaPrimeraVez",
    "identifica",
    "infracciones",
    "mensaje",
    "nombres",
    "puntos",
    "restriccion",
    "telefono",
    "tipoLicen",
    "tipoSangre"
})
public class DatosLicencia {

    protected String apellido1;
    protected String apellido2;
    protected String apellidos;
    protected String caducidad;
    @XmlElement(name = "cod_error")
    protected String codError;
    protected String direccion;
    protected String fechaPrimeraVez;
    protected String identifica;
    protected String infracciones;
    protected String mensaje;
    protected String nombres;
    protected String puntos;
    protected String restriccion;
    protected String telefono;
    @XmlElement(name = "tipo_Licen")
    protected String tipoLicen;
    @XmlElement(name = "tipo_sangre")
    protected String tipoSangre;

    /**
     * Gets the value of the apellido1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Sets the value of the apellido1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Gets the value of the apellido2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Sets the value of the apellido2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Gets the value of the apellidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets the value of the apellidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidos(String value) {
        this.apellidos = value;
    }

    /**
     * Gets the value of the caducidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaducidad() {
        return caducidad;
    }

    /**
     * Sets the value of the caducidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaducidad(String value) {
        this.caducidad = value;
    }

    /**
     * Gets the value of the codError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodError() {
        return codError;
    }

    /**
     * Sets the value of the codError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodError(String value) {
        this.codError = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the fechaPrimeraVez property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPrimeraVez() {
        return fechaPrimeraVez;
    }

    /**
     * Sets the value of the fechaPrimeraVez property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPrimeraVez(String value) {
        this.fechaPrimeraVez = value;
    }

    /**
     * Gets the value of the identifica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifica() {
        return identifica;
    }

    /**
     * Sets the value of the identifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifica(String value) {
        this.identifica = value;
    }

    /**
     * Gets the value of the infracciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfracciones() {
        return infracciones;
    }

    /**
     * Sets the value of the infracciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfracciones(String value) {
        this.infracciones = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
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
     * Gets the value of the puntos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntos() {
        return puntos;
    }

    /**
     * Sets the value of the puntos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntos(String value) {
        this.puntos = value;
    }

    /**
     * Gets the value of the restriccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestriccion() {
        return restriccion;
    }

    /**
     * Sets the value of the restriccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestriccion(String value) {
        this.restriccion = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the tipoLicen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoLicen() {
        return tipoLicen;
    }

    /**
     * Sets the value of the tipoLicen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoLicen(String value) {
        this.tipoLicen = value;
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

}
