package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Project;

@Stateless
@LocalBean
public class ProjectFacade extends AbstractFacade<Project, Integer> {

    /**
     * Default constructor. 
     */
    public ProjectFacade() {
    	super(Project.class, Integer.class);		
    }
    
    public Project crear(String userCreate, Project entidad) throws Exception{
    	entidad.setProjCreatorUser(userCreate);
    	entidad.setProjCreationDate(nowTimespan());
    	entidad.setProjRegisterDate(nowTimespan());
    	entidad.setProjRegisterStatus("C");
    	entidad.setProjFinancingStatus("N");
    	if(!entidad.getProjHasRelationshipOther()||(entidad.getProjHasRelationshipOther()&&entidad.getProjIsFinancier())){
    		entidad.setProjParentId(null);
		}
    	create(entidad);
    	return entidad;
    }
    public Project editar(String userUpdate, Project entidad) throws Exception{
    	entidad.setProjUserUpdate(userUpdate);
    	entidad.setProjDateUpdate(nowTimespan());
    	if(!entidad.getProjHasRelationshipOther()||(entidad.getProjHasRelationshipOther()&&entidad.getProjIsFinancier())){
    		entidad.setProjParentId(null);
		}
    	edit(entidad);
    	return entidad;
    }
	
	public void eliminarLogico(String userDelete, Project entidad) throws Exception{
		entidad.setProjStatus(false);
		entidad.setProjUserUpdate(userDelete);
    	entidad.setProjDateUpdate(nowTimespan());
		edit(entidad);
	}
	
	public List<Project> listarProyectosPorListaCodigosAcciones(List<Integer> lstCodAcciones) throws Exception{
		String sql="select p.pracId.psobId.projId from ProjectsActionsCompatibility p where p.pacoStatus=true and p.pracId.pracStatus=true and p.pracId.psobId.psobStatus=true and p.pracId.psobId.projId.projStatus=true and p.actiId.actiId in :param1 order by p.pracId.psobId.projId.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", lstCodAcciones);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Project> listarProyectosGestoresDeFondos() throws Exception{
		String sql="select p from Project p where p.projStatus=true and p.projIsFinancier=true order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Project> listarProyectosPorIdPlanAccion(Integer acplId) throws Exception{
		String sql="select p from Project p where p.projStatus=true and p.acplId.acplId=:param1 order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", acplId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Project> listarProyectosPorIdSocioImpl(Integer partId) throws Exception{
		String sql="select p from Project p where p.projStatus=true and p.partId.partId=:param1 order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", partId);
		return findByCreateQuery(sql, camposCondicion);
	}
	public List<Project> listarProyectosPorIdSocioImplYUsuarioCreadorOCodigoProyecto(Integer partId,String projCreatorUser,Integer projId) throws Exception{
		String sql="select p from Project p where p.projStatus=true and ((p.partId.partId=:param1 and p.projCreatorUser=:param2) or p.projId=:param3) order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", partId);
		camposCondicion.put("param2", projCreatorUser);
		camposCondicion.put("param3", projId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Project> listarProyectosPorTextoTitulo(String textoTitulo) throws Exception{
		String sql="select p from Project p where p.projStatus=true and UPPER(p.projTitle) like :param1 order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", "%"+textoTitulo.toUpperCase()+"%");
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void actualizarNombreGeoTabla(Integer projId,String geoTableName) throws Exception{
		String sql="update sigma.projects set proj_geo_table='"+geoTableName+"' where proj_id="+projId+";";
		sentenciaNativa(sql);
	}
	
	public String obtenerMetadataSimpleProyecto(Project proyecto, Catalog catalogoEje){
		String m="";
		
			m=""+proyecto.getProjShortName()+" : "+catalogoEje.getCataText1();
		
		return m;
	}
	public String obtenerMetadataExtensaProyecto(Project proyecto){
		String m="";
		m=proyecto.getProjCode()+" <br/>"+proyecto.getProjTitle()+" <br/>"+proyecto.getPartId().getPartName();
		return m;
	}
	
	public List<Project> listarProyectosConGeoTable() throws Exception{
		String sql="select p from Project p where p.projStatus=true and p.projGeoTable is not null order by p.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public String obtenerProyectoConCodigoDuplicado(Integer projId, String projCode) throws Exception{
		String res="";
		String sql="";
		if(projId==null){
			sql="select p from Project p where p.projStatus=true and p.projRegisterStatus='V' and p.projCode=:param1";
		}else{
			sql="select p from Project p where p.projStatus=true and p.projRegisterStatus='V' and p.projCode=:param1 and p.projId<>"+projId;
		}
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", projCode);
		List<Project> lst=findByCreateQuery(sql, camposCondicion);
		if(!lst.isEmpty()){
			res=lst.get(0).getProjTitle();
		}
		return res;
	}
	
	//SIS
	/**
	 * Carga todos los proyectos
	 */
	public List<Project> buscarTodosLosProyectos() throws Exception{
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery("SELECT P FROM Project P WHERE P.projStatus=true AND P.projRegisterStatus='V'", camposCondicion);
	}
	
	public List<Project> listarProyectosSocioImplementador(String ruc) throws Exception{
		String sql="SELECT P FROM Project P WHERE P.projStatus=true AND P.projRegisterStatus='V' AND P.partId.partStatus = TRUE AND P.partId.partIdNumber=:ruc ORDER BY P.projTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("ruc", ruc);
		return findByCreateQuery(sql, camposCondicion);
	}

}
