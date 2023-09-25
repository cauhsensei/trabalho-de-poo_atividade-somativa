package util;

import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);

    public double pedirValorImovel() {
        return solicitarValorPositivo("Digite o valor do imóvel: ", "Digite um valor positivo para o imóvel.");
    }

    public int pedirPrazoFinanciamento() {
        return (int) solicitarValorPositivo("Digite o prazo do financiamento em anos: ", "Digite um prazo positivo.");
    }

    public double pedirTaxaJuros() {
        return solicitarValorPositivo("Digite a taxa de juros anual: ", "Digite uma taxa positiva.");
    }

    public double pedirTamanhoAreaConstruida() {
        return solicitarValorPositivo("Digite o tamanho da área construída (em metros quadrados): ", "Digite um valor positivo para a área construída.");
    }

    public double pedirTamanhoTerreno() {
        return solicitarValorPositivo("Digite o tamanho do terreno (em metros quadrados): ", "Digite um valor positivo para o tamanho do terreno.");
    }

    public int pedirNumeroVagasGaragem() {
        return (int) solicitarValorPositivo("Digite o número de vagas na garagem: ", "Digite um número positivo de vagas.");
    }

    public int pedirNumeroAndar() {
        return (int) solicitarValorPositivo("Digite o número do andar: ", "Digite um número de andar positivo.");
    }

    public String pedirTipoZona() {
        System.out.println("Digite o tipo de zona (Residencial/Comercial): ");
        String tipoZona;
        do {
            tipoZona = scanner.nextLine().trim();
            if (!(tipoZona.equalsIgnoreCase("Residencial") || tipoZona.equalsIgnoreCase("Comercial"))) {
                System.out.println("Digite um tipo de zona válido. Opções: Residencial ou Comercial.");
            }
        } while (!(tipoZona.equalsIgnoreCase("Residencial") || tipoZona.equalsIgnoreCase("Comercial")));
        return tipoZona;
    }

    private double solicitarValorPositivo(String promptMessage, String errorMessage) {
        double valor = -1;
        do {
            System.out.println(promptMessage);
            valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println(errorMessage);
            }
        } while (valor <= 0);
        return valor;
    }
}
