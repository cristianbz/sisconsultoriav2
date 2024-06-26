package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import ec.gob.ambiente.sigma.ejb.entidades.Document;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.PartnersRequirement;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancingAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.facades.AgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsAgreementFacade;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;

@Named("conv")
@ViewScoped
public class ConvenioImplementacionController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SocioImplementadorController.class.getName());

	private Agreement convenioActual;
	private List<Agreement> listaConvenios;
	private StreamedContent archivoConvenioADescargar;
	private boolean flagSoloCrear;

	@EJB
	private AgreementFacade convenioFacade;
	@EJB
	private ProjectsAgreementFacade proyectoConvenioFacade;

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	public ConvenioImplementacionController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			prepararNuevoConvenio();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			String attr = (String) session.getAttribute("TIPO_PAG_CONIMP");
			if(attr!=null&&attr.contains("SOLO")){
				//solo crear para mostrar en registro proyectos
				flagSoloCrear=true;
			}else{
				listaConvenios = new ArrayList<>();
				listaConvenios = convenioFacade.listarConvenios();
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void prepararNuevoConvenio(){
		try{
			convenioActual = new Agreement();
			//usuario temporal
			convenioActual.setUserId(sesBean.getUsuarioLogeado());
			convenioActual.setAgreRegisterDate(new Date());
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarConvenio() {
		try {
			List<String[]> listaMsg = validarConvenio(convenioActual);

			if (listaMsg.isEmpty()) {
				
				if(convenioActual.getAgreId()==null){
					convenioFacade.crear(sesBean.getUsuarioLogeado().getUserName(), convenioActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg041"));
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg161"));
				}else{
					convenioFacade.editar(sesBean.getUsuarioLogeado().getUserName(), convenioActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg042"));
				}
				if(flagSoloCrear){
					listaConvenios=convenioFacade.listarConvenioUnico(convenioActual.getAgreId());
					
					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
							.getSession(false);
					Integer attr2 = (Integer) session.getAttribute("ID_PROYECTO_ACTUAL");
					if (attr2 != null) {
						ProjectsAgreement pa=new ProjectsAgreement();
						pa.setAgreId(convenioActual);
						pa.setProjId(new Project(attr2));
						proyectoConvenioFacade.create(pa);
					}
					
				}else{
					listaConvenios=convenioFacade.listarConvenios();
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

	private List<String[]> validarConvenio(Agreement convenio) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (convenio.getAgreRegisterCode().length() != 6) {
				sesBean.validacionMsg("frm:agreRegCode", appBean.getBundle().getString("msg037"), listaMsg);
			}else{
				Agreement conv=convenioFacade.obtenerConvenioPorCodigo(convenio.getAgreRegisterCode());
				if(conv!=null){
					if(convenio.getAgreId()==null){
						sesBean.validacionMsg("frm:agreRegCode", appBean.getBundle().getString("msg065")+":"+conv.getAgreRegisterCode(), listaMsg);
					}else{
						if(convenio.getAgreId()!=conv.getAgreId()){
							sesBean.validacionMsg("frm:agreRegCode", appBean.getBundle().getString("msg065")+":"+conv.getAgreRegisterCode(), listaMsg);
						}
					}
				}
			}
			if(convenio.getAgreTitle().length()==0){
				sesBean.validacionMsg("frm:agreTitle",appBean.getBundle().getString("lbl185")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if(convenio.getAgreValidity().length()==0){
				sesBean.validacionMsg("frm:agreValidity", appBean.getBundle().getString("lbl082")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if(convenio.getAgreSubscriptionDate()==null){
				sesBean.validacionMsg("frm:agreSubsDate", appBean.getBundle().getString("lbl083")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if(convenio.getAgreSubscriptionDate()!=null){
				if(convenio.getAgreToDate()!=null){
					if(convenio.getAgreSubscriptionDate().compareTo(convenio.getAgreToDate())>0){
						sesBean.validacionMsg("frm:agreToDate", appBean.getBundle().getString("msg181"), listaMsg);
					}
				}
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	public void seleccionarConvenioAEditar(Agreement conv){
		try {
			convenioActual=conv;
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void eliminarConvenio(Agreement conv){
		try{
			convenioFacade.eliminarLogico("temp", conv);
			prepararNuevoConvenio();
			listaConvenios=convenioFacade.listarConvenios();
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarConvenioParaAdjuntarArchivo(Agreement conv){
		try{
			convenioActual=conv;
			PrimeFaces.current().executeScript("PF('dlgCargarArchivoConv').show()");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void adjuntarArchivoEnConvenio(FileUploadEvent event) {
		try{
			if(convenioActual!=null&&convenioActual.getAgreId()!=null){
				Document d=appBean.almacenarYObtenerDocSigma(event.getFile());
				convenioActual.setDocuId(d);
				convenioFacade.editar("temp",convenioActual);
				PrimeFaces.current().executeScript("PF('dlgCargarArchivoConv').hide()");
				listaConvenios=convenioFacade.listarConvenios();
			}
			if(flagSoloCrear){
				volverARegistroProyecto();
			}
			
		}catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void descargarArchivoConvenio(Document docSigma){
		try{
			archivoConvenioADescargar=appBean.obtenerStreamedContentDeDocSigma(docSigma);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void volverARegistroProyecto(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("TIPO_PAG_CONIMP", null);
		
		sesBean.redireccionarAPaginaConParametro("projects", "RegistroProyecto", "REDIRECCION_DESDE", TipoRetornoAProyecto.DESDE_CONVENIO_IMPLEMENTACION.getCodigo());
	}

	public Agreement getConvenioActual() {
		return convenioActual;
	}

	public void setConvenioActual(Agreement convenioActual) {
		this.convenioActual = convenioActual;
	}

	public List<Agreement> getListaConvenios() {
		return listaConvenios;
	}

	public void setListaConvenios(List<Agreement> listaConvenios) {
		this.listaConvenios = listaConvenios;
	}

	public StreamedContent getArchivoConvenioADescargar() {
		return archivoConvenioADescargar;
	}

	public void setArchivoConvenioADescargar(StreamedContent archivoConvenioADescargar) {
		this.archivoConvenioADescargar = archivoConvenioADescargar;
	}

	public boolean isFlagSoloCrear() {
		return flagSoloCrear;
	}

	public void setFlagSoloCrear(boolean flagSoloCrear) {
		this.flagSoloCrear = flagSoloCrear;
	}
	
	

}
