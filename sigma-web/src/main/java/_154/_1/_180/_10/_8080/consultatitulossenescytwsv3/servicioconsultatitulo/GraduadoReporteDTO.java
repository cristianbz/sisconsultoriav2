
package _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for graduadoReporteDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="graduadoReporteDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="niveltitulos" type="{http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo}nivelTitulosDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "graduadoReporteDTO", propOrder = {
    "msg",
    "niveltitulos"
})
public class GraduadoReporteDTO {

    protected String msg;
    @XmlElement(nillable = true)
    protected List<NivelTitulosDTO> niveltitulos;

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * Gets the value of the niveltitulos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the niveltitulos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNiveltitulos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NivelTitulosDTO }
     * 
     * 
     */
    public List<NivelTitulosDTO> getNiveltitulos() {
        if (niveltitulos == null) {
            niveltitulos = new ArrayList<NivelTitulosDTO>();
        }
        return this.niveltitulos;
    }

}
