package br.com.fiap.models;

import br.com.fiap.dto.FilmeDto;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.time.LocalTime;
@XmlRootElement
public class Sessao {
    private Long id;
    private LocalDate data;
    private LocalTime horario;
    private double preco;
    private Sala sala;
    private Filme filme;

    public Sessao() {
    }

    public Sessao(Long id, LocalDate data, LocalTime hoario, double preco, Sala sala, Filme filme) {
        this.id = id;
        this.data = data;
        this.horario = hoario;
        this.preco = preco;
        this.sala = sala;
        this.filme = filme;
    }

    public Sessao(LocalDate data, LocalTime horario, double preco, Sala sala, Filme filme) {
        this.data = data;
        this.horario = horario;
        this.preco = preco;
        this.sala = sala;
        this.filme = filme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime hoario) {
        this.horario = hoario;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
