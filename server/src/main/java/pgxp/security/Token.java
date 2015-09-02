/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.security;

import java.io.Serializable;
import java.util.logging.Logger;
import pgxp.domain.Usuario;


class Token implements Serializable {

    private Long tempo;
    private Usuario usuario;

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    private static final Logger LOG = Logger.getLogger(Token.class.getName());

}
