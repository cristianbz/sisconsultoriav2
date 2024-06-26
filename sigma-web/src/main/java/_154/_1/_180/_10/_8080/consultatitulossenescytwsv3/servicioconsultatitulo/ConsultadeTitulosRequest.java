
package _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultadeTitulosRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultadeTitulosRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CedulaTitulado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultadeTitulosRequest", propOrder = {
    "cedulaTitulado"
})
public class ConsultadeTitulosRequest {

    @XmlElement(name = "CedulaTitulado")
    protected String cedulaTitulado;

    /**
     * Gets the value of the cedulaTitulado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedulaTitulado() {
        return cedulaTitulado;
    }

    /**
     * Sets the value of the cedulaTitulado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedulaTitulado(String value) {
        this.cedulaTitulado = value;
    }

}
