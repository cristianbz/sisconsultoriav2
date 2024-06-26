package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsProblem;

/**
 * Session Bean implementation class TracingsProblemFacade
 */
@Stateless
@LocalBean
public class TracingsProblemFacade extends AbstractFacade<TracingsProblem , Integer>{


public TracingsProblemFacade() {
    	super(TracingsProblem.class, Integer.class);		
    }
	
	public void eliminarLogico(TracingsProblem entidad){
		entidad.setTrprStatus(false);
		edit(entidad);
	}
	
	public List<TracingsProblem> listarProblemasPorReporte(Integer tracId) throws Exception{
		String sql="select p from TracingsProblem p where p.trprStatus=true and p.tracId.tracId=:param1 order by p.trprId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", tracId);
		return findByCreateQuery(sql, camposCondicion);
	}

}
