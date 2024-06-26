
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ubicacionGeografica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ubicacionGeografica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="canton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codCanton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codParroquia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parroquia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ubicacionGeografica", propOrder = {
    "canton",
    "codCanton",
    "codPais",
    "codParroquia",
    "codProvincia",
    "codRegion",
    "pais",
    "parroquia",
    "provincia",
    "region"
})
public class UbicacionGeografica {

    protected String canton;
    protected String codCanton;
    protected String codPais;
    protected String codParroquia;
    protected String codProvincia;
    protected String codRegion;
    protected String pais;
    protected String parroquia;
    protected String provincia;
    protected String region;

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
     * Gets the value of the codCanton property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCanton() {
        return codCanton;
    }

    /**
     * Sets the value of the codCanton property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCanton(String value) {
        this.codCanton = value;
    }

    /**
     * Gets the value of the codPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPais() {
        return codPais;
    }

    /**
     * Sets the value of the codPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPais(String value) {
        this.codPais = value;
    }

    /**
     * Gets the value of the codParroquia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodParroquia() {
        return codParroquia;
    }

    /**
     * Sets the value of the codParroquia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodParroquia(String value) {
        this.codParroquia = value;
    }

    /**
     * Gets the value of the codProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodProvincia() {
        return codProvincia;
    }

    /**
     * Sets the value of the codProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodProvincia(String value) {
        this.codProvincia = value;
    }

    /**
     * Gets the value of the codRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRegion() {
        return codRegion;
    }

    /**
     * Sets the value of the codRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRegion(String value) {
        this.codRegion = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
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
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

}
