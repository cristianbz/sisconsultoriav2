
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoContribuyente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoContribuyente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nivel1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nivel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nivel3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nivel4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ultimoNivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoContribuyente", propOrder = {
    "id",
    "nivel1",
    "nivel2",
    "nivel3",
    "nivel4",
    "ultimoNivel"
})
public class TipoContribuyente {

    protected Long id;
    protected String nivel1;
    protected String nivel2;
    protected String nivel3;
    protected String nivel4;
    protected String ultimoNivel;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nivel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel1() {
        return nivel1;
    }

    /**
     * Sets the value of the nivel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel1(String value) {
        this.nivel1 = value;
    }

    /**
     * Gets the value of the nivel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel2() {
        return nivel2;
    }

    /**
     * Sets the value of the nivel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel2(String value) {
        this.nivel2 = value;
    }

    /**
     * Gets the value of the nivel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel3() {
        return nivel3;
    }

    /**
     * Sets the value of the nivel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel3(String value) {
        this.nivel3 = value;
    }

    /**
     * Gets the value of the nivel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel4() {
        return nivel4;
    }

    /**
     * Sets the value of the nivel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel4(String value) {
        this.nivel4 = value;
    }

    /**
     * Gets the value of the ultimoNivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimoNivel() {
        return ultimoNivel;
    }

    /**
     * Sets the value of the ultimoNivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimoNivel(String value) {
        this.ultimoNivel = value;
    }

}
