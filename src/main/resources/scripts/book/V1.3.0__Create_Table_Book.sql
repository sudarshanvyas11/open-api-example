CREATE TABLE IF NOT EXISTS
library.book
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(100) NOT NULL,
    genre           VARCHAR(20) NOT NULL,
    author_id       INT,
    publisher_id    INT,
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);