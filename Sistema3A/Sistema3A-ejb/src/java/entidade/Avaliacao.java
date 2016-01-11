/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Avaliacao.findByIdAvaliacao", query = "SELECT a FROM Avaliacao a WHERE a.idAvaliacao = :idAvaliacao"),
    @NamedQuery(name = "Avaliacao.findByNota", query = "SELECT a FROM Avaliacao a WHERE a.nota = :nota"),
    @NamedQuery(name = "Avaliacao.findByTitulo", query = "SELECT a FROM Avaliacao a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "Avaliacao.findByAceitacao", query = "SELECT a FROM Avaliacao a WHERE a.aceitacao = :aceitacao"),
    @NamedQuery(name = "Avaliacao.findByComentario", query = "SELECT a FROM Avaliacao a WHERE a.comentarioIdcomentario = :idComentario")})
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAvaliacao")
    private Integer idAvaliacao;
    @Column(name = "nota")
    private Integer nota;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aceitacao")
    private boolean aceitacao;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;
    @JoinColumn(name = "comentario_idcomentario", referencedColumnName = "idcomentario")
    @ManyToOne(optional = false)
    private Comentario comentarioIdcomentario;

    public Avaliacao() {
    }

    public Avaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Avaliacao(Integer idAvaliacao, boolean aceitacao) {
        this.idAvaliacao = idAvaliacao;
        this.aceitacao = aceitacao;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getAceitacao() {
        return aceitacao;
    }

    public void setAceitacao(boolean aceitacao) {
        this.aceitacao = aceitacao;
    }

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
    }

    public Comentario getComentarioIdcomentario() {
        return comentarioIdcomentario;
    }

    public void setComentarioIdcomentario(Comentario comentarioIdcomentario) {
        this.comentarioIdcomentario = comentarioIdcomentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvaliacao != null ? idAvaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idAvaliacao == null && other.idAvaliacao != null) || (this.idAvaliacao != null && !this.idAvaliacao.equals(other.idAvaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Avaliacao[ idAvaliacao=" + idAvaliacao + " ]";
    }
}
