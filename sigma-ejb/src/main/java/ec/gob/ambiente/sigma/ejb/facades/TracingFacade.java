package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Tracing;

/**
 * Session Bean implementation class TracingFacade
 */
@Stateless
@LocalBean
public class TracingFacade extends AbstractFacade<Tracing, Integer> {

	public TracingFacade() {
		super(Tracing.class, Integer.class);
	}

	public Tracing crear(String userCreate, Tracing entidad) throws Exception {
		entidad.setTracCreatorUser(userCreate);
		entidad.setTracCreationDate(nowTimespan());
		create(entidad);
		return entidad;
	}

	public Tracing editar(String userUpdate, Tracing entidad) throws Exception {
		entidad.setTracUserUpdate(userUpdate);
		entidad.setTracDateUpdate(nowTimespan());
		edit(entidad);
		return entidad;
	}

	public void eliminarLogico(Tracing entidad) {
		entidad.setTracStatus(false);
		entidad.setTracDateUpdate(nowTimespan());
		edit(entidad);
	}
	
	public List<Tracing> listarReportesPorSocio(Integer partId){
		String sql="select t from Tracing t where t.tracStatus=true and t.projId.partId.partId=:param1 order by t.tracId desc";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", partId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Tracing> listarReportesPorTituloProyecto(String titulo){
		String sql="select t from Tracing t where t.tracStatus=true and UPPER(t.projId.projTitle) like :param1 order by t.tracId desc";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", "%"+titulo.toUpperCase()+"%");
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Tracing> listarReportesPorProyecto(Integer projId){
		String sql="select t from Tracing t where t.tracStatus=true and t.projId.projId=:param1 order by t.tracId desc";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1",projId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public boolean existeReportePorSocioYPeriodo(Integer projId,Integer anio, Integer catId, Integer tracId) throws Exception{
		String sql="select t from Tracing t where t.tracStatus=true and t.projId.projStatus=true and t.projId.projId=:param1 and t.tracYear=:param2 and t.cataId.cataId=:param3 order by t.tracId desc";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		camposCondicion.put("param2", anio);
		camposCondicion.put("param3", catId);
		List<Tracing> lst=findByCreateQuery(sql, camposCondicion);
		if(lst.isEmpty()){
			return false;
		}else{
			
			if(lst.size()==1&&lst.get(0).getTracId()==tracId){
				return false;
			}else{
				return true;
			}
			
			
		}
		
	}
	
	public String listaTracIdsParaReportes(Integer acplId, Integer anio, Integer catIdsemestre){
		String tracIds="";
		List<Integer> l = new ArrayList<>();
		List<Object[]> result=new ArrayList<>();
		try{
			if(anio==null||anio==0||catIdsemestre==null||catIdsemestre==0){
				//se busca el ultimo reporte validado
				String sql="select pro.proj_last_report,pro.proj_id from sigma.projects pro where pro.proj_status=true and pro.acpl_id="+acplId+" and proj_last_report is not null";
				result = consultaNativa(sql);
			}else{
				//se busca por plan de accion, año semestre
				String sql="select tra.trac_id,pro.proj_id from sigma.projects pro, sigma.tracings tra where pro.proj_id=tra.proj_id and tra.trac_year="+anio+" and tra.cata_id="+catIdsemestre+" and pro.acpl_id="+acplId+" and tra.trac_status=true and tra.trac_register_status='V'";
				result = consultaNativa(sql);
			}
			if (!result.isEmpty()) {
				for (Object[] o : result) {
					l.add(Integer.valueOf(String.valueOf(o[0])));
				}
			}
			
			if(l.isEmpty()){
				tracIds="'0'";
			}else{
				tracIds="'"+l.get(0)+"'";
				for(int i=1;i<l.size();i++){
					tracIds=tracIds+",'"+l.get(i)+"'";
				}
			}
		}catch(Exception ex){
			System.out.println("error metodo listaTracIdsParaReportes"+ex);
		}
		
		return tracIds;
	}

}
