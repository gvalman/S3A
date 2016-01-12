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
    
    private Comentario comentarioSelecionado;
    private String tituloAvaliacao,descricaoAvaliacao;
    private int notaAvaliacao;
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

        List<Comentario> resultado = new ArrayList<Comentario>(), Temp;
        List<Avaliacao> avaliacoesAvaliadas;
        int SomaNota = 0, media;

        for (Comentario comentario : comentarioFacade.FindComentarioByUbs(UnitySelected)) {
            //System.out.println(comentario);
            avaliacoesAvaliadas = new ArrayList<Avaliacao>();
            SomaNota = 0;
            for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                if (avaliacao.getAceitacao() && avaliacao.getNota() > 0) {
                    //System.out.println(avaliacao + " Nota:" + avaliacao.getNota());
                    avaliacoesAvaliadas.add(avaliacao);
                    SomaNota += avaliacao.getNota();
                }
            }

            media = SomaNota / avaliacoesAvaliadas.size();
            //System.out.println("Soma das Notas: " + SomaNota + " Média:" + mediaSelecionado);
            if (avaliacoesAvaliadas.size() == 3 && media >= 7) {
                comentario.setAvaliacaoCollection(avaliacoesAvaliadas);
                resultado.add(comentario);
            }
        }
        //System.out.println(resultado);
        return resultado;
    }

    public List<Avaliacao> FindAvaliacoesByComentario(Comentario IdComentario) {
        List<Avaliacao> resultado = null;
        resultado = avaliacaoFacade.FindAvaliacaoByComentario(IdComentario);
        return resultado;
    }

    public int CalcularMediaAvaliacoes(List<Avaliacao> Avaliacoes) {
        int media = 0;

        for (Avaliacao avaliacao : Avaliacoes) {
            media += avaliacao.getNota();
        }

        media = media / Avaliacoes.size();
        return media;
    }

    /**
     * @return the comentarioSelecionado
     */
    public Comentario getComentarioSelecionado() {
        return comentarioSelecionado;
    }

    /**
     * @param comentarioSelecionado the comentarioSelecionado to set
     */
    public void setComentarioSelecionado(Comentario comentarioSelecionado) {
        this.comentarioSelecionado = comentarioSelecionado;
    }

    /**
     * @return the tituloAvaliacao
     */
    public String getTituloAvaliacao() {
        return tituloAvaliacao;
    }

    /**
     * @param tituloAvaliacao the tituloAvaliacao to set
     */
    public void setTituloAvaliacao(String tituloAvaliacao) {
        this.tituloAvaliacao = tituloAvaliacao;
    }

    /**
     * @return the descricaoAvaliacao
     */
    public String getDescricaoAvaliacao() {
        return descricaoAvaliacao;
    }

    /**
     * @param descricaoAvaliacao the descricaoAvaliacao to set
     */
    public void setDescricaoAvaliacao(String descricaoAvaliacao) {
        this.descricaoAvaliacao = descricaoAvaliacao;
    }

    /**
     * @return the notaAvaliacao
     */
    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    /**
     * @param notaAvaliacao the notaAvaliacao to set
     */
    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }
}
