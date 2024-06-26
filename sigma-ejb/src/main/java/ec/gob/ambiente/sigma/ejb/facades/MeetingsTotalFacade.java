package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.MeetingsTotal;

/**
 * Session Bean implementation class GoalFacade
 */
@Stateless
@LocalBean
public class MeetingsTotalFacade extends AbstractFacade<MeetingsTotal, Integer>{

    /**
     * Default constructor. 
     */
	public MeetingsTotalFacade() {
		super(MeetingsTotal.class, Integer.class);		
	}
	
	public List<MeetingsTotal> listarTotalesPorEvento(Integer meetId){
		String sql="select t from MeetingsTotal t where t.metoStatus=true and t.meetId.meetId=:param1 order by t.metoId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", meetId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public void eliminarLogico(MeetingsTotal entidad){
		entidad.setMetoStatus(false);
		edit(entidad);
	}

}
