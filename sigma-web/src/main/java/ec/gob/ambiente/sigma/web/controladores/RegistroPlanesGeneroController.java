package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;

import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.CatalogsType;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.Meeting;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAction;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancing;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsRisk;
import ec.gob.ambiente.sigma.ejb.entidades.Tracing;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsAmount;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsKnowledgeManagement;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsProblem;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsProgress;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.facades.MeetingFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsActionFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsFinancingFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsRiskFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsSpecificObjectiveFacade;
import ec.gob.ambiente.sigma.ejb.facades.TracingFacade;
import ec.gob.ambiente.sigma.ejb.facades.TracingsAmountFacade;
import ec.gob.ambiente.sigma.ejb.facades.TracingsKnowledgeManagementFacade;
import ec.gob.ambiente.sigma.ejb.facades.TracingsProblemFacade;
import ec.gob.ambiente.sigma.ejb.facades.TracingsProgressFacade;
import ec.gob.ambiente.sigma.web.utils.OpcionesRegistro;

@Named("gene")
@ViewScoped
public class RegistroPlanesGeneroController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistroPlanesGeneroController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private PartnerFacade partnerFacade;
	@EJB
	private ProjectFacade projectFacade;
	//@EJB
	//private ProjectsGenderInfoFacade projectGenderInfoFacade;

	private String tipoBusq;
	private Integer codSocioSeleccionado;
	private List<Partner> listaSociosImpl;
	private String tituloProyecto;
	private List<Project> listaProyectosEncontrados;

	private int controlAvance;

	private Project proyectoActual;
	private Boolean proyectoTienePlan;
	private List<CatalogsType> listaTipoLineasGenero;
	/*private List<ProjectsGenderInfo> listaInfoGenero;
	private List<ProjectsGenderInfo> listaInfoGeneroTema1;
	private List<ProjectsGenderInfo> listaInfoGeneroTema2;
	private List<ProjectsGenderInfo> listaInfoGeneroTema3;
	private List<ProjectsGenderInfo> listaInfoGeneroOtroTema;*/
	private boolean flag;

	public RegistroPlanesGeneroController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			/*controlAvance = 0;
			listaSociosImpl = partnerFacade.listarSociosImplementadores();
			tipoBusq = "SOC";
			listaProyectosEncontrados = new ArrayList<>();
			codSocioSeleccionado = 0;
			tituloProyecto = "";
			proyectoTienePlan = null;
			listaTipoLineasGenero = new ArrayList<>();
			listaInfoGenero = new ArrayList<>();
			listaInfoGeneroTema1 = new ArrayList<>();
			listaInfoGeneroTema2 = new ArrayList<>();
			listaInfoGeneroTema3 = new ArrayList<>();
			listaInfoGeneroOtroTema=new ArrayList<>();*/
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*public void buscarPlanesGenero() {
		try {
			listaProyectosEncontrados = new ArrayList<>();
			List<String[]> listaMsg = validarBusquedaProyectos();
			if (listaMsg.isEmpty()) {
				if (tipoBusq.equals("SOC")) {
					listaProyectosEncontrados = projectFacade.listarProyectosPorIdSocioImpl(codSocioSeleccionado);
				} else if (tipoBusq.equals("TIT")) {
					listaProyectosEncontrados = projectFacade.listarProyectosPorTextoTitulo(tituloProyecto);
				}
				if (listaProyectosEncontrados.isEmpty()) {
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg064"));
				}
			} else {
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarBusquedaProyectos() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (tipoBusq.equals("SOC") && codSocioSeleccionado == null) {
				sesBean.validacionMsg("frm:codSocioSel", appBean.getBundle().getString("msg031"), listaMsg);
			} else if (tipoBusq.equals("TIT") && tituloProyecto.length() < 5) {
				sesBean.validacionMsg("frm:tituloBusq", appBean.getBundle().getString("msg057"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void irPlanDeGeneroProyecto(Project proy) {
		try {
			listaTipoLineasGenero = new ArrayList<>();
			listaInfoGenero = new ArrayList<>();
			listaInfoGeneroTema1 = new ArrayList<>();
			listaInfoGeneroTema2 = new ArrayList<>();
			listaInfoGeneroTema3 = new ArrayList<>();
			listaInfoGeneroOtroTema=new ArrayList<>();
			listaInfoGenero = projectGenderInfoFacade.listarInfoGeneroPorProyecto(proy.getProjId());
			proyectoActual = proy;
			if(!listaInfoGenero.isEmpty()){
				proyectoTienePlan=true;
			}
			cargarListaTematicas();
			controlAvance = 1;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarListaTematicas() {
		try {
				listaTipoLineasGenero.add(appBean.getLstCatEnfGenero1().get(0).getCatyId());
				listaTipoLineasGenero.add(appBean.getLstCatEnfGenero2().get(0).getCatyId());
				listaTipoLineasGenero.add(appBean.getLstCatEnfGenero3().get(0).getCatyId());
				
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void indicarSiTienePlan(){
		try{
			if(proyectoTienePlan!=null&&proyectoTienePlan==false){
				
			}
            if(proyectoTienePlan!=null&&proyectoTienePlan==true){
				
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarSeccionInicio() {
		try {
			List<String[]> listaMsg = validarSeccion1();
			if (listaMsg.isEmpty()) {
				
				if(proyectoTienePlan!=null&&proyectoTienePlan){
					if(listaInfoGenero.isEmpty()&&!flag){
						projectGenderInfoFacade.crearRegistrosGeneroDeProyectoYTema(proyectoActual.getProjId(), appBean.getLstCatEnfGenero1(), appBean.getCodCatEnfGenero1Otro());
						projectGenderInfoFacade.crearRegistrosGeneroDeProyectoYTema(proyectoActual.getProjId(), appBean.getLstCatEnfGenero2(), appBean.getCodCatEnfGenero2Otro());
						projectGenderInfoFacade.crearRegistrosGeneroDeProyectoYTema(proyectoActual.getProjId(), appBean.getLstCatEnfGenero3(), appBean.getCodCatEnfGenero3Otro());
						List<ProjectsGenderInfo> listaInfoG = projectGenderInfoFacade.listarInfoGeneroPorProyecto(proyectoActual.getProjId());
						cargarInfo(listaInfoG);
						flag=true;
					}else{
						cargarInfo(listaInfoGenero);
					}
					controlAvance = 2;
				}else{
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg118"));
				}
				
			} else {
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion1() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	private void cargarInfo(List<ProjectsGenderInfo> infoGenero){
		try{
			listaInfoGeneroTema1 = new ArrayList<>();
			listaInfoGeneroTema2 = new ArrayList<>();
			listaInfoGeneroTema3 = new ArrayList<>();
			listaInfoGeneroOtroTema=new ArrayList<>();
			for(ProjectsGenderInfo gi:infoGenero){
				if(gi.getPginActivities()!=null&&gi.getPginActivities().length()>0){
					gi.setSeleccionado(true);
				}
				if(gi.getCataId()!=null&&gi.getCataId().getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_1")){
					listaInfoGeneroTema1.add(gi);
				}else if(gi.getCataId()!=null&&gi.getCataId().getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_2")){
					listaInfoGeneroTema2.add(gi);
				}else if(gi.getCataId()!=null&&gi.getCataId().getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_3")){
					listaInfoGeneroTema3.add(gi);
				}else if(gi.getCataId()==null){
					listaInfoGeneroOtroTema.add(gi);
				}
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void insertarOtraLineaTema1(){
		try{
			projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema1);
			projectGenderInfoFacade.crearOtraLinea(proyectoActual.getProjId(), appBean.getCodCatEnfGenero1Otro());
			listaInfoGeneroTema1=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(), "LINEAS_ENF_GENERO_1");
			listaInfoGeneroTema1=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema1,true);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	public void insertarOtraLineaTema2(){
		try{
			projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema2);
			projectGenderInfoFacade.crearOtraLinea(proyectoActual.getProjId(), appBean.getCodCatEnfGenero2Otro());
			listaInfoGeneroTema2=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(), "LINEAS_ENF_GENERO_2");
			listaInfoGeneroTema2=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema2,true);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	public void insertarOtraLineaTema3(){
		try{
			projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema3);
			projectGenderInfoFacade.crearOtraLinea(proyectoActual.getProjId(), appBean.getCodCatEnfGenero3Otro());
			listaInfoGeneroTema3=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(), "LINEAS_ENF_GENERO_3");
			listaInfoGeneroTema3=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema3,true);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void insertarOtraLineaTemaOtro(){
		try{
			projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroOtroTema);
			projectGenderInfoFacade.crearOtraLinea(proyectoActual.getProjId(), null);
			listaInfoGeneroOtroTema=projectGenderInfoFacade.listarInfoGeneroPorProyectoYOtroTema(proyectoActual.getProjId());
			listaInfoGeneroOtroTema=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroOtroTema,true);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void eliminarOtraLineaTema1(ProjectsGenderInfo gi){
		try{
			projectGenderInfoFacade.eliminarLogico(gi);
			listaInfoGeneroTema1=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(),"LINEAS_ENF_GENERO_1");
			listaInfoGeneroTema1=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema1,false);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	public void eliminarOtraLineaTema2(ProjectsGenderInfo gi){
		try{
			projectGenderInfoFacade.eliminarLogico(gi);
			listaInfoGeneroTema2=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(),"LINEAS_ENF_GENERO_2");
			listaInfoGeneroTema2=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema2,false);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	public void eliminarOtraLineaTema3(ProjectsGenderInfo gi){
		try{
			projectGenderInfoFacade.eliminarLogico(gi);
			listaInfoGeneroTema3=projectGenderInfoFacade.listarInfoGeneroPorProyectoYTema(proyectoActual.getProjId(),"LINEAS_ENF_GENERO_3");
			listaInfoGeneroTema3=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroTema3,false);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	public void eliminarOtraLineaOtroTema(ProjectsGenderInfo gi){
		try{
			projectGenderInfoFacade.eliminarLogico(gi);
			listaInfoGeneroOtroTema=projectGenderInfoFacade.listarInfoGeneroPorProyectoYOtroTema(proyectoActual.getProjId());
			listaInfoGeneroOtroTema=projectGenderInfoFacade.actualizarCheckSeleccionado(listaInfoGeneroOtroTema,false);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	

	public void guardarInfoGenero() {
		try {

			List<String[]> listaMsg = validarInfGenero(listaInfoGeneroTema1, listaInfoGeneroTema2,
					listaInfoGeneroTema3,listaInfoGeneroOtroTema);

			if (listaMsg.isEmpty()) {
				projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema1);
				projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema2);
				projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroTema3);
				projectGenderInfoFacade.guardarInfoDeProyecto(listaInfoGeneroOtroTema);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));

			} else {

				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarInfGenero(List<ProjectsGenderInfo> lst1, List<ProjectsGenderInfo> lst2,
			List<ProjectsGenderInfo> lst3,List<ProjectsGenderInfo> lstOtro) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			boolean isValidG1 = false;
			boolean isValidG2 = false;
			boolean isValidG3 = false;
			for (ProjectsGenderInfo g1 : lst1) {
				if (g1.isSeleccionado()) {
					isValidG1 = true;
					if (g1.getPginActivities().length() == 0 || g1.getPginIndicator().length() == 0
							|| g1.getPginResults().length() == 0 || g1.getPginBudget()==null||g1.getPginBudget().length() == 0
							||g1.getPginResultsType()==null||g1.getPginResultsType().equals("")
							||(g1.getCataId().getCataNumber()==7&&g1.getPginOtherLine().length()==0)) {
						sesBean.validacionMsg("frm:lstEnfGen1", appBean.getBundle().getString("msg069")+": "+g1.getCataId().getCataText2(), listaMsg);
					}
				}
			}
			for (ProjectsGenderInfo g2 : lst2) {
				if (g2.isSeleccionado()) {
					isValidG2 = true;
					if (g2.getPginActivities().length() == 0 || g2.getPginIndicator().length() == 0
							|| g2.getPginResults().length() == 0 || g2.getPginBudget()==null||g2.getPginBudget().length() == 0
							||g2.getPginResultsType()==null||g2.getPginResultsType().equals("")
							||(g2.getCataId().getCataNumber()==4&&g2.getPginOtherLine().length()==0)) {
						sesBean.validacionMsg("frm:lstEnfGen2", appBean.getBundle().getString("msg069")+": "+g2.getCataId().getCataText2(), listaMsg);
					}
				}
			}
			for (ProjectsGenderInfo g3 : lst3) {
				if (g3.isSeleccionado()) {
					isValidG3 = true;
					if (g3.getPginActivities().length() == 0 || g3.getPginIndicator().length() == 0
							|| g3.getPginResults().length() == 0 ||g3.getPginBudget()==null|| g3.getPginBudget().length() == 0
							||g3.getPginResultsType()==null||g3.getPginResultsType().equals("")
							||(g3.getCataId().getCataNumber()==5&&g3.getPginOtherLine().length()==0)) {
						sesBean.validacionMsg("frm:lstEnfGen3", appBean.getBundle().getString("msg069")+": "+g3.getCataId().getCataText2(), listaMsg);
					}
				}
			}
			
			for (ProjectsGenderInfo go : lstOtro) {
				if (go.isSeleccionado()) {
					
					if (go.getPginActivities().length() == 0 || go.getPginIndicator().length() == 0
							|| go.getPginResults().length() == 0 ||go.getPginBudget()==null|| go.getPginBudget().length() == 0
							||go.getPginResultsType()==null||go.getPginResultsType().equals("")
							||(go.getPginOtherLine().length()==0)) {
						sesBean.validacionMsg("frm:lstEnfGenO", appBean.getBundle().getString("msg069"), listaMsg);
					}
				}
			}

			if (!isValidG1) {
				sesBean.validacionMsg("frm:lstEnfGen1", appBean.getBundle().getString("msg080"), listaMsg);
			}
			if (!isValidG2) {
				sesBean.validacionMsg("frm:lstEnfGen2", appBean.getBundle().getString("msg080"), listaMsg);
			}
			if (!isValidG3) {
				sesBean.validacionMsg("frm:lstEnfGen3", appBean.getBundle().getString("msg080"), listaMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	public void cancelarSeccion(String seccionActual) {
		try {
			Integer sec = Integer.valueOf(seccionActual);
			if(sec==2){
				listaInfoGenero=new ArrayList<>();
				listaInfoGenero = projectGenderInfoFacade.listarInfoGeneroPorProyecto(proyectoActual.getProjId());
			}
			controlAvance = sec - 1;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}


	public String getTipoBusq() {
		return tipoBusq;
	}

	public void setTipoBusq(String tipoBusq) {
		this.tipoBusq = tipoBusq;
	}

	public Integer getCodSocioSeleccionado() {
		return codSocioSeleccionado;
	}

	public void setCodSocioSeleccionado(Integer codSocioSeleccionado) {
		this.codSocioSeleccionado = codSocioSeleccionado;
	}

	public List<Partner> getListaSociosImpl() {
		return listaSociosImpl;
	}

	public void setListaSociosImpl(List<Partner> listaSociosImpl) {
		this.listaSociosImpl = listaSociosImpl;
	}

	public String getTituloProyecto() {
		return tituloProyecto;
	}

	public void setTituloProyecto(String tituloProyecto) {
		this.tituloProyecto = tituloProyecto;
	}

	public List<Project> getListaProyectosEncontrados() {
		return listaProyectosEncontrados;
	}

	public void setListaProyectosEncontrados(List<Project> listaProyectosEncontrados) {
		this.listaProyectosEncontrados = listaProyectosEncontrados;
	}

	public int getControlAvance() {
		return controlAvance;
	}

	public void setControlAvance(int controlAvance) {
		this.controlAvance = controlAvance;
	}

	public Project getProyectoActual() {
		return proyectoActual;
	}

	public void setProyectoActual(Project proyectoActual) {
		this.proyectoActual = proyectoActual;
	}

	public Boolean getProyectoTienePlan() {
		return proyectoTienePlan;
	}

	public void setProyectoTienePlan(Boolean proyectoTienePlan) {
		this.proyectoTienePlan = proyectoTienePlan;
	}

	public List<CatalogsType> getListaTipoLineasGenero() {
		return listaTipoLineasGenero;
	}

	public void setListaTipoLineasGenero(List<CatalogsType> listaTipoLineasGenero) {
		this.listaTipoLineasGenero = listaTipoLineasGenero;
	}

	public List<ProjectsGenderInfo> getListaInfoGeneroTema1() {
		return listaInfoGeneroTema1;
	}

	public void setListaInfoGeneroTema1(List<ProjectsGenderInfo> listaInfoGeneroTema1) {
		this.listaInfoGeneroTema1 = listaInfoGeneroTema1;
	}

	public List<ProjectsGenderInfo> getListaInfoGeneroTema2() {
		return listaInfoGeneroTema2;
	}

	public void setListaInfoGeneroTema2(List<ProjectsGenderInfo> listaInfoGeneroTema2) {
		this.listaInfoGeneroTema2 = listaInfoGeneroTema2;
	}

	public List<ProjectsGenderInfo> getListaInfoGeneroTema3() {
		return listaInfoGeneroTema3;
	}

	public void setListaInfoGeneroTema3(List<ProjectsGenderInfo> listaInfoGeneroTema3) {
		this.listaInfoGeneroTema3 = listaInfoGeneroTema3;
	}

	public List<ProjectsGenderInfo> getListaInfoGeneroOtroTema() {
		return listaInfoGeneroOtroTema;
	}

	public void setListaInfoGeneroOtroTema(List<ProjectsGenderInfo> listaInfoGeneroOtroTema) {
		this.listaInfoGeneroOtroTema = listaInfoGeneroOtroTema;
	}

	*/
	

}
