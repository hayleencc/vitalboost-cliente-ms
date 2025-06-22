package org.vb.service.utils;

import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.request.UpdateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.model.entity.Cliente;

import java.util.UUID;

public class TestDataFactory {
    public static CreateClienteDTO createClienteDTO(){
        CreateClienteDTO dto = new CreateClienteDTO();
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }

    public static Cliente createCliente(){
        Cliente dto = new Cliente();
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");

        return dto;
    }

    public static ClienteResponseDTO responseClienteDTO(){
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }

    public static Cliente createClienteEntityWithId(UUID id) {
        return new Cliente(id, "Nombre Test", "test@mail.com");
    }

    public static ClienteResponseDTO createClienteResponseDTOWithId(UUID id) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(id);
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }

    public static UpdateClienteDTO updateClienteDTO(){
        UpdateClienteDTO dto = new UpdateClienteDTO();
        dto.setNombreCompleto("Editado Nombre Test");
        dto.setEmail("testedited@mail.com");
        return dto;
    }

}
