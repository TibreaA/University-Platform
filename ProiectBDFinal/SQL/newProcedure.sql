drop procedure if exists addDateInCalendar;
DELIMITER //
CREATE PROCEDURE addDateInCalendar(idActiv int , dateStart datetime, dateEnd datetime)
BEGIN
	declare dateInsert datetime default dateStart;
	r: while datediff(dateEnd, dateInsert) > 0 do
		insert into calendar(idActivitate, date) values (idActiv, dateInsert);
        set dateInsert = adddate(dateInsert, 7);
    end while r;
END;
// DELIMITER ;


-- -----------------------------------------------------------------------------------------------------------------------------------------------


drop procedure if exists giveUpGroupActivity;
DELIMITER //
CREATE PROCEDURE giveUpGroupActivity(nameActiv varchar(45) , idStud int)
BEGIN
	declare id int ;
	
    select min(participanti._id)  into id from participanti, activitate 
    where participanti.idStudent = idStud and participanti.idActivitate = activitate._id and
    activitate.idGrup = ANY( select grupuri._id from grupuri, activitatedidactica
    where grupuri.idMaterie = activitatedidactica._id and activitatedidactica.nume = nameActiv);
    
    while id is not null do
		delete from participanti where _id = id;
        
		select min(participanti._id)  into id from participanti, activitate
		where participanti.idStudent = idStud and participanti.idActivitate = activitate._id and
		activitate.idGrup = ANY( select grupuri._id from grupuri, activitatedidactica
		where grupuri.idMaterie = activitatedidactica._id and activitatedidactica.nume = nameActiv);
        
    end while;
END;
// DELIMITER ;

drop procedure if exists giveUpGroup;
DELIMITER //
CREATE PROCEDURE giveUpGroup(nameActiv varchar(45) , idStud int)
BEGIN
	declare id int ;
    
	select min(listamembri._id)  into id from listamembri where listamembri.idStudent = idStud and
    listamembri.idGrup = ANY( select grupuri._id from grupuri, activitatedidactica
    where grupuri.idMaterie = activitatedidactica._id and activitatedidactica.nume = nameActiv);
    
    while id is not null do
		delete from listamembri where _id = id;
        
		select min(listamembri._id)  into id from listamembri where listamembri.idStudent = idStud and
		listamembri.idGrup = ANY( select grupuri._id from grupuri, activitatedidactica
		where grupuri.idMaterie = activitatedidactica._id and activitatedidactica.nume = nameActiv);
        
    end while;
    
END;
// DELIMITER ;

drop procedure if exists giveUpCourse;
DELIMITER //
CREATE PROCEDURE giveUpCourse(nameActiv varchar(45) , idStud int)
BEGIN
	declare id int default null;
    
	call giveUpGroupActivity(nameActiv, idStud);
	call giveUpGroup(nameActiv, idStud);
    
    SET FOREIGN_KEY_CHECKS = 0;
	delete from student_activitatedidactica where idActivitate = any(select _id from activitatedidactica where nume = "MS") and idStudent = idStud;
	update student_activitatedidactica set nrStudent = nrStudent - 1 where idActivitate = any(select _id from activitatedidactica where nume = "MS");
    SET FOREIGN_KEY_CHECKS = 1;
    
END;
// DELIMITER ;

drop trigger IF exists decrementProfessorNrStud;
DELIMITER //
create trigger decrementProfessorNrStud after delete on student_activitatedidactica
for each row
begin
	 update profesor_activitatedidactica set nrStudent = nrStudent - 1 where idActivitate = old.idActivitate;
end // 
DELIMITER ; 

