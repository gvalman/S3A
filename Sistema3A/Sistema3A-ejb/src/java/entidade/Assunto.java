/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author german
 */
@Entity
@Table(name = "assunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assunto.findAll", query = "SELECT a FROM Assunto a"),
    @NamedQuery(name = "Assunto.findByIdassunto", query = "SELECT a FROM Assunto a WHERE a.idassunto = :idassunto"),
    @NamedQuery(name = "Assunto.findByTitulo", query = "SELECT a FROM Assunto a WHERE a.titulo = :titulo")})
public class Assunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idassunto")
    private Integer idassunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aSSUNTOidassunto")
    private Collection<Comentario> comentarioCollection;

    public Assunto() {
    }

    public Assunto(Integer idassunto) {
        this.idassunto = idassunto;
    }

    public Assunto(Integer idassunto, String titulo, String descricao) {
        this.idassunto = idassunto;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Integer getIdassunto() {
        return idassunto;
    }

    public void setIdassunto(Integer idassunto) {
        this.idassunto = idassunto;
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

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idassunto != null ? idassunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assunto)) {
            return false;
        }
        Assunto other = (Assunto) object;
        if ((this.idassunto == null && other.idassunto != null) || (this.idassunto != null && !this.idassunto.equals(other.idassunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Assunto[ idassunto=" + idassunto + " ]";
    }
    
}
