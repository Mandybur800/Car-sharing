CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `taxi_service`.`manufacturers` (
  `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `manufacturername` VARCHAR(200) NOT NULL,
  `origin` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`manufacturer_id`),
  UNIQUE INDEX `manufacturername_UNIQUE` (`manufacturername` ASC) VISIBLE);

ALTER TABLE `taxi_service`.`manufacturers`
ADD COLUMN `deleted` TINYINT NULL DEFAULT 0 AFTER `origin`;

ALTER TABLE `taxi_service`.`manufacturers`
DROP INDEX `manufacturername_UNIQUE` ;
;

ALTER TABLE `taxi_service`.`manufacturers`
CHANGE COLUMN `manufacturername` `manufacturer_name` VARCHAR(200) NOT NULL ;

CREATE TABLE `taxi_service`.`drivers` (
                                          `id_drivers` BIGINT(11) NOT NULL,
                                          `name` VARCHAR(200) NOT NULL,
                                          `lisense_number` BIGINT(16) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`id_drivers`));

ALTER TABLE `taxi_service`.`drivers`
    CHANGE COLUMN `driver_id` `driver_id` BIGINT NOT NULL AUTO_INCREMENT ;


CREATE TABLE `taxi_service`.`cars` (
                                       `car_id` INT NOT NULL AUTO_INCREMENT,
                                       `manufacturer_id` BIGINT(11) NOT NULL,
                                       `model` VARCHAR(200) NOT NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`car_id`),
                                       UNIQUE INDEX `id_car_UNIQUE` (`car_id` ASC) VISIBLE,
                                       INDEX `manufacturer_id_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `manufacturer_id`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturers` (`manufacturer_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);

ALTER TABLE `taxi_service`.`drivers`
    CHANGE COLUMN `id_drivers` `driver_id` BIGINT NOT NULL ;

ALTER TABLE `taxi_service`.`drivers`
    CHANGE COLUMN `lisense_number` `license_number` BIGINT NOT NULL ;

CREATE TABLE `taxi_service`.`cars_drivers` (
                                               `driver_id` BIGINT(11) NOT NULL,
                                               `car_id` INT NOT NULL,
                                               INDEX `driver_id_idx` (`driver_id` ASC) VISIBLE,
                                               INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
                                               CONSTRAINT `driver_id`
                                                   FOREIGN KEY (`driver_id`)
                                                       REFERENCES `taxi_service`.`drivers` (`driver_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `car_id`
                                                   FOREIGN KEY (`car_id`)
                                                       REFERENCES `taxi_service`.`cars` (`car_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);

ALTER TABLE `taxi_service`.`cars_drivers`
    ADD COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT FIRST,
    ADD PRIMARY KEY (`id`);
;

ALTER TABLE `taxi_service`.`drivers`
    ADD COLUMN `login` VARCHAR(200) NOT NULL AFTER `deleted`,
    ADD COLUMN `password` VARCHAR(200) NOT NULL AFTER `login`,
    ADD UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE;
;
