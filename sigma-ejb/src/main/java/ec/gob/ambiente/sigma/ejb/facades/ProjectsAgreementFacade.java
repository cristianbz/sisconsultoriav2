package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement;

/**
 * Session Bean implementation class ProjectsAgreement
 */
@Stateless
@LocalBean
public class ProjectsAgreementFacade extends AbstractFacade<ProjectsAgreement, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectsAgreementFacade() {
    	super(ProjectsAgreement.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsAgreement entidad){
		entidad.setPragStatus(false);
		edit(entidad);
	}
	public List<ProjectsAgreement> listarConveniosPorProyecto(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAgreement a where a.pragStatus=true and a.agreId.agreStatus=true and a.projId.projId=:param1 order by a.agreId.agreId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
	
	public List<ProjectsAgreement> listarConvenios() throws Exception{
    	String sql="select a from ProjectsAgreement a where a.pragStatus=true and a.agreId.agreStatus=true order by a.agreId.agreId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
    }
	
	public void eliminarConveniosDeProyecto(Integer projId) throws Exception{
		String sql="update sigma.projects_agreements set prag_status=false where proj_id="+projId+";";
		sentenciaNativa(sql);
	}
	
	public void guardarConvenios(List<ProjectsAgreement> listaConvenios, Integer projId) throws Exception{
		for(ProjectsAgreement pa:listaConvenios){
			if(pa.getPragId()==null){
				pa.setProjId(new Project(projId));
				create(pa);
			}
		}
	}

	
	

}
