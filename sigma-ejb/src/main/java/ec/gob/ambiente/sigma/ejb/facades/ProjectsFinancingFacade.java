package ec.gob.ambiente.sigma.ejb.facades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancing;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;

/**
 * Session Bean implementation class ProjectsFinancingFacade
 */
@Stateless
@LocalBean
public class ProjectsFinancingFacade extends AbstractFacade<ProjectsFinancing, Integer> {

	/**
	 * Default constructor.
	 */
	public ProjectsFinancingFacade() {
		super(ProjectsFinancing.class, Integer.class);
	}

	public void eliminarLogico(ProjectsFinancing entidad) {
		entidad.setPrfiStatus(false);
		edit(entidad);
	}

	public List<ProjectsFinancing> listarMontosFinanciamientoPorProyecto(Integer projId) throws Exception {

		String sql1  = "select m from ProjectsFinancing m where m.prfiStatus=true and m.projId.projId=:param1 and m.psobId is null order by m.prfiId";
		String sql2 = "select m from ProjectsFinancing m where m.prfiStatus=true and m.projId.projId=:param1  and m.psobId is not null order by m.psobId.psobCode";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		List<ProjectsFinancing> res1 = findByCreateQuery(sql1, camposCondicion);
		List<ProjectsFinancing> res2 = findByCreateQuery(sql2, camposCondicion);
		List<ProjectsFinancing> res = new ArrayList<>();
		res.addAll(res1);
		res.addAll(res2);

		return res;
	}

	public List<ProjectsFinancing> listarMontosFinanciamientoTotalesPorProyecto(Integer projId) throws Exception {

		String sql = "select m from ProjectsFinancing m where m.prfiStatus=true and m.prfiType in ('TOT','TOF','REQ') and m.projId.projId=:param1 order by m.prfiId";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<ProjectsFinancing> listarMontosFinanciamientoObjPorProyecto(Integer projId) throws Exception {

		String sql = "select m from ProjectsFinancing m where m.prfiStatus=true and m.prfiType='OBJ' and m.projId.projId=:param1 order by m.psobId.psobCode";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<ProjectsFinancing> listarMontosFinanciamientoCofPorProyecto(Integer projId) throws Exception {

		String sql = "select m from ProjectsFinancing m where m.prfiStatus=true and m.prfiType in ('COF','PDI','INI','AUT') and m.projId.projId=:param1 order by m.prfiType,m.prfiId";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

	public void crearMontosInicialesDeProyecto(Integer projId) throws Exception {
		ProjectsFinancing montoTotal = new ProjectsFinancing();
		montoTotal.setPrfiType("TOT");
		montoTotal.setProjId(new Project(projId));
		ProjectsFinancing montoFinan = new ProjectsFinancing();
		montoFinan.setPrfiType("TOF");
		montoFinan.setProjId(new Project(projId));
		//ProjectsFinancing montoAutoFinan = new ProjectsFinancing();
		//montoAutoFinan.setPrfiType("AUT");
		///montoAutoFinan.setProjId(new Project(projId));
		ProjectsFinancing montoReq = new ProjectsFinancing();
		montoReq.setPrfiType("REQ");
		montoReq.setProjId(new Project(projId));
		create(montoTotal);
		create(montoFinan);
		//create(montoAutoFinan);
		create(montoReq);
	}

	public void crearMontosDeObjComp(Integer projId, List<Integer> listaIdObj) throws Exception {
		for (Integer i : listaIdObj) {
			ProjectsFinancing m = new ProjectsFinancing();
			m.setProjId(new Project(projId));
			m.setPrfiType("OBJ");
			m.setPsobId(new ProjectsSpecificObjective(i));
			create(m);
		}
	}

	public void crearMontoCof(Integer projId, Integer fusoId, String typeCof) throws Exception {
		ProjectsFinancing m = new ProjectsFinancing();
		m.setProjId(new Project(projId));
		m.setPrfiType(typeCof);
		m.setFusoId(new FundingSource(fusoId));
		create(m);
	}

	public BigDecimal obtenerMontoTotalAsignado(List<ProjectsFinancing> lista) throws Exception {
		BigDecimal m = new BigDecimal("0");
		for (ProjectsFinancing pf : lista) {
			if (pf.getPrfiType().equals("TOT")) {
				m = pf.getPrfiAmount();
			}
		}
		return m;
	}

	public List<BigDecimal> listarMontosObjAsignados(List<ProjectsFinancing> lista) throws Exception {
		List<BigDecimal> lst = new ArrayList<BigDecimal>();
		for (ProjectsFinancing pf : lista) {
			if (pf.getPrfiType().equals("OBJ")) {
				lst.add(pf.getPrfiAmount());
			}
		}
		return lst;
	}

	public List<Integer> listarIdsObjAsignados(List<ProjectsFinancing> lista) throws Exception {
		List<Integer> lst = new ArrayList<Integer>();
		for (ProjectsFinancing pf : lista) {
			if (pf.getPrfiType().equals("OBJ")) {
				lst.add(pf.getPsobId().getPsobId());
				
			}
		}
		return lst;
	}

	public void crearMontosDeNuevosObjetivos(Integer projId) throws Exception {
		crearMontosDeObjComp(projId, listarNuevosObjetivosSinMonto(projId));
	}

	private List<Integer> listarNuevosObjetivosSinMonto(Integer projId) throws Exception {
		List<Integer> l = new ArrayList<>();
		String sql = "select psob_id,psob_status from sigma.projects_specific_objectives where psob_status=true and psob_type='N' and proj_id="
				+ projId
				+ " and psob_id not in (select psob_id from sigma.projects_financing where psob_id is not null and proj_id="
				+ projId + " and prfi_status=true)";
		List<Object[]> result = consultaNativa(sql);

		if (!result.isEmpty()) {
			for (Object[] o : result) {
				l.add(Integer.valueOf(String.valueOf(o[0])));
			}
		}
		return l;
	}

}
