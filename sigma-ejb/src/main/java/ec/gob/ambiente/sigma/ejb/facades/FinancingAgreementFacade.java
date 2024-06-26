package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.FinancingAgreement;

/**
 * Session Bean implementation class FinancingAgreementFacade
 */
@Stateless
@LocalBean
public class FinancingAgreementFacade extends AbstractFacade<FinancingAgreement, Integer> {

    /**
     * Default constructor. 
     */
    public FinancingAgreementFacade() {
    	super(FinancingAgreement.class, Integer.class);		
    }
    
    public FinancingAgreement crear(String userCreator, FinancingAgreement entidad) throws Exception{
    	entidad.setFiagCreatorUser(userCreator);
    	entidad.setFiagCreationDate(nowTimespan());
    	create(entidad);
    	return entidad;
    }
    public FinancingAgreement editar(String userUpdate, FinancingAgreement entidad) throws Exception{
    	entidad.setFiagUserUpdate(userUpdate);
    	entidad.setFiagDateUpdate(nowTimespan());
    	edit(entidad);
    	return entidad;
    }
	
	public void eliminarLogico(String userDelete,FinancingAgreement entidad){
		entidad.setFiagUserUpdate(userDelete);
    	entidad.setFiagDateUpdate(nowTimespan());
		entidad.setFiagStatus(false);
		edit(entidad);
	}
	
	
	
	public List<FinancingAgreement> listarAcuerdosFinanciamientoPorTitulo(String titulo) throws Exception{
		String sql="select f from FinancingAgreement f where f.fiagStatus=true and UPPER(f.fiagTitle) like :param1 order by f.fiagTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", "%"+titulo.toUpperCase()+"%");
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<FinancingAgreement> listarAcuerdosFinanciamientoPorFuente(Integer fusoId) throws Exception{
		String sql="select f from FinancingAgreement f where f.fiagStatus=true and f.fusoId.fusoId=:param1 order by f.fiagTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", fusoId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<FinancingAgreement> listarAcuerdosFinanciamiento() throws Exception{
		String sql="select f from FinancingAgreement f where f.fiagStatus=true order by f.fiagTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<FinancingAgreement> listarAcuerdoFinanciamientoUnico(Integer fiagId) throws Exception{
		String sql="select f from FinancingAgreement f where f.fiagStatus=true and f.fiagId="+fiagId+" order by f.fiagTitle";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public FinancingAgreement obtenerAcuerdoPorCodigo(String codigo) throws Exception{
		String sql="select a from FinancingAgreement a where a.fiagStatus=true and UPPER(a.fiagCode)=:param1 order by a.fiagTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", codigo.toUpperCase());
		List<FinancingAgreement> lst=findByCreateQuery(sql, camposCondicion);
		if(lst.isEmpty()){
			return null;
		}else{
			return lst.get(0);
		}
		
	}
	

}
