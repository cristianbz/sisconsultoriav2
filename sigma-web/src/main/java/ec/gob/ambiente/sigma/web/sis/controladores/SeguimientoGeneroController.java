/**
@autor proamazonia [Christian Báez]  3 ago. 2021

 **/
package ec.gob.ambiente.sigma.web.sis.controladores;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionProjectGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionSafeguards;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Catalogs;
import ec.gob.ambiente.sigma.ejb.sis.entidades.DetailAdvanceGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectGenderIndicator;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.TableResponses;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ValueAnswers;
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsSpecificObjectiveFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsStrategicPartnerFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceExecutionProjectGenderFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceExecutionSafeguardsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.DetailAdvanceGenderFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectGenderIndicatorFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectsGenderInfoFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.QuestionsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.TableResponsesFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ValueAnswersFacade;
import ec.gob.ambiente.sigma.web.sis.beans.AplicacionBean;
import ec.gob.ambiente.sigma.web.security.LoginBean;
import ec.gob.ambiente.sigma.web.sis.beans.SeguimientoGeneroBean;
import ec.gob.ambiente.sigma.web.sis.utils.Mensaje;
import ec.gob.ambiente.sigma.web.sis.utils.OperacionesCatalogo;
import ec.gob.ambiente.sigma.web.sis.utils.ResumenPDF;
import ec.gob.ambiente.sigma.web.sis.utils.UtilsCadenas;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoEstadoReporteEnum;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoRespuestaEnum;
import lombok.Getter;
import lombok.Setter;

@Named()
@ViewScoped
public class SeguimientoGeneroController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SeguimientoGeneroController.class);

	private static final int CODIGO_IDENTIFICACION_INDIGENA = 54;
