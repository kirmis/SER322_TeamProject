INSERT INTO User VALUES('rkirmis', 'password1', 1.00, 'Debit', '1234123412341234', '123', '2020-01-01');
INSERT INTO User VALUES('mramirez', 'password2', 5.70, 'Debit', '4321432143214321', '234', '2021-06-15');
INSERT INTO User VALUES('amencek', 'password3', 30.60, 'Credit', '1111222233334444', '345', '2022-01-28');
INSERT INTO User VALUES('tstark', 'password4', 6.70, 'Debit', '2222333344445555', '456', '2024-01-01');
INSERT INTO User VALUES('srogers', 'password5', 40.00, 'Credit', '3333444455556666', '567', '2028-01-01');
INSERT INTO User VALUES('mchief', 'password6', 50.00, 'Debit', '4444555566667777', '678', '2020-01-01');
INSERT INTO User VALUES('skywalker', 'password7', 80.00, 'Credit', '5555666677778888', '789', '2020-07-21');
INSERT INTO User VALUES('thor', 'password8', 20.00, 'Debit', '6666777788889999', '890', '2020-01-01');
INSERT INTO User VALUES('banner', 'password9', 1.00, 'Credit', '7777888899990000', '987', '2024-06-01');
INSERT INTO User VALUES('thanos', 'password10', 20.00, 'Credit', '9999888877776666', '876', '2019-08-20');
INSERT INTO User VALUES('ultron', 'password11', 1.00, 'Debit', '8888777766665555', '765', '2021-01-01');
INSERT INTO User VALUES('john', 'password12', 20.00, 'Credit', '7777666655554444', '654', '2021-05-22');
INSERT INTO User VALUES('joe', 'password13', 1.00, 'Debit', '6666555544443333', '543', '2018-01-01');
INSERT INTO User VALUES('sue', 'password14', 30.00, 'Credit', '5555444433332222', '432', '2020-01-01');
INSERT INTO User VALUES('jim', 'password15', 40.00, 'Credit', '4444333322221111', '321', '2030-09-11');
INSERT INTO User VALUES('jake', 'password16', 30.00, 'Debit', '3333222211110000', '111', '2020-01-01');
INSERT INTO User VALUES('george', 'password17', 0.00, 'Debit', '2345234523452345', '222', '2040-01-01');
INSERT INTO User VALUES('bob', 'password18', 0.00, 'Credit', '3456345634563456', '333', '2020-01-18');
INSERT INTO User VALUES('ashley', 'password19', 45.00, 'Debit', '4567456745674567', '444', '2022-01-22');
INSERT INTO User VALUES('tom', 'password20', 62.00, 'Credit', '5678567856785678', '555', '2023-01-01');
INSERT INTO User VALUES('jennifer', 'password21', 100.00, 'Debit', '6789678967896789', '666', '2018-01-23');
INSERT INTO User VALUES('dan', 'password22', 1.00, 'Debit', '7890789078907890', '777', '2020-01-01');
INSERT INTO User VALUES('shaun', 'password23', 54.62, 'Credit', '9876987698769876', '888', '2090-01-24');
INSERT INTO User VALUES('tracy', 'password24', 100.00, 'Credit', '8765876587658765', '999', '2020-01-08');
INSERT INTO User VALUES('tim', 'password25', 400.34, 'Debit', '765476547654', '000', '2015-07-08');

INSERT INTO Premium_user VALUES('rkirmis', TRUE, TRUE, '2020-03-20');
INSERT INTO Premium_user VALUES('mramirez', TRUE, FALSE, '2019-04-05');
INSERT INTO Premium_user VALUES('amencek', TRUE, TRUE, '2020-01-20');
INSERT INTO Premium_user VALUES('tstark', FALSE, TRUE, '2024-03-24');
INSERT INTO Premium_user VALUES('srogers', TRUE, FALSE, '2019-09-20');

INSERT INTO Review_user VALUES('mchief', TRUE);
INSERT INTO Review_user VALUES('skywalker', TRUE);
INSERT INTO Review_user VALUES('thor', FALSE);
INSERT INTO Review_user VALUES('banner', TRUE);
INSERT INTO Review_user VALUES('thanos', FALSE);

