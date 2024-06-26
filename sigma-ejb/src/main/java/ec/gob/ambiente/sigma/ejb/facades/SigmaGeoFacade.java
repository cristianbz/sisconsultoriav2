package ec.gob.ambiente.sigma.ejb.facades;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacadeGeo;
import ec.gob.ambiente.sigma.ejb.entidades.Project;

@Stateless
@LocalBean
public class SigmaGeoFacade extends AbstractFacadeGeo<Project, Integer> {

    /**
     * Default constructor. 
     */
    public SigmaGeoFacade() {
    	super(Project.class, Integer.class);		
    }
    
    public List<String> listarGeomAsGeoJson(String geoTableName) throws Exception{
    	List<String> geoJson=new ArrayList<>();
    	String sql="select ST_ASGEOJSON(ST_SimplifyPreserveTopology(geom,0.00005)) from "+geoTableName+"";
    	//String sql="select ST_ASGEOJSON(geom) from "+geoTableName+"";
    	Query q=getEntityManager().createNativeQuery(sql);
    	geoJson=(List<String>)q.getResultList();
    	return geoJson;
    }
    public List<Object[]> listarGeoWithDescAsGeoJson(String geoTableName,String camposDesc) throws Exception{
    	List<Object[]> descAndGeoJson=new ArrayList<>();
    	String sql1="select count(*) from "+geoTableName+"";
    	Query q1=getEntityManager().createNativeQuery(sql1);
    	Integer numRows=Integer.valueOf(String.valueOf(q1.getSingleResult()));
    	
    	String sql="";
    	if(numRows>10000){
    		sql="select "+camposDesc+", ST_ASGEOJSON(ST_SimplifyPreserveTopology(geom,1)) from "+geoTableName+"";
        }else{
    		sql="select "+camposDesc+", ST_ASGEOJSON(ST_SimplifyPreserveTopology(geom,0.00005)) from "+geoTableName+"";
        }
    	Query q=getEntityManager().createNativeQuery(sql);
    	descAndGeoJson=q.getResultList();
    	return descAndGeoJson;
    }
    
   

}
