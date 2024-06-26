
package ec.gob.turismo.ws.catastro;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Respuesta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Respuesta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MensajeError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GuiasCatastro" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="GuiaCatastro" type="{http://ws.turismo.gob.ec/Catastro}GuiaCatastro" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Respuesta", propOrder = {
    "codigoError",
    "mensajeError",
    "guiasCatastro"
})
public class Respuesta {

    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "MensajeError")
    protected String mensajeError;
    @XmlElement(name = "GuiasCatastro")
    protected Respuesta.GuiasCatastro guiasCatastro;

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
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the guiasCatastro property.
     * 
     * @return
     *     possible object is
     *     {@link Respuesta.GuiasCatastro }
     *     
     */
    public Respuesta.GuiasCatastro getGuiasCatastro() {
        return guiasCatastro;
    }

    /**
     * Sets the value of the guiasCatastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Respuesta.GuiasCatastro }
     *     
     */
    public void setGuiasCatastro(Respuesta.GuiasCatastro value) {
        this.guiasCatastro = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="GuiaCatastro" type="{http://ws.turismo.gob.ec/Catastro}GuiaCatastro" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "guiaCatastro"
    })
    public static class GuiasCatastro {

        @XmlElement(name = "GuiaCatastro")
        protected List<GuiaCatastro> guiaCatastro;

        /**
         * Gets the value of the guiaCatastro property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the guiaCatastro property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGuiaCatastro().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GuiaCatastro }
         * 
         * 
         */
        public List<GuiaCatastro> getGuiaCatastro() {
            if (guiaCatastro == null) {
                guiaCatastro = new ArrayList<GuiaCatastro>();
            }
            return this.guiaCatastro;
        }

    }

}
