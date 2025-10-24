package br.com.fiap.dao;

import br.com.fiap.enums.StatusPedidoEnum;
import br.com.fiap.models.Ingresso;
import br.com.fiap.models.Pedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    private Connection conexao;

    public void inserir(Pedido pedido) throws SQLException {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        String sql = "INSERT INTO TBL_PEDIDO (DT_CRIACAO, TP_STATUS) VALUES (?, ?)";

        try {
            /* Inicia transação fazendo com que várias operações sejam tratadas
               como uma única transação
            **/
            conexao.setAutoCommit(false);

            comandoSQL = conexao.prepareStatement(sql, new String[]{"ID_PEDIDO"});
            comandoSQL.setTimestamp(1, Timestamp.valueOf(pedido.getDataCriacao()));
            comandoSQL.setString(2, pedido.getStatus().name());
            comandoSQL.executeUpdate();
            //Após fazer a inserção no banco devolve a chave primária criada automaticamente
            ResultSet rs = comandoSQL.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getLong(1));
            }

            // Inserir na tabela de associação N:N (TBL_PEDIDO_INGRESSO)
            inserirPedidoIngressos(pedido.getId(), pedido.getIngressos());

            conexao.commit(); // Faz o commit, finaliza a transação, de fato grava tudo no banco

        } catch (SQLException e) {
            conexao.rollback(); // Em caso de erro será realizado um rollback, ou seja nada será gravado no banco
            throw e;
        } finally {
            if (comandoSQL != null) {
                comandoSQL.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
    }

    private void inserirPedidoIngressos(Long pedidoId, List<Ingresso> ingressos) throws SQLException {
        PreparedStatement comandoSQL = null;
        String sql = "INSERT INTO TBL_REL_PEDIDO_INGRESSO (ID_PEDIDO, ID_INGRESSO) VALUES (?, ?)";
        try {
            comandoSQL = conexao.prepareStatement(sql);
            for (Ingresso ingresso : ingressos) {
                comandoSQL.setLong(1, pedidoId);
                comandoSQL.setLong(2, ingresso.getId());
                comandoSQL.addBatch();
            }
            comandoSQL.executeBatch();
        } finally {
            if (comandoSQL != null) {
                comandoSQL.close();
            }
        }
    }

    public Pedido buscarPorId(Long id) {
        Pedido pedido = null;
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TBL_PEDIDO WHERE ID_PEDIDO = ?")) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido();
                    pedido.setId(rs.getLong("ID_PEDIDO"));
                    pedido.setDataCriacao(rs.getTimestamp("DT_CRIACAO").toLocalDateTime());
                    pedido.setStatus(StatusPedidoEnum.valueOf(rs.getString("TP_STATUS")));
                    pedido.setIngressos(buscarIngressosPorPedidoId(pedido.getId(), conexao));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TBL_PEDIDO ORDER BY DT_CRIACAO DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("ID_PEDIDO"));
                pedido.setDataCriacao(rs.getTimestamp("DT_CRIACAO").toLocalDateTime());
                pedido.setStatus(StatusPedidoEnum.valueOf(rs.getString("TP_STATUS")));
                // Para evitar múltiplas queries (N+1)
                pedido.setIngressos(buscarIngressosPorPedidoId(pedido.getId(), conexao));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    public List<Pedido> listarComJoin() {
        String sql = "SELECT p.ID_PEDIDO, p.DT_CRIACAO, p.TP_STATUS, rpi.ID_INGRESSO " +
                     "FROM TBL_PEDIDO p " +
                     "LEFT JOIN TBL_REL_PEDIDO_INGRESSO rpi ON p.ID_PEDIDO = rpi.ID_PEDIDO " +
                     "ORDER BY p.ID_PEDIDO";

        java.util.Map<Long, Pedido> pedidoMap = new java.util.LinkedHashMap<>();

        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long pedidoId = rs.getLong("ID_PEDIDO");
                Pedido pedido = pedidoMap.get(pedidoId);

                if (pedido == null) {
                    pedido = new Pedido();
                    pedido.setId(pedidoId);
                    pedido.setDataCriacao(rs.getTimestamp("DT_CRIACAO").toLocalDateTime());
                    pedido.setStatus(StatusPedidoEnum.valueOf(rs.getString("TP_STATUS")));
                    pedido.setIngressos(new ArrayList<>());
                    pedidoMap.put(pedidoId, pedido);
                }

                long ingressoId = rs.getLong("ID_INGRESSO");
                if (ingressoId != 0) { // Ocorre se o LEFT JOIN não encontrar correspondência
                    pedido.getIngressos().add(new Ingresso(ingressoId));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos com join", e);
        }

        return new ArrayList<>(pedidoMap.values());
    }

    private List<Ingresso> buscarIngressosPorPedidoId(Long pedidoId, Connection conexao) throws SQLException {
        List<Ingresso> ingressos = new ArrayList<>();
        String sql = "SELECT ID_INGRESSO FROM TBL_REL_PEDIDO_INGRESSO WHERE ID_PEDIDO = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setLong(1, pedidoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ingresso ingresso = new Ingresso();
                    ingresso.setId(rs.getLong("ID_INGRESSO"));
                    ingressos.add(ingresso);
                }
            }
        }
        return ingressos;
    }
}
