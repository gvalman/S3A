/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.User;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessionbeans.UserFacade;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "formUserController")
@RequestScoped
public class formUserController {

    @EJB
    private UserFacade userFacade;

    private User userInsert;
    private String login, senha, nome, email;
    private int cep;

    public void cadastrar() {
        System.out.println(cep);
        String saida = null;
        userInsert = new User();
        userInsert.setLogin(login);
        userInsert.setNome(nome);
        userInsert.setSenha(senha);
        userInsert.setCep(cep);
        userInsert.setEmail(email);
        try {
            userFacade.create(userInsert);
            saida = "Cadastro realizado com sucesso!";
        } catch (Exception e) {
            saida = "Cadastro não pode ser realizado.Tente Novamente!";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", saida));
    }
    
    /**
     * @return the userInsert
     */
    private User getUserInsert() {
        return userInsert;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cep
     */
    public int getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(int cep) {
        this.cep = cep;
    }
}
