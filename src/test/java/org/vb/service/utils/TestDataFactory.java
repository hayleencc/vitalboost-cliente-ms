package org.vb.service.utils;

import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.request.UpdateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.model.entity.Cliente;

import java.util.UUID;

public class TestDataFactory {
    public static final String CLIENTE_ID = "XyZ123abcDEF456ghiJKL789mnoPQR012stuVWX";
    public static final String ROL = "cliente";

    public static CreateClienteDTO createClienteDTO(){
        CreateClienteDTO dto = new CreateClienteDTO();
        dto.setId(CLIENTE_ID);
        dto.setRol(ROL);
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }

    public static Cliente createCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(CLIENTE_ID);
        cliente.setRol(ROL);
        cliente.setNombreCompleto("Nombre Test");
        cliente.setEmail("test@mail.com");
        return cliente;
    }

    public static ClienteResponseDTO responseClienteDTO(){
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(CLIENTE_ID);
        dto.setRol(ROL);
        dto.setNombreCompleto("Nombre Test");
        dto.setEmail("test@mail.com");
        return dto;
    }

    public static Cliente createClienteEntityWithId(String id) {
        return new Cliente(CLIENTE_ID, "Nombre Test", "test@mail.com", ROL);
    }

    public static ClienteResponseDTO createClienteResponseDTOWithId(String id) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(CLIENTE_ID);
        dto.setRol(ROL);
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
