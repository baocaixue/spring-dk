CREATE USER 'isaac'@'localhost' IDENTIFIED BY 'isaac';

CREATE SCHEMA MUSICDB;
GRANT ALL PRIVILEGES ON MUSICDB . * TO 'isaac'@'localhost';
FLUSH PRIVILEGES;

SET GLOBAL time_zone = '+8:00';