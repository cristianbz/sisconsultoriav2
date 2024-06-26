/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.facades;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Meeting;

/**
 *
 * @author Blueit-Participante
 */
@Stateless
public class MeetingFacade extends AbstractFacade<Meeting,Integer> {

    
    public MeetingFacade() {
        super(Meeting.class,Integer.class);
    }
    
    public void crearEvento(String userCreator,Meeting entidad) throws Exception{
    	entidad.setMeetCreatorUser(userCreator);
    	entidad.setMeetCreationDate(nowTimespan());
    	create(entidad);
    }
    
    public Meeting crearyObtenerEvento(String userCreator,Meeting entidad) throws Exception{
    	entidad.setMeetCreatorUser(userCreator);
    	entidad.setMeetCreationDate(nowTimespan());
    	return create(entidad);
    }
    
    public void editarEvento(String userUpdate,Meeting entidad) throws Exception{
    	entidad.setMeetUserUpdate(userUpdate);
    	entidad.setMeetDateUpdate(nowTimespan());
    	edit(entidad);
    }
    public void eliminarLogico(String userUpdate,Meeting entidad) throws Exception{
    	entidad.setMeetUserUpdate(userUpdate);
    	entidad.setMeetDateUpdate(nowTimespan());
    	entidad.setMeetStatus(false);
		edit(entidad);
	}
    
    public List<Meeting> listaEventosPorProyecto(Integer projId) throws Exception{
        String sql="select m from Meeting m where m.meetStatus=true and m.projId.projId=:param1 order by m.meetDateStart";
    	Map<String, Object> camposCondicion = new HashMap<String, Object>();
        camposCondicion.put("param1", projId);
        return findByCreateQuery(sql, camposCondicion);
    }
    

    public List<Meeting> listaEventosPorProyectoySemestre(Integer projId,int anio, int semestre) throws Exception{
        String rangoIni="";
        String rangoEnd="";
        if(semestre==1){
        	rangoIni="'"+anio+"-01-01'";
        	rangoEnd="'"+anio+"-06-30'";
        }else{
        	rangoEnd="'"+anio+"-12-31'";
        	rangoIni="'"+anio+"-07-01'";
        }
    	String sql="select m from Meeting m where m.meetStatus=true and m.projId.projId=:param1 and m.meetDateStart>="+rangoIni+" and m.meetDateStart<="+rangoEnd+" order by m.meetDateStart";
    	Map<String, Object> camposCondicion = new HashMap<String, Object>();
        camposCondicion.put("param1", projId);
        return findByCreateQuery(sql, camposCondicion);
    }
     
   
}
