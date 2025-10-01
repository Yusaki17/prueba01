package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.entity.Participa;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface ParticipaMapper extends BaseMapper<Participa,ParticipaDTO> {

    @Mapping(source = "proyectoId.id", target = "proyectoId")
    @Mapping(source = "colaboradorId.id", target = "colaboradorId")
    ParticipaDTO toDTO(Participa entity);

    @Mapping(source = "proyectoId", target = "proyectoId.id")
    @Mapping(source = "colaboradorId", target = "colaboradorId.id")
    Participa toEntity(ParticipaDTO dto);

}
