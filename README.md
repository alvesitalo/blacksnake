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
Sons e efeitos adicionais por [ZAPSPLAT](https://www.zapsplat.com).
