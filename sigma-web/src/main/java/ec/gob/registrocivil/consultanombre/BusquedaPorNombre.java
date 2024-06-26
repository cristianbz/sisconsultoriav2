
package ec.gob.registrocivil.consultanombre;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for busquedaPorNombre complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="busquedaPorNombre"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nombre1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nombre2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EdadInicio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="EdadFinal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Genero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Contrasenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "busquedaPorNombre", propOrder = {
    "apellido1",
    "apellido2",
    "nombre1",
    "nombre2",
    "edadInicio",
    "edadFinal",
    "genero",
    "usuario",
    "contrasenia"
})
public class BusquedaPorNombre {

    @XmlElement(name = "Apellido1")
    protected String apellido1;
    @XmlElement(name = "Apellido2")
    protected String apellido2;
    @XmlElement(name = "Nombre1")
    protected String nombre1;
    @XmlElement(name = "Nombre2")
    protected String nombre2;
    @XmlElement(name = "EdadInicio")
    protected Integer edadInicio;
    @XmlElement(name = "EdadFinal")
    protected Integer edadFinal;
    @XmlElement(name = "Genero")
    protected Integer genero;
    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasenia")
    protected String contrasenia;

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

}
