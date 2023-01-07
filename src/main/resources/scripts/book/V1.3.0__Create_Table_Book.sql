CREATE TABLE IF NOT EXISTS
book
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(100) NOT NULL,
    genre           VARCHAR(20) NOT NULL,
    author_id       INT,
    publisher_id    INT,
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);