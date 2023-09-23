-- Create the database
CREATE DATABASE IF NOT EXISTS toDoOk;

-- Switch to the database
USE toDoOk;

-- User Authentication Table
CREATE TABLE UserAuthentication (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Email TEXT UNIQUE, 
    Password TEXT, 
    Role TEXT 
);

-- User Profile Table
CREATE TABLE UserProfile (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    UserID INTEGER,
    Name TEXT,
    Email TEXT UNIQUE,
    ProfilePicture TEXT,
    FOREIGN KEY (UserID) REFERENCES UserAuthentication(ID)
);

-- Tasks Table
CREATE TABLE Tasks (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    UserID INTEGER,
    TaskName TEXT,
    CreationDate DATE,
    DueDate DATE,
    Status TEXT,
    FOREIGN KEY (UserID) REFERENCES UserAuthentication(ID)
);

