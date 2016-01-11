/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "especialidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidades.findAll", query = "SELECT e FROM Especialidades e"),
    @NamedQuery(name = "Especialidades.findByIdESPECIALIDADES", query = "SELECT e FROM Especialidades e WHERE e.idESPECIALIDADES = :idESPECIALIDADES"),
    @NamedQuery(name = "Especialidades.findByNome", query = "SELECT e FROM Especialidades e WHERE e.nome = :nome")})
public class Especialidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idESPECIALIDADES")
    private Integer idESPECIALIDADES;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @ManyToMany(mappedBy = "especialidadesCollection")
    private Collection<Ubs> ubsCollection;

    public Especialidades() {
    }

    public Especialidades(Integer idESPECIALIDADES) {
        this.idESPECIALIDADES = idESPECIALIDADES;
    }

    public Especialidades(Integer idESPECIALIDADES, String nome) {
        this.idESPECIALIDADES = idESPECIALIDADES;
        this.nome = nome;
    }

    public Integer getIdESPECIALIDADES() {
        return idESPECIALIDADES;
    }

    public void setIdESPECIALIDADES(Integer idESPECIALIDADES) {
        this.idESPECIALIDADES = idESPECIALIDADES;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Ubs> getUbsCollection() {
        return ubsCollection;
    }

    public void setUbsCollection(Collection<Ubs> ubsCollection) {
        this.ubsCollection = ubsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idESPECIALIDADES != null ? idESPECIALIDADES.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidades)) {
            return false;
        }
        Especialidades other = (Especialidades) object;
        if ((this.idESPECIALIDADES == null && other.idESPECIALIDADES != null) || (this.idESPECIALIDADES != null && !this.idESPECIALIDADES.equals(other.idESPECIALIDADES))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Especialidades[ idESPECIALIDADES=" + idESPECIALIDADES + " ]";
    }
    
}
