CREATE DATABASE loginDB;

USE loginDB;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    fullname VARCHAR(100)
);
