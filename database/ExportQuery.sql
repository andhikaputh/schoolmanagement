-- =========================================
-- DROP TABLES IF EXIST
-- =========================================
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS faculties;
DROP TABLE IF EXISTS roles;

-- =========================================
-- CREATE TABLE: roles
-- =========================================
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: faculties
-- =========================================
CREATE TABLE faculties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: programs
-- =========================================
CREATE TABLE programs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    faculty_id INTEGER NOT NULL REFERENCES faculties(id) ON DELETE CASCADE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: users
-- =========================================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nip VARCHAR(20) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    name VARCHAR(100) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES roles(id),
    program_id INTEGER REFERENCES programs(id),
    is_active BOOLEAN DEFAULT TRUE,
    graduate_at DATE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: courses
-- =========================================
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    sks INTEGER NOT NULL,
    program_id INTEGER NOT NULL REFERENCES programs(id) ON DELETE CASCADE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- INSERT DATA INTO roles
-- =========================================
INSERT INTO roles (name, description, created_by, updated_by)
VALUES
  ('admin', 'System administrator', 1, 1),
  ('lecturer', 'Faculty lecturer', 1, 1),
  ('student', 'University student', 1, 1);

-- =========================================
-- INSERT DATA INTO faculties
-- =========================================
INSERT INTO faculties (name, created_by, updated_by)
VALUES
  ('Faculty of Engineering', 1, 1),
  ('Faculty of Science', 1, 1),
  ('Faculty of Business', 1, 1);

-- =========================================
-- INSERT DATA INTO programs
-- =========================================
INSERT INTO programs (name, faculty_id, created_by, updated_by)
VALUES
  ('Computer Science', 1, 1, 1),
  ('Physics', 2, 1, 1),
  ('Marketing', 3, 1, 1);

-- =========================================
-- INSERT DATA INTO courses
-- =========================================
INSERT INTO courses (name, sks, program_id, created_by, updated_by)
VALUES
  ('Data Structures', 3, 1, 1, 1),
  ('Quantum Mechanics', 4, 2, 1, 1),
  ('Consumer Behavior', 2, 3, 1, 1);

-- =========================================
-- INSERT DATA INTO users
-- =========================================
INSERT INTO users (nip, password, name, role_id, program_id, is_active, graduate_at, created_by, updated_by)
VALUES 
  ('1001', 'hashedpass1', 'Alice Admin', 1, NULL, TRUE, NULL, 1, 1),
  ('1002', 'hashedpass2', 'Bob Lecturer', 2, 1, TRUE, NULL, 1, 1),
  ('1003', 'hashedpass3', 'Charlie Student', 3, 1, TRUE, '2025-08-01', 1, 1);
