package main;

import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;
import modelo.Financiamento;
import util.InterfaceUsuario;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
    DecimalFormat df = new DecimalFormat("#.00");
    Scanner scanner = new Scanner(System.in);
    
    // Solicitando dados para CASA:
    System.out.println("Dados para financiamento de Casa:");
    System.out.print("Valor do imóvel (Casa): ");
    double valorImovelCasa = scanner.nextDouble();
    System.out.print("Prazo de financiamento (em anos): ");
    int prazoFinanciamentoCasa = scanner.nextInt();
    System.out.print("Taxa de juros anual (em %): ");
    double taxaJurosCasa = scanner.nextDouble();
    System.out.print("Tamanho da área construída: ");
    double tamanhoAreaConstruida = scanner.nextDouble();
    System.out.print("Tamanho do terreno: ");
    double tamanhoTerreno = scanner.nextDouble();
    Casa financiamentoCasa = new Casa(valorImovelCasa, prazoFinanciamentoCasa, taxaJurosCasa, tamanhoAreaConstruida, tamanhoTerreno);
    
    // Solicitando dados para APARTAMENTO:
    System.out.println("\nDados para financiamento de Apartamento:");
    System.out.print("Valor do imóvel (Apartamento): ");
    double valorImovelApt = scanner.nextDouble();
    System.out.print("Prazo de financiamento (em anos): ");
    int prazoFinanciamentoApt = scanner.nextInt();
    System.out.print("Taxa de juros anual (em %): ");
    double taxaJurosApt = scanner.nextDouble();
    System.out.print("Número de vagas na garagem: ");
    int numeroVagasGaragem = scanner.nextInt();
    System.out.print("Número do andar: ");
    int numeroAndar = scanner.nextInt();
    Apartamento financiamentoApt = new Apartamento(valorImovelApt, prazoFinanciamentoApt, taxaJurosApt, numeroVagasGaragem, numeroAndar);
    
    // Solicitando dados para TERRENO:
    System.out.println("\nDados para financiamento de Terreno:");
    System.out.print("Valor do imóvel (Terreno): ");
    double valorImovelTerreno = scanner.nextDouble();
    System.out.print("Prazo de financiamento (em anos): ");
    int prazoFinanciamentoTerreno = scanner.nextInt();
    System.out.print("Taxa de juros anual (em %): ");
    double taxaJurosTerreno = scanner.nextDouble();
    System.out.print("Tipo de zona (residencial/comercial): ");
    String tipoZona = scanner.next();
    Terreno financiamentoTerreno = new Terreno(valorImovelTerreno, prazoFinanciamentoTerreno, taxaJurosTerreno, tipoZona);

    // Resumo:
    System.out.println("\n--- RESUMO DOS FINANCIAMENTOS ---");
    
    System.out.println("\nFinanciamento Casa:");
    financiamentoCasa.exibirDadosFinanciamento();
    
    System.out.println("\nFinanciamento Apartamento:");
    financiamentoApt.exibirDadosFinanciamento();
    
    System.out.println("\nFinanciamento Terreno:");
    financiamentoTerreno.exibirDadosFinanciamento();

    // Somando os valores totais:
    double totalFinanciado = financiamentoCasa.calcularTotalPagamento() + financiamentoApt.calcularTotalPagamento() + financiamentoTerreno.calcularTotalPagamento();

    System.out.println("\nValor total financiado (de todos os imóveis): R$ " + df.format(totalFinanciado));

    scanner.close();
}

}
