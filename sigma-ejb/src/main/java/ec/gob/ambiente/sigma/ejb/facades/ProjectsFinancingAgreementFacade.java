package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancingAgreement;

/**
 * Session Bean implementation class ProjectsAgreement
 */
@Stateless
@LocalBean
public class ProjectsFinancingAgreementFacade extends AbstractFacade<ProjectsFinancingAgreement, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectsFinancingAgreementFacade() {
    	super(ProjectsFinancingAgreement.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsFinancingAgreement entidad){
		entidad.setPfagStatus(false);
		edit(entidad);
	}
	public List<ProjectsFinancingAgreement> listarAcuerdosPorProyecto(Integer proyId) throws Exception{
    	String sql="select a from ProjectsFinancingAgreement a where a.pfagStatus=true and a.fiagId.fiagStatus=true and a.projId.projId=:param1 order by a.fiagId.fiagId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
	
	
	
	public void eliminarAcuerdosDeProyecto(Integer projId) throws Exception{
		String sql="update sigma.projects_financing_agreements set pfag_status=false where proj_id="+projId+";";
		sentenciaNativa(sql);
	}
	
	public void guardarAcuerdos(List<ProjectsFinancingAgreement> listaAcuerdos, Integer projId) throws Exception{
		for(ProjectsFinancingAgreement pa:listaAcuerdos){
			if(pa.getPfagId()==null){
				pa.setProjId(new Project(projId));
				create(pa);
			}
		}
	}

	
	

}
