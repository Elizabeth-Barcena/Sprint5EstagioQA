# Plano de testes

## 1. Introdução
1.1 Objetivos
    
    Esse documento tem o objetivo de planejar a execução de testes da API Server Rest  de acordo com os critérios de aceitação do projeto. Além da elaboração dos testes, haverá a descrição dos recursos que devem ser utilizados e os possíveis riscos ao implementar os testes.

1.2 O sistema Server Rest

    O sistema Server Rest é uma API REST utilizada para fins de sistemas de e-commerce. 
1.3 Escopo

    Os testes realizados para esses sistemas são os testes funcionais, onde será verificado se o sistema está realizando as aplicações estabelecidas.


## 2. Itens-alvo dos testes

2.1 Funcionalidades ou Módulos a serem testados
    
    O CRUD de usuários: 
    - Listagem de usuários 
    - Cadastro de usuários
    - Busca de usuário por ID
    - Excluir usuário
    - Editar usuário

    Login
    - Login do usuário

    O CRUD de produtos
    - Listagem de produtos 
    - Cadastro de produtos
    - Busca de produtos por ID
    - Excluir produtos
    - Editar produtos

2.2 Recursos necessários

    Os recursos necessários para realizar os testes são:
    - Energia elétrica
    - Computador que suporte rodar o Server Rest e Postman
    - Internet estável

2.3 Riscos
Os possíveis riscos:

    1. Energia elétrica instável

    Possível solução: Obter um nobreak no local da aplicação

    2. Internet instável

    Possível solução: Obter um modem 4G/5G
    3. Computador com problemas

    Possível solução: Obter um notebook ou computador reserva

## 3. Técnicas e tipos de teste
3.1 Testes funcionais
    
    Objetivo: Os testes funcionais tem como objetivo encontrar erros durante a execução do sistema.

    Técnica utilizada: Serão feitos testes funcionais para verificar se a aplicação está apta para realizar as funções pre-estabelecidas. Esse teste deverá ser automatizado através de um framework de testes.

    Framework utilizado: Postman

## 4. Criterios de aceitação para o testes
### Critérios de usuário

    001- Os vendedores (usuários) deverão possuir os campos NOME, E-MAIL, PASSWORD e ADMINISTRADOR;

        
    002- Não deverá ser possível fazer ações e chamadas para usuários inexistentes;

        
    003- Não deve ser possível criar um usuário com e-mail já utilizado;

        
    004- Caso não seja encontrado usuário com o ID informado no PUT, um novo usuário deverá ser criado;

        
    005- Não deve ser possível cadastrar usuário com e-mail já utilizado utilizando PUT;

        
    006- Não deverá ser possível cadastrar usuários com e-mails de provedor gmail e hotmail;

        
    007- Os e-mails devem seguir um padrão válido de e-mail para o cadastro;

        
    008- As senhas devem possuír no mínimo 5 caracteres e no máximo 10 caracteres;

### Critérios de Login
    009- Usuários não cadastrados não deverão conseguir autenticar;

        
    010- Usuários com senha inválida não deverão conseguir autenticar;

        
    011- No caso de não autenticação, deverá ser retornado um status code 401 (Unauthorized);

        
    012- Usuários existentes e com a senha correta deverão ser autenticados;

        
    013- A autenticação deverá gerar um token Bearer;

        
    014- A duração da validade do token deverá ser de 10 minutos;

### Critérios de Produtos
    015- Usuários não autenticados não devem conseguir realizar ações na rota de Produtos;

        
    016- Não deve ser possível realizar o cadastro de produtos com nomes já utilizados;

        
    017- Não deve ser possível excluir produtos que estão dentro de carrinhos (dependência API Carrinhos);

        
    018- Caso não exista produto com o o ID informado na hora do UPDATE, um novo produto deverá ser criado;

        
    019- Produtos criados através do PUT não poderão ter nomes previamente cadastrados;

## 5. Programação dos testes
### Testes de usuário
5.1 Criar o usuário através do POST com um dos campos em branco de cada vez.
    
    Resultado esperado: Os vendedores (usuários) deverão possuir todos os campos NOME, E-MAIL, PASSWORD e ADMINISTRADOR preenchidos;

5.2 Verificar a resposta do sistema fazendo chamada de um usuário inexistente através de um GET, PUT e DELETE.

    Resultado esperado: Não deverá ser possível fazer ações e chamadas para usuários inexistentes;

5.3 Criar um novo usuário utilizando o POST com um email já existente.

    Resultado esperado: Não deve ser possível criar um usuário com e-mail já utilizado;

5.4 Criar um usuário com um ID não existente através do PUT 
    

    Resultado esperado:  Caso não seja encontrado usuário com o ID informado no PUT, um novo usuário deverá ser criado;

5.5 Criar um novo usuário utilizando o PUT, porém com mesmo email já existente.

    Resultado esperado:  Não deve ser possível cadastrar usuário com e-mail já utilizado utilizando PUT.

