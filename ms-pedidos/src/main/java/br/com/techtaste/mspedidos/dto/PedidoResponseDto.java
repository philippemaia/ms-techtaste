package br.com.techtaste.mspedidos.dto;

import br.com.techtaste.mspedidos.model.ItemPedido;
import br.com.techtaste.mspedidos.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(UUID id,
                                Status status,
                                String cpf,
                                List<ItemPedido> itens,
                                BigDecimal valorTotal,
                                LocalDate data) {
}
