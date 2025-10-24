package br.com.fiap.service;

import br.com.fiap.dao.FilmeDao;
import br.com.fiap.dto.FilmeDto;
import br.com.fiap.models.Filme;

public class FilmeService {
    private FilmeDao dao = new FilmeDao();

    public void cadastrar(FilmeDto dto){
        dao.cadastrarFilme(dto.convertToFilme(dto));
    }

    public FilmeDto buscarPorId(Long id){
        FilmeDto dto = new FilmeDto();
        return dto.convertToFilmeDto(dao.buscarPorId(id));
    }
}
