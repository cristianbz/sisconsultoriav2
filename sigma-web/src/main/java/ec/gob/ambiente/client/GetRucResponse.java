
package ec.gob.ambiente.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.gov.sri.wsconsultacontribuyente.ContribuyenteCompleto;


/**
 * <p>Java class for getRucResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRucResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://sri.gov.ec/wsConsultaContribuyente}contribuyenteCompleto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRucResponse", propOrder = {
    "_return"
})
public class GetRucResponse {

    @XmlElement(name = "return")
    protected ContribuyenteCompleto _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ContribuyenteCompleto }
     *     
     */
    public ContribuyenteCompleto getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContribuyenteCompleto }
     *     
     */
    public void setReturn(ContribuyenteCompleto value) {
        this._return = value;
    }

}
