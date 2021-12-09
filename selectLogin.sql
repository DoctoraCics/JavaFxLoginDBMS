CREATE DEFINER=`USER`@`localhost` PROCEDURE `selectLogin`(IN email VARCHAR(45))
BEGIN
	SELECT * FROM caliyxdb.user_login WHERE email_address=email;
END