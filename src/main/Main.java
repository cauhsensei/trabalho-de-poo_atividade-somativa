package main;

import modelo.Casa;
import modelo.Financiamento;

import java.util.ArrayList;

import modelo.Apartamento;
import modelo.Terreno;
import util.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        Casa casa = interfaceUsuario.cadastrarCasa();
        Apartamento apt = interfaceUsuario.cadastrarApartamento();
        Terreno terreno = interfaceUsuario.cadastrarTerreno();

        interfaceUsuario.aplicarDescontoEmCasa(casa);
        interfaceUsuario.exibirResumoFinanciamentos(casa, apt, terreno);

        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        financiamentos.add(casa);
        financiamentos.add(apt);
        financiamentos.add(terreno);

        interfaceUsuario.salvarDadosSerializados(financiamentos);
        interfaceUsuario.salvarFinanciamentoEmTexto(financiamentos);

        ArrayList<Financiamento> financiamentosLidos = interfaceUsuario.lerDadosSerializados();
        for (Financiamento f : financiamentosLidos) {
            System.out.println(f.toString());
        }

        interfaceUsuario.fechar();
    }

}
