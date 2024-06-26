package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import ec.gob.ambiente.sigma.ejb.entidades.Agreement;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Document;
import ec.gob.ambiente.sigma.ejb.entidades.FinancingAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancingAgreement;
import ec.gob.ambiente.sigma.ejb.facades.FinancingAgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourceFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsFinancingAgreementFacade;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;

@Named("acue")
@ViewScoped
public class AcuerdoFinanciamientoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AcuerdoFinanciamientoController.class.getName());

	private FinancingAgreement acuerdoActual;
	private List<FinancingAgreement> listaAcuerdos;
	private List<FundingSource> listaFinanciadores;
	private StreamedContent acuerdoADescargar;
	
	private boolean flagSoloCrear;

	@EJB
	private FinancingAgreementFacade acuerdoFacade;
	@EJB
	private FundingSourceFacade fuentesFacade;
	@EJB
	private ProjectsFinancingAgreementFacade proyectoAcuerdoFacade;
	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	public AcuerdoFinanciamientoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			prepararAcuerdoNuevo();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			String attr = (String) session.getAttribute("TIPO_PAG_ACUFIN");
			if(attr!=null&&attr.contains("SOLO")){
				//solo crear para mostrar en registro proyectos
				flagSoloCrear=true;
			}else{
				listaAcuerdos = new ArrayList<>();
				listaAcuerdos = acuerdoFacade.listarAcuerdosFinanciamiento();
			}
			listaFinanciadores = new ArrayList<>();
			listaFinanciadores = fuentesFacade.listarFuentesDeFinanciamiento();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararAcuerdoNuevo() {
		try {
			acuerdoActual = new FinancingAgreement();
			acuerdoActual.setCataAdminId(new Catalog());
			acuerdoActual.setFusoId(new FundingSource());

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarAcuerdo() {
		try {
			List<String[]> listaMsg = validarAcuerdo(acuerdoActual);

			if (listaMsg.isEmpty()) {
					if(acuerdoActual.getFiagId()==null){
						acuerdoFacade.crear("temp", acuerdoActual);
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg054"));
						//prepararAcuerdoNuevo();
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg161"));
					}else{
						acuerdoFacade.editar("temp", acuerdoActual);
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg055"));
					}
					if(flagSoloCrear){
						listaAcuerdos=acuerdoFacade.listarAcuerdoFinanciamientoUnico(acuerdoActual.getFiagId());
						HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
								.getSession(false);
						Integer attr2 = (Integer) session.getAttribute("ID_PROYECTO_ACTUAL");
						if (attr2 != null) {
							ProjectsFinancingAgreement pa=new ProjectsFinancingAgreement();
							pa.setFiagId(acuerdoActual);
							pa.setProjId(new Project(attr2));
							proyectoAcuerdoFacade.create(pa);
						}
					}else{
						listaAcuerdos=acuerdoFacade.listarAcuerdosFinanciamiento();
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

	private List<String[]> validarAcuerdo(FinancingAgreement fa) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (fa.getFusoId()==null||fa.getFusoId().getFusoId()==null) {
				sesBean.validacionMsg("frm:faFuente", appBean.getBundle().getString("lbl103")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getCataAdminId()==null||fa.getCataAdminId().getCataId()==null) {
				sesBean.validacionMsg("frm:faAdmin", appBean.getBundle().getString("lbl104")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagCode().length()==0) {
				sesBean.validacionMsg("frm:faCode", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagTitle().length()==0) {
				sesBean.validacionMsg("frm:faTitle", appBean.getBundle().getString("lbl081")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagFromDate()==null) {
				sesBean.validacionMsg("frm:faFromDate", appBean.getBundle().getString("lbl108")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagToDate()==null) {
				sesBean.validacionMsg("frm:faToDate", appBean.getBundle().getString("lbl109")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagSignDate()==null) {
				sesBean.validacionMsg("frm:faSignDate", appBean.getBundle().getString("lbl107")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fa.getFiagSignDate()!=null) {
				if (fa.getFiagFromDate()!=null) {
					if(fa.getFiagSignDate().compareTo(fa.getFiagFromDate())>0){
						sesBean.validacionMsg("frm:faFromDate", appBean.getBundle().getString("msg182"), listaMsg);
					}
				}
			}
			if (fa.getFiagFromDate()!=null) {
				if (fa.getFiagToDate()!=null) {
                    if(fa.getFiagFromDate().compareTo(fa.getFiagToDate())>=0){
                    	sesBean.validacionMsg("frm:faToDate", appBean.getBundle().getString("msg167"), listaMsg);
					}
				}
			}
			
			if (fa.getFiagAmount()==null||(fa.getFiagAmount().compareTo(new BigDecimal("0"))<=0)) {
				sesBean.validacionMsg("frm:faAmount", appBean.getBundle().getString("lbl111")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	public void seleccionarAcuerdoAEditar(FinancingAgreement fa){
		try {
			acuerdoActual=fa;
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void eliminarAcuerdo(FinancingAgreement fa){
		try{
			acuerdoFacade.eliminarLogico("temp", fa);
			prepararAcuerdoNuevo();
			listaAcuerdos=acuerdoFacade.listarAcuerdosFinanciamiento();
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarAcuerdoParaAdjuntarArchivo(FinancingAgreement fa){
		try{
			acuerdoActual=fa;
			PrimeFaces.current().executeScript("PF('dlgCargarArchivoAcuerdo').show()");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void adjuntarArchivoEnAcuerdo(FileUploadEvent event) {
		try{
			if(acuerdoActual!=null&&acuerdoActual.getFiagId()!=null){
				Document d=appBean.almacenarYObtenerDocSigma(event.getFile());
				acuerdoActual.setDocuId(d);
				acuerdoFacade.editar("temp",acuerdoActual);
				PrimeFaces.current().executeScript("PF('dlgCargarArchivoAcuerdo').hide()");
				listaAcuerdos=acuerdoFacade.listarAcuerdosFinanciamiento();
			}
			if(flagSoloCrear){
				volverARegistroProyecto();
			}
			
		}catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void descargarArchivoAcuerdo(Document docSigma){
		try{
			acuerdoADescargar=appBean.obtenerStreamedContentDeDocSigma(docSigma);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void volverARegistroProyecto(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("TIPO_PAG_ACUFIN", null);
		sesBean.redireccionarAPaginaConParametro("projects", "RegistroProyecto", "REDIRECCION_DESDE", TipoRetornoAProyecto.DESDE_ACUERDO_FINANCIAMIENTO.getCodigo());
	}

	public FinancingAgreement getAcuerdoActual() {
		return acuerdoActual;
	}

	public void setAcuerdoActual(FinancingAgreement acuerdoActual) {
		this.acuerdoActual = acuerdoActual;
	}

	public List<FinancingAgreement> getListaAcuerdos() {
		return listaAcuerdos;
	}

	public void setListaAcuerdos(List<FinancingAgreement> listaAcuerdos) {
		this.listaAcuerdos = listaAcuerdos;
	}

	public List<FundingSource> getListaFinanciadores() {
		return listaFinanciadores;
	}

	public void setListaFinanciadores(List<FundingSource> listaFinanciadores) {
		this.listaFinanciadores = listaFinanciadores;
	}

	public StreamedContent getAcuerdoADescargar() {
		return acuerdoADescargar;
	}

	public void setAcuerdoADescargar(StreamedContent acuerdoADescargar) {
		this.acuerdoADescargar = acuerdoADescargar;
	}

	public boolean isFlagSoloCrear() {
		return flagSoloCrear;
	}

	public void setFlagSoloCrear(boolean flagSoloCrear) {
		this.flagSoloCrear = flagSoloCrear;
	}
	
	
	
	
}
