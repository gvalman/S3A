/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author german
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Sistema3A-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User VerificarUser(String login, String senha) {

        List<User> resultado = null;

        resultado = em.createNamedQuery("User.findByLoginSenha")
                .setParameter("login", login)
                .setParameter("senha", senha)
                .getResultList();
 
        try {
            return resultado.get(0);
        } catch (Exception e) {
            return null;
        }
    }

}
