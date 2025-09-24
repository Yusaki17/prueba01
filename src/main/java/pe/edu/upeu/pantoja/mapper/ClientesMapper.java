package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;


@Mapper(componentModel = "spring")
public interface ClientesMapper extends BaseMapper<Clientes, ClientesDTO> {
}
