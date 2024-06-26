package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.LegalFrameworkDoc;

/**
 * Session Bean implementation class LegalFrameworkDocFacade
 */
@Stateless
@LocalBean
public class LegalFrameworkDocFacade extends AbstractFacade<LegalFrameworkDoc, Integer>{

    /**
     * Default constructor. 
     */
	public LegalFrameworkDocFacade() {
		super(LegalFrameworkDoc.class, Integer.class);		
	}
	
	public List<LegalFrameworkDoc> listarDocumentosPorPlan(Integer acplId){
		String sql="select doc from LegalFrameworkDoc doc where doc.lfdoStatus=true and doc.acplId.acplId=:param1 order by doc.lfdoName";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", acplId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public void eliminarLogico(LegalFrameworkDoc entidad){
		entidad.setLfdoStatus(false);
		edit(entidad);
	}

}
