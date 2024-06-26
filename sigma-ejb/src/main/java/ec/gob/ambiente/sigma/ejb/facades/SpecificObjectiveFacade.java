package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.SpecificObjective;

/**
 * Session Bean implementation class SpecificObjectiveFacade
 */
@Stateless
@LocalBean
public class SpecificObjectiveFacade extends AbstractFacade<SpecificObjective, Integer>{

    /**
     * Default constructor. 
     */
	public SpecificObjectiveFacade() {
		super(SpecificObjective.class, Integer.class);		
	}
	
	public List<SpecificObjective> listarObjetivosPorPlan(Integer acplId){
		String sql="select so from SpecificObjective so where so.spobStatus=true and so.acplId.acplId=:param1 order by so.spobId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", acplId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public void eliminarLogico(SpecificObjective entidad){
		entidad.setSpobStatus(false);
		edit(entidad);
	}

}
