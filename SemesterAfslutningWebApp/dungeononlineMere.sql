 
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
VALUES (1, 'West', 2),
(1, 'West', 2),
(1, 'East', 2),
(2, 'West', 3),
(2, 'North', 4);