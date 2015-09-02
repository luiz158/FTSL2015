/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import pgxp.domain.Usuario;

/**
 *
 * @author gladson
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario find(String email) {
        String jpql
               = "select u from Usuario u" +
                 " where u.nome = :nome";

        Query query = em.createQuery(jpql);
        query.setParameter("nome", email);
        if (!query.getResultList().isEmpty()) {
            return (Usuario) query.getResultList().get(0);
        } else {
            return null;
        }
    }
}
