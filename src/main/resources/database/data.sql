delete from User WHERE userID = 458945474;

DROP DATABASE IF EXISTS Schoolmanagement;
CREATE DATABASE Schoolmanagement;

USE Schoolmanagement;

CREATE TABLE User (
     userID BIGINT(10) NOT NULL AUTO_INCREMENT,
     firstName VARCHAR(50) NOT NULL,
     lastName VARCHAR(50) NOT NULL,
     gender VARCHAR(10) NOT NULL,
     dateOfBirth DATE NOT NULL,
     age INT NOT NULL,
     phoneNum VARCHAR(60) NOT NULL,
     email VARCHAR(60) NOT NULL,

     PRIMARY KEY (userID)
) ENGINE = INNODB;

CREATE TABLE Authenticate (
    authID BIGINT(8) NOT NULL AUTO_INCREMENT,
    userID BIGINT(10) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(10) NOT NULL,

    PRIMARY KEY (authID)
) ENGINE = INNODB;

CREATE TABLE Role (
    roleID BIGINT(10) NOT NULL AUTO_INCREMENT,
    userRoleID BIGINT(8) NOT NULL ,
    role VARCHAR(15) NOT NULL,

    PRIMARY KEY (roleID)

) ENGINE = INNODB;


CREATE TABLE Address (
    userAID BIGINT(10) NOT NULL AUTO_INCREMENT,
    userID BIGINT(10) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipCode INT NOT NULL,

    PRIMARY KEY(userAID)
) ENGINE = INNODB;

INSERT INTO User VALUES (
     78231679,
     'Mohamed',
     'Keita',
     'Male',
     '1995-01-01',
     26,
     5404160017,
    'keitamohamed251@gmail.com'
);

INSERT INTO Authenticate VALUES (
      78231679,
      78231679,
      'keitamohamed',
      '!2Mohamed'
);

INSERT INTO Role VALUES (
     7912311,
    78231679,
    'Admin'
);

INSERT INTO Address VALUES (
    782351672,
    78231679,
    '1340 King William RD',
    'Charlotte',
    'NC',
    24016
);

ALTER TABLE Authenticate ADD FOREIGN KEY (userID) REFERENCES User(userID);
ALTER TABLE Address ADD FOREIGN KEY (userID) REFERENCES User(userID);
ALTER TABLE role ADD FOREIGN KEY (userRoleID) REFERENCES Authenticate(authID);


