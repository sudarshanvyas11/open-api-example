CREATE TABLE IF NOT EXISTS
library.author
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL
);