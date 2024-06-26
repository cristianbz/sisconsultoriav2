
package ant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ingreso_Citaciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ingreso_Citaciones"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Identificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Placa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AgenteTransito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarInfraccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Institucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingreso_Citaciones", propOrder = {
    "canal",
    "usuario",
    "identificacion",
    "placa",
    "agenteTransito",
    "articulo",
    "fecha",
    "lugarInfraccion",
    "provincia",
    "localidad",
    "institucion",
    "imagen"
})
public class IngresoCitaciones {

    @XmlElement(name = "Canal")
    protected String canal;
    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Identificacion")
    protected String identificacion;
    @XmlElement(name = "Placa")
    protected String placa;
    @XmlElement(name = "AgenteTransito")
    protected String agenteTransito;
    @XmlElement(name = "Articulo")
    protected String articulo;
    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlElement(name = "LugarInfraccion")
    protected String lugarInfraccion;
    @XmlElement(name = "Provincia")
    protected String provincia;
    @XmlElement(name = "Localidad")
    protected String localidad;
    @XmlElement(name = "Institucion")
    protected String institucion;
    @XmlElement(name = "Imagen", required = true)
    protected byte[] imagen;

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
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
     * Gets the value of the identificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Sets the value of the identificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificacion(String value) {
        this.identificacion = value;
    }

    /**
     * Gets the value of the placa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Sets the value of the placa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaca(String value) {
        this.placa = value;
    }

    /**
     * Gets the value of the agenteTransito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgenteTransito() {
        return agenteTransito;
    }

    /**
     * Sets the value of the agenteTransito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgenteTransito(String value) {
        this.agenteTransito = value;
    }

    /**
     * Gets the value of the articulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * Sets the value of the articulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticulo(String value) {
        this.articulo = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the lugarInfraccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarInfraccion() {
        return lugarInfraccion;
    }

    /**
     * Sets the value of the lugarInfraccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarInfraccion(String value) {
        this.lugarInfraccion = value;
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
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the institucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitucion() {
        return institucion;
    }

    /**
     * Sets the value of the institucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitucion(String value) {
        this.institucion = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

}
