package br.com.techtaste.mspedidos.dto;

import br.com.techtaste.mspedidos.model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(String cpf,
                               List<ItemPedido> itens) {
}
