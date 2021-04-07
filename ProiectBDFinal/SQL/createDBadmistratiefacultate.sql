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
  _data datetime not null,
  pondere int,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
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
  PRIMARY KEY (`_id`))
ENGINE = InnoDB
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
  idActivitate int not null,
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`chat` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `mess` VARCHAR(400) NULL DEFAULT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `idStudent` INT NULL DEFAULT NULL,
  PRIMARY KEY (`_id`))
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `administratiefacultate`.`grupuri`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administratiefacultate`.`grupuri` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `idMaterie` INT NULL DEFAULT NULL,
  `idStudent` INT NULL DEFAULT NULL,
  `idChat` INT NULL DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE INDEX `idMaterie_UNIQUE` (`idMaterie` ASC) VISIBLE,
  INDEX `_id_idx` (`idChat` ASC) VISIBLE,
  CONSTRAINT `_id`
    FOREIGN KEY (`idChat`)
    REFERENCES `administratiefacultate`.`chat` (`_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS