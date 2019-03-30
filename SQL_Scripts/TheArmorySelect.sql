SELECT username, password, balance, cardType, cardNumber, securityCode, expDate FROM User;
SELECT username FROM User;
SELECT password FROM User WHERE username = 'rkirmis';
SELECT balance FROM User WHERE username = 'tstark';

SELECT renewalDate FROM Premium_user;
SELECT discountedGames FROM Premium_user;
SELECT playOnline FROM Premium_user;
SELECT username FROM Premium_user;

SELECT username FROM Review_user;
SELECT earlyAccess From Review_user;

SELECT username FROM System_administrator;

SELECT username, bannedDescription FROM Banned_user;
SELECT username, bannedDescription FROM Banned_user WHERE Banned_user.bannedBranding = TRUE;

SELECT gameName, releaseDate, price FROM Game;
SELECT username, gameName FROM Game NATURAL JOIN Owns NATURAL JOIN User ORDER BY username;

SELECT gameName FROM Game NATURAL JOIN Owns NATURAL JOIN User WHERE User.username = 'rkirmis';

SELECT developerName, gameName FROM Game NATURAL JOIN develops NATURAL JOIN Developer ORDER BY developerName;
SELECT developerName, location FROM Developer NATURAL JOIN Developer_branch ORDER BY developerName;
SELECT developerName, location AS Main_Location FROM Developer JOIN Developer_branch ON (Developer.developerID = Developer_branch.developerID AND Developer.mainBranch = Developer_branch.branchID);

SELECT publisherName, location, gameName FROM Game NATURAL JOIN publisher ORDER BY publisherName;

SELECT gameName, title, rating, reviewerUsername, datePosted FROM Game NATURAL JOIN Review;
SELECT genreType, gameName FROM Game NATURAL JOIN Is_a WHERE genreType = 'Fighting' ORDER BY genreType;
SELECT type, description FROM Genre;

SELECT username, gameName as games_user_can_buy, platformType as platform, price, balance as current_balance, balance - price as resulting_balance FROM Game NATURAL JOIN Available_on NATURAL JOIN Uses NATURAL JOIN User WHERE price < balance;