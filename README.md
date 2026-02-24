# 🚀 Java Core Mastery | Performance & Data Structures Lab

Um laboratório prático dedicado ao aprofundamento em **Java Core**, com foco em engenharia de software, gerenciamento de memória (JVM), alta performance e manipulação eficiente de grandes volumes de dados.

O objetivo deste repositório é ir além dos frameworks e demonstrar domínio sobre as bibliotecas base da linguagem, provando que é possível escrever código escalável e otimizado escolhendo as estruturas de dados e APIs corretas.

---

## 🧪 Experimento 01: O Desafio de 1 Milhão de Registros (I/O vs NIO.2)

### 📌 O Problema
Sistemas corporativos frequentemente precisam processar grandes arquivos (logs, conciliações bancárias, extração de dados) sem derrubar o servidor. O desafio era ler um arquivo CSV com **1.000.000 de transações financeiras**, filtrar apenas as "COMPRAS" e calcular a soma e a média gasta, tudo isso evitando gargalos de CPU e estouro de memória RAM (`OutOfMemoryError`).

### ❌ A Abordagem Ingênua (`java.io` + `ArrayList`)
A primeira tentativa utilizou a abordagem clássica ensinada no início dos estudos de programação:
* Uso de `java.io.File` e `Scanner`.
* Leitura de todo o arquivo para dentro de uma `List<String>` na memória principal antes do processamento.
* **O Gargalo:** Instanciar 1 milhão de objetos `String` simultaneamente causa um enorme pico de consumo de RAM e faz o *Garbage Collector* da JVM trabalhar sob estresse extremo.

### ✅ A Abordagem de Alta Performance (`java.nio` + `Stream API`)
A solução otimizada substituiu o carregamento em lote por um **pipeline de processamento contínuo**:
* Uso de `Files.lines()` (NIO.2) para ler o arquivo sob demanda (lazy evaluation).
* Uso de `Streams` para filtrar e mapear os dados "on the fly".
* Uso de `DoubleSummaryStatistics` para calcular soma, média e contagem em uma única passagem (O(n)).
* **A Vantagem:** A linha lida do disco é processada e imediatamente descartada da memória, mantendo o consumo de RAM próximo a zero, independentemente do tamanho do arquivo.

### 📊 Resultados do Benchmark

| Métrica | Abordagem Ingênua | Abordagem Otimizada (NIO.2) |
| :--- | :--- | :--- |
| **Volume de Dados** | 1.000.000 linhas | 1.000.000 linhas |
| **Tempo de Execução** | ~2116 ms | ~657 ms |
| **Consumo de Memória** | Crítico (Gigabytes) | Mínimo (Megabytes) |

**Conclusão:** A abordagem com NIO.2 e Streams reduziu o tempo de processamento em mais de **3x (68% mais rápido)**, além de garantir a estabilidade do sistema ao isolar o uso da memória RAM.

---

## 🛠️ Tecnologias Utilizadas
* **Java 21+**
* `java.nio.file` (New I/O)
* Stream API & Lambdas
* JVM Memory Management Concepts

## 👨‍💻 Autor
**[Pedro Henrique Carvalho]** *Estudante de Engenharia de Software PUC MINAS* 🔗 [LinkedIn](https://www.linkedin.com/in/pedrohcpereira/)
