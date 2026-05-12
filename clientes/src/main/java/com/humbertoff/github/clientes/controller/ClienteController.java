package com.humbertoff.github.clientes.controller;

import com.humbertoff.github.clientes.controller.dto.ClienteDTO;
import com.humbertoff.github.clientes.controller.mappers.ClienteMapper;
import com.humbertoff.github.clientes.model.Cliente;
import com.humbertoff.github.clientes.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("clientes")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService service;

    private ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<Long> salvar(@RequestBody ClienteDTO clienteDTO) {
        var cliente = mapper.map(clienteDTO);
        service.salvarCliente(cliente);
        return ResponseEntity.ok(cliente.getCodigo());
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ClienteDTO> obterDados(@PathVariable("codigo") Long codigo) {
         Optional<Cliente> cliente = service.obterPorCodigo(codigo);

         ClienteDTO clienteDTO = mapper.dtoToObj(cliente);

        return clienteDTO != null? ResponseEntity.ok(clienteDTO) : ResponseEntity.notFound().build();
    }
}
