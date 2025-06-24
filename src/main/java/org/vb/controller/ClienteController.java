package org.vb.controller;

import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.request.UpdateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.service.ClienteService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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


    @Operation(summary = "Listar clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes obtenidos correctamente")
    })
    @GetMapping
    public List<ClienteResponseDTO> getAllClientes(
    ) {
        return clienteService.getClientes();
    }

    @Operation(summary = "Listar datos de un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable String id) {
        ClienteResponseDTO cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Actualizar datos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable String id, @RequestBody UpdateClienteDTO clienteDetails) throws JsonPatchException, IOException {
        ClienteResponseDTO updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }
}
