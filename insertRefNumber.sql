CREATE DEFINER=`USER`@`localhost` PROCEDURE `insertRefNumber`(IN ref INT,IN houseId INT)
BEGIN
	INSERT INTO `caliyxdb`.`reference_number` (`reference_num`, `house_Id`) VALUES (ref, houseId);
END