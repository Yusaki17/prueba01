package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.entity.Participa;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipaMapper {

    @Mapping(source = "proyectoId.id", target = "proyectoId")
    @Mapping(source = "colaboradorId.id", target = "colaboradorId")
    ParticipaDTO toDTO(Participa entity);

    @Mapping(source = "proyectoId", target = "proyectoId.id")
    @Mapping(source = "colaboradorId", target = "colaboradorId.id")
    Participa toEntity(ParticipaDTO dto);

    List<ParticipaDTO> toDTOs(List<Participa> list);
    List<Participa> toEntityList(List<ParticipaDTO> list);
}
