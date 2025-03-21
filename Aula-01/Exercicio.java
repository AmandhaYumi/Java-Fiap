import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int res;

        System.out.println("Digite o valor: ");
        res = entrada.nextInt();

        System.out.println(res/50+" Cédulas de 50");
        System.out.println((res%50)/20+" Cédulas de 20");
        System.out.println(((res%50)%20)/10+" Cédulas de 10");

    }
}