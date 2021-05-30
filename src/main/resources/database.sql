-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema conferences
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema conferences
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS conferences;
CREATE SCHEMA IF NOT EXISTS `conferences` DEFAULT CHARACTER SET utf8 ;
USE `conferences` ;

-- -----------------------------------------------------
-- Table `conferences`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conferences`.`user` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `username` VARCHAR(16) NOT NULL,
    `email` VARCHAR(255) NULL,
    `password` VARCHAR(32) NOT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `role` TINYINT(3) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conferences`.`conference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conferences`.`conference` (
                                                          `id` INT NOT NULL AUTO_INCREMENT,
                                                          `title` VARCHAR(128) NOT NULL,
    `description` VARCHAR(2048) NULL,
    `creator_id` INT NOT NULL,
    `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` TINYINT(3) ZEROFILL NOT NULL,
    `place` VARCHAR(128) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_conference_user_idx` (`creator_id` ASC) VISIBLE,
    CONSTRAINT `fk_conference_user`
    FOREIGN KEY (`creator_id`)
    REFERENCES `conferences`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `conferences`.`presentation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conferences`.`presentation` (
                                                            `id` INT NOT NULL AUTO_INCREMENT,
                                                            `title` VARCHAR(128) NOT NULL,
    `description` VARCHAR(2048) NULL,
    `conference_id` INT NOT NULL,
    `speaker_id` INT NOT NULL,
    `status` TINYINT(1) ZEROFILL NOT NULL,
    `presentationcol` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_presentation_conference1_idx` (`conference_id` ASC) VISIBLE,
    INDEX `fk_presentation_user1_idx` (`speaker_id` ASC) VISIBLE,
    CONSTRAINT `fk_presentation_conference1`
    FOREIGN KEY (`conference_id`)
    REFERENCES `conferences`.`conference` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_presentation_user1`
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conferences`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `conferences`.`user_has_conference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conferences`.`user_has_conference` (
                                                                   `user_id` INT NOT NULL,
                                                                   `conference_id` INT NOT NULL,
                                                                   `is_present` TINYINT(1) ZEROFILL NOT NULL,
    PRIMARY KEY (`user_id`, `conference_id`),
    INDEX `fk_user_has_conference_conference1_idx` (`conference_id` ASC) VISIBLE,
    INDEX `fk_user_has_conference_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_conference_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `conferences`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_conference_conference1`
    FOREIGN KEY (`conference_id`)
    REFERENCES `conferences`.`conference` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
