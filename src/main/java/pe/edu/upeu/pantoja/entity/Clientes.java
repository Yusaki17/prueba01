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
    @Column(name = "TELEFONO", unique = true, nullable = false, length = 45)
    private String telefono;
    @Column(name = "DOMICILIO", unique = true, nullable = false, length = 45)
    private String domicilio;
    @Column(name = "RAZON_SOCIAL", unique = true, nullable = false, length = 45)
    private String razonsocial;

}
