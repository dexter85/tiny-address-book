SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`people__info`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`people__info` (
  `id_people` INT NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(255) NULL ,
  `last_name` VARCHAR(255) NULL ,
  `gender` ENUM('m', 'f') NULL ,
  `born` DATE NULL ,
  `town` VARCHAR(255) NULL ,
  `street` VARCHAR(255) NULL ,
  `street_number` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_people`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`system__tags`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`system__tags` (
  `id_tag` INT NOT NULL AUTO_INCREMENT ,
  `tag` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_tag`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`people__emails`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`people__emails` (
  `id_email` INT NOT NULL AUTO_INCREMENT ,
  `id_people` INT NOT NULL ,
  `id_tag` INT NULL ,
  `email` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_email`, `id_people`) ,
  INDEX `fk_people__emails_people__info_idx` (`id_people` ASC) ,
  INDEX `fk_people__emails_system__tags1_idx` (`id_tag` ASC) ,
  CONSTRAINT `fk_people__emails_people__info`
    FOREIGN KEY (`id_people` )
    REFERENCES `tinyaddressbook`.`people__info` (`id_people` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_people__emails_system__tags1`
    FOREIGN KEY (`id_tag` )
    REFERENCES `tinyaddressbook`.`system__tags` (`id_tag` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`people__phones`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`people__phones` (
  `id_phone` INT NOT NULL ,
  `id_people` INT NOT NULL ,
  `id_tag` INT NULL ,
  `phone` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_phone`, `id_people`) ,
  INDEX `fk_people__addresses_people__info1_idx` (`id_people` ASC) ,
  INDEX `fk_people__addresses_system__tags1_idx` (`id_tag` ASC) ,
  CONSTRAINT `fk_people__addresses_people__info1`
    FOREIGN KEY (`id_people` )
    REFERENCES `tinyaddressbook`.`people__info` (`id_people` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_people__addresses_system__tags1`
    FOREIGN KEY (`id_tag` )
    REFERENCES `tinyaddressbook`.`system__tags` (`id_tag` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`calendar__info`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`calendar__info` (
  `id_calendar` INT NOT NULL ,
  `id_tag` INT NULL ,
  `start` DATETIME NULL ,
  `end` DATETIME NULL ,
  `title` VARCHAR(45) NULL ,
  `description` TEXT NULL ,
  PRIMARY KEY (`id_calendar`) ,
  INDEX `fk_calendar__info_system__tags1_idx` (`id_tag` ASC) ,
  CONSTRAINT `fk_calendar__info_system__tags1`
    FOREIGN KEY (`id_tag` )
    REFERENCES `tinyaddressbook`.`system__tags` (`id_tag` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinyaddressbook`.`calendar__people`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tinyaddressbook`.`calendar__people` (
  `id_calendar_people` INT NOT NULL AUTO_INCREMENT ,
  `id_calendar` INT NOT NULL ,
  `id_people` INT NOT NULL ,
  PRIMARY KEY (`id_calendar_people`, `id_calendar`, `id_people`) ,
  INDEX `fk_calendar__people_calendar__info1_idx` (`id_calendar` ASC) ,
  INDEX `fk_calendar__people_people__info1_idx` (`id_people` ASC) ,
  CONSTRAINT `fk_calendar__people_calendar__info1`
    FOREIGN KEY (`id_calendar` )
    REFERENCES `tinyaddressbook`.`calendar__info` (`id_calendar` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_calendar__people_people__info1`
    FOREIGN KEY (`id_people` )
    REFERENCES `tinyaddressbook`.`people__info` (`id_people` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
