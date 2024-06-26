package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;

/**
 * Session Bean implementation class GeographicalLocationsFacade
 */
@Stateless
@LocalBean
public class GeographicalLocationsFacade extends AbstractFacade<GeographicalLocations, Integer> {

	/**
	 * Default constructor.
	 */
	public GeographicalLocationsFacade() {
		super(GeographicalLocations.class, Integer.class);
	}

	public void eliminarLogico(GeographicalLocations entidad) {
		entidad.setGeloStatus(false);
		edit(entidad);
	}

	public List<Object[]> listarProvincias() throws Exception {
		String sql = "select g.gelo_name,g.gelo_id,g.gelo_codification_inec from geographical_locations g where g.gelo_parent_id=1 and g.gelo_status=true order by g.gelo_codification_inec";
		return consultaNativa(sql);
	}

	public List<Object[]> listarCantones() throws Exception {
		String sql = "select g.gelo_name,g.gelo_id,g.gelo_codification_inec,g.gelo_parent_id from geographical_locations g "
				+ " where gelo_parent_id in (select g.gelo_id from geographical_locations g where g.gelo_parent_id=1 and g.gelo_status=true) "
				+ " and g.gelo_status=true order by g.gelo_codification_inec";
		return consultaNativa(sql);
	}

	public List<Object[]> listarParroquias() throws Exception {
		String sql = "select g.gelo_name,g.gelo_id,g.gelo_codification_inec,g.gelo_parent_id from geographical_locations g"
				+ " where  gelo_parent_id in ( select g.gelo_id from geographical_locations g "
				+ " where gelo_parent_id in (select g.gelo_id from geographical_locations g where g.gelo_parent_id=1 and g.gelo_status=true)"
				+ " and g.gelo_status=true order by g.gelo_codification_inec )" + " order by g.gelo_codification_inec";
		return consultaNativa(sql);
	}

}
