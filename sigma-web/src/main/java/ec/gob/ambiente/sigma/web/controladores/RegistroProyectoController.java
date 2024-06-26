package ec.gob.ambiente.sigma.web.controladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.diagram.connector.StraightConnector;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacadeGeo;
import ec.gob.ambiente.sigma.ejb.entidades.Action;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Agreement;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Document;
import ec.gob.ambiente.sigma.ejb.entidades.FinancingAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.Measure;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAction;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsActionsCompatibility;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsDoc;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancing;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancingAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsGeographicalArea;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsRisk;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.entidades.Tracing;
import ec.gob.ambiente.sigma.ejb.facades.ActionFacade;
import ec.gob.ambiente.sigma.ejb.facades.ActionPlanFacade;
import ec.gob.ambiente.sigma.ejb.facades.AgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.CatalogFacade;
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
import ec.gob.ambiente.sigma.ejb.facades.FinancingAgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.MeasureFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsActionFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsActionsCompatibilityFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsAgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsDocFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsFinancingAgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsFinancingFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsGeographicalAreaFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsRiskFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsSpecificObjectiveFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsStrategicPartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.SigmaGeoFacade;
import ec.gob.ambiente.sigma.web.utils.OpcionesRegistro;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;
import ec.gov.sri.wsconsultacontribuyente.ContribuyenteCompleto;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author proamazonia
 *
 */
