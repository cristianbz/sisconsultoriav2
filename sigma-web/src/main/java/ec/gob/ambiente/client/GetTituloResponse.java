
package ec.gob.ambiente.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo.GraduadoReporteDTO;


/**
 * <p>Java class for getTituloResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTituloResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo}graduadoReporteDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTituloResponse", propOrder = {
    "_return"
})
public class GetTituloResponse {

    @XmlElement(name = "return")
    protected GraduadoReporteDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GraduadoReporteDTO }
     *     
     */
    public GraduadoReporteDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraduadoReporteDTO }
     *     
     */
    public void setReturn(GraduadoReporteDTO value) {
        this._return = value;
    }

}
