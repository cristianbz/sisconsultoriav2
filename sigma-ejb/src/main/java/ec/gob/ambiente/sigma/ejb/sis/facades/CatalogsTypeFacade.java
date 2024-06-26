/**
@autor proamazonia [Christian Báez]  27 jul. 2021

**/
package ec.gob.ambiente.sigma.ejb.sis.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.sis.entidades.CatalogTypes;


@LocalBean
@Stateless
public class CatalogsTypeFacade extends AbstractFacade<CatalogTypes, Integer>{
	public CatalogsTypeFacade(){
		super(CatalogTypes.class,Integer.class);
	}
	/**
	 * Recupera los catalogos activos
	 */
	public List<CatalogTypes> listaTipoCatalogos() throws Exception{
		String sql="SELECT CT FROM CatalogTypes CT WHERE CT.catyStatus=TRUE ORDER BY CT.catyId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();		
		return findByCreateQuery(sql, camposCondicion);
	}
	/**
	 * Carga las lineas de accion de genero
	 * @return
	 * @throws Exception
	 */
	public List<CatalogTypes> listaLineasGenero() throws Exception{
		String sql="SELECT CT FROM CatalogTypes CT WHERE CT.catyStatus=TRUE AND CT.catyId IN(33,34,35) ORDER BY CT.catyId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();		
		return findByCreateQuery(sql, camposCondicion);
	}

}

