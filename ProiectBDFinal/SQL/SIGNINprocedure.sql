use administratiefacultate;

drop procedure if exists setNrContractAdmin;
DELIMITER //
CREATE  PROCEDURE `setNrContractAdmin`(id int, newNrContract int)
BEGIN
	update administrator set nr_Contract = newNrContract where _id = id;
END;
// DELIMITER ;


drop procedure if exists setNrContractProf;
DELIMITER //
CREATE PROCEDURE `setNrContractProf`(id int, newNrContract int)
BEGIN
	update profesor set nr_Contract = newNrContract where _id = id;
END;
// DELIMITER ;


drop procedure if exists setNrContractStud;
DELIMITER //
CREATE PROCEDURE `setNrContractStud`(id int, newNrContract int)
BEGIN
	update student set nr_Contract = newNrContract where _id = id;
END;
// DELIMITER ;


drop procedure if exists setPasswordProcedure;
DELIMITER //
CREATE PROCEDURE setPasswordProcedure(new_parola varchar(45), idCont int)
BEGIN
	update conturi set parola = new_parola where _idCont = idCont;
END;
// DELIMITER ;


drop procedure if exists signInStudentProcedure;
DELIMITER //
CREATE PROCEDURE `signInStudentProcedure`(CNP varchar(45), nume varchar(45), prenume varchar(45), adresa varchar(45), nr_telefon varchar(45), email varchar(45), IBAN varchar(45), parola varchar(45))
BEGIN
	insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values (CNP, nume, prenume, adresa, nr_telefon, email, IBAN, 0, 1, 0);
	select max(_id) into @id from student;
	select max(_idCont) into @cont from conturi;
    call setNrContractStud(@id, @cont);
	call setPasswordProcedure(parola, @cont);
END;
// DELIMITER ;


drop procedure if exists signInProfesorProcedure;
DELIMITER //
CREATE PROCEDURE `signInProfesorProcedure`(CNP varchar(45), nume varchar(45), prenume varchar(45), adresa varchar(45), nr_telefon varchar(45), email varchar(45), IBAN varchar(45), nrMinOre int, nrMaxOre int, departament varchar(45), parola varchar(45))
BEGIN
	insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values (CNP, nume, prenume, adresa, nr_telefon, email, IBAN, 0, nrMinOre, nrMaxOre, departament);
   select max(_id) into @id from profesor;
	select max(_idCont) into @cont from conturi;
    call setNrContractProf(@id, @cont);
	call setPasswordProcedure(parola, @cont);
END;
// DELIMITER ;

drop procedure if exists signInAdministratorProcedure;
DELIMITER //
CREATE PROCEDURE `signInAdministratorProcedure`(CNP varchar(45), nume varchar(45), prenume varchar(45), adresa varchar(45), nr_telefon varchar(45), email varchar(45), IBAN varchar(45), parola varchar(45))
BEGIN
	insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values (CNP, nume, prenume, adresa, nr_telefon, email, IBAN, 0);
	select max(_id) into @id from administrator;
	select max(_idCont) into @cont from conturi;
    call setNrContractAdmin(@id, @cont);
	call setPasswordProcedure(parola, @cont);
END;
// DELIMITER ;
