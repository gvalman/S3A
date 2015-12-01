/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Ubs;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author german
 */
@ManagedBean(name = "AcolhimentoController")
@ViewScoped
public class AcolhimentoController implements Serializable{
    /*@ManagedProperty(value = "#{AplicacaoController}")
    private UbsController ubsControl;
    */
    private Ubs UnitySelected;

    /**
     * @return the UnitySelected
     */
    public Ubs getUnitySelected() {
        return UnitySelected;
    }

    /**
     * @param UnitySelected the UnitySelected to set
     */
    public void setUnitySelected(Ubs UnitySelected) {
        this.UnitySelected = UnitySelected;
    }
}
