/*
@ViewScoped 
Instancia do bean é criada e mantida enquanto o usuário estiver na página (por exemplo, com os manipuladores de eventos ou Ajax). Deve implementar Serializable 

@SessionScoped 
Criado um por sessão de cada usuário. 

@ApplicationScoped 
Criado um para toda a aplicação. 

@RequestScoped 
Escopo padrão. É criado a cada requisição.
 */
package ManagedBeans;

import entidade.Ubs;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import sessionbeans.UbsFacade;
/**
 *
 * @author german
 */
@ManagedBean(name="UbsController")
@ViewScoped
public class UbsController implements Serializable{
    @EJB
    private UbsFacade ubsFacade;
    private List<Ubs> unidades = null;
  
    public List<Ubs> getUnidades() {
        if(unidades == null){
            setUnidades(ubsFacade.findAll());
        }
        return unidades;
    }

    public void setUnidades(List<Ubs> unidades) {
        this.unidades = unidades;
    }
}
