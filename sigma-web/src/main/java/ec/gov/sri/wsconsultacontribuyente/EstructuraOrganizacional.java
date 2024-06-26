
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estructuraOrganizacional complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estructuraOrganizacional"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codigoProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoRegional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nombreProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreRegional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estructuraOrganizacional", propOrder = {
    "codigoProvincia",
    "codigoRegional",
    "id",
    "nombreProvincia",
    "nombreRegional"
})
public class EstructuraOrganizacional {

    protected String codigoProvincia;
    protected String codigoRegional;
    protected long id;
    protected String nombreProvincia;
    protected String nombreRegional;

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
     * Gets the value of the codigoRegional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRegional() {
        return codigoRegional;
    }

    /**
     * Sets the value of the codigoRegional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRegional(String value) {
        this.codigoRegional = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nombreProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProvincia() {
        return nombreProvincia;
    }

    /**
     * Sets the value of the nombreProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProvincia(String value) {
        this.nombreProvincia = value;
    }

    /**
     * Gets the value of the nombreRegional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRegional() {
        return nombreRegional;
    }

    /**
     * Sets the value of the nombreRegional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRegional(String value) {
        this.nombreRegional = value;
    }

}
