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

