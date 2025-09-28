package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientesDTO {
    private Long id;
    private String telefono;
    private String domicilio;
    private String razonsocial;
    private List<ProyectosDTO> proyectos;
}
