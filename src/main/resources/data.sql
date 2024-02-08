-- Inserting data into users table
INSERT INTO users (created_at, updated_at, id, barcode, email, first_name, last_name, file_photo_name, role)
VALUES
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '', 'user1@example.com', 'Eva', 'Mutsuraeva', 'user3.jpeg', 'USER'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '123456', 'user1@example.com', 'Candidate1', '1', 'user1.jpeg', 'ACTIVE_CANDIDATE'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', '654321', 'user2@example.com', 'Candidate2', '2', 'user2.jpg', 'ACTIVE_CANDIDATE');

-- Inserting data into elections table
INSERT INTO elections (total_votes_count, year, created_at, deadline, updated_at, id, description, status)
VALUES
   (1000, 2023, CURRENT_TIMESTAMP, '2023-12-31 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'Election 2023', 'COMPLETED'),
   (1500, 2024, CURRENT_TIMESTAMP, '2024-12-31 23:59:59', CURRENT_TIMESTAMP, 'a3fa5abc-cf09-4171-ba32-fbf9c6cfe1aa', 'Election 2024', 'IN_PROGRESS');

-- Inserting data into stages table
INSERT INTO stages (created_at, deadline, updated_at, election_id, id, description)
VALUES
   (CURRENT_TIMESTAMP, '2023-12-20 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'be316cf6-e4b7-415e-b21d-37fcf32815ab', 'Nomination Stage'),
   (CURRENT_TIMESTAMP, '2024-01-20 23:59:59', CURRENT_TIMESTAMP, 'a3fa5abc-cf09-4171-ba32-fbf9c6cfe1aa', '60127c87-351d-4693-aeed-ea0bbd00cde8', 'Voting Stage');


-- Inserting data into candidature_plans table
INSERT INTO candidature_plans (created_at, updated_at, id, description, instagram_link, slogan, telegram_link)
VALUES
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'adeacd03-fdb0-40cf-ab75-d2aea6ce1b45', 'Description 1',
   'https://instagram.com/link1', 'Slogan 1', 'https://t.me/link1'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1cb23d0e-1b43-4f71-81b9-33f5db18687d', 'Description 2',
   'https://instagram.com/link2', 'Slogan 2', 'https://t.me/link2');


-- Inserting data into candidatures table
INSERT INTO candidatures (approved, created_at, updated_at, approved_by_id, election_id, candidature_plan_id, id, user_id)
VALUES
   (true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
   'adeacd03-fdb0-40cf-ab75-d2aea6ce1b45', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be'),
   (false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', 'a3fa5abc-cf09-4171-ba32-fbf9c6cfe1aa',
   '1cb23d0e-1b43-4f71-81b9-33f5db18687d', '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8');

-- Inserting data into candidature_stages table
INSERT INTO candidature_stages (created_at, updated_at, candidature_id, id, stage_id)
VALUES
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'c91bd8bf-4c90-44d1-aeb8-2dca2b586c2e', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'fe257553-fbc6-420c-9218-5a824c143b7c', '60127c87-351d-4693-aeed-ea0bbd00cde8');

--
-- Inserting data into votes table
INSERT INTO votes (created_at, updated_at, candidature_stage_id, elector_id, id)
VALUES
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'c91bd8bf-4c90-44d1-aeb8-2dca2b586c2e', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '4281c81f-a0c4-40ee-91f2-c252c8d26891'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'fe257553-fbc6-420c-9218-5a824c143b7c', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', 'd48d3a19-21df-4898-ad03-9bbe17366f8a');
