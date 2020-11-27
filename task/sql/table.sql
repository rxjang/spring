CREATE TABLE `task`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cname` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `task`.`sus_01` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pname` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `info` VARCHAR(1000) NULL,
  `createtime` DATETIME NULL,
  `updatetime` DATETIME NULL,
  `category_id` INT NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX `fk_sus_01_c_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_sus_01_c_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `task`.`category` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);
    
CREATE TABLE `task`.`sus_02` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pname` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `info` VARCHAR(1000) NULL,
  `createtime` DATETIME NULL,
  `updatetime` DATETIME NULL,
  `category_id` INT NULL,
  `sus_01_id` INT NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX `fk_sus_01_c_id_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_sus_01_id_idx` (`sus_01_id` ASC) VISIBLE,
  CONSTRAINT `fk_sus_02_c_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `task`.`category` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sus_01_id`
    FOREIGN KEY (`sus_01_id`)
    REFERENCES `task`.`sus_01` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);