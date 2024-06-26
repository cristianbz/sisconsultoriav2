package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Cobenefit;

/**
 * Session Bean implementation class CatalogFacade
 */
@Stateless
@LocalBean
public class CatalogFacade extends AbstractFacade<Catalog, Integer>{

    /**
     * Default constructor. 
     */
	public CatalogFacade() {
		super(Catalog.class, Integer.class);		
	}
	
	public List<Catalog> listarCatalogosPorNemonicosTipos(List<String> lstNemonicos){
		String sql="select ca from Catalog ca where ca.cataStatus=true and ca.catyId.catyMnemonic in :param1 and ca.catyId.catyStatus=true order by ca.catyId.catyMnemonic,ca.cataOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", lstNemonicos);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Integer> listaIdsCatalogos(List<Catalog> listaCatalogos){
		List<Integer> lst=new ArrayList<Integer>();
		for(Catalog c: listaCatalogos){
			lst.add(c.getCataId());
		}
		return lst;
	}
	
	public List<Catalog> listarCatalogosPorTipo(Integer catyId){
		String sql="select ca from Catalog ca where ca.cataStatus=true and ca.catyId.catyId=:param1 and ca.catyId.catyStatus=true order by ca.catyId.catyMnemonic,ca.cataOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", catyId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void eliminarLogico(Integer cataId){
		String sql="update sigma.catalogs set cata_status=false where cata_id="+cataId+";";
		sentenciaNativa(sql);
	}
	
	public String obtenerTextoConcatenadoDeItemsDeCatalogo(List<Catalog> listaItemsCatalogo, String separador) throws Exception{
		String res="";
		for(int i=0;i<=listaItemsCatalogo.size()-2;i++){
			res=res+listaItemsCatalogo.get(i).getCataText1()+separador;
		}
		res=res+listaItemsCatalogo.get(listaItemsCatalogo.size()-1).getCataText1();
		return res;
	}

}
