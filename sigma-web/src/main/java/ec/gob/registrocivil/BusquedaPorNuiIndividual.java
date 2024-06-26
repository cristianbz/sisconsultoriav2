
package ec.gob.registrocivil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusquedaPorNuiIndividual complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusquedaPorNuiIndividual"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodigoInstitucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoAgencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Contrasenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndividualDactilar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusquedaPorNuiIndividual", propOrder = {
    "codigoInstitucion",
    "codigoAgencia",
    "usuario",
    "contrasenia",
    "nui",
    "individualDactilar"
})
public class BusquedaPorNuiIndividual {

    @XmlElement(name = "CodigoInstitucion")
    protected String codigoInstitucion;
    @XmlElement(name = "CodigoAgencia")
    protected String codigoAgencia;
    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasenia")
    protected String contrasenia;
    @XmlElement(name = "NUI")
    protected String nui;
    @XmlElement(name = "IndividualDactilar")
    protected String individualDactilar;

    /**
     * Gets the value of the codigoInstitucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoInstitucion() {
        return codigoInstitucion;
    }

    /**
     * Sets the value of the codigoInstitucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoInstitucion(String value) {
        this.codigoInstitucion = value;
    }

    /**
     * Gets the value of the codigoAgencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    /**
     * Sets the value of the codigoAgencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoAgencia(String value) {
        this.codigoAgencia = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the contrasenia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Sets the value of the contrasenia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenia(String value) {
        this.contrasenia = value;
    }

    /**
     * Gets the value of the nui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUI() {
        return nui;
    }

    /**
     * Sets the value of the nui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUI(String value) {
        this.nui = value;
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

}
