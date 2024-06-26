
package ec.gob.registrocivil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ciudadano complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ciudadano"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Acta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ActaDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ActaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AnioInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Apellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantonNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Clase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ClaseDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ClaseMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCantonNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCondicionCedulado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCondicionDonante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoEstadoCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoGrupoEtnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoInstruccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoNacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoNacionalidadMadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoNacionalidadPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoPaisNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoParroquiaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProfesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProvinciaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoSexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoTipoCedula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CondicionCedulado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CondicionDonante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Conyuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EstadoCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaActualizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaCedulacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaInscripcionNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Firma" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="FirmaElectronica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Fotografia" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="Genero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GrupoEtnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndividualDactilar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Instruccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NacionalidadMadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NacionalidadPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombreMadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombrePadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NuiConyuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NuiMadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NuiPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumeroCasa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ObservacionInstitucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaginaDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaginaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParroquiaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Profesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaFallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaInscripcionDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaInscripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProvinciaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoCedula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tomo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TomoDefuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TomoMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ciudadano", propOrder = {
    "acta",
    "actaDefuncion",
    "actaMatrimonio",
    "anioInscripcion",
    "apellidos",
    "calle",
    "cantonDomicilio",
    "cantonFallecimiento",
    "cantonInscripcion",
    "cantonInscripcionDefuncion",
    "cantonInscripcionGenero",
    "cantonMatrimonio",
    "cantonNacimiento",
    "clase",
    "claseDefuncion",
    "claseMatrimonio",
    "codigoCantonDomicilio",
    "codigoCantonFallecimiento",
    "codigoCantonInscripcion",
    "codigoCantonInscripcionDefuncion",
    "codigoCantonInscripcionGenero",
    "codigoCantonMatrimonio",
    "codigoCantonNacimiento",
    "codigoCondicionCedulado",
    "codigoCondicionDonante",
    "codigoDomicilio",
    "codigoError",
    "codigoEstadoCivil",
    "codigoGenero",
    "codigoGrupoEtnico",
    "codigoInstruccion",
    "codigoLugarDefuncion",
    "codigoLugarFallecimiento",
    "codigoLugarInscripcion",
    "codigoLugarInscripcionGenero",
    "codigoLugarMatrimonio",
    "codigoLugarNacimiento",
    "codigoNacionalidad",
    "codigoNacionalidadMadre",
    "codigoNacionalidadPadre",
    "codigoPaisDomicilio",
    "codigoPaisFallecimiento",
    "codigoPaisInscripcion",
    "codigoPaisInscripcionDefuncion",
    "codigoPaisInscripcionGenero",
    "codigoPaisMatrimonio",
    "codigoPaisNacimiento",
    "codigoParroquiaDomicilio",
    "codigoParroquiaFallecimiento",
    "codigoParroquiaInscripcion",
    "codigoParroquiaInscripcionDefuncion",
    "codigoParroquiaInscripcionGenero",
    "codigoParroquiaMatrimonio",
    "codigoParroquiaNacimiento",
    "codigoProfesion",
    "codigoProvinciaDomicilio",
    "codigoProvinciaFallecimiento",
    "codigoProvinciaInscripcion",
    "codigoProvinciaInscripcionDefuncion",
    "codigoProvinciaInscripcionGenero",
    "codigoProvinciaMatrimonio",
    "codigoProvinciaNacimiento",
    "codigoSexo",
    "codigoTipoCedula",
    "condicionCedulado",
    "condicionDonante",
    "conyuge",
    "domicilio",
    "error",
    "estadoCivil",
    "fechaActualizacion",
    "fechaCedulacion",
    "fechaFallecimiento",
    "fechaInscripcionDefuncion",
    "fechaInscripcionGenero",
    "fechaInscripcionNacimiento",
    "fechaMatrimonio",
    "fechaNacimiento",
    "firma",
    "firmaElectronica",
    "fotografia",
    "genero",
    "grupoEtnico",
    "individualDactilar",
    "instruccion",
    "lugarDefuncion",
    "lugarFallecimiento",
    "lugarInscripcion",
    "lugarInscripcionGenero",
    "lugarMatrimonio",
    "lugarNacimiento",
    "nui",
    "nacionalidad",
    "nacionalidadMadre",
    "nacionalidadPadre",
    "nombre",
    "nombreMadre",
    "nombrePadre",
    "nombres",
    "nuiConyuge",
    "nuiMadre",
    "nuiPadre",
    "numeroCasa",
    "observacion",
    "observacionInstitucion",
    "pagina",
    "paginaDefuncion",
    "paginaMatrimonio",
    "paisDomicilio",
    "paisFallecimiento",
    "paisInscripcion",
    "paisInscripcionDefuncion",
    "paisInscripcionGenero",
    "paisMatrimonio",
    "paisNacimiento",
    "parroquiaDomicilio",
    "parroquiaFallecimiento",
    "parroquiaInscripcion",
    "parroquiaInscripcionDefuncion",
    "parroquiaInscripcionGenero",
    "parroquiaMatrimonio",
    "parroquiaNacimiento",
    "profesion",
    "provinciaDomicilio",
    "provinciaFallecimiento",
    "provinciaInscripcion",
    "provinciaInscripcionDefuncion",
    "provinciaInscripcionGenero",
    "provinciaMatrimonio",
    "provinciaNacimiento",
    "sexo",
    "tipoCedula",
    "tomo",
    "tomoDefuncion",
    "tomoMatrimonio"
})
public class Ciudadano {

