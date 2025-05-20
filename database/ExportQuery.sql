-- =========================================
-- DROP TABLES IF EXIST
-- =========================================
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS faculties;

-- =========================================
-- CREATE TABLE: roles
-- =========================================
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(50) NOT NULL,
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
    slug VARCHAR(100) NOT NULL,

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
    slug VARCHAR(100) NOT NULL,
    faculty_id INTEGER NOT NULL REFERENCES faculties(id) ON DELETE CASCADE,

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
    slug VARCHAR(100) NOT NULL,
    sks INTEGER NOT NULL,
    program_id INTEGER NOT NULL REFERENCES programs(id) ON DELETE CASCADE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: users (now with faculty_id AND course_id)
-- =========================================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nip VARCHAR(20) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES roles(id),
    program_id INTEGER REFERENCES programs(id),
    faculty_id INTEGER REFERENCES faculties(id),
    course_id INTEGER REFERENCES courses(id), -- newly added
    is_active BOOLEAN DEFAULT TRUE,
    graduate_at DATE,

    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- INSERT DATA INTO roles
-- =========================================
INSERT INTO roles (name, slug, description, created_by, updated_by)
VALUES
  ('admin', 'admin', 'System administrator', 1, 1),
  ('lecturer', 'lecturer', 'Faculty lecturer', 1, 1),
  ('student', 'student', 'University student', 1, 1);

-- =========================================
-- INSERT DATA INTO faculties
-- =========================================
INSERT INTO faculties (name, slug, created_by, updated_by)
VALUES
  ('Faculty of Engineering', 'faculty-of-engineering', 1, 1),
  ('Faculty of Science', 'faculty-of-science', 1, 1),
  ('Faculty of Business', 'faculty-of-business',  1, 1);

-- =========================================
-- INSERT DATA INTO programs
-- =========================================
INSERT INTO programs (name, slug, faculty_id, created_by, updated_by)
VALUES
  ('Computer Science', 'computer-science', 1, 1, 1),
  ('Physics', 'physics', 2, 1, 1),
  ('Marketing', 'marketing', 3, 1, 1);

-- =========================================
-- INSERT DATA INTO courses
-- =========================================
INSERT INTO courses (name, slug, sks, program_id, created_by, updated_by)
VALUES
  ('Data Structures', 'data-structures', 3, 1, 1, 1),
  ('Quantum Mechanics', 'quantum-mechanics', 4, 2, 1, 1),
  ('Customer Behavior', 'customer-behavior', 2, 3, 1, 1);


-- =========================================
-- INSERT DATA INTO users (includes faculty_id and course_id)
-- =========================================
INSERT INTO users (nip, password, name, slug, role_id, program_id, faculty_id, course_id, is_active, graduate_at, created_by, updated_by)
VALUES
  ('1001', 'hashedpass1', 'Alice Admin', 'alice-admin', 1, NULL, NULL, NULL, TRUE, NULL, 1, 1),
  ('1002', 'hashedpass2', 'Bob Lecturer', 'bob-lecturer', 2, 1, 1, 1, TRUE, NULL, 1, 1),
  ('1003', 'hashedpass3', 'Charlie Student', 'charlie-student', 3, 1, 1, 1, TRUE, '2025-08-01', 1, 1);

select * from users;