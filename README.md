# 📚 Library Management API

API REST para gerenciamento de uma biblioteca, desenvolvida com **Java 21 e Spring Boot**.

O projeto tem como objetivo praticar conceitos de desenvolvimento de APIs REST, persistência de dados, regras de negócio, tratamento de exceções, testes e documentação.

## 🚀 Tecnologias

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Spring Data JPA
- Bean Validation
- Lombok
- Docker
- Swagger / OpenAPI
- JUnit
- Mockito

## 📌 Funcionalidades

### 📖 Livros

- Cadastro de livros
- Atualização de livros
- Exclusão de livros
- Consulta por ID
- Listagem paginada
- Filtro por título
- Filtro por autor
- Filtro por ano de publicação
- Filtro por disponibilidade

### ✍️ Autores

- Cadastro de autores
- Atualização de autores
- Exclusão de autores
- Consulta por ID

### 👤 Usuários

- Cadastro de usuários
- Atualização de usuários
- Exclusão de usuários
- Consulta de usuários

### 📚 Empréstimos

- Realização de empréstimos
- Atualização de empréstimos
- Devolução de livros
- Controle de disponibilidade
- Consulta de empréstimos

## 🧠 Regras de negócio

- Não permitir empréstimo de livro indisponível.
- Ao realizar um empréstimo, diminuir a quantidade disponível.
- Ao devolver um livro, aumentar a quantidade disponível.
- Não permitir devolver um empréstimo mais de uma vez.
- Não permitir empréstimo de livro inexistente.
- Validar a existência do usuário.
- Validar a existência do empréstimo.