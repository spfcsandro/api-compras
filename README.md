# Sitema de Compras Online

Sistema de compras online baseado em REST API usando Java Spring, Spring Boot, Hibernate ORM com MySQL, Spring Fox (Swagger API docs) e JWT.

## Instalar o Banco de dados Mysql com usuário=root e senha=123456

## REST API Endpoints
Todos os parâmetros de entrada e saída são no Formato JSON.

**Para abrir a documentação da API Swagger, navegue até http://localhost:8080/swagger-ui.html**

**1º Precisa ser feito o login para pegar o token.**
**2º Na hora de chamar o método precisa colocar o tokens em uma parâmetro chamado "Authorization".**

**OBS: Não esquecer de preencher o input com (Bearer + token)**

```
/v1/usuarios/login
  POST / - Login usando email: admin@gmail.com e password:123456 (Criado automáticamente).
  
/v1/usuarios  
  GET / - Lista os usuários.
  GET /{id} - Visualiza um usuário.
  POST / - Salva um usuário com ROLE de ADMINISTRADOR.
  PUT /{id} - Atualiza um usuário.
  DELETE / {id} - Deleta um usuário.
  
/v1/usuarios/cadastrar-cliente
  POST /{nome}, {email} e {senha} - Salvar um usuário(Cliente) com ROLE de CLIENTE.

Segue abaixo a ordem dos cadastros:  
 
 /v1/cupons
  GET / - Lista os cupons.
  GET /{id} - Visualiza um cupom.
  POST / - Salva um cupom.
  PUT /{id} - Atualiza um cupom.
  DELETE / {id} - Deleta um cupom.
  
 /v1/categoria
  GET / - Lista as categorias.
  GET /{id} - Visualiza uma categoria.
  POST / - Salva uma categoria.
  PUT /{id} - Atualiza uma categoria.
  DELETE / {id} - Deleta uma categoria.
  
  /v1/produtos  
  GET / - Lista os produtos.
  GET /{id} - Visualiza um produto.
  POST / - Salva um produto.
  PUT /{id} - Atualiza um produto.
  DELETE / {id} - Deleta um produto.