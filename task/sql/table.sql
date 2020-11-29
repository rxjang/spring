CREATE TABLE `task`.`sus_01` (
  `id` INT NOT NULL,
  `pname` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `info` VARCHAR(1000) NULL,
  `createtime` DATETIME NULL,
  `updatetime` DATETIME NULL,
  `category` VARCHAR(100) NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`));
    
CREATE TABLE `task`.`sus_02` (
  `id` INT NOT NULL,
  `pname` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `info` VARCHAR(1000) NULL,
  `createtime` DATETIME NULL,
  `updatetime` DATETIME NULL,
  `category` VARCHAR(100) NULL,
  `sus_01_id` INT NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX `fk_sus_01_id_idx` (`sus_01_id` ASC) VISIBLE,
  CONSTRAINT `fk_sus_01_id`
    FOREIGN KEY (`sus_01_id`)
    REFERENCES `task`.`sus_01` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);