INSERT INTO User VALUES('user0', 'password0',335.81, 'Debit', '87888445','355', '2020-5-29');
INSERT INTO User VALUES('user1', 'password1',107.92, 'Debit', '70529755','906', '2020-5-26');
INSERT INTO User VALUES('user2', 'password2',137.40, 'Debit', '77822617','561', '2020-2-21');
INSERT INTO User VALUES('user3', 'password3',141.101, 'Credit', '95544357','348', '2020-9-5');
INSERT INTO User VALUES('user4', 'password4',197.22, 'Credit', '80126431','976', '2020-5-28');


INSERT INTO Premium_user VALUES('user0',FALSE,FALSE, '2020-4-19');


INSERT INTO Review_user VALUES('user1',TRUE);


INSERT INTO Banned_user VALUES('user1',FALSE,'Illegal Gambling');


INSERT INTO System_administrator VALUES('user2');


INSERT INTO Publisher VALUES('56445', 'publisher0', ' City 0 ,New York ');


INSERT INTO Game VALUES('78000', 'Game Title0', '2019-02-22', '2019-9-2',38.99, '56445');


INSERT INTO Owns VALUES('user0', '78000');
INSERT INTO Owns VALUES('user1', '78000');
INSERT INTO Owns VALUES('user2', '78000');
INSERT INTO Owns VALUES('user3', '78000');
INSERT INTO Owns VALUES('user4', '78000');


INSERT INTO Developer VALUES('1048203', 'Developer0', NULL);


INSERT INTO Developer_branch VALUES('1048203', '33711', 'City0, Oregon');


UPDATE Developer SET mainBranch = '33711' WHERE developerID = '1048203';


INSERT INTO Develops VALUES('1048203', '78000');


INSERT INTO Genre VALUES('Role-playing', 'The player controls the actions of a unique character.');
INSERT INTO Genre VALUES('Fighting', 'Players control a character and engage in close combat with opponents.');
INSERT INTO Genre VALUES('Shooter', 'The player controls a character and uses weapons, typically guns, to fight enemies.');
INSERT INTO Genre VALUES('Action', 'The player must interact in physical challenges such as hand-eye coordination and reaction-time.');
INSERT INTO Genre VALUES('Adventure', 'The player takes the role of a protagonist in a story that features exploration and puzzle-solving.');


INSERT INTO Is_a VALUES('78000', 'Fighting');


INSERT INTO Review VALUES('Review0', 3.7, '78000', 'user1', '2019-2-28');


INSERT INTO Uses VALUES('user0', 'Playstation 4');
INSERT INTO Uses VALUES('user1', 'Xbox one');
INSERT INTO Uses VALUES('user2', 'PC');
INSERT INTO Uses VALUES('user3', 'Nintendo Switch');
INSERT INTO Uses VALUES('user4', 'PC');


INSERT INTO Available_on VALUES('78000', 'Xbox one');
INSERT INTO Available_on VALUES('78000', 'PC');
