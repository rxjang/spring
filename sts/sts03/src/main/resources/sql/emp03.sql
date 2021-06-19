CREATE TABLE `xe`.`emp03` (
  `sabun` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NULL,
  `nalja` TIMESTAMP NULL,
  `deptno` INT NULL,
  `pay` INT NULL,
  PRIMARY KEY (`sabun`),
  INDEX `deptno_idx` (`deptno` ASC) VISIBLE,
  CONSTRAINT `deptno_fk`
    FOREIGN KEY (`deptno`)
    REFERENCES `xe`.`dept03` (`deptno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);