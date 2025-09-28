package pe.edu.upeu.pantoja.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.pantoja.dto.ProyectosDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import pe.edu.upeu.pantoja.entity.Proyectos;
import pe.edu.upeu.pantoja.mapper.ProyectosMapper;
import pe.edu.upeu.pantoja.repository.ClientesRepository;
import pe.edu.upeu.pantoja.repository.ProyectosRepository;
import pe.edu.upeu.pantoja.service.service.ProyectosService;

import java.util.List;
@Service
public class ProyectosServiceImpl implements ProyectosService {
    private final ProyectosRepository proyectosRepository;
    private final ProyectosMapper proyectosMapper;
    private final ClientesRepository clientesRepository;

    public ProyectosServiceImpl(ProyectosRepository proyectosRepository, ProyectosMapper proyectosMapper, ClientesRepository clientesRepository) {
        this.proyectosRepository = proyectosRepository;
        this.proyectosMapper = proyectosMapper;
        this.clientesRepository = clientesRepository;
    }

    @Override
    public ProyectosDTO create(ProyectosDTO proyectosDTO) throws ServiceException {
        if(proyectosDTO==null){
            throw new IllegalArgumentException("los proyectos no puede ser nulo.");
        }
        try {
            Proyectos proyectos = proyectosMapper.toEntity(proyectosDTO);
            return proyectosMapper.toDTO(proyectosRepository.save(proyectos));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ProyectosDTO update(Long aLong, ProyectosDTO proyectosDTO) throws ServiceException {
        if (aLong == null || proyectosDTO == null) {
            throw new IllegalArgumentException("El ID y el proyecto no pueden ser nulos");
        }
        Proyectos existente = proyectosRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("proyectos no encontrado con ID: " + aLong));

        try {

            if(proyectosDTO.getClienteId()!=null){
                Clientes clientes = clientesRepository.findById(proyectosDTO.getClienteId())
                        .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado con ID "+aLong));
                existente.setClienteId(clientes);
            }
            Proyectos actualizado = proyectosRepository.save(existente);
            return proyectosMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProyectosDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulos");
        }
        Proyectos existente = proyectosRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyectos no encontrado con ID: " + aLong));
        try {
            return proyectosMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Proyectos existente = proyectosRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyectos no encontrado con ID: " + aLong));
        try{
            proyectosRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProyectosDTO> findAll() throws ServiceException {
        List<Proyectos> proyectos = proyectosRepository.findAll();
        if (proyectos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron proyectos registrados");
        }

        return proyectos.stream()
                .map(proyectosMapper::toDTO)
                .toList();
    }
}
