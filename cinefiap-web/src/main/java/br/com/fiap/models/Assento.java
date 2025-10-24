package br.com.fiap.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Assento {
    private Long id;
    private String fileira;
    private Integer posicao;
    private Sala sala;

    public Assento() {
    }

    public Assento(String fileira, Integer posicao, Sala sala) {
        this.fileira = fileira;
        this.posicao = posicao;
        this.sala = sala;
    }

    public Assento(Long id, String fileira, Integer posicao, Sala sala) {
        this.id = id;
        this.fileira = fileira;
        this.posicao = posicao;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileira() {
        return fileira;
    }

    public void setFileira(String fileira) {
        this.fileira = fileira;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}