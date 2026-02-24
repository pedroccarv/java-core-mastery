package br.com.projeto.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamChallenge {

    // Record: Uma forma moderna e limpa de criar classes de dados no Java
    public record Venda(String categoria, double valor, String status) {}

    public static void main(String[] args) {

        List<Venda> vendas = Arrays.asList(
                new Venda("Eletrônicos", 2500.00, "APROVADA"),
                new Venda("Roupas", 150.00, "RECUSADA"),
                new Venda("Eletrônicos", 3200.00, "APROVADA"),
                new Venda("Livros", 90.00, "APROVADA"),
                new Venda("Roupas", 200.00, "APROVADA"),
                new Venda("Eletrônicos", 4500.00, "RECUSADA"),
                new Venda("Livros", 120.00, "APROVADA")
        );

        System.out.println("Processando relatório de vendas...\n");

        // SUA MISSÃO COMEÇA AQUI:
        // Crie o pipeline de stream para filtrar "APROVADA", agrupar por categoria e somar o valor.

        /* Map<String, Double> relatorio = vendas.stream()
                .??? // 1. Filtre as aprovadas
                .??? // 2. Colete agrupando por categoria e somando o valor
        */

        // System.out.println(relatorio);

        // RESULTADO ESPERADO NO CONSOLE QUANDO VOCÊ TERMINAR:
        // {Roupas=200.0, Eletrônicos=5700.0, Livros=210.0}

        Map<String, Double> vendasAprovadas = vendas.stream().filter(x -> x.status.equals("APROVADA")).collect(Collectors.groupingBy(Venda::categoria, Collectors.summingDouble(Venda::valor)));
        System.out.println(vendasAprovadas);

    }
}