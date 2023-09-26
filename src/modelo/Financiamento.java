package modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import modelo.Financiamento;


public abstract class Financiamento implements Serializable {
    private double valorImovel;
    private int prazoFinanciamento; 
    private double taxaJurosAnual;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public Financiamento() {}

    public double calcularPagamentoMensal() {
        int prazoEmMeses = prazoFinanciamento * 12; 
        return (valorImovel / prazoEmMeses) * (1 + (taxaJurosAnual / 100 / 12)); 
    }

    public double calcularTotalPagamento() {
        int prazoEmMeses = prazoFinanciamento * 12;
        return calcularPagamentoMensal() * prazoEmMeses;
    }

    // Getters
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    // dados do financiamento
    public void exibirDadosFinanciamento() {
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("Valor do Imóvel: " + df.format(valorImovel));
        System.out.println("Prazo de Financiamento: " + prazoFinanciamento + " anos");
        System.out.println("Taxa de Juros Anual: " + taxaJurosAnual + "%");
        System.out.println("Total do Pagamento: " + df.format(calcularTotalPagamento()));
    }
}
