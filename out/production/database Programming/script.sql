DROP DATABASE IF EXISTS ijse;
CREATE DATABASE IF NOT EXISTS ijse;
SHOW DATABASES;
USE ijse;

CREATE TABLE IF NOT EXISTS Student(
    student_id VARCHAR (45),
    student_name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    email TEXT,
    contact VARCHAR (20),
    address TEXT,
    nic VARCHAR (45),
    CONSTRAINT PRIMARY KEY (student_id)
);
SHOW TABLES;
DESCRIBE Student;

DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
    teacher_id VARCHAR(45),
    name VARCHAR(45),
    nic VARCHAR (45),
    contact VARCHAR(45),
    CONSTRAINT PRIMARY KEY (teacher_id)

);
DESC Teacher;






DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject(
  subject_id VARCHAR(45),
  subject_name VARCHAR (45),
  credit DOUBLE ,
  teacher_id VARCHAR (45),
  CONSTRAINT PRIMARY KEY (subject_id),
   CONSTRAINT FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE

);
DESC Subject;



DROP TABLE IF EXISTS Course;

CREATE TABLE IF NOT EXISTS Course(
    course_id VARCHAR(45),
    course_name VARCHAR(45),
    cost DOUBLE,
    duration VARCHAR (45),
    subject_id VARCHAR (45),
    CONSTRAINT PRIMARY KEY (course_id),
    CONSTRAINT FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE ON UPDATE CASCADE

);
DESC Course;




DROP TABLE IF EXISTS Intake;

CREATE TABLE IF NOT EXISTS Intake(
    intake_id VARCHAR(45),
    start_date DATE ,
    intakeCol VARCHAR(45),
    description VARCHAR (45),
    course_id VARCHAR (45),
    CONSTRAINT PRIMARY KEY (intake_id),
    CONSTRAINT FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE ON UPDATE CASCADE

);
DESC Intake;






DROP TABLE IF EXISTS Registration;

CREATE TABLE IF NOT EXISTS Registration(
    registration_id VARCHAR(45),
    reg_date DATE ,
    student_id VARCHAR (45),
    intake_id VARCHAR (45),
    CONSTRAINT PRIMARY KEY (registration_id),
    CONSTRAINT FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (intake_id) REFERENCES Intake(intake_id) ON DELETE CASCADE ON UPDATE CASCADE

);
DESC Registration;


DROP TABLE IF EXISTS Payment;

CREATE TABLE IF NOT EXISTS Payment(
    payment_id INT AUTO_INCREMENT,
    date DATE ,
    cost VARCHAR(45),
    registration_id VARCHAR (45),
    CONSTRAINT PRIMARY KEY (payment_id),
    CONSTRAINT FOREIGN KEY (registration_id) REFERENCES Registration(registration_id) ON DELETE CASCADE ON UPDATE CASCADE

);
DESC Payment;


INSERT INTO Teacher (teacher_id,name,nic,contact)
VALUES ('T001','Kamal','12345678V','0761299862'),
('T002','Nimal','4586544654','0710556692'),
('T003','Amal','9898465651','0715689245');

INSERT INTO Subject (subject_id,subject_name,credit,teacher_id)
VALUES ('S001','Java',90,'T001'),
       ('S002','DBMS',80,'T002'),
       ('S003','webDevelopment',80,'T002');
SELECT * FROM Subject

INSERT INTO Course(course_id,course_name,cost,duration,subject_id)
VALUES ('C001','GDSE',300000,'2 years','S001'),
       ('C002','CMJD',60000,'6 months','S003');
SELECT * FROM Course;

INSERT INTO Intake(intake_id,start_date,intakeCol,description,course_id)
VALUES ('IN001','2023-01-01','EMPTY','this is best way in software Engineering','C001'),
       ('IN002','2023-01-06','EMPTY','web development and develop skill in course','C002');
SELECT * FROM Intake;




