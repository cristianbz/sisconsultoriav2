package ec.gob.ambiente.sigma.ejb.sis.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Sectors;



@Stateless
@LocalBean
public class SectorsFacade extends AbstractFacade<Sectors, Integer> {


	public SectorsFacade() {
		super(Sectors.class,Integer.class);
	}
	/**
	 * Carga todos los sectores
	 * @return
	 * @throws Exception
	 */
	public List<Sectors> buscarTodosLosSectores() throws Exception{
		String sql="SELECT S FROM Sectors S WHERE S.sectStatus=true";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}

}
