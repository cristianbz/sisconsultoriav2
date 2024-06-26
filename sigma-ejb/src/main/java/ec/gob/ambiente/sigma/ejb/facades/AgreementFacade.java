package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Agreement;

/**
 * Session Bean implementation class AgreementFacade
 */
@Stateless
@LocalBean
public class AgreementFacade extends AbstractFacade<Agreement, Integer> {

	
    /**
     * Default constructor. 
     */
    public AgreementFacade() {
    	super(Agreement.class, Integer.class);		
    }
    
    public Agreement crear(String userCreator,Agreement entidad) throws Exception{
    	entidad.setAgreCreatorUser(userCreator);
    	entidad.setAgreCreationDate(nowTimespan());
    	create(entidad);
    	return entidad;
    }
    
    public Agreement editar(String userUpdate,Agreement entidad) throws Exception{
    	entidad.setAgreUserUpdate(userUpdate);
    	entidad.setAgreDateUpdate(nowTimespan());
    	edit(entidad);
    	return entidad;
    }
	
	public void eliminarLogico(String userDelete,Agreement entidad) throws Exception{
		entidad.setAgreUserUpdate(userDelete);
    	entidad.setAgreDateUpdate(nowTimespan());
		entidad.setAgreStatus(false);
		edit(entidad);
	}
	
	public List<Agreement> listarConveniosPorTitulo(String titulo) throws Exception{
		String sql="select a from Agreement a where a.agreStatus=true and UPPER(a.agreTitle) like :param1 order by a.agreTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", "%"+titulo.toUpperCase()+"%");
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Agreement> listarConvenios() throws Exception{
		String sql="select a from Agreement a where a.agreStatus=true order by a.agreTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Agreement> listarConvenioUnico(Integer agreId) throws Exception{
		String sql="select a from Agreement a where a.agreStatus=true and a.agreId="+agreId+" order by a.agreTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public Agreement obtenerConvenioPorCodigo(String codigo) throws Exception{
		String sql="select a from Agreement a where a.agreStatus=true and a.agreRegisterCode=:param1 order by a.agreTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", codigo);
		List<Agreement> lst=findByCreateQuery(sql, camposCondicion);
		if(lst.isEmpty()){
			return null;
		}else{
			return lst.get(0);
		}
		
	}
	public Agreement obtenerConvenioPorTitulo(String titulo) throws Exception{
		String sql="select a from Agreement a where a.agreStatus=true and UPPER(a.agreTitle)=:param1 order by a.agreTitle ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", titulo.toUpperCase());
		List<Agreement> lst=findByCreateQuery(sql, camposCondicion);
		if(lst.isEmpty()){
			return null;
		}else{
			return lst.get(0);
		}
		
	}
	
	
}
