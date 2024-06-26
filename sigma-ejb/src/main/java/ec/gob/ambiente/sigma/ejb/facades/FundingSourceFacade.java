package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.FundingSource;

/**
 * Session Bean implementation class FundingSourceFacade
 */
@Stateless
@LocalBean
public class FundingSourceFacade extends AbstractFacade<FundingSource, Integer> {

    /**
     * Default constructor. 
     */
    public FundingSourceFacade() {
    	super(FundingSource.class, Integer.class);		
    }
    
    public FundingSource crear(String userCreator,FundingSource entidad) throws Exception{
    	entidad.setFusoCreatorUser(userCreator);
    	entidad.setFusoCreationDate(nowTimespan());
    	if(entidad.getFusoContactPersonPosition()==null||entidad.getFusoContactPersonPosition().length()==0){
    		entidad.setFusoContactPersonPosition(" ");
    	}
    	create(entidad);
    	return entidad;
    }
    
    public FundingSource editar(String userUpdate,FundingSource entidad) throws Exception{
    	entidad.setFusoUserUpdate(userUpdate);
    	entidad.setFusoDateUpate(nowTimespan());
    	edit(entidad);
    	return entidad;
    }
	
	public void eliminarLogico(String userDelete,FundingSource entidad) throws Exception{
		entidad.setFusoStatus(false);
		entidad.setFusoUserUpdate(userDelete);
		entidad.setFusoDateUpate(nowTimespan());
		edit(entidad);
	}
	
	public List<FundingSource> listarFuentesDeFinanciamiento() throws Exception{
		String sql="select f from FundingSource f where f.fusoStatus=true order by f.fusoName";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	public List<FundingSource> listarFuentesDeFinanciamientoPorNombre(String nombre) throws Exception{
		String sql="select f from FundingSource f where f.fusoStatus=true and f.fusoName like :param1 order by f.fusoName";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", "%"+nombre+"%");
		return findByCreateQuery(sql, camposCondicion);
	}

	public List<FundingSource> listarFuentesDeFinanciamientoPorSigla(String sigla) throws Exception{
		String sql="select f from FundingSource f where f.fusoStatus=true and f.fusoCode=:param1 order by f.fusoName";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", sigla);
		return findByCreateQuery(sql, camposCondicion);
	}
}
