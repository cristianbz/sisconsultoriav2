package ec.gob.ambiente.sigma.web.controladores;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.CmisObject;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.CatalogsType;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.facades.ActionPlanFacade;
import ec.gob.ambiente.sigma.ejb.facades.CatalogFacade;
import ec.gob.ambiente.sigma.ejb.facades.CatalogTypeFacade;
import ec.gob.ambiente.sigma.ejb.facades.DocumentSigmaFacade;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourceFacade;
import ec.gob.ambiente.sigma.ejb.facades.GeoFacade;
import ec.gob.ambiente.sigma.ejb.facades.GeographicalLocationsFacade;
import ec.gob.ambiente.sigma.ejb.facades.SafeguardFacade;
import ec.gob.ambiente.sigma.web.utils.ProjectGeoType;

@Named("app")
@ApplicationScoped
public class AplicacionControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(AplicacionControlador.class.getName());

	private ResourceBundle bundle;

	@EJB
	private CatalogTypeFacade catalogoTypeFacade;
	@EJB
	private CatalogFacade catalogoFacade;
	@EJB
	private DocumentSigmaFacade documentSigmaFacade;
	@EJB
	private GeographicalLocationsFacade geolocFacade;
	@EJB
	private ActionPlanFacade planAccionFacade;
	@EJB
	private FundingSourceFacade fuenteFinancFacade;
	@EJB
	private SafeguardFacade salvaguardasFacade;
	@EJB
	private GeoFacade geoFacade;

	private String var1;
	
	private List<CatalogsType> lstCatTypes;
	private List<Catalog> lstCatTipoComp;
	private List<Catalog> lstCatMecJurid;
	private List<Catalog> lstCatTipoSocImpl;
	private List<Catalog> lstCatReqSoc1;
	private List<Catalog> lstCatReqSoc2;
	private List<Catalog> lstCatDecTipoId;
	private List<Catalog> lstCatDecEstCivil;
	private List<Catalog> lstCatDecParrafos;
	private List<Catalog> lstCatOpFinTipoFin;
	private List<Catalog> lstCatOpFinTipoFond;
	private List<Catalog> lstCatOpFinCat;
	private List<Catalog> lstCatOpFinSector;
	private List<Catalog> lstCatTipFueFin;
	private List<Catalog> lstCatAdministrador;
	private List<Catalog> lstCatCatObj;
	private List<Catalog> lstCatSubCatObj;
	private List<Catalog> lstCatTipoCobeneficio;
	private List<Catalog> lstCatTipoRiesgo;

	private List<Catalog> lstCatEnfGenero1;
	private List<Catalog> lstCatEnfGenero2;
	private List<Catalog> lstCatEnfGenero3;
	private Integer codCatEnfGenero1Otro;
	private Integer codCatEnfGenero2Otro;
	private Integer codCatEnfGenero3Otro;
	
	private List<Catalog> lstCatTipoDocProyecto;
	private Integer codOtroTipoDoc;
	
	private List<Catalog> lstCatTiposPeriodosReporte;
	private Integer codPrimerSemestre;
	private Integer codUltimoSemestre;
	private List<Catalog> lstCatCostosGastos;
	private List<Catalog> lstCatPregGestCon;
	private List<Catalog> lstCatMecAccInf;
	private List<Catalog> lstCatMecInvAct;
	private List<Catalog> lstCatTiposAct;
	private List<Catalog> lstCatPregCom;
	private List<Catalog> lstCatTiposProblemas;
	private List<Catalog> lstCatEveOrigenEtnico;
	private List<Catalog> lstCatEveNacionalidadesInd;
	
	private List<Catalog> lstCatCapasGeoBase;
	
	private List<Catalog> lstCatEjeTematicoShapeNivel1;
	private List<Catalog> lstCatEjeTematicoShapeTodo;
	
	private List<Catalog> lstCatCamposObjGeoREDD;
	
	
	
	private List<Object[]> lstGeoLocProvincias;
	private List<Object[]> lstGeoLocCantones;
	private List<Object[]> lstGeoLocParroquias;
	private Integer codCatCompEstrategico;
	private Integer codCatTipoSocComuIndig;
	
	private List<ActionPlan> lstPlanesAccion;
	
	private List<SelectItem> lstMeses;
	private List<SelectItem> lstAnios;
	
	private List<FundingSource> lstFuentesFin;
	
	//este objeto es temporal, hasta definir si las salvaguardas pertenecen a un PA
	private List<Safeguard> lstSalvaguardasTemp;
	
	private String pathUploads;
	private String os;
	//max size in bytes
	private String maxSizeZipShape;
	
	
	private List<List<Object[]>> listaGeoCapasBase;
	private List<Object[]> configuracionesSigma;
	private String sigmaWebServicesUsername;
	private String sigmaWebServicesPassword;
	private String urlWebServicesBusSnap;

	public AplicacionControlador() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			var1 = "ok";
			System.out.println("app iniciada");
			cargarCatalogos();
			cargarGeografia();
			//cargarGeoCapasBase();
			cargarConfiguracionesSigma();
			recargarPlanesAccion();
			recargarFuentesFinanciamiento();
			recargarSalvaguardasTemp();
			cargarUtilitarios();
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
			cargarSistemaOperativo();
			//temp
			maxSizeZipShape="10000000";
			cargarParametrosCorreo();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void cargarSistemaOperativo(){
		String osp = System.getProperty("os.name");
		if(osp.toUpperCase().contains("WINDOWS")){
			os="WINDOWS";
		}else{
			os="LINUX";
		}
	}

	private void cargarCatalogos() {
		try {
			
			lstCatTypes=new ArrayList<>();
			lstCatTypes=catalogoTypeFacade.listarTiposCatalogo();

			List<String> lstNemonicos = new ArrayList<>();
			lstNemonicos.add("TIPO_COMPONENTES");
			lstCatTipoComp = new ArrayList<>();
			lstNemonicos.add("MECANISMO_JURIDICO");
			lstCatMecJurid = new ArrayList<>();
			lstNemonicos.add("TIPO_SOCIO_IMPLEMENTADOR");
			lstCatTipoSocImpl = new ArrayList<>();
			lstNemonicos.add("REQUISITO_SOCIO_1");
			lstCatReqSoc1 = new ArrayList<>();
			lstNemonicos.add("REQUISITO_SOCIO_2");
			lstCatReqSoc2 = new ArrayList<>();
			lstNemonicos.add("DEC_TIPO_IDENTIFICACION");
			lstCatDecTipoId=new ArrayList<>();
			lstNemonicos.add("DEC_ESTADO_CIVIL");
			lstCatDecEstCivil=new ArrayList<>();
			lstNemonicos.add("PARRAFOS_DECL_JURAM");
			lstCatDecParrafos=new ArrayList<>();
			lstNemonicos.add("OPC_FIN_TIPO_FIN");
			lstCatOpFinTipoFin=new ArrayList<>();
			lstNemonicos.add("OPC_FIN_TIPO_FON");
			lstCatOpFinTipoFond=new ArrayList<>();
			lstNemonicos.add("OPC_FIN_CATEG");
			lstCatOpFinCat=new ArrayList<>();
			lstNemonicos.add("OPC_FIN_SECTOR_ENCC");
			lstCatOpFinSector=new ArrayList<>();
			lstNemonicos.add("OPC_FIN_TIPO_FUE");
			lstCatTipFueFin=new ArrayList<>();
			lstNemonicos.add("CAT_ADM_FINANC");
			lstCatAdministrador=new ArrayList<>();
			
			lstNemonicos.add("CATEG_OBJ_ESP");
			lstCatCatObj=new ArrayList<>();
			lstNemonicos.add("SUBCAT_OBJ_ESP");
			lstCatSubCatObj=new ArrayList<>();
			
			lstNemonicos.add("TIPO_COBENEFICIO");
			lstCatTipoCobeneficio=new ArrayList<>();
			lstNemonicos.add("TIPO_RIESGO");
			lstCatTipoRiesgo=new ArrayList<>();
			
			lstNemonicos.add("LINEAS_ENF_GENERO_1");
			lstCatEnfGenero1=new ArrayList<>();
			lstNemonicos.add("LINEAS_ENF_GENERO_2");
			lstCatEnfGenero2=new ArrayList<>();
			lstNemonicos.add("LINEAS_ENF_GENERO_3");
			lstCatEnfGenero3=new ArrayList<>();
			
			lstNemonicos.add("TIPO_DOC_PROYECTO");
			lstCatTipoDocProyecto=new ArrayList<>();
			lstNemonicos.add("CAT_TIPO_PERIODO_REPORTE");
			lstCatTiposPeriodosReporte=new ArrayList<>();
			lstNemonicos.add("CAT_COSTOS_GASTOS");
			lstCatCostosGastos=new ArrayList<>();
			lstNemonicos.add("CAT_PREG_GEST_CON");
			lstCatPregGestCon=new ArrayList<>();
			lstNemonicos.add("CAT_MEC_ACC_INF_PROY");
			lstCatMecAccInf=new ArrayList<>();
			lstNemonicos.add("CAT_MEC_INV_ACT_FUNC");
			lstCatMecInvAct=new ArrayList<>();
			lstNemonicos.add("CAT_TIPO_ACTORES");
			lstCatTiposAct=new ArrayList<>();
			lstNemonicos.add("CAT_PREG_COM");
			lstCatPregCom=new ArrayList<>();
			lstNemonicos.add("CAT_TIPO_PROBLEMA");
			lstCatTiposProblemas=new ArrayList<>();
			lstNemonicos.add("CAT_EVE_ORIGEN_ETNICO");
			lstCatEveOrigenEtnico=new ArrayList<>();
			lstNemonicos.add("CAT_EVE_NACIONALIDADES_IND");
			lstCatEveNacionalidadesInd=new ArrayList<>();
			
			lstNemonicos.add("CAT_CAPAS_GEO_BASE");
			lstCatCapasGeoBase=new ArrayList<>();
			
			lstNemonicos.add("CAT_EJES_TEMATICOS_GEO");
			lstCatEjeTematicoShapeNivel1=new ArrayList<>();
			lstCatEjeTematicoShapeTodo=new ArrayList<>();
			
			lstNemonicos.add("CAT_OBJ_GEO_REDD");
			lstCatCamposObjGeoREDD=new ArrayList<>();
			
			
			
			List<Catalog> lstCatalogos = catalogoFacade.listarCatalogosPorNemonicosTipos(lstNemonicos);

			for (Catalog c : lstCatalogos) {

				if (c.getCatyId().getCatyMnemonic().equals("TIPO_COMPONENTES")) {
					lstCatTipoComp.add(c);
					if (c.getCataNumber() == 1) {
						codCatCompEstrategico = c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("MECANISMO_JURIDICO")) {
					lstCatMecJurid.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("TIPO_SOCIO_IMPLEMENTADOR")) {
					lstCatTipoSocImpl.add(c);
					if (c.getCataNumber() == 8) {
						codCatTipoSocComuIndig = c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("REQUISITO_SOCIO_1")) {
					lstCatReqSoc1.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("REQUISITO_SOCIO_2")) {
					lstCatReqSoc2.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("DEC_TIPO_IDENTIFICACION")) {
					lstCatDecTipoId.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("DEC_ESTADO_CIVIL")) {
					lstCatDecEstCivil.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("PARRAFOS_DECL_JURAM")) {
					lstCatDecParrafos.add(c);
				}
				
				if (c.getCatyId().getCatyMnemonic().equals("OPC_FIN_TIPO_FIN")) {
					lstCatOpFinTipoFin.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("OPC_FIN_TIPO_FON")) {
					lstCatOpFinTipoFond.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("OPC_FIN_CATEG")) {
					lstCatOpFinCat.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("OPC_FIN_SECTOR_ENCC")) {
					lstCatOpFinSector.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("OPC_FIN_TIPO_FUE")) {
					lstCatTipFueFin.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_ADM_FINANC")) {
					lstCatAdministrador.add(c);
				}
				
				if (c.getCatyId().getCatyMnemonic().equals("CATEG_OBJ_ESP")) {
					lstCatCatObj.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("SUBCAT_OBJ_ESP")) {
					lstCatSubCatObj.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("TIPO_COBENEFICIO")) {
					lstCatTipoCobeneficio.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("TIPO_RIESGO")) {
					lstCatTipoRiesgo.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_1")) {
					lstCatEnfGenero1.add(c);
					if(c.getCataNumber()==7){
						codCatEnfGenero1Otro=c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_2")) {
					lstCatEnfGenero2.add(c);
					if(c.getCataNumber()==4){
						codCatEnfGenero2Otro=c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("LINEAS_ENF_GENERO_3")) {
					lstCatEnfGenero3.add(c);
					if(c.getCataNumber()==5){
						codCatEnfGenero3Otro=c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("TIPO_DOC_PROYECTO")) {
					lstCatTipoDocProyecto.add(c);
					if (c.getCataNumber() == 5) {
						codOtroTipoDoc= c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_TIPO_PERIODO_REPORTE")) {
					lstCatTiposPeriodosReporte.add(c);
					if (c.getCataNumber() == 2) {
						codUltimoSemestre= c.getCataId();
					}else{
						codPrimerSemestre= c.getCataId();
					}
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_COSTOS_GASTOS")) {
					lstCatCostosGastos.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_PREG_GEST_CON")) {
					lstCatPregGestCon.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_MEC_ACC_INF_PROY")) {
					lstCatMecAccInf.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_MEC_INV_ACT_FUNC")) {
					lstCatMecInvAct.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_TIPO_ACTORES")) {
					lstCatTiposAct.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_PREG_COM")) {
					lstCatPregCom.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_TIPO_PROBLEMA")) {
					lstCatTiposProblemas.add(c);
				}
				
				if (c.getCatyId().getCatyMnemonic().equals("CAT_EVE_ORIGEN_ETNICO")) {
					lstCatEveOrigenEtnico.add(c);
				}
				if (c.getCatyId().getCatyMnemonic().equals("CAT_EVE_NACIONALIDADES_IND")) {
					lstCatEveNacionalidadesInd.add(c);
				}
				
				if(c.getCatyId().getCatyMnemonic().equals("CAT_CAPAS_GEO_BASE")){
					lstCatCapasGeoBase.add(c);
				}
				if(c.getCatyId().getCatyMnemonic().equals("CAT_EJES_TEMATICOS_GEO")){
					lstCatEjeTematicoShapeTodo.add(c);
					if(c.getCataLevel()!=null&&c.getCataLevel()==1){
						lstCatEjeTematicoShapeNivel1.add(c);
					}
					
				}
				
				if(c.getCatyId().getCatyMnemonic().equals("CAT_OBJ_GEO_REDD")){
					lstCatCamposObjGeoREDD.add(c);
				}

			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void cargarGeografia(){
		try{
			lstGeoLocProvincias=geolocFacade.listarProvincias();
			lstGeoLocCantones=geolocFacade.listarCantones();
			lstGeoLocParroquias=geolocFacade.listarParroquias();
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	/*private void cargarGeoCapasBase(){
		try{
			listaGeoCapasBase=new ArrayList<>();
			for(Catalog c:getLstCatCapasGeoBase()){
				List<Object[]> lst2 = geoFacade.listarDescGeomAsGeoJson(c.getCataText2().split(";")[0],c.getCataText2().split(";")[1]);
				listaGeoCapasBase.add(lst2);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}*/
	
	private void cargarConfiguracionesSigma(){
		try{
			configuracionesSigma=new ArrayList<>();
			configuracionesSigma=catalogoFacade.consultaNativa("SELECT coen_key, coen_value FROM suia_configuration.config_entries where coen_active=true and coen_key like 'sigma%' union SELECT coen_key, coen_value FROM suia_configuration.config_entries where coen_active=true and coen_key='app.service.bus.snap'");
			
			for(Object[] ob:configuracionesSigma){
				if(String.valueOf(ob[0]).equals("sigma.web.services.username")){
					sigmaWebServicesUsername=String.valueOf(ob[1]);
				}
				if(String.valueOf(ob[0]).equals("sigma.web.services.password")){
					sigmaWebServicesPassword=String.valueOf(ob[1]);
				}
				if(String.valueOf(ob[0]).equals("sigma.web.uploadshape.path")){
					pathUploads=String.valueOf(ob[1]);
				}
				if(String.valueOf(ob[0]).equals("app.service.bus.snap")){
					urlWebServicesBusSnap=String.valueOf(ob[1]);
				}
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public ec.gob.ambiente.sigma.ejb.entidades.Document almacenarYObtenerDocSigma(UploadedFile archivoSubido) {
		ec.gob.ambiente.sigma.ejb.entidades.Document docSigma = new ec.gob.ambiente.sigma.ejb.entidades.Document();
		try {
			byte[] contentUploadedFile=archivoSubido.getContent();
			SessionFactory factory = SessionFactoryImpl.newInstance();
			Map<String, String> parameters = new HashMap<String, String>();
			// user credentials
			parameters.put(SessionParameter.USER, "sgmya");
			parameters.put(SessionParameter.PASSWORD, "sgmya");
			// connection settings
			parameters.put(SessionParameter.ATOMPUB_URL,
					"http://desarrollo-alfresco.ambiente.gob.ec:8080/alfresco/cmisatom");
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			// parameters.put(SessionParameter.REPOSITORY_ID, "sgmya");
			// create session
			Session session = factory.getRepositories(parameters).get(0).createSession();
			//System.out.println(session.getRepositoryInfo());
			Folder root = session.getRootFolder();

			ItemIterable<CmisObject> children = root.getChildren();

			for (CmisObject o : children) {
				// System.out.println(o.getName());
				if (o.getName().equals("sgmya")) {
					root = (Folder) o;
					break;
				}
			}
			// properties
			// Map<String, Object> properties = new HashMap<String,
			// Object>();
			// properties.put(PropertyIds.OBJECT_TYPE_ID,
			// "cmis:folder");
			// properties.put(PropertyIds.NAME, "MyAlfrescoFolder"); //
			// folder name
			// create the folder
			// Folder parent = root.createFolder(properties);

			String nameAlfresco = obtenerMarcaTiempoEnTexto()+"~"+archivoSubido.getFileName();
			String name = archivoSubido.getFileName();
			// properties
			Map<String, Object> properties2 = new HashMap<String, Object>();
			properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
			properties2.put(PropertyIds.NAME, nameAlfresco);
			// content
			// byte[] content =
			// fileLegalFrameworkDocCurrent.getContent();
			InputStream stream = new ByteArrayInputStream(contentUploadedFile);
			ContentStream contentStream = new ContentStreamImpl(nameAlfresco, BigInteger.valueOf(contentUploadedFile.length),
					archivoSubido.getContentType(), stream);
			// create a major version
			Document newDoc = root.createDocument(properties2, contentStream, VersioningState.MAJOR);

			docSigma.setDocuAlfrescoId(newDoc.getId());
			docSigma.setDocuName(name);
			docSigma.setDocuMime(archivoSubido.getContentType());
			docSigma.setDocuAddressAlfresco("ok");
			docSigma.setDocuDateGenerationDocument(new Date());
			docSigma.setDocuCodePublic("ok");
			docSigma.setDocuDescription("ok");
			
			docSigma = documentSigmaFacade.crear("temp", docSigma);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return docSigma;
	}
	
	public StreamedContent obtenerStreamedContentDeDocSigma(ec.gob.ambiente.sigma.ejb.entidades.Document docSigma) {
		// String fullPath= destinationFolder + fileName;
		// Document newDocument = (Document) getSession(serverUrl, username,
		// password).getObject(documentID);
		try {

			SessionFactory factory = SessionFactoryImpl.newInstance();
			Map<String, String> parameters = new HashMap<String, String>();
			// user credentials
			parameters.put(SessionParameter.USER, "sgmya");
			parameters.put(SessionParameter.PASSWORD, "sgmya");
			// connection settings
			parameters.put(SessionParameter.ATOMPUB_URL,
					"http://desarrollo-alfresco.ambiente.gob.ec:8080/alfresco/cmisatom");
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			// parameters.put(SessionParameter.REPOSITORY_ID, "sgmya");
			// create session
			Session session = factory.getRepositories(parameters).get(0).createSession();
			Document newDocument = (Document) session.getObject(docSigma.getDocuAlfrescoId());
			//System.out.println(newDocument.getId());

			ContentStream cs = newDocument.getContentStream();
			return new DefaultStreamedContent(cs.getStream(), docSigma.getDocuMime(), docSigma.getDocuName());
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<Object[]> listarCantonesPorIdProvincia(Integer idProv){
        List<Object[]> lst=new ArrayList<>();
        try{
        	for(Object[] o:lstGeoLocCantones){
        		if((Integer.valueOf(String.valueOf(o[3]))).equals(idProv)){
        			lst.add(o);
        		}
        	}
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return lst;
    }
	
	public List<Object[]> listarParroquiasPorIdCanton(Integer idCanton){
        List<Object[]> lst=new ArrayList<>();
        try{
        	for(Object[] o:lstGeoLocParroquias){
        		if((Integer.valueOf(String.valueOf(o[3]))).equals(idCanton)){
        			lst.add(o);
        		}
        	}
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return lst;
    }
	
	public String obtenerTextoProvPorId(Integer idProv){
		String s="";
		try{
			for(Object[] o:lstGeoLocProvincias){
				if(idProv.equals(Integer.valueOf(String.valueOf(o[1])))){
					s=String.valueOf(o[0]);
					break;
				}
			}
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return s;
	}
	
	public String obtenerTextoMesPorMonth(Integer codMonth){
		String s="";
		try{
			if(codMonth==0){
				s="Enero";
			}else if(codMonth==1){
				s="Febrero";
			}else if(codMonth==2){
				s="Marzo";
			}else if(codMonth==3){
				s="Abril";
			}else if(codMonth==4){
				s="Mayo";
			}else if(codMonth==5){
				s="Junio";
			}else if(codMonth==6){
				s="Julio";
			}else if(codMonth==7){
				s="Agosto";
			}else if(codMonth==8){
				s="Septiembre";
			}else if(codMonth==9){
				s="Octubre";
			}else if(codMonth==10){
				s="Noviembre";
			}else if(codMonth==11){
				s="Diciembre";
			}
			
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return s;
	}
	
	public void recargarPlanesAccion(){
		try{
			lstPlanesAccion=new ArrayList<>();
			lstPlanesAccion=planAccionFacade.listarPlanes();
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
	}
	
	private void cargarUtilitarios(){
		try{
			lstMeses=new ArrayList<>();
			lstMeses.add(new SelectItem("01", "Enero"));
			lstMeses.add(new SelectItem("02", "Febrero"));
			lstMeses.add(new SelectItem("03", "Marzo"));
			lstMeses.add(new SelectItem("04", "Abril"));
			lstMeses.add(new SelectItem("05", "Mayo"));
			lstMeses.add(new SelectItem("06", "Junio"));
			lstMeses.add(new SelectItem("07", "Julio"));
			lstMeses.add(new SelectItem("08", "Agosto"));
			lstMeses.add(new SelectItem("09", "Septiembre"));
			lstMeses.add(new SelectItem("10", "Octubre"));
			lstMeses.add(new SelectItem("11", "Noviembre"));
			lstMeses.add(new SelectItem("12", "Diciembre"));
			
			lstAnios=new ArrayList<>();
			for(int i=2016;i<=2030;i++){
				lstAnios.add(new SelectItem(""+i,""+i));
			}
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
	}
	
	public List<Catalog> listarSubCatObjPorCat(Integer idPadre){
		List<Catalog> lst=new ArrayList<>();
		for(Catalog c: lstCatSubCatObj){
			if(c.getCataParentId()!=null&&c.getCataParentId().getCataId()==idPadre){
				lst.add(c);
			}
		}
		return lst;
	}
	
	public List<Catalog> listarCatalogoHijoPorIdPadre(List<Catalog> catalogoHijoTotal, int idPadre){
		List<Catalog> lst=new ArrayList<>();
		for(Catalog c: catalogoHijoTotal){
			if(c.getCataParentId()!=null&&c.getCataParentId().getCataId()==idPadre){
				lst.add(c);
			}
		}
		return lst;
	}
	
	public List<Catalog> listarSecCatPorCategoriaFinanciamiento(List<Integer> lstIdsCatFin){
		List<Catalog> lst=new ArrayList<>();
		
		for(Integer i:lstIdsCatFin){
			for(Catalog c: lstCatOpFinCat){
				if(c.getCataId()==i){
					for(Catalog s:lstCatOpFinSector){
						if(s.getCataNumber()==c.getCataNumber()){
							lst.add(s);
						}
					}
				}
			}
		}
		
		return lst;
	}
	
	public List<Catalog> listarCatEjeTematicoShapeNivel2(Integer cataIdNivel1){
		List<Catalog>  lst=new ArrayList<>();
		try{
			for(Catalog c:lstCatEjeTematicoShapeTodo){
				if(c.getCataParentId()!=null&&c.getCataParentId().getCataId().equals(cataIdNivel1)){
					lst.add(c);
				}
			}
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return lst;
	}
	
	public void recargarFuentesFinanciamiento(){
		try{
			lstFuentesFin=new ArrayList<>();
			lstFuentesFin=fuenteFinancFacade.listarFuentesDeFinanciamiento();
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
	}
	
	public void recargarSalvaguardasTemp(){
		try{
			//esta programacion es temporal, 
			//hasta definir si las salvaguardas pertenecen a un PA
			// por el momento se crea una lista de Salvaguardas a partir del PA vigente
			int paId=0;
			for(ActionPlan ap:lstPlanesAccion){
				if(ap.getAcplIscurrent()){
					paId=ap.getAcplId();
				}
			}
			
			lstSalvaguardasTemp=new ArrayList<>();
			lstSalvaguardasTemp=salvaguardasFacade.listarSalvaguardasPorPlanAccion(paId);
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
	}
	
	private String obtenerMarcaTiempoEnTexto(){
		String m="";
		try{
			m = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return m;
	}
	
	public String obtenerTextoDesdeArreglo(String[] array){
		String res="";
		for(int i=0;i<array.length;i++){
			res=res+array[i]+", ";
		}
		return res;
	}
	
	public String obtenerNombreDePrimeraCarpeta(File carpeta){
		String n="";
		try{
			for(File f: carpeta.listFiles()){
				if(f.isDirectory()){
					n=f.getName();
				}
			}
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return n;
	}
	
	public String obtenerNombreDePrimerShapeFile(File carpeta){
		String n="";
		try{
			int c=0;
			for(File f: carpeta.listFiles()){
				if(f.isFile()&&f.getName().endsWith(".shp")){
					n=f.getName();
					c++;
				}
			}
			if(c>1){
				n="";
			}
		}catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
		return n;
	}


	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public List<Catalog> getLstCatTipoComp() {
		return lstCatTipoComp;
	}

	public void setLstCatTipoComp(List<Catalog> lstCatTipoComp) {
		this.lstCatTipoComp = lstCatTipoComp;
	}

	public List<Catalog> getLstCatMecJurid() {
		return lstCatMecJurid;
	}

	public void setLstCatMecJurid(List<Catalog> lstCatMecJurid) {
		this.lstCatMecJurid = lstCatMecJurid;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Integer getCodCatCompEstrategico() {
		return codCatCompEstrategico;
	}

	public void setCodCatCompEstrategico(Integer codCatCompEstrategico) {
		this.codCatCompEstrategico = codCatCompEstrategico;
	}

	public List<Catalog> getLstCatTipoSocImpl() {
		return lstCatTipoSocImpl;
	}

	public void setLstCatTipoSocImpl(List<Catalog> lstCatTipoSocImpl) {
		this.lstCatTipoSocImpl = lstCatTipoSocImpl;
	}

	public List<Catalog> getLstCatReqSoc1() {
		return lstCatReqSoc1;
	}

	public void setLstCatReqSoc1(List<Catalog> lstCatReqSoc1) {
		this.lstCatReqSoc1 = lstCatReqSoc1;
	}

	public List<Catalog> getLstCatReqSoc2() {
		return lstCatReqSoc2;
	}

	public void setLstCatReqSoc2(List<Catalog> lstCatReqSoc2) {
		this.lstCatReqSoc2 = lstCatReqSoc2;
	}

	public Integer getCodCatTipoSocComuIndig() {
		return codCatTipoSocComuIndig;
	}

	public void setCodCatTipoSocComuIndig(Integer codCatTipoSocComuIndig) {
		this.codCatTipoSocComuIndig = codCatTipoSocComuIndig;
	}

	public List<Object[]> getLstGeoLocProvincias() {
		return lstGeoLocProvincias;
	}

	public void setLstGeoLocProvincias(List<Object[]> lstGeoLocProvincias) {
		this.lstGeoLocProvincias = lstGeoLocProvincias;
	}

	public List<Object[]> getLstGeoLocCantones() {
		return lstGeoLocCantones;
	}

	public void setLstGeoLocCantones(List<Object[]> lstGeoLocCantones) {
		this.lstGeoLocCantones = lstGeoLocCantones;
	}

	public List<Catalog> getLstCatDecTipoId() {
		return lstCatDecTipoId;
	}

	public void setLstCatDecTipoId(List<Catalog> lstCatDecTipoId) {
		this.lstCatDecTipoId = lstCatDecTipoId;
	}

	public List<Catalog> getLstCatDecEstCivil() {
		return lstCatDecEstCivil;
	}

	public void setLstCatDecEstCivil(List<Catalog> lstCatDecEstCivil) {
		this.lstCatDecEstCivil = lstCatDecEstCivil;
	}

	public List<Catalog> getLstCatDecParrafos() {
		return lstCatDecParrafos;
	}

	public void setLstCatDecParrafos(List<Catalog> lstCatDecParrafos) {
		this.lstCatDecParrafos = lstCatDecParrafos;
	}

	public List<Catalog> getLstCatOpFinTipoFin() {
		return lstCatOpFinTipoFin;
	}

	public void setLstCatOpFinTipoFin(List<Catalog> lstCatOpFinTipoFin) {
		this.lstCatOpFinTipoFin = lstCatOpFinTipoFin;
	}

	public List<Catalog> getLstCatOpFinTipoFond() {
		return lstCatOpFinTipoFond;
	}

	public void setLstCatOpFinTipoFond(List<Catalog> lstCatOpFinTipoFond) {
		this.lstCatOpFinTipoFond = lstCatOpFinTipoFond;
	}

	public List<Catalog> getLstCatOpFinCat() {
		return lstCatOpFinCat;
	}

	public void setLstCatOpFinCat(List<Catalog> lstCatOpFinCat) {
		this.lstCatOpFinCat = lstCatOpFinCat;
	}

	public List<Catalog> getLstCatOpFinSector() {
		return lstCatOpFinSector;
	}

	public void setLstCatOpFinSector(List<Catalog> lstCatOpFinSector) {
		this.lstCatOpFinSector = lstCatOpFinSector;
	}

	public List<Catalog> getLstCatTipFueFin() {
		return lstCatTipFueFin;
	}

	public void setLstCatTipFueFin(List<Catalog> lstCatTipFueFin) {
		this.lstCatTipFueFin = lstCatTipFueFin;
	}

	public List<Catalog> getLstCatAdministrador() {
		return lstCatAdministrador;
	}

	public void setLstCatAdministrador(List<Catalog> lstCatAdministrador) {
		this.lstCatAdministrador = lstCatAdministrador;
	}

	public List<Object[]> getLstGeoLocParroquias() {
		return lstGeoLocParroquias;
	}

	public void setLstGeoLocParroquias(List<Object[]> lstGeoLocParroquias) {
		this.lstGeoLocParroquias = lstGeoLocParroquias;
	}

	public List<ActionPlan> getLstPlanesAccion() {
		return lstPlanesAccion;
	}

	public void setLstPlanesAccion(List<ActionPlan> lstPlanesAccion) {
		this.lstPlanesAccion = lstPlanesAccion;
	}

	public List<SelectItem> getLstMeses() {
		return lstMeses;
	}

	public void setLstMeses(List<SelectItem> lstMeses) {
		this.lstMeses = lstMeses;
	}

	public List<SelectItem> getLstAnios() {
		return lstAnios;
	}

	public void setLstAnios(List<SelectItem> lstAnios) {
		this.lstAnios = lstAnios;
	}

	public List<Catalog> getLstCatCatObj() {
		return lstCatCatObj;
	}

	public void setLstCatCatObj(List<Catalog> lstCatCatObj) {
		this.lstCatCatObj = lstCatCatObj;
	}

	public List<Catalog> getLstCatTipoCobeneficio() {
		return lstCatTipoCobeneficio;
	}

	public void setLstCatTipoCobeneficio(List<Catalog> lstCatTipoCobeneficio) {
		this.lstCatTipoCobeneficio = lstCatTipoCobeneficio;
	}

	public List<Catalog> getLstCatTipoRiesgo() {
		return lstCatTipoRiesgo;
	}

	public void setLstCatTipoRiesgo(List<Catalog> lstCatTipoRiesgo) {
		this.lstCatTipoRiesgo = lstCatTipoRiesgo;
	}

	public List<Catalog> getLstCatEnfGenero1() {
		return lstCatEnfGenero1;
	}

	public void setLstCatEnfGenero1(List<Catalog> lstCatEnfGenero1) {
		this.lstCatEnfGenero1 = lstCatEnfGenero1;
	}

	public List<Catalog> getLstCatEnfGenero2() {
		return lstCatEnfGenero2;
	}

	public void setLstCatEnfGenero2(List<Catalog> lstCatEnfGenero2) {
		this.lstCatEnfGenero2 = lstCatEnfGenero2;
	}

	public List<Catalog> getLstCatEnfGenero3() {
		return lstCatEnfGenero3;
	}

	public void setLstCatEnfGenero3(List<Catalog> lstCatEnfGenero3) {
		this.lstCatEnfGenero3 = lstCatEnfGenero3;
	}

	public List<FundingSource> getLstFuentesFin() {
		return lstFuentesFin;
	}

	public void setLstFuentesFin(List<FundingSource> lstFuentesFin) {
		this.lstFuentesFin = lstFuentesFin;
	}

	public List<Catalog> getLstCatTipoDocProyecto() {
		return lstCatTipoDocProyecto;
	}

	public void setLstCatTipoDocProyecto(List<Catalog> lstCatTipoDocProyecto) {
		this.lstCatTipoDocProyecto = lstCatTipoDocProyecto;
	}

	public Integer getCodOtroTipoDoc() {
		return codOtroTipoDoc;
	}

	public void setCodOtroTipoDoc(Integer codOtroTipoDoc) {
		this.codOtroTipoDoc = codOtroTipoDoc;
	}

	public List<Safeguard> getLstSalvaguardasTemp() {
		return lstSalvaguardasTemp;
	}

	public void setLstSalvaguardasTemp(List<Safeguard> lstSalvaguardasTemp) {
		this.lstSalvaguardasTemp = lstSalvaguardasTemp;
	}

	public List<Integer> getLstIdsCatCostosGastos() {
		return catalogoFacade.listaIdsCatalogos(lstCatCostosGastos);
	}

	

	public List<Catalog> getLstCatPregGestCon() {
		return lstCatPregGestCon;
	}

	public void setLstCatPregGestCon(List<Catalog> lstCatPregGestCon) {
		this.lstCatPregGestCon = lstCatPregGestCon;
	}

	public List<Catalog> getLstCatMecAccInf() {
		return lstCatMecAccInf;
	}

	public void setLstCatMecAccInf(List<Catalog> lstCatMecAccInf) {
		this.lstCatMecAccInf = lstCatMecAccInf;
	}

	public List<Catalog> getLstCatMecInvAct() {
		return lstCatMecInvAct;
	}

	public void setLstCatMecInvAct(List<Catalog> lstCatMecInvAct) {
		this.lstCatMecInvAct = lstCatMecInvAct;
	}

	public List<Catalog> getLstCatTiposAct() {
		return lstCatTiposAct;
	}

	public void setLstCatTiposAct(List<Catalog> lstCatTiposAct) {
		this.lstCatTiposAct = lstCatTiposAct;
	}

	public List<Catalog> getLstCatPregCom() {
		return lstCatPregCom;
	}

	public void setLstCatPregCom(List<Catalog> lstCatPregCom) {
		this.lstCatPregCom = lstCatPregCom;
	}

	public List<Catalog> getLstCatTiposProblemas() {
		return lstCatTiposProblemas;
	}

	public void setLstCatTiposProblemas(List<Catalog> lstCatTiposProblemas) {
		this.lstCatTiposProblemas = lstCatTiposProblemas;
	}

	public List<Catalog> getLstCatTiposPeriodosReporte() {
		return lstCatTiposPeriodosReporte;
	}

	public void setLstCatTiposPeriodosReporte(List<Catalog> lstCatTiposPeriodosReporte) {
		this.lstCatTiposPeriodosReporte = lstCatTiposPeriodosReporte;
	}

	public String getPathUploads() {
		return pathUploads;
	}

	public void setPathUploads(String pathUploads) {
		this.pathUploads = pathUploads;
	}

	public String getMaxSizeZipShape() {
		return maxSizeZipShape;
	}

	public void setMaxSizeZipShape(String maxSizeZipShape) {
		this.maxSizeZipShape = maxSizeZipShape;
	}

	public List<Catalog> getLstCatCapasGeoBase() {
		return lstCatCapasGeoBase;
	}

	public void setLstCatCapasGeoBase(List<Catalog> lstCatCapasGeoBase) {
		this.lstCatCapasGeoBase = lstCatCapasGeoBase;
	}

	public ProjectGeoType[] getProjectGeoTypes() {
        return ProjectGeoType.values();
    }

	
  
	public List<Catalog> getLstCatEjeTematicoShapeNivel1() {
		return lstCatEjeTematicoShapeNivel1;
	}

	public void setLstCatEjeTematicoShapeNivel1(List<Catalog> lstCatEjeTematicoShapeNivel1) {
		this.lstCatEjeTematicoShapeNivel1 = lstCatEjeTematicoShapeNivel1;
	}

	public String textoEjeTematico(String codigo){
		String s="";
		for(Catalog c:lstCatEjeTematicoShapeTodo){
			if(c.getCataText2().equals(codigo)){
				s=c.getCataText1();
				break;
			}
		}
		return s;
	}
	
	public Catalog obtenerObjetoCatalogoPorIdLista(Integer idCat, List<Catalog> lst){
		Catalog cc=new Catalog();
		for(Catalog c:lst){
			if(c.getCataId().equals(idCat)){
				System.out.println("okokkkk");
				cc=c;
				break;
			}
		}
		return cc;
	}
	
	

	public Integer getCodCatEnfGenero1Otro() {
		return codCatEnfGenero1Otro;
	}

	public void setCodCatEnfGenero1Otro(Integer codCatEnfGenero1Otro) {
		this.codCatEnfGenero1Otro = codCatEnfGenero1Otro;
	}

	public Integer getCodCatEnfGenero2Otro() {
		return codCatEnfGenero2Otro;
	}

	public void setCodCatEnfGenero2Otro(Integer codCatEnfGenero2Otro) {
		this.codCatEnfGenero2Otro = codCatEnfGenero2Otro;
	}

	public Integer getCodCatEnfGenero3Otro() {
		return codCatEnfGenero3Otro;
	}

	public void setCodCatEnfGenero3Otro(Integer codCatEnfGenero3Otro) {
		this.codCatEnfGenero3Otro = codCatEnfGenero3Otro;
	}

	public Integer getCodUltimoSemestre() {
		return codUltimoSemestre;
	}

	public void setCodUltimoSemestre(Integer codUltimoSemestre) {
		this.codUltimoSemestre = codUltimoSemestre;
	}

	public List<List<Object[]>> getListaGeoCapasBase() {
		return listaGeoCapasBase;
	}

	public void setListaGeoCapasBase(List<List<Object[]>> listaGeoCapasBase) {
		this.listaGeoCapasBase = listaGeoCapasBase;
	}

	public List<Catalog> getLstCatEveOrigenEtnico() {
		return lstCatEveOrigenEtnico;
	}

	public void setLstCatEveOrigenEtnico(List<Catalog> lstCatEveOrigenEtnico) {
		this.lstCatEveOrigenEtnico = lstCatEveOrigenEtnico;
	}

	public List<Catalog> getLstCatEveNacionalidadesInd() {
		return lstCatEveNacionalidadesInd;
	}

	public void setLstCatEveNacionalidadesInd(List<Catalog> lstCatEveNacionalidadesInd) {
		this.lstCatEveNacionalidadesInd = lstCatEveNacionalidadesInd;
	}

	public String getSigmaWebServicesUsername() {
		return sigmaWebServicesUsername;
	}

	public void setSigmaWebServicesUsername(String sigmaWebServicesUsername) {
		this.sigmaWebServicesUsername = sigmaWebServicesUsername;
	}

	public String getSigmaWebServicesPassword() {
		return sigmaWebServicesPassword;
	}

	public void setSigmaWebServicesPassword(String sigmaWebServicesPassword) {
		this.sigmaWebServicesPassword = sigmaWebServicesPassword;
	}

	public Integer getCodPrimerSemestre() {
		return codPrimerSemestre;
	}

	public void setCodPrimerSemestre(Integer codPrimerSemestre) {
		this.codPrimerSemestre = codPrimerSemestre;
	}

	public List<CatalogsType> getLstCatTypes() {
		return lstCatTypes;
	}

	public void setLstCatTypes(List<CatalogsType> lstCatTypes) {
		this.lstCatTypes = lstCatTypes;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public List<Catalog> getLstCatCamposObjGeoREDD() {
		return lstCatCamposObjGeoREDD;
	}

	public void setLstCatCamposObjGeoREDD(List<Catalog> lstCatCamposObjGeoREDD) {
		this.lstCatCamposObjGeoREDD = lstCatCamposObjGeoREDD;
	}

	public String getUrlWebServicesBusSnap() {
		return urlWebServicesBusSnap;
	}

	public void setUrlWebServicesBusSnap(String urlWebServicesBusSnap) {
		this.urlWebServicesBusSnap = urlWebServicesBusSnap;
	}

	
	
	public Properties cargarParametrosCorreo(){
		Properties prop = new Properties();
		//String pathFile="C:\\Users\\PROAmazonia\\devstudio\\runtimes\\paramconfigMAE.properties";
		String pathFile="/opt/paramconfigMAE.properties";
		
		try (InputStream input = new FileInputStream(pathFile)) {

			Properties propTotal = new Properties();
			propTotal.load(input);

			for(Entry<Object, Object> e : propTotal.entrySet()) {
		            if(String.valueOf(e.getKey()).contains("mail.smtp")){
		            	prop.put(e.getKey(),e.getValue());
		            }
		    }
            

        } catch (IOException ex) {
        	System.out.println("Recuerde colocar el archivo paramconfigMAE.properties en /opt/");
            ex.printStackTrace();
        }
		return prop;
	}
	

}
