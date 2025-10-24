package br.com.fiap.dto;

import java.util.List;

public class PedidoRequestDto {
    private List<Long> ingressoIds;

    public PedidoRequestDto() {
    }

    public PedidoRequestDto(List<Long> ingressoIds) {
        this.ingressoIds = ingressoIds;
    }

    public List<Long> getIngressoIds() {
        return ingressoIds;
    }

    public void setIngressoIds(List<Long> ingressoIds) {
        this.ingressoIds = ingressoIds;
    }
}
