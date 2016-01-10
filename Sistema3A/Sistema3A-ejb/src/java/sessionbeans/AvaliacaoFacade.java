/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.Avaliacao;
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
public class AvaliacaoFacade extends AbstractFacade<Avaliacao> {

    @PersistenceContext(unitName = "Sistema3A-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvaliacaoFacade() {
        super(Avaliacao.class);
    }

    public List<Avaliacao> AvaliacoesPendentesByUser(int idUser) {
        List<Avaliacao> resultado = null;

        resultado = em.createNamedQuery("Avaliacao.findPendentesByUser")
                .setParameter("nota", 0)
                .setParameter("userIduser", idUser)
                .getResultList();

        return resultado;
    }
    
    public List<Avaliacao> AvaliacoesAvaliadasByUser(int idUser) {
        List<Avaliacao> resultado = null;

        resultado = em.createNamedQuery("Avaliacao.findAvaliadosByUser")
                .setParameter("nota", 0)
                .setParameter("userIduser", idUser)
                .getResultList();

        return resultado;
    }
}
