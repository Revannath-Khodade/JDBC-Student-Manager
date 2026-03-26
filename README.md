# JDBC-Student-Manager (JDBC)

A console-based Student Management System built using Core Java and JDBC with MySQL.  
This project demonstrates CRUD operations, transaction management, and secure database interaction using PreparedStatement.

---

## 🚀 Features
- Insert Student Records
- Update Student Details
- Delete Student Records
- Display All Students
- Transaction Management (Commit & Rollback)
- SQL Injection Prevention using PreparedStatement
- Menu-driven Console Interface

---

## 🛠 Technologies Used
- Java (Core Java)
- JDBC (Java Database Connectivity)
- MySQL
- IntelliJ IDEA

---

## 🗄 Database Schema

```sql
CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
