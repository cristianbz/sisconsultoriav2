package ec.gob.ambiente.sigma.ejb.facades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Contact;
import ec.gob.ambiente.sigma.ejb.entidades.Organization;
import ec.gob.ambiente.sigma.ejb.entidades.People;


@Stateless
public class ContactFacade extends AbstractFacade<Contact, Integer> implements Serializable {

	private static final long serialVersionUID = 1164242243166182670L;

	public ContactFacade() {
		super(Contact.class, Integer.class);		
	}
	
	public List<Contact> listaContactosPrincipalesPorPersonaUOrganizacion(String tipo,Integer cod) throws Exception{
		List<Contact> lst=new ArrayList<Contact>();
		try{
			String cond="";
			if(tipo.equals("PERSONA")){
				cond="c.peopId.peopId";
			}else{
				cond="c.orgaId.orgaId";
			}
			Query query = getEntityManager().createQuery("select c from Contact c where c.contStatus=true and c.cofoId in (2,4,5,6) and "+cond+"=:param1 order by c.cofoId").setParameter("param1",cod);
			lst= (List<Contact>) query.getResultList();
			
			//programacion especial para cuando los contactos no esten completos en la base
			if(lst.size()!=4){
				List<Contact> newlst=new ArrayList<Contact>();
				boolean flag2=false;
				boolean flag4=false;
				boolean flag5=false;
				boolean flag6=false;
				for(Contact c:lst){
					if(c.getCofoId().equals(2)){
						newlst.add(c);
						flag2=true;
					}
				}
				if(!flag2){
					newlst.add(obtenerObjetoContacto(tipo, cod, 2));
				}
				for(Contact c:lst){
					if(c.getCofoId().equals(4)){
						newlst.add(c);
						flag4=true;
					}
				}
				if(!flag4){
					newlst.add(obtenerObjetoContacto(tipo, cod, 4));
				}
				for(Contact c:lst){
					if(c.getCofoId().equals(5)){
						newlst.add(c);
						flag5=true;
					}
				}
				if(!flag5){
					newlst.add(obtenerObjetoContacto(tipo, cod, 5));
				}
				for(Contact c:lst){
					if(c.getCofoId().equals(6)){
						newlst.add(c);
						flag6=true;
					}
				}
				if(!flag6){
					newlst.add(obtenerObjetoContacto(tipo, cod, 6));
				}
				
				if(newlst.size()==4){
					lst=newlst;
				}
				
			}
			
			
		}catch(NoResultException e){
		}
		return lst;
	}
	
	private Contact obtenerObjetoContacto(String tipo, Integer cod, Integer cofoId){
		Contact c=new Contact();
		c.setContStatus(true);
		c.setCofoId(cofoId);
		if(tipo.equals("PERSONA")){
			c.setPeopId(new People(cod));
		}else{
			c.setOrgaId(new Organization(cod));
		}
		return c;
	}
	public List<Contact> listaOtrosContactosPorPersonaUOrganizacion(String tipo, Integer cod) throws Exception{
		List<Contact> lst=new ArrayList<Contact>();
		try{
			String cond="";
			if(tipo.equals("PERSONA")){
				cond="c.peopId.peopId";
			}else{
				cond="c.orgaId.orgaId";
			}
			Query query = getEntityManager().createQuery("select c from Contact c where c.contStatus=true and c.cofoId not in (2,4,5,6) and "+cond+"=:param1 order by c.cofoId").setParameter("param1",cod);
			lst= (List<Contact>) query.getResultList();
		}catch(NoResultException e){
		}
		return lst;
	}
	
	public void actualizarListaDeContactosPrincipales(List<Contact> lst,String userUpdate,String ipUpdate) throws Exception{
		for(Contact c:lst){
			c.setContDateUpdate(new Date());
			c.setContUserUpdate(userUpdate);
			c.setContIpUpdate(ipUpdate);
			save(c);
		}
	}
	
	private Integer obtenerSiguienteId() throws Exception{
		Integer i=0;
		Query query = getEntityManager().createQuery("select max(c.contId)+1 from Contact c");
		i= (Integer) query.getSingleResult();
		return i;
	}
	
	
	

	public boolean save(Contact contact)
	{
		boolean result = false;
		try
		{
			if(contact.getContId()==null){
				contact.setContId(obtenerSiguienteId()); 
				contact.setContStatus(true);
				create(contact);
			}else{
				edit(contact);
			}
			result = true;
		}
		catch(Exception ex)
		{
			result = false;
		}
		return result;
	}
	
	public void eliminarLogico(String userDelete,Contact contacto) throws Exception{
		contacto.setContStatus(false);
		contacto.setContUserUpdate(userDelete);
		contacto.setContDateUpdate(new Date());
		edit(contacto);
	}
	
	
}