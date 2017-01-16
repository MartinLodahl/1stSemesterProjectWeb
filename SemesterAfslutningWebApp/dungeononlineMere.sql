 
 DROP TABLE IF exists room;
 DROP TABLE IF exists link;
 DROP TABLE IF exists players;
 DROP TABLE IF exists items;
 DROP TABLE IF exists itemtypes;
 DROP TABLE IF exists monster;
 
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
 gold int,
 roomId int);
 
 CREATE TABLE items
 (ItemId int NOT NULL UNIQUE,
 type int,
 picture varchar(40),
 x int,
 y int,
 roomId int);
 
 CREATE TABLE itemtypes
 (type int,
 picture VARCHAR(40),
 stat double,
 modify int,
 note varchar(200));

 CREATE TABLE monster
 (ID int (11) UNIQUE,
 picture varchar(40),
 description text,
 health int(11),
 attack int(11));
 
 INSERT INTO monster
 values 
 (1, '', 'This is a mean alf', 20, 5),
 (2, '', 'This is a mean dog', 30, 5),
 (3, '', 'This is the Flying DutchMan', 50, 2),
 (4, '', 'This person.. Is it a person? KILL IT', 10, 2);
 
 Insert into itemtypes
 values
 (1,'coins',1,50,'Conin give you money'),
 (2,'Armor/Kindregret',2,20,'Armor of type Kindregret, give u bonus health'),
 (3,'WeaponsRanged/Manticore',3,10,'Ranged weapon type Manticore, give u bonus attack dmg'),
 (4,'WeaponsMelee/Bonewrath',3,15,'Melee weapon type Bonewrath, give u bonus attack dmg'),
 (5,'WeaponsMelee/Dishonored',3,25,'Melle weapon type Dishonored, give u bonus attack dmg');
 
 INSERT INTO items
 VALUES
 (1,1,200,200,1),
  (2,1,300,500,3),
   (3,1,200,400,4),
    (4,2,300,450,5),
     (5,3,650,250,2),
      (6,4,700,400,3),
       (7,5,500,200,1);
 
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
