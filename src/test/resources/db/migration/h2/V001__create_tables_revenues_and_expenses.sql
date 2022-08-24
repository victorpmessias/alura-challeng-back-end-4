CREATE TABLE revenues(
	id INT  AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    `value` DECIMAL(15,2),
    `date` DATE
);

CREATE TABLE expenses(
	id INT AUTO_INCREMENT PRIMARY KEY,
    description  VARCHAR(255),
    `value` DECIMAL(15,2),
   `date` DATE
);