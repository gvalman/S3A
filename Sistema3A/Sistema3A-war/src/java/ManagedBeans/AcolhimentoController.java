/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Avaliacao;
import entidade.Comentario;
import entidade.Ubs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import sessionbeans.AvaliacaoFacade;
import sessionbeans.ComentarioFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AcolhimentoController")
@ViewScoped
public class AcolhimentoController implements Serializable {

    /*@ManagedProperty(value = "#{AplicacaoController}")
     private UbsController ubsControl;
     */
    @EJB
    private ComentarioFacade comentarioFacade;
    @EJB
    private AvaliacaoFacade avaliacaoFacade;
    private Ubs UnitySelected;
    private int mediaSelecionado;

    /**
     * @return the UnitySelected
     */
    public Ubs getUnitySelected() {
        return UnitySelected;
    }

    /**
     * @param UnitySelected the UnitySelected to set
     */
    public void setUnitySelected(Ubs UnitySelected) {
        this.UnitySelected = UnitySelected;
    }

    public List<Comentario> ComentariosUnidadeAceitos() {
        List<Comentario> resultado = null, Temp;
        List<Avaliacao> avaliacoesAvaliadas;
        int SomaNota = 0;

        for (Comentario comentario : comentarioFacade.FindComentarioByUbs(UnitySelected)) {
            avaliacoesAvaliadas = new ArrayList<Avaliacao>();
            SomaNota = 0;
            for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                if (avaliacao.getAceitacao() && avaliacao.getNota() > 0) {
                    avaliacoesAvaliadas.add(avaliacao);
                    SomaNota += avaliacao.getNota();
                }
            }
            if (avaliacoesAvaliadas.size() == 3 && (SomaNota / avaliacoesAvaliadas.size()) >= 7) {
                mediaSelecionado = SomaNota / avaliacoesAvaliadas.size();
                comentario.setAvaliacaoCollection(avaliacoesAvaliadas);
                resultado.add(comentario);
            }
        }
        return resultado;
    }

    public List<Avaliacao> FindAvaliacoesByComentario(Comentario IdComentario) {
        List<Avaliacao> resultado = null;
        resultado = avaliacaoFacade.FindAvaliacaoByComentario(IdComentario);
        return resultado;
    }

    /**
     * @return the mediaSelecionado
     */
    public int getMediaSelecionado() {
        return mediaSelecionado;
    }

    /**
     * @param mediaSelecionado the mediaSelecionado to set
     */
    public void setMediaSelecionado(int mediaSelecionado) {
        this.mediaSelecionado = mediaSelecionado;
    }
}
