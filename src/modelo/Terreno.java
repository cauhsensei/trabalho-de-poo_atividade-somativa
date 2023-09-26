package modelo;

import java.text.DecimalFormat;

public class Terreno extends Financiamento {
    private String tipoZona; // Ex: "Residencial" ou "Comercial"

    // Construtor padrão exigido para deserialização
    public Terreno() {
        super();
    }

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() * 1.02;
    }

    @Override
    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * getPrazoFinanciamento() * 12;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return "Financiamento Terreno:\n" +
                "Valor do Imóvel: R$ " + df.format(getValorImovel()) + "\n" +
                "Prazo de Financiamento: " + getPrazoFinanciamento() + " anos\n" +
                "Taxa de Juros Anual: " + getTaxaJurosAnual() + "%\n" +
                "Total do Pagamento: R$ " + df.format(calcularTotalPagamento()) + "\n" +
                "Total a ser pago: R$ " + df.format(calcularTotalPagamento()) + "\n";
    }

}
