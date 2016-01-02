/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Assunto;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.AssuntoFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AssuntoController")
@ViewScoped
public class AssuntoController {

    @EJB
    private AssuntoFacade assuntoFacade;
    private List<Assunto> Assuntos = null;

    private  int idAssunto;
    private String titulo;
    private String descricao;

    public List<Assunto> ObterAssuntos() {
        Assuntos = assuntoFacade.findAll();
        return Assuntos;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the idAssunto
     */
    public int getIdAssunto() {
        return idAssunto;
    }

    /**
     * @param idAssunto the idAssunto to set
     */
    public void setIdAssunto(int idAssunto) {
        this.idAssunto = idAssunto;
    }

}
