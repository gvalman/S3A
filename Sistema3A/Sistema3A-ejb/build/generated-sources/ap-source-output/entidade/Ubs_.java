package entidade;

import entidade.Comentario;
import entidade.Especialidades;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-20T08:34:25")
@StaticMetamodel(Ubs.class)
public class Ubs_ { 

    public static volatile SingularAttribute<Ubs, String> fone;
    public static volatile SingularAttribute<Ubs, Integer> rpa;
    public static volatile SingularAttribute<Ubs, String> endereco;
    public static volatile SingularAttribute<Ubs, String> bairro;
    public static volatile SingularAttribute<Ubs, Double> latitude;
    public static volatile SingularAttribute<Ubs, Integer> cnes;
    public static volatile SingularAttribute<Ubs, String> unidade;
    public static volatile SingularAttribute<Ubs, Integer> microRegiao;
    public static volatile SingularAttribute<Ubs, Integer> idUBS;
    public static volatile CollectionAttribute<Ubs, Especialidades> especialidadesCollection;
    public static volatile CollectionAttribute<Ubs, Comentario> comentarioCollection;
    public static volatile SingularAttribute<Ubs, Double> longitude;

}