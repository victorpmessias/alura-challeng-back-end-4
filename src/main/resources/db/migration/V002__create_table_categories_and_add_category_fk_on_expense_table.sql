CREATE TABLE categories(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

ALTER TABLE expenses
ADD category_id INT DEFAULT 1,
ADD FOREIGN KEY (category_id) REFERENCES categories(id);