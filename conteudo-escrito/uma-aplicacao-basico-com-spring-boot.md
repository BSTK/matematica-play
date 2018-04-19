# Matemática Play - Um estudo de caso

Depois de apresentar todas as ferramentas que iremos utilizar para construir nosso aplicativo **Matemática Play**, é hora de colocarmos a mão na massa.
Vamos seguir uma abordagem **iterativa e incremental orientada a requisitos**, onde partiremos de uma simples aplicação monolítica até chegarmos a glória dos micro-serviços.

## Criando o projeto

Como estamos utilizando o **Spring Boot**, vamos usar as facilidades que o Spring nos fornece. Eu estou utilizando o [STS - Spring Tool Suite](https://spring.io/tools/sts/all) mas caso você use o Eclipse ou IntelliJ, podemos usar um serviços do Spring para gerar a estrutura do nosso projeto, basta acessar  [Spring Initializer](http://start.spring.io/) e preencher os campos conforme imagem abaixo :

> COLOCAR A UMAGEM : spring-initializer.png

Logo em seguida clique no botão **Generate Project** para fazer o download do projeto :
> COLOCAR A IMAGEM : spring-initializer-gerar-projeto.png

Após o download, descompacte o arquivo zip no seu workspace e importe o projeto em sua IDE. A estrutura do projeto importado deve se parecer como imagem abaixo :
> COLOCAR IMAGEM : estrutura-do-projeto.png

## Pronto é quando está no ar !

Você acabou de importar o projeto na sua IDE favorita e olha que surpresa, não temos um erro ! Será que o projeto funciona ?
Antes de tudo vamos criar um pacote chamado : 
> com.kuiiz.matematicaplay.operacao.web

e nele criarmos uma classe de nome : *HomeController*. Esta classe será um controlador REST, para isso devemos anotar com a annotation : *@RestController*. Feito isso, crie o método : *home* que devolverá apenas uma string com a mensagem:   *Matemática Play (-: *.

O código completo da classe ficará :
```java
@RestController
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "<h1> Matemática Play (-: </h1>";
	}
}
```

Agora que temos um endpoint válido para receber requisições, vamos iniciar nosso aplicativo e ve-lo funcionando. 
Na pasta raiz do projeto, execute o seguinte comando : 
```java
./mvnw spring-boot:run
```
> Nota 1: Eu estou utilizando o Linux, caso você esteja com Windows, digite apenas :  mvnw spring-boot:run sem o ./
> 
> Nota 2: O arquivo mvnw que veio junto com o projeto é um **wrapper do maven**, assim não sendo necessário que você tenha o maven instalado em sua máquina.

Agora acesse *http://localhost:8080/* e você deverá ver a seguinte tela :
> COLOCAR A IMAGEM : tela-inicial-configuraaoo-pronta.png

## User Story - Requisitos do usuário

Vamos usar o conceito de **user story (história de usuário)** para guiar o desenvolvimento do app. Se você nunca ouviu falar no termo **user story**, deixo aqui dois artigos ([O que é a User Story?](http://www.knowledge21.com.br/sobreagilidade/user-stories/o-que-e-user-story/) e [Como é a User Story?](http://www.knowledge21.com.br/sobreagilidade/user-stories/como-e-user-story/)) explicando o conceito de forma bem detalhada. Uma explicação rápida seria : **user story** é uma forma simples que busca descrever a necessidade do usuário. 

## User Story 1
> Como usuário do aplicativo, quero apresentar uma operação matemática (soma, subtração, multiplicação, divisão) aleatória para que eu possa resolve-lá e que não seja muito fácil.

Então precisamos implementar o requisito do usuário, vamos dividir em pequenas tarefas :
 - Criar um serviço básico com a lógica de negócios. 
 - Criar um endpoint básico da API para acessar esse serviço (API REST). 
 - Criar uma página da Web básica para solicitar aos usuários que resolvam a operação.




```java

```
