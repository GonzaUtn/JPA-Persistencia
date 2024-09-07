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
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String apellido;

    @Column(name = "DNI", unique = true)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Factura> factura = new ArrayList<Factura>();

}
