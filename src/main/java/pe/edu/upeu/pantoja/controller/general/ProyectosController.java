package pe.edu.upeu.pantoja.controller.general;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upeu.pantoja.dto.ProyectosDTO;

import pe.edu.upeu.pantoja.service.service.ProyectosService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ProyectosController {
    private final ProyectosService proyectosService;

    public ProyectosController(ProyectosService proyectosService) {

        this.proyectosService = proyectosService;
    }
    @GetMapping
    public ResponseEntity<List<ProyectosDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(proyectosService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProyectosDTO> read(@PathVariable Long id) throws ServiceException {
        ProyectosDTO proyectosDTO = proyectosService.findById(id);
        return ResponseEntity.ok(proyectosDTO);
    }
    @PostMapping
    public ResponseEntity<ProyectosDTO> create(@RequestBody ProyectosDTO proyectosDTO) throws ServiceException {
        ProyectosDTO proyectosDTO1 = proyectosService.create(proyectosDTO);
        return new ResponseEntity<>(proyectosDTO1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProyectosDTO> update(@PathVariable Long id, @RequestBody ProyectosDTO proyectosDTO) throws ServiceException {
        ProyectosDTO proyectosDTO1 = proyectosService.update(id,proyectosDTO);
        return ResponseEntity.ok(proyectosDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        proyectosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
