package ec.gob.ambiente.sigma.ejb.dao;

/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.sun.mail.smtp.SMTPTransport;

/**
 * 
 * @author SUIA
 */
public abstract class AbstractFacade<T, E> {

	private Class<T> entityClass;
	private Class<E> primaryKeyClass;

	@PersistenceContext(unitName = "suiaPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbstractFacade(Class<T> entityClass, Class<E> primaryKeyClass) {
		this.entityClass = entityClass;
		this.primaryKeyClass = primaryKeyClass;
	}

	public T create(T entity) {
		getEntityManager().persist(entity);
		em.flush();
		return entity;
	}

	public T edit(T entity) {
		getEntityManager().merge(entity);
		em.flush();
		return entity;
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(E id) {
		return getEntityManager().find(entityClass, id);
	}

	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	/**
	 * 
	 * <b> Ejecuta un namedQery con los parametros indicados en el mapa, en el
	 * que la clave del mapa es el nombre del parametro, los parametro de
	 * limites indican el rango del total de los registros que se necesitan.
	 * </b>
	 * 
	 * @author rene
	 * @version Revision: 1.0
	 *          <p>
	 *          [Autor: rene, Fecha: Oct 28, 2014]
	 *          </p>
	 * @param namedQueryName
	 *            nombre del namedQuery
	 * @param parameters
	 *            parametros del query
	 * @param limiteInicio
	 *            rango de inicio
	 * @param limiteFin
	 *            rango de fin
	 * @return resultado de la consulta
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCreateQueryPaginado(final String query_, final Map<String, Object> parameters,
			int limiteInicio, int limiteFin) {
		Query query = getEntityManager().createQuery(query_).setFirstResult(limiteInicio).setMaxResults(limiteFin);
		if (parameters != null) {
			Set<Entry<String, Object>> parameterSet = parameters.entrySet();
			for (Entry<String, Object> entry : parameterSet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}

	// Obtener la fecha actual en java.util.Date
	public static Date nowDate() {

		// 1) create a java calendar instance
		java.util.Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		return now;
	}

	// Obtener la fecha actual en java.sql.Timestamp
	public static Timestamp nowTimespan() {
		java.util.Date now = nowDate();

		// a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		return currentTimestamp;
	}

	/**
	 * 
	 * <b> Ejecuta un namedQery con los parametros indicados en el mapa, en el
	 * que la clave del mapa es el nombre del parametro</b>
	 * 
	 * @author dguano
	 * @version Revision: 1.0
	 *          <p>
	 *          [Autor: dguano, Fecha: Oct 31, 2020]
	 *          </p>
	 * @param namedQueryName
	 *            nombre del namedQuery
	 * @param parameters
	 *            parametros del query
	 * @return resultado de la consulta
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCreateQuery(final String query_, final Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(query_);
		if (parameters != null) {
			Set<Entry<String, Object>> parameterSet = parameters.entrySet();
			for (Entry<String, Object> entry : parameterSet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultaNativa(String sql) {
		Query q = getEntityManager().createNativeQuery(sql);
		return q.getResultList();
	}

	public void sentenciaNativa(String sql) {
		Query q = getEntityManager().createNativeQuery(sql);
		q.executeUpdate();
	}

	public void sentenciaNativaEspecial(String sql) {
		try {
			Query q = getEntityManager().createNativeQuery(sql);
			q.executeUpdate();
		} catch (Exception ex) {
			// En la sentencia existe un SELECT AddGeometryColumn que devuelve
			// un valor
			System.out.println("ERROR OK;");
		}
	}

	public List<T> consulta(String jpql) {
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	// SIS

	@SuppressWarnings("unchecked")
	public T findByCreateQuerySingleResult(final String query_, final Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(query_);
		if (parameters != null) {
			Set<Entry<String, Object>> parameterSet = parameters.entrySet();
			for (Entry<String, Object> entry : parameterSet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return (T) query.getSingleResult();
	}

	/**
	 * Devuelve las el numero de filas existentes
	 * 
	 * @param query_
	 * @param parameters
	 * @return
	 */
	public Number countAll(final String query_, final Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(query_);
		if (parameters != null) {
			Set<Entry<String, Object>> parameterSet = parameters.entrySet();
			for (Entry<String, Object> entry : parameterSet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return ((Number) query.getSingleResult()).intValue();
	}
	
	public void enviarMail(Properties prop,String asunto, List<String> para,List<String> copia,List<String> oculto, String mensaje) throws Exception{
		try{
			String encodingOptions = "text/html; charset=UTF-8";
			String emailTo="";
			String emailCc="";
			String emailCo="";
			if(para.size()>0){
				emailTo=para.get(0);
				for(int i=1;i<para.size();i++){
					emailTo=emailTo+","+para.get(i);
				}
			}
			if(copia.size()>0){
				emailCc=copia.get(0);
				for(int i=1;i<copia.size();i++){
					emailCc=emailCc+","+copia.get(i);
				}
			}
			if(oculto.size()>0){
				emailCo=oculto.get(0);
				for(int i=1;i<oculto.size();i++){
					emailCo=emailCo+","+oculto.get(i);
				}
			}
			
			Session session = Session.getInstance(prop, null);
			Message msg = new MimeMessage(session);
			msg.setContent("Hello", "text/plain");
			// from
            msg.setFrom(new InternetAddress(prop.getProperty("mail.smtp.user")));
			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));
			// cc
            if(emailCc.length()>0){
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(emailCc, false));
            }
           // cc
            if(emailCo.length()>0){
            msg.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(emailCo, false));
            }
			// subject
            msg.setSubject(asunto);
			// content 
            msg.setText(mensaje);
            msg.setHeader("Content-Type", encodingOptions);
         
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(prop.getProperty("mail.smtp.host"),prop.getProperty("mail.smtp.user"),prop.getProperty("mail.smtp.auth"));
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();
		}catch (MessagingException e) {
            e.printStackTrace();
        }
	}

}