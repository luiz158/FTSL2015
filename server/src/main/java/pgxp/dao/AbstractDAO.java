/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.dao;

import java.util.List;
import java.util.function.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gladson
 */
public abstract class AbstractDAO<T> {

    protected final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public void edit(T entity) {
        em.merge(entity);
    }

    public void remove(Long id) {
        em.remove(em.find(entityClass, id));
    }

    public List<T> list(String field, String order, int init, int qtde) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> utente = cq.from(entityClass);

        if (order.equalsIgnoreCase("asc")) {
            cq.orderBy(cb.asc(utente.get(field)));
        } else {
            cq.orderBy(cb.desc(utente.get(field)));
        }
        final TypedQuery query = em.createQuery(cq);

        return query.setFirstResult(init).setMaxResults(qtde).getResultList();
    }

    public T find(Long id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void beginTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
    }

    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

}
