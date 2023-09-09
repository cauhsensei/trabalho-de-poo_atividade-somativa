package util;

import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);

    public double pedirValorImovel() {
        double valor = -1;
        do {
            System.out.println("Digite o valor do imóvel: ");
            valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Digite um valor positivo para o imóvel.");
            }
        } while (valor <= 0);
        return valor;
    }

    public int pedirPrazoFinanciamento() {
        int prazo = -1;
        do {
            System.out.println("Digite o prazo do financiamento em anos: ");
            prazo = scanner.nextInt();
            if (prazo <= 0) {
                System.out.println("Digite um prazo positivo.");
            }
        } while (prazo <= 0);
        return prazo;
    }

    public double pedirTaxaJuros() {
        double taxa = -1;
        do {
            System.out.println("Digite a taxa de juros anual: ");
            taxa = scanner.nextDouble();
            if (taxa <= 0) {
                System.out.println("Digite uma taxa positiva.");
            }
        } while (taxa <= 0);
        return taxa;
    }
}
