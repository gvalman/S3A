/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author german
 */
@Entity
@Table(name = "avaliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a"),
    @NamedQuery(name = "Avaliacao.findByUserIduser", query = "SELECT a FROM Avaliacao a WHERE a.avaliacaoPK.userIduser = :userIduser"),
    @NamedQuery(name = "Avaliacao.findByComentarioIdcomentario", query = "SELECT a FROM Avaliacao a WHERE a.avaliacaoPK.comentarioIdcomentario = :comentarioIdcomentario"),
    @NamedQuery(name = "Avaliacao.findByNota", query = "SELECT a FROM Avaliacao a WHERE a.nota = :nota")})
public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AvaliacaoPK avaliacaoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota")
    private int nota;
    @JoinColumn(name = "comentario_idcomentario", referencedColumnName = "idcomentario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentario comentario;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Avaliacao() {
    }

    public Avaliacao(AvaliacaoPK avaliacaoPK) {
        this.avaliacaoPK = avaliacaoPK;
    }

    public Avaliacao(AvaliacaoPK avaliacaoPK, int nota) {
        this.avaliacaoPK = avaliacaoPK;
        this.nota = nota;
    }

    public Avaliacao(int userIduser, int comentarioIdcomentario) {
        this.avaliacaoPK = new AvaliacaoPK(userIduser, comentarioIdcomentario);
    }

    public AvaliacaoPK getAvaliacaoPK() {
        return avaliacaoPK;
    }

    public void setAvaliacaoPK(AvaliacaoPK avaliacaoPK) {
        this.avaliacaoPK = avaliacaoPK;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avaliacaoPK != null ? avaliacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.avaliacaoPK == null && other.avaliacaoPK != null) || (this.avaliacaoPK != null && !this.avaliacaoPK.equals(other.avaliacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Avaliacao[ avaliacaoPK=" + avaliacaoPK + " ]";
    }
    
}
