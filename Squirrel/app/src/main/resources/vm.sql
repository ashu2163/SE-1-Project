DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS vehicle_schedule;
DROP TABLE IF EXISTS vehicle_inventory;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS vehicle;


CREATE TABLE IF NOT EXISTS user(
userid int PRIMARY KEY,
fname varchar(20),
lname varchar(20),
uname varchar(20) NOT NULL,
password varchar(40) NOT NULL,
role varchar(10) NOT NULL DEFAULT 'other',
email varchar(40),
phone varchar(15),
street_address varchar(100),
city varchar(15),
state varchar(15),
zipcode int
);

DELETE FROM user;

INSERT INTO user(userid,fname,lname,uname,password,role,email,phone,street_address,city,state,zipcode) VALUES
(1001000,'Margaret','Jones','margarettjones','AtC33%G.mm','other','MargaretTJones@uta.mavs.edu','9725878858','2959 Worthington Drive','Dallas','TX',75244),
(1002000,'Donna','Batson','donnagbatson','ubS$_,vxL/hJ<7j','other','DonnaGBatson@uta.mavs.edu','3016851343','1556  Wilson Avenue','Carrollton','TX',75244),
(1003000,'Sarah','Maxie','sarahwmaxie','wgS2jm"+Ah=2fd{','other','SarahWMaxie@uta.mavs.edu','9048456024','1880 Ryan Road','Deadwood','SD',57732),
(1004000,'Edward','Tarleton','edwardhtarleton','9TywUg<v_Y','manager','EdwardHTarleton@uta.edu','7084245246','2851 Beeghley Street','Temple','TX',76501),
(1005000,'Heather','Peterson','heatherapeterson','$y6.jZXe"?','other','HeatherAPeterson@uta.mavs.edu','7084540101','3660 Joy Lane','Calabasas','CA',91302),
(1006000,'Thomas','Murphy','thomaslmurphy','uS@*,Vz{9>','other','ThomasLMurphy@uta.mavs.edu','5088263769','1579 Woodbridge Lane','Detroit','MI',48202),
(1007000,'Gary','Watson','garyewatson','*_5aa%GGUGt_R}:','operator','GaryEWatson@uta.edu','5617391409','3444 Maloy Court','Salina','KS',67401),
(1008000,'Gene','Bookman','genembookman','2V/J36=8+b~wA$$','other','GeneMBookman@uta.mavs.edu','9704280507','3291 Coplin Avenue','Phoenix','AZ',85003),
(1009000,'Eleanor','Peoples','eleanorgpeoples','&zr=#9%hUW9S[KC','operator','EleanorGPeoples@uta.edu','6067911291','4768 Horizon Circle','Federal Way','WA',98003),
(1001001,'James','West','jamesswest','{=uux8^qZtrbL\q','other','JamesSWest@uta.mavs.edu','2704997441','1213 Chicago Avenue','Fresno','CA',93721),
(1001100,'Helen','Baker','helenabaker','bQT%cZe$8P8mTW8','other','HelenABaker@uta.mavs.edu','2037962739','3446 Michigan Avenue','Bridgeville','PA',15017),
(1001200,'George','Sisk','georgecsisk','vf*p!22JqxPCT5','other','GeorgeCSisk@uta.mavs.edu','2034240092','2460 Stratford Park','Bloomington','IN',47408);



CREATE TABLE IF NOT EXISTS location(
locid varchar PRIMARY KEY,
locname varchar(20) NOT NULL,
duration int(1) NOT NULL
);

DELETE FROM location;

INSERT INTO location(locid,locname,duration) VALUES
('Location 1','Cooper & UTA Blvd',2),
('Location 2','W Nedderman & Greek Row',1),
('Location 3','S Davis & W Mitchell',2),
('Location 4','Cooper & W Mitchell',3),
('Location 5','S Oak & UTA Blvd',2),
('Location 6','Spaniolo & W 1st',4),
('Location 7','Spaniolo & W Mitchell',2),
('Location 8','S Center & W Mitchell',1);


