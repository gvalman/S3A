/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author german
 */
@ManagedBean(name = "ComentarioController")
@RequestScoped
public class ComentarioController {
    private String titulo;
    private String descricao;
    private int idUnidade;
    private int idUsuario;
    private int idAssunto;

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
     * @return the idUnidade
     */
    public int getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
