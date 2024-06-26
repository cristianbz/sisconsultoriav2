package ec.gob.ambiente.sigma.web.controladores;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import ec.gob.ambiente.sigma.ejb.entidades.Action;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Cobenefit;
import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Goal;
import ec.gob.ambiente.sigma.ejb.entidades.LegalFrameworkDoc;
import ec.gob.ambiente.sigma.ejb.entidades.Measure;
import ec.gob.ambiente.sigma.ejb.entidades.SpecificObjective;
import ec.gob.ambiente.sigma.ejb.facades.ActionFacade;
import ec.gob.ambiente.sigma.ejb.facades.ActionPlanFacade;
import ec.gob.ambiente.sigma.ejb.facades.CobenefitFacade;
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
import ec.gob.ambiente.sigma.ejb.facades.DocumentSigmaFacade;
import ec.gob.ambiente.sigma.ejb.facades.GoalFacade;
import ec.gob.ambiente.sigma.ejb.facades.LegalFrameworkDocFacade;
import ec.gob.ambiente.sigma.ejb.facades.MeasureFacade;
import ec.gob.ambiente.sigma.ejb.facades.SpecificObjectiveFacade;

@Named("plac")
@ViewScoped
public class PlanAccionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PlanAccionController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	@EJB
	private ActionPlanFacade actionPlanFacade;
	@EJB
	private SpecificObjectiveFacade specificObjectiveFacade;
	@EJB
	private GoalFacade goalFacade;
	@EJB
	private ComponentFacade componentFacade;
	@EJB
	private MeasureFacade measureFacade;
	@EJB
	private ActionFacade actionFacade;
	@EJB
	private CobenefitFacade cobenefitFacade;
	@EJB
	private LegalFrameworkDocFacade legalFrameworkDocFacade;
	@EJB
	private DocumentSigmaFacade documentSigmaFacade;

	private ActionPlan actionPlanCurrent;
	private List<ActionPlan> listActionPlan;

	private List<SpecificObjective> listSpecificObjective;
	private SpecificObjective specificObjectiveCurrent;
	private List<Goal> listGoal;
	private Goal goalCurrent;
	private List<Component> listComponent;
	private Component componentCurrent;
	private List<Object[]> listMeasureAction;
	private Measure measureCurrent;
	private List<Object[]> listIndicator;
	private List<Measure> listMeasureByComponent;
	private Action actionCurrent;
	private List<Cobenefit> listCobenefit;
	private Cobenefit cobenefitCurrent;
	private List<LegalFrameworkDoc> listLegalFrameworkDoc;
	private LegalFrameworkDoc legalFrameworkDocCurrent;
	private UploadedFile fileLegalFrameworkDocCurrent;
	private byte[] contentUploadedFile;
	private StreamedContent fileLegalFrameworkDocToDownload;
	
	private int tabIndex;

	public PlanAccionController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			actionPlanCurrent = new ActionPlan();
			specificObjectiveCurrent = new SpecificObjective();
			goalCurrent = new Goal();
			inicializarComponente();
			inicializarMedida();
			inicializarIndicador();
			inicializarAccion();
			inicializarCobeneficio();
			inicializarDocMarcoLegal();
			actualizarListaPlanes();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void actualizarListaPlanes() {
		try {
			listActionPlan = actionPlanFacade.listarPlanes();

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void llamarVentanaInfoGeneralNuevo() {
		try {
			actionPlanCurrent = new ActionPlan();
			PrimeFaces.current().executeScript("PF('dlgInfoGeneral').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarPlanAccion() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			String s1 = "frm:";
			String s2 = "";
			if (actionPlanCurrent.getAcplId() != null) {
				s1 = "frm:tvContenido:";
				s2 = "2";
			}
			// validacion un solo proyecto vigente
			if (actionPlanCurrent.getAcplIscurrent()) {
				int id1 = 0;
				if (actionPlanCurrent.getAcplId() != null) {
					id1 = actionPlanCurrent.getAcplId();
				}
				for (ActionPlan ap : listActionPlan) {
					if (ap.getAcplId() != id1) {
						if (ap.getAcplIscurrent()) {
							listaMsg = sesBean.validacionMsg(s1 + "acplIscurrent" + s2,
									appBean.getBundle().getString("msg027") + " ", listaMsg);
							break;
						}
					}
				}
			}
			// validaciones campos obligatorios
			if (actionPlanCurrent.getAcplName().length() == 0) {
				listaMsg = sesBean.validacionMsg(s1 + "acplName" + s2, appBean.getBundle().getString("lbl002")+" es "+appBean.getBundle().getString("msg031") + " ",
						listaMsg);
			}
			boolean valFechas=true;
			if (actionPlanCurrent.getAcplStartDate() == null) {
				listaMsg = sesBean.validacionMsg(s1 + "acplStartDate" + s2,
						appBean.getBundle().getString("lbl003")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				valFechas=false;
			}
			if (actionPlanCurrent.getAcplFinishDate() == null) {
				listaMsg = sesBean.validacionMsg(s1 + "acplFinishDate" + s2,
						appBean.getBundle().getString("lbl004")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				valFechas=false;
			}
			if(valFechas){
				if(actionPlanCurrent.getAcplStartDate().compareTo(actionPlanCurrent.getAcplFinishDate())>=0){
					listaMsg = sesBean.validacionMsg(s1 + "acplStartDate" + s2,
							appBean.getBundle().getString("msg167") + " ", listaMsg);
				}
			}
			if (actionPlanCurrent.getAcplGeneralObjective().length() == 0) {
				listaMsg = sesBean.validacionMsg(s1 + "acplGeneralObjective" + s2,
						appBean.getBundle().getString("lbl007")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if (listaMsg.isEmpty()) {
				if (actionPlanCurrent.getAcplId() == null) {
					actionPlanFacade.crear("temp", actionPlanCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg001"));

				} else {
					
					actionPlanFacade.editar("temp", actionPlanCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg002"));
				}
				actualizarListaPlanes();
				appBean.recargarPlanesAccion();
				PrimeFaces.current().executeScript("PF('dlgInfoGeneral').hide()");
			} else {
				actualizarListaPlanes();
				if (actionPlanCurrent.getAcplId() != null) {
					actionPlanCurrent.setAcplIscurrent(false);
				}

				sesBean.addErrorMessagesForComponentes(listaMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarInfoGeneralEditar(ActionPlan ap) {
		try {
			actionPlanCurrent = ap;
			PrimeFaces.current().executeScript("PF('dlgInfoGeneral').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarPlanAccion(ActionPlan ap) {
		try {
			actionPlanCurrent = new ActionPlan();
			actionPlanFacade.eliminarLogico("temp", ap);
			actualizarListaPlanes();
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg003"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarPlanAccionVer(ActionPlan ap) {
		try {
			actionPlanCurrent = ap;
			listSpecificObjective = specificObjectiveFacade.listarObjetivosPorPlan(actionPlanCurrent.getAcplId());
			listGoal = goalFacade.listarMetasPorPlan(actionPlanCurrent.getAcplId());
			listComponent = componentFacade.listarComponentesPorPlan(actionPlanCurrent.getAcplId());
			listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
			listIndicator = measureFacade.listarIndicadoresResumenPorPlan(actionPlanCurrent.getAcplId());
			listCobenefit = cobenefitFacade.listarCobeneficiosPorPlan(actionPlanCurrent.getAcplId());
			listLegalFrameworkDoc = legalFrameworkDocFacade.listarDocumentosPorPlan(actionPlanCurrent.getAcplId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void llamarVentanaObjetivoEspecificoNuevo() {
		try {
			specificObjectiveCurrent = new SpecificObjective();
			specificObjectiveCurrent.setAcplId(actionPlanCurrent);
			PrimeFaces.current().executeScript("PF('dlgObjEspecifico').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarObjetivoEspecificoEditar(SpecificObjective so) {
		try {
			specificObjectiveCurrent = so;
			PrimeFaces.current().executeScript("PF('dlgObjEspecifico').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarObjetivoEspecifico() {
		try {
			String msgVal = "";
			if (specificObjectiveCurrent.getSpobDescription() == null
					|| specificObjectiveCurrent.getSpobDescription().length() == 0) {
				msgVal = appBean.getBundle().getString("lbl014")+" es "+appBean.getBundle().getString("msg031");
			}
			if (msgVal.equals("")) {
				if (specificObjectiveCurrent.getSpobId() == null) {
					specificObjectiveFacade.create(specificObjectiveCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg004"));
				} else {
					specificObjectiveFacade.edit(specificObjectiveCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg005"));
				}
				listSpecificObjective = specificObjectiveFacade.listarObjetivosPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgObjEspecifico').hide()");
			} else {
				sesBean.mensajeErrorComponente("frm:objEspDesc", msgVal);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarObjetivoEspecifico(SpecificObjective so) {
		try {
			specificObjectiveCurrent = new SpecificObjective();
			specificObjectiveFacade.eliminarLogico(so);
			listSpecificObjective = specificObjectiveFacade.listarObjetivosPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg006"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void llamarVentanaMetaNuevo() {
		try {
			goalCurrent = new Goal();
			goalCurrent.setAcplId(actionPlanCurrent);
			PrimeFaces.current().executeScript("PF('dlgMeta').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarMetaEditar(Goal go) {
		try {
			goalCurrent = go;
			PrimeFaces.current().executeScript("PF('dlgMeta').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarMeta() {
		try {
			String msgVal = "";
			if (goalCurrent.getGoalDescription() == null || goalCurrent.getGoalDescription().length() == 0) {
				msgVal = appBean.getBundle().getString("lbl016")+" es "+appBean.getBundle().getString("msg031");
			}
			if (msgVal.equals("")) {
				if (goalCurrent.getGoalId() == null) {
					goalFacade.create(goalCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg007"));
				} else {
					goalFacade.edit(goalCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg008"));
				}
				listGoal = goalFacade.listarMetasPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgMeta').hide()");
			} else {
				sesBean.mensajeErrorComponente("frm:metaDesc", msgVal);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarMeta(Goal go) {
		try {
			goalCurrent = new Goal();
			goalFacade.eliminarLogico(go);
			listGoal = goalFacade.listarMetasPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg009"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarComponente() {
		componentCurrent = new Component();
		componentCurrent.setCataId(new Catalog());
	}

	public void llamarVentanaComponenteNuevo() {
		try {
			inicializarComponente();
			componentCurrent.setAcplId(actionPlanCurrent);
			PrimeFaces.current().executeScript("PF('dlgComponente').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarComponenteEditar(Component co) {
		try {
			componentCurrent = co;
			PrimeFaces.current().executeScript("PF('dlgComponente').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarComponente() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			if(componentCurrent.getCompName().length()==0){
				listaMsg = sesBean.validacionMsg("frm:compNom",
						appBean.getBundle().getString("lbl018")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(componentCurrent.getCataId().getCataId()==null){
				listaMsg = sesBean.validacionMsg("frm:compTipo",
						appBean.getBundle().getString("lbl019")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(componentCurrent.getCompObjective().length()==0){
				listaMsg = sesBean.validacionMsg("frm:compObj",
						appBean.getBundle().getString("lbl020")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if (componentCurrent.getCompId() != null) {
				if(componentCurrent.getCompCode().length()==0){
					listaMsg = sesBean.validacionMsg("frm:compCod",
							appBean.getBundle().getString("lbl017")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				}
			}
			if (listaMsg.isEmpty()) {
				if (componentCurrent.getCompId() == null) {
					componentCurrent.setCompCode(construirCodigoComponente(componentCurrent.getCataId().getCataId()));

					componentFacade.create(componentCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg010"));
				} else {
					componentFacade.edit(componentCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg011"));
				}
				listComponent = componentFacade.listarComponentesPorPlan(actionPlanCurrent.getAcplId());
				listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgComponente').hide()");
			} else {
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private String construirCodigoComponente(Integer codCatTipoComp) {
		int n = 0;
		for (Component c : listComponent) {
			if (c.getCataId().getCataId() == codCatTipoComp) {
				n++;
			}
		}
		n++;
		String prefix = "";
		if (codCatTipoComp == appBean.getCodCatCompEstrategico()) {
			prefix = "CE";
		} else {
			prefix = "CO";
		}
		return n < 10 ? prefix + "0" + n : prefix + n;
	}

	public void eliminarComponente(Component co) {
		try {
			inicializarComponente();
			componentFacade.eliminarLogico(co);
			listComponent = componentFacade.listarComponentesPorPlan(actionPlanCurrent.getAcplId());
			listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
			listCobenefit = cobenefitFacade.listarCobeneficiosPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg012"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void prepararNuevaMedidaDesdeComponente(Component co) {
		try {
			llamarVentanaMedidaNuevo();
			measureCurrent.setCompId(co);
	    } catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void prepararNuevaAccionDesdeComponente(Component co) {
		try {
			llamarVentanaAccionNuevo();
			actionCurrent.getMeasId().setCompId(co);
			listMeasureByComponent=measureFacade.listarMedidasPorComponente(co.getCompId());
	    } catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarIndicador() {
		measureCurrent = new Measure();
		measureCurrent.setCompId(new Component());
		measureCurrent.setMeasType("I");
	}

	public void llamarVentanaIndicadorNuevo() {
		try {
			inicializarIndicador();;
			PrimeFaces.current().executeScript("PF('dlgIndicador').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarIndicadorEditar(Integer id) {
		try {
			measureCurrent = measureFacade.find(id);
			PrimeFaces.current().executeScript("PF('dlgIndicador').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void listarIndicadoresPorComponente() {
		try {
			if (actionCurrent.getMeasId().getCompId().getCompId() != null) {
				listMeasureByComponent = measureFacade
						.listarIndicadoresPorComponente(actionCurrent.getMeasId().getCompId().getCompId());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarIndicador() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			if (measureCurrent.getMeasId() != null) {
				if(measureCurrent.getMeasCode().length()==0){
					listaMsg = sesBean.validacionMsg("frm:indCod",
							appBean.getBundle().getString("lbl017")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				}
				
			}
			if(measureCurrent.getCompId()==null||measureCurrent.getCompId().getCompId()==null){
				listaMsg = sesBean.validacionMsg("frm:indComp",
						appBean.getBundle().getString("lbl022")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(measureCurrent.getMeasDescription().length()==0){
				listaMsg = sesBean.validacionMsg("frm:indDesc",
						appBean.getBundle().getString("lbl097")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(measureCurrent.getMeasGoal().length()==0){
				listaMsg = sesBean.validacionMsg("frm:indGoal",
						appBean.getBundle().getString("lbl016")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			
			if(listaMsg.isEmpty()){
				if (measureCurrent.getMeasId() == null) {
					measureCurrent.setMeasCode(construirCodigoIndicador(measureCurrent.getCompId().getCompId()));
					measureFacade.create(measureCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg051"));
				} else {
					measureFacade.edit(measureCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg052"));
				}
				listIndicator = measureFacade.listarIndicadoresResumenPorPlan(actionPlanCurrent.getAcplId());
				
				PrimeFaces.current().executeScript("PF('dlgIndicador').hide()");
			}else{
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private String construirCodigoIndicador(Integer compId) {
		return "I"+(listIndicator.size()+1);
	}

	public void eliminarIndicador(Integer id) {
		try {
			inicializarIndicador();;
			measureFacade.eliminarLogico(id);
			listIndicator = measureFacade.listarIndicadoresResumenPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg053"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void inicializarMedida() {
		measureCurrent = new Measure();
		measureCurrent.setCompId(new Component());
		measureCurrent.setMeasType("M");
	}

	public void llamarVentanaMedidaNuevo() {
		try {
			inicializarMedida();
			PrimeFaces.current().executeScript("PF('dlgMedida').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarMedidaEditar(Integer id) {
		try {
			measureCurrent = measureFacade.find(id);
			PrimeFaces.current().executeScript("PF('dlgMedida').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	

	public void listarMedidasPorComponente() {
		try {
			if (actionCurrent.getMeasId().getCompId()!=null&&actionCurrent.getMeasId().getCompId().getCompId() != null) {
				listMeasureByComponent = measureFacade
						.listarMedidasPorComponente(actionCurrent.getMeasId().getCompId().getCompId());
			}else{
				listMeasureByComponent=new ArrayList<>();
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarMedida() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			if (measureCurrent.getMeasId() != null) {
				if(measureCurrent.getMeasCode().length()==0){
					listaMsg = sesBean.validacionMsg("frm:medCod",
							appBean.getBundle().getString("lbl017")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				}
				
			}
			if(measureCurrent.getCompId()==null||measureCurrent.getCompId().getCompId()==null){
				listaMsg = sesBean.validacionMsg("frm:medComp",
						appBean.getBundle().getString("lbl022")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(measureCurrent.getMeasDescription().length()==0){
				listaMsg = sesBean.validacionMsg("frm:medDesc",
						appBean.getBundle().getString("lbl023")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			
			if(listaMsg.isEmpty()){
				if (measureCurrent.getMeasId() == null) {
					measureCurrent.setMeasCode(construirCodigoMedida(measureCurrent.getCompId().getCompId()));
					measureFacade.create(measureCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg013"));
				} else {
					measureFacade.edit(measureCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg014"));
				}
				listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgMedida').hide()");
			}else{
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private String construirCodigoMedida(Integer compId) {
		int n = 0;
		for (Object[] o : listMeasureAction) {
			if (Integer.valueOf(String.valueOf(o[7])).equals(compId) && o[5] != null) {
				n++;
			}
		}
		n++;
		String prefix = "";
		for (Component c : listComponent) {
			if (c.getCompId() == compId) {
				prefix = c.getCompCode();
				break;
			}
		}
		return n < 10 ? prefix + "M0" + n : prefix + "M" + n;
	}

	public void eliminarMedida(Integer id) {
		try {
			inicializarMedida();
			measureFacade.eliminarLogico(id);
			listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg015"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	

	private void inicializarAccion() {
		actionCurrent = new Action();
		Measure m = new Measure();
		m.setCompId(new Component());
		actionCurrent.setMeasId(m);
	}

	public void llamarVentanaAccionNuevo() {
		try {
			inicializarAccion();
			PrimeFaces.current().executeScript("PF('dlgAccion').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarAccionEditar(Integer id) {
		try {
			actionCurrent = actionFacade.find(id);
			listarMedidasPorComponente();
			PrimeFaces.current().executeScript("PF('dlgAccion').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarAccion() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			
			if(actionCurrent.getMeasId()==null||actionCurrent.getMeasId().getMeasId()==null){
				listaMsg = sesBean.validacionMsg("frm:accComp",
						appBean.getBundle().getString("lbl022")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				listaMsg = sesBean.validacionMsg("frm:accMed",
						appBean.getBundle().getString("lbl023")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
				Measure m = new Measure();
				m.setCompId(new Component());
				actionCurrent.setMeasId(m);
			}
			if(actionCurrent.getActiDescription().length()==0){
				listaMsg = sesBean.validacionMsg("frm:accDesc",
						appBean.getBundle().getString("lbl024")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(listaMsg.isEmpty()){
				if (actionCurrent.getActiId() == null) {
					actionFacade.create(actionCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg016"));
				} else {
					actionFacade.edit(actionCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg017"));
				}
				listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgAccion').hide()");
			}else{
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarAccion(Integer id) {
		try {
			inicializarAccion();
			actionFacade.eliminarLogico(id);
			listMeasureAction = measureFacade.listarMedidasyAccionesPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg018"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarCobeneficio() {
		cobenefitCurrent = new Cobenefit();
		cobenefitCurrent.setCompId(new Component());
	}

	public void llamarVentanaCobeneficioNuevo() {
		try {
			inicializarCobeneficio();
			PrimeFaces.current().executeScript("PF('dlgCobeneficio').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarCobeneficioEditar(Cobenefit co) {
		try {
			cobenefitCurrent = co;
			PrimeFaces.current().executeScript("PF('dlgCobeneficio').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarCobeneficio() {
		try {
			List<String[]> listaMsg = new ArrayList<>();
			
			if(cobenefitCurrent.getCompId()==null||cobenefitCurrent.getCompId().getCompId()==null){
				listaMsg = sesBean.validacionMsg("frm:cobComp",
						appBean.getBundle().getString("lbl022")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(cobenefitCurrent.getCobeDescription().length()==0){
				listaMsg = sesBean.validacionMsg("frm:cobDesc",
						appBean.getBundle().getString("lbl026")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			
			if(listaMsg.isEmpty()){
				if (cobenefitCurrent.getCobeId() == null) {
					cobenefitFacade.create(cobenefitCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg019"));
				} else {
					cobenefitFacade.edit(cobenefitCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg020"));
				}
				listCobenefit = cobenefitFacade.listarCobeneficiosPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgCobeneficio').hide()");
			}else{
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarCobeneficio(Cobenefit co) {
		try {
			inicializarCobeneficio();
			cobenefitFacade.eliminarLogico(co);
			listCobenefit = cobenefitFacade.listarCobeneficiosPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg021"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void inicializarDocMarcoLegal() {
		legalFrameworkDocCurrent = new LegalFrameworkDoc();
		legalFrameworkDocCurrent.setCataId(new Catalog());
		fileLegalFrameworkDocCurrent=null;
	}

	public void llamarVentanaMarcoLegalNuevo() {
		try {
			inicializarDocMarcoLegal();
			PrimeFaces.current().executeScript("PF('dlgMarcoLegal').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarMarcoLegalEditar(LegalFrameworkDoc le) {
		try {
			legalFrameworkDocCurrent = le;
			PrimeFaces.current().executeScript("PF('dlgMarcoLegal').show()");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		
		fileLegalFrameworkDocCurrent = event.getFile();
		contentUploadedFile = fileLegalFrameworkDocCurrent.getContent();
		if (fileLegalFrameworkDocCurrent != null) {
			sesBean.addSuccessMessage(
					appBean.getBundle().getString("msg022") + " " + fileLegalFrameworkDocCurrent.getFileName());
		}
	}

	public void guardarMarcoLegal() {
		try {

			List<String[]> listaMsg = new ArrayList<>();
			if (legalFrameworkDocCurrent.getLfdoId() == null && fileLegalFrameworkDocCurrent == null) {
				
				listaMsg = sesBean.validacionMsg("frm:fuMarcoLegal",
						appBean.getBundle().getString("msg023") + " ", listaMsg);
			}
			if(fileLegalFrameworkDocCurrent!=null){
				if(fileLegalFrameworkDocCurrent.getFileName().endsWith(".pdf")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".doc")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".docx")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".jpeg")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".png")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".zip")||
						fileLegalFrameworkDocCurrent.getFileName().endsWith(".rar")){
					
				}else{
					listaMsg = sesBean.validacionMsg("frm:fuMarcoLegal",
							appBean.getBundle().getString("msg168") + " ", listaMsg);
				}
			}
			
			if(legalFrameworkDocCurrent.getCataId()==null||legalFrameworkDocCurrent.getCataId().getCataId()==null){
				listaMsg = sesBean.validacionMsg("frm:docMec",
						appBean.getBundle().getString("lbl019")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			if(legalFrameworkDocCurrent.getLfdoName().length()==0){
				listaMsg = sesBean.validacionMsg("frm:docNom",
						appBean.getBundle().getString("lbl018")+" es "+appBean.getBundle().getString("msg031") + " ", listaMsg);
			}
			/*if(legalFrameworkDocCurrent.getLfdoOfficialRegistryNumber().length()==0){
				listaMsg = sesBean.validacionMsg("frm:docReg",
						appBean.getBundle().getString("msg031") + " ", listaMsg);
			}*/

			if (listaMsg.isEmpty()) {

				if (legalFrameworkDocCurrent.getLfdoId() == null) {
					

					legalFrameworkDocCurrent.setDocuId(appBean.almacenarYObtenerDocSigma(fileLegalFrameworkDocCurrent));
					legalFrameworkDocCurrent.setAcplId(actionPlanCurrent);
					legalFrameworkDocFacade.create(legalFrameworkDocCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg024"));
				} else {
					if(fileLegalFrameworkDocCurrent!=null){
						legalFrameworkDocCurrent.setDocuId(appBean.almacenarYObtenerDocSigma(fileLegalFrameworkDocCurrent));
					}
					legalFrameworkDocFacade.edit(legalFrameworkDocCurrent);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg025"));
				}

				listLegalFrameworkDoc = legalFrameworkDocFacade.listarDocumentosPorPlan(actionPlanCurrent.getAcplId());
				PrimeFaces.current().executeScript("PF('dlgMarcoLegal').hide()");
			} else {
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}

		} catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarMarcoLegal(LegalFrameworkDoc le) {
		try {
			inicializarDocMarcoLegal();
			legalFrameworkDocFacade.eliminarLogico(le);
			listLegalFrameworkDoc = legalFrameworkDocFacade.listarDocumentosPorPlan(actionPlanCurrent.getAcplId());
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg026"));
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	/*public void descargarMarcoLegal(LegalFrameworkDoc le) {
		try {
			inicializarDocMarcoLegal();
			legalFrameworkDocFacade.eliminarLogico(le);
			listLegalFrameworkDoc = legalFrameworkDocFacade.listarDocumentosPorPlan(actionPlanCurrent.getAcplId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}*/

	public void descargarArchivo(ec.gob.ambiente.sigma.ejb.entidades.Document docSigma){
		try{
			inicializarAccion();
			fileLegalFrameworkDocToDownload=appBean.obtenerStreamedContentDeDocSigma(docSigma);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void cambiarTab(int newTabIndex){
		tabIndex=newTabIndex;
	}
	
	public void finalizarRegistro(){
		inicializar();
		sesBean.addSuccessMessage(appBean.getBundle().getString("msg084"));
	}


	public ActionPlan getActionPlanCurrent() {
		return actionPlanCurrent;
	}

	public void setActionPlanCurrent(ActionPlan actionPlanCurrent) {
		this.actionPlanCurrent = actionPlanCurrent;
	}

	public List<ActionPlan> getListActionPlan() {
		return listActionPlan;
	}

	public void setListActionPlan(List<ActionPlan> listActionPlan) {
		this.listActionPlan = listActionPlan;
	}

	public List<SpecificObjective> getListSpecificObjective() {
		return listSpecificObjective;
	}

	public void setListSpecificObjective(List<SpecificObjective> listSpecificObjective) {
		this.listSpecificObjective = listSpecificObjective;
	}

	public SpecificObjective getSpecificObjectiveCurrent() {
		return specificObjectiveCurrent;
	}

	public void setSpecificObjectiveCurrent(SpecificObjective specificObjectiveCurrent) {
		this.specificObjectiveCurrent = specificObjectiveCurrent;
	}

	public List<Goal> getListGoal() {
		return listGoal;
	}

	public void setListGoal(List<Goal> listGoal) {
		this.listGoal = listGoal;
	}

	public Goal getGoalCurrent() {
		return goalCurrent;
	}

	public void setGoalCurrent(Goal goalCurrent) {
		this.goalCurrent = goalCurrent;
	}

	public List<Component> getListComponent() {
		return listComponent;
	}

	public void setListComponent(List<Component> listComponent) {
		this.listComponent = listComponent;
	}

	public Component getComponentCurrent() {
		return componentCurrent;
	}

	public void setComponentCurrent(Component componentCurrent) {
		this.componentCurrent = componentCurrent;
	}

	public List<Object[]> getListMeasureAction() {
		return listMeasureAction;
	}

	public void setListMeasureAction(List<Object[]> listMeasureAction) {
		this.listMeasureAction = listMeasureAction;
	}

	public Measure getMeasureCurrent() {
		return measureCurrent;
	}

	public void setMeasureCurrent(Measure measureCurrent) {
		this.measureCurrent = measureCurrent;
	}

	public Action getActionCurrent() {
		return actionCurrent;
	}

	public void setActionCurrent(Action actionCurrent) {
		this.actionCurrent = actionCurrent;
	}

	public List<Cobenefit> getListCobenefit() {
		return listCobenefit;
	}

	public void setListCobenefit(List<Cobenefit> listCobenefit) {
		this.listCobenefit = listCobenefit;
	}

	public Cobenefit getCobenefitCurrent() {
		return cobenefitCurrent;
	}

	public void setCobenefitCurrent(Cobenefit cobenefitCurrent) {
		this.cobenefitCurrent = cobenefitCurrent;
	}

	public List<LegalFrameworkDoc> getListLegalFrameworkDoc() {
		return listLegalFrameworkDoc;
	}

	public void setListLegalFrameworkDoc(List<LegalFrameworkDoc> listLegalFrameworkDoc) {
		this.listLegalFrameworkDoc = listLegalFrameworkDoc;
	}

	public LegalFrameworkDoc getLegalFrameworkDocCurrent() {
		return legalFrameworkDocCurrent;
	}

	public void setLegalFrameworkDocCurrent(LegalFrameworkDoc legalFrameworkDocCurrent) {
		this.legalFrameworkDocCurrent = legalFrameworkDocCurrent;
	}

	public List<Measure> getListMeasureByComponent() {
		return listMeasureByComponent;
	}

	public void setListMeasureByComponent(List<Measure> listMeasureByComponent) {
		this.listMeasureByComponent = listMeasureByComponent;
	}

	public UploadedFile getFileLegalFrameworkDocCurrent() {
		return fileLegalFrameworkDocCurrent;
	}

	public void setFileLegalFrameworkDocCurrent(UploadedFile fileLegalFrameworkDocCurrent) {
		this.fileLegalFrameworkDocCurrent = fileLegalFrameworkDocCurrent;
	}

	public StreamedContent getFileLegalFrameworkDocToDownload() {
		return fileLegalFrameworkDocToDownload;
	}

	public void setFileLegalFrameworkDocToDownload(StreamedContent fileLegalFrameworkDocToDownload) {
		this.fileLegalFrameworkDocToDownload = fileLegalFrameworkDocToDownload;
	}

	public List<Object[]> getListIndicator() {
		return listIndicator;
	}

	public void setListIndicator(List<Object[]> listIndicator) {
		this.listIndicator = listIndicator;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	

}
