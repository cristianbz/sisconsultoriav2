package ec.gob.ambiente.sigma.web.controladores;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

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
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.Meeting;
import ec.gob.ambiente.sigma.ejb.entidades.MeetingsTotal;
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
import ec.gob.ambiente.sigma.ejb.facades.MeetingsTotalFacade;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named("segui")
@ViewScoped
public class ReporteAvanceController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ReporteAvanceController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private TracingFacade tracingFacade;
	@EJB
	private TracingsAmountFacade tracingAmountFacade;
	@EJB
	private TracingsKnowledgeManagementFacade tracingsKnowManagFacade;

	@EJB
	private TracingsProblemFacade tracingsProblemFacade;
	@EJB
	private TracingsProgressFacade tracingsProgressFacade;
	@EJB
	private PartnerFacade partnerFacade;
	@EJB
	private ProjectFacade projectFacade;
	@EJB
	private ProjectsSpecificObjectiveFacade projectsSpeObjFacade;
	@EJB
	private ProjectsActionFacade projectsActionFacade;
	@EJB
	private ProjectsRiskFacade projectsRiskFacade;
	@EJB
	private ProjectsFinancingFacade projectsFinancingFacade;
	@EJB
	private MeetingFacade meetingFacade;
	@EJB
	private MeetingsTotalFacade meetingTotalFacade;

	private String tipoBusq;
	private Integer codSocioSeleccionado;
	private List<Partner> listaSociosImpl;
	private String tituloProyecto;
	private List<Project> listaProyectosEncontrados;
	private List<Tracing> listaReportesEncontrados;

	private int controlAvance;
	private Tracing reporteActual;
	private List<Integer> listaAniosReporte;
	private TracingsAmount montosTotales;
	private List<TracingsAmount> listaMontosObjComp;
	private Integer[] listaColumnaAnios;
	private int anchoDataTableMontos;

	private List<TracingsProgress> listaAcciones;
	private List<TracingsProgress> listaProgresoHistoricoPorAccion;
	private Meeting eventoActual;
	private List<Meeting> listaEventosProyecto;
	private MeetingsTotal totalEvento;
	private Integer tipoTotal;
	private List<Catalog> listaCatalogoTotal;
	private List<MeetingsTotal> listaTotalesDeEvento;

	private List<TracingsKnowledgeManagement> listaPregGestCon;
	private List<Object[]> listaProvinciasEspecial;
	private String[] provinciasSeleccionadas;
	private List<TracingsKnowledgeManagement> listaPregComunicacion;
	private List<TracingsProgress> listaRiesgos;
	private TracingsProblem objetoProblema;
	private List<TracingsProblem> listaProblemas;
	private DonutChartModel donutModelAvanceEjecPres;
	private DonutChartModel donutModelAvanceAcciones;
	private BarChartModel barModelEjecPres;

	public ReporteAvanceController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			controlAvance = 0;
			listaSociosImpl = partnerFacade.listarSociosImplementadores();
			tipoBusq = "SOC";
			reporteActual = new Tracing();
			reporteActual.setCataId(new Catalog());
			listaProvinciasEspecial = new ArrayList<>();
			listaProvinciasEspecial.addAll(appBean.getLstGeoLocProvincias());
			Object[] obj = { "TODAS", 0, "" };
			listaProvinciasEspecial.add(obj);
			eventoActual = new Meeting();
			eventoActual.setProjId(reporteActual.getProjId());
			eventoActual.setGeloId(new GeographicalLocations());
			listaCatalogoTotal = new ArrayList<>();
			tipoTotal = 1;
			totalEvento = new MeetingsTotal();
			totalEvento.setCataId(new Catalog());
			listaTotalesDeEvento = new ArrayList<>();
			listaCatalogoTotal = appBean.getLstCatEveOrigenEtnico();
			listaProyectosEncontrados = new ArrayList<>();
			listaReportesEncontrados = new ArrayList<>();
			codSocioSeleccionado = 0;
			tituloProyecto = "";

			inicializarCharts();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void inicializarCharts() {
		donutModelAvanceEjecPres = new DonutChartModel();
		donutModelAvanceAcciones = new DonutChartModel();
		barModelEjecPres = new BarChartModel();

	}

	private void actualizarChartAvanceEjecPres() {
		try {
			if (reporteActual.getTracBudgetProgress() > 0) {
				ChartData data = new ChartData();

				DonutChartDataSet dataSet = new DonutChartDataSet();
				List<Number> values = new ArrayList<>();
				values.add(reporteActual.getTracBudgetProgress());
				values.add(100 - reporteActual.getTracBudgetProgress());
				dataSet.setData(values);
				List<String> bgColors = new ArrayList<>();
				bgColors.add("rgb(54, 162, 235)");
				bgColors.add("rgb(255, 99, 132)");
				dataSet.setBackgroundColor(bgColors);
				data.addChartDataSet(dataSet);
				List<String> labels = new ArrayList<>();
				labels.add(appBean.getBundle().getString("lbl213"));
				labels.add(appBean.getBundle().getString("lbl296"));
				data.setLabels(labels);
				donutModelAvanceEjecPres.setData(data);
				donutModelAvanceEjecPres.setExtender("chartExtender");
				DonutChartOptions options = new DonutChartOptions();
				Title title = new Title();
				title.setDisplay(true);
				title.setText(appBean.getBundle().getString("lbl194"));
				options.setTitle(title);
				donutModelAvanceEjecPres.setOptions(options);

				ChartData dataBar = new ChartData();
				BarChartDataSet barDataSetPl = new BarChartDataSet();
				barDataSetPl.setLabel(appBean.getBundle().getString("lbl197"));
				barDataSetPl.setBackgroundColor("rgb(40, 196, 214)");
				List<Number> dataValPl = new ArrayList<>();
				BarChartDataSet barDataSetEj = new BarChartDataSet();
				barDataSetEj.setLabel(appBean.getBundle().getString("lbl198"));
				barDataSetEj.setBackgroundColor("rgb(54, 162, 235)");
				List<Number> dataValEj = new ArrayList<>();
				List<String> labelsBar = new ArrayList<>();
				int i = 1;
				for (TracingsAmount obj : listaMontosObjComp) {

					dataValPl.add(obj.getTramAssignedAmount());
					dataValEj.add(obj.getTramExecutedAmount());
					labelsBar.add("" + i);
					i++;
				}
				barDataSetPl.setData(dataValPl);
				barDataSetEj.setData(dataValEj);

				dataBar.addChartDataSet(barDataSetPl);
				dataBar.addChartDataSet(barDataSetEj);
				dataBar.setLabels(labelsBar);
				barModelEjecPres.setData(dataBar);

				BarChartOptions optionsBar = new BarChartOptions();
				CartesianScales cScales = new CartesianScales();
				CartesianLinearAxes linearAxes = new CartesianLinearAxes();
				linearAxes.setOffset(true);
				CartesianLinearTicks ticks = new CartesianLinearTicks();
				ticks.setBeginAtZero(true);
				linearAxes.setTicks(ticks);
				cScales.addYAxesData(linearAxes);
				optionsBar.setScales(cScales);
				Title titleBar = new Title();
				titleBar.setDisplay(true);
				String tit = "";
				if (reporteActual.getProjId().getProjType().equals("PDI")) {
					tit = appBean.getBundle().getString("lbl194") + " " + appBean.getBundle().getString("lbl297");
				} else {
					tit = appBean.getBundle().getString("lbl194") + " " + appBean.getBundle().getString("lbl298");
				}

				titleBar.setText(tit);

				optionsBar.setTitle(titleBar);
				barModelEjecPres.setOptions(optionsBar);
				// barModelEjecPres.setExtender("chartExtender");

			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void actualizarChartAvanceAcciones() {
		try {
			if (reporteActual.getTracActionsProgress() != null && reporteActual.getTracActionsProgress() > 0) {
				ChartData data = new ChartData();
				DonutChartDataSet dataSet = new DonutChartDataSet();
				List<Number> values = new ArrayList<>();
				values.add(reporteActual.getTracActionsProgress());
				values.add(100 - reporteActual.getTracActionsProgress());
				dataSet.setData(values);
				List<String> bgColors = new ArrayList<>();
				bgColors.add("rgb(54, 162, 235)");
				bgColors.add("rgb(255, 99, 132)");
				dataSet.setBackgroundColor(bgColors);
				data.addChartDataSet(dataSet);
				List<String> labels = new ArrayList<>();
				labels.add(appBean.getBundle().getString("lbl213"));
				labels.add(appBean.getBundle().getString("lbl296"));
				data.setLabels(labels);
				donutModelAvanceAcciones.setData(data);
				donutModelAvanceAcciones.setExtender("chartExtender");
				DonutChartOptions options = new DonutChartOptions();
				Title title = new Title();
				title.setDisplay(true);
				title.setText(appBean.getBundle().getString("lbl209"));
				options.setTitle(title);
				donutModelAvanceAcciones.setOptions(options);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarProyectosyReportes() {
		try {
			listaProyectosEncontrados = new ArrayList<>();
			listaReportesEncontrados = new ArrayList<>();
			List<String[]> listaMsg = validarBusquedaProyectosReportes();
			if (listaMsg.isEmpty()) {
				if (tipoBusq.equals("SOC")) {
					listaProyectosEncontrados = projectFacade.listarProyectosPorIdSocioImpl(codSocioSeleccionado);
					// listaReportesEncontrados =
					// tracingFacade.listarReportesPorSocio(codSocioSeleccionado);
				} else if (tipoBusq.equals("TIT")) {
					listaProyectosEncontrados = projectFacade.listarProyectosPorTextoTitulo(tituloProyecto);
					// listaReportesEncontrados =
					// tracingFacade.listarReportesPorTituloProyecto(tituloProyecto);
				}
				/*
				 * if (listaProyectosEncontrados.isEmpty() &&
				 * listaReportesEncontrados.isEmpty()) {
				 * sesBean.addSuccessMessage(appBean.getBundle().getString(
				 * "msg064")); }
				 */
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

	public void buscarProyectosyReportesDeSocio(String rol) {
		try {
			listaProyectosEncontrados = new ArrayList<>();
			listaReportesEncontrados = new ArrayList<>();

			List<Partner> lst = partnerFacade
					.listarSocioImplementadorPorIdentificacion(sesBean.obtenerIdUsuarioSocio());
			if (!lst.isEmpty()) {
				
				if(rol.equals("SIGMA_SOCIO")){
					listaProyectosEncontrados = projectFacade.listarProyectosPorIdSocioImpl(lst.get(0).getPartId());
				}else if(rol.equals("SIGMA_IMPLEMENTADOR")){
					listaProyectosEncontrados = projectFacade.listarProyectosPorIdSocioImplYUsuarioCreadorOCodigoProyecto(lst.get(0).getPartId(),sesBean.getUsername(),sesBean.obtenerProjIdDeUsuarioConRolImplementador());
				}
				
				// listaReportesEncontrados =
				// tracingFacade.listarReportesPorSocio(lst.get(0).getPartId());
			}

			/*
			 * if (listaProyectosEncontrados.isEmpty() &&
			 * listaReportesEncontrados.isEmpty()) {
			 * sesBean.addSuccessMessage(appBean.getBundle().getString("msg064")
			 * ); }
			 */
			if (listaProyectosEncontrados.isEmpty()) {
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg064"));
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarBusquedaProyectosReportes() {
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

	public void crearNuevoReporte(Project proyecto) {
		try {
			boolean val = true;
			String valMsg = "";
			if (proyecto.getProjRegisterStatus() == null || !proyecto.getProjRegisterStatus().equals("V")) {
				val = false;
				valMsg = appBean.getBundle().getString("msg104");
			}
			if (val) {
				reporteActual = new Tracing();
				reporteActual.setTracRegisterStatus("C");
				reporteActual.setProjId(proyecto);
				reporteActual.setUserId(sesBean.getUsuarioLogeado());
				reporteActual.setTracBudgetProgress(0);
				reporteActual.setTracExecutedBudget(new BigDecimal("0"));
				reporteActual = tracingFacade.crear(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
				// seccion 2
				List<ProjectsFinancing> listaMontosAsignados = projectsFinancingFacade
						.listarMontosFinanciamientoPorProyecto(reporteActual.getProjId().getProjId());
				tracingAmountFacade.crearMontosReporte(reporteActual.getTracId(),
						projectsFinancingFacade.obtenerMontoTotalAsignado(listaMontosAsignados),
						projectsFinancingFacade.listarIdsObjAsignados(listaMontosAsignados),
						projectsFinancingFacade.listarMontosObjAsignados(listaMontosAsignados),
						appBean.getLstIdsCatCostosGastos());
				// seccion 3
				List<ProjectsAction> listaAcciones = new ArrayList<>();
				listaAcciones = projectsActionFacade.listarIndicadoresPorProyecto(proyecto.getProjId());
				/*
				 * if (proyecto.getProjType().equals("PDI")) { listaAcciones =
				 * projectsActionFacade.listarAccionesPorPlanDeImplementacion(
				 * proyecto.getProjId()); } else { listaAcciones =
				 * projectsActionFacade.listarAccionesPorProgramaProyecto(
				 * proyecto.getProjId()); }
				 */
				tracingsProgressFacade.crearAccionesPorReporte(reporteActual.getTracId(),
						projectsActionFacade.listaIdsAcciones(listaAcciones));

				// sección 4

				// seccion 5
				tracingsKnowManagFacade.crearPreguntasGestionConocimiento(reporteActual.getTracId(),
						appBean.getLstCatPregGestCon(), proyecto.getProjType());
				// seccion 6
				// tracingsPartFacade.crearRegistroParticipacion(reporteActual.getTracId());
				// seccion 7
				tracingsKnowManagFacade.crearPreguntasComunicacion(reporteActual.getTracId(),
						appBean.getLstCatPregCom(), proyecto.getProjType());

				// seccion 8
				List<ProjectsRisk> listaRiesgos = projectsRiskFacade.listarRiesgosPorProyecto(proyecto.getProjId());
				tracingsProgressFacade.crearRiesgosPorReporte(reporteActual.getTracId(),
						projectsRiskFacade.listaIdsRiesgos(listaRiesgos));
				// seccion 9
				cargarListaAnios(proyecto.getAcplId());
				cargarSeccion01();
			} else {
				sesBean.addErrorMessage(valMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarReportesDeProyecto(Project proyecto) {
		try {

			listaReportesEncontrados = new ArrayList<>();
			listaReportesEncontrados = tracingFacade.listarReportesPorProyecto(proyecto.getProjId());
			if (listaReportesEncontrados.isEmpty()) {
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg137"));
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void editarReporte(Tracing rep) {
		try {
			reporteActual = rep;
			cargarListaAnios(rep.getProjId().getAcplId());
			cargarSeccion01();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void verReporteValidado(Tracing rep) {
		try {
			reporteActual = rep;
			cargarSeccion01();
			cargarSeccion02(false);
			actualizarChartAvanceEjecPres();
			cargarSeccion03();
			actualizarChartAvanceAcciones();
			cargarSeccion04();
			cargarSeccion05();
			cargarSeccion06();
			cargarSeccion07();
			cargarSeccion08();
			cargarSeccion09();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarReporte(Tracing rep) {
		try {
			Project proj = rep.getProjId();
			rep.setTracStatus(false);
			tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), rep);
			buscarReportesDeProyecto(proj);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg152"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void habilitarEdicion(Tracing rep) {
		try {
			Project proj = rep.getProjId();
			rep.setTracRegisterStatus("G");
			tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), rep);
			buscarReportesDeProyecto(proj);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg154"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void cargarListaAnios(ActionPlan ap) {
		listaAniosReporte = new ArrayList<>();
		for (int i = (ap.getAcplStartDate().getYear() + 1900); i <= (ap.getAcplFinishDate().getYear() + 1900); i++) {
			listaAniosReporte.add(i);
		}
	}

	public void cargarSeccion01() {
		try {
			if (reporteActual.getCataId() == null) {
				reporteActual.setCataId(new Catalog());
			}
			controlAvance = 1;
			// ya está cargado el objeto reporteActual
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion01() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (reporteActual.getTracYear() == null || reporteActual.getTracYear() == 0) {
				sesBean.validacionMsg("frm:tracYear", appBean.getBundle().getString("lbl192")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (reporteActual.getCataId() == null || reporteActual.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:tracSemester", appBean.getBundle().getString("lbl193")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

			if (reporteActual.getTracYear() != null && reporteActual.getCataId() != null
					&& reporteActual.getCataId().getCataId() != null) {

				if (tracingFacade.existeReportePorSocioYPeriodo(reporteActual.getProjId().getProjId(),
						reporteActual.getTracYear(), reporteActual.getCataId().getCataId(),
						reporteActual.getTracId())) {
					if (reporteActual.getTracId() == null) {
						sesBean.validacionMsg("frm:tracSemester", appBean.getBundle().getString("msg134"), listaMsg);
					}
				}
				String projPlazoDesdAnio = reporteActual.getProjId().getProjTermFrom().substring(0, 4);
				String projPlazoHastAnio = reporteActual.getProjId().getProjTermTo().substring(0, 4);
				String projPlazoDesdMes = reporteActual.getProjId().getProjTermFrom().substring(5, 7);
				String projPlazoHastMes = reporteActual.getProjId().getProjTermTo().substring(5, 7);
				Integer codCatPrimerSemestre = appBean.getLstCatTiposPeriodosReporte().get(0).getCataId();
				if (reporteActual.getTracYear() >= Integer.valueOf(projPlazoDesdAnio)
						&& reporteActual.getTracYear() <= Integer.valueOf(projPlazoHastAnio)) {

					if (reporteActual.getTracYear() == Integer.valueOf(projPlazoDesdAnio)
							&& Integer.valueOf(projPlazoDesdMes) > 6
							&& reporteActual.getCataId().getCataId() == codCatPrimerSemestre) {
						sesBean.validacionMsg("frm:tracSemester", appBean.getBundle().getString("msg136"), listaMsg);
					}
					if (reporteActual.getTracYear() == Integer.valueOf(projPlazoHastAnio)
							&& Integer.valueOf(projPlazoHastMes) < 7
							&& reporteActual.getCataId().getCataId() != codCatPrimerSemestre) {
						sesBean.validacionMsg("frm:tracSemester", appBean.getBundle().getString("msg136"), listaMsg);
					}
				} else {
					sesBean.validacionMsg("frm:tracYear", appBean.getBundle().getString("msg135"), listaMsg);
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion01(String op) {
		try {
			List<String[]> listaMsg = validarSeccion01();
			if (listaMsg.isEmpty()) {

				reporteActual = tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (reporteActual.getTracRegisterStatus().equals("C")) {
					int anioAnt = 0;
					int codSemAnt = 0;
					if (reporteActual.getCataId().getCataId().equals(appBean.getCodUltimoSemestre())) {
						anioAnt = reporteActual.getTracYear();
						codSemAnt = appBean.getCodPrimerSemestre();
					} else {
						anioAnt = reporteActual.getTracYear() - 1;
						codSemAnt = appBean.getCodUltimoSemestre();
					}
					tracingsProgressFacade.actualizarCamposDeAccionesConAvanceAlCienPorciento(reporteActual.getTracId(),
							anioAnt, codSemAnt, appBean.getBundle().getString("lbl426"));
				}
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion02(true);
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

	public void cargarSeccion02(boolean paraEditar) {
		try {
			List<TracingsAmount> listaMontos = tracingAmountFacade.listarMontosDeReporte(reporteActual.getTracId());
			montosTotales = tracingAmountFacade.obtenerMontosTotalesDeReporte(listaMontos);
			listaMontosObjComp = tracingAmountFacade.listarMontosDeObjetivosComp(listaMontos);
			for (TracingsAmount ta : listaMontosObjComp) {
				List<TracingsAmount> lst = tracingAmountFacade.listarMontosCategPorObjetivosComp(listaMontos,
						ta.getPsobId().getPsobId());
				ta.setListaPorCategorias(lst);
			}

			if (validarNumeroAniosProyecto(reporteActual.getProjId())) {
				if (reporteActual.getTracRegisterStatus().equals("C")) {
					// en estado C, se ejecuta solo la primera vez
					int anioAnterior = 0;
					int semestre = 0;
					if (appBean.getCodUltimoSemestre().equals(reporteActual.getCataId().getCataId())) {
						anioAnterior = reporteActual.getTracYear();
						semestre = appBean.getCodPrimerSemestre();
					} else {
						anioAnterior = reporteActual.getTracYear() - 1;
						semestre = appBean.getCodUltimoSemestre();
					}

					List<TracingsAmount> listaMontosReporteAnterior = tracingAmountFacade
							.listarMontosDeReporteValidadosPorProyectoAnioSemestre(
									reporteActual.getProjId().getProjId(), anioAnterior, semestre);
					if (listaMontosReporteAnterior != null && !listaMontosReporteAnterior.isEmpty()) {

						for (TracingsAmount ta : listaMontosObjComp) {
							for (TracingsAmount tad : ta.getListaPorCategorias()) {
								if (tad.getPsobId() != null && tad.getCataId() != null) {
									for (TracingsAmount ant : listaMontosReporteAnterior) {
										if (ant.getPsobId() != null && ant.getCataId() != null
												&& tad.getPsobId().getPsobId().equals(ant.getPsobId().getPsobId())
												&& tad.getCataId().getCataId().equals(ant.getCataId().getCataId())) {
											BigDecimal tramTotal = new BigDecimal("0");
											if (ant.getTramYear1() != null) {
												tad.setTramYear1(ant.getTramYear1());
												tramTotal = tramTotal.add(ant.getTramYear1());
											}
											if (ant.getTramYear2() != null) {
												tad.setTramYear2(ant.getTramYear2());
												tramTotal = tramTotal.add(ant.getTramYear2());
											}
											if (ant.getTramYear3() != null) {
												tad.setTramYear3(ant.getTramYear3());
												tramTotal = tramTotal.add(ant.getTramYear3());
											}
											if (ant.getTramYear4() != null) {
												tad.setTramYear4(ant.getTramYear4());
												tramTotal = tramTotal.add(ant.getTramYear4());
											}
											if (ant.getTramYear5() != null) {
												tad.setTramYear5(ant.getTramYear5());
												tramTotal = tramTotal.add(ant.getTramYear5());
											}
											if (ant.getTramYear6() != null) {
												tad.setTramYear6(ant.getTramYear6());
												tramTotal = tramTotal.add(ant.getTramYear6());
											}
											if (ant.getTramYear7() != null) {
												tad.setTramYear7(ant.getTramYear7());
												tramTotal = tramTotal.add(ant.getTramYear7());
											}
											if (ant.getTramYear8() != null) {
												tad.setTramYear8(ant.getTramYear8());
												tramTotal = tramTotal.add(ant.getTramYear8());
											}
											if (ant.getTramYear9() != null) {
												tad.setTramYear9(ant.getTramYear9());
												tramTotal = tramTotal.add(ant.getTramYear9());
											}
											if (ant.getTramYear10() != null) {
												tad.setTramYear10(ant.getTramYear10());
												tramTotal = tramTotal.add(ant.getTramYear10());
											}

											tad.setTramTotal(tramTotal);

										}
									}
								}
							}
						}
						int idxObj = 0;
						for (TracingsAmount ta : listaMontosObjComp) {
							PrimeFaces.current().executeScript(
									"repoEjecPresCalcTotalCat('frm:rep:" + idxObj + ":lstObjCat:0:year1_input');");
							PrimeFaces.current().executeScript(
									"repoEjecPresCalcTotalCat('frm:rep:" + idxObj + ":lstObjCat:1:year1_input');");

							idxObj++;
						}
					}
				}

			}

			if (paraEditar) {
				PrimeFaces.current().executeScript("repoEjecPresCargarValoresEnLabels();");
				controlAvance = 2;
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private boolean validarNumeroAniosProyecto(Project proyecto) {
		boolean f = false;
		try {
			Integer a1 = Integer.valueOf(proyecto.getProjTermFrom().substring(0, 4));
			Integer a2 = Integer.valueOf(proyecto.getProjTermTo().substring(0, 4));
			anchoDataTableMontos=425+(((a2-a1)+1)*70);
			if ((a2 - a1) <= 9) {
				f = true;
				listaColumnaAnios = new Integer[10];
				int c = 0;
				for (int i = a1; i <= a2; i++) {
					listaColumnaAnios[c] = i;
					c++;
				}
				if (c < 10) {
					for (int j = c; j < 10; j++) {
						listaColumnaAnios[j] = null;
					}
				}

			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return f;
	}

	private List<String[]> validarSeccion02() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			actualizarChartAvanceEjecPres();
			if (reporteActual.getTracBudgetProgress() < 0 || reporteActual.getTracBudgetProgress() > 100) {
				sesBean.validacionMsg("frm:avancePresTotal", appBean.getBundle().getString("msg116"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion02(String op) {
		try {

			List<String[]> listaMsg = validarSeccion02();
			if (listaMsg.isEmpty()) {
				reporteActual.setTracRegisterStatus("P");
				tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
				tracingAmountFacade.edit(montosTotales);
				tracingAmountFacade.editarListaMontos(listaMontosObjComp);
				BigDecimal totalExecuted = new BigDecimal("0");
				for (TracingsAmount ta : listaMontosObjComp) {
					if (ta.getTramExecutedAmount() != null) {
						totalExecuted = totalExecuted.add(ta.getTramExecutedAmount());
					}
					tracingAmountFacade.editarListaMontos(ta.getListaPorCategorias());

				}
				reporteActual.setTracExecutedBudget(totalExecuted);
				tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion03();
				} else {
					PrimeFaces.current().executeScript("repoEjecPresCargarValoresEnLabels();");
				}
			} else {
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
				PrimeFaces.current().executeScript("repoEjecPresCargarValoresEnLabels();");
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion03() {
		try {
			listaAcciones = tracingsProgressFacade.listarAvanceAccionesPorReporte(reporteActual.getTracId());
			listaAcciones = tracingsProgressFacade.listarAvanceAccionesPorReporteConInfoExtra(listaAcciones,
					tracingsProgressFacade.listarAvanceHistoricoPorProyecto(reporteActual.getProjId().getProjId(),
							reporteActual.getTracId()));
			controlAvance = 3;

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion03() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			actualizarChartAvanceAcciones();
			if (reporteActual.getTracActionsProgress() == null || reporteActual.getTracActionsProgress() <= 0
					|| reporteActual.getTracActionsProgress() > 1000) {
				sesBean.validacionMsg("frm:avanceTotal", appBean.getBundle().getString("msg116"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion03(String op) {
		try {
			List<String[]> listaMsg = validarSeccion03();
			if (listaMsg.isEmpty()) {
				listaAcciones = tracingsProgressFacade.calcularAvanceDeAcciones(listaAcciones);
				reporteActual
						.setTracActionsProgress(tracingsProgressFacade.calcularAvanceTotalDeAcciones(listaAcciones));
				tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
				tracingsProgressFacade.guardarListaAvance(listaAcciones);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion04();
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

	public void mostrarProgresoHistoricoDeAccionIndicador(TracingsProgress p) {
		try {
			listaProgresoHistoricoPorAccion = new ArrayList<>();
			listaProgresoHistoricoPorAccion = tracingsProgressFacade.listarAvanceHistoricoPorAccionIndicador(
					p.getPracId().getPracId(), reporteActual.getProjId().getProjId(), reporteActual.getTracId());
			PrimeFaces.current().executeScript("PF('dlgProgresoHistorico').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion04() {
		try {
			listaEventosProyecto = new ArrayList<>();
			int semestre = 1;
			if (appBean.getCodUltimoSemestre().equals(reporteActual.getCataId().getCataId())) {
				semestre = 2;
			}
			listaEventosProyecto = meetingFacade.listaEventosPorProyectoySemestre(reporteActual.getProjId().getProjId(),
					reporteActual.getTracYear(), semestre);
			eventoActual = new Meeting();
			eventoActual.setProjId(reporteActual.getProjId());
			eventoActual.setGeloId(new GeographicalLocations());
			tipoTotal = 1;
			totalEvento = new MeetingsTotal();
			totalEvento.setCataId(new Catalog());
			controlAvance = 4;

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoEvento() {
		try {
			eventoActual = new Meeting();
			eventoActual.setProjId(reporteActual.getProjId());
			eventoActual.setGeloId(new GeographicalLocations());
			tipoTotal = 1;
			totalEvento = new MeetingsTotal();
			totalEvento.setCataId(new Catalog());
			listaTotalesDeEvento=new ArrayList<>();
			PrimeFaces.current().executeScript("PF('dlgEvento').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void actualizarCatalogoTotal() {
		try {
			if (tipoTotal != null) {
				listaCatalogoTotal = new ArrayList<>();
				if (tipoTotal == 1) {
					//
					listaCatalogoTotal = appBean.getLstCatEveOrigenEtnico();
				} else {
					//
					listaCatalogoTotal = appBean.getLstCatEveNacionalidadesInd();
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void aniadirObjetoTotal() {
		try {
			if(totalEvento.getMetoNumber1()==null){
				totalEvento.setMetoNumber1(0);
			}
			if(totalEvento.getMetoNumber2()==null){
				totalEvento.setMetoNumber2(0);
			}
			if(totalEvento.getMetoNumber3()==null){
				totalEvento.setMetoNumber3(0);
			}
			if (totalEvento.getCataId() != null) {
				totalEvento.setCataId(appBean.obtenerObjetoCatalogoPorIdLista(totalEvento.getCataId().getCataId(),
						listaCatalogoTotal));
				listaTotalesDeEvento.add(totalEvento);
				totalEvento = new MeetingsTotal();
				totalEvento.setCataId(new Catalog());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarEvento() {
		try {
			List<String[]> listaMsg = validacionFormularioSimplificado(eventoActual);
			if (listaMsg.isEmpty()) {
				if (eventoActual.getMeetId() == null) {
					eventoActual = meetingFacade.crearyObtenerEvento(sesBean.getUsuarioLogeado().getUserName(),
							eventoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg101"));
				} else {
					meetingFacade.editarEvento(sesBean.getUsuarioLogeado().getUserName(), eventoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg102"));
				}
				crearTotalesEvento(eventoActual);
				PrimeFaces.current().executeScript("PF('dlgEvento').hide()");
				cargarSeccion04();
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

	public void crearTotalesEvento(Meeting eve) {
		try {
			for (MeetingsTotal t : listaTotalesDeEvento) {
				t.setMeetId(eve);
				t.setMetoTotal(eve.getMeetParticipantsNumber());
				if (t.getMetoId() == null) {
					meetingTotalFacade.create(t);
				} else {
					meetingTotalFacade.edit(t);
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarTotalEvento(Integer idx) {
		try {

			List<MeetingsTotal> newList = new ArrayList<>();
			for (int i = 0; i < listaTotalesDeEvento.size(); i++) {
				if (i != idx.intValue()) {
					newList.add(listaTotalesDeEvento.get(i));
				}

			}
			listaTotalesDeEvento = newList;

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validacionFormularioSimplificado(Meeting evento) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (evento.getMeetName().length() == 0) {
				sesBean.validacionMsg("frm:eveName", appBean.getBundle().getString("lbl272")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (evento.getMeetDateStart() == null) {
				sesBean.validacionMsg("frm:eveDateStart", appBean.getBundle().getString("lbl003")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (evento.getMeetDateEnd() == null) {
				sesBean.validacionMsg("frm:eveDateEnd", appBean.getBundle().getString("lbl004")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (evento.getGeloId() == null || evento.getGeloId().getGeloId() == null
					|| evento.getGeloId().getGeloId() == -1) {
				sesBean.validacionMsg("frm:eveGeo", appBean.getBundle().getString("lbl063")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			
			
			if(evento.getMeetDateStart()!=null&&evento.getMeetDateEnd()!=null) {
				int mesIni=0;
				int mesFin=0;
				int diaFin=0;
				if(appBean.getCodPrimerSemestre().equals(reporteActual.getCataId().getCataId())) {
					mesIni=0;
					mesFin=5;
					diaFin=30;
				}else{
					mesIni=6;
					mesFin=11;
					diaFin=31;
				}
				Calendar ini=java.util.Calendar.getInstance();
				ini.set(Calendar.YEAR, reporteActual.getTracYear());
				ini.set(Calendar.MONTH, mesIni);
				ini.set(Calendar.DAY_OF_MONTH, 1);
				Calendar fin=java.util.Calendar.getInstance();
				fin.set(Calendar.YEAR, reporteActual.getTracYear());
				fin.set(Calendar.MONTH, mesFin);
				fin.set(Calendar.DAY_OF_MONTH,diaFin);
				fin.set(Calendar.HOUR_OF_DAY, 23);
				fin.set(Calendar.MINUTE, 59);
				if(evento.getMeetDateStart().compareTo(ini.getTime())>=0&&evento.getMeetDateStart().compareTo(fin.getTime())<=0){
					if(evento.getMeetDateStart().compareTo(evento.getMeetDateEnd())>0){
						sesBean.validacionMsg("frm:eveDateStart", appBean.getBundle().getString("msg167"), listaMsg);
					}
					
				}else{
					sesBean.validacionMsg("frm:eveDateStart", appBean.getBundle().getString("msg184"), listaMsg);
				}
			}
			if (evento.getMeetTotalMale() == null) {
				evento.setMeetTotalMale(0);
			}
			if (evento.getMeetTotalFemale() == null) {
				evento.setMeetTotalFemale(0);
			}
			if (evento.getMeetTotalAge18to25() == null) {
				evento.setMeetTotalAge18to25(0);
			}
			if (evento.getMeetTotalAge26to35() == null) {
				evento.setMeetTotalAge26to35(0);
			}
			if (evento.getMeetTotalAge36to45() == null) {
				evento.setMeetTotalAge36to45(0);
			}
			if (evento.getMeetTotalAge46to55() == null) {
				evento.setMeetTotalAge46to55(0);
			}
			if (evento.getMeetTotalAge56to64() == null) {
				evento.setMeetTotalAge56to64(0);
			}
			if (evento.getMeetTotalAge65andmore() == null) {
				evento.setMeetTotalAge65andmore(0);
			}
			if (evento.getMeetTotalIndigenous() == null) {
				evento.setMeetTotalIndigenous(0);
			}
			int total = 0;
			if (evento.getMeetParticipantsNumber() != null && evento.getMeetParticipantsNumber() > 0) {
				total = evento.getMeetParticipantsNumber();
				/*
				 * if(total<30){ sesBean.validacionMsg("frm:totalPart",
				 * appBean.getBundle().getString("msg100"), listaMsg); }
				 */
			} else {
				sesBean.validacionMsg("frm:totalPart", appBean.getBundle().getString("msg095"), listaMsg);
			}
			int sumaSexo = evento.getMeetTotalMale() + evento.getMeetTotalFemale();
			int sumaRangoEdad = evento.getMeetTotalAge18to25() + evento.getMeetTotalAge26to35()
					+ evento.getMeetTotalAge36to45() + evento.getMeetTotalAge46to55() + evento.getMeetTotalAge56to64()
					+ evento.getMeetTotalAge65andmore();

			if (total != sumaSexo) {
				sesBean.validacionMsg("frm:totalMasc", appBean.getBundle().getString("msg096"), listaMsg);
			}
			if (total != sumaRangoEdad) {
				sesBean.validacionMsg("frm:total18a25", appBean.getBundle().getString("msg097"), listaMsg);
			}
			if (total < evento.getMeetTotalIndigenous()) {
				sesBean.validacionMsg("frm:totalIndig", appBean.getBundle().getString("msg098"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void seleccionarEventoAEditar(Meeting evento) {
		eventoActual = evento;
		listaTotalesDeEvento=new ArrayList<>();
		listaTotalesDeEvento = meetingTotalFacade.listarTotalesPorEvento(eventoActual.getMeetId());
		PrimeFaces.current().executeScript("PF('dlgEvento').show()");
	}

	public void eliminarEvento(Meeting evento) {
		try {
			meetingFacade.eliminarLogico(sesBean.getUsuarioLogeado().getUserName(), evento);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg103"));
			cargarSeccion04();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion04() {
		List<String[]> listaMsg = new ArrayList<>();
		try {

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion04(String op) {
		try {
			List<String[]> listaMsg = validarSeccion04();
			if (listaMsg.isEmpty()) {

				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion05();
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

	public void cargarSeccion05() {
		try {
			listaPregGestCon = tracingsKnowManagFacade
					.listarPreguntasGestionConocimientoPorReporte(reporteActual.getTracId());
			controlAvance = 5;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion05() {
		List<String[]> listaMsg = new ArrayList<>();
		try {

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion05(String op) {
		try {
			List<String[]> listaMsg = validarSeccion05();
			if (listaMsg.isEmpty()) {
				tracingsKnowManagFacade.editarListaPreguntas(listaPregGestCon);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion06();
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

	public void cargarSeccion06anterior() {
		try {
			/*
			 * objetoParticipacion =
			 * tracingsPartFacade.obtenerRegistroParticipacion(reporteActual.
			 * getTracId()); List<TracingsParticipationDetail> listaDetalles =
			 * tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporte(reporteActual.getTracId())
			 * ; List<TracingsParticipationDetail> listaDetallesAccInf =
			 * tracingsPartDetFacade
			 * .obtenerListaMecanismosAccesoInf(listaDetalles);
			 * listaPregPartMecanismosAccesoInf = new ArrayList<>(); for
			 * (Catalog c : appBean.getLstCatMecAccInf()) { boolean flag = true;
			 * for (TracingsParticipationDetail d : listaDetallesAccInf) { if
			 * (d.getCataId() == c) { flag = false;
			 * listaPregPartMecanismosAccesoInf.add(d); } } if (flag == true) {
			 * TracingsParticipationDetail pd = new
			 * TracingsParticipationDetail(); pd.setTracId(reporteActual);
			 * pd.setCataId(c); listaPregPartMecanismosAccesoInf.add(pd); } }
			 * inicializarObjetosPartDetalle("MINVA");
			 * inicializarObjetosPartDetalle("SOCAC");
			 * inicializarObjetosPartDetalle("SOCIN");
			 * inicializarObjetosPartDetalle("CLPIA");
			 * inicializarObjetosPartDetalle("CLPIR");
			 * inicializarObjetosPartDetalle("RCACT");
			 * listaPregPartMecanismosInvActores =
			 * tracingsPartDetFacade.obtenerListaMecanismosInvActores(
			 * listaDetalles); listaPregPartSocializacionActores =
			 * tracingsPartDetFacade.obtenerListaSocializacionActores(
			 * listaDetalles); listaPregPartSocializacionInfo =
			 * tracingsPartDetFacade.obtenerListaSocializacionInfo(listaDetalles
			 * ); listaPregPartCLPIActores =
			 * tracingsPartDetFacade.obtenerListaCLPIActores(listaDetalles);
			 * listaPregPartCLPIResultados =
			 * tracingsPartDetFacade.obtenerListaCLPIResultados(listaDetalles);
			 * listaPregPartRendicionCuentasActores = tracingsPartDetFacade
			 * .obtenerListaRendicionCuentasActores(listaDetalles);
			 */
			controlAvance = 6;

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjetosPartDetalle(String tipo) {
		/*
		 * if (tipo.equals("MINVA")) { partMecanismosInvActores = new
		 * TracingsParticipationDetail();
		 * partMecanismosInvActores.setTracId(reporteActual);
		 * partMecanismosInvActores.setTpdeType("MINVA");
		 * partMecanismosInvActores.setCataId(new Catalog()); //
		 * partMecanismosInvActores.setGeloId(new GeographicalLocations());
		 * provinciasSeleccionadas = new String[0]; } else if
		 * (tipo.equals("SOCAC")) { partSocializacionActores = new
		 * TracingsParticipationDetail();
		 * partSocializacionActores.setTracId(reporteActual);
		 * partSocializacionActores.setTpdeType("SOCAC");
		 * partSocializacionActores.setCataId(new Catalog()); } else if
		 * (tipo.equals("SOCIN")) { partSocializacionInfo = new
		 * TracingsParticipationDetail();
		 * partSocializacionInfo.setTracId(reporteActual);
		 * partSocializacionInfo.setTpdeType("SOCIN");
		 * partSocializacionInfo.setCataId(new Catalog()); } else if
		 * (tipo.equals("CLPIA")) { partCLPIActores = new
		 * TracingsParticipationDetail();
		 * partCLPIActores.setTracId(reporteActual);
		 * partCLPIActores.setTpdeType("CLPIA"); } else if
		 * (tipo.equals("CLPIR")) { partCLPIResultados = new
		 * TracingsParticipationDetail();
		 * partCLPIResultados.setTracId(reporteActual);
		 * partCLPIResultados.setTpdeType("CLPIR"); } else if
		 * (tipo.equals("RCACT")) { partRendicionCuentasActores = new
		 * TracingsParticipationDetail();
		 * partRendicionCuentasActores.setTracId(reporteActual);
		 * partRendicionCuentasActores.setTpdeType("RCACT"); }
		 */
	}

	public void guardarDetallePart(String tipo) {
		try {
			/*
			 * if (tipo.equals("MINVA")) {
			 * partMecanismosInvActores.setTpdeText2(appBean.
			 * obtenerTextoDesdeArreglo(provinciasSeleccionadas));
			 * tracingsPartDetFacade.create(partMecanismosInvActores);
			 * listaPregPartMecanismosInvActores = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } else if (tipo.equals("SOCAC")) {
			 * tracingsPartDetFacade.create(partSocializacionActores);
			 * listaPregPartSocializacionActores = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } else if (tipo.equals("SOCIN")) {
			 * tracingsPartDetFacade.create(partSocializacionInfo);
			 * listaPregPartSocializacionInfo = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } else if (tipo.equals("CLPIA")) {
			 * tracingsPartDetFacade.create(partCLPIActores);
			 * listaPregPartCLPIActores = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } else if (tipo.equals("CLPIR")) {
			 * tracingsPartDetFacade.create(partCLPIResultados);
			 * listaPregPartCLPIResultados = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } else if (tipo.equals("RCACT")) {
			 * tracingsPartDetFacade.create(partRendicionCuentasActores);
			 * listaPregPartRendicionCuentasActores = tracingsPartDetFacade
			 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.
			 * getTracId(), tipo);
			 * 
			 * } inicializarObjetosPartDetalle(tipo);
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * public void eliminarDetallePart(TracingsParticipationDetail objPart,
	 * String tipo) { try { tracingsPartDetFacade.eliminarLogico(objPart); if
	 * (tipo.equals("MINVA")) { listaPregPartMecanismosInvActores =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } else if (tipo.equals("SOCAC")) { listaPregPartSocializacionActores =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } else if (tipo.equals("SOCIN")) { listaPregPartSocializacionInfo =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } else if (tipo.equals("CLPIA")) { listaPregPartCLPIActores =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } else if (tipo.equals("CLPIR")) { listaPregPartCLPIResultados =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } else if (tipo.equals("RCACT")) { listaPregPartRendicionCuentasActores =
	 * tracingsPartDetFacade
	 * .listarDetallesParticipacionPorReporteYTipo(reporteActual.getTracId(),
	 * tipo);
	 * 
	 * } } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 */

	private List<String[]> validarSeccion06anterior() {
		List<String[]> listaMsg = new ArrayList<>();
		try {

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion06anterior(String op) {
		try {
			/*
			 * List<String[]> listaMsg = validarSeccion06(); if
			 * (listaMsg.isEmpty()) {
			 * tracingsPartFacade.edit(objetoParticipacion);
			 * tracingsPartDetFacade.guardarDetallesParticipacionMecAccInf(
			 * listaPregPartMecanismosAccesoInf);
			 * sesBean.addSuccessMessage(appBean.getBundle().getString("msg063")
			 * ); if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
			 * cargarSeccion07(); } } else { for (String[] s : listaMsg) {
			 * sesBean.mensajeErrorComponente(s[0], s[1]); }
			 * PrimeFaces.current().focus(listaMsg.get(0)[0]); }
			 */
			if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
				cargarSeccion06anterior();
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion06() {
		try {
			listaPregComunicacion = tracingsKnowManagFacade
					.listarPreguntasComunicacionPorReporte(reporteActual.getTracId());
			controlAvance = 6;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion06() {
		List<String[]> listaMsg = new ArrayList<>();
		try {

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion06(String op) {
		try {
			List<String[]> listaMsg = validarSeccion06();
			if (listaMsg.isEmpty()) {
				tracingsKnowManagFacade.editarListaPreguntas(listaPregComunicacion);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion07();
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

	public void cargarSeccion07() {
		try {
			listaRiesgos = tracingsProgressFacade.listarAvanceRiesgoPorReporte(reporteActual.getTracId());
			controlAvance = 7;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void limpiarDetalleRiesgo(TracingsProgress riesgo){
		try{
			if(!riesgo.getTrprRiskIspresent()){
				riesgo.setTrprRiskStatus(null);
				riesgo.setTrprDetails("");
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion07() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			int fila = 1;
			for (TracingsProgress r : listaRiesgos) {
				if (r.getTrprRiskIspresent()) {
					if (r.getTrprRiskStatus() == null) {
						sesBean.validacionMsg("frm:lstRiesgos",
								appBean.getBundle().getString("msg188") + " en la fila " + fila, listaMsg);
					}
					if (r.getTrprDetails()==null||r.getTrprDetails().length() == 0) {
						sesBean.validacionMsg("frm:lstRiesgos",
								appBean.getBundle().getString("msg189") + " en la fila " + fila, listaMsg);
					}
				}
				fila++;
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarSeccion07(String op) {
		try {
			List<String[]> listaMsg = validarSeccion07();
			if (listaMsg.isEmpty()) {
				tracingsProgressFacade.guardarListaAvance(listaRiesgos);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion08();
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

	public void cargarSeccion08() {
		try {
			inicializarObjetoProblema();
			listaProblemas = tracingsProblemFacade.listarProblemasPorReporte(reporteActual.getTracId());
			controlAvance = 8;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjetoProblema() {
		objetoProblema = new TracingsProblem();
		objetoProblema.setCataId(new Catalog());
		objetoProblema.setTracId(reporteActual);
	}

	public void seleccionarProblemaAEditar(TracingsProblem p) {
		objetoProblema = p;
	}

	public void guardarProblema() {
		try {
			List<String[]> listaMsg = validarProblema(objetoProblema);
			if (listaMsg.isEmpty()) {
				if (objetoProblema.getTrprId() == null) {
					tracingsProblemFacade.create(objetoProblema);
				} else {
					tracingsProblemFacade.edit(objetoProblema);
				}
				cargarSeccion08();
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

	private List<String[]> validarProblema(TracingsProblem pro) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (pro.getCataId() == null || pro.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:probTipo",appBean.getBundle().getString("lbl019")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (pro.getTrprDescription().length() == 0) {
				sesBean.validacionMsg("frm:probDesc", appBean.getBundle().getString("lbl244")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (pro.getTrprSolution().length() == 0) {
				sesBean.validacionMsg("frm:probSol", appBean.getBundle().getString("lbl245")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarProblema(TracingsProblem problema) {
		try {
			tracingsProblemFacade.eliminarLogico(problema);
			cargarSeccion08();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion09() {
		try {
			controlAvance = 9;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cancelarSeccion(String seccionActual) {
		try {
			Integer sec = Integer.valueOf(seccionActual);
			controlAvance = sec - 1;
			if (controlAvance == 2) {
				PrimeFaces.current().executeScript("repoEjecPresCargarValoresEnLabels();");
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void marcarComoGuardadoReporte() {
		try {
			reporteActual.setTracRegisterStatus("G");
			tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg129"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void finalizarReporte() {
		try {
			reporteActual.setTracRegisterStatus("V");
			tracingFacade.editar(sesBean.getUsuarioLogeado().getUserName(), reporteActual);
			reporteActual.getProjId().setProjLastReport(reporteActual.getTracId());
			projectFacade.edit(reporteActual.getProjId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg130"));
			inicializar();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void generarReportePDF() {
		try {
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain = new File(FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("reports/reporte/rep_main.jasper"));
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("param_trac_id", reporteActual.getTracId());
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_"
					+ reporteActual.getProjId().getProjShortName().replace(" " , "_") + "_" + reporteActual.getTracYear() + ".pdf");
			ServletOutputStream stream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void generarReportePDF(Tracing rep) {
		try {
			reporteActual = rep;
			generarReportePDF();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
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

	public List<Project> getListaProyectosEncontrados() {
		return listaProyectosEncontrados;
	}

	public void setListaProyectosEncontrados(List<Project> listaProyectosEncontrados) {
		this.listaProyectosEncontrados = listaProyectosEncontrados;
	}

	public List<Tracing> getListaReportesEncontrados() {
		return listaReportesEncontrados;
	}

	public void setListaReportesEncontrados(List<Tracing> listaReportesEncontrados) {
		this.listaReportesEncontrados = listaReportesEncontrados;
	}

	public int getControlAvance() {
		return controlAvance;
	}

	public void setControlAvance(int controlAvance) {
		this.controlAvance = controlAvance;
	}

	public TracingsAmount getMontosTotales() {
		return montosTotales;
	}

	public void setMontosTotales(TracingsAmount montosTotales) {
		this.montosTotales = montosTotales;
	}

	public List<TracingsAmount> getListaMontosObjComp() {
		return listaMontosObjComp;
	}

	public void setListaMontosObjComp(List<TracingsAmount> listaMontosObjComp) {
		this.listaMontosObjComp = listaMontosObjComp;
	}

	public List<TracingsProgress> getListaAcciones() {
		return listaAcciones;
	}

	public void setListaAcciones(List<TracingsProgress> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}

	public List<TracingsKnowledgeManagement> getListaPregGestCon() {
		return listaPregGestCon;
	}

	public void setListaPregGestCon(List<TracingsKnowledgeManagement> listaPregGestCon) {
		this.listaPregGestCon = listaPregGestCon;
	}

	public List<TracingsKnowledgeManagement> getListaPregComunicacion() {
		return listaPregComunicacion;
	}

	public void setListaPregComunicacion(List<TracingsKnowledgeManagement> listaPregComunicacion) {
		this.listaPregComunicacion = listaPregComunicacion;
	}

	public List<TracingsProgress> getListaRiesgos() {
		return listaRiesgos;
	}

	public void setListaRiesgos(List<TracingsProgress> listaRiesgos) {
		this.listaRiesgos = listaRiesgos;
	}

	public TracingsProblem getObjetoProblema() {
		return objetoProblema;
	}

	public void setObjetoProblema(TracingsProblem objetoProblema) {
		this.objetoProblema = objetoProblema;
	}

	public List<TracingsProblem> getListaProblemas() {
		return listaProblemas;
	}

	public void setListaProblemas(List<TracingsProblem> listaProblemas) {
		this.listaProblemas = listaProblemas;
	}

	public String getTipoBusq() {
		return tipoBusq;
	}

	public void setTipoBusq(String tipoBusq) {
		this.tipoBusq = tipoBusq;
	}

	public String getTituloProyecto() {
		return tituloProyecto;
	}

	public void setTituloProyecto(String tituloProyecto) {
		this.tituloProyecto = tituloProyecto;
	}

	public Tracing getReporteActual() {
		return reporteActual;
	}

	public void setReporteActual(Tracing reporteActual) {
		this.reporteActual = reporteActual;
	}

	public List<Integer> getListaAniosReporte() {
		return listaAniosReporte;
	}

	public void setListaAniosReporte(List<Integer> listaAniosReporte) {
		this.listaAniosReporte = listaAniosReporte;
	}

	public Meeting getEventoActual() {
		return eventoActual;
	}

	public void setEventoActual(Meeting eventoActual) {
		this.eventoActual = eventoActual;
	}

	public List<Meeting> getListaEventosProyecto() {
		return listaEventosProyecto;
	}

	public void setListaEventosProyecto(List<Meeting> listaEventosProyecto) {
		this.listaEventosProyecto = listaEventosProyecto;
	}

	public String[] getProvinciasSeleccionadas() {
		return provinciasSeleccionadas;
	}

	public void setProvinciasSeleccionadas(String[] provinciasSeleccionadas) {
		this.provinciasSeleccionadas = provinciasSeleccionadas;
	}

	public List<Object[]> getListaProvinciasEspecial() {
		return listaProvinciasEspecial;
	}

	public void setListaProvinciasEspecial(List<Object[]> listaProvinciasEspecial) {
		this.listaProvinciasEspecial = listaProvinciasEspecial;
	}

	public DonutChartModel getDonutModelAvanceEjecPres() {
		return donutModelAvanceEjecPres;
	}

	public void setDonutModelAvanceEjecPres(DonutChartModel donutModelAvanceEjecPres) {
		this.donutModelAvanceEjecPres = donutModelAvanceEjecPres;
	}

	public DonutChartModel getDonutModelAvanceAcciones() {
		return donutModelAvanceAcciones;
	}

	public void setDonutModelAvanceAcciones(DonutChartModel donutModelAvanceAcciones) {
		this.donutModelAvanceAcciones = donutModelAvanceAcciones;
	}

	public BarChartModel getBarModelEjecPres() {
		return barModelEjecPres;
	}

	public void setBarModelEjecPres(BarChartModel barModelEjecPres) {
		this.barModelEjecPres = barModelEjecPres;
	}

	public Integer[] getListaColumnaAnios() {
		return listaColumnaAnios;
	}

	public void setListaColumnaAnios(Integer[] listaColumnaAnios) {
		this.listaColumnaAnios = listaColumnaAnios;
	}

	public MeetingsTotal getTotalEvento() {
		return totalEvento;
	}

	public void setTotalEvento(MeetingsTotal totalEvento) {
		this.totalEvento = totalEvento;
	}

	public Integer getTipoTotal() {
		return tipoTotal;
	}

	public void setTipoTotal(Integer tipoTotal) {
		this.tipoTotal = tipoTotal;
	}

	public List<MeetingsTotal> getListaTotalesDeEvento() {
		return listaTotalesDeEvento;
	}

	public void setListaTotalesDeEvento(List<MeetingsTotal> listaTotalesDeEvento) {
		this.listaTotalesDeEvento = listaTotalesDeEvento;
	}

	public List<Catalog> getListaCatalogoTotal() {
		return listaCatalogoTotal;
	}

	public void setListaCatalogoTotal(List<Catalog> listaCatalogoTotal) {
		this.listaCatalogoTotal = listaCatalogoTotal;
	}

	public List<TracingsProgress> getListaProgresoHistoricoPorAccion() {
		return listaProgresoHistoricoPorAccion;
	}

	public void setListaProgresoHistoricoPorAccion(List<TracingsProgress> listaProgresoHistoricoPorAccion) {
		this.listaProgresoHistoricoPorAccion = listaProgresoHistoricoPorAccion;
	}

	public int getAnchoDataTableMontos() {
		return anchoDataTableMontos;
	}

	public void setAnchoDataTableMontos(int anchoDataTableMontos) {
		this.anchoDataTableMontos = anchoDataTableMontos;
	}
	
	

}
