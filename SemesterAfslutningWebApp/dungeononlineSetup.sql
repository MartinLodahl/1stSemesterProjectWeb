 
 DROP TABLE IF exists room;
 DROP TABLE IF exists link;
 DROP TABLE IF exists players;
 DROP TABLE IF exists items;
 DROP TABLE IF exists itemtypes;
 DROP TABLE IF exists monster;
 DROP TABLE IF exists monstertype;
 
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
 roomId int);
 
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
 y int);

 CREATE TABLE monstertype
 (type int,
 picture varchar(40),
 description text,
 health int(11),
 attack int(11));
 
 INSERT INTO monster
 values 
 (0, 1, 1, 1,-1,-1, 600, 600),
 (0, 2, 2, 2,-1,-1, 200, 400),
 (0, 3, 3, 3,-1,-1, 300, 200),
 (0, 4, 4, 4,-1,-1, 400, 400),
 (0, 5, 7, 7,-1,-1, 200, 300),
 (0, 6, 7, 7,-1,-1, 800, 400),
 (0, 7, 5, 8,-1,-1, 400, 400);

 
INSERT INTO monstertype
 values 
 (1, 'PicturesPokemon/Psyduck', 'ARHWOO', 20, 5),
 (2, 'PicturesPokemon/Onix', 'Watch out, he is throwing bones!', 30, 5),
 (3, 'PicturesPokemon/Golem', 'Must.Have.BRAAAAAINS!', 50, 2),
 (4, 'PicturesPokemon/Gengar', 'This person.. It is a person! KILL IT', 10, 2),
 (5, 'PicturesPokemon/Mewtwo', '', 1000, 50),
 (6, 'PicturesPokemon/Dugtrio', '', 100, 2),
 (7, 'PicturesPokemon/Gastly', '', 50, 80);

 
 Insert into itemtypes
 values
 (1,'coins',1,50,'Conin give you money'),
 (2,'ArmorChest/Kindregret',2,20,'Armor of type Kindregret, give u bonus health'),
 (3,'WeaponsRanged/Manticore',3,10,'Ranged weapon type Manticore, give u bonus attack dmg'),
 (4,'WeaponsMelee/Bonewrath',3,15,'Melee weapon type Bonewrath, give u bonus attack dmg'),
 (5,'WeaponsMelee/Dishonored',3,25,'Melle weapon type Dishonored, give u bonus attack dmg');
 
 INSERT INTO items
 VALUES
 (0, 1, 1,200,200,1),
 (0, 2, 1,300,500,3),
 (0, 3, 1,200,400,4),
 (0, 4, 2,300,450,5),
 (0, 5, 3,650,250,2),
 (0, 6, 4,700,400,3),
 (0, 7, 5,500,200,1);
 
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
(2, 'NORTH', 6),
(6, 'SOUTH', 2),
(6, 'WEST', 7),
(7, 'EAST', 6),
(8, 'WEST', 6),
(6, 'EAST', 8),
(0, 'NORTH', 1);
