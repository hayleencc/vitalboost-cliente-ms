package org.vb.service.utils;

import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.model.entity.Cliente;

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

    public static ClienteResponseDTO reponseClienteDTO(){
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }



}
