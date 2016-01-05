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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "UserController")
@ViewScoped
public class UserController {

    @EJB
    private UserFacade userFacade;

    //private static final String URL_WEBSERVICE= "https://viacep.com.br/ws/";
    private static final String URL_WEBSERVICE = "http://cep.correiocontrol.com.br/";
    private User userInsert;
    private String login, senha, nome, email;
    private int cep;

    public void cadastrar() {
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
            limparForm();
        } catch (Exception e) {
            saida = "Cadastro não pode ser realizado.Tente Novamente!";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", saida));
    }

    private List<User> AllUser() {
        List<User> Users = null;
        Users = userFacade.findAll();
        return Users;
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

    public void limparForm() {
        this.login = null;
        this.nome = null;
        this.senha = null;
        this.cep = 0;
        this.email = null;
    }

    public void checkCEP() {

        URL stockURL = null;
        BufferedReader in = null;

        HttpURLConnection connection = null;
        String saida = "";

        try {
            System.out.println(URL_WEBSERVICE + cep + ".json");
            stockURL = new URL(URL_WEBSERVICE + cep + ".json");
            in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            in.close();

            connection = (HttpURLConnection) stockURL.openConnection();
            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                //sb.append(line + "\n");
                for (String item : line.split(",")) {
                    item = item.replace("{", "");
                    item = item.replace("}", "");
                    saida += item + "\n";
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", saida));
    }

}
