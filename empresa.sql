
create database empresa;
use empresa;

CREATE TABLE departamentos (
 num_dpt  int PRIMARY KEY,
 nombre_dpt  VARCHAR(20),
 planta  int,
 edificio   VARCHAR(30),
 ciudad   varchar(20)
)Engine=InnoDB;

CREATE TABLE proyectos (
 num_proy  int PRIMARY KEY,
 nombre_proy  VARCHAR(10),
 producto   VARCHAR(20),
 presupuesto  int
)Engine=InnoDB;

CREATE TABLE empleados (
 num_empl INT PRIMARY KEY,
 nombre_empl  VARCHAR(30),
 sueldo   int,
 ciudad VARCHAR(20),
 num_dpt  int, 
 num_proy  int,
 Foreign Key (num_dpt) REFERENCES departamentos(num_dpt)
 on delete cascade on update CASCADE,
 Foreign Key (num_proy) REFERENCES proyectos(num_proy)
 on delete cascade on update CASCADE
)Engine=InnoDB;

INSERT INTO departamentos VALUES (1,'DIRECCION',10,'PAU CLARIS','BARCELONA');
INSERT INTO departamentos VALUES (2,'DIRECCION',8,'RIO ROSAS','MADRID');
INSERT INTO departamentos VALUES (3,'MARKETING',1,'PAU CLARIS','BARCELONA');
INSERT INTO departamentos VALUES (4,'CALIDAD',5,'RIO ROSAS','MADRID');
INSERT INTO departamentos VALUES (5,'PRODUCCION',1,'PAU CLARIS','BARCELONA');
INSERT INTO departamentos VALUES (6,'RRHH',1,'PAU CLARIS','BARCELONA');

INSERT INTO proyectos VALUES (1,'IBDTEL','TELEVISION',1000000);
INSERT INTO proyectos VALUES (2,'IBDVID','VIDEO',500000);
INSERT INTO proyectos VALUES (3,'IBDTEF','TELEFONIA',750000);
INSERT INTO proyectos VALUES (4,'IBDRAD','RADIO',250000);
INSERT INTO proyectos VALUES (5,'IBDCOM','COMUNICACIONES',500000);

INSERT INTO empleados VALUES (1,'CARMEN',40000,'MATARO',1,1);
INSERT INTO empleados VALUES (2,'EUGENIA',35000,'TOLEDO',2,2);
INSERT INTO empleados VALUES (3,'JOSEP',25000,'SITGES',3,1);
INSERT INTO empleados VALUES (4,'RICARDO',40000,'BARCELONA',1,1);
INSERT INTO empleados VALUES (11,'NURIA',20000,'VIC',3,2);
INSERT INTO empleados VALUES (12,'NURIA',20000,'MATARO',5,5);
INSERT INTO empleados VALUES (13,'ALBERT',20000,'BARCELONA',1,5);
INSERT INTO empleados VALUES (14,'MANEL',30000,'TARRASA',4,3);
INSERT INTO empleados VALUES (15,'JORDI',30000,'BARCELONA',5,3);
INSERT INTO empleados VALUES (20,'MARIA',18000,'GETAFE',5,2);
INSERT INTO empleados VALUES (21,'IGNACIO',37000,'MADRID',4,5);
