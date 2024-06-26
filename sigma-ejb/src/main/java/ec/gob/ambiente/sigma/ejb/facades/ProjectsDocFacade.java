package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsDoc;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsRisk;

/**
 * Session Bean implementation class ProjectsDocFacade
 */
@Stateless
@LocalBean
public class ProjectsDocFacade extends AbstractFacade<ProjectsDoc, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectsDocFacade() {
    	super(ProjectsDoc.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsDoc entidad){
		entidad.setPrdoStatus(false);
		edit(entidad);
	}
	
	public List<ProjectsDoc> listarDocumentosPorProyecto(Integer projId) throws Exception{
		String sql="select d from ProjectsDoc d where d.prdoStatus=true and d.projId.projId=:param1 order by d.prdoId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		return findByCreateQuery(sql, camposCondicion);
	}

}
