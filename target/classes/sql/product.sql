CREATE TABLE IF NOT EXISTS product(
	p_id INT(30) NOT NULL,
	p_name VARCHAR(20),
	p_price  INTEGER,
	p_category VARCHAR(20),
	p_description TEXT,
	p_releaseDate VARCHAR(20),
	p_soldout VARCHAR(20),
	p_fileName  VARCHAR(30),
	PRIMARY KEY (p_id)
)default CHARSET=utf8;