/**
@autor proamazonia [Christian Báez]  4 ago. 2021

**/
package ec.gob.ambiente.sigma.web.sis.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ec.gob.ambiente.sigma.ejb.facades.ProjectFacade;
import ec.gob.ambiente.sigma.ejb.model.RolesUser;
import ec.gob.ambiente.sigma.web.security.LoginBean;
import ec.gob.ambiente.sigma.web.sis.utils.UtilsCadenas;
import ec.gob.ambiente.sigma.web.sis.utils.enumeraciones.TipoRolesUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Named()
@ViewScoped
public class RegistroDeGeneroController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(RegistroDeGeneroController.class);

	

	@Getter
	@Setter
	@Inject
	private MensajesController mensajesController;

	@Inject
	@Getter
	private ComponenteBuscaProyectos componenteBuscarProyectos;
	
	@Getter
    @Setter
    @Inject
    private LoginBean loginBean;
    
	@EJB
	@Getter
	private ProjectFacade projectsFacade;

	@PostConstruct
	private void init(){
		try{
			getComponenteBuscarProyectos().getBuscaProyectosBean().setEsRegistroSalvaguardas(false);
			getComponenteBuscarProyectos().getBuscaProyectosBean().setEsRegistroGenero(true);
			getComponenteBuscarProyectos().getBuscaProyectosBean().setMenuAdministrador(true);
			getComponenteBuscarProyectos().setEsSeguimientoSalvaguardas(false);
			if(getLoginBean().getListaRolesUsuario().size()>1){
				for (RolesUser ru : getLoginBean().getListaRolesUsuario()) {						
					if(ru.getRole().getRoleName().equals(TipoRolesUsuarioEnum.SIS_socio_implementador.getEtiqueta())){					
						getLoginBean().setTipoRol(2);
						getComponenteBuscarProyectos().getBuscaProyectosBean().setRolUsuarioSeleccionado(ru);
						getLoginBean().setListaProyectosDelSocioImplementador(getProjectsFacade().listarProyectosSocioImplementador(UtilsCadenas.ultimosCaracteres(ru.getRousDescription(),13)));
						break;
					}
				}
			}
			getComponenteBuscarProyectos().validarRol();
		}catch(Exception e){
			LOG.error(new StringBuilder().append(this.getClass().getName() + "." + "init" + ": ").append(e.getMessage()));
		}
	}
	/**
	 * Regresa a la busqueda de proyectos
	 */
	public void volverBuscarProyectos(){
		getComponenteBuscarProyectos().volverABuscarProyectos();		
	}
	public boolean datosProyecto(){		
		return getComponenteBuscarProyectos().datosProyecto();
	}
}

