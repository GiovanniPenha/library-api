# 📚 Library API

API REST para gerenciamento de bibliotecas desenvolvida com Java e Spring Boot.

Este projeto faz parte do meu portfólio de desenvolvimento backend e tem como objetivo demonstrar conhecimentos em construção de APIs REST, persistência de dados, banco de dados relacionais e boas práticas de desenvolvimento utilizando o ecossistema Spring.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- Bean Validation
- Maven
- Git
- GitHub

---

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java 21
- PostgreSQL
- Git

### 1. Clone o repositório

```bash
git clone <https://github.com/GiovanniPenha/library-api>
cd library-api
```

### 2. Configure o banco de dados

Crie um banco de dados PostgreSQL chamado `library_db`.

Configure a variável de ambiente `DB_PASSWORD` com a senha do usuário PostgreSQL utilizado pela aplicação.

### 3. Execute a aplicação

No Windows, utilizando o Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

### 4. Execute os testes

No Windows:

```powershell
.\mvnw.cmd test
```

No Linux ou macOS:

```bash
./mvnw test
```

## 📖 Documentação da API

A API disponibiliza documentação interativa por meio do Swagger UI.

Com a aplicação em execução, acesse:

http://localhost:8080/swagger-ui/index.html

Pela interface, é possível consultar os endpoints disponíveis, visualizar os parâmetros e testar as requisições HTTP.


## 📋 Funcionalidades

### Implementadas
- Cadastro e consulta de autores
- Cadastro, consulta, atualização e exclusão de livros
- Associação de livros a autores
- Validação dos dados recebidos pela API
- Tratamento padronizado de exceções
- Verificação de ISBN duplicado
- Testes automatizados para a camada de serviço

### Planejadas
- Cadastro e gerenciamento de usuários
- Sistema de empréstimos e devoluções
- Consulta de empréstimos

---

## 🛣️ Roadmap

## 🛣️ Roadmap

- [x] Configuração inicial do projeto
- [x] Configuração do PostgreSQL
- [x] Configuração do Git e GitHub
- [x] Modelagem das entidades Author e Book
- [x] CRUD de autores
- [x] CRUD de livros
- [x] Validação de dados e tratamento de exceções
- [x] Testes automatizados da camada de serviço
- [ ] CRUD de usuários
- [ ] Sistema de empréstimos e devoluções
- [ ] Docker
- [ ] Deploy

---

## 👨‍💻 Autor

Giovanni Penha