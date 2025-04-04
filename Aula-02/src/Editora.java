package br.com.fiap.models;

public class Editora {
    private String nome; /*Só a classe que criou tem acesso*/
    public String site;
    public String telefone;

    public Editora(String nome){
        this.nome = nome;
    }
    public Editora(){}

    public void exibirEditora(){
        System.out.println("br.com.fiap.models.Editora " + nome);
        System.out.println("Site: " + site);
        System.out.println("Telefone: " + telefone);
    }
}