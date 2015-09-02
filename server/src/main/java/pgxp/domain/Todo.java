/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "todo", catalog = "todocool")
@XmlRootElement
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Size(max = 255)
    @Column(length = 255)
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date afazer;
    @Temporal(TemporalType.DATE)
    private Date fazendo;
    @Temporal(TemporalType.DATE)
    private Date feito;

    public Todo() {
    }

    public Todo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getAfazer() {
        return afazer;
    }

    public void setAfazer(Date afazer) {
        this.afazer = afazer;
    }

    public Date getFazendo() {
        return fazendo;
    }

    public void setFazendo(Date fazendo) {
        this.fazendo = fazendo;
    }

    public Date getFeito() {
        return feito;
    }

    public void setFeito(Date feito) {
        this.feito = feito;
    }

}
