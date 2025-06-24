package org.vb.mapper;

import org.mapstruct.*;
import org.vb.dto.request.CreateClienteDTO;
import org.vb.dto.request.UpdateClienteDTO;
import org.vb.dto.response.ClienteResponseDTO;
import org.vb.model.entity.Cliente;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class
ClienteMapper {

    @Mapping(target = "id", source = "id")
    public abstract Cliente toEntity(CreateClienteDTO clienteDTO);

    public abstract ClienteResponseDTO toResponseDTO(Cliente cliente);

    @Mappings({
            @Mapping(target = "rol", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    public abstract void updateClienteFromDto(UpdateClienteDTO dto, @MappingTarget Cliente entity);

}
