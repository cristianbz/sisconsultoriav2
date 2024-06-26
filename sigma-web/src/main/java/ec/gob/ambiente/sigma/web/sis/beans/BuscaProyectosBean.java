/**
@autor proamazonia [Christian Báez]  26 may. 2021

**/
package ec.gob.ambiente.sigma.web.sis.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.sis.dto.DtoComponenteSalvaguarda;
import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionSafeguards;
import ec.gob.ambiente.sigma.ejb.sis.entidades.CatalogTypes;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Catalogs;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Indicators;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectGenderIndicator;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectQuestions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectsGenderInfo;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Sectors;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class BuscaProyectosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private boolean esRegistroSalvaguardas;
	
	@Getter
	@Setter //1 implementador  2 estrategico
	private int tipoSocio;
	

	
	@Getter
	@Setter
	private boolean esRegistroGenero;
	
	@Setter
	@Getter
	private boolean datosProyecto;
	
	@Getter
	@Setter
	private Integer codigoLineaGenero;
	
	@Getter
	@Setter
	private Integer codigoProyecto;
	
	@Getter
	@Setter
	private Integer codigoStrategicPartner;
	
	@Getter
	@Setter
	private Integer posicionTab;
	
	@Getter
	@Setter
	private Integer posicionTabGenero;
	
	@Setter
	@Getter
	private Integer codigoBusquedaProyecto;
	
	@Setter
	@Getter
	private int codigoSocioImplementador;
	
	@Getter
	@Setter
	private List<Partner> listaSociosImplementadores;
	
	@Setter
	@Getter
	private String tituloProyecto;
	
	@Setter
	@Getter
	private String nombreSocioEstrategico;
	
	@Getter
	@Setter
	private List<Project> listaProyectos;
	
	@Getter
	@Setter
	private List<AdvanceExecutionSafeguards> listaProyectosReportados;
	
	@Getter
	@Setter
	private List<Project> listaProyectosSociosReportados;
	
	@Getter
	@Setter
	private List<ProjectsStrategicPartner> listaPartnersProyectos;
	
	@Getter
	@Setter
	private List<Integer> listaSectoresSeleccionados;
	
	@Getter
	@Setter
	private List<Catalogs> listaPreguntasSalvaguardas;
	
	@Getter
	@Setter
	private List<Catalogs> listaLineasAccion;
	
	@Getter
	@Setter
	private List<ProjectQuestions> listaPreguntasAsignadas;
	
	@Getter
	@Setter
	private List<ProjectQuestions> listaSalvaguardasAsignadas;
	
//	@Getter
//	@Setter
//	private List<Integer> preguntasSelecionadas;
	
	@Getter
	@Setter
	private List<Catalogs> preguntasSelecionadas;
	
	@Getter
	@Setter
	private List<Sectors> listaSectoresDisponibles;

	
	@Setter
	@Getter
	private Project proyectoSeleccionado;
	
	@Getter
	@Setter
	private Partner socioImplementador;
	
	@Getter
	@Setter
	private AdvanceExecutionSafeguards advanceExecution;
	
	@Getter
	@Setter
	private Integer anioReporte;
	
	@Getter
	@Setter
	private String periodoDesde;
	
	
	@Getter
	@Setter
	private boolean asignacionSalvaguardas;
	
	@Getter
	@Setter
	private boolean menuAdministrador;
	
	
	@Getter
	@Setter
	private boolean asignacionGenero;
	
	@Getter
	@Setter
	private boolean salvaguardasSociosEstrategicos;
	
	@Getter
	@Setter
	private boolean lineasGeneroSociosEstrategicos;
	
	@Getter
	@Setter
	private boolean sinDatosProyectoPreguntas;
	
	@Getter
	@Setter
	private int tipoRol;
	
