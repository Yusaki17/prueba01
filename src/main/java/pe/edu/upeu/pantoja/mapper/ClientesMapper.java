package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientesMapper {
    ClientesDTO toDTO(Clientes entity);
    Clientes toEntity(ClientesDTO dto);
    List<ClientesDTO> toDTOs(List<Clientes> list);
    List<Clientes> toEntityList(List<ClientesDTO> list);
}
