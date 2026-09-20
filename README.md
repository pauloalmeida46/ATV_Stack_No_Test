# tdd-stack

Implementação de uma pilha (stack) genérica com limite de tamanho e suporte a uma estratégia de cálculo configurável (`CalculableStrategy`), acompanhada de testes de unidade com **JUnit 5** e **Mockito**.

## Projeto acadêmico

Este projeto foi desenvolvido como atividade acadêmica da **FATEC São José dos Campos**, na disciplina de **Qualidade de Testes de Software**.

## Sobre o projeto

A classe principal é a `CustomStack<T extends Number>`, que implementa as operações clássicas de uma pilha:

- `push(T element)` — empilha um elemento, aplicando antes uma `CalculableStrategy` sobre o valor; lança `StackFullException` se o limite for atingido.
- `pop()` — desempilha e retorna o último elemento; lança `StackEmptyException` se a pilha estiver vazia.
- `top()` — retorna o último elemento sem removê-lo.
- `isEmpty()` — indica se a pilha está vazia.
- `size()` — retorna a quantidade de elementos empilhados.

## Estrutura do projeto

```
tdd-stack/
├── pom.xml
├── README.md
├── TESTES.md
└── src/
    ├── main/java/br/edu/fatec/sjc/
    │   ├── CustomStack.java
    │   ├── CalculableStrategy.java
    │   ├── StackFullException.java
    │   └── StackEmptyException.java
    └── test/java/br/edu/fatec/sjc/
        ├── CustomStackTest.java
        ├── CalculableStrategyTest.java
        ├── StackFullExceptionTest.java
        └── StackEmptyExceptionTest.java
```

Veja o [TESTES.md](TESTES.md) para uma explicação detalhada de cada arquivo de teste.

## Tecnologias

- Java 11
- Maven
- JUnit 5
- Mockito
- JaCoCo (relatório de cobertura de testes)

## Como rodar os testes

```bash
mvn test
```

O relatório de cobertura é gerado em `target/site/jacoco/index.html` após a execução.
