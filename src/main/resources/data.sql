INSERT INTO user (deleted, created_at, updated_at, email, firstname, lastname, password, profile_picture)
VALUES (0, NOW(), NOW(), 'admin', 'John', 'Doe', '$2a$10$v7ODyhz1mqID5W9bUT5/o.AcNKH9hrxSt607nezK9km75uI6Q/jyu',
        'https://firebasestorage.googleapis.com/v0/b/issue-tracker-56a84.appspot.com/o/files%2FScreenshot%202023-07-19%20144707.png?alt=media&token=f983c311-ae8a-4fa6-af06-d8de91d4ff32');

INSERT INTO permission (deleted, created_at, updated_at, name)
VALUES (0, NOW(), NOW(), 'canCreateAttachment'),
       (0, NOW(), NOW(), 'canReadAttachment'),
       (0, NOW(), NOW(), 'canUpdateAttachment'),
       (0, NOW(), NOW(), 'canDeleteAttachment'),
       (0, NOW(), NOW(), 'canCreateComment'),
       (0, NOW(), NOW(), 'canReadComment'),
       (0, NOW(), NOW(), 'canUpdateComment'),
       (0, NOW(), NOW(), 'canDeleteComment'),
       (0, NOW(), NOW(), 'canCreateIssue'),
       (0, NOW(), NOW(), 'canReadIssue'),
       (0, NOW(), NOW(), 'canUpdateIssue'),
       (0, NOW(), NOW(), 'canDeleteIssue'),
       (0, NOW(), NOW(), 'canCreatePermission'),
       (0, NOW(), NOW(), 'canReadPermission'),
       (0, NOW(), NOW(), 'canUpdatePermission'),
       (0, NOW(), NOW(), 'canDeletePermission'),
       (0, NOW(), NOW(), 'canCreateRole'),
       (0, NOW(), NOW(), 'canReadRole'),
       (0, NOW(), NOW(), 'canUpdateRole'),
       (0, NOW(), NOW(), 'canDeleteRole'),
       (0, NOW(), NOW(), 'canCreateTag'),
       (0, NOW(), NOW(), 'canReadTag'),
       (0, NOW(), NOW(), 'canUpdateTag'),
       (0, NOW(), NOW(), 'canDeleteTag'),
       (0, NOW(), NOW(), 'canCreateType'),
       (0, NOW(), NOW(), 'canReadType'),
       (0, NOW(), NOW(), 'canUpdateType'),
       (0, NOW(), NOW(), 'canDeleteType'),
       (0, NOW(), NOW(), 'canCreateUser'),
       (0, NOW(), NOW(), 'canReadUser'),
       (0, NOW(), NOW(), 'canUpdateUser'),
       (0, NOW(), NOW(), 'canDeleteUser'),
       (0, NOW(), NOW(), 'canReadLog'),
       (0, NOW(), NOW(), 'canAssignIssue'),
       (0, NOW(), NOW(), 'canResolveIssue'),
       (0, NOW(), NOW(), 'canCloseIssue'),
       (0, NOW(), NOW(), 'canReadDashboard');

INSERT INTO role (deleted, created_at, updated_at, name)
VALUES (0, NOW(), NOW(), 'ADMIN');

-- Generate dummy data for the permission_roles table
INSERT INTO role_permission (permission_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1),
       (33, 1),
       (34, 1),
       (35, 1),
       (36, 1),
       (37, 1);

INSERT INTO user_roles (roles_id, user_id)
VALUES (1, 1);


-- Generate dummy data for the 'tag' table with 20 rows
INSERT INTO tag (deleted, created_at, updated_at, name)
SELECT
    0,
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    CONCAT('Tag_', LPAD(ROW_NUMBER() OVER(), 2, '0'))
FROM
    (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
     UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
     UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
     UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20) AS temp;

-- Generate dummy data for the 'type' table with 20 rows
INSERT INTO type (deleted, created_at, updated_at, name)
SELECT
    0,
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    CONCAT('Type_', LPAD(ROW_NUMBER() OVER(), 2, '0'))
FROM
    (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
     UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
     UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
     UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20) AS temp;

-- Generate dummy data for the 'user' table with 20 rows
INSERT INTO user (deleted, created_at, updated_at, email, firstname, lastname, password, profile_picture)
SELECT
    0,
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    CONCAT('user', LPAD(ROW_NUMBER() OVER(), 2, '0'), '@example.com'), -- Email with incrementing number
    CONCAT('First_', LPAD(ROW_NUMBER() OVER(), 2, '0')), -- First name with incrementing number
    CONCAT('Last_', LPAD(ROW_NUMBER() OVER(), 2, '0')), -- Last name with incrementing number
    'password', -- Password (You may use a secure hash function to generate passwords)
    NULL -- Null profile_picture
FROM
    (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
     UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
     UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
     UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20) AS temp;

-- Generate dummy data for the 'issue' table with 20 rows
INSERT INTO issue (deleted, assigned_at, assigned_to, created_at, created_by, parent_issue_id, resolved_at, reviewer_id, type_id, updated_at, description, status, title)
SELECT
    0,
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    user_id, -- Use an existing user_id for assigned_to
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    user_id, -- Use an existing user_id for created_by
    NULL, -- Null parent_issue_id for now
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    user_id, -- Use an existing user_id for reviewer_id
    type_id, -- Use an existing type_id for type_id
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    CONCAT('Issue_Description_', LPAD(ROW_NUMBER() OVER(), 2, '0')), -- Issue description with incrementing number
    ELT(1 + FLOOR(RAND() * 4), 'ASSIGNED', 'CLOSED', 'CREATED', 'RESOLVED'), -- Random status from the enum
    CONCAT('Issue_Title_', LPAD(ROW_NUMBER() OVER(), 2, '0')) -- Issue title with incrementing number