    @XmlElement(name = "Acta")
    protected String acta;
    @XmlElement(name = "ActaDefuncion")
    protected String actaDefuncion;
    @XmlElement(name = "ActaMatrimonio")
    protected String actaMatrimonio;
    @XmlElement(name = "AnioInscripcion")
    protected String anioInscripcion;
    @XmlElement(name = "Apellidos")
    protected String apellidos;
    @XmlElement(name = "Calle")
    protected String calle;
    @XmlElement(name = "CantonDomicilio")
    protected String cantonDomicilio;
    @XmlElement(name = "CantonFallecimiento")
    protected String cantonFallecimiento;
    @XmlElement(name = "CantonInscripcion")
    protected String cantonInscripcion;
    @XmlElement(name = "CantonInscripcionDefuncion")
    protected String cantonInscripcionDefuncion;
    @XmlElement(name = "CantonInscripcionGenero")
    protected String cantonInscripcionGenero;
    @XmlElement(name = "CantonMatrimonio")
    protected String cantonMatrimonio;
    @XmlElement(name = "CantonNacimiento")
    protected String cantonNacimiento;
    @XmlElement(name = "Clase")
    protected String clase;
    @XmlElement(name = "ClaseDefuncion")
    protected String claseDefuncion;
    @XmlElement(name = "ClaseMatrimonio")
    protected String claseMatrimonio;
    @XmlElement(name = "CodigoCantonDomicilio")
    protected String codigoCantonDomicilio;
    @XmlElement(name = "CodigoCantonFallecimiento")
    protected String codigoCantonFallecimiento;
    @XmlElement(name = "CodigoCantonInscripcion")
    protected String codigoCantonInscripcion;
    @XmlElement(name = "CodigoCantonInscripcionDefuncion")
    protected String codigoCantonInscripcionDefuncion;
    @XmlElement(name = "CodigoCantonInscripcionGenero")
    protected String codigoCantonInscripcionGenero;
    @XmlElement(name = "CodigoCantonMatrimonio")
    protected String codigoCantonMatrimonio;
    @XmlElement(name = "CodigoCantonNacimiento")
    protected String codigoCantonNacimiento;
    @XmlElement(name = "CodigoCondicionCedulado")
    protected String codigoCondicionCedulado;
    @XmlElement(name = "CodigoCondicionDonante")
    protected String codigoCondicionDonante;
    @XmlElement(name = "CodigoDomicilio")
    protected String codigoDomicilio;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "CodigoEstadoCivil")
    protected String codigoEstadoCivil;
    @XmlElement(name = "CodigoGenero")
    protected String codigoGenero;
    @XmlElement(name = "CodigoGrupoEtnico")
    protected String codigoGrupoEtnico;
    @XmlElement(name = "CodigoInstruccion")
    protected String codigoInstruccion;
    @XmlElement(name = "CodigoLugarDefuncion")
    protected String codigoLugarDefuncion;
    @XmlElement(name = "CodigoLugarFallecimiento")
    protected String codigoLugarFallecimiento;
    @XmlElement(name = "CodigoLugarInscripcion")
    protected String codigoLugarInscripcion;
    @XmlElement(name = "CodigoLugarInscripcionGenero")
    protected String codigoLugarInscripcionGenero;
    @XmlElement(name = "CodigoLugarMatrimonio")
    protected String codigoLugarMatrimonio;
    @XmlElement(name = "CodigoLugarNacimiento")
    protected String codigoLugarNacimiento;
    @XmlElement(name = "CodigoNacionalidad")
    protected String codigoNacionalidad;
    @XmlElement(name = "CodigoNacionalidadMadre")
    protected String codigoNacionalidadMadre;
    @XmlElement(name = "CodigoNacionalidadPadre")
    protected String codigoNacionalidadPadre;
    @XmlElement(name = "CodigoPaisDomicilio")
    protected String codigoPaisDomicilio;
    @XmlElement(name = "CodigoPaisFallecimiento")
    protected String codigoPaisFallecimiento;
    @XmlElement(name = "CodigoPaisInscripcion")
    protected String codigoPaisInscripcion;
    @XmlElement(name = "CodigoPaisInscripcionDefuncion")
    protected String codigoPaisInscripcionDefuncion;
    @XmlElement(name = "CodigoPaisInscripcionGenero")
    protected String codigoPaisInscripcionGenero;
    @XmlElement(name = "CodigoPaisMatrimonio")
    protected String codigoPaisMatrimonio;
    @XmlElement(name = "CodigoPaisNacimiento")
    protected String codigoPaisNacimiento;
    @XmlElement(name = "CodigoParroquiaDomicilio")
    protected String codigoParroquiaDomicilio;
    @XmlElement(name = "CodigoParroquiaFallecimiento")
    protected String codigoParroquiaFallecimiento;
    @XmlElement(name = "CodigoParroquiaInscripcion")
    protected String codigoParroquiaInscripcion;
    @XmlElement(name = "CodigoParroquiaInscripcionDefuncion")
    protected String codigoParroquiaInscripcionDefuncion;
    @XmlElement(name = "CodigoParroquiaInscripcionGenero")
    protected String codigoParroquiaInscripcionGenero;
    @XmlElement(name = "CodigoParroquiaMatrimonio")
    protected String codigoParroquiaMatrimonio;
    @XmlElement(name = "CodigoParroquiaNacimiento")
    protected String codigoParroquiaNacimiento;
    @XmlElement(name = "CodigoProfesion")
    protected String codigoProfesion;
    @XmlElement(name = "CodigoProvinciaDomicilio")
    protected String codigoProvinciaDomicilio;
    @XmlElement(name = "CodigoProvinciaFallecimiento")
    protected String codigoProvinciaFallecimiento;
    @XmlElement(name = "CodigoProvinciaInscripcion")
    protected String codigoProvinciaInscripcion;
    @XmlElement(name = "CodigoProvinciaInscripcionDefuncion")
    protected String codigoProvinciaInscripcionDefuncion;
    @XmlElement(name = "CodigoProvinciaInscripcionGenero")
    protected String codigoProvinciaInscripcionGenero;
    @XmlElement(name = "CodigoProvinciaMatrimonio")
    protected String codigoProvinciaMatrimonio;
    @XmlElement(name = "CodigoProvinciaNacimiento")
    protected String codigoProvinciaNacimiento;
    @XmlElement(name = "CodigoSexo")
    protected String codigoSexo;
    @XmlElement(name = "CodigoTipoCedula")
    protected String codigoTipoCedula;
    @XmlElement(name = "CondicionCedulado")
    protected String condicionCedulado;
    @XmlElement(name = "CondicionDonante")
    protected String condicionDonante;
    @XmlElement(name = "Conyuge")
    protected String conyuge;
    @XmlElement(name = "Domicilio")
    protected String domicilio;
    @XmlElement(name = "Error")
    protected String error;
    @XmlElement(name = "EstadoCivil")
    protected String estadoCivil;
    @XmlElement(name = "FechaActualizacion")
    protected String fechaActualizacion;
    @XmlElement(name = "FechaCedulacion")
    protected String fechaCedulacion;
    @XmlElement(name = "FechaFallecimiento")
    protected String fechaFallecimiento;
    @XmlElement(name = "FechaInscripcionDefuncion")
    protected String fechaInscripcionDefuncion;
    @XmlElement(name = "FechaInscripcionGenero")
    protected String fechaInscripcionGenero;
    @XmlElement(name = "FechaInscripcionNacimiento")
    protected String fechaInscripcionNacimiento;
    @XmlElement(name = "FechaMatrimonio")
    protected String fechaMatrimonio;
    @XmlElement(name = "FechaNacimiento")
    protected String fechaNacimiento;
    @XmlElement(name = "Firma")
    protected byte[] firma;
    @XmlElement(name = "FirmaElectronica")
    protected String firmaElectronica;
    @XmlElement(name = "Fotografia")
    protected byte[] fotografia;
    @XmlElement(name = "Genero")
    protected String genero;
    @XmlElement(name = "GrupoEtnico")
    protected String grupoEtnico;
    @XmlElement(name = "IndividualDactilar")
    protected String individualDactilar;
    @XmlElement(name = "Instruccion")
    protected String instruccion;
    @XmlElement(name = "LugarDefuncion")
    protected String lugarDefuncion;
    @XmlElement(name = "LugarFallecimiento")
    protected String lugarFallecimiento;
    @XmlElement(name = "LugarInscripcion")
    protected String lugarInscripcion;
    @XmlElement(name = "LugarInscripcionGenero")
    protected String lugarInscripcionGenero;
    @XmlElement(name = "LugarMatrimonio")
    protected String lugarMatrimonio;
    @XmlElement(name = "LugarNacimiento")
    protected String lugarNacimiento;
    @XmlElement(name = "NUI")
    protected String nui;
    @XmlElement(name = "Nacionalidad")
    protected String nacionalidad;
    @XmlElement(name = "NacionalidadMadre")
    protected String nacionalidadMadre;
    @XmlElement(name = "NacionalidadPadre")
    protected String nacionalidadPadre;
    @XmlElement(name = "Nombre")
    protected String nombre;
    @XmlElement(name = "NombreMadre")
    protected String nombreMadre;
    @XmlElement(name = "NombrePadre")
    protected String nombrePadre;
    @XmlElement(name = "Nombres")
    protected String nombres;
    @XmlElement(name = "NuiConyuge")
    protected String nuiConyuge;
    @XmlElement(name = "NuiMadre")
    protected String nuiMadre;
    @XmlElement(name = "NuiPadre")
    protected String nuiPadre;
    @XmlElement(name = "NumeroCasa")
    protected String numeroCasa;
    @XmlElement(name = "Observacion")
    protected String observacion;
    @XmlElement(name = "ObservacionInstitucion")
    protected String observacionInstitucion;
    @XmlElement(name = "Pagina")
    protected String pagina;
    @XmlElement(name = "PaginaDefuncion")
    protected String paginaDefuncion;
    @XmlElement(name = "PaginaMatrimonio")
    protected String paginaMatrimonio;
    @XmlElement(name = "PaisDomicilio")
    protected String paisDomicilio;
    @XmlElement(name = "PaisFallecimiento")
    protected String paisFallecimiento;
    @XmlElement(name = "PaisInscripcion")
    protected String paisInscripcion;
    @XmlElement(name = "PaisInscripcionDefuncion")
    protected String paisInscripcionDefuncion;
    @XmlElement(name = "PaisInscripcionGenero")
    protected String paisInscripcionGenero;
    @XmlElement(name = "PaisMatrimonio")
    protected String paisMatrimonio;
    @XmlElement(name = "PaisNacimiento")
    protected String paisNacimiento;
    @XmlElement(name = "ParroquiaDomicilio")
    protected String parroquiaDomicilio;
    @XmlElement(name = "ParroquiaFallecimiento")
    protected String parroquiaFallecimiento;
    @XmlElement(name = "ParroquiaInscripcion")
    protected String parroquiaInscripcion;
    @XmlElement(name = "ParroquiaInscripcionDefuncion")
    protected String parroquiaInscripcionDefuncion;
    @XmlElement(name = "ParroquiaInscripcionGenero")
    protected String parroquiaInscripcionGenero;
    @XmlElement(name = "ParroquiaMatrimonio")
    protected String parroquiaMatrimonio;
    @XmlElement(name = "ParroquiaNacimiento")
    protected String parroquiaNacimiento;
    @XmlElement(name = "Profesion")
    protected String profesion;
    @XmlElement(name = "ProvinciaDomicilio")
    protected String provinciaDomicilio;
    @XmlElement(name = "ProvinciaFallecimiento")
    protected String provinciaFallecimiento;
    @XmlElement(name = "ProvinciaInscripcion")
    protected String provinciaInscripcion;
    @XmlElement(name = "ProvinciaInscripcionDefuncion")
    protected String provinciaInscripcionDefuncion;
    @XmlElement(name = "ProvinciaInscripcionGenero")
    protected String provinciaInscripcionGenero;
    @XmlElement(name = "ProvinciaMatrimonio")
    protected String provinciaMatrimonio;
    @XmlElement(name = "ProvinciaNacimiento")
    protected String provinciaNacimiento;
    @XmlElement(name = "Sexo")
    protected String sexo;
    @XmlElement(name = "TipoCedula")
    protected String tipoCedula;
    @XmlElement(name = "Tomo")
    protected String tomo;
    @XmlElement(name = "TomoDefuncion")
    protected String tomoDefuncion;
    @XmlElement(name = "TomoMatrimonio")
    protected String tomoMatrimonio;

    /**
     * Gets the value of the acta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActa() {
        return acta;
    }

    /**
     * Sets the value of the acta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActa(String value) {
        this.acta = value;
    }

    /**
     * Gets the value of the actaDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActaDefuncion() {
        return actaDefuncion;
    }

    /**
     * Sets the value of the actaDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActaDefuncion(String value) {
        this.actaDefuncion = value;
    }

    /**
     * Gets the value of the actaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActaMatrimonio() {
        return actaMatrimonio;
    }

    /**
     * Sets the value of the actaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActaMatrimonio(String value) {
        this.actaMatrimonio = value;
    }

    /**
     * Gets the value of the anioInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnioInscripcion() {
        return anioInscripcion;
    }

    /**
     * Sets the value of the anioInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnioInscripcion(String value) {
        this.anioInscripcion = value;
    }

    /**
     * Gets the value of the apellidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets the value of the apellidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidos(String value) {
        this.apellidos = value;
    }

    /**
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the cantonDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonDomicilio() {
        return cantonDomicilio;
    }

    /**
     * Sets the value of the cantonDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonDomicilio(String value) {
        this.cantonDomicilio = value;
    }

    /**
     * Gets the value of the cantonFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonFallecimiento() {
        return cantonFallecimiento;
    }

    /**
     * Sets the value of the cantonFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonFallecimiento(String value) {
        this.cantonFallecimiento = value;
    }

    /**
     * Gets the value of the cantonInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonInscripcion() {
        return cantonInscripcion;
    }

    /**
     * Sets the value of the cantonInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonInscripcion(String value) {
        this.cantonInscripcion = value;
    }

    /**
     * Gets the value of the cantonInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonInscripcionDefuncion() {
        return cantonInscripcionDefuncion;
    }

    /**
     * Sets the value of the cantonInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonInscripcionDefuncion(String value) {
        this.cantonInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the cantonInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonInscripcionGenero() {
        return cantonInscripcionGenero;
    }

    /**
     * Sets the value of the cantonInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonInscripcionGenero(String value) {
        this.cantonInscripcionGenero = value;
    }

    /**
     * Gets the value of the cantonMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonMatrimonio() {
        return cantonMatrimonio;
    }

    /**
     * Sets the value of the cantonMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonMatrimonio(String value) {
        this.cantonMatrimonio = value;
    }

    /**
     * Gets the value of the cantonNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonNacimiento() {
        return cantonNacimiento;
    }

    /**
     * Sets the value of the cantonNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonNacimiento(String value) {
        this.cantonNacimiento = value;
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
     * Gets the value of the claseDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseDefuncion() {
        return claseDefuncion;
    }

    /**
     * Sets the value of the claseDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseDefuncion(String value) {
        this.claseDefuncion = value;
    }

    /**
     * Gets the value of the claseMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseMatrimonio() {
        return claseMatrimonio;
    }

    /**
     * Sets the value of the claseMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseMatrimonio(String value) {
        this.claseMatrimonio = value;
    }

    /**
     * Gets the value of the codigoCantonDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonDomicilio() {
        return codigoCantonDomicilio;
    }

    /**
     * Sets the value of the codigoCantonDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonDomicilio(String value) {
        this.codigoCantonDomicilio = value;
    }

    /**
     * Gets the value of the codigoCantonFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonFallecimiento() {
        return codigoCantonFallecimiento;
    }

    /**
     * Sets the value of the codigoCantonFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonFallecimiento(String value) {
        this.codigoCantonFallecimiento = value;
    }

    /**
     * Gets the value of the codigoCantonInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonInscripcion() {
        return codigoCantonInscripcion;
    }

    /**
     * Sets the value of the codigoCantonInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonInscripcion(String value) {
        this.codigoCantonInscripcion = value;
    }

    /**
     * Gets the value of the codigoCantonInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonInscripcionDefuncion() {
        return codigoCantonInscripcionDefuncion;
    }

    /**
     * Sets the value of the codigoCantonInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonInscripcionDefuncion(String value) {
        this.codigoCantonInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the codigoCantonInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonInscripcionGenero() {
        return codigoCantonInscripcionGenero;
    }

    /**
     * Sets the value of the codigoCantonInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonInscripcionGenero(String value) {
        this.codigoCantonInscripcionGenero = value;
    }

    /**
     * Gets the value of the codigoCantonMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonMatrimonio() {
        return codigoCantonMatrimonio;
    }

    /**
     * Sets the value of the codigoCantonMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonMatrimonio(String value) {
        this.codigoCantonMatrimonio = value;
    }

    /**
     * Gets the value of the codigoCantonNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCantonNacimiento() {
        return codigoCantonNacimiento;
    }

    /**
     * Sets the value of the codigoCantonNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCantonNacimiento(String value) {
        this.codigoCantonNacimiento = value;
    }

    /**
     * Gets the value of the codigoCondicionCedulado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCondicionCedulado() {
        return codigoCondicionCedulado;
    }

    /**
     * Sets the value of the codigoCondicionCedulado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCondicionCedulado(String value) {
        this.codigoCondicionCedulado = value;
    }

    /**
     * Gets the value of the codigoCondicionDonante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCondicionDonante() {
        return codigoCondicionDonante;
    }

    /**
     * Sets the value of the codigoCondicionDonante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCondicionDonante(String value) {
        this.codigoCondicionDonante = value;
    }

    /**
     * Gets the value of the codigoDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDomicilio() {
        return codigoDomicilio;
    }

    /**
     * Sets the value of the codigoDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDomicilio(String value) {
        this.codigoDomicilio = value;
    }

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the codigoEstadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    /**
     * Sets the value of the codigoEstadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoCivil(String value) {
        this.codigoEstadoCivil = value;
    }

    /**
     * Gets the value of the codigoGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoGenero() {
        return codigoGenero;
    }

    /**
     * Sets the value of the codigoGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoGenero(String value) {
        this.codigoGenero = value;
    }

    /**
     * Gets the value of the codigoGrupoEtnico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoGrupoEtnico() {
        return codigoGrupoEtnico;
    }

    /**
     * Sets the value of the codigoGrupoEtnico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoGrupoEtnico(String value) {
        this.codigoGrupoEtnico = value;
    }

    /**
     * Gets the value of the codigoInstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoInstruccion() {
        return codigoInstruccion;
    }

    /**
     * Sets the value of the codigoInstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoInstruccion(String value) {
        this.codigoInstruccion = value;
    }

    /**
     * Gets the value of the codigoLugarDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarDefuncion() {
        return codigoLugarDefuncion;
    }

    /**
     * Sets the value of the codigoLugarDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarDefuncion(String value) {
        this.codigoLugarDefuncion = value;
    }

    /**
     * Gets the value of the codigoLugarFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarFallecimiento() {
        return codigoLugarFallecimiento;
    }

    /**
     * Sets the value of the codigoLugarFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarFallecimiento(String value) {
        this.codigoLugarFallecimiento = value;
    }

    /**
     * Gets the value of the codigoLugarInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarInscripcion() {
        return codigoLugarInscripcion;
    }

    /**
     * Sets the value of the codigoLugarInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarInscripcion(String value) {
        this.codigoLugarInscripcion = value;
    }

    /**
     * Gets the value of the codigoLugarInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarInscripcionGenero() {
        return codigoLugarInscripcionGenero;
    }

    /**
     * Sets the value of the codigoLugarInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarInscripcionGenero(String value) {
        this.codigoLugarInscripcionGenero = value;
    }

    /**
     * Gets the value of the codigoLugarMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarMatrimonio() {
        return codigoLugarMatrimonio;
    }

    /**
     * Sets the value of the codigoLugarMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarMatrimonio(String value) {
        this.codigoLugarMatrimonio = value;
    }

    /**
     * Gets the value of the codigoLugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLugarNacimiento() {
        return codigoLugarNacimiento;
    }

    /**
     * Sets the value of the codigoLugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLugarNacimiento(String value) {
        this.codigoLugarNacimiento = value;
    }

    /**
     * Gets the value of the codigoNacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoNacionalidad() {
        return codigoNacionalidad;
    }

    /**
     * Sets the value of the codigoNacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoNacionalidad(String value) {
        this.codigoNacionalidad = value;
    }

    /**
     * Gets the value of the codigoNacionalidadMadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoNacionalidadMadre() {
        return codigoNacionalidadMadre;
    }

    /**
     * Sets the value of the codigoNacionalidadMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoNacionalidadMadre(String value) {
        this.codigoNacionalidadMadre = value;
    }

    /**
     * Gets the value of the codigoNacionalidadPadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoNacionalidadPadre() {
        return codigoNacionalidadPadre;
    }

    /**
     * Sets the value of the codigoNacionalidadPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoNacionalidadPadre(String value) {
        this.codigoNacionalidadPadre = value;
    }

    /**
     * Gets the value of the codigoPaisDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisDomicilio() {
        return codigoPaisDomicilio;
    }

    /**
     * Sets the value of the codigoPaisDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisDomicilio(String value) {
        this.codigoPaisDomicilio = value;
    }

    /**
     * Gets the value of the codigoPaisFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisFallecimiento() {
        return codigoPaisFallecimiento;
    }

    /**
     * Sets the value of the codigoPaisFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisFallecimiento(String value) {
        this.codigoPaisFallecimiento = value;
    }

    /**
     * Gets the value of the codigoPaisInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisInscripcion() {
        return codigoPaisInscripcion;
    }

    /**
     * Sets the value of the codigoPaisInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisInscripcion(String value) {
        this.codigoPaisInscripcion = value;
    }

    /**
     * Gets the value of the codigoPaisInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisInscripcionDefuncion() {
        return codigoPaisInscripcionDefuncion;
    }

    /**
     * Sets the value of the codigoPaisInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisInscripcionDefuncion(String value) {
        this.codigoPaisInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the codigoPaisInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisInscripcionGenero() {
        return codigoPaisInscripcionGenero;
    }

    /**
     * Sets the value of the codigoPaisInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisInscripcionGenero(String value) {
        this.codigoPaisInscripcionGenero = value;
    }

    /**
     * Gets the value of the codigoPaisMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisMatrimonio() {
        return codigoPaisMatrimonio;
    }

    /**
     * Sets the value of the codigoPaisMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisMatrimonio(String value) {
        this.codigoPaisMatrimonio = value;
    }

    /**
     * Gets the value of the codigoPaisNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPaisNacimiento() {
        return codigoPaisNacimiento;
    }

    /**
     * Sets the value of the codigoPaisNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPaisNacimiento(String value) {
        this.codigoPaisNacimiento = value;
    }

    /**
     * Gets the value of the codigoParroquiaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaDomicilio() {
        return codigoParroquiaDomicilio;
    }

    /**
     * Sets the value of the codigoParroquiaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaDomicilio(String value) {
        this.codigoParroquiaDomicilio = value;
    }

    /**
     * Gets the value of the codigoParroquiaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaFallecimiento() {
        return codigoParroquiaFallecimiento;
    }

    /**
     * Sets the value of the codigoParroquiaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaFallecimiento(String value) {
        this.codigoParroquiaFallecimiento = value;
    }

    /**
     * Gets the value of the codigoParroquiaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaInscripcion() {
        return codigoParroquiaInscripcion;
    }

    /**
     * Sets the value of the codigoParroquiaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaInscripcion(String value) {
        this.codigoParroquiaInscripcion = value;
    }

    /**
     * Gets the value of the codigoParroquiaInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaInscripcionDefuncion() {
        return codigoParroquiaInscripcionDefuncion;
    }

    /**
     * Sets the value of the codigoParroquiaInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaInscripcionDefuncion(String value) {
        this.codigoParroquiaInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the codigoParroquiaInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaInscripcionGenero() {
        return codigoParroquiaInscripcionGenero;
    }

    /**
     * Sets the value of the codigoParroquiaInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaInscripcionGenero(String value) {
        this.codigoParroquiaInscripcionGenero = value;
    }

    /**
     * Gets the value of the codigoParroquiaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaMatrimonio() {
        return codigoParroquiaMatrimonio;
    }

    /**
     * Sets the value of the codigoParroquiaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaMatrimonio(String value) {
        this.codigoParroquiaMatrimonio = value;
    }

    /**
     * Gets the value of the codigoParroquiaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoParroquiaNacimiento() {
        return codigoParroquiaNacimiento;
    }

    /**
     * Sets the value of the codigoParroquiaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoParroquiaNacimiento(String value) {
        this.codigoParroquiaNacimiento = value;
    }

    /**
     * Gets the value of the codigoProfesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProfesion() {
        return codigoProfesion;
    }

    /**
     * Sets the value of the codigoProfesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProfesion(String value) {
        this.codigoProfesion = value;
    }

    /**
     * Gets the value of the codigoProvinciaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaDomicilio() {
        return codigoProvinciaDomicilio;
    }

    /**
     * Sets the value of the codigoProvinciaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaDomicilio(String value) {
        this.codigoProvinciaDomicilio = value;
    }

    /**
     * Gets the value of the codigoProvinciaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaFallecimiento() {
        return codigoProvinciaFallecimiento;
    }

    /**
     * Sets the value of the codigoProvinciaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaFallecimiento(String value) {
        this.codigoProvinciaFallecimiento = value;
    }

    /**
     * Gets the value of the codigoProvinciaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaInscripcion() {
        return codigoProvinciaInscripcion;
    }

    /**
     * Sets the value of the codigoProvinciaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaInscripcion(String value) {
        this.codigoProvinciaInscripcion = value;
    }

    /**
     * Gets the value of the codigoProvinciaInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaInscripcionDefuncion() {
        return codigoProvinciaInscripcionDefuncion;
    }

    /**
     * Sets the value of the codigoProvinciaInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaInscripcionDefuncion(String value) {
        this.codigoProvinciaInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the codigoProvinciaInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaInscripcionGenero() {
        return codigoProvinciaInscripcionGenero;
    }

    /**
     * Sets the value of the codigoProvinciaInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaInscripcionGenero(String value) {
        this.codigoProvinciaInscripcionGenero = value;
    }

    /**
     * Gets the value of the codigoProvinciaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaMatrimonio() {
        return codigoProvinciaMatrimonio;
    }

    /**
     * Sets the value of the codigoProvinciaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaMatrimonio(String value) {
        this.codigoProvinciaMatrimonio = value;
    }

    /**
     * Gets the value of the codigoProvinciaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvinciaNacimiento() {
        return codigoProvinciaNacimiento;
    }

    /**
     * Sets the value of the codigoProvinciaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvinciaNacimiento(String value) {
        this.codigoProvinciaNacimiento = value;
    }

    /**
     * Gets the value of the codigoSexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSexo() {
        return codigoSexo;
    }

    /**
     * Sets the value of the codigoSexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSexo(String value) {
        this.codigoSexo = value;
    }

    /**
     * Gets the value of the codigoTipoCedula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoCedula() {
        return codigoTipoCedula;
    }

    /**
     * Sets the value of the codigoTipoCedula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoCedula(String value) {
        this.codigoTipoCedula = value;
    }

    /**
     * Gets the value of the condicionCedulado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionCedulado() {
        return condicionCedulado;
    }

    /**
     * Sets the value of the condicionCedulado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionCedulado(String value) {
        this.condicionCedulado = value;
    }

    /**
     * Gets the value of the condicionDonante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionDonante() {
        return condicionDonante;
    }

    /**
     * Sets the value of the condicionDonante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionDonante(String value) {
        this.condicionDonante = value;
    }

    /**
     * Gets the value of the conyuge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConyuge() {
        return conyuge;
    }

    /**
     * Sets the value of the conyuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConyuge(String value) {
        this.conyuge = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicilio(String value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the estadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the value of the estadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivil(String value) {
        this.estadoCivil = value;
    }

    /**
     * Gets the value of the fechaActualizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * Sets the value of the fechaActualizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaActualizacion(String value) {
        this.fechaActualizacion = value;
    }

    /**
     * Gets the value of the fechaCedulacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCedulacion() {
        return fechaCedulacion;
    }

    /**
     * Sets the value of the fechaCedulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCedulacion(String value) {
        this.fechaCedulacion = value;
    }

    /**
     * Gets the value of the fechaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    /**
     * Sets the value of the fechaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFallecimiento(String value) {
        this.fechaFallecimiento = value;
    }

    /**
     * Gets the value of the fechaInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcionDefuncion() {
        return fechaInscripcionDefuncion;
    }

    /**
     * Sets the value of the fechaInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcionDefuncion(String value) {
        this.fechaInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the fechaInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcionGenero() {
        return fechaInscripcionGenero;
    }

    /**
     * Sets the value of the fechaInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcionGenero(String value) {
        this.fechaInscripcionGenero = value;
    }

    /**
     * Gets the value of the fechaInscripcionNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcionNacimiento() {
        return fechaInscripcionNacimiento;
    }

    /**
     * Sets the value of the fechaInscripcionNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcionNacimiento(String value) {
        this.fechaInscripcionNacimiento = value;
    }

    /**
     * Gets the value of the fechaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaMatrimonio() {
        return fechaMatrimonio;
    }

    /**
     * Sets the value of the fechaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaMatrimonio(String value) {
        this.fechaMatrimonio = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the firma property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFirma(byte[] value) {
        this.firma = value;
    }

    /**
     * Gets the value of the firmaElectronica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    /**
     * Sets the value of the firmaElectronica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaElectronica(String value) {
        this.firmaElectronica = value;
    }

    /**
     * Gets the value of the fotografia property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFotografia() {
        return fotografia;
    }

    /**
     * Sets the value of the fotografia property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFotografia(byte[] value) {
        this.fotografia = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    /**
     * Gets the value of the grupoEtnico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoEtnico() {
        return grupoEtnico;
    }

    /**
     * Sets the value of the grupoEtnico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoEtnico(String value) {
        this.grupoEtnico = value;
    }

    /**
     * Gets the value of the individualDactilar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDactilar() {
        return individualDactilar;
    }

    /**
     * Sets the value of the individualDactilar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDactilar(String value) {
        this.individualDactilar = value;
    }

    /**
     * Gets the value of the instruccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstruccion() {
        return instruccion;
    }

    /**
     * Sets the value of the instruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstruccion(String value) {
        this.instruccion = value;
    }

    /**
     * Gets the value of the lugarDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDefuncion() {
        return lugarDefuncion;
    }

    /**
     * Sets the value of the lugarDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDefuncion(String value) {
        this.lugarDefuncion = value;
    }

    /**
     * Gets the value of the lugarFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarFallecimiento() {
        return lugarFallecimiento;
    }

    /**
     * Sets the value of the lugarFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarFallecimiento(String value) {
        this.lugarFallecimiento = value;
    }

    /**
     * Gets the value of the lugarInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarInscripcion() {
        return lugarInscripcion;
    }

    /**
     * Sets the value of the lugarInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarInscripcion(String value) {
        this.lugarInscripcion = value;
    }

    /**
     * Gets the value of the lugarInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarInscripcionGenero() {
        return lugarInscripcionGenero;
    }

    /**
     * Sets the value of the lugarInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarInscripcionGenero(String value) {
        this.lugarInscripcionGenero = value;
    }

    /**
     * Gets the value of the lugarMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarMatrimonio() {
        return lugarMatrimonio;
    }

    /**
     * Sets the value of the lugarMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarMatrimonio(String value) {
        this.lugarMatrimonio = value;
    }

    /**
     * Gets the value of the lugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Sets the value of the lugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarNacimiento(String value) {
        this.lugarNacimiento = value;
    }

    /**
     * Gets the value of the nui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUI() {
        return nui;
    }

    /**
     * Sets the value of the nui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUI(String value) {
        this.nui = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nacionalidadMadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadMadre() {
        return nacionalidadMadre;
    }

    /**
     * Sets the value of the nacionalidadMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadMadre(String value) {
        this.nacionalidadMadre = value;
    }

    /**
     * Gets the value of the nacionalidadPadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadPadre() {
        return nacionalidadPadre;
    }

    /**
     * Sets the value of the nacionalidadPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadPadre(String value) {
        this.nacionalidadPadre = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombreMadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreMadre() {
        return nombreMadre;
    }

    /**
     * Sets the value of the nombreMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreMadre(String value) {
        this.nombreMadre = value;
    }

    /**
     * Gets the value of the nombrePadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePadre() {
        return nombrePadre;
    }

    /**
     * Sets the value of the nombrePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePadre(String value) {
        this.nombrePadre = value;
    }

    /**
     * Gets the value of the nombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets the value of the nombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombres(String value) {
        this.nombres = value;
    }

    /**
     * Gets the value of the nuiConyuge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuiConyuge() {
        return nuiConyuge;
    }

    /**
     * Sets the value of the nuiConyuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuiConyuge(String value) {
        this.nuiConyuge = value;
    }

    /**
     * Gets the value of the nuiMadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuiMadre() {
        return nuiMadre;
    }

    /**
     * Sets the value of the nuiMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuiMadre(String value) {
        this.nuiMadre = value;
    }

    /**
     * Gets the value of the nuiPadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuiPadre() {
        return nuiPadre;
    }

    /**
     * Sets the value of the nuiPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuiPadre(String value) {
        this.nuiPadre = value;
    }

    /**
     * Gets the value of the numeroCasa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCasa() {
        return numeroCasa;
    }

    /**
     * Sets the value of the numeroCasa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCasa(String value) {
        this.numeroCasa = value;
    }

    /**
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the observacionInstitucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacionInstitucion() {
        return observacionInstitucion;
    }

    /**
     * Sets the value of the observacionInstitucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacionInstitucion(String value) {
        this.observacionInstitucion = value;
    }

    /**
     * Gets the value of the pagina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * Sets the value of the pagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagina(String value) {
        this.pagina = value;
    }

    /**
     * Gets the value of the paginaDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaginaDefuncion() {
        return paginaDefuncion;
    }

    /**
     * Sets the value of the paginaDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaginaDefuncion(String value) {
        this.paginaDefuncion = value;
    }

    /**
     * Gets the value of the paginaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaginaMatrimonio() {
        return paginaMatrimonio;
    }

    /**
     * Sets the value of the paginaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaginaMatrimonio(String value) {
        this.paginaMatrimonio = value;
    }

    /**
     * Gets the value of the paisDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisDomicilio() {
        return paisDomicilio;
    }

    /**
     * Sets the value of the paisDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisDomicilio(String value) {
        this.paisDomicilio = value;
    }

    /**
     * Gets the value of the paisFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisFallecimiento() {
        return paisFallecimiento;
    }

    /**
     * Sets the value of the paisFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisFallecimiento(String value) {
        this.paisFallecimiento = value;
    }

    /**
     * Gets the value of the paisInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisInscripcion() {
        return paisInscripcion;
    }

    /**
     * Sets the value of the paisInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisInscripcion(String value) {
        this.paisInscripcion = value;
    }

    /**
     * Gets the value of the paisInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisInscripcionDefuncion() {
        return paisInscripcionDefuncion;
    }

    /**
     * Sets the value of the paisInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisInscripcionDefuncion(String value) {
        this.paisInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the paisInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisInscripcionGenero() {
        return paisInscripcionGenero;
    }

    /**
     * Sets the value of the paisInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisInscripcionGenero(String value) {
        this.paisInscripcionGenero = value;
    }

    /**
     * Gets the value of the paisMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisMatrimonio() {
        return paisMatrimonio;
    }

    /**
     * Sets the value of the paisMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisMatrimonio(String value) {
        this.paisMatrimonio = value;
    }

    /**
     * Gets the value of the paisNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Sets the value of the paisNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisNacimiento(String value) {
        this.paisNacimiento = value;
    }

    /**
     * Gets the value of the parroquiaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaDomicilio() {
        return parroquiaDomicilio;
    }

    /**
     * Sets the value of the parroquiaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaDomicilio(String value) {
        this.parroquiaDomicilio = value;
    }

    /**
     * Gets the value of the parroquiaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaFallecimiento() {
        return parroquiaFallecimiento;
    }

    /**
     * Sets the value of the parroquiaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaFallecimiento(String value) {
        this.parroquiaFallecimiento = value;
    }

    /**
     * Gets the value of the parroquiaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaInscripcion() {
        return parroquiaInscripcion;
    }

    /**
     * Sets the value of the parroquiaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaInscripcion(String value) {
        this.parroquiaInscripcion = value;
    }

    /**
     * Gets the value of the parroquiaInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaInscripcionDefuncion() {
        return parroquiaInscripcionDefuncion;
    }

    /**
     * Sets the value of the parroquiaInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaInscripcionDefuncion(String value) {
        this.parroquiaInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the parroquiaInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaInscripcionGenero() {
        return parroquiaInscripcionGenero;
    }

    /**
     * Sets the value of the parroquiaInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaInscripcionGenero(String value) {
        this.parroquiaInscripcionGenero = value;
    }

    /**
     * Gets the value of the parroquiaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaMatrimonio() {
        return parroquiaMatrimonio;
    }

    /**
     * Sets the value of the parroquiaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaMatrimonio(String value) {
        this.parroquiaMatrimonio = value;
    }

    /**
     * Gets the value of the parroquiaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParroquiaNacimiento() {
        return parroquiaNacimiento;
    }

    /**
     * Sets the value of the parroquiaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParroquiaNacimiento(String value) {
        this.parroquiaNacimiento = value;
    }

    /**
     * Gets the value of the profesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * Sets the value of the profesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfesion(String value) {
        this.profesion = value;
    }

    /**
     * Gets the value of the provinciaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaDomicilio() {
        return provinciaDomicilio;
    }

    /**
     * Sets the value of the provinciaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaDomicilio(String value) {
        this.provinciaDomicilio = value;
    }

    /**
     * Gets the value of the provinciaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaFallecimiento() {
        return provinciaFallecimiento;
    }

    /**
     * Sets the value of the provinciaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaFallecimiento(String value) {
        this.provinciaFallecimiento = value;
    }

    /**
     * Gets the value of the provinciaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaInscripcion() {
        return provinciaInscripcion;
    }

    /**
     * Sets the value of the provinciaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaInscripcion(String value) {
        this.provinciaInscripcion = value;
    }

    /**
     * Gets the value of the provinciaInscripcionDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaInscripcionDefuncion() {
        return provinciaInscripcionDefuncion;
    }

    /**
     * Sets the value of the provinciaInscripcionDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaInscripcionDefuncion(String value) {
        this.provinciaInscripcionDefuncion = value;
    }

    /**
     * Gets the value of the provinciaInscripcionGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaInscripcionGenero() {
        return provinciaInscripcionGenero;
    }

    /**
     * Sets the value of the provinciaInscripcionGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaInscripcionGenero(String value) {
        this.provinciaInscripcionGenero = value;
    }

    /**
     * Gets the value of the provinciaMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaMatrimonio() {
        return provinciaMatrimonio;
    }

    /**
     * Sets the value of the provinciaMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaMatrimonio(String value) {
        this.provinciaMatrimonio = value;
    }

    /**
     * Gets the value of the provinciaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaNacimiento() {
        return provinciaNacimiento;
    }

    /**
     * Sets the value of the provinciaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaNacimiento(String value) {
        this.provinciaNacimiento = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the tipoCedula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCedula() {
        return tipoCedula;
    }

    /**
     * Sets the value of the tipoCedula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCedula(String value) {
        this.tipoCedula = value;
    }

    /**
     * Gets the value of the tomo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTomo() {
        return tomo;
    }

    /**
     * Sets the value of the tomo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTomo(String value) {
        this.tomo = value;
    }

    /**
     * Gets the value of the tomoDefuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTomoDefuncion() {
        return tomoDefuncion;
    }

    /**
     * Sets the value of the tomoDefuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTomoDefuncion(String value) {
        this.tomoDefuncion = value;
    }

    /**
     * Gets the value of the tomoMatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTomoMatrimonio() {
        return tomoMatrimonio;
    }

    /**
     * Sets the value of the tomoMatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTomoMatrimonio(String value) {
        this.tomoMatrimonio = value;
    }

}
