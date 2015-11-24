/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Ubs;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author german
 */
@ManagedBean(name = "AcessoNewController")
@ViewScoped
public class AcessoNewController implements Serializable {

    @ManagedProperty(value = "#{UbsController}")
    private UbsController ubsControl;
    
    private String teste="OKKKK!!!";

    @PostConstruct
    public void init() {
        for (Ubs unidade : this.getUbsControl().getUnidades()) {

        }
    }

    public UbsController getUbsControl() {
        return ubsControl;
    }

    public void setUbsControl(UbsController ubsControl) {
        this.ubsControl = ubsControl;
    }

    /**
     * @return the teste
     */
    public String getTeste() {
        return teste;
    }

    /**
     * @param teste the teste to set
     */
    public void setTeste(String teste) {
        this.teste = teste;
    }

}
