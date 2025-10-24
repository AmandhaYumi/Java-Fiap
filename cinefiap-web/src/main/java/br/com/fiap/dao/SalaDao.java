package br.com.fiap.dao;

import br.com.fiap.models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDao {
    private Connection conexao;

    public void cadastrarSala(Sala sala){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try{
            String sql = "insert into tbl_sala (id_sala, tx_nome, nr_preco) values(?,?,?)";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, sala.getId());
            comandoSQL.setString(2, sala.getNome());
            comandoSQL.setDouble(3, sala.getPreco());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Sala buscarPorId(Long id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Sala sala = new Sala();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_SALA " +
                    "WHERE ID_SALA = ?");
            ps.setLong(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                sala.setId(rs.getLong(1));
                sala.setNome(rs.getString(2));
                sala.setPreco(rs.getDouble(3));
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sala;
    }

    public void alterarSala(Sala sala){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try{
            String sql = "update tbl_sala set tx_nome = ?, nr_preco = ? where id_sala = ?";
            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setString(1, sala.getNome());
            comandoSQL.setDouble(2, sala.getPreco());
            comandoSQL.setLong(3, sala.getId());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void excluirSala(Long id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try{
            String sql = "delete from tbl_sala where id_sala = ?";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, id);
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Sala> listar(){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        List<Sala> salas = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * from TBL_SALA order by tx_nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Sala sala = new Sala();
                sala.setId(rs.getLong(1));
                sala.setNome(rs.getString(2));
                sala.setPreco(rs.getDouble(3));
                salas.add(sala);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salas;
    }
}
