DROP DATABASE IF EXISTS book_lend_system_HieuPNM;

CREATE DATABASE book_lend_system_HieuPNM;

USE book_lend_system_HieuPNM;

CREATE TABLE User (
    userId INT NOT NULL CHECK (userId <> 0),
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    DOB DATE NOT NULL,
    createdDate DATE NOT NULL,
    PRIMARY KEY (userId)
);

CREATE TABLE Book (
    bookId INT NOT NULL,
    title VARCHAR(20) NOT NULL,
    author VARCHAR(20) NOT NULL,
    createdDate DATE NOT NULL,
    isLend BOOLEAN NOT NULL DEFAULT FALSE,
    lendDate DATE,
    returnDate DATE,
    userId INT,
    PRIMARY KEY (bookId),
    FOREIGN KEY (userId)
        REFERENCES User (userId)
);

DROP VIEW IF EXISTS v_user_lending_status;

CREATE VIEW v_user_lending_status AS
    SELECT 
        userId,
        COUNT(bookId) AS bookLend,
        COUNT(IF(lendDate >= returnDate, 1, NULL)) AS bookOverdue
    FROM
        book
    GROUP BY userId;


DROP VIEW IF EXISTS v_user_stats;
CREATE VIEW v_user_stats AS
    (SELECT 
        A.userId,
        firstname,
        lastname,
        DOB,
        createdDate,
        bookLend,
        bookOverdue
    FROM
        user AS A
            LEFT JOIN
        v_user_lending_status AS B ON A.userId = B.userId)
