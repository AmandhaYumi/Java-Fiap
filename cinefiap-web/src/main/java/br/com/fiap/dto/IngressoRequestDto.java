package br.com.fiap.dto;

import br.com.fiap.enums.TipoIngressoEnum;

public class IngressoRequestDto {
    private TipoIngressoEnum tipo;
    private double preco;
    private Long sessaoId;
    private Long assentoId;

    // Getters e Setters

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

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Long getAssentoId() {
        return assentoId;
    }

    public void setAssentoId(Long assentoId) {
        this.assentoId = assentoId;
    }
}
