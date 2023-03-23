create table task_categories(
    id serial primary key,
    task_id int not null references tasks(id),
    category_id int not null references categories(id)
);

ALTER TABLE tasks ADD COLUMN category_id int REFERENCES categories(id);