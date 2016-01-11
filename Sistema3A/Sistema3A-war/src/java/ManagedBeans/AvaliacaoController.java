/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Avaliacao;
import entidade.Comentario;
import entidade.Ubs;
import entidade.User;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;
import sessionbeans.AvaliacaoFacade;
import sessionbeans.UbsFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AvaliacaoController")
@ViewScoped
public class AvaliacaoController implements Serializable {

    @EJB
    private AvaliacaoFacade avaliacaoFacade;
    @EJB
    private UbsFacade ubsFacade;
    private List<Ubs> UnidadesAvaliacaoPendente, UnidadesAvaliacaoAvaliadas;
    private String titulo, descricao;
    private int nota;
    private Avaliacao avaliacaoSelecionado;

    @PostConstruct
    public void init() {
        ListarUnidadeAvaliacaoPendente();
        ListarUnidadeAvaliacaoAvaliadas();
    }
    
    @PreDestroy
    public void finalizar(){
        UnidadesAvaliacaoPendente= null;
        UnidadesAvaliacaoAvaliadas=null;
    }
    
    public void ListarUnidadeAvaliacaoAvaliadas() {

        List<Ubs> Todas = ubsFacade.findAll(), resultado = new ArrayList<Ubs>();
        List<Avaliacao> AvaliacaoPendentes;
        User UsuarioLoagado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        boolean adicionar;
        for (Ubs unidade : Todas) {
            adicionar = false;
            for (Comentario comentario : unidade.getComentarioCollection()) {
                AvaliacaoPendentes = new ArrayList<Avaliacao>();
                for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                    if (avaliacao.getAceitacao() && avaliacao.getNota() != 0 && avaliacao.getUserIduser().getIduser() == UsuarioLoagado.getIduser()) {
                        AvaliacaoPendentes.add(avaliacao);
                        adicionar = true;
                    }
                }
                comentario.setAvaliacaoCollection(AvaliacaoPendentes);
            }
            if (adicionar) {
                resultado.add(unidade);
            }
        }
        UnidadesAvaliacaoAvaliadas = resultado;
    }

    public void ListarUnidadeAvaliacaoPendente() {
        /*
         Date data = new Date();
         SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
         String dia = formata.format(data);
         formata = new SimpleDateFormat("hh:mm:ss");
         String hora = formata.format(data);
         System.out.println(dia + " " + hora);
         */

        List<Ubs> Todas = ubsFacade.findAll(), resultado = new ArrayList<Ubs>();
        List<Avaliacao> AvaliacaoPendentes;
        User UsuarioLoagado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        boolean adicionar;
        for (Ubs unidade : Todas) {
            adicionar = false;
            for (Comentario comentario : unidade.getComentarioCollection()) {
                AvaliacaoPendentes = new ArrayList<Avaliacao>();
                for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                    if (avaliacao.getAceitacao() && avaliacao.getNota() == 0 && avaliacao.getUserIduser().getIduser() == UsuarioLoagado.getIduser()) {
                        AvaliacaoPendentes.add(avaliacao);
                        adicionar = true;
                        //System.out.println("Avaliação: " + avaliacao.getIdAvaliacao());
                    }
                }
                comentario.setAvaliacaoCollection(AvaliacaoPendentes);
            }
            if (adicionar) {
                resultado.add(unidade);
            }
        }

        for (Ubs unidade : resultado) {
            for (Comentario comentario : unidade.getComentarioCollection()) {
                for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                    System.out.println();
                    System.out.println("Usuário: " + avaliacao.getUserIduser().getIduser()
                            + " Unidade: " + unidade.getIdUBS()
                            + " Comentário: " + comentario.getIdcomentario()
                            + " Avaliação: " + avaliacao.getIdAvaliacao());
                }
            }
        }

        UnidadesAvaliacaoPendente = resultado;
    }

    public void AtualizarAvaliacao() {
        avaliacaoSelecionado.setNota(nota);
        avaliacaoSelecionado.setTitulo(titulo);
        avaliacaoSelecionado.setDescricao(descricao);

        /*
         System.out.println(titulo + " " + descricao + " " + nota);
         System.out.println("Avaliação: " + avaliacaoSelecionado.getIdAvaliacao() + " "
         + "Comentário: " + avaliacaoSelecionado.getComentarioIdcomentario().getIdcomentario() + " "
         + "User: " + avaliacaoSelecionado.getUserIduser().getIduser() + " "
         + "Nota: " + avaliacaoSelecionado.getNota() + " "
         + "Titulo: " + avaliacaoSelecionado.getTitulo() + " "
         + "Descrição: " + avaliacaoSelecionado.getDescricao() + " "
         + "Aceitação: " + avaliacaoSelecionado.getAceitacao());
         */

        avaliacaoFacade.edit(avaliacaoSelecionado);
        nota = 0;
        titulo = null;
        descricao = null;
    }

    public void ObterNota(RateEvent rateEvent) {
        setNota(((Integer) rateEvent.getRating()).intValue());
    }

    /**
     * @return the avaliacaoSelecionado
     */
    public Avaliacao getAvaliacaoSelecionado() {
        return avaliacaoSelecionado;
    }

    /**
     * @param avaliacaoSelecionado the avaliacaoSelecionado to set
     */
    public void setAvaliacaoSelecionado(Avaliacao avaliacaoSelecionado) {
        this.avaliacaoSelecionado = avaliacaoSelecionado;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * @return the UnidadesAvaliacaoPendente
     */
    public List<Ubs> getUnidadesAvaliacaoPendente() {
        return UnidadesAvaliacaoPendente;
    }

    /**
     * @param UnidadesAvaliacaoPendente the UnidadesAvaliacaoPendente to set
     */
    public void setUnidadesAvaliacaoPendente(List<Ubs> UnidadesAvaliacaoPendente) {
        this.UnidadesAvaliacaoPendente = UnidadesAvaliacaoPendente;
    }

    /**
     * @return the UnidadesAvaliacaoAvaliadas
     */
    public List<Ubs> getUnidadesAvaliacaoAvaliadas() {
        return UnidadesAvaliacaoAvaliadas;
    }

    /**
     * @param UnidadesAvaliacaoAvaliadas the UnidadesAvaliacaoAvaliadas to set
     */
    public void setUnidadesAvaliacaoAvaliadas(List<Ubs> UnidadesAvaliacaoAvaliadas) {
        this.UnidadesAvaliacaoAvaliadas = UnidadesAvaliacaoAvaliadas;
    }
}
