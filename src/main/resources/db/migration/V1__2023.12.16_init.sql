CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);

CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    header      VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(255) NOT NULL,
    priority    VARCHAR(255) NOT NULL,
    author_id   BIGINT REFERENCES users (id),
    assignee_id BIGINT REFERENCES users (id)
);

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    comment TEXT NOT NULL,
    task_id BIGINT REFERENCES tasks (id)
);