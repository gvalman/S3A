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
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sessionbeans.AvaliacaoFacade;
import sessionbeans.UbsFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AvaliacaoController")
@ViewScoped
public class AvaliacaoController {

    @EJB
    private AvaliacaoFacade avaliacaoFacade;
    @EJB
    private UbsFacade ubsFacade;

    public List<Avaliacao> AvaliacoesPendentesPeloUser() {
        List<Avaliacao> Lista = null;
        User UsuarioLoagado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Lista = avaliacaoFacade.AvaliacoesPendentesByUser(UsuarioLoagado.getIduser());
        return Lista;
    }

    public List<Avaliacao> AvaliacoesRealiazadasPeloUser() {
        List<Avaliacao> Lista = null;
        User UsuarioLoagado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Lista = avaliacaoFacade.AvaliacoesAvaliadasByUser(UsuarioLoagado.getIduser());
        return Lista;
    }

    public List<Ubs> ListarUnidadeAvaliacaoPendente() {

        Date data = new Date();
        SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
        String dia = formata.format(data);
        formata = new SimpleDateFormat("hh:mm:ss");
        String hora = formata.format(data);
        System.out.println(dia + " " + hora);

        List<Ubs> Todas = ubsFacade.findAll(), resultado = new ArrayList<Ubs>();
        List<Avaliacao> AvaliacaoPendentes;
        User UsuarioLoagado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        boolean adicionar;
        for (Ubs unidade : Todas) {
            adicionar = false;
            for (Comentario comentario : unidade.getComentarioCollection()) {
                AvaliacaoPendentes = new ArrayList<Avaliacao>();
                for (Avaliacao avaliacao : comentario.getAvaliacaoCollection()) {
                    if (avaliacao.getAceitacao() && avaliacao.getNota() == 0 && avaliacao.getUser().getIduser() == UsuarioLoagado.getIduser()) {
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

        return resultado;
    }
}
