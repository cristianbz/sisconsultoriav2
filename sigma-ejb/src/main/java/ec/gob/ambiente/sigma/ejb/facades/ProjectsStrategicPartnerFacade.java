package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.exceptions.DaoException;

/**
 * Session Bean implementation class ProjectStrategicPartnerFacade
 */
@Stateless
@LocalBean
public class ProjectsStrategicPartnerFacade extends AbstractFacade<ProjectsStrategicPartner, Integer> {

	/**
	 * Default constructor.
	 */
	public ProjectsStrategicPartnerFacade() {
		super(ProjectsStrategicPartner.class, Integer.class);
	}

	public void eliminarLogico(ProjectsStrategicPartner entidad) throws Exception {
		entidad.setPspaStatus(false);
		edit(entidad);
	}

	public List<ProjectsStrategicPartner> listaSociosEstrategicosPorProyecto(Integer projId) throws Exception {
		String sql = "select sp from ProjectsStrategicPartner sp where sp.pspaStatus=true and sp.projId.projId=:param1 order by sp.pspaType,sp.pspaId";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

	public void eliminarSociosDeProyecto(Integer projId) throws Exception {
		String sql = "update sigma.projects_strategic_partners set pspa_status=false where proj_id=" + projId + ";";
		sentenciaNativa(sql);
	}

	public void guardarSociosDeProyecto(List<ProjectsStrategicPartner> lista, Integer projId) throws Exception {
		for (ProjectsStrategicPartner s : lista) {
			if (s.getPspaId() != null && !s.isSeleccionado()) {
				eliminarLogico(s);
			} else if (s.getPspaId() == null && s.isSeleccionado()) {
				s.setProjId(new Project(projId));
				create(s);
			}
		}
	}

	public void guardarSociosDeProyectoPrimeraVez(List<ProjectsStrategicPartner> lista, Integer projId)
			throws Exception {
		for (ProjectsStrategicPartner s : lista) {
			if (s.getPspaId() == null) {
				s.setProjId(new Project(projId));
				create(s);
			}
		}
	}

	public List<String> listarNombresSocioEstrategicosSeleccionados(List<ProjectsStrategicPartner> lista) {
		List<String> res = new ArrayList<String>();
		for (ProjectsStrategicPartner sp : lista) {
			if (sp.isSeleccionado()) {
				res.add(sp.getPartId().getPartName());
			}
		}
		return res;
	}

	// SIS
	/**
	 * Consulta los partners del proyecto
	 * 
	 * @param codigoProyecto
	 * @return
	 * @throws Exception
	 */
	public List<ProjectsStrategicPartner> listaPartnersActivos(Integer codigoProyecto) throws Exception {
		String sql = "SELECT PSP FROM ProjectsStrategicPartner PSP WHERE  PSP.projId.projId=:codigoProyecto AND PSP.pspaStatus=TRUE";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", codigoProyecto);
		return findByCreateQuery(sql, camposCondicion);
	}

	/**
	 * Obtiene el partner del proyecto
	 * 
	 * @param codigoProyecto
	 * @param codigoPartner
	 * @return
	 * @throws DaoException
	 */
	public ProjectsStrategicPartner partnerDelProyecto(Integer codigoProyecto, Integer codigoPartner)
			throws DaoException {
		try {
			String sql = "SELECT PSP FROM ProjectsStrategicPartner PSP WHERE  PSP.projId.projId=:codigoProyecto AND PSP.pspaId=:codigoPartner AND PSP.pspaStatus=TRUE";
			Map<String, Object> camposCondicion = new HashMap<String, Object>();
			camposCondicion.put("codigoProyecto", codigoProyecto);
			camposCondicion.put("codigoPartner", codigoPartner);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException();
		}
	}

	/**
	 * Devuelve el partner estrategico
	 * 
	 * @param codigoPartner
	 *            El codigo del partner estrategico
	 * @return
	 * @throws DaoException
	 */
	public ProjectsStrategicPartner partnerEstrategico(Integer codigoPartner) throws DaoException {
		try {
			String sql = "SELECT PSP FROM ProjectsStrategicPartner PSP WHERE  PSP.pspaId=:codigoPartner AND PSP.pspaStatus=TRUE";
			Map<String, Object> camposCondicion = new HashMap<String, Object>();
			camposCondicion.put("codigoPartner", codigoPartner);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException();
		}
	}

	/**
	 * Devuelve los proyectos del socio estrategico
	 * 
	 * @param rucSocio
	 *            RUC del socio estrategico
	 * @return Lista de proyectos
	 * @throws Exception
	 */
	public List<ProjectsStrategicPartner> listaProyectosSocioEstrategico(String rucSocio) throws Exception {
		String sql="";
		String valores[]= rucSocio.split(" ");
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		if(valores.length==2 && valores[0].length()==13){
			sql = "SELECT PSP FROM ProjectsStrategicPartner PSP JOIN PSP.projId P JOIN PSP.partId PA JOIN P.partId PART WHERE PSP.partId.partIdNumber=:rucSocio AND PSP.pspaStatus=TRUE AND PART.partIdNumber=:rucImplementador AND P.projRegisterStatus='V' AND P.projStatus=TRUE ORDER BY P.projTitle";
			camposCondicion.put("rucSocio", valores[0]);
			camposCondicion.put("rucImplementador", valores[1]);
		}else{
			
			sql = "SELECT PSP FROM ProjectsStrategicPartner PSP,Project P, Partner PA WHERE PSP.projId.projId = P.projId AND PSP.projId.projId =:codProj  AND PSP.partId.partId = PA.partId AND PA.partIdNumber=:rucSocio AND PSP.pspaStatus=TRUE AND P.projRegisterStatus='V' AND P.projStatus=TRUE ORDER BY P.projTitle";
			camposCondicion.put("rucSocio", valores[1]);
			camposCondicion.put("codProj", Integer.valueOf(valores[0].substring(5)));
		}		
		
		return findByCreateQuery(sql, camposCondicion);
	}
}
