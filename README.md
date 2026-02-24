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

## 🧪 Experimento 02: O Confronto das Collections (ArrayList vs HashSet)

### 📌 O Problema
Em sistemas de alta disponibilidade (como motores de antifraude), é comum precisarmos verificar se um elemento existe dentro de uma grande base de dados em memória. O objetivo deste laboratório foi provar o impacto da escolha da estrutura de dados correta ao realizar buscas.

### ⚔️ O Cenário
Foram gerados **5.000.000 de UUIDs** aleatórios e inseridos simultaneamente em uma `ArrayList` e em um `HashSet`. Em seguida, o algoritmo foi instruído a buscar o **último ID inserido** em ambas as estruturas, forçando o pior cenário possível (Worst-Case Scenario) para a Lista.

### 📊 Resultados e Complexidade Algorítmica

* **Busca na ArrayList (Complexidade O(n)):** Levou cerca de **182 ms**. A lista precisou iterar sequencialmente sobre 4.999.999 elementos até encontrar a correspondência (`.equals()`).
* **Busca no HashSet (Complexidade O(1)):** Tempo virtualmente instantâneo. Utilizando o `hashCode()` do objeto, a JVM calcula o *bucket* exato de memória e acessa o elemento diretamente, sem iterações.

**Conclusão:** O uso de Tabelas Hash (como `HashSet` ou `HashMap`) é obrigatório para otimização de buscas em grandes volumes de dados na memória principal, eliminando o gargalo de iteração linear.

---

## 🧪 Experimento 03: Processamento Funcional com Stream API

### 📌 O Problema
Extrair inteligência de uma lista de dados brutos (Vendas) sem utilizar estruturas de controle imperativas (`for`, `if`, variáveis temporárias de soma), garantindo um código mais legível, conciso e menos suscetível a erros de estado.

### 🛠️ A Solução
Utilização de **Stream API**, **Lambdas** e **Method References** para criar um pipeline de dados que realiza três operações em sequência:
1. **Filtragem:** Seleção apenas de registros com status "APROVADA".
2. **Agrupamento:** Separação dos dados por categorias lógicas utilizando `Collectors.groupingBy`.
3. **Agregação:** Soma monetária dos valores de cada categoria via `Collectors.summingDouble`.

### 💡 O Aprendizado
A migração do paradigma imperativo para o funcional permite que o desenvolvedor foque na regra de negócio. Além disso, o uso de `Records` (Java 16+) trouxe imutabilidade e clareza para o modelo de dados do experimento.

## 🛠️ Tecnologias Utilizadas
* **Java 21+**
* `java.nio.file` (New I/O)
* Stream API & Lambdas
* JVM Memory Management Concepts

## 👨‍💻 Autor
**[Pedro Henrique Carvalho]** *Estudante de Engenharia de Software PUC MINAS* 🔗 [LinkedIn](https://www.linkedin.com/in/pedrohcpereira/)
