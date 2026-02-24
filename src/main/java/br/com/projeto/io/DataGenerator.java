package br.com.projeto.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        String filePath = "transacoes_gigantes.csv";
        int linhasParaGerar = 1_000_000;

        System.out.println("Iniciando a geração do arquivo...");
        long inicio = System.currentTimeMillis();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("ID_TRANSACAO,TIPO_LANCAMENTO,VALOR");
            writer.newLine();

            Random random = new Random();
            String[] tipos = {"COMPRA", "SAQUE", "DEPOSITO", "TRANSFERENCIA"};

            for (int i = 1; i <= linhasParaGerar; i++) {
                String tipo = tipos[random.nextInt(tipos.length)];
                double valor = 10.0 + (random.nextDouble() * 990.0);

                String linha = i + "," + tipo + "," + String.format("%.2f", valor);

                writer.write(linha);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao tentar escrever no disco: " + e.getMessage());
        }

        long fim = System.currentTimeMillis();
        System.out.println("Arquivo gerado com sucesso em " + (fim - inicio) + " ms!");
    }
}