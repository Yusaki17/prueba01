package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.pantoja.dto.ProyectosDTO;
import pe.edu.upeu.pantoja.entity.Proyectos;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectosMapper {

    @Mapping(source = "clienteId.id", target = "clienteId")
    ProyectosDTO toDTO(Proyectos entity);

    @Mapping(source = "clienteId", target = "clienteId.id")
    Proyectos toEntity(ProyectosDTO dto);

    List<ProyectosDTO> toDTOs(List<Proyectos> list);
    List<Proyectos> toEntityList(List<ProyectosDTO> list);
}