package pe.edu.upeu.pantoja.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "NIF")
    private String nif;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "BANCO")
    private String banco;
    @Column(name = "NUMCUENTA")
    private String numCuenta;
    @OneToMany(mappedBy = "colaboradorId", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Participa> participa = new ArrayList<>();
}
