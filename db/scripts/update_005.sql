CREATE TABLE participants (
    id SERIAL PRIMARY KEY,
    item_id INT REFERENCES items(id),
    user_id INT REFERENCES j_user(id)
);