-- Author Adam Schaible
-- CS 3210
-- 09/28/17

CREATE DATABASE flowers;
USE flowers;

CREATE TABLE Zones(
id			INT(2) PRIMARY KEY,
lowerTemp	INT(3) NOT NULL,
higherTemp 	INT(3) NOT NULL
);

-- Populate Zones
INSERT INTO Zones(id,lowerTemp,higherTemp)
values
	( 2,-50,-40),
	( 3,-40,-30),
	( 4,-30,-20),
	( 5,-20,-10),
	( 6,-10,  0),
	( 7,  0, 10),
	( 8, 10, 20),
	( 9, 20, 30),
	(10, 30, 40)
	;

CREATE TABLE Deliveries(
id 		INT (1) 		PRIMARY KEY,
categ 	CHAR(5) 		NOT NULL,
delSize DECIMAL(8,3)
);
-- Populate Deliveries
INSERT INTO Deliveries(id,categ,delSize)
values
	(1,	'pot',		1.500),
	(2,	'pot',		2.250),
	(3,	'pot',		2.625),
	(4,	'pot',		4.250),
	(5,	'plant',	NULL),
	(6,	'bulb',		NULL),
	(7,	'hedge',	18.000),
	(8,	'shrub',	24.000),
	(9,	'tree',		36.000)
	;

CREATE TABLE FlowersInfo(
id 			INT (3)		PRIMARY KEY,
comName 	CHAR(30),
latName 	CHAR(35),
cZone 		INT(2),
hZone 		INT(2),
deliver 	INT(1),
sunNeeds	CHAR(5),
FOREIGN KEY (cZone) REFERENCES Zones(id),
FOREIGN KEY (hZone) REFERENCES Zones(id),
FOREIGN KEY (deliver) REFERENCES Deliveries(id)
);	

-- Populate FlowersInfo
INSERT INTO FlowersInfo(id,comName,latName,cZone,hZone,deliver,sunNeeds)
values
	(101,'Lady Fern',				'Atbyrium filix-femina',			 2, 9, 5,'SH'),
	(102,'Pink Caladiums',			'C.x bortulanum',					10,10, 6,'PtoSH'),
	(103,'Lily-Of-the-Valley',		'Convallaria majalis',				 2, 8, 5,'PtoSH'),
	(105,'Purple Liatris',			'Liatris spicata',					 3, 9, 6,'StoP'),
	(106,'Black Eyed Susan',		'Rudbeckia fulgida var. specios',	 4,10, 2,'Stop'),
	(107,'Nikko Blue Hydrangea',	'Hydrangea macrophylla',			 5, 9, 4,'StoSH'),
	(108,'Variegated Weigela',		'W. florida Variegata',				 4, 9, 8,'StoP'),
	(110,'Lombardy Poplar',			'Populus nigra Italica',			 3, 9, 9,'S'),
	(111,'Purple Leaf Plum Hedge',	'Prunus x cistena',					 2, 8, 7,'S'),
	(114,'Thorndale Ivy',			'Hedera belix Thorndale',			 3, 9, 1,'StoSH');
	;
-- Queries

-- a) the total number of zones
SELECT COUNT(*) FROM zones;

-- b) the number of flowers per cool zones
SELECT cZone, COUNT(*) AS FlowerTotal FROM FlowersInfo GROUP BY cZone;

-- c) common names of the plants that have delivery sizes less than 5
SELECT A.comName AS 'common Names Of Flowers Where Delivery Size Less Than 5' FROM FlowersInfo A INNER JOIN Deliveries B ON A.deliver = B.id WHERE delSize < 5;

-- d) common names of the plants that require full sun (i.e. sun needs contains 'S'
SELECT comName AS 'common name of plants requiring full sun' FROM FlowersInfo WHERE sunNeeds like '%S%';

-- e) all delivery category names order alphabetically (without repitition)
SELECT categ AS 'Delivery Category Names' FROM Deliveries GROUP BY categ ORDER BY categ;

-- f) the exact output(note that it is order by Name)--see output table example
SELECT A.comName AS Name, B.lowerTemp AS 'Cool Zone (low)', B.higherTemp AS 'Cool Zone (high)', C.categ AS 'Delivery Category' FROM FlowersInfo A INNER JOIN Zones B ON A.cZone = B.id INNER JOIN Deliveries C ON A.deliver = C.id ORDER BY A.comName;

-- g) plant names that have the same hotzone as "Pink Caladiums" (your solution MUST get the hot zone of 
-- 	  in a variable
set @pink_caladium_zone := (SELECT hZone FROM FlowersInfo WHERE comName = 'Pink Caladiums');
SELECT comName AS 'Plant names that have the same Hot Zone as "Pink Caladiums"' FROM FlowersInfo WHERE hZone = @pink_caladium_zone AND NOT comName = 'Pink Caladiums';

-- h) the total number of plants, the minimum delivery size, the maximum delivery size, and the average
-- 	  size based on the plants thathave delivery sizes (note that the average value should be rounded
-- 	  using two decimals). - see table output example
SELECT COUNT(*) AS Total, TRUNCATE(MIN(delSize),1) AS 'Min', TRUNCATE(MAX(delSize),0) AS 'Max', ROUND(AVG(delSize),2) AS 'Average' FROM Deliveries WHERE delSize IS NOT NULL;

-- i) the Latin name of the plant that has the word "Eyed" in its name (you must use LIKE in is query total to
-- 	  get full credit).
SELECT latName AS 'Latin names for common plants that contain "eyed"' FROM FlowersInfo WHERE comName like '%Eyed%';

-- j) the exact output(ordered by Category and then by Name): - see table example
SELECT A.categ AS 'Category',B.comName AS 'Name' FROM Deliveries A INNER JOIN FlowersInfo B ON A.id = B.deliver ORDER BY A.categ,B.comName;