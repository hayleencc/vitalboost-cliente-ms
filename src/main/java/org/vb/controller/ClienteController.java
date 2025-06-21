package org.vb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService=clienteService;
    }

    @Operation(summary = "Crear un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Ocurrió un error")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createCliente(@Valid @RequestBody CreateClienteDTO cliente) {
        ClienteResponseDTO nuevoCliente = clienteService.createCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }
}
