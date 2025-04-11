package br.com.fiap.tests;

import br.com.fiap.models.Editora;
import br.com.fiap.models.LivroFisico;

public class TesteLivroFisico {
    public static void main(String[] args) {
        Editora novatec = new Editora();
        novatec.setNome("Novatec");
        novatec.setSite("www.novatec.com");
        novatec.setTelefone("11568958995");

        LivroFisico livroFisico = new LivroFisico();
        livroFisico.setTitulo("Livrox");
        livroFisico.setEditora(novatec);
        livroFisico.setAutor("Alguem");
        livroFisico.setEndereco("Avenida Paulista");
        livroFisico.setTaxaEntrega(14);
        livroFisico.setPreco(100);

        livroFisico.exibirLivro();
        System.out.println("Preço final R$ " + livroFisico.aplicarDesconto(15));
    }
}
