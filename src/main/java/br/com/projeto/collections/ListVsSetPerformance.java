package br.com.projeto.collections;

import java.util.*;

public class ListVsSetPerformance {

    public static void main(String[] args) {
        int quantidade = 5_000_000;
        List<String> lista = new ArrayList<>();
        Set<String> conjunto = new HashSet<>();
        System.out.println("Gerando " + quantidade + " IDs de transações...");
        System.out.println("Isso pode levar alguns segundos, aguarde...\n");

        String ultimoId = "";
        for (int i = 0; i < quantidade; i++) {
            String id = UUID.randomUUID().toString();
            lista.add(id);
            conjunto.add(id);
            if (i == quantidade - 1){
                ultimoId = id;
            }
        }
        System.out.println("Dados carregados na memória. Iniciando o teste de busca...\n");
        long inicioLista = System.currentTimeMillis();
        boolean achouNaLista = lista.contains(ultimoId);
        long fimLista = System.currentTimeMillis();

        long inicioSet = System.currentTimeMillis();
        boolean achouNoSet = conjunto.contains(ultimoId);
        long fimSet = System.currentTimeMillis();

        System.out.println("--- RESULTADOS DO CONFRONTO ---");
        System.out.println("Buscando na Lista (ArrayList):");
        System.out.println("Encontrou? " + achouNaLista);
        System.out.println("Tempo de busca: " + (fimLista - inicioLista) + " milissegundos\n");

        System.out.println("Buscando no Conjunto (HashSet):");
        System.out.println("Encontrou? " + achouNoSet);
        double tempoSetMs = (fimSet - inicioSet) / 1_000_000.0;
        System.out.println("Tempo de busca: " + (fimSet - inicioSet) + " nanosegundos (" + String.format("%.4f", tempoSetMs) + " ms)");
    }
}
