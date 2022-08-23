CREATE TABLE revenues(
	id INT  AUTO_INCREMENT,
    description  VARCHAR(255),
    value DECIMAL(15,2),
    date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE expenses(
	id INT  AUTO_INCREMENT,
    description  VARCHAR(255),
    value DECIMAL(15,2),
    date DATE,
    PRIMARY KEY (id)
);