package br.com.fiap.dto;

public class AssentoResponseDto {
    private Long id;
    private String fileira;
    private Integer posicao;
    private SalaDto sala;

    public AssentoResponseDto() {
    }

    public AssentoResponseDto(Long id, String fileira, Integer posicao, SalaDto sala) {
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

    public SalaDto getSala() {
        return sala;
    }

    public void setSala(SalaDto sala) {
        this.sala = sala;
    }
}
