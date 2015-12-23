/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessionbeans.AssuntoFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AssuntoController")
@RequestScoped
public class AssuntoController {
    @EJB
    private AssuntoFacade assuntoFacade;///<<<----CONTINUAR COM O CADASTRO DO COMENTÁRIO
    
    private String titulo;
    private String descricao;

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
    
}
