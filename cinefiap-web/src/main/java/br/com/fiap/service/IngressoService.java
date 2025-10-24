package br.com.fiap.service;

import br.com.fiap.dao.AssentoDao;
import br.com.fiap.dao.IngressoDao;
import br.com.fiap.dao.SessaoDao;
import br.com.fiap.dto.*;
import br.com.fiap.models.Assento;
import br.com.fiap.models.Ingresso;
import br.com.fiap.models.Sessao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class IngressoService {

    private IngressoDao ingressoDao = new IngressoDao();
    private SessaoDao sessaoDao = new SessaoDao();
    private AssentoDao assentoDao = new AssentoDao();

    public IngressoResponseDto criarIngresso(IngressoRequestDto requestDto) {
        Ingresso ingresso = toEntity(requestDto);
        try {
            ingressoDao.inserir(ingresso);
            return toResponseDto(ingresso);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar ingresso", e);
        }
    }

    public List<IngressoResponseDto> listarIngressos() {
        return ingressoDao.listar().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public IngressoResponseDto buscarIngressoPorId(Long id) {
        Ingresso ingresso = ingressoDao.buscarPorId(id);
        if (ingresso == null) {
            throw new RuntimeException("Ingresso não encontrado");
        }
        return toResponseDto(ingresso);
    }

    public IngressoResponseDto atualizarIngresso(Long id, IngressoRequestDto requestDto) {
        Ingresso ingresso = ingressoDao.buscarPorId(id);
        if (ingresso == null) {
            throw new RuntimeException("Ingresso não encontrado para atualização");
        }
        updateEntityFromDto(ingresso, requestDto);
        try {
            ingressoDao.atualizar(ingresso);
            return toResponseDto(ingresso);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ingresso", e);
        }
    }

    public void deletarIngresso(Long id) {
        if (ingressoDao.buscarPorId(id) == null) {
            throw new RuntimeException("Ingresso não encontrado para exclusão");
        }
        try {
            ingressoDao.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar ingresso", e);
        }
    }

    private Ingresso toEntity(IngressoRequestDto dto) {
        Sessao sessao = sessaoDao.buscarPorId(dto.getSessaoId());
        if (sessao == null) throw new RuntimeException("Sessão não encontrada");

        Assento assento = assentoDao.consultarPorId(dto.getAssentoId());
        if (assento == null) throw new RuntimeException("Assento não encontrado");

        Ingresso ingresso = new Ingresso();
        ingresso.setTipo(dto.getTipo());
        ingresso.setPreco(dto.getPreco());
        ingresso.setSessao(sessao);
        ingresso.setAssento(assento);
        return ingresso;
    }

    private void updateEntityFromDto(Ingresso ingresso, IngressoRequestDto dto) {
        Sessao sessao = sessaoDao.buscarPorId(dto.getSessaoId());
        if (sessao == null) throw new RuntimeException("Sessão não encontrada");

        Assento assento = assentoDao.consultarPorId(dto.getAssentoId());
        if (assento == null) throw new RuntimeException("Assento não encontrado");

        ingresso.setTipo(dto.getTipo());
        ingresso.setPreco(dto.getPreco());
        ingresso.setSessao(sessao);
        ingresso.setAssento(assento);
    }

    private IngressoResponseDto toResponseDto(Ingresso ingresso) {
        // Supondo que existam mappers ou construtores para criar os DTOs aninhados
        SessaoDto sessaoDto = new SessaoDto(ingresso.getSessao().getId(), ingresso.getSessao().getData(), ingresso.getSessao().getHorario(), ingresso.getSessao().getPreco(), null, null); // Simplificado
        AssentoResponseDto assentoDto = new AssentoResponseDto(ingresso.getAssento().getId(), ingresso.getAssento().getFileira(), ingresso.getAssento().getPosicao(), null); // Simplificado

        return new IngressoResponseDto(ingresso.getId(), ingresso.getTipo(), ingresso.getPreco(), sessaoDto, assentoDto);
    }
}
