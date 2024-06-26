/**
@autor proamazonia [Christian Báez]  13 jul. 2021

**/
package ec.gob.ambiente.sigma.ejb.sis.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ProjectQuestions;
import ec.gob.ambiente.sigma.ejb.exceptions.DaoException;


@Stateless
@LocalBean
public class ProjectQuestionsFacade extends AbstractFacade<ProjectQuestions, Integer>{
	
	
	
	public ProjectQuestionsFacade(){
		super(ProjectQuestions.class,Integer.class);
	}
	/**
	 * Lista de preguntas seleccionadas por proyecto
	 * @param codigoProyecto
	 * @return
	 * @throws Exception
	 */
	public List<ProjectQuestions> listaPreguntasProyectoSeleccionadas(Integer codigoProyecto,Integer anio)throws Exception{
		String sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projects.projId=:codigoProyecto AND PQ.prquStatus=TRUE AND PQ.prquYear = :anio";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", codigoProyecto);
		camposCondicion.put("anio", anio);
		return findByCreateQuery(sql, camposCondicion);
	}
	/**
	 * Lista de preguntas seleccionadas por strategic_partner
	 * @param codigoPartner
	 * @return
	 * @throws Exception
	 */
	public List<ProjectQuestions> listaPreguntasPartnerSeleccionadas(Integer codigoPartner,Integer anio)throws Exception{
		String sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projectsStrategicPartners.pspaId=:codigoPartner AND PQ.prquStatus=TRUE AND PQ.prquYear = :anio";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoPartner", codigoPartner);
		camposCondicion.put("anio", anio);
		return findByCreateQuery(sql, camposCondicion);
	}
	/**
	 * Agrega las preguntas y las salvaguardas asignadas al Partner.
	 * @param listaProjectQuestions
	 * @param listaSafeguarsAssigned
	 * @throws Exception
	 */
	public void agregaPreguntasProyecto(List<ProjectQuestions> listaProjectQuestions,boolean esProyecto,int tipo,boolean nuevoIngreso,int anio)throws Exception{
		if(!nuevoIngreso)
			controlPreguntasProyecto(listaProjectQuestions, esProyecto, tipo,anio);
		for (ProjectQuestions preguntas : listaProjectQuestions) {
			if(preguntas.getPrquId() == null)
				create(preguntas);	
			else
				edit(preguntas);
		}

	}
	/**
	 * Controla el movimiento de las preguntas asignadas o removidas
	 * @param listaProjectQuestions
	 * @param esProyecto
	 * @param tipo
	 * @throws Exception
	 */
	public void controlPreguntasProyecto(List<ProjectQuestions> listaProjectQuestions,boolean esProyecto,int tipo,int anio)throws Exception{
		List<ProjectQuestions> listaTemp;
		if(esProyecto)
			listaTemp=listaPreguntasProyectoSeleccionadas(tipo,anio);
		else
			listaTemp=listaPreguntasPartnerSeleccionadas(tipo,anio);
		if(listaTemp.size()== listaProjectQuestions.size()){
			boolean encontrado=false;
			for (ProjectQuestions pqtemp : listaTemp) {
				encontrado = false;
				for (ProjectQuestions pq : listaProjectQuestions) {
					if(pqtemp.getCatalogs().getCataId().equals(pq.getCatalogs().getCataId())){
						pq.setPrquId(pqtemp.getPrquId());
						encontrado=true;
						break;
					}
				}
				if(encontrado==false){
					pqtemp.setPrquStatus(false);
					edit(pqtemp);
				}
			}
		}else if(listaTemp.size()>listaProjectQuestions.size() || listaTemp.size()<listaProjectQuestions.size()){
			for (ProjectQuestions pqtemp : listaTemp) {
				pqtemp.setPrquStatus(false);
				edit(pqtemp);
			}
		}
	}
	/**
	 * Busca las salvaguardas asignadas por proyecto o por socio estrategico
	 * @param codigoProyecto  Es el codigo del proyecto seleccionado
	 * @param codigoSocioEstrategico Es el codigo del socio estratégico seleccionado
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> listaSalvaguardasComponentes(Integer codigoProyecto,Integer codigoSocioEstrategico)throws Exception{
		String sql="";		
		if(codigoProyecto!=null && codigoSocioEstrategico == null)
			sql="SELECT DISTINCT safe_id, prqu_components FROM sis.project_questions WHERE prqu_status=true AND proj_id="+codigoProyecto +" ORDER BY safe_id";			
		else if(codigoSocioEstrategico != null)
			sql="SELECT DISTINCT safe_id, prqu_components FROM sis.project_questions WHERE prqu_status=true AND pspa_id="+codigoSocioEstrategico +" ORDER BY safe_id";							
		return consultaNativa(sql);
	}
	/**
	 * Retorna el numero de preguntas seleccionadas del proyecto
	 * @param codigoProyecto
	 * @return
	 * @throws Exception
	 */
	public Integer numeroPreguntasProyectoSeleccionadas(Integer codigoProyecto)throws Exception{
		String sql="SELECT COUNT(PQ) FROM ProjectQuestions PQ WHERE PQ.projects.projId=:codigoProyecto AND PQ.prquStatus=TRUE";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", codigoProyecto);		
		return (Integer) countAll(sql, camposCondicion);
	}
	/**
	 * Lista el numero de preguntas seleccionadas por strategic_partner
	 * @param codigoPartner
	 * @return
	 * @throws Exception
	 */
	public Integer numeroPreguntasPartnerSeleccionadas(Integer codigoPartner)throws Exception{
		String sql="SELECT COUNT(PQ) FROM ProjectQuestions PQ WHERE PQ.projectsStrategicPartners.pspaId=:codigoPartner AND PQ.prquStatus=TRUE";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoPartner", codigoPartner);
		return (Integer) countAll(sql, camposCondicion);
	}
	/**
	 * Encuentra una pregunta asignada en la tabla project_questions
	 * @param codigoPartner
	 * @param codigoProyecto
	 * @param anio
	 * @param codigoPregunta
	 * @return
	 * @throws Exception
	 */
	public ProjectQuestions buscaPreguntAsignada(Integer codigoPartner, Integer codigoProyecto,Integer anio,Integer codigoPregunta) throws DaoException{
		try{
			String sql="";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			if(codigoPartner == null){
				sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projects.projId=:codigoProyecto AND PQ.prquStatus=TRUE AND PQ.prquYear=:anio AND PQ.catalogs.cataId=:codigoPregunta";
				camposCondicion.put("codigoProyecto", codigoProyecto);
			}else{
				sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projectsStrategicPartners.pspaId=:codigoPartner AND PQ.prquStatus=TRUE AND PQ.prquYear=:anio AND PQ.catalogs.cataId=:codigoPregunta";
				camposCondicion.put("codigoPartner", codigoPartner);
			}
			camposCondicion.put("codigoPregunta", codigoPregunta);
			camposCondicion.put("anio", anio);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	public List<ProjectQuestions> listaPreguntasAsignadas(Integer codigoPartner, Integer codigoProyecto) throws DaoException{
		List<ProjectQuestions> lista;
		try{
			String sql="";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			if(codigoPartner == null){
				sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projects.projId=:codigoProyecto AND PQ.prquStatus=TRUE ORDER BY PQ.prquYear";
				camposCondicion.put("codigoProyecto", codigoProyecto);
			}else{
				sql="SELECT PQ FROM ProjectQuestions PQ WHERE PQ.projectsStrategicPartners.pspaId=:codigoPartner AND PQ.prquStatus=TRUE ORDER BY PQ.prquYear";
				camposCondicion.put("codigoPartner", codigoPartner);
			}	
			lista =findByCreateQuery(sql, camposCondicion);
			return lista;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}
}

