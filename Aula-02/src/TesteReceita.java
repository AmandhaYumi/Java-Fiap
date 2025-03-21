public class TesteReceita {
    public static void main(String[] args) {
        Receita boloFuba = new Receita();
        boloFuba.nome = "Bolo de fubá";
        boloFuba.porcoes = 8;
        boloFuba.ingredientes = "ovos, farinha, óleo, fubá, açúcar";
        boloFuba.autor = "Milaflor24.moura";
        boloFuba.preparo = "Bater os ovos, açúcar, óleo e ovos no liquidificador, depois adicionar o fubá e assar";
        boloFuba.descricao = "bolo de fubá cremoso";
        boloFuba.dificuldade = "fácil";
        boloFuba.tempo = 35;

        boloFuba.exibirReceita();
        System.out.println("=======");
        System.out.println(boloFuba.exibirReceita2());
    }

}
