# Modelo de Domínio

Este documento descreve as entidades, os atributos, os relacionamentos e as
regras iniciais da Library API.

## Author

Representa uma pessoa responsável pela autoria de um ou mais livros.

### Atributos

- `id`: identificador interno e único.
- `name`: nome do autor.

### Regras

- O nome é obrigatório.
- O nome não pode estar vazio.
- Um autor pode estar relacionado a vários livros.

---

## Book

Representa um livro cadastrado na biblioteca.

### Atributos

- `id`: identificador interno e único.
- `title`: título do livro.
- `isbn`: identificador da edição do livro.
- `genre`: gênero literário.
- `totalCopies`: quantidade total de exemplares.
- `availableCopies`: quantidade atualmente disponível.
- `author`: autor relacionado ao livro.

### Regras

- O título é obrigatório.
- O ISBN é obrigatório e deve ser único.
- O gênero é obrigatório.
- O livro deve possuir um autor.
- A quantidade total deve ser maior que zero.
- A quantidade disponível não pode ser negativa.
- A quantidade disponível não pode ser maior que a quantidade total.

---

## LibraryUser

Representa um usuário cadastrado para utilizar os serviços da biblioteca.

### Atributos

- `id`: identificador interno e único.
- `name`: nome do usuário.
- `userCode`: código único gerado ou utilizado pela biblioteca.
- `email`: endereço de e-mail do usuário.

### Regras

- O nome é obrigatório.
- O código de usuário é obrigatório e único.
- O e-mail é obrigatório e deve ser único.
- Um usuário pode possuir vários empréstimos.

---

## Loan

Representa um empréstimo de um livro realizado por um usuário.

### Atributos

- `id`: identificador interno e único.
- `user`: usuário responsável pelo empréstimo.
- `book`: livro emprestado.
- `loanDate`: data em que o empréstimo foi realizado.
- `dueDate`: data prevista para devolução.
- `returnDate`: data real da devolução.

### Regras

- Cada nova retirada gera um novo empréstimo.
- O usuário deve estar cadastrado no sistema.
- O livro deve estar cadastrado no sistema.
- O livro precisa possuir exemplares disponíveis.
- Um empréstimo devolvido não pode ser devolvido novamente.
- Ao realizar o empréstimo, a quantidade disponível diminui em uma unidade.
- Ao registrar a devolução, a quantidade disponível aumenta em uma unidade.
- Um usuário poderá possuir no máximo três empréstimos ativos.

---

## Relacionamentos

```text
Author 1 -------- N Book

Book 1 ---------- N Loan

LibraryUser 1 --- N Loan