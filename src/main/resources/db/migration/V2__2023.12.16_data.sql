INSERT INTO users (name, password, email)
VALUES ('User1', '1', 'user1@example.com'),
       ('User2', '2', 'user2@example.com'),
       ('User3', '3', 'user3@example.com'),
       ('User4', '4', 'user4@example.com'),
       ('User5', '5', 'user5@example.com');

INSERT INTO tasks (header, description, status, priority, author_id, assignee_id)
VALUES
    -- User1
    ('Task 1', 'Description for Task 1', 'PENDING', 'HIGH', 1, NULL),
    ('Task 2', 'Description for Task 2', 'IN_PROGRESS', 'MEDIUM', 1, 1),
    ('Task 3', 'Description for Task 3', 'COMPLETED', 'LOW', 1, NULL),
    ('Task 4', 'Description for Task 4', 'PENDING', 'HIGH', 1, 2),
    -- User2
    ('Task 5', 'Description for Task 5', 'IN_PROGRESS', 'MEDIUM', 2, 3),
    ('Task 6', 'Description for Task 6', 'COMPLETED', 'LOW', 2, NULL),
    ('Task 7', 'Description for Task 7', 'PENDING', 'HIGH', 2, 4),
    ('Task 8', 'Description for Task 8', 'IN_PROGRESS', 'MEDIUM', 2, NULL),
    -- User3
    ('Task 9', 'Description for Task 9', 'COMPLETED', 'LOW', 3, NULL),
    ('Task 10', 'Description for Task 10', 'PENDING', 'HIGH', 3, 5),
    ('Task 11', 'Description for Task 11', 'IN_PROGRESS', 'MEDIUM', 3, NULL),
    ('Task 12', 'Description for Task 12', 'COMPLETED', 'LOW', 3, NULL),
    -- User4
    ('Task 13', 'Description for Task 13', 'PENDING', 'HIGH', 4, 1),
    ('Task 14', 'Description for Task 14', 'IN_PROGRESS', 'MEDIUM', 4, NULL),
    ('Task 15', 'Description for Task 15', 'COMPLETED', 'LOW', 4, 2),
    ('Task 16', 'Description for Task 16', 'PENDING', 'HIGH', 4, NULL),
    -- User5
    ('Task 17', 'Description for Task 17', 'IN_PROGRESS', 'MEDIUM', 5, NULL),
    ('Task 18', 'Description for Task 18', 'COMPLETED', 'LOW', 5, 3),
    ('Task 19', 'Description for Task 19', 'PENDING', 'HIGH', 5, NULL),
    ('Task 20', 'Description for Task 20', 'IN_PROGRESS', 'MEDIUM', 5, 4);

INSERT INTO comments (comment, task_id)
VALUES ('Comment 1 for Task 1', 1),
       ('Comment 2 for Task 1', 1),
       ('Comment 3 for Task 2', 2),
       ('Comment 4 for Task 3', 3),
       ('Comment 5 for Task 4', 4),
       ('Comment 6 for Task 5', 5),
       ('Comment 7 for Task 6', 6),
       ('Comment 8 for Task 7', 7),
       ('Comment 9 for Task 8', 8),
       ('Comment 10 for Task 9', 9),
       ('Comment 11 for Task 10', 10),
       ('Comment 12 for Task 11', 11),
       ('Comment 13 for Task 12', 12),
       ('Comment 14 for Task 13', 13),
       ('Comment 15 for Task 14', 14),
       ('Comment 16 for Task 15', 15),
       ('Comment 17 for Task 16', 16),
       ('Comment 18 for Task 17', 17),
       ('Comment 19 for Task 18', 18),
       ('Comment 20 for Task 19', 19);