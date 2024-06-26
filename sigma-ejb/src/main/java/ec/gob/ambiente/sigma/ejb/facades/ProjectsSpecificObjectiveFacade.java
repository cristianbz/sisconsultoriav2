package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;

/**
 * Session Bean implementation class ProjectsSpecificObjectiveFacade
 */
@Stateless
@LocalBean
public class ProjectsSpecificObjectiveFacade extends AbstractFacade<ProjectsSpecificObjective, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectsSpecificObjectiveFacade() {
    	super(ProjectsSpecificObjective.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsSpecificObjective entidad){
		entidad.setPsobStatus(false);
		edit(entidad);
		sentenciaNativa("update sigma.projects_specific_objectives set psob_status=false where psob_parent_id="+entidad.getPsobId());
		sentenciaNativa("update sigma.projects_financing set prfi_status=false where psob_id="+entidad.getPsobId());
	} 

    public List<ProjectsSpecificObjective> listarObjetivosPorProyecto(Integer proyId) throws Exception{
    	String sql="select s from ProjectsSpecificObjective s where s.psobStatus=true and s.projId.projId=:param1 order by s.psobParentId, s.psobCode,s.geloId  ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
    
    public List<ProjectsSpecificObjective> listarObjetivosPadrePorProyecto(Integer proyId) throws Exception{
    	String sql="select s from ProjectsSpecificObjective s where s.psobStatus=true and s.psobParentId is null and s.psobType='N' and s.projId.projId=:param1 order by s.psobCode ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
    
    public List<ProjectsSpecificObjective> listarObjetivosHijosPorProyecto(Integer proyId) throws Exception{
    	String sql="select s from ProjectsSpecificObjective s where s.psobStatus=true and s.psobParentId is not null and s.psobParentId.psobStatus=true and s.projId.projId=:param1 order by s.geloId.geloCodificationInec,s.psobDescription ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
    public List<ProjectsSpecificObjective> listarObjetivosHijosPorPadre(Integer psobId) throws Exception{
    	String sql="select s from ProjectsSpecificObjective s where s.psobStatus=true and s.psobParentId is not null and s.psobParentId.psobStatus=true and s.psobParentId.psobId=:param1 order by s.geloId.geloCodificationInec,s.psobDescription ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", psobId);
		return findByCreateQuery(sql, camposCondicion);
    }
    
    public List<Integer> listaIdsObjetivos(List<ProjectsSpecificObjective> listaObjetivos){
		List<Integer> lst=new ArrayList<Integer>();
		for(ProjectsSpecificObjective c: listaObjetivos){
			lst.add(c.getPsobId());
		}
		return lst;
	}
    
    public void actualizarProvDeCompObj(ProjectsSpecificObjective compObjPadre,List<Integer> idsProvNuevos,String idsProvAEliminar) throws Exception{
    	for(Integer i:idsProvNuevos){
    		ProjectsSpecificObjective pso=new ProjectsSpecificObjective();
			pso.setProjId(compObjPadre.getProjId());
			pso.setPsobParentId(compObjPadre);
			pso.setPsobCode(compObjPadre.getPsobCode());
			pso.setPsobDescription(compObjPadre.getPsobDescription());
			pso.setPsobSequence(compObjPadre.getPsobSequence());
			pso.setGeloId(new GeographicalLocations(i));
			create(pso);
    	}
    	String condicionEliminar="";
    	if(idsProvAEliminar.length()>0){
    		condicionEliminar= " and s.gelo_id in ("+idsProvAEliminar+")";
    	}else{
    		condicionEliminar= " and s.gelo_id in (-1)";
    	}
    	String sqlUpdate="update sigma.projects_specific_objectives s set psob_status=false where s.psob_parent_id="+compObjPadre.getPsobId()+ condicionEliminar+";";
    	sentenciaNativa(sqlUpdate);
    }
    
    public void eliminarCompObjDeProyecto(Integer projId){
		sentenciaNativa("update sigma.projects_specific_objectives set psob_status=false where proj_id="+projId);
		sentenciaNativa("update sigma.projects_actions set prac_status=false where psob_id in (select psob_id from sigma.projects_specific_objectives where proj_id="+projId+")");
		sentenciaNativa("update sigma.projects_financing set prfi_status=false where psob_id in (select psob_id from sigma.projects_specific_objectives where proj_id="+projId+")");
	} 
    

}
