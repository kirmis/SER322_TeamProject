CREATE TABLE user(
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE
)

CREATE TABLE premium_User(
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	discounted_games BOOLEAN,
	online BOOLEAN,
	renewal_date DATE
)

CREATE TABLE review_user(
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	early_access BOOLEAN
)

CREATE TABLE banned_user(
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	banned_description VARCHAR(64),
	banned BOOLEAN
)

CREATE TABLE system_administrator(
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	manages BOOLEAN
)

CREATE TABLE owns(
	username VARCHAR(64) NOT NULL,
	gameID VARCHAR(32) NOT NULL
)

CREATE TABLE game(
	gameID VARCHAR(64) NOT NULL,
	game_name VARCHAR(64),
	release_date DATE,
	review_releaase_date DATE,
	publisherID VARCHAR(32)
	)
	
CREATE TABLE developer(
	developerID VARCHAR(64) NOT NULL,
	developer_name VARCHAR(32),
	main_branch VARCHAR(32)
)

CREATE TABLE developer_branch(
	developerID VARCHAR(64) NOT NULL,
	branchID VARCHAR(64) NOT NULL,
	location VARCHAR(64)
)
	
CREATE TABLE develops(
	developerID VARCHAR(64) NOT NULL,
	gameID VARCHAR(64) NOT NULL
)

CREATE TABLE genre (
	type VARCHAR(32) NOT NULL,
	description VARCHAR(64)
)

CREATE TABLE Is_a(
 	gameID VARCHAR(64) NOT NULL,
 	gentreType VARCHAR(32) NOT NULL
)

CREATE TABLE publisher(
	publisherID VARCHAR(64) NOT NULL,
	publisher_name VARCHAR(64),
	publisher_location VARCHAR(64)
	)
	
CREATE TABLE review(
	title VARCHAR(64) NOT NULL,
	rating float,
	gameID VARCHAR(64) NOT NULL,
	reviewer_username VARCHAR(64),
	date_posted DATE
)

CREATE TABLE uses(
	username VARCHAR(64) NOT NULL,
	platformType VARCHAR(32)
	)
	
CREATE TABLE available_on(
	gameID VARCHAR(64) NOT NULL,
	platformType VARCHAR(32)
)

ALTER TABLE user
	ADD CONSTRAINT userPk PRIMARY KEY (username);

ALTER TABLE premium_User
	ADD CONSTRAINT premium_Userfk FOREIGN KEY (username) REFERENCES user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE review_user
	ADD CONSTRAINT review_userfk FOREIGN KEY (username) REFERENCES user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;
	
ALTER TABLE banned_user
	ADD CONSTRAINT banned_userfk FOREIGN KEY (username) REFERENCES user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;
	
ALTER TABLE system_administrator
	ADD CONSTRAINT system_administratorfk FOREIGN KEY (username) REFERENCES user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE game
 ADD CONSTRAINT gamePk PRIMARY KEY (gameID);
 
ALTER TABLE owns
	ADD CONSTRAINT ownsfk FOREIGN KEY (username, gameID) REFERENCES user(username), game(gameID) ON UPDATE RESTRICT ON DELETE RESTRICT;
	
ALTER TABLE developer
	ADD CONSTRAINT developerPk PRIMARY KEY (developerID);

ALTER TABLE developer_branch
	ADD CONSTRAINT developer_branchPk PRIMARY KEY (developerID, branchID); 
	
ALTER TABLE develops
	ADD CONSTRAINT developsfk FOREIGN KEY (developerID, gameID) REFERENCES developer(developerID) , game(gameID) ON UPDATE RESTRICT ON DELETE RESTRICT;
	
ALTER TABLE genre
	ADD CONSTRAINT genrePk PRIMARY KEY (type);

ALTER TABLE is_a 
	ADD CONSTRAINT is_aFk FOREIGN KEY (gameID, genreType) REFERENCES game(gameID), genre(type);

ALTER TABLE publisher
	ADD CONSTRAINT publisherPk PRIMARY KEY (publisherID) ON UPDATE RESTRICT ON DELETE RESTRICT;
	
ALTER TABLE game
	ADD CONSTRAINT gameFk FOREIGN KEY (publisherID) REFERENCES publisher(publisherID) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE review
	ADD CONSTRAINT reviewPk PRIMARY KEY (title);
	
ALTER TABLE review
	ADD CONSTRAINT reviewFk FOREIGN KEY (gameID, reviewer_username) REFERENCES game(gameID) , user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE uses
	ADD CONSTRAINT usesFk FOREIGN KEY (username) REFERENCES user(username) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE available_on
	ADD CONSTRAINT available_onFk FOREIGN KEY (gameID) REFERENCES game(gameID) ON UPDATE RESTRICT ON DELETE RESTRICT;