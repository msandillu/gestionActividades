CREATE TABLE sys.Task (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    creationuser INT,
    creationdate DATETIME
);