INSERT INTO Banned_user VALUES('ultron', TRUE, 'cheating');
INSERT INTO Banned_user VALUES('john', FALSE, 'cheating');
INSERT INTO Banned_user VALUES('joe', TRUE, 'illegal gambling');
INSERT INTO Banned_user VALUES('sue', FALSE, 'cheating');
INSERT INTO Banned_user VALUES('jim', FALSE, 'illegal gambling');

INSERT INTO System_administrator VALUES('jake');
INSERT INTO System_administrator VALUES('george');
INSERT INTO System_administrator VALUES('bob');
INSERT INTO System_administrator VALUES('ashley');
INSERT INTO System_administrator VALUES('tom');

INSERT INTO Publisher VALUES('12345', 'Electronic Arts', 'Redwood City, California');
INSERT INTO Publisher VALUES('23456', 'Ubisoft', 'Montreuil, France');
INSERT INTO Publisher VALUES('34567', 'Nintendo', 'Kyoto, Japan');
INSERT INTO Publisher VALUES('45678', 'Activision', 'Santa Monica, California');
INSERT INTO Publisher VALUES('56789', 'Bethesda', 'Rockville, Maryland');

INSERT INTO Game VALUES('11111', 'Anthem', '2019-02-22', '2019-02-22', '12345');
INSERT INTO Game VALUES('22222', 'Tom Clancys The Division', '2019-03-15', '2019-03-08', '23456');
INSERT INTO Game VALUES('33333', 'Super Smash Bros. Ultimate', '2018-12-07', '2018-12-07', '34567');
INSERT INTO Game VALUES('44444', 'Call of Duty: Black Ops 4', '2018-10-12', '2018-10-12', '45678');
INSERT INTO Game VALUES('55555', 'Fallout 76', '2019-02-22', '2018-11-14', '56789');

INSERT INTO Owns VALUES('rkirmis', '22222');
INSERT INTO Owns VALUES('mramirez', '11111');
INSERT INTO Owns VALUES('amencek', '33333');
INSERT INTO Owns VALUES('tstark', '44444');
INSERT INTO Owns VALUES('srogers', '55555');
INSERT INTO Owns VALUES('mchief', '11111');
INSERT INTO Owns VALUES('skywalker', '22222');
INSERT INTO Owns VALUES('thor', '33333');
INSERT INTO Owns VALUES('banner', '44444');
INSERT INTO Owns VALUES('thanos', '55555');
INSERT INTO Owns VALUES('john', '22222');
INSERT INTO Owns VALUES('joe', '33333');
INSERT INTO Owns VALUES('sue', '44444');
INSERT INTO Owns VALUES('rkirmis', '55555');
INSERT INTO Owns VALUES('jake', '11111');
INSERT INTO Owns VALUES('george', '22222');
INSERT INTO Owns VALUES('bob', '33333');
INSERT INTO Owns VALUES('ashley', '44444');
INSERT INTO Owns VALUES('tom', '33333');
INSERT INTO Owns VALUES('dan', '22222');
INSERT INTO Owns VALUES('shaun', '33333');
INSERT INTO Owns VALUES('tracy', '44444');
INSERT INTO Owns VALUES('tim', '55555');
INSERT INTO Owns VALUES('mramirez', '55555');

INSERT INTO Developer VALUES('66666', 'BioWare', NULL);
INSERT INTO Developer VALUES ('77777', 'Massive Entertainment', NULL);
INSERT INTO Developer VALUES('88888', 'BANDAI NAMCO', NULL);
INSERT INTO Developer VALUES('99999', 'Treyarch', NULL);
INSERT INTO Developer VALUES('00000', 'Bethesda Game Studios', NULL);

INSERT INTO Developer_branch VALUES('66666', '98765', 'Edmonton, Canada');
INSERT INTO Developer_branch VALUES('77777', '87654', 'Malmo, Sweden');
INSERT INTO Developer_branch VALUES('88888', '76543', 'Tokyo, Japan');
INSERT INTO Developer_branch VALUES('99999', '65432', 'Santa Monica, California');
INSERT INTO Developer_branch VALUES('00000', '54321', 'Rockville, Maryland');

INSERT INTO Develops VALUES('66666', '11111');
INSERT INTO Develops VALUES('77777', '22222');
INSERT INTO Develops VALUES('88888', '33333');
INSERT INTO Develops VALUES('99999', '44444');
INSERT INTO Develops VALUES('00000', '55555');

