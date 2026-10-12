CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    course VARCHAR(50)
);
