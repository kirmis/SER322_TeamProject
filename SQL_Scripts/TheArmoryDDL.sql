CREATE DATABASE TheArmory;
USE TheArmory;

CREATE TABLE User(
	username VARCHAR(64) PRIMARY KEY,
	password VARCHAR(32) NOT NULL,
	balance FLOAT(7, 2),
	cardType VARCHAR(32),
	cardNumber VARCHAR(32),
	securityCode VARCHAR(3),
	expDate DATE
);

CREATE TABLE Premium_user(
	username VARCHAR(64) PRIMARY KEY,
	discountedGames BOOLEAN,
	playOnline BOOLEAN,
	renewalDate DATE,
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Review_user(
	username VARCHAR(64) PRIMARY KEY,
	earlyAcess BOOLEAN,
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Banned_user(
	username VARCHAR(64) PRIMARY KEY,
	bannedBranding BOOLEAN,
	bannedDescription VARCHAR(64),
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE System_administrator(
	username VARCHAR(64) PRIMARY KEY,
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Publisher(
	publisherID VARCHAR(32) PRIMARY KEY,
	publisherName VARCHAR(64),
	location VARCHAR(64)
);

CREATE TABLE Game(
	gameID VARCHAR(32) PRIMARY KEY,
	gameName VARCHAR(64),
	releaseDate DATE,
	reviewReleasDate DATE,
	publisherID VARCHAR(32),
	FOREIGN KEY(publisherID) REFERENCES Publisher(publisherID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Owns(
	username VARCHAR(64),
	gameID VARCHAR(32),
	PRIMARY KEY(username, gameID),
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(gameID) REFERENCES Game(gameID) ON UPDATE CASCADE ON DELETE CASCADE
);
	
CREATE TABLE Developer(
	developerID VARCHAR(32) PRIMARY KEY,
	developerName VARCHAR(64),
	mainBranch VARCHAR(32)
);

CREATE TABLE Developer_branch(
	developerID VARCHAR(32),
	branchID VARCHAR(32),
	location VARCHAR(64),
	PRIMARY KEY(developerID, branchID),
	FOREIGN KEY(developerID) REFERENCES Developer(developerID) ON UPDATE CASCADE ON DELETE CASCADE
);
	
CREATE TABLE Develops(
	developerID VARCHAR(32),
	gameID VARCHAR(32),
	PRIMARY KEY(developerID, gameID)
);

CREATE TABLE Genre(
	type VARCHAR(32) PRIMARY KEY,
	description VARCHAR(300)
);

CREATE TABLE Is_a(
 	gameID VARCHAR(32),
 	genreType VARCHAR(32),
 	PRIMARY KEY(gameID, genreType),
 	FOREIGN KEY(gameID) REFERENCES Game(gameID) ON UPDATE CASCADE ON DELETE CASCADE,
 	FOREIGN KEY(genreType) REFERENCES Genre(type) ON UPDATE CASCADE ON DELETE CASCADE
);
	
CREATE TABLE Review(
	title VARCHAR(64),
	rating FLOAT(3, 1),
	gameID VARCHAR(32),
	reviewerUsername VARCHAR(64),
	datePosted DATE,
	PRIMARY KEY(title, gameID, reviewerUsername),
	FOREIGN KEY(gameID) REFERENCES Game(gameID) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(reviewerUsername) REFERENCES Review_user(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Uses(
	username VARCHAR(64),
	platformType VARCHAR(32),
	PRIMARY KEY(username, platformType),
	FOREIGN KEY(username) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE
);
	
CREATE TABLE Available_on(
	gameID VARCHAR(64),
	platformType VARCHAR(32),
	PRIMARY KEY(gameID, platformType),
	FOREIGN KEY(gameID) REFERENCES Game(gameID) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE Developer ADD FOREIGN KEY(developerID, mainBranch) REFERENCES Developer_branch(developerID, branchID);