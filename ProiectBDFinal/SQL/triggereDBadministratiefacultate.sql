use  administratiefacultate;

drop trigger if exists  insertIntoConturiAfterinsertIntoStudent;

DELIMITER //
create trigger insertIntoConturiAfterinsertIntoStudent after insert on student
for each row
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from student where _id = new._id;
        select email into @new_id from student where _id = new._id;
        select nume into @newparola from student where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 1, @newidUtilizator);
	-- end if;
end // 
DELIMITER ;

drop trigger if exists  insertIntoConturiAfterinsertIntoProfesor;

DELIMITER //
create trigger insertIntoConturiAfterinsertIntoProfesor after insert on profesor
for each row
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from profesor where _id = new._id;
        select email into @new_id from profesor  where _id = new._id;
        select nume into @newparola from profesor  where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 2, @newidUtilizator);
	-- end if;
end // 
DELIMITER ;

drop trigger if exists  insertIntoConturiAfterinsertIntoAdministrator;

DELIMITER //
create trigger insertIntoConturiAfterinsertIntoAdministrator after insert on administrator
for each row
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from administrator where _id = new._id;
        select email into @new_id from administrator  where _id = new._id;
        select nume into @newparola from administrator  where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 3, @newidUtilizator);
	-- end if;
end // 
DELIMITER ; 

