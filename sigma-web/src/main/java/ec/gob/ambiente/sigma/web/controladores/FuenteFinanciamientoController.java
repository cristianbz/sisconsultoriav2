package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
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

import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesNote;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesOption;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourceFacade;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourcesNoteFacade;
import ec.gob.ambiente.sigma.ejb.facades.FundingSourcesOptionFacade;
import ec.gob.ambiente.sigma.web.utils.TipoRetornoAProyecto;

@Named("fina")
@ViewScoped
public class FuenteFinanciamientoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FuenteFinanciamientoController.class.getName());

	@EJB
	private FundingSourceFacade fuenteFinanFacade;
	@EJB
	private FundingSourcesNoteFacade fuenteFinanNotasFacade;
	@EJB
	private FundingSourcesOptionFacade fuenteFinanOpcionFacade;

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;

	private FundingSource fuenteFinanActual;
	private List<FundingSource> listaFuentesFinan;
	private List<FundingSourcesOption> listaOpcionesTFI;
	private List<FundingSourcesOption> listaOpcionesTFO;
	private List<FundingSourcesOption> listaOpcionesCAT;
	private List<FundingSourcesOption> listaOpcionesSEC;

	private FundingSourcesNote fuenteFinanNotaActual;
	private List<FundingSourcesNote> listaNotasFuenteFinan;

	private boolean flagSoloCrear;
	
	private boolean flagOtroTipo;

	public FuenteFinanciamientoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			prepararNuevaFuente();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			String attr = (String) session.getAttribute("TIPO_PAG_FFIN");
			if (attr != null && attr.contains("SOLO")) {
				// solo crear para mostrar en registro proyectos
				flagSoloCrear = true;
			} else {
				listaFuentesFinan = new ArrayList<>();
				listaFuentesFinan = fuenteFinanFacade.listarFuentesDeFinanciamiento();
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void prepararNuevaFuente() {
		try {
			fuenteFinanActual = new FundingSource();
			fuenteFinanActual.setCataId(new Catalog());
			fuenteFinanNotaActual = new FundingSourcesNote();
			listaNotasFuenteFinan = new ArrayList<>();
			prepararNuevaNota();
			prepararOpciones();
			flagOtroTipo=false;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void prepararOpciones() {
		try {

			listaOpcionesTFI = new ArrayList<>();
			listaOpcionesTFO = new ArrayList<>();
			listaOpcionesCAT = new ArrayList<>();
			// listaOpcionesSEC = new ArrayList<>();
			for (Catalog c : appBean.getLstCatOpFinTipoFin()) {
				FundingSourcesOption o = new FundingSourcesOption();
				o.setCataId(c);
				o.setFsopType("TFI");
				listaOpcionesTFI.add(o);
			}
			for (Catalog c : appBean.getLstCatOpFinTipoFond()) {
				FundingSourcesOption o = new FundingSourcesOption();
				o.setCataId(c);
				o.setFsopType("TFO");
				listaOpcionesTFO.add(o);
			}
			for (Catalog c : appBean.getLstCatOpFinCat()) {
				FundingSourcesOption o = new FundingSourcesOption();
				o.setCataId(c);
				o.setFsopType("CAT");
				listaOpcionesCAT.add(o);
			}
			/*
			 * for (Catalog c : appBean.getLstCatOpFinSector()) {
			 * FundingSourcesOption o = new FundingSourcesOption();
			 * o.setCataId(c); o.setFsopType("SEC"); listaOpcionesSEC.add(o); }
			 */
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void actualizarListaSectores() {
		try {
			List<Integer> lst = new ArrayList<>();
			for (FundingSourcesOption c : listaOpcionesCAT) {
				if (c.getSelected() != null && c.getSelected()) {
					lst.add(c.getCataId().getCataId());
				}
			}
			listaOpcionesSEC = new ArrayList<>();
			//if (fusoId == null) {
				for (Catalog c : appBean.listarSecCatPorCategoriaFinanciamiento(lst)) {
					FundingSourcesOption o = new FundingSourcesOption();
					o.setCataId(c);
					o.setFsopType("SEC");
					listaOpcionesSEC.add(o);
				}
			//}else{
				
			//}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void cargarNotasFuenteActual() {
		try {
			listaNotasFuenteFinan = new ArrayList<>();
			listaNotasFuenteFinan = fuenteFinanNotasFacade
					.listarNotasDeFuenteFinanciamiento(fuenteFinanActual.getFusoId());
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarFuenteFinanciamiento() {
		try {
			List<String[]> listaMsg = validarFuente(fuenteFinanActual);

			if (listaMsg.isEmpty()) {
				fuenteFinanActual.setFusoCode(fuenteFinanActual.getFusoCode().toUpperCase());
				fuenteFinanActual.setFusoName(fuenteFinanActual.getFusoName().toUpperCase());
				boolean flagCrear=false;
				if (fuenteFinanActual.getFusoId() == null) {
					flagCrear=true;
					fuenteFinanActual = fuenteFinanFacade.crear(sesBean.getUsuarioLogeado().getUserName(), fuenteFinanActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg045"));
				} else {
					fuenteFinanActual = fuenteFinanFacade.editar(sesBean.getUsuarioLogeado().getUserName(), fuenteFinanActual);
					sesBean.addSuccessMessage(appBean.getBundle().getString("msg046"));
				}
				guardarOpcionesFuente(fuenteFinanActual);
				cargarOpcionesFuenteActual();
				listaFuentesFinan = fuenteFinanFacade.listarFuentesDeFinanciamiento();
				appBean.recargarFuentesFinanciamiento();
				if (flagSoloCrear) {
					volverARegistroProyecto();
				}else{
					if(flagCrear){
						prepararNuevaFuente();
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

	public void volverARegistroProyecto() {
		sesBean.redireccionarAPaginaConParametro("projects", "RegistroProyecto", "REDIRECCION_DESDE",
				TipoRetornoAProyecto.DESDE_FUENTE_FINANCIAMIENTO.getCodigo());
	}

	private void guardarOpcionesFuente(FundingSource fs) {
		try {
			if (fs.getFusoId() != null) {
				fuenteFinanOpcionFacade.eliminarOpcionesTodas(fs.getFusoId());
				for (FundingSourcesOption o : listaOpcionesTFI) {
					if (o.getSelected()) {
						o.setFusoId(fs);
						fuenteFinanOpcionFacade.create(o);
					}
				}
				for (FundingSourcesOption o : listaOpcionesTFO) {
					if (o.getSelected()) {
						o.setFusoId(fs);
						fuenteFinanOpcionFacade.create(o);
					}
				}
				for (FundingSourcesOption o : listaOpcionesCAT) {
					if (o.getSelected()) {
						o.setFusoId(fs);
						fuenteFinanOpcionFacade.create(o);
					}
				}
				for (FundingSourcesOption o : listaOpcionesSEC) {
					if (o.getSelected()) {
						o.setFusoId(fs);
						fuenteFinanOpcionFacade.create(o);
					}
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private List<String[]> validarFuente(FundingSource fuente) {
		List<String[]> listaMsg = new ArrayList<>();
		try {

			if (fuente.getFusoCode().length() < 3) {
				sesBean.validacionMsg("frm:ffCode", appBean.getBundle().getString("lbl017")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			} else {
				List<FundingSource> lstValCode = fuenteFinanFacade
						.listarFuentesDeFinanciamientoPorSigla(fuente.getFusoCode());
				if (fuente.getFusoId() == null && !lstValCode.isEmpty()) {
					sesBean.validacionMsg("frm:ffCode", appBean.getBundle().getString("msg077"), listaMsg);
				}
				if (fuente.getFusoId() != null && !lstValCode.isEmpty()
						&& lstValCode.get(0).getFusoId() != fuente.getFusoId()) {
					sesBean.validacionMsg("frm:ffCode", appBean.getBundle().getString("msg077"), listaMsg);
				}
			}
			if (fuente.getFusoName().length() == 0) {
				sesBean.validacionMsg("frm:ffName", appBean.getBundle().getString("lbl018")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fuente.getFusoContactPerson().length() == 0) {
				sesBean.validacionMsg("frm:ffContactPerson", appBean.getBundle().getString("lbl052")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			/*
			 * if (fuente.getFusoContactPersonPosition().length() == 0) {
			 * sesBean.validacionMsg("frm:ffContactPersonPos",
			 * appBean.getBundle().getString("msg031"), listaMsg); }
			 */
			if (fuente.getFusoContactPersonEmail().length() == 0) {
				sesBean.validacionMsg("frm:ffContactPersonEmail", appBean.getBundle().getString("lbl054")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}
			if (fuente.getFusoPhones().length() == 0) {
				sesBean.validacionMsg("frm:ffPhones",appBean.getBundle().getString("lbl096")+" es "+  appBean.getBundle().getString("msg031"), listaMsg);
			}
			boolean flagOp1 = false;
			for (FundingSourcesOption o : listaOpcionesTFI) {
				if (o.getSelected()) {
					flagOp1 = true;
					break;
				}
			}
			if (!flagOp1) {
				sesBean.validacionMsg("frm:lstOpTFI", appBean.getBundle().getString("msg044"), listaMsg);
			}
			boolean flagOp2 = false;
			boolean flagOp2Otro = false;
			for (FundingSourcesOption o : listaOpcionesTFO) {
				if (o.getSelected()) {
					flagOp2 = true;
					if (o.getCataId().getCataNumber() == 4) {
						flagOp2Otro = true;
					}
				}
			}
			if (!flagOp2) {
				sesBean.validacionMsg("frm:lstOpTFO", appBean.getBundle().getString("msg044"), listaMsg);
			}
			if (flagOp2Otro && fuente.getFusoOtherFund().length() == 0) {
				sesBean.validacionMsg("frm:ffOtherFund", appBean.getBundle().getString("msg031"), listaMsg);
			}
			boolean flagOp3 = false;
			for (FundingSourcesOption o : listaOpcionesCAT) {
				if (o.getSelected()) {
					flagOp3 = true;
					break;
				}
			}
			if (!flagOp3) {
				sesBean.validacionMsg("frm:lstOpCAT", appBean.getBundle().getString("msg044"), listaMsg);
			}
			/*boolean flagOp4 = false;
			boolean flagOp4Otro = false;
			for (FundingSourcesOption o : listaOpcionesSEC) {
				if (o.getSelected()) {
					flagOp4 = true;
					if (o.getCataId().getCataNumber() == 5) {
						flagOp4Otro = true;
					}
				}
			}
			if (!flagOp4) {
				sesBean.validacionMsg("frm:lstOpSEC", appBean.getBundle().getString("msg044"), listaMsg);
			}
			if (flagOp4Otro && fuente.getFusoOtherSector().length() == 0) {
				sesBean.validacionMsg("frm:ffOtherSec", appBean.getBundle().getString("msg031"), listaMsg);
			}*/

			if (fuente.getCataId() == null || fuente.getCataId().getCataId() == null) {
				sesBean.validacionMsg("frm:ffType1", appBean.getBundle().getString("lbl019")+" es "+ appBean.getBundle().getString("msg031"), listaMsg);
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}

	private void cargarOpcionesFuenteActual() {
		try {
			List<FundingSourcesOption> lstOp = new ArrayList<>();
			lstOp = fuenteFinanOpcionFacade.listarOpcionesFuenteFinanciamiento(fuenteFinanActual.getFusoId());
			prepararOpciones();

			for (FundingSourcesOption op : lstOp) {
				if (op.getFsopType().equals("TFI")) {
					for (FundingSourcesOption c : listaOpcionesTFI) {
						if (c.getCataId().getCataId() == op.getCataId().getCataId()) {
							c.setSelected(true);
						}
					}
				} else if (op.getFsopType().equals("TFO")) {
					for (FundingSourcesOption c : listaOpcionesTFO) {
						if (c.getCataId().getCataId() == op.getCataId().getCataId()) {
							c.setSelected(true);
						}
					}
				} else if (op.getFsopType().equals("CAT")) {
					for (FundingSourcesOption c : listaOpcionesCAT) {
						if (c.getCataId().getCataId() == op.getCataId().getCataId()) {
							c.setSelected(true);

						}
					}
				}
			}
			actualizarListaSectores();
			for (FundingSourcesOption op : lstOp) {
				if (op.getFsopType().equals("SEC")) {
					for (FundingSourcesOption c : listaOpcionesSEC) {
						
						if (c.getCataId().getCataId().equals(op.getCataId().getCataId())) {
							c.setSelected(true);
						}
					}
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarTipoFinanciamiento(Catalog cat){
		try{
			for(FundingSourcesOption t: listaOpcionesTFO){
				
					if(cat.getCataId()==t.getCataId().getCataId()&&t.getCataId().getCataText1().toUpperCase().contains("OTRO")){
						if(t.getSelected()!=null&&t.getSelected()){
						    flagOtroTipo=true;
					    }else{
					    	fuenteFinanActual.setFusoOtherFund("");
					    	flagOtroTipo=false;
					    	
					    }
						PrimeFaces.current().ajax().update("frm:ffOtherFund");
				    }
				
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarFuenteAEditar(FundingSource fs) {
		try {
			fuenteFinanActual = fs;

			cargarOpcionesFuenteActual();

			cargarNotasFuenteActual();
			flagOtroTipo=false;
			for(FundingSourcesOption t: listaOpcionesTFO){
				if(t.getCataId().getCataText1().toUpperCase().contains("OTRO")){
					if(t.getSelected()!=null&&t.getSelected()){
					    flagOtroTipo=true;
				    }
			    }
			
		}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarFuenteDeFinanciamiento(FundingSource fs) {
		try {
			fuenteFinanFacade.eliminarLogico("temp", fs);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg047"));
			listaFuentesFinan = fuenteFinanFacade.listarFuentesDeFinanciamiento();
		} catch (Exception ex) {

		}
	}

	public void prepararNuevaNota() {
		try {
			fuenteFinanNotaActual = new FundingSourcesNote();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void seleccionarNotaAEditar(FundingSourcesNote not) {
		try {
			fuenteFinanNotaActual = not;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void guardarNotaDeFuente() {
		try {
			if (fuenteFinanNotaActual.getFsnoNote().length() != 0) {
				if (fuenteFinanActual.getFusoId() != null) {
					if (fuenteFinanNotaActual.getFsnoId() == null) {
						fuenteFinanNotaActual.setFusoId(fuenteFinanActual);
						fuenteFinanNotasFacade.create(fuenteFinanNotaActual);
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg048"));
					} else {
						fuenteFinanNotasFacade.edit(fuenteFinanNotaActual);
						sesBean.addSuccessMessage(appBean.getBundle().getString("msg049"));
					}
					prepararNuevaNota();
					cargarNotasFuenteActual();
				}
			} else {
				List<String[]> listaMsg = new ArrayList<>();
				sesBean.validacionMsg("frm:ffNotaDesc", appBean.getBundle().getString("msg031"), listaMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarNotaFuenteDeFinanciamiento(FundingSourcesNote fsn) {
		try {
			fuenteFinanNotasFacade.eliminarLogico(fsn);
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg050"));
			cargarNotasFuenteActual();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public List<FundingSourcesOption> getListaOpcionesTFI() {
		return listaOpcionesTFI;
	}

	public void setListaOpcionesTFI(List<FundingSourcesOption> listaOpcionesTFI) {
		this.listaOpcionesTFI = listaOpcionesTFI;
	}

	public List<FundingSourcesOption> getListaOpcionesTFO() {
		return listaOpcionesTFO;
	}

	public void setListaOpcionesTFO(List<FundingSourcesOption> listaOpcionesTFO) {
		this.listaOpcionesTFO = listaOpcionesTFO;
	}

	public List<FundingSourcesOption> getListaOpcionesCAT() {
		return listaOpcionesCAT;
	}

	public void setListaOpcionesCAT(List<FundingSourcesOption> listaOpcionesCAT) {
		this.listaOpcionesCAT = listaOpcionesCAT;
	}

	public List<FundingSourcesOption> getListaOpcionesSEC() {
		return listaOpcionesSEC;
	}

	public void setListaOpcionesSEC(List<FundingSourcesOption> listaOpcionesSEC) {
		this.listaOpcionesSEC = listaOpcionesSEC;
	}

	public FundingSource getFuenteFinanActual() {
		return fuenteFinanActual;
	}

	public void setFuenteFinanActual(FundingSource fuenteFinanActual) {
		this.fuenteFinanActual = fuenteFinanActual;
	}

	public List<FundingSource> getListaFuentesFinan() {
		return listaFuentesFinan;
	}

	public void setListaFuentesFinan(List<FundingSource> listaFuentesFinan) {
		this.listaFuentesFinan = listaFuentesFinan;
	}

	public List<FundingSourcesNote> getListaNotasFuenteFinan() {
		return listaNotasFuenteFinan;
	}

	public void setListaNotasFuenteFinan(List<FundingSourcesNote> listaNotasFuenteFinan) {
		this.listaNotasFuenteFinan = listaNotasFuenteFinan;
	}

	public FundingSourcesNote getFuenteFinanNotaActual() {
		return fuenteFinanNotaActual;
	}

	public void setFuenteFinanNotaActual(FundingSourcesNote fuenteFinanNotaActual) {
		this.fuenteFinanNotaActual = fuenteFinanNotaActual;
	}

	public boolean isFlagSoloCrear() {
		return flagSoloCrear;
	}

	public void setFlagSoloCrear(boolean flagSoloCrear) {
		this.flagSoloCrear = flagSoloCrear;
	}

	public boolean getFlagOtroTipo() {
		return flagOtroTipo;
	}

	public void setFlagOtroTipo(boolean flagOtroTipo) {
		this.flagOtroTipo = flagOtroTipo;
	}

	
}
