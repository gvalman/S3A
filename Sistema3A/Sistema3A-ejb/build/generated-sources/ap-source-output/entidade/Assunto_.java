package entidade;

import entidade.Comentario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-05T03:19:43")
@StaticMetamodel(Assunto.class)
public class Assunto_ { 

    public static volatile SingularAttribute<Assunto, Integer> idassunto;
    public static volatile SingularAttribute<Assunto, String> titulo;
    public static volatile CollectionAttribute<Assunto, Comentario> comentarioCollection;
    public static volatile SingularAttribute<Assunto, String> descricao;

}