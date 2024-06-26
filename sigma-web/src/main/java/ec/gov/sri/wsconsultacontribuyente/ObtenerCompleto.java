
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerCompleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerCompleto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroRuc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fuenteDatos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerCompleto", propOrder = {
    "numeroRuc",
    "fuenteDatos"
})
public class ObtenerCompleto {

    protected String numeroRuc;
    protected String fuenteDatos;

    /**
     * Gets the value of the numeroRuc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRuc() {
        return numeroRuc;
    }

    /**
     * Sets the value of the numeroRuc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRuc(String value) {
        this.numeroRuc = value;
    }

    /**
     * Gets the value of the fuenteDatos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuenteDatos() {
        return fuenteDatos;
    }

    /**
     * Sets the value of the fuenteDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuenteDatos(String value) {
        this.fuenteDatos = value;
    }

}
