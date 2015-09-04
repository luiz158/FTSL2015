/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import pgxp.dao.UsuarioDAO;
import pgxp.domain.Usuario;

/**
 *
 * @author gladson
 */
@RequestScoped
public class PGXPAuthenticator {

    @Inject
    private UsuarioDAO dao;

    public Usuario login(String nome) throws LoginException {
        return dao.find(nome);
    }

}
