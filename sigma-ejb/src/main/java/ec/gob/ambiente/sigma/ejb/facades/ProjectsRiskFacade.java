package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsRisk;

/**
 * Session Bean implementation class ProjectsRiskFacade
 */
@Stateless
@LocalBean
public class ProjectsRiskFacade extends AbstractFacade<ProjectsRisk, Integer> {

	
	/**
	 * Default constructor.
	 */
	public ProjectsRiskFacade() {
		super(ProjectsRisk.class, Integer.class);
	}

	public void eliminarLogico(ProjectsRisk entidad) {
		entidad.setPrriStatus(false);
		edit(entidad);
	}

	public List<ProjectsRisk> listarRiesgosPorProyecto(Integer projId) throws Exception {
		String sql = "select r from ProjectsRisk r where r.prriStatus=true and r.projId.projId=:param1 order by r.prriId ";
		Map<String, Object> camposCondicion = new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		List<ProjectsRisk> res= findByCreateQuery(sql, camposCondicion);
		//res=obtenerListaConSalvaguardas(res, projId);
		return res;
	}

	/*private List<ProjectsRisk> obtenerListaConSalvaguardas(List<ProjectsRisk> listaInicial, Integer projId)
			throws Exception {
		if (!listaInicial.isEmpty()) {
			List<ProjectsSafeguard> lst = salvaguardasFacade.listarSalvaguardasRiesgosPorProyecto(projId);
			if (!lst.isEmpty()) {
				for(ProjectsRisk r:listaInicial){
					List<String> salv=new ArrayList<>();
					for(ProjectsSafeguard s:lst){
						if(r.getPrriId()==s.getPrriId().getPrriId()){
							salv.add(s.getSafeId().getSafeCode());
						}
					}
					r.setListaSalvaguardas(salv);
				}
			}
		}
		return listaInicial;
	}*/

	public List<Integer> listaIdsRiesgos(List<ProjectsRisk> listaRiesgos) {
		List<Integer> lst = new ArrayList<Integer>();
		for (ProjectsRisk c : listaRiesgos) {
			lst.add(c.getPrriId());
		}
		return lst;
	}
	
	public void actualizarListaDeRiesgosEnReportes(Integer projId){
		/*
		 * INSERT INTO sigma.tracings_progress(trac_id,prri_id,trpr_status)
  select rep.trac_id,r.prri_id,true from sigma.projects_risks r, sigma.tracings rep 
  where r.prri_status=true and rep.trac_status=true and r.proj_id=rep.proj_id 
  and r.prri_id not in (select prri_id from sigma.tracings_progress where trpr_status=true and prri_id is not null and trac_id=rep.trac_id)

		 */
	}

}
