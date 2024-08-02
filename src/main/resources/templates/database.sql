use jobapplication;

-- Drop the savejob table if it exists
DROP TABLE IF EXISTS `savejob`;

-- Create the savejob table
CREATE TABLE `savejob` (
    `user_id` INT NOT NULL,
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


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  
  KEY `FK_INSTRUCTOR_idx` (`user_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


-- Insert 10 example records into the user table
INSERT INTO `user` (`name`) VALUES ('Alice Johnson');
INSERT INTO `user` (`name`) VALUES ('Bob Smith');
INSERT INTO `user` (`name`) VALUES ('Charlie Davis');
INSERT INTO `user` (`name`) VALUES ('Diana Green');
INSERT INTO `user` (`name`) VALUES ('Ethan Brown');
INSERT INTO `user` (`name`) VALUES ('Fiona Lee');
INSERT INTO `user` (`name`) VALUES ('George White');
INSERT INTO `user` (`name`) VALUES ('Hannah Wilson');
INSERT INTO `user` (`name`) VALUES ('Ian Clark');
INSERT INTO `user` (`name`) VALUES ('Jasmine Adams');

-- Insert 10 example records into the job table, associating with users
INSERT INTO `job` (`title`, `user_id`) VALUES ('Software Engineer', 1);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Data Analyst', 2);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Project Manager', 3);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Product Designer', 4);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Marketing Specialist', 5);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Sales Representative', 6);
INSERT INTO `job` (`title`, `user_id`) VALUES ('UX Researcher', 7);
INSERT INTO `job` (`title`, `user_id`) VALUES ('System Administrator', 8);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Business Analyst', 9);
INSERT INTO `job` (`title`, `user_id`) VALUES ('Customer Support', 10);
