package br.com.projeto.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadPerfomance {

    public static void main(String[] args) {
        String filePath = "transacoes_gigantes.csv";
        System.out.println("Iniciando leitura com Scanner (Abordagem Ingenua)...");
        long inicio = System.currentTimeMillis();
        List<String> todasAsLinhas = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                todasAsLinhas.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");
            return;
        }
        double somaCompras = 0.0;
        int totalCompras = 0;
        for (String linhas : todasAsLinhas) {
            String[] colunas = linhas.split(",");

            if (colunas[1].equals("COMPRA")){
                somaCompras += Double.parseDouble(colunas[2].replace(",", "."));
                totalCompras++;
            }
        }

        long fim = System.currentTimeMillis();
        System.out.println("Total de compras encontradas " + totalCompras);
        System.out.println("Soma total: R$ " + String.format("%.2f", somaCompras));
        System.out.println("Tempo de processamento (BadPerfomance): " + (fim - inicio));
    }
}
