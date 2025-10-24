package br.com.fiap.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessaoRequestDto {

    private LocalDate data;
    private LocalTime horario;
    private double preco;
    private Long idSala;
    private Long idFilme;

    public SessaoRequestDto() {
    }

    public SessaoRequestDto(LocalDate data, LocalTime horario, double preco, Long idSala, Long idFilme) {

        this.data = data;
        this.horario = horario;
        this.preco = preco;
        this.idSala = idSala;
        this.idFilme = idFilme;
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

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }
}
