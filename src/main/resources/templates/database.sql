USE jobapplication;

-- Drop the savejob table if it exists
DROP TABLE IF EXISTS `savejob`;
DROP TABLE IF EXISTS `job`;
DROP TABLE IF EXISTS `user`;

-- Create the user table first
CREATE TABLE `user` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the job table
CREATE TABLE `job` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(128) DEFAULT NULL,
    `user_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    CONSTRAINT `FK_INSTRUCTOR` 
    FOREIGN KEY (`user_id`) 
    REFERENCES `user` (`id`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Create the savejob table after user and job tables
CREATE TABLE `savejob` (
    `user_id` BIGINT NOT NULL,
    `job_id` INT NOT NULL,
    `saved_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`, `job_id`),
    CONSTRAINT `FK_SAVEJOB_USER`
        FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT `FK_SAVEJOB_JOB`
        FOREIGN KEY (`job_id`)
        REFERENCES `job` (`id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Insert 5 example records into the user table
INSERT INTO `user` (name, email, password) 
VALUES 
('alice', 'alice@example.com', 'passwordAlice'),
('bob', 'bob@example.com', 'passwordBob'),
('charlie', 'charlie@example.com', 'passwordCharlie'),
('david', 'david@example.com', 'passwordDavid'),
('eve', 'eve@example.com', 'passwordEve');

-- Insert 10 example records into the job table, associating with users
INSERT INTO `job` (`title`, `user_id`) VALUES ('Software Engineer', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Data Analyst', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Project Manager', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Product Designer', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Marketing Specialist', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Sales Representative', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('UX Researcher', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('System Administrator',null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Business Analyst', null);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Customer Support', null);
