
package ec.gob.arcom.dm.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for coordenadas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="coordenadas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoCoordenada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utmEste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utmNorte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coordenadas", propOrder = {
    "numero",
    "tipoArea",
    "tipoCoordenada",
    "utmEste",
    "utmNorte",
    "zona"
})
public class Coordenadas {

    protected String numero;
    protected String tipoArea;
    protected String tipoCoordenada;
    protected String utmEste;
    protected String utmNorte;
    protected String zona;

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the tipoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoArea() {
        return tipoArea;
    }

    /**
     * Sets the value of the tipoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoArea(String value) {
        this.tipoArea = value;
    }

    /**
     * Gets the value of the tipoCoordenada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCoordenada() {
        return tipoCoordenada;
    }

    /**
     * Sets the value of the tipoCoordenada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCoordenada(String value) {
        this.tipoCoordenada = value;
    }

    /**
     * Gets the value of the utmEste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtmEste() {
        return utmEste;
    }

    /**
     * Sets the value of the utmEste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtmEste(String value) {
        this.utmEste = value;
    }

    /**
     * Gets the value of the utmNorte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtmNorte() {
        return utmNorte;
    }

    /**
     * Sets the value of the utmNorte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtmNorte(String value) {
        this.utmNorte = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
    }

}
