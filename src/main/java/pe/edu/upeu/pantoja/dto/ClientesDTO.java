package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientesDTO {
    private Long id;
    private String telefono;
    private String domicilio;
    private String razonSocial;
}
