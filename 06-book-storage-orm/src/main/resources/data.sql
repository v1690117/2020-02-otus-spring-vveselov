INSERT INTO genres
VALUES (1, 'drama');
INSERT INTO genres
VALUES (2, 'comedy');
INSERT INTO genres
VALUES (3, 'science');
INSERT INTO genres
VALUES (4, 'scifi');
INSERT INTO genres
VALUES (5, 'documental');
INSERT INTO genres
VALUES (6, 'horror');
INSERT INTO genres
VALUES (7, 'manual');
INSERT INTO genres
VALUES (8, 'software development');
INSERT INTO genres
VALUES (9, 'opposition');
INSERT INTO genres
VALUES (10, 'testing');

INSERT INTO authors
VALUES (1, 'Fowler', 'Martin');
INSERT INTO authors
VALUES (2, 'Bugaenko', 'Egor');
INSERT INTO authors
VALUES (3, 'Nystrom', 'Robert');
INSERT INTO authors
VALUES (4, 'Gamma', 'Erich');
INSERT INTO authors
VALUES (5, 'Johnson', 'Ralph');
INSERT INTO authors
VALUES (6, 'Vlissides', 'John');
INSERT INTO authors
VALUES (7, 'Freeman', 'Eric');
INSERT INTO authors
VALUES (8, 'Sierra', 'Kathy');
INSERT INTO authors
VALUES (9, 'Bates', 'Bert');
INSERT INTO authors
VALUES (10, 'Martin', 'Robert');
INSERT INTO authors
VALUES (11, 'Bloch', 'Joshua');
INSERT INTO authors
VALUES (12, 'West', 'David');
INSERT INTO authors
VALUES (13, 'Eckel', 'Bruce');
INSERT INTO authors
VALUES (14, 'Rice', 'David');
INSERT INTO authors
VALUES (15, 'Foemmel', 'Matthew');
INSERT INTO authors
VALUES (16, 'Hieatt', 'Edward');
INSERT INTO authors
VALUES (17, 'Mee', 'Robert');
INSERT INTO authors
VALUES (18, 'Stafford', 'Randy');
INSERT INTO authors
VALUES (19, 'Unkown', 'Unkown');
INSERT INTO authors
VALUES (20, 'Helm', 'Richard');
INSERT INTO authors
VALUES (21, 'Robson', 'Elisabeth');
INSERT INTO authors
VALUES (22, 'Beck', 'Kent');
INSERT INTO authors
VALUES (23, 'Roberts', 'Don');
INSERT INTO authors
VALUES (24, 'Test', 'Test');

INSERT INTO books
VALUES (1, 'Elegant Objects', ' ... Elegant Objects ...', '2016');
INSERT INTO books
VALUES (2, 'Elegant Objects (Volume 2)', ' ... Elegant Objects (Volume 2) ...', '2017');
INSERT INTO books
VALUES (3, 'Code Ahead: Volume 1', ' ... Code Ahead: Volume 1 ...', '2018');
INSERT INTO books
VALUES (4, 'Game Programming Patterns', ' ... Game Programming Patterns ...', '2014');
INSERT INTO books
VALUES (5, 'Design Patterns: Elements of Reusable Object-Oriented Software',
        ' ... Design Patterns: Elements of Reusable Object-Oriented Software ...', '1994');
INSERT INTO books
VALUES (6, 'Head First Design Patterns', ' ... Head First Design Patterns ...', '2004');
INSERT INTO books
VALUES (7, 'Patterns of Enterprise Application Architecture',
        ' ... Patterns of Enterprise Application Architecture ...', '2002');
INSERT INTO books
VALUES (8, 'Clean Architecture', ' ... Clean Architecture ...', '2017');
INSERT INTO books
VALUES (9, 'Clean Code: A Handbook of Agile Software Craftsmanship',
        ' ... Clean Code: A Handbook of Agile Software Craftsmanship ...', '2008');
