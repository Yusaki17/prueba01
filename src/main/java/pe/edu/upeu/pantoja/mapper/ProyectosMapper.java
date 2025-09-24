package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ProyectosDTO;
import pe.edu.upeu.pantoja.entity.Proyectos;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;

@Mapper(componentModel = "spring")

public interface ProyectosMapper extends BaseMapper<Proyectos, ProyectosDTO> {
}
