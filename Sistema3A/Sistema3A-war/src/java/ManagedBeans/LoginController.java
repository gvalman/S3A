/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.User;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessionbeans.UserFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "LoginController")
@RequestScoped
public class LoginController {

    @EJB
    private UserFacade userFacade;

    private String login;
    private String senha;
    private User UserChecado;

    public void logar() {

        UserChecado = userFacade.VerificarUser(login, senha);
        String saida = "";
        if (UserChecado == null) {
            saida = "Usuário não encontrado.Tente novamente!!";
        } else {
            //Verifica se há uma sessão se não tiver irá criar uma
            HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sessao.setAttribute("usuario", UserChecado);
            saida = "Bem Vindo," + UserChecado.getNome();
        }
        //Mostra uma caixa de dialogo
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", saida));
    }

    public void Deslogar() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", "Sessão Finalizada."));
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the userFacade
     */
    private UserFacade getUserFacade() {
        return userFacade;
    }
}