//	@Getter
//	@Setter
//	private int codigoRolUsuario;
	
	@Getter
	@Setter
	private RolesUser rolUsuarioSeleccionado;
	
	@Getter
	@Setter
	private List<CatalogTypes> listaLineasGenero;
	
	@Getter
	@Setter
	private boolean proyectoTienePlanGenero;

	@Getter
	@Setter
	private List<ProjectsGenderInfo> listaTemaGenero1;
	
	@Getter
	@Setter
	private List<ProjectsGenderInfo> listaTemaGenero2;
	
	@Getter
	@Setter
	private List<ProjectsGenderInfo> listaTemaGenero3;
	
	@Getter
	@Setter
	private List<ProjectsGenderInfo> listaTemaGeneroOtros;
	
	@Getter
	@Setter
	private List<Indicators> listaIndicadores;
		
//	@Getter
//	@Setter
//	private List<ProjectsSpecificObjectives> listaObjetivosEspecificos;
	
//	@Getter
//	@Setter
//	private ProjectsGenderInfo lineaGeneroSeleccionada;
	
	@Getter
	@Setter
	private boolean nuevoSeguimiento;
	
	@Getter
	@Setter
	private boolean proyectoReportado;
	
	@Getter
	@Setter
	private List<ProjectQuestions> listaPreguntasAsignadasAux;
	
	@Getter
	@Setter
	private List<ProjectsSpecificObjective> listaComponentes;
	
	@Getter
	@Setter
	private List<DtoComponenteSalvaguarda> listaComponentesSalvaguardas;
	
//	@Getter
//	@Setter
//	private int codigoComponente;
	
	@Getter
	@Setter
	private List<Safeguard> listadoSalvaguardasAsignadasProyecto;
	
	@Getter
	@Setter
	private boolean mostrarOpcionesBusqueda;
	
	@Getter
	@Setter
	private boolean mostrarOpcionesBusquedaGenero;
	
//	@Getter
//	@Setter
//	private List<ProjectsSpecificObjectives> listaComponentesParaBusqueda;
	
	@Getter
	@Setter
	private String estadoReporte;
		
	@Getter
	@Setter
	private boolean mostrarTabs;
	
	@Getter
	@Setter
	private boolean nuevaLineaAccion;
	
	@Getter
	@Setter
	private CatalogTypes lineaGeneroSel;
	
	@Getter
	@Setter
	private Catalogs lineaAccionSel;
	
	@Getter
	@Setter
	private List<Component> listadoComponentes;
	
	
	@Getter
	@Setter
	private List<String> listadoComponentesSeleccionados;
	
	@Getter
	@Setter
	private List<ProjectGenderIndicator> listadoProyectoGeneroIndicador;
	
	@Getter
	@Setter
	private ProjectsGenderInfo projectGenderInfoSeleccionado;
	
	@Getter
	@Setter
	private ProjectsGenderInfo projectGenderInfoSinGenero;
	
	@Getter
	@Setter
	private List<ProjectsGenderInfo> listaAccionesdeGeneroProyecto;
	
	@Getter
	@Setter
	private ProjectGenderIndicator indicadorSeleccionado;
	
	@Getter
	@Setter
	private List<Catalogs> listaPeriodosReportar;
	@Getter
	@Setter
	private String mesDesde;
	
	@Getter
	@Setter
	private String mesHasta;
	
	@Getter
	@Setter
	private boolean deshabilitaIniciarReporte;
	
	@Getter
	@Setter
	private List<ProjectsStrategicPartner> listadoPartnersAsignados;
	
	@Getter
	@Setter
	private List<RolesUser> listaRolesDelUsuario;
	
	@Getter
	@Setter
	private Integer[] periodoSeleccionados;
	
	@Getter
	@Setter
	private boolean desactivaSelecionPreguntas;
	
	@Getter
	@Setter
	private List<ProjectQuestions> listaPreguntasAsignadasAnio;
		
	@PostConstruct
	public void init(){
//		lineaGeneroSel=new CatalogsType();
//		lineaAccionSel = new Catalogs();
	}
}

