 
 DROP TABLE IF exists room;
 DROP TABLE IF exists link;
 DROP TABLE IF exists players;
 DROP TABLE IF exists items;
 DROP TABLE IF exists itemtypes;
 DROP TABLE IF exists monster;
 DROP TABLE IF exists monstertype;
 DROP TABLE IF exists highscore;
 
 CREATE TABLE highscore
 (name varchar(40),
 score int(11));
 
 CREATE TABLE room
 (ID int(11),
 Description text);
 
 CREATE TABLE link 
 (room_id int(11),
 direction varchar(255),
 goto int(11),
 UNIQUE(room_id, goto));
 
 
 CREATE TABLE players
 (playerId int NOT NULL UNIQUE,
 name varchar(40),
 health int,
 attackDmg int,
 defense int,
 gold int,
 roomId int);
 
 CREATE TABLE items
 (playerId int NOT NULL DEFAULT 0,
 ItemId int NOT NULL,
 type int,
 x int,
 y int,
 roomId int,
 UNIQUE(playerId, ItemId));
 
 CREATE TABLE itemtypes
 (type int,
 picture VARCHAR(40),
 stat int,
 modify int,
 note varchar(200));


 CREATE TABLE monster
 (playerId int NOT NULL DEFAULT 0,
 ID int (11),
 type int,
 roomId int,
 health int(11),
 attack int (11),
 x int,
 y int,
 UNIQUE(playerId, ID));

 CREATE TABLE monstertype
 (type int,
 picture varchar(40),
 description text,
 health int(11),
 attack int(11));
 
 INSERT INTO monster
 values 
 (0, 1, 1, 41,-1,-1, 600, 600),
 (0, 2, 2, 11,-1,-1, 200, 400),
 (0, 3, 3, 25,-1,-1, 300, 200),
 (0, 4, 4, 34,-1,-1, 400, 400),
 (0, 5, 7, 12,-1,-1, 200, 300),
 (0, 6, 7, 13,-1,-1, 800, 400),
 (0, 7, 5, 14,-1,-1, 400, 400),
 (0, 8, 2, 15,-1,-1, 200, 400),
 (0, 9, 3, 21,-1,-1, 300, 200),
 (0, 10, 4, 22,-1,-1, 400, 400),
 (0, 11, 7, 23,-1,-1, 200, 300),
 (0, 12, 7, 24,-1,-1, 800, 400),
 (0, 13, 5, 31,-1,-1, 400, 400),
 (0, 14, 2, 32,-1,-1, 200, 400),
 (0, 15, 3, 33,-1,-1, 300, 200),
 (0, 16, 4, 35,-1,-1, 400, 400),
 (0, 17, 7, 42,-1,-1, 200, 300),
 (0, 18, 7, 43,-1,-1, 800, 400),
 (0, 19, 4, 45,-1,-1, 400, 400),
 (0, 20, 7, 51,-1,-1, 200, 300),
 (0, 21, 7, 55,-1,-1, 800, 400),
 (0, 22, 5, 44,-1,-1, 400, 400);

 
INSERT INTO monstertype
 values 
 (1, 'PicturesPokemon/Psyduck', '', 20, 5),
 (2, 'PicturesPokemon/Onix', '', 30, 5),
 (3, 'PicturesPokemon/Golem', '', 50, 2),
 (4, 'PicturesPokemon/Gengar', '', 10, 2),
 (5, 'PicturesPokemon/Mewtwo', '', 1000, 50),
 (6, 'PicturesPokemon/Dugtrio', '', 100, 2),
 (7, 'PicturesPokemon/Gastly', '', 50, 80),
 (8, 'PicturesPokemon/Zubat', '', 1000, 50),
 (9, 'PicturesPokemon/Dugtrio', '', 100, 2),
 (10, 'PicturesPokemon/Gastly', '', 50, 80);

 
 Insert into itemtypes
 values
 (1,'coins',1,20,'Conin give you money'),
 (2,'atk',2,10,'Armor of type Kindregret, give u bonus health'),
 (3,'def',3,20,'Ranged weapon type Manticore, give u bonus attack dmg'),
 (4,'heal',4,15,'Melee weapon type Bonewrath, give u bonus attack dmg'),
 (5,'coins',1,50,'Conin give you money'),
 (6,'atk',2,20,'Armor of type Kindregret, give u bonus health'),
 (7,'def',3,30,'Ranged weapon type Manticore, give u bonus attack dmg'),
 (8,'heal',4,25,'Melee weapon type Bonewrath, give u bonus attack dmg');
 
 INSERT INTO items
 VALUES
 (0, 1, 1,200,200,1),
 (0, 2, 1,300,500,3),
 (0, 3, 1,200,400,4),
 (0, 5, 3,650,250,2),
 (0, 6, 4,700,400,3),
 (0, 7, 5,500,200,1);
 
 INSERT INTO link(room_id, direction, goto)
VALUES 
/*Horizontal*/
(11,'EAST',21),
(21,'WEST',11),
(21,'EAST',31),
(31,'WEST',21),
(51,'EAST',61),
(61,'WEST',51),
(61,'EAST',71),
(71,'WEST',61),
(22,'EAST',32),
(32,'WEST',22),
(32,'EAST',42),
(42,'WEST',32),
(42,'EAST',52),
(52,'WEST',42),
(62,'EAST',72),
(72,'WEST',62),
(13,'EAST',23),
(23,'WEST',13),
(23,'EAST',33),
(33,'WEST',23),
(43,'EAST',53),
(53,'WEST',43),
(14,'EAST',24),
(24,'WEST',14),
(24,'EAST',34),
(34,'WEST',24),
(34,'EAST',44),
(44,'WEST',34),
(54,'EAST',64),
(64,'WEST',54),
(35,'EAST',45),
(45,'WEST',35),
(45,'EAST',55),
(55,'WEST',45),
(65,'EAST',75),
(75,'WEST',65),
/*Vertical*/
(11,'NORTH',12),
(12,'SOUTH',11),
(12,'NORTH',13),
(13,'SOUTH',12),
(14,'NORTH',15),
(15,'SOUTH',14),
(24,'NORTH',25),
(25,'SOUTH',24),
(31,'NORTH',32),
(32,'SOUTH',31),
(32,'NORTH',33),
(33,'SOUTH',32),
(33,'NORTH',34),
(34,'SOUTH',33),
(34,'NORTH',35),
(35,'SOUTH',34),
(41,'NORTH',42),
(42,'SOUTH',41),
(44,'NORTH',45),
(45,'SOUTH',44),
(52,'NORTH',53),
(53,'SOUTH',52),
(53,'NORTH',54),
(54,'SOUTH',53),
(62,'NORTH',63),
(63,'SOUTH',62),
(63,'NORTH',64),
(64,'SOUTH',63),
(64,'NORTH',65),
(65,'SOUTH',64),
(71,'NORTH',72),
(72,'SOUTH',71),
(72,'NORTH',73),
(73,'SOUTH',72),
(73,'NORTH',74),
(74,'SOUTH',73);
