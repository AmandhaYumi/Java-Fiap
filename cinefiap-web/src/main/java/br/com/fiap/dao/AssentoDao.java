package br.com.fiap.dao;

import br.com.fiap.models.Assento;
import br.com.fiap.models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssentoDao {

    private Connection conexao;

    public void inserir(Assento assento) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try {
            String sql = "INSERT INTO TBL_ASSENTO (TX_FILEIRA, NR_POSICAO, ID_SALA)  VALUES (?, ?, ?)";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, assento.getFileira());
            comandoSQL.setInt(2, assento.getPosicao());
            comandoSQL.setLong(3, assento.getSala().getId());
            comandoSQL.executeUpdate();

            ResultSet rs = comandoSQL.getGeneratedKeys();
            if (rs.next()) {
                assento.setId(rs.getLong(1));
            }
            comandoSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Assento consultarPorId(Long id) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Assento assento = null;
        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_ASSENTO WHERE ID_ASSENTO = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                assento = new Assento();
                assento.setId(rs.getLong("ID_ASSENTO"));
                assento.setFileira(rs.getString("TX_FILEIRA"));
                assento.setPosicao(rs.getInt("NR_POSICAO"));
                
                SalaDao salaDao = new SalaDao();
                Sala sala = salaDao.buscarPorId(rs.getLong("ID_SALA"));
                assento.setSala(sala);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assento;
    }

    public List<Assento> listar() {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        List<Assento> assentos = new ArrayList<>();
        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_ASSENTO ORDER BY TX_FILEIRA");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Assento assento = new Assento();
                assento.setId(rs.getLong("ID_ASSENTO"));
                assento.setFileira(rs.getString("TX_FILEIRA"));
                assento.setPosicao(rs.getInt("NR_POSICAO"));

                SalaDao salaDao = new SalaDao();
                Sala sala = salaDao.buscarPorId(rs.getLong("ID_SALA"));
                assento.setSala(sala);
                
                assentos.add(assento);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assentos;
    }
}
