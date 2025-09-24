package pe.edu.upeu.pantoja.controller.general;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.dto.ColaboradoresDTO;
import pe.edu.upeu.pantoja.service.service.ClientesService;
import pe.edu.upeu.pantoja.service.service.ColaboradoresService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/colaboradores")
public class ColaboradoresController {
    private final ColaboradoresService colaboradoresService;

    public ColaboradoresController(ColaboradoresService colaboradoresService) {

        this.colaboradoresService = colaboradoresService;
    }
    @GetMapping
    public ResponseEntity<List<ColaboradoresDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(colaboradoresService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ColaboradoresDTO> read(@PathVariable Long id) throws ServiceException {
        ColaboradoresDTO colaboradoresDTO = colaboradoresService.findById(id);
        return ResponseEntity.ok(colaboradoresDTO);
    }
    @PostMapping
    public ResponseEntity<ColaboradoresDTO> create(@RequestBody ColaboradoresDTO colaboradoresDTO) throws ServiceException {
        ColaboradoresDTO colaboradoresDTO1 = colaboradoresService.create(colaboradoresDTO);
        return new ResponseEntity<>(colaboradoresDTO1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ColaboradoresDTO> update(@PathVariable Long id, @RequestBody ColaboradoresDTO colaboradoresDTO) throws ServiceException {
        ColaboradoresDTO colaboradoresDTO1 = colaboradoresService.update(id,colaboradoresDTO);
        return ResponseEntity.ok(colaboradoresDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        colaboradoresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
