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
    (0, NOW(), NOW(), 'canDeleteUser');

INSERT INTO role (deleted, created_at, updated_at, name)
VALUES
    (0, NOW(), NOW(), 'ADMIN');

-- Generate dummy data for the permission_roles table
INSERT INTO permission_roles (permissions_id, roles_id)
VALUES
    (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1), (21, 1), (22, 1), (23, 1), (24, 1), (25, 1), (26, 1), (27, 1), (28, 1), (29, 1), (30, 1), (31, 1), (32, 1);

INSERT INTO user_roles (roles_id, user_id)
VALUES
    (1, 1);
