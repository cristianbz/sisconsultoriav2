package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;

/**
 * Session Bean implementation class ActionPlanFacade
 */
@Stateless
@LocalBean
public class ActionPlanFacade extends AbstractFacade<ActionPlan, Integer>{

    /**
     * Default constructor. 
     */
	public ActionPlanFacade() {
		super(ActionPlan.class, Integer.class);		
	}
	
	public List<ActionPlan> listarPlanes(){
		String sql="select ap from ActionPlan ap where ap.acplStatus=true order by ap.acplStartDate";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public ActionPlan crear(String userCreator,ActionPlan entidad){
		entidad.setAcplCreatorUser(userCreator);
		entidad.setAcplCreationDate(nowTimespan());
		return create(entidad);
	}
	public ActionPlan editar(String userUpdate,ActionPlan entidad){
		entidad.setAcplUserUpdate(userUpdate);
		entidad.setAcplDateUpdate(nowTimespan());
		return edit(entidad);
	}
	public void eliminarLogico(String userDelete,ActionPlan entidad){
		entidad.setAcplUserUpdate(userDelete);
		entidad.setAcplDateUpdate(nowTimespan());
		entidad.setAcplStatus(false);
		edit(entidad);
	}

	public void eliminar(String userDelete,ActionPlan entidad){
		entidad.setAcplUserUpdate(userDelete);
		entidad.setAcplDateUpdate(nowTimespan());
		remove(entidad);
	}

}
