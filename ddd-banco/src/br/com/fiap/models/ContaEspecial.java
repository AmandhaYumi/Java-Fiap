package br.com.fiap.models;

public class ContaEspecial extends ContaComum{
    private double limiteConta;

    public double getLimiteConta(double limiteConta){
        return limiteConta;
    }
    public void setLimiteConta(){
        this.limiteConta = limiteConta;
    }

    //Métodos
    public long abrirConta(int senha, double limite) {
        super.abrirConta(senha);
        this.limiteConta = limite;

        return super.nroConta;
    }
    public String sacarValor(double valor){
        return "";
    }
}
