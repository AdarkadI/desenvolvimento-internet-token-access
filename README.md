# API Petshop (projeto-token-pt3)

Este projeto consiste em um conjunto de APIs RESTful para gerenciar serviços veterinários. Ele é composto por quatro APIs principais: autenticação, cadastro de animais, cadastro de pessoas e gerenciamento de serviços, todas protegidas por autenticação via JWT (JSON Web Token). O banco de dados utilizado é o MongoDB.

## Como rodar o sistema

1. Certifique-se de que o MongoDB esteja instalado e rodando na máquina local na porta padrão 27017.
2. Configure o arquivo `application.properties` com as seguintes propriedades básicas:
   - `jwt.secret` com a chave secreta para assinatura dos tokens JWT.
   - Configurações do MongoDB (`spring.data.mongodb.host`, `spring.data.mongodb.port`, `spring.data.mongodb.database`).
   - Credenciais de usuário para autenticação (`credential.email` e `credential.senha`).
3. Compile e execute cada módulo da API separadamente, começando pela Auth API para gerar os tokens JWT.
4. Utilize o token JWT gerado para autenticar as demais APIs (Animais, Pessoas e Serviços).
5. Cada API roda em sua própria aplicação Spring Boot e pode ser executada pelo comando `mvn spring-boot:run` ou via IDE.

## Endpoints disponíveis

### Auth API
- `POST /auth/login`  
  Recebe email e senha, retorna um token JWT para autenticação.

### Pessoa API
- `POST /pessoas`  
  Cadastra uma pessoa com nome, CPF, telefone e endereço.
- `GET /pessoas`  
  Lista todas as pessoas cadastradas.
- `GET /pessoas/{id}`  
  Busca uma pessoa pelo seu ID.

### Animal API
- `POST /animais`  
  Cadastra um animal com nome, espécie, raça, idade e associa a uma pessoa pelo ID.
- `GET /animais`  
  Lista todos os animais cadastrados.
- `GET /animais/{id}`  
  Busca um animal pelo seu ID.

### Serviço API
- `POST /servicos`  
  Registra um serviço prestado a um animal, com tipo, data, valor, status e o ID do animal atendido.
- `GET /servicos`  
  Lista todos os serviços registrados.
- `GET /servicos/{id}`  
  Busca um serviço pelo seu ID.

## Como gerar e usar o token JWT

- Para obter um token JWT, faça uma requisição POST para `/auth/login` enviando o email e senha cadastrados.
- A resposta conterá o token JWT.
- Para acessar os endpoints protegidos das APIs de pessoas, animais e serviços, inclua no cabeçalho da requisição HTTP o seguinte campo:  
  `Authorization: Bearer <token_jwt_aqui>`
- O token é válido até o tempo configurado na aplicação (configuração do JWT).

## Exemplos de uso das APIs com o Postman

1. Faça uma requisição POST para `/auth/login` com o corpo JSON contendo email e senha para obter o token.
2. Copie o token retornado e configure um cabeçalho `Authorization` com o valor `Bearer <token>`.
3. Para cadastrar uma pessoa, faça um POST para `/pessoas` com JSON contendo nome, CPF, telefone e endereço.
4. Use o ID retornado da pessoa para cadastrar um animal via POST em `/animais` incluindo o campo `pessoaId`.
5. Para registrar um serviço, faça um POST para `/servicos` enviando o tipo do serviço, data, valor, status e o `animalId`.
6. Utilize os métodos GET para consultar os registros cadastrados em cada API.
