package br.com.fiap.dao;

import br.com.fiap.models.Filme;
import br.com.fiap.models.Sala;
import br.com.fiap.models.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SessaoDao {
    private Connection conexao;
    private FilmeDao filmeDao;
    private SalaDao salaDao;

    public SessaoDao() {
        this.filmeDao = new FilmeDao();
        this.salaDao = new SalaDao();
    }

    public void cadastrarSessao(Sessao sessao) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try {
            String sql = "INSERT INTO TBL_SESSAO ( DT_DATA, HR_HORARIO, NR_PRECO, ID_SALA, ID_FILME) VALUES (?, ?, ?, ?, ?, ?)";
            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setDate(1, java.sql.Date.valueOf(sessao.getData()));
            comandoSQL.setTime(2, java.sql.Time.valueOf(sessao.getHorario()));
            comandoSQL.setDouble(3, sessao.getPreco());
            comandoSQL.setLong(4, sessao.getSala().getId());
            comandoSQL.setLong(5, sessao.getFilme().getId());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sessao buscarPorId(Long id) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Sessao sessao = null;
        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_SESSAO WHERE ID_SESSAO = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sessao = new Sessao();
                sessao.setId(rs.getLong("ID_SESSAO"));
                sessao.setData(rs.getDate("DT_DATA").toLocalDate());
                sessao.setHorario(rs.getTime("HR_HORARIO").toLocalTime());
                sessao.setPreco(rs.getDouble("NR_PRECO"));

                Long idSala = rs.getLong("ID_SALA");
                Sala sala = salaDao.buscarPorId(idSala);
                sessao.setSala(sala);

                Long idFilme = rs.getLong("ID_FILME");
                Filme filme = filmeDao.buscarPorId(idFilme);
                sessao.setFilme(filme);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessao;
    }

    public void alterarSessao(Sessao sessao) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try {
            String sql = "UPDATE TBL_SESSAO SET DT_DATA = ?, HR_HORARIO = ?, NR_PRECO = ?, ID_SALA = ?, ID_FILME = ? WHERE ID_SESSAO = ?";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setDate(1, java.sql.Date.valueOf(sessao.getData()));
            comandoSQL.setTime(2, java.sql.Time.valueOf(sessao.getHorario()));
            comandoSQL.setDouble(3, sessao.getPreco());
            comandoSQL.setLong(4, sessao.getSala().getId());
            comandoSQL.setLong(5, sessao.getFilme().getId());
            comandoSQL.setLong(6, sessao.getId());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirSessao(Long id) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try {
            String sql = "DELETE FROM TBL_SESSAO WHERE ID_SESSAO = ?";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, id);
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sessao> listar() {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        List<Sessao> sessoes = new ArrayList<>();
        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_SESSAO ORDER BY DT_DATA");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setId(rs.getLong("ID_SESSAO"));
                sessao.setData(rs.getDate("DT_DATA").toLocalDate());
                sessao.setHorario(rs.getTime("HR_HORARIO").toLocalTime());
                sessao.setPreco(rs.getDouble("NR_PRECO"));

                Long idSala = rs.getLong("ID_SALA");
                Sala sala = salaDao.buscarPorId(idSala);
                sessao.setSala(sala);

                Long idFilme = rs.getLong("ID_FILME");
                Filme filme = filmeDao.buscarPorId(idFilme);
                sessao.setFilme(filme);

                sessoes.add(sessao);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessoes;
    }
}
