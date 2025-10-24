package br.com.fiap.service;

import br.com.fiap.dao.PedidoDao;
import br.com.fiap.dto.PedidoRequestDto;
import br.com.fiap.dto.PedidoResponseDto;
import br.com.fiap.enums.StatusPedidoEnum;
import br.com.fiap.models.Ingresso;
import br.com.fiap.models.Pedido;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoService {

    private PedidoDao pedidoDao = new PedidoDao();

    public PedidoResponseDto criarPedido(PedidoRequestDto pedidoRequestDto) {
        List<Ingresso> ingressos = pedidoRequestDto.getIngressoIds().stream()
                .map(Ingresso::new)
                .collect(Collectors.toList());

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedidoEnum.PENDENTE); // Vamos definir o estado inicial de um pedido como pendente
        pedido.setIngressos(ingressos);

        try {
            pedidoDao.inserir(pedido);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar pedido no banco de dados", e);
        }

        List<Long> ingressoIds = pedido.getIngressos().stream()
                .map(Ingresso::getId)
                .collect(Collectors.toList());

        return new PedidoResponseDto(pedido.getId(), pedido.getDataCriacao(), pedido.getStatus(), ingressoIds);
    }

    public List<PedidoResponseDto> listarPedidos() {
        return pedidoDao.listar().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public PedidoResponseDto buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoDao.buscarPorId(id);
        if (pedido == null) {
            return null; // Ou lançar uma exceção de "Não Encontrado"
        }
        return toResponseDto(pedido);
    }

    public List<PedidoResponseDto> listarPedidosComJoin() {
        return pedidoDao.listarComJoin().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private PedidoResponseDto toResponseDto(Pedido pedido) {
        List<Long> ingressoIds = pedido.getIngressos().stream()
                .map(Ingresso::getId)
                .collect(Collectors.toList());
        return new PedidoResponseDto(pedido.getId(), pedido.getDataCriacao(), pedido.getStatus(), ingressoIds);
    }
}
