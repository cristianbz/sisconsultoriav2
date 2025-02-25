/**
@autor proamazonia [Christian Báez]  26 may. 2021

 **/
package ec.gob.ambiente.sigma.web.sis.controladores;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.application.resource.barcode.Int2of5Generator;
import org.primefaces.component.selectmanymenu.SelectManyMenu;

import ec.gob.ambiente.sigma.ejb.sis.dto.DtoRespuestasSalvaguardas;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoResumenGenero;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoResumenSalvaguarda;
import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionProjectGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionSafeguards;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceSectors;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceSummary;
import ec.gob.ambiente.sigma.ejb.sis.entidades.CatalogTypes;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Catalogs;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Indicators;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectGenderIndicator;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectQuestions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectsGenderInfo;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Sectors;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ValueAnswers;
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsSpecificObjectiveFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsStrategicPartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.SafeguardFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceExecutionProjectGenderFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceExecutionSafeguardsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceSummaryFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.CatalogsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.CatalogsTypeFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.IndicatorsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectGenderIndicatorFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectQuestionsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectsGenderInfoFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.QuestionsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.SectorsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.TableResponsesFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ValueAnswersFacade;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
import ec.gob.ambiente.sigma.web.sis.beans.AplicacionBean;
import ec.gob.ambiente.sigma.web.sis.beans.BuscaProyectosBean;
import ec.gob.ambiente.sigma.web.controladores.SesionControlador;
import ec.gob.ambiente.sigma.web.security.LoginBean;
import ec.gob.ambiente.sigma.web.sis.utils.GeneradorPdfHtml;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarPdfResumen;
import ec.gob.ambiente.sigma.web.sis.utils.Mensaje;
import ec.gob.ambiente.sigma.web.sis.utils.UtilsCadenas;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoCatalogoEnum;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoRolesUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Dependent
public class ComponenteBuscaProyectos implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ComponenteBuscaProyectos.class);


	@Inject
	@Getter
	private GeneradorPdfHtml generadorPdfHtml;
	
	@Inject
	@Getter
	private BuscaProyectosBean buscaProyectosBean;

	@Getter    
	@Inject
	private MensajesController mensajesController;

	@EJB
	@Getter
	private PartnerFacade partnersFacade;
	
	@EJB
	@Getter
	private AdvanceSummaryFacade advanceSummaryFacade;
	
	
	@Getter
	@Setter
	@Inject
	private AplicacionController aplicacionController;
	
	
	@Getter
	@Setter
	@Inject
	private AplicacionBean aplicacionBean;

	@Getter
	@Setter
	@Inject
	private LoginBean loginBean;

	@EJB
	@Getter
	private AdvanceExecutionSafeguardsFacade advanceExecutionSafeguardsFacade;
	
	@EJB
	@Getter
	private AdvanceExecutionProjectGenderFacade advanceExecutionProjectGenderFacade;

	@EJB
	@Getter
	private ProjectFacade projectsFacade;


    @EJB
   	@Getter
   	private ComponentFacade componentsFacade;

	@EJB
	@Getter
	private CatalogsFacade catalogsFacade;
	
	@EJB
	@Getter
	private CatalogsTypeFacade catalogsTypeFacade;

	@EJB
	@Getter
	private SafeguardFacade safeguardsFacade;

	@EJB
	@Getter
	private ProjectQuestionsFacade projectQuestionsFacade;

	@EJB
	@Getter
	private SectorsFacade sectorsFacade;

	@EJB
	@Getter
	private ProjectsStrategicPartnerFacade projectsStrategicPartnersFacade;
	
	@EJB
	@Getter
	private ProjectsGenderInfoFacade projectsGenderInfoFacade;
	
	@EJB
	@Getter
	private ProjectGenderIndicatorFacade projectGenderIndicatorFacade;

	
	@EJB
	@Getter
	private ProjectsSpecificObjectiveFacade projectsSpecificObjectivesFacade;
	
	@EJB
	@Getter
	private IndicatorsFacade indicatorsFacade;
	

	@Getter
	@Setter
	private boolean esReporteGenero;

	@Getter
	@Setter
	private boolean esReporteSalvaguardas;
	
	@Setter
	@Getter
	private boolean esSeguimientoSalvaguardas;
	
	@Getter
	@Setter
	private Integer codigoPartner;
	
	@EJB
	@Getter
	private QuestionsFacade questionsFacade;
	
	@EJB
	@Getter
	private TableResponsesFacade tableResponsesFacade;
	@EJB
	@Getter
	private ValueAnswersFacade valueAnswersFacade;
	

	private boolean esProyecto;
	private boolean nuevoIngreso;

	private List<Safeguard> listadoSalvaguardas;
	private List<Catalogs> listaTempPreguntasAsignadas;
	
	@Inject
	private SesionControlador sesBean;
	
	@Setter
	@Getter
	private String comentarioActivarReporte;
	
	@Setter
	@Getter
	private String correoSocios;

	@PostConstruct
	public void init(){
		try{
			getBuscaProyectosBean().setTipoSocio(1);
			cargaSociosImplementadores();
			getBuscaProyectosBean().setCodigoBusquedaProyecto(null);
			esProyecto=true;
			nuevoIngreso=true;
			getBuscaProyectosBean().setSinDatosProyectoPreguntas(false);
			indicaTipoRol();
			getBuscaProyectosBean().setListaLineasGenero(getCatalogsTypeFacade().listaLineasGenero());
			getBuscaProyectosBean().setLineaGeneroSel(new CatalogTypes());
			getBuscaProyectosBean().setLineaAccionSel(new Catalogs());
			getBuscaProyectosBean().setNuevaLineaAccion(false);
			getBuscaProyectosBean().setListaPeriodosReportar(new ArrayList<Catalogs>());
			getBuscaProyectosBean().setListaPeriodosReportar(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.PERIODOREPORTE.getCodigo()));
			getBuscaProyectosBean().setDeshabilitaIniciarReporte(false);
			getBuscaProyectosBean().setListaRolesDelUsuario(new ArrayList<RolesUser>());
			if(getLoginBean().getListaRolesUsuario().size()>1){
				for (RolesUser ru : getLoginBean().getListaRolesUsuario()) {
					if(ru.getRole().getRoleName().substring(0,3).equals("SIS"))
						getBuscaProyectosBean().getListaRolesDelUsuario().add(ru);
				}
			}
			
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "init " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Carga los socios implementadores
	 */
	public void cargaSociosImplementadores(){
		try{
			getBuscaProyectosBean().setListaSociosImplementadores(new ArrayList<Partner>());
			getBuscaProyectosBean().setListaSociosImplementadores(getPartnersFacade().listarSociosImplementadores());
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaSociosImplementadores " + ": ").append(e.getMessage()));
		}
	}

	/**
	 * Carga los sectores de interes
	 */
	public void cargaSectoresInteres(){
		try{
			getBuscaProyectosBean().setListaSectoresDisponibles(new ArrayList<Sectors>());
			getBuscaProyectosBean().setListaSectoresDisponibles(getSectorsFacade().buscarTodosLosSectores());
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaSectoresInteres " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Carga los sectores de interes del proyecto seleccionado
	 */
	public void sectoresInteresProyecto(){

		getBuscaProyectosBean().setListaSectoresSeleccionados(new ArrayList<Integer>());
		if(getBuscaProyectosBean().getAdvanceExecution()!=null && getBuscaProyectosBean().getAdvanceExecution().getAdvanceSectorsList()!=null){
			for(AdvanceSectors as:getBuscaProyectosBean().getAdvanceExecution().getAdvanceSectorsList())
				if(as.isAdseSelectedSector())
					getBuscaProyectosBean().getListaSectoresSeleccionados().add(as.getSectors().getSectId());					
		}
	}

	public void vaciaDatosBusqueda(){
		getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
		getBuscaProyectosBean().setCodigoProyecto(0);
		getBuscaProyectosBean().setCodigoSocioImplementador(0);

		getBuscaProyectosBean().setProyectoSeleccionado(new Project());
		getBuscaProyectosBean().setSocioImplementador(new Partner());
		getBuscaProyectosBean().setTituloProyecto("");
		getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
		getBuscaProyectosBean().setMostrarOpcionesBusqueda(false);
		getBuscaProyectosBean().setMostrarOpcionesBusquedaGenero(false);
	}
	/**
	 * Busca proyectos
	 */
	public void buscarProyectos(){
		try{
			getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
			getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
			if(getBuscaProyectosBean().getTipoRol()==4 || getBuscaProyectosBean().getTipoRol()==1){
				getBuscaProyectosBean().setListaProyectos(getProjectsFacade().buscarTodosLosProyectos());
			}else if(getBuscaProyectosBean().getTipoRol()==2){
				List<Project> listaProyectousuarios=new ArrayList<>();
				getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
				
				if(getLoginBean().getListaProyectosDelSocioImplementador().size()>0){	
					listaProyectousuarios=getLoginBean().getListaProyectosDelSocioImplementador();
					for (Project pu : listaProyectousuarios) {
						getBuscaProyectosBean().getListaProyectos().add(pu);
					}
				}else{
					Mensaje.actualizarComponente(":form:growl");				
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.proyectosUsuario"));
				}
			}else if(getBuscaProyectosBean().getTipoRol()==3){
				List<ProjectsStrategicPartner> listaProyectousuarios=new ArrayList<>();
				getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());					
				if(getLoginBean().getListaProyectosDelSocioEstrategico().size()>0){	
					listaProyectousuarios=getLoginBean().getListaProyectosDelSocioEstrategico();
					for (ProjectsStrategicPartner pu : listaProyectousuarios) {
						getBuscaProyectosBean().getListaProyectos().add(pu.getProjId());
					}
				}else{
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.proyectosUsuario"));
					Mensaje.actualizarComponente(":form:growl");				
					
				}
			}

		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "buscarProyectos " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Busca los proyectos asociados al tecnico o el administrador
	 */
	public void buscaProyectosPerfilTecnicoAdministrador(){
		try{
			getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());

			if(getBuscaProyectosBean().getTipoRol()==4 || getBuscaProyectosBean().getTipoRol()==1){

				if(getBuscaProyectosBean().getCodigoBusquedaProyecto()!=null ){
					if(getBuscaProyectosBean().getCodigoBusquedaProyecto()==1 ){			
						getBuscaProyectosBean().setListaProyectos(getProjectsFacade().listarProyectosPorIdSocioImpl(getBuscaProyectosBean().getCodigoSocioImplementador()));
						if(getBuscaProyectosBean().getListaProyectos().size()==0){
							Mensaje.actualizarComponente(":form:growl");				
							Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.noProyectos"));
						}

					}else if(getBuscaProyectosBean().getCodigoBusquedaProyecto()==2 ){
						if (getBuscaProyectosBean().getTituloProyecto().length()>4){
							getBuscaProyectosBean().setListaProyectos(getProjectsFacade().listarProyectosPorTextoTitulo(getBuscaProyectosBean().getTituloProyecto()));
							if(getBuscaProyectosBean().getListaProyectos().size()==0){
								Mensaje.actualizarComponente(":form:growl");				
								Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.noProyectos"));
							}
						}else{
							Mensaje.actualizarComponente(":form:growl");				
							Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.longitudMinima"));
						}


					}

				}else{					
					Mensaje.actualizarComponente(":form:growl");				
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionBusqueda"));
				}
			}else if(getLoginBean().getListaRolesUsuario().size()>1){
				Mensaje.verDialogo("dlgTipoRol");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Valida si el usuario tiene mas roles
	 */
	public void validarRol(){
		getBuscaProyectosBean().setMostrarOpcionesBusqueda(false);
		getBuscaProyectosBean().setMostrarOpcionesBusquedaGenero(false);
		getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
		if(getLoginBean().getListaRolesUsuario().size()>1 && !getBuscaProyectosBean().isMenuAdministrador()){
			Mensaje.verDialogo("dlgTipoRol");
		}else{
			buscarProyectos();
		}
	}
	/**
	 * Selecciona el proyecto del rol usuario
	 */
	public void seleccionaProyectosRolUsuario(){
		try{
			
			for (RolesUser ru : getLoginBean().getListaRolesUsuario()) {
				if(ru.getRousId() == getBuscaProyectosBean().getRolUsuarioSeleccionado().getRousId()){
					if(ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_tecnico.getEtiqueta()) ){
						
						getLoginBean().setTipoRol(4);
						getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
						break;
					}else if(ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_socio_estrategico.getEtiqueta()) ){

						getLoginBean().setListaProyectosDelSocioEstrategico(getProjectsStrategicPartnersFacade().listaProyectosSocioEstrategico(ru.getRousDescription()));					
						getLoginBean().setTipoRol(3);
						getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
						seleccionaProyectosdelUsuario(getLoginBean().getTipoRol());
						break;
					}else if(ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_socio_implementador.getEtiqueta())){
						getLoginBean().setListaProyectosDelSocioImplementador(getProjectsFacade().listarProyectosSocioImplementador(UtilsCadenas.ultimosCaracteres(ru.getRousDescription(),13)));
						getLoginBean().setTipoRol(2);
						getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
						seleccionaProyectosdelUsuario(getLoginBean().getTipoRol());
						break;
					}else{
						getLoginBean().setTipoRol(1);
						getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
						break;
					}
				}
			}
			Mensaje.ocultarDialogo("dlgTipoRol");
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "ubicaRolUsuarioSeleccionado " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Permite cargar los proyectos del usuario
	 * @param codigoRol Codigo del rol
	 */
	public void seleccionaProyectosdelUsuario(int codigoRol){
		
		if(codigoRol == 2){
			List<Project> listaProyectousuarios=new ArrayList<>();
			getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
			if(getLoginBean().getListaProyectosDelSocioImplementador().size()>0){	
				listaProyectousuarios=getLoginBean().getListaProyectosDelSocioImplementador();
				for (Project pu : listaProyectousuarios) {
					getBuscaProyectosBean().getListaProyectos().add(pu);
				}
			}else{
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.proyectosUsuario"));
			}
		}else if(codigoRol == 3){
			List<ProjectsStrategicPartner> listaProyectousuarios=new ArrayList<>();
			getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
			if(getLoginBean().getListaProyectosDelSocioEstrategico().size()>0){	
				listaProyectousuarios=getLoginBean().getListaProyectosDelSocioEstrategico();
				for (ProjectsStrategicPartner pu : listaProyectousuarios) {
					getBuscaProyectosBean().getListaProyectos().add(pu.getProjId());
				}
			}else{
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.proyectosUsuario"));
			}
		}
	}

	public void obtienePeriodoReporte(AdvanceExecutionSafeguards avanceEjecucion){		
		String desde="";
		String hasta="";
		HashMap<String, String> periodo = new HashMap<String, String>();
		periodo.put("01", "Enero");
		periodo.put("02", "Febrero");
		periodo.put("03", "Marzo");
		periodo.put("04", "Abril");
		periodo.put("05", "Mayo");
		periodo.put("06", "Junio");
		periodo.put("07", "Julio");
		periodo.put("08", "Agosto");
		periodo.put("09", "Septiembre");
		periodo.put("10", "Octubre");
		periodo.put("11", "Noviembre");
		periodo.put("12", "Diciembre");
		String[] parts1 = avanceEjecucion.getAdexTermFrom().split("-");
		String[] parts2 = avanceEjecucion.getAdexTermTo().split("-");
		for (Map.Entry<String, String> entry : periodo.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    if(parts1[1].equals(key))
		    	desde = value;
		    else if(parts2[1].equals(key))
		    	hasta = value;
		}			
		getBuscaProyectosBean().setPeriodoDesde(desde.concat("-").concat(hasta));
	}
	/**
	 * Busca el avance de ejecucion del proyecto reportado
	 * @param proyecto
	 */
	public void buscaAvanceEjecucionDelProyectoReportado(AdvanceExecutionSafeguards avanceEjecucion){
		try{
			obtienePeriodoReporte(avanceEjecucion);
			if(avanceEjecucion.getProjects().getPartId()!= null)
				getBuscaProyectosBean().setSocioImplementador(getPartnersFacade().buscarPartnerPorCodigo(avanceEjecucion.getProjects().getPartId().getPartId()) );
			if(avanceEjecucion.isAdexIsReported() || avanceEjecucion.getAdexReportedStatus().equals("F"))
				getBuscaProyectosBean().setProyectoReportado(true);
			else
				getBuscaProyectosBean().setProyectoReportado(false);
			if(esReporteGenero){				
				getBuscaProyectosBean().setAdvanceExecution(avanceEjecucion);
				getBuscaProyectosBean().setDatosProyecto(true);
				if(avanceEjecucion.getProjectsStrategicPartners()!=null){
					getBuscaProyectosBean().setCodigoStrategicPartner(avanceEjecucion.getProjectsStrategicPartners().getPspaId());
					getBuscaProyectosBean().setNombreSocioEstrategico(getProjectsStrategicPartnersFacade().partnerEstrategico(getBuscaProyectosBean().getCodigoStrategicPartner()).getPartId().getPartName());
				}else{
					getBuscaProyectosBean().setCodigoStrategicPartner(null);
					getBuscaProyectosBean().setNombreSocioEstrategico("");
				}
				if (getBuscaProyectosBean().getAdvanceExecution()!=null ){					
					getBuscaProyectosBean().setAnioReporte(Integer.valueOf(getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0, 4)));
//					getBuscaProyectosBean().setPeriodoDesde("01");
				}
			}else{				
				getBuscaProyectosBean().setAdvanceExecution(avanceEjecucion);

				if(avanceEjecucion.getProjectsStrategicPartners()!=null){
					getBuscaProyectosBean().setCodigoStrategicPartner(avanceEjecucion.getProjectsStrategicPartners().getPspaId());
					getBuscaProyectosBean().setNombreSocioEstrategico(getProjectsStrategicPartnersFacade().partnerEstrategico(getBuscaProyectosBean().getCodigoStrategicPartner()).getPartId().getPartName());
				}else{
					getBuscaProyectosBean().setNombreSocioEstrategico("");
				}
					cargaSectoresInteres();		
					getBuscaProyectosBean().setDatosProyecto(true);
					getBuscaProyectosBean().setAnioReporte(Integer.valueOf(getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0,4)));
//					getBuscaProyectosBean().setPeriodoDesde("01");
					sectoresInteresProyecto();
					
			}
			getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "buscaAvanceEjecucionDelProyectoReportado " + ": ").append(e.getMessage()));
		}
	}

		
	public void nuevoSeguimientoProyectoSocioEstrategico(Project proyecto){
		try{
			getBuscaProyectosBean().setListaSectoresSeleccionados(new ArrayList<Integer>());
			getBuscaProyectosBean().setPeriodoDesde(null);
			getBuscaProyectosBean().setAnioReporte(null);
			getBuscaProyectosBean().setProyectoSeleccionado(new Project());
			getBuscaProyectosBean().setProyectoSeleccionado(proyecto);

			getBuscaProyectosBean().setSocioImplementador(getPartnersFacade().buscarPartnerPorCodigo(proyecto.getPartId().getPartId()) );
			AdvanceExecutionSafeguards avanceEjecucion=new AdvanceExecutionSafeguards();
			avanceEjecucion.setAdexIsReported(false);
			avanceEjecucion.setAdexRegisterDate(new Date());
			avanceEjecucion.setAdexCreationDate(new Date());				
			avanceEjecucion.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());

			avanceEjecucion.setUsers(getLoginBean().getUser());
			avanceEjecucion.setAdexStatus(true);

			avanceEjecucion.setAdexCreatorUser(getLoginBean().getUser().getUserName());
			avanceEjecucion.setAdexTermFrom(null);
			avanceEjecucion.setAdexTermTo(null);
			avanceEjecucion.setAdexReportedStatus("I");
			
			ProjectsStrategicPartner psp=new ProjectsStrategicPartner();
			if (getLoginBean().getTipoRol()==3){
				for(ProjectsStrategicPartner prosp:getLoginBean().getListaProyectosDelSocioEstrategico()){
					if(proyecto.getProjId() == prosp.getProjId().getProjId()){
						getBuscaProyectosBean().setCodigoStrategicPartner(prosp.getPspaId());
						avanceEjecucion.setProjectsStrategicPartners(prosp);
						break;
					}
				}
				
			}
			
			if(!esReporteGenero){
				cargaSectoresInteres();
				avanceEjecucion.setAdexIsGender(false);
			}else{
				
				avanceEjecucion.setAdexIsGender(true);		

			}
			if(avanceEjecucion.getProjectsStrategicPartners()!=null)
				getBuscaProyectosBean().setNombreSocioEstrategico(getProjectsStrategicPartnersFacade().partnerEstrategico(avanceEjecucion.getProjectsStrategicPartners().getPspaId()).getPartId().getPartName());
			else
				getBuscaProyectosBean().setNombreSocioEstrategico("");
			getBuscaProyectosBean().setAdvanceExecution(avanceEjecucion);
			
			
			getBuscaProyectosBean().setDatosProyecto(true);
			getBuscaProyectosBean().setNuevoSeguimiento(true);
			getBuscaProyectosBean().setDeshabilitaIniciarReporte(false);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "nuevoSeguimientoProyectoSocioEstrategico " + ": ").append(e.getMessage()));
		}
		
	}

	public void informacionAvanceEjecucion(){
		cargaSectoresInteres();	
		codigoPartner = null;
		if(getBuscaProyectosBean().getCodigoStrategicPartner()==null && getBuscaProyectosBean().isSalvaguardasSociosEstrategicos()){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionSocio"));
		}else{

			if(getBuscaProyectosBean().getCodigoStrategicPartner()== null){					
				cargarAvanceEjecucionSalvaguarda(0,getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
			}else{			
				codigoPartner = getBuscaProyectosBean().getCodigoStrategicPartner();
				cargarAvanceEjecucionSalvaguarda(getBuscaProyectosBean().getCodigoStrategicPartner(),getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
				getBuscaProyectosBean().setCodigoStrategicPartner(codigoPartner);
			}
			getBuscaProyectosBean().setDatosProyecto(true);
			sectoresInteresProyecto();
			getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			Mensaje.ocultarDialogo("dlgSeleccionSocios");
		}
	}

	public void buscaAsignacionSalvaguardas(Project proyecto){
		try{
			getBuscaProyectosBean().setAsignacionSalvaguardas(true);

		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "buscaAsignacionSalvaguardas " + ": ").append(e.getMessage()));
		}
	}	

	public AdvanceExecutionSafeguards getAdvanceExecution(){
		return getBuscaProyectosBean().getAdvanceExecution();
	}
	public void volverABuscarProyectos(){
		getBuscaProyectosBean().setDatosProyecto(false);
		getBuscaProyectosBean().setPosicionTab(0);
		getBuscaProyectosBean().setPosicionTabGenero(0);
		getBuscaProyectosBean().setListaSalvaguardasAsignadas(null);
		getBuscaProyectosBean().setPreguntasSelecionadas(null);
		getBuscaProyectosBean().setAsignacionGenero(false);
	}
	public boolean datosProyecto(){
		return getBuscaProyectosBean().isDatosProyecto();
	}
	public Project proyectoSeleccionado(){
		return getBuscaProyectosBean().getProyectoSeleccionado();
	}
	/**
	 * Carga los partners del proyecto
	 */
	public void cargaPartnersProyecto(Project proyecto){
		try{
			getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
			getBuscaProyectosBean().setListaPartnersProyectos(getProjectsStrategicPartnersFacade().listaPartnersActivos(proyecto.getProjId()));			
			getBuscaProyectosBean().setDatosProyecto(true);
			getBuscaProyectosBean().setAsignacionSalvaguardas(true);
			cargaListadoSalvaguardas();
			getBuscaProyectosBean().setTipoSocio(1);
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			getBuscaProyectosBean().setNombreSocioEstrategico("");
			esProyecto=true;
			validaSalvaguardasAsignadasAParter();
			getBuscaProyectosBean().setAnioReporte(0);
			getBuscaProyectosBean().setDesactivaSelecionPreguntas(true);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaPartnersProyecto " + ": ").append(e.getMessage()));
		}
	}
	
	public void validaSalvaguardasAsignadasAParter(){
		try{
//			getBuscaProyectosBean().setListadoPartnersAsignados(new ArrayList<ProjectsStrategicPartner>());
//			int registros = 0;
//			registros = getProjectQuestionsFacade().numeroPreguntasProyectoSeleccionadas(getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
//			if(registros>0){
//				ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
//				psp.setProjId(getBuscaProyectosBean().getProyectoSeleccionado());
//				psp.setPartId(getBuscaProyectosBean().getProyectoSeleccionado().getPartId());
//				getBuscaProyectosBean().getListadoPartnersAsignados().add(psp);
//			}
//
//			for (ProjectsStrategicPartner psp : getBuscaProyectosBean().getListaPartnersProyectos()) {
//				registros = 0;
//				registros = getProjectQuestionsFacade().numeroPreguntasPartnerSeleccionadas(psp.getPspaId());
//				if(registros>0)
//					getBuscaProyectosBean().getListadoPartnersAsignados().add(psp);
//			}
			Map<Integer,ProjectQuestions> mapaTemp=new HashMap<Integer,ProjectQuestions>();
			
			getBuscaProyectosBean().setListaPreguntasAsignadasAnio(new ArrayList<ProjectQuestions>());
			List<ProjectQuestions> lista= new ArrayList<ProjectQuestions>();
			lista = getProjectQuestionsFacade().listaPreguntasAsignadas(null, getBuscaProyectosBean().getProyectoSeleccionado().getProjId());					
			for (ProjectQuestions pq : lista) {
				mapaTemp.put(pq.getPrquYear(), pq);				
			}
			for(Entry<Integer,ProjectQuestions> sa:mapaTemp.entrySet()){
				getBuscaProyectosBean().getListaPreguntasAsignadasAnio().add(sa.getValue());
			}
			for (ProjectQuestions pq : getBuscaProyectosBean().getListaPreguntasAsignadasAnio()) {
				pq.setPrquTipoSocio("Implementador");
				pq.setPrquNombreSocio(pq.getProjects().getPartId().getPartName());
			}
			
			List<ProjectQuestions> listaPreguntasSocios= new ArrayList<ProjectQuestions>();
			List<ProjectsStrategicPartner> listapartners = new ArrayList<ProjectsStrategicPartner>();
			listapartners = getProjectsStrategicPartnersFacade().listaPartnersActivos(getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
			mapaTemp=new HashMap<Integer,ProjectQuestions>();
			for (ProjectsStrategicPartner psp : listapartners) {			
				
				lista= new ArrayList<ProjectQuestions>();
				lista = getProjectQuestionsFacade().listaPreguntasAsignadas(psp.getPspaId(), null);					
				for (ProjectQuestions pq : lista) {
					StringBuilder claveMapa= new StringBuilder();
					claveMapa.append(pq.getProjectsStrategicPartners().getPspaId()).append(pq.getPrquYear());					
					mapaTemp.put(Integer.parseInt(claveMapa.toString()) , pq);					
				}
			}
			
			for(Entry<Integer,ProjectQuestions> sa:mapaTemp.entrySet()){
				listaPreguntasSocios.add(sa.getValue());
			}
			for (ProjectQuestions pq : listaPreguntasSocios) {
				if(pq.getProjectsStrategicPartners().getPspaType().equals("E"))
					pq.setPrquTipoSocio("Estratégico");
				else if(pq.getProjectsStrategicPartners().getPspaType().equals("I"))
					pq.setPrquTipoSocio("Implementador Secundario");
				else
					pq.setPrquTipoSocio("Estratégico Apoyo");
				pq.setPrquNombreSocio(pq.getProjectsStrategicPartners().getPartId().getPartName());
			}
			getBuscaProyectosBean().getListaPreguntasAsignadasAnio().addAll(listaPreguntasSocios);
			
			Collections.sort(getBuscaProyectosBean().getListaPreguntasAsignadasAnio(), new Comparator<ProjectQuestions>(){
				@Override
				public int compare(ProjectQuestions o1, ProjectQuestions o2) {
					return o1.getPrquYear()>o2.getPrquYear()?1:o1.getPrquYear()<o2.getPrquYear()?-1:0;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void capturaNombreSocioEstrategico(){
		for (ProjectsStrategicPartner psp : getBuscaProyectosBean().getListaPartnersProyectos()) {
			if(getBuscaProyectosBean().getCodigoStrategicPartner().equals(psp.getPspaId())){
				getBuscaProyectosBean().setNombreSocioEstrategico(psp.getPartId().getPartName());
				break;
			}
		}
	}
	
	public boolean asignacionSalvaguardas(){
		return getBuscaProyectosBean().isAsignacionSalvaguardas();
	}
	public void tabChange() {
		getBuscaProyectosBean().setPosicionTab(0);
	}
	public void tabChangeGenero() {
		getBuscaProyectosBean().setPosicionTabGenero(0);
	}
	/**
	 * Avanza al tab de seleccion de preguntas para salvaguardas
	 */
	public void siguienteTabPreguntas(){
		try{
		if((getBuscaProyectosBean().getTipoSocio()==2 && getBuscaProyectosBean().getCodigoStrategicPartner()!=null) ||
				getBuscaProyectosBean().getTipoSocio()==1){
			getBuscaProyectosBean().setPosicionTab(1);
//			getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Integer>());
			getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Catalogs>());
			getBuscaProyectosBean().setListaPreguntasSalvaguardas(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.PREGUNTASASIGNACIONSALVAGUARDAS.getCodigo()));
			getBuscaProyectosBean().setDesactivaSelecionPreguntas(true);
//			preguntasAsignadas();
		}else{
			Mensaje.actualizarComponente(":form:growl");				
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionSocio"));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void atrasTabSocios(){
		getBuscaProyectosBean().setPosicionTab(0);
		getBuscaProyectosBean().setAnioReporte(0);
	}
	public void atrasTabPreguntas(){
		getBuscaProyectosBean().setPosicionTab(1);
	}
	public void siguienteTabSalvaguardasSeleccionadas(){
//		getBuscaProyectosBean().getPreguntasSelecionadas().addAll(listaTempPreguntasAsignadas);
		////
		boolean encontrado=false;
		List<Catalogs> listaTemp= new ArrayList<Catalogs>();
		for(Catalogs ca: listaTempPreguntasAsignadas){
			for(Catalogs pa:getBuscaProyectosBean().getPreguntasSelecionadas()){
				if(ca.getCataId().equals(pa.getCataId())){
					encontrado=true;
					break;
				}
			}
			if ( encontrado == false){
				listaTemp.add(ca);								
			}
			encontrado=false;
		}
		getBuscaProyectosBean().getPreguntasSelecionadas().addAll(listaTemp);
		
		
		if(getBuscaProyectosBean().getPreguntasSelecionadas()!=null && getBuscaProyectosBean().getPreguntasSelecionadas().size()>0){
			getBuscaProyectosBean().setPosicionTab(2);
			asignarSalvaguardas();
			
		}else{
			Mensaje.actualizarComponente(":form:growl");				
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.selecionPreguntas"));
		}
//		System.out.println("Salvaguardas Seleccionadas " +getBuscaProyectosBean().getPreguntasSelecionadas().size());
	}
	/**
	 * Lista de preguntas asignadas para asignacion salvaguardas
	 */
	public void preguntasAsignadas(){
		try{
			listaTempPreguntasAsignadas=new ArrayList<Catalogs>();
			for (Catalogs ca : getBuscaProyectosBean().getListaPreguntasSalvaguardas()) {
				ca.setCataEstadoAux(false);
			}
			
			getBuscaProyectosBean().setDesactivaSelecionPreguntas(false);
			int desde=Integer.valueOf(getBuscaProyectosBean().getProyectoSeleccionado().getProjTermFrom().substring(0, 4));
			int hasta =Integer.valueOf(getBuscaProyectosBean().getProyectoSeleccionado().getProjTermTo().substring(0, 4));
//			getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Integer>());
			getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Catalogs>());
			if(getBuscaProyectosBean().getAnioReporte()>= desde && getBuscaProyectosBean().getAnioReporte()<=hasta){
				getBuscaProyectosBean().setListaPreguntasAsignadas(new ArrayList<ProjectQuestions>());
				if(getBuscaProyectosBean().getTipoSocio()==2)
					getBuscaProyectosBean().setListaPreguntasAsignadas(getProjectQuestionsFacade().listaPreguntasPartnerSeleccionadas(getBuscaProyectosBean().getCodigoStrategicPartner(),getBuscaProyectosBean().getAnioReporte()));
				else
					getBuscaProyectosBean().setListaPreguntasAsignadas(getProjectQuestionsFacade().listaPreguntasProyectoSeleccionadas(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(),getBuscaProyectosBean().getAnioReporte()));			

				getBuscaProyectosBean().setListaPreguntasAsignadasAux(getBuscaProyectosBean().getListaPreguntasAsignadas());
				if(getBuscaProyectosBean().getListaPreguntasAsignadas().size()==0){
//					getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Integer>());
					getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Catalogs>());
					getBuscaProyectosBean().setSinDatosProyectoPreguntas(true);
					nuevoIngreso = true;
//					getBuscaProyectosBean().setDesactivaSelecionPreguntas(false);
				}else{
//					getBuscaProyectosBean().setDesactivaSelecionPreguntas(true);
					nuevoIngreso= false;

					getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Catalogs>());
					for (Catalogs ca : getBuscaProyectosBean().getListaPreguntasSalvaguardas()) {
						for(ProjectQuestions pq: getBuscaProyectosBean().getListaPreguntasAsignadas()){							
							if(ca.getCataId().equals(pq.getCatalogs().getCataId())){								
								getBuscaProyectosBean().getPreguntasSelecionadas().add(ca);
								if(desactivaPregunta(ca.getCataId())){
									ca.setCataEstadoAux(true);
									listaTempPreguntasAsignadas.add(ca);
								}
							}
						}
					}
//					System.out.println("Inicia " + getBuscaProyectosBean().getPreguntasSelecionadas().size());
					Mensaje.actualizarComponente(":form:growl");				
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.modificarPreguntas"));
				}
			}else{
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.anioAsignacionSalvaguarda"));
				getBuscaProyectosBean().setDesactivaSelecionPreguntas(true);
//				getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Integer>());
				getBuscaProyectosBean().setPreguntasSelecionadas(new ArrayList<Catalogs>());
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "preguntasAsignadas " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Seleccion de salvaguardas en base a las preguntas seleccionadas
	 */
	@SuppressWarnings("rawtypes")
	public void asignarSalvaguardas(){
		
		List<Catalogs> catalogoPreguntas=new ArrayList<>();
		getBuscaProyectosBean().setListaSalvaguardasAsignadas(new ArrayList<ProjectQuestions>());
		Map<String,ProjectQuestions> mapaTemp=new HashMap<String,ProjectQuestions>();
		List<ProjectQuestions> listaTemporal=new ArrayList<>();	

		for(Catalogs p:getBuscaProyectosBean().getPreguntasSelecionadas()){
			for(Catalogs cat:getBuscaProyectosBean().getListaPreguntasSalvaguardas()){
				if(p.getCataId().equals(cat.getCataId())){
					catalogoPreguntas.add(cat);
				}
			}
		}

		for(Catalogs catalogo:catalogoPreguntas){
			ProjectQuestions salvaguardaAsignada=new ProjectQuestions();
			for(Safeguard salvaguarda:listadoSalvaguardas){
				if(catalogo.getCataText1().equals(salvaguarda.getSafeCode())){
					if(esProyecto)
						salvaguardaAsignada.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
					else{
						ProjectsStrategicPartner partner=new ProjectsStrategicPartner();
						partner.setPspaId(getBuscaProyectosBean().getCodigoStrategicPartner());
						salvaguardaAsignada.setProjectsStrategicPartners(partner);
					}
					salvaguardaAsignada.setSafeguards(salvaguarda);
					salvaguardaAsignada.setPrquStatus(true);
					salvaguardaAsignada.setCatalogs(catalogo);
					salvaguardaAsignada.setPrquYear(getBuscaProyectosBean().getAnioReporte());
					listaTemporal.add(salvaguardaAsignada);
				}
			}
		}
		getBuscaProyectosBean().setListaPreguntasAsignadas(listaTemporal);			
		for(ProjectQuestions sa:listaTemporal){
			mapaTemp.put(sa.getSafeguards().getSafeCode(), sa);
		}
		for(Entry<String,ProjectQuestions> sa:mapaTemp.entrySet()){
			getBuscaProyectosBean().getListaSalvaguardasAsignadas().add(sa.getValue());
		}
		
	}
	
	public void cargaListadoSalvaguardas(){
		try{
			List<Object[]> listaTemp=getAplicacionBean().getListaSalvaguardas();
			listadoSalvaguardas=new ArrayList<>();
			for (Object[] objects : listaTemp) {
				Safeguard salvaguarda=new Safeguard();
				salvaguarda.setSafeId(Integer.parseInt(objects[0].toString()));				
				salvaguarda.setSafeCode(objects[3].toString());
				salvaguarda.setSafeTitle(objects[4].toString());
				listadoSalvaguardas.add(salvaguarda);
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaListadoSalvaguardas " + ": ").append(e.getMessage()));
		}
	}
	public void quitaSeleccionSalvaguardaSocioEstrategico(){
		if(getBuscaProyectosBean().getTipoSocio() == 1){
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			esProyecto = true;
		}else
			esProyecto = false;
		getBuscaProyectosBean().setNombreSocioEstrategico("");
	}
	/**
	 * Grabar la asignacion de preguntas y salvaguardas
	 */
	public void grabarAsignacionSalvaguardas(){
		try{
			int tipo=0;
			if(esProyecto)
				tipo=getBuscaProyectosBean().getProyectoSeleccionado().getProjId();
			else
				tipo = getBuscaProyectosBean().getCodigoStrategicPartner();
			
			getProjectQuestionsFacade().agregaPreguntasProyecto(getBuscaProyectosBean().getListaPreguntasAsignadas(),esProyecto,tipo,nuevoIngreso,getBuscaProyectosBean().getAnioReporte());
			getBuscaProyectosBean().setAsignacionSalvaguardas(false);
			volverABuscarProyectos();
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);
			Mensaje.actualizarComponente(":form:growl");				
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.infoGrabada"));
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "grabarAsignacionSalvaguardas " + ": ").append(e.getMessage()));			
			Mensaje.actualizarComponente(":form:growl");				
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.grabar"));
		}
	}
		
	/**
	 * Setea el tipo de rol del usuario
	 */
	public void indicaTipoRol(){
		getBuscaProyectosBean().setTipoRol(getLoginBean().getTipoRol());
	}

	public void cargarAvanceEjecucionSalvaguarda(int codigoPartner,int codigoProyecto){
		try{

			getBuscaProyectosBean().setAdvanceExecution(new AdvanceExecutionSafeguards());
			if(codigoPartner==0)
				getBuscaProyectosBean().setAdvanceExecution(getAdvanceExecutionSafeguardsFacade().buscarPorProyecto(codigoProyecto));
			else
				getBuscaProyectosBean().setAdvanceExecution(getAdvanceExecutionSafeguardsFacade().buscarPorStrategicPartner(codigoPartner,codigoProyecto));
			if (getBuscaProyectosBean().getAdvanceExecution()==null){
				AdvanceExecutionSafeguards avanceEjecucion=new AdvanceExecutionSafeguards();
				avanceEjecucion.setAdexIsReported(false);
				avanceEjecucion.setAdexRegisterDate(new Date());
				avanceEjecucion.setAdexCreationDate(new Date());				
				avanceEjecucion.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
				if(codigoPartner>0){
					ProjectsStrategicPartner psp=new ProjectsStrategicPartner();
					psp.setPspaId(codigoPartner);
					avanceEjecucion.setProjectsStrategicPartners(psp);
				}
				avanceEjecucion.setUsers(getLoginBean().getUser());
				avanceEjecucion.setAdexStatus(true);
				avanceEjecucion.setAdexIsGender(false);
				avanceEjecucion.setAdexCreatorUser(getLoginBean().getUser().getUserName());
				avanceEjecucion.setAdexTermFrom(null);
				avanceEjecucion.setAdexTermTo(null);
				getBuscaProyectosBean().setAdvanceExecution(avanceEjecucion);

			}else{
				getBuscaProyectosBean().setAnioReporte(Integer.valueOf(getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0,4)));
				getBuscaProyectosBean().setPeriodoDesde("01");
				sectoresInteresProyecto();

			}


		}catch(NoResultException e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargarAvanceEjecucionSalvaguarda " + ": ").append(e.getMessage()));
		} catch (Exception e) {
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargarAvanceEjecucionSalvaguarda " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Cancela la seleccion del socio estrategico
	 */
	public void cancelaSeleccionSocio(){
		getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);
		getBuscaProyectosBean().setCodigoStrategicPartner(null);
	}
	/**
	 * Carga proyectos reportados como socio estrategico
	 * @param proyecto
	 */
	public void cargaProyectosReportados(Project proyecto){
		try{
			getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			if(getLoginBean().getTipoRol() == 3){
				for(ProjectsStrategicPartner psp:getLoginBean().getListaProyectosDelSocioEstrategico()){
					if(psp.getProjId().getProjId() == proyecto.getProjId()){
						getBuscaProyectosBean().setCodigoStrategicPartner(psp.getPspaId());
						break;
					}
				}
			}
			
			if(getLoginBean().getTipoRol() == 2)
				getBuscaProyectosBean().setListaProyectosReportados(getAdvanceExecutionSafeguardsFacade().listarProyectosReportados(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), 2));
			else if(getLoginBean().getTipoRol() == 3){
				getBuscaProyectosBean().setListaProyectosReportados(getAdvanceExecutionSafeguardsFacade().listarProyectosReportadosSocioEstrategico(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), 2,getBuscaProyectosBean().getCodigoStrategicPartner()));
			}
			if(getBuscaProyectosBean().getListaProyectosReportados().size()==0){
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "" ,getMensajesController().getPropiedad("info.noReportesGenerados") );
			}
			Collections.sort(getBuscaProyectosBean().getListaProyectosReportados(), new Comparator<AdvanceExecutionSafeguards>(){
				@Override
				public int compare(AdvanceExecutionSafeguards o1, AdvanceExecutionSafeguards o2) {
					return o1.getAdexTermFrom().compareToIgnoreCase(o2.getAdexTermFrom());
				}
			});
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaProyectosReportados " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Carga los proyectos en base a los criterios de busqueda seleccionados
	 */
	public void cargarProyectosCriterioBusqueda(){
		try{

			if(getBuscaProyectosBean().getTipoSocio()==2 && getBuscaProyectosBean().getCodigoStrategicPartner()==null){
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "" ,getMensajesController().getPropiedad("error.seleccionSocio") );
			}else{
				if (getLoginBean().getTipoRol()==3){
					for(ProjectsStrategicPartner psp:getLoginBean().getListaProyectosDelSocioEstrategico()){
						if(psp.getProjId().getProjId() == getBuscaProyectosBean().getProyectoSeleccionado().getProjId()){
							getBuscaProyectosBean().setCodigoStrategicPartner(psp.getPspaId());														
							break;
						}
					}
				}

				getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
				List<String> listaPeriodos = new ArrayList<String>();
				
				Integer [] array = new Integer[getAplicacionBean().getPeriodosPlanAccion().size()];
				if(getBuscaProyectosBean().getPeriodoSeleccionados().length==0){
					for(int i=0;i<getAplicacionBean().getPeriodosPlanAccion().size();i++){
						array[i]=getAplicacionBean().getPeriodosPlanAccion().get(i);
					}
					getBuscaProyectosBean().setPeriodoSeleccionados(array);
				}
				
				for (Integer valor : getBuscaProyectosBean().getPeriodoSeleccionados()) {
					listaPeriodos.add(String.valueOf(valor).concat("-").concat("01") );
				}
				getBuscaProyectosBean().setListaProyectosReportados(getAdvanceExecutionSafeguardsFacade().listarProyReportadosConCriteriosBusqueda(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), getBuscaProyectosBean().getCodigoStrategicPartner(),  listaPeriodos));
			
//				Collections.sort(getBuscaProyectosBean().getListaProyectosReportados(), new Comparator<AdvanceExecutionSafeguards>(){
//					@Override
//					public int compare(AdvanceExecutionSafeguards o1, AdvanceExecutionSafeguards o2) {
//						return o1.getAdexTermFrom().compareToIgnoreCase(o2.getAdexTermFrom());
//					}
//				});
				if(getBuscaProyectosBean().getListaProyectosReportados().size()==0){
					Mensaje.actualizarComponente(":form:growl");				
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "" ,getMensajesController().getPropiedad("info.noReportesGenerados") );
				}else{
					getBuscaProyectosBean().setMostrarOpcionesBusqueda(false);

					getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);

					setCodigoPartner(getBuscaProyectosBean().getCodigoStrategicPartner());
					getBuscaProyectosBean().setEstadoReporte("");
					getBuscaProyectosBean().setAnioReporte(0);
				}
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargarProyectosCriterioBusqueda " + ": ").append(e.getMessage()));
		}
	}
	
	public void cargarProyectosGeneroCriterioBusqueda(){
		try{

			if(getBuscaProyectosBean().getTipoSocio()==2 && getBuscaProyectosBean().getCodigoStrategicPartner()==null){
				Mensaje.actualizarComponente(":form:growl");				
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "" ,getMensajesController().getPropiedad("error.seleccionSocio") );
			}else{
				if (getLoginBean().getTipoRol()==3){
					for(ProjectsStrategicPartner psp:getLoginBean().getListaProyectosDelSocioEstrategico()){
						if(psp.getProjId().getProjId() == getBuscaProyectosBean().getProyectoSeleccionado().getProjId()){
							getBuscaProyectosBean().setCodigoStrategicPartner(psp.getPspaId());														
							break;
						}
					}
				}

//				if(getBuscaProyectosBean().getAnioReporte() != null){
//					periodoReporte = String.valueOf(getBuscaProyectosBean().getAnioReporte());
//					periodoReporte = periodoReporte.concat("-").concat("01");
//				}

				List<String> listaPeriodos = new ArrayList<String>();
				
				Integer [] array = new Integer[getAplicacionBean().getPeriodosPlanAccion().size()];
				if(getBuscaProyectosBean().getPeriodoSeleccionados().length==0){
					for(int i=0;i<getAplicacionBean().getPeriodosPlanAccion().size();i++){
						array[i]=getAplicacionBean().getPeriodosPlanAccion().get(i);
					}
					getBuscaProyectosBean().setPeriodoSeleccionados(array);
				}
				
				for (Integer valor : getBuscaProyectosBean().getPeriodoSeleccionados()) {
					listaPeriodos.add(String.valueOf(valor).concat("-").concat("01") );
				}

				getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
				getBuscaProyectosBean().setListaProyectosReportados(getAdvanceExecutionSafeguardsFacade().listarProyGeneroReportadosConCriteriosBusqueda(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), getBuscaProyectosBean().getCodigoStrategicPartner(), listaPeriodos ));
				if(getBuscaProyectosBean().getListaProyectosReportados().size()==0){
					Mensaje.actualizarComponente(":form:growl");				
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "" ,getMensajesController().getPropiedad("info.noReportesGenerados") );
				}else{
					getBuscaProyectosBean().setMostrarOpcionesBusquedaGenero(false);

					getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);

					setCodigoPartner(getBuscaProyectosBean().getCodigoStrategicPartner());
					getBuscaProyectosBean().setEstadoReporte("");
					getBuscaProyectosBean().setAnioReporte(0);
				}
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargarProyectosCriterioBusqueda " + ": ").append(e.getMessage()));
		}
	}
	
	/**
	 * Muestra el panel para busqueda de proyectos reportados
	 * @param proyecto  Proyecto seleccionado
	 */
	public void mostrarPanelBusquedaProyectos(Project proyecto){
		try{
			if(getBuscaProyectosBean().getListaProyectos().size()>1){
				getBuscaProyectosBean().setListaProyectos(new ArrayList<Project>());
				getBuscaProyectosBean().getListaProyectos().add(proyecto);
			}
			if(!esReporteGenero){
				getBuscaProyectosBean().setTipoSocio(1);
				getBuscaProyectosBean().setNuevoSeguimiento(false);
				getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
				getBuscaProyectosBean().setMostrarOpcionesBusqueda(true);
				getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
				getBuscaProyectosBean().setListaPartnersProyectos(new ArrayList<ProjectsStrategicPartner>());		
				getBuscaProyectosBean().setCodigoStrategicPartner(null);
				getBuscaProyectosBean().setAnioReporte(null);
				getBuscaProyectosBean().setListaPartnersProyectos(getProjectsStrategicPartnersFacade().listaPartnersActivos(proyecto.getProjId()));
				Collections.sort(getBuscaProyectosBean().getListaPartnersProyectos(), new Comparator<ProjectsStrategicPartner>(){
					@Override
					public int compare(ProjectsStrategicPartner o1, ProjectsStrategicPartner o2) {
						return o1.getPartId().getPartName().compareToIgnoreCase(o2.getPartId().getPartName());
					}
				});
				getBuscaProyectosBean().setPeriodoSeleccionados(null);
			}else{
				if(getLoginBean().getTipoRol() == 3){
					getBuscaProyectosBean().setNuevoSeguimiento(false);
					getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
					getBuscaProyectosBean().setMostrarOpcionesBusquedaGenero(true);
					getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
					getBuscaProyectosBean().setCodigoStrategicPartner(null);
					getBuscaProyectosBean().setAnioReporte(null);

				}else{
					getBuscaProyectosBean().setTipoSocio(1);
					getBuscaProyectosBean().setNuevoSeguimiento(false);
					getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
					getBuscaProyectosBean().setMostrarOpcionesBusquedaGenero(true);
					getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
					getBuscaProyectosBean().setListaPartnersProyectos(new ArrayList<ProjectsStrategicPartner>());		
					getBuscaProyectosBean().setCodigoStrategicPartner(null);
					getBuscaProyectosBean().setAnioReporte(null);
					getBuscaProyectosBean().setListaPartnersProyectos(getProjectsStrategicPartnersFacade().listaPartnersActivos(proyecto.getProjId()));
					Collections.sort(getBuscaProyectosBean().getListaPartnersProyectos(), new Comparator<ProjectsStrategicPartner>(){
						@Override
						public int compare(ProjectsStrategicPartner o1, ProjectsStrategicPartner o2) {
							return o1.getPartId().getPartName().compareToIgnoreCase(o2.getPartId().getPartName());
						}
					});
				}
				getBuscaProyectosBean().setPeriodoSeleccionados(null);
			}
			getBuscaProyectosBean().setDeshabilitaIniciarReporte(false);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "mostrarPanelBusquedaProyectos " + ": ").append(e.getMessage()));
		}
	}
	
	
	/**
	 * Permite asignar las lineas de genero para que puedan ser reportadas
	 * @param proyecto
	 */
	public void asignacionLineasGenero(Project proyecto){
		try{
			getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
			getBuscaProyectosBean().setAsignacionGenero(true);	
			getBuscaProyectosBean().setListaPartnersProyectos(getProjectsStrategicPartnersFacade().listaPartnersActivos(proyecto.getProjId()));		
			getBuscaProyectosBean().setDatosProyecto(true);
			getBuscaProyectosBean().setProyectoTienePlanGenero(true);
			getBuscaProyectosBean().setListaIndicadores(getIndicatorsFacade().listaIndicadoresGenero());
			getBuscaProyectosBean().setTipoSocio(1);
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			getBuscaProyectosBean().setNombreSocioEstrategico("");
			getBuscaProyectosBean().setListadoComponentes(new ArrayList<Component>());
			getBuscaProyectosBean().setListadoComponentes(getComponentsFacade().listaComponentesActivos(getBuscaProyectosBean().getProyectoSeleccionado().getProjId()));
			Component componente = new Component();
    		componente.setCompId(1000);
    		componente.setCompStatus(true);
    		componente.setCompCode("CEO5");
    		componente.setCompName("Componentes Operativos");
    		getBuscaProyectosBean().getListadoComponentes().add(componente);
    		esProyecto=true;
    		Collections.sort(getBuscaProyectosBean().getListaPartnersProyectos(), new Comparator<ProjectsStrategicPartner>(){
				@Override
				public int compare(ProjectsStrategicPartner o1, ProjectsStrategicPartner o2) {
					return o1.getPartId().getPartName().compareToIgnoreCase(o2.getPartId().getPartName());
				}
			});
    		validalineasGeneroAsignadas();
    		
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "asignacionLineasGenero " + ": ").append(e.getMessage()));
		}
	}
	
	public void validalineasGeneroAsignadas(){
		try{
			getBuscaProyectosBean().setListadoPartnersAsignados(new ArrayList<ProjectsStrategicPartner>());
			int registros = 0;
			registros = getProjectsGenderInfoFacade().numeroLineasAccionProyectoSeleccionado(getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
			if(registros>0){
				ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
				psp.setProjId(getBuscaProyectosBean().getProyectoSeleccionado());
				psp.setPartId(getBuscaProyectosBean().getProyectoSeleccionado().getPartId());
				getBuscaProyectosBean().getListadoPartnersAsignados().add(psp);
			}
				
			for (ProjectsStrategicPartner psp : getBuscaProyectosBean().getListaPartnersProyectos()) {
				registros = 0;
				registros = getProjectsGenderInfoFacade().numeroLineasAccionPartnerSeleccionado(psp.getPspaId());
				if(registros>0)
					getBuscaProyectosBean().getListadoPartnersAsignados().add(psp);
			}
    		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Pasa al tab de seleccion de socios estrategicos para reportar genero
	 */
	public void siguienteTabGeneroSeleccionSocio(){
		try{
			getBuscaProyectosBean().setProjectGenderInfoSinGenero(getProjectsGenderInfoFacade().buscaProjectGenderInfoPorPlanGeneroProyecto(getBuscaProyectosBean().getProyectoSeleccionado().getProjId()));
			if(getBuscaProyectosBean().isProyectoTienePlanGenero()){
				getBuscaProyectosBean().setPosicionTabGenero(1);	
				getBuscaProyectosBean().setLineasGeneroSociosEstrategicos(false);
				getBuscaProyectosBean().setCodigoStrategicPartner(null);					
			}else{
				List<ProjectsGenderInfo> lista=getProjectsGenderInfoFacade().listaProjectsGenderInfo(getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
				if(lista.size()>0 && getBuscaProyectosBean().getProjectGenderInfoSinGenero()==null)
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,"" , getMensajesController().getPropiedad("error.existeProyectoGenero") );
				else{
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,"" , getMensajesController().getPropiedad("info.sinGenero") );
					if(getBuscaProyectosBean().getProjectGenderInfoSinGenero()==null){
						ProjectsGenderInfo pgin= new ProjectsGenderInfo();
						pgin.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
						pgin.setPginComponents("");
						pgin.setPginCreationDate(new Date());
						pgin.setPginCreatorUser(getLoginBean().getUser().getUserName());
						pgin.setPginStatus(true);
						pgin.setPginHasToPlan(false);
						getProjectsGenderInfoFacade().create(pgin);
					}
				}

			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Ejecuta validaciones si no estan asignadas lineas de genero
	 */
	public void botonSinGenero(){
		List<ProjectsGenderInfo> lista;
		try {
			getBuscaProyectosBean().setProjectGenderInfoSinGenero(getProjectsGenderInfoFacade().buscaProjectGenderInfoPorPlanGeneroProyecto(getBuscaProyectosBean().getProyectoSeleccionado().getProjId()));
			if(getBuscaProyectosBean().isProyectoTienePlanGenero()==false){
				lista = getProjectsGenderInfoFacade().listaProjectsGenderInfo(getBuscaProyectosBean().getProyectoSeleccionado().getProjId());
				if(lista.size()>0 && getBuscaProyectosBean().getProjectGenderInfoSinGenero()==null)
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,"" , getMensajesController().getPropiedad("error.existeProyectoGenero") );
				else{
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,"" , getMensajesController().getPropiedad("info.sinGenero") );
					if(getBuscaProyectosBean().getProjectGenderInfoSinGenero()==null){
						ProjectsGenderInfo pgin= new ProjectsGenderInfo();
						pgin.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
						pgin.setPginComponents("");
						pgin.setPginCreationDate(new Date());
						pgin.setPginCreatorUser(getLoginBean().getUser().getUserName());
						pgin.setPginStatus(true);
						pgin.setPginHasToPlan(false);
						getProjectsGenderInfoFacade().create(pgin);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitaSeleccionLineasGeneroSocioEstrategico(){

		if(getBuscaProyectosBean().getTipoSocio()==1){
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			esProyecto = true;
		}else
			esProyecto = false;
		getBuscaProyectosBean().setNombreSocioEstrategico("");
	}
	/**
	 * Avanza al tab de asignacion de lineas de genero
	 */
	public void siguienteTabAsignaLineasGenero(){
		if(getBuscaProyectosBean().getTipoSocio()==2 && getBuscaProyectosBean().getCodigoStrategicPartner()!=null || getBuscaProyectosBean().getTipoSocio()==1){
			cargarLineasGeneroAsignadas();		
			getBuscaProyectosBean().setPosicionTabGenero(2);
		}else{
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "" ,getMensajesController().getPropiedad("error.seleccionSocio") );
		}
	}
	
	public void cargarLineasGeneroAsignadas(){
		try{
			getBuscaProyectosBean().setListaAccionesdeGeneroProyecto(new ArrayList<ProjectsGenderInfo>());
			if(getBuscaProyectosBean().getCodigoStrategicPartner()==null)
				getBuscaProyectosBean().setListaAccionesdeGeneroProyecto(getProjectsGenderInfoFacade().listaLineasGeneroProyectoPartner(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), 0));
			else
				getBuscaProyectosBean().setListaAccionesdeGeneroProyecto(getProjectsGenderInfoFacade().listaLineasGeneroProyectoPartner(getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), getBuscaProyectosBean().getCodigoStrategicPartner()));
			if(getBuscaProyectosBean().getListaAccionesdeGeneroProyecto()!=null && getBuscaProyectosBean().getListaAccionesdeGeneroProyecto().size()>0){
				String [] codigos= null;
				for (ProjectsGenderInfo pgi : getBuscaProyectosBean().getListaAccionesdeGeneroProyecto()) {
					codigos = pgi.getPginComponents().split(",");
					pgi.setComponentesGenero(ubicaComponentesGenero(codigos));
				}
				for(ProjectsGenderInfo pgi: getBuscaProyectosBean().getListaAccionesdeGeneroProyecto()){
					pgi.setProjectGenderIndicatorList(getProjectGenderIndicatorFacade().listaPorProjectGender(pgi.getPginId()));
				}
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargarLineasGeneroAsignadas " + ": ").append(e.getMessage()));
		}
	}
	
	public String ubicaComponentesGenero(String[] codigos){
		StringBuilder componentes=new StringBuilder();
		for(int x=0;x<codigos.length;x++){
			for (Component c : getBuscaProyectosBean().getListadoComponentes()) {
				if(c.getCompId() == Integer.valueOf(codigos[x].trim())){
					componentes.append(c.getCompName()).append(",");
				}else if(Integer.valueOf(codigos[x].trim()) == 1000){
					componentes.append("Componentes Operativos").append(",");
					break;
				}
			}
		}
		return componentes.toString();
	}
	
	
	public void atrasInicioGenero(){
		getBuscaProyectosBean().setPosicionTabGenero(0);
	}
	public void atrasSelecionSocioGenero(){
		getBuscaProyectosBean().setPosicionTabGenero(1);
		validalineasGeneroAsignadas();
	}
	
	/**
	 * Permite eliminar la linea de genero seleccionada
	 */
	public void eliminaLineaGeneroSeleccionada(){
		try{
			
			List<AdvanceExecutionProjectGender> listaTemp= new ArrayList<>();
			List<ProjectGenderIndicator> listaIndicadores=new ArrayList<>();			
			listaIndicadores = getProjectGenderIndicatorFacade().listaPorProjectGender(getBuscaProyectosBean().getProjectGenderInfoSeleccionado().getPginId());
			for (ProjectGenderIndicator pgi : listaIndicadores) {
				pgi.setPgigStatus(false);
				listaTemp .addAll(getAdvanceExecutionProjectGenderFacade().ubicaIndicadoresActivosNoFinalizados(pgi.getPgigId()));
			}
			getBuscaProyectosBean().getProjectGenderInfoSeleccionado().setPginStatus(false);
			getProjectsGenderInfoFacade().agregarEditarProjectGenerInfo(getBuscaProyectosBean().getProjectGenderInfoSeleccionado(), listaIndicadores,listaTemp);
			getBuscaProyectosBean().getListaAccionesdeGeneroProyecto().remove(getBuscaProyectosBean().getProjectGenderInfoSeleccionado());
			
			
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "", getMensajesController().getPropiedad("info.eliminar"));
			Mensaje.actualizarComponente(":form:growl");
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "eliminaLineaGeneroSeleccionada " + ": ").append(e.getMessage()));
		}

	}
	
	public void eliminaIndicadorGenero(){
		try{
			getBuscaProyectosBean().getListadoProyectoGeneroIndicador().remove(getBuscaProyectosBean().getIndicadorSeleccionado());
			if(getBuscaProyectosBean().getIndicadorSeleccionado().getPgigId() != null){
				getBuscaProyectosBean().getIndicadorSeleccionado().setPgigStatus(false);
				getProjectGenderIndicatorFacade().agregarEditar(getBuscaProyectosBean().getIndicadorSeleccionado());
				cargarLineasGeneroAsignadas();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	
	/**
	 * Genera un nuevo seguimiento como socio implementador o administrador
	 * @param proyecto  Codigo del proyecto
	 */
	public void nuevoSeguimientoProyectoAdminSocioImpl(Project proyecto){
		try{
			getBuscaProyectosBean().setAnioReporte(null);
			getBuscaProyectosBean().setPeriodoDesde(null);
			getBuscaProyectosBean().setCodigoStrategicPartner(null);
			getBuscaProyectosBean().setListaSectoresSeleccionados(null);
			getBuscaProyectosBean().setNuevoSeguimiento(true);
			getBuscaProyectosBean().setProyectoSeleccionado(proyecto);
			
			getBuscaProyectosBean().setListaPartnersProyectos(getProjectsStrategicPartnersFacade().listaPartnersActivos(proyecto.getProjId()));		
			getBuscaProyectosBean().setListaProyectosReportados(new ArrayList<AdvanceExecutionSafeguards>());
			getBuscaProyectosBean().setCodigoBusquedaProyecto(1);
			getBuscaProyectosBean().setTipoSocio(1);
			getBuscaProyectosBean().setDeshabilitaIniciarReporte(false);
			Mensaje.verDialogo("dlgSeleccionSocios");			
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "nuevoSeguimientoProyectoAdminSocioImpl " + ": ").append(e.getMessage()));
		}
	}
	public void nuevaInformacionAvanceEjecucion(){
		try{
			codigoPartner = null;
			ProjectsStrategicPartner psp=new ProjectsStrategicPartner();
			AdvanceExecutionSafeguards avanceEjecucion=new AdvanceExecutionSafeguards();
			avanceEjecucion.setAdexCreatorUser(getLoginBean().getUser().getUserName());
			avanceEjecucion.setAdexCreationDate(new Date());
			avanceEjecucion.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
			avanceEjecucion.setAdexIsReported(false);
			avanceEjecucion.setAdexRegisterDate(new Date());
			avanceEjecucion.setAdexReportedStatus("I");
			avanceEjecucion.setAdexStatus(true);
			avanceEjecucion.setUsers(getLoginBean().getUser());
			getBuscaProyectosBean().setSocioImplementador(getPartnersFacade().buscarPartnerPorCodigo(getBuscaProyectosBean().getProyectoSeleccionado().getPartId().getPartId()) );
			if(getBuscaProyectosBean().getCodigoStrategicPartner()==null && getBuscaProyectosBean().getTipoSocio()==2){
				Mensaje.actualizarComponente(":form:growl");
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionSocio"));
			}else{
				if(esReporteSalvaguardas){
					cargaSectoresInteres();	

					avanceEjecucion.setAdexIsGender(false);
//					if(getBuscaProyectosBean().getCodigoStrategicPartner()== null){					
//
//						avanceEjecucion.setProjectsStrategicPartners(null);
//					}else{			
//						codigoPartner = getBuscaProyectosBean().getCodigoStrategicPartner();
//
//						getBuscaProyectosBean().setCodigoStrategicPartner(codigoPartner);
//						psp.setPspaId(codigoPartner);
//						avanceEjecucion.setProjectsStrategicPartners(psp);
//					}
					getBuscaProyectosBean().setDatosProyecto(true);
					getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);
					Mensaje.ocultarDialogo("dlgSeleccionSocios");
				}else{
					avanceEjecucion.setAdexIsGender(true);
					getBuscaProyectosBean().setDatosProyecto(true);
					getBuscaProyectosBean().setSalvaguardasSociosEstrategicos(false);
					Mensaje.ocultarDialogo("dlgSeleccionSocios");
				}
				
				if(getBuscaProyectosBean().getCodigoStrategicPartner()== null){					
					avanceEjecucion.setProjectsStrategicPartners(null);
				}else{			
					codigoPartner = getBuscaProyectosBean().getCodigoStrategicPartner();
					getBuscaProyectosBean().setCodigoStrategicPartner(codigoPartner);
					psp.setPspaId(codigoPartner);
					avanceEjecucion.setProjectsStrategicPartners(psp);
				}

				
				
				getBuscaProyectosBean().setAdvanceExecution(avanceEjecucion);
				if(getBuscaProyectosBean().getAdvanceExecution()!=null && avanceEjecucion.getProjectsStrategicPartners()!=null)
					getBuscaProyectosBean().setNombreSocioEstrategico(getProjectsStrategicPartnersFacade().partnerEstrategico(avanceEjecucion.getProjectsStrategicPartners().getPspaId()).getPartId().getPartName());
				else
					getBuscaProyectosBean().setNombreSocioEstrategico("");
				getBuscaProyectosBean().setDeshabilitaIniciarReporte(false);
			}

		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "nuevaInformacionAvanceEjecucion " + ": ").append(e.getMessage()));
		}

	}
	
	public void mostrarDialogoFinAsignaSalvaguardas(){
		Mensaje.verDialogo("dlgAsignaSalvaguardas");
	}
	/**
	 * Carga las lineas de accion por tema
	 */
	public void cargaLineasAccionPorTema(){
		try{
			if(getBuscaProyectosBean().getLineaGeneroSel()!=null)
				getBuscaProyectosBean().setListaLineasAccion(getCatalogsFacade().buscaCatalogosPorTipo(getBuscaProyectosBean().getLineaGeneroSel().getCatyId()));
			
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaLineasAccionPorTema " + ": ").append(e.getMessage()));
		}
	}

	public void nuevaLineaAccion(){
		getBuscaProyectosBean().setListadoProyectoGeneroIndicador(new ArrayList<ProjectGenderIndicator>());
		getBuscaProyectosBean().setProjectGenderInfoSeleccionado(new ProjectsGenderInfo());
		getBuscaProyectosBean().setLineaGeneroSel(new CatalogTypes());
		getBuscaProyectosBean().setListaLineasAccion(new ArrayList<Catalogs>());
		getBuscaProyectosBean().setListadoComponentesSeleccionados(new ArrayList<String>());
		getBuscaProyectosBean().setNuevaLineaAccion(true);
	}
	public void agregaIndicadorGenero(){
		ProjectGenderIndicator pgi= new ProjectGenderIndicator();
		pgi.setProjectsGenderInfo(getBuscaProyectosBean().getProjectGenderInfoSeleccionado());
		getBuscaProyectosBean().getListadoProyectoGeneroIndicador().add(pgi);
		
	}

	public void vaciaInfoIndicador(ProjectGenderIndicator e ){
		ProjectGenderIndicator indicador =  e;
		indicador.setPgigGoalValueOne(0);
		indicador.setPgigGoalValueTwo(0);
		indicador.setPgigValueAnotherIndicator("");
		
	}
	
	public void grabarLineasAccion(){
		try{
			
			List<String[]> listaMsg = validarLineaDeAccion();
			
			if(listaMsg.isEmpty()){
			
			StringBuilder cmp = new StringBuilder();

				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().size()>0){
					ProjectsGenderInfo projectGenderInfo = new ProjectsGenderInfo();
					projectGenderInfo = getBuscaProyectosBean().getProjectGenderInfoSeleccionado();
					projectGenderInfo.setProjects(getBuscaProyectosBean().getProyectoSeleccionado());
					///cambio
					if(getBuscaProyectosBean().getProjectGenderInfoSinGenero()!=null){
						projectGenderInfo.setPginId(getBuscaProyectosBean().getProjectGenderInfoSinGenero().getPginId());
						projectGenderInfo.setPginCreationDate(getBuscaProyectosBean().getProjectGenderInfoSinGenero().getPginCreationDate());
						projectGenderInfo.setPginCreatorUser(getBuscaProyectosBean().getProjectGenderInfoSinGenero().getPginCreatorUser());
					}
					if(getBuscaProyectosBean().getCodigoStrategicPartner()!=null){
						ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
						psp.setPspaId(getBuscaProyectosBean().getCodigoStrategicPartner());					
						projectGenderInfo.setProjectsStrategicPartners(psp);
					}else
						projectGenderInfo.setProjectsStrategicPartners(null);
					
//					projectGenderInfo.setPspaId(getBuscaProyectosBean().getCodigoStrategicPartner());
					Iterator it= getBuscaProyectosBean().getListadoComponentesSeleccionados().iterator();
					while(it.hasNext()){
						cmp.append(ubicaComponente(it.next().toString())).append(",");
					}
					for(ProjectGenderIndicator pgi:getBuscaProyectosBean().getListadoProyectoGeneroIndicador()){
						pgi.setPgigStatus(true);
						if(pgi.getIndicators().getIndiType().equals("B")){
							if(pgi.isPgigBooleanValue())
								pgi.setPgigGoalValueOne(1);
							else
								pgi.setPgigGoalValueOne(0);
						}
					}
					projectGenderInfo.setPginComponents(cmp.toString());
					projectGenderInfo.setCataId(getBuscaProyectosBean().getLineaAccionSel());
					projectGenderInfo.setPginStatus(true);
					projectGenderInfo.setPginHasToPlan(true);
					if(projectGenderInfo.getPginId()==null){
						projectGenderInfo.setPginCreationDate(new Date());
						projectGenderInfo.setPginCreatorUser(getLoginBean().getUser().getUserName());				
					}else{
						projectGenderInfo.setPginUpdateDate(new Date());
						projectGenderInfo.setPginUpdateUser(getLoginBean().getUser().getUserName());
					}
					getProjectsGenderInfoFacade().agregarEditarProjectGenerInfo(projectGenderInfo, getBuscaProyectosBean().getListadoProyectoGeneroIndicador(),null);
					cargarLineasGeneroAsignadas();
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "", getMensajesController().getPropiedad("info.infoGrabada"));
					Mensaje.ocultarDialogo("dlgAsignaLineaGenero");
					getBuscaProyectosBean().setNuevaLineaAccion(false);
				}else{
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "", getMensajesController().getPropiedad("info.indicador"));
					
				}
			}else{
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}
		
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "grabarLineasAccion " + ": ").append(e.getMessage()));
		}	
	}
	
	private List<String[]> validarLineaDeAccion(){
		List<String[]> listaMsg = new ArrayList<>();
		try{
			for(int i=0;i<getBuscaProyectosBean().getListadoProyectoGeneroIndicador().size();i++){
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getIndicators()==null){
					String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":temasObj", "Campo Obligatorio" };
					listaMsg.add(msg);
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigBaseLine().length()==0){
					String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":lineaBase", "Campo Obligatorio" };
					listaMsg.add(msg);
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoals().length()==0){
					String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":metas", "Campo Obligatorio" };
					listaMsg.add(msg);
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getIndicators().getIndiType().equals("G")){
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==null||getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":valorUno", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getIndicators().getIndiType().equals("G")){
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==null||getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":valorUno", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueTwo()==null||getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueTwo()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":valorDos", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getIndicators().getIndiType().equals("N")){
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==null||getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigGoalValueOne()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":valorTres", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
				}
				if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getIndicators().getIndiType().equals("O")){
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigAnotherIndicator().length()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":otroIndicador", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
					if(getBuscaProyectosBean().getListadoProyectoGeneroIndicador().get(i).getPgigValueAnotherIndicator().length()==0){
						String[] msg = { "form:buscaProyectos:tablaIndicadores:"+i+":valorCinco", "Campo Obligatorio" };
						listaMsg.add(msg);
					}
				}
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
		}	
		return listaMsg;
	}
	
	public void validatablaIndicadores(){
//		cargarLineasGeneroAsignadas();
		if(getBuscaProyectosBean().getProjectGenderInfoSeleccionado().getProjectGenderIndicatorList() !=null){
			Iterator itera= getBuscaProyectosBean().getProjectGenderInfoSeleccionado().getProjectGenderIndicatorList().iterator();
			while (itera.hasNext()){
				ProjectGenderIndicator pgi= (ProjectGenderIndicator)itera.next();
				if(pgi.getIndicators()==null)
					itera.remove();
			}
		}
		Mensaje.ocultarDialogo("dlgAsignaLineaGenero");
	}
	
	public void editarLineaAccion(ProjectsGenderInfo linea){
		try{
			getBuscaProyectosBean().setNuevaLineaAccion(true);
			getBuscaProyectosBean().setProjectGenderInfoSeleccionado(linea);
			for (CatalogTypes c : getBuscaProyectosBean().getListaLineasGenero()) {
				if(c.getCatyDescription().equals(linea.getCataId().getCatalogTypes().getCatyDescription())){
					getBuscaProyectosBean().setLineaGeneroSel(c);
					break;
				}
			}
			cargaLineasAccionPorTema();
			for(Catalogs c:getBuscaProyectosBean().getListaLineasAccion()){
				if(c.getCataText2().equals(linea.getCataId().getCataText2())){
					getBuscaProyectosBean().setLineaAccionSel(c);
					break;
				}
			}
			getBuscaProyectosBean().setListadoComponentesSeleccionados(new ArrayList<String>());
			String[] componentes = linea.getPginComponents().split(",");
			for(int i=0;i<componentes.length;i++){
				getBuscaProyectosBean().getListadoComponentesSeleccionados().add(ubicaComponentePorCodigo(Integer.valueOf(componentes[i])));
			}
			
			getBuscaProyectosBean().setListadoProyectoGeneroIndicador(linea.getProjectGenderIndicatorList());
			for (ProjectGenderIndicator pgin : getBuscaProyectosBean().getListadoProyectoGeneroIndicador()) {
				pgin.setIndicators(ubicaIndicador(pgin.getIndicators()));
				if(pgin.getIndicators().getIndiType().equals("B")){
					if(pgin.getPgigGoalValueOne().equals(1))
						pgin.setPgigBooleanValue(true);
					else
						pgin.setPgigBooleanValue(false);
				}
			}
			Mensaje.verDialogo("dlgAsignaLineaGenero");
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "editarLineaAccion " + ": ").append(e.getMessage()));
		}
	}
	
	public int ubicaComponente(String componente){
		int codigo=0;
		for (Component comp : getBuscaProyectosBean().getListadoComponentes()) {			
			if(comp.getCompName().equals(componente)){
				codigo=comp.getCompId();
				break;
			}
		}
		return codigo;
	}
	public String ubicaComponentePorCodigo(int codigo){
		String componente="";
		for (Component comp : getBuscaProyectosBean().getListadoComponentes()) {
			if(comp.getCompId()== codigo){
				componente=comp.getCompName();
				break;
			}
		}
		return componente;
	}
	
	public Indicators ubicaIndicador(Indicators i){
		Indicators obj=null;
		try{
			
			for (Indicators indi : getBuscaProyectosBean().getListaIndicadores()) {
				if(indi.getIndiDescription().equals(i.getIndiDescription())){
					obj=indi;
					break;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	
	public void finAsignacionGenero(){
		getBuscaProyectosBean().setListadoComponentesSeleccionados(new ArrayList<String>());
		getBuscaProyectosBean().setProjectGenderInfoSeleccionado(new ProjectsGenderInfo());
		getBuscaProyectosBean().setListadoProyectoGeneroIndicador(new ArrayList<ProjectGenderIndicator>());
		getBuscaProyectosBean().setListaAccionesdeGeneroProyecto(new ArrayList<ProjectsGenderInfo>());
		getBuscaProyectosBean().setAsignacionGenero(false);
		getBuscaProyectosBean().setDatosProyecto(false);
		getBuscaProyectosBean().setPosicionTabGenero(0);
	}
	
	public void mostrarDialogoActivarReporte(){
		comentarioActivarReporte="";
		correoSocios="";
		if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners()!=null){
			if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartContactPersonEmail()!=null)
				correoSocios=getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartContactPersonEmail();			
		}
		Mensaje.actualizarComponente(":form:buscaProyectos:panelCorreos");
		Mensaje.verDialogo("dlgVolverActivarReporte");
	}
	/**
	 * Permite volver a activar el reporte
	 */
	public void volverActivarReporte(){
		try{
			List<String> para= new ArrayList<>();
			List<String> listaCopia=new ArrayList<>();
			boolean correoValido=false;
			String correos[]= correoSocios.split(";");
			if(correos.length==1){
				correoValido = UtilsCadenas.isValidEmailAddress(correos[0].trim());
				para.add(correos[0].trim());
			}else if(correos.length>1){
				for(int c =0;c<correos.length;c++){
					correoValido = UtilsCadenas.isValidEmailAddress(correos[c].trim());
					if(correoValido==true && c==0){
						para.add(correos[c].trim());						
					}else if(correoValido==true && c>0){
						listaCopia.add(correos[c].trim());
					}else if(correoValido == false)
						break;					
				}
			}			
			if(comentarioActivarReporte.trim().length()>0 && correoValido){				
				AdvanceExecutionSafeguards avanceImplementador = getAdvanceExecutionSafeguardsFacade().buscaAvanceEjecucionSocioImplementador(getBuscaProyectosBean().getAdvanceExecution());
				if(avanceImplementador != null){
					avanceImplementador.setAdexIsReported(false);
					avanceImplementador.setAdexReportedStatus("I");
					avanceImplementador.setAdexUpdateUser(getLoginBean().getUser().getUserName());
					avanceImplementador.setAdexUpdateDate(new Date());
					getAdvanceExecutionSafeguardsFacade().edit(avanceImplementador);
				}
				getBuscaProyectosBean().getAdvanceExecution().setAdexIsReported(false);
				getBuscaProyectosBean().getAdvanceExecution().setAdexReportedStatus("I");
				getBuscaProyectosBean().getAdvanceExecution().setAdexUpdateUser(getLoginBean().getUser().getUserName());
				getBuscaProyectosBean().getAdvanceExecution().setAdexUpdateDate(new Date());
				getAdvanceExecutionSafeguardsFacade().edit(getBuscaProyectosBean().getAdvanceExecution());
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "", getMensajesController().getPropiedad("info.reactivacion"));				
				enviarCorreoActivacionProyecto(para,listaCopia);
				Mensaje.ocultarDialogo("dlgVolverActivarReporte");
			}else{
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "", getMensajesController().getPropiedad("error.correoSocio"));	
			}
			
			
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "volverActivarReporte " + ": ").append(e.getMessage()));
		}
	}
	
	public void enviarCorreoActivacionProyecto(List<String> para, List<String> copia){
//		List<String> para=new ArrayList<>();
//		List<String> copia=new ArrayList<>();
		List<String> oculto=new ArrayList<>();
		String periodo=getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom()+" - "+getBuscaProyectosBean().getAdvanceExecution().getAdexTermTo();
		String projName=getBuscaProyectosBean().getAdvanceExecution().getProjects().getProjTitle();
		String partImplName=getBuscaProyectosBean().getAdvanceExecution().getProjects().getPartId().getPartName();
		String mensaje="Estimado socio,<br/><br/>";
		mensaje=mensaje+"El siguiente reporte de avance de salvaguardas ha sido habilitado para su edici&oacute;n:<br/><br/>";
		mensaje=mensaje+"<strong>Periodo: </strong>"+periodo+"<br/><br/>";
		mensaje=mensaje+"<strong>Proyecto: </strong>"+projName+"<br/><br/>";
		mensaje=mensaje+"<strong>Socio Implementador: </strong>"+partImplName+"<br/><br/>";
		//para.add(getBuscaProyectosBean().getAdvanceExecution().getProjects().getPartId().getPartContactPersonEmail());
		if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners()!=null){
			mensaje=mensaje+"<strong>Socio Estrat&eacute;gico: </strong>"+(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartName())+"<br/><br/>";
//			para.add(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartContactPersonEmail()!=null?getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartContactPersonEmail():"sincorreo@correo.com");
		}
		mensaje=mensaje+"<strong>Observaciones: </strong>"+comentarioActivarReporte;
