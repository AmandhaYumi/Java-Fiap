package br.com.fiap.dto;

import br.com.fiap.enums.TipoIngressoEnum;

public class IngressoResponseDto {
    private Long id;
    private TipoIngressoEnum tipo;
    private double preco;
    private SessaoDto sessao;
    private AssentoResponseDto assento;

    public IngressoResponseDto(Long id, TipoIngressoEnum tipo, double preco, SessaoDto sessao, AssentoResponseDto assento) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.sessao = sessao;
        this.assento = assento;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIngressoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngressoEnum tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public SessaoDto getSessao() {
        return sessao;
    }

    public void setSessao(SessaoDto sessao) {
        this.sessao = sessao;
    }

    public AssentoResponseDto getAssento() {
        return assento;
    }

    public void setAssento(AssentoResponseDto assento) {
        this.assento = assento;
    }
}
