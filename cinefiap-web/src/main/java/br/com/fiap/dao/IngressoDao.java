package br.com.fiap.dao;

import br.com.fiap.enums.TipoIngressoEnum;
import br.com.fiap.models.Assento;
import br.com.fiap.models.Ingresso;
import br.com.fiap.models.Sessao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngressoDao {

    private SessaoDao sessaoDao;
    private AssentoDao assentoDao;

    public IngressoDao() {
        this.sessaoDao = new SessaoDao();
        this.assentoDao = new AssentoDao();
    }

    public void inserir(Ingresso ingresso) throws SQLException {
        String sql = "INSERT INTO TBL_INGRESSO (TP_INGRESSO, NR_PRECO, ID_SESSAO, ID_ASSENTO) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql, new String[]{"ID_INGRESSO"})) {

            ps.setString(1, ingresso.getTipo().name());
            ps.setDouble(2, ingresso.getPreco());
            ps.setLong(3, ingresso.getSessao().getId());
            ps.setLong(4, ingresso.getAssento().getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ingresso.setId(rs.getLong(1));
                }
            }
        }
    }

    public Ingresso buscarPorId(Long id) {
        String sql = "SELECT * FROM TBL_INGRESSO WHERE ID_INGRESSO = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return toEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Ingresso> listar() {
        List<Ingresso> ingressos = new ArrayList<>();
        String sql = "SELECT * FROM TBL_INGRESSO ORDER BY ID_INGRESSO";
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ingressos.add(toEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingressos;
    }

    public void atualizar(Ingresso ingresso) throws SQLException {
        String sql = "UPDATE TBL_INGRESSO SET TP_INGRESSO = ?, NR_PRECO = ?, ID_SESSAO = ?, ID_ASSENTO = ? WHERE ID_INGRESSO = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, ingresso.getTipo().name());
            ps.setDouble(2, ingresso.getPreco());
            ps.setLong(3, ingresso.getSessao().getId());
            ps.setLong(4, ingresso.getAssento().getId());
            ps.setLong(5, ingresso.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM TBL_INGRESSO WHERE ID_INGRESSO = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private Ingresso toEntity(ResultSet rs) throws SQLException {
        Ingresso ingresso = new Ingresso();
        ingresso.setId(rs.getLong("ID_INGRESSO"));
        ingresso.setTipo(TipoIngressoEnum.valueOf(rs.getString("TP_INGRESSO")));
        ingresso.setPreco(rs.getDouble("NR_PRECO"));

        Sessao sessao = sessaoDao.buscarPorId(rs.getLong("ID_SESSAO"));
        ingresso.setSessao(sessao);

        Assento assento = assentoDao.consultarPorId(rs.getLong("ID_ASSENTO"));
        ingresso.setAssento(assento);

        return ingresso;
    }
}
