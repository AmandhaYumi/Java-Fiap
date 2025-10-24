package br.com.fiap.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessaoResponseDto {
    private Long id;

    private LocalDate data;

    private LocalTime horario;
    private double preco;
    private SalaDto sala;
    private FilmeDto filme;

    public SessaoResponseDto() {
    }

    public SessaoResponseDto(Long id, LocalDate data, LocalTime horario, double preco, SalaDto sala, FilmeDto filme) {
        this.id = id;
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

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public SalaDto getSala() {
        return sala;
    }

    public void setSala(SalaDto sala) {
        this.sala = sala;
    }

    public FilmeDto getFilme() {
        return filme;
    }

    public void setFilme(FilmeDto filme) {
        this.filme = filme;
    }
}
