package com.humbertoff.github.pedidos.controller.dto;

import com.humbertoff.github.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento tipoPagamento){
}
