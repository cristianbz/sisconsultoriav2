/**
@autor proamazonia [Christian Báez]  2 dic. 2022

**/
package ec.gob.ambiente.sigma.ejb.sis.facades;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.gob.ambiente.sigma.ejb.dao.AbstractFacade;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Documentslog;


@Stateless
@LocalBean
public class DocumentslogFacade extends AbstractFacade<Documentslog, Integer>{

	public DocumentslogFacade() {
		super(Documentslog.class, Integer.class);
	}
	
	public Documentslog agregarActualizar(Documentslog documento) throws Exception{
		if(documento.getDcloId() == null)
			documento = create(documento);
		else
			documento = edit(documento);
		return documento;
	}
	
	public void agregarActualizar(List<Documentslog> lista)throws Exception{
		for (Documentslog doclog : lista) {
			if(doclog.getDcloId() == null)
				create(doclog);
			else
				edit(doclog);
		}
	}
	
	public List<Documentslog> listaBitacorasTodas() throws Exception{
		String sql="SELECT D FROM Documentslog D WHERE D.dcloStatus=TRUE";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		return findByCreateQuery(sql, camposCondicion);
	}
	
	public List<Documentslog> listaBitacorasFecha(Date fechaInicio,Date fechaFin) throws Exception{
		String sql="SELECT D FROM Documentslog D WHERE D.dcloStatus=TRUE AND D.dcloSendDate>=:fechaInicio AND D.dcloSendDate<=:fechaFin ORDER BY D.dcloSendDate";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		camposCondicion.put("fechaInicio", fechaInicio);
		camposCondicion.put("fechaFin", fechaFin);
		return findByCreateQuery(sql, camposCondicion);
	}
	/**
	 * Realiza la consulta en base a los filtros
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 * @param proyecto
	 * @param destinatario
	 * @param nroOficio
	 * @param remitente
	 * @param asunto
	 * @param institucion
	 * @return
	 * @throws Exception
	 */
	public List<Documentslog> listaBitacorasFiltros(Date fechaInicio, Date fechaFin, Project proyecto, String destinatario, String nroOficio, String remitente, String asunto, String institucion) throws Exception{
		String sql="SELECT D FROM Documentslog D WHERE D.dcloStatus=TRUE ";
		Map<String, Object> camposCondicion=new HashMap<String, Object>();
		if(fechaInicio !=null && fechaFin !=null){
			sql = sql +" AND D.dcloSendDate>=:fechaInicio AND D.dcloSendDate<=:fechaFin ";
			camposCondicion.put("fechaInicio", fechaInicio);
			camposCondicion.put("fechaFin", fechaFin);
		}
		if (proyecto!=null && proyecto.getProjId()!=null){
			sql = sql +" AND D.projects.projId = :proyecto ";
			camposCondicion.put("proyecto", proyecto.getProjId());
		}
		if(destinatario !=null){
			sql = sql +" AND D.dcloInstitution LIKE :destinatario ";
//			camposCondicion.put("destinatario", destinatario);
			camposCondicion.put("destinatario", "%"+destinatario+"%");
		}
		if(nroOficio !=null){
			sql = sql +" AND D.dcloDocumentNumber = :nrooficio ";
			camposCondicion.put("nrooficio", nroOficio);
		}
		if(remitente !=null){
			sql = sql +" AND D.dcloSender LIKE :remitente ";
//			camposCondicion.put("remitente", remitente);
			camposCondicion.put("remitente", "%"+remitente+"%");
		}
		if(asunto !=null){
			sql = sql +" AND D.dcloSubject LIKE :asunto ";
//			camposCondicion.put("asunto", asunto);
			camposCondicion.put("asunto", "%"+asunto+"%");
		}
		if(institucion !=null){
			sql = sql +" AND D.dcloReferringInstitution LIKE :institucion ";
//			camposCondicion.put("institucion", institucion);
			camposCondicion.put("institucion", "%"+institucion+"%");
		}
		sql = sql + " ORDER BY D.dcloSendDate";
		return findByCreateQuery(sql, camposCondicion);
	}
}

