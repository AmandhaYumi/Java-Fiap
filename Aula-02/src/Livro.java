package br.com.fiap.models;

import br.com.fiap.enums.TipoCapaEnum;

public class Livro {
    public String titulo;
    public String autor;
    public double preco;
    public Editora editora;
    public int paginas;
    public String resumo;
    public TipoCapaEnum tipoCapa; //COMUM, DURA


    public Livro(){
        tipoCapa = TipoCapaEnum.COMUM;
    }
    public Livro(Editora editora)
    {
        this.editora = editora;
        tipoCapa = TipoCapaEnum.COMUM; //A capa por padrão vai ser comum
        //só muda se ela for especificada na hora da criação

    }


    public void exibirLivro()
    {
        System.out.println("br.com.fiap.models.Livro: "+ titulo);
        System.out.println("Autor: "+ autor);
        System.out.println("br.com.fiap.models.Editora: "+ editora.nome);
        System.out.println("Preço: "+ preco);
        System.out.println("Tipo de capa: "+tipoCapa);
        System.out.println("----------------------------\n");
    }
}
