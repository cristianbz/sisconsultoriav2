package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
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
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.CatalogsType;
import ec.gob.ambiente.sigma.ejb.entidades.Document;
import ec.gob.ambiente.sigma.ejb.entidades.FinancingAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;
import ec.gob.ambiente.sigma.ejb.facades.CatalogFacade;
import ec.gob.ambiente.sigma.ejb.facades.FinancingAgreementFacade;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourceFacade;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;

@Named("cataContr")
@ViewScoped
public class CatalogoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CatalogoController.class.getName());

	private Catalog catalogoActual;
	private CatalogsType tipoActual;
	private List<Catalog> listaCatalogoPorTipo;
	private String mnemonicoActual;
	
	@EJB
	private CatalogFacade catalogFacade;
	
	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	public CatalogoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			mnemonicoActual="";
			inicializarObjetoCatalogo();
			listaCatalogoPorTipo=new ArrayList<>();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void inicializarObjetoCatalogo(){
		try{
			catalogoActual=new Catalog();
			catalogoActual.setCatyId(new CatalogsType());
			catalogoActual.setCataStatus(true);
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void listarCatalogosPorTipo(){
		try{
			mnemonicoActual="";
			inicializarObjetoCatalogo();
			listaCatalogoPorTipo=new ArrayList<>();
			if(tipoActual!=null&&tipoActual.getCatyId()!=null){
				listaCatalogoPorTipo=catalogFacade.listarCatalogosPorTipo(tipoActual.getCatyId());
				if(!listaCatalogoPorTipo.isEmpty()){
					mnemonicoActual=listaCatalogoPorTipo.get(0).getCatyId().getCatyMnemonic();
				}
			}
			int items=listaCatalogoPorTipo.size();
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg162").replace("_x_", ""+items));
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	

	public void guardarCatalogo() {
		try {
			List<String[]> listaMsg = validarCatalogo(catalogoActual);

			if (listaMsg.isEmpty()) {
					if(catalogoActual.getCataId()==null){
						if(esCatalogoCerrado(mnemonicoActual)){
							sesBean.addSuccessMessage(appBean.getBundle().getString("msg165"));
						}else{
							catalogoActual.setCataCreationDate(new Date());
							catalogoActual.setCataCreatorUser(sesBean.getUsuarioLogeado().getUserName());
							catalogoActual.setCatyId(tipoActual);
							catalogFacade.create(catalogoActual);
							sesBean.addSuccessMessage(appBean.getBundle().getString("msg164"));
						}
						listaCatalogoPorTipo=catalogFacade.listarCatalogosPorTipo(catalogoActual.getCatyId().getCatyId());
					}else{
						catalogoActual.setAcplDateUpdate(new Date());
						catalogoActual.setCataUserUpdate(sesBean.getUsuarioLogeado().getUserName());
						catalogFacade.edit(catalogoActual);
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg163"));
					}
					inicializarObjetoCatalogo();
					
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

	private List<String[]> validarCatalogo(Catalog catalogo) {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			
			if (catalogo.getCatyId()==null) {
				sesBean.validacionMsg("frm:caTipo", appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (catalogo.getCataOrder()==null||catalogo.getCataOrder()<0) {
				sesBean.validacionMsg("frm:caOrden", appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (catalogo.getCataText1().length()==0) {
				sesBean.validacionMsg("frm:caText1", appBean.getBundle().getString("msg031"), listaMsg);
			}
			
			if (catalogo.getCataNumber()==null||catalogo.getCataNumber()<0) {
				sesBean.validacionMsg("frm:caNumber", appBean.getBundle().getString("msg031"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	private boolean esCatalogoCerrado(String mnemonico){
		boolean b= false;
		List<String> lstCatCerrados=new ArrayList<>();
		lstCatCerrados.add("TIPO_COMPONENTES");
		lstCatCerrados.add("REQUISITO_SOCIO_1");
		lstCatCerrados.add("REQUISITO_SOCIO_2");
		lstCatCerrados.add("PARRAFOS_DECL_JURAM");
		lstCatCerrados.add("CAT_COSTOS_GASTOS");
		lstCatCerrados.add("CAT_PREG_GEST_CON");
		lstCatCerrados.add("CAT_PREG_COM");
		
		for(String n:lstCatCerrados){
			if(mnemonico.equals(n)){
				b=true;
				break;
			}
		}
		
		return b;
	}
	
	public void seleccionarCatalogoAEditar(Catalog c){
		try {
			catalogoActual=c;
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public Catalog getCatalogoActual() {
		return catalogoActual;
	}

	public void setCatalogoActual(Catalog catalogoActual) {
		this.catalogoActual = catalogoActual;
	}

	public List<Catalog> getListaCatalogoPorTipo() {
		return listaCatalogoPorTipo;
	}

	public void setListaCatalogoPorTipo(List<Catalog> listaCatalogoPorTipo) {
		this.listaCatalogoPorTipo = listaCatalogoPorTipo;
	}

	public CatalogsType getTipoActual() {
		return tipoActual;
	}

	public void setTipoActual(CatalogsType tipoActual) {
		this.tipoActual = tipoActual;
	}
	
	
	
	
	
	
}
