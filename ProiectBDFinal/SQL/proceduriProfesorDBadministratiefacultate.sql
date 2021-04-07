use administratiefacultate;

DELIMITER //
drop procedure if exists adauga_activitate;
CREATE  PROCEDURE `adauga_activitate`(nume varchar(45), idProfesor int, tip varchar(45), _data datetime,  pondere int)
BEGIN
	insert into activitatedidactica(nume, idProfesor, tip, _data, pondere) values (nume, idProfesor, tip, _data, pondere);
	select max(_id) into @newIdActivitate from activitatedidactica;
    insert into profesor_activitatedidactica(idProfesor, idActivitate, nrStudent) values(idProfesor,  @newIdActivitate,0);
END;
// DELIMITER ;


DELIMITER //
drop procedure if exists adaugare_nota;
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_nota`(idProfesor int, idStudent int, idActivitate int, nota int)
BEGIN
	insert into catalog(idProfesorAct, idStudentAct, idActivitate, nota, data) values (idProfesor, idStudent, idActivitate, nota, current_date());
END;
// DELIMITER ;

DELIMITER //
drop procedure if exists schimba_pondere;
CREATE DEFINER=`root`@`localhost` PROCEDURE `schimba_pondere`(idActivitate int, pond int)
BEGIN
	update activitatedidactica set pondere = pond where _id = idActivitate;
END;
// DELIMITER ;

DELIMITER //
drop procedure if exists schimbare_nota;
CREATE DEFINER=`root`@`localhost` PROCEDURE `schimbare_nota`(idProfesor int, idStudent int, idActiv int, new_nota int)
BEGIN
	update catalog set nota = new_nota where idProfesorAct = idProfesor and idStudentAct = idStudent and idActivitate = idActivitate;
END;
// DELIMITER ;

