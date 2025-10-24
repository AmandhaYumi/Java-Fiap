package br.com.fiap.dto;

import br.com.fiap.enums.StatusPedidoEnum;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDto {
    private Long id;
    private LocalDateTime dataCriacao;
    private StatusPedidoEnum status;
    private List<Long> ingressoIds;

    public PedidoResponseDto() {
    }

    public PedidoResponseDto(Long id, LocalDateTime dataCriacao, StatusPedidoEnum status, List<Long> ingressoIds) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.ingressoIds = ingressoIds;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public List<Long> getIngressoIds() {
        return ingressoIds;
    }

    public void setIngressoIds(List<Long> ingressoIds) {
        this.ingressoIds = ingressoIds;
    }
}
