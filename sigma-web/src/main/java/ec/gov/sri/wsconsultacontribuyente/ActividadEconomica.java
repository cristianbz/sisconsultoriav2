
package ec.gov.sri.wsconsultacontribuyente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actividadEconomica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actividadEconomica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actividadGeneral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN1Familia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN2Grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN3SubGrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN4Clase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN5SubClase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codN6Actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n1Familia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n2Grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n3SubGrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n4Clase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n5SubClase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="n6Actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actividadEconomica", propOrder = {
    "actividadGeneral",
    "codN1Familia",
    "codN2Grupo",
    "codN3SubGrupo",
    "codN4Clase",
    "codN5SubClase",
    "codN6Actividad",
    "n1Familia",
    "n2Grupo",
    "n3SubGrupo",
    "n4Clase",
    "n5SubClase",
    "n6Actividad"
})
public class ActividadEconomica {

    protected String actividadGeneral;
    protected String codN1Familia;
    protected String codN2Grupo;
    protected String codN3SubGrupo;
    protected String codN4Clase;
    protected String codN5SubClase;
    protected String codN6Actividad;
    protected String n1Familia;
    protected String n2Grupo;
    protected String n3SubGrupo;
    protected String n4Clase;
    protected String n5SubClase;
    protected String n6Actividad;

    /**
     * Gets the value of the actividadGeneral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadGeneral() {
        return actividadGeneral;
    }

    /**
     * Sets the value of the actividadGeneral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadGeneral(String value) {
        this.actividadGeneral = value;
    }

    /**
     * Gets the value of the codN1Familia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN1Familia() {
        return codN1Familia;
    }

    /**
     * Sets the value of the codN1Familia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN1Familia(String value) {
        this.codN1Familia = value;
    }

    /**
     * Gets the value of the codN2Grupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN2Grupo() {
        return codN2Grupo;
    }

    /**
     * Sets the value of the codN2Grupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN2Grupo(String value) {
        this.codN2Grupo = value;
    }

    /**
     * Gets the value of the codN3SubGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN3SubGrupo() {
        return codN3SubGrupo;
    }

    /**
     * Sets the value of the codN3SubGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN3SubGrupo(String value) {
        this.codN3SubGrupo = value;
    }

    /**
     * Gets the value of the codN4Clase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN4Clase() {
        return codN4Clase;
    }

    /**
     * Sets the value of the codN4Clase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN4Clase(String value) {
        this.codN4Clase = value;
    }

    /**
     * Gets the value of the codN5SubClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN5SubClase() {
        return codN5SubClase;
    }

    /**
     * Sets the value of the codN5SubClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN5SubClase(String value) {
        this.codN5SubClase = value;
    }

    /**
     * Gets the value of the codN6Actividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodN6Actividad() {
        return codN6Actividad;
    }

    /**
     * Sets the value of the codN6Actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodN6Actividad(String value) {
        this.codN6Actividad = value;
    }

    /**
     * Gets the value of the n1Familia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN1Familia() {
        return n1Familia;
    }

    /**
     * Sets the value of the n1Familia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN1Familia(String value) {
        this.n1Familia = value;
    }

    /**
     * Gets the value of the n2Grupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN2Grupo() {
        return n2Grupo;
    }

    /**
     * Sets the value of the n2Grupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN2Grupo(String value) {
        this.n2Grupo = value;
    }

    /**
     * Gets the value of the n3SubGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN3SubGrupo() {
        return n3SubGrupo;
    }

    /**
     * Sets the value of the n3SubGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN3SubGrupo(String value) {
        this.n3SubGrupo = value;
    }

    /**
     * Gets the value of the n4Clase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN4Clase() {
        return n4Clase;
    }

    /**
     * Sets the value of the n4Clase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN4Clase(String value) {
        this.n4Clase = value;
    }

    /**
     * Gets the value of the n5SubClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN5SubClase() {
        return n5SubClase;
    }

    /**
     * Sets the value of the n5SubClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN5SubClase(String value) {
        this.n5SubClase = value;
    }

    /**
     * Gets the value of the n6Actividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN6Actividad() {
        return n6Actividad;
    }

    /**
     * Sets the value of the n6Actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN6Actividad(String value) {
        this.n6Actividad = value;
    }

}
