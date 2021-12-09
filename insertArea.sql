CREATE DEFINER=`root`@`localhost` PROCEDURE `insertArea`(IN zipCode INT, IN areaName VARCHAR(300), IN provId INT)
BEGIN
	INSERT INTO `caliyxdb`.`area_table` (`zip_id`, `area_name`, `province_id`) VALUES (zipCode, areaName, provId);
END