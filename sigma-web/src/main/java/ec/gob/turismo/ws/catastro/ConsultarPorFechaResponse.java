
package ec.gob.turismo.ws.catastro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarPorFechaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarPorFechaResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ListaGuiaCatastro" type="{http://ws.turismo.gob.ec/Catastro}Respuesta" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarPorFechaResponse", propOrder = {
    "listaGuiaCatastro"
})
public class ConsultarPorFechaResponse {

    @XmlElement(name = "ListaGuiaCatastro")
    protected Respuesta listaGuiaCatastro;

    /**
     * Gets the value of the listaGuiaCatastro property.
     * 
     * @return
     *     possible object is
     *     {@link Respuesta }
     *     
     */
    public Respuesta getListaGuiaCatastro() {
        return listaGuiaCatastro;
    }

    /**
     * Sets the value of the listaGuiaCatastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Respuesta }
     *     
     */
    public void setListaGuiaCatastro(Respuesta value) {
        this.listaGuiaCatastro = value;
    }

}
