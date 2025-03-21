public class Receita {
    String nome;
    String ingredientes;
    String autor;
    int tempo;
    int porcoes;
    String preparo;
    String dificuldade;
    String descricao;

    //Método sem retorno
    public void exibirReceita()
    {
        System.out.println("Nome receita: "+nome);
        System.out.println("Publicado por: "+autor);
        System.out.println("Descrição: "+descricao);
        System.out.println("Tempo: "+tempo);
        System.out.println("Dificuldade da receita: "+dificuldade);
        System.out.println("Rende: "+porcoes+" porções");
        System.out.println("Ingredientes: "+ingredientes);
        System.out.println("Modo de preparo: "+preparo);
    }

    //Método com retorno
    public String exibirReceita2()
    {
        String mensagem = "Nome da receita: "+nome +
                "Publicado por: "+autor +
                "Descrição: "+descricao+
                "Dificuldade: "+dificuldade +
                "Rende: "+porcoes+" porções" +
                "Tempo: "+tempo +
                "Ingredientes: "+ingredientes +
                "\tModo de preparo: "+preparo;

        return mensagem;
    }
}