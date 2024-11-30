-- Вставка данных об авторах
INSERT INTO author (name) VALUES ('J.K. Rowling');
INSERT INTO author (name) VALUES ('George Orwell');
INSERT INTO author (name) VALUES ('J.R.R. Tolkien');
INSERT INTO author (name) VALUES ('Agatha Christie');
INSERT INTO author (name) VALUES ('Harper Lee');
INSERT INTO author (name) VALUES ('Jane Austen');
INSERT INTO author (name) VALUES ('Mark Twain');
INSERT INTO author (name) VALUES ('F. Scott Fitzgerald');
INSERT INTO author (name) VALUES ('Ernest Hemingway');
INSERT INTO author (name) VALUES ('Charles Dickens');
INSERT INTO author (name) VALUES ('Leo Tolstoy');
INSERT INTO author (name) VALUES ('Emily Brontë');
INSERT INTO author (name) VALUES ('Herman Melville');
INSERT INTO author (name) VALUES ('J.D. Salinger');
INSERT INTO author (name) VALUES ('William Shakespeare');
INSERT INTO author (name) VALUES ('C.S. Lewis');
INSERT INTO author (name) VALUES ('Franz Kafka');
INSERT INTO author (name) VALUES ('Stephen King');
INSERT INTO author (name) VALUES ('J.R.R. Tolkien');
INSERT INTO author (name) VALUES ('John Steinbeck');
INSERT INTO author (name) VALUES ('Vladimir Nabokov');
INSERT INTO author (name) VALUES ('Isaac Asimov');
INSERT INTO author (name) VALUES ('Ray Bradbury');
INSERT INTO author (name) VALUES ('Aldous Huxley');
INSERT INTO author (name) VALUES ('Kurt Vonnegut');
INSERT INTO author (name) VALUES ('Tolkien');
INSERT INTO author (name) VALUES ('Margaret Atwood');
INSERT INTO author (name) VALUES ('William Faulkner');
INSERT INTO author (name) VALUES ('Dan Brown');
INSERT INTO author (name) VALUES ('Paulo Coelho');

-- Вставка данных о книгах (30 книг)
INSERT INTO book (title, genre, published_year, author_id) VALUES
    ('Harry Potter and the Sorcerers Stone', 'Fantasy', 1997, 1),
    ('1984', 'Dystopian', 1949, 2),
    ('The Hobbit', 'Fantasy', 1937, 3),
    ('Murder on the Orient Express', 'Mystery', 1934, 4),
    ('To Kill a Mockingbird', 'Fiction', 1960, 5),
    ('Pride and Prejudice', 'Romance', 1813, 6),
    ('The Adventures of Huckleberry Finn', 'Adventure', 1884, 7),
    ('The Great Gatsby', 'Fiction', 1925, 8),
    ('The Old Man and the Sea', 'Fiction', 1952, 9),
    ('A Tale of Two Cities', 'Historical Fiction', 1859, 10),
    ('War and Peace', 'Historical Fiction', 1869, 11),
    ('Wuthering Heights', 'Gothic Fiction', 1847, 12),
    ('Moby-Dick', 'Adventure', 1851, 13),
    ('The Catcher in the Rye', 'Fiction', 1951, 14),
    ('Hamlet', 'Tragedy', 1609, 15),
    ('The Chronicles of Narnia', 'Fantasy', 1950, 16),
    ('The Trial', 'Philosophical Fiction', 1914, 17),
    ('The Shining', 'Horror', 1977, 18),
    ('The Lord of the Rings: The Fellowship of the Ring', 'Fantasy', 1954, 19),
    ('The Grapes of Wrath', 'Fiction', 1939, 20),
    ('Lolita', 'Drama', 1955, 21),
    ('Foundation', 'Science Fiction', 1951, 22),
    ('Fahrenheit 451', 'Dystopian', 1953, 23),
    ('Brave New World', 'Dystopian', 1932, 24),
    ('Slaughterhouse-Five', 'Science Fiction', 1969, 25),
    ('The Hobbit', 'Fantasy', 1937, 3),
    ('The Handmaids Tale', 'Dystopian', 1985, 26),
    ('As I Lay Dying', 'Southern Gothic', 1930, 27),
    ('The Da Vinci Code', 'Thriller', 2003, 28),
    ('The Alchemist', 'Adventure', 1988, 29),
    ('The Girl with the Dragon Tattoo', 'Mystery', 2005, 30);
