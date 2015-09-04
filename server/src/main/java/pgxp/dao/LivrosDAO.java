/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.dao;

import javax.ejb.Stateless;
import pgxp.domain.Livros;

/**
 *
 * @author gladson
 */
@Stateless
public class LivrosDAO extends AbstractDAO<Livros> {

    public LivrosDAO() {
        super(Livros.class);
    }

}
