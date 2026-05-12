package com.humbertoff.github.pedidos.validator;

import com.humbertoff.github.pedidos.client.ProdutosClient;
import com.humbertoff.github.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoValidator {

    private final ProdutosClient produtosClient;

    public void validar(Pedido pedido) {
        // Implement validation logic for new Pedido
    }
}
