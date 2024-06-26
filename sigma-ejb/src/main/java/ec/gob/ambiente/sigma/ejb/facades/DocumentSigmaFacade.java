package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.ActionPlan;
import ec.gob.ambiente.sigma.ejb.entidades.Document;

/**
 * Session Bean implementation class ActionPlanFacade
 */
@Stateless
@LocalBean
public class DocumentSigmaFacade extends AbstractFacade<Document, Integer>{

    /**
     * Default constructor. 
     */
	public DocumentSigmaFacade() {
		super(Document.class, Integer.class);		
	}
	
	
	
	public Document crear(String userCreator,Document entidad){
		entidad.setDocuCreatorUser(userCreator);
		entidad.setDocuCreationDate(nowTimespan());
		entidad.setDocuDateUpload(nowTimespan());
		return create(entidad);
	}
	public Document editar(String userUpdate,Document entidad){
		entidad.setDocuUserUpdate(userUpdate);
		entidad.setDocuDateUpdate(nowTimespan());
		return edit(entidad);
	}
	public void eliminarLogico(String userDelete,Document entidad){
		entidad.setDocuUserUpdate(userDelete);
		entidad.setDocuDateUpdate(nowTimespan());
		entidad.setDocuStatus(false);
		edit(entidad);
	}

	

}
