
package _154._1._180._10._8080.consultatitulossenescytwsv3.servicioconsultatitulo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultadeTitulosRequestResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultadeTitulosRequestResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="detalleGraduado" type="{http://10.180.1.154:8080/ConsultaTitulosSenescytWSv3/ServicioConsultaTitulo}graduadoReporteDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultadeTitulosRequestResponse", propOrder = {
    "detalleGraduado"
})
public class ConsultadeTitulosRequestResponse {

    protected GraduadoReporteDTO detalleGraduado;

    /**
     * Gets the value of the detalleGraduado property.
     * 
     * @return
     *     possible object is
     *     {@link GraduadoReporteDTO }
     *     
     */
    public GraduadoReporteDTO getDetalleGraduado() {
        return detalleGraduado;
    }

    /**
     * Sets the value of the detalleGraduado property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraduadoReporteDTO }
     *     
     */
    public void setDetalleGraduado(GraduadoReporteDTO value) {
        this.detalleGraduado = value;
    }

}
