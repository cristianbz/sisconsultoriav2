package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Partner;

/**
 * Session Bean implementation class PartnerFacade
 */
@Stateless
@LocalBean
public class PartnerFacade extends AbstractFacade<Partner, Integer> {

	/**
	 * Default constructor.
	 */
	public PartnerFacade() {
		super(Partner.class, Integer.class);
	}

	public Partner crear(String userCreate, Partner entidad) throws Exception {
		entidad.setPartCreatorUser(userCreate);
		entidad.setPartCreationDate(nowTimespan());
		create(entidad);
		return entidad;
	}

	public Partner editar(String userUpdate, Partner entidad) throws Exception {
		entidad.setPartUserUpdate(userUpdate);
		entidad.setPartDateUpdate(nowTimespan());
		edit(entidad);
		return entidad;
	}

	public void eliminarLogico(String userDelete, Partner entidad) throws Exception {
		entidad.setPartUserUpdate(userDelete);
		entidad.setPartDateUpdate(nowTimespan());
		entidad.setPartStatus(false);
		edit(entidad);
	}

	public List<Partner> listarSocioImplementadorPorIdentificacion(String identificacion) throws Exception {
		String sql = "select p from Partner p where p.partStatus=true and p.partType='I' and p.partIdNumber like :param1 order by p.partIdNumber ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", "%" + identificacion + "%");
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<Partner> listarSocioImplementadorPorNombre(String nombre) throws Exception {
		String sql = "select p from Partner p where p.partStatus=true and p.partType='I' and UPPER(p.partName) like :param1 order by p.partName ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", "%" + nombre.toUpperCase() + "%");
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<Partner> listarSociosEstrategicos() throws Exception {
		String sql = "select p from Partner p where p.partStatus=true and p.partType='E' order by p.partAcronym ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<Partner> listarSocioEstrategicoPorIdentificacion(String identificacion) throws Exception {
		String sql = "select p from Partner p where p.partStatus=true and p.partType='E' and p.partIdNumber like :param1 order by p.partIdNumber ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", "%" + identificacion + "%");
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<Partner> listarSociosEstrategicosPorProyecto(Integer projId) throws Exception {
		String sql = "select ps.partId from ProjectsStrategicPartner ps where ps.pspaStatus=true and ps.partId.partStatus=true and ps.partId.partType='E' and ps.projId.projId=:param1 order by ps.partId.partName ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<Partner> listarSociosImplementadores() throws Exception {
		String sql = "select p from Partner p where p.partStatus=true and p.partType='I' order by p.partName ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}

	// SIS
	/***
	 * Busca socio por codigo
	 */
	public Partner buscarPartnerPorCodigo(Integer codigoSocio) throws Exception {
		String sql = "SELECT PA FROM Partner PA WHERE PA.partId=:codigoSocio";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("codigoSocio", codigoSocio);
		return findByCreateQuerySingleResult(sql, camposCondicion);
	}
	
	public List<Partner> listarSociosEstrategicosPorRucSocioImplementador(String rucSocioImplementador) throws Exception {
		String sql = "select distinct ps.partId from ProjectsStrategicPartner ps where ps.pspaStatus=true and ps.partId.partStatus=true and ps.partId.partType='E' and ps.projId.partId.partIdNumber=:param1 and ps.projId.projStatus=true order by ps.partId.partName ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", rucSocioImplementador);
		return findByCreateQuery(sql, camposCondicion);
	}
}
