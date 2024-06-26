/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.web.controladores;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.gob.ambiente.client.SuiaServices;
import ec.gob.ambiente.client.SuiaServices_Service;
import ec.gob.ambiente.sigma.ejb.entidades.Contact;
import ec.gob.ambiente.sigma.ejb.entidades.Organization;
import ec.gob.ambiente.sigma.ejb.entidades.People;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.facades.ContactFacade;
import ec.gob.ambiente.sigma.ejb.facades.OrganizationFacade;
import ec.gob.ambiente.sigma.ejb.facades.PeopleFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectsStrategicPartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.UserFacade;
import ec.gob.ambiente.sigma.ejb.model.Menu;
import ec.gob.ambiente.sigma.ejb.model.MenuRole;
import ec.gob.ambiente.sigma.ejb.model.MenuVO;
import ec.gob.ambiente.sigma.ejb.model.Role;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
import ec.gob.ambiente.sigma.ejb.services.MenuFacade;
import ec.gob.ambiente.sigma.ejb.services.MenuRoleFacade;
import ec.gob.ambiente.sigma.ejb.services.RoleFacade;
import ec.gob.ambiente.sigma.ejb.services.RolesUserFacade;
import ec.gob.ambiente.sigma.web.security.LoginBean;
import ec.gob.ambiente.sigma.web.sis.utils.UtilsCadenas;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoRolesUsuarioEnum;
import ec.gob.ambiente.sigma.web.utils.JsfUtil;
import ec.gov.sri.wsconsultacontribuyente.ContribuyenteCompleto;

/**
 *
 * @author davidguano
 */
@Named(value = "ses")
@SessionScoped
public class SesionControlador implements Serializable {

	private static final Logger LOG = Logger.getLogger(SesionControlador.class.getName());

	@EJB
	private UserFacade usuFacade;
	@EJB
	private RoleFacade rolFacade;
	@EJB
	private RolesUserFacade rolUserFacade;
	@EJB
	private MenuRoleFacade menuRolFacade;
	@EJB
	private ProjectFacade projectsFacade;
	@EJB
	private ProjectsStrategicPartnerFacade projectsStrategicPartnersFacade;
	@EJB
	private PeopleFacade personaFacade;
	@EJB
	private ContactFacade contactFacade;
	@EJB
	private OrganizationFacade orgaFacade;

	@Inject
	private AplicacionControlador appBean;

	private User usuarioLogeado;
	private List<RolesUser> listaRolesDeUsuarioLogeado;
	private List<Menu> listaMenusDeRolesDeUsuarioLogeado;
	private MenuModel menuModel;

	private String username;
	private String password;

	private String claveAnterior;
	private String nuevaClave;
	private String nuevaClaveConf;
	private String mailRecuperarClave;

	private People personaLogeada;
	private List<Contact> listaDatosContactoPersonaLogeada;
	private List<Contact> listaOtrosDatosContactoPersonaLogeada;
	private Contact nuevoDatoContacto;
	private Integer idProvinciaPersonaLogeada;
	private Integer idCantonPersonaLogeada;
	private Organization organizacionLogeada;
	private int tipoUsuario;
	private String nombreLogeado;

	private boolean tieneRolSIGMA;
	private boolean tieneRolSIS;
	private String moduloActual;

	@Inject
	private LoginBean loginBean;
	private ExternalContext ec;

	/**
	 * Creates a new instance of SesionControlador
	 */
	public SesionControlador() {
	}

