CREATE TABLE IF NOT EXISTS
library.publisher
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    website     VARCHAR(100) NOT NULL,
    address_id  INT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);