package org.vb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.model.entity.Cliente;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClienteMapper {
    public abstract Cliente toEntity(CreateClienteDTO clienteDTO);
    public abstract ClienteResponseDTO toResponseDTO(Cliente cliente);

}
