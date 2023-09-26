package util;

import excecoes.DescontoMaiorDoQueJurosException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Apartamento;
import modelo.Terreno;


public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);

    public void salvarDadosSerializados(ArrayList<Financiamento> financiamentos) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("financiamentos_serializados.dat"))) {
        oos.writeObject(financiamentos);
    } catch (IOException e) {
        System.out.println("Erro ao salvar dados serializados: " + e.getMessage());
    }
}


public ArrayList<Financiamento> lerDadosSerializados() {
    ArrayList<Financiamento> financiamentos = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("financiamentos_serializados.dat"))) {
        Object obj = ois.readObject();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> objList = (ArrayList<?>) obj;
            for (Object item : objList) {
                if (item instanceof Financiamento) {
                    financiamentos.add((Financiamento) item);
                } else {
                    System.out.println("Erro ao ler dados serializados: objeto não é do tipo Financiamento.");
                }
            }
        } else {
            System.out.println("Erro ao ler dados serializados: objeto não é do tipo ArrayList.");
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Erro ao ler dados serializados: " + e.getMessage());
    }
    return financiamentos;
}

public void salvarFinanciamentoEmTexto(ArrayList<Financiamento> financiamentos) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("financiamentos.txt"))) {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        writer.write("--- RESUMO DOS FINANCIAMENTOS ---\n\n");
        for (Financiamento financiamento : financiamentos) {
            writer.write(financiamento.toString());
        }
        double totalFinanciado = financiamentos.stream().mapToDouble(Financiamento::calcularTotalPagamento).sum();
        writer.write("Valor total financiado (de todos os imóveis): R$ " + df.format(totalFinanciado));
    } catch (IOException e) {
        System.out.println("Erro ao salvar financiamentos: " + e.getMessage());
    }
}

    public void lerFinanciamentoDeTexto() {
        System.out.println("Dados lidos do arquivo de texto:");
        try (BufferedReader reader = new BufferedReader(new FileReader("financiamentos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler financiamentos: " + e.getMessage());
        }
}

    public Casa cadastrarCasa() {
        System.out.println("Dados para financiamento de Casa:");
        double valorImovelCasa = pedirValor("Valor do imóvel (Casa): ");
        int prazoFinanciamentoCasa = (int) pedirValor("Prazo de financiamento (em anos): ");
        double taxaJurosCasa = pedirValor("Taxa de juros anual (em %): ");
        double tamanhoAreaConstruida = pedirValor("Tamanho da área construída: ");
        double tamanhoTerreno = pedirValor("Tamanho do terreno: ");
        
        return new Casa(valorImovelCasa, prazoFinanciamentoCasa, taxaJurosCasa, tamanhoAreaConstruida, tamanhoTerreno);
    }

    public Apartamento cadastrarApartamento() {
        System.out.println("\nDados para financiamento de Apartamento:");
        double valorImovelApt = pedirValor("Valor do imóvel (Apartamento): ");
        int prazoFinanciamentoApt = (int) pedirValor("Prazo de financiamento (em anos): ");
        double taxaJurosApt = pedirValor("Taxa de juros anual (em %): ");
        int numeroVagasGaragem = (int) pedirValor("Número de vagas na garagem: ");
        int numeroAndar = (int) pedirValor("Número do andar: ");
        
        return new Apartamento(valorImovelApt, prazoFinanciamentoApt, taxaJurosApt, numeroVagasGaragem, numeroAndar);
    }

    public Terreno cadastrarTerreno() {
        System.out.println("\nDados para financiamento de Terreno:");
        double valorImovelTerreno = pedirValor("Valor do imóvel (Terreno): ");
        int prazoFinanciamentoTerreno = (int) pedirValor("Prazo de financiamento (em anos): ");
        double taxaJurosTerreno = pedirValor("Taxa de juros anual (em %): ");
        System.out.print("Tipo de zona (residencial/comercial): ");
        String tipoZona;
        do {
            tipoZona = scanner.nextLine().trim();
            if (!(tipoZona.equalsIgnoreCase("Residencial") || tipoZona.equalsIgnoreCase("Comercial"))) {
                System.out.println("Digite um tipo de zona válido. Opções: Residencial ou Comercial.");
            }
        } while (!(tipoZona.equalsIgnoreCase("Residencial") || tipoZona.equalsIgnoreCase("Comercial")));
        
        return new Terreno(valorImovelTerreno, prazoFinanciamentoTerreno, taxaJurosTerreno, tipoZona);
    }

    public void exibirResumoFinanciamentos(Casa casa, Apartamento apt, Terreno terreno) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\n--- RESUMO DOS FINANCIAMENTOS ---");

        System.out.println("\nFinanciamento Casa:");
        casa.exibirDadosFinanciamento();
        System.out.println("Total a ser pago: R$ " + df.format(casa.calcularTotalPagamento()));

        System.out.println("\nFinanciamento Apartamento:");
        apt.exibirDadosFinanciamento();
        System.out.println("Total a ser pago: R$ " + df.format(apt.calcularTotalPagamento()));

        System.out.println("\nFinanciamento Terreno:");
        terreno.exibirDadosFinanciamento();
        System.out.println("Total a ser pago: R$ " + df.format(terreno.calcularTotalPagamento()));

        double totalFinanciado = casa.calcularTotalPagamento() + apt.calcularTotalPagamento() + terreno.calcularTotalPagamento();
        System.out.println("\nValor total financiado (de todos os imóveis): R$ " + df.format(totalFinanciado));
    }
    
     public void aplicarDescontoEmCasa(Casa casa) {
        while (true) {
            try {
                double desconto = pedirValor("Informe o desconto para a casa: ");
                casa.aplicarDesconto(desconto);
                break;
            } catch (DescontoMaiorDoQueJurosException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private double pedirValor(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.next(); // limpandom o scanner
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}
