-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema administratiefacultate
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema administratiefacultate
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `administratiefacultate` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `administratiefacultate` ;

-- -----------------------------------------------------
-- Table `administratiefacultate`.`activitatedidactica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`activitatedidactica` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(45) NOT NULL,
  `idProfesor` INT NOT NULL,
  `tip` INT NOT NULL,
  `pondere` INT NULL DEFAULT NULL,
  `activitatedidacticacol` VARCHAR(45) NULL,
  `descriere` VARCHAR(100) NULL,
  `nrMaxStudenti` INT NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`administrator` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `CNP` VARCHAR(45) NOT NULL,
  `nume` VARCHAR(45) NOT NULL,
  `prenume` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `nr_telefon` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  `nr_Contract` INT NOT NULL,
  `rank` INT NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`profesor` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `CNP` VARCHAR(45) NOT NULL,
  `nume` VARCHAR(45) NOT NULL,
  `prenume` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `nr_telefon` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  `nr_Contract` INT NOT NULL,
  `nrMinOre` INT NOT NULL,
  `nrMaxOre` INT NOT NULL,
  `departament` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`profesor_activitatedidactica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`profesor_activitatedidactica` (
  `idProfesor` INT NOT NULL,
  `idActivitate` INT NOT NULL,
  `nrStudent` INT NOT NULL,
  INDEX `idProfesor` (`idProfesor` ASC) VISIBLE,
  INDEX `idActivitate` (`idActivitate` ASC) VISIBLE,
  CONSTRAINT `profesor_activitatedidactica_ibfk_1`
    FOREIGN KEY (`idProfesor`)
    REFERENCES `administratiefacultate`.`profesor` (`_id`),
  CONSTRAINT `profesor_activitatedidactica_ibfk_2`
    FOREIGN KEY (`idActivitate`)
    REFERENCES `administratiefacultate`.`activitatedidactica` (`_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`student` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `CNP` VARCHAR(45) NOT NULL,
  `nume` VARCHAR(45) NOT NULL,
  `prenume` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `nr_telefon` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  `nr_Contract` INT NOT NULL,
  `anStudiu` VARCHAR(45) NOT NULL,
  `nrOre` INT NOT NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`student_activitatedidactica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`student_activitatedidactica` (
  `idStudent` INT NOT NULL,
  `idActivitate` INT NOT NULL,
  `nrStudent` INT NOT NULL,
  INDEX `idStudent` (`idStudent` ASC) VISIBLE,
  INDEX `idActivitate` (`idActivitate` ASC) VISIBLE,
  CONSTRAINT `student_activitatedidactica_ibfk_1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `administratiefacultate`.`student` (`_id`),
  CONSTRAINT `student_activitatedidactica_ibfk_2`
    FOREIGN KEY (`idActivitate`)
    REFERENCES `administratiefacultate`.`activitatedidactica` (`_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`catalog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`catalog` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `idProfesorAct` INT NOT NULL,
  `idStudentAct` INT NOT NULL,
  `idActivitate` INT NOT NULL,
  `nota` INT NOT NULL,
  `data` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`_id`),
  INDEX `idProfesor_idx` (`idProfesorAct` ASC) VISIBLE,
  INDEX `idStudent_idx` (`idStudentAct` ASC) VISIBLE,
  CONSTRAINT `idProfesor`
    FOREIGN KEY (`idProfesorAct`)
    REFERENCES `administratiefacultate`.`profesor_activitatedidactica` (`idProfesor`),
  CONSTRAINT `idStudent`
    FOREIGN KEY (`idStudentAct`)
    REFERENCES `administratiefacultate`.`student_activitatedidactica` (`idStudent`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`grupuri`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`grupuri` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `idMaterie` INT NULL DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE INDEX `idMaterie_UNIQUE` (`idMaterie` ASC) VISIBLE,
  CONSTRAINT `grupuri_ibfk_1`
    FOREIGN KEY (`idMaterie`)
    REFERENCES `administratiefacultate`.`student_activitatedidactica` (`idActivitate`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`chat` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL DEFAULT NULL,
  `idStudent` INT NULL DEFAULT NULL,
  `idGrup` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idGrup` ASC) VISIBLE,
  CONSTRAINT `_id`
    FOREIGN KEY (`idGrup`)
    REFERENCES `administratiefacultate`.`grupuri` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`conturi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`conturi` (
  `_idCont` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NULL DEFAULT NULL,
  `parola` VARCHAR(45) NULL DEFAULT NULL,
  `restrictie` INT NULL DEFAULT NULL,
  `idUtilizator` INT NULL DEFAULT NULL,
  PRIMARY KEY (`_idCont`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`Calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`Calendar` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `idActivitate` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idActivitate` ASC) VISIBLE,
    FOREIGN KEY (`idActivitate`)
    REFERENCES `administratiefacultate`.`activitatedidactica` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`Mesaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`Mesaje` (
  `_id` INT NOT NULL,
  `mesaj` VARCHAR(400) NULL,
  `idChat` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idChat` ASC) VISIBLE,
  
    FOREIGN KEY (`idChat`)
    REFERENCES `administratiefacultate`.`chat` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`Activitate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`Activitate` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(45) NULL,
  `nrMinParticipanti` INT NULL,
  `termenLimita` DATE NULL,
  `Activitatecol` VARCHAR(45) NULL,
  `idGrup` INT NULL,
  `idProfesor` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idGrup` ASC) VISIBLE,
  
    FOREIGN KEY (`idGrup`)
    REFERENCES `administratiefacultate`.`grupuri` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`Participanti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`Participanti` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `idStudent` INT NULL,
  `idActivitate` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idActivitate` ASC) VISIBLE,
 
    FOREIGN KEY (`idActivitate`)
    REFERENCES `administratiefacultate`.`Activitate` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`ListaMembri`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`ListaMembri` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `idStudent` INT NULL,
  `idGrup` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `_id_idx` (`idGrup` ASC) VISIBLE,
  
    FOREIGN KEY (`idGrup`)
    REFERENCES `administratiefacultate`.`grupuri` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `administratiefacultate` ;

-- -----------------------------------------------------
-- procedure adauga_activitate
-- -----------------------------------------------------

DELIMITER $$
USE `administratiefacultate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `adauga_activitate`(nume varchar(45), idProfesor int, tip int, _data datetime,  pondere int)
BEGIN
	insert into activitatedidactica(nume, idProfesor, tip, _data, pondere) values (nume, idProfesor, tip, _data, pondere);
	select max(_id) into @newIdActivitate from activitatedidactica;
    insert into profesor_activitatedidactica(idProfesor, idActivitate, nrStudent) values(idProfesor,  @newIdActivitate,0);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure adaugare_nota
-- -----------------------------------------------------

DELIMITER $$
USE `administratiefacultate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `adaugare_nota`(idProfesor int, idStudent int, idActivitate int, nota int)
BEGIN
	insert into catalog(idProfesorAct, idStudentAct, idActivitate, nota, data) values (idProfesor, idStudent, idActivitate, nota, current_date());
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure schimba_pondere
-- -----------------------------------------------------

DELIMITER $$
USE `administratiefacultate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `schimba_pondere`(idActivitate int, pond int)
BEGIN
	update activitatedidactica set pondere = pond where _id = idActivitate;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure schimbare_nota
-- -----------------------------------------------------

DELIMITER $$
USE `administratiefacultate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `schimbare_nota`(idProfesor int, idStudent int, idActiv int, new_nota int)
BEGIN
	update catalog set nota = new_nota where idProfesorAct = idProfesor and idStudentAct = idStudent and idActivitate = idActivitate;
END$$

DELIMITER ;
USE `administratiefacultate`;

DELIMITER $$
USE `administratiefacultate`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `administratiefacultate`.`insertIntoConturiAfterinsertIntoAdministrator`
AFTER INSERT ON `administratiefacultate`.`administrator`
FOR EACH ROW
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from administrator where _id = new._id;
        select email into @new_id from administrator  where _id = new._id;
        select nume into @newparola from administrator  where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 3, @newidUtilizator);
	-- end if;
end$$

USE `administratiefacultate`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `administratiefacultate`.`insertIntoConturiAfterinsertIntoProfesor`
AFTER INSERT ON `administratiefacultate`.`profesor`
FOR EACH ROW
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from profesor where _id = new._id;
        select email into @new_id from profesor  where _id = new._id;
        select nume into @newparola from profesor  where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 2, @newidUtilizator);
	-- end if;
end$$

USE `administratiefacultate`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `administratiefacultate`.`insertIntoConturiAfterinsertIntoStudent`
AFTER INSERT ON `administratiefacultate`.`student`
FOR EACH ROW
begin
	 -- if _id = new._id then
		select _id into @newidUtilizator from student where _id = new._id;
        select email into @new_id from student where _id = new._id;
        select nume into @newparola from student where _id = new._id;
        
        insert into conturi(id, parola, restrictie, idUtilizator) values(@new_id,  @newparola, 1, @newidUtilizator);
	-- end if;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

 