USE jobapplication;

-- Drop the savejob, job, user, profile, and skill tables if they exist
DROP TABLE IF EXISTS savejob;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS profile_skill;
DROP TABLE IF EXISTS skill;

DROP TABLE IF EXISTS education;
DROP TABLE IF EXISTS experience;
DROP TABLE IF EXISTS profile;
-- Create the profile table
CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4;

-- Create the user table
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id BIGINT DEFAULT NULL,
    CONSTRAINT FK_PROFILE FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- Create the skill table
CREATE TABLE skill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the junction table to link profile and skill (many-to-many relationship)
CREATE TABLE profile_skill (
    profile_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    PRIMARY KEY (profile_id, skill_id),
    CONSTRAINT FK_PROFILE_SKILL_PROFILE FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE,
    CONSTRAINT FK_PROFILE_SKILL_SKILL FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table for Education (OneToMany relationship with Profile)
CREATE TABLE education (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    school VARCHAR(255),
    field VARCHAR(255),
    start_date DATETIME,
    end_date DATETIME,
    description TEXT,
    profile_id BIGINT,
    CONSTRAINT fk_education_profile
        FOREIGN KEY (profile_id)
        REFERENCES profile(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table for Experience (OneToMany relationship with Profile)
CREATE TABLE experience (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    company VARCHAR(255),
    start_date DATETIME,
    end_date DATETIME,
    is_working BOOLEAN,
    profile_id BIGINT,
    CONSTRAINT fk_experience_profile
        FOREIGN KEY (profile_id)
        REFERENCES profile(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the job table
CREATE TABLE job (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(128) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY TITLE_UNIQUE (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the savejob table after user and job tables
CREATE TABLE savejob (
    user_id BIGINT NOT NULL,
    job_id INT NOT NULL,
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, job_id),
    CONSTRAINT FK_SAVEJOB_USER
        FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT FK_SAVEJOB_JOB
        FOREIGN KEY (job_id)
        REFERENCES job (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert 5 example records into the profile table
INSERT INTO profile (title, phone_number) 
VALUES 
('Software Engineer', '123-456-7890'),
('Data Analyst', '234-567-8901'),
('Project Manager', '345-678-9012'),
('Product Designer', '456-789-0123'),
('Marketing Specialist', '567-890-1234');

-- Insert 5 example records into the user table with profiles
INSERT INTO user (name, email, password, profile_id) 
VALUES 
('alice', 'alice@example.com', 'passwordAlice', 1000),
('bob', 'bob@example.com', 'passwordBob', 1001),
('charlie', 'charlie@example.com', 'passwordCharlie', 1002),
('david', 'david@example.com', 'passwordDavid', 1003),
('eve', 'eve@example.com', 'passwordEve', 1004);

-- Insert 10 example records into the job table
INSERT INTO job (title) 
VALUES 
('Software Engineer'),
('Data Analyst'),
('Project Manager'),
('Product Designer'),
('Marketing Specialist'),
('Sales Representative'),
('UX Researcher'),
('System Administrator'),
('Business Analyst'),
('Customer Support');

-- Insert records into the savejob table for all users
INSERT INTO savejob (user_id, job_id, saved_at)
VALUES
(1, 1, NOW()),  -- Alice saved Software Engineer
(1, 2, NOW()),  -- Alice saved Data Analyst
(2, 3, NOW()),  -- Bob saved Project Manager
(3, 4, NOW()),  -- Charlie saved Product Designer
(3, 5, NOW()),  -- Charlie saved Marketing Specialist
(4, 6, NOW()),  -- David saved Sales Representative
(5, 7, NOW());  -- Eve saved UX Researcher

-- Insert example records into the skill table
INSERT INTO skill (title) 
VALUES 
('Java Programming'),     
('Python Programming'),   
('Web Development'),      
('Database Management'),  
('Cloud Computing'),      
('Mobile App Development'),
('Data Analysis'),        
('Project Management'),   
('UI/UX Design'),         
('Network Security');     

-- Insert records into the profile_skill table to link profiles and skills
INSERT INTO profile_skill (profile_id, skill_id) 
VALUES 
(1000, 1),  -- Profile 1000 (Software Engineer) has Skill 1 (Java Programming)
(1000, 2),  -- Profile 1000 (Software Engineer) has Skill 2 (Python Programming)
(1001, 3),  -- Profile 1001 (Data Analyst) has Skill 3 (Web Development)
(1001, 4),  -- Profile 1001 (Data Analyst) has Skill 4 (Database Management)
(1002, 5),  -- Profile 1002 (Project Manager) has Skill 5 (Cloud Computing)
(1000, 6),  -- Profile 1000 (Software Engineer) has Skill 6 (Mobile App Development)
(1001, 7),  -- Profile 1001 (Data Analyst) has Skill 7 (Data Analysis)
(1002, 8),  -- Profile 1002 (Project Manager) has Skill 8 (Project Management)
(1003, 9),  -- Profile 1003 (Product Designer) has Skill 9 (UI/UX Design)
(1002, 10); -- Profile 1002 (Project Manager) has Skill 10 (Network Security)

-- Insert example records into the education table
INSERT INTO education (school, field, start_date, end_date, description, profile_id) 
VALUES 
('University A', 'Computer Science', '2018-01-01', '2022-01-01', 'Bachelor Degree in Computer Science', 1000),
('University B', 'Data Analytics', '2017-01-01', '2021-01-01', 'Bachelor Degree in Data Analytics', 1001),
('University C', 'Project Management', '2015-01-01', '2019-01-01', 'Bachelor Degree in Project Management', 1002);

-- Insert example records into the experience table
INSERT INTO experience (title, description, company, start_date, end_date, is_working, profile_id) 
VALUES 
('Software Developer', 'Developed various applications', 'Tech Corp', '2022-01-01', '2024-09-01', FALSE, 1000),
('Data Analyst', 'Analyzed complex data sets', 'Data Inc.', '2021-01-01', '2024-01-01', TRUE, 1001),
('Project Manager', 'Managed various projects', 'PM Solutions', '2019-01-01', '2024-01-01', TRUE, 1002);
