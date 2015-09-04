/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pgxp.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "biblia", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"liv_tes_id", "liv_posicao"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livros.findAll", query = "SELECT l FROM Livros l"),
    @NamedQuery(name = "Livros.findByLivId", query = "SELECT l FROM Livros l WHERE l.livId = :livId"),
    @NamedQuery(name = "Livros.findByLivTesId", query = "SELECT l FROM Livros l WHERE l.livTesId = :livTesId"),
    @NamedQuery(name = "Livros.findByLivPosicao", query = "SELECT l FROM Livros l WHERE l.livPosicao = :livPosicao"),
    @NamedQuery(name = "Livros.findByLivNome", query = "SELECT l FROM Livros l WHERE l.livNome = :livNome")})
public class Livros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "liv_id", nullable = false)
    private Short livId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liv_tes_id", nullable = false)
    private short livTesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liv_posicao", nullable = false)
    private short livPosicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "liv_nome", nullable = false, length = 30)
    private String livNome;

    public Livros() {
    }

    public Livros(Short livId) {
        this.livId = livId;
    }

    public Livros(Short livId, short livTesId, short livPosicao, String livNome) {
        this.livId = livId;
        this.livTesId = livTesId;
        this.livPosicao = livPosicao;
        this.livNome = livNome;
    }

    public Short getLivId() {
        return livId;
    }

    public void setLivId(Short livId) {
        this.livId = livId;
    }

    public short getLivTesId() {
        return livTesId;
    }

    public void setLivTesId(short livTesId) {
        this.livTesId = livTesId;
    }

    public short getLivPosicao() {
        return livPosicao;
    }

    public void setLivPosicao(short livPosicao) {
        this.livPosicao = livPosicao;
    }

    public String getLivNome() {
        return livNome;
    }

    public void setLivNome(String livNome) {
        this.livNome = livNome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (livId != null ? livId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livros)) {
            return false;
        }
        Livros other = (Livros) object;
        if ((this.livId == null && other.livId != null) || (this.livId != null && !this.livId.equals(other.livId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgxp.domain.Livros[ livId=" + livId + " ]";
    }

}
