/**
@autor proamazonia [Christian Báez]  4 ago. 2021

**/
package ec.gob.ambiente.sigma.web.sis.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionProjectGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionSafeguards;
import ec.gob.ambiente.sigma.ejb.sis.entidades.DetailAdvanceGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectsGenderInfo;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.TableResponses;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ValueAnswers;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class SeguimientoGeneroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private boolean preguntasGenero;
	
	@Getter
	@Setter
	private boolean datosSeguimiento;
	
	@Getter
	@Setter
	private boolean nuevoDetalleGenero;
	
	@Getter
	@Setter
	private AdvanceExecutionSafeguards advanceExecutionSafeguards;
	
	@Getter
	@Setter
	private Project proyectoSeleccionado;
	
	@Getter
	@Setter
	private Integer codigoStrategicPartner;
	
	@Getter
	@Setter
	private String codigoTablaDatos;
	

	@Getter
	@Setter
	private List<AdvanceExecutionSafeguards> listaPresentadosIniciados;
	
//	@Getter
//	@Setter
//	private List<GenderAdvances> listaAvancesGenero;
//		
//	@Getter
//	@Setter
//	private List<GenderAdvances> listaAvancesGeneroOtrosTemas;
	
	@Getter
	@Setter
	private List<AdvanceExecutionProjectGender> listaLineasGenero;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntas;
	
	@Getter
	@Setter
	private List<ValueAnswers> listaValoresRespuestas;
	
//	@Getter
//	@Setter
//	private GenderAdvances avanceGeneroSeleccionado;
	
	@Getter
	@Setter
	private boolean mostrarTablaSeguimiento1;
	
//	@Getter
//	@Setter
//	private List<AdvanceExecutionProjectGender> tablaAvanceGenero;
	
	@Getter
	@Setter
	private DetailAdvanceGender detalleAdvanceGender;
	
	@Getter
	@Setter
	private Integer codProvincia;
	
	@Getter
	@Setter
	private Integer codCanton;
	
	@Getter
	@Setter
	private Integer codParroquia;
	
	@Getter
	@Setter
	private Integer codigoAutoIdentificacion;
	
	@Getter
	@Setter
	private int codigoPuebloNacionalidad;
	
	@Getter
	@Setter
	private List<GeographicalLocations> listaProvincias;
	
	@Getter
	@Setter
	private List<GeographicalLocations> listaCantones;
	
	@Getter
	@Setter
	private List<GeographicalLocations> listaParroquias;
	
	@Getter
	@Setter
	private boolean habilitaPuebloNacionalidad;
	
	@Getter
	@Setter
	private DetailAdvanceGender registroTablaGenero;
	
	@Getter
	@Setter
	private String[] tipoOrganizacionSeleccionados;
	
	@Getter
	@Setter
	private List<String> listadoTipoOrganizaciones;
	
	@Getter
	@Setter
	private String[] tipoIncentivoSeleccionado;
	
	@Getter
	@Setter
	private List<String> listadoTipoIncentivo;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas1;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas2;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas3;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas4;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas5;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas6;
	
	@Getter
	@Setter
	private List<TableResponses> tablaRespuestas7;
	
	@Getter
	@Setter
	private TableResponses filaTabla3;
	
	@Getter
	@Setter
	private TableResponses filaTabla4;
	
	@Getter
	@Setter
	private TableResponses filaTabla5;
	
	@Getter
	@Setter
	private TableResponses filaTabla6;
	
	@Getter
	@Setter
	private TableResponses filaTabla7;
	
	@Getter
	@Setter
	private boolean nuevoRegistroTablaGenero3;
	
	@Getter
	@Setter
	private boolean nuevoRegistroTablaGenero4;
	
	@Getter
	@Setter
	private boolean nuevoRegistroTablaGenero5;
	
	@Getter
	@Setter
	private boolean nuevoRegistroTablaGenero6;
	
	@Getter
	@Setter
	private boolean nuevoRegistroTablaGenero7;
	
	@Getter
	@Setter
	private Integer posicionTab;
	
	@Getter
	@Setter
	private boolean datosGeneroParaMostrar;
	
	@Getter
	@Setter
	private ProjectsGenderInfo informacionProyectoGeneroSeleccionado;

	@Getter
	@Setter
	private Partner socioImplementador;
	
	@Getter
	@Setter
	private List<DetailAdvanceGender> listaDetalleAvancesGenero;
	
	@Getter
	@Setter
	private List<Component> listaComponentes;
	
	@Getter
	@Setter
	private List<DetailAdvanceGender> listaDetalleAvancesGeneroOtros;
	
	@Getter
	@Setter
	private AdvanceExecutionProjectGender advanceExecutionProjectGenderSeleccionado; 
	
	@PostConstruct
	public void init(){
//		setAvanceGeneroSeleccionado(new GenderAdvances());
	}
}

