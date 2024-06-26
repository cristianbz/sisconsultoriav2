package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Organization;
import ec.gob.ambiente.sigma.ejb.entidades.People;

//import ec.gob.ambiente.vo.ProponentVO;

@Stateless
public class OrganizationFacade extends AbstractFacade<Organization, Integer> {

	public OrganizationFacade() {
		super(Organization.class, Integer.class);		
	}
	
	/**
	 * Buscar Institucion u organizacion
	 * @param orgaRuc
	 * @return
	 */	
	public Organization findByRuc(String orgaRuc) {
		try {
			Query query = getEntityManager().createQuery(" select o from Organization o where o.orgaStatus=true and o.orgaRuc = :orgaRuc");		
			query.setParameter("orgaRuc",	orgaRuc);
			return (Organization) query.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
				
	}
	
	
	public boolean save(Organization organization)
	{
		try
		{						
			if (organization.getOrgaId() == null) {
				
				create(organization);

			} else {
				
				edit(organization);
			}

			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/*public List<ProponentVO> getOrganizationsTypeProponentByName(String name, Integer rol)
	{
		List<ProponentVO> proponents = new ArrayList<ProponentVO>();
		
		if(name != null && !name.equals(""))
		{		
			String name_ = "%" + name + "%";
			Query query = super.getEntityManager().createNativeQuery(
					  " select distinct trim(o.orga_name_organization) as proponente,"
					+ " trim(peop_name) as representante, o.orga_ruc"
					+ " from users as u"
					+ " inner join suia_iii.roles_users as ru on u.user_id = ru.user_id"
					+ " inner join people as p on u.peop_id = p.peop_id"
					+ " inner join organizations as o on p.peop_id = o.peop_id"
					+ " where ru.role_id = :rol and o.orga_name_organization <> '' and upper(o.orga_name_organization) like :name")					
					.setParameter("rol", rol)
					.setParameter("name", name_.toUpperCase().trim());
			
			List<Object[]> resultList = (List<Object[]>) query.getResultList();		
	
			if (resultList.size() > 0) {
	
				for (int i = 0; i < resultList.size(); i++) {
					Object[] proponentObject = (Object[]) resultList.get(i);
	
					ProponentVO proponent = new ProponentVO();
					if(proponentObject[0] != null)
					proponent.setName(((String) proponentObject[0]).trim());
					if(proponentObject[1] != null)
					proponent.setLegalRepresentative(((String) proponentObject[1]).trim());					
					if(proponentObject[2] != null)
					proponent.setRuc(((String) proponentObject[2]).trim());
					
					proponents.add(proponent);
				}
			}
		}
		
		return proponents;
	}*/
	
	@SuppressWarnings("unchecked")
	public Organization findByPeopleAndRuc(People people,String orgaRuc) {
		Query query = getEntityManager().createQuery(" select o from Organization o where o.orgaStatus=true and o.peopId.peopId=:peopId and o.orgaRuc = :orgaRuc");
		query.setParameter("peopId",	people.getPeopId());
		query.setParameter("orgaRuc",	orgaRuc);

		List<Organization> resultList = (List<Organization>) query.getResultList();
		if (resultList.size() > 0)
			return resultList.get(0);
		return null;
	}
	
}