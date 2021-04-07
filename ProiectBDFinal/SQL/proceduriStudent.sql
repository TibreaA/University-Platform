
drop procedure if exists inscriereLaActivitate;
DELIMITER //
CREATE PROCEDURE inscriereLaActivitate(idStud int, idActiv int)
BEGIN
	declare _nr int default 0;
    declare _nr1 int default 0;
	select max(nrStudent) into _nr1 from student_activitatedidactica where idActivitate = idActiv;
    if _nr1 is null then
		set _nr = 1; 
	else 
		set _nr = _nr1 + 1;
	end if;
    insert into student_activitatedidactica values (idStud, idActiv, _nr);
    update student_activitatedidactica set nrStudent = _nr where idActivitate = idActiv;
	update profesor_activitatedidactica set nrStudent = _nr where idActivitate = idActiv;
END;
// DELIMITER ;


drop procedure if exists registrationForTheActivity;
DELIMITER //
CREATE PROCEDURE registrationForTheActivity(idStud int, nameActivity varchar(45), typeActivity int)
BEGIN
	declare _nr int default 0;
    declare _nr1 int default 0;
    declare _idActiv int default 0;
    declare _NrStud int default -9999;
    
    select min(nrStudent) from profesor_activitatedidactica 
    where profesor_activitatedidactica.idActivitate = Any(
    select activitatedidactica._id from activitatedidactica 
	inner join calendar
	on activitatedidactica._id = calendar.idActivitate
	left outer join student_activitatedidactica
	on activitatedidactica._id <> student_activitatedidactica.idActivitate
	where activitatedidactica.nume = nameActivity and idStudent = idStud);
    
#    select min(idActivitate) from profesor_activitatedidactica where nrStudent = _nrStud;
    
    set _NrStud = _NrStud + 1;
    
#    insert into student_activitatedidactica values (idStud, _idActiv, _nrStud);
#    update student_activitatedidactica set nrStudent = _nr where idActivitate = _idActiv;
#	update profesor_activitatedidactica set nrStudent = _nr where idActivitate = _idActiv;
END;
// DELIMITER ;

call registrationForTheActivity(2, "MS", 2);

select min(nrStudent) from profesor_activitatedidactica 
where profesor_activitatedidactica.idActivitate = Any( select activitatedidactica._id from activitatedidactica 
  inner join calendar
  on activitatedidactica._id = calendar.idActivitate
  left outer join student_activitatedidactica
  on idStudent = 2
  where activitatedidactica.nume = "MS"and calendar.idActivitate <> student_activitatedidactica.idActivitate);
    
   
   Select * FROM student_activitatedidactica 
INNER JOIN activitatedidactica ON student_activitatedidactica.idActivitate = activitatedidactica._id
INNER JOIN calendar ON activitatedidactica._id = calendar.idActivitate
WHERE  calendar.idActivitate <> 2 and calendar.date = any(Select calendar.date FROM 
calendar WHERE calendar.idActivitate = 2);