package pe.edu.upeu.pantoja.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.entity.Colaboradores;
import pe.edu.upeu.pantoja.entity.Participa;
import pe.edu.upeu.pantoja.entity.Proyectos;
import pe.edu.upeu.pantoja.mapper.ParticipaMapper;
import pe.edu.upeu.pantoja.repository.ColaboradoresRepository;
import pe.edu.upeu.pantoja.repository.ParticipaRepository;
import pe.edu.upeu.pantoja.repository.ProyectosRepository;
import pe.edu.upeu.pantoja.service.service.ParticipaService;

import java.util.List;
@Service
public class ParticipaServiceImpl implements ParticipaService {
    private final ParticipaRepository participaRepository;
    private final ParticipaMapper participaMapper;
    private final ProyectosRepository proyectosRepository;
    private final ColaboradoresRepository colaboradoresRepository;

    public ParticipaServiceImpl(ParticipaRepository participaRepository, ParticipaMapper participaMapper, ProyectosRepository proyectosRepository, ColaboradoresRepository colaboradoresRepository) {
        this.participaRepository = participaRepository;
        this.participaMapper = participaMapper;
        this.proyectosRepository = proyectosRepository;
        this.colaboradoresRepository = colaboradoresRepository;
    }


    @Override
    public ParticipaDTO create(ParticipaDTO participaDTO) throws ServiceException {
        if(participaDTO==null){
            throw new IllegalArgumentException("La participa no puede ser nulo.");
        }
        try {
            Participa participa = participaMapper.toEntity(participaDTO);
            return participaMapper.toDTO(participaRepository.save(participa));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ParticipaDTO update(Long aLong, ParticipaDTO participaDTO) throws ServiceException {
        if (aLong == null || participaDTO == null) {
            throw new IllegalArgumentException("El ID y el participa no pueden ser nulos");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("participa no encontrado con ID: " + aLong));


        try {
            if(participaDTO.getProyectoId()!=null){
                Proyectos proyectos = proyectosRepository.findById(participaDTO.getProyectoId())
                        .orElseThrow(()-> new ResourceNotFoundException("proyectos no encontrado con ID "+aLong));
                existente.setProyectoId(proyectos);
            }
            if(participaDTO.getColaboradorId()!=null){
                Colaboradores colaboradores = colaboradoresRepository.findById(participaDTO.getColaboradorId())
                        .orElseThrow(()-> new ResourceNotFoundException("colaboradores no encontrado con ID "+aLong));
                existente.setColaboradorId(colaboradores);

            }

            Participa actualizado = participaRepository.save(existente);
            return participaMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ParticipaDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulos");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("participa no encontrado con ID: " + aLong));
        try {
            return participaMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("participa no encontrado con ID: " + aLong));
        try{
            participaRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<ParticipaDTO> findAll() throws ServiceException {
        List<Participa> participa = participaRepository.findAll();
        if (participa.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron participas registrados");
        }

        return participa.stream()
                .map(participaMapper::toDTO)
                .toList();
    }
}
