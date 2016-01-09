/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author german
 */
@Embeddable
public class AvaliacaoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser")
    private int userIduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comentario_idcomentario")
    private int comentarioIdcomentario;

    public AvaliacaoPK() {
    }

    public AvaliacaoPK(int userIduser, int comentarioIdcomentario) {
        this.userIduser = userIduser;
        this.comentarioIdcomentario = comentarioIdcomentario;
    }

    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    public int getComentarioIdcomentario() {
        return comentarioIdcomentario;
    }

    public void setComentarioIdcomentario(int comentarioIdcomentario) {
        this.comentarioIdcomentario = comentarioIdcomentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIduser;
        hash += (int) comentarioIdcomentario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvaliacaoPK)) {
            return false;
        }
        AvaliacaoPK other = (AvaliacaoPK) object;
        if (this.userIduser != other.userIduser) {
            return false;
        }
        if (this.comentarioIdcomentario != other.comentarioIdcomentario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.AvaliacaoPK[ userIduser=" + userIduser + ", comentarioIdcomentario=" + comentarioIdcomentario + " ]";
    }
    
}
