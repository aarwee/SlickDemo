DROP TABLE IF EXISTS subject;
CREATE TABLE IF NOT EXISTS subject(sub_id int PRIMARY KEY ,name varchar(50));
DROP TABLE IF EXISTS student_info;
CREATE TABLE IF NOT EXISTS student_info(id int PRIMARY KEY ,name varchar(50),email varchar(50));

DROP TABLE IF EXISTS sub_stud;
CREATE TABLE IF NOT EXISTS sub_stud(id int PRIMARY KEY ,sub_id int ,stud_id int );