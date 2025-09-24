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
@Table(name="TBL_COLABORADORES")
public class Colaboradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NIF", unique = true, nullable = false, length = 45)
    private String nif;
    @Column(name = "NOMBRE", unique = true, nullable = false, length = 45)
    private String nombre;
    @Column(name = "DOMICILIO", unique = true, nullable = false, length = 45)
    private String domicilio;
    @Column(name = "TELEFONO", unique = true, nullable = false, length = 45)
    private String telefono;
    @Column(name = "BANCO", unique = true, nullable = false, length = 45)
    private String banco;
    @Column(name = "NUM_CUENTA", unique = true, nullable = false, length = 45)
    private String numCuenta;
}
