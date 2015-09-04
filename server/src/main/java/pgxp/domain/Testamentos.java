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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "biblia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testamentos.findAll", query = "SELECT t FROM Testamentos t"),
    @NamedQuery(name = "Testamentos.findByTesId", query = "SELECT t FROM Testamentos t WHERE t.tesId = :tesId"),
    @NamedQuery(name = "Testamentos.findByTesNome", query = "SELECT t FROM Testamentos t WHERE t.tesNome = :tesNome")})
public class Testamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tes_id", nullable = false)
    private Short tesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tes_nome", nullable = false, length = 30)
    private String tesNome;

    public Testamentos() {
    }

    public Testamentos(Short tesId) {
        this.tesId = tesId;
    }

    public Testamentos(Short tesId, String tesNome) {
        this.tesId = tesId;
        this.tesNome = tesNome;
    }

    public Short getTesId() {
        return tesId;
    }

    public void setTesId(Short tesId) {
        this.tesId = tesId;
    }

    public String getTesNome() {
        return tesNome;
    }

    public void setTesNome(String tesNome) {
        this.tesNome = tesNome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tesId != null ? tesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testamentos)) {
            return false;
        }
        Testamentos other = (Testamentos) object;
        if ((this.tesId == null && other.tesId != null) || (this.tesId != null && !this.tesId.equals(other.tesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgxp.domain.Testamentos[ tesId=" + tesId + " ]";
    }

}
