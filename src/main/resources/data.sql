-- Insert sample data into permission table
INSERT INTO permission (created_at, deleted, name, updated_at)
VALUES (NOW(), 0, 'Permission 1', NOW()),
       (NOW(), 0, 'Permission 2', NOW()),
       (NOW(), 0, 'Permission 3', NOW());

-- Insert sample data into role table
INSERT INTO role (created_at, deleted, name, updated_at)
VALUES (NOW(), 0, 'Role 1', NOW()),
       (NOW(), 0, 'Role 2', NOW()),
       (NOW(), 0, 'Role 3', NOW());

-- Insert sample data into permission_roles table
INSERT INTO permission_roles (permissions_id, roles_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 3);

-- Insert sample data into status table
INSERT INTO status (created_at, deleted, name, updated_at)
VALUES (NOW(), 0, 'Status 1', NOW()),
       (NOW(), 0, 'Status 2', NOW()),
       (NOW(), 0, 'Status 3', NOW());

-- Insert sample data into tag table
INSERT INTO tag (created_at, deleted, name, updated_at)
VALUES (NOW(), 0, 'Tag 1', NOW()),
       (NOW(), 0, 'Tag 2', NOW()),
       (NOW(), 0, 'Tag 3', NOW());

-- Insert sample data into type table
INSERT INTO type (created_at, deleted, name, updated_at)
VALUES (NOW(), 0, 'Type 1', NOW()),
       (NOW(), 0, 'Type 2', NOW()),
       (NOW(), 0, 'Type 3', NOW());

-- Insert sample data into user table
INSERT INTO user (created_at, deleted, email, firstname, lastname, password, updated_at)
VALUES (NOW(), 0, 'user1@example.com', 'John', 'Doe', 'password1', NOW()),
       (NOW(), 0, 'user2@example.com', 'Jane', 'Smith', 'password2', NOW()),
       (NOW(), 0, 'user3@example.com', 'Mike', 'Johnson', 'password3', NOW());

-- Insert sample data into issue table
INSERT INTO issue (created_at, deleted, description, title, updated_at, created_by, parent_issue_id, assigned_to,
                   status, type_id)
VALUES (NOW(), 0, 'Issue 1 description', 'Issue 1', NOW(), 1, NULL, 2, 1, 3),
       (NOW(), 0, 'Issue 2 description', 'Issue 2', NOW(), 2, NULL, 3, 2, 1),
       (NOW(), 0, 'Issue 3 description', 'Issue 3', NOW(), 3, NULL, 1, 3, 2);

-- Insert sample data into comment table
INSERT INTO comment (comment_text, created_at, deleted, updated_at, issue_id, parent_comment_id, user_id)
VALUES ('Comment 1', NOW(), 0, NOW(), 1, NULL, 1),
       ('Comment 2', NOW(), 0, NOW(), 1, NULL, 2),
       ('Comment 3', NOW(), 0, NOW(), 2, 1, 3);

-- Insert sample data into issue_tags table
INSERT INTO issue_tags (issues_id, tags_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 2);

-- Insert sample data into user_roles table
INSERT INTO user_roles (user_id, roles_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
