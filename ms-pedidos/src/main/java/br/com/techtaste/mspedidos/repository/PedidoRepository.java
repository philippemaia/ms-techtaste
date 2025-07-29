package br.com.techtaste.mspedidos.repository;

import br.com.techtaste.mspedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
