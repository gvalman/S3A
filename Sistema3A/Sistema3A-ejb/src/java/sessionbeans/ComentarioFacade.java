/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.Comentario;
import entidade.Ubs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author german
 */
@Stateless
public class ComentarioFacade extends AbstractFacade<Comentario> {

    @PersistenceContext(unitName = "Sistema3A-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }

    public List<Comentario> FindComentarioByUbs(Ubs idUbs) {
        List<Comentario> resultado = null;
        resultado = em.createNamedQuery("Comentario.findByUnidade")
                .setParameter("idUBS", idUbs)
                .getResultList();
        return resultado;
    }
}
