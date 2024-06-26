
package ec.gob.registrocivil.consultacedula;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cedula complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cedula"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CalleDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Cedula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CondicionCedulado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Conyuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EstadoCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaCedulacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FirmaElectronica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Genero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndividualDactilar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Instruccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombreMadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombrePadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumeroDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Profesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cedula", propOrder = {
    "calleDomicilio",
    "cedula",
    "codigoError",
    "condicionCedulado",
    "conyuge",
    "domicilio",
    "error",
    "estadoCivil",
    "fechaCedulacion",
    "fechaNacimiento",
    "firmaElectronica",
    "genero",
    "individualDactilar",
    "instruccion",
    "lugarNacimiento",
    "nacionalidad",
    "nombreMadre",
    "nombrePadre",
    "nombre",
    "numeroDomicilio",
    "profesion"
})
public class Cedula {

    @XmlElement(name = "CalleDomicilio")
    protected String calleDomicilio;
    @XmlElement(name = "Cedula")
    protected String cedula;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "CondicionCedulado")
    protected String condicionCedulado;
    @XmlElement(name = "Conyuge")
    protected String conyuge;
    @XmlElement(name = "Domicilio")
    protected String domicilio;
    @XmlElement(name = "Error")
    protected String error;
    @XmlElement(name = "EstadoCivil")
    protected String estadoCivil;
    @XmlElement(name = "FechaCedulacion")
    protected String fechaCedulacion;
    @XmlElement(name = "FechaNacimiento")
    protected String fechaNacimiento;
    @XmlElement(name = "FirmaElectronica")
    protected String firmaElectronica;
    @XmlElement(name = "Genero")
    protected String genero;
    @XmlElement(name = "IndividualDactilar")
    protected String individualDactilar;
    @XmlElement(name = "Instruccion")
    protected String instruccion;
    @XmlElement(name = "LugarNacimiento")
    protected String lugarNacimiento;
    @XmlElement(name = "Nacionalidad")
    protected String nacionalidad;
    @XmlElement(name = "NombreMadre")
    protected String nombreMadre;
    @XmlElement(name = "NombrePadre")
    protected String nombrePadre;
    @XmlElement(name = "Nombre")
    protected String nombre;
    @XmlElement(name = "NumeroDomicilio")
    protected String numeroDomicilio;
    @XmlElement(name = "Profesion")
    protected String profesion;

    /**
     * Gets the value of the calleDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalleDomicilio() {
        return calleDomicilio;
    }

    /**
     * Sets the value of the calleDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalleDomicilio(String value) {
        this.calleDomicilio = value;
    }

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
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the condicionCedulado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionCedulado() {
        return condicionCedulado;
    }

    /**
     * Sets the value of the condicionCedulado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionCedulado(String value) {
        this.condicionCedulado = value;
    }

    /**
     * Gets the value of the conyuge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConyuge() {
        return conyuge;
    }

    /**
     * Sets the value of the conyuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConyuge(String value) {
        this.conyuge = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicilio(String value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the estadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the value of the estadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivil(String value) {
        this.estadoCivil = value;
    }

    /**
     * Gets the value of the fechaCedulacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCedulacion() {
        return fechaCedulacion;
    }

    /**
     * Sets the value of the fechaCedulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCedulacion(String value) {
        this.fechaCedulacion = value;
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
     * Gets the value of the firmaElectronica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    /**
     * Sets the value of the firmaElectronica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaElectronica(String value) {
        this.firmaElectronica = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    /**
     * Gets the value of the individualDactilar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDactilar() {
        return individualDactilar;
    }

    /**
     * Sets the value of the individualDactilar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDactilar(String value) {
        this.individualDactilar = value;
    }

    /**
     * Gets the value of the instruccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstruccion() {
        return instruccion;
    }

    /**
     * Sets the value of the instruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstruccion(String value) {
        this.instruccion = value;
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
     * Gets the value of the nombreMadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreMadre() {
        return nombreMadre;
    }

    /**
     * Sets the value of the nombreMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreMadre(String value) {
        this.nombreMadre = value;
    }

    /**
     * Gets the value of the nombrePadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePadre() {
        return nombrePadre;
    }

    /**
     * Sets the value of the nombrePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePadre(String value) {
        this.nombrePadre = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the numeroDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDomicilio() {
        return numeroDomicilio;
    }

    /**
     * Sets the value of the numeroDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDomicilio(String value) {
        this.numeroDomicilio = value;
    }

    /**
     * Gets the value of the profesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * Sets the value of the profesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfesion(String value) {
        this.profesion = value;
    }

}
