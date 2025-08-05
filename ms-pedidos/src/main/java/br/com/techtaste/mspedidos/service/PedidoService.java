package br.com.techtaste.mspedidos.service;

import br.com.techtaste.mspedidos.dto.AutorizacaoDto;
import br.com.techtaste.mspedidos.dto.PedidoRequestDto;
import br.com.techtaste.mspedidos.dto.PedidoResponseDto;
import br.com.techtaste.mspedidos.model.Pedido;
import br.com.techtaste.mspedidos.model.Status;
import br.com.techtaste.mspedidos.repository.PedidoRepository;
import br.com.techtaste.mspedidos.utils.AutorizacaoPagamentoClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final AutorizacaoPagamentoClient client;

    public PedidoService(PedidoRepository repository, AutorizacaoPagamentoClient client) {
        this.repository = repository;
        this.client = client;
    }

    private Status obterStatusPagamento(String id) {
        AutorizacaoDto autorizacao = client.obterAutorizacao(id);
        if (autorizacao.status().equalsIgnoreCase("autorizado")) {
            return Status.PREPARANDO;
        }

        return Status.RECUSADO;
    }

    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto, Boolean comErro) {
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Status status = Status.AGUARDANDO_PAGAMENTO;
        pedido.setStatus(status);
        pedido.setData(LocalDate.now());
        pedido.calcularTotal();
        repository.save(pedido);
        if (comErro) {
            status = Status.ERRO_CONSULTA_PGTO;
        } else {
            status = obterStatusPagamento(pedido.getId().toString());
        }
        pedido.setStatus(status);
        repository.save(pedido);
        return new PedidoResponseDto(pedido.getId(), pedido.getStatus(),
                pedido.getCpf(), pedido.getItens(), pedido.getValorTotal(),
                pedido.getData());
    }

    public List<PedidoResponseDto> obterTodos() {
        return repository.findAll().stream()
                .map(pedido -> new PedidoResponseDto(pedido.getId(), pedido.getStatus(),
                        pedido.getCpf(), pedido.getItens(), pedido.getValorTotal(),
                        pedido.getData()))
                .collect(Collectors.toList());
    }

}
