package ec.gob.ambiente.sigma.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.entidades.Partner;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.facades.PartnerFacade;
import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.facades.UserFacade;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
import ec.gob.ambiente.sigma.ejb.services.RoleFacade;
import ec.gob.ambiente.sigma.ejb.services.RolesUserFacade;

@Named("asig")
@ViewScoped
public class AsignacionUsuariosController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AsignacionUsuariosController.class.getName());

	@Inject
	private SesionControlador sesBean;
	@Inject
	private AplicacionControlador appBean;
	
	@EJB
    private RolesUserFacade rolUserFacade;
	@EJB
    private UserFacade usuFacade;
	 @EJB
	    private RoleFacade rolFacade;
	 @EJB
	 private PartnerFacade socioFacade;
	 @EJB
	 private ProjectFacade proyFacade;

	
	
	private String rucActual;
	private String tipoBusqueda;
	private String peopPinBusqueda;
	private List<User> listaUsuariosBusqueda;
	
	private List<RolesUser> listaUsuariosRolDeRuc;
	private String roleParaEnlazar;
	private String tipoAsignacion;
	private List<Project> listaProyectos;
	private Integer idProySeleccionado;
	private List<Partner> listaSociosEstrategicosDeProyecto;
	private String rucSocioEst;
	
	private String rucAEnlazar;
	
	
	
	


	public AsignacionUsuariosController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		try {
			tipoBusqueda="C";
			peopPinBusqueda="";
			listaUsuariosBusqueda=new ArrayList<>();
			listaUsuariosRolDeRuc = new ArrayList<>();
			if(sesBean.getUsuarioLogeado()!=null){
				rucActual=sesBean.getUsuarioLogeado().getUserName();
			}else{
				rucActual="";
			}
			
			roleParaEnlazar="REDD+_SOCIO_IMPLEMENTADOR";
			tipoAsignacion="SOC";
			idProySeleccionado=0;
			listaProyectos=new ArrayList<>();
			rucAEnlazar="";
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void cargarRolesUsuarioDeUsuario(User usu){
		try{
			listaUsuariosRolDeRuc=new ArrayList<>();
			listaUsuariosRolDeRuc=rolUserFacade.listarRolesDeUsuario(usu);			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void listarUsuariosPorPeopPin(){
		try{
			if(peopPinBusqueda.length()==10 && tipoBusqueda.equals("C")){
				listarUsuariosCedulaRuc();
			}else if(peopPinBusqueda.length()<10 || peopPinBusqueda.length()>10 && tipoBusqueda.equals("C")){
				sesBean.addErrorMessage(appBean.getBundle().getString("msg175"));
			}else if(peopPinBusqueda.length()==13 && tipoBusqueda.equals("R")){
				listarUsuariosCedulaRuc();
			}else if(peopPinBusqueda.length()<13 && tipoBusqueda.equals("R")){
				sesBean.addErrorMessage(appBean.getBundle().getString("msg032"));
			}
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void listarUsuariosCedulaRuc(){
		try{
		listaUsuariosBusqueda=new ArrayList<>();
		//se asume que cuando se crea registro en people, se crea en user
		listaUsuariosBusqueda=usuFacade.listarUsuariosPorPeopPin(peopPinBusqueda);
		if(!listaUsuariosBusqueda.isEmpty()){
			sesBean.addSuccessMessage(appBean.getBundle().getString("msg171"));
			cargarRolesUsuarioDeUsuario(listaUsuariosBusqueda.get(0));
		}else{
			sesBean.addErrorMessage(appBean.getBundle().getString("msg172"));
		}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public Integer getIdProySeleccionado() {
		return idProySeleccionado;
	}

	public void setIdProySeleccionado(Integer idProySeleccionado) {
		this.idProySeleccionado = idProySeleccionado;
	}

	public List<Project> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List<Project> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

	public void validarTipoAsignacion(){
		try{
			listaProyectos=new ArrayList<>();
			idProySeleccionado=0;
			if(tipoAsignacion.equals("SOC")){
				listaSociosEstrategicosDeProyecto=socioFacade.listarSociosEstrategicosPorRucSocioImplementador(rucActual);
			}else if(tipoAsignacion.equals("PROY")){
				List<Partner> soc=socioFacade.listarSocioImplementadorPorIdentificacion(rucActual);
				if(soc!=null&&!soc.isEmpty()){
					listaProyectos=proyFacade.listarProyectosPorIdSocioImpl(soc.get(0).getPartId());
					if(roleParaEnlazar.equals("SIS_socio_estrategico")) {
						if(!listaProyectos.isEmpty()) {
							idProySeleccionado=listaProyectos.get(0).getProjId();					
							listaSociosEstrategicosDeProyecto=socioFacade.listarSociosEstrategicosPorProyecto(idProySeleccionado);
						}
					}
					
					
				}
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void listarSociosEstrategicosDeProyectoSeleccionado() {
		try {
			if(idProySeleccionado>0) {
				listaSociosEstrategicosDeProyecto=socioFacade.listarSociosEstrategicosPorProyecto(idProySeleccionado);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	public void asignarRolAUsuarioYEnlazarSocio() {
		try {
			List<String[]> listaMsg=validarAsignacionRol();
			if(listaMsg.isEmpty()){
				
				boolean tieneRol=false;
				if(listaUsuariosRolDeRuc!=null) {
				for(RolesUser ru:listaUsuariosRolDeRuc) {
					if(roleParaEnlazar.equals(ru.getRole().getRoleName())) {
						tieneRol=true;
					}
				}
				}
				if(tieneRol) {
					actualizarRolUsuarioConRuc(roleParaEnlazar, rucAEnlazar);
				}else {
					rolUserFacade.crearRolUsuarioConRUCAsociado(rolFacade.findByName(roleParaEnlazar), listaUsuariosBusqueda.get(0), rucAEnlazar);
				}
				
				cargarRolesUsuarioDeUsuario(listaUsuariosBusqueda.get(0));
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg173").replace("xxx", roleParaEnlazar));

			}else {
				sesBean.addErrorMessagesForComponentes(listaMsg);
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	private void actualizarRolUsuarioConRuc(String rol,String rucAEnlazar) {
		if(listaUsuariosRolDeRuc!=null) {
		for(RolesUser ru:listaUsuariosRolDeRuc) {
			if(ru.getRole().getRoleName().equals(rol)) {
				ru.setRousDescription(rucAEnlazar);
				rolUserFacade.edit(ru);
			}
		}
		}
			
	}
	
	private List<String[]> validarAsignacionRol() {
		List<String[]> listaMsg = new ArrayList<>();
		try {
			if(roleParaEnlazar.equals("REDD+_SOCIO_IMPLEMENTADOR")) {
				rucAEnlazar=rucActual;
				if(tipoAsignacion.equals("PROY")) {
					if(idProySeleccionado<=0) {
						sesBean.validacionMsg("frm:idProy", "Debe seleccionar un proyecto", listaMsg);
					}else {
						rucAEnlazar="PROJ:"+idProySeleccionado+" "+rucActual;
					}
				}
			}else if(roleParaEnlazar.equals("SIS_socio_estrategico")) {
				if(tipoAsignacion.equals("PROY")) {
					if(idProySeleccionado<=0) {
						sesBean.validacionMsg("frm:idProy", "Debe seleccionar un proyecto", listaMsg);
					}else {
						rucAEnlazar="PROJ:"+idProySeleccionado+" "+rucSocioEst;
					}
					//pendiente visualizacion de usuarios con cédula SIS a proyectos especificos
				}else if(tipoAsignacion.equals("SOC")) {
					if(rucSocioEst==null||rucSocioEst.isEmpty()||rucSocioEst.equals("")) {
						sesBean.validacionMsg("frm:rucSocioEst", "Debe seleccionar un socio estratégico", listaMsg);
					}else {
//						rucAEnlazar=rucSocioEst;
						rucAEnlazar = rucSocioEst + " " + rucActual;
					}
				}
			}
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return listaMsg;
	}
	
	
	public void eliminarAsignacion(RolesUser ru){
		try{
			if(!ru.getUser().getUserId().equals(sesBean.getUsuarioLogeado().getUserId())){
				rolUserFacade.eliminarLogico(ru.getRousId());
				cargarRolesUsuarioDeUsuario(listaUsuariosBusqueda.get(0));
				sesBean.addSuccessMessage(appBean.getBundle().getString("msg117"));
			}else{
				sesBean.addErrorMessage(appBean.getBundle().getString("msg179"));
			}
			
		}catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public String getPeopPinBusqueda() {
		return peopPinBusqueda;
	}

	public void setPeopPinBusqueda(String peopPinBusqueda) {
		this.peopPinBusqueda = peopPinBusqueda;
	}

	public List<User> getListaUsuariosBusqueda() {
		return listaUsuariosBusqueda;
	}

	public void setListaUsuariosBusqueda(List<User> listaUsuariosBusqueda) {
		this.listaUsuariosBusqueda = listaUsuariosBusqueda;
	}


	public List<RolesUser> getListaUsuariosRolDeRuc() {
		return listaUsuariosRolDeRuc;
	}

	public void setListaUsuariosRolDeRuc(List<RolesUser> listaUsuariosRolDeRuc) {
		this.listaUsuariosRolDeRuc = listaUsuariosRolDeRuc;
	}



	public String getRoleParaEnlazar() {
		return roleParaEnlazar;
	}

	public void setRoleParaEnlazar(String roleParaEnlazar) {
		this.roleParaEnlazar = roleParaEnlazar;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getRucAEnlazar() {
		return rucAEnlazar;
	}

	public void setRucAEnlazar(String rucAEnlazar) {
		this.rucAEnlazar = rucAEnlazar;
	}

	public List<Partner> getListaSociosEstrategicosDeProyecto() {
		return listaSociosEstrategicosDeProyecto;
	}

	public void setListaSociosEstrategicosDeProyecto(List<Partner> listaSociosEstrategicosDeProyecto) {
		this.listaSociosEstrategicosDeProyecto = listaSociosEstrategicosDeProyecto;
	}

	public String getRucSocioEst() {
		return rucSocioEst;
	}

	public void setRucSocioEst(String rucSocioEst) {
		this.rucSocioEst = rucSocioEst;
	}
	
	
	
	
}
