-- =========================================
-- DROP TABLES IF EXIST (to reset schema)
-- =========================================
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS role;

-- =========================================
-- CREATE TABLE: role
-- =========================================
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: faculty
-- =========================================
CREATE TABLE faculty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: program
-- =========================================
CREATE TABLE program (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    faculty_id INTEGER NOT NULL REFERENCES faculty(id) ON DELETE CASCADE,

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
    role_id INTEGER NOT NULL REFERENCES role(id),
    program_id INTEGER REFERENCES program(id),
    is_active BOOLEAN DEFAULT TRUE,
    graduate_at DATE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: course
-- =========================================
CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    sks INTEGER NOT NULL,
    program_id INTEGER NOT NULL REFERENCES program(id) ON DELETE CASCADE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- INSERT DATA INTO role
-- =========================================
INSERT INTO role (name, description, created_by, updated_by)
VALUES 
  ('admin', 'System administrator', 1, 1),
  ('lecturer', 'Faculty lecturer', 1, 1),
  ('student', 'University student', 1, 1);

-- =========================================
-- INSERT DATA INTO faculty
-- =========================================
INSERT INTO faculty (name, created_by, updated_by)
VALUES 
  ('Faculty of Engineering', 1, 1),
  ('Faculty of Science', 1, 1),
  ('Faculty of Business', 1, 1);

-- =========================================
-- INSERT DATA INTO program
-- =========================================
INSERT INTO program (name, faculty_id, created_by, updated_by)
VALUES 
  ('Computer Science', 1, 1, 1),
  ('Physics', 2, 1, 1),
  ('Marketing', 3, 1, 1);

-- =========================================
-- INSERT DATA INTO course
-- =========================================
INSERT INTO course (name, sks, program_id, created_by, updated_by)
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
