package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsAction;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsActionsCompatibility;

/**
 * Session Bean implementation class ProjectsActionFacade
 */
@Stateless
@LocalBean
public class ProjectsActionFacade extends AbstractFacade<ProjectsAction, Integer> {

	@EJB
	private ProjectsActionsCompatibilityFacade compatibilidadFacade;
    /**
     * Default constructor. 
     */
    public ProjectsActionFacade() {
    	super(ProjectsAction.class, Integer.class);		
    }
	
	public void eliminarLogico(ProjectsAction entidad){
		entidad.setPracStatus(false);
		edit(entidad);
	}
	
	private List<ProjectsAction> obtenerListaConCompatibilidad(List<ProjectsAction> listaInicial, String tipoConsulta, Integer codConsulta) throws Exception{
		if(!listaInicial.isEmpty()){
			List<ProjectsActionsCompatibility> lst=new ArrayList<>();
					if(tipoConsulta.equals("PROY")){
						lst=compatibilidadFacade.listarCompatibilidadAccionesPorProyecto(codConsulta);
					}else if(tipoConsulta.equals("PROV")){
						lst=compatibilidadFacade.listarCompatibilidadAccionesPorProvincia(codConsulta);
					}
			
			
			
			if(!lst.isEmpty()){
				for(ProjectsAction pa:listaInicial){
					List<String[]> comp=new ArrayList<>();
					List<String[]> indi=new ArrayList<>();
					for(ProjectsActionsCompatibility pac:lst){
						if(pac.getPracId().getPracId()==pa.getPracId()){
							String[] s=new String[3];
							if(pac.getActiId()!=null){
								s[0]=pac.getActiId().getMeasId().getMeasCode()+" AC";
								s[1]=pac.getActiId().getActiDescription();
								s[2]=pac.getPacoId()+"";
								comp.add(s);
							}else{
								if(pac.getMeasId()!=null&&pac.getMeasId().getMeasType().equals("M")){
									s[0]=pac.getMeasId().getMeasCode();
									s[1]=pac.getMeasId().getMeasDescription();
									s[2]=pac.getPacoId()+"";
									comp.add(s);
								}else if(pac.getMeasId()!=null&&pac.getMeasId().getMeasType().equals("I")){
									String[] i=new String[3];
									i[0]=pac.getMeasId().getMeasCode();
									i[1]=pac.getMeasId().getMeasDescription();
									i[2]=pac.getPacoId()+"";
									indi.add(i);
								}else{
									s[0]=pac.getCompId().getCompCode();
									s[1]=pac.getCompId().getCompName();
									s[2]=pac.getPacoId()+"";
									comp.add(s);
								}
							}
							
						}
					}
					pa.setListaCompatibilidadPA(comp);
					pa.setListaCompatibilidadIndicadoresPA(indi);
				}
			}
			}
			return listaInicial;
	}
	
	public List<ProjectsAction> listarAccionesySubaccionesPorPlanDeImplementacion(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAction a  where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='A' and a.psobId.projId.projId=:param1 order by a.partId,a.psobId.psobDescription,a.pracCode ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		List<ProjectsAction> res= findByCreateQuery(sql, camposCondicion);
		res=obtenerListaConCompatibilidad(res,"PROY", proyId);
		return res;
    }
	
	public List<ProjectsAction> listarAccionesPorPlanDeImplementacion(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAction a  where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='A' and a.pracParentId is null and a.psobId.projId.projId=:param1 order by a.psobId.psobSequence";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		List<ProjectsAction> res= findByCreateQuery(sql, camposCondicion);
		res=obtenerListaConCompatibilidad(res,"PROY", proyId);
		return res;
    }
	
	public List<ProjectsAction> listarMetasPorProgramaProyecto(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAction a where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='M' and a.psobId.projId.projId=:param1 order by a.pracId ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }
	public List<ProjectsAction> listarIndicadoresPorProyecto(Integer proyId) throws Exception{
    	String sql="select i from ProjectsAction i, ProjectsAction a, ProjectsSpecificObjective o where a.psobId=o and i.pracParentId=a and o.psobStatus=true and a.pracStatus=true and i.pracStatus=true and i.pracType='I' and o.projId.projId=:param1 order by o.psobCode,a.pracCode";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		List<ProjectsAction> res= findByCreateQuery(sql, camposCondicion);
		res=obtenerListaConCompatibilidad(res, "PROY",proyId);
		return res;
    }
	/*public List<ProjectsAction> listarAccionesPorProgramaProyecto(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAction a where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='A' and a.pracParentId is not null and a.psobId.projId.projId=:param1 order by a.pracId ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		return findByCreateQuery(sql, camposCondicion);
    }*/
	public List<ProjectsAction> listarAccionesPorProgramaProyecto(Integer proyId) throws Exception{
    	String sql="select a from ProjectsAction a where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='A' and a.pracParentId is null and a.psobId.projId.projId=:param1 order by a.pracId ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", proyId);
		List<ProjectsAction> res= findByCreateQuery(sql, camposCondicion);
		res=obtenerListaConCompatibilidad(res,"PROY", proyId);
		return res;
    }
	
	public List<Integer> listaIdsAcciones(List<ProjectsAction> listaAcciones){
		List<Integer> lst=new ArrayList<Integer>();
		for(ProjectsAction c: listaAcciones){
			lst.add(c.getPracId());
		}
		return lst;
	}
	
	public ProjectsAction obtenerObjetoActualizadoDesdeLista(List<ProjectsAction> lista, int idAccion) throws Exception{
		ProjectsAction pa=new ProjectsAction();
		for(ProjectsAction p:lista){
			if(p.getPracId()==idAccion){
				pa=p;
				break;
			}
		}
		return pa;
	}
    
	
	
	public List<ProjectsAction> listarIndicadoresPorProvincia(Integer codProvincia) throws Exception{
    	String sql="select a from ProjectsAction a where a.pracStatus=true and a.psobId.psobStatus=true and a.pracType='I' and a.psobId.geloId.geloId=:param1 order by a.psobId.projId.projCode,a.psobId.psobDescription,a.pracParentId.pracCode,a.pracId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", codProvincia);
		List<ProjectsAction> res= findByCreateQuery(sql, camposCondicion);
		res=obtenerListaConCompatibilidad(res, "PROV",codProvincia);
		return res;
    }

}
