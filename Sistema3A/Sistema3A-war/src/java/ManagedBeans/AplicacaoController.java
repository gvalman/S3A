/*
 @RequestScoped 
 Escopo padrão. É criado a cada requisição.

 @ViewScoped 
 Instancia do bean é criada e mantida enquanto o usuário estiver na página (por exemplo, com os manipuladores de eventos ou Ajax). Deve implementar Serializable 

 @SessionScoped 
 Criado um por sessão de cada usuário. 

 @ApplicationScoped 
 Criado um para toda a aplicação. 
 */
package ManagedBeans;

import entidade.Assunto;
import entidade.Ubs;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import sessionbeans.UbsFacade;

/**
 *
 * @author german
 */
@ApplicationScoped
@ManagedBean(name = "AplicacaoController")
public class AplicacaoController implements Serializable {

    @EJB
    private UbsFacade ubsFacade;
    private List<Ubs> unidades = null;

    @PostConstruct
    public void init() {
        //ubsFacade = new UbsFacade();
        //unidades = ubsFacade.FindUBS();
        unidades = getUbsFacade().findAll();
        //setUnidades(ubsFacade.findAll());
        System.out.println("AppplicationScoped Iniciado");
    }

    public List<Ubs> getUnidades() {
        /*
         if(unidades == null){
         setUnidades(ubsFacade.findAll());
         }*/
        return unidades;
    }

    public void setUnidades(List<Ubs> unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the ubsFacade
     */
    private UbsFacade getUbsFacade() {
        return ubsFacade;
    }
    
    public List<Ubs> ListarAllUnidade(){
         return ubsFacade.findAll();
    }
}
