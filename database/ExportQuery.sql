-- =========================================
-- DROP TABLES IF EXIST
-- =========================================
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Programs;
DROP TABLE IF EXISTS Faculties;
DROP TABLE IF EXISTS Roles;

-- =========================================
-- CREATE TABLE: Roles
-- =========================================
CREATE TABLE Roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,

    createdBy INTEGER,
    updatedBy INTEGER,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: Faculties
-- =========================================
CREATE TABLE Faculties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,

    createdBy INTEGER,
    updatedBy INTEGER,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: Programs
-- =========================================
CREATE TABLE Programs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    facultyId INTEGER NOT NULL REFERENCES Faculties(id) ON DELETE CASCADE,

    createdBy INTEGER,
    updatedBy INTEGER,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: Users
-- =========================================
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    nip VARCHAR(20) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    name VARCHAR(100) NOT NULL,
    roleId INTEGER NOT NULL REFERENCES Roles(id),
    programId INTEGER REFERENCES Programs(id),
    isActive BOOLEAN DEFAULT TRUE,
    graduateAt DATE,

    createdBy INTEGER,
    updatedBy INTEGER,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: Courses
-- =========================================
CREATE TABLE Courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    sks INTEGER NOT NULL,
    programId INTEGER NOT NULL REFERENCES Programs(id) ON DELETE CASCADE,

    createdBy INTEGER,
    updatedBy INTEGER,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- INSERT DATA INTO Roles
-- =========================================
INSERT INTO Roles (name, description, createdBy, updatedBy)
VALUES
  ('admin', 'System administrator', 1, 1),
  ('lecturer', 'Faculty lecturer', 1, 1),
  ('student', 'University student', 1, 1);

-- =========================================
-- INSERT DATA INTO Faculties
-- =========================================
INSERT INTO Faculties (name, createdBy, updatedBy)
VALUES
  ('Faculty of Engineering', 1, 1),
  ('Faculty of Science', 1, 1),
  ('Faculty of Business', 1, 1);

-- =========================================
-- INSERT DATA INTO Programs
-- =========================================
INSERT INTO Programs (name, facultyId, createdBy, updatedBy)
VALUES
  ('Computer Science', 1, 1, 1),
  ('Physics', 2, 1, 1),
  ('Marketing', 3, 1, 1);

-- =========================================
-- INSERT DATA INTO Courses
-- =========================================
INSERT INTO Courses (name, sks, programId, createdBy, updatedBy)
VALUES
  ('Data Structures', 3, 1, 1, 1),
  ('Quantum Mechanics', 4, 2, 1, 1),
  ('Consumer Behavior', 2, 3, 1, 1);

-- =========================================
-- INSERT DATA INTO Users
-- =========================================
INSERT INTO Users (nip, password, name, roleId, programId, isActive, graduateAt, createdBy, updatedBy)
VALUES 
  ('1001', 'hashedpass1', 'Alice Admin', 1, NULL, TRUE, NULL, 1, 1),
  ('1002', 'hashedpass2', 'Bob Lecturer', 2, 1, TRUE, NULL, 1, 1),
  ('1003', 'hashedpass3', 'Charlie Student', 3, 1, TRUE, '2025-08-01', 1, 1);
