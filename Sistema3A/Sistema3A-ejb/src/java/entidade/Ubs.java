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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "ubs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubs.findAll", query = "SELECT u FROM Ubs u"),
    @NamedQuery(name = "Ubs.findByIdUBS", query = "SELECT u FROM Ubs u WHERE u.idUBS = :idUBS"),
    @NamedQuery(name = "Ubs.findByRpa", query = "SELECT u FROM Ubs u WHERE u.rpa = :rpa"),
    @NamedQuery(name = "Ubs.findByMicroRegiao", query = "SELECT u FROM Ubs u WHERE u.microRegiao = :microRegiao"),
    @NamedQuery(name = "Ubs.findByCnes", query = "SELECT u FROM Ubs u WHERE u.cnes = :cnes"),
    @NamedQuery(name = "Ubs.findByBairro", query = "SELECT u FROM Ubs u WHERE u.bairro = :bairro"),
    @NamedQuery(name = "Ubs.findByFone", query = "SELECT u FROM Ubs u WHERE u.fone = :fone"),
    @NamedQuery(name = "Ubs.findByLatitude", query = "SELECT u FROM Ubs u WHERE u.latitude = :latitude"),
    @NamedQuery(name = "Ubs.findByLongitude", query = "SELECT u FROM Ubs u WHERE u.longitude = :longitude")})
public class Ubs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUBS")
    private Integer idUBS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rpa")
    private int rpa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "micro_regiao")
    private int microRegiao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnes")
    private int cnes;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "unidade")
    private String unidade;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "fone")
    private String fone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @JoinTable(name = "ubs_has_especialidades", joinColumns = {
        @JoinColumn(name = "UBS_idUBS", referencedColumnName = "idUBS")}, inverseJoinColumns = {
        @JoinColumn(name = "ESPECIALIDADES_idESPECIALIDADES", referencedColumnName = "idESPECIALIDADES")})
    @ManyToMany
    private Collection<Especialidades> especialidadesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uBSidUBS")
    private Collection<Comentario> comentarioCollection;

    public Ubs() {
    }

    public Ubs(Integer idUBS) {
        this.idUBS = idUBS;
    }

    public Ubs(Integer idUBS, int rpa, int microRegiao, int cnes, String unidade, String endereco, String bairro, String fone, double latitude, double longitude) {
        this.idUBS = idUBS;
        this.rpa = rpa;
        this.microRegiao = microRegiao;
        this.cnes = cnes;
        this.unidade = unidade;
        this.endereco = endereco;
        this.bairro = bairro;
        this.fone = fone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getIdUBS() {
        return idUBS;
    }

    public void setIdUBS(Integer idUBS) {
        this.idUBS = idUBS;
    }

    public int getRpa() {
        return rpa;
    }

    public void setRpa(int rpa) {
        this.rpa = rpa;
    }

    public int getMicroRegiao() {
        return microRegiao;
    }

    public void setMicroRegiao(int microRegiao) {
        this.microRegiao = microRegiao;
    }

    public int getCnes() {
        return cnes;
    }

    public void setCnes(int cnes) {
        this.cnes = cnes;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Collection<Especialidades> getEspecialidadesCollection() {
        return especialidadesCollection;
    }

    public void setEspecialidadesCollection(Collection<Especialidades> especialidadesCollection) {
        this.especialidadesCollection = especialidadesCollection;
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
        hash += (idUBS != null ? idUBS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubs)) {
            return false;
        }
        Ubs other = (Ubs) object;
        if ((this.idUBS == null && other.idUBS != null) || (this.idUBS != null && !this.idUBS.equals(other.idUBS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Ubs[ idUBS=" + idUBS + " ]";
    }
    
}
