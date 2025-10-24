package br.com.fiap.dto;

public class AssentoRequestDto {
    private String fileira;
    private Integer posicao;
    private Long salaId;

    public AssentoRequestDto() {
    }

    public AssentoRequestDto(String fileira, Integer posicao, Long salaId) {
        this.fileira = fileira;
        this.posicao = posicao;
        this.salaId = salaId;
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

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }
}