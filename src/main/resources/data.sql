INSERT INTO user (deleted, created_at, updated_at, email, firstname, lastname, password, profile_picture)
VALUES
    (0, NOW(), NOW(), 'admin', 'John', 'Doe', '$2a$10$v7ODyhz1mqID5W9bUT5/o.AcNKH9hrxSt607nezK9km75uI6Q/jyu', 'profile1.jpg');

INSERT INTO permission (deleted, created_at, updated_at, name)
VALUES
    (0, NOW(), NOW(), 'canCreateAttachment'),
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
VALUES
    (0, NOW(), NOW(), 'ADMIN');

-- Generate dummy data for the permission_roles table
INSERT INTO role_permission (permission_id, role_id)
VALUES
    (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1), (21, 1), (22, 1), (23, 1), (24, 1), (25, 1), (26, 1), (27, 1), (28, 1), (29, 1), (30, 1), (31, 1), (32, 1),(33, 1),(34, 1),(35, 1),(36, 1),(37,1);

INSERT INTO user_roles (roles_id, user_id)
VALUES
    (1, 1);

INSERT INTO tag (deleted, created_at, updated_at, name)
VALUES
    (0, NOW(), NOW(), 'Tag 1'),
    (0, NOW(), NOW(), 'Tag 2'),
    (0, NOW(), NOW(), 'Tag 3');

-- Generate dummy data for the type table
INSERT INTO type (deleted, created_at, updated_at, name)
VALUES
    (0, NOW(), NOW(), 'Type 1'),
    (0, NOW(), NOW(), 'Type 2'),
    (0, NOW(), NOW(), 'Type 3');

-- Generate dummy data for the user table
INSERT INTO user (deleted, created_at, updated_at, email, firstname, lastname, password, profile_picture)
VALUES
    (0, NOW(), NOW(), 'user1@example.com', 'John', 'Doe', 'password1', 'profile1.jpg'),
    (0, NOW(), NOW(), 'user2@example.com', 'Jane', 'Smith', 'password2', 'profile2.jpg'),
    (0, NOW(), NOW(), 'user3@example.com', 'Mike', 'Johnson', 'password3', 'profile3.jpg');

-- Generate dummy data for the issue table
INSERT INTO issue (deleted, assigned_at, assigned_to, created_at, created_by, parent_issue_id, resolved_at, reviewer_id, type_id, updated_at, description, status, title)
VALUES
    (0, NOW(), 1, NOW(), 2, NULL, NOW(), NULL, 1, NOW(), 'Issue 1 description', 'CREATED', 'Issue 1'),
    (0, NOW(), 2, NOW(), 1, NULL, NOW(), NULL, 2, NOW(), 'Issue 2 description', 'ASSIGNED', 'Issue 2'),
    (0, NOW(), 1, NOW(), 3, NULL, NOW(), NULL, 3, NOW(), 'Issue 3 description', 'CLOSED', 'Issue 3');

-- Generate dummy data for the comment table
INSERT INTO comment (deleted, created_at, updated_at, issue_id, parent_comment_id, user_id, comment_text)
VALUES
    (0, NOW(), NOW(), 1, NULL, 1, 'Comment 1 for Issue 1'),
    (0, NOW(), NOW(), 1, NULL, 2, 'Comment 2 for Issue 1'),
    (0, NOW(), NOW(), 2, NULL, 3, 'Comment 1 for Issue 2');

-- Generate dummy data for the attachment table
INSERT INTO attachment (deleted, comment_id, created_at, issue_id, updated_at, url)
VALUES
    (0, 1, NOW(), NULL, NOW(), 'attachment1.jpg'),
    (0, 1, NOW(), NULL, NOW(), 'attachment2.jpg'),
    (0, 2, NOW(), NULL, NOW(), 'attachment3.jpg');

-- Generate dummy data for the issue_tags table
INSERT INTO issue_tags (issues_id, tags_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3);
