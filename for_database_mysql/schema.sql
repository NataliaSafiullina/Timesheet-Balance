-- mysql --
DROP DATABASE IF EXISTS `balance`;
CREATE DATABASE `balance`;

CREATE TABLE `balance`.`positions` (
	`position` VARCHAR(50) PRIMARY KEY NOT NULL,
	`rate` INT UNSIGNED NOT NULL,
	CONSTRAINT ch_rate_1 CHECK (rate > 0)  
);

CREATE TABLE `balance`.`tasks` (
	`task_id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`task_name` VARCHAR(50) NOT NULL,
	`task_text` TEXT
);

CREATE TABLE `balance`.`employees` (
	`employee_id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`employee_name` VARCHAR(50) NOT NULL,
	`employee_position` VARCHAR(50),
	CONSTRAINT `employee_position_fk_1` FOREIGN KEY (`employee_position`) REFERENCES `balance`.`positions`(`position`) ON DELETE SET NULL 
);

CREATE TABLE `balance`.`timesheet` (
	`timesheet_id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`employee_id` INT UNSIGNED,
	`task_id` INT UNSIGNED,
	`start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`finish_time` TIMESTAMP,
	CONSTRAINT `employee_id_fk_1` FOREIGN KEY (`employee_id`) REFERENCES `balance`.`employees`(`employee_id`) ON DELETE CASCADE,
	CONSTRAINT `task_id_fk_2` FOREIGN KEY (`task_id`) REFERENCES `balance`.`tasks` (`task_id`) ON DELETE CASCADE	 
);

CREATE TABLE `balance`.`timesheet_history` (
	`timesheet_id` INT UNSIGNED,
	`employee_name` VARCHAR(50),
	`task_name` VARCHAR(50),
	`start_time` TIMESTAMP,
	`finish_time` TIMESTAMP
);

