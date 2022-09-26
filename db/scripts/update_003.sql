CREATE TABLE j_role (
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE j_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    role_id INT NOT NULL REFERENCES j_role(id)
);