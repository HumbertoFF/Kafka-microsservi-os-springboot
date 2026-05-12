package com.humbertoff.github.pedidos.repository;

import com.humbertoff.github.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
