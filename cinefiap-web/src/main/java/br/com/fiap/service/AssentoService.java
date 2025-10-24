package br.com.fiap.service;

import br.com.fiap.dao.AssentoDao;
import br.com.fiap.dao.SalaDao;
import br.com.fiap.dto.AssentoRequestDto;
import br.com.fiap.dto.AssentoResponseDto;
import br.com.fiap.dto.SalaDto;
import br.com.fiap.models.Assento;
import br.com.fiap.models.Sala;

import java.util.List;
import java.util.stream.Collectors;

public class AssentoService {

    private AssentoDao assentoDao = new AssentoDao();
    private SalaDao salaDao = new SalaDao();

    public AssentoResponseDto inserir(AssentoRequestDto requestDto) {
        Assento assento = toEntity(requestDto);
        assentoDao.inserir(assento);
        return toResponseDto(assento);
    }

    public AssentoResponseDto consultarPorId(Long id) {
        Assento assento = assentoDao.consultarPorId(id);
        return toResponseDto(assento);
    }

    public List<AssentoResponseDto> listar() {
        return assentoDao.listar().stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    private Assento toEntity(AssentoRequestDto requestDto) {
        Sala sala = salaDao.buscarPorId(requestDto.getSalaId());
        if (sala == null) {
            throw new RuntimeException("Sala não encontrada");
        }
        return new Assento(null, requestDto.getFileira(), requestDto.getPosicao(), sala);
    }

    private AssentoResponseDto toResponseDto(Assento assento) {
        if (assento == null) {
            return null;
        }
        SalaDto salaDto = new SalaDto(assento.getSala().getId(), assento.getSala().getNome(), assento.getSala().getPreco());
        return new AssentoResponseDto(assento.getId(), assento.getFileira(), assento.getPosicao(), salaDto);
    }
}
