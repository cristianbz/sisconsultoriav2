package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesOption;

/**
 * Session Bean implementation class FundingSourcesOptionFacade
 */
@Stateless
@LocalBean
public class FundingSourcesOptionFacade extends AbstractFacade<FundingSourcesOption, Integer> {

    /**
     * Default constructor. 
     */
    public FundingSourcesOptionFacade() {
    	super(FundingSourcesOption.class, Integer.class);		
    }
	
	public void eliminarLogico(FundingSourcesOption entidad){
		entidad.setFsopStatus(false);
		edit(entidad);
	}
	
	public List<FundingSourcesOption> listarOpcionesFuenteFinanciamiento(Integer fusoId) throws Exception{
		String sql="select o from FundingSourcesOption o where o.fsopStatus=true and o.fusoId.fusoId=:param1 order by o.cataId.catyId, o.cataId.cataOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", fusoId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void eliminarOpcionesTodas(Integer fusoId) throws Exception{
		String sql="update sigma.funding_sources_options set fsop_status=false where fuso_id="+fusoId+";";
		sentenciaNativa(sql);
	}

}
