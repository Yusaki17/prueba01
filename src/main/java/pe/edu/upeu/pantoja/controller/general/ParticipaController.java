package pe.edu.upeu.pantoja.controller.general;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.service.service.ClientesService;
import pe.edu.upeu.pantoja.service.service.ParticipaService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/participa")
public class ParticipaController {
    private final ParticipaService participaService;

    public ParticipaController(ParticipaService participaService) {

        this.participaService = participaService;
    }
    @GetMapping
    public ResponseEntity<List<ParticipaDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(participaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParticipaDTO> read(@PathVariable Long id) throws ServiceException {
        ParticipaDTO participaDTO = participaService.findById(id);
        return ResponseEntity.ok(participaDTO);
    }
    @PostMapping
    public ResponseEntity<ParticipaDTO> create(@RequestBody ParticipaDTO participaDTO) throws ServiceException {
        ParticipaDTO participaDTO1 = participaService.create(participaDTO);
        return new ResponseEntity<>(participaDTO1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ParticipaDTO> update(@PathVariable Long id, @RequestBody ParticipaDTO participaDTO) throws ServiceException {
        ParticipaDTO participaDTO1 = participaService.update(id,participaDTO);
        return ResponseEntity.ok(participaDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        participaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
