package pe.edu.upeu.pantoja.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja.dto.ColaboradoresDTO;
import pe.edu.upeu.pantoja.entity.Colaboradores;
import pe.edu.upeu.pantoja.mapper.base.BaseMapper;

@Mapper(componentModel = "spring", uses = {ParticipaMapper.class})
public interface ColaboradoresMapper extends BaseMapper<Colaboradores,ColaboradoresDTO> {

}
