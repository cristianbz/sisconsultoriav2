package ec.gob.ambiente.sigma.ejb.sis.facades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.sis.dto.DtoDatosProyectoResumen;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionProjectGender;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceExecutionSafeguards;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceSectors;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ValueAnswers;
import ec.gob.ambiente.sigma.ejb.exceptions.DaoException;


@Stateless
@LocalBean
public class AdvanceExecutionSafeguardsFacade extends AbstractFacade<AdvanceExecutionSafeguards, Integer>{

	@EJB
	private TableResponsesFacade tableResponsesFacade;
	
	@EJB
	private ValueAnswersFacade valueAnswersFacade;
	
	@EJB
	private AdvanceSectorsFacade advanceSectorsFacade;
	
	@EJB
	private AdvanceExecutionProjectGenderFacade advanceExecutionProjectGenderFacade;
	
	public AdvanceExecutionSafeguardsFacade() {
		super(AdvanceExecutionSafeguards.class,Integer.class);
	}
	/**
	 * Busca si existe un avance de ejecucion para genero o proyecto en base a un periodo de reporte
	 * @param codigoProyecto
	 * @param codigoPartner
	 * @param generoSalvaguarda  1 salvaguarda  2 genero
	 * @param desde
	 * @param hasta
	 * @return
	 * @throws DaoException
	 */
	public AdvanceExecutionSafeguards buscarAvanceSalvaguardaGeneroReportado(int codigoProyecto, int codigoPartner,int generoSalvaguarda,String desde,String hasta) throws DaoException{
		try{
			String sql="";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			if (codigoProyecto>0 && codigoPartner == 0 && generoSalvaguarda == 1){
				sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.projectsStrategicPartners.pspaId=NULL AND AE.adexIsGender = FALSE AND AE.adexTermFrom =:desde AND AE.adexTermTo =:hasta AND AE.adexStatus = TRUE";
				camposCondicion.put("codigoProyecto", codigoProyecto);
				camposCondicion.put("desde", desde);
				camposCondicion.put("hasta", hasta);
			}else if (codigoProyecto>0 && codigoPartner > 0 && generoSalvaguarda == 1){
				sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoPartner AND AE.adexIsGender = FALSE AND AE.adexTermFrom =:desde AND AE.adexTermTo =:hasta AND AE.adexStatus = TRUE";
				camposCondicion.put("codigoProyecto", codigoProyecto);
				camposCondicion.put("codigoPartner", codigoPartner);
				camposCondicion.put("desde", desde);
				camposCondicion.put("hasta", hasta);
			}else if (codigoProyecto>0 && codigoPartner == 0 && generoSalvaguarda == 2){
				sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.projectsStrategicPartners.pspaId=NULL AND AE.adexIsGender = TRUE AND AE.adexTermFrom =:desde AND AE.adexTermTo =:hasta AND AE.adexStatus = TRUE";
				camposCondicion.put("codigoProyecto", codigoProyecto);
				camposCondicion.put("desde", desde);
				camposCondicion.put("hasta", hasta);
			}else if(codigoProyecto>0 && codigoPartner > 0 && generoSalvaguarda == 2){
				sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoPartner AND AE.adexIsGender = TRUE AND AE.adexTermFrom =:desde AND AE.adexTermTo =:hasta AND AE.adexStatus = TRUE";
				camposCondicion.put("codigoProyecto", codigoProyecto);
				camposCondicion.put("codigoPartner", codigoPartner);
				camposCondicion.put("desde", desde);
				camposCondicion.put("hasta", hasta);
			}
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}
	
	/**
	 * Devuelve el avance de ejecucion por proyecto
	 * @param codigoProyecto
	 * @return
	 */	
	public AdvanceExecutionSafeguards buscarPorProyecto(int codigoProyecto) throws DaoException{
		try{
			String sql="SELECT AP FROM AdvanceExecutionSafeguards AP WHERE AP.projects.projId=:codigoProyecto AND AP.projectsStrategicPartners.pspaId=NULL AND AP.adexIsGender = FALSE AND AP.adexIsReported=FALSE";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			camposCondicion.put("codigoProyecto", codigoProyecto);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}
	/**
	 * Devuelve el avance de ejecucion por strategicPartner
	 * @param codigoProyecto
	 * @return
	 */	
	public AdvanceExecutionSafeguards buscarPorStrategicPartner(int codigoPartner,int codigoProyecto) throws DaoException{
		try{
			String sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projectsStrategicPartners.pspaId=:codigoPartner AND AE.projects.projId=:codigoProyecto AND AE.adexIsGender = FALSE AND AE.projectsStrategicPartners.pspaStatus= TRUE AND AE.adexIsReported=FALSE";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			camposCondicion.put("codigoPartner", codigoPartner);
			camposCondicion.put("codigoProyecto", codigoProyecto);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}
	/**
	 * Devuelve el anvance de ejecucion para genero por proyecto 
	 * @param codigoProyecto
	 * @return
	 * @throws DaoException
	 */
	public AdvanceExecutionSafeguards buscarAvanceGeneroPorProyecto(int codigoProyecto) throws DaoException{
		try{
//			String sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.adexIsReported=false AND AE.adexIsGender=true AND AE.adexStatus=true";
			String sql="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.adexIsGender=true AND AE.adexIsReported=false AND AE.adexStatus=true";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			camposCondicion.put("codigoProyecto", codigoProyecto);
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}

	/**
	 * Graba el AvanceEjecucionSalvaguarda
	 * @param avanceEjecucion
	 * @throws Exception
	 */
	public AdvanceExecutionSafeguards grabarAvanceEjecucionSalvaguarda(AdvanceExecutionSafeguards avanceEjecucion) throws Exception{
		if(avanceEjecucion.getAdexId()==null){
			avanceEjecucion = create(avanceEjecucion);
//			for (TableResponses respuestaTabla : avanceEjecucion.getTableResponsesList()) {
//				if(respuestaTabla.getTareId()==null)
//					tableResponsesFacade.create(respuestaTabla);
//				else
//					tableResponsesFacade.edit(respuestaTabla);
//			}
			for (ValueAnswers respuestas : avanceEjecucion.getValueAnswersList()) { 
				respuestas.setAdvanceExecutionSaveguards(avanceEjecucion);
				if(respuestas.getVaanId()==null)
					valueAnswersFacade.create(respuestas);
				else
					valueAnswersFacade.edit(respuestas);
			}
		}else{
			controlaAvencesSectores(avanceEjecucion.getAdvanceSectorsList(), avanceEjecucion.getAdexId());
			for (ValueAnswers respuestas : avanceEjecucion.getValueAnswersList()) {
					valueAnswersFacade.edit(respuestas);
			}
			edit(avanceEjecucion);			
		}
		return avanceEjecucion;

	}
	public void controlaAvencesSectores(List<AdvanceSectors> listaAvanceSectores,int codigoAvanceEjecucion) throws Exception{
		List<AdvanceSectors> listaTemp=advanceSectorsFacade.listaAvanceSectoresPorAvanceEjecucion(codigoAvanceEjecucion);
		if(listaTemp.size()==listaAvanceSectores.size()){
			boolean encontrado=false;
			for (AdvanceSectors asTemp : listaTemp) {
				encontrado=false;
				for (AdvanceSectors as : listaAvanceSectores) {
					if(asTemp.getSectors().getSectId().equals(as.getSectors().getSectId())){
						as.setAdseId(asTemp.getAdseId());
						encontrado=true;
						break;
					}
				}
				if(encontrado==false){
					break;
				}
			}
			if(encontrado==false){
				for (AdvanceSectors asTemp : listaTemp) {
					asTemp.setAdseSelectedSector(false);
					advanceSectorsFacade.eliminarAvanceSectores(asTemp);
				}
			}
			
		}else if(listaTemp.size()>=listaAvanceSectores.size() || listaTemp.size()<=listaAvanceSectores.size()){
			for (AdvanceSectors asTemp : listaTemp) {
				asTemp.setAdseSelectedSector(false);
				advanceSectorsFacade.edit(asTemp);
			}
		}
	}
	
	/**
	 * Registra el avance de ejecucion para genero
	 * @param avanceEjecucion
	 * @return
	 * @throws Exception
	 */
	public AdvanceExecutionSafeguards agregarEditarAvanceEjecucionGenero(AdvanceExecutionSafeguards avanceEjecucion, List<AdvanceExecutionProjectGender> avancesGenero)throws Exception{
		if (avanceEjecucion.getAdexId() == null){
			create(avanceEjecucion);
			for (ValueAnswers respuestas : avanceEjecucion.getValueAnswersList()) { 
				respuestas.setAdvanceExecutionSaveguards(avanceEjecucion);
				if(respuestas.getVaanId()==null)
					valueAnswersFacade.create(respuestas);
				else
					valueAnswersFacade.edit(respuestas);
			}
			for(AdvanceExecutionProjectGender ag:avancesGenero){
				ag.setAdvanceExecutionSafeguards(avanceEjecucion);
				advanceExecutionProjectGenderFacade.agregarEditar(ag);
			}						
		}else{
			edit(avanceEjecucion);
			for(AdvanceExecutionProjectGender ag:avancesGenero){
				ag.setAdvanceExecutionSafeguards(avanceEjecucion);
				advanceExecutionProjectGenderFacade.agregarEditar(ag);
			}
		}
		return avanceEjecucion;
	}
	/**
	 * Actualiza avance ejecucion genero
	 * @param avanceEjecucion
	 * @param avancesGenero
	 * @return
	 * @throws Exception
	 */
	public AdvanceExecutionSafeguards actualizaAvanceEjecucionGenero(AdvanceExecutionSafeguards avanceEjecucion)throws Exception{
		edit(avanceEjecucion);		
		return avanceEjecucion;
	} 

	
	/**
	 * Busca los avances de ejecucion reportados
	 * @param codigoProyecto
	 * @param tipo  1  salvaguardas  2 genero
	 * @return
	 * @throws Exception
	 */
	public List<AdvanceExecutionSafeguards> listarProyectosReportados(Integer codigoProyecto, Integer tipo) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		if (tipo == 1)
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexIsGender = FALSE";
		else
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexIsGender = TRUE";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", codigoProyecto);
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	
	/**
	 * Busca los avances de ejecucion reportados del socio estrategico
	 * @param codigoProyecto
	 * @param tipo  1  salvaguardas  2 genero
	 * @return
	 * @throws Exception
	 */
	public List<AdvanceExecutionSafeguards> listarProyectosReportadosSocioEstrategico(Integer codigoProyecto, Integer tipo,Integer codigoSocio) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		if (tipo == 1)
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexIsGender = FALSE AND AE.projectsStrategicPartners.pspaId=:codigoSocio";
		else
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexIsGender = TRUE AND AE.projectsStrategicPartners.pspaId=:codigoSocio";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", codigoProyecto);
		camposCondicion.put("codigoSocio", codigoSocio);
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	/**
	 * Busca los proyectos de salvaguardas reportados en base a los criterios de busqueda.
	 * @param codigoProyecto  Código del proyecto
	 * @param codigoSocio  Código del socio estratégico
	 * @param codigoComponente   Código del coponente
	 * @param codigoPeriodo Código del periodo reportado
	 * @param codigoEstado  Código del estado del reporte
	 * @return
	 * @throws Exception
	 */
	public List<AdvanceExecutionSafeguards> listarProyReportadosConCriteriosBusqueda(Integer codigoProyecto, Integer codigoSocio, String codigoPeriodo, String codigoEstado) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		if (codigoSocio == null && codigoPeriodo!=null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexTermFrom=:codigoPeriodo AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = FALSE AND AE.projectsStrategicPartners.pspaId IS NULL AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio == null && codigoPeriodo==null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = FALSE AND AE.projectsStrategicPartners.pspaId IS NULL AND AE.adexStatus=TRUE";			
		}else if(codigoSocio != null && codigoPeriodo != null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexTermFrom=:codigoPeriodo AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = FALSE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoSocio", codigoSocio);
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio != null && codigoPeriodo == null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = FALSE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoSocio", codigoSocio);			
		}
		camposCondicion.put("codigoProyecto", codigoProyecto);		
		camposCondicion.put("codigoEstado", codigoEstado);
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	
	public List<AdvanceExecutionSafeguards> listarProyReportadosConCriteriosBusqueda(Integer codigoProyecto, Integer codigoSocio, List<String> codigoPeriodo) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		if (codigoSocio == null && codigoPeriodo!=null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexTermFrom IN :codigoPeriodo AND AE.adexIsGender = FALSE AND AE.adexStatus=TRUE ORDER BY AE.adexTermFrom DESC";
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio != null && codigoPeriodo != null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexTermFrom IN :codigoPeriodo AND AE.adexIsGender = FALSE AND AE.adexStatus=TRUE ORDER BY AE.adexTermFrom DESC";
			camposCondicion.put("codigoSocio", codigoSocio);
			camposCondicion.put("codigoPeriodo", codigoPeriodo);				
		}

		camposCondicion.put("codigoProyecto", codigoProyecto);				
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	
	public List<AdvanceExecutionSafeguards> listarProyGeneroReportadosConCriteriosBusqueda(Integer codigoProyecto, Integer codigoSocio, String codigoPeriodo, String codigoEstado) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		if (codigoSocio == null && codigoPeriodo!=null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexTermFrom=:codigoPeriodo AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = TRUE AND AE.projectsStrategicPartners.pspaId IS NULL AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio == null && codigoPeriodo==null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = TRUE AND AE.projectsStrategicPartners.pspaId IS NULL AND AE.adexStatus=TRUE";			
		}else if(codigoSocio != null && codigoPeriodo != null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexTermFrom=:codigoPeriodo AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = TRUE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoSocio", codigoSocio);
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio != null && codigoPeriodo == null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexReportedStatus=:codigoEstado AND AE.adexIsGender = TRUE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoSocio", codigoSocio);			
		}
		camposCondicion.put("codigoProyecto", codigoProyecto);		
		camposCondicion.put("codigoEstado", codigoEstado);
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	
	public List<AdvanceExecutionSafeguards> listarProyGeneroReportadosConCriteriosBusqueda(Integer codigoProyecto, Integer codigoSocio, List<String> codigoPeriodo) throws Exception{
		String sql="";
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		if (codigoSocio == null && codigoPeriodo!=null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.adexTermFrom IN :codigoPeriodo AND AE.adexIsGender = TRUE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}else if(codigoSocio != null && codigoPeriodo != null){
			sql="SELECT AE FROM Project P, AdvanceExecutionSafeguards AE WHERE AE.projects.projId = P.projId AND P.projStatus=TRUE AND P.projId= :codigoProyecto AND AE.projectsStrategicPartners.pspaId=:codigoSocio AND AE.adexTermFrom IN :codigoPeriodo AND AE.adexIsGender = TRUE AND AE.adexStatus=TRUE";
			camposCondicion.put("codigoSocio", codigoSocio);
			camposCondicion.put("codigoPeriodo", codigoPeriodo);
		}
		camposCondicion.put("codigoProyecto", codigoProyecto);				
		listaTemp = findByCreateQuery(sql, camposCondicion);
		return listaTemp;
	}
	
	/**
	 * 
	 * @param codigoProyecto Codigo del proyecti/pdi/programa
	 * @param tipoProyecto  1 Salvaguarda  2 Genero
	 * @return
	 * @throws Exception
	 */
	public List<AdvanceExecutionSafeguards> listadoProyectosPresentadosIniciados(AdvanceExecutionSafeguards adex, int tipoProyecto) throws Exception{
		List<AdvanceExecutionSafeguards> listaTemp=new ArrayList<AdvanceExecutionSafeguards>();
		String sql ="";
		if(tipoProyecto ==1)
			sql ="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.adexIsGender=false AND AE.adexIsReported=false AND AE.adexStatus=true AND (AE.adexReportedStatus = 'I' OR AE.adexReportedStatus = 'P') AND AE.adexTermFrom=:desde AND AE.adexTermTo=:hasta AND AE.projectsStrategicPartners IS NOT NULL";
		else if(tipoProyecto ==2)
			sql ="SELECT AE FROM AdvanceExecutionSafeguards AE WHERE AE.projects.projId=:codigoProyecto AND AE.adexIsGender=true AND AE.adexIsReported=false AND AE.adexStatus=true AND (AE.adexReportedStatus = 'I' OR AE.adexReportedStatus = 'P') AND AE.adexTermFrom=:desde AND AE.adexTermTo=:hasta AND AE.projectsStrategicPartners IS NOT NULL";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("codigoProyecto", adex.getProjects().getProjId());
		camposCondicion.put("desde", adex.getAdexTermFrom());
		camposCondicion.put("hasta", adex.getAdexTermTo());
		listaTemp = findByCreateQuery(sql, camposCondicion);

		return listaTemp;
	}
	/**
	 * Busca el avance de ejeucion de un socio implementador
	 * @param aes
	 * @return
	 * @throws DaoException
	 */
	public AdvanceExecutionSafeguards buscaAvanceEjecucionSocioImplementador(AdvanceExecutionSafeguards aes) throws DaoException{
		try{
			String sql ="SELECT AP FROM AdvanceExecutionSafeguards AP WHERE AP.projects.projId=:codigoProyecto AND AP.projectsStrategicPartners.pspaId IS NULL AND AP.adexIsReported = TRUE AND AP.adexIsGender = FALSE AND AP.adexTermFrom =:desde AND AP.adexTermTo=:hasta";
			Map<String, Object> camposCondicion=new HashMap<String, Object>();
			camposCondicion.put("codigoProyecto", aes.getProjects().getProjId());
			camposCondicion.put("desde",aes.getAdexTermFrom());
			camposCondicion.put("hasta", aes.getAdexTermTo());
			return findByCreateQuerySingleResult(sql, camposCondicion);
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DaoException();
		}
	}
	/**
	 * Datos del proyecto 
	 * @param codigoReporte
	 * @return
	 * @throws Exception
	 */
	public List<DtoDatosProyectoResumen> buscaDatosProyecto(int codigoReporte)throws Exception{
		List<DtoDatosProyectoResumen> listaTemp= new ArrayList<>();
		List<Object[]> resultado= null;
		String sql ="SELECT p.proj_title,pa.part_name,s.sect_name FROM sigma.project p, sis.advance_execution_safeguards aex, sis.advance_sectors ase, sis.sectors s, sigma.partner pa " +
				    " WHERE p.proj_id = aex.proj_id AND aex.adex_id = ase.adex_id AND s.sect_id = ase.sect_id AND pa.part_id = p.part_id AND aex.adex_id =" + codigoReporte;
		resultado = (List<Object[]>)consultaNativa(sql);
		if(resultado.size()>0){
			for(Object obj:resultado){
				Object[] dataObj = (Object[]) obj;
				DtoDatosProyectoResumen dto = new DtoDatosProyectoResumen();				
				if(dataObj[0]!=null)
					dto.setProyecto(dataObj[0].toString());					
				if(dataObj[1]!=null)
					dto.setPartner(dataObj[1].toString());
				if(dataObj[2]!=null)
					dto.setSector(dataObj[2].toString());													
				listaTemp.add(dto);
			}
		}
		return listaTemp;
	}
}
