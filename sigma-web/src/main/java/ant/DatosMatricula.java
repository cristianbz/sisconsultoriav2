
package ant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosMatricula complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosMatricula"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="anio_Matriculado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="capacidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="chasis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cod_Matricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cod_error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cooperativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="disco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="doc_Propietario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaCaducidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecha_Retencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="infracciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lugar_Retenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="modelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="motor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="placa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="propietario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="restricciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="retenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ruc_Cooperativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo_Servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosMatricula", propOrder = {
    "anio",
    "anioMatriculado",
    "apellido1",
    "apellido2",
    "canal",
    "capacidad",
    "chasis",
    "clase",
    "codMatricula",
    "codError",
    "color",
    "cooperativa",
    "disco",
    "docPropietario",
    "estado",
    "fechaCaducidad",
    "fechaMatricula",
    "fechaRetencion",
    "infracciones",
    "lugarRetenido",
    "marca",
    "mensaje",
    "modelo",
    "motor",
    "placa",
    "propietario",
    "restricciones",
    "retenido",
    "rucCooperativa",
    "tipo",
    "tipoVehiculo",
    "tipoServicio",
    "usuario"
})
public class DatosMatricula {

    protected String anio;
    @XmlElement(name = "anio_Matriculado")
    protected String anioMatriculado;
    protected String apellido1;
    protected String apellido2;
    protected String canal;
    protected String capacidad;
    protected String chasis;
    protected String clase;
    @XmlElement(name = "cod_Matricula")
    protected String codMatricula;
    @XmlElement(name = "cod_error")
    protected String codError;
    protected String color;
    protected String cooperativa;
    protected String disco;
    @XmlElement(name = "doc_Propietario")
    protected String docPropietario;
    protected String estado;
    protected String fechaCaducidad;
    protected String fechaMatricula;
    @XmlElement(name = "fecha_Retencion")
    protected String fechaRetencion;
    protected String infracciones;
    @XmlElement(name = "lugar_Retenido")
    protected String lugarRetenido;
    protected String marca;
    protected String mensaje;
    protected String modelo;
    protected String motor;
    protected String placa;
    protected String propietario;
    protected String restricciones;
    protected String retenido;
    @XmlElement(name = "ruc_Cooperativa")
    protected String rucCooperativa;
    protected String tipo;
    protected String tipoVehiculo;
    @XmlElement(name = "tipo_Servicio")
    protected String tipoServicio;
    protected String usuario;

    /**
     * Gets the value of the anio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnio(String value) {
        this.anio = value;
    }

    /**
     * Gets the value of the anioMatriculado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnioMatriculado() {
        return anioMatriculado;
    }

    /**
     * Sets the value of the anioMatriculado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnioMatriculado(String value) {
        this.anioMatriculado = value;
    }

    /**
     * Gets the value of the apellido1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Sets the value of the apellido1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Gets the value of the apellido2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Sets the value of the apellido2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Gets the value of the capacidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapacidad() {
        return capacidad;
    }

    /**
     * Sets the value of the capacidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapacidad(String value) {
        this.capacidad = value;
    }

    /**
     * Gets the value of the chasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChasis() {
        return chasis;
    }

    /**
     * Sets the value of the chasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChasis(String value) {
        this.chasis = value;
    }

    /**
     * Gets the value of the clase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClase() {
        return clase;
    }

    /**
     * Sets the value of the clase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClase(String value) {
        this.clase = value;
    }

    /**
     * Gets the value of the codMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMatricula() {
        return codMatricula;
    }

    /**
     * Sets the value of the codMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMatricula(String value) {
        this.codMatricula = value;
    }

    /**
     * Gets the value of the codError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodError() {
        return codError;
    }

    /**
     * Sets the value of the codError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodError(String value) {
        this.codError = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(String value) {
        this.color = value;
    }

    /**
     * Gets the value of the cooperativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCooperativa() {
        return cooperativa;
    }

    /**
     * Sets the value of the cooperativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCooperativa(String value) {
        this.cooperativa = value;
    }

    /**
     * Gets the value of the disco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisco() {
        return disco;
    }

    /**
     * Sets the value of the disco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisco(String value) {
        this.disco = value;
    }

    /**
     * Gets the value of the docPropietario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocPropietario() {
        return docPropietario;
    }

    /**
     * Sets the value of the docPropietario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocPropietario(String value) {
        this.docPropietario = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fechaCaducidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Sets the value of the fechaCaducidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCaducidad(String value) {
        this.fechaCaducidad = value;
    }

    /**
     * Gets the value of the fechaMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaMatricula() {
        return fechaMatricula;
    }

    /**
     * Sets the value of the fechaMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaMatricula(String value) {
        this.fechaMatricula = value;
    }

    /**
     * Gets the value of the fechaRetencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRetencion() {
        return fechaRetencion;
    }

    /**
     * Sets the value of the fechaRetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRetencion(String value) {
        this.fechaRetencion = value;
    }

    /**
     * Gets the value of the infracciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfracciones() {
        return infracciones;
    }

    /**
     * Sets the value of the infracciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfracciones(String value) {
        this.infracciones = value;
    }

    /**
     * Gets the value of the lugarRetenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarRetenido() {
        return lugarRetenido;
    }

    /**
     * Sets the value of the lugarRetenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarRetenido(String value) {
        this.lugarRetenido = value;
    }

    /**
     * Gets the value of the marca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the value of the marca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarca(String value) {
        this.marca = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the modelo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets the value of the modelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelo(String value) {
        this.modelo = value;
    }

    /**
     * Gets the value of the motor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotor() {
        return motor;
    }

    /**
     * Sets the value of the motor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotor(String value) {
        this.motor = value;
    }

    /**
     * Gets the value of the placa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Sets the value of the placa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaca(String value) {
        this.placa = value;
    }

    /**
     * Gets the value of the propietario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * Sets the value of the propietario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropietario(String value) {
        this.propietario = value;
    }

    /**
     * Gets the value of the restricciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestricciones() {
        return restricciones;
    }

    /**
     * Sets the value of the restricciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestricciones(String value) {
        this.restricciones = value;
    }

    /**
     * Gets the value of the retenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetenido() {
        return retenido;
    }

    /**
     * Sets the value of the retenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetenido(String value) {
        this.retenido = value;
    }

    /**
     * Gets the value of the rucCooperativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRucCooperativa() {
        return rucCooperativa;
    }

    /**
     * Sets the value of the rucCooperativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRucCooperativa(String value) {
        this.rucCooperativa = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the tipoVehiculo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Sets the value of the tipoVehiculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoVehiculo(String value) {
        this.tipoVehiculo = value;
    }

    /**
     * Gets the value of the tipoServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * Sets the value of the tipoServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServicio(String value) {
        this.tipoServicio = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

}
