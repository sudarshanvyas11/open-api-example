CREATE TABLE IF NOT EXISTS
address
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_line  VARCHAR(100) NOT NULL,
    second_line VARCHAR(100),
    post_code   VARCHAR(20) NOT NULL,
    city        VARCHAR(50) NOT NULL,
    country     VARCHAR(50)
);