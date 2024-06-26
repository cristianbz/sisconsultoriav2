package ec.gob.ambiente.sigma.ejb.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Catalog;
import ec.gob.ambiente.sigma.ejb.entidades.Tracing;
import ec.gob.ambiente.sigma.ejb.entidades.TracingsKnowledgeManagement;

/**
 * Session Bean implementation class TracingsKnowledgeManagementFacade
 */
@Stateless
@LocalBean
public class TracingsKnowledgeManagementFacade extends AbstractFacade<TracingsKnowledgeManagement, Integer> {

	public TracingsKnowledgeManagementFacade() {
		super(TracingsKnowledgeManagement.class, Integer.class);
	}

	public void eliminarLogico(TracingsKnowledgeManagement entidad) {
		entidad.setTkmaStatus(false);
		edit(entidad);
	}

	public void crearPreguntasGestionConocimiento(Integer tracId, List<Catalog> listaPreg, String tipoProy)
			throws Exception {
		if (tipoProy.equals("PDI")) {
			for (Catalog cataId : listaPreg) {
				if (cataId.getCataNumber() == 1 || cataId.getCataNumber() == 9) {
					TracingsKnowledgeManagement tk = new TracingsKnowledgeManagement();
					tk.setTracId(new Tracing(tracId));
					tk.setCataId(cataId);
					create(tk);
				}
			}
		} else {
			for (Catalog cataId : listaPreg) {
				if (cataId.getCataNumber() < 9) {
					TracingsKnowledgeManagement tk = new TracingsKnowledgeManagement();
					tk.setTracId(new Tracing(tracId));
					tk.setCataId(cataId);
					create(tk);
				}
			}
		}
	}
	
	public List<TracingsKnowledgeManagement> listarPreguntasGestionConocimientoPorReporte(Integer tracId) throws Exception{
		String sql="select t from TracingsKnowledgeManagement t where t.tkmaStatus=true and t.tracId.tracId=:param1 and  t.cataId.catyId.catyMnemonic='CAT_PREG_GEST_CON' order by t.cataId.cataOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", tracId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void crearPreguntasComunicacion(Integer tracId, List<Catalog> listaPreg, String tipoProy)
			throws Exception {
		if (tipoProy.equals("PDI")) {
			for (Catalog cataId : listaPreg) {
				if (cataId.getCataNumber() == 1 || cataId.getCataNumber() == 9|| cataId.getCataNumber() == 10|| cataId.getCataNumber() == 11) {
					TracingsKnowledgeManagement tk = new TracingsKnowledgeManagement();
					tk.setTracId(new Tracing(tracId));
					tk.setCataId(cataId);
					create(tk);
				}
			}
		} else {
			for (Catalog cataId : listaPreg) {
				if (cataId.getCataNumber() > 1 && cataId.getCataNumber() < 9) {
					TracingsKnowledgeManagement tk = new TracingsKnowledgeManagement();
					tk.setTracId(new Tracing(tracId));
					tk.setCataId(cataId);
					create(tk);
				}
			}
		}
	}
	
	public List<TracingsKnowledgeManagement> listarPreguntasComunicacionPorReporte(Integer tracId) throws Exception{
		String sql="select t from TracingsKnowledgeManagement t where t.tkmaStatus=true and t.tracId.tracId=:param1 and  t.cataId.catyId.catyMnemonic='CAT_PREG_COM' order by t.cataId.cataOrder";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("param1", tracId);
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public void editarListaPreguntas(List<TracingsKnowledgeManagement> listaPreguntas) throws Exception{
		for(TracingsKnowledgeManagement tk:listaPreguntas){
			edit(tk);
		}
	}

}
