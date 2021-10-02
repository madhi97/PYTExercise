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
select * from roombookingdetails;