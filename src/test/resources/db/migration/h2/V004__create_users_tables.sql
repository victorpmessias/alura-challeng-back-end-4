CREATE TABLE roles(
	id INT AUTO_INCREMENT PRIMARY kEY,
	role VARCHAR(30) NOT NULL UNIQUE
);


CREATE TABLE users_creds(
	id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);	

CREATE TABLE users_roles(
    id INT AUTO_INCREMENT PRIMARY KEY,
	role_id INT,
    user_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (user_id) REFERENCES users_creds(id)
);


CREATE TABLE users_info(
	id INT AUTO_INCREMENT PRIMARY KEY, 
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

