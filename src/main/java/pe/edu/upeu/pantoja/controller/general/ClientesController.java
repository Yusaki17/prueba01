package pe.edu.upeu.pantoja.controller.general;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.service.service.ClientesService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {

        this.clientesService = clientesService;
    }
    @GetMapping
    public ResponseEntity<List<ClientesDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(clientesService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientesDTO> read(@PathVariable Long id) throws ServiceException {
        ClientesDTO clientesDTO = clientesService.findById(id);
        return ResponseEntity.ok(clientesDTO);
    }
    @PostMapping
    public ResponseEntity<ClientesDTO> create(@RequestBody ClientesDTO clientesDTO) throws ServiceException {
        ClientesDTO clientesDTO1 = clientesService.create(clientesDTO);
        return new ResponseEntity<>(clientesDTO1,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientesDTO> update(@PathVariable Long id, @RequestBody ClientesDTO clientesDTO) throws ServiceException {
        ClientesDTO clientesDTO1 = clientesService.update(id,clientesDTO);
        return ResponseEntity.ok(clientesDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        clientesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
