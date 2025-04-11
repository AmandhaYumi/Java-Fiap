package br.com.fiap.teste;

import models.ContaEspecial;
import models.Cliente;
import models.ContaComum;

public class testeCliente {
    public static void main(String[] args) {
        ContaEspecial ce = new ContaEspecial();
        ce.abrirConta(123,1000);
        ce.sacarValor(1000);
        System.out.println("Saldo "+ce.getSaldo());
        System.out.println("Limite utilizado " + ce.getLimiteConta(1000));
    }
}