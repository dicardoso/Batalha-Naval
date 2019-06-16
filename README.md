# Batalha-Naval
**Projeto para a disciplina Linguagem de Programação 1 - 2014.1 (UFPB)**

## 1.	INTRODUÇÃO
“Batalha Naval” consiste em um jogo de estratégia que pode ser jogado por até duas pessoas, que devem escolher a posição de seus navios. O jogo tem um número mínimo de três navios:
- **Submarino** – 1 posição
- **Torpedeiro** – 2 posições
- **Fragata** – 3 posições

*Podendo conter mais dois*

- **Encouraçado** – 4 posições
- **Porta-aviões** – 5 posições

Totalizando, no máximo cinco navios. O objetivo é destruir todos os navios do jogador adversário.

## 2.	CONCEITO
O jogo foi desenvolvido usando conceitos de **Orientação à Objetos** e **Java** como linguagem de programação. 
Consiste em: uma interface `INavios` que possui todos os métodos usados pelas cinco classes de navios: `Submarino`, `Torpedeiro`, `Fragata`, `Encouraçado` e `Porta-aviões` e mais três classes: `BatalhaNaval`, `Tabuleiro`, `BatalhaException`).

A classe `BatalhaNaval` possui o método principal, ela é a responsável por executar o jogo enquanto o objetivo não for atingido. Primeiramente essa classe instancia dois objetos de `Tabuleiro` com valores nulos, que vão representar cada jogador, em seguida exibe o tabuleiro “default” e logo após inicia os navios.

Feito isso entra em um laço `do-while` que executa as rodadas do jogo.

#### Na classe `Tabuleiro`:
* O tabuleiro é criado usando um **array bidimensional 10x10**.
* `inciaNavios()` que atribui -1 (navio não atingido) nas posições dos navios.
* `iniciaTabuleiro()` que usa dois laços `for` para percorrer todo o array, atribuindo -2 (água não atingida).
* `exibeTabuleiro()`, que usa o mesmo princípio, porém possui regras para saber o que imprimir, dependendo da situação do tabuleiro. 
* `darTiro()` o jogador escolhe onde atirar, ele recebe dois parâmetros *(linha e coluna)* que são passados pela classe principal, o método então analisa a posição no tabuleiro e, dependendo do caso, substitui o valor do elemento do array por 1(navio atingido) ou 2(água atingida).

#### Para todas as classes de navios
* A leitura de dados é feita na própria classe do navio através do método `consultaUsuario()`, que lê os atributos linha e coluna separadamente, como o primeiro elemento do array é [0,0], sempre que o usuário digita um número, o método decrementa os atributos uma vez, para que a localização seja a correta.

#### Nas outras classes, com exceção de `Submarino`
- `setDirecao()` lê um String, uma letra que representa a direção que o navio deve se posicionar (Norte, Sul, Leste ou Oeste), e armazena em um atributo da classe que será usado no método `bussola()`
- `bussola()` responsável por incrementar ou decrementar linha ou coluna, para que os navios preencham o número de casas correspondente ao seu tamanho, que é predefinido na programação.

Há ainda a classe BatalhaException, porém não foi possível implementá-la no programa.
