DROP TABLE IF EXISTS roombookingdetails;
commit;
CREATE TABLE  roombookingdetails (
id INT AUTO_INCREMENT  PRIMARY KEY,
room_id VARCHAR(250),
max_adults INT,
max_children INT,
max_child_age INT,
from_date DATE,
to_date DATE,
base_room_price DECIMAL(20,2),
extra_adult DECIMAL(20,2),
extra_child DECIMAL(20,2)
);
commit;

INSERT INTO roombookingdetails(room_id,max_adults,max_children,max_child_age,from_date,to_date,base_room_price,extra_adult,extra_child) VALUES
('R123',3,2,12,PARSEDATETIME('01-SEP-2021' , 'dd-MMM-yy' ,  'en'),PARSEDATETIME('31-DEC-2021' , 'dd-MMM-yy' ,  'en'),1000,500,200),
('R123',3,2,12,PARSEDATETIME('01-FEB-2022' , 'dd-MMM-yy' ,  'en'),PARSEDATETIME('31-DEC-2022' , 'dd-MMM-yy' ,  'en'),2000,700,300),
('R124',3,2,12,PARSEDATETIME('01-SEP-2021' , 'dd-MMM-yy' ,  'en'),PARSEDATETIME('31-DEC-2021' , 'dd-MMM-yy' ,  'en'),2000,700,400),
('R124',3,2,12,PARSEDATETIME('01-FEB-2022' , 'dd-MMM-yy' ,  'en'),PARSEDATETIME('31-DEC-2022' , 'dd-MMM-yy' ,  'en'),3000,900,600)
;
commit;

DROP TABLE IF EXISTS userdetails;
commit;
CREATE TABLE  userdetails (
user_id INT  PRIMARY KEY,
tier VARCHAR(250),
coins INT,
referrer_id INT,
total_amount_spent INT,
);
commit;

Insert Into userdetails (user_id,tier,coins,referrer_id,total_amount_spent) VALUES (1,'Gold',0,0,1000),
(21,'Gold',0,0,1000),
(22,'Gold',0,0,1000),
(23,'Gold',0,0,1000),
(24,'Gold',0,0,1000);
commit;

DROP TABLE IF EXISTS bookings;
commit;
CREATE TABLE  bookings (
trip_id INT AUTO_INCREMENT  PRIMARY KEY,
user_id INT ,
start_date DATE,
end_date DATE,
referrer_id INT,
trip_type VARCHAR(250),
trip_status VARCHAR(250),
amount_spent INT,
is_referral_booking INT
);
commit;
DROP TABLE IF EXISTS coins;
commit;
CREATE TABLE  coins (
tier VARCHAR(250),
trip_type VARCHAR(250),
coins_value INT
);
commit;
Insert Into coins (tier,trip_type,coins_value) VALUES
('Silver','Domestic',500),
('Silver','International',1000),
('Gold','Domestic',1000),
('Gold','International',2000),
('Platinum','Domestic',2000),
('Platinum','International',4000);
commit;




