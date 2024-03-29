/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.service;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author gladson
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public Object findNamedQuary(String namedQuary) {
        return getEntityManager().createNamedQuery(namedQuary).getResultList();
    }

    public Object findNamedQuary(String namedQuary, String nameParameter, Object parameter) {
        return getEntityManager().createNamedQuery(namedQuary).setParameter(nameParameter, parameter).getResultList();
    }

    public Object findNamedQuary(String namedQuary, String nameParameter1, Object parameter1, String nameParameter2, Object parameter2) {
        return getEntityManager().createNamedQuery(namedQuary).setParameter(nameParameter1, parameter1).setParameter(nameParameter2, parameter2).getResultList();
    }

}
