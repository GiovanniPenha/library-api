CREATE TABLE books (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    isbn VARCHAR(20) NOT NULL UNIQUE,

    genre VARCHAR(50) NOT NULL,

    author_id BIGINT NOT NULL,

    CONSTRAINT fk_books_author
        FOREIGN KEY (author_id)
        REFERENCES authors(id)

);