//	int CODIGO_IDENTIFICACION_INDIGENA =0;
	@Getter
	@Setter
	@Inject
	private MensajesController mensajesController;

	@Inject
	@Getter
	private ComponenteBuscaProyectos componenteBuscarProyectos;

	@Inject
	@Getter
	private SeguimientoGeneroBean seguimientoGeneroBean;

	@Inject
	@Getter
	private LoginBean loginBean;

	@EJB
	@Getter
	private DetailAdvanceGenderFacade detailAdvanceGenderFacade;

	@EJB
	@Getter
	private TableResponsesFacade tableResponsesFacade;

	@EJB
	@Getter
	private ValueAnswersFacade valueAnswersFacade;
	
	@EJB
	@Getter
	private ProjectGenderIndicatorFacade projectGenderIndicatorFacade;
	
	@EJB
	@Getter
	private ProjectsSpecificObjectiveFacade projectsSpecificObjectivesFacade;

	@Inject
	@Getter
	private AplicacionBean aplicacionBean;

	@Inject
	@Getter
	private AplicacionController aplicacionController;

	@EJB
	@Getter
	private AdvanceExecutionSafeguardsFacade advanceExecutionSafeguardsFacade;

	@EJB
	@Getter
	private AdvanceExecutionProjectGenderFacade  advanceExecutionProjectGenderFacade;

	@EJB
	@Getter
	private ComponentFacade componentsFacade;

	@EJB
	@Getter
	private ProjectsGenderInfoFacade projectsGenderInfoFacade;
	@EJB
	@Getter
	private ProjectsStrategicPartnerFacade projectsStrategicPartnersFacade;

	@EJB
	@Getter
	private QuestionsFacade questionsFacade;

	private String rutaPDF;

	@PostConstruct
	private void init(){
		try{
			getComponenteBuscarProyectos().getBuscaProyectosBean().setMenuAdministrador(false);
			getComponenteBuscarProyectos().setEsReporteGenero(true);
			getComponenteBuscarProyectos().setEsSeguimientoSalvaguardas(false);
			cargaProvincias();
			cargaTipoOrganizacion();
			cargaTipoIncentivo();
			getSeguimientoGeneroBean().setPosicionTab(0);
			
//			CODIGO_IDENTIFICACION_INDIGENA =getAplicacionBean().getCodigoIndigena();
			getComponenteBuscarProyectos().validarRol();
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "init" + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Regresa a la busqueda de proyectos
	 */
	public void volverBuscarProyectos(){
		getComponenteBuscarProyectos().volverABuscarProyectos();
		getSeguimientoGeneroBean().setDatosSeguimiento(false);
		getComponenteBuscarProyectos().getBuscaProyectosBean().setNuevoSeguimiento(false);
		getComponenteBuscarProyectos().getBuscaProyectosBean().setCodigoBusquedaProyecto(null);
	}
	public void calculaPeriodoReporte(){
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
		String[] parts = getComponenteBuscarProyectos().getBuscaProyectosBean().getPeriodoDesde().split("-");
		for (Map.Entry<String, String> entry : periodo.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    if(parts[0].equals(value))
		    	getComponenteBuscarProyectos().getBuscaProyectosBean().setMesDesde(key);
		    else if(parts[1].equals(value))
		    	getComponenteBuscarProyectos().getBuscaProyectosBean().setMesHasta(key);
		}		
	}
	/**
	 * Carga los datos de genero del proyecto seleccionado
	 */
	public void cargaDatosProyectoSeleccionado(){
		try{
			calculaPeriodoReporte();
			getSeguimientoGeneroBean().setProyectoSeleccionado(getComponenteBuscarProyectos().getBuscaProyectosBean().getProyectoSeleccionado());
			if(validaPeriodoReporteProyecto(getSeguimientoGeneroBean().getProyectoSeleccionado(), getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte())){
				getSeguimientoGeneroBean().setSocioImplementador(getComponenteBuscarProyectos().getBuscaProyectosBean().getSocioImplementador());
				getSeguimientoGeneroBean().setMostrarTablaSeguimiento1(false);
				getSeguimientoGeneroBean().setPosicionTab(0);

				getSeguimientoGeneroBean().setCodigoStrategicPartner(getComponenteBuscarProyectos().getBuscaProyectosBean().getCodigoStrategicPartner());			
				getSeguimientoGeneroBean().setListaComponentes(new ArrayList<Component>());
				getSeguimientoGeneroBean().setListaComponentes(getComponentsFacade().listaComponentesActivos(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId()));
				Component componente = new Component();
				componente.setCompId(1000);
				componente.setCompStatus(true);
				componente.setCompCode("CEO5");
				componente.setCompName("Componentes Operativos");
				getSeguimientoGeneroBean().getListaComponentes().add(componente);
				if(getComponenteBuscarProyectos().getBuscaProyectosBean().isNuevoSeguimiento()){
//					AdvanceExecutionSafeguards avance =getAdvanceExecutionSafeguardsFacade().buscarAvanceSalvaguardaGeneroReportado(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId(), getSeguimientoGeneroBean().getCodigoStrategicPartner()==null?0:getSeguimientoGeneroBean().getCodigoStrategicPartner(), 2, String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getPeriodoDesde()), String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-12"));
					AdvanceExecutionSafeguards avance =getAdvanceExecutionSafeguardsFacade().buscarAvanceSalvaguardaGeneroReportado(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId(), getSeguimientoGeneroBean().getCodigoStrategicPartner()==null?0:getSeguimientoGeneroBean().getCodigoStrategicPartner(), 2, String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesDesde()), String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesHasta()));
					if(avance == null){

						getSeguimientoGeneroBean().setAdvanceExecutionSafeguards(getComponenteBuscarProyectos().getBuscaProyectosBean().getAdvanceExecution());	
						List<ProjectGenderIndicator> listaPGI=new ArrayList<>();
						List<AdvanceExecutionProjectGender> listaAvances=new ArrayList<>();
						listaPGI = getProjectGenderIndicatorFacade().listaLineasGeneroProyectoPartner(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId(), getSeguimientoGeneroBean().getCodigoStrategicPartner()==null?0:getSeguimientoGeneroBean().getCodigoStrategicPartner());
						if(listaPGI!=null && listaPGI.size()>0){
							getSeguimientoGeneroBean().setDatosSeguimiento(true);
							for (ProjectGenderIndicator pgi : listaPGI) {
								AdvanceExecutionProjectGender aepg = new AdvanceExecutionProjectGender();
								pgi.getProjectsGenderInfo().setComponentesGenero(armaComponentes(pgi.getProjectsGenderInfo().getPginComponents()));
								aepg.setAdvanceExecutionSafeguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
								aepg.setProjectGenderIndicator(pgi);
								aepg.setAepgStatus(true);
								listaAvances.add(aepg);
							}

							getSeguimientoGeneroBean().setListaPreguntas(getQuestionsFacade().buscaPreguntasGenero());
							getSeguimientoGeneroBean().setListaValoresRespuestas(new ArrayList<ValueAnswers>());
							valoresRespuestasPorDefecto(getSeguimientoGeneroBean().getListaPreguntas(), getSeguimientoGeneroBean().getListaValoresRespuestas());
							getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setValueAnswersList(getSeguimientoGeneroBean().getListaValoresRespuestas());
							getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexTermFrom(String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesDesde()));
							getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexTermTo(String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesHasta()));
							if(getSeguimientoGeneroBean().getCodigoStrategicPartner() != null){
								ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
								psp= getProjectsStrategicPartnersFacade().partnerEstrategico(getSeguimientoGeneroBean().getCodigoStrategicPartner());
								getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setProjectsStrategicPartners(psp);
							}else{
								getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setProjectsStrategicPartners(null);
							}
							getAdvanceExecutionSafeguardsFacade().agregarEditarAvanceEjecucionGenero(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards(), listaAvances);

							getSeguimientoGeneroBean().setListaLineasGenero(getAdvanceExecutionProjectGenderFacade().listaIndicadoresReportados(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()));
							for (AdvanceExecutionProjectGender aepg : getSeguimientoGeneroBean().getListaLineasGenero()) {
								aepg.getProjectGenderIndicator().getProjectsGenderInfo().setComponentesGenero(armaComponentes(aepg.getProjectGenderIndicator().getProjectsGenderInfo().getPginComponents()));
								aepg.setListaReportesAnteriores(new ArrayList<AdvanceExecutionProjectGender>());
								aepg.setListaReportesAnteriores(getAdvanceExecutionProjectGenderFacade().listaIndicadoresReportadosProyecto(aepg.getAdvanceExecutionSafeguards().getProjects().getProjId(), aepg.getProjectGenderIndicator().getIndicators().getIndiId(),aepg.getAdvanceExecutionSafeguards().getAdexTermFrom(),aepg.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCataId()));

							}
							Collections.sort(getSeguimientoGeneroBean().getListaLineasGenero(), new Comparator<AdvanceExecutionProjectGender>(){
								@Override
								public int compare(AdvanceExecutionProjectGender o1, AdvanceExecutionProjectGender o2) {
									return o1.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCatalogTypes().getCatyMnemonic().compareToIgnoreCase(o2.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCatalogTypes().getCatyMnemonic());
								}
							});

							cargaValoresPreguntasRespuestas();
							preparaRespuestasGenero();
							getComponenteBuscarProyectos().getBuscaProyectosBean().setDeshabilitaIniciarReporte(true);
						}else{
							Mensaje.actualizarComponente(":form:growl");
							Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("info.noInfoGenero"));
						}
					}else{
						Mensaje.actualizarComponente(":form:growl");
						Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.periodoReporteExiste"));
					}
				}else{
					AdvanceExecutionSafeguards avance =getAdvanceExecutionSafeguardsFacade().buscarAvanceSalvaguardaGeneroReportado(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId(), getSeguimientoGeneroBean().getCodigoStrategicPartner()==null?0:getSeguimientoGeneroBean().getCodigoStrategicPartner(), 2, String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesDesde()), String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesHasta()));
					getSeguimientoGeneroBean().setAdvanceExecutionSafeguards(avance);
					getSeguimientoGeneroBean().setListaLineasGenero(new ArrayList<AdvanceExecutionProjectGender>());
					List<AdvanceExecutionProjectGender> listaLineas=new ArrayList<>();
					listaLineas=getAdvanceExecutionProjectGenderFacade().listaReportados(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId(), getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId());
					List<ProjectGenderIndicator> listaPGI=new ArrayList<>();				

					listaPGI = getProjectGenderIndicatorFacade().listaLineasGeneroProyectoPartner(getSeguimientoGeneroBean().getProyectoSeleccionado().getProjId(), getSeguimientoGeneroBean().getCodigoStrategicPartner()==null?0:getSeguimientoGeneroBean().getCodigoStrategicPartner());
					for (ProjectGenderIndicator pgi : listaPGI) {
						boolean encontrado=false;
						for(AdvanceExecutionProjectGender aepg:listaLineas){						
							if(pgi.getPgigId() == aepg.getProjectGenderIndicator().getPgigId()){
								pgi.getProjectsGenderInfo().setComponentesGenero(armaComponentes(pgi.getProjectsGenderInfo().getPginComponents()));
								encontrado=true;
								aepg.setProjectGenderIndicator(pgi);
								break;
							}
						}
						if(!encontrado){
							AdvanceExecutionProjectGender aepg = new AdvanceExecutionProjectGender();
							pgi.getProjectsGenderInfo().setComponentesGenero(armaComponentes(pgi.getProjectsGenderInfo().getPginComponents()));
							aepg.setAdvanceExecutionSafeguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
							aepg.setProjectGenderIndicator(pgi);
							aepg.setAepgStatus(true);
							getAdvanceExecutionProjectGenderFacade().agregarEditar(aepg);
						}
					}
					getSeguimientoGeneroBean().setListaLineasGenero(getAdvanceExecutionProjectGenderFacade().listaIndicadoresReportados(avance.getAdexId()));
					for (AdvanceExecutionProjectGender aepg : getSeguimientoGeneroBean().getListaLineasGenero()) {
						aepg.getProjectGenderIndicator().getProjectsGenderInfo().setComponentesGenero(armaComponentes(aepg.getProjectGenderIndicator().getProjectsGenderInfo().getPginComponents()));
						aepg.setListaReportesAnteriores(new ArrayList<AdvanceExecutionProjectGender>());
						aepg.setListaReportesAnteriores(getAdvanceExecutionProjectGenderFacade().listaIndicadoresReportadosProyecto(aepg.getAdvanceExecutionSafeguards().getProjects().getProjId(), aepg.getProjectGenderIndicator().getIndicators().getIndiId(),aepg.getAdvanceExecutionSafeguards().getAdexTermFrom(),aepg.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCataId()));

					}
					Collections.sort(getSeguimientoGeneroBean().getListaLineasGenero(), new Comparator<AdvanceExecutionProjectGender>(){
						@Override
						public int compare(AdvanceExecutionProjectGender o1, AdvanceExecutionProjectGender o2) {
							return o1.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCatalogTypes().getCatyMnemonic().compareToIgnoreCase(o2.getProjectGenderIndicator().getProjectsGenderInfo().getCataId().getCatalogTypes().getCatyMnemonic());
						}
					});
					getSeguimientoGeneroBean().setDatosSeguimiento(true);
					cargaValoresPreguntasRespuestas();
					preparaRespuestasGenero();
					getComponenteBuscarProyectos().getBuscaProyectosBean().setDeshabilitaIniciarReporte(true);
				}
			}else{
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,   "",getMensajesController().getPropiedad("error.fechaPeriodoReporte"));
				Mensaje.actualizarComponente(":form:growl");
			}	
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaDatosProyectoSeleccionado" + ": ").append(e.getMessage()));
			e.printStackTrace();
		}

	}
	public String armaComponentes(String componentes){
		StringBuilder respuesta=new StringBuilder();
		String[] auxcomp = componentes.split(",");
		for(int i=0;i<auxcomp.length;i++){
			respuesta.append(OperacionesCatalogo.ubicaComponentePorCodigo(Integer.valueOf(auxcomp[i]),getSeguimientoGeneroBean().getListaComponentes())).append(",");
		}
		return respuesta.toString();
	}

	/**
	 * Arma los valores de las respuestas por defecto.
	 * @param listaPreguntas
	 * @param listaRespuestas
	 */
	public void valoresRespuestasPorDefecto(List<Questions> listaPreguntas, List<ValueAnswers> listaRespuestas){
		for (Questions  preguntas : listaPreguntas) {
			if(preguntas.getCatalogs().getCataId().equals(TipoRespuestaEnum.CHECKBOX.getCodigo()) || preguntas.getCatalogs().getCataId().equals(TipoRespuestaEnum.RADIOBUTTON.getCodigo())
					|| preguntas.getCatalogs().getCataId().equals(TipoRespuestaEnum.TEXTO.getCodigo())){
				ValueAnswers valoresRespuestas=new ValueAnswers();
				valoresRespuestas.setQuestions(preguntas);
				valoresRespuestas.setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
				valoresRespuestas.setVaanStatus(true);
				valoresRespuestas.setVaanNumericAnswerValue(0);
				valoresRespuestas.setVaanTextAnswerValue("");
				valoresRespuestas.setVaanYesnoAnswerValue(false);
				listaRespuestas.add(valoresRespuestas);
			}
		}
	}

	public void grabarAvanceLineaGenero(){
		try{
			if(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().getProjectGenderIndicator().getIndicators().getIndiType().equals("B"))
				getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().setAepgValueReachedOne(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().isAepgBooleanValue()?1:0);
			getAdvanceExecutionProjectGenderFacade().agregarEditar(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado());
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,   "",getMensajesController().getPropiedad("info.infoGrabada"));
			Mensaje.actualizarComponente(":form:growl");
			Mensaje.ocultarDialogo("dlgRegistroSeguimiento");
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "grabarAvanceLineaGenero " + ": ").append(e.getMessage()));
		}
	}
	public void cargaProvincias(){
		getSeguimientoGeneroBean().setListaProvincias(new ArrayList<GeographicalLocations>());

		for (Object[] objects : getAplicacionController().getAplicacionBean().getListaProvincias()) {
			GeographicalLocations geo=new GeographicalLocations();
			geo.setGeloName(objects[0].toString());
			geo.setGeloId(Integer.valueOf(objects[1].toString()));
			geo.setGeloCodificationInec(objects[2].toString());
			getSeguimientoGeneroBean().getListaProvincias().add(geo);
		}
	}
	public void filtraCantones(){
		getSeguimientoGeneroBean().setListaCantones(new ArrayList<GeographicalLocations>());
		getSeguimientoGeneroBean().setListaParroquias(new ArrayList<GeographicalLocations>());
//		List<Object[]> listaTemporal =getAplicacionBean().getListaTodosCantones().stream().filter(canton->Integer.valueOf(canton[3].toString()).equals(getSeguimientoGeneroBean().getCodProvincia())).collect(Collectors.toList());		
		List<Object[]> listaTemporal = new ArrayList<>();
		for (Object[] obj : getAplicacionBean().getListaTodosCantones()) {
			if(Integer.parseInt(obj[3].toString())==getSeguimientoGeneroBean().getCodProvincia())
				listaTemporal.add(obj);
		}
		
		
		for (Object[] objects : listaTemporal) {
			GeographicalLocations geo=new GeographicalLocations();
			geo.setGeloName(objects[0].toString());
			geo.setGeloId(Integer.valueOf(objects[1].toString()));
			geo.setGeloCodificationInec(objects[2].toString());
			getSeguimientoGeneroBean().getListaCantones().add(geo);
		}
		Collections.sort(getSeguimientoGeneroBean().getListaCantones(), new Comparator<GeographicalLocations>(){
			@Override
			public int compare(GeographicalLocations o1, GeographicalLocations o2) {
				return o1.getGeloName().compareToIgnoreCase(o2.getGeloName());
			}
		});
	}

	public void filtraParroquias(){
		getSeguimientoGeneroBean().setListaParroquias(new ArrayList<GeographicalLocations>());
//		List<Object[]> listaTemporal = getAplicacionBean().getListaTodasParroquias().stream().filter(canton->Integer.valueOf(canton[3].toString()).equals(getSeguimientoGeneroBean().getCodCanton())).collect(Collectors.toList());		
		List<Object[]> listaTemporal = new ArrayList<>();
		for (Object[] obj : getAplicacionBean().getListaTodasParroquias()) {
			if(Integer.parseInt(obj[3].toString())==getSeguimientoGeneroBean().getCodCanton())
				listaTemporal.add(obj);
		}
		for (Object[] objects : listaTemporal) {
			GeographicalLocations geo=new GeographicalLocations();
			geo.setGeloName(objects[0].toString());
			geo.setGeloId(Integer.valueOf(objects[1].toString()));
			geo.setGeloCodificationInec(objects[2].toString());
			getSeguimientoGeneroBean().getListaParroquias().add(geo);
		}
		Collections.sort(getSeguimientoGeneroBean().getListaParroquias(), new Comparator<GeographicalLocations>(){
			@Override
			public int compare(GeographicalLocations o1, GeographicalLocations o2) {
				return o1.getGeloName().compareToIgnoreCase(o2.getGeloName());
			}
		});
	}
	public void habilitaPuebloNacionalidad(){

		if(getSeguimientoGeneroBean().getCodigoAutoIdentificacion() == CODIGO_IDENTIFICACION_INDIGENA)
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(true);
		else{
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(false);
			getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(0);
		}
	}

	/**
	 * Agrega registros al detalle del avance de genero en la tabla 1
	 */
	public void agregaDetalleRegistroGeneroTabla1(){
		try{
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setAdvanceExecutionProjectGender(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado());
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagProvince(getSeguimientoGeneroBean().getCodProvincia());
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagCanton(getSeguimientoGeneroBean().getCodCanton());
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagParish(getSeguimientoGeneroBean().getCodParroquia());
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setProvincia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagProvince(), 1));
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setCanton(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagCanton(), 2));
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setParroquia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagParish(), 3));
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagEthnicity(getSeguimientoGeneroBean().getCodigoAutoIdentificacion());
			getSeguimientoGeneroBean().getDetalleAdvanceGender().setEtnia(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagEthnicity(), getAplicacionBean().getListaAutoIdentificacion()));
			if(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagId()== null){								
				if(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagEthnicity() == CODIGO_IDENTIFICACION_INDIGENA){
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagTown(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setPueblo(ubicaPuebloNacionalidad(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagTown()));
				}else{
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagTown(null);
				}
				getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagStatus(true);
				getDetailAdvanceGenderFacade().agregarEditarRegistroDetalleAvanceGenero(getSeguimientoGeneroBean().getDetalleAdvanceGender());
				getSeguimientoGeneroBean().getListaDetalleAvancesGenero().add(getSeguimientoGeneroBean().getDetalleAdvanceGender());
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
			}else{				
				if(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagEthnicity() == CODIGO_IDENTIFICACION_INDIGENA){
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagTown(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setPueblo(ubicaPuebloNacionalidad(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagTown()));
				}else{
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setDtagTown(null);
					getSeguimientoGeneroBean().getDetalleAdvanceGender().setPueblo("");
				}
				getDetailAdvanceGenderFacade().agregarEditarRegistroDetalleAvanceGenero(getSeguimientoGeneroBean().getDetalleAdvanceGender());
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
			}
			getSeguimientoGeneroBean().setNuevoDetalleGenero(false);
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(false);
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "eliminarRegistroTablaGenero " + ": ").append(e.getMessage()));
		}
	}
	/**
	 * 
	 * @param codigo
	 * @param tipo 1 provincia  2 canton  3 parroquia
	 * @return
	 */
	public String ubicaProvinciaCantonParroquia(int codigo, int tipo){
		String resultado="";
		if(tipo == 1){
			for(Object[] provincia:getAplicacionBean().getListaProvincias()){
				if(Integer.valueOf(provincia[1].toString()) == codigo){
					resultado = provincia[0].toString();
					break;
				}
			}
		}else if(tipo == 2){
			for(Object[] canton:getAplicacionBean().getListaTodosCantones()){
				if(Integer.valueOf(canton[1].toString()) == codigo){
					resultado = canton[0].toString();
					break;
				}
			}
		}else if(tipo == 3){
			for(Object[] parroquia:getAplicacionBean().getListaTodasParroquias()){
				if(Integer.valueOf(parroquia[1].toString()) == codigo){
					resultado = parroquia[0].toString();
					break;
				}
			}
		}
		return resultado;
	}

	public String ubicaPuebloNacionalidad(int codigoPueblo){
		String pueblo="";
		
//		Optional<Catalogs> resultado= getAplicacionController().getAplicacionBean().getListaPueblosNacionalidades().stream().filter((p)->p.getCataId().equals(codigoPueblo)).findFirst();
//		if(resultado.isPresent()){
//			Catalogs catalogo= resultado.get();
//			pueblo=catalogo.getCataText1();
//		}
		for (Catalogs catalogo : getAplicacionController().getAplicacionBean().getListaPueblosNacionalidades()) {
			if(catalogo.getCataId().equals(codigoPueblo))
				pueblo = catalogo.getCataText1();
		}
		return pueblo;
	}

	/**
	 * Edita un registro de la tabla 1
	 */
	public void editarRegistroTabla1(){
		getSeguimientoGeneroBean().setCodProvincia(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagProvince());
		filtraCantones();
		getSeguimientoGeneroBean().setCodCanton(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagCanton());
		filtraParroquias();
		getSeguimientoGeneroBean().setCodParroquia(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagParish());
		getSeguimientoGeneroBean().setCodigoAutoIdentificacion(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagEthnicity());
		if(getSeguimientoGeneroBean().getCodigoAutoIdentificacion() == CODIGO_IDENTIFICACION_INDIGENA){
			getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(getSeguimientoGeneroBean().getDetalleAdvanceGender().getDtagTown());
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(true);
		}
	}

	public void nuevoRegistroDetalleGenero(){
		getSeguimientoGeneroBean().setNuevoDetalleGenero(true);
		getSeguimientoGeneroBean().setDetalleAdvanceGender(new DetailAdvanceGender());
		getSeguimientoGeneroBean().setCodCanton(null);
		getSeguimientoGeneroBean().setCodigoAutoIdentificacion(null);
		getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(0);
		getSeguimientoGeneroBean().setCodProvincia(null);
		getSeguimientoGeneroBean().setCodParroquia(null);
	}
	
	public void asignaInformacionLineaGenero(AdvanceExecutionProjectGender avanceGenero){
		getSeguimientoGeneroBean().setAdvanceExecutionProjectGenderSeleccionado(avanceGenero);
		if(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().getProjectGenderIndicator().getIndicators().getIndiType().equals("B")){
			if(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().getAepgValueReachedOne() == null){
				getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().setAepgBooleanValue(false);
			}else if(getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().getAepgValueReachedOne() == 1){
				getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().setAepgBooleanValue(true);
			}else{
				getSeguimientoGeneroBean().getAdvanceExecutionProjectGenderSeleccionado().setAepgBooleanValue(false);
			}
		}
	}
	
	/**
	 * Muestra el cuadro de dialogo para eliminar segun el tipo
	 * @param codigo indica el tipo de dialogo a mostrar
	 */
	public void mostrarDialogoEliminaDatosTabla(int codigo){
		switch(codigo){
		case 1:
			Mensaje.verDialogo("dlgEliminaItemTabla1");
			break;
		case 3:
			Mensaje.verDialogo("dlgEliminaItemTabla3");
			break;
		case 6:
			Mensaje.verDialogo("dlgEliminaItemTabla6");
			break;
		case 7:
			Mensaje.verDialogo("dlgEliminaItemTabla7");
			break;	
		}
	}

	/**
	 * Elimina un registro de la tabla de detalle genero
	 */
	public void eliminarRegistroTablaGenero(int codigo){
		try{
			switch(codigo){
			case 1:
				getSeguimientoGeneroBean().getListaDetalleAvancesGenero().remove(getSeguimientoGeneroBean().getRegistroTablaGenero());
				getDetailAdvanceGenderFacade().eliminaRegistroDetalleAvanceGenero(getSeguimientoGeneroBean().getRegistroTablaGenero());
				break;	
			case 3:
				getSeguimientoGeneroBean().getTablaRespuestas3().remove(getSeguimientoGeneroBean().getFilaTabla3());
				getTableResponsesFacade().eliminarRespuestasTabla(getSeguimientoGeneroBean().getFilaTabla3());
				break;
			case 6:
				getSeguimientoGeneroBean().getTablaRespuestas6().remove(getSeguimientoGeneroBean().getFilaTabla6());
				getTableResponsesFacade().eliminarRespuestasTabla(getSeguimientoGeneroBean().getFilaTabla6());
				break;
			case 7:
				getSeguimientoGeneroBean().getTablaRespuestas7().remove(getSeguimientoGeneroBean().getFilaTabla7());
				getTableResponsesFacade().eliminarRespuestasTabla(getSeguimientoGeneroBean().getFilaTabla7());
				break;	
			}

		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.eliminarInfo"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "eliminarRegistroTablaGenero " + ": ").append(e.getMessage()));
		}
	}


	public void mostrarDatosTablaBeneficiarios(AdvanceExecutionProjectGender avanceGenero){
		try{
			getSeguimientoGeneroBean().setAdvanceExecutionProjectGenderSeleccionado(avanceGenero);
			getSeguimientoGeneroBean().setMostrarTablaSeguimiento1(true);
			getSeguimientoGeneroBean().setListaDetalleAvancesGenero(new ArrayList<DetailAdvanceGender>());
			getSeguimientoGeneroBean().setListaDetalleAvancesGenero(getDetailAdvanceGenderFacade().listadoDetalleAvanceGenero(avanceGenero.getAepgId()));
			for (DetailAdvanceGender dag : getSeguimientoGeneroBean().getListaDetalleAvancesGenero()) {
				dag.setProvincia(ubicaProvinciaCantonParroquia(dag.getDtagProvince(), 1));
				dag.setCanton(ubicaProvinciaCantonParroquia(dag.getDtagCanton(), 2));
				dag.setParroquia(ubicaProvinciaCantonParroquia(dag.getDtagParish(), 3));
				dag.setEtnia(OperacionesCatalogo.ubicaDescripcionCatalogo(dag.getDtagEthnicity(),getAplicacionBean().getListaAutoIdentificacion()));				
				if(dag.getDtagEthnicity()== CODIGO_IDENTIFICACION_INDIGENA)
					dag.setPueblo(OperacionesCatalogo.ubicaDescripcionCatalogo(dag.getDtagTown(),getAplicacionBean().getListaPueblosNacionalidades()));					
			}
			Mensaje.verDialogo("dlgAsignaBeneficiarios");
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "mostrarDatosTablaBeneficiarios" + ": ").append(e.getMessage()));
		}
	}
	
	

	public void cargaTipoOrganizacion(){
			getSeguimientoGeneroBean().setListadoTipoOrganizaciones(new ArrayList<String>());
			for (Catalogs catalog : getAplicacionBean().getListaTipoOrganizacion()) {
				getSeguimientoGeneroBean().getListadoTipoOrganizaciones().add(catalog.getCataText1());
			}
	}

	/**
	 * Carga los tipos de incentivos
	 */	
	public void cargaTipoIncentivo(){		
			getSeguimientoGeneroBean().setListadoTipoIncentivo(new ArrayList<String>());
			for (Catalogs catalog : getAplicacionBean().getListaTipoIncentivo()) {
				getSeguimientoGeneroBean().getListadoTipoIncentivo().add(catalog.getCataText1());
			}		
	}

	public void mostrarDialogoEliminaValoresTabla(String codigo){
		getSeguimientoGeneroBean().setCodigoTablaDatos(codigo);
		if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G3")){
			if (validaDatosTabla(getSeguimientoGeneroBean().getTablaRespuestas3()) 
					&& getSeguimientoGeneroBean().getListaValoresRespuestas().get(0).isVaanYesnoAnswerValue()==false){
				Mensaje.verDialogo("dlgEliminaDatosTabla");
			}
		}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G6")){
			if (validaDatosTabla(getSeguimientoGeneroBean().getTablaRespuestas6()) 
					&& getSeguimientoGeneroBean().getListaValoresRespuestas().get(1).isVaanYesnoAnswerValue()==false){
				Mensaje.verDialogo("dlgEliminaDatosTabla");
			}
		}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G7")){
			if (validaDatosTabla(getSeguimientoGeneroBean().getTablaRespuestas7()) 
					&& getSeguimientoGeneroBean().getListaValoresRespuestas().get(2).isVaanYesnoAnswerValue()==false){
				Mensaje.verDialogo("dlgEliminaDatosTabla");
			}
		}						
	}

	public boolean validaDatosTabla(List<TableResponses> tablaRespuestas){
		if(tablaRespuestas.size()>0)
			return true;
		else
			return false;
	}

	public void nuevoRegistroGeneroTabla3(){
		
			getSeguimientoGeneroBean().setNuevoRegistroTablaGenero3(true);
			getSeguimientoGeneroBean().setFilaTabla3(new TableResponses());			
			getSeguimientoGeneroBean().setCodCanton(0);
			getSeguimientoGeneroBean().setCodProvincia(0);
			getSeguimientoGeneroBean().setCodParroquia(0);
			getSeguimientoGeneroBean().setCodigoAutoIdentificacion(0);
			getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(0);
			getSeguimientoGeneroBean().setTipoOrganizacionSeleccionados(null);
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(false);
			vaciarDatosProvinciaCantonParroquia();
	}
	public void nuevoRegistroGeneroTabla6(){
		if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()!=null ){
			getSeguimientoGeneroBean().setNuevoRegistroTablaGenero6(true);
			getSeguimientoGeneroBean().setFilaTabla6(new TableResponses());			
			getSeguimientoGeneroBean().setCodCanton(0);
			getSeguimientoGeneroBean().setCodProvincia(0);
			getSeguimientoGeneroBean().setCodParroquia(0);
			getSeguimientoGeneroBean().setCodigoAutoIdentificacion(0);
			getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(0);
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(false);
			vaciarDatosProvinciaCantonParroquia();
		}
	}
	public void nuevoRegistroGeneroTabla7(){
		if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()!=null ){
			getSeguimientoGeneroBean().setNuevoRegistroTablaGenero7(true);
			getSeguimientoGeneroBean().setFilaTabla7(new TableResponses());			
			getSeguimientoGeneroBean().setCodCanton(0);
			getSeguimientoGeneroBean().setCodProvincia(0);
			getSeguimientoGeneroBean().setCodParroquia(0);
			getSeguimientoGeneroBean().setCodigoAutoIdentificacion(0);
			getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(0);
			getSeguimientoGeneroBean().setHabilitaPuebloNacionalidad(false);
			vaciarDatosProvinciaCantonParroquia();
		}
	}


	public void agregaDetalleRegistroGeneroTabla3(){
		try{
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()==null){
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.primeroGrabarSalvaguarda"));
			}else{
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberOne(getSeguimientoGeneroBean().getCodProvincia());
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberTwo(getSeguimientoGeneroBean().getCodCanton());
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberThree(getSeguimientoGeneroBean().getCodParroquia());
				getSeguimientoGeneroBean().getFilaTabla3().setTareProvincia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberOne(), 1));
				getSeguimientoGeneroBean().getFilaTabla3().setTareCanton(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberTwo(), 2));
				getSeguimientoGeneroBean().getFilaTabla3().setTareParroquia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberThree(), 3));
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberFour(getSeguimientoGeneroBean().getCodigoAutoIdentificacion());
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberFive(0);
				getSeguimientoGeneroBean().getFilaTabla3().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));
				getSeguimientoGeneroBean().getFilaTabla3().setTareColumnTree(UtilsCadenas.pasarArrayAString(getSeguimientoGeneroBean().getTipoOrganizacionSeleccionados()));
				if(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA){
					getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberFive(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
					getSeguimientoGeneroBean().getFilaTabla3().setTareGenericoDos(ubicaPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFive()));
				}else{
					getSeguimientoGeneroBean().getFilaTabla3().setTareColumnNumberFive(0);
					getSeguimientoGeneroBean().getFilaTabla3().setTareGenericoDos("");
				}
				if(getSeguimientoGeneroBean().getFilaTabla3().getTareId()== null){				
					getSeguimientoGeneroBean().getFilaTabla3().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla3().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(0));					
					getSeguimientoGeneroBean().getFilaTabla3().setTareStatus(true);					

					
					getTableResponsesFacade().agregaRespuestaTabla(getSeguimientoGeneroBean().getFilaTabla3(), getSeguimientoGeneroBean().getListaValoresRespuestas().get(0));
					getSeguimientoGeneroBean().getTablaRespuestas3().add(getSeguimientoGeneroBean().getFilaTabla3());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,   "",getMensajesController().getPropiedad("info.infoGrabada"));
				}else{

					getSeguimientoGeneroBean().getFilaTabla3().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla3().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(0));					
					getSeguimientoGeneroBean().getFilaTabla3().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));

					getTableResponsesFacade().edit(getSeguimientoGeneroBean().getFilaTabla3());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
				}
				getSeguimientoGeneroBean().setNuevoRegistroTablaGenero3(false);

			}
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregaDetalleRegistroGeneroTabla3 " + ": ").append(e.getMessage()));
		}
	}

	public void agregaDetalleRegistroGeneroTabla6(){
		try{
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()==null){
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.primeroGrabarSalvaguarda"));
			}else{
				getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberOne(getSeguimientoGeneroBean().getCodProvincia());
				getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberTwo(getSeguimientoGeneroBean().getCodCanton());
				getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberThree(getSeguimientoGeneroBean().getCodParroquia());
				getSeguimientoGeneroBean().getFilaTabla6().setTareProvincia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberOne(), 1));
				getSeguimientoGeneroBean().getFilaTabla6().setTareCanton(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberTwo(), 2));
				getSeguimientoGeneroBean().getFilaTabla6().setTareParroquia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberThree(), 3));
				getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberFour(getSeguimientoGeneroBean().getCodigoAutoIdentificacion());
