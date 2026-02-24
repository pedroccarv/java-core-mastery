package br.com.projeto.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

public class HighPerformance {

    public static void main(String[] args) {
        Path filePath = Paths.get("transacoes_gigantes.csv");
        System.out.println("Iniciando leitura com NIO.2 e Streams (Abordagem Profissional)...");
        long inicio = System.currentTimeMillis();
        try (Stream<String> linhas = Files.lines(filePath)) {
            DoubleSummaryStatistics estatisticas = linhas
                    .skip(1)
                    .filter(linha -> linha.contains(",COMPRA,"))
                    .mapToDouble(linha -> {
                        String[] colunas = linha.split(",");
                        return Double.parseDouble(colunas[2].replace(",", "."));
                    })
                    .summaryStatistics();
            long fim = System.currentTimeMillis();
            System.out.println("Total de COMPRAS encontradas: " + estatisticas.getCount());
            System.out.println("Soma total: R$ " + String.format("%.2f", estatisticas.getSum()));
            System.out.println("Média gasta por compra: R$ " + String.format("%.2f", estatisticas.getAverage()));
            System.out.println("Tempo de processamento (HighPerformance): " + (fim - inicio) + " ms!");
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

}
