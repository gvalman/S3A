/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidade.Avaliacao;
import entidade.Comentario;
import java.util.ArrayList;
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

    public List<Avaliacao> FindAvaliacaoByComentario(Comentario idComentario) {
        List<Avaliacao> resultado = null, saida = new ArrayList<>();
        resultado = em.createNamedQuery("Avaliacao.findByComentario")
                .setParameter("idComentario", idComentario)
                .getResultList();
        for (Avaliacao avaliacao : resultado) {
            if (!avaliacao.getAceitacao()) {
                saida.add(avaliacao);
            }
        }
        return saida;
    }
}
