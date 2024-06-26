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
import javax.servlet.http.Part;

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
import ec.gob.ambiente.sigma.ejb.entidades.Component;
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
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
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

@Named("consulta")
@ViewScoped
public class ConsultaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ConsultaController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private ComponentFacade componenteFacade;
	
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
	
	
	private List<ProjectsAction> listaAccionesRegistradas;
	private List<Object[]> listaProvincias;
	private Integer codProvincia;
	
	private Component componentePAVigente;


	public ConsultaController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			
			componentePAVigente=new Component();
			listaAccionesRegistradas=new ArrayList<>();
			listaProvincias=appBean.getLstGeoLocProvincias();
			
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarComponenteEstrategicoPA(String codigo){
		try{
			componentePAVigente=new Component();
			componentePAVigente=componenteFacade.buscarComponentePAVigentePorCodigo(codigo);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void consultarAccionesRegistradasPorProvincia(){
		try{
			listaAccionesRegistradas=new ArrayList<>();
			if(codProvincia>0){
				listaAccionesRegistradas=projectsActionFacade.listarIndicadoresPorProvincia(codProvincia);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public List<ProjectsAction> getListaAccionesRegistradas() {
		return listaAccionesRegistradas;
	}

	public void setListaAccionesRegistradas(List<ProjectsAction> listaAccionesRegistradas) {
		this.listaAccionesRegistradas = listaAccionesRegistradas;
	}

	public List<Object[]> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<Object[]> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public Integer getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(Integer codProvincia) {
		this.codProvincia = codProvincia;
	}

	public Component getComponentePAVigente() {
		return componentePAVigente;
	}

	public void setComponentePAVigente(Component componentePAVigente) {
		this.componentePAVigente = componentePAVigente;
	}

	
	
}
