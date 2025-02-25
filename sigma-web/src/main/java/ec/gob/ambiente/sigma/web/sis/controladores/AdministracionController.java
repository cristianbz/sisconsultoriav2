/**
@autor proamazonia [Christian Báez]  16 jun. 2021

**/
package ec.gob.ambiente.sigma.web.sis.controladores;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.github.cliftonlabs.json_simple.Jsoner;

import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoA;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoB;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoC;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoD;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoE;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoF;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoG;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosSitioPublicoGenero;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoGenero;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.sis.entidades.CatalogTypes;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Catalogs;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Indicators;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.TableResponses;
import ec.gob.ambiente.sigma.ejb.facades.ComponentFacade;
import ec.gob.ambiente.sigma.ejb.facades.MeetingFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsStrategicPartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.SafeguardFacade;
import ec.gob.ambiente.sigma.ejb.facades.UserFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.AdvanceExecutionProjectGenderFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.CatalogsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.CatalogsTypeFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.IndicatorsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.ProjectsGenderInfoFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.QuestionsFacade;
import ec.gob.ambiente.sigma.ejb.sis.facades.TableResponsesFacade;
import ec.gob.ambiente.sigma.ejb.services.RoleFacade;
import ec.gob.ambiente.sigma.web.sis.beans.AdministracionBean;
import ec.gob.ambiente.sigma.web.sis.beans.AplicacionBean;
import ec.gob.ambiente.sigma.web.security.LoginBean;
import ec.gob.ambiente.sigma.web.sis.beans.SitioPublicoBean;
import ec.gob.ambiente.sigma.web.sis.utils.GeneraBDSalvaguardaB;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDGenero;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaA;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaC;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaD;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaE;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaF;
import ec.gob.ambiente.sigma.web.sis.utils.GenerarBDSalvaguardaG;
import ec.gob.ambiente.sigma.web.sis.utils.Mensaje;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoCatalogoEnum;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoRolesUsuarioEnum;

import lombok.Getter;
import lombok.Setter;

