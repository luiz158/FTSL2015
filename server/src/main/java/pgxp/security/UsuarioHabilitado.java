/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.security;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UsuarioHabilitado implements Serializable {

    private Long idusu;
    private String nome;

    public Long getIdusu() {
        return idusu;
    }

    public void setIdusu(Long idusu) {
        this.idusu = idusu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