	@PostConstruct
	public void inicializar() {
		try {

			username = "";
			password = "";
			moduloActual = "";

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void iniciarSesion() {
		try {
			if (username.length() > 0 && password.length() > 0) {
				usuarioLogeado = usuFacade.obtenerUsuarioConPassword(username, JsfUtil.claveEncriptadaSHA1(password));
				if (usuarioLogeado == null) {
					addErrorMessage(appBean.getBundle().getString("msg124"));
					username = "";
					password = "";
				} else {
					getLoginBean().setUser(usuarioLogeado);
					ec = FacesContext.getCurrentInstance().getExternalContext();
					getLoginBean().setSesion((HttpSession) ec.getSession(true));
					getLoginBean().getSesion().setAttribute("logeado", true);
					listaRolesDeUsuarioLogeado = rolUserFacade.listarRolesDeUsuario(usuarioLogeado);
					getLoginBean().setListaRolesUsuario(listaRolesDeUsuarioLogeado);
					if (listaRolesDeUsuarioLogeado != null && !listaRolesDeUsuarioLogeado.isEmpty()) {
						listaMenusDeRolesDeUsuarioLogeado = menuRolFacade.listarMenusPorRol(listaRolesDeUsuarioLogeado);
						if(username.length()==10) {
							listaMenusDeRolesDeUsuarioLogeado=restringirMenuAsignacionRolesParaUsuariosConCedula(listaMenusDeRolesDeUsuarioLogeado);
						}
						
						mostrarIngresosSegunMenus();
					}
					cargarDatosUsuarioLogeado();
					redireccionarAPagina("", "preinicio");
				}
			} else {
				addWarningMessage(appBean.getBundle().getString("msg123"));
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private List<Menu> restringirMenuAsignacionRolesParaUsuariosConCedula(List<Menu> menus){
		List<Menu> lst=new ArrayList<>();
		for(Menu m:menus) {
			if(!m.getMenuMnemonic().equals("SIGMA_ASIG_USUARIOS")) {
				lst.add(m);
			}
		}
		return lst;
	}
	
	private void mostrarIngresosSegunMenus() {
		try {
			tieneRolSIGMA = false;
			tieneRolSIS = false;
			for (Menu menu : listaMenusDeRolesDeUsuarioLogeado) {
				if (menu.getMenuMnemonic().startsWith("SIGMA_")) {
					tieneRolSIGMA = true;
				}
				if (menu.getMenuMnemonic().startsWith("SIS_")) {
					tieneRolSIS = true;
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	

	public void llenarYMostrarMenu(String subsis) {
		try {
			List<RolesUser> rolesDeSubSis = new ArrayList<>();
			
			if(subsis.equals("SIGMA")) {
				for (RolesUser ru : listaRolesDeUsuarioLogeado) {
					if (ru.getRole().getRoleName().startsWith(subsis)||ru.getRole().getRoleName().startsWith("REDD+_")) {
						rolesDeSubSis.add(ru);
					}
				}
			}else {
				for (RolesUser ru : listaRolesDeUsuarioLogeado) {
					if (ru.getRole().getRoleName().startsWith(subsis) || ru.getRole().getRoleName().startsWith("REDD+_")) {
						rolesDeSubSis.add(ru);
					}
				}
			}
			

			listaMenusDeRolesDeUsuarioLogeado = menuRolFacade.listarMenusPorRol(rolesDeSubSis);
			if(subsis.equals("SIGMA")){
				Iterator itera = listaMenusDeRolesDeUsuarioLogeado.iterator();
				while(itera.hasNext()){
					Menu menu = (Menu)itera.next();
					if(menu.getMenuMnemonic().startsWith("SIS_")){
						itera.remove();
					}
				}
			}else{
				Iterator itera = listaMenusDeRolesDeUsuarioLogeado.iterator();
				while(itera.hasNext()){
					Menu menu = (Menu)itera.next();
					if(menu.getMenuMnemonic().startsWith("SIGMA_") && !menu.getMenuMnemonic().equals("SIGMA_ASIG_USUARIOS")){
						itera.remove();
					}
				}
			}
			
			if(username.length()==10) {
				listaMenusDeRolesDeUsuarioLogeado=restringirMenuAsignacionRolesParaUsuariosConCedula(listaMenusDeRolesDeUsuarioLogeado);
			}
			llenarMenu();
			moduloActual = subsis;
			controlRolesSis();
			redireccionarAPagina("", "inicio");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void volverAPreinicio(){
		try{
			menuModel = new DefaultMenuModel();
			redireccionarAPagina("", "preinicio");
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	private void llenarMenu() {
		try {
			menuModel = new DefaultMenuModel();
			
			 DefaultMenuItem itemSalir = DefaultMenuItem.builder().value("Inicio") .icon("fa fa-sliders").command("#{ses.volverAPreinicio()}").update("msgs") .build(); 
			 menuModel.getElements().add(itemSalir);
			 

			if (!listaMenusDeRolesDeUsuarioLogeado.isEmpty()) {
				for (Menu mr : listaMenusDeRolesDeUsuarioLogeado) {

					if (mr.getMenuEndNode() == false) {
						DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label(mr.getMenuName())
								.icon(mr.getMenuIcon()).build();
						for (Menu mr1 : listaMenusDeRolesDeUsuarioLogeado) {
							if (mr1.getMenuEndNode() && mr1.getMenu() != null
									&& mr1.getMenu().getMenuId() == mr.getMenuId()) {
								String menuName = mr1.getMenuName().startsWith("lbl")
										? appBean.getBundle().getString(mr1.getMenuName()) : mr1.getMenuName();
								DefaultMenuItem item = DefaultMenuItem.builder().value(menuName).icon(mr1.getMenuIcon())
										.command(mr1.getMenuAction()).update("msgs").build();
								firstSubmenu.getElements().add(item);
							}
						}
						menuModel.getElements().add(firstSubmenu);
					}
				}

			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void cerrarSesion() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(Boolean.FALSE);
			session.invalidate();
			redirect("/sigma-web/index.xhtml?faces-redirect=true&redirected=true");
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public User getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(User usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}

	public List<RolesUser> getListaRolesDeUsuarioLogeado() {
		return listaRolesDeUsuarioLogeado;
	}

	public void setListaRolesDeUsuarioLogeado(List<RolesUser> listaRolesDeUsuarioLogeado) {
		this.listaRolesDeUsuarioLogeado = listaRolesDeUsuarioLogeado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Se encarga de ejecutar una redireccion.
	 *
	 * @param url
	 *            url de destino
	 * @throws IOException
	 *             en caso de no poder hacer la redireccion
	 */
	public void redirect(final String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}

	public void redireccionarAPagina(String carpeta, String pagina) {
		try {
			if (carpeta.equals("")) {
				redirect("/sigma-web/pages/" + pagina + ".xhtml");
			} else {
				redirect("/sigma-web/pages/" + carpeta + "/" + pagina + ".xhtml");
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void redireccionarAPaginaConParametro(String carpeta, String pagina, String paramName, String param) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute(paramName, param);

			redireccionarAPagina(carpeta, pagina);

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void redireccionarAPaginaConParametros(String carpeta, String pagina, String paramName1, String param1,
			String paramName2, Integer param2) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute(paramName1, param1);
			session.setAttribute(paramName2, param2);

			redireccionarAPagina(carpeta, pagina);

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	public void redireccionarAPaginaConParametros(String carpeta, String pagina, String paramName1, String param1,
			String paramName2, String param2, String paramName3, Integer param3) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute(paramName1, param1);
			session.setAttribute(paramName2, param2);
			session.setAttribute(paramName3, param3);

			redireccionarAPagina(carpeta, pagina);

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	


	public void cambiarClave() {
		try {
			String valMsg = "";
			if (claveAnterior.length() < 8) {
				valMsg = valMsg + " Clave anterior no válida.";
			}
			if (nuevaClave.length() < 8) {
				valMsg = valMsg + " Clave nueva no válida.";
			} else {

				if (!usuarioLogeado.getUserPassword().equals(JsfUtil.claveEncriptadaSHA1(claveAnterior))) {
					valMsg = valMsg + " Clave anterior no coincide.";
				}
				if (!nuevaClave.equals(nuevaClaveConf)) {
					valMsg = valMsg + " La nueva clave no coincide.";
				}
				if (claveAnterior.equals(nuevaClave)) {
					valMsg = valMsg + " Nueva clave similar a la anterior.";
				}
			}

			if (valMsg.isEmpty()) {
				usuarioLogeado.setUserPassword(JsfUtil.claveEncriptadaSHA1(nuevaClave));
				usuarioLogeado.setUserDateUpdate(new Date());
				usuarioLogeado.setUserUserUpdate(usuarioLogeado.getUserName());
				usuFacade.updateUser(usuarioLogeado);
				nuevaClave = "";
				nuevaClaveConf = "";
				cerrarSesion();
			} else {
				addErrorMessage(valMsg);
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	

	public void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public void addWarningMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public void addSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	public void mensajeErrorComponente(String idElemento, String detalle) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(idElemento, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", detalle));
	}

	public String obtenerIp() {
		// String ipCliente = getRequest().getRemoteAddr();
		String ipCliente = getRequest().getHeader("X-FORWARDED-FOR");
		if (ipCliente == null) {
			ipCliente = getRequest().getRemoteAddr();
		}
		return ipCliente;
	}

	public HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request == null) {
			throw new RuntimeException("No se pudo recuperar HttpServletRequest");
		}
		return request;
	}

	public String getRequestHeader(String nombre) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestHeaderMap().get(nombre);
	}

	public List<String[]> validacionMsg(String idElemento, String msg1, List<String[]> listaGeneral) {
		try {
			String[] msg = { idElemento, msg1 };
			listaGeneral.add(msg);
			return listaGeneral;
		} catch (Exception ex) {

			LOG.log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void addErrorMessagesForComponentes(List<String[]> listaMsg) {
		for (String[] s : listaMsg) {
			mensajeErrorComponente(s[0], s[1]);
		}
		PrimeFaces.current().focus(listaMsg.get(0)[0]);
	}

	public String getNuevaClave() {
		return nuevaClave;
	}

	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	public String getNuevaClaveConf() {
		return nuevaClaveConf;
	}

	public void setNuevaClaveConf(String nuevaClaveConf) {
		this.nuevaClaveConf = nuevaClaveConf;
	}

	public String getClaveAnterior() {
		return claveAnterior;
	}

	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = claveAnterior;
	}

	public String getMailRecuperarClave() {
		return mailRecuperarClave;
	}

	public void setMailRecuperarClave(String mailRecuperarClave) {
		this.mailRecuperarClave = mailRecuperarClave;
	}

	public String obtenerIdUsuarioSocio() {
		String id = "";

		if (usuarioLogeado != null && usuarioLogeado.getUserId() != null && !listaRolesDeUsuarioLogeado.isEmpty()) {
			for (RolesUser r : listaRolesDeUsuarioLogeado) {
				if (r.getRole().getRoleName().contains("SIGMA_SOCIO")
						|| r.getRole().getRoleName().contains("SIGMA_IMPLEMENTADOR")) {
					if (r.getRousDescription() != null && r.getRousDescription().length() >= 13) {
						id = r.getRousDescription().substring(r.getRousDescription().length() - 13);
					}
				}
			}
		}

		return id;
	}

	public Integer obtenerProjIdDeUsuarioConRolImplementador() {
		Integer id = 0;

		if (usuarioLogeado != null && usuarioLogeado.getUserId() != null && !listaRolesDeUsuarioLogeado.isEmpty()) {
			for (RolesUser r : listaRolesDeUsuarioLogeado) {
				if (r.getRole().getRoleName().contains("SIGMA_IMPLEMENTADOR")) {
					// if(r.getRousDescription()!=null&&r.getRousDescription().length()>=13){
					// id=r.getRousDescription().substring(r.getRousDescription().length()
					// - 13);
					// }
				}
			}
		}

		return id;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public boolean accesoAMenu(String mnemonicPage) {
		boolean r = false;
		try {
			// programacion para evaluar rolesDeUsuario con sus menus permitidos
			if (listaMenusDeRolesDeUsuarioLogeado != null) {
				for (Menu m : listaMenusDeRolesDeUsuarioLogeado) {
					if (m.getMenuMnemonic().contains(mnemonicPage)) {
						r = true;
					}
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return r;
	}

	public boolean visualizacionSegunRol(String nameRol) {
		boolean r = false;
		try {

			if (listaRolesDeUsuarioLogeado != null && !listaRolesDeUsuarioLogeado.isEmpty()) {
				for (RolesUser rolu : listaRolesDeUsuarioLogeado) {
					if (rolu.getRole().getRoleName().contains(nameRol)) {
						r = true;
					}
				}
			}

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return r;
	}

	public boolean urlValida(String url) {
		boolean b = false;
		try {
			Pattern p = Pattern
					.compile("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$");
			Matcher m;
			m = p.matcher(url);
			b = m.matches();

			if (b) {
				if (!url.startsWith("http://") && !url.startsWith("https://")) {
					b = false;
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return b;
	}

	public boolean emailValido(String email) {
		boolean b = false;
		try {
			Pattern p = Pattern.compile(
					"^[_A-Za-z0-9+-]+(?:[.'’][_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(?:\\.[_A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");
			Matcher m;
			m = p.matcher(email);
			b = m.matches();

		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return b;
	}

	public ContribuyenteCompleto obtenerContribuyenteDesdeWSSri(String ruc) {
		ContribuyenteCompleto c = new ContribuyenteCompleto();
		try {
			URL newWsdlLocation = new URL(appBean.getUrlWebServicesBusSnap());
			SuiaServices_Service service1 = new SuiaServices_Service(newWsdlLocation);
			SuiaServices port1 = service1.getSuiaServicesPort();
			c = port1.getRuc(appBean.getSigmaWebServicesUsername(), appBean.getSigmaWebServicesPassword(), ruc);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return c;
	}

	public List<Menu> getListaMenusDeRolesDeUsuarioLogeado() {
		return listaMenusDeRolesDeUsuarioLogeado;
	}

	public void setListaMenusDeRolesDeUsuarioLogeado(List<Menu> listaMenusDeRolesDeUsuarioLogeado) {
		this.listaMenusDeRolesDeUsuarioLogeado = listaMenusDeRolesDeUsuarioLogeado;
	}

	public boolean getTieneRolSIGMA() {
		return tieneRolSIGMA;
	}

	public void setTieneRolSIGMA(boolean tieneRolSIGMA) {
		this.tieneRolSIGMA = tieneRolSIGMA;
	}

	public boolean getTieneRolSIS() {
		return tieneRolSIS;
	}

	public void setTieneRolSIS(boolean tieneRolSIS) {
		this.tieneRolSIS = tieneRolSIS;
	}

	public String getModuloActual() {
		return moduloActual;
	}

	public void setModuloActual(String moduloActual) {
		this.moduloActual = moduloActual;
	}

	private void cargarDatosUsuarioLogeado() {
		try {
			personaLogeada = new People();
			claveAnterior = "";
			
			if (usuarioLogeado.getPeopId() != null) {
				personaLogeada = usuarioLogeado.getPeopId();
				nuevoDatoContacto = new Contact();
				
				//dgv definicion de tipo de usuario: persona u organizacion
				organizacionLogeada=orgaFacade.findByPeopleAndRuc(usuarioLogeado.getPeopId(), usuarioLogeado.getUserName())	;
				if(organizacionLogeada==null){
					tipoUsuario=1;
					//persona natural
					nombreLogeado=personaLogeada.getPeopName();
					personaLogeada.setIdTipoPersona(1);
					listaDatosContactoPersonaLogeada = contactFacade
							.listaContactosPrincipalesPorPersonaUOrganizacion("PERSONA",personaLogeada.getPeopId());
					listaOtrosDatosContactoPersonaLogeada = contactFacade
							.listaOtrosContactosPorPersonaUOrganizacion("PERSONA",personaLogeada.getPeopId());
				}else{
					tipoUsuario=2;
					//persona juridica
					nombreLogeado=organizacionLogeada.getOrgaNameOrganization();
					personaLogeada.setIdTipoPersona(2);
					listaDatosContactoPersonaLogeada = contactFacade
							.listaContactosPrincipalesPorPersonaUOrganizacion("ORG",organizacionLogeada.getOrgaId());
					listaOtrosDatosContactoPersonaLogeada = contactFacade
							.listaOtrosContactosPorPersonaUOrganizacion("ORG",organizacionLogeada.getOrgaId());
				}
				if(personaLogeada.getPeopPin().length()==10){
					personaLogeada.setIdTipoDocumento(1);
				}else if(personaLogeada.getPeopPin().length()==13){
					personaLogeada.setIdTipoDocumento(2);
				}else{
					personaLogeada.setIdTipoDocumento(3);
				}
				
				
				if (personaLogeada.getGeloId() != null) {
					for (Object[] o1 : appBean.getLstGeoLocParroquias()) {
						if (Integer.valueOf(String.valueOf(o1[1])).equals(personaLogeada.getGeloId())) {
							idCantonPersonaLogeada = Integer.valueOf(String.valueOf(o1[3]));
							break;
						}
					}
					for (Object[] o2 : appBean.getLstGeoLocCantones()) {
						if (Integer.valueOf(String.valueOf(o2[1])).equals(idCantonPersonaLogeada)) {
							idProvinciaPersonaLogeada = Integer.valueOf(String.valueOf(o2[3]));
							break;
						}
					}
				}
				if (personaLogeada.getTrtyId() == null) {
					personaLogeada.setTrtyId(0);
				}
				if (personaLogeada.getNatiId() == null) {
					personaLogeada.setNatiId(0);
				}
				
				
			} else {
				personaLogeada.setNatiId(0);
				personaLogeada.setTrtyId(0);
				personaLogeada.setGeloId(0);
				listaDatosContactoPersonaLogeada = new ArrayList<>();
				Contact cTelefono = new Contact();
				cTelefono.setCofoId(6);
				listaDatosContactoPersonaLogeada.add(cTelefono);
				Contact cCelular = new Contact();
				cCelular.setCofoId(4);
				listaDatosContactoPersonaLogeada.add(cCelular);
				Contact cCorreo = new Contact();
				cCorreo.setCofoId(5);
				listaDatosContactoPersonaLogeada.add(cCorreo);
				Contact cDireccion = new Contact();
				cDireccion.setCofoId(2);
				listaDatosContactoPersonaLogeada.add(cDireccion);
				listaOtrosDatosContactoPersonaLogeada = new ArrayList<>();
				if (usuarioLogeado.getUserDocuId() != null && usuarioLogeado.getUserDocuId().equals("Cédula")) {
					personaLogeada.setIdTipoPersona(1);
				} else {
					personaLogeada.setIdTipoPersona(2);
				}
			}

			

		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}

	}
	
	public void adicionarInfoContactoAdicional(){
		try{
			if(nuevoDatoContacto.getCofoId()>0&&nuevoDatoContacto.getContValue()!=null&&nuevoDatoContacto.getContValue().length()>0){
				nuevoDatoContacto.setContStatus(true);
				if(tipoUsuario==1){
					nuevoDatoContacto.setPeopId(usuarioLogeado.getPeopId());
				}else{
					nuevoDatoContacto.setOrgaId(organizacionLogeada);
				}
				
				nuevoDatoContacto.setContDateCreate(new Date());
				nuevoDatoContacto.setContUserCreate(usuarioLogeado.getUserName());
				nuevoDatoContacto.setContIpCreate(obtenerIp());
				contactFacade.save(nuevoDatoContacto);
				nuevoDatoContacto = new Contact();
				if(tipoUsuario==1){
					//persona natural
					nuevoDatoContacto.setPeopId(usuarioLogeado.getPeopId());
					listaOtrosDatosContactoPersonaLogeada = contactFacade
							.listaOtrosContactosPorPersonaUOrganizacion("PERSONA",personaLogeada.getPeopId());
				}else{
					//persona juridica
					nuevoDatoContacto.setOrgaId(organizacionLogeada);
					listaOtrosDatosContactoPersonaLogeada = contactFacade
							.listaOtrosContactosPorPersonaUOrganizacion("ORG",organizacionLogeada.getOrgaId());
				}
			}
		}catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}
	}
	
	public void eliminarInfoContactoAdicional(Contact c){
		try{
			c.setContIpUpdate(obtenerIp());
			contactFacade.eliminarLogico(usuarioLogeado.getUserName(), c);
			if(tipoUsuario==1){
				//persona natural
				listaOtrosDatosContactoPersonaLogeada = contactFacade
						.listaOtrosContactosPorPersonaUOrganizacion("PERSONA",personaLogeada.getPeopId());
			}else{
				//persona juridica
				listaOtrosDatosContactoPersonaLogeada = contactFacade
						.listaOtrosContactosPorPersonaUOrganizacion("ORG",organizacionLogeada.getOrgaId());
			}
		}catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}
	}
	
	public void actualizarDatosContactoYParroquia(){
		try{
			personaLogeada.setPeopDateUpdate(new Date());
			personaLogeada.setPeopIpUpdate(obtenerIp());
			personaLogeada.setPeopUserUpdate(usuarioLogeado.getUserName());
			personaFacade.edit(personaLogeada);
			boolean valDatPrin=true;
			for(Contact c:listaDatosContactoPersonaLogeada){
				if(c.getContValue()==null||c.getContValue().length()==0){
					valDatPrin=false;
				}
			}
			if(valDatPrin){
				contactFacade.actualizarListaDeContactosPrincipales(listaDatosContactoPersonaLogeada, usuarioLogeado.getUserName(), obtenerIp());
				addSuccessMessage("Información de Contacto y Ubicación actualizada correctamente.");
			}else{
				addErrorMessage("Falta información de Contacto.");
			}
			
		}catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}
	}

	// SIS

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void controlRolesSis() {
		try {
			List<RolesUser> listaTemp = new ArrayList<RolesUser>();
			listaTemp = getLoginBean().getListaRolesUsuario();
			getLoginBean().setListaRolesUsuario(new ArrayList<RolesUser>());
			for (RolesUser ru : listaTemp) {
				if (ru.getRole().getRoleName().startsWith("SIS_") || ru.getRole().getRoleName().startsWith("REDD+_"))
					getLoginBean().getListaRolesUsuario().add(ru);
			}

			if (getLoginBean().getListaRolesUsuario().size() > 1) {
				for (RolesUser ru : getLoginBean().getListaRolesUsuario()) {
					if (ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_socio_implementador.getEtiqueta())) {
						getLoginBean().setListaProyectosDelSocioImplementador(
//								projectsFacade.listarProyectosSocioImplementador(ru.getRousDescription()));
								projectsFacade.listarProyectosSocioImplementador(UtilsCadenas.ultimosCaracteres(ru.getRousDescription(),13)));
						getLoginBean().setTipoRol(2);
						break;
					}
				}
			} else {
				for (RolesUser ru : getLoginBean().getListaRolesUsuario()) {
					if (ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_tecnico.getEtiqueta())) {
						getLoginBean().setTipoRol(4);
						break;
					} else if (ru.getRole().getRoleName()
							.equals(TipoRolesUsuarioEnum.SIS_socio_estrategico.getEtiqueta())) {
						getLoginBean().setListaProyectosDelSocioEstrategico(projectsStrategicPartnersFacade
								.listaProyectosSocioEstrategico(ru.getRousDescription()));
//								.listaProyectosSocioEstrategico(UtilsCadenas.ultimosCaracteres(ru.getRousDescription(),13)));
						getLoginBean().setTipoRol(3);
						break;
					} else if (ru.getRole().getRoleName()
							.equals(TipoRolesUsuarioEnum.SIS_socio_implementador.getEtiqueta())) {						
						getLoginBean().setListaProyectosDelSocioImplementador(
//								projectsFacade.listarProyectosSocioImplementador(ru.getRousDescription()));
								projectsFacade.listarProyectosSocioImplementador(UtilsCadenas.ultimosCaracteres(ru.getRousDescription(),13)));
						getLoginBean().setTipoRol(2);
						break;
					} else {
						getLoginBean().setTipoRol(1);
						break;
					}
				}

			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}
	}

	public void enviarCorreo(String asunto, List<String> para, List<String> copia, List<String> oculto,
			String mensaje) {
		try {
			usuFacade.enviarMail(appBean.cargarParametrosCorreo(), asunto, para, copia, oculto, mensaje);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		}
	}

	public People getPersonaLogeada() {
		return personaLogeada;
	}

	public void setPersonaLogeada(People personaLogeada) {
		this.personaLogeada = personaLogeada;
	}

	public List<Contact> getListaDatosContactoPersonaLogeada() {
		return listaDatosContactoPersonaLogeada;
	}

	public void setListaDatosContactoPersonaLogeada(List<Contact> listaDatosContactoPersonaLogeada) {
		this.listaDatosContactoPersonaLogeada = listaDatosContactoPersonaLogeada;
	}

	public List<Contact> getListaOtrosDatosContactoPersonaLogeada() {
		return listaOtrosDatosContactoPersonaLogeada;
	}

	public void setListaOtrosDatosContactoPersonaLogeada(List<Contact> listaOtrosDatosContactoPersonaLogeada) {
		this.listaOtrosDatosContactoPersonaLogeada = listaOtrosDatosContactoPersonaLogeada;
	}

	public Integer getIdProvinciaPersonaLogeada() {
		return idProvinciaPersonaLogeada;
	}

	public void setIdProvinciaPersonaLogeada(Integer idProvinciaPersonaLogeada) {
		this.idProvinciaPersonaLogeada = idProvinciaPersonaLogeada;
	}

	public Integer getIdCantonPersonaLogeada() {
		return idCantonPersonaLogeada;
	}

	public void setIdCantonPersonaLogeada(Integer idCantonPersonaLogeada) {
		this.idCantonPersonaLogeada = idCantonPersonaLogeada;
	}

	public Contact getNuevoDatoContacto() {
		return nuevoDatoContacto;
	}

	public void setNuevoDatoContacto(Contact nuevoDatoContacto) {
		this.nuevoDatoContacto = nuevoDatoContacto;
	}

	public Organization getOrganizacionLogeada() {
		return organizacionLogeada;
	}

	public void setOrganizacionLogeada(Organization organizacionLogeada) {
		this.organizacionLogeada = organizacionLogeada;
	}

	public String getNombreLogeado() {
		return nombreLogeado;
	}

	public void setNombreLogeado(String nombreLogeado) {
		this.nombreLogeado = nombreLogeado;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	

}
