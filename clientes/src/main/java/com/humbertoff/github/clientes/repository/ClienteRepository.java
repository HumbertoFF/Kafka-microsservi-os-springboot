package com.humbertoff.github.clientes.repository;

import com.humbertoff.github.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
