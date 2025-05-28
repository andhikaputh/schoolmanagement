-- =========================================
-- DROP TABLES IF EXIST
-- =========================================
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS krs;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS course_assignments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS lecturers;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS faculties;
DROP TABLE IF EXISTS roles;

-- =========================================
-- CREATE TABLE: roles
-- =========================================
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255),
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
    name VARCHAR(100),
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
    faculty_id INTEGER NOT NULL REFERENCES faculties(id) ON DELETE CASCADE,
    name VARCHAR(100),
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: users (program_id removed)
-- =========================================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nip VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    name VARCHAR(100),
    role_id INTEGER REFERENCES roles(id),
    is_active BOOLEAN DEFAULT TRUE,
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: students (program_id added)
-- =========================================
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    user_id INTEGER UNIQUE REFERENCES users(id),
    nim VARCHAR(50) UNIQUE,
    semester INTEGER,
    graduate_at TIMESTAMP,
    program_id INTEGER REFERENCES programs(id)
);

-- =========================================
-- CREATE TABLE: lecturers
-- =========================================
CREATE TABLE lecturers (
    id SERIAL PRIMARY KEY,
    user_id INTEGER UNIQUE REFERENCES users(id),
    nidn VARCHAR(50) UNIQUE,
    faculty_id INTEGER REFERENCES faculties(id)
);

-- =========================================
-- CREATE TABLE: courses
-- =========================================
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    program_id INTEGER REFERENCES programs(id),
    name VARCHAR(100),
    code VARCHAR(50) UNIQUE,
    semester INTEGER,
    sks INTEGER,
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: course_assignments
-- =========================================
CREATE TABLE course_assignments (
    id SERIAL PRIMARY KEY,
    lecturer_id INTEGER REFERENCES lecturers(id),
    course_id INTEGER REFERENCES courses(id),
    academic_year VARCHAR(20),
    class_name VARCHAR(50),
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: schedules
-- =========================================
CREATE TABLE schedules (
    id SERIAL PRIMARY KEY,
    course_assignment_id INTEGER REFERENCES course_assignments(id),
    day_of_week VARCHAR(20),
    start_time TIME,
    end_time TIME,
    room VARCHAR(50),
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: krs
-- =========================================
CREATE TABLE krs (
    id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students(id),
    course_assignment_id INTEGER REFERENCES course_assignments(id),
    is_approve BOOLEAN DEFAULT FALSE,
    academic_year VARCHAR(20),
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: grades
-- =========================================
CREATE TABLE grades (
    id SERIAL PRIMARY KEY,
    krs_id INTEGER REFERENCES krs(id),
    assignment_score DECIMAL,
    midterm_score DECIMAL,
    final_score DECIMAL,
    final_grade VARCHAR(5),
    created_by INTEGER,
    updated_by INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- CREATE TABLE: attendance
-- =========================================
CREATE TABLE attendance (
    id SERIAL PRIMARY KEY,
    krs_id INTEGER REFERENCES krs(id),
    schedule_id INTEGER REFERENCES schedules(id),
    meeting_date DATE,
    status VARCHAR(20),
    note TEXT,
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
  ('Faculty of Science', 1, 1);

-- =========================================
-- INSERT DATA INTO programs
-- =========================================
INSERT INTO programs (faculty_id, name, created_by, updated_by)
VALUES
  (1, 'Computer Science', 1, 1),
  (2, 'Physics', 1, 1);

-- =========================================
-- INSERT DATA INTO users (program_id removed)
-- =========================================
INSERT INTO users (nip, password, name, role_id, is_active, created_by, updated_by)
VALUES
  ('1001', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Alice Admin', 1, TRUE, 1, 1),
  ('1002', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Bob Lecturer', 2, TRUE, 1, 1),
  ('1003', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Charlie Student', 3, TRUE, 1, 1),
  ('1004', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Diana Student', 3, TRUE, 1, 1),
  ('1005', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Evan Student', 3, TRUE, 1, 1),
  ('1006', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Fiona Lecturer', 2, TRUE, 1, 1),
  ('1007', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'George Student', 3, TRUE, 1, 1),
  ('1008', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Hannah Student', 3, TRUE, 1, 1),
  ('1009', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Ivan Lecturer', 2, TRUE, 1, 1),
  ('1010', '$2a$10$bHeGrxyraFRG6r1FzzRzTuazz2IPJCwtz7H40K9M5Y/2W76sjtdiS', 'Julia Student', 3, TRUE, 1, 1);

-- =========================================
-- INSERT DATA INTO students (program_id added)
-- =========================================
INSERT INTO students (user_id, nim, semester, graduate_at, program_id)
VALUES
  (3, 'S1003', 6, '2025-08-01', 1),
  (4, 'S1004', 4, NULL, 1),
  (5, 'S1005', 2, NULL, 2),
  (7, 'S1007', 8, '2025-01-01', 2),
  (8, 'S1008', 1, NULL, 1),
  (10, 'S1010', 6, NULL, 2);

-- =========================================
-- INSERT DATA INTO lecturers
-- =========================================
INSERT INTO lecturers (user_id, nidn, faculty_id)
VALUES
  (2, 'L1002', 1),
  (6, 'L1006', 2),
  (9, 'L1009', 1);

-- =========================================
-- INSERT DATA INTO courses
-- =========================================
INSERT INTO courses (program_id, name, code, semester, sks, created_by, updated_by)
VALUES
  (1, 'Data Structures', 'CS101', 2, 3, 1, 1),
  (2, 'Quantum Mechanics', 'PH301', 5, 4, 1, 1);

-- =========================================
-- INSERT DATA INTO course_assignments
-- =========================================
INSERT INTO course_assignments (lecturer_id, course_id, academic_year, class_name, created_by, updated_by)
VALUES
  (1, 1, '2024/2025', 'A', 1, 1);

-- =========================================
-- INSERT DATA INTO schedules
-- =========================================
INSERT INTO schedules (course_assignment_id, day_of_week, start_time, end_time, room, created_by, updated_by)
VALUES
  (1, 'Monday', '08:00', '10:00', 'Lab 1', 1, 1);

-- =========================================
-- INSERT DATA INTO krs
-- =========================================
INSERT INTO krs (student_id, course_assignment_id, is_approve, academic_year, created_by, updated_by)
VALUES
  (1, 1, TRUE, '2024/2025', 1, 1);

-- =========================================
-- INSERT DATA INTO grades
-- =========================================
INSERT INTO grades (krs_id, assignment_score, midterm_score, final_score, final_grade, created_by, updated_by)
VALUES
  (1, 85, 88, 90, 'A', 1, 1);

-- =========================================
-- INSERT DATA INTO attendance
-- =========================================
INSERT INTO attendance (krs_id, schedule_id, meeting_date, status, note, created_by, updated_by)
VALUES
  (1, 1, '2025-03-01', 'Present', 'On time', 1, 1);