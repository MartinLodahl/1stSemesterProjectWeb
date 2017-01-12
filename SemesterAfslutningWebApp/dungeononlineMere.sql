 
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
 (name varchar(40),
 health int,
 gold int,
 room int);
 
 INSERT INTO link(room_id, direction, goto)
VALUES 
(1, 'NORTH', 2),
(1, 'WEST', 2),
(2, 'EAST', 1),
(2, 'NORTH', 4),
(2, 'WEST', 3),
(3, 'NORTH', 4),
(3, 'EAST', 2),
(4, 'SOUTH', 2),
(4, 'WEST', 3),
(0, 'NORTH', 1);