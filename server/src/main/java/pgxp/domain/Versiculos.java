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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Versiculos.findAll", query = "SELECT v FROM Versiculos v"),
    @NamedQuery(name = "Versiculos.findByVerId", query = "SELECT v FROM Versiculos v WHERE v.verId = :verId"),
    @NamedQuery(name = "Versiculos.findByVerVrsId", query = "SELECT v FROM Versiculos v WHERE v.verVrsId = :verVrsId"),
    @NamedQuery(name = "Versiculos.findByVerLivId", query = "SELECT v FROM Versiculos v WHERE v.verLivId = :verLivId"),
    @NamedQuery(name = "Versiculos.findByVerCapitulo", query = "SELECT v FROM Versiculos v WHERE v.verCapitulo = :verCapitulo"),
    @NamedQuery(name = "Versiculos.findByVerVersiculo", query = "SELECT v FROM Versiculos v WHERE v.verVersiculo = :verVersiculo")})
public class Versiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ver_id", nullable = false)
    private Integer verId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver_vrs_id", nullable = false)
    private short verVrsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver_liv_id", nullable = false)
    private short verLivId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver_capitulo", nullable = false)
    private short verCapitulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver_versiculo", nullable = false)
    private short verVersiculo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ver_texto", nullable = false, length = 65535)
    private String verTexto;

    public Versiculos() {
    }

    public Versiculos(Integer verId) {
        this.verId = verId;
    }

    public Versiculos(Integer verId, short verVrsId, short verLivId, short verCapitulo, short verVersiculo, String verTexto) {
        this.verId = verId;
        this.verVrsId = verVrsId;
        this.verLivId = verLivId;
        this.verCapitulo = verCapitulo;
        this.verVersiculo = verVersiculo;
        this.verTexto = verTexto;
    }

    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public short getVerVrsId() {
        return verVrsId;
    }

    public void setVerVrsId(short verVrsId) {
        this.verVrsId = verVrsId;
    }

    public short getVerLivId() {
        return verLivId;
    }

    public void setVerLivId(short verLivId) {
        this.verLivId = verLivId;
    }

    public short getVerCapitulo() {
        return verCapitulo;
    }

    public void setVerCapitulo(short verCapitulo) {
        this.verCapitulo = verCapitulo;
    }

    public short getVerVersiculo() {
        return verVersiculo;
    }

    public void setVerVersiculo(short verVersiculo) {
        this.verVersiculo = verVersiculo;
    }

    public String getVerTexto() {
        return verTexto;
    }

    public void setVerTexto(String verTexto) {
        this.verTexto = verTexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verId != null ? verId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Versiculos)) {
            return false;
        }
        Versiculos other = (Versiculos) object;
        if ((this.verId == null && other.verId != null) || (this.verId != null && !this.verId.equals(other.verId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgxp.domain.Versiculos[ verId=" + verId + " ]";
    }

}
