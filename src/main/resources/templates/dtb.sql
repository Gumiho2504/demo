USE jobapplication;

-- Drop tables if they exist
DROP TABLE IF EXISTS savejob;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS profile_skill;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS education;
DROP TABLE IF EXISTS experience;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS company_information;

DROP TABLE IF EXISTS requestionment;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS company;


-- Create the profile table
CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4;

-- Create the company table with BIGINT id
CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    phone_number VARCHAR(50) DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
    CONSTRAINT fk_education_profile FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE
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
    CONSTRAINT fk_experience_profile FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the job table
-- Modify the job table to use BIGINT for the id column
CREATE TABLE job (
    id BIGINT NOT NULL AUTO_INCREMENT,  -- Changed id to BIGINT
    title VARCHAR(128) DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    salary VARCHAR(50) DEFAULT NULL,
    work_model VARCHAR(50) DEFAULT NULL,
    type VARCHAR(50) DEFAULT NULL,
    level VARCHAR(50) DEFAULT NULL,
    company_id BIGINT,  -- Foreign key to company, already BIGINT
    PRIMARY KEY (id),
    UNIQUE KEY TITLE_UNIQUE (title),
    CONSTRAINT fk_job_company FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the requestionment table
CREATE TABLE requestment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key with AUTO_INCREMENT
    requestment TEXT,  -- String equivalent in SQL is TEXT or VARCHAR
    job_id BIGINT,  -- Foreign key reference to the Job table (now compatible as BIGINT)
    CONSTRAINT fk_requestment_job FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE -- Foreign key constraint
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create the savejob table after user and job tables
-- Create the savejob table after user and job tables
CREATE TABLE savejob (
    user_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,  -- Changed to BIGINT to match job(id)
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, job_id),
    CONSTRAINT FK_SAVEJOB_USER FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT FK_SAVEJOB_JOB FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Create the company_information table
CREATE TABLE company_information (
    id INT AUTO_INCREMENT PRIMARY KEY,
    information VARCHAR(255) DEFAULT NULL,
    company_id BIGINT,
    CONSTRAINT fk_information_company FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert example records into the profile table
INSERT INTO profile (title, phone_number) 
VALUES 
('Software Engineer', '123-456-7890'),
('Data Analyst', '234-567-8901'),
('Project Manager', '345-678-9012'),
('Product Designer', '456-789-0123'),
('Marketing Specialist', '567-890-1234');

-- Insert example records into the company table
INSERT INTO company (name, description, phone_number, email) 
VALUES 
('Tech Corp', 'Technology Solutions', '123-456-7890', 'contact@techcorp.com'),
('Data Inc.', 'Data Analysis and Solutions', '234-567-8901', 'info@datainc.com'),
('PM Solutions', 'Project Management Services', '345-678-9012', 'pm@solutions.com'),
('Design Hub', 'Creative Design Services', '456-789-0123', 'info@designhub.com'),
('Marketing Pros', 'Marketing and Branding', '567-890-1234', 'contact@marketingpros.com');

-- Insert example records into the user table
INSERT INTO user (name, email, password, profile_id) 
VALUES 
('alice', 'alice@example.com', 'passwordAlice', 1000),
('bob', 'bob@example.com', 'passwordBob', 1001),
('charlie', 'charlie@example.com', 'passwordCharlie', 1002),
('david', 'david@example.com', 'passwordDavid', 1003),
('eve', 'eve@example.com', 'passwordEve', 1004);

-- Insert example records into the job table
INSERT INTO job (title, description, salary, work_model, type, level, company_id) 
VALUES 
('Software Engineer', 'Develop and maintain software', '60000', 'Remote', 'Full-Time', 'Mid-Level', 1),
('Data Analyst', 'Analyze complex data sets', '55000', 'On-site', 'Full-Time', 'Entry-Level', 2),
('Project Manager', 'Manage projects and teams', '70000', 'Hybrid', 'Full-Time', 'Senior-Level', 3),
('Product Designer', 'Design products and interfaces', '50000', 'Remote', 'Part-Time', 'Mid-Level', 4),
('Marketing Specialist', 'Develop and execute marketing strategies', '45000', 'On-site', 'Full-Time', 'Entry-Level', 5);

-- Insert example records into the savejob table
INSERT INTO savejob (user_id, job_id, saved_at)
VALUES 
(1, 1, NOW()), 
(1, 2, NOW()), 
(2, 3, NOW()), 
(3, 4, NOW()), 
(3, 5, NOW()), 
(4, 1, NOW()), 
(5, 2, NOW());

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

-- Insert example records into the profile_skill table
INSERT INTO profile_skill (profile_id, skill_id) 
VALUES 
(1000, 1),  -- Software Engineer has Java Programming
(1000, 2),  -- Software Engineer has Python Programming
(1001, 3),  -- Data Analyst has Web Development
(1001, 4),  -- Data Analyst has Database Management
(1002, 5),  -- Project Manager has Cloud Computing
(1000, 6),  -- Software Engineer has Mobile App Development
(1001, 7),  -- Data Analyst has Data Analysis
(1002, 8),  -- Project Manager has Project Management
(1003, 9),  -- Product Designer has UI/UX Design
(1002, 10); -- Project Manager has Network Security

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

-- Insert example records into the company_information table
INSERT INTO company_information (information, company_id)
VALUES
('Leading tech company in the software industry', 1),
('Innovative data analytics solutions provider', 2),
('Expert in project management services', 3),
('Creative product design agency', 4),
('Top-notch marketing and branding services', 5);

-- Insert example records into the requestionment table
INSERT INTO requestment (requestment, job_id)
VALUES 
('Can you clarify the requirements for this job?', 1),
('How many years of experience are needed?', 2),
('Is remote work an option?', 3),
('What is the design tool preference for the position?', 4),
('Are there any specific skills required for marketing strategy?', 5);
