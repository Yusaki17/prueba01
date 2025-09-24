package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColaboradoresDTO {
    private Long id;
    private String nif;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String banco;
    private String numcuenta;
}
