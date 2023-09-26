package modelo;

import java.text.DecimalFormat;

public class Apartamento extends Financiamento {
    private int numeroVagasGaragem;
    private int numeroAndar;

    // Construtor padrão exigido para deserialização
    public Apartamento() {
        super();
    }

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numeroVagasGaragem,
            int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 100 / 12;
        double meses = getPrazoFinanciamento() * 12;
        return getValorImovel() * Math.pow(1 + taxaMensal, meses) * taxaMensal / (Math.pow(1 + taxaMensal, meses) - 1);
    }

    @Override
    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * getPrazoFinanciamento() * 12;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return "Financiamento Apartamento:\n" +
                "Valor do Imóvel: R$ " + df.format(getValorImovel()) + "\n" +
                "Prazo de Financiamento: " + getPrazoFinanciamento() + " anos\n" +
                "Taxa de Juros Anual: " + getTaxaJurosAnual() + "%\n" +
                "Total do Pagamento: R$ " + df.format(calcularTotalPagamento()) + "\n" +
                "Total a ser pago: R$ " + df.format(calcularTotalPagamento()) + "\n";
    }

}
