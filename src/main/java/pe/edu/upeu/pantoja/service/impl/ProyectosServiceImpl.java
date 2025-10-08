package pe.edu.upeu.pantoja.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.pantoja.dto.ParticipaDTO;
import pe.edu.upeu.pantoja.dto.ProyectosDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import pe.edu.upeu.pantoja.entity.Colaboradores;
import pe.edu.upeu.pantoja.entity.Participa;
import pe.edu.upeu.pantoja.entity.Proyectos;
import pe.edu.upeu.pantoja.mapper.ProyectosMapper;
import pe.edu.upeu.pantoja.repository.ClientesRepository;
import pe.edu.upeu.pantoja.repository.ColaboradoresRepository;
import pe.edu.upeu.pantoja.repository.ParticipaRepository;
import pe.edu.upeu.pantoja.repository.ProyectosRepository;
import pe.edu.upeu.pantoja.service.service.ProyectosService;

import java.util.List;
@Service
public class ProyectosServiceImpl implements ProyectosService {
    private final ProyectosRepository proyectosRepository;
    private final ProyectosMapper proyectosMapper;
    private final ClientesRepository clientesRepository;
    private final ParticipaRepository participaRepository;
    private final ColaboradoresRepository colaboradoresRepository;

    public ProyectosServiceImpl(ProyectosRepository proyectosRepository, ProyectosMapper proyectosMapper, ClientesRepository clientesRepository, ParticipaRepository participaRepository, ColaboradoresRepository colaboradoresRepository) {
        this.proyectosRepository = proyectosRepository;
        this.proyectosMapper = proyectosMapper;
        this.clientesRepository = clientesRepository;
        this.participaRepository = participaRepository;
        this.colaboradoresRepository = colaboradoresRepository;
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
            existente.setDescripcion(proyectosDTO.getDescripcion());
            existente.setCuentas(proyectosDTO.getCuentas());
            existente.setFechaInicio(proyectosDTO.getFechaInicio());
            existente.setFechaFin(proyectosDTO.getFechaFin());

            if(proyectosDTO.getClienteId() != null){
                Clientes clientes = clientesRepository.findById(proyectosDTO.getClienteId())
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID " + proyectosDTO.getClienteId()));
                existente.setClienteId(clientes);
            }

            // AGREGAR: Manejo de la lista participa
            if(proyectosDTO.getParticipa() != null && !proyectosDTO.getParticipa().isEmpty()) {
                // Limpiar la lista existente
                existente.getParticipa().clear();

                // Agregar los nuevos participantes
                for(ParticipaDTO participaDTO : proyectosDTO.getParticipa()) {
                    Participa participa = new Participa();

                    // Si tiene ID, buscar el existente
                    if(participaDTO.getId() != null) {
                        Participa participaExistente = participaRepository.findById(participaDTO.getId())
                                .orElse(new Participa());
                        participa = participaExistente;
                    }

                    // Establecer el proyecto
                    participa.setProyectoId(existente);

                    // Establecer el colaborador
                    if(participaDTO.getColaboradorId() != null) {
                        Colaboradores colaborador = colaboradoresRepository.findById(participaDTO.getColaboradorId())
                                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID " + participaDTO.getColaboradorId()));
                        participa.setColaboradorId(colaborador);
                    }

                    existente.getParticipa().add(participa);
                }
            }

            Proyectos actualizado = proyectosRepository.save(existente);
            return proyectosMapper.toDTO(actualizado);

        } catch (Exception e) {
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
