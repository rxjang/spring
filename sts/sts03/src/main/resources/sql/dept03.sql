CREATE TABLE `xe`.`dept03` (
  `deptno` INT NOT NULL AUTO_INCREMENT,
  `dname` VARCHAR(15) NOT NULL,
  `loc` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`deptno`));
  
 
 insert into dept03 (dname,loc) values ('영업1','서울');
 insert into dept03 (dname,loc) values ('영업2','부산');

 
 select * from dept03;