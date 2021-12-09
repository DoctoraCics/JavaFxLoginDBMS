CREATE DEFINER=`USER`@`localhost` PROCEDURE `createnewAccount`(
IN contactNumber BIGINT, IN firstName VARCHAR(45), IN lastName VARCHAR(45),
IN birthDay VARCHAR(45), IN home_add VARCHAR(200), IN email VARCHAR(45), IN pass VARCHAR(1000)
)
BEGIN
	INSERT INTO `caliyxdb`.`user_login` 
    (`email_address`, `password`)
    VALUES (email, pass);
    
	INSERT INTO `caliyxdb`.`user_table` 
    (`contact_num`, `fname`, `lname`, `bday`, `home_address`, `email_address`)
    VALUES (contactNumber, firstName, lastName, birthday, home_add, email);
END