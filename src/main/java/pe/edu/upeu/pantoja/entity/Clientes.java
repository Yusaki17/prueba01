package pe.edu.upeu.pantoja.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="TBL_CLIENTES")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;

}
