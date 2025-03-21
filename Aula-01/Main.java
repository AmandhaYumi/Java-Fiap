import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String nome;
        byte idade;

        System.out.println("Digite seu nome: ");
        nome = entrada.nextLine();

        System.out.println("Digite sua idade: ");
        idade = entrada.nextByte();

        System.out.println("Bom dia " + nome + "!");
        System.out.println(idade + " anos");

    }
}