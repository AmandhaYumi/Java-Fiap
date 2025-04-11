package br.com.fiap.models;

import br.com.fiap.enums.SituacaoEnum;

import java.time.LocalDate;
import java.util.Random;

public class ContaComum {
    protected Long nroConta;
    protected LocalDate dtAbertura;
    protected LocalDate dtEncerramento;
    protected SituacaoEnum situacao;
    protected int senha;
    protected double saldo;
    protected Cliente cliente;


    public Long abrirConta(int senha) {
        this.senha = senha;
        Random random = new Random();
        this.nroConta = random.nextLong(1000);
        this.senha = senha;
        this.situacao = SituacaoEnum.ATIVA;
        this.dtAbertura = LocalDate.now();
        this.saldo = 100;
        return nroConta;
    }

    public boolean validarSenha(int senha) {
        if (this.senha == senha)
            return true;
        else
            return false;
    }

    public String sacarValor(double valor) {
        if (valor <= saldo) {
            saldo -= valor;

            return "Saque efetuado com sucesso";
        }
        return "Saque não efetuado, saldo insuficiente";

    }

    public void depositarValor(double valor, long nroConta) {
        if (this.nroConta == nroConta) {
            saldo += valor;
        }
    }

    public String encerrarConta(long nroConta) {
        if (this.nroConta == nroConta) {
            this.situacao = SituacaoEnum.INATIVA;
            this.saldo = 0;

            return "Conta encerrada";
        }
        return "Conta inválida";
    }
}


