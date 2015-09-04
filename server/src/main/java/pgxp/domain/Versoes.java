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
    @NamedQuery(name = "Versoes.findAll", query = "SELECT v FROM Versoes v"),
    @NamedQuery(name = "Versoes.findByVrsId", query = "SELECT v FROM Versoes v WHERE v.vrsId = :vrsId"),
    @NamedQuery(name = "Versoes.findByVrsNome", query = "SELECT v FROM Versoes v WHERE v.vrsNome = :vrsNome")})
public class Versoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vrs_id", nullable = false)
    private Short vrsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "vrs_nome", nullable = false, length = 50)
    private String vrsNome;

    public Versoes() {
    }

    public Versoes(Short vrsId) {
        this.vrsId = vrsId;
    }

    public Versoes(Short vrsId, String vrsNome) {
        this.vrsId = vrsId;
        this.vrsNome = vrsNome;
    }

    public Short getVrsId() {
        return vrsId;
    }

    public void setVrsId(Short vrsId) {
        this.vrsId = vrsId;
    }

    public String getVrsNome() {
        return vrsNome;
    }

    public void setVrsNome(String vrsNome) {
        this.vrsNome = vrsNome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vrsId != null ? vrsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Versoes)) {
            return false;
        }
        Versoes other = (Versoes) object;
        if ((this.vrsId == null && other.vrsId != null) || (this.vrsId != null && !this.vrsId.equals(other.vrsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgxp.domain.Versoes[ vrsId=" + vrsId + " ]";
    }

}
