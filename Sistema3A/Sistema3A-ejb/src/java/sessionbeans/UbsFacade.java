/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.Ubs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author german
 */
@Stateless
public class UbsFacade extends AbstractFacade<Ubs> {
    @PersistenceContext(unitName = "Sistema3A-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbsFacade() {
        super(Ubs.class);
    }
    
}
