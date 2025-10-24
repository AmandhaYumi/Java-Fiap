package br.com.fiap.service;

import br.com.fiap.dao.FilmeDao;
import br.com.fiap.dao.SalaDao;
import br.com.fiap.dao.SessaoDao;
import br.com.fiap.dto.FilmeDto;
import br.com.fiap.dto.SalaDto;
import br.com.fiap.dto.SessaoRequestDto;
import br.com.fiap.dto.SessaoResponseDto;
import br.com.fiap.models.Filme;
import br.com.fiap.models.Sala;
import br.com.fiap.models.Sessao;

import java.util.ArrayList;
import java.util.List;

public class SessaoService {
    private SessaoDao sessaoDao = new SessaoDao();
    private FilmeDao filmeDao = new FilmeDao();
    private SalaDao salaDao = new SalaDao();

    public void cadastrar(SessaoRequestDto dto) {
        Filme filme = filmeDao.buscarPorId(dto.getIdFilme());
        Sala sala = salaDao.buscarPorId(dto.getIdSala());
        Sessao sessao = new Sessao(dto.getData(), dto.getHorario(), dto.getPreco(), sala, filme);
        sessaoDao.cadastrarSessao(sessao);
    }

    public SessaoResponseDto buscarPorId(Long id) {
        Sessao sessao = sessaoDao.buscarPorId(id);
        if (sessao == null) {
            return null;
        }
        FilmeDto filmeDto = new FilmeDto(sessao.getFilme().getId(), sessao.getFilme().getNome(), sessao.getFilme().getDescricao(), sessao.getFilme().getDuracao(), sessao.getFilme().getGenero(), sessao.getFilme().getClassificacao());
        SalaDto salaDto = new SalaDto(sessao.getSala().getId(), sessao.getSala().getNome(), sessao.getSala().getPreco());
        return new SessaoResponseDto(sessao.getId(), sessao.getData(), sessao.getHorario(), sessao.getPreco(), salaDto, filmeDto);
    }

    public void alterar(SessaoResponseDto dto) {
        Filme filme = filmeDao.buscarPorId(dto.getId());
        Sala sala = salaDao.buscarPorId(dto.getSala().getId());
        Sessao sessao = new Sessao(dto.getData(), dto.getHorario(), dto.getPreco(), sala, filme);
        sessaoDao.alterarSessao(sessao);
    }

    public void excluir(Long id) {
        sessaoDao.excluirSessao(id);
    }

    public List<SessaoResponseDto> listar() {
        List<Sessao> sessoes = sessaoDao.listar();
        List<SessaoResponseDto> dtos = new ArrayList<>();
        for (Sessao sessao : sessoes) {
            FilmeDto filmeDto = new FilmeDto(sessao.getFilme().getId(), sessao.getFilme().getNome(), sessao.getFilme().getDescricao(), sessao.getFilme().getDuracao(), sessao.getFilme().getGenero(), sessao.getFilme().getClassificacao());
            SalaDto salaDto = new SalaDto(sessao.getSala().getId(), sessao.getSala().getNome(), sessao.getSala().getPreco());
            dtos.add(new SessaoResponseDto(sessao.getId(), sessao.getData(), sessao.getHorario(), sessao.getPreco(), salaDto, filmeDto));
        }
        return dtos;
    }
}
