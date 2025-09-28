package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectosDTO {
    private Long id;
    private String descripcion;
    private BigDecimal cuentas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long clienteId;
    private List<ParticipaDTO> participa;
}