INSERT INTO books
VALUES (10, 'Effective Java Programming Language Guide', ' ... Effective Java Programming Language Guide ...', '2001');
INSERT INTO books
VALUES (11, 'Refactoring: Improving the Design of Existing Code',
        ' ... Refactoring: Improving the Design of Existing Code ...', '1999');
INSERT INTO books
VALUES (12, 'Thinking in Java', ' ... Thinking in Java ...', '2006');
INSERT INTO books
VALUES (13, 'Object Thinking', ' ... Object Thinking ...', '2004');

INSERT INTO books_authors
VALUES (NULL, 1, 2);
INSERT INTO books_authors
VALUES (NULL, 2, 2);
INSERT INTO books_authors
VALUES (NULL, 3, 2);
INSERT INTO books_authors
VALUES (NULL, 4, 3);
INSERT INTO books_authors
VALUES (NULL, 5, 4);
INSERT INTO books_authors
VALUES (NULL, 5, 5);
INSERT INTO books_authors
VALUES (NULL, 5, 6);
INSERT INTO books_authors
VALUES (NULL, 5, 20);
INSERT INTO books_authors
VALUES (NULL, 6, 7);
INSERT INTO books_authors
VALUES (NULL, 6, 8);
INSERT INTO books_authors
VALUES (NULL, 6, 9);
INSERT INTO books_authors
VALUES (NULL, 6, 21);
INSERT INTO books_authors
VALUES (NULL, 7, 1);
INSERT INTO books_authors
VALUES (NULL, 7, 14);
INSERT INTO books_authors
VALUES (NULL, 7, 15);
INSERT INTO books_authors
VALUES (NULL, 7, 16);
INSERT INTO books_authors
VALUES (NULL, 7, 17);
INSERT INTO books_authors
VALUES (NULL, 7, 18);
INSERT INTO books_authors
VALUES (NULL, 8, 10);
INSERT INTO books_authors
VALUES (NULL, 9, 10);
INSERT INTO books_authors
VALUES (NULL, 10, 11);
INSERT INTO books_authors
VALUES (NULL, 11, 1);
INSERT INTO books_authors
VALUES (NULL, 11, 22);
INSERT INTO books_authors
VALUES (NULL, 11, 23);
INSERT INTO books_authors
VALUES (NULL, 12, 13);
INSERT INTO books_authors
VALUES (NULL, 13, 12);

INSERT INTO books_genres
VALUES (NULL, 1, 8);
INSERT INTO books_genres
VALUES (NULL, 1, 9);
INSERT INTO books_genres
VALUES (NULL, 2, 8);
INSERT INTO books_genres
VALUES (NULL, 2, 9);
INSERT INTO books_genres
VALUES (NULL, 3, 8);
INSERT INTO books_genres
VALUES (NULL, 3, 1);
INSERT INTO books_genres
VALUES (NULL, 4, 8);
INSERT INTO books_genres
VALUES (NULL, 4, 7);
INSERT INTO books_genres
VALUES (NULL, 5, 8);
INSERT INTO books_genres
VALUES (NULL, 5, 6);
INSERT INTO books_genres
VALUES (NULL, 6, 8);
INSERT INTO books_genres
VALUES (NULL, 6, 7);
INSERT INTO books_genres
VALUES (NULL, 6, 2);
INSERT INTO books_genres
VALUES (NULL, 7, 8);
INSERT INTO books_genres
VALUES (NULL, 7, 6);
INSERT INTO books_genres
VALUES (NULL, 8, 8);
INSERT INTO books_genres
VALUES (NULL, 9, 8);
INSERT INTO books_genres
VALUES (NULL, 10, 8);
INSERT INTO books_genres
VALUES (NULL, 11, 8);
INSERT INTO books_genres
VALUES (NULL, 12, 8);
INSERT INTO books_genres
VALUES (NULL, 13, 8);

INSERT INTO book_comments
VALUES (NULL, 2, 'This is the book');
INSERT INTO book_comments
VALUES (NULL, 3, 'This is another book');