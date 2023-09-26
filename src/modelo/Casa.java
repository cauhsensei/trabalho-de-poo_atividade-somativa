package modelo;

import java.text.DecimalFormat;

import excecoes.DescontoMaiorDoQueJurosException;

public class Casa extends Financiamento {
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;
    private static final double VALOR_SEGURO = 80.0;

    //construtor 
    public Casa() {
        super();
    }

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida,
            double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public void aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double juros = calcularPagamentoMensal() - super.calcularPagamentoMensal();
        if (desconto > juros) {
            throw new DescontoMaiorDoQueJurosException("O desconto não pode ser maior que os juros da mensalidade.");
        }
    }

    @Override
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() + VALOR_SEGURO;
    }

    @Override
    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * getPrazoFinanciamento() * 12;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return "Financiamento Casa:\n" +
                "Valor do Imóvel: R$ " + df.format(getValorImovel()) + "\n" +
                "Prazo de Financiamento: " + getPrazoFinanciamento() + " anos\n" +
                "Taxa de Juros Anual: " + getTaxaJurosAnual() + "%\n" +
                "Total do Pagamento: R$ " + df.format(calcularTotalPagamento()) + "\n" +
                "Total a ser pago: R$ " + df.format(calcularTotalPagamento()) + "\n";
    }

}
