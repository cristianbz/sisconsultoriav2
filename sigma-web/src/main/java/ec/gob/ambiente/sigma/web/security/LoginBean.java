package ec.gob.ambiente.sigma.web.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.model.menu.MenuModel;

import ec.gob.ambiente.sigma.ejb.entidades.Organization;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.facades.OrganizationFacade;
import ec.gob.ambiente.sigma.ejb.facades.PeopleFacade;
import ec.gob.ambiente.sigma.ejb.facades.UserFacade;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
//import ec.gob.ambiente.suia.enlisy.services.UserFacade;
import ec.gob.ambiente.sigma.web.utils.JsfUtil;


@SessionScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = -6576425428957899684L;
	
	@EJB
	private UserFacade userFacade;
	
	@EJB
	private PeopleFacade peopleFacade;
	
	@EJB
	private OrganizationFacade organizationFacade;

	private String username;

	private String password;

	private User user;

	private Map<String, Object> content;

	private Long taskId;

	private boolean loggedIn;
	
	private long processInstanceId;
	
	private MenuModel menuModel;
	
	
	private Organization organization;
	
	
	private int tiempoSession;
	@Getter
	@Setter
	private List<ProjectsStrategicPartner> listaProyectosDelSocioEstrategico;
	@Getter
	@Setter
	private List<Project> listaProyectosDelSocioImplementador;
	@Getter
	@Setter
	private List<RolesUser> listaRolesUsuario;
	@Getter
	@Setter
	private HttpSession sesion;
	@Getter
	@Setter
	//1 Admin  2 Socio Implementador  3 Socio estrategico
	private int tipoRol;
	@PostConstruct
	public void init() {
		
		organization=null;

	}
	
	/**
     * Método para cerrar sesión
     *
     * @return
     * @throws IOException 
     * @throws RuntimeException 
     * @throws ServletException 
     */
    public void logout() throws RuntimeException, IOException, ServletException 
    {
    	HttpServletRequest request = JsfUtil.getRequest();
        request.logout();

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();    	
	    JsfUtil.redirect("/index.xhtml?faces-redirect=true");
    }
    
    public void updateUser() throws RuntimeException, IOException, ServletException 
    {    	    	
	    JsfUtil.redirect("/pages/usuario/editarUsuario.xhtml");
    }
    
    public void logoutSessionExpired() throws RuntimeException, IOException {
        HttpServletRequest request = JsfUtil.getRequest();
        request.getSession().invalidate();
        JsfUtil.redirect("/sessionExpired.xhtml");
    }
	
	public String getCompleteName()
	{
		return userFacade.getCompleteNameByUserName(username);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getContent() {
		if (content != null) {
			for (Map.Entry<String, Object> entry : content.entrySet()) {
				System.out.println("Key : " + entry.getKey() + "Value : "
						+ entry.getValue());
			}
		}

		return content;
	}
	
	public void cerrarSessionExpirada() throws RuntimeException, IOException {
		HttpServletRequest request = JsfUtil.getRequest();
		request.getSession().invalidate();
		JsfUtil.redirect("/errors/viewExpired.xhtml");
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;

	}

	public User getUser() {		
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		organization=organizationFacade.findByRuc(user.getUserName());
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getTiempoSession() {
		return tiempoSession;
	}

	public void setTiempoSession(int tiempoSession) {
		this.tiempoSession = tiempoSession;
	}
	
	

}