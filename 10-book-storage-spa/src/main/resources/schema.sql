DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    genre_id BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(20) NOT NULL,
    CONSTRAINT unique_name
        UNIQUE (name)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    book_id    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    annotation VARCHAR(255),
    year       VARCHAR(4)
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    author_id  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    last_name  VARCHAR(20) NOT NULL,
    first_name VARCHAR(20)
);

DROP TABLE IF EXISTS books_genres;
CREATE TABLE books_genres
(
    id       BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT genres_genre_id_fk
        FOREIGN KEY (genre_id)
            REFERENCES genres (genre_id),
    CONSTRAINT books_book_id_fk
        FOREIGN KEY (book_id)
            REFERENCES books (book_id)
);

DROP TABLE IF EXISTS books_authors;
CREATE TABLE books_authors
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT authors_author_id_fk
        FOREIGN KEY (author_id)
            REFERENCES authors (author_id),
    CONSTRAINT books_authors_books_book_id_fk
        FOREIGN KEY (book_id)
            REFERENCES books (book_id)
);

DROP TABLE IF EXISTS book_comments;
CREATE TABLE book_comments
(
    comment_id BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id    BIGINT       NOT NULL,
    text       VARCHAR(255) NOT NULL,
    CONSTRAINT book_comments_book_id_fk
        FOREIGN KEY (book_id)
            REFERENCES books (book_id)
);