//		para.add("david.guano@undp.org");
//		para.add("david.guano@gmail.com");
//		para.add(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartContactPersonEmail());
		
		sesBean.enviarCorreo("SIS – Se ha habilitado su reporte de salvaguardas", para, copia, oculto, mensaje);
		comentarioActivarReporte="";
	}
	
	public void obtenerSalvaguardasAsignadas(int codigoPartner,int codigoProyecto){

		try{
			Map<String,ProjectQuestions> mapaTemp=new HashMap<String,ProjectQuestions>();
			List<ProjectQuestions> listaTemporal=new ArrayList<>();
			getBuscaProyectosBean().setListaSalvaguardasAsignadas(new ArrayList<ProjectQuestions>());
			int anio=0;
			if(getBuscaProyectosBean().getAdvanceExecution().getAdexId()!=null){
				anio= Integer.valueOf(getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0,4));
			}
			
			if(codigoProyecto==0){
				if(getBuscaProyectosBean().getAnioReporte()>0)
					listaTemporal = getProjectQuestionsFacade().listaPreguntasPartnerSeleccionadas(codigoPartner,getBuscaProyectosBean().getAnioReporte());
				else
					listaTemporal = getProjectQuestionsFacade().listaPreguntasPartnerSeleccionadas(codigoPartner,anio);
			}else{
				if(getBuscaProyectosBean().getAnioReporte()>0)
					listaTemporal = getProjectQuestionsFacade().listaPreguntasProyectoSeleccionadas(codigoProyecto,getBuscaProyectosBean().getAnioReporte());
				else
					listaTemporal = getProjectQuestionsFacade().listaPreguntasProyectoSeleccionadas(codigoProyecto,anio);
			}
			for(ProjectQuestions sa:listaTemporal){
				mapaTemp.put(sa.getSafeguards().getSafeCode(), sa);
			}
			for(Entry<String,ProjectQuestions> sa:mapaTemp.entrySet())
				getBuscaProyectosBean().getListaSalvaguardasAsignadas().add(sa.getValue());

		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "obtenerSalvaguardasAsignadas " + ": ").append(e.getMessage()));
		}
	}
	
	public boolean localizaSalvaguardaAsignada(String salvaguarda){
		boolean encontrado = false;
		for (ProjectQuestions saf : getBuscaProyectosBean().getListaSalvaguardasAsignadas()) {
			if(saf.getSafeguards().getSafeCode().equals(salvaguarda)){
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}
	
	public void generarPdf(){
		try{
			List<AdvanceSummary> listaAvances = new ArrayList<>();
			String htmlReporte = GenerarPdfResumen.REPORTE_RESUMEN_ENCABEZADO;
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			String periodo = getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0, 4).concat(" Enero - Diciembre");
//			List<Questions> preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("A");
			List<Questions> preguntasActivas = new ArrayList<>();
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//			String logoEscudo = ctx.getRealPath("/") + File.separator +  "resources"+ File.separator +  "images"+ File.separator + "escudoE.png";
//			String logoMae = ctx.getRealPath("/") + "/resources/images/mae.png";
//			System.out.println(logoEscudo);
			listaAvances = getAdvanceSummaryFacade().listaAvancesPorAvanceEjecucion(getBuscaProyectosBean().getAdvanceExecution().getAdexId());
			
			if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners() == null)
				obtenerSalvaguardasAsignadas(0, getBuscaProyectosBean().getAdvanceExecution().getProjects().getProjId());
			else
				obtenerSalvaguardasAsignadas(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPspaId(), 0);
			
			DtoResumenSalvaguarda dto = new DtoResumenSalvaguarda();
			dto.setLogoEscudo("escudoE2023.png");
			dto.setLogoMae("mae.png");
			dto.setLogoPieIzquierda("maate_2023_izquierda.jpeg");
			dto.setLogoPieDerecha("maate_2023_derecha.jpeg");
			dto.setFecha(date);
			dto.setProyecto(getBuscaProyectosBean().getAdvanceExecution().getProjects().getProjTitle());
			dto.setSocioImplementador(getBuscaProyectosBean().getAdvanceExecution().getProjects().getPartId().getPartName());
			dto.setPeriodo(periodo);
			if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners()!=null)
				dto.setSocioEstrategico(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartName());
			StringBuffer sectores= new StringBuffer();
			for (AdvanceSectors ava : getBuscaProyectosBean().getAdvanceExecution().getAdvanceSectorsList()) {
				sectores.append(ava.getSectors().getSectName()).append(" , ");				
			}
			dto.setSectores(sectores.toString());
			
			if(localizaSalvaguardaAsignada("A")){
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("A");
				dto.setPregunta1(preguntasActivas.get(0).getQuesContentQuestion());
				dto.setPregunta2(preguntasActivas.get(1).getQuesContentQuestion());
				dto.setPregunta3A(preguntasActivas.get(2).getQuesContentQuestion());			
				dto.setResumenA(ubicaAvaneResumen("A", listaAvances).getAdsuAdvance());			
				llenarTablasPdfA(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAA;
			}
			if(localizaSalvaguardaAsignada("B")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "B");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("B");
				dto.setPregunta4B(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta41B(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta41B("");
				dto.setPregunta5B(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta51B(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta51B("");
				dto.setPregunta6B(preguntasActivas.get(4).getQuesContentQuestion());
				if(listaSiNo.get(2).isVaanYesnoAnswerValue())
					dto.setPregunta61B(preguntasActivas.get(5).getQuesContentQuestion());
				else
					dto.setPregunta61B("");
				dto.setPregunta7B(preguntasActivas.get(7).getQuesContentQuestion());
				if(listaSiNo.get(3).isVaanYesnoAnswerValue())
					dto.setPregunta71B(preguntasActivas.get(8).getQuesContentQuestion());
				else
					dto.setPregunta71B("");				
				dto.setPregunta8B(preguntasActivas.get(9).getQuesContentQuestion());
				if(listaSiNo.get(4).isVaanYesnoAnswerValue())
					dto.setPregunta81B(preguntasActivas.get(10).getQuesContentQuestion());
				else
					dto.setPregunta81B("");
				dto.setPregunta9B(preguntasActivas.get(11).getQuesContentQuestion());
				if(!listaSiNo.get(5).isVaanYesnoAnswerValue())
					dto.setPregunta91B(preguntasActivas.get(14).getQuesContentQuestion());
				else
					dto.setPregunta91B("");
				dto.setPregunta11B(preguntasActivas.get(12).getQuesContentQuestion());
				if(listaSiNo.get(6).isVaanYesnoAnswerValue())
					dto.setPregunta111B(preguntasActivas.get(13).getQuesContentQuestion());
				else
					dto.setPregunta111B("");
				dto.setPregunta12B(preguntasActivas.get(15).getQuesContentQuestion());
				if(listaSiNo.get(7).isVaanYesnoAnswerValue())
					dto.setPregunta121B(preguntasActivas.get(16).getQuesContentQuestion());
				else
					dto.setPregunta121B("");
				dto.setPregunta13B(preguntasActivas.get(17).getQuesContentQuestion());
				if(listaSiNo.get(8).isVaanYesnoAnswerValue())
					dto.setPregunta131B(preguntasActivas.get(18).getQuesContentQuestion());
				else
					dto.setPregunta131B("");
				dto.setPregunta14B(preguntasActivas.get(19).getQuesContentQuestion());
				if(listaSiNo.get(9).isVaanYesnoAnswerValue())
					dto.setPregunta141B(preguntasActivas.get(20).getQuesContentQuestion());
				else
					dto.setPregunta141B("");
				dto.setResumenB(ubicaAvaneResumen("B", listaAvances).getAdsuAdvance());
				llenarTablasPdfB(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAB;
			}
			if(localizaSalvaguardaAsignada("C")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "C");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("C");
				dto.setPregunta20C(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta201C(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta201C("");
				dto.setPregunta21C(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta211C(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta211C("");
				dto.setPregunta24C(preguntasActivas.get(4).getQuesContentQuestion());
				if(listaSiNo.get(2).isVaanYesnoAnswerValue()){
					dto.setPregunta241C(preguntasActivas.get(5).getQuesContentQuestion());
					dto.setPregunta242C(preguntasActivas.get(6).getQuesContentQuestion());
				}else{
					dto.setPregunta241C("");
					dto.setPregunta242C("");
				}
								
				dto.setPregunta25C(preguntasActivas.get(7).getQuesContentQuestion());
				if(listaSiNo.get(3).isVaanYesnoAnswerValue())
					dto.setPregunta26C(preguntasActivas.get(8).getQuesContentQuestion());
				else
					dto.setPregunta26C("");
				dto.setPregunta27C(preguntasActivas.get(9).getQuesContentQuestion());
				if(listaSiNo.get(4).isVaanYesnoAnswerValue())
					dto.setPregunta271C(preguntasActivas.get(10).getQuesContentQuestion());
				else
					dto.setPregunta271C("");
				dto.setPregunta28C(preguntasActivas.get(11).getQuesContentQuestion());				
				dto.setPregunta29C(preguntasActivas.get(12).getQuesContentQuestion());
				if(listaSiNo.get(5).isVaanYesnoAnswerValue())
					dto.setPregunta291C(preguntasActivas.get(13).getQuesContentQuestion());
				else
					dto.setPregunta291C("");
				dto.setPregunta30C(preguntasActivas.get(14).getQuesContentQuestion());
				if(listaSiNo.get(6).isVaanYesnoAnswerValue())
					dto.setPregunta301C(preguntasActivas.get(15).getQuesContentQuestion());
				else
					dto.setPregunta301C("");
				dto.setPregunta31C(preguntasActivas.get(16).getQuesContentQuestion());
				if(listaSiNo.get(7).isVaanYesnoAnswerValue())
					dto.setPregunta311C(preguntasActivas.get(17).getQuesContentQuestion());
				else
					dto.setPregunta311C("");
				dto.setResumenC(ubicaAvaneResumen("C", listaAvances).getAdsuAdvance());
				llenarTablasPdfC(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAC;
			}
			if(localizaSalvaguardaAsignada("D")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "D");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("D");
				dto.setPregunta32D(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta321D(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta321D("");
				dto.setPregunta33D(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta331D(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta331D("");
				dto.setResumenD(ubicaAvaneResumen("D", listaAvances).getAdsuAdvance());
				llenarTablasPdfD(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAD;
			}
			if(localizaSalvaguardaAsignada("E")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "E");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("E");
				dto.setPregunta34E(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta341E(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta341E("");
				dto.setPregunta35E(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta351E(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta351E("");
				dto.setPregunta36E(preguntasActivas.get(4).getQuesContentQuestion());
				if(listaSiNo.get(2).isVaanYesnoAnswerValue())
					dto.setPregunta361E(preguntasActivas.get(5).getQuesContentQuestion());
				else
					dto.setPregunta361E("");
				dto.setPregunta37E(preguntasActivas.get(6).getQuesContentQuestion());
				if(listaSiNo.get(3).isVaanYesnoAnswerValue())
					dto.setPregunta371E(preguntasActivas.get(7).getQuesContentQuestion());
				else
					dto.setPregunta371E("");
				dto.setPregunta372E(preguntasActivas.get(8).getQuesContentQuestion());
				dto.setPregunta38E(preguntasActivas.get(9).getQuesContentQuestion());
				if(listaSiNo.get(4).isVaanYesnoAnswerValue())
					dto.setPregunta381E(preguntasActivas.get(10).getQuesContentQuestion());
				else
					dto.setPregunta381E("");
				dto.setPregunta39E(preguntasActivas.get(11).getQuesContentQuestion());
				if(listaSiNo.get(5).isVaanYesnoAnswerValue())
					dto.setPregunta391E(preguntasActivas.get(12).getQuesContentQuestion());
				else
					dto.setPregunta391E("");
				dto.setPregunta40E(preguntasActivas.get(13).getQuesContentQuestion());
				dto.setPregunta401E(preguntasActivas.get(14).getQuesContentQuestion());
				dto.setPregunta402E(preguntasActivas.get(15).getQuesContentQuestion());
				dto.setPregunta403E(preguntasActivas.get(16).getQuesContentQuestion());
				dto.setPregunta404E(preguntasActivas.get(17).getQuesContentQuestion());
				dto.setPregunta405E(preguntasActivas.get(18).getQuesContentQuestion());
				dto.setPregunta406E(preguntasActivas.get(19).getQuesContentQuestion());
				dto.setPregunta407E(preguntasActivas.get(20).getQuesContentQuestion());
				dto.setPregunta408E(preguntasActivas.get(21).getQuesContentQuestion());
				dto.setPregunta409E(preguntasActivas.get(22).getQuesContentQuestion());
				dto.setPregunta4010E(preguntasActivas.get(23).getQuesContentQuestion());
				dto.setPregunta4011E(preguntasActivas.get(24).getQuesContentQuestion());
				dto.setPregunta4012E(preguntasActivas.get(25).getQuesContentQuestion());
				dto.setPregunta4013E(preguntasActivas.get(26).getQuesContentQuestion());
				dto.setPregunta4014E(preguntasActivas.get(27).getQuesContentQuestion());
				dto.setPregunta4015E(preguntasActivas.get(28).getQuesContentQuestion());
				dto.setPregunta4016E(preguntasActivas.get(29).getQuesContentQuestion());
				dto.setPregunta4017E(preguntasActivas.get(30).getQuesContentQuestion());
				dto.setPregunta4018E(preguntasActivas.get(31).getQuesContentQuestion());
				dto.setPregunta4019E(preguntasActivas.get(32).getQuesContentQuestion());
				dto.setPregunta4020E(preguntasActivas.get(33).getQuesContentQuestion());
				dto.setPregunta4021E(preguntasActivas.get(34).getQuesContentQuestion());
				dto.setPregunta4022E(preguntasActivas.get(35).getQuesContentQuestion());
				dto.setPregunta4023E(preguntasActivas.get(36).getQuesContentQuestion());
				dto.setPregunta4024E(preguntasActivas.get(37).getQuesContentQuestion());
				dto.setPregunta4025E(preguntasActivas.get(38).getQuesContentQuestion());
				dto.setPregunta4026E(preguntasActivas.get(39).getQuesContentQuestion());
				dto.setPregunta4027E(preguntasActivas.get(40).getQuesContentQuestion());
				dto.setPregunta4028E(preguntasActivas.get(41).getQuesContentQuestion());
				dto.setPregunta4029E(preguntasActivas.get(42).getQuesContentQuestion());
				dto.setPregunta4030E(preguntasActivas.get(43).getQuesContentQuestion());
				dto.setPregunta4031E(preguntasActivas.get(44).getQuesContentQuestion());
				dto.setPregunta4032E(preguntasActivas.get(45).getQuesContentQuestion());
				dto.setPregunta4033E(preguntasActivas.get(46).getQuesContentQuestion());
				dto.setPregunta4034E(preguntasActivas.get(47).getQuesContentQuestion());
				dto.setPregunta4035E(preguntasActivas.get(48).getQuesContentQuestion());
				dto.setPregunta4036E(preguntasActivas.get(49).getQuesContentQuestion());
				dto.setPregunta4037E(preguntasActivas.get(50).getQuesContentQuestion());
				dto.setPregunta4038E(preguntasActivas.get(51).getQuesContentQuestion());
				dto.setResumenE(ubicaAvaneResumen("E", listaAvances).getAdsuAdvance());
				llenarTablasPdfE(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAE;
			}
			if(localizaSalvaguardaAsignada("F")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "F");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("F");
				dto.setPregunta41F(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta411F(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta411F("");
				dto.setPregunta42F(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta421F(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta421F("");
				dto.setPregunta43F(preguntasActivas.get(4).getQuesContentQuestion());
				if(listaSiNo.get(2).isVaanYesnoAnswerValue())
					dto.setPregunta431F(preguntasActivas.get(5).getQuesContentQuestion());
				else
					dto.setPregunta431F("");
				dto.setPregunta44F(preguntasActivas.get(6).getQuesContentQuestion());
				if(listaSiNo.get(3).isVaanYesnoAnswerValue())
					dto.setPregunta441F(preguntasActivas.get(7).getQuesContentQuestion());
				else
					dto.setPregunta441F("");
				dto.setPregunta45F(preguntasActivas.get(8).getQuesContentQuestion());
				if(listaSiNo.get(4).isVaanYesnoAnswerValue())
					dto.setPregunta451F(preguntasActivas.get(9).getQuesContentQuestion());
				else
					dto.setPregunta451F("");
				dto.setResumenF(ubicaAvaneResumen("F", listaAvances).getAdsuAdvance());
				llenarTablasPdfF(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAF;
			}
			if(localizaSalvaguardaAsignada("G")){
				List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "G");
				preguntasActivas = getQuestionsFacade().buscaPreguntaPorCodigoSalvaguarda("G");
				dto.setPregunta46G(preguntasActivas.get(0).getQuesContentQuestion());
				if(listaSiNo.get(0).isVaanYesnoAnswerValue())
					dto.setPregunta461G(preguntasActivas.get(1).getQuesContentQuestion());
				else
					dto.setPregunta461G("");
				dto.setPregunta47G(preguntasActivas.get(2).getQuesContentQuestion());
				if(listaSiNo.get(1).isVaanYesnoAnswerValue())
					dto.setPregunta471G(preguntasActivas.get(3).getQuesContentQuestion());
				else
					dto.setPregunta471G("");
				dto.setPregunta48G(preguntasActivas.get(5).getQuesContentQuestion());
				if(listaSiNo.get(2).isVaanYesnoAnswerValue())
					dto.setPregunta481G(preguntasActivas.get(6).getQuesContentQuestion());
				else
					dto.setPregunta481G("");
				dto.setPregunta49G(preguntasActivas.get(7).getQuesContentQuestion());
				if(listaSiNo.get(3).isVaanYesnoAnswerValue())
					dto.setPregunta491G(preguntasActivas.get(8).getQuesContentQuestion());
				else
					dto.setPregunta491G("");
				dto.setPregunta50G(preguntasActivas.get(9).getQuesContentQuestion());
				if(listaSiNo.get(4).isVaanYesnoAnswerValue())
					dto.setPregunta501G(preguntasActivas.get(10).getQuesContentQuestion());
				else
					dto.setPregunta501G("");
				dto.setPregunta51G(preguntasActivas.get(11).getQuesContentQuestion());
				if(listaSiNo.get(5).isVaanYesnoAnswerValue())
					dto.setPregunta511G(preguntasActivas.get(12).getQuesContentQuestion());
				else
					dto.setPregunta511G("");
				dto.setPregunta512G(preguntasActivas.get(13).getQuesContentQuestion());
				dto.setPregunta513G(preguntasActivas.get(14).getQuesContentQuestion());
				dto.setResumenG(ubicaAvaneResumen("G", listaAvances).getAdsuAdvance());
				llenarTablasPdfG(dto,preguntasActivas);
				htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAG;
			}
			htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_PIE;
			
//			String html = GenerarPdfResumen.REPORTE_RESUMEN_SALVAGUARDAS;
//			html = getGeneradorPdfHtml().procesar(html, dto);
			String html = getGeneradorPdfHtml().procesar(htmlReporte, dto);

//			byte[] array = getGeneradorPdfHtml().crearPdf(html, 25, 25, 25, 25, null);
			byte[] array = getGeneradorPdfHtml().crearDocumentoPdf(html,2);
			descargarReporte("application/pdf", "resumenSalvaguardas" , array);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "generarPdf " + ": ").append(e.getMessage()));
			
		}
	}
	
	private void descargarReporte(String mimeType, String nombreArchivo, byte[] bs) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		response.reset();
		response.setContentType(mimeType);
		response.setHeader("Content-disposition", "attachment; filename=\"" + nombreArchivo + ".pdf");

		OutputStream output = response.getOutputStream();
		output.write(bs);
		output.flush();
		output.close();

		facesContext.responseComplete();
	}
	
	public void llenarTablasPdfA(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{
			
			List<DtoRespuestasSalvaguardas> listaA = getTableResponsesFacade().resumenSalvaguardaA(getBuscaProyectosBean().getAdvanceExecution().getAdexId());
			List<Catalogs> catalogos= new ArrayList<>();
			catalogos = getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.MARCOJURIDICOINTERNACIONAL.getCodigo());
			String tabla = "<table width='100%' style='margin-left: 3em;border-style:none;border-collapse: collapse;font-size:11px;font-family: sans-serif;'>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
				for (Catalogs catalogs : catalogos) {
					if(catalogs.getCataId().equals(valores.getCodigoPoliticaLey()) && valores.getCodigoPoliticaLey()!=null){
						tabla += "  <tr>\r\n" + " <td bgcolor='#FFFFFF' width='100%'>" + valores.getPoliticaLey()+ "</td></tr>\r\n";
					}
				}
			}
			
			tabla += "</table>\r\n";
			dtoResumen.setTablaJuridicoInternacional(tabla);
			
			tabla="";
			catalogos= new ArrayList<>();
			catalogos = getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.MARCOJURIDICONACIONAL.getCodigo());
			tabla = "<table width='100%' style='margin-left: 3em;border-style:none;border-collapse: collapse;font-size:11px;font-family: sans-serif;'>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
				for (Catalogs catalogs : catalogos) {
					if(catalogs.getCataId().equals(valores.getCodigoPoliticaLey()) && valores.getCodigoPoliticaLey()!=null){
						tabla += "  <tr>\r\n" + " <td bgcolor='#FFFFFF' width='100%'>" + valores.getPoliticaLey()+ "</td></tr>\r\n";
					}
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setTablaJuridicoNacional(tabla);
			
			tabla="";
			catalogos= new ArrayList<>();
			catalogos = getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.NORMATIVASECUNDARIANACIONAL.getCodigo());
			tabla = "<table width='100%' style='margin-left: 3em;border-style:none;border-collapse: collapse;font-size:11px;font-family: sans-serif;'>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
				for (Catalogs catalogs : catalogos) {
					if(catalogs.getCataId().equals(valores.getCodigoPoliticaLey()) && valores.getCodigoPoliticaLey()!=null){
						tabla += "  <tr>\r\n" + " <td bgcolor='#FFFFFF' width='100%'>" + valores.getPoliticaLey()+ "</td></tr>\r\n";
					}
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setTablaNormativaNacional(tabla);
			
			tabla="";
			catalogos= new ArrayList<>();
			catalogos = getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.POLITICAS.getCodigo());
			tabla = "<table width='100%' style='margin-left: 3em;border-style:none;border-collapse: collapse;font-size:11px;font-family: sans-serif;'>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
				for (Catalogs catalogs : catalogos) {
					if(catalogs.getCataId().equals(valores.getCodigoPoliticaLey()) && valores.getCodigoPoliticaLey()!=null){
						tabla += "  <tr>\r\n" + " <td bgcolor='#FFFFFF' width='100%'>" + valores.getPoliticaLey()+ "</td></tr>\r\n";
					}
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setTablaPoliticaPrograma(tabla);

			tabla="";
			catalogos= new ArrayList<>();
			catalogos = getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.POLITICAS.getCodigo());
			tabla = "<table width='100%' style='margin-left: 3em;border-style:none;border-collapse: collapse;font-size:11px;font-family: sans-serif;'>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
				for (Catalogs catalogs : catalogos) {
					if(catalogs.getCataId().equals(valores.getCodigoPoliticaLey()) && valores.getCodigoPoliticaLey()!=null){
						tabla += "  <tr>\r\n" + " <td bgcolor='#FFFFFF' width='100%'>" + valores.getPoliticaLey()+ "</td></tr>\r\n";
					}
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setTablaPoliticaPrograma(tabla);
			
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='80%' > Programa/Proyecto del Gobierno Nacional</td> <td class='tablaborder' bgcolor='#FFFFFF' width='20%'> Presupuesto asignado</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
					if(preguntasActivas.get(2).getQuesId().equals(valores.getCodigoPregunta())){
						String catalogo="";
						if(valores.getCatalogo1()!=null && valores.getCatalogo1().length()>0)
							catalogo = valores.getCatalogo1();
						else
							catalogo = valores.getOtrocatalogo();
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='80%'>" + catalogo+ " <td class='tablaborder' bgcolor='#FFFFFF' width='20%'>" + valores.getDecimal1()+ "</td></tr>\r\n";
					}
				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla3A(tabla);
			
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : listaA) {
					if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
					}
				
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalA(tabla);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Llena datos en la tabla B
	 * @param dtoResumen Objeto que recepta los valores de las tablas
	 * @param preguntasActivas Lista con las preguntas de la salvaguarda
	 */
	public void llenarTablasPdfB(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaB(getBuscaProyectosBean().getAdvanceExecution().getAdexId());
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "B");
			
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='8%' >Modalidad</td> <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Autoidentificación étnica</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='5%'>Nro hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='5%'>Nro mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
						String nacionalidad="";
						if(valores.getNacionalidad() != null)
							nacionalidad =valores.getNacionalidad();
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getCatalogo1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getFecha()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + nacionalidad  + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='5%'>" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='5%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='width:15%;'> <a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
					}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo4B(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo4B().equals("SI"))
				dtoResumen.setTabla41B(tabla);
			else
				dtoResumen.setTabla41B("");
						
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Institución</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Actividades de coordinación</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Link verificador del convenio</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta()))
//						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='width:20%;'> " + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='width:20%;'> <a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo5B(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo5B().equals("SI"))
				dtoResumen.setTabla51B(tabla);
			else
				dtoResumen.setTabla51B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Institución</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Actividad que realiza la comunidad</td><td bgcolor='#FFFFFF' >Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(5).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo6B(listaSiNo.get(2).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo6B().equals("SI"))
				dtoResumen.setTabla61B(tabla);
			else
				dtoResumen.setTabla61B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Instrumento</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Relevancia o importancia del instrumento</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Mecanismo de Institucionalización</td><td class='tablaborder' bgcolor='#FFFFFF' style='width:100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(8).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' style='word-break: break-all;'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='word-break: break-all;'><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo7B(listaSiNo.get(3).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo7B().equals("SI"))
				dtoResumen.setTabla71B(tabla);
			else
				dtoResumen.setTabla71B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Actividad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Hectáreas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(10).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal1()+ "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo8B(listaSiNo.get(4).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo8B().equals("SI"))
				dtoResumen.setTabla81B(tabla);
			else
				dtoResumen.setTabla81B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='20%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Comunidad</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(11).getQuesId().equals(valores.getCodigoPregunta())){
						String nacionalidad="";
						if(valores.getNacionalidad() != null)
							nacionalidad =valores.getNacionalidad();
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td></tr>\r\n";
					}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo9B(listaSiNo.get(5).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo9B().equals("NO"))
				dtoResumen.setTabla9B(tabla);
			else
				dtoResumen.setTabla9B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Hectáreas</td><td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Estado</td><td class='tablaborder' bgcolor='#FFFFFF' width='6%'>Nro Hombres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='6%'>Nro Mujeres beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='15%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(13).getQuesId().equals(valores.getCodigoPregunta())){
						String nacionalidad="";
						if(valores.getNacionalidad() != null)
							nacionalidad =valores.getNacionalidad();
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
					}
			}
//			tabla += "</table>\r\n";
//			dtoResumen.setSiNo10B(listaSiNo.get(6).isVaanYesnoAnswerValue()?"SI":"NO");
//			dtoResumen.setTabla101B(tabla);
//			tabla="";
//			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
//			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cómo funciona</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Casos que se han reportado durante el período de reporte</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Casos que se han atendido durante el período de reporte</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
//			for (DtoRespuestasSalvaguardas valores : lista) {
//					if(preguntasActivas.get(16).getQuesId().equals(valores.getCodigoPregunta()))												
//						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoUno()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoDos()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";					
//			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo11B(listaSiNo.get(6).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo11B().equals("SI"))
				dtoResumen.setTabla111B(tabla);
			else
				dtoResumen.setTabla111B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nombre de la organización Beneficiaria</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Acciones implementadas</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Conformación de la organización</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nro hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nro mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(16).getQuesId().equals(valores.getCodigoPregunta())){
						String nacionalidad="";
						if(valores.getNacionalidad() != null)
							nacionalidad =valores.getNacionalidad();
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
					}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo12B(listaSiNo.get(7).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo12B().equals("SI"))
				dtoResumen.setTabla121B(tabla);
			else
				dtoResumen.setTabla121B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Actividad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Temática tratada</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro de personas que accedena la info</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(18).getQuesId().equals(valores.getCodigoPregunta()))												
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoUno()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo13B(listaSiNo.get(8).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo13B().equals("SI"))
				dtoResumen.setTabla131B(tabla);
			else
				dtoResumen.setTabla131B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Número de personas</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>¿Que información se comunica a los beneficiarios?</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>¿Como se informa a la gente sobre la ejecución del proyecto/programa?</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Provincia</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(20).getQuesId().equals(valores.getCodigoPregunta()))												
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoUno()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";					
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo14B(listaSiNo.get(9).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo14B().equals("SI"))
				dtoResumen.setTabla141B(tabla);
			else
				dtoResumen.setTabla141B("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(21).getQuesId().equals(valores.getCodigoPregunta())){
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
					}
				
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalB(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Llena informacion de la salvaguarda C
	 * @param dtoResumen
	 * @param preguntasActivas
	 */
	public void llenarTablasPdfC(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaC(getBuscaProyectosBean().getAdvanceExecution().getAdexId());	
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "C");
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro Hombres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro Mujeres beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Hectareas</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Presupuesto asignado a la actividad</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Tipo de acceso</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getCatalogo1()+ "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo20C(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo20C().equals("SI"))
				dtoResumen.setTabla201C(tabla);
			else	
				dtoResumen.setTabla201C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Práctica o Saber ancestral</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>¿Cómo se reconocieron estas prácticas?</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>¿Cómo se han promovido estas prácticas?</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto4()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto5() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto5(),valores.getTexto5().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo21C(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo21C().equals("SI"))
				dtoResumen.setTabla211C(tabla);
			else
				dtoResumen.setTabla211C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Fecha</td> <td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Organizaciones que participan</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Num hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Num mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Temática</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(5).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo24C(listaSiNo.get(2).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo21C().equals("SI"))
				dtoResumen.setTabla241C(tabla);
			else
				dtoResumen.setTabla241C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='50%' >Medidas que se han tomado para motivar la participación de mujeres</td> <td class='tablaborder' bgcolor='#FFFFFF' width='50%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(6).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			if(dtoResumen.getSiNo21C().equals("SI"))
				dtoResumen.setTabla242C(tabla);
			else
				dtoResumen.setTabla242C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='100px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Representantes (nombres completos)</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Cargo</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(8).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF'><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";			
			dtoResumen.setSiNo25C(listaSiNo.get(3).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo25C().equals("SI"))
				dtoResumen.setTabla26C(tabla);
			else
				dtoResumen.setTabla26C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Objeto del convenio</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Hectáreas bajo actividad REDD+</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(10).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";			
			dtoResumen.setSiNo27C(listaSiNo.get(4).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo27C().equals("SI"))
				dtoResumen.setTabla271C(tabla);
			else
				dtoResumen.setTabla271C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Etnia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Objeto de contratación</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Figura de contratación</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Presupuesto</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cómo se están promoviendo y fomentando el derecho laboral en pueblos indígenas a través del PDI, programa y/ proyecto</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Componente</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(11).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					String objeto="";
					if (valores.getTexto2().equals("PERSONALPLANTA"))
						objeto= "PERSONAL PLANTA";
					else
						objeto= "PERSONAL CONSULTORIA";	
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getPueblo()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + objeto+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getComponente()+ "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			if(dtoResumen.getSiNo27C().equals("SI"))
				dtoResumen.setTabla28C(tabla);
			else
				dtoResumen.setTabla28C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Resultado</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Link documento respaldo</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Objeto del acuerdo</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Componente</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(13).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					String resultado="";
					if(valores.getSinCatalogo()!=null && valores.getSinCatalogo()==1)
						resultado="Se firmó acuerdo de consentimiento";
					else
						resultado="No se firmó acuerdo de consentimiento";
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" +  valores.getPueblo() + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + resultado+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10' nowrap><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getComponente()+ "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo29C(listaSiNo.get(5).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo29C().equals("SI"))
				dtoResumen.setTabla291C(tabla);
			else
				dtoResumen.setTabla291C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Acciones reportadas que causan daño</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Medidas de remediación</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Medidas de compensación</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link de la remediación</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link de la compensación</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(15).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" +  valores.getTexto1() + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto4()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10' nowrap><a href=" + valores.getTexto5() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto5(),valores.getTexto5().length()) + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'><a href=" + valores.getTexto6() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto6(),valores.getTexto6().length()) + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getComponente()+ "</td></tr>\r\n";
				
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo30C(listaSiNo.get(6).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo30C().equals("SI"))
				dtoResumen.setTabla301C(tabla);
			else
				dtoResumen.setTabla301C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Asistentes hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Asistentes mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador participantes</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(17).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" +  valores.getPueblo() + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='font-size:9px;'><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
				}
				
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo31C(listaSiNo.get(7).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo31C().equals("SI"))
				dtoResumen.setTabla311C(tabla);
			else
				dtoResumen.setTabla311C("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(18).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalC(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Llena informacion de la salvaguarda D
	 * @param dtoResumen
	 * @param preguntasActivas
	 */
	public void llenarTablasPdfD(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaD(getBuscaProyectosBean().getAdvanceExecution().getAdexId());	
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "D");
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Espacio de difusión de la información</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Actores que participan en el proceso de acceso a la información</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Asistentes hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Asistentes mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo32D(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo32D().equals("SI"))
				dtoResumen.setTabla321D(tabla);
			else
				dtoResumen.setTabla321D("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Actor / Organización</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Actividad que realiza la comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Presupuesto asignado a la actividad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nivel de involucramiento</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo33D(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo33D().equals("SI"))
				dtoResumen.setTabla331D(tabla);
			else
				dtoResumen.setTabla331D("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(4).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalD(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Llena las preguntas de la salvaguardas E 
	 * @param dtoResumen
	 * @param preguntasActivas
	 */
	public void llenarTablasPdfE(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{
			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaE(getBuscaProyectosBean().getAdvanceExecution().getAdexId());	
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "E");
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Actores involucrados clave</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Hectáreas</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Tipo de área consolidada</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
					String tipo="";
					if(valores.getSinCatalogo()==1)
						tipo="Conservación";
					else if(valores.getSinCatalogo()==2)
						tipo="Restauración";
					else if(valores.getSinCatalogo()==3)
						tipo="Producción Sostenible";
					else if(valores.getSinCatalogo()==4)
						tipo="Conectividad de Areas";
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + tipo+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo34E(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo34E().equals("SI"))
				dtoResumen.setTabla341E(tabla);
			else
				dtoResumen.setTabla341E("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Nivel</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Herramienta (PDOT, ACUS, Ordenanza, etc)</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
					String nivel="";
					if(valores.getTexto1().equals("nivProv"))
						nivel="Provincial";
					else if(valores.getTexto1().equals("nivCant"))
						nivel="Cantonal";
					else if(valores.getTexto1().equals("nivParr"))
						nivel="Parroquial";
					else if(valores.getTexto1().equals("nivOrgIn"))
						nivel="Organización indigena";
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + nivel + "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='font-size:9px;'><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
			
				}
			}	
			tabla += "</table>\r\n";
			dtoResumen.setSiNo35E(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo35E().equals("SI"))
				dtoResumen.setTabla351E(tabla);
			else
				dtoResumen.setTabla351E("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Actores clave</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Hectareas</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(5).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getDecimal1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo36E(listaSiNo.get(2).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo36E().equals("SI"))
				dtoResumen.setTabla361E(tabla);
			else
				dtoResumen.setTabla361E("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Temas</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Método</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Público</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(7).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' style='font-size:9px;'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo37E(listaSiNo.get(3).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo37E().equals("SI"))
				dtoResumen.setTabla371E(tabla);
			else
				dtoResumen.setTabla371E("");
			ValueAnswers respuestas = listaSiNo.get(4);
			dtoResumen.setRespuesta372E(respuestas.getVaanTextAnswerValue());
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Servicio</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(10).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo38E(listaSiNo.get(5).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo38E().equals("SI"))
				dtoResumen.setTabla381E(tabla);
			else
				dtoResumen.setTabla381E("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Recurso</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Periodicidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(12).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo39E(listaSiNo.get(6).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo39E().equals("SI"))
				dtoResumen.setTabla391E(tabla);
			else
				dtoResumen.setTabla391E("");	
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nacionalidad</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(14).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + nacionalidad + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla401E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(15).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla402E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(16).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla403E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Espacio de gobernanza</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(17).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla404E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(18).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla405E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Riesgo abordado</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(19).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla406E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Capacidades fortalecidas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(20).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla407E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(21).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla408E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(22).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla409E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Productos incentivados</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(23).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4010E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Organización fortalecida</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(24).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4011E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Asociación apoyada</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(25).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4012E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>% Incremento ingresos</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(26).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4013E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(27).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4014E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(28).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4015E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(29).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4016E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(30).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4017E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Fuentes de ingreso apoyadas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(31).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4018E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Comunidad</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(32).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4019E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Capacidades fortalecidas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(33).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4020E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Iniciativa turismo apoyada</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(34).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4021E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Instrumento/mecanismo que mejora la gobernanza forestal</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(35).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4022E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nombre de las Organizaciones Apoyadas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(36).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4023E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nombre de las Organizaciones Apoyadas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(37).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4024E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(38).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4025E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(39).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4026E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(40).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4027E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Especies protegidas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(41).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4028E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Especies protegidas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(42).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4029E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(43).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4030E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Usos alternativos</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Servicios valorados</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(44).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto2()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4031E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nombre de las Organizaciones Apoyadas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(45).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getTexto1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4032E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(46).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4033E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(47).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4034E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(48).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4035E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(49).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4036E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(50).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4037E(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro Hectareas</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(51).getQuesId().equals(valores.getCodigoPregunta()))					
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroHombres()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getDecimal1()+ "</td></tr>\r\n";				
			}
			tabla += "</table>\r\n";
			dtoResumen.setTabla4038E(tabla);
			
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(52).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalE(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void llenarTablasPdfF(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaF(getBuscaProyectosBean().getAdvanceExecution().getAdexId());	
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "F");
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Riesgo</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo41F(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo41F().equals("SI"))
				dtoResumen.setTabla411F(tabla);
			else
				dtoResumen.setTabla411F("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Riesgo asociado</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Medida tomada</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo42F(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo42F().equals("SI"))
				dtoResumen.setTabla421F(tabla);
			else
				dtoResumen.setTabla421F("");

			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Riesgo</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Actividad para mitigar</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(7).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo44F(listaSiNo.get(3).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo44F().equals("SI"))
				dtoResumen.setTabla441F(tabla);
			else
				dtoResumen.setTabla441F("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Sistemas</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Nro. Casos reportados</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nro. Casos atendidos</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(9).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoUno()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getGenericoDos()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo45F(listaSiNo.get(4).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo45F().equals("SI"))
				dtoResumen.setTabla451F(tabla);
			else
				dtoResumen.setTabla451F("");
			
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(10).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalF(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	public void llenarTablasPdfG(DtoResumenSalvaguarda dtoResumen,List<Questions> preguntasActivas){
		try{			
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenSalvaguardaG(getBuscaProyectosBean().getAdvanceExecution().getAdexId());	
			List<ValueAnswers> listaSiNo = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguardaCode(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "G");
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Responsable del estudio</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Autores de la investigación</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Riesgo Principal Identificado</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Nombre del estudio</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo46G(listaSiNo.get(0).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo46G().equals("SI"))
				dtoResumen.setTabla461G(tabla);
			else
				dtoResumen.setTabla461G("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='8%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='8%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Actividad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nro hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nro mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(3).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF'>" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto2() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto2(),valores.getTexto2().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo47G(listaSiNo.get(1).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo47G().equals("SI"))
				dtoResumen.setTabla471G(tabla);
			else
				dtoResumen.setTabla471G("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Institución acompaña</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Actividad ilícita reportada</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Resultado</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Fecha</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(6).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getFecha()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo48G(listaSiNo.get(2).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo48G().equals("SI"))
				dtoResumen.setTabla481G(tabla);
			else
				dtoResumen.setTabla481G("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Organización Beneficiaria</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Tipo de incentivo</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro Hombres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro Mujeres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Support value chain</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(8).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCatalogo1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo49G(listaSiNo.get(3).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo49G().equals("SI"))
				dtoResumen.setTabla491G(tabla);
			else
				dtoResumen.setTabla491G("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Actividad</td> <td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Presupuesto</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Resultado</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Componente</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(10).getQuesId().equals(valores.getCodigoPregunta()))
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getDecimal1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getComponente()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo50G(listaSiNo.get(4).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo50G().equals("SI"))
				dtoResumen.setTabla501G(tabla);
			else
				dtoResumen.setTabla501G("");
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='10%'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nro hombres</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Nro mujeres</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Monitoreo remoto</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Monitoreo in situ</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Periodicidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>Componente</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(12).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='10%'>" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getTexto4()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' width='7%'>" + valores.getComponente()+ "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			dtoResumen.setSiNo51G(listaSiNo.get(5).isVaanYesnoAnswerValue()?"SI":"NO");
			if(dtoResumen.getSiNo51G().equals("SI"))
				dtoResumen.setTabla511G(tabla);
			else
				dtoResumen.setTabla511G("");
//			ValueAnswers respuesta = getValueAnswersFacade().buscarPorAvanceEjecucionYSalvaguarda(getBuscaProyectosBean().getAdvanceExecution().getAdexId(), "G", preguntasActivas.get(14).getQuesId());
			ValueAnswers respuesta =listaSiNo.get(7);
			dtoResumen.setSiNo512G(listaSiNo.get(6).isVaanYesnoAnswerValue()?"SI":"NO");
			dtoResumen.setRespuesta513G(respuesta.getVaanTextAnswerValue());
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%' >Actividad que aporta a la salvaguarda</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Logro alcanzado que se reporta</td><td class='tablaborder' bgcolor='#FFFFFF' width='30%'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
					if(preguntasActivas.get(15).getQuesId().equals(valores.getCodigoPregunta()))
						tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='40%'>" + valores.getTexto1()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'>" + valores.getTexto2()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' width='30%'><a href=" + valores.getTexto3() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto3(),valores.getTexto3().length()) + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			dtoResumen.setInfoAdicionalG(tabla);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	public AdvanceSummary ubicaAvaneResumen(String salvaguarda, List<AdvanceSummary> lista){
		AdvanceSummary avance = new AdvanceSummary();
		for(AdvanceSummary av: lista){
			if (av.getSafeguards().getSafeCode().equals(salvaguarda)){
				avance = av;
				break;
			}
		}
		return avance;
	}

	public void generarResumenGeneroPdf(){
		try{
//			List<AdvanceSummary> listaAvances = new ArrayList<>();
			List<ValueAnswers> listaValoresRespuestas= new ArrayList<>();
			listaValoresRespuestas = getValueAnswersFacade().buscarPorAvanceEjecucionGenero(getBuscaProyectosBean().getAdvanceExecution().getAdexId());
	
			String htmlReporte = GenerarPdfResumen.REPORTE_RESUMEN_ENCABEZADO_GENERO;
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			String periodo="";
			List<Questions> preguntasActivas = getQuestionsFacade().buscaPreguntasGenero();			
//			listaAvances = getAdvanceSummaryFacade().listaAvancesPorAvanceEjecucion(getBuscaProyectosBean().getAdvanceExecution().getAdexId());			
			DtoResumenGenero dto = new DtoResumenGenero();
			dto.setLogoEscudo("escudoE2023.png");
			dto.setLogoMae("mae.png");
			dto.setLogoPieIzquierda("maate_2023_izquierda.jpeg");
			dto.setLogoPieDerecha("maate_2023_derecha.jpeg");
			dto.setFecha(date);
			dto.setProyecto(getBuscaProyectosBean().getAdvanceExecution().getProjects().getProjTitle());
			dto.setSocioImplementador(getBuscaProyectosBean().getAdvanceExecution().getProjects().getPartId().getPartName());
			periodo = getBuscaProyectosBean().getAdvanceExecution().getAdexTermFrom().substring(0, 4).concat(" Enero - Diciembre");
			dto.setPeriodo(periodo);
			if(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners()!=null)
				dto.setSocioEstrategico(getBuscaProyectosBean().getAdvanceExecution().getProjectsStrategicPartners().getPartId().getPartName());
			llenaTablaIndicadoresGenero(dto,preguntasActivas,listaValoresRespuestas);
			htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_GENERO_CONTENIDO;
			htmlReporte += GenerarPdfResumen.REPORTE_RESUMEN_PIE;			
			String html = getGeneradorPdfHtml().procesar(htmlReporte, dto);
			byte[] array = getGeneradorPdfHtml().crearDocumentoPdf(html,2);
			descargarReporte("application/pdf", "resumenGenero" , array);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "generarResumenGeneroPdf" + ": ").append(e.getMessage()));			
		}
	}
	
	public void llenaTablaIndicadoresGenero(DtoResumenGenero genero,List<Questions> preguntasActivas,List<ValueAnswers> respuestasSiNo){
		try{
			List<DtoRespuestasSalvaguardas> lista = getTableResponsesFacade().resumenPreguntasGenero(getBuscaProyectosBean().getAdvanceExecution().getAdexId());
			List<DtoResumenGenero> listaDatos = getAdvanceExecutionProjectGenderFacade().listaResumenAvanceGenero(getBuscaProyectosBean().getAdvanceExecution().getProjects().getProjId(), getBuscaProyectosBean().getAdvanceExecution().getAdexId());
			String tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:11px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='65px;' >Tema</td> <td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Línea de acción</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Indicador</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Meta</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Valor alcanzado</td><td class='tablaborder' bgcolor='#FFFFFF' width='65px;'>Acciones implementadas</td></tr>\r\n";
			for (DtoResumenGenero valores : listaDatos) {
				StringBuilder valorMeta = new StringBuilder();
				StringBuilder valorAlcanzado = new StringBuilder();	
				String acciones ="";
				if(valores.acciones==null)
					acciones="";
				else
					acciones = valores.acciones;
				if(valores.tipoIndicador.equals("G") && (Integer.valueOf(valores.codigoIndicador)==9 || Integer.valueOf(valores.codigoIndicador) == 10)  ){
					valorMeta.append("<br/>").append("Hombres: ").append(valores.valorMetaUno).append(", ").append("Mujeres: ").append(valores.valorMetaDos);
				}else if(valores.tipoIndicador.equals("G")){
					valorMeta.append("<br/>").append("Nro hombres: ").append(valores.valorMetaUno).append(", ").append("Nro mujeres: ").append(valores.valorMetaDos);
				}else if(valores.tipoIndicador.equals("B")){
					valorMeta.append("<br/>").append("Valor: ").append(Integer.valueOf(valores.valorMetaUno)==1?"Si":"No");
				}else if(valores.tipoIndicador.equals("N")){
					valorMeta.append("<br/>").append("Valor: ").append(valores.valorMetaUno);
				}
				
				if(valores.tipoIndicador.equals("G") && (Integer.valueOf(valores.codigoIndicador)==9 || Integer.valueOf(valores.codigoIndicador) == 10)  ){
					valorAlcanzado.append("<br/>").append("Hombres: ").append(valores.getValorAlcanzadoUno()!=null?valores.getValorAlcanzadoUno():0).append(", ").append("Mujeres: ").append(valores.valorAlcanzadoDos!=null?valores.getValorAlcanzadoDos():0);
				}else if(valores.tipoIndicador.equals("G")){
					valorAlcanzado.append("<br/>").append("Nro hombres: ").append(valores.getValorAlcanzadoUno()!=null?valores.getValorAlcanzadoUno():0).append(", ").append("Nro mujeres: ").append(valores.valorAlcanzadoDos!=null?valores.getValorAlcanzadoDos():0);
				}else if(valores.tipoIndicador.equals("B")){
					String valAlcanzado="";
					if(valores.valorAlcanzadoUno!=null){
						if(valores.valorAlcanzadoUno.equals("1"))
							valAlcanzado="Si";
						else
							valAlcanzado="No";
					}else{
						valAlcanzado="No";
					}
					valorAlcanzado.append("<br/>").append("Valor: ").append(valAlcanzado);
				}else if(valores.tipoIndicador.equals("N")){
					valorAlcanzado.append("<br/>").append("Valor: ").append(valores.valorAlcanzadoUno!=null?valores.valorAlcanzadoUno:0);
				}
				tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.tema+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.linea+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.indicador+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.meta.concat(valorMeta.toString())  + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valorAlcanzado.toString() + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + acciones + "</td></tr>\r\n";
			}
			tabla += "</table>\r\n";
			genero.setTablaTemas(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:10px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Espacio identificado</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Tipo de organización</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Fin/rol de la organización </td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Acciones implementadas para fortalecer a la organización</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nro hombres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nro mujeres beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(0).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto4()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto5()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto6() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto6(),valores.getTexto6().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			genero.setTablaUno(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:10px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nombre de la lideresa identificada </td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Espacio de Diálogo/Participación </td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Rol que cumple en el espacio de diálogo/participación</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Acciones de fortalecimiento de la lideresa en el espacio de diálogo/participación</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(1).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto4()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto5()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto6() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto6(),valores.getTexto6().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			genero.setTablaDos(tabla);
			tabla="";
			tabla = "<table class='tablaborder' width='100%' style='margin-left: 3em;font-size:10px;font-family: sans-serif;table-layout: fixed;'>\r\n";
			tabla += "<tr class='titulotabla'>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' width='60px;' >Provincia</td> <td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Cantón</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Parroquia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Etnia</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nacionalidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Comunidad</td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Acción </td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Resultado </td><td class='tablaborder' bgcolor='#FFFFFF' width='60px;'>Nro Mujeres beneficiarias</td><td class='tablaborder' bgcolor='#FFFFFF' width='40px;'>Nro Hombres beneficiarios</td><td class='tablaborder' bgcolor='#FFFFFF' width='100px;'>Link verificador</td></tr>\r\n";
			for (DtoRespuestasSalvaguardas valores : lista) {
				if(preguntasActivas.get(2).getQuesId().equals(valores.getCodigoPregunta())){
					String nacionalidad="";
					if(valores.getNacionalidad() != null)
						nacionalidad =valores.getNacionalidad();
					tabla += "  <tr>\r\n" + " <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getProvincia()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getCanton()+ "</td> <td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getParroquia()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getPueblo()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + nacionalidad + "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto1()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto2()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getTexto3()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroMujeres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' >" + valores.getNumeroHombres()+ "</td><td class='tablaborder' bgcolor='#FFFFFF' ><a href=" + valores.getTexto4() + " target='_blank'>" + UtilsCadenas.romperCadena(valores.getTexto4(),valores.getTexto4().length()) + "</td></tr>\r\n";
				}
			}
			tabla += "</table>\r\n";
			genero.setTablaTres(tabla);
			genero.setPreguntaUno(preguntasActivas.get(0).getQuesContentQuestion());
			genero.setSiNo1(respuestasSiNo.get(0).isVaanYesnoAnswerValue()==false?"NO":"");
			genero.setPreguntaDos(preguntasActivas.get(1).getQuesContentQuestion());
			genero.setSiNo2(respuestasSiNo.get(1).isVaanYesnoAnswerValue()==false?"NO":"");
			genero.setPreguntaTres(preguntasActivas.get(2).getQuesContentQuestion());
			genero.setSiNo3(respuestasSiNo.get(2).isVaanYesnoAnswerValue()==false?"NO":"");
		}catch(Exception e){
//			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "llenaTablaIndicadoresGenero" + ": ").append(e.getMessage()));
			e.printStackTrace();
		}
	}
		
	/**
	 * Valida si una pregunta de asignacion salvaguardas ya esta reportada
	 * @param codigoPregunta
	 * @return
	 */
	public boolean desactivaPregunta(Integer codigoPregunta){
		boolean respuesta = false;
		int salvaguardasAsignadas=0;
		ProjectQuestions pregunta=new ProjectQuestions();
		String periodo="";
		try{
			pregunta = getProjectQuestionsFacade().buscaPreguntAsignada(getBuscaProyectosBean().getCodigoStrategicPartner(), getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), getBuscaProyectosBean().getAnioReporte() , codigoPregunta);
			if(pregunta!=null){
				periodo= String.valueOf(pregunta.getPrquYear()).concat("-01");
				salvaguardasAsignadas= getTableResponsesFacade().salvaguardasReportadas(pregunta.getSafeguards().getSafeId(), getBuscaProyectosBean().getProyectoSeleccionado().getProjId(), getBuscaProyectosBean().getCodigoStrategicPartner(), periodo);
				if(salvaguardasAsignadas>0)
					respuesta = true;
			}else
				respuesta = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}
	/**
	 * Limpia el contenido de otra linea
	 */
	public void limpiaOtraLineaAccion(){		
		getBuscaProyectosBean().getProjectGenderInfoSeleccionado().setPginOtherLine(null);
	}
	
	public void cancelarAsignacionGenero(){
		getBuscaProyectosBean().setAsignacionGenero(false);	
				
		getBuscaProyectosBean().setDatosProyecto(false);
		getBuscaProyectosBean().setProyectoTienePlanGenero(false);
		
		getBuscaProyectosBean().setTipoSocio(1);
		getBuscaProyectosBean().setCodigoStrategicPartner(null);

	}
	
	public void limpiarFiltro(){
		buscarProyectos();
		getBuscaProyectosBean().setCodigoBusquedaProyecto(null);
		getBuscaProyectosBean().setTituloProyecto("");
		getBuscaProyectosBean().setCodigoBusquedaProyecto(null);
	}
}
