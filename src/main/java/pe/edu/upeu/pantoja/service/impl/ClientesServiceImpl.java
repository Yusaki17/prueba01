package pe.edu.upeu.pantoja.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.pantoja.dto.ClientesDTO;
import pe.edu.upeu.pantoja.entity.Clientes;
import pe.edu.upeu.pantoja.mapper.ClientesMapper;
import pe.edu.upeu.pantoja.repository.ClientesRepository;
import pe.edu.upeu.pantoja.service.service.ClientesService;

import java.util.List;
@Service
public class ClientesServiceImpl implements ClientesService {
    private final ClientesRepository clientesRepository;
    private final ClientesMapper clientesMapper;

    public ClientesServiceImpl(ClientesRepository clientesRepository, ClientesMapper clientesMapper) {
        this.clientesRepository = clientesRepository;
        this.clientesMapper = clientesMapper;
    }

    @Override
    public ClientesDTO create(ClientesDTO clientesDTO) throws ServiceException {
        try {
            Clientes clientes = clientesMapper.toEntity(clientesDTO);
            Clientes clientesSaved = clientesRepository.save(clientes);
            return clientesMapper.toDTO(clientesSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear clientes",e);
        }
    }

    @Override
    public ClientesDTO update(Long aLong, ClientesDTO clientesDTO) throws ServiceException {
        try {
            Clientes clientes = clientesRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("Clientes no encontrada"));
            clientes.setTelefono(clientesDTO.getTelefono());
            clientes.setDomicilio(clientesDTO.getDomicilio());
            clientes.setRazonsocial(clientesDTO.getRazonsocial());
            Clientes clientesUpdate = clientesRepository.save(clientes);
            return clientesMapper.toDTO(clientesUpdate);
        } catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al actualizar clientes",e);
        }
    }

    @Override
    public ClientesDTO findById(Long aLong) throws ServiceException {
        try {
            Clientes clientes = clientesRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("cliente con ID "+aLong+" no fue encontrada"));
            return clientesMapper.toDTO(clientes);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el cliente con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!clientesRepository.findById(aLong).isPresent()){
                throw new ResourceNotFoundException("Clientes con ID "+aLong+" no fue encontrada");
            }
            clientesRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al eliminar el cliente con id " + aLong, e);
        }
    }

    @Override
    public List<ClientesDTO> findAll() throws ServiceException {
        try {
            List<Clientes> clientes = clientesRepository.findAll();
            return clientesMapper.toDTOs(clientes);
        }catch (Exception e) {
            throw new ServiceException("Error al listar los clientes",e);
        }
    }
}