@Named()
@ViewScoped
public class AdministracionController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AdministracionController.class);
	
	private static final int TIPO_RESPUESTA=1;
	
	@EJB
	@Getter
	private ProjectsStrategicPartnerFacade projectsStrategicPartnersFacade;
	
	@EJB
	@Getter
	private ProjectsGenderInfoFacade projectsGenderFacade;
	
	@EJB
	@Getter
	private QuestionsFacade questionsFacade;
	
	@EJB
	@Getter
	private ProjectFacade projectsFacade;
	
	@EJB
	@Getter
	private AdvanceExecutionProjectGenderFacade generoFacade;
	@EJB
	@Getter
	private IndicatorsFacade indicatorsFacade;
	

	@EJB
	@Getter
	private CatalogsFacade catalogsFacade;
	
	@EJB
	@Getter
	private UserFacade usersFacade;
	
	@EJB
	@Getter
	private CatalogsTypeFacade catalogsTypeFacade;
	
	@EJB
	@Getter
	private RoleFacade roleFacade;
	
    @Getter
    @Setter
    @Inject
    private AplicacionController aplicacionController;
	
	@Inject
	@Getter
	private AdministracionBean administracionBean;
	
	@Inject
	@Getter
	private AplicacionBean aplicacionBean;
	
    @Getter
    @Setter
    @Inject
    private MensajesController mensajesController;

	@Getter
	@Setter
	@Inject
	private LoginBean loginBean;
	
	@Inject
	@Getter
	private SitioPublicoBean sitioPublicoBean;

	@EJB
	@Getter
	private TableResponsesFacade tableResponsesFacade;

	@EJB
	@Getter
	private SafeguardFacade safeguardsFacade;

	@EJB
	@Getter
	private MeetingFacade meetingsFacade;
	
	@EJB
	@Getter
	private ComponentFacade componentsFacade;

	@EJB
	@Getter
	private AdvanceExecutionProjectGenderFacade avanceExecutionFacade;
	
	private ResourceBundle rb;
	
	@PostConstruct
	public void init(){
		try{
			getAdministracionBean().setNoEsSocioEstrategico(true);
			getAdministracionBean().setDeshabilitaOrdenPregunta(true);
			getAdministracionBean().setDeshabilitaOrdenCatalogo(true);
			getAdministracionBean().setListaPreguntas(new ArrayList<Questions>());
			getAdministracionBean().setListaIndicadores(new ArrayList<Indicators>());
			getAdministracionBean().setListaIndicadores(getIndicatorsFacade().listaTodosIndicadoresGenero());
			getAdministracionBean().setListaPreguntas(getQuestionsFacade().listaPreguntasIngresadas());		
			getAdministracionBean().setListaPreguntasGenero(getQuestionsFacade().buscaTodasPreguntasGenero());
			getAdministracionBean().setListaTipoRespuestaPregunta(getCatalogsFacade().buscaCatalogosPorTipo(TIPO_RESPUESTA));
			getAdministracionBean().setListaCatalogos(getCatalogsFacade().buscaTodosCatalogos());
			Collections.sort(getAdministracionBean().getListaCatalogos(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCatalogTypes().getCatyDescription().compareToIgnoreCase(o2.getCatalogTypes().getCatyDescription());
				}
			});
			getAdministracionBean().setListaTipoCatalogo(getCatalogsTypeFacade().listaTipoCatalogos());
			Collections.sort(getAdministracionBean().getListaTipoCatalogo(), new Comparator<CatalogTypes>(){
				@Override
				public int compare(CatalogTypes o1, CatalogTypes o2) {
					return o1.getCatyDescription().compareToIgnoreCase(o2.getCatyDescription());
				}
			});
			getAdministracionBean().setListaProyectos(getProjectsFacade().buscarTodosLosProyectos());
			getAdministracionBean().setNuevaPregunta(false);
			getAplicacionController().cargarSalvaguardas();
			cargaSalvaguardas();
			rb = ResourceBundle.getBundle("resources.indicadores");
			getAdministracionBean().setListaComponentes(getComponentsFacade().listaComponentesActivos());
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "init " + ": ").append(e.getMessage()));
		}
	}
	
	public void cargaSalvaguardas(){
		getAdministracionBean().setListaSalvaguardas(new ArrayList<Safeguard>());
		for (Object[] sf : getAplicacionBean().getListaSalvaguardas() ) {
			Safeguard safe = new Safeguard();
			safe.setSafeId(Integer.valueOf( sf[0].toString()));
			safe.setSafeCode(sf[3].toString());
			getAdministracionBean().getListaSalvaguardas().add(safe);
		}
	}
	/**
	 * Habilita crear nueva pregunta
	 * @param tipo 1 Pregunta salvaguarda  2 Pregunta de genero
	 */
	public void nuevaPregunta(int tipo){
		try{
			int numeroOrden=0;
			getAdministracionBean().setDeshabilitaOrdenPregunta(true);
			getAdministracionBean().setPreguntaSeleccionada(new Questions());
			if(tipo == 1){
				getAdministracionBean().setNuevaPregunta(true);			
				getAdministracionBean().setNuevaPreguntaGenero(false);

			}else{
				getAdministracionBean().setNuevaPreguntaGenero(true);
				getAdministracionBean().setNuevaPregunta(false);
				numeroOrden= getQuestionsFacade().campoOrdenPreguntaGenero();
				if (numeroOrden==0)
					numeroOrden=1;
				else
					numeroOrden++;
				getAdministracionBean().getPreguntaSeleccionada().setQuesQuestionOrder(numeroOrden);
			}			
			getAdministracionBean().setCodigoSalvaguarda(null);
			getAdministracionBean().setCodigoTipoRespuesta(null);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "nuevaPregunta " + ": ").append(e.getMessage()));
		}
	}
	
	public void obtieneValorOrden(){
		try{
			int numeroOrden=0;
			numeroOrden= getQuestionsFacade().campoOrdenPregunta(getAdministracionBean().getCodigoSalvaguarda());
			if (numeroOrden==0)
				numeroOrden=1;
			else
				numeroOrden++;
			getAdministracionBean().getPreguntaSeleccionada().setQuesQuestionOrder(numeroOrden);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "obtieneValorOrden " + ": ").append(e.getMessage()));
		}
	}
	
	public void obtieneValorOrdenCatalogo(){
		try{
			int numeroOrden=0;
			numeroOrden= getCatalogsFacade().campoOrdenCatalogo(getAdministracionBean().getCodigoTipoCatalogo());
			if (numeroOrden==0)
				numeroOrden=1;
			else
				numeroOrden++;
			getAdministracionBean().getCatalogoSeleccionado().setCataOrder(numeroOrden);
			getAdministracionBean().getCatalogoSeleccionado().setCataNumber(numeroOrden);
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "obtieneValorOrdenCatalogo " + ": ").append(e.getMessage()));
		}
	}
	
	public void nuevoCatalogo(){
		getAdministracionBean().setNuevoCatalogo(true);
		getAdministracionBean().setCatalogoSeleccionado(new Catalogs());
		getAdministracionBean().setCodigoTipoCatalogo(null);
	}
	public void nuevoIndicador(){
		getAdministracionBean().setNuevoIndicador(true);
		getAdministracionBean().setIndicadorSeleccionado(new Indicators());
	}
	
	/**
	 * Edita una pregunta
	 * @param pregunta
	 */
	public void editarPregunta(Questions pregunta){
		getAdministracionBean().setPreguntaSeleccionada(pregunta);
		getAdministracionBean().setCodigoSalvaguarda(getAdministracionBean().getPreguntaSeleccionada().getSafeguards().getSafeId());
		getAdministracionBean().setNuevaPregunta(true);
		getAdministracionBean().setCodigoTipoRespuesta(getAdministracionBean().getPreguntaSeleccionada().getCatalogs().getCataId());
	}
	public void editarPreguntaGenero(Questions pregunta){
		getAdministracionBean().setPreguntaSeleccionada(pregunta);		
		getAdministracionBean().setNuevaPreguntaGenero(true);
		getAdministracionBean().setCodigoTipoRespuesta(getAdministracionBean().getPreguntaSeleccionada().getCatalogs().getCataId());
	}
	public void editarcatalogo(Catalogs catalogo){
		getAdministracionBean().setCatalogoSeleccionado(catalogo);
		getAdministracionBean().setCodigoTipoCatalogo(catalogo.getCatalogTypes().getCatyId());
		getAdministracionBean().setNuevoCatalogo(true);
	}
	public void editarIndicador(Indicators indicador){
		getAdministracionBean().setIndicadorSeleccionado(indicador);
		getAdministracionBean().setNuevoIndicador(true);
	}
	
	public boolean rolSocioEstrategico(String roles){
		boolean respuesta=false;
		
		if(roles.contains(",")){
			String[] cadenas=roles.split(",");
			for(int i=0;i<cadenas.length;i++){
				if(cadenas[i].equals(TipoRolesUsuarioEnum.SIS_socio_estrategico)){
					respuesta= true;
					break;
				}
			}
		}else{
			if(roles.equals(TipoRolesUsuarioEnum.SIS_socio_estrategico.toString())){
				respuesta = true;
			}
		}
		return respuesta;
	}
	
	/**
	 * Agrega o edita una pregunta
	 */
	public void agregarEditarPregunta(){
		try{
			Questions pregunta = getAdministracionBean().getPreguntaSeleccionada();
			Safeguard salvaguarda = new Safeguard();
			Catalogs catalogo = new Catalogs();
			catalogo.setCataId(getAdministracionBean().getCodigoTipoRespuesta());
			salvaguarda.setSafeId(getAdministracionBean().getCodigoSalvaguarda());
			pregunta.setSafeguards(salvaguarda);
			pregunta.setCatalogs(catalogo);
			if(pregunta.getQuesId()!=null){
				pregunta.setQuesUpdateDate(new Date());
				pregunta.setQuesUpdateUser(getLoginBean().getUser().getUserName());
			}else{
				pregunta.setQuesCreatorUser(getLoginBean().getUser().getUserName());
				pregunta.setQuesCreationDate(new Date());
				pregunta.setQuesIsGender(false);
				pregunta.setQuesImportantQuestion(false);
				pregunta.setQuesPrincipalQuestion(false);
				List<Questions> listaHijos=new ArrayList<>();				
				listaHijos.add(pregunta);
				pregunta.setQuestionsList(listaHijos);
				pregunta.setQuesStatus(true);
			}
			getQuestionsFacade().crearEditarPregunta(pregunta);
			getAdministracionBean().setNuevaPregunta(false);			
			getAdministracionBean().setListaPreguntas(getQuestionsFacade().listaPreguntasIngresadas());
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));						
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregarEditarPregunta " + ": ").append(e.getMessage()));
		}
	}
	
	public void agregaEditaIndicador(){
		try{
			Indicators indicador=getAdministracionBean().getIndicadorSeleccionado();
			indicador.setIndiCreationDate(new Date());
			indicador.setIndiCreatorUser(getLoginBean().getUser().getUserName());
			if(indicador.getIndiId()!=null){
				indicador.setIndiUpdateDate(new Date());
				indicador.setIndiUpdateUser(getLoginBean().getUser().getUserName());
			}
			getIndicatorsFacade().agregaEditaIndicador(indicador);
			getAdministracionBean().setNuevoIndicador(false);
			getAdministracionBean().setListaIndicadores(getIndicatorsFacade().listaTodosIndicadoresGenero());
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregaEditaIndicador " + ": ").append(e.getMessage()));
		}
	}
	
	public void agregarEditarPreguntaGenero(){
		try{
			Questions pregunta = getAdministracionBean().getPreguntaSeleccionada();
			
			Catalogs catalogo = new Catalogs();
			catalogo.setCataId(getAdministracionBean().getCodigoTipoRespuesta());
			
			pregunta.setSafeguards(null);
			pregunta.setCatalogs(catalogo);
			if(pregunta.getQuesId()!=null){
				pregunta.setQuesUpdateDate(new Date());
				pregunta.setQuesUpdateUser(getLoginBean().getUser().getUserName());
			}else{
				
				pregunta.setQuesCreatorUser(getLoginBean().getUser().getUserName());
				pregunta.setQuesCreationDate(new Date());
				pregunta.setQuesIsGender(true);
				pregunta.setQuesImportantQuestion(false);
				pregunta.setQuesPrincipalQuestion(false);
				List<Questions> listaHijos=new ArrayList<>();
				
				listaHijos.add(pregunta);
				pregunta.setQuestionsList(listaHijos);
				pregunta.setQuesStatus(true);
			}
			getQuestionsFacade().crearEditarPregunta(pregunta);
			getAdministracionBean().setNuevaPreguntaGenero(false);
			getAdministracionBean().setListaPreguntasGenero(getQuestionsFacade().buscaTodasPreguntasGenero());	
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO,  "",getMensajesController().getPropiedad("info.infoGrabada"));						
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregarEditarPregunta " + ": ").append(e.getMessage()));
		}
	}
	
	public void agregaEditaCatalogo(){
		try{
			Catalogs catalogo = getAdministracionBean().getCatalogoSeleccionado();
			CatalogTypes tipo= new CatalogTypes();
			tipo.setCatyId(getAdministracionBean().getCodigoTipoCatalogo());
			catalogo.setCatalogTypes(tipo);
			catalogo.setCataNumber(catalogo.getCataOrder());
			if (catalogo.getCataId() == null){
				catalogo.setCataCreatorUser(getLoginBean().getUser().getUserName());
				catalogo.setCataCreationDate(new Date());
				catalogo.setCataStatus(true);
			}else{
				catalogo.setCataUserUpdate(getLoginBean().getUser().getUserName());
				catalogo.setCatalDateUpdate(new Date());
				
			}
			getCatalogsFacade().agregaEditaCatalogo(catalogo);
			getAdministracionBean().setListaCatalogos(getCatalogsFacade().buscaTodosCatalogos());
			Collections.sort(getAdministracionBean().getListaCatalogos(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCatalogTypes().getCatyDescription().compareToIgnoreCase(o2.getCatalogTypes().getCatyDescription());
				}
			});
			cargacatalogos();
			getAdministracionBean().setNuevoCatalogo(false);
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.infoGrabada"));						
		}catch(Exception e){
			Mensaje.verMensaje(FacesMessage.SEVERITY_ERROR, "",getMensajesController().getPropiedad("error.grabar"));
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "agregaEditaCatalogo " + ": ").append(e.getMessage()));
		}
	}
	
	public void eliminarPregunta(){
		try{
			Questions pregunta = getAdministracionBean().getPreguntaSeleccionada();
			pregunta.setQuesUpdateDate(new Date());
			pregunta.setQuesUpdateUser(getLoginBean().getUser().getUserName());
			pregunta.setQuesStatus(false);
			getQuestionsFacade().edit(pregunta);
			getAdministracionBean().setListaPreguntas(getQuestionsFacade().listaPreguntasIngresadas());	
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "eliminarPregunta " + ": ").append(e.getMessage()));
		}
	}
	
	public void generarResumen(){

		List<TableResponses> listaTemp= new ArrayList<>();
		List<TableResponses> listaTempComunidades= new ArrayList<>();		
		List<String> listaComunidades=new ArrayList<>();
		List<TableResponses> listaTempProvincias= new ArrayList<>();
		try{
			List<String> listaProyectos=new ArrayList<>();
			BigDecimal totalInversion = new BigDecimal(0);
			listaTemp = getTableResponsesFacade().listaProyectosValoresSalvaguardaA();
			Map<String,BigDecimal> mapaTemp=new HashMap<String,BigDecimal>();
			for(TableResponses tr: listaTemp){
				mapaTemp.put(tr.getTareColumnOne(), tr.getTareColumnDecimalOne());
				totalInversion = totalInversion.add(tr.getTareColumnDecimalOne());
			}
			for(Entry<String,BigDecimal> proy: mapaTemp.entrySet()){
				listaProyectos.add(proy.getKey());
			}			
			DtoDatosSitioPublicoA dtoSalvaguardaA = new DtoDatosSitioPublicoA("A");
			dtoSalvaguardaA.setNumeroProyectos(listaProyectos.size());
			dtoSalvaguardaA.setTotalInversionProyectos(totalInversion);
//			dtoSalvaguardaA.setListadoProyectos(getTableResponsesFacade().listadoProyectos());
			dtoSalvaguardaA.setListadoProyectos(getTableResponsesFacade().listadoProyectosConservacion());
			////B
			DtoDatosSitioPublicoB dtoSalvaguardaB = new DtoDatosSitioPublicoB("B");
			listaTempComunidades = new ArrayList<>();
			listaTempComunidades = getTableResponsesFacade().listaMaximoHombresMujeresSalvaguardaB();
			dtoSalvaguardaB.setTotalPersonasAccesoInfo(getTableResponsesFacade().totalPersonasAccesoInfoB());
			dtoSalvaguardaB.setNumeroAlianzas(getTableResponsesFacade().numeroAlianzasB());
			dtoSalvaguardaB.setNumeroEventosRendicion(getTableResponsesFacade().numeroEventosRendicionB());
			for(TableResponses tr: listaTempComunidades){
				if(tr.getTareColumnNumberOne() != null)
					dtoSalvaguardaB.setNumeroHombresB(tr.getTareColumnNumberOne());
				else
					dtoSalvaguardaB.setNumeroHombresB(0);
				if(tr.getTareColumnNumberTwo() != null)	
					dtoSalvaguardaB.setNumeroMujeresB(tr.getTareColumnNumberTwo());
				else
					dtoSalvaguardaB.setNumeroMujeresB(0);
			}

			///// C
			DtoDatosSitioPublicoC dtoSalvaguardaC = new DtoDatosSitioPublicoC("C");
			listaTempComunidades = getTableResponsesFacade().listaComunidadesSalvaguardaB_C_G(57);
			Map<String,Integer> mapaTempC=new HashMap<String,Integer>();
			for(TableResponses tr: listaTempComunidades){
				mapaTempC.put(tr.getTareColumnOne().trim(), tr.getTareColumnNumberOne());				
			}
			for(Entry<String,Integer> proy: mapaTempC.entrySet()){
				listaComunidades.add(proy.getKey());
			}			
			dtoSalvaguardaC.setNumeroComunidadesC(listaComunidades.size());
			dtoSalvaguardaC.setNumeroPracticas(getTableResponsesFacade().listaSaberesAncestralesSalvaguardaC());
			dtoSalvaguardaC.setNumeroOrganizacionIndigenaC(getTableResponsesFacade().numeroOrganizacionIndigenaC());
			dtoSalvaguardaC.setNumeroEventosCPLIC(getTableResponsesFacade().numeroEventosCPLIC());

			///// D
			DtoDatosSitioPublicoD dtoSalvaguardaD = new DtoDatosSitioPublicoD("D");

//			dtoSalvaguardaD.setTotalEventosHombres(getMeetingsFacade().listaEventosFortalecimientoHombres());
//			dtoSalvaguardaD.setTotalEventosMujeres(getMeetingsFacade().listaEventosFortalecimientoMujeres());
			dtoSalvaguardaD.setTotalEspaciosD(getTableResponsesFacade().numeroEspaciosCapacitacionD());
			dtoSalvaguardaD.setTotalPersonasParticipacionD(getTableResponsesFacade().totalPersonasParticipacionD());
			dtoSalvaguardaD.setNumeroOrganizacionesLocalesD(getTableResponsesFacade().numeroOrganizacionesLocalesD());

			// E
			DtoDatosSitioPublicoE dtoSalvaguardaE = new DtoDatosSitioPublicoE("E");
			listaTempProvincias = getTableResponsesFacade().listaFomentoGestionComunitariaE();

//			dtoSalvaguardaE.setNumeroFomentoGestionComunitaria(listaTempProvincias.size());
//			dtoSalvaguardaE.setTotalHectareasCobertura(getTableResponsesFacade().totalHectareasCoberturaE());
			dtoSalvaguardaE.setTotalHectareasConservadoE(getTableResponsesFacade().totalHectareasConservacionE());
			dtoSalvaguardaE.setTotalHectareasRestauradoE(getTableResponsesFacade().totalHectareasRestauracionE());
			dtoSalvaguardaE.setNumeroInvestigacionesE(getTableResponsesFacade().numeroInvestigacionesE());
			// F
			DtoDatosSitioPublicoF dtoSalvaguardaF = new DtoDatosSitioPublicoF("F");			
			dtoSalvaguardaF.setTotalAccionesReversion(getTableResponsesFacade().numeroAccionesEvitarRiesgos_F());
			dtoSalvaguardaF.setListadoMedidasTomadas(getTableResponsesFacade().listaMedidasTomadas_F());
			dtoSalvaguardaF.setNumeroInvestigacionesF(getTableResponsesFacade().numeroInvestigacionesF());
			dtoSalvaguardaF.setNumeroAccionesFortalecimientoF(getTableResponsesFacade().numeroAccionesFortalecimientoF());
			// G
			DtoDatosSitioPublicoG dtoSalvaguardaG = new DtoDatosSitioPublicoG("G");
			listaTempComunidades = getTableResponsesFacade().listaComunidadesSalvaguardaB_C_G(136);
			Map<String,Integer> mapaTempG=new HashMap<String,Integer>();
			for(TableResponses tr: listaTempComunidades){
				mapaTempG.put(tr.getTareColumnOne().trim(), tr.getTareColumnNumberOne());				
			}
			for(Entry<String,Integer> proy: mapaTempG.entrySet()){
				listaComunidades.add(proy.getKey());
			}

			dtoSalvaguardaG.setNumeroAcciones(getTableResponsesFacade().listaAccionesGeneradasSalvaguardaG());
			dtoSalvaguardaG.setNumeroComunidades(listaComunidades.size());
			dtoSalvaguardaG.setNumeroActividadesControl(getTableResponsesFacade().numeroActividadesControlG());
			// GENERO
			DtoDatosSitioPublicoGenero dtoGenero = new DtoDatosSitioPublicoGenero("GENERO");
			List<DtoGenero> listTempTemas = getAvanceExecutionFacade().listaTemasGenero();
			List<String> listTempAcciones = getAvanceExecutionFacade().listadoAccionesGenero();
			dtoGenero.setTotalPresupuesto(getAvanceExecutionFacade().presupuestoGenero());
			int totalTemas = 0;			
//			if(listTempTemas!=null && listTempTemas.size()>0){
//				dtoGenero.setListaTemasGenero(listTempTemas);
//				for (DtoGenero genero : listTempTemas) {
//					totalTemas = totalTemas + genero.getNumero();
//				}
//				dtoGenero.setTotalTemasAplicados(totalTemas);
//			}else{
//				dtoGenero.setTotalTemasAplicados(0);
//				dtoGenero.setListaTemasGenero(null);
//			}

			if(listTempAcciones!=null && listTempAcciones.size()>0){
				dtoGenero.setListaAccionesGenero(listTempAcciones);
				dtoGenero.setTotalAccionesImplementadas(listTempAcciones.size());
			}else{
				dtoGenero.setListaAccionesGenero(null);
				dtoGenero.setTotalAccionesImplementadas(0);
			}
			dtoGenero.setListaLineasAccionProyecto(procesaLineasAccionProyectoGenero(getProjectsGenderFacade().listaLineaAccionProyecto()));
			dtoGenero.setListaAporteProyectoGenero(procesaAporteProyectoGenero(getProjectsGenderFacade().listaAporteProyectoGenero()));
			
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String archivoJSON = new StringBuilder().append(ctx.getRealPath("")).append(File.separator).append("reportes").append(File.separator).append("archivo").append(".json").toString();
			List<String> lista = Arrays.asList(dtoSalvaguardaA.toJson(), dtoSalvaguardaB.toJson(),dtoSalvaguardaC.toJson(),dtoSalvaguardaD.toJson(), dtoSalvaguardaE.toJson() , dtoSalvaguardaF.toJson() , dtoSalvaguardaG.toJson(),dtoGenero.toJson());
			try (FileWriter fileWriter = new FileWriter(archivoJSON)) {
				Jsoner.serialize(lista, fileWriter);
			}
			Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.generaArchivo"));
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "generarResumen " + ": ").append(e.getMessage()));
		}
	}

	public void generarArchivosSalvaguardas(){

		GenerarBDSalvaguardaA.generaArchivoSalvaguardaA(getTableResponsesFacade(), getQuestionsFacade());
		GeneraBDSalvaguardaB.generaArchivoSalvaguardaB(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDSalvaguardaC.generaArchivoSalvaguardaC(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDSalvaguardaD.generaArchivoSalvaguardaD(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDSalvaguardaE.generaArchivoSalvaguardaE(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDSalvaguardaF.generaArchivoSalvaguardaF(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDSalvaguardaG.generaArchivoSalvaguardaG(getTableResponsesFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		GenerarBDGenero.generaArchivoGenero(getTableResponsesFacade(),getGeneroFacade(), getQuestionsFacade(),getAdministracionBean().getListaCatalogos(),getAplicacionBean().getListaProvincias(),getAplicacionBean().getListaTodosCantones(),getAplicacionBean().getListaTodasParroquias(),getAdministracionBean().getListaComponentes());
		Mensaje.verMensaje(FacesMessage.SEVERITY_INFO, "",getMensajesController().getPropiedad("info.generaArchivo"));
	}
	
	public void cargacatalogos(){
		try{
    		getAplicacionBean().setListaPueblosNacionalidades(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.PUEBLOSNACIONALIDADES.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaPueblosNacionalidades(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		
    		
    		getAplicacionBean().setListaAutoIdentificacion(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.AUTOIDENTIFICACION.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaAutoIdentificacion(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaPublico(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.PUBLICO.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaPublico(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaMetodo(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.METODO.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaMetodo(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaCatalogoRiesgo(new ArrayList<Catalogs>());
    		getAplicacionBean().setListaCatalogoRiesgo(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.RIESGO.getCodigo()));
    		getAplicacionBean().setListaRecursos(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.RECURSOS.getCodigo()));
//    		getAplicacionBean().setListaRecursos(getAplicacionBean().getListaRecursos().stream().sorted((r1,r2)->r1.getCataText1().compareTo(r2.getCataText1())).collect(Collectors.toList()));
    		Collections.sort(getAplicacionBean().getListaRecursos(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaPeriodicidad(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.PERIODICIDAD.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaPeriodicidad(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaSistemas(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.SISTEMAS.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaSistemas(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaAccion(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.ACCION.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaAccion(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaSalvaguardas(getSafeguardsFacade().listarSalvaguardas());
    		getAplicacionBean().setListaAlternativaEconomica(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.ALTERNATIVAECONOMICA.getCodigo()));
    		Collections.sort(getAplicacionBean().getListaAlternativaEconomica(), new Comparator<Catalogs>(){
				@Override
				public int compare(Catalogs o1, Catalogs o2) {
					return o1.getCataText1().compareToIgnoreCase(o2.getCataText1());
				}
			});
    		getAplicacionBean().setListaMonitoreoRemoto(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.MONITOREOREMOTO.getCodigo()));
    		getAplicacionBean().setListaMonitoreoInSitu(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.MONITOREOINSITU.getCodigo()));
    		getAplicacionBean().setListaLineaAccion(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.LINEAACCION.getCodigo()));
    		getAplicacionBean().setListaControlVigilancia(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.CONTROLVIGILANCIA.getCodigo()));
    		getAplicacionBean().setListaTipoOrganizacion(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.TIPOORGANIZACION.getCodigo()));
    		getAplicacionBean().setListaTipoIncentivo(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.TIPOINCENTIVO.getCodigo()));
    		getAplicacionBean().setListaInstitucionAcompania(getCatalogsFacade().buscaCatalogosPorTipo(TipoCatalogoEnum.TIPOINSTITUCION.getCodigo()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<DtoGenero> procesaLineasAccionProyectoGenero(List<Object[]> lista){
		List<DtoGenero> listaTemp = new ArrayList<>();
		List<DtoGenero> listaFinal = new ArrayList<>();
		if(lista.size()>0){
			for(Object obj:lista){
				Object[] dataObj = (Object[]) obj;
				DtoGenero genero = new DtoGenero();
				genero.setLineaAccion(dataObj[0].toString());
				if(genero.getLineaAccion().equals("Otro"))
					genero.setLineaAccion(dataObj[1].toString());
				genero.setProyecto(dataObj[2].toString());
				listaTemp.add(genero);
			}
			String lineaAccion= listaTemp.get(0).lineaAccion;
			StringBuilder proyecto= new StringBuilder();
			DtoGenero genero = new DtoGenero();
			for (DtoGenero dtoGenero : listaTemp) {
				if(dtoGenero.getLineaAccion().equals(lineaAccion)){
					genero.setLineaAccion(dtoGenero.getLineaAccion());
					proyecto.append(dtoGenero.getProyecto()).append(",");
				}else{
					genero.setProyecto(proyecto.toString());
					listaFinal.add(genero);
					genero=new DtoGenero();
					lineaAccion = dtoGenero.getLineaAccion();
					genero.setLineaAccion(lineaAccion);
					proyecto = new StringBuilder();
					proyecto.append(dtoGenero.getProyecto()).append(",");
				}
				if(listaTemp.size()==1){
					genero.setProyecto(proyecto.toString());
					listaFinal.add(genero);
				}
					
			}
			return listaFinal;
		}else
			return null;
	}
	
	public  List<DtoGenero> procesaAporteProyectoGenero(List<Object[]> lista){
		List<DtoGenero> listaTemp = new ArrayList<>();
		List<DtoGenero> listaFinal = new ArrayList<>();
		if(lista.size()>0){
			for(Object obj:lista){
				Object[] dataObj = (Object[]) obj;
				DtoGenero genero = new DtoGenero();
				genero.setProyecto(dataObj[0].toString());				
				genero.setPresupuesto((BigDecimal)dataObj[1]);
				listaTemp.add(genero);
			}
			String proyecto= listaTemp.get(0).proyecto;
			BigDecimal monto= BigDecimal.ZERO;
			DtoGenero genero = new DtoGenero();
			int contador=0;
			for (DtoGenero dtoGenero : listaTemp) {
				if(dtoGenero.getProyecto().equals(proyecto)){
					genero.setProyecto(dtoGenero.getProyecto());
					monto = monto.add(dtoGenero.getPresupuesto());
				}else{
					genero.setPresupuesto(monto);
					listaFinal.add(genero);
					genero=new DtoGenero();
					proyecto = dtoGenero.getProyecto();
					genero.setProyecto(proyecto);
					monto = BigDecimal.ZERO;
					monto = monto.add( dtoGenero.getPresupuesto());
				}
				contador++;	
				if(listaTemp.size()==1 || listaTemp.size() == contador){
					genero.setPresupuesto(monto);
					listaFinal.add(genero);
				}
				
			}
			return listaFinal;
		}else
			return null;
		
	}
}

