
package ec.gob.ambiente.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNameOld complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNameOld"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="edadInicio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="edadFinal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="genero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNameOld", propOrder = {
    "apellido1",
    "apellido2",
    "nombre1",
    "nombre2",
    "edadInicio",
    "edadFinal",
    "genero"
})
public class GetNameOld {

    protected String apellido1;
    protected String apellido2;
    protected String nombre1;
    protected String nombre2;
    protected Integer edadInicio;
    protected Integer edadFinal;
    protected Integer genero;

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
     * Gets the value of the nombre1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * Sets the value of the nombre1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre1(String value) {
        this.nombre1 = value;
    }

    /**
     * Gets the value of the nombre2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * Sets the value of the nombre2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre2(String value) {
        this.nombre2 = value;
    }

    /**
     * Gets the value of the edadInicio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEdadInicio() {
        return edadInicio;
    }

    /**
     * Sets the value of the edadInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEdadInicio(Integer value) {
        this.edadInicio = value;
    }

    /**
     * Gets the value of the edadFinal property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEdadFinal() {
        return edadFinal;
    }

    /**
     * Sets the value of the edadFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEdadFinal(Integer value) {
        this.edadFinal = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGenero(Integer value) {
        this.genero = value;
    }

}
