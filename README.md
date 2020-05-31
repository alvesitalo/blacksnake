# Blacksnake

Blacksnake é um remake do jogo *Snake* feito em Java Swing, possui um painel de HighScore, com 3 tipos de cobras jogáveis e mais 4 tipos de frutas para coletar.

## Jogabilidade

Comandos são mostrados em todas as telas para a navegação com as teclas Espaço e Enter.  
No jogo, a Snake atual vai aumentando sua velocidade progressivamente de acordo com a pontuação, inimigos estáticos podem aparecer.  
A parte superior possui a pontuação atual do jogador e a pontuação mais alta já atingida (HighScore).  
No fim do jogo, digite seu nome e a pontuação será acrescentada ao painel de HighScores.

- Frutas coletáveis:

1. **Apple Fruit:** Uma maçã simples, a preferida da Black.
2. **Bomb Fruit:** Pimenta para uma cobra? Qualquer Snake morre se encostar.
3. **Big Fruit:** Comum, a Kitty dá as caras e mostra seu poder.
4. **Decrease Fruit:** A mais rara de ser encontrada, nela a BlackSnake se tranforma na Star.

- Cobras jogáveis:

1. **Black:** Não tem poderes especiais, mas a reputação dela é sempre boa.
2. **Kitty:** Kitty pode colidir com qualquer inimigo e é fofa.  
3. **Star:** Poderosíssima como a espada de uma samurai, a cobra Star pode atravessar as bordas do jogo (e se teletransportar para o outro lado) e ganhar o 
dobro de pontos, mas não pode colidir com um inimigo.

## Requisitos

- [Java JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) (1.8.0) ou superior. 

Verifique sua versão do Java e da JVM no terminal com o comando:  
```java -version```

## Jogar

1. Na pasta blacksnake, compile e digite o seguinte comando no terminal:  
```java -cp bin snake.Game```

2. Após abrir a janela, no menu, tecle Enter para iniciar o jogo
3. Use as setas direcionais do teclado para mover a BlackSnake
4. Em Game Over, tecle Enter para voltar ao menu ou Espaço para jogar outra vez.

Caso algum arquivo seja modificado é necessário compilar o jogo novamente.

## Como compilar

No terminal, entre com o comando abaixo na pasta blacksnake:  
```javac -d bin/ src/snake/*.java```

Se tudo ocorrer bem, nenhuma mensagem será retornada.

## Créditos

Frutas e outros ícones por [FontAwesome](https://fontawesome.com/)
Sons e efeitos adicionais por [ZAPSPLAT](https://www.zapsplat.com)

# EP2 - OO 2019.2 (UnB - Gama)

Turmas Renato e Carla
Data de entrega: 05/11/2019

## Descrição

Para este EP o objetivo será implementar o famoso jogo Snake (também conhecido como "jogo da cobrinha") é um jogo que ficou conhecido por diversas versões cuja versão inicial começou com o jogo Blockade de 1976, sendo feitas várias imitações em vídeo-games e computadores. No fim dos anos 90 foi popularizado em celulares da Nokia que vinham com o jogo já incluso.

O jogador controla uma longa e fina criatura que se arrasta pela tela, coletando comida (ou algum outro item), não podendo colidir com seu próprio corpo ou as "paredes" que cercam a área de jogo. Cada vez que a serpente come um pedaço de comida, seu rabo cresce, aumentando a dificuldade do jogo. O usuário controla a direção da cabeça da serpente (para cima, para baixo, esquerda e direita) e seu corpo segue.

Para este EP o jogo deve conter barreiras em seu cenário, além das especificações abaixo.

## Game Loop

Para o jogo deve ser implementado um game loop. Um game loop é um laço infinito que continua rodando enquanto o jogo estiver sendo executado. O game loop verifica periodicamente os inputs de usuário e realiza as ações requisitadas. Ele deve encerrar com a morte da Snake. Quando o game loop for encerrado deve aparecer uma tela de Game Over.

## Tipos de Snakes

Deve ser implementado ao menos 3 tipos de Snakes:
* **Comum:** A Snake classica, sem habilidades especiais.
* **Kitty:** Essa Snake tem as habilidades de atravessar as barreiras do jogo, mas não pode atravessar as bordas nem a si mesma.
* **Star:** Recebe o dobro de pontos ao comer as frutas.

## Colisões

As Snakes devem colidir com barreiras no interior do jogo, salvo a Snake Kitty que atravessa barreiras. Nenhuma cobra deve atravessar as bordas do jogo. Quando houver algum tipo de colisão, tanto nas barreiras, quanto elas mesmas, a Snake deve morrer.

## Frutas

As frutas são elementos que aparecem aleatoriamente e são os objetivos das Snakes. As frutas devem desaparecer em um tempo especifico e não devem aparecer mais de duas frutas por vez. Devem ser implementados ao menos 4 tipos de frutas:

* **Simple Fruit:** Fruta comum, dá um ponto e aumenta o tamanho da cobra.
* **Bomb Fruit:** Essa fruta deve levar a morte da Snake.
* **Big Fruit:** Dá o dobro de pontos da Simple Fruit e aumenta o tamanho da cobra da mesma forma que a Simple Fruit.
* **Decrease Fruit:** Diminui o tamanho da cobra para o tamanho inicial, sem fornecer nem retirar pontos.

**OBS.:** As frutas podem ser geradas por thread.

## Pontos

Os pontos são calculados de acordo com as frutas coletadas.

**OBS.:** Os pontos podem ser calculados a partir de thread.

## Orientações

O aluno deve escolher se deve implementar thread nos pontos, frutas ou ambos. Lembrando que ter thread faz parte da pontuação do EP.

Quando finalizado, o projeto deverá conter neste README as instruções de execução (comandos, menus, etc) e a lista de dependências (bibliotecas ou pacotes necessários para se executar o software).

## 5. Critérios de Avaliação

 A avaliação será realizada seguindo os seguintes critérios:

|   ITEM    |   COMENTÁRIO  |   VALOR   |
|------------------------|---------------------------------------------------------------------------------------------------------|---------|
|**Game Loop**| A aplicação deve ter implementados o Game Loop do Jogo com tela de Game Over ao final. |    1,0 |
|**Tipos de Snake e Frutas**| A aplicação deve ter implementados os 3 tipos de cobra e 4 tipos de fruta. |   1,0 |
|**Herança**| Utilização de Herança (que faça sentido).  |   1,0 |
|**Polimorfismo**|  No código deve haver, no mínimo, três casos de polimorfismo sendo utilizados.    |   1,0 |
|**Encapsulamento**| Utilização adequada de encapsulamento em todas as classes.    |   1,0 |
|**Modelagem**| Qualidade do código e dos relacionamentos entre classes.  |  0,5 |
|**Thread**| O jogo deve possuir ao menos uma thread.  |  0,5 |
|**Interface**| O programa deve possuir a interface Swing e possuir interação com o usuário |   1,0 |
|**Qualidade do Código**| Utilização de boas práticas como o uso de bons nomes, modularização e organização em geral.    |  1,0 |
|**UML**| O EP deve possuir um diagrama UML em sua documentação |  1,0 |
|**Repositório**|   Utilização correta do repositório, commits claros e frequentes. |   1,0 |
|      |    TOTAL   |   10  |
|**Pontuação Extra**| Possiveis pontos extras:<br/>- Ranking em arquivo com nome do jogador e quantidade de pontos<br/>- Fazer as cobras atravessarem as bordas do jogo aparecendo no outro lado<br/>- Testes automatizados|  0,5 (para cada funcionalidade) |
