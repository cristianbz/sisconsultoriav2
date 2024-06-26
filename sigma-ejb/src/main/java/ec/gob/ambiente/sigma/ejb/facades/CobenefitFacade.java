package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Cobenefit;

/**
 * Session Bean implementation class CobenefitFacade
 */
@Stateless
@LocalBean
public class CobenefitFacade extends AbstractFacade<Cobenefit, Integer>{

    /**
     * Default constructor. 
     */
	public CobenefitFacade() {
		super(Cobenefit.class, Integer.class);		
	}
	
	public List<Cobenefit> listarCobeneficiosPorPlan(Integer acplId){
		String sql="select co from Cobenefit co where co.cobeStatus=true and co.compId.acplId.acplId=:param1 and co.compId.compStatus=true order by co.compId.compCode,co.cobeOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", acplId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public void eliminarLogico(Cobenefit entidad){
		entidad.setCobeStatus(false);
		edit(entidad);
	}

}
