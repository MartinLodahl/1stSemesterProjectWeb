DROP TABLE IF exists room;
DROP TABLE IF exists link;

CREATE TABLE room
(ID int(11),
Description text);



CREATE TABLE link 
(room_id int(11),
direction varchar(255),
goto int(11));

