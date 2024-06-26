package ec.gob.ambiente.sigma.ejb.facades;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.CatalogsType;

/**
 * Session Bean implementation class CatalogFacade
 */
@Stateless
@LocalBean
public class CatalogTypeFacade extends AbstractFacade<CatalogsType, Integer>{

    /**
     * Default constructor. 
     */
	public CatalogTypeFacade() {
		super(CatalogsType.class, Integer.class);		
	}
	
	public List<CatalogsType> listarTiposCatalogo(){
		String sql="select ct from CatalogsType ct where ct.catyStatus=true order by ct.catyMnemonic ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		//camposCondicion.put("param1", lstNemonicos);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	

}
