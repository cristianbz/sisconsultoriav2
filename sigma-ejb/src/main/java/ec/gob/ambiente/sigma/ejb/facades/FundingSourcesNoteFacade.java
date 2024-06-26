package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesNote;

/**
 * Session Bean implementation class FundingSourcesNoteFacade
 */
@Stateless
@LocalBean
public class FundingSourcesNoteFacade extends AbstractFacade<FundingSourcesNote, Integer> {

    /**
     * Default constructor. 
     */
    public FundingSourcesNoteFacade() {
    	super(FundingSourcesNote.class, Integer.class);		
    }
	
	public void eliminarLogico(FundingSourcesNote entidad){
		entidad.setFsnoStatus(false);
		edit(entidad);
	}
	
	public List<FundingSourcesNote> listarNotasDeFuenteFinanciamiento(Integer fusoId) throws Exception{
		String sql="select n from FundingSourcesNote n where n.fsnoStatus=true and n.fusoId.fusoId=:param1 order by n.fsnoId ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", fusoId);
		return findByCreateQuery(sql, camposCondicion);
	}
	

}
