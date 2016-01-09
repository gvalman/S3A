/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.Assunto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author german
 */
@Stateless
public class AssuntoFacade extends AbstractFacade<Assunto> {
    @PersistenceContext(unitName = "Sistema3A-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssuntoFacade() {
        super(Assunto.class);
    }
    
}
