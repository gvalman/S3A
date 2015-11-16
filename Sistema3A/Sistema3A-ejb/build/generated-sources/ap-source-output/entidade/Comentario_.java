package entidade;

import entidade.Assunto;
import entidade.Ubs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-16T21:21:02")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Date> data;
    public static volatile SingularAttribute<Comentario, Date> hora;
    public static volatile SingularAttribute<Comentario, Assunto> aSSUNTOidassunto;
    public static volatile SingularAttribute<Comentario, Ubs> uBSidUBS;
    public static volatile SingularAttribute<Comentario, String> titulo;
    public static volatile SingularAttribute<Comentario, String> nome;
    public static volatile SingularAttribute<Comentario, Integer> idcomentario;
    public static volatile SingularAttribute<Comentario, Integer> nota;
    public static volatile SingularAttribute<Comentario, String> email;
    public static volatile SingularAttribute<Comentario, String> descricao;

}