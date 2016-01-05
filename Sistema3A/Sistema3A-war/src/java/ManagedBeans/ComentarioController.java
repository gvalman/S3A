/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Avaliacao;
import entidade.AvaliacaoPK;
import entidade.Comentario;
import entidade.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sessionbeans.AssuntoFacade;
import sessionbeans.AvaliacaoFacade;
import sessionbeans.ComentarioFacade;
import sessionbeans.UbsFacade;
import sessionbeans.UserFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "ComentarioController")
@ViewScoped
public class ComentarioController {

    //@ManagedProperty(value = "#{AplicacaoController}")
    //private AplicacaoController aplControl;
    @EJB
    private ComentarioFacade comentarioFacade;

    @EJB
    private UbsFacade ubsFacade;
    @EJB
    private AssuntoFacade assuntoFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private AvaliacaoFacade avaliacaoFacade;

    private Comentario comentario;

    private String titulo;
    private String descricao;
    private int IdUnidade;
    private int Idassunto;
    private User UsuarioLogado;

    public void CadastrarComentario() {

        String saida = null;
        comentario = new Comentario();
        comentario.setTitulo(titulo);
        comentario.setDescricao(descricao);

        comentario.setUBSidUBS(ubsFacade.find(IdUnidade));
        //comentario.setUBSidUBS(aplicacaoControl.ObterUnidade(IdUnidade));
        comentario.setASSUNTOidassunto(assuntoFacade.find(Idassunto));
        //comentario.setASSUNTOidassunto(aplicacaoControl.ObterAssunto(Idassunto));

        UsuarioLogado = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        comentario.setUserIduser(UsuarioLogado);
        Date data = new Date();
        comentario.setData(data);
        comentario.setHora(data);
        try {
            comentarioFacade.create(comentario);
            GerarAvaliacaoAceitacao();
            saida = "Cadastro realizado com sucesso!";
        } catch (Exception e) {
            saida = "Cadastro não pode ser realizado.Tente Novamente!";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema 3A Saúde", saida));
    }

    private void GerarAvaliacaoAceitacao() {

        ArrayList<Integer> Escolhidos = new ArrayList<Integer>();
        List<User> Users = userFacade.findAll();
        int escolhido;
        boolean Adicionar = true;
        Random gerador = new Random();
        Avaliacao avaliacao = new Avaliacao();
        AvaliacaoPK pk = new AvaliacaoPK();

        while (Escolhidos.size() < 3) {
            escolhido = gerador.nextInt(Users.size());
            for (int i : Escolhidos) {
                if (i == escolhido || Users.get(i).getIduser() == UsuarioLogado.getIduser()) {
                    Adicionar = false;
                    break;
                }
            }
            if (Adicionar) {
                pk.setUserIduser(Users.get(escolhido).getIduser());
                pk.setComentarioIdcomentario(comentarioFacade.findAll().get(comentarioFacade.findAll().size() - 1).getIdcomentario());
                avaliacao.setAvaliacaoPK(pk);

                avaliacao.setAceitacao(true);
                avaliacaoFacade.create(avaliacao);

                Escolhidos.add(escolhido);
            }
            Adicionar = true;
        }
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
     * @return the IdUnidade
     */
    public int getIdUnidade() {
        return IdUnidade;
    }

    /**
     * @param IdUnidade the IdUnidade to set
     */
    public void setIdUnidade(int IdUnidade) {
        this.IdUnidade = IdUnidade;
    }

    /**
     * @return the Idassunto
     */
    public int getIdassunto() {
        return Idassunto;
    }

    /**
     * @param Idassunto the Idassunto to set
     */
    public void setIdassunto(int Idassunto) {
        this.Idassunto = Idassunto;
    }
}