@Named("proy")
@ViewScoped
public class RegistroProyectoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistroProyectoController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private ProjectFacade proyectoFacade;
	@EJB
	private PartnerFacade socioFacade;
	@EJB
	private ProjectsStrategicPartnerFacade proyectosSocioEstFacade;
	@EJB
	private ProjectsGeographicalAreaFacade proyectosGeoAreaFacade;
	@EJB
	private ProjectsAgreementFacade proyectosConvenioFacade;
	@EJB
	private AgreementFacade convenioFacade;
	@EJB
	private CatalogFacade catalogFacade;
	@EJB
	private ProjectsSpecificObjectiveFacade proyectosObjEspCompFacade;
	@EJB
	private ProjectsActionFacade proyectosAccionFacade;
	@EJB
	private ComponentFacade componentePAFacade;

	@EJB
	private ProjectsRiskFacade proyectosRiesgoFacade;

	@EJB
	private ProjectsFinancingFacade proyectosFinanFacade;
	@EJB
	private ProjectsDocFacade proyectosDocFacade;

	@EJB
	private FinancingAgreementFacade financingAgreementFacade;
	@EJB
	private ProjectsFinancingAgreementFacade proyectosAcuerdosFinFacade;
	@EJB
	private ActionPlanFacade actionPlanFacade;
	@EJB
	private MeasureFacade medidaFacade;
	@EJB
	private ActionFacade accionFacade;
	@EJB
	private ProjectsActionsCompatibilityFacade compatibilidadAccFacade;
	@EJB
	private SigmaGeoFacade sigmaGeoFacade;

	@Inject
	private SocioImplementadorController socioBean;

	private int tipoBusquedaProyectos;
	private Partner socioEstActual;
	private String socioEstTipo;
	private List<Partner> listaSociosImpl;
	private List<Partner> listaSociosEstrategicosTodos;
	private Integer codSocioImplBusqueda;
	private String tituloProyectoBusqueda;
	private Integer codPlanAccionBusqueda;
	private List<Project> listaProyectosEncontrados;

	private Project proyectoActual;
	private int projCodAnio;
	private String projCodSec;
	private String projPlazoDesdAnio;
	private String projPlazoDesdMes;
	private String projPlazoHastAnio;
	private String projPlazoHastMes;

	private int tipoBusquedaSociosImp;
	private String rucSocioImplBusq;
	private String nombreSocioImplBusq;
	private List<Partner> listaSociosImplEncontrados;
	private Partner socioImplSeleccionado;

	private int controlAvance;
	private List<ProjectsStrategicPartner> listaSociosEstrategicos;
	private List<ProjectsGeographicalArea> listaAreasGeoNivel1Prov;
	private List<ProjectsGeographicalArea> listaAreasGeoNivel2Cant;
	private List<ProjectsGeographicalArea> listaAreasGeoNivel3Parr;
	private List<GeographicalLocations> listaProvSeleccionadas;
	private List<GeographicalLocations> listaCantSeleccionadas;
	private List<GeographicalLocations> listaParrSeleccionadas;
	private boolean flagModifGeo;
	private boolean flagSocioImpEspecial;
	private String srid;
	private List<Project> listaProyectosGestoresDeFondos;

	private List<Partner> listaSociosEstrategicosApoyoSinPdi;

	private int tipoConsultaComponentes;
	private List<ProjectsSpecificObjective> listaComponentesObjetivos;
	private List<ProjectsSpecificObjective> listaComponentesObjetivosTodos;
	private Integer[] codProvinciasSeleccionadas;
	private ProjectsSpecificObjective componenteObjetivoActual;
	private Integer[] codProvinciasCompObjActual;
	private String codsProvEliminados;
	private List<Integer> codsProvNuevos;
	private ProjectsAction accionActual;
	private ProjectsAction indicadorActual;
	private List<ProjectsAction> listaIndicadoresAccionActual;
	private List<ProjectsAction> listaIndicadores;

	private Component codCompCompat;
	private Measure codMedCompat;
	private Action codAccCompat;
	private Measure codIndEnlace;
	private List<Component> listaComponentesPlanActual;
	private List<Measure> listaMedidasPlanActual;
	private List<Action> listaAccionesPlanActual;
	private List<Measure> listaIndicadoresPlanActual;

	private boolean esSubAcc;

	private List<ProjectsRisk> listaRiesgos;
	private ProjectsRisk riesgoActual;

	private List<ProjectsFinancing> listaFinancTotales;
	private List<ProjectsFinancing> listaFinancObjComp;
	private List<ProjectsFinancing> listaFinancCofinan;
	private Integer codFuenteFinActual;
	private String typeCofin;
	private BigDecimal totalIniPdi;

	private List<ProjectsDoc> listaDocs;
	private Integer codCatEjeTematico1;
	private Catalog catEjeTematico2;
	private List<Catalog> listaEjeTematicoShape;
	private ProjectsDoc docProyCurrent;
	private Document documentCurrent;
	private Integer docIdToEdit;
	private StreamedContent fileDocToDownload;

	private String codBusqConvenio;
	private List<ProjectsAgreement> listaConveniosProyecto;
	private List<Agreement> listaConveniosEncontrados;
	private String campoBusquedaAcuerdo;
	private List<ProjectsFinancingAgreement> listaAcuerdosProyecto;
	private List<FinancingAgreement> listaAcuerdosEncontrados;

	private ProjectsGeographicalArea tablaGeoActual;
	private String ayudaCamposCapa;
	private List<ProjectsGeographicalArea> listaTablasGeoProyecto;

	private String flagTipoProy;

	private String[] provinciasSel;
	private String[] cantonesSel;
	private String[] parroquiasSel;
	private int flagProvSel;
	private int flagCantSel;
	private int flagParrSel;
	private List<AreaGeoRegistro> listaAreaGeoRegistro;

	public RegistroProyectoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			controlAvance = 0;
			listaSociosImpl = socioFacade.listarSociosImplementadores();
			listaSociosEstrategicosTodos = socioFacade.listarSociosEstrategicos();
			tipoBusquedaSociosImp = 1;
			inicializarObjProyecto();
			inicializarObjComponenteObjetivo();
			indicadorActual = new ProjectsAction();
			inicializarObjAccion();
			listaComponentesPlanActual = new ArrayList<>();
			listaMedidasPlanActual = new ArrayList<>();
			listaAccionesPlanActual = new ArrayList<>();
			listaIndicadoresPlanActual = new ArrayList<>();
			// inicializarObjCobenef();
			inicializarObjRiesgo();
			inicializarObjetoDoc();
			inicializarObjetoTablaGeo();

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			String attr1 = (String) session.getAttribute("REDIRECCION_DESDE");
			Integer attr2 = (Integer) session.getAttribute("ID_PROYECTO_ACTUAL");
			Integer attr3 = (Integer) session.getAttribute("ID_SOCIMP_ACTUAL");
			if (attr1 != null) {
				mostrarProyectoActual(attr2, attr1, attr3);
				session.setAttribute("REDIRECCION_DESDE", null);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarProyectos() {
		try {
			listaProyectosEncontrados = new ArrayList<>();
			List<String[]> listaMsg = validarBusquedaProyectos();

			if (listaMsg.isEmpty()) {
				if (tipoBusquedaProyectos == 1) {
					listaProyectosEncontrados = proyectoFacade.listarProyectosPorIdSocioImpl(codSocioImplBusqueda);
				} else if (tipoBusquedaProyectos == 2) {
					listaProyectosEncontrados = proyectoFacade.listarProyectosPorTextoTitulo(tituloProyectoBusqueda);
				} else if (tipoBusquedaProyectos == 3) {
					listaProyectosEncontrados = proyectoFacade.listarProyectosPorIdPlanAccion(codPlanAccionBusqueda);
				}
				if (listaProyectosEncontrados.isEmpty()) {
					sesBean.addErrorMessage(appBean.getBundle().getString("lbl047"));
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
			if (tipoBusquedaProyectos == 1 && (codSocioImplBusqueda == null || codSocioImplBusqueda <= 0)) {
				sesBean.validacionMsg("frm:codSocioBusq", appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (tipoBusquedaProyectos == 2 && (tituloProyectoBusqueda.length() < 5)) {
				sesBean.validacionMsg("frm:tituloBusq", appBean.getBundle().getString("msg057"), listaMsg);
			}
			if (tipoBusquedaProyectos == 3 && (codPlanAccionBusqueda == null || codPlanAccionBusqueda <= 0)) {
				sesBean.validacionMsg("frm:codPABusq", appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (tipoBusquedaProyectos == 0) {
				sesBean.addErrorMessage(appBean.getBundle().getString("msg072"));
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void buscarProyectosDeSocio(String rol) {
		try {
			listaProyectosEncontrados = new ArrayList<>();
			List<Partner> lst = socioFacade.listarSocioImplementadorPorIdentificacion(sesBean.obtenerIdUsuarioSocio());

			if (!lst.isEmpty()) {
				if (rol.equals("SIGMA_SOCIO")) {
					listaProyectosEncontrados = proyectoFacade.listarProyectosPorIdSocioImpl(lst.get(0).getPartId());
				} else if (rol.equals("SIGMA_IMPLEMENTADOR")) {
					listaProyectosEncontrados = proyectoFacade
							.listarProyectosPorIdSocioImplYUsuarioCreadorOCodigoProyecto(lst.get(0).getPartId(),
									sesBean.getUsername(), sesBean.obtenerProjIdDeUsuarioConRolImplementador());
				}

			}
			if (listaProyectosEncontrados.isEmpty()) {
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg064"));
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarProyectoAEditar(Project proyecto) {
		try {
			limpiarBusquedaProyectos();
			proyectoActual = proyecto;
			socioEstActual = new Partner();
			listaSociosEstrategicos = new ArrayList<>();
			listaSociosEstrategicos = proyectosSocioEstFacade.listaSociosEstrategicosPorProyecto(proyecto.getProjId());
			socioImplSeleccionado = proyecto.getPartId();
			validarSocioImplementadorProvisional();
			if (proyectoActual.getProjParentId() == null) {
				proyectoActual.setProjParentId(new Project());
			}
			descomponerCodigoProyecto(proyecto);
			descomponerPlazoProyecto(proyecto);
			limpiarBusquedaSocios();
			controlAvance = 1;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void mostrarProyectoActual(Integer idProyActual, String tipoRetorno, Integer idSocImplNuevo) {
		try {
			limpiarBusquedaProyectos();
			if (idProyActual != null && idProyActual > 0) {
				proyectoActual = proyectoFacade.find(idProyActual);
				socioImplSeleccionado = proyectoActual.getPartId();
				socioEstActual = new Partner();
				listaSociosEstrategicos = new ArrayList<>();
				listaSociosEstrategicos = proyectosSocioEstFacade.listaSociosEstrategicosPorProyecto(idProyActual);
				descomponerCodigoProyecto(proyectoActual);
				descomponerPlazoProyecto(proyectoActual);
			}

			if (proyectoActual.getProjParentId() == null) {
				proyectoActual.setProjParentId(new Project());
			}

			if (tipoRetorno.equals(TipoRetornoAProyecto.DESDE_SOCIO_IMPLEMENTADOR.getCodigo())) {
				if (idSocImplNuevo != null) {
					socioImplSeleccionado = socioFacade.find(idSocImplNuevo);
					proyectoActual.setPartId(socioImplSeleccionado);
				}
				validarSocioImplementadorProvisional();
				controlAvance = 1;
			} else if (tipoRetorno.equals(TipoRetornoAProyecto.DESDE_FUENTE_FINANCIAMIENTO.getCodigo())) {
				cargarSeccion02(proyectoActual);
				cargarSeccion03(proyectoActual);
				cargarSeccion04(proyectoActual);
				cargarSeccion05(proyectoActual);
			} else if (tipoRetorno.equals(TipoRetornoAProyecto.DESDE_CONVENIO_IMPLEMENTACION.getCodigo())
					|| tipoRetorno.equals(TipoRetornoAProyecto.DESDE_ACUERDO_FINANCIAMIENTO.getCodigo())
					|| tipoRetorno.equals(TipoRetornoAProyecto.DESDE_GEOVISOR.getCodigo())) {
				cargarSeccion02(proyectoActual);
				cargarSeccion03(proyectoActual);
				cargarSeccion04(proyectoActual);
				cargarSeccion05(proyectoActual);
				cargarSeccion06(proyectoActual);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void limpiarBusquedaProyectos() {
		try {
			codSocioImplBusqueda = 0;
			tituloProyectoBusqueda = "";
			codPlanAccionBusqueda = 0;
			listaProyectosEncontrados = new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}

	}

	private void descomponerCodigoProyecto(Project proyecto) {
		try {
			projCodAnio = Integer.valueOf(proyecto.getProjCode().substring(12, 16));
			projCodSec = proyecto.getProjCode().substring(17, 20);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void descomponerPlazoProyecto(Project proyecto) {
		try {
			projPlazoDesdAnio = proyecto.getProjTermFrom().substring(0, 4);
			projPlazoHastAnio = proyecto.getProjTermTo().substring(0, 4);
			projPlazoDesdMes = proyecto.getProjTermFrom().substring(5, 7);
			projPlazoHastMes = proyecto.getProjTermTo().substring(5, 7);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoProyecto(String rol) {
		try {
			limpiarBusquedaSocios();
			limpiarBusquedaProyectos();
			inicializarObjProyecto();
			socioImplSeleccionado = new Partner();
			controlAvance = 1;
			if (rol.equals("SIGMA_SOCIO")) {
				List<Partner> lst = socioFacade
						.listarSocioImplementadorPorIdentificacion(sesBean.obtenerIdUsuarioSocio());
				if (!lst.isEmpty()) {
					socioImplSeleccionado = lst.get(0);
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjProyecto() {
		try {
			proyectoActual = new Project();
			proyectoActual.setAcplId(new ActionPlan());
			proyectoActual.setPartId(new Partner());
			proyectoActual.setProjParentId(new Project());
			projCodAnio = 0;
			projCodSec = "";
			projPlazoDesdAnio = "";
			projPlazoDesdMes = "";
			projPlazoHastAnio = "";
			projPlazoHastMes = "";
			srid = "";
			socioEstActual = new Partner();
			listaSociosEstrategicos = new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarSociosImplementadores() {
		try {
			listaSociosImplEncontrados = new ArrayList<>();
			List<String[]> listaMsg = validarBusquedaSocios();

			if (listaMsg.isEmpty()) {
				if (tipoBusquedaSociosImp == 1) {
					listaSociosImplEncontrados = socioFacade
							.listarSocioImplementadorPorIdentificacion(rucSocioImplBusq);
				} else if (tipoBusquedaSociosImp == 2) {
					listaSociosImplEncontrados = socioFacade.listarSocioImplementadorPorNombre(nombreSocioImplBusq);
				}
				if (listaSociosImplEncontrados.isEmpty()) {
					sesBean.addErrorMessage(appBean.getBundle().getString("msg064"));
				} else {
					rucSocioImplBusq = "";
					nombreSocioImplBusq = "";
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

	private List<String[]> validarBusquedaSocios() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (tipoBusquedaSociosImp == 1 && (rucSocioImplBusq.length() != 13)) {
				sesBean.validacionMsg("frm:rucSocioBusq", appBean.getBundle().getString("msg032"), listaMsg);
			}
			if (tipoBusquedaSociosImp == 2 && (nombreSocioImplBusq.length() < 5)) {
				sesBean.validacionMsg("frm:nomSocioBusq", appBean.getBundle().getString("msg057"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	private void limpiarBusquedaSocios() {
		try {
			rucSocioImplBusq = "";
			nombreSocioImplBusq = "";
			listaSociosImplEncontrados = new ArrayList<>();

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarSocioImplementador(Partner socio) {
		try {
			socioImplSeleccionado = socio;
			validarSocioImplementadorProvisional();
			limpiarBusquedaSocios();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void irCreacionSocioImplementador() {
		try {
			sesBean.redireccionarAPaginaConParametros("partner", "SocioImplementador", "TIPO_PAG_SOCIOS",
					"SOLO_CREAR_SOC_IMPL", "ID_PROYECTO_ACTUAL", proyectoActual.getProjId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void validarSocioImplementadorProvisional() {
		try {
			flagSocioImpEspecial = false;
			if (socioImplSeleccionado != null && socioImplSeleccionado.getPartName() != null) {
				if (socioImplSeleccionado.getPartIdNumber().equals("1768192860001")
						|| socioImplSeleccionado.getPartIdNumber().equals("1760001470001")) {
					flagSocioImpEspecial = true;
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void llamarVentanaNuevoSocioEstrategico() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("TIPO_PAG_SOCIOS", "SOLO_CREAR_SOC_EST");
			socioBean.inicializar();
			PrimeFaces.current().executeScript("PF('dlgNuevoSocioEst').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarSocioEstrategicoNuevo() {
		try {
			String res = socioBean.guardarSocioEstrategico();

			if (!res.equals("ERROR")) {
				listaSociosEstrategicosTodos = socioFacade.listarSociosEstrategicos();
				PrimeFaces.current().executeScript("PF('dlgNuevoSocioEst').hide()");
				PrimeFaces.current().ajax().update("frm:msgs");
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarSocioEstrategicoDeProyecto() {
		try {
			boolean val = true;
			List<String[]> listaMsg = new ArrayList<>();
			if (socioEstActual.getPartId() == null || socioEstTipo == null || socioEstTipo.length() == 0) {
				val = false;
				sesBean.validacionMsg("frm:socioEstAct", appBean.getBundle().getString("msg131"), listaMsg);

			} else {
				if (listaSociosEstrategicos != null && !listaSociosEstrategicos.isEmpty()) {
					for (ProjectsStrategicPartner sp : listaSociosEstrategicos) {
						if (sp.getPartId().getPartId() == socioEstActual.getPartId()) {
							val = false;
							sesBean.validacionMsg("frm:socioEstAct", appBean.getBundle().getString("msg133"), listaMsg);
						}
					}
				}
			}

			if (val) {
				ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
				psp.setPspaType(socioEstTipo);
				psp.setPartId(socioEstActual);
				if (proyectoActual.getProjId() != null) {
					psp.setProjId(proyectoActual);
					proyectosSocioEstFacade.create(psp);
					listaSociosEstrategicos = proyectosSocioEstFacade
							.listaSociosEstrategicosPorProyecto(proyectoActual.getProjId());
				} else {
					psp.setPartId(obtenerObjetoPartnerDesdeId(socioEstActual.getPartId()));
					listaSociosEstrategicos.add(psp);
				}
				socioEstActual = new Partner();
				socioEstTipo = "";
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

	public void eliminarSocioEstrategicoProyecto(ProjectsStrategicPartner psp) {
		try {
			proyectosSocioEstFacade.eliminarLogico(psp);
			listaSociosEstrategicos = proyectosSocioEstFacade
					.listaSociosEstrategicosPorProyecto(proyectoActual.getProjId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void removerObjetoSocioEstrategicoProyecto(ProjectsStrategicPartner psp) {
		try {
			int idx = 0;
			for (ProjectsStrategicPartner pspa : listaSociosEstrategicos) {
				if (pspa.getPartId().getPartId() == psp.getPartId().getPartId()) {
					break;
				}
				idx++;
			}
			listaSociosEstrategicos.remove(idx);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public boolean guardarSeccion01() {
		boolean val = false;
		try {
			val = validacionSociosSeleccionados();

			if (val) {
				proyectoActual.setPartId(socioImplSeleccionado);
				if (proyectoActual.getProjId() != null) {
					proyectoFacade.editar(sesBean.getUsuarioLogeado().getUserName(), proyectoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return val;
	}

	private boolean validacionSociosSeleccionados() {
		boolean val = true;

		if (socioImplSeleccionado == null || socioImplSeleccionado.getPartId() == null) {
			val = false;
			sesBean.addErrorMessage(appBean.getBundle().getString("msg058"));
		} else {
			if (flagSocioImpEspecial) {
				if (!proyectoActual.getProjTemporaryPartner()
						&& proyectoActual.getProjImplementerProject().length() == 0) {
					val = false;
					sesBean.addErrorMessage(appBean.getBundle().getString("msg091"));
				}
			}
		}
		if (listaSociosEstrategicos.isEmpty()) {
			val = false;
			sesBean.addErrorMessage(appBean.getBundle().getString("msg132"));
		}
		return val;
	}

	public void guardarYContinuar01() {
		try {
			boolean val = guardarSeccion01();
			if (val) {
				cargarSeccion02(proyectoActual);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion02(Project proyecto) {
		try {
			controlAvance = 2;
			// cargarSociosEstDeProyecto(proyecto);
			cargarAreasGeograficasDeProyecto(proyecto);
			flagModifGeo = false;
			cargarConveniosDeProyecto(proyecto);
			cargarAcuerdosDeProyecto(proyecto);
			listaProyectosGestoresDeFondos = proyectoFacade.listarProyectosGestoresDeFondos();
			if (proyecto.getProjParentId() == null) {
				proyecto.setProjParentId(new Project());
			}
			if (proyecto.getProjType() != null) {
				flagTipoProy = proyecto.getProjType();
			} else {
				flagTipoProy = "";
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private boolean esCampioTipoImportante() {
		boolean b = false;
		try {
			if (proyectoActual.getProjType() != null) {
				if (((flagTipoProy.equals("PRG") || flagTipoProy.equals("PRY"))
						&& (proyectoActual.getProjType().equals("PDI")))
						|| ((proyectoActual.getProjType().equals("PRG") || proyectoActual.getProjType().equals("PRY"))
								&& flagTipoProy.equals("PDI"))) {
					List<ProjectsSpecificObjective> lst = proyectosObjEspCompFacade
							.listarObjetivosPadrePorProyecto(proyectoActual.getProjId());
					if (!lst.isEmpty()) {
						b = true;
					}
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return b;
	}

	public void mostrarAlertaCambioTipo() {
		try {
			if (esCampioTipoImportante()) {
				sesBean.addWarningMessage(appBean.getBundle().getString("msg177"));
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * private void cargarSociosEstDeProyecto(Project proyecto) { try {
	 * List<ProjectsStrategicPartner> lstSocEstProy = new ArrayList<>(); if
	 * (proyecto.getProjId() != null) { lstSocEstProy =
	 * proyectosSocioEstFacade.listaSociosEstrategicosPorProyecto(proyecto.
	 * getProjId()); }
	 * 
	 * List<Partner> lstSocEst = socioFacade.listarSociosEstrategicos();
	 * listaSociosEstrategicos = new ArrayList<>(); for (Partner s : lstSocEst)
	 * { ProjectsStrategicPartner psp = new ProjectsStrategicPartner();
	 * psp.setProjId(proyecto); psp.setPartId(s); for
	 * (ProjectsStrategicPartnerproyId sp : lstSocEstProy) { if
	 * (sp.getPartId().getPartId().equals(s.getPartId())) {
	 * psp.setPspaId(sp.getPspaId()); psp.setSeleccionado(true); } }
	 * listaSociosEstrategicos.add(psp); }
	 * 
	 * } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 */

	private void cargarAreasGeograficasDeProyecto(Project proyecto) {
		try {
			List<ProjectsGeographicalArea> lstProvinciaProy = new ArrayList<>();
			List<ProjectsGeographicalArea> lstCantonProy = new ArrayList<>();
			List<ProjectsGeographicalArea> lstParroquiaProy = new ArrayList<>();
			if (proyecto.getProjId() != null) {
				lstProvinciaProy = proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyecto.getProjId(), "1");
				lstCantonProy = proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyecto.getProjId(), "2");
				lstParroquiaProy = proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyecto.getProjId(), "3");
			}
			listaAreasGeoNivel1Prov = new ArrayList<>();
			listaAreasGeoNivel2Cant = new ArrayList<>();
			listaAreasGeoNivel3Parr = new ArrayList<>();
			listaProvSeleccionadas = new ArrayList<>();
			listaCantSeleccionadas = new ArrayList<>();
			listaParrSeleccionadas = new ArrayList<>();
			for (Object[] o : appBean.getLstGeoLocProvincias()) {
				ProjectsGeographicalArea pgapr = new ProjectsGeographicalArea();
				pgapr.setProjId(proyecto);
				pgapr.setPgarLevel("1");
				pgapr.setGeloId(new GeographicalLocations(Integer.valueOf(String.valueOf(o[1])), String.valueOf(o[0])));
				for (ProjectsGeographicalArea prov : lstProvinciaProy) {
					if (prov.getGeloId().getGeloId().equals(Integer.valueOf(String.valueOf(o[1])))) {
						pgapr.setPgarId(prov.getPgarId());
						pgapr.setSeleccionado(true);
						listaProvSeleccionadas.add(prov.getGeloId());
					}
				}
				listaAreasGeoNivel1Prov.add(pgapr);
			}
			for (GeographicalLocations lst1 : listaProvSeleccionadas) {
				List<Object[]> lstCantones = appBean.listarCantonesPorIdProvincia(lst1.getGeloId());
				for (Object[] o : lstCantones) {
					ProjectsGeographicalArea pgapr = new ProjectsGeographicalArea();
					pgapr.setProjId(proyecto);
					pgapr.setPgarLevel("2");
					pgapr.setGeloId(
							new GeographicalLocations(Integer.valueOf(String.valueOf(o[1])), String.valueOf(o[0]),lst1.getGeloId()));
					for (ProjectsGeographicalArea cant : lstCantonProy) {
						if (cant.getGeloId().getGeloId().equals(Integer.valueOf(String.valueOf(o[1])))) {
							pgapr.setPgarId(cant.getPgarId());
							pgapr.setSeleccionado(true);
							listaCantSeleccionadas.add(cant.getGeloId());
						}
					}
					listaAreasGeoNivel2Cant.add(pgapr);
				}
			}

			for (GeographicalLocations lst2 : listaCantSeleccionadas) {
				List<Object[]> lstParroquias = appBean.listarParroquiasPorIdCanton(lst2.getGeloId());
				for (Object[] o : lstParroquias) {
					ProjectsGeographicalArea pgapr = new ProjectsGeographicalArea();
					pgapr.setProjId(proyecto);
					pgapr.setPgarLevel("3");
					pgapr.setGeloId(
							new GeographicalLocations(Integer.valueOf(String.valueOf(o[1])), String.valueOf(o[0]),lst2.getGeloId()));
					for (ProjectsGeographicalArea parr : lstParroquiaProy) {
						if (parr.getGeloId().getGeloId().equals(Integer.valueOf(String.valueOf(o[1])))) {
							pgapr.setPgarId(parr.getPgarId());
							pgapr.setSeleccionado(true);
							listaParrSeleccionadas.add(parr.getGeloId());
						}
					}
					listaAreasGeoNivel3Parr.add(pgapr);
				}
			}
			
			
			if(!listaProvSeleccionadas.isEmpty()){
				provinciasSel=new String[listaProvSeleccionadas.size()];
				for(int i=0;i<listaProvSeleccionadas.size();i++){
					provinciasSel[i]=listaProvSeleccionadas.get(i).getGeloId()+"";
				}
				if(!listaCantSeleccionadas.isEmpty()){
					cantonesSel=new String[listaCantSeleccionadas.size()];
					for(int i=0;i<listaCantSeleccionadas.size();i++){
						cantonesSel[i]=listaCantSeleccionadas.get(i).getGeloId()+"";
					}
					if(!listaParrSeleccionadas.isEmpty()){
						parroquiasSel=new String[listaParrSeleccionadas.size()];
						for(int i=0;i<listaParrSeleccionadas.size();i++){
							parroquiasSel[i]=listaParrSeleccionadas.get(i).getGeloId()+"";
						}
						cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, cantonesSel, parroquiasSel);
					}else{
						cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, cantonesSel, null);
					}
				}else{
					cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, null, null);
				}
			}
			

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<Integer> obtenerNuevosIdsPadre(String[] idsSel, List<ProjectsGeographicalArea> lista) {
		List<Integer> ids = new ArrayList<>();
		try {
			for (int i = 0; i < idsSel.length; i++) {
				boolean flag = true;
				for (ProjectsGeographicalArea a : lista) {
					if (a.getGeloId().getGeloParentId().getGeloId().equals(Integer.valueOf(idsSel[i]))) {
						flag = false;
						break;
					}
				}
				if (flag) {
					ids.add(Integer.valueOf(idsSel[i]));
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return ids;
	}

	private List<Integer> obtenerIdsPadreEliminados(String[] idsSel, List<ProjectsGeographicalArea> lista) {
		List<Integer> ids = new ArrayList<>();
		try {
			for (ProjectsGeographicalArea a : lista) {
				boolean flag1 = true;
				for (Integer j : ids) {
					if (j.equals(a.getGeloId().getGeloParentId().getGeloId())) {
						flag1 = false;
					}
				}
				if (flag1) {
					boolean flag = false;
					for (int i = 0; i < idsSel.length; i++) {
						if (a.getGeloId().getGeloParentId().getGeloId().equals(Integer.valueOf(idsSel[i]))) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						ids.add(a.getGeloId().getGeloParentId().getGeloId());
					}
				}

			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return ids;
	}

	public void cargarCantonesPorProvinciasSel() {
		try {
			if(listaAreasGeoNivel2Cant==null||!flagModifGeo){
				   listaAreasGeoNivel2Cant = new ArrayList<>();
			}
			flagModifGeo = true;
			
			listaAreasGeoNivel3Parr = new ArrayList<>();

			
			if (provinciasSel.length > flagProvSel) {
				
				List<Integer> nuevosIds=obtenerNuevosIdsPadre(provinciasSel, listaAreasGeoNivel2Cant);
				for (Integer i:nuevosIds) {
					
					List<Object[]> lstCantones = appBean
							.listarCantonesPorIdProvincia(i);
					for (Object[] o : lstCantones) {
						ProjectsGeographicalArea pgapr = new ProjectsGeographicalArea();
						pgapr.setProjId(proyectoActual);
						pgapr.setPgarLevel("2");
						pgapr.setGeloId(new GeographicalLocations(Integer.valueOf(String.valueOf(o[1])),
								String.valueOf(o[0]), i));

						listaAreasGeoNivel2Cant.add(pgapr);
					}

				}
			} else {
				//
				List<Integer> idsEliminados=obtenerIdsPadreEliminados(provinciasSel, listaAreasGeoNivel2Cant);
				
				for(Integer i:idsEliminados){
					for(int j=0;j<listaAreasGeoNivel2Cant.size();j++){
						if(listaAreasGeoNivel2Cant.get(j).getGeloId().getGeloParentId().getGeloId().equals(i)){
							listaAreasGeoNivel2Cant.remove(j);
						}
					}
				}
			}
			flagProvSel = provinciasSel.length;
			cargarParroquiasPorCantonesSel();
			cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, cantonesSel, parroquiasSel);
			/*
			 * for (ProjectsGeographicalArea prov : listaAreasGeoNivel1Prov) {
			 * if (prov.isSeleccionado()) { List<Object[]> lstCantones =
			 * appBean.listarCantonesPorIdProvincia(prov.getGeloId().getGeloId()
			 * ); for (Object[] o : lstCantones) { ProjectsGeographicalArea
			 * pgapr = new ProjectsGeographicalArea();
			 * pgapr.setProjId(proyectoActual); pgapr.setPgarLevel("2");
			 * pgapr.setGeloId( new
			 * GeographicalLocations(Integer.valueOf(String.valueOf(o[1])),
			 * String.valueOf(o[0]))); listaAreasGeoNivel2Cant.add(pgapr); }
			 * listaProvSeleccionadas.add(prov.getGeloId());
			 * 
			 * } }
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarParroquiasPorCantonesSel() {
		try {
			if(listaAreasGeoNivel3Parr==null||!flagModifGeo){
				   listaAreasGeoNivel3Parr = new ArrayList<>();
			}
			flagModifGeo = true;
			
			if(cantonesSel!=null){
			if (cantonesSel.length > flagCantSel) {
				
				List<Integer> nuevosIds=obtenerNuevosIdsPadre(cantonesSel, listaAreasGeoNivel3Parr);
				for (Integer i:nuevosIds) {
					
					List<Object[]> lstParroquias = appBean
							.listarParroquiasPorIdCanton(i);
					for (Object[] o : lstParroquias) {
						ProjectsGeographicalArea pgapr = new ProjectsGeographicalArea();
						pgapr.setProjId(proyectoActual);
						pgapr.setPgarLevel("3");
						pgapr.setGeloId(new GeographicalLocations(Integer.valueOf(String.valueOf(o[1])),
								String.valueOf(o[0]), i));

						listaAreasGeoNivel3Parr.add(pgapr);
					}

				}
			} else if(cantonesSel.length < flagCantSel) {
				//
				List<Integer> idsEliminados=obtenerIdsPadreEliminados(cantonesSel, listaAreasGeoNivel3Parr);
				
				for(Integer i:idsEliminados){
					for(int j=0;j<listaAreasGeoNivel3Parr.size();j++){
						if(listaAreasGeoNivel3Parr.get(j).getGeloId().getGeloParentId().getGeloId().equals(i)){
							listaAreasGeoNivel3Parr.remove(j);
						}
					}
				}
			}else{
				listaAreasGeoNivel3Parr= proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyectoActual.getProjId(), "3");
			}
			flagCantSel = cantonesSel.length;
			cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, cantonesSel, parroquiasSel);
			
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarParroquiasSeleccionadas() {
		try {
			flagModifGeo = true;
			/*listaParrSeleccionadas = new ArrayList<>();
			for (ProjectsGeographicalArea parr : listaAreasGeoNivel3Parr) {
				if (parr.isSeleccionado()) {
					listaParrSeleccionadas.add(parr.getGeloId());

				}
			}*/
			cargarListaAreasGeoRegistro(listaAreasGeoNivel1Prov, listaAreasGeoNivel2Cant, listaAreasGeoNivel3Parr, provinciasSel, cantonesSel, parroquiasSel);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void cargarListaAreasGeoRegistro(List<ProjectsGeographicalArea> pgaPr,List<ProjectsGeographicalArea> pgaCa,List<ProjectsGeographicalArea> pgaPa,String[] prSel,String[] caSel,String[] paSel){
		try{
			listaAreaGeoRegistro=new ArrayList<>();
			
			List<ProjectsGeographicalArea> prSeleccionadas=new ArrayList<>();
			//se recarga la ListaProvSeleccionadas para usarla en la creacion de componentes
			listaProvSeleccionadas=new ArrayList<>();
			for(ProjectsGeographicalArea pr:pgaPr){
				for(int i=0;i<prSel.length;i++){
					if(pr.getGeloId().getGeloId().equals(Integer.valueOf(prSel[i]))){
						ProjectsGeographicalArea p=new ProjectsGeographicalArea();
						p.setGeloId(new GeographicalLocations(Integer.valueOf(prSel[i]), pr.getGeloId().getGeloName()));
						prSeleccionadas.add(p);
						pr.setSeleccionado(true);
						listaProvSeleccionadas.add(p.getGeloId());
					}
				}
			}
			List<ProjectsGeographicalArea> caSeleccionadas=new ArrayList<>();
			for(ProjectsGeographicalArea ca:pgaCa){
				if(caSel!=null){
				for(int i=0;i<caSel.length;i++){
					if(ca.getGeloId().getGeloId().equals(Integer.valueOf(caSel[i]))){
						ProjectsGeographicalArea p=new ProjectsGeographicalArea();
						p.setGeloId(new GeographicalLocations(Integer.valueOf(caSel[i]), ca.getGeloId().getGeloName(),ca.getGeloId().getGeloParentId().getGeloId()));
						caSeleccionadas.add(p);
						ca.setSeleccionado(true);
					}
				}
				}
			}
			List<ProjectsGeographicalArea> paSeleccionadas=new ArrayList<>();
			for(ProjectsGeographicalArea pa:pgaPa){
				if(paSel!=null){
				for(int i=0;i<paSel.length;i++){
					if(pa.getGeloId().getGeloId().equals(Integer.valueOf(paSel[i]))){
						ProjectsGeographicalArea p=new ProjectsGeographicalArea();
						p.setGeloId(new GeographicalLocations(Integer.valueOf(paSel[i]), pa.getGeloId().getGeloName(),pa.getGeloId().getGeloParentId().getGeloId()));
						paSeleccionadas.add(p);
						pa.setSeleccionado(true);
					}
				}
				}
			}
			
			for(ProjectsGeographicalArea pr:prSeleccionadas){
				boolean tieneCantones=false;
				for(ProjectsGeographicalArea ca:caSeleccionadas){
					boolean tieneParroquias=false;
					if(ca.getGeloId().getGeloParentId().getGeloId().equals(pr.getGeloId().getGeloId())){
						tieneCantones=true;
						List<String> lstParr=new ArrayList<>();
						for(ProjectsGeographicalArea pa:paSeleccionadas){
							if(pa.getGeloId().getGeloParentId().getGeloId().equals(ca.getGeloId().getGeloId())){
								tieneParroquias=true;
								lstParr.add(pa.getGeloId().getGeloName());
							}
						}
						if(!tieneParroquias){
							listaAreaGeoRegistro.add(new AreaGeoRegistro(pr.getGeloId().getGeloName(), ca.getGeloId().getGeloName(),new ArrayList<String>()));
						}else{
							listaAreaGeoRegistro.add(new AreaGeoRegistro(pr.getGeloId().getGeloName(), ca.getGeloId().getGeloName(),lstParr));
						}
						
					}
				}
				if(!tieneCantones){
					listaAreaGeoRegistro.add(new AreaGeoRegistro(pr.getGeloId().getGeloName(), "",new ArrayList<String>()));
				}
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarSeccion02(String op) {
		try {
			List<String[]> listaMsg = validarSeccion02(proyectoActual, listaSociosEstrategicos, listaAreaGeoRegistro);

			if (listaMsg.isEmpty()) {
				proyectoActual.setProjTitle(proyectoActual.getProjTitle().toUpperCase());
				proyectoActual.setProjCode(construirCodigoProyecto(proyectoActual.getProjType()));
				proyectoActual.setProjTermFrom(construirPlazoProyecto(projPlazoDesdAnio, projPlazoDesdMes));
				proyectoActual.setProjTermTo(construirPlazoProyecto(projPlazoHastAnio, projPlazoHastMes));

				boolean flagCrearGeo = false;
				if (proyectoActual.getProjId() == null) {
					proyectoActual = proyectoFacade.crear(sesBean.getUsuarioLogeado().getUserName(), proyectoActual);
					flagCrearGeo = true;
					proyectosSocioEstFacade.guardarSociosDeProyectoPrimeraVez(listaSociosEstrategicos,
							proyectoActual.getProjId());
					listaSociosEstrategicos = proyectosSocioEstFacade
							.listaSociosEstrategicosPorProyecto(proyectoActual.getProjId());

				} else {
					proyectoActual = proyectoFacade.editar(sesBean.getUsuarioLogeado().getUserName(), proyectoActual);
					flagCrearGeo = flagModifGeo;
					if (flagCrearGeo) {
						proyectosGeoAreaFacade.eliminarGeosDeProyecto(proyectoActual.getProjId());
						listaAreasGeoNivel1Prov = proyectosGeoAreaFacade.eliminarIdsDeListado(listaAreasGeoNivel1Prov);
						listaAreasGeoNivel2Cant = proyectosGeoAreaFacade.eliminarIdsDeListado(listaAreasGeoNivel2Cant);
						listaAreasGeoNivel3Parr = proyectosGeoAreaFacade.eliminarIdsDeListado(listaAreasGeoNivel3Parr);
						flagModifGeo = false;
					}

					if (esCampioTipoImportante()) {
						proyectosObjEspCompFacade.eliminarCompObjDeProyecto(proyectoActual.getProjId());
						flagTipoProy = proyectoActual.getProjType();
					}
				}

				if (flagCrearGeo) {
					proyectosGeoAreaFacade.guardarAreasDeProyecto(listaAreasGeoNivel1Prov, proyectoActual.getProjId());
					proyectosGeoAreaFacade.guardarAreasDeProyecto(listaAreasGeoNivel2Cant, proyectoActual.getProjId());
					proyectosGeoAreaFacade.guardarAreasDeProyecto(listaAreasGeoNivel3Parr, proyectoActual.getProjId());
				}
				// proyectosConvenioFacade.guardarConvenios(listaConvenios,
				// proyectoActual.getProjId());
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (op.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion03(proyectoActual);
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

	private String construirCodigoProyecto(String tipo) {
		String code = "";
		try {
			code = tipo + appBean.getBundle().getString("lbl134") + projCodAnio + "-" + projCodSec;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return code;
	}

	private String construirPlazoProyecto(String anio, String mes) {
		String code = "";
		try {
			code = anio + "-" + mes;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return code;
	}

	public void retomarContactoConServidor() {
		try {
			// metodo que al ejecutarse garantiza que se reinicie el timeout
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeccion02(Project proyecto, List<ProjectsStrategicPartner> lstSocEst,
			List<AreaGeoRegistro> lstProv) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (proyecto.getAcplId() == null || proyecto.getAcplId().getAcplId() == null) {
				sesBean.validacionMsg("frm:acplId", appBean.getBundle().getString("lbl116")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			boolean valCod1 = true;
			boolean valCod2 = true;
			boolean valCod3 = true;
			if (proyecto.getProjType() == null || proyecto.getProjType().length() == 0) {
				sesBean.validacionMsg("frm:projType",appBean.getBundle().getString("lbl120")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
				valCod1 = false;
			}
			if (projCodAnio == 0) {
				sesBean.validacionMsg("frm:projCode", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
				valCod2 = false;
			}
			if (projCodSec.length() != 3) {
				sesBean.validacionMsg("frm:projCode", appBean.getBundle().getString("msg107"), listaMsg);
				valCod3 = false;
			}
			if (valCod1 && valCod2 && valCod3) {
				String titulo = proyectoFacade.obtenerProyectoConCodigoDuplicado(proyecto.getProjId(),
						construirCodigoProyecto(proyecto.getProjType()));
				if (titulo.length() > 0) {
					sesBean.validacionMsg("frm:projCode", appBean.getBundle().getString("msg145") + ": " + titulo,
							listaMsg);
				}
			}
			if (proyecto.getProjTitle().length() == 0) {
				sesBean.validacionMsg("frm:projTitle", appBean.getBundle().getString("lbl115")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

			if (proyecto.getAcplId() != null) {
				Date fechaInicioPA = new Date();
				Date dummyDateNow = new Date();
				if (proyecto.getAcplId().getAcplStartDate() == null) {
					fechaInicioPA = actionPlanFacade.find(proyecto.getAcplId().getAcplId()).getAcplStartDate();
				} else {
					fechaInicioPA = proyecto.getAcplId().getAcplStartDate();
				}

				if (proyecto.getProjRegistrationDate() != null) {
					if (proyecto.getProjRegistrationDate().compareTo(dummyDateNow) > 0) {
						sesBean.validacionMsg("frm:projDateReg", appBean.getBundle().getString("msg090"), listaMsg);
					} else if (proyecto.getProjRegistrationDate().compareTo(fechaInicioPA) < 0) {
						sesBean.validacionMsg("frm:projDateReg", appBean.getBundle().getString("msg092"), listaMsg);
					}
					if (proyecto.getProjStartDate() != null) {
						if (proyecto.getProjRegistrationDate().compareTo(proyecto.getProjStartDate()) > 0) {
							sesBean.validacionMsg("frm:projStartDate", appBean.getBundle().getString("msg180"),
									listaMsg);
						}
					}
				}

				if (proyecto.getProjStartDate() != null) {
					if (proyecto.getProjStartDate().compareTo(fechaInicioPA) < 0) {
						sesBean.validacionMsg("frm:projStartDate", appBean.getBundle().getString("msg092"), listaMsg);
					}
				}
			}

			if (projPlazoDesdAnio == null || projPlazoDesdAnio.length() != 4) {
				sesBean.validacionMsg("frm:projTermFrom", appBean.getBundle().getString("lbl135")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}else{
				if (projPlazoHastAnio != null && projPlazoHastAnio.length() == 4) {
					if((proyecto.getProjStartDate().getYear()+1900)<Integer.valueOf(projPlazoDesdAnio)||(proyecto.getProjStartDate().getYear()+1900)>Integer.valueOf(projPlazoHastAnio)){
						sesBean.validacionMsg("frm:projStartDate", appBean.getBundle().getString("msg191"), listaMsg);
					}
				}
				
			}
			if (projPlazoDesdMes == null || projPlazoDesdMes.length() != 2) {
				sesBean.validacionMsg("frm:projTermFromMonth", appBean.getBundle().getString("lbl135")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (projPlazoHastAnio == null || projPlazoHastAnio.length() != 4) {
				sesBean.validacionMsg("frm:projTermTo",appBean.getBundle().getString("lbl136")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (projPlazoHastMes == null || projPlazoHastMes.length() != 2) {
				sesBean.validacionMsg("frm:projTermToMonth", appBean.getBundle().getString("lbl136")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (projPlazoDesdAnio != null && projPlazoHastAnio != null
					&& Integer.valueOf(projPlazoDesdAnio) > Integer.valueOf(projPlazoHastAnio)) {
				sesBean.validacionMsg("frm:projTermFrom", appBean.getBundle().getString("msg087"), listaMsg);
			}
			if (proyecto.getProjGeneralPurpose().length() == 0) {
				sesBean.validacionMsg("frm:projGenPurp", appBean.getBundle().getString("lbl007")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

			if (lstProv.isEmpty()) {
				sesBean.validacionMsg("frm:lstProv", appBean.getBundle().getString("msg060"), listaMsg);
			}
			/*
			 * if (lstConv.isEmpty()) { sesBean.validacionMsg("frm:lstConv",
			 * appBean.getBundle().getString("msg061"), listaMsg); }
			 */
			if (proyecto.getProjHasRelationshipOther() && !proyecto.getProjIsFinancier()
					&& (proyecto.getProjParentId() == null || proyecto.getProjParentId().getProjId() == null)) {
				sesBean.validacionMsg("frm:projParent", appBean.getBundle().getString("msg062"), listaMsg);
			}
			if (proyecto.getProjDirectBeneficiariesNumber() == null
					|| proyecto.getProjDirectBeneficiariesNumber() <= 0) {
				sesBean.validacionMsg("frm:projBenDir", appBean.getBundle().getString("lbl127")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (proyecto.getProjShortName() == null || proyecto.getProjShortName().length() <= 0) {
				sesBean.validacionMsg("frm:projShortName", appBean.getBundle().getString("lbl414")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (proyecto.getProjStyleColor() == null || (proyecto.getProjStyleColor().length() < 6)
					|| (proyecto.getProjStyleColor().length() > 7)) {
				proyecto.setProjStyleColor("#20BDB4");
			} else {
				if (proyecto.getProjStyleColor().length() == 6) {
					proyecto.setProjStyleColor("#" + proyecto.getProjStyleColor());
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	private void cargarListaSocioEstrategicosApoyoSinPdi() {
		try {
			listaSociosEstrategicosApoyoSinPdi = new ArrayList<>();
			for (ProjectsStrategicPartner psp : listaSociosEstrategicos) {
				if (psp.getPspaType() != null) {
					listaSociosEstrategicosApoyoSinPdi.add(psp.getPartId());
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarComponentesProyecto(Integer codProy) {
		try {
			if (tipoConsultaComponentes == 0) {
				listaComponentesObjetivos = proyectosObjEspCompFacade.listarObjetivosPadrePorProyecto(codProy);
			} else {
				listaComponentesObjetivos = proyectosObjEspCompFacade.listarObjetivosHijosPorProyecto(codProy);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarSeccion03(Project proyecto) {
		try {
			controlAvance = 3;
			PrimeFaces.current().executeScript("PF('dataTableIndicadores').clearFilters()");
			cargarListaSocioEstrategicosApoyoSinPdi();
			cargarComponentesProyecto(proyecto.getProjId());
			listaComponentesObjetivosTodos = proyectosObjEspCompFacade.listarObjetivosPorProyecto(proyecto.getProjId());
			listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyecto.getProjId());

			/*
			 * if (proyecto.getProjType().equals("PDI")) { listaTipo2Objetivos =
			 * proyectosObjEspCompFacade.listarObjetivosPorProyecto(proyecto.
			 * getProjId()); listaTipo2AccionesDeObjetivos =
			 * proyectosAccionFacade
			 * .listarAccionesySubaccionesPorPlanDeImplementacion(proyecto.
			 * getProjId()); } else { listaTipo1Componentes =
			 * proyectosObjEspCompFacade.listarObjetivosPorProyecto(proyecto.
			 * getProjId()); listaTipo1MetasDeComponentes =
			 * proyectosAccionFacade
			 * .listarMetasPorProgramaProyecto(proyecto.getProjId());
			 * listaTipo1IndicadoresDeMetas = proyectosAccionFacade
			 * .listarIndicadoresPorProgramaProyecto(proyecto.getProjId());
			 * listaTipo1AccionesDeIndicadores = proyectosAccionFacade
			 * .listarAccionesPorProgramaProyecto(proyecto.getProjId()); }
			 */

			if (listaComponentesPlanActual.isEmpty()) {
				listaComponentesPlanActual = componentePAFacade
						.listarComponentesPorPlan(proyecto.getAcplId().getAcplId());
			}
			if (listaIndicadoresPlanActual.isEmpty()) {
				listaIndicadoresPlanActual = medidaFacade
						.listarIndicadoresPorPAParaCompatibilidad(proyecto.getAcplId().getAcplId());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoComponenteObjetivo() {
		try {
			inicializarObjComponenteObjetivo();
			PrimeFaces.current().executeScript("PF('dlgCompObj').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjComponenteObjetivo() {
		try {
			componenteObjetivoActual = new ProjectsSpecificObjective();
			componenteObjetivoActual.setProjId(proyectoActual);
			componenteObjetivoActual.setGeloId(new GeographicalLocations());
			codProvinciasSeleccionadas=null;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * public void prepararNuevoComponentePro() { try {
	 * inicializarObjComponentePro(); codCatObjActual = 0;
	 * PrimeFaces.current().executeScript("PF('dlgCompPro').show()"); } catch
	 * (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private void inicializarObjComponentePro() { try { componenteProActual =
	 * new ProjectsSpecificObjective();
	 * componenteProActual.setProjId(proyectoActual); //
	 * componenteProActual.setCataId(new Catalog()); } catch (Exception ex) {
	 * LOG.log(Level.SEVERE, null, ex); } }
	 */

	public void seleccionarComponenteObjetivo(ProjectsSpecificObjective obj) {
		try {
			componenteObjetivoActual = obj;
			if (componenteObjetivoActual.getGeloId() == null) {
				List<ProjectsSpecificObjective> hijos = proyectosObjEspCompFacade
						.listarObjetivosHijosPorPadre(componenteObjetivoActual.getPsobId());
				if (!hijos.isEmpty()) {
					codProvinciasCompObjActual = new Integer[hijos.size()];
					for (int i = 0; i < hijos.size(); i++) {
						codProvinciasCompObjActual[i] = hijos.get(i).getGeloId().getGeloId();
					}
					this.codsProvEliminados = "";
					this.codsProvNuevos = new ArrayList<>();
				} else {
					codProvinciasCompObjActual = new Integer[0];
				}
				codProvinciasSeleccionadas = codProvinciasCompObjActual;
			}
			PrimeFaces.current().executeScript("PF('dlgCompObj').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void obtenerCodigosProvEnEdicionComponenteObjetivo(Integer[] codProvAntes, Integer[] codprovAhora) {
		try {
			List<Integer> codsProvAEliminar = new ArrayList<>();
			List<Integer> codsProvACrear = new ArrayList<>();
			for (int i = 0; i < codProvAntes.length; i++) {
				boolean flagAEliminar = true;
				for (int j = 0; j < codprovAhora.length; j++) {
					if (codprovAhora[j] == codProvAntes[i]) {
						flagAEliminar = false;
					}
				}
				if (flagAEliminar) {
					codsProvAEliminar.add(codProvAntes[i]);
				}
			}

			for (int i = 0; i < codprovAhora.length; i++) {
				boolean flagACrear = true;
				for (int j = 0; j < codProvAntes.length; j++) {
					if (codProvAntes[j] == codprovAhora[i]) {
						flagACrear = false;
					}
				}
				if (flagACrear) {
					codsProvACrear.add(codprovAhora[i]);
				}
			}
			this.codsProvEliminados = "";
			for (Integer a : codsProvAEliminar) {
				codsProvEliminados = this.codsProvEliminados + "," + a;
			}
			if (codsProvEliminados.length() > 0) {
				codsProvEliminados = codsProvEliminados.substring(1);
			}
			codsProvNuevos = codsProvACrear;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarComponenteObjetivo() {
		try {
			List<String[]> listaMsg = validarObjetivoComponente(componenteObjetivoActual, codProvinciasSeleccionadas);

			if (listaMsg.isEmpty()) {
				int flagCreateEdit = 0;
				String msg = "";
				if (componenteObjetivoActual.getPsobId() == null) {
					String desc = componenteObjetivoActual.getPsobDescription();
					String code = componenteObjetivoActual.getPsobCode();
					Integer sec = listaComponentesObjetivos.size() + 1;
					ProjectsSpecificObjective psoNac = new ProjectsSpecificObjective();
					psoNac.setProjId(proyectoActual);
					psoNac.setPsobCode(code);
					psoNac.setPsobDescription(desc);
					psoNac.setPsobType("N");
					psoNac.setPsobSequence(sec);
					psoNac = proyectosObjEspCompFacade.create(psoNac);
					for (int i = 0; i < codProvinciasSeleccionadas.length; i++) {
						ProjectsSpecificObjective pso = new ProjectsSpecificObjective();
						pso.setProjId(proyectoActual);
						pso.setPsobParentId(psoNac);
						pso.setPsobCode(code);
						pso.setPsobDescription(desc);
						pso.setPsobSequence(sec);
						pso.setGeloId(new GeographicalLocations(codProvinciasSeleccionadas[i]));
						proyectosObjEspCompFacade.create(pso);
						sec++;
					}
					flagCreateEdit = 0;
				} else {
					proyectosObjEspCompFacade.edit(componenteObjetivoActual);
					if (componenteObjetivoActual.getPsobParentId() != null) {
						String code = componenteObjetivoActual.getPsobCode();
						String desc = componenteObjetivoActual.getPsobDescription();
						ProjectsSpecificObjective nac = componenteObjetivoActual.getPsobParentId();
						nac.setPsobCode(code);
						nac.setPsobDescription(desc);
						proyectosObjEspCompFacade.edit(nac);
					} else {
						obtenerCodigosProvEnEdicionComponenteObjetivo(codProvinciasCompObjActual,
								codProvinciasSeleccionadas);
						proyectosObjEspCompFacade.actualizarProvDeCompObj(componenteObjetivoActual, codsProvNuevos,
								codsProvEliminados);
					}

					flagCreateEdit = 1;
				}
				if (proyectoActual.getProjType().equals("PDI")) {
					msg = flagCreateEdit == 0 ? appBean.getBundle().getString("msg004")
							: appBean.getBundle().getString("msg005");
				} else {
					msg = flagCreateEdit == 0 ? appBean.getBundle().getString("msg010")
							: appBean.getBundle().getString("msg011");
				}
				sesBean.addSuccessMessage(msg);
				cargarComponentesProyecto(proyectoActual.getProjId());
				listaComponentesObjetivosTodos = proyectosObjEspCompFacade
						.listarObjetivosPorProyecto(proyectoActual.getProjId());

				PrimeFaces.current().executeScript("PF('dlgCompObj').hide()");
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

	private List<String[]> validarObjetivoComponente(ProjectsSpecificObjective obj, Integer[] codProvs) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (obj.getPsobCode() == null || obj.getPsobCode().length() == 0) {
				sesBean.validacionMsg("frm:objEspCode", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (obj.getPsobDescription() == null || obj.getPsobDescription().length() == 0) {
				if (proyectoActual.getProjType().equals("PDI")) {
					sesBean.validacionMsg("frm:objEspDesc", appBean.getBundle().getString("lbl014")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
				}else{
					sesBean.validacionMsg("frm:objEspDesc", appBean.getBundle().getString("lbl022")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
				}
				
			}
			if (obj.getPsobId() == null && codProvs.length == 0) {
				sesBean.validacionMsg("frm:objEspProvs", appBean.getBundle().getString("msg141"), listaMsg);
			}
			if (obj.getPsobId() != null && obj.getPsobType() == null && obj.getGeloId() == null) {
				sesBean.validacionMsg("frm:objEspProv", appBean.getBundle().getString("msg141"), listaMsg);
			}
			/*
			 * if (obj.getCataId() == null || obj.getCataId().getCataId() ==
			 * null) { sesBean.validacionMsg("frm:objEspSubc",
			 * appBean.getBundle().getString("msg031"), listaMsg); }
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarObjetivoComponente(ProjectsSpecificObjective obj) {
		try {
			proyectosObjEspCompFacade.eliminarLogico(obj);
			cargarComponentesProyecto(proyectoActual.getProjId());
			listaComponentesObjetivosTodos = proyectosObjEspCompFacade
					.listarObjetivosPorProyecto(proyectoActual.getProjId());
			if (proyectoActual.getProjType().equals("PDI")) {
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg006"));
			} else {
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg012"));
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevaAccion() {
		try {
			inicializarObjAccion();
			PrimeFaces.current().executeScript("PF('dlgAccion').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoIndicador(ProjectsAction accionPadre) {
		try {
			inicializarObjIndicador(accionPadre);
			PrimeFaces.current().executeScript("PF('dlgIndicador').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjIndicador(ProjectsAction accionPadre) {
		try {
			indicadorActual = new ProjectsAction();
			indicadorActual.setPracType("I");
			indicadorActual.setPsobId(accionPadre.getPsobId());
			indicadorActual.setPracParentId(accionPadre);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjAccion() {
		try {
			accionActual = new ProjectsAction();
			accionActual.setPracType("A");
			accionActual.setPsobId(new ProjectsSpecificObjective());
			accionActual.setPartId(new Partner());
			listaIndicadoresAccionActual = new ArrayList<>();
			ProjectsAction i = new ProjectsAction();
			i.setPracType("I");
			listaIndicadoresAccionActual.add(i);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void aniadirIndicadorALista() {
		try {
			ProjectsAction i = new ProjectsAction();
			i.setPracType("I");
			listaIndicadoresAccionActual.add(i);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarIndicadorDeLista() {
		try {
			if (listaIndicadoresAccionActual.size() > 0) {
				listaIndicadoresAccionActual.remove(listaIndicadoresAccionActual.size() - 1);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarAccion(ProjectsAction accion) {
		try {
			accionActual = accion;
			if (accionActual.getPartId() == null) {
				accionActual.setPartId(new Partner());
			}
			PrimeFaces.current().executeScript("PF('dlgAccion').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarIndicador(ProjectsAction accion) {
		try {
			indicadorActual = accion;
			PrimeFaces.current().executeScript("PF('dlgIndicador').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarAccion() {
		try {
			List<String[]> listaMsg = validarAccion(accionActual, listaIndicadoresAccionActual);

			if (listaMsg.isEmpty()) {
				if (accionActual.getPartId() != null && accionActual.getPartId().getPartId() == null) {
					accionActual.setPartId(null);
				}
				if (accionActual.getPracId() == null) {
					accionActual = proyectosAccionFacade.create(accionActual);

					for (ProjectsAction pa : listaIndicadoresAccionActual) {
						pa.setPsobId(accionActual.getPsobId());
						pa.setPracParentId(accionActual);
						proyectosAccionFacade.create(pa);
					}

					sesBean.addSuccessMessage(appBean.getBundle().getString("msg016"));

				} else {
					proyectosAccionFacade.edit(accionActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg017"));
				}
				inicializarObjAccion();
				listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyectoActual.getProjId());
				PrimeFaces.current().executeScript("PF('dlgAccion').hide()");
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

	private List<String[]> validarAccion(ProjectsAction accion, List<ProjectsAction> lstInd) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (accion.getPsobId() == null || accion.getPsobId().getPsobId() == null) {
				sesBean.validacionMsg("frm:accionObj", appBean.getBundle().getString("lbl020")+" / "+appBean.getBundle().getString("lbl022")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (accion.getPracCode().length() == 0) {
				sesBean.validacionMsg("frm:accionCode", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (accion.getPracDescription().length() == 0) {
				sesBean.validacionMsg("frm:accionDesc", appBean.getBundle().getString("lbl244")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

			if (accion.getPracId() == null) {
				if (lstInd == null || lstInd.isEmpty()) {
					sesBean.validacionMsg("frm:lstInd", appBean.getBundle().getString("msg138"), listaMsg);
				} else {
					for (ProjectsAction pa : lstInd) {
						boolean valInd = true;
						if (pa.getPracDescription() == null || pa.getPracDescription().length() == 0) {
							valInd = false;
						}
						if (pa.getPracGoal() == null || pa.getPracGoal().length() == 0) {
							valInd = false;
						}
						if (pa.getPracGoalValue() == null || pa.getPracGoalValue() < 0) {
							valInd = false;
						}
						if (!valInd) {
							sesBean.validacionMsg("frm:lstInd", appBean.getBundle().getString("msg183"), listaMsg);
						}
					}
				}
			}

			/*
			 * if (accion.getPracWeighing() == null || accion.getPracWeighing()
			 * == 0) { sesBean.validacionMsg("frm:accpdiPeso",
			 * appBean.getBundle().getString("msg031"), listaMsg); }
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void guardarIndicador() {
		try {
			List<String[]> listaMsg = validarIndicador(indicadorActual);
			if (listaMsg.isEmpty()) {
				if (indicadorActual.getPracId() == null) {
					proyectosAccionFacade.create(indicadorActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg051"));
				} else {
					proyectosAccionFacade.edit(indicadorActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg052"));
				}
				listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyectoActual.getProjId());
				PrimeFaces.current().executeScript("PF('dlgIndicador').hide()");
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

	private List<String[]> validarIndicador(ProjectsAction accion) {
		List<String[]> listaMsg = new ArrayList<>();
		try {

			if (accion.getPracDescription().length() == 0) {
				sesBean.validacionMsg("frm:indDesc", appBean.getBundle().getString("msg139"), listaMsg);
			}
			if (accion.getPracGoalValue()==null||accion.getPracGoalValue()<=0) {
				sesBean.validacionMsg("frm:indMetaMinima", appBean.getBundle().getString("msg140"), listaMsg);
			}
			if (accion.getPracGoal().length() == 0) {
				sesBean.validacionMsg("frm:indMeta", appBean.getBundle().getString("msg140"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarAccion(ProjectsAction accion) {
		try {
			proyectosAccionFacade.eliminarLogico(accion);
			listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyectoActual.getProjId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg018"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarIndicador(ProjectsAction indicador) {
		try {
			proyectosAccionFacade.eliminarLogico(indicador);
			listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyectoActual.getProjId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg053"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarAccionCompatibilidad(ProjectsAction acc) {
		try {
			indicadorActual = acc;
			inicializarCompAccion();
			PrimeFaces.current().executeScript("PF('dlgAccCompat').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarCompAccion() {
		try {
			codCompCompat = new Component();
			codMedCompat = new Measure();
			codAccCompat = new Action();
			codIndEnlace = new Measure();
			if (listaComponentesPlanActual.isEmpty()) {
				listaComponentesPlanActual = componentePAFacade
						.listarComponentesPorPlan(proyectoActual.getAcplId().getAcplId());
			}
			listaMedidasPlanActual = new ArrayList<>();
			listaAccionesPlanActual = new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cargarMedidasAcciones(String tipo) {
		try {
			if (tipo.equals("MED")) {
				if (codCompCompat != null && codCompCompat.getCompId() != null && codCompCompat.getCompId() > 0) {
					listaMedidasPlanActual = medidaFacade.listarMedidasPorComponente(codCompCompat.getCompId());
					listaAccionesPlanActual = new ArrayList<>();
					if (listaMedidasPlanActual == null || listaMedidasPlanActual.isEmpty()) {
						sesBean.addWarningMessage(appBean.getBundle().getString("msg155"));
					}
				}
			} else {
				if (codMedCompat != null && codMedCompat.getMeasId() != null && codMedCompat.getMeasId() > 0) {
					listaAccionesPlanActual = accionFacade.listarAccionesPorMedida(codMedCompat.getMeasId());
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarCompatibilidadAccion() {
		try {
			boolean val = true;
			String valMsg = "";
			if ((codCompCompat == null || codCompCompat.getCompId() == 0)
					&& (codMedCompat == null || codMedCompat.getMeasId() == 0)
					&& (codAccCompat == null || codAccCompat.getActiId() == 0)) {
				val = false;
				valMsg = appBean.getBundle().getString("msg105");
			}
			if (val) {
				int idAcc = 0;
				idAcc = indicadorActual.getPracId();
				boolean val2 = true;
				if (codAccCompat != null && codAccCompat.getActiId() != null && codAccCompat.getActiId() > 0) {
					if (!compatibilidadAccFacade.existeRegistroCompatibilidad(idAcc, "ACC", codAccCompat.getActiId())) {
						compatibilidadAccFacade.crearRegistroCompatibilidad(idAcc, "ACC", codAccCompat.getActiId());
					} else {
						val2 = false;
					}

				} else {
					if (codMedCompat != null && codMedCompat.getMeasId() != null && codMedCompat.getMeasId() > 0) {
						if (!compatibilidadAccFacade.existeRegistroCompatibilidad(idAcc, "MED",
								codMedCompat.getMeasId())) {
							compatibilidadAccFacade.crearRegistroCompatibilidad(idAcc, "MED", codMedCompat.getMeasId());
						} else {
							val2 = false;
						}

					} else {
						if (!compatibilidadAccFacade.existeRegistroCompatibilidad(idAcc, "COM",
								codCompCompat.getCompId())) {
							compatibilidadAccFacade.crearRegistroCompatibilidad(idAcc, "COM",
									codCompCompat.getCompId());
						} else {
							val2 = false;
						}

					}
				}
				if (val2) {
					actualizarAccionesConCompatibilidad(idAcc);
					inicializarCompAccion();
				} else {
					sesBean.addErrorMessage(appBean.getBundle().getString("msg178"));
				}

			} else {
				sesBean.addErrorMessage(valMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void actualizarAccionesConCompatibilidad(Integer idAccActual) {
		try {
			listaIndicadores = proyectosAccionFacade.listarIndicadoresPorProyecto(proyectoActual.getProjId());
			indicadorActual = proyectosAccionFacade.obtenerObjetoActualizadoDesdeLista(listaIndicadores, idAccActual);

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarCompatibilidad(String idPaco, Integer idProjAcc) {
		try {
			compatibilidadAccFacade.eliminarLogico(Integer.valueOf(idPaco));
			actualizarAccionesConCompatibilidad(idProjAcc);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarEnlaceAccionIndicador() {
		try {
			if (codIndEnlace != null && codIndEnlace.getMeasId() != null && codIndEnlace.getMeasId() > 0) {
				if (!compatibilidadAccFacade.existeRegistroCompatibilidad(indicadorActual.getPracId(), "IND",
						codIndEnlace.getMeasId())) {
					compatibilidadAccFacade.crearRegistroCompatibilidad(indicadorActual.getPracId(), "IND",
							codIndEnlace.getMeasId());
					actualizarAccionesConCompatibilidad(indicadorActual.getPracId());
					codIndEnlace=new Measure();
				} else {
					sesBean.addErrorMessage(appBean.getBundle().getString("msg178"));
				}

			}else{
				sesBean.addErrorMessage(appBean.getBundle().getString("msg185"));
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void validaryContinuar03() {
		try {
			boolean isValid = true;
			actualizarPonderacionesIndicadores();

			if (listaIndicadores.isEmpty()) {
				isValid = false;
				sesBean.addErrorMessage(appBean.getBundle().getString("msg078"));
			}

			if (isValid) {
				cargarSeccion04(proyectoActual);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void actualizarPonderacionesIndicadores() {
		try {
			int totalIndicadores = 0;

			totalIndicadores = listaIndicadores.size();
			if (totalIndicadores > 0) {
				BigDecimal bd = new BigDecimal(totalIndicadores);
				double p = (new BigDecimal("100")).divide(bd, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal a = new BigDecimal("100");
				BigDecimal b = (new BigDecimal((p * (totalIndicadores - 1))));
				double p1 = a.subtract(b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				ProjectsAction pa = listaIndicadores.get(0);
				pa.setPracWeighing(p1);
				proyectosAccionFacade.edit(pa);
				for (int i = 1; i < totalIndicadores; i++) {
					ProjectsAction ps = listaIndicadores.get(i);
					ps.setPracWeighing(p);
					proyectosAccionFacade.edit(ps);
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * private void cargarSeccion04(Project proyecto) { try { controlAvance = 4;
	 * if (listaComponentesPlanActual.isEmpty()) { listaComponentesPlanActual =
	 * componentePAFacade
	 * .listarComponentesPorPlan(proyecto.getAcplId().getAcplId()); }
	 * listaCobeneficios =
	 * proyectosCobFacade.listarCobeneficiosPorProyecto(proyectoActual.getProjId
	 * ()); } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * public void prepararNuevoCobenef() { try { inicializarObjCobenef();
	 * PrimeFaces.current().executeScript("PF('dlgCobeneficio').show()"); }
	 * catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private void inicializarObjCobenef() { try { cobeneficioActual = new
	 * ProjectsCobenefit(); cobeneficioActual.setProjId(proyectoActual);
	 * cobeneficioActual.setCataId(new Catalog());
	 * cobeneficioActual.setCompId(new Component()); } catch (Exception ex) {
	 * LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * public void seleccionarCobeneficio(ProjectsCobenefit cob) { try {
	 * cobeneficioActual = cob;
	 * PrimeFaces.current().executeScript("PF('dlgCobeneficio').show()"); }
	 * catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * public void guardarCobeneficio() { try { List<String[]> listaMsg =
	 * validarCobeneficio(cobeneficioActual);
	 * 
	 * if (listaMsg.isEmpty()) { if (cobeneficioActual.getPrcoId() == null) {
	 * proyectosCobFacade.create(cobeneficioActual);
	 * sesBean.addSuccessMessage(appBean.getBundle().getString("msg019")); }
	 * else { proyectosCobFacade.edit(cobeneficioActual);
	 * sesBean.addSuccessMessage(appBean.getBundle().getString("msg020")); }
	 * listaCobeneficios =
	 * proyectosCobFacade.listarCobeneficiosPorProyecto(proyectoActual.getProjId
	 * ()); PrimeFaces.current().executeScript("PF('dlgCobeneficio').hide()"); }
	 * else {
	 * 
	 * for (String[] s : listaMsg) { sesBean.mensajeErrorComponente(s[0], s[1]);
	 * } PrimeFaces.current().focus(listaMsg.get(0)[0]); } } catch (Exception
	 * ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private List<String[]> validarCobeneficio(ProjectsCobenefit cob) {
	 * List<String[]> listaMsg = new ArrayList<>(); try { if (cob.getCataId() ==
	 * null || cob.getCataId().getCataId() == null) {
	 * sesBean.validacionMsg("frm:cobType",
	 * appBean.getBundle().getString("msg031"), listaMsg); } if (cob.getCompId()
	 * == null || cob.getCompId().getCompId() == null) {
	 * sesBean.validacionMsg("frm:cobComp",
	 * appBean.getBundle().getString("msg031"), listaMsg); } if
	 * (cob.getPrcoDescription().length() == 0) {
	 * sesBean.validacionMsg("frm:cobDesc",
	 * appBean.getBundle().getString("msg031"), listaMsg); } } catch (Exception
	 * ex) { LOG.log(Level.SEVERE, null, ex); } return listaMsg; }
	 * 
	 * public void prepararEnlaceCobeneficioSalvaguarda(ProjectsCobenefit cob) {
	 * try { cobeneficioActual = cob; List<ProjectsSafeguard> lstActual =
	 * proyectoSalvagFacade.listarSalvaguardasPorCobeneficio(cob.getPrcoId());
	 * listaSalvaguardasItemActual = new ArrayList<>();
	 * listaSalvaguardasItemActual =
	 * proyectoSalvagFacade.listarSalvaguardasProyectoTipo(
	 * appBean.getLstSalvaguardasTemp(), "COBENEFICIO", cob.getPrcoId(),
	 * lstActual);
	 * PrimeFaces.current().executeScript("PF('dlgSalvagCob').show()"); } catch
	 * (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * public void guardarEnlaceCobeneficioSalvaguarda() { try {
	 * proyectoSalvagFacade.guardarSalvaguardasProyecto(
	 * listaSalvaguardasItemActual);
	 * PrimeFaces.current().executeScript("PF('dlgSalvagCob').hide()"); } catch
	 * (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * public void eliminarCobeneficio(ProjectsCobenefit cob) { try {
	 * proyectosCobFacade.eliminarLogico(cob); listaCobeneficios =
	 * proyectosCobFacade.listarCobeneficiosPorProyecto(proyectoActual.getProjId
	 * ()); sesBean.addSuccessMessage(appBean.getBundle().getString("msg021"));
	 * } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 */
	/*
	 * public void validaryContinuar04() { try {
	 * 
	 * boolean isValid = true; for (Component comp : listaComponentesPlanActual)
	 * { boolean f = false; for (ProjectsCobenefit c1 : listaCobeneficios) { if
	 * (c1.getCompId().getCompId() == comp.getCompId()) { f = true; } } if (!f)
	 * { isValid = false; } } if (!isValid) {
	 * sesBean.addWarningMessage("ALERTA: " +
	 * appBean.getBundle().getString("msg079")); }
	 * if(listaCobeneficios.isEmpty()){ sesBean.addErrorMessage("ALERTA: " +
	 * appBean.getBundle().getString("msg093")); }else{
	 * cargarSeccion05(proyectoActual); }
	 * 
	 * cargarSeccion05(proyectoActual); } catch (Exception ex) {
	 * LOG.log(Level.SEVERE, null, ex); } }
	 */

	/*
	 * private void cargarSeccion05(Project proyecto) { try { controlAvance = 5;
	 * cargarInfoDeGeneroDeProyecto(proyectoActual); } catch (Exception ex) {
	 * LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private void cargarInfoDeGeneroDeProyecto(Project proyecto) { try {
	 * List<ProjectsGenderInfo> lstInfoGenero = new ArrayList<>(); if
	 * (proyecto.getProjId() != null) { lstInfoGenero =
	 * proyectosEnfGeneroFacade.listarInfoGeneroPorProyecto(proyecto.getProjId()
	 * ); } listaLineasEnfoqueGenero1 = new ArrayList<>();
	 * listaLineasEnfoqueGenero2 = new ArrayList<>(); listaLineasEnfoqueGenero3
	 * = new ArrayList<>(); listaLineasEnfoqueGenero1 =
	 * obtenerListaEnfGeneroPorCat(appBean.getLstCatEnfGenero1(), lstInfoGenero,
	 * proyecto); listaLineasEnfoqueGenero2 =
	 * obtenerListaEnfGeneroPorCat(appBean.getLstCatEnfGenero2(), lstInfoGenero,
	 * proyecto); listaLineasEnfoqueGenero3 =
	 * obtenerListaEnfGeneroPorCat(appBean.getLstCatEnfGenero3(), lstInfoGenero,
	 * proyecto);
	 * 
	 * } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private List<ProjectsGenderInfo>
	 * obtenerListaEnfGeneroPorCat(List<Catalog> catalogo,
	 * List<ProjectsGenderInfo> infoProyecto, Project proyecto) {
	 * List<ProjectsGenderInfo> lstInfoGenero = new ArrayList<>(); try { for
	 * (Catalog c : catalogo) { ProjectsGenderInfo pgin = new
	 * ProjectsGenderInfo(); pgin.setProjId(proyecto); for (ProjectsGenderInfo i
	 * : infoProyecto) { if (i.getCataId().equals(c)) {
	 * pgin.setPginId(i.getPginId()); pgin.setCataId(c);
	 * pgin.setPginActivities(i.getPginActivities());
	 * pgin.setPginBudget(i.getPginBudget());
	 * pgin.setPginIndicator(i.getPginIndicator());
	 * pgin.setPginResults(i.getPginResults());
	 * pgin.setPginOtherLine(i.getPginOtherLine()); pgin.setSeleccionado(true);
	 * } } if (pgin.getCataId() == null) { pgin.setCataId(c); }
	 * lstInfoGenero.add(pgin); } } catch (Exception ex) { LOG.log(Level.SEVERE,
	 * null, ex); } return lstInfoGenero; }
	 * 
	 * public void guardaryContinuar05(String opcion) { try {
	 * 
	 * List<String[]> listaMsg = validarInfGenero(listaLineasEnfoqueGenero1,
	 * listaLineasEnfoqueGenero2, listaLineasEnfoqueGenero3);
	 * 
	 * if (listaMsg.isEmpty()) {
	 * proyectosEnfGeneroFacade.guardarInfoDeProyecto(listaLineasEnfoqueGenero1)
	 * ;
	 * proyectosEnfGeneroFacade.guardarInfoDeProyecto(listaLineasEnfoqueGenero2)
	 * ;
	 * proyectosEnfGeneroFacade.guardarInfoDeProyecto(listaLineasEnfoqueGenero3)
	 * ; sesBean.addSuccessMessage(appBean.getBundle().getString("msg063")); if
	 * (opcion.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
	 * cargarSeccion06(proyectoActual); }
	 * 
	 * } else {
	 * 
	 * for (String[] s : listaMsg) { sesBean.mensajeErrorComponente(s[0], s[1]);
	 * } PrimeFaces.current().focus(listaMsg.get(0)[0]); }
	 * 
	 * } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } }
	 * 
	 * private List<String[]> validarInfGenero(List<ProjectsGenderInfo> lst1,
	 * List<ProjectsGenderInfo> lst2, List<ProjectsGenderInfo> lst3) {
	 * List<String[]> listaMsg = new ArrayList<>(); try { boolean isValid=false;
	 * boolean isValidG1=false; boolean isValidG2=false; boolean
	 * isValidG3=false; for (ProjectsGenderInfo g1 : lst1) { if
	 * (g1.isSeleccionado()) { isValidG1=true; isValid=true; if
	 * (g1.getPginActivities().length() == 0 || g1.getPginIndicator().length()
	 * == 0 || g1.getPginResults().length() == 0 || g1.getPginBudget().length()
	 * == 0) { sesBean.validacionMsg("frm:lstEnfGen1",
	 * appBean.getBundle().getString("msg069"), listaMsg); } } } for
	 * (ProjectsGenderInfo g2 : lst2) { if (g2.isSeleccionado()) {
	 * isValidG2=true; isValid=true; if (g2.getPginActivities().length() == 0 ||
	 * g2.getPginIndicator().length() == 0 || g2.getPginResults().length() == 0
	 * || g2.getPginBudget().length() == 0) {
	 * sesBean.validacionMsg("frm:lstEnfGen2",
	 * appBean.getBundle().getString("msg069"), listaMsg); } } } for
	 * (ProjectsGenderInfo g3 : lst3) { if (g3.isSeleccionado()) {
	 * isValidG3=true; isValid=true; if (g3.getPginActivities().length() == 0 ||
	 * g3.getPginIndicator().length() == 0 || g3.getPginResults().length() == 0
	 * || g3.getPginBudget().length() == 0) {
	 * sesBean.validacionMsg("frm:lstEnfGen3",
	 * appBean.getBundle().getString("msg069"), listaMsg); } } }
	 * 
	 * if(!isValidG1){ sesBean.validacionMsg("frm:lstEnfGen1",
	 * appBean.getBundle().getString("msg080"), listaMsg); } if(!isValidG2){
	 * sesBean.validacionMsg("frm:lstEnfGen2",
	 * appBean.getBundle().getString("msg080"), listaMsg); } if(!isValidG3){
	 * sesBean.validacionMsg("frm:lstEnfGen3",
	 * appBean.getBundle().getString("msg080"), listaMsg); }
	 * 
	 * } catch (Exception ex) { LOG.log(Level.SEVERE, null, ex); } return
	 * listaMsg; }
	 */

	private void cargarSeccion04(Project proyecto) {
		try {
			controlAvance = 4;
			listaRiesgos = proyectosRiesgoFacade.listarRiesgosPorProyecto(proyectoActual.getProjId());

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoRiesgo() {
		try {
			inicializarObjRiesgo();
			PrimeFaces.current().executeScript("PF('dlgRiesgo').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjRiesgo() {
		try {
			riesgoActual = new ProjectsRisk();
			riesgoActual.setProjId(proyectoActual);
			riesgoActual.setCataId(new Catalog());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarRiesgo(ProjectsRisk riesgo) {
		try {
			riesgoActual = riesgo;
			PrimeFaces.current().executeScript("PF('dlgRiesgo').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarRiesgo() {
		try {
			List<String[]> listaMsg = validarRiesgo(riesgoActual);

			if (listaMsg.isEmpty()) {
				riesgoActual.setPrriComplianceIndicator(" ");
				if (riesgoActual.getPrriId() == null) {
					proyectosRiesgoFacade.create(riesgoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg066"));
				} else {
					proyectosRiesgoFacade.edit(riesgoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg067"));
				}
				listaRiesgos = proyectosRiesgoFacade.listarRiesgosPorProyecto(proyectoActual.getProjId());

				PrimeFaces.current().executeScript("PF('dlgRiesgo').hide()");
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

	private List<String[]> validarRiesgo(ProjectsRisk riesgo) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (riesgo.getCataId() == null || riesgo.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:rieType", appBean.getBundle().getString("lbl019")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (riesgo.getPrriDescription().length() == 0) {
				sesBean.validacionMsg("frm:rieDesc",appBean.getBundle().getString("lbl150")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (riesgo.getPrriMitigationMeasure().length() == 0) {
				sesBean.validacionMsg("frm:rieMed", appBean.getBundle().getString("lbl153")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarRiesgo(ProjectsRisk riesgo) {
		try {
			proyectosRiesgoFacade.eliminarLogico(riesgo);
			listaRiesgos = proyectosRiesgoFacade.listarRiesgosPorProyecto(proyectoActual.getProjId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg068"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararEnlaceRiesgoSalvaguarda(ProjectsRisk risk) {
		try {
			/*
			 * riesgoActual = risk; List<ProjectsSafeguard> lstActual =
			 * proyectoSalvagFacade.listarSalvaguardasPorRiesgo(risk.getPrriId()
			 * ); listaSalvaguardasItemActual = new ArrayList<>();
			 * listaSalvaguardasItemActual =
			 * proyectoSalvagFacade.listarSalvaguardasProyectoTipo(
			 * appBean.getLstSalvaguardasTemp(), "RIESGO", risk.getPrriId(),
			 * lstActual);
			 * PrimeFaces.current().executeScript("PF('dlgSalvagRiesgo').show()"
			 * );
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarEnlaceRiesgoSalvaguarda() {
		try {
			/*
			 * proyectoSalvagFacade.guardarSalvaguardasProyecto(
			 * listaSalvaguardasItemActual); listaRiesgos =
			 * proyectosRiesgoFacade.listarRiesgosPorProyecto(proyectoActual.
			 * getProjId());
			 * PrimeFaces.current().executeScript("PF('dlgSalvagRiesgo').hide()"
			 * );
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void validaryContinuar04() {
		try {
			boolean isValid = true;
			String valMsg = "";
			if (!listaRiesgos.isEmpty()) {

				/*
				 * for (ProjectsRisk r : listaRiesgos) { if
				 * (r.getCataId().getCataNumber() <= 2 &&
				 * (r.getListaSalvaguardas() == null ||
				 * r.getListaSalvaguardas().isEmpty())) { isValid = false;
				 * valMsg = appBean.getBundle().getString("msg106"); } }
				 */

			} else {
				isValid = false;
				valMsg = appBean.getBundle().getString("msg081");

			}
			if (isValid) {
				cargarSeccion05(proyectoActual);
			} else {
				sesBean.addErrorMessage(valMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void cargarSeccion05(Project proyecto) {
		try {
			controlAvance = 5;
			listaFinancTotales = proyectosFinanFacade
					.listarMontosFinanciamientoTotalesPorProyecto(proyectoActual.getProjId());
			if (listaFinancTotales.isEmpty()) {
				proyectosFinanFacade.crearMontosInicialesDeProyecto(proyectoActual.getProjId());
				List<Integer> lstIdObj = new ArrayList<>();
				List<ProjectsSpecificObjective> lstObj = new ArrayList<>();

				lstObj = listaComponentesObjetivosTodos;

				for (ProjectsSpecificObjective o : lstObj) {
					if (o.getPsobType() != null && o.getPsobType().equals("N")) {
						lstIdObj.add(o.getPsobId());
					}

				}
				proyectosFinanFacade.crearMontosDeObjComp(proyectoActual.getProjId(), lstIdObj);
				listaFinancTotales = proyectosFinanFacade
						.listarMontosFinanciamientoTotalesPorProyecto(proyectoActual.getProjId());

			} else {
				if (proyectoActual.getProjRegisterStatus() == null
						|| !proyectoActual.getProjRegisterStatus().equals("V")) {
					proyectosFinanFacade.crearMontosDeNuevosObjetivos(proyectoActual.getProjId());
				}
			}
			listaFinancObjComp = proyectosFinanFacade
					.listarMontosFinanciamientoObjPorProyecto(proyectoActual.getProjId());
			listaFinancCofinan = proyectosFinanFacade
					.listarMontosFinanciamientoCofPorProyecto(proyectoActual.getProjId());
			calcularMontoIniPdi();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void irCreacionFuenteFinanciamiento() {
		try {
			guardarMontosFinanciamiento();
			sesBean.redireccionarAPaginaConParametros("financer", "FuenteFinanciamiento", "TIPO_PAG_FFIN",
					"SOLO_CREAR_FFIN", "ID_PROYECTO_ACTUAL", proyectoActual.getProjId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void crearMontoCofinanciamiento() {
		try {
			boolean val = true;
			if (codFuenteFinActual == null || codFuenteFinActual == 0) {
				sesBean.mensajeErrorComponente("frm:codFinan", appBean.getBundle().getString("msg070"));
				val = false;
			}
			if (typeCofin == null || typeCofin.length() == 0) {
				String id = proyectoActual.getProjType().equals("PDI") ? "frm:typeCof" : "frm:typeCof2";
				sesBean.mensajeErrorComponente(id, appBean.getBundle().getString("msg150"));
				val = false;
			}

			if (val) {
				for (ProjectsFinancing f3 : listaFinancCofinan) {
					proyectosFinanFacade.edit(f3);
				}
				proyectosFinanFacade.crearMontoCof(proyectoActual.getProjId(), codFuenteFinActual, typeCofin);
				listaFinancCofinan = proyectosFinanFacade
						.listarMontosFinanciamientoCofPorProyecto(proyectoActual.getProjId());
				codFuenteFinActual = 0;
				typeCofin = null;
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void calcularMontoIniPdi() {
		try {
			totalIniPdi = new BigDecimal("0.00");
			for (ProjectsFinancing f : listaFinancCofinan) {
				if ((f.getPrfiType().equals("PDI") || f.getPrfiType().equals("INI")) && f.getPrfiAmount() != null) {
					totalIniPdi = totalIniPdi.add(f.getPrfiAmount());
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarMontoCofinanciamiento(ProjectsFinancing cof) {
		try {
			proyectosFinanFacade.eliminarLogico(cof);
			listaFinancCofinan = proyectosFinanFacade
					.listarMontosFinanciamientoCofPorProyecto(proyectoActual.getProjId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg071"));
			calcularMontoIniPdi();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void calcularMontoRequerido() {
		try {
			listaFinancTotales = calcularMontoRequerido(listaFinancTotales);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardaryContinuar05(String opcion) {
		try {

			List<String[]> listaMsg = validarInfoFinanciamiento(listaFinancTotales, listaFinancObjComp,
					listaFinancCofinan);

			if (listaMsg.isEmpty()) {
				calcularMontoRequerido();

				guardarMontosFinanciamiento();

				proyectoActual.setProjFinancingStatus(obtenerEstadoFinanciamiento(listaFinancTotales));
				proyectoFacade.editar(sesBean.getUsuarioLogeado().getUserName(), proyectoActual);

				sesBean.addSuccessMessage(appBean.getBundle().getString("msg063"));
				if (opcion.equals(OpcionesRegistro.SIGUIENTE.getCodigo())) {
					cargarSeccion06(proyectoActual);
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

	private void guardarMontosFinanciamiento() {
		try {
			for (ProjectsFinancing f1 : listaFinancTotales) {
				proyectosFinanFacade.edit(f1);
			}
			for (ProjectsFinancing f2 : listaFinancObjComp) {
				proyectosFinanFacade.edit(f2);
			}
			for (ProjectsFinancing f3 : listaFinancCofinan) {
				proyectosFinanFacade.edit(f3);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarInfoFinanciamiento(List<ProjectsFinancing> lstTot, List<ProjectsFinancing> lstObj,
			List<ProjectsFinancing> lstCof) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if(lstTot.get(0).getPrfiAmount().compareTo(new BigDecimal("0"))<=0){
				sesBean.validacionMsg("frm:montoTotal", appBean.getBundle().getString("msg186"), listaMsg);
			}
			
			BigDecimal totalPorObj = new BigDecimal("0");
			for (ProjectsFinancing o : lstObj) {
				if (o.getPrfiAmount() != null) {
					totalPorObj = totalPorObj.add(o.getPrfiAmount());
				}
			}
			if (!totalPorObj.equals(lstTot.get(0).getPrfiAmount())) {
				sesBean.validacionMsg("frm:lstMontosObj", appBean.getBundle().getString("msg073").replace("XXX", totalPorObj+"").replace("YYY", lstTot.get(0).getPrfiAmount()+"") , listaMsg);
			}

			if (lstTot.get(1).getPrfiAmount() != null
					&& (lstTot.get(1).getPrfiAmount().compareTo(new BigDecimal("0")) > 0)) {
				BigDecimal totalFin = new BigDecimal("0");
				for (ProjectsFinancing o : lstCof) {
					if (o.getPrfiAmount() != null) {
						totalFin = totalFin.add(o.getPrfiAmount());
					}
				}
				if(lstTot.get(1).getPrfiAmount().compareTo(lstTot.get(0).getPrfiAmount())>0){
					sesBean.validacionMsg("frm:montoFinanc", appBean.getBundle().getString("msg187"), listaMsg);
				}
				/*
				 * if (lstTot.get(2).getPrfiAmount() != null) { totalFin =
				 * totalFin.add(lstTot.get(2).getPrfiAmount()); }
				 */
				if (lstTot.get(1).getPrfiAmount() != null && !totalFin.equals(lstTot.get(1).getPrfiAmount())) {
					sesBean.validacionMsg("frm:lstMontosCof", appBean.getBundle().getString("msg074").replace("XXX", totalFin+"").replace("YYY", lstTot.get(1).getPrfiAmount()+"") ,listaMsg);
				}
			} else {
				// sesBean.validacionMsg("frm:montoFinanc",
				// appBean.getBundle().getString("msg031"), listaMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	private List<ProjectsFinancing> calcularMontoRequerido(List<ProjectsFinancing> lstTotales) {
		List<ProjectsFinancing> lst = new ArrayList<>();
		try {
			if (lstTotales.get(0).getPrfiAmount() == null) {
				lstTotales.get(0).setPrfiAmount(new BigDecimal("0.00"));
			}
			if (lstTotales.get(1).getPrfiAmount() == null) {
				lstTotales.get(1).setPrfiAmount(new BigDecimal("0.00"));
			}
			lstTotales.get(2)
					.setPrfiAmount(lstTotales.get(0).getPrfiAmount().subtract(lstTotales.get(1).getPrfiAmount()));
			lst = lstTotales;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return lst;
	}

	private String obtenerEstadoFinanciamiento(List<ProjectsFinancing> lstTotales) {
		String e = "";
		try {
			if (lstTotales.get(2).getPrfiAmount().compareTo(new BigDecimal("0"))==0) {
				e = "F";
			} else {
				if (lstTotales.get(1).getPrfiAmount().compareTo(new BigDecimal("0")) > 0) {
					e = "P";
				} else {
					e = "N";
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return e;
	}

	public void cargarSeccion06(Project proyecto) {
		try {
			controlAvance = 6;
			listaDocs = proyectosDocFacade.listarDocumentosPorProyecto(proyectoActual.getProjId());
			listaTablasGeoProyecto = proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyecto.getProjId(), "G");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarDocAEditar(ProjectsDoc doc) {
		try {
			docProyCurrent = doc;
			docIdToEdit = docProyCurrent.getDocuId().getDocuId();
			documentCurrent = docProyCurrent.getDocuId();
			PrimeFaces.current().executeScript("PF('dlgDoc').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevoDoc() {
		try {
			inicializarObjetoDoc();
			PrimeFaces.current().executeScript("PF('dlgDoc').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjetoDoc() {
		try {
			docProyCurrent = new ProjectsDoc();
			docProyCurrent.setProjId(proyectoActual);
			docProyCurrent.setCataId(new Catalog());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void adjuntarArchivoParaDoc(FileUploadEvent event) {
		try {
			documentCurrent = appBean.almacenarYObtenerDocSigma(event.getFile());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg160"));
		} catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void prepararCargaShape(){
		try{
			inicializarObjetoTablaGeo();
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarObjetoTablaGeo() {
		codCatEjeTematico1 = 0;

		tablaGeoActual = new ProjectsGeographicalArea();
		tablaGeoActual.setPgarLevel("G");
		catEjeTematico2 = new Catalog();
		listaEjeTematicoShape = new ArrayList<>();
		ayudaCamposCapa="";
	}

	public void actualizarListaEjeTematico() {
		try {
			listaEjeTematicoShape = new ArrayList<>();
			if (codCatEjeTematico1 != null) {
				listaEjeTematicoShape = appBean.listarCatEjeTematicoShapeNivel2(codCatEjeTematico1);
				if(!listaEjeTematicoShape.isEmpty()){
					catEjeTematico2=listaEjeTematicoShape.get(0);
					mostrarAyudaCamposCapa();
				}
				
			}
		} catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private String obtenerCamposDeCapa() {
		String res = "";
		try {
			if (catEjeTematico2 != null && catEjeTematico2.getCataId()!=null) {
				List<Catalog> camposObjGeo = appBean.listarCatalogoHijoPorIdPadre(appBean.getLstCatCamposObjGeoREDD(),
						catEjeTematico2.getCataId());

				res = catalogFacade.obtenerTextoConcatenadoDeItemsDeCatalogo(camposObjGeo, ",");
			}
		} catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
		return res;
	}

	public void mostrarAyudaCamposCapa() {
		try {
			String res=obtenerCamposDeCapa();
			if(res!=""){
				ayudaCamposCapa = appBean.getBundle().getString("lbl429") + ": " + res;
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void adjuntarZipConShape(FileUploadEvent event) {
		try {

			Path folder = Paths.get(appBean.getPathUploads());
			String filename = FilenameUtils.getBaseName(event.getFile().getFileName()).replace("(", "_")
					.replace(")", "_").replace(" ", "_");
			String extension = FilenameUtils.getExtension(event.getFile().getFileName());
			Path file = Files.createTempFile(folder, filename + "_", "." + extension);

			List<String[]> listaMsg = validarSeleccionCapa(tablaGeoActual);
			if (listaMsg.isEmpty()) {

				try (InputStream input = event.getFile().getInputStream()) {
					Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
				}

				sesBean.addSuccessMessage((appBean.getBundle().getString("msg109")));

				String newDirPath1 = file.toString().replace(".zip", "");
				System.out.println(newDirPath1);
				File dirUtil = new File(newDirPath1);
				dirUtil.mkdir();
				String pathShape = "";
				// RUTINA PARA EXTRACCION DE ZIP
				try {
					ZipFile zipFile = new ZipFile(file.toString());
					// si a futuro se desea aceptar zip con clave
					// if (zipFile.isEncrypted()) {
					// zipFile.setPassword(password);
					// }

					zipFile.extractAll(newDirPath1);

					sesBean.addSuccessMessage((appBean.getBundle().getString("msg110")));

					File folder1 = new File(newDirPath1);
					String carpeta = appBean.obtenerNombreDePrimeraCarpeta(folder1);
					String shapeFileName = "";
					if (carpeta.length() > 0) {
						File folder2 = new File(newDirPath1 + "/" + carpeta);
						shapeFileName = appBean.obtenerNombreDePrimerShapeFile(folder2);
					} else {
						shapeFileName = appBean.obtenerNombreDePrimerShapeFile(folder1);
					}

					if (shapeFileName.length() > 0 && !shapeFileName.contains(" ") && !shapeFileName.contains("(")
							&& !shapeFileName.contains(")")) {
						if (carpeta.length() > 0) {
							pathShape = newDirPath1 + "/" + carpeta + "/" + shapeFileName;
						} else {
							pathShape = newDirPath1 + "/" + shapeFileName;
						}
					} else {
						sesBean.addErrorMessage((appBean.getBundle().getString("msg111")));
					}

				} catch (ZipException e) {
					sesBean.addErrorMessage((appBean.getBundle().getString("msg112")));
					e.printStackTrace();
				}
				// RUTINA PARA PROCESAMIENTO SHP A SQL
				if (pathShape.length() > 0) {
					try {
						String sql = "";
						String s;
						Process p;
						String fileNameString = file.getFileName() + "";
						String tableName = "sigma_geo." + fileNameString.replace(".zip", "");
						String psrid = "";

						if (srid == null || srid.isEmpty() || srid.equals("4326")) {
							psrid = "4326";
						} else {
							psrid = srid + ":4326";
						}
						String command = " -s " + psrid + " -e -I " + pathShape + " " + tableName + " > "
								+ pathShape.replace(".shp", ".sql") + "";

						List<String> cmdList = new ArrayList<String>();

						if (appBean.getOs().equals("LINUX")) {

							ProcessBuilder pb = new ProcessBuilder("/usr/pgsql-10/bin/shp2pgsql", "-s" + psrid + "-e",
									"-I", pathShape, tableName);
							p = pb.start();
							// para lectura del resultado del comando shp2pgsql
							BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
							// para escribir el resultado en un archivo sql
							BufferedWriter writer = new BufferedWriter(
									new FileWriter(pathShape.replace(".shp", ".sql"), true));
							// escritura
							String readline;
							int i = 0;
							while ((readline = reader.readLine()) != null) {
								if (!readline.equals("BEGIN;") && !readline.equals("COMMIT;")) {
									writer.write(readline);
									writer.newLine();
								}

							}
							writer.close();

						} else {
							String env = "\"C:\\Program Files\\PostgreSQL\\10\\bin\\";
							cmdList.add("cmd");
							cmdList.add("/c");
							cmdList.add(env + "shp2pgsql.exe\"" + command);
							ProcessBuilder pb = new ProcessBuilder();
							pb.command(cmdList);
							p = pb.start();
							p.waitFor();
						}

						File f = new File(pathShape.replace(".shp", ".sql"));
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
						int contador = 0;
						while ((s = br.readLine()) != null) {
							sql = sql + s;
							Thread.sleep(10);
							contador++;
						}
						br.close();
						if (psrid.contains(":")) {
							sql = sql.replace(":", "\\:");
						}
						String[] sentencias = sql.split("\\;");
						sql = null;
						s = null;
						p.destroy();
						// el primer grupo de sentencias son para encoding,
						// crear tabla y clave primaria
						String sent1 = "";
						// la siguiente sentencia es para añadir el campo de
						// geometria a la tabla
						String sent2 = "";
						// el siguiente grupo de sentencias son los inserts de
						// los datos
						List<String> listaSql = new ArrayList<>();
						String sqlx = "";
						for (int a = 0; a <= 3; a++) {
							sent1 = sent1 + sentencias[a] + ";";
						}
						sent2 = sentencias[4] + ";";

						int x = 0;
						for (int i = 5; i < sentencias.length; i++) {
							x++;
							sqlx = sqlx + sentencias[i] + ";";
							if (x == 10) {
								listaSql.add(sqlx);
								sqlx = "";
								x = 0;
							}
						}
						if (sqlx.length() > 0) {
							listaSql.add(sqlx);
						}
						// limpiamos el objeto que tiene todo el contenido del
						// archivo sql
						sentencias = null;

						// SE INSERTAN LOS DATOS EN EL ESQUEMA sigma_geo
						sigmaGeoFacade.sentenciaNativa(sent1);
						sigmaGeoFacade.consultaNativa(sent2);
						int contadorValidacion = 0;
						boolean flagError = false;
						for (String s1 : listaSql) {
							sigmaGeoFacade.sentenciaNativa(s1);

							// se valida que cumpla con el estandar del catalogo
							if (contadorValidacion == 0) {
								contadorValidacion = 1;
								String sql1 = "select " + obtenerCamposDeCapa() + " from " + tableName + " limit 1";
								try {
									sigmaGeoFacade.consultaNativa(sql1);
								} catch (Exception ex) {
									flagError = true;
									sesBean.addErrorMessage(appBean.getBundle().getString("msg166"));
									break;
								}
							}

							// el grupo de sentencias se ejecuta cada segundo
							Thread.sleep(1000);
						}
						// SI CUMPLE CON EL ESTANDAR DEL CATALOGO, SE FINALIZA
						// EL REGISTRO DE LA CAPA
						if (!flagError) {
							tablaGeoActual.setPgarGeoTable(tableName);
							tablaGeoActual.setProjId(proyectoActual);
							tablaGeoActual.setCataId(catEjeTematico2);
							proyectosGeoAreaFacade.create(tablaGeoActual);
							listaTablasGeoProyecto = proyectosGeoAreaFacade
									.listarAreasGeoPorProyectoNivel(proyectoActual.getProjId(), "G");
							//inicializarObjetoTablaGeo();

							sesBean.addSuccessMessage((appBean.getBundle().getString("msg113")));
						}
					} catch (Exception e) {
						sesBean.addErrorMessage((appBean.getBundle().getString("msg114")));
						e.printStackTrace();
					}
				} else {
					sesBean.addErrorMessage(appBean.getBundle().getString("msg114"));
				}
			} else {
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}
		} catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg115"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarSeleccionCapa(ProjectsGeographicalArea tablaGeo) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (catEjeTematico2 == null || catEjeTematico2.getCataId()==null) {
				sesBean.validacionMsg("frm:projTabGeoType",
						appBean.getBundle().getString("msg031") + ": " + appBean.getBundle().getString("lbl302"),
						listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarTablaGeo(ProjectsGeographicalArea tabla) {
		try {
			proyectosGeoAreaFacade.eliminarLogico(tabla);
			listaTablasGeoProyecto = proyectosGeoAreaFacade.listarAreasGeoPorProyectoNivel(proyectoActual.getProjId(),
					"G");
			sesBean.addSuccessMessage((appBean.getBundle().getString("msg117")));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarDocumento() {
		try {
			List<String[]> listaMsg = validarDoc(docProyCurrent, documentCurrent);

			if (listaMsg.isEmpty()) {
				if (docProyCurrent.getPrdoId() == null) {
					docProyCurrent.setDocuId(documentCurrent);
					proyectosDocFacade.create(docProyCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg024"));
				} else {
					if (documentCurrent.getDocuId() != docIdToEdit) {
						docProyCurrent.setDocuId(documentCurrent);
					}
					proyectosDocFacade.edit(docProyCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg025"));
				}
				listaDocs = proyectosDocFacade.listarDocumentosPorProyecto(proyectoActual.getProjId());
				PrimeFaces.current().executeScript("PF('dlgDoc').hide()");
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

	private List<String[]> validarDoc(ProjectsDoc doc, Document file) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (doc.getCataId() == null || doc.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:docType",appBean.getBundle().getString("lbl019")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (doc.getCataId() != null && doc.getCataId().getCataId() != null
					&& doc.getCataId().getCataId() == appBean.getCodOtroTipoDoc()) {
				if (doc.getPrdoOtherType().length() == 0) {
					sesBean.validacionMsg("frm:docOtherType",appBean.getBundle().getString("lbl176")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
				}

			}
			if (doc.getPrdoId() == null) {
				if (file == null || file.getDocuId() == null) {
					sesBean.validacionMsg("frm:docFile", appBean.getBundle().getString("msg023"), listaMsg);
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	public void eliminarDocumentoProyecto(ProjectsDoc doc) {
		try {
			proyectosDocFacade.eliminarLogico(doc);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg026"));
			listaDocs = proyectosDocFacade.listarDocumentosPorProyecto(proyectoActual.getProjId());

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void cargarConveniosDeProyecto(Project proyecto) {
		try {
			listaConveniosProyecto = new ArrayList<>();
			if (proyecto.getProjId() != null) {
				listaConveniosProyecto = proyectosConvenioFacade.listarConveniosPorProyecto(proyecto.getProjId());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void cargarAcuerdosDeProyecto(Project proyecto) {
		try {
			listaAcuerdosProyecto = new ArrayList<>();
			if (proyecto.getProjId() != null) {
				listaAcuerdosProyecto = proyectosAcuerdosFinFacade.listarAcuerdosPorProyecto(proyecto.getProjId());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarConveniosPorTitulo() {
		try {
			listaConveniosEncontrados = new ArrayList<>();
			if (codBusqConvenio.length() > 3) {
				listaConveniosEncontrados = convenioFacade.listarConveniosPorTitulo(codBusqConvenio);
			}
			PrimeFaces.current().executeScript("PF('dlgBusqConv').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void asignarConvenioAProyecto(Agreement a) {
		try {
			// Agreement a =
			// convenioFacade.obtenerConvenioPorTitulo(codBusqConvenio);
			// if (a != null) {
			ProjectsAgreement pa = new ProjectsAgreement();
			pa.setAgreId(a);
			pa.setProjId(proyectoActual);
			// listaConveniosProyecto.add(pa);
			codBusqConvenio = "";
			// }
			// proyectosConvenioFacade.guardarConvenios(listaConveniosProyecto,
			// proyectoActual.getProjId());
			proyectosConvenioFacade.create(pa);
			cargarConveniosDeProyecto(proyectoActual);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg088"));
			PrimeFaces.current().executeScript("PF('dlgBusqConv').hide()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarConvenioProyecto(ProjectsAgreement pa) {
		try {
			proyectosConvenioFacade.eliminarLogico(pa);
			cargarConveniosDeProyecto(proyectoActual);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg043"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void buscarAcuerdoFinPorTitulo() {
		try {
			listaAcuerdosEncontrados = new ArrayList<>();
			if (campoBusquedaAcuerdo.length() > 3) {
				listaAcuerdosEncontrados = financingAgreementFacade
						.listarAcuerdosFinanciamientoPorTitulo(campoBusquedaAcuerdo);
			}
			PrimeFaces.current().executeScript("PF('dlgBusqAcuFin').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void asignarAcuerdoAProyecto(FinancingAgreement a) {
		try {
			// FinancingAgreement
			// a=financingAgreementFacade.obtenerAcuerdoPorCodigo(campoBusquedaAcuerdo);
			// if(a!=null){
			ProjectsFinancingAgreement pa = new ProjectsFinancingAgreement();
			pa.setFiagId(a);
			pa.setProjId(proyectoActual);
			// listaAcuerdosProyecto.add(pa);
			campoBusquedaAcuerdo = "";
			// }
			// proyectosAcuerdosFinFacade.guardarAcuerdos(listaAcuerdosProyecto,
			// proyectoActual.getProjId());
			proyectosAcuerdosFinFacade.create(pa);
			cargarAcuerdosDeProyecto(proyectoActual);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg089"));
			PrimeFaces.current().executeScript("PF('dlgBusqAcuFin').hide()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarAcuerdoDeProyecto(ProjectsFinancingAgreement pa) {
		try {
			proyectosAcuerdosFinFacade.eliminarLogico(pa);
			cargarAcuerdosDeProyecto(proyectoActual);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg056"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void validarFormularioRegistro() {
		try {
			boolean isValid = true;
			/*
			 * if (proyectoActual.getProjType().equals("PDI")) { if
			 * (listaDocs.size() < 2) { isValid = false;
			 * sesBean.addErrorMessage(appBean.getBundle().getString("msg086"));
			 * } } else { if (listaDocs.size() < 1) { isValid = false;
			 * sesBean.addErrorMessage(appBean.getBundle().getString("msg082"));
			 * } }
			 */
			if (isValid) {
				String tipo = "";
				if (proyectoActual.getProjType().equals("PDI")) {
					tipo = "Plan de Implementación";
				} else if (proyectoActual.getProjType().equals("PRG")) {
					tipo = "Programa";
				} else if (proyectoActual.getProjType().equals("PRY")) {
					tipo = "Proyecto";
				}
				proyectoActual.setProjRegisterStatus("V");

				proyectoFacade.editar(sesBean.getUsuarioLogeado().getUserName(), proyectoActual);
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg085").replace("XXXX", tipo));
				// sesBean.redireccionarAPagina("", "inicio");
				inicializar();
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cancelarSeccion(String seccionActual) {
		try {
			Integer sec = Integer.valueOf(seccionActual);
			controlAvance = sec - 1;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void descargarDocumento(ProjectsDoc doc) {
		fileDocToDownload = appBean.obtenerStreamedContentDeDocSigma(doc.getDocuId());
	}

	public void irCreacionConvenioImplementacion() {
		try {
			sesBean.redireccionarAPaginaConParametros("partner", "ConvenioImplementacion", "TIPO_PAG_CONIMP",
					"SOLO_CREAR_CON_IMP", "ID_PROYECTO_ACTUAL", proyectoActual.getProjId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void irCreacionAcuerdoFinanciamiento() {
		try {
			sesBean.redireccionarAPaginaConParametros("financer", "AcuerdoFinanciamiento", "TIPO_PAG_ACUFIN",
					"SOLO_CREAR_ACU_FIN", "ID_PROYECTO_ACTUAL", proyectoActual.getProjId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void irGeoVisorProyectoActual() {
		try {
			if (listaTablasGeoProyecto.size() > 0) {

				String geoTablesName = "";
				geoTablesName = listaTablasGeoProyecto.get(0).getPgarGeoTable() + "&"
						+ listaTablasGeoProyecto.get(0).getPgarGeoType();

				if (listaTablasGeoProyecto.size() > 1) {
					for (int i = 1; i < listaTablasGeoProyecto.size(); i++) {
						geoTablesName = geoTablesName + "⁓" + listaTablasGeoProyecto.get(i).getPgarGeoTable() + "&"
								+ listaTablasGeoProyecto.get(i).getPgarGeoType();
					}
				}

				sesBean.redireccionarAPaginaConParametros("geo", "geo1", "TIPO_GEOVISOR", "SOLO_PROYECTO",
						"GEOTABLE_NAME", geoTablesName, "ID_PROYECTO_ACTUAL", proyectoActual.getProjId());
			} else {
				sesBean.addErrorMessage(appBean.getBundle().getString("msg108"));
			}
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
					.getRealPath("reports/registro/rep_main.jasper"));
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("param_proj_id", proyectoActual.getProjId());
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
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REGISTRO_"
					+ proyectoActual.getProjShortName().replace(" ", "_") + ".pdf");
			ServletOutputStream stream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void generarReportePDF(Project proy) {
		try {
			proyectoActual = proy;
			generarReportePDF();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public int getTipoBusquedaProyectos() {
		return tipoBusquedaProyectos;
	}

	public void setTipoBusquedaProyectos(int tipoBusquedaProyectos) {
		this.tipoBusquedaProyectos = tipoBusquedaProyectos;
	}

	public Integer getCodSocioImplBusqueda() {
		return codSocioImplBusqueda;
	}

	public void setCodSocioImplBusqueda(Integer codSocioImplBusqueda) {
		this.codSocioImplBusqueda = codSocioImplBusqueda;
	}

	public String getTituloProyectoBusqueda() {
		return tituloProyectoBusqueda;
	}

	public void setTituloProyectoBusqueda(String tituloProyectoBusqueda) {
		this.tituloProyectoBusqueda = tituloProyectoBusqueda;
	}

	public Integer getCodPlanAccionBusqueda() {
		return codPlanAccionBusqueda;
	}

	public void setCodPlanAccionBusqueda(Integer codPlanAccionBusqueda) {
		this.codPlanAccionBusqueda = codPlanAccionBusqueda;
	}

	public List<Project> getListaProyectosEncontrados() {
		return listaProyectosEncontrados;
	}

	public void setListaProyectosEncontrados(List<Project> listaProyectosEncontrados) {
		this.listaProyectosEncontrados = listaProyectosEncontrados;
	}

	public Project getProyectoActual() {
		return proyectoActual;
	}

	public void setProyectoActual(Project proyectoActual) {
		this.proyectoActual = proyectoActual;
	}

	public int getTipoBusquedaSociosImp() {
		return tipoBusquedaSociosImp;
	}

	public void setTipoBusquedaSociosImp(int tipoBusquedaSociosImp) {
		this.tipoBusquedaSociosImp = tipoBusquedaSociosImp;
	}

	public String getRucSocioImplBusq() {
		return rucSocioImplBusq;
	}

	public void setRucSocioImplBusq(String rucSocioImplBusq) {
		this.rucSocioImplBusq = rucSocioImplBusq;
	}

	public String getNombreSocioImplBusq() {
		return nombreSocioImplBusq;
	}

	public void setNombreSocioImplBusq(String nombreSocioImplBusq) {
		this.nombreSocioImplBusq = nombreSocioImplBusq;
	}

	public List<Partner> getListaSociosImplEncontrados() {
		return listaSociosImplEncontrados;
	}

	public void setListaSociosImplEncontrados(List<Partner> listaSociosImplEncontrados) {
		this.listaSociosImplEncontrados = listaSociosImplEncontrados;
	}

	public Partner getSocioImplSeleccionado() {
		return socioImplSeleccionado;
	}

	public void setSocioImplSeleccionado(Partner socioImplSeleccionado) {
		this.socioImplSeleccionado = socioImplSeleccionado;
	}

	public int getControlAvance() {
		return controlAvance;
	}

	public void setControlAvance(int controlAvance) {
		this.controlAvance = controlAvance;
	}

	public List<ProjectsStrategicPartner> getListaSociosEstrategicos() {
		return listaSociosEstrategicos;
	}

	public void setListaSociosEstrategicos(List<ProjectsStrategicPartner> listaSociosEstrategicos) {
		this.listaSociosEstrategicos = listaSociosEstrategicos;
	}

	public List<ProjectsGeographicalArea> getListaAreasGeoNivel1Prov() {
		return listaAreasGeoNivel1Prov;
	}

	public void setListaAreasGeoNivel1Prov(List<ProjectsGeographicalArea> listaAreasGeoNivel1Prov) {
		this.listaAreasGeoNivel1Prov = listaAreasGeoNivel1Prov;
	}

	public List<ProjectsGeographicalArea> getListaAreasGeoNivel2Cant() {
		return listaAreasGeoNivel2Cant;
	}

	public void setListaAreasGeoNivel2Cant(List<ProjectsGeographicalArea> listaAreasGeoNivel2Cant) {
		this.listaAreasGeoNivel2Cant = listaAreasGeoNivel2Cant;
	}

	public List<ProjectsGeographicalArea> getListaAreasGeoNivel3Parr() {
		return listaAreasGeoNivel3Parr;
	}

	public void setListaAreasGeoNivel3Parr(List<ProjectsGeographicalArea> listaAreasGeoNivel3Parr) {
		this.listaAreasGeoNivel3Parr = listaAreasGeoNivel3Parr;
	}

	public List<GeographicalLocations> getListaProvSeleccionadas() {
		return listaProvSeleccionadas;
	}

	public void setListaProvSeleccionadas(List<GeographicalLocations> listaProvSeleccionadas) {
		this.listaProvSeleccionadas = listaProvSeleccionadas;
	}

	public List<GeographicalLocations> getListaCantSeleccionadas() {
		return listaCantSeleccionadas;
	}

	public void setListaCantSeleccionadas(List<GeographicalLocations> listaCantSeleccionadas) {
		this.listaCantSeleccionadas = listaCantSeleccionadas;
	}

	public List<GeographicalLocations> getListaParrSeleccionadas() {
		return listaParrSeleccionadas;
	}

	public void setListaParrSeleccionadas(List<GeographicalLocations> listaParrSeleccionadas) {
		this.listaParrSeleccionadas = listaParrSeleccionadas;
	}

	public List<Project> getListaProyectosGestoresDeFondos() {
		return listaProyectosGestoresDeFondos;
	}

	public void setListaProyectosGestoresDeFondos(List<Project> listaProyectosGestoresDeFondos) {
		this.listaProyectosGestoresDeFondos = listaProyectosGestoresDeFondos;
	}

	public List<ProjectsRisk> getListaRiesgos() {
		return listaRiesgos;
	}

	public void setListaRiesgos(List<ProjectsRisk> listaRiesgos) {
		this.listaRiesgos = listaRiesgos;
	}

	public int getProjCodAnio() {
		return projCodAnio;
	}

	public void setProjCodAnio(int projCodAnio) {
		this.projCodAnio = projCodAnio;
	}

	public String getProjCodSec() {
		return projCodSec;
	}

	public void setProjCodSec(String projCodSec) {
		this.projCodSec = projCodSec;
	}

	public String getProjPlazoDesdAnio() {
		return projPlazoDesdAnio;
	}

	public void setProjPlazoDesdAnio(String projPlazoDesdAnio) {
		this.projPlazoDesdAnio = projPlazoDesdAnio;
	}

	public String getProjPlazoDesdMes() {
		return projPlazoDesdMes;
	}

	public void setProjPlazoDesdMes(String projPlazoDesdMes) {
		this.projPlazoDesdMes = projPlazoDesdMes;
	}

	public String getProjPlazoHastAnio() {
		return projPlazoHastAnio;
	}

	public void setProjPlazoHastAnio(String projPlazoHastAnio) {
		this.projPlazoHastAnio = projPlazoHastAnio;
	}

	public String getProjPlazoHastMes() {
		return projPlazoHastMes;
	}

	public void setProjPlazoHastMes(String projPlazoHastMes) {
		this.projPlazoHastMes = projPlazoHastMes;
	}

	public String getCodBusqConvenio() {
		return codBusqConvenio;
	}

	public void setCodBusqConvenio(String codBusqConvenio) {
		this.codBusqConvenio = codBusqConvenio;
	}

	public List<Component> getListaComponentesPlanActual() {
		return listaComponentesPlanActual;
	}

	public void setListaComponentesPlanActual(List<Component> listaComponentesPlanActual) {
		this.listaComponentesPlanActual = listaComponentesPlanActual;
	}

	public ProjectsRisk getRiesgoActual() {
		return riesgoActual;
	}

	public void setRiesgoActual(ProjectsRisk riesgoActual) {
		this.riesgoActual = riesgoActual;
	}

	public List<ProjectsFinancing> getListaFinancTotales() {
		return listaFinancTotales;
	}

	public void setListaFinancTotales(List<ProjectsFinancing> listaFinancTotales) {
		this.listaFinancTotales = listaFinancTotales;
	}

	public List<ProjectsFinancing> getListaFinancObjComp() {
		return listaFinancObjComp;
	}

	public void setListaFinancObjComp(List<ProjectsFinancing> listaFinancObjComp) {
		this.listaFinancObjComp = listaFinancObjComp;
	}

	public List<ProjectsFinancing> getListaFinancCofinan() {
		return listaFinancCofinan;
	}

	public void setListaFinancCofinan(List<ProjectsFinancing> listaFinancCofinan) {
		this.listaFinancCofinan = listaFinancCofinan;
	}

	public Integer getCodFuenteFinActual() {
		return codFuenteFinActual;
	}

	public void setCodFuenteFinActual(Integer codFuenteFinActual) {
		this.codFuenteFinActual = codFuenteFinActual;
	}

	public List<ProjectsDoc> getListaDocs() {
		return listaDocs;
	}

	public void setListaDocs(List<ProjectsDoc> listaDocs) {
		this.listaDocs = listaDocs;
	}

	public ProjectsDoc getDocProyCurrent() {
		return docProyCurrent;
	}

	public void setDocProyCurrent(ProjectsDoc docProyCurrent) {
		this.docProyCurrent = docProyCurrent;
	}

	public Document getDocumentCurrent() {
		return documentCurrent;
	}

	public void setDocumentCurrent(Document documentCurrent) {
		this.documentCurrent = documentCurrent;
	}

	public StreamedContent getFileDocToDownload() {
		return fileDocToDownload;
	}

	public void setFileDocToDownload(StreamedContent fileDocToDownload) {
		this.fileDocToDownload = fileDocToDownload;
	}

	public SocioImplementadorController getSocioBean() {
		return socioBean;
	}

	public void setSocioBean(SocioImplementadorController socioBean) {
		this.socioBean = socioBean;
	}

	public String getCampoBusquedaAcuerdo() {
		return campoBusquedaAcuerdo;
	}

	public void setCampoBusquedaAcuerdo(String campoBusquedaAcuerdo) {
		this.campoBusquedaAcuerdo = campoBusquedaAcuerdo;
	}

	public List<ProjectsAgreement> getListaConveniosProyecto() {
		return listaConveniosProyecto;
	}

	public void setListaConveniosProyecto(List<ProjectsAgreement> listaConveniosProyecto) {
		this.listaConveniosProyecto = listaConveniosProyecto;
	}

	public List<Agreement> getListaConveniosEncontrados() {
		return listaConveniosEncontrados;
	}

	public void setListaConveniosEncontrados(List<Agreement> listaConveniosEncontrados) {
		this.listaConveniosEncontrados = listaConveniosEncontrados;
	}

	public List<ProjectsFinancingAgreement> getListaAcuerdosProyecto() {
		return listaAcuerdosProyecto;
	}

	public void setListaAcuerdosProyecto(List<ProjectsFinancingAgreement> listaAcuerdosProyecto) {
		this.listaAcuerdosProyecto = listaAcuerdosProyecto;
	}

	public List<FinancingAgreement> getListaAcuerdosEncontrados() {
		return listaAcuerdosEncontrados;
	}

	public void setListaAcuerdosEncontrados(List<FinancingAgreement> listaAcuerdosEncontrados) {
		this.listaAcuerdosEncontrados = listaAcuerdosEncontrados;
	}

	public boolean isFlagSocioImpEspecial() {
		return flagSocioImpEspecial;
	}

	public void setFlagSocioImpEspecial(boolean flagSocioImpEspecial) {
		this.flagSocioImpEspecial = flagSocioImpEspecial;
	}

	private Partner obtenerObjetoPartnerDesdeId(Integer partId) {
		Partner p = new Partner();
		for (Partner pp : listaSociosEstrategicosTodos) {
			if (pp.getPartId() == partId) {
				p = pp;
				break;
			}
		}
		return p;
	}

	public List<Partner> getListaSociosEstrategicosTodos() {
		return listaSociosEstrategicosTodos;
	}

	public void setListaSociosEstrategicosTodos(List<Partner> listaSociosEstrategicosTodos) {
		this.listaSociosEstrategicosTodos = listaSociosEstrategicosTodos;
	}

	public Partner getSocioEstActual() {
		return socioEstActual;
	}

	public void setSocioEstActual(Partner socioEstActual) {
		this.socioEstActual = socioEstActual;
	}

	public String getSocioEstTipo() {
		return socioEstTipo;
	}

	public void setSocioEstTipo(String socioEstTipo) {
		this.socioEstTipo = socioEstTipo;
	}

	public boolean isEsSubAcc() {
		return esSubAcc;
	}

	public void setEsSubAcc(boolean esSubAcc) {
		this.esSubAcc = esSubAcc;
	}

	public Component getCodCompCompat() {
		return codCompCompat;
	}

	public void setCodCompCompat(Component codCompCompat) {
		this.codCompCompat = codCompCompat;
	}

	public List<Measure> getListaMedidasPlanActual() {
		return listaMedidasPlanActual;
	}

	public void setListaMedidasPlanActual(List<Measure> listaMedidasPlanActual) {
		this.listaMedidasPlanActual = listaMedidasPlanActual;
	}

	public List<Action> getListaAccionesPlanActual() {
		return listaAccionesPlanActual;
	}

	public void setListaAccionesPlanActual(List<Action> listaAccionesPlanActual) {
		this.listaAccionesPlanActual = listaAccionesPlanActual;
	}

	public String getSrid() {
		return srid;
	}

	public void setSrid(String srid) {
		this.srid = srid;
	}

	public ProjectsGeographicalArea getTablaGeoActual() {
		return tablaGeoActual;
	}

	public void setTablaGeoActual(ProjectsGeographicalArea tablaGeoActual) {
		this.tablaGeoActual = tablaGeoActual;
	}

	public List<ProjectsGeographicalArea> getListaTablasGeoProyecto() {
		return listaTablasGeoProyecto;
	}

	public void setListaTablasGeoProyecto(List<ProjectsGeographicalArea> listaTablasGeoProyecto) {
		this.listaTablasGeoProyecto = listaTablasGeoProyecto;
	}

	public List<Measure> getListaIndicadoresPlanActual() {
		return listaIndicadoresPlanActual;
	}

	public void setListaIndicadoresPlanActual(List<Measure> listaIndicadoresPlanActual) {
		this.listaIndicadoresPlanActual = listaIndicadoresPlanActual;
	}

	public List<Partner> getListaSociosImpl() {
		return listaSociosImpl;
	}

	public void setListaSociosImpl(List<Partner> listaSociosImpl) {
		this.listaSociosImpl = listaSociosImpl;
	}

	public List<Partner> getListaSociosEstrategicosApoyoSinPdi() {
		return listaSociosEstrategicosApoyoSinPdi;
	}

	public void setListaSociosEstrategicosApoyoSinPdi(List<Partner> listaSociosEstrategicosApoyoSinPdi) {
		this.listaSociosEstrategicosApoyoSinPdi = listaSociosEstrategicosApoyoSinPdi;
	}

	public List<ProjectsSpecificObjective> getListaComponentesObjetivos() {
		return listaComponentesObjetivos;
	}

	public void setListaComponentesObjetivos(List<ProjectsSpecificObjective> listaComponentesObjetivos) {
		this.listaComponentesObjetivos = listaComponentesObjetivos;
	}

	public ProjectsSpecificObjective getComponenteObjetivoActual() {
		return componenteObjetivoActual;
	}

	public void setComponenteObjetivoActual(ProjectsSpecificObjective componenteObjetivoActual) {
		this.componenteObjetivoActual = componenteObjetivoActual;
	}

	public ProjectsAction getAccionActual() {
		return accionActual;
	}

	public void setAccionActual(ProjectsAction accionActual) {
		this.accionActual = accionActual;
	}

	public ProjectsAction getIndicadorActual() {
		return indicadorActual;
	}

	public void setIndicadorActual(ProjectsAction indicadorActual) {
		this.indicadorActual = indicadorActual;
	}

	public List<ProjectsAction> getListaIndicadoresAccionActual() {
		return listaIndicadoresAccionActual;
	}

	public void setListaIndicadoresAccionActual(List<ProjectsAction> listaIndicadoresAccionActual) {
		this.listaIndicadoresAccionActual = listaIndicadoresAccionActual;
	}

	public List<ProjectsAction> getListaIndicadores() {
		return listaIndicadores;
	}

	public void setListaIndicadores(List<ProjectsAction> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
	}

	public Integer[] getCodProvinciasSeleccionadas() {
		return codProvinciasSeleccionadas;
	}

	public void setCodProvinciasSeleccionadas(Integer[] codProvinciasSeleccionadas) {
		this.codProvinciasSeleccionadas = codProvinciasSeleccionadas;
	}

	public List<ProjectsSpecificObjective> getListaComponentesObjetivosTodos() {
		return listaComponentesObjetivosTodos;
	}

	public void setListaComponentesObjetivosTodos(List<ProjectsSpecificObjective> listaComponentesObjetivosTodos) {
		this.listaComponentesObjetivosTodos = listaComponentesObjetivosTodos;
	}

	public int getTipoConsultaComponentes() {
		return tipoConsultaComponentes;
	}

	public void setTipoConsultaComponentes(int tipoConsultaComponentes) {
		this.tipoConsultaComponentes = tipoConsultaComponentes;
	}

	public String getTypeCofin() {
		return typeCofin;
	}

	public void setTypeCofin(String typeCofin) {
		this.typeCofin = typeCofin;
	}

	public BigDecimal getTotalIniPdi() {
		return totalIniPdi;
	}

	public void setTotalIniPdi(BigDecimal totalIniPdi) {
		this.totalIniPdi = totalIniPdi;
	}

	public Measure getCodMedCompat() {
		return codMedCompat;
	}

	public void setCodMedCompat(Measure codMedCompat) {
		this.codMedCompat = codMedCompat;
	}

	public Action getCodAccCompat() {
		return codAccCompat;
	}

	public void setCodAccCompat(Action codAccCompat) {
		this.codAccCompat = codAccCompat;
	}

	public Measure getCodIndEnlace() {
		return codIndEnlace;
	}

	public void setCodIndEnlace(Measure codIndEnlace) {
		this.codIndEnlace = codIndEnlace;
	}

	public Integer getCodCatEjeTematico1() {
		return codCatEjeTematico1;
	}

	public void setCodCatEjeTematico1(Integer codCatEjeTematico1) {
		this.codCatEjeTematico1 = codCatEjeTematico1;
	}
	
	public Catalog getCatEjeTematico2() {
		return catEjeTematico2;
	}

	public void setCatEjeTematico2(Catalog catEjeTematico2) {
		this.catEjeTematico2 = catEjeTematico2;
	}

	public List<Catalog> getListaEjeTematicoShape() {
		return listaEjeTematicoShape;
	}

	public void setListaEjeTematicoShape(List<Catalog> listaEjeTematicoShape) {
		this.listaEjeTematicoShape = listaEjeTematicoShape;
	}

	public String getAyudaCamposCapa() {
		return ayudaCamposCapa;
	}

	public void setAyudaCamposCapa(String ayudaCamposCapa) {
		this.ayudaCamposCapa = ayudaCamposCapa;
	}

	public String[] getProvinciasSel() {
		return provinciasSel;
	}

	public void setProvinciasSel(String[] provinciasSel) {
		this.provinciasSel = provinciasSel;
	}

	public String[] getCantonesSel() {
		return cantonesSel;
	}

	public void setCantonesSel(String[] cantonesSel) {
		this.cantonesSel = cantonesSel;
	}

	public String[] getParroquiasSel() {
		return parroquiasSel;
	}

	public void setParroquiasSel(String[] parroquiasSel) {
		this.parroquiasSel = parroquiasSel;
	}
	
	public class AreaGeoRegistro{
		String provincia;
		String canton;
		List<String> parroquias;
		
		AreaGeoRegistro(String p,String c,List<String> pa){
			this.provincia=p;
			this.canton=c;
			this.parroquias=pa;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}

		public String getCanton() {
			return canton;
		}

		public void setCanton(String canton) {
			this.canton = canton;
		}

		public List<String> getParroquias() {
			return parroquias;
		}

		public void setParroquias(List<String> parroquias) {
			this.parroquias = parroquias;
		}
		
		
	}

	public List<AreaGeoRegistro> getListaAreaGeoRegistro() {
		return listaAreaGeoRegistro;
	}

	public void setListaAreaGeoRegistro(List<AreaGeoRegistro> listaAreaGeoRegistro) {
		this.listaAreaGeoRegistro = listaAreaGeoRegistro;
	}
	
	

}
