package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Measure;

/**
 * Session Bean implementation class MeasureFacade
 */
@Stateless
@LocalBean
public class MeasureFacade extends AbstractFacade<Measure, Integer>{

    /**
     * Default constructor. 
     */
	public MeasureFacade() {
		super(Measure.class, Integer.class);		
	}
	
	public List<Measure> listarMedidasPorComponente(Integer compId) throws Exception{
		String sql="select me from Measure me where me.measStatus=true and me.measType='M' and me.compId.compId=:param1 order by me.measCode";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", compId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Measure> listarIndicadoresPorComponente(Integer compId){
		String sql="select me from Measure me where me.measStatus=true and me.measType='I' and me.compId.compId=:param1 order by me.measCode";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", compId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Measure> listarIndicadoresPorPA(Integer apId){
		String sql="select me from Measure me where me.measStatus=true and me.measType='I' and me.compId.acplId.acplId=:param1 order by me.measCode";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", apId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Measure> listarIndicadoresPorPAParaCompatibilidad(Integer apId){
		String sql="select me from Measure me where me.measStatus=true and me.measType='I' and me.compId.acplId.acplId=:param1 and me.measCode like 'C%' order by me.measCode";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", apId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Object[]> listarMedidasyAccionesPorPlan(Integer acplId){
		String sql="select  c.comp_code, c.comp_name, m.meas_code,m.meas_description, a.acti_description, m.meas_id, a.acti_id, c.comp_id "
				+ "from sigma.components c "
				+ "left join sigma.measures m on c.comp_id=m.comp_id and m.meas_status=true and m.meas_type='M' "
				+ "left join sigma.actions a on m.meas_id=a.meas_id and a.acti_status=true "
				+ "where c.comp_status=true and c.acpl_id="+acplId+" "
				+ "order by c.comp_code,m.meas_code,a.acti_order";
		
		return consultaNativa(sql);
	}
	public void eliminarLogico(Integer measId){
		String sql="update sigma.measures set meas_status=false where meas_id="+measId+";";
		sentenciaNativa(sql);
	}
	
	public List<Object[]> listarMedidasResumenPorPlan(Integer acplId) throws Exception{
		String sql="select m.meas_code, m.meas_description, m.meas_id, c.comp_id from sigma.measures m, sigma.components c where m.comp_id=c.comp_id and m.meas_status=true and m.meas_type='M' and c.comp_status=true and c.acpl_id="+acplId+ " order by m.meas_code";
		return consultaNativa(sql);
	}
	
	public List<Object[]> listarIndicadoresResumenPorPlan(Integer acplId) throws Exception{
		String sql="select m.meas_code, m.meas_description, m.meas_goal, m.meas_id, c.comp_id , c.comp_code, c.comp_name, m.meas_goal_value from sigma.measures m, sigma.components c where m.comp_id=c.comp_id and m.meas_status=true and m.meas_type='I' and c.comp_status=true and c.acpl_id="+acplId+ " order by m.meas_code";
		return consultaNativa(sql);
	}

}
