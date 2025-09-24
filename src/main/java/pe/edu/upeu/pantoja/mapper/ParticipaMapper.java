package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.entity.Participa;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;
@Mapper(componentModel = "spring")
public interface ParticipaMapper extends BaseMapper<Participa, ParticipaDTO> {
}
