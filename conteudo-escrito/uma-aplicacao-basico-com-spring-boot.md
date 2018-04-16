# Matemática Play - Um estudo de caso

Depois de apresentar todas as ferramentas que iremos utilizar para construir nosso aplicativo **Matemática Play**, é hora de colocarmos a mão na massa.
Vamos seguir uma abordagem **iterativa e incremental orientada a requisitos**, onde partiremos de uma simples aplicação monolítica até chegarmos a glória dos micro-serviços.

## User Story - Requisitos do usuário

Vamos usar o conceito de **user story (história de usuário)** para guiar o desenvolvimento do app. Se você nunca ouviu falar no termo **user story**, deixo aqui dois artigos ([O que é a User Story?](http://www.knowledge21.com.br/sobreagilidade/user-stories/o-que-e-user-story/) e [Como é a User Story?](http://www.knowledge21.com.br/sobreagilidade/user-stories/como-e-user-story/)) explicando o conceito de forma bem detalhada. Uma explicação rápida seria : **user story** é uma forma simples que busca descrever a necessidade do usuário. 

## User Story 1
> Como usuário do aplicativo, quero apresentar uma operação matemática (soma, subtração, multiplicação, divisão) aleatória para que eu possa resolve-lá e que não seja muito fácil.

Então precisamos implementar o requisito do usuário, vamos dividir em pequenas tarefas :
 - Criar um serviço básico com a lógica de negócios. 
 - Criar um endpoint básico da API para acessar esse serviço (API REST). 
 - Criar uma página da Web básica para solicitar aos usuários que resolvam o operação.
