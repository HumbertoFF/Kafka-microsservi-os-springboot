package com.humbertoff.github.clientes.controller.mappers;

import com.humbertoff.github.clientes.controller.dto.ClienteDTO;
import com.humbertoff.github.clientes.model.Cliente;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente map(ClienteDTO dto);
    ClienteDTO dtoToObj(Optional<Cliente> cliente);
}
