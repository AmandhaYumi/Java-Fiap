package br.com.fiap.models;

import br.com.fiap.enums.StatusPedidoEnum;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
public class Pedido {
    private Long id;
    private LocalDateTime dataCriacao;
    private StatusPedidoEnum status;
    private List<Ingresso> ingressos;

    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime dataCriacao, StatusPedidoEnum status, List<Ingresso> ingressos) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.ingressos = ingressos;
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

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}