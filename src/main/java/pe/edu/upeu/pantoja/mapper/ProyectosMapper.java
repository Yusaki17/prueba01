package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.pantoja.dto.ProyectosDTO;
import pe.edu.upeu.pantoja.entity.Proyectos;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;

@Mapper(componentModel = "spring", uses = {ParticipaMapper.class})
public interface ProyectosMapper extends BaseMapper<Proyectos,ProyectosDTO> {

    @Mapping(source = "clienteId.id", target = "clienteId")
    ProyectosDTO toDTO(Proyectos entity);

    @Mapping(source = "clienteId", target = "clienteId.id")
    Proyectos toEntity(ProyectosDTO dto);
}