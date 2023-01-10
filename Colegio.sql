create database Colegio;
use Colegio;

CREATE TABLE Alumnos (
	Id 		  int primary key,
	Nombre 	  varchar(15),
	Apellido  varchar(15),
	Nota 	  tinyint)Engine=InnoDB;

Create table Cambios(
	Id 		    int primary key,
	Tipo 	    varchar(2),
	Matricula   int,
	Incremento 	tinyint)Engine=InnoDB;

INSERT INTO Alumnos VALUES (1,'Pedro','Martinez',4);
INSERT INTO Alumnos VALUES (2,'Maria','Ruiz',5);
INSERT INTO Alumnos VALUES (3,'Juan','Sanchez',6);
INSERT INTO Alumnos VALUES (4,'Alberto','Gonzalez',5);
INSERT INTO Alumnos VALUES (5,'Alex','Lozano',5);
INSERT INTO Alumnos VALUES (6,'Alberto','Gonzalez',5);
INSERT INTO Alumnos VALUES (7,'Marian','Bellota',5);
INSERT INTO Alumnos VALUES (8,'Adolfo','Garcia',5);
INSERT INTO Alumnos VALUES (9,'Josefina','Ortiz',7);
INSERT INTO Alumnos VALUES (10,'Natalia','Lopez',3);
INSERT INTO Alumnos VALUES (11,'Pablo','Diza',8);
INSERT INTO Alumnos VALUES (12,'Juan Carlos','Jimenez',7);
INSERT INTO Alumnos VALUES (13,'Isabel','Marquez',2);
INSERT INTO Alumnos VALUES (14,'Julia','Fernandez',7);
INSERT INTO Alumnos VALUES (15,'Sandra','Endrino',7);

INSERT INTO Cambios VALUES (11,'BJ',5,0); 
INSERT INTO Cambios VALUES (12,'MD',4,1);          
INSERT INTO Cambios VALUES (13,'MD',3,2);          
INSERT INTO Cambios VALUES (14,'BJ',2,0); 
INSERT INTO Cambios VALUES (15,'MD',1,3);          
INSERT INTO Cambios VALUES (20,'BJ',14,0); 
INSERT INTO Cambios VALUES (21,'MD',6,1);          
INSERT INTO Cambios VALUES (22,'MD',7,2);         
INSERT INTO Cambios VALUES (23,'BJ',9,0); 
INSERT INTO Cambios VALUES (24,'MD',11,2); 