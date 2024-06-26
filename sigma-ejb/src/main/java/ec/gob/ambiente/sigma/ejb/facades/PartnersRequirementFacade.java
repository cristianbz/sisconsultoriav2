package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.PartnersRequirement;

/**
 * Session Bean implementation class PartnersRequirement
 */
@Stateless
@LocalBean
public class PartnersRequirementFacade extends AbstractFacade<PartnersRequirement, Integer> {

    /**
     * Default constructor. 
     */
    public PartnersRequirementFacade() {
    	super(PartnersRequirement.class, Integer.class);		
    }
	
	public void eliminarLogico(PartnersRequirement entidad){
		entidad.setPareStatus(false);
		edit(entidad);
	}
	
	public List<PartnersRequirement> listarRequisitosPorSocioImplementador(Integer partId) throws Exception{
		String sql="select pr from PartnersRequirement pr where pr.pareStatus=true and pr.partId.partId=:param1 order by pr.cataId.cataOrder ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", partId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void eliminarRequisitosPorSocioImplementador(Integer partId) throws Exception{
		String sql="update sigma.partners_requirements set pare_status=false where part_id="+partId;
		sentenciaNativa(sql);
		
	}

}
