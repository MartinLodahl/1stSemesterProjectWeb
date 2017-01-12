 
 DROP TABLE IF exists room;
 DROP TABLE IF exists link;
 DROP TABLE IF exists players;
 
 CREATE TABLE room
 (ID int(11),
 Description text);
 
 CREATE TABLE link 
 (room_id int(11),
 direction varchar(255),
 goto int(11));
 
 
 CREATE TABLE players
 (playerId int NOT NULL UNIQUE,
 name varchar(40),
 health int,
 gold int,
 room int);
 
 INSERT INTO link(room_id, direction, goto)
VALUES 
(1, 'NORTH', 2),
(1, 'WEST', 3),
(2, 'SOUTH', 1),
(2, 'WEST', 5),
(3, 'WEST', 4),
(3, 'EAST', 1),
(4, 'EAST', 3),
(4, 'NORTH', 5),
(5, 'SOUTH', 4),
(5, 'EAST', 2),
(0, 'NORTH', 1);
