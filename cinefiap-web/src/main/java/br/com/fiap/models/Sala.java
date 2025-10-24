package br.com.fiap.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sala {
    private Long id;
    private String nome;
    private Double preco;

    public Sala() {
    }

    public Sala(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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
