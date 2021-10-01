DROP TABLE IF EXISTS room_bookings;

DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');


CREATE TABLE room_bookings (
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
