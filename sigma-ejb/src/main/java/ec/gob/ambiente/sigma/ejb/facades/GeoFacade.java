package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;

@Stateless
@LocalBean
public class GeoFacade extends AbstractFacade<Project, Integer> {

    /**
     * Default constructor. 
     */
    public GeoFacade() {
    	super(Project.class, Integer.class);		
    }
    
   
    
    public List<Object[]> listarCantGeomAsGeoJsonPorSalvaguarda(String safeCode) throws Exception{
    	List<Object[]> descAndGeoJson=new ArrayList<>();
    	List<Object[]> query1=new ArrayList<>();
    	String sql="SELECT g.gelo_codification_inec,g.gelo_name,soc.part_name,P.proj_short_name,q.ques_content_question, cast(g.gelo_observations as VARCHAR(200)) "+
  " FROM sis.table_responses r, sis.questions q, sigma.safeguards s, geographical_locations g, sis.advance_execution_safeguards a, sigma.projects p, sigma.partners soc"+
  " where r.adex_id=a.adex_id and a.proj_id=p.proj_id and p.part_id=soc.part_id and r.ques_id=q.ques_id and q.safe_id=s.safe_id and "+
  " CAST(r.tare_column_number_two AS INTEGER)=G.gelo_id and length(g.gelo_codification_inec)=4 and safe_code='"+safeCode+"' order by g.gelo_id,p.proj_id";
    	//SE REALIZA UN CAST A LA COLUMNA DE OBSERVACIONES PORQUE EN LA BASE ES DE TIPO CHARACTER FIJO
    	Query q=getEntityManager().createNativeQuery(sql);
    	query1=q.getResultList();
    	if(!query1.isEmpty()){
    		Object[] s=new Object[2];
    		s[0]=obtenerHTMLRegistroSalvaguarda(String.valueOf(query1.get(0)[1]), String.valueOf(query1.get(0)[2]), String.valueOf(query1.get(0)[3]), String.valueOf(query1.get(0)[4]));
    		s[1]=query1.get(0)[5];
    		for(int i=1;i<query1.size();i++){
    			
        		if(!String.valueOf(query1.get(i)[0]).equals(String.valueOf(query1.get(i-1)[0]))){
        			descAndGeoJson.add(s);
        			s=new Object[2];
        			s[0]=obtenerHTMLRegistroSalvaguarda(String.valueOf(query1.get(i)[1]), String.valueOf(query1.get(i)[2]), String.valueOf(query1.get(i)[3]), String.valueOf(query1.get(i)[4]));
            		s[1]=query1.get(i)[5];
        		}else{
        			s[0]=s[0]+obtenerHTMLRegistroSalvaguarda(String.valueOf(query1.get(i)[1]), String.valueOf(query1.get(i)[2]), String.valueOf(query1.get(i)[3]), String.valueOf(query1.get(i)[4]));
        		}
        	}
    		descAndGeoJson.add(s);
    		
    	}
    	
    	
    	
    	return descAndGeoJson;
    }
    
    public String obtenerHTMLRegistroSalvaguarda(String nombreCiudad, String nombreSocio, String nombreProy,String pregunta){
    	String res="";
    	res="<strong>Cantón: </strong><label>"+nombreCiudad+"</label><br/>"
    			+ "<strong>Proyecto: </strong><label>"+nombreProy+"</label><br/>"
    			+ "<strong>Actividad: </strong><label>"+pregunta+"</label><br/><br/>";
    	return res;
    }

}
