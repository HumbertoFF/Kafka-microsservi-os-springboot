package com.humbertoff.github.pedidos.controller.mappers;

import com.humbertoff.github.pedidos.controller.dto.ItemPedidoDTO;
import com.humbertoff.github.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedido map(ItemPedidoDTO dto);
}
