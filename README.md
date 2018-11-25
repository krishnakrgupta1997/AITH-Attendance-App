# AITH-Attendance-App
This is Desktop application built in JavaFX. It is useful to have digital attendance of the students.

Before runnig the application user will require to :

*install mysql

*create database attendance by :    "CREATE DATABASE attendance"

*create table user by :  "CREATE TABLE user(password VARCHAR (20) NOT NULL,username VARCHAR(20) NOT NULL)"

*create table student by : "CREATE TABLE student(rno BIGINT NOT NULL,  name VARCHAR(25), classesAttended INT DEFAULT 0, contact BIGINT, yoj INT, prntName VARCHAR(25), prntCntct BIGINT, course varchar(15) default NULL, address varchar(40) default NULL, PRIMARY KEY (rno, course));"

