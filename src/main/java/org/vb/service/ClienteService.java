package org.vb.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.request.UpdateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.exception.EmailAlreadyExistsException;
import org.vb.mapper.ClienteMapper;
import org.vb.model.entity.Cliente;
import org.vb.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseDTO createCliente(CreateClienteDTO cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        Cliente nuevoCliente = clienteMapper.toEntity(cliente);
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        return clienteMapper.toResponseDTO(clienteGuardado);
    }

    public List<ClienteResponseDTO> getClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO getClienteById(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al cliente con ID: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

    public ClienteResponseDTO updateCliente(UUID id, UpdateClienteDTO clienteToUpdate) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al cliente con ID: " + id));

        clienteMapper.updateClienteFromDto(clienteToUpdate, existingCliente);

        Cliente clienteGuardado = clienteRepository.save(existingCliente);
        return clienteMapper.toResponseDTO(clienteGuardado);
    }

}
