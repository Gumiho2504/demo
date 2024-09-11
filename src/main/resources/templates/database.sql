USE jobapplication;

-- Drop the savejob, job, user, profile, and skill tables if they exist
DROP TABLE IF EXISTS savejob;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS profile;

-- Create the profile table
CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
) AUTO_INCREMENT = 1000;

-- Create the user table
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id BIGINT,                      -- Profile foreign key, can be null
    FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE SET NULL -- Foreign key to Profile, set NULL if Profile is deleted
);

-- Create the skill table
CREATE TABLE skill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    profile_id BIGINT, -- Foreign key to profile, can be null
    FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE SET NULL
);

-- Create the job table
CREATE TABLE job (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(128) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY TITLE_UNIQUE (title)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- Insert 10 example records into the skill table linked to profiles
INSERT INTO skill (title, profile_id) 
VALUES 
('Java Programming', 1000),     -- Skill for profile 1000 (Software Engineer)
('Python Programming', 1000),   -- Skill for profile 1000 (Software Engineer)
('Web Development', 1001),      -- Skill for profile 1001 (Data Analyst)
('Database Management', 1001),  -- Skill for profile 1001 (Data Analyst)
('Cloud Computing', 1002),      -- Skill for profile 1002 (Project Manager)
('Mobile App Development', 1000), -- Skill for profile 1000 (Software Engineer)
('Data Analysis', 1001),        -- Skill for profile 1001 (Data Analyst)
('Project Management', 1002),   -- Skill for profile 1002 (Project Manager)
('UI/UX Design', 1003),         -- Skill for profile 1003 (Product Designer)
('Network Security', 1002);     -- Skill for profile 1002 (Project Manager)
