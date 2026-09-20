# Testes do projeto `tdd-stack`

Este documento explica os 4 arquivos de teste criados em `src/test/java/br/edu/fatec/sjc/`, cada um correspondendo a um arquivo de `src/main/java/br/edu/fatec/sjc/`.

Última execução (`mvn test`): **19 testes, 0 falhas, 0 erros**, com **100% de cobertura de instruções, branches, linhas e métodos** em `CustomStack`, `StackFullException` e `StackEmptyException` (medido pelo plugin JaCoCo). A interface `CalculableStrategy` não gera métricas de cobertura porque não possui código executável próprio (apenas a assinatura do método), mas seu comportamento é validado tanto pelo teste dedicado quanto indiretamente pelos testes de `CustomStack`.

---

## `CustomStackTest.java`

Cobre a classe `CustomStack<T extends Number>`, que implementa a pilha (stack) com limite de tamanho.

Testes incluídos:

- **Estado inicial**: a pilha começa vazia (`isEmpty() == true`, `size() == 0`).
- **`push`**: adiciona elemento e o torna acessível via `top()`.
- **`push` com estratégia customizada**: usa um mock de `CalculableStrategy` (Mockito) para confirmar que o valor armazenado é o retornado por `calculateValue(...)`, e não o valor original — validando que `push` realmente delega a transformação para a estratégia.
- **`push` além do limite**: enche a pilha até o `limit` definido no construtor e verifica que uma nova tentativa lança `StackFullException`, mantendo o tamanho inalterado.
- **`push` após `pop`**: confirma que, depois de liberar espaço com `pop`, é possível empilhar novamente até o limite.
- **`pop`**: remove e retorna o último elemento empilhado (comportamento LIFO), atualizando `size()` e `top()`.
- **`pop` em pilha vazia**: lança `StackEmptyException`.
- **`pop` até esvaziar**: depois de remover todos os elementos, uma nova chamada a `pop` volta a lançar `StackEmptyException`.
- **`isEmpty`**: casos `true` (sem elementos) e `false` (com elementos).
- **`top`**: retorna o último elemento sem removê-lo (`size()` não muda).
- **`size`**: reflete corretamente o número de elementos após pushes e pops combinados.

Por que um mock aqui? A estratégia (`CalculableStrategy`) é uma dependência injetada no construtor, então usar um mock isola o teste do comportamento de `CustomStack` em si — sem depender de uma implementação real da estratégia.

---

## `CalculableStrategyTest.java`

Cobre a interface `CalculableStrategy<T extends Number>`, que define o método `calculateValue(T value)`.

Como é uma interface (sem implementação própria), o teste valida o **contrato** que qualquer implementação deve cumprir, usando implementações de exemplo criadas só para o teste:

- **Implementação via lambda**: cria uma estratégia que dobra o valor recebido e confirma que o retorno é o esperado.
- **Implementação via classe anônima**: mesma ideia, mas usando `new CalculableStrategy<Integer>() { ... }` para garantir que a interface também funciona com a sintaxe tradicional (não só lambdas).
- **Propagação de `NullPointerException`**: como a assinatura do método declara `throws NullPointerException`, o teste confirma que uma implementação que lança essa exceção ao receber `null` realmente a propaga para quem chama.

---

## `StackFullExceptionTest.java`

Cobre a classe `StackFullException`, lançada por `CustomStack.push(...)` quando a pilha atinge o limite.

Testes incluídos:

- **É uma `Exception`**: instancia a classe e confirma que ela é subtipo de `Exception` (via checked exception).
- **É lançável e capturável**: usa `assertThrows` para confirmar que `throw new StackFullException()` é corretamente propagada e reconhecida pelo tipo.

Como a classe não tem campos, construtores customizados ou lógica própria, o teste cobre 100% dela apenas instanciando-a e verificando seu comportamento como exceção.

---

## `StackEmptyExceptionTest.java`

Cobre a classe `StackEmptyException`, lançada por `CustomStack.pop()` quando a pilha está vazia.

Testes incluídos (estrutura idêntica ao `StackFullExceptionTest`):

- **É uma `Exception`**: confirma o tipo via instanciação.
- **É lançável e capturável**: usa `assertThrows` para validar o fluxo de `throw`/`catch`.

---

## Como rodar

```bash
mvn test
```

O relatório de cobertura HTML fica em `target/site/jacoco/index.html`, e o resumo em CSV em `target/site/jacoco/jacoco.csv`.