//				getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberFive(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
				getSeguimientoGeneroBean().getFilaTabla6().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));
				if(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA){
					getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberFive(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
					getSeguimientoGeneroBean().getFilaTabla6().setTareGenericoDos(ubicaPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFive()));
				}else{
					getSeguimientoGeneroBean().getFilaTabla6().setTareColumnNumberFive(0);
					getSeguimientoGeneroBean().getFilaTabla6().setTareGenericoDos("");
				}
				if(getSeguimientoGeneroBean().getFilaTabla6().getTareId()== null){				
					getSeguimientoGeneroBean().getFilaTabla6().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla6().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(1));					
					getSeguimientoGeneroBean().getFilaTabla6().setTareStatus(true);					


					getTableResponsesFacade().agregaRespuestaTabla(getSeguimientoGeneroBean().getFilaTabla6(), getSeguimientoGeneroBean().getListaValoresRespuestas().get(1));
					getSeguimientoGeneroBean().getTablaRespuestas6().add(getSeguimientoGeneroBean().getFilaTabla6());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
				}else{

					getSeguimientoGeneroBean().getFilaTabla6().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla6().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(1));					
					getSeguimientoGeneroBean().getFilaTabla6().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));
					
					getTableResponsesFacade().edit(getSeguimientoGeneroBean().getFilaTabla6());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
				}
				getSeguimientoGeneroBean().setNuevoRegistroTablaGenero6(false);
			}
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregaDetalleRegistroGeneroTabla6 " + ": ").append(e.getMessage()));
		}
	}

	public void agregaDetalleRegistroGeneroTabla7(){
		try{
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()==null){
				Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.primeroGrabarSalvaguarda"));
			}else{
				getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberOne(getSeguimientoGeneroBean().getCodProvincia());
				getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberTwo(getSeguimientoGeneroBean().getCodCanton());
				getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberThree(getSeguimientoGeneroBean().getCodParroquia());
				getSeguimientoGeneroBean().getFilaTabla7().setTareProvincia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberOne(), 1));
				getSeguimientoGeneroBean().getFilaTabla7().setTareCanton(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberTwo(), 2));
				getSeguimientoGeneroBean().getFilaTabla7().setTareParroquia(ubicaProvinciaCantonParroquia(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberThree(), 3));
				getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberFour(getSeguimientoGeneroBean().getCodigoAutoIdentificacion());
				getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberFive(0);
				getSeguimientoGeneroBean().getFilaTabla7().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));
				if(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA){
					getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberFive(getSeguimientoGeneroBean().getCodigoPuebloNacionalidad());
					getSeguimientoGeneroBean().getFilaTabla7().setTareGenericoDos(ubicaPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFive()));
				}else{
					getSeguimientoGeneroBean().getFilaTabla7().setTareColumnNumberFive(0);
					getSeguimientoGeneroBean().getFilaTabla7().setTareGenericoDos("");
				}
				if(getSeguimientoGeneroBean().getFilaTabla7().getTareId()== null){				
					getSeguimientoGeneroBean().getFilaTabla7().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla7().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(2));					
					getSeguimientoGeneroBean().getFilaTabla7().setTareStatus(true);					


					getTableResponsesFacade().agregaRespuestaTabla(getSeguimientoGeneroBean().getFilaTabla7(), getSeguimientoGeneroBean().getListaValoresRespuestas().get(2));
					getSeguimientoGeneroBean().getTablaRespuestas7().add(getSeguimientoGeneroBean().getFilaTabla7());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
				}else{

					getSeguimientoGeneroBean().getFilaTabla7().setAdvanceExecutionSaveguards(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
					getSeguimientoGeneroBean().getFilaTabla7().setQuestions(getSeguimientoGeneroBean().getListaPreguntas().get(2));					
					getSeguimientoGeneroBean().getFilaTabla7().setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));
				
					getTableResponsesFacade().edit(getSeguimientoGeneroBean().getFilaTabla7());
					Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
				}
				getSeguimientoGeneroBean().setNuevoRegistroTablaGenero7(false);
			}
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregaDetalleRegistroGeneroTabla7 " + ": ").append(e.getMessage()));
		}
	}

	public void editarRegistroTabla3(){
		getSeguimientoGeneroBean().setCodProvincia(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberOne());
		filtraCantones();
		getSeguimientoGeneroBean().setCodCanton(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberTwo());
		filtraParroquias();
		getSeguimientoGeneroBean().setCodParroquia(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberThree());
		getSeguimientoGeneroBean().setCodigoAutoIdentificacion(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFour());
		getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnNumberFive());
		getSeguimientoGeneroBean().setTipoOrganizacionSeleccionados(getSeguimientoGeneroBean().getFilaTabla3().getTareColumnTree().split(","));

	}

	public void editarRegistroTabla6(){
		getSeguimientoGeneroBean().setCodProvincia(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberOne());
		filtraCantones();
		getSeguimientoGeneroBean().setCodCanton(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberTwo());
		filtraParroquias();
		getSeguimientoGeneroBean().setCodParroquia(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberThree());
		getSeguimientoGeneroBean().setCodigoAutoIdentificacion(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFour());
		getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla6().getTareColumnNumberFive());
	}
	public void editarRegistroTabla7(){
		getSeguimientoGeneroBean().setCodProvincia(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberOne());
		filtraCantones();
		getSeguimientoGeneroBean().setCodCanton(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberTwo());
		filtraParroquias();
		getSeguimientoGeneroBean().setCodParroquia(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberThree());
		getSeguimientoGeneroBean().setCodigoAutoIdentificacion(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFour());
		getSeguimientoGeneroBean().setCodigoPuebloNacionalidad(getSeguimientoGeneroBean().getFilaTabla7().getTareColumnNumberFive());
	}

	public void retrocederTab(){
		getSeguimientoGeneroBean().setPosicionTab(0);
	}
	public void tabChange() {
		getSeguimientoGeneroBean().setPosicionTab(0);		
	}

	public void mostrarDialogoGrabarAvanceGenero(){
		if(validaOpcionSiTablas())
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionSi"));
		else
			Mensaje.verDialogo("dlgGrabaAvanceGenero");
	}
	/**
	 * Valida si estan las opciones Si con datos o no
	 * @return
	 */
	public boolean validaOpcionSiTablas(){
		boolean nocumple=false;
		if((getSeguimientoGeneroBean().getListaValoresRespuestas().get(0).isVaanYesnoAnswerValue() && getSeguimientoGeneroBean().getTablaRespuestas3().size()==0)){
			nocumple = true; 
		}else if(getSeguimientoGeneroBean().getListaValoresRespuestas().get(1).isVaanYesnoAnswerValue() && getSeguimientoGeneroBean().getTablaRespuestas6().size() == 0){
			nocumple = true;
		}else if(getSeguimientoGeneroBean().getListaValoresRespuestas().get(2).isVaanYesnoAnswerValue() && getSeguimientoGeneroBean().getTablaRespuestas7().size() == 0){
			nocumple = true;

		}
		return nocumple;
	}
	/**
	 * Imprime el pdf del reporte de genero
	 */
	public void imprimirResumenGenero(){
		try{

			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String directorioArchivoPDF = new StringBuilder().append(ctx.getRealPath("")).append(File.separator).append("reportes").append(File.separator).append(1).append(".pdf").toString();
			rutaPDF=directorioArchivoPDF;
			ResumenPDF.reporteGenero(directorioArchivoPDF, getSeguimientoGeneroBean());

			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "",getMensajesController().getPropiedad("error.lineaAccion"));
			Mensaje.actualizarComponente(":form:growl");

		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.generarPDF"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "imprimirResumenGenero " + ": ").append(e.getMessage()));			
		}
	}

	/**
	 * Muestra el dialogo para finalizar reporte de genero
	 */
	public void mostrarDialogoFinalizarReporte(){
		try{
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards()!=null ){
				if(validaOpcionSiTablas())
					Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.seleccionSi"));
				else{
//					Mensaje.verDialogo("dlgFinalizarReporteGenero");
					if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getProjectsStrategicPartners() == null){
						//					Mensaje.verDialogo("dlgFinalizarReporteSalvaguarda");
						getSeguimientoGeneroBean().setListaPresentadosIniciados(new ArrayList<AdvanceExecutionSafeguards>());
						//					List<AdvanceExecutionSafeguards> listaTemp= new ArrayList<>();
						getSeguimientoGeneroBean().setListaPresentadosIniciados(getAdvanceExecutionSafeguardsFacade().listadoProyectosPresentadosIniciados(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards(), 2));
						if(getSeguimientoGeneroBean().getListaPresentadosIniciados().size()==0 && getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getProjectsStrategicPartners() == null)
							Mensaje.verDialogo("dlgFinalizarReporteGenero");
						else{


							Mensaje.verDialogo("dlgReportesNoCerrados");
							Mensaje.actualizarComponente(":form:listaSegEstado");

						}

					}else if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getProjectsStrategicPartners() != null)
						Mensaje.verDialogo("dlgFinalizarReporteGenero");
					else{
						Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "",getMensajesController().getPropiedad("error.cerrarProyectos"));
						Mensaje.actualizarComponente("growl");
					}
				}


			}else{
				Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "",getMensajesController().getPropiedad("error.avanceGenero"));
				Mensaje.actualizarComponente(":form:growl");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Carga los valores de las preguntas respuestas de las tablas de genero
	 */
	public void cargaValoresPreguntasRespuestas(){
		try{
			getSeguimientoGeneroBean().setListaPreguntas(getQuestionsFacade().buscaPreguntasGenero());
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards()!=null && getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId() != null){
				getSeguimientoGeneroBean().setListaValoresRespuestas(getValueAnswersFacade().buscarPorAvanceEjecucionGenero(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId()));
//				getSeguimientoGeneroBean().setListaValoresRespuestas(getSeguimientoGeneroBean().getListaValoresRespuestas().stream().sorted((vr1,vr2)->vr1.getQuestions().getQuesQuestionOrder().compareTo(vr2.getQuestions().getQuesQuestionOrder())).collect(Collectors.toList()));
	    		Collections.sort(getSeguimientoGeneroBean().getListaValoresRespuestas(), new Comparator<ValueAnswers>(){
					@Override
					public int compare(ValueAnswers o1, ValueAnswers o2) {
						return o1.getQuestions().getQuesQuestionOrder().compareTo(o2.getQuestions().getQuesQuestionOrder());
					}
				});
				
				if(getSeguimientoGeneroBean().getListaValoresRespuestas().size()==0 && getSeguimientoGeneroBean().getListaValoresRespuestas()!=null)
					valoresRespuestasPorDefecto(getSeguimientoGeneroBean().getListaPreguntas(), getSeguimientoGeneroBean().getListaValoresRespuestas());
			}else{
				getSeguimientoGeneroBean().setListaValoresRespuestas(new ArrayList<ValueAnswers>());
				valoresRespuestasPorDefecto(getSeguimientoGeneroBean().getListaPreguntas(), getSeguimientoGeneroBean().getListaValoresRespuestas());
			}
			getSeguimientoGeneroBean().setPreguntasGenero(true);
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.respuestasPreguntas"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "cargaValoresPreguntasRespuestas " + ": ").append(e.getMessage()));
		}
	}

	public void preparaRespuestasGenero(){
		try{
			List<TableResponses> listaRespuestas=new ArrayList<>();
			if(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards()!= null && getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId() !=null )
				listaRespuestas = getTableResponsesFacade().buscarPorAvanceEjecucionYGenero(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexId());
//			getSeguimientoGeneroBean().setTablaRespuestas3(listaRespuestas.stream().filter((sc)->sc.getQuestions().getQuesId().equals(174)).collect(Collectors.toList()));
			getSeguimientoGeneroBean().setTablaRespuestas3(new ArrayList<TableResponses>());
			for (TableResponses tr : listaRespuestas) {
				if(tr.getQuestions().getQuesId().equals(174))
					getSeguimientoGeneroBean().getTablaRespuestas3().add(tr);
			}
			
			for(TableResponses res:getSeguimientoGeneroBean().getTablaRespuestas3()){
				res.setTareProvincia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberOne(), 1));
				res.setTareCanton(ubicaProvinciaCantonParroquia(res.getTareColumnNumberTwo(), 2));
				res.setTareParroquia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberThree(), 3));				
				res.setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));				
				if(res.getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA)
					res.setTareGenericoDos(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFive(),getAplicacionBean().getListaPueblosNacionalidades()));
			}

