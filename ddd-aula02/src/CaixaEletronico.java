import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int valor, notas50, notas20, notas10;
        System.out.println("Informe o valor a ser sacado: R$ ");
        valor = leitor.nextInt();
        notas50 = valor / 50;
        System.out.println("Notas de R$ 50: " + notas50);
        valor = valor % 50; //resto da divisão
        notas20 = valor / 20;
        System.out.println("Notas de R$ 20: " + notas20);
        valor = valor % 20;
        notas10 = valor / 10;
        System.out.println("Notas de R$ 10: " + notas10);
    }

}
