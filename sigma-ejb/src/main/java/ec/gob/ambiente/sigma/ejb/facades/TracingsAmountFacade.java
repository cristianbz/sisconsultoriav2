package ec.gob.ambiente.sigma.ejb.facades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;
import ec.gob.ambiente.sigma.ejb.entidades.Tracing;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsAmount;

/**
 * Session Bean implementation class TracingsAmountFacade
 */
@Stateless
@LocalBean
public class TracingsAmountFacade extends AbstractFacade< TracingsAmount, Integer>{


public TracingsAmountFacade() {
    	super(TracingsAmount.class, Integer.class);		
    }
	
	public void eliminarLogico(TracingsAmount entidad){
		entidad.setTramStatus(false);
		edit(entidad);
	}
	
	public void crearMontosReporte(Integer tracId,BigDecimal montoAsignadoTotal, List<Integer> listaObjComp,List<BigDecimal> listaMontosAsigObj,List<Integer> listaCat) throws Exception{
		TracingsAmount totalGeneral=new TracingsAmount();
		totalGeneral.setTracId(new Tracing(tracId));
		totalGeneral.setTramAssignedAmount(montoAsignadoTotal);
		create(totalGeneral);
		for(int i=0; i<listaObjComp.size();i++){
			TracingsAmount m=new TracingsAmount();
			m.setTracId(new Tracing(tracId));
			m.setPsobId(new ProjectsSpecificObjective(listaObjComp.get(i)));
			m.setTramAssignedAmount(listaMontosAsigObj.get(i));
			create(m);
			for(Integer cataId:listaCat){
				TracingsAmount c=new TracingsAmount();
				c.setTracId(new Tracing(tracId));
				c.setPsobId(new ProjectsSpecificObjective(listaObjComp.get(i)));
				c.setCataId(new Catalog(cataId));
				create(c);
			}
		}
	}
	
	public List<TracingsAmount> listarMontosDeReporte(Integer tracId) throws Exception{
		String sql="select t from TracingsAmount t where t.tramStatus=true and t.tracId.tracId=:param1 order by t.tramId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", tracId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<TracingsAmount> listarMontosDeReporteValidadosPorProyectoAnioSemestre(Integer projId, Integer anio, Integer codCatSemestre) throws Exception{
		String sql="select t from TracingsAmount t where t.tramStatus=true and t.tracId.tracRegisterStatus='V' and t.tracId.projId.projId=:param1 and t.tracId.tracYear=:param2 and t.tracId.cataId.cataId=:param3 order by t.tramId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", projId);
		camposCondicion.put("param2", anio);
		camposCondicion.put("param3", codCatSemestre);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public TracingsAmount obtenerMontosTotalesDeReporte(List<TracingsAmount> listaMontos) throws Exception{
		TracingsAmount ta=new TracingsAmount();
		for(TracingsAmount t:listaMontos){
			if(t.getPsobId()==null&&t.getCataId()==null){
				ta=t;
				break;
			}
		}
		return ta;
	}
	
	public List<TracingsAmount> listarMontosDeObjetivosComp(List<TracingsAmount> listaMontos) throws Exception{
		List<TracingsAmount> lst=new ArrayList<>();
		for(TracingsAmount t:listaMontos){
			if(t.getPsobId()!=null&&t.getCataId()==null){
				lst.add(t);
			}
		}
		return lst;
	}
	
	public List<TracingsAmount> listarMontosCategPorObjetivosComp(List<TracingsAmount> listaMontos, Integer psobId) throws Exception{
		List<TracingsAmount> lst=new ArrayList<>();
		for(TracingsAmount t:listaMontos){
			if(t.getPsobId()!=null&&t.getPsobId().getPsobId()==psobId&&t.getCataId()!=null){
				lst.add(t);
			}
		}
		return lst;
	}
	
	public void editarListaMontos(List<TracingsAmount> listaMontos) throws Exception{
		for(TracingsAmount ta:listaMontos){
			edit(ta);
		}
	}

}
