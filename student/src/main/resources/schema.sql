DROP TABLE IF EXISTS TBL_STUDENTS;
DROP TABLE IF EXISTS TBL_COURSE;
DROP TABLE IF EXISTS TBL_SUBJECT;
DROP TABLE IF EXISTS TBL_ADDRESS;

CREATE TABLE TBL_STUDENTS (
  stuid NUMBER(20) PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  phone VARCHAR(14),
  doj TIMESTAMP,
  subjId NUMBER(15),
  courseId NUMBER(20),
  adId NUMBER(15)
);

CREATE TABLE TBL_COURSE (
  courseId NUMBER(20) PRIMARY KEY,
  cName VARCHAR(250) NOT NULL,
  duration NUMBER(1),
  subjId NUMBER(15)
);

CREATE TABLE TBL_SUBJECT (
  subjId NUMBER(15)  PRIMARY KEY,
  subjName VARCHAR(250) NOT NULL,
  score NUMBER(10) NOT NULL,
  courseId NUMBER(15)
);

CREATE TABLE TBL_ADDRESS (
  adId NUMBER(15)  PRIMARY KEY,
  stuid NUMBER(15)
);

ALTER TABLE TBL_STUDENTS ADD CONSTRAINT FK_STUDENTS_1 FOREIGN KEY (subjId) REFERENCES TBL_SUBJECT(subjId) ON DELETE CASCADE;
ALTER TABLE TBL_STUDENTS ADD CONSTRAINT FK_STUDENTS_2 FOREIGN KEY (courseId) REFERENCES TBL_COURSE(courseId) ON DELETE CASCADE;
ALTER TABLE TBL_STUDENTS ADD CONSTRAINT FK_STUDENTS_3 FOREIGN KEY (adId) REFERENCES TBL_ADDRESS(adId) ON DELETE CASCADE;

ALTER TABLE TBL_COURSE ADD CONSTRAINT FK_COURSE_1 FOREIGN KEY (subjId) REFERENCES TBL_SUBJECT(subjId) ON DELETE CASCADE;
ALTER TABLE TBL_SUBJECT ADD CONSTRAINT FK_SUBJECT_1 FOREIGN KEY (courseId) REFERENCES TBL_COURSE(courseId) ON DELETE CASCADE;
ALTER TABLE TBL_ADDRESS ADD CONSTRAINT FK_ADDRESS_1 FOREIGN KEY (stuid) REFERENCES TBL_STUDENTS(stuid) ON DELETE CASCADE;