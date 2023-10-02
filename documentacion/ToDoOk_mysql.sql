-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS toDoOk;

-- Switch to the database
USE toDoOk;

-- User Authentication Table
CREATE TABLE UserAuthentication (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(255) UNIQUE, -- Unique username
    Password VARCHAR(255), -- Password
    Role VARCHAR(255) -- User role 
);

-- User Profile Table
CREATE TABLE UserProfile (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    Name VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    ProfilePicture VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES UserAuthentication(ID)
);

-- Tasks Table
CREATE TABLE Tasks (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    TaskName VARCHAR(255),
    CreationDate DATE,
    DueDate DATE,
    Status VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES UserAuthentication(ID)
);
