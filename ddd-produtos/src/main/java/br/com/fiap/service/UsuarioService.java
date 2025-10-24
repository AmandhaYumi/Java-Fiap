package br.com.fiap.service;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.dto.UsuarioLoginDto;
import br.com.fiap.dto.UsuarioRequestDto;
import br.com.fiap.dto.UsuarioResponseDto;

import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    /**
     * Esse método é responsável pelo cadastro do usuário no banco de dados,
     * os dados serão recebidos por meio da classe controller.
     * @author Eliane Marion
     * @param usuarioDto
     * @throws SQLException
     */
    public void cadastrar(UsuarioRequestDto usuarioDto) throws SQLException {
        usuarioDao.cadastrar(usuarioDto.convertToUsuario(usuarioDto));
    }

    public UsuarioResponseDto buscar(String login) throws SQLException {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        return dto.convertToUsuarioResponseDto(usuarioDao.buscar(login));
    }
    /**
     * Esse método é responsável pela autenticação do usuário no banco de dados,
     * e retornará uma string informando se houve sucesso ou falha na autenticação.
     * @author Eliane Marion
     * @param dto
     * @return String
     * @throws SQLException
     */
    public String autenticarUsuario(UsuarioLoginDto dto) throws SQLException {
        return usuarioDao.autenticarUsuario(dto.convertToUsuario(dto));
    }


}
