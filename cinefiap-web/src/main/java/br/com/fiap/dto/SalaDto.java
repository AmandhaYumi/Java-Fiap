package br.com.fiap.dto;

import br.com.fiap.models.Sala;

public class SalaDto {
    private Long id;
    private String nome;
    private Double preco;

    public SalaDto(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public SalaDto() {

    }

    public Sala convertToSala(SalaDto dto){
        return new Sala(dto.id, dto.nome, dto.preco);
    }

    public SalaDto convertToSalaDto(Sala sala){
        return new SalaDto(sala.getId(), sala.getNome(), sala.getPreco());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
