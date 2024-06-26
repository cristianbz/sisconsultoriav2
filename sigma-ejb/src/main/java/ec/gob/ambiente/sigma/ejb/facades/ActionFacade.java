package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Action;

/**
 * Session Bean implementation class GoalFacade
 */
@Stateless
@LocalBean
public class ActionFacade extends AbstractFacade<Action, Integer>{

    /**
     * Default constructor. 
     */
	public ActionFacade() {
		super(Action.class, Integer.class);		
	}
	
	public void eliminarLogico(Integer actiId){
		String sql="update sigma.actions set acti_status=false where acti_id="+actiId+";";
		sentenciaNativa(sql);
	}
	
	public List<Object[]> listarAccionesResumenPorPlan(Integer acplId) throws Exception{
		String sql="select a.acti_description, a.acti_id, m.meas_id from sigma.actions a, sigma.measures m, sigma.components c where a.meas_id=m.meas_id and m.comp_id=c.comp_id and a.acti_status=true and m.meas_status=true and c.compStatus=true and c.acpl_id="+acplId+ " order by a.acti_id";
		return consultaNativa(sql);
	}
	
	public List<Action> listarAccionesPorMedida(Integer measId) throws Exception{
		String sql="select a from Action a where a.actiStatus=true and a.measId.measId=:param1 order by a.actiId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", measId);
		return findByCreateQuery(sql, camposCondicion);
	}

}
