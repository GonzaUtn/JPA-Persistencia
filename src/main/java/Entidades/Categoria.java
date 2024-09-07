package Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="Categoria")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;

    @ManyToMany(mappedBy = "categoria")
    @Builder.Default
    private List<Articulo> articulo = new ArrayList<Articulo>();

}