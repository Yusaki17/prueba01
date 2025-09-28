package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ColaboradoresDTO;
import pe.edu.upeu.pantoja.entity.Colaboradores;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColaboradoresMapper   {
    ColaboradoresDTO toDTO(Colaboradores entity);
    Colaboradores toEntity(ColaboradoresDTO dto);
    List<ColaboradoresDTO> toDTOs(List<Colaboradores> list);
    List<Colaboradores> toEntityList(List<ColaboradoresDTO> list);
}
