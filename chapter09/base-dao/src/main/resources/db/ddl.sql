CREATE USER 'isaac_A'@'localhost' IDENTIFIED BY 'isaac_A';
CREATE SCHEMA MUSICDB_A;
GRANT ALL PRIVILEGES ON MUSICDB_A . * TO 'isaac_A'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'isaac_B'@'localhost' IDENTIFIED BY 'isaac_B';
CREATE SCHEMA MUSICDB_B;
GRANT ALL PRIVILEGES ON MUSICDB_B . * TO 'isaac_B'@'localhost';
FLUSH PRIVILEGES;


SET GLOBAL time_zone = '+8:00';