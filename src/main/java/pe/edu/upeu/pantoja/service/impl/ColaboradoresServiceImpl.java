package pe.edu.upeu.pantoja.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.pantoja.dto.ColaboradoresDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import pe.edu.upeu.pantoja.entity.Colaboradores;
import pe.edu.upeu.pantoja.mapper.ColaboradoresMapper;
import pe.edu.upeu.pantoja.repository.ColaboradoresRepository;
import pe.edu.upeu.pantoja.service.service.ColaboradoresService;

import java.util.List;
@Service
public class ColaboradoresServiceImpl implements ColaboradoresService {
    private final ColaboradoresRepository colaboradoresRepository;
    private final ColaboradoresMapper colaboradoresMapper;

    public ColaboradoresServiceImpl(ColaboradoresRepository colaboradoresRepository, ColaboradoresMapper colaboradoresMapper) {
        this.colaboradoresRepository = colaboradoresRepository;
        this.colaboradoresMapper = colaboradoresMapper;
    }

    @Override
    public ColaboradoresDTO create(ColaboradoresDTO colaboradoresDTO) throws ServiceException {
        try {
            Colaboradores colaboradores = colaboradoresMapper.toEntity(colaboradoresDTO);
            Colaboradores colaboradoresSaved = colaboradoresRepository.save(colaboradores);
            return colaboradoresMapper.toDTO(colaboradoresSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear colaboradores",e);
        }
    }

    @Override
    public ColaboradoresDTO update(Long aLong, ColaboradoresDTO colaboradoresDTO) throws ServiceException {
        try {
            Colaboradores colaboradores = colaboradoresRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("colaboradores no encontrados"));
            colaboradores.setTelefono(colaboradoresDTO.getTelefono());
            Colaboradores colaboradoresUpdate = colaboradoresRepository.save(colaboradores);
            return colaboradoresMapper.toDTO(colaboradoresUpdate);
        } catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al actualizar colaboradores",e);
        }
    }

    @Override
    public ColaboradoresDTO findById(Long aLong) throws ServiceException {
        try {
            Colaboradores colaboradores = colaboradoresRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("colaboradores con ID "+aLong+" no fue encontrada"));
            return colaboradoresMapper.toDTO(colaboradores);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el colaborador con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!colaboradoresRepository.findById(aLong).isPresent()){
                throw new ResourceNotFoundException("colaboradores con ID "+aLong+" no fue encontrados");
            }
            colaboradoresRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al eliminar el colaborador con id " + aLong, e);
        }
    }

    @Override
    public List<ColaboradoresDTO> findAll() throws ServiceException {
        try {
            List<Colaboradores> colaboradores = colaboradoresRepository.findAll();
            return colaboradoresMapper.toDTOs(colaboradores);
        }catch (Exception e) {
            throw new ServiceException("Error al listar los colaboradores",e);
        }
    }
}
