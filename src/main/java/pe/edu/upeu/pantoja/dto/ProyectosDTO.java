package pe.edu.upeu.pantoja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectosDTO {
    private Long id;
    private String descripcion;
    private BigDecimal cuentas;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private Long clienteId;

}
