package pe.edu.upeu.pantoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.pantoja.entity.Clientes;

public interface ClientesRepository  extends JpaRepository<Clientes, Long> {
}
