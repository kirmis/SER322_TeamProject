CREATE TABLE user{
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE
}

CREATE TABLE premium_User{
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
}

CREATE TABLE review_user{
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	early_access BOOLEAN
}

CREATE TABLE banned_user{
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	banned_description VARCHAR(64),
	banned BOOLEAN
}

CREATE TABLE system_administrator{
	username VARCHAR(64) NOT NULL,
	password VARCHAR(32),
	balance float,
	card_type VARCHAR(32),
	card_num INT,
	security_code INT,
	experation DATE,
	manages BOOLEAN
}

CREATE TABLE owns{
	username VARCHAR(64) NOT NULL,
	gameID VARCHAR(32) NOT NULL
}

CREATE TABLE game{
	gameID VARCHAR(64) NOT NULL,
	game_name VARCHAR(64),
	release_date DATE,
	review_releaase_date DATE,
	publisherID VARCHAR(32)
	}
	
CREATE TABLE developer{
	developerID VARCHAR(64) NOT NULL,
	developer_name VARCHAR(32),
	main_branch VARCHAR(32)
}

CREATE TABLE developer_branch{
	developerID VARCHAR(64) NOT NULL,
	branchID VARCHAR(64) NOT NULL,
	location VARCHAR(64)
	}
	
CREATE TABLE develops{
	developerID VARCHAR(64) NOT NULL,
	gameID VARCHAR(64) NOT NULL
}

CREATE TABLE genre {
	type VARCHAR(32) NOT NULL,
	description VARCHAR(64)
}

CREATE TABLE Is_a{
 	gameID VARCHAR(64) NOT NULL,
 	gentreType VARCHAR(32) NOT NULL
}

CREATE TABLE publisher{
	publisherID VARCHAR(64) NOT NULL,
	publisher_name VARCHAR(64),
	publisher_location VARCHAR(64)
	}
	
CREATE TABLE review{
	title VARCHAR(64) NOT NULL,
	rating float,
	gameID VARCHAR(64) NOT NULL,
	reviewer_username VARCHAR(64),
	date_posted DATE
}