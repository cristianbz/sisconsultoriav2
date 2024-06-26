package ec.gob.ambiente.sigma.ejb.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.exceptions.ServiceException;
import ec.gob.ambiente.sigma.ejb.model.Role;



@Stateless
public class RoleFacade extends AbstractFacade <Role, Integer> implements Serializable{

	private static final long serialVersionUID = 2650804737317304970L;

	public RoleFacade() {
		super(Role.class, Integer.class);		
	}
	
	public Role findByName(String rolName) {
		try {
			 Query query = getEntityManager().createQuery("Select o from Role o where o.roleStatus =true and o.roleName =:rolName");
			    query.setParameter("rolName", rolName);    
			    return (Role) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	   
	}
	

	/**
	 * Lista los roles de un usuario
	 * @param Usuario
	 * @return Devuelve null si no encuntra ningun registro
	 */
	@SuppressWarnings("unchecked")
    public List<Role> listRoleByUser(User usuario) throws ServiceException{
        List<Role> result = null;
        try {
        	Query query = getEntityManager().createQuery(" SELECT r FROM RolesUser ru, Role r WHERE ru.role.roleId=r.roleId and ru.user.userId = :userId");		
			query.setParameter("userId",usuario.getUserId());
			return (List<Role>) query.getResultList();
        } catch (NoResultException e) {
        	e.printStackTrace();
			result = null;
		}
        return result;
    }
	
	
	
}