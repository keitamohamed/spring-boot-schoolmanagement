SET GLOBAL time_zone = '-4:00';

delete
from Address
WHERE userID = 586365955;
delete
from Authenticate
WHERE userAuthID = 586365955;
delete
from User
WHERE userID = 586365955;

DROP DATABASE IF EXISTS Schoolmanagement;

CREATE DATABASE Schoolmanagement;
USE Schoolmanagement;

CREATE TABLE User
(
    userID      BIGINT(10)  NOT NULL AUTO_INCREMENT,
    firstName   VARCHAR(50) NOT NULL,
    lastName    VARCHAR(50) NOT NULL,
    gender      VARCHAR(10) NULL,
    dateOfBirth DATE        NOT NULL,
    age         INT         NULL,
    phoneNum    VARCHAR(60) NOT NULL,
    email       VARCHAR(60) NOT NULL,

#      UNIQUE KEY unique_email(email),

    PRIMARY KEY (userID)
) ENGINE = INNODB;

CREATE TABLE Authenticate
(
    authID          BIGINT(10)  NOT NULL AUTO_INCREMENT,
    userAuthID      BIGINT(10)  NOT NULL,
    username        VARCHAR(50) NOT NULL,
    userRole        VARCHAR(15) NOT NULL,
    password        VARCHAR(10) NOT NULL,
    conformPassword VARCHAR(10) NULL,

    PRIMARY KEY (authID),
    CONSTRAINT FK_UserAuthID FOREIGN KEY (userAuthID) REFERENCES User (userID)
) ENGINE = INNODB;

CREATE TABLE Address
(
    addressID BIGINT(10)  NOT NULL AUTO_INCREMENT,
    userID    BIGINT(10)  NOT NULL,
    street    VARCHAR(50) NOT NULL,
    city      VARCHAR(50) NOT NULL,
    state     VARCHAR(30) NOT NULL,
    zipCode   INT         NOT NULL,

    PRIMARY KEY (addressID),
    CONSTRAINT FK_AUserID FOREIGN KEY (userID) REFERENCES User (userID)
) ENGINE = INNODB;

INSERT INTO User
VALUES (78231679, 'Mohamed', 'Keita', 'Male', '1995-01-01', 26, 5404160017, 'keitamohamed251@gmail.com'),
       (012799718, 'Drianne', 'Harvey', 'Female', '2006-07-29', 19, 9764536146, 'kmaggio@hotmail.com'),
       (768447557, 'Kacie', 'Boyle I', 'Female', '1987-03-14', 0, 2704340444, 'yframi@koelpin.com'),
       (138144291, 'Marcelina', 'Quitzon DVM', 'Female', '2008-12-09', 0, 2278903260, 'jerrell97@yahoo.com'),
       (840796108, 'Hilbert', 'West', 'Male', '2003-12-17', 0, 9565168195, 'ubosco@yahoo.com'),
       (727134521, 'Austen', 'Ernser I', 'Male', '1998-03-16', 0, 3905874908, 'lubowitz.arno@durgan.com'),
       (683368846, 'Tabitha', 'Barton', 'Female', '2009-07-24', 0, 683368846, 'koelpin.maryam@hotmail.com');

INSERT INTO Authenticate (userAuthID, username, userRole, password, conformPassword)
VALUES (78231679, 'keitamohamed', 'Admin', '!2Mohamed', '!2Mohamed'),
       (012799718, 'briaey', 'Student', '37f7594f', '37f7594f'),
       (683368846, 'tabion', 'Student', '23a231ad', '23a231ad'),
       (727134521, 'austri', 'Staff', 'd56ecaf3', 'd56ecaf3'),
       (840796108, 'hilbest', 'Student', '796ab476', '796ab476'),
       (138144291, 'marcvm', 'Student', '5522046b', '5522046b'),
       (768447557, 'kaciei', 'Staff', 'ee5a3e88', 'ee5a3e88');

INSERT INTO Address
VALUES (782351672, 78231679, '1340 King William RD', 'Charlotte', 'NC', 24016),
       (78231536, 012799718, '1474 Cletus Station', 'Brownview', 'NM', 35683),
       (78230019, 683368846, '698 Dickens Harbors Suite 504', 'Bergstromburgh', 'VA', 80250),
       (99672133, 727134521, '9301 Kip Circle Apt. 678', 'Corwinmouth', 'MN', 52672),
       (77623901, 840796108, '2835 Anissa Extensions', 'Crystelhaven', 'HI', 54366),
       (66458845, 138144291, '894 Leannon Lock Lake', 'Llewellyn', 'MI', 14028),
       (44231892, 768447557, '375 Franecki Gardens Apt. 397', 'South Alfredaberg', 'WV', 44728)

# ALTER TABLE Address ADD FOREIGN KEY (userID) REFERENCES User(userID);
# ALTER TABLE role ADD FOREIGN KEY (userRoleID) REFERENCES Authenticate(authID);