CREATE TABLE IF NOT EXISTS item(
itemid int PRIMARY KEY,
itemtype varchar(20) NOT NULL,
cost decimal(6,2) NOT NULL
);

DELETE FROM item;

INSERT INTO item(itemid,itemtype,cost) VALUES
(81,'Drinks',1.50),
(82,'Sandwiches',5.75),
(83,'Snacks',1.25);



CREATE TABLE IF NOT EXISTS vehicle(
vehid int PRIMARY KEY,
vehname varchar(20) NOT NULL
);

DELETE FROM vehicle;

INSERT INTO vehicle(vehid,vehname) VALUES
(51,'foodtruck1'),
(52,'foodtruck2'),
(53,'stationcart1'),
(54,'stationcart2'),
(55,'stationcart3'),
(56,'stationcart4'),
(57,'stationcart5');



CREATE TABLE IF NOT EXISTS vehicle_inventory(
vehid int,
itemid int,
quantity int,
available_date date,
PRIMARY KEY (vehid,itemid,available_date),
FOREIGN KEY (vehid) REFERENCES vehicle(vehid),
FOREIGN KEY (itemid) REFERENCES item(itemid)
);

DELETE FROM vehicle_inventory;

INSERT INTO vehicle_inventory(vehid,itemid,quantity,available_date) VALUES
(51,81,50,date('now')),
(51,81,50,date('now','+1 day')),
(51,82,35,date('now')),
(51,82,35,date('now','+1 day')),
(51,83,40,date('now')),
(51,83,40,date('now','+1 day')),

(52,81,50,date('now')),
(52,82,35,date('now')),
(52,83,40,date('now')),

(53,81,30,date('now')),
(53,81,30,date('now','+1 day')),
(53,82,5,date('now')),
(53,82,5,date('now','+1 day')),
(53,83,30,date('now')),
(53,83,30,date('now','+1 day')),

(54,81,30,date('now')),
(54,81,30,date('now','+1 day')),
(54,82,5,date('now')),
(54,82,5,date('now','+1 day')),
(54,83,30,date('now')),
(54,83,30,date('now','+1 day')),

(55,81,30,date('now')),
(55,81,30,date('now','+1 day')),
(55,82,5,date('now')),
(55,82,5,date('now','+1 day')),
(55,83,30,date('now')),
(55,83,30,date('now','+1 day')),

(56,81,30,date('now')),
(56,82,5,date('now')),
(56,83,30,date('now')),

(57,81,30,date('now')),
(57,81,30,date('now','+1 day')),
(57,82,5,date('now')),
(57,82,5,date('now','+1 day')),
(57,83,30,date('now')),
(57,83,30,date('now','+1 day'));



CREATE TABLE IF NOT EXISTS vehicle_schedule(
vehid int,
locid int,
opid int,
slotbegin int,
slotend int,
PRIMARY KEY (vehid,slotbegin,slotend),
FOREIGN KEY (vehid) REFERENCES vehicle(vehid),
FOREIGN KEY (locid) REFERENCES location(locid),
FOREIGN KEY (opid) REFERENCES user(userid)
);

DELETE FROM vehicle_schedule;

INSERT INTO vehicle_schedule(vehid,locid,opid,slotbegin,slotend) VALUES
(51,'Location 1',1007000,8,10),
(51,'Location 2',1009000,10,11),
(51,'Location 3',1007000,11,13),
(53,'Location 4',1009000,8,11),
(53,'Location 4',1009000,11,14),
(53,'Location 4',1009000,14,17),
(57,'Location 7',1007000,15,17);

CREATE TABLE IF NOT EXISTS cart(
userid int,
itemid int,
buy_quantity int,
PRIMARY KEY (userid,itemid),
FOREIGN KEY (userid) REFERENCES user(userid),
FOREIGN KEY (itemid) REFERENCES item(itemid)
);

DELETE FROM cart;

INSERT INTO cart(userid,itemid,buy_quantity) VALUES
(1003000,81,1),
(1001001,82,10),
(1003000,83,2);