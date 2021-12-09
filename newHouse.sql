CREATE DEFINER=`root`@`localhost` PROCEDURE `newHouse`(
IN idHouse INT, IN floorNo INT,
IN bedroomNo INT, IN kitchenNo INT,
IN bathroomNo INT, IN poolYesNo INT,
IN garageYesNo INT, IN price INT, 
IN zip INT
)
BEGIN
INSERT INTO `caliyxdb`.`house_list` 
(`house_id`, `floor_no`, `bedroom_no`, `kitchen_no`, `bathroom_no`, `pool_yes_no`, `garage__yes_no`, `price`, `zip_id`) 
VALUES
 (idHouse, floorNo, bedroomNo, kitchenNo, bathroomNo, poolYesNo, garageYesNo, price, zip);
END