//			getSeguimientoGeneroBean().setTablaRespuestas6(listaRespuestas.stream().filter((sc)->sc.getQuestions().getQuesId().equals(177)).collect(Collectors.toList()));
			getSeguimientoGeneroBean().setTablaRespuestas6(new ArrayList<TableResponses>());
			for (TableResponses tr : listaRespuestas) {
				if(tr.getQuestions().getQuesId().equals(177))
					getSeguimientoGeneroBean().getTablaRespuestas6().add(tr);
			}
			
			for(TableResponses res:getSeguimientoGeneroBean().getTablaRespuestas6()){
				res.setTareProvincia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberOne(), 1));
				res.setTareCanton(ubicaProvinciaCantonParroquia(res.getTareColumnNumberTwo(), 2));
				res.setTareParroquia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberThree(), 3));				
				res.setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));				
				if(res.getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA)
					res.setTareGenericoDos(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFive(),getAplicacionBean().getListaPueblosNacionalidades()));
			}
//			getSeguimientoGeneroBean().setTablaRespuestas7(listaRespuestas.stream().filter((sc)->sc.getQuestions().getQuesId().equals(178)).collect(Collectors.toList()));
			getSeguimientoGeneroBean().setTablaRespuestas7(new ArrayList<TableResponses>());
			for (TableResponses tr : listaRespuestas) {
				if(tr.getQuestions().getQuesId().equals(178))
					getSeguimientoGeneroBean().getTablaRespuestas7().add(tr);
			}
			for(TableResponses res:getSeguimientoGeneroBean().getTablaRespuestas7()){
				res.setTareProvincia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberOne(), 1));
				res.setTareCanton(ubicaProvinciaCantonParroquia(res.getTareColumnNumberTwo(), 2));
				res.setTareParroquia(ubicaProvinciaCantonParroquia(res.getTareColumnNumberThree(), 3));				
				res.setTareGenerico(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFour(),getAplicacionBean().getListaAutoIdentificacion()));				
				if(res.getTareColumnNumberFour() == CODIGO_IDENTIFICACION_INDIGENA)
					res.setTareGenericoDos(OperacionesCatalogo.ubicaDescripcionCatalogo(res.getTareColumnNumberFive(),getAplicacionBean().getListaPueblosNacionalidades()));
			}
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "", getMensajesController().getPropiedad("error.respuestasPreguntas"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "preparaRespuestasGenero " + ": ").append(e.getMessage()));
		}
	}

	/**
	 * Graba el avance de genero
	 */
	public void grabarAvanceGenero(){
		try{
			String desde="";
			String hasta="";
			desde = String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesDesde());
			hasta = String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getMesHasta());
