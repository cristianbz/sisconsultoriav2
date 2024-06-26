package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.exceptions.DaoException;

/**
 * Session Bean implementation class SafeguardFacade
 */
@Stateless
@LocalBean
public class SafeguardFacade extends AbstractFacade<Safeguard, Integer> {

    /**
     * Default constructor. 
     */
    public SafeguardFacade() {
    	super(Safeguard.class, Integer.class);		
    }
	
	public void eliminarLogico(Safeguard entidad){
		entidad.setSafeStatus(false);
		edit(entidad);
	}
	
	public List<Safeguard> listarSalvaguardasPorPlanAccion(Integer paId) throws Exception{
		String sql="select s from Safeguard s where s.safeStatus=true and s.safeLevel=1 and s.acplId.acplId=:param1 order by s.safeOrder ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", paId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	//SIS

	/**
	 * Carga todas las salvaguardas registradas
	 */
	public List<Safeguard> buscarTodosLosProyectos() throws Exception{
		String sql="SELECT S FROM Safeguard S WHERE S.safeStatus=true AND S.acplId.acplIscurrent=TRUE AND S.acplId.acplStatus=TRUE ORDER BY S.safeId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();		
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Safeguard> cargaSalvaguardasActivas() throws Exception{
		String sql="SELECT S FROM Safeguard S WHERE S.safeStatus=true AND S.safeParentId IS NULL AND S.acplId.acplIscurrent=TRUE AND S.acplId.acplStatus=TRUE ORDER BY S.safeId";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();		
		return findByCreateQuery(sql, camposCondicion);
	}
	public List<Object[]> listarSalvaguardas() throws Exception {
		String sql = "select safe_id,safe_description,safe_order,safe_code,safe_title from sigma.safeguards WHERE safe_level=1 order by safe_order";
		return consultaNativa(sql);
	}
	/**
	 * Obtiene la salvaguarda en base a su campo clave
	 * @param codigoSalvaguarda Campo clave de la salvaguarda
	 * @return
	 * @throws DaoException
	 */
	public Safeguard obtieneSalvaguarda(int codigoSalvaguarda)throws DaoException{
		try{
			String sql="SELECT S FROM Safeguard S WHERE S.safeId=:codigoSalvaguarda AND S.safeStatus=true AND S.acplId.acplIscurrent=TRUE AND S.acplId.acplStatus=TRUE ORDER BY S.safeId";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			camposCondicion.put("codigoSalvaguarda", codigoSalvaguarda);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}


}
