package org.vb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.exception.EmailAlreadyExistsException;
import org.vb.mapper.ClienteMapper;
import org.vb.model.entity.Cliente;
import org.vb.repository.ClienteRepository;
import org.vb.service.utils.TestDataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;


    @Test
    void crearCliente_shouldReturnCliente() {
        CreateClienteDTO dto = TestDataFactory.createClienteDTO();
        Cliente cliente = TestDataFactory.createCliente();
        ClienteResponseDTO response = TestDataFactory.reponseClienteDTO();

        when(clienteMapper.toEntity(dto)).thenReturn(cliente);
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.empty());
        when(clienteMapper.toResponseDTO(cliente)).thenReturn(response);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteResponseDTO respuesta = clienteService.createCliente(dto);

        assertNotNull(respuesta);
        assertEquals("Nombre Test", respuesta.getNombreCompleto());
        assertEquals("test@mail.com", respuesta.getEmail());
        verify(clienteMapper).toEntity(dto);
        verify(clienteRepository).save(cliente);
        verify(clienteMapper).toResponseDTO(cliente);
    }

    @Test
    void crearCliente_emailExistente_lanzaExcepcion() {
        CreateClienteDTO dto = TestDataFactory.createClienteDTO();
        Cliente cliente = TestDataFactory.createCliente();
        String email = "test@mail.com";

        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));

        assertThrows(EmailAlreadyExistsException.class, () -> {
            clienteService.createCliente(dto);
        });
    }

    @Test
    void getClientes_siNoExistenCreados_retornaListaVacia() {
        List<Cliente> clientes = new ArrayList<>();
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteResponseDTO> result = clienteService.getClientes();

        assertEquals(0, result.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void getClientes_siExistenCreados_retornarListaExitosamente() {
        Cliente cliente = TestDataFactory.createCliente();
        CreateClienteDTO dto = TestDataFactory.createClienteDTO();
        ClienteResponseDTO response = TestDataFactory.reponseClienteDTO();

        List<Cliente> clientes = List.of(cliente);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteResponseDTO> result = clienteService.getClientes();

        assertEquals(1, result.size());
        verify(clienteRepository).findAll();
    }



}
