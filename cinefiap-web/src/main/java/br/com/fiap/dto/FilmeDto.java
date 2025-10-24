package br.com.fiap.dto;

import br.com.fiap.enums.ClassificacaoIndicativaEnum;
import br.com.fiap.enums.GeneroEnum;
import br.com.fiap.models.Filme;

public class FilmeDto {
    private Long id;
    private String nome;
    private int duracao;
    private GeneroEnum genero;
    private ClassificacaoIndicativaEnum classificacao;
    private Integer ano;
    private String capa;
    private String diretor;
    private String elenco;
    private String descricao;
    private Double avaliacao;

    public FilmeDto(Long id, String nome, int duracao, GeneroEnum genero, ClassificacaoIndicativaEnum classificacao, Integer ano, String capa, String diretor, String elenco, String descricao, Double avaliacao) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.genero = genero;
        this.classificacao = classificacao;
        this.ano = ano;
        this.capa = capa;
        this.diretor = diretor;
        this.elenco = elenco;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
    }


public FilmeDto(Long id, String nome, String descricao, int duracao, GeneroEnum genero, ClassificacaoIndicativaEnum classificacao) {
    this.id = id;
    this.nome = nome;
    this.duracao = duracao;
    this.genero = genero;
    this.classificacao = classificacao;
    this.descricao = descricao;

}
    public FilmeDto() {

    }

    public Filme convertToFilme(FilmeDto dto){
        return new Filme(dto.id, dto.nome, dto.duracao, dto.genero, dto.classificacao, dto.ano, dto.capa,
                dto.diretor, dto.elenco, dto.descricao, dto.avaliacao);
    }

    public FilmeDto convertToFilmeDto(Filme filme){
        return new FilmeDto(filme.getId(), filme.getNome(), filme.getDuracao(),
                filme.getGenero(), filme.getClassificacao(), filme.getAno(), filme.getCapa(),
                filme.getDiretor(), filme.getElenco(), filme.getDescricao(), filme.getAvaliacao());
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

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public ClassificacaoIndicativaEnum getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoIndicativaEnum classificacao) {
        this.classificacao = classificacao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
