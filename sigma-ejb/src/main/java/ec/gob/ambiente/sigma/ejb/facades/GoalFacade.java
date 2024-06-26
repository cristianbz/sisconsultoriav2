package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Goal;
import ec.gob.ambiente.sigma.ejb.entidades.SpecificObjective;

/**
 * Session Bean implementation class GoalFacade
 */
@Stateless
@LocalBean
public class GoalFacade extends AbstractFacade<Goal, Integer>{

    /**
     * Default constructor. 
     */
	public GoalFacade() {
		super(Goal.class, Integer.class);		
	}
	
	public List<Goal> listarMetasPorPlan(Integer acplId){
		String sql="select go from Goal go where go.goalStatus=true and go.acplId.acplId=:param1 order by go.goalId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", acplId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public void eliminarLogico(Goal entidad){
		entidad.setGoalStatus(false);
		edit(entidad);
	}

}
