  package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import ec.gob.ambiente.client.SuiaServices;
import ec.gob.ambiente.client.SuiaServices_Service;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Document;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.PartnersRequirement;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.PartnersRequirementFacade;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;
import ec.gov.sri.wsconsultacontribuyente.ContribuyenteCompleto;

@Named("soci")
@ViewScoped
public class SocioImplementadorController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SocioImplementadorController.class.getName());

	private boolean flagSoloCrear;
	private boolean flagCrearEditarSocioImp;
	private String nombreSocioBusq;
	private Partner socioImplementadorActual;
	private List<Partner> listaSociosImplementadores;
	private Partner socioEstrategicoActual;
	private List<Partner> listaSociosEstrategicos;
	private List<PartnersRequirement> listaRequerimientosSocioImplActual;
	private PartnersRequirement requisitoActualAModificar;
	private StreamedContent archivoRequisitoADescargar;
	private int flagCambioReq;

	@EJB
	private PartnerFacade sociosFacade;
	@EJB
	private PartnersRequirementFacade reqFacade;

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;
	
	
	//campos auxiliares para la generacion de declaracion
	private Integer decCodCiudad;
	private String decDescProvincia;
	private Integer decCodProvincia;
	private String decDescCiudad;
	private List<Object[]> decLstCiudadesPorProv;
	private String decNacionalidad;
	private Integer decCodTipoId;
	private String decDescTipoId;
	private String decNumId;
	private Integer decCodEstCivil;
	private String decDesEstCivil;
	private String decTituloPdi;
	private String decZonInterv;
	private Date decFechaPdi;
	private String decTextoTotal;
	

	public SocioImplementadorController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			String attr = (String) session.getAttribute("TIPO_PAG_SOCIOS");
			//cuando se llama a la pagina desde la creacion de proyectos, solo se debe permitir crear
			//attr SOLO_CREAR_SOC_IMPL / SOLO_CREAR_SOC_EST
			if(attr==null||attr.contains("EST")){
				socioEstrategicoActual = new Partner();
				socioEstrategicoActual.setPartType("E");
				listaSociosEstrategicos = new ArrayList<>();
				if(attr!=null&&!attr.contains("CREAR")){
					listaSociosEstrategicos=sociosFacade.listarSociosEstrategicos();
				}else{
					flagSoloCrear=true;
				}
			}else{
				socioImplementadorActual = new Partner();
				socioImplementadorActual.setCataId(new Catalog());
				socioImplementadorActual.setPartType("I");
				socioImplementadorActual.setPartTypeId("R");
				listaRequerimientosSocioImplActual = new ArrayList<>();
				decLstCiudadesPorProv=new ArrayList<>();
				listaSociosImplementadores = new ArrayList<>();
				if(attr!=null&&attr.contains("CREAR")){
					flagCrearEditarSocioImp=true;
					flagSoloCrear=true;
				}
				socioEstrategicoActual = new Partner();
			}
			
			
			//System.out.println("inicializado socio controller");
			//System.out.println("ATTR:"+attr);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void prepararNuevoSocioImp(){
		try{
			flagCrearEditarSocioImp=true;
			socioImplementadorActual = new Partner();
			socioImplementadorActual.setCataId(new Catalog());
			socioImplementadorActual.setPartType("I");
			socioImplementadorActual.setPartTypeId("R");
			listaRequerimientosSocioImplActual = new ArrayList<>();
			decLstCiudadesPorProv=new ArrayList<>();
			nombreSocioBusq="";
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	
	public void listarSociosImplementadoresPorNombre(){
		try{
			if(nombreSocioBusq.length()>4){
				listaSociosImplementadores=sociosFacade.listarSocioImplementadorPorNombre(nombreSocioBusq);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarSocioImplAEditar(Partner socio) {
		try {
			socioImplementadorActual = socio;
			flagCrearEditarSocioImp=true;
			listaRequerimientosSocioImplActual = new ArrayList<>();
			flagCambioReq = socioImplementadorActual.getCataId().getCataId();
			listarRequisitosPorSocioImpActual();
			
			listaSociosImplementadores = new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarSocioImplementador() {
		try {
			List<String[]> listaMsg = validarSocioImplementador(socioImplementadorActual);

			if (listaMsg.isEmpty()) {
				if (socioImplementadorActual.getPartId() == null) {
					sociosFacade.crear("temp",socioImplementadorActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg033"));
					crearRequisitosDeSocio(socioImplementadorActual);
					listarRequisitosPorSocioImpActual();
					if(flagSoloCrear){
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg076"));
						volverARegistroProyecto();
					}
					
				} else {
					sociosFacade.editar("temp",socioImplementadorActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg034"));
					//si hay cambio de tipo, se validan los requisitos
					if (flagCambioReq != socioImplementadorActual.getCataId().getCataId()) {
						if (flagCambioReq == appBean.getCodCatTipoSocComuIndig() || socioImplementadorActual.getCataId()
								.getCataId() == appBean.getCodCatTipoSocComuIndig()) {
							reqFacade.eliminarRequisitosPorSocioImplementador(socioImplementadorActual.getPartId());
							crearRequisitosDeSocio(socioImplementadorActual);
							listarRequisitosPorSocioImpActual();
						}
					}
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
	
	public void volverARegistroProyecto(){
			sesBean.redireccionarAPaginaConParametros("projects", "RegistroProyecto", "REDIRECCION_DESDE", TipoRetornoAProyecto.DESDE_SOCIO_IMPLEMENTADOR.getCodigo(),"ID_SOCIMP_ACTUAL",socioImplementadorActual.getPartId());
		
	}

	private List<String[]> validarSocioImplementador(Partner socioImp) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (socioImp.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:partType1", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (socioImp.getPartIdNumber().length() != 13) {
				sesBean.validacionMsg("frm:partRuc", appBean.getBundle().getString("msg032"), listaMsg);
			}else{
				List<Partner> lstValRuc=sociosFacade.listarSocioImplementadorPorIdentificacion(socioImp.getPartIdNumber());
				if(socioImp.getPartId()==null&&!lstValRuc.isEmpty()){
					sesBean.validacionMsg("frm:partRuc", appBean.getBundle().getString("msg075"), listaMsg);
				}
				if(socioImp.getPartId()!=null&&!lstValRuc.isEmpty()&&lstValRuc.get(0).getPartId()!=socioImp.getPartId()){
					sesBean.validacionMsg("frm:partRuc", appBean.getBundle().getString("msg075"), listaMsg);
				}
			}
			if (socioImp.getPartName().length() < 1) {
				sesBean.validacionMsg("frm:partName",appBean.getBundle().getString("lbl018")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (socioImp.getPartWebsite()!=null&&socioImp.getPartWebsite().length()>0) {
				if(!sesBean.urlValida(socioImp.getPartWebsite())){
					sesBean.validacionMsg("frm:partWebSite", appBean.getBundle().getString("msg169"), listaMsg);
				}
				
			}
			if (socioImp.getPartContactPerson().length() < 1) {
				sesBean.validacionMsg("frm:partConPerName", appBean.getBundle().getString("lbl052")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			
			if(socioImp.getPartContactPersonEmail()!=null&&socioImp.getPartContactPersonEmail().length()>0){
				if(!sesBean.emailValido(socioImp.getPartContactPersonEmail())){
					sesBean.validacionMsg("frm:partContactPersonEmail", appBean.getBundle().getString("msg170"), listaMsg);
				}
			}
			if (socioImp.getPartPhones().length() < 1) {
				sesBean.validacionMsg("frm:partConPerPhone", appBean.getBundle().getString("lbl055")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (socioImp.getPartAcronym()==null || socioImp.getPartAcronym().length() < 1) {
				sesBean.validacionMsg("frm:partSigla", appBean.getBundle().getString("lbl339")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	private void crearRequisitosDeSocio(Partner soc) {
		try {
			if (soc.getCataId().getCataId() != appBean.getCodCatTipoSocComuIndig()) {
				for (Catalog c : appBean.getLstCatReqSoc1()) {
					PartnersRequirement req = new PartnersRequirement();
					req.setPartId(soc);
					req.setCataId(c);
					reqFacade.create(req);
				}
			} else {
				for (Catalog c : appBean.getLstCatReqSoc2()) {
					PartnersRequirement req = new PartnersRequirement();
					req.setPartId(soc);
					req.setCataId(c);
					reqFacade.create(req);
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	

	public void eliminarSocioImplementador(Partner socio) {
		try {
			sociosFacade.eliminarLogico("temp",socio);
			listaSociosImplementadores = sociosFacade.listarSociosImplementadores();
			nombreSocioBusq="";
			//listaRequerimientosSocioImplActual = new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void listarRequisitosPorSocioImpActual() {
		try {
			listaRequerimientosSocioImplActual=new ArrayList<>();
			listaRequerimientosSocioImplActual=reqFacade.listarRequisitosPorSocioImplementador(socioImplementadorActual.getPartId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarRequisitoParaAdjuntarArchivo(PartnersRequirement req){
		try{
			requisitoActualAModificar=req;
			PrimeFaces.current().executeScript("PF('dlgCargarArchivoReq').show()");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void adjuntarArchivoEnRequisito(FileUploadEvent event) {
		try{
			if(requisitoActualAModificar!=null&&requisitoActualAModificar.getPareId()!=null){
				Document d=appBean.almacenarYObtenerDocSigma(event.getFile());
				requisitoActualAModificar.setDocuId(d);
				reqFacade.edit(requisitoActualAModificar);
				PrimeFaces.current().executeScript("PF('dlgCargarArchivoReq').hide()");
				listarRequisitosPorSocioImpActual();
			}
			
			/*if(flagSoloCrear){
				boolean f=true;
				if(listaRequerimientosSocioImplActual.isEmpty()){
					f=false;
				}
				for(PartnersRequirement r:listaRequerimientosSocioImplActual){
					if(r.getDocuId()==null){
						f=false;
					}
				}
				if(f){
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg076"));
					volverARegistroProyecto();
				}
			}*/
			
		}catch (Exception ex) {
			sesBean.addErrorMessage(appBean.getBundle().getString("msg029"));
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void descargarArchivoRequisito(Document docSigma){
		try{
			archivoRequisitoADescargar=appBean.obtenerStreamedContentDeDocSigma(docSigma);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	
	public void prepararGeneracionDeclaracion(){
		try{
			decCodProvincia=null;
			decDescProvincia="";
			decDescCiudad="";
			decNacionalidad="";
			decDescTipoId="";
			decNumId="";
			decDesEstCivil="";
			decTituloPdi="";
			decZonInterv="";
			decFechaPdi=new Date();
			decTextoTotal="";
			PrimeFaces.current().executeScript("PF('dlgDatosDeclaracion').show()");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void generarDeclaracion(){
		try{
			decDescProvincia=appBean.obtenerTextoProvPorId(decCodProvincia);
			decTextoTotal=appBean.getLstCatDecParrafos().get(0).getCataText2()+"<br/><br/>"+
					appBean.getLstCatDecParrafos().get(1).getCataText2()+"<br/><br/>"+
					appBean.getLstCatDecParrafos().get(2).getCataText2()+"<br/><br/>"+
					appBean.getLstCatDecParrafos().get(3).getCataText2()+"<br/><br/>"+
					appBean.getLstCatDecParrafos().get(4).getCataText2()+"<br/><br/>"+
					appBean.getLstCatDecParrafos().get(5).getCataText2();
			decTextoTotal=decTextoTotal.replace("PARAM_CIUDAD", decDescCiudad);
			decTextoTotal=decTextoTotal.replace("PARAM_PROVINCIA", decDescProvincia);
			decTextoTotal=decTextoTotal.replace("PARAM_PERS_CONT", socioImplementadorActual.getPartContactPerson());
			decTextoTotal=decTextoTotal.replace("PARAM_CARGO_PERS_CONT", socioImplementadorActual.getPartContactPersonPosition());
			decTextoTotal=decTextoTotal.replace("PARAM_NACIO_PERS_CONT", decNacionalidad.toUpperCase());
			decTextoTotal=decTextoTotal.replace("PARAM_TIPO_ID", decDescTipoId);
			decTextoTotal=decTextoTotal.replace("PARAM_ID", decNumId);
			decTextoTotal=decTextoTotal.replace("PARAM_EST_CIVIL", decDesEstCivil);
			decTextoTotal=decTextoTotal.replace("PARAM_NOMBRE_ORG", socioImplementadorActual.getPartName());
			decTextoTotal=decTextoTotal.replace("PARAM_NOMBRE_PROY", decTituloPdi.toUpperCase());
			decTextoTotal=decTextoTotal.replace("PARAM_ZONA_INTERV", decZonInterv.toUpperCase());
			Calendar cal = Calendar.getInstance();
			cal.setTime(decFechaPdi);
			decTextoTotal=decTextoTotal.replace("PARAM_DIA_APROB", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
			decTextoTotal=decTextoTotal.replace("PARAM_MES_APROB", appBean.obtenerTextoMesPorMonth(cal.get(Calendar.MONTH)));
			decTextoTotal=decTextoTotal.replace("PARAM_ANIO_APROB", String.valueOf(cal.get(Calendar.YEAR)));
			decTextoTotal=decTextoTotal.replace("PARAM_DECL_CIUDAD", decDescCiudad);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			decTextoTotal=decTextoTotal.replace("PARAM_DIA_DECL", String.valueOf(cal2.get(Calendar.DAY_OF_MONTH)));
			decTextoTotal=decTextoTotal.replace("PARAM_MES_DECL", appBean.obtenerTextoMesPorMonth(cal2.get(Calendar.MONTH)));
			decTextoTotal=decTextoTotal.replace("PARAM_ANIO_DECL", String.valueOf(cal2.get(Calendar.YEAR)));
			
			PrimeFaces.current().executeScript("PF('dlgDatosDeclaracion').hide()");
			PrimeFaces.current().executeScript("PF('dlgDeclaracion').show()");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void volveraDatosDeclaracion(){
		try{
			PrimeFaces.current().executeScript("PF('dlgDeclaracion').hide()");
			PrimeFaces.current().executeScript("PF('dlgDatosDeclaracion').show()");
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void obtenerDatosSocioImplementadorDesdeSri(){
		try{
			if(socioImplementadorActual.getPartIdNumber()!=null&&socioImplementadorActual.getPartIdNumber().length()==13){
				ContribuyenteCompleto c=sesBean.obtenerContribuyenteDesdeWSSri(socioImplementadorActual.getPartIdNumber());
				socioImplementadorActual.setPartName("");
				socioImplementadorActual.setPartPhones("");
				socioImplementadorActual.setPartContactPerson("");
				socioImplementadorActual.setPartContactPersonPosition("");
				socioImplementadorActual.setPartContactPersonEmail("");
				if(c!=null&&c.getRazonSocial()!=null){
					if(c.getRepresentanteLegal().getNombre()!=null&&c.getRepresentanteLegal().getNombre().length()>0){
						if(c.getNombreComercial()!=null&&c.getNombreComercial().length()>1){
							socioImplementadorActual.setPartName(c.getNombreComercial().substring(0, 200));
						}else{
							socioImplementadorActual.setPartName(c.getRazonSocial());
						}
						if(c.getTelefonoTrabajo()!=null&&c.getTelefonoTrabajo().length()>1){
							String[] s=c.getTelefonoTrabajo().split(",");
							socioImplementadorActual.setPartPhones(s[0].trim());
						}
						socioImplementadorActual.setPartContactPerson(c.getRepresentanteLegal().getNombre());
						socioImplementadorActual.setPartContactPersonPosition(c.getRepresentanteLegal().getCargo());
						if(c.getEmail()!=null){
							String[] s=c.getEmail().split(",");
							socioImplementadorActual.setPartContactPersonEmail(s[0].trim());
						}
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg158"));
					}else{
						sesBean.addErrorMessage(appBean.getBundle().getString("msg190"));
					}
					
				}else{
					sesBean.addErrorMessage(appBean.getBundle().getString("msg157"));
				}
			}else{
				sesBean.addErrorMessage(appBean.getBundle().getString("msg156"));
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	
	public void prepararNuevoSocioEst(){
		try{
			socioEstrategicoActual=new Partner();
			socioEstrategicoActual.setPartType("E");
			socioEstrategicoActual.setPartTypeId("R");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public String guardarSocioEstrategico() {
		String res="";
		try {
			List<String[]> listaMsg = validarSocioEstrategico(socioEstrategicoActual);

			if (listaMsg.isEmpty()) {
				if (socioEstrategicoActual.getPartId() == null) {
					sociosFacade.crear("temp",socioEstrategicoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg038"));
					
				} else {
					sociosFacade.editar("temp",socioEstrategicoActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg039"));
					
				}
				
				if(!flagSoloCrear){
					listaSociosEstrategicos=sociosFacade.listarSociosEstrategicos();
				}
				
			} else {
				res="ERROR";
				for (String[] s : listaMsg) {
					sesBean.mensajeErrorComponente(s[0], s[1]);
				}
				PrimeFaces.current().focus(listaMsg.get(0)[0]);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return res;
	}
	
	private List<String[]> validarSocioEstrategico(Partner socioEst) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if (socioEst.getPartIdNumber().length() != 13) {
				sesBean.validacionMsg("frm:partIdNumber", appBean.getBundle().getString("msg032"), listaMsg);
			}else{
				List<Partner> lstValRuc=sociosFacade.listarSocioEstrategicoPorIdentificacion(socioEst.getPartIdNumber());
				if(socioEst.getPartId()==null&&!lstValRuc.isEmpty()){
					sesBean.validacionMsg("frm:partIdNumber", appBean.getBundle().getString("msg075"), listaMsg);
				}
				if(socioEst.getPartId()!=null&&!lstValRuc.isEmpty()&&lstValRuc.get(0).getPartId()!=socioEst.getPartId()){
					sesBean.validacionMsg("frm:partIdNumber", appBean.getBundle().getString("msg075"), listaMsg);
				}
			}
			if(socioEst.getPartIdNumber()!=null && !StringUtils.isNumeric(socioEst.getPartIdNumber())){
				sesBean.validacionMsg("frm:partIdNumber", appBean.getBundle().getString("msg176"), listaMsg);
			}
			if (socioEst.getPartName()==null||socioEst.getPartName().length() < 1) {
				sesBean.validacionMsg("frm:partName", appBean.getBundle().getString("lbl018")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (socioEst.getPartAcronym()==null||socioEst.getPartAcronym().length() < 1) {
				sesBean.validacionMsg("frm:partSigla", appBean.getBundle().getString("lbl339")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (socioEst.getPartContactPerson()==null||socioEst.getPartContactPerson().length() < 1) {
				sesBean.validacionMsg("frm:partConPer", appBean.getBundle().getString("lbl052")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if(socioEst.getPartContactPersonEmail()!=null&&socioEst.getPartContactPersonEmail().length()>0){
				if(!sesBean.emailValido(socioEst.getPartContactPersonEmail())){
					sesBean.validacionMsg("frm:partConPerEmail", appBean.getBundle().getString("msg170"), listaMsg);
				}
			}
			if (socioEst.getPartPhones()==null||socioEst.getPartPhones().length() < 1) {
				sesBean.validacionMsg("frm:partConPerTelf", appBean.getBundle().getString("lbl055")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	public void seleccionarSocioEstAEditar(Partner socio) {
		try {
			socioEstrategicoActual= socio;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarSocioEstrategico(Partner socio) {
		try {
			sociosFacade.eliminarLogico("temp",socio);
			listaSociosEstrategicos=sociosFacade.listarSociosEstrategicos();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void listarCantonesPorProvincia(){
		try{
			if(decCodProvincia!=null&&decCodProvincia>0){
				decLstCiudadesPorProv=new ArrayList<>();
				decLstCiudadesPorProv=appBean.listarCantonesPorIdProvincia(decCodProvincia);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void obtenerDatosSocioEstrategicoDesdeSri(){
		try{
			if(socioEstrategicoActual.getPartIdNumber()!=null&&socioEstrategicoActual.getPartIdNumber().length()==13){
				ContribuyenteCompleto c=sesBean.obtenerContribuyenteDesdeWSSri(socioEstrategicoActual.getPartIdNumber());
				socioEstrategicoActual.setPartName("");
				socioEstrategicoActual.setPartPhones("");
				socioEstrategicoActual.setPartContactPerson("");
				socioEstrategicoActual.setPartContactPersonEmail("");
				if(c!=null&&c.getRazonSocial()!=null){
					if(c.getRepresentanteLegal().getNombre()!=null&&c.getRepresentanteLegal().getNombre().length()>0){
						if(c.getNombreComercial()!=null&&c.getNombreComercial().length()>1){
							socioEstrategicoActual.setPartName(c.getNombreComercial().substring(0, 200));
						}else{
							socioEstrategicoActual.setPartName(c.getRazonSocial());
						}
						
						if(c.getTelefonoTrabajo()!=null&&c.getTelefonoTrabajo().length()>1){
							String[] s=c.getTelefonoTrabajo().split(",");
							socioEstrategicoActual.setPartPhones(s[0].trim());
						}
						socioEstrategicoActual.setPartContactPerson(c.getRepresentanteLegal().getNombre());
						socioEstrategicoActual.setPartContactPersonPosition(c.getRepresentanteLegal().getCargo());
						if(c.getEmail()!=null){
							String[] s=c.getEmail().split(",");
							socioEstrategicoActual.setPartContactPersonEmail(s[0].trim());
						}
						
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg158"));
					}else{
						sesBean.addErrorMessage(appBean.getBundle().getString("msg190"));
					}
					
				}else{
					sesBean.addErrorMessage(appBean.getBundle().getString("msg157"));
				}
			}else{
				sesBean.addErrorMessage(appBean.getBundle().getString("msg156"));
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public Partner getSocioImplementadorActual() {
		return socioImplementadorActual;
	}

	public void setSocioImplementadorActual(Partner socioImplementadorActual) {
		this.socioImplementadorActual = socioImplementadorActual;
	}

	public List<Partner> getListaSociosImplementadores() {
		return listaSociosImplementadores;
	}

	public void setListaSociosImplementadores(List<Partner> listaSociosImplementadores) {
		this.listaSociosImplementadores = listaSociosImplementadores;
	}

	public Partner getSocioEstrategicoActual() {
		return socioEstrategicoActual;
	}

	public void setSocioEstrategicoActual(Partner socioEstrategicoActual) {
		this.socioEstrategicoActual = socioEstrategicoActual;
	}

	public List<Partner> getListaSociosEstrategicos() {
		return listaSociosEstrategicos;
	}

	public void setListaSociosEstrategicos(List<Partner> listaSociosEstrategicos) {
		this.listaSociosEstrategicos = listaSociosEstrategicos;
	}

	public List<PartnersRequirement> getListaRequerimientosSocioImplActual() {
		return listaRequerimientosSocioImplActual;
	}

	public void setListaRequerimientosSocioImplActual(List<PartnersRequirement> listaRequerimientosSocioImplActual) {
		this.listaRequerimientosSocioImplActual = listaRequerimientosSocioImplActual;
	}

	public PartnersRequirement getRequisitoActualAModificar() {
		return requisitoActualAModificar;
	}

	public void setRequisitoActualAModificar(PartnersRequirement requisitoActualAModificar) {
		this.requisitoActualAModificar = requisitoActualAModificar;
	}

	public Integer getDecCodCiudad() {
		return decCodCiudad;
	}

	public void setDecCodCiudad(Integer decCodCiudad) {
		this.decCodCiudad = decCodCiudad;
	}

	public Integer getDecCodProvincia() {
		return decCodProvincia;
	}

	public void setDecCodProvincia(Integer decCodProvincia) {
		this.decCodProvincia = decCodProvincia;
	}

	public List<Object[]> getDecLstCiudadesPorProv() {
		return decLstCiudadesPorProv;
	}

	public void setDecLstCiudadesPorProv(List<Object[]> decLstCiudadesPorProv) {
		this.decLstCiudadesPorProv = decLstCiudadesPorProv;
	}

	public String getDecNacionalidad() {
		return decNacionalidad;
	}

	public void setDecNacionalidad(String decNacionalidad) {
		this.decNacionalidad = decNacionalidad;
	}

	public Integer getDecCodTipoId() {
		return decCodTipoId;
	}

	public void setDecCodTipoId(Integer decCodTipoId) {
		this.decCodTipoId = decCodTipoId;
	}

	public String getDecNumId() {
		return decNumId;
	}

	public void setDecNumId(String decNumId) {
		this.decNumId = decNumId;
	}

	public Integer getDecCodEstCivil() {
		return decCodEstCivil;
	}

	public void setDecCodEstCivil(Integer decCodEstCivil) {
		this.decCodEstCivil = decCodEstCivil;
	}

	public String getDecTituloPdi() {
		return decTituloPdi;
	}

	public void setDecTituloPdi(String decTituloPdi) {
		this.decTituloPdi = decTituloPdi;
	}

	public String getDecZonInterv() {
		return decZonInterv;
	}

	public void setDecZonInterv(String decZonInterv) {
		this.decZonInterv = decZonInterv;
	}

	public Date getDecFechaPdi() {
		return decFechaPdi;
	}

	public void setDecFechaPdi(Date decFechaPdi) {
		this.decFechaPdi = decFechaPdi;
	}
	
	

	public String getDecDescProvincia() {
		return decDescProvincia;
	}

	public void setDecDescProvincia(String decDescProvincia) {
		this.decDescProvincia = decDescProvincia;
	}

	public String getDecDescCiudad() {
		return decDescCiudad;
	}

	public void setDecDescCiudad(String decDescCiudad) {
		this.decDescCiudad = decDescCiudad;
	}

	public String getDecDescTipoId() {
		return decDescTipoId;
	}

	public void setDecDescTipoId(String decDescTipoId) {
		this.decDescTipoId = decDescTipoId;
	}

	public String getDecDesEstCivil() {
		return decDesEstCivil;
	}

	public void setDecDesEstCivil(String decDesEstCivil) {
		this.decDesEstCivil = decDesEstCivil;
	}
	
	

	public String getDecTextoTotal() {
		return decTextoTotal;
	}

	public void setDecTextoTotal(String decTextoTotal) {
		this.decTextoTotal = decTextoTotal;
	}

	public String getNombreSocioBusq() {
		return nombreSocioBusq;
	}

	public void setNombreSocioBusq(String nombreSocioBusq) {
		this.nombreSocioBusq = nombreSocioBusq;
	}

	public boolean isFlagSoloCrear() {
		return flagSoloCrear;
	}

	public void setFlagSoloCrear(boolean flagSoloCrear) {
		this.flagSoloCrear = flagSoloCrear;
	}

	public boolean isFlagCrearEditarSocioImp() {
		return flagCrearEditarSocioImp;
	}

	public void setFlagCrearEditarSocioImp(boolean flagCrearEditarSocioImp) {
		this.flagCrearEditarSocioImp = flagCrearEditarSocioImp;
	}

	public StreamedContent getArchivoRequisitoADescargar() {
		return archivoRequisitoADescargar;
	}

	public void setArchivoRequisitoADescargar(StreamedContent archivoRequisitoADescargar) {
		this.archivoRequisitoADescargar = archivoRequisitoADescargar;
	}
	
	

}
