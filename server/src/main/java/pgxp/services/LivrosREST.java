/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pgxp.services;

import javax.ws.rs.Path;
import pgxp.domain.Livros;

/**
 *
 * @author gladson
 */
@Path("livros")
public class LivrosREST extends AbstractREST<Livros> {

    public LivrosREST() {
        super(Livros.class);
    }

}