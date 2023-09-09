package main;

import modelo.Financiamento;
import util.InterfaceUsuario;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");

        // Parte que pega informações do usuário
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJuros = interfaceUsuario.pedirTaxaJuros();
        
        Financiamento financiamentoUsuario = new Financiamento(valorImovel, prazoFinanciamento, taxaJuros);
        financiamentoUsuario.exibirDadosFinanciamento();

        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        financiamentos.add(new Financiamento(200000, 20, 10.0));
        financiamentos.add(new Financiamento(300000, 25, 26.6667));
        financiamentos.add(new Financiamento(150000, 15, 3.3333));
        financiamentos.add(new Financiamento(250000, 30, 10.0));

        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;

        // Mostrando detalhes de cada financiamento no ArrayList
        int i = 1;
        for(Financiamento f : financiamentos) {
            System.out.println("Financiamento " + i + " – valor do imóvel: R$ " + df.format(f.getValorImovel()) +
                               ", valor do financiamento: R$ " + df.format(f.calcularTotalPagamento()));
            totalValorImoveis += f.getValorImovel();
            totalValorFinanciamentos += f.calcularTotalPagamento();
            i++;
        }

        // Somando com o financiamento inserido pelo usuário
        totalValorImoveis += financiamentoUsuario.getValorImovel();
        totalValorFinanciamentos += financiamentoUsuario.calcularTotalPagamento();

        System.out.println("Total de todos os imóveis: R$ " + df.format(totalValorImoveis));
        System.out.println("Total de todos os financiamentos: R$ " + df.format(totalValorFinanciamentos));
    }
}
