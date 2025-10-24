package br.com.fiap.service;

import br.com.fiap.dao.SalaDao;
import br.com.fiap.dto.SalaRequestDto;
import br.com.fiap.dto.SalaResponseDto;
import br.com.fiap.models.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaService {
    private SalaDao dao = new SalaDao();

    public void cadastrar(SalaRequestDto dto){
        Sala sala = new Sala(dto.getId(), dto.getNome(), dto.getPreco());
        dao.cadastrarSala(sala);
    }

    public SalaResponseDto buscarPorId(Long id){
        Sala sala = dao.buscarPorId(id);
        return new SalaResponseDto(sala.getId(), sala.getNome(), sala.getPreco());
    }

    public void alterar(SalaRequestDto dto){
        Sala sala = new Sala(dto.getId(), dto.getNome(), dto.getPreco());
        dao.alterarSala(sala);
    }

    public void excluir(Long id){
        dao.excluirSala(id);
    }

    public List<SalaResponseDto> listar(){
        List<Sala> salas = dao.listar();
        List<SalaResponseDto> dtos = new ArrayList<>();
        for (Sala sala : salas) {
            dtos.add(new SalaResponseDto(sala.getId(), sala.getNome(), sala.getPreco()));
        }
        return dtos;
    }
}
