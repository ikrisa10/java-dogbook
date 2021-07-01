DROP TABLE IF EXISTS dogs;

CREATE TABLE dogs (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  photo_url VARCHAR(255) NOT NULL,
  breed VARCHAR(255) NOT NULL,
  sex VARCHAR(255) NOT NULL
);