package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsGeographicalArea;

/**
 * Session Bean implementation class ProjectsGeographicalAreaFacade
 */
@Stateless
@LocalBean
public class ProjectsGeographicalAreaFacade extends AbstractFacade<ProjectsGeographicalArea, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectsGeographicalAreaFacade() {
    	super(ProjectsGeographicalArea.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsGeographicalArea entidad){
		entidad.setPgarStatus(false);
		edit(entidad);
	}
	
	public List<ProjectsGeographicalArea> listarAreasGeoPorProyectoNivel(Integer projId, String nivel) throws Exception{
		String sql="select ga from ProjectsGeographicalArea ga where ga.pgarStatus=true and ga.projId.projId=:param1 and ga.pgarLevel=:param2 order by ga.pgarId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		camposCondicion.put("param2", nivel);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void eliminarGeosDeProyecto(Integer projId) throws Exception{
		String sql="update sigma.projects_geographical_areas set pgar_status=false where proj_id="+projId+" and pgar_level<>'G';";
		sentenciaNativa(sql);
	}
	
	public void guardarAreasDeProyecto(List<ProjectsGeographicalArea> lista, Integer projId) throws Exception{
		for(ProjectsGeographicalArea a:lista){
			if(a.getPgarId()!=null&&!a.isSeleccionado()){
				eliminarLogico(a);
			}else if(a.getPgarId()==null&&a.isSeleccionado()){
				a.setProjId(new Project(projId));
				create(a);
			}
		}
	}
	public List<ProjectsGeographicalArea> eliminarIdsDeListado(List<ProjectsGeographicalArea> lista) {
		List<ProjectsGeographicalArea> lst=new ArrayList<ProjectsGeographicalArea>();
		for(ProjectsGeographicalArea g:lista ){
			g.setPgarId(null);
			lst.add(g);
		}
		return lst;
	}
	
	public List<ProjectsGeographicalArea> listarTablasProyectos() throws Exception{
		String sql="select ga from ProjectsGeographicalArea ga where ga.pgarStatus=true and ga.pgarLevel='G' and ga.pgarGeoTable is not null order by ga.projId.projId,ga.pgarGeoType";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
}