FROM
    (SELECT id AS user_id FROM user ORDER BY RAND() LIMIT 20) AS random_users,
    (SELECT id AS type_id FROM type ORDER BY RAND() LIMIT 20) AS random_types;

-- Generate dummy data for the 'comment' table with 20 rows
INSERT INTO comment (deleted, created_at, updated_at, issue_id, parent_comment_id, user_id, comment_text)
SELECT
    0,
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    issue_id, -- Use an existing issue_id for issue_id
    NULL, -- Null parent_comment_id for now
    user_id, -- Use an existing user_id for user_id
    CONCAT('Comment_Text_', LPAD(ROW_NUMBER() OVER(), 2, '0')) -- Comment text with incrementing number
FROM
    (SELECT id AS issue_id FROM issue ORDER BY RAND() LIMIT 20) AS random_issues,
    (SELECT id AS user_id FROM user ORDER BY RAND() LIMIT 20) AS random_users;

-- Generate dummy data for the 'attachment' table with 20 rows
INSERT INTO attachment (deleted, comment_id, created_at, updated_at, issue_id, url)
SELECT
    0,
    comment_id, -- Use an existing comment_id for comment_id
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    DATE_ADD(NOW(), INTERVAL -(FLOOR(RAND() * 365)) DAY),
    issue_id, -- Use an existing issue_id for issue_id
    CONCAT('http://example.com/attachment_', LPAD(ROW_NUMBER() OVER(), 2, '0')) -- Attachment URL with incrementing number
FROM
    (SELECT id AS comment_id FROM comment ORDER BY RAND() LIMIT 20) AS random_comments,
    (SELECT id AS issue_id FROM issue ORDER BY RAND() LIMIT 20) AS random_issues;

-- Generate dummy data for the 'issue_tags' table with 20 rows
INSERT INTO issue_tags (issues_id, tags_id)
SELECT
    issue_id, -- Use an existing issue_id for issues_id
    tags_id -- Use an existing tags_id for tags_id
FROM
    (SELECT id AS issue_id FROM issue ORDER BY RAND() LIMIT 20) AS random_issues,
    (SELECT id AS tags_id FROM tag ORDER BY RAND() LIMIT 20) AS random_tags;



# INSERT INTO tag (deleted, created_at, updated_at, name)
# VALUES (0, NOW(), NOW(), 'Tag 1'),
#        (0, NOW(), NOW(), 'Tag 2'),
#        (0, NOW(), NOW(), 'Tag 3');
#
# -- Generate dummy data for the type table
# INSERT INTO type (deleted, created_at, updated_at, name)
# VALUES (0, NOW(), NOW(), 'Type 1'),
#        (0, NOW(), NOW(), 'Type 2'),
#        (0, NOW(), NOW(), 'Type 3');
#
# -- Generate dummy data for the user table
# INSERT INTO user (deleted, created_at, updated_at, email, firstname, lastname, password, profile_picture)
# VALUES (0, NOW(), NOW(), 'user1@example.com', 'Jack', 'Doe', 'password1', 'profile1.jpg'),
#        (0, NOW(), NOW(), 'user2@example.com', 'Jane', 'Smith', 'password2', 'profile2.jpg'),
#        (0, NOW(), NOW(), 'user3@example.com', 'Mike', 'Johnson', 'password3', 'profile3.jpg');
#
# -- Generate dummy data for the issue table
# INSERT INTO issue (deleted, assigned_at, assigned_to, created_at, created_by, parent_issue_id, resolved_at, reviewer_id,
#                    type_id, updated_at, description, status, title)
# VALUES (0, NOW(), 1, NOW(), 2, NULL, NOW(), NULL, 1, NOW(), 'Issue 1 description', 'CREATED', 'Issue 1'),
#        (0, NOW(), 2, NOW(), 1, NULL, NOW(), NULL, 2, NOW(), 'Issue 2 description', 'ASSIGNED', 'Issue 2'),
#        (0, NOW(), 1, NOW(), 3, NULL, NOW(), NULL, 3, NOW(), 'Issue 3 description', 'CLOSED', 'Issue 3');
#
# -- Generate dummy data for the comment table
# INSERT INTO comment (deleted, created_at, updated_at, issue_id, parent_comment_id, user_id, comment_text)
# VALUES (0, NOW(), NOW(), 1, NULL, 1, 'Comment 1 for Issue 1'),
#        (0, NOW(), NOW(), 1, NULL, 2, 'Comment 2 for Issue 1'),
#        (0, NOW(), NOW(), 2, NULL, 3, 'Comment 1 for Issue 2');
#
# -- Generate dummy data for the attachment table
# INSERT INTO attachment (deleted, comment_id, created_at, issue_id, updated_at, url)
# VALUES (0, 1, NOW(), NULL, NOW(), 'attachment1.jpg'),
#        (0, 1, NOW(), NULL, NOW(), 'attachment2.jpg'),
#        (0, 2, NOW(), NULL, NOW(), 'attachment3.jpg');
#
# -- Generate dummy data for the issue_tags table
# INSERT INTO issue_tags (issues_id, tags_id)
# VALUES (1, 1),
#        (1, 2),
#        (2, 3);