INSERT INTO Genre VALUES('Role-playing', 'The player controls the actions of a unique character.');
INSERT INTO Genre VALUES('Fighting', 'Players control a character and engage in close combat with opponents.');
INSERT INTO Genre VALUES('Shooter', 'The player controls a character and uses weapons, typically guns, to fight enemies
.');
INSERT INTO Genre VALUES('Action', 'The player must interact in physical challenges such as hand-eye coordination and reaction-time.');
INSERT INTO Genre VALUES('Adventure', 'The player takes the role of a protagonist in a story that features exploration and puzzle-solving.');

INSERT INTO Is_a VALUES('11111', 'Action');
INSERT INTO Is_a VALUES('11111', 'Role-playing');
INSERT INTO Is_a VALUES('22222', 'Action');
INSERT INTO Is_a VALUES('22222', 'Role-playing');
INSERT INTO Is_a VALUES('33333', 'Fighting');
INSERT INTO Is_a VALUES('44444', 'Shooter');
INSERT INTO Is_a VALUES('55555', 'Role-playing');

INSERT INTO Review VALUES('Anthem Review', 7.2, '11111', 'mchief', '2019-02-22');
INSERT INTO Review VALUES('The Division 2 Review', 7.8, '22222', 'skywalker', '2019-03-08');
INSERT INTO Review VALUES('Smash Ultimate Review: The Best One Yet', 9.0, '33333', 'thor', '2018-12-07');
INSERT INTO Review VALUES('Black Ops 4 Review', 6.9, '44444', 'banner', '2018-10-12');
INSERT INTO Review VALUES('Fallout 76 Review: A Cash Grab', 4.9, '55555', 'thanos', '2018-11-14');

INSERT INTO Uses VALUES('rkirmis', 'Xbox One');
INSERT INTO Uses VALUES('mramirez', 'PC');
INSERT INTO Uses VALUES('amencek', 'Nintendo Switch');
INSERT INTO Uses VALUES('tstark', 'PC');
INSERT INTO Uses VALUES('srogers', 'PlayStation 4');
INSERT INTO Uses VALUES('mchief', 'Xbox One');
INSERT INTO Uses VALUES('skywalker', 'PC');
INSERT INTO Uses VALUES('thor', 'Nintendo Switch');
INSERT INTO Uses VALUES('banner', 'Xbox One');
INSERT INTO Uses VALUES('thanos', 'PlayStation 4');
INSERT INTO Uses VALUES('john', 'Xbox One');
INSERT INTO Uses VALUES('joe', 'PlayStation 4');
INSERT INTO Uses VALUES('sue', 'PC');
INSERT INTO Uses VALUES('rkirmis', 'PC');
INSERT INTO Uses VALUES('jake', 'PC');
INSERT INTO Uses VALUES('george', 'Xbox One');
INSERT INTO Uses VALUES('bob', 'Nintendo Switch');
INSERT INTO Uses VALUES('ashley', 'PC');
INSERT INTO Uses VALUES('tom', 'Nintendo Switch');
INSERT INTO Uses VALUES('dan', 'PlayStation 4');
INSERT INTO Uses VALUES('shaun', 'PlayStation 4');
INSERT INTO Uses VALUES('tracy', 'Xbox One');
INSERT INTO Uses VALUES('tim', 'PlayStation 4');
INSERT INTO Uses VALUES('mramirez', 'Nintendo Switch');

INSERT INTO Available_on VALUES('11111', 'Xbox One');
INSERT INTO Available_on VALUES('11111', 'Playstation 4');
INSERT INTO Available_on VALUES('11111', 'PC');
INSERT INTO Available_on VALUES('22222', 'Xbox One');
INSERT INTO Available_on VALUES('22222', 'Playstation 4');
INSERT INTO Available_on VALUES('22222', 'PC');
INSERT INTO Available_on VALUES('33333', 'Nintendo Switch');
INSERT INTO Available_on VALUES('44444', 'Xbox One');
INSERT INTO Available_on VALUES('44444', 'Playstation 4');
INSERT INTO Available_on VALUES('44444', 'PC');
INSERT INTO Available_on VALUES('55555', 'Xbox One');
INSERT INTO Available_on VALUES('55555', 'Playstation 4');
INSERT INTO Available_on VALUES('55555', 'PC');

