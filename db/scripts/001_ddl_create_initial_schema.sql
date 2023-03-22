CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN
);

CREATE TABLE todo_user(
    id SERIAL PRIMARY KEY,
    name TEXT not null,
    login TEXT unique,
    password TEXT not null
);