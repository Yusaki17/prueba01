package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParticipaDTO {
    private Long id;
    private Long proyectoId;
    private Long colaboradorId;
}
