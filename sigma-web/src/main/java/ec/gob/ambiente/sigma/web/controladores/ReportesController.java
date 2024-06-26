package ec.gob.ambiente.sigma.web.controladores;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import ec.gob.ambiente.sigma.ejb.facades.ActionPlanFacade;
import ec.gob.ambiente.sigma.ejb.facades.MeetingFacade;
import ec.gob.ambiente.sigma.ejb.facades.MeetingsTotalFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsActionFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsActionsCompatibilityFacade;
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

@Named("report")
@ViewScoped
public class ReportesController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ReportesController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private ActionPlanFacade actionPlanFacade;
	@EJB
	private TracingFacade tracingFacade;
	@EJB
	private ProjectsActionsCompatibilityFacade compatibilidadPAFacade;
	
	
	private ActionPlan actionPlanActual;
	private int anioReporte;
	private Integer codCatSemestre;
	private boolean paTieneCompatibilidad;
	private boolean paTieneCompatibilidadIndicadores;
	

	public ReportesController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			actionPlanActual=new ActionPlan();
			anioReporte=0;
			codCatSemestre=0;
			//calcularAnioReporte();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void validarPaSeleccionado(){
		try{
			if(actionPlanActual.getAcplId()!=null&&actionPlanActual.getAcplId()>0){
				paTieneCompatibilidad=compatibilidadPAFacade.existeCompatibilidadAccionesPorPA(actionPlanActual.getAcplId());
				paTieneCompatibilidadIndicadores=compatibilidadPAFacade.existeCompatibilidadIndicadoresPorPA(actionPlanActual.getAcplId());
				if(!paTieneCompatibilidad){
					sesBean.addErrorMessage(appBean.getBundle().getString("msg064"));
				}else{
					if(!paTieneCompatibilidadIndicadores){
						
					}
				}
			
			}
			
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void calcularAnioReporte(){
		try{
			int mesActual=0;
			int anioActual=0;
			Calendar cal=java.util.Calendar.getInstance();
			cal.setTime(new Date());
			
			mesActual=cal.get(Calendar.MONTH)+1;
			anioActual=cal.get(Calendar.YEAR);
			System.out.println(mesActual+" "+anioActual);
			if(mesActual>=1&&mesActual<=6){
				anioReporte=anioActual-1;
			}else{
				anioReporte=anioActual;
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF1(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_impl/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_IMPLEMENTACIONES_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF2(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_acc_medidas/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_ACCIONES_PAREDD_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDFx(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_acciones/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/pie_maate_docs_2023.png");
			parametros.put("img2_path", img2_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_ACCIONES_PAREDD_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF3(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_acc_provincia/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_ACCIONES_PROVINCIA_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF4(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_indicadores/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_INDICADORES_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF5(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_indicadores_resumen/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			parametros.put("param_trac_ids", tracingFacade.listaTracIdsParaReportes(actionPlanActual.getAcplId(), anioReporte, codCatSemestre));
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_INDICADORES_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarReportePDF6(){
		try{
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:jboss/datasources/SuiaDS");
			Connection connection = dataSource.getConnection();
			File jasperMain=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/reporte_avance_impl_semestre/rep_main.jasper"));	
			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("param_acpl_id", actionPlanActual.getAcplId());
			parametros.put("param_trac_year",anioReporte);
			parametros.put("param_cata_idsemestre", codCatSemestre);
			
			if(codCatSemestre.equals(appBean.getCodUltimoSemestre())){
				parametros.put("param_trac_year_ant",anioReporte);
				parametros.put("param_cata_idsemestre_ant", appBean.getCodPrimerSemestre());
			}else{
				parametros.put("param_trac_year_ant",anioReporte-1);
				parametros.put("param_cata_idsemestre_ant", appBean.getCodUltimoSemestre());
			}
				
			String img1_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/cabecera_maate_docs_2023.png");
			parametros.put("img1_path", img1_path);
			String img2_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_izq.png");
			parametros.put("img2_path", img2_path);
			String img3_path = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/images/maate_2023_pie_der.png");
			parametros.put("img3_path", img3_path);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperMain.getPath(), parametros, connection);
			connection.close();
			HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=SIGMA_REPORTE_AVANCE_IMPLEMENTACIONES_"+anioReporte+".pdf");
			ServletOutputStream stream=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public ActionPlan getActionPlanActual() {
		return actionPlanActual;
	}

	public void setActionPlanActual(ActionPlan actionPlanActual) {
		this.actionPlanActual = actionPlanActual;
	}

	public int getAnioReporte() {
		return anioReporte;
	}

	public void setAnioReporte(int anioReporte) {
		this.anioReporte = anioReporte;
	}

	public Integer getCodCatSemestre() {
		return codCatSemestre;
	}

	public void setCodCatSemestre(Integer codCatSemestre) {
		this.codCatSemestre = codCatSemestre;
	}

	public boolean isPaTieneCompatibilidad() {
		return paTieneCompatibilidad;
	}

	public void setPaTieneCompatibilidad(boolean paTieneCompatibilidad) {
		this.paTieneCompatibilidad = paTieneCompatibilidad;
	}

	public boolean isPaTieneCompatibilidadIndicadores() {
		return paTieneCompatibilidadIndicadores;
	}

	public void setPaTieneCompatibilidadIndicadores(boolean paTieneCompatibilidadIndicadores) {
		this.paTieneCompatibilidadIndicadores = paTieneCompatibilidadIndicadores;
	}

	
	
	
	
	

}
