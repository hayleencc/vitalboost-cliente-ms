package org.vb.service;

import org.springframework.stereotype.Service;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.exception.EmailAlreadyExistsException;
import org.vb.mapper.ClienteMapper;
import org.vb.model.entity.Cliente;
import org.vb.repository.ClienteRepository;

import java.util.List;

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

}
