CREATE DEFINER=`USER`@`localhost` PROCEDURE `searchHouse`(
IN floor_ra1 INT, IN floor_ra2 INT,
IN bedroom_ra1 INT, IN bedroom_ra2 INT,
IN kitchen_in INT, IN bathroom_in INT,
IN poolYesno INT, IN garageYesno INT,
IN price_ra1 INT, IN price_ra2 INT
)
BEGIN
SELECT  
		`house_list`.`house_id`,
        `house_list`.`floor_no`,
        `house_list`.`bedroom_no`,
        `house_list`.`kitchen_no`,
        `house_list`.`bathroom_no`,
        `house_list`.`pool_yes_no`,
        `house_list`.`garage__yes_no`,
        `house_list`.`price`,
        `house_list`.`zip_id`,
        `area_table`.`area_name`,
        `province_table`.`province_id`,
        `province_table`.`province_name`
		FROM `province_table` JOIN `area_table` ON `area_table`.`province_id` = `province_table`.`province_id`
		JOIN `house_list` ON `house_list`.`zip_id` = `area_table`.`zip_id` 
		AND
		(floor_no = floor_ra1 OR floor_no = floor_ra2) AND
		(bedroom_no = bedroom_ra1 OR bedroom_no = bedroom_ra2) AND
		(kitchen_no = kitchen_in) AND
		(bathroom_no = bathroom_in) AND
		(pool_yes_no = poolYesno) AND
		(garage__yes_no = garageYesno) AND
		(price > price_ra1 AND price < price_ra2);
END