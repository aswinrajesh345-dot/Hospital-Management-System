# Hospital Management System

A Java-based Hospital Management System that allows users to record patient details and stores them in a MySQL database.

## Features
- Add new patients (Name, Age, Disease).
- View all registered patients from the database.
- Real-time connection with MySQL database.

## Prerequisites
- Java JDK installed.
- MySQL Server installed.
- MySQL Connector/J (JDBC driver).

## Setup
1. Clone this repository.
2. Create the database in MySQL:
   ```sql
   CREATE DATABASE hospital_db;
   USE hospital_db;
   CREATE TABLE patients (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), age INT, disease VARCHAR(100));