
package ec.gob.arcom.dm.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for derechoMineroMAEDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="derechoMineroMAEDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="coordenadasPSAD56" type="{http://ws.dm.arcom.gob.ec/}coordenadas" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="coordenadasWGS84" type="{http://ws.dm.arcom.gob.ec/}coordenadas" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="derechoMinero" type="{http://ws.dm.arcom.gob.ec/}derechoMinero" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "derechoMineroMAEDTO", propOrder = {
    "coordenadasPSAD56",
    "coordenadasWGS84",
    "derechoMinero"
})
public class DerechoMineroMAEDTO {

    @XmlElement(nillable = true)
    protected List<Coordenadas> coordenadasPSAD56;
    @XmlElement(nillable = true)
    protected List<Coordenadas> coordenadasWGS84;
    protected DerechoMinero derechoMinero;

    /**
     * Gets the value of the coordenadasPSAD56 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordenadasPSAD56 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordenadasPSAD56().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Coordenadas }
     * 
     * 
     */
    public List<Coordenadas> getCoordenadasPSAD56() {
        if (coordenadasPSAD56 == null) {
            coordenadasPSAD56 = new ArrayList<Coordenadas>();
        }
        return this.coordenadasPSAD56;
    }

    /**
     * Gets the value of the coordenadasWGS84 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordenadasWGS84 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordenadasWGS84().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Coordenadas }
     * 
     * 
     */
    public List<Coordenadas> getCoordenadasWGS84() {
        if (coordenadasWGS84 == null) {
            coordenadasWGS84 = new ArrayList<Coordenadas>();
        }
        return this.coordenadasWGS84;
    }

    /**
     * Gets the value of the derechoMinero property.
     * 
     * @return
     *     possible object is
     *     {@link DerechoMinero }
     *     
     */
    public DerechoMinero getDerechoMinero() {
        return derechoMinero;
    }

    /**
     * Sets the value of the derechoMinero property.
     * 
     * @param value
     *     allowed object is
     *     {@link DerechoMinero }
     *     
     */
    public void setDerechoMinero(DerechoMinero value) {
        this.derechoMinero = value;
    }

}
