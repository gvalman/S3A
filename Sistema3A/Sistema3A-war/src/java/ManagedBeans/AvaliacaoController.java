/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Avaliacao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.AvaliacaoFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AvaliacaoController")
@ViewScoped
public class AvaliacaoController {

    @EJB
    private AvaliacaoFacade avaliacaoFacade;

    public List<Avaliacao> FindAvaliacoesUser() {
        List<Avaliacao> avaliacaoUser = null;
        avaliacaoUser = avaliacaoFacade.findAll();
        return avaliacaoUser;
    }
}
