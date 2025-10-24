package br.com.fiap.models;

import br.com.fiap.enums.TipoIngressoEnum;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingresso {
    private Long id;
    private TipoIngressoEnum tipo;
    private double preco;
    private Sessao sessao;
    private Assento assento;

    public Ingresso() {
    }

    public Ingresso(Long id, TipoIngressoEnum tipo, double preco, Sessao sessao, Assento assento) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.sessao = sessao;
        this.assento = assento;
    }

    public Ingresso(Long id) {
        this.id = id;
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

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }
}
