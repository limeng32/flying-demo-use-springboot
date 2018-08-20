DROP TABLE IF EXISTS account_;
CREATE TABLE account_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  email varchar(320) DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  activated tinyint(1) DEFAULT NULL,
  activateValue varchar(8) DEFAULT NULL,
  opLock int(11) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
  deputy_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8;
