CREATE TABLE `province_table` (
  `province_id` int NOT NULL,
  `province_name` varchar(45) NOT NULL,
  PRIMARY KEY (`province_id`));
  
CREATE TABLE `area_table` (
  `zip_id` int NOT NULL,
  `area_name` varchar(300) NOT NULL,
  `province_id` int NOT NULL,
  PRIMARY KEY (`zip_id`),
  KEY `region_id_idx` (`province_id`),
  CONSTRAINT `region_id` FOREIGN KEY (`province_id`) REFERENCES `province_table` (`province_id`));
  
  CREATE TABLE `house_list` (
  `house_id` int NOT NULL,
  `floor_no` int NOT NULL,
  `bedroom_no` int NOT NULL,
  `kitchen_no` int NOT NULL,
  `bathroom_no` int NOT NULL,
  `pool_yes_no` tinyint NOT NULL,
  `garage__yes_no` tinyint NOT NULL,
  `price` int NOT NULL,
  `zip_id` int NOT NULL,
  PRIMARY KEY (`house_id`),
  KEY `province_id_idx` (`zip_id`),
  CONSTRAINT `province_id` FOREIGN KEY (`zip_id`) REFERENCES `area_table` (`zip_id`));
  
  CREATE TABLE `reference_number` (
  `reference_num` int NOT NULL,
  `house_Id` int NOT NULL,
  PRIMARY KEY (`reference_num`),
  KEY `house_id_idx` (`house_Id`),
  CONSTRAINT `house_Id` FOREIGN KEY (`house_Id`) REFERENCES `house_list` (`house_id`));
  
  CREATE TABLE `user_login` (
  `email_address` varchar(45) NOT NULL,
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`email_address`));
  
  CREATE TABLE `user_table` (
  `contact_num` bigint NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `bday` varchar(45) NOT NULL,
  `home_address` varchar(200) NOT NULL,
  `email_address` varchar(200) NOT NULL,
  PRIMARY KEY (`contact_num`),
  KEY `email_address_idx` (`email_address`),
  CONSTRAINT `email_address` FOREIGN KEY (`email_address`) REFERENCES `user_login` (`email_address`));
  
  