//			desde = String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat(getComponenteBuscarProyectos().getBuscaProyectosBean().getPeriodoDesde());
//			hasta = String.valueOf(getComponenteBuscarProyectos().getBuscaProyectosBean().getAnioReporte()).concat("-").concat("12");
			if(!getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().getAdexTermFrom().equals(desde)){
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexTermFrom(desde);
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexTermTo(hasta);
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexUpdateUser(getLoginBean().getUser().getUserName());
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexUpdateDate(new Date());
			}
			getAdvanceExecutionSafeguardsFacade().actualizaAvanceEjecucionGenero(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR,  "",getMensajesController().getPropiedad("error.grabarAvanceGenero"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "grabarAvanceGenero " + ": ").append(e.getMessage()));
		}
	}

	/**
	 * Finaliza el reporte de genero del proyecto
	 */
	public void finalizaReporteGenero(){
		try{
			getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexUpdateUser(getLoginBean().getUser().getUserName());
			getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexUpdateDate(new Date());
			if(getLoginBean().getTipoRol()==3){
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexIsReported(false);
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexReportedStatus(TipoEstadoReporteEnum.ENPROCESO.getCodigo());
			}else{
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexIsReported(true);
				getSeguimientoGeneroBean().getAdvanceExecutionSafeguards().setAdexReportedStatus(TipoEstadoReporteEnum.FINALIZADO.getCodigo());
			}
			getAdvanceExecutionSafeguardsFacade().actualizaAvanceEjecucionGenero(getSeguimientoGeneroBean().getAdvanceExecutionSafeguards());
			getSeguimientoGeneroBean().setDatosGeneroParaMostrar(false);
			getComponenteBuscarProyectos().volverABuscarProyectos();
			vaciaDatosGenero();
			getSeguimientoGeneroBean().setDatosSeguimiento(false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void vaciaDatosGenero(){

		getSeguimientoGeneroBean().setCodProvincia(null);
		getSeguimientoGeneroBean().setCodCanton(null);
		getSeguimientoGeneroBean().setCodParroquia(null);

		getSeguimientoGeneroBean().setInformacionProyectoGeneroSeleccionado(null);
		getSeguimientoGeneroBean().setListaPreguntas(new ArrayList<Questions>());
		getSeguimientoGeneroBean().setListaValoresRespuestas(new ArrayList<ValueAnswers>());
		getSeguimientoGeneroBean().setTablaRespuestas3(new ArrayList<TableResponses>());
		getSeguimientoGeneroBean().setTablaRespuestas4(new ArrayList<TableResponses>());
		getSeguimientoGeneroBean().setTablaRespuestas5(new ArrayList<TableResponses>());
		getSeguimientoGeneroBean().setTablaRespuestas6(new ArrayList<TableResponses>());
		getSeguimientoGeneroBean().setTablaRespuestas7(new ArrayList<TableResponses>());
		getSeguimientoGeneroBean().setPreguntasGenero(false);
		getSeguimientoGeneroBean().setAdvanceExecutionSafeguards(getComponenteBuscarProyectos().getAdvanceExecution());
		getSeguimientoGeneroBean().setDatosGeneroParaMostrar(false);
		getSeguimientoGeneroBean().setPosicionTab(0);
	}
	public void siguienteTab(){
		getSeguimientoGeneroBean().setPosicionTab(1);
		getSeguimientoGeneroBean().setPreguntasGenero(true);
		getSeguimientoGeneroBean().setDatosGeneroParaMostrar(true);
	}

	public boolean datosProyecto(){		
		return getComponenteBuscarProyectos().datosProyecto();
	}
	public void eliminaValoresTablaDatos(){
		try{
			if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G3")){
				getTableResponsesFacade().eliminarDatosTablaActualizaValueAnswer(getSeguimientoGeneroBean().getTablaRespuestas3(),getSeguimientoGeneroBean().getListaValoresRespuestas().get(0));
				getSeguimientoGeneroBean().setTablaRespuestas3(new ArrayList<TableResponses>());
				getSeguimientoGeneroBean().setCodigoTablaDatos("");
				Mensaje.actualizarComponente(":form:tabs:panelTablaGenero3");
			}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G6")){
				getTableResponsesFacade().eliminarDatosTablaActualizaValueAnswer(getSeguimientoGeneroBean().getTablaRespuestas6(),getSeguimientoGeneroBean().getListaValoresRespuestas().get(1));
				getSeguimientoGeneroBean().setTablaRespuestas6(new ArrayList<TableResponses>());				
				getSeguimientoGeneroBean().setCodigoTablaDatos("");
				Mensaje.actualizarComponente(":form:tabs:panelTablaGenero6");
			}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G7")){
				getTableResponsesFacade().eliminarDatosTablaActualizaValueAnswer(getSeguimientoGeneroBean().getTablaRespuestas7(),getSeguimientoGeneroBean().getListaValoresRespuestas().get(2));
				getSeguimientoGeneroBean().setTablaRespuestas7(new ArrayList<TableResponses>());				
				getSeguimientoGeneroBean().setCodigoTablaDatos("");
				Mensaje.actualizarComponente(":form:tabs:panelTablaGenero7");
			}
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "eliminaValoresTablaDatos" + ": ").append(e.getMessage()));
		}
	}
	public void cancelaEliminaValoresTablaDatos(){
		if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G3")){
			getSeguimientoGeneroBean().getListaValoresRespuestas().get(0).setVaanYesnoAnswerValue(true);
			getSeguimientoGeneroBean().setCodigoTablaDatos("");
			Mensaje.actualizarComponente(":form:tabs:radiopSB1");	
			Mensaje.actualizarComponente(":form:tabs:btnTablaGen3");
		}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G6")){
			getSeguimientoGeneroBean().getListaValoresRespuestas().get(1).setVaanYesnoAnswerValue(true);
			getSeguimientoGeneroBean().setCodigoTablaDatos("");
			Mensaje.actualizarComponente(":form:tabs:radiopSB4"); 
			Mensaje.actualizarComponente(":form:tabs:btnTablaGen6");
		}else if(getSeguimientoGeneroBean().getCodigoTablaDatos().equals("G7")){
			getSeguimientoGeneroBean().getListaValoresRespuestas().get(2).setVaanYesnoAnswerValue(true);
			getSeguimientoGeneroBean().setCodigoTablaDatos("");
			Mensaje.actualizarComponente(":form:tabs:radiopSB5"); 
			Mensaje.actualizarComponente(":form:tabs:btnTablaGen7");
		}
	}
	

	
	public boolean validaPeriodoReporteProyecto(Project proyecto,int desde){
		boolean resultado=false;
		String inicioProyecto="";
		String finProyecto="";
		inicioProyecto = proyecto.getProjTermFrom().substring(0, 4);
		finProyecto = proyecto.getProjTermTo().substring(0,4);
		if(desde>=Integer.valueOf(inicioProyecto) && desde<=Integer.valueOf(finProyecto))
			resultado = true;
		else
			resultado = false;
		return resultado;
	}
	public void vaciarDatosProvinciaCantonParroquia(){
		getSeguimientoGeneroBean().setListaCantones(new ArrayList<GeographicalLocations>());
		getSeguimientoGeneroBean().setListaParroquias(new ArrayList<GeographicalLocations>());
		getSeguimientoGeneroBean().setCodProvincia(0);
		getSeguimientoGeneroBean().setCodCanton(0);
		getSeguimientoGeneroBean().setCodParroquia(0);
	}
	
	public void mostrarValoresAnterioresLineaGenero(AdvanceExecutionProjectGender avanceGenero){
		getSeguimientoGeneroBean().setAdvanceExecutionProjectGenderSeleccionado(avanceGenero);
	}
}