5.6 Criar um novo usuário com provedor de gmail e hotmail, utilizando o POST

    Resultado esperado: Não deverá ser possível cadastrar usuários com e-mails de provedor gmail e hotmail;

5.7 Criar um usuário com email não válido utilizando o POST

   
    Resultado esperado:  Os e-mails devem seguir um padrão válido de e-mail para o cadastro;

5.8 Criar um usuário com uma senha abaixo de 5 caracteres e acima de 10 caracteres.

    Resultado esperado: As senhas devem possuír no mínimo 5 caracteres e no máximo 10 caracteres.

5.9 Listar todos os usuários com GET

    Resultado: Os usuários devem ser mostrados

5.10 Listar usuário por ID com GET
  
    Resultado: O usuário deve ser mostrado

5.11 Listar usuário inexistente com GET

    Resultado: Deve dar erro

### Testes de Login
 5.12 Tentar logar com usuário não existente
    

    Resultado esperado: Usuário inexistente não deve conseguir autenticar

5.10 Fazer login com senha incorreta

    Resultado esperado: Usuários com senha inválida não deverão conseguir autenticar e  deverá ser retornado um status code 401 (Unauthorized);

5.11 Logar com um usuário e senha válidos
    
    Resultado esperado: Usuários existentes e com a senha correta deverão ser autenticados e deverá gerar um token Bearer;

5.12 Logar com senha vazia somente com espaço
    
    Resultado esperado: O usuário não deve conseguir autenticar 

5.13 Logar sem email apenas com senha

    Resultado esperado: O usuário não deve conseguir autenticar 

5.14 Logar sem senha apenas email

    Resultado esperado: O usuário não deve conseguir autenticar 

### Testes de produto

5.14 Cadastrar produtos

    Resultado esperado: Produtos devem ser cadastrados

5.15 Cadastrar produtos com nome já utilizados
    
    Resultado esperado: Usuário não deve conseguir cadastrar produtos com mesmo nome

5.16 Cadastrar produto sem nome

    Resultado esperado: Usuário não deve cadastrar produto sem nome

5.17  Cadastrar produto com preço nulo

    Resultado esperado: Usuário não deve cadastrar produto com preço nulo

5.18 Cadastrar produto sem descrição

    Resultado esperado: Usuário não deve cadastrar produto sem descrição

5.19 Cadastrar produto com quantidade nula

    Resultado esperado: Usuário não deve cadastrar produto com quantidade nula

5.20 Cadastrar produto sem fazer token

    Resultado esperado: Usuário não deve cadastrar produto sem fazer token

5.21 Cadastrar produtos com todos os campos nulos

    Resultado esperado: Usuario não deve cadastrar produtos com todos os campos nulos
    
5.22 Listar produtos cadastrados

    Resultado esperado: Todos os produtos cadastrados sejam listados
    
5.23 Listar produto por id

    Resultado esperado: listar o produto com id

5.24 Listar produto não existente

    Resultado esperado: Nenhum produto deve ser encontrado
    
5.25 Editar dados do produto
    
    Resultado esperado: Produto deve ser atualizado com novos dados

5.26 Editar produtos sem token

    Resultado esperado: Produto não pode ser editado sem o acesso token

5.27 Criar um novo produto com PUT utilizando ID inexistente

    Resultado esperado: Um novo produto devera ser criado através do PUT com id não existente

5.28 Excluir produto

    Resultado esperado: Produto deve ser excluido

5.29 Excluir produto sem fazer login

    Resultado esperado: Produto não pode ser excluido sem login

5.30 Excluir produto que está dentro do carrinho

    Resultado esperado: Produto que está dentro do carrinho não pode ser excluido

5.31 Excluir produto com usuário que não é administrador

    Resultado esperado : Usuário que não é administrador não pode excluir produtos

### Testes de carrinho
    5.32 Listar todos os carrinhos cadastrados

    5.33 Cadastrar carrinho

    5.34 Cadastrar carrinho com quantidade 0 de produtos

    5.35 Cadastrar carrinho sem token

    5.36 Cadastrar 2 carrinhos com 1 usuário

    5.37 Ver carrinho por ID
    
    5.38 Ver carrinho por ID inexistente

    5.39 Excluir carrinho ao concluir uma compra

    5.40 Excluir carrinho sem token ao concluir uma compra
    
    5.41 Excluir carrinho ao cancelar uma compra e devolver produto pro estoque

    5.42 Excluir carrinho sem token ao cancelar uma compra e devolver produto pro estoque

## 7. Cobertura de teste 
Foram criados 8 testes para os 8 critérios de aceite. Todos devem ser automatizados para cobrir 100% de testes.

## 8. Como os resultados do teste serão divulgados

Os resultados dos testes serão fornecidos no repositório do gitlab:

 https://gitlab.com/compassouol-istudio-qa/pbjavat01/area-desenvolvimento/pb-java-mariaelizabeth

 TestesExecutados: Clique [aqui](/com.aula.pb.inicio/)