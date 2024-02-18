---- Inserting data into users table
--INSERT INTO users (created_at, updated_at, id, barcode, email, first_name, last_name, file_photo_name, role)
--VALUES
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '29718428-14e1-4482-9b32-e4b806e58320', '', '211360@astanait.edu.kz', 'Daniyar', 'Chapagan', '', 'SUPER_ADMIN'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '', 'user1@example.com', 'Eva', 'Mutsuraeva', '', 'USER'),
--
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '123456', 'user1@example.com', 'Candidate1', '1', '', 'ACTIVE_STUDENT'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', '654321', 'user2@example.com', 'Candidate2', '2', '', 'ACTIVE_STUDENT'),
--
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '30a567a9-37bb-4fc0-8659-ba7543403c6d', '111111', 'gigabyte@example.com', 'gigabyte', 'jinsovich', '', 'ACTIVE_STUDENT'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '222222', 'kamchick@example.com', 'kamchick', 'kamchickovich', '', 'ACTIVE_STUDENT'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '333333', 'zestuar@example.com', 'aituar', 'zestovich', '', 'ACTIVE_STUDENT'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '444444', 'skinhead@example.com', 'skinhead', 'skinheadov', '', 'ACTIVE_STUDENT'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '555555', 'test@example.com', 'test', 'testovich', '', 'ACTIVE_STUDENT'),
--
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '84b8c821-6754-4db0-9f6f-407ff69f1086', '193211', 'satanist@example.com', 'satan', 'satanov', '', 'ACTIVE_CANDIDATE'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '3122d297-7abd-4951-9d44-171b00651465', '214234', 'drip@example.com', 'fara', 'dripovich', '', 'ACTIVE_CANDIDATE');
--
---- Inserting data into elections table
--INSERT INTO elections (year, created_at, deadline, updated_at, id, description, status)
--VALUES
--   (2023, CURRENT_TIMESTAMP, '2023-12-31 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'Election 2023', 'COMPLETED'),
--   (2024, CURRENT_TIMESTAMP, '2024-12-31 23:59:59', CURRENT_TIMESTAMP, 'a3fa5abc-cf09-4171-ba32-fbf9c6cfe1aa', 'Election 2024', 'COMPLETED'),
--   (2025, CURRENT_TIMESTAMP, '2025-12-31 23:59:59', CURRENT_TIMESTAMP, 'ab159bae-6d23-441b-b647-99a8312838c4', 'Election 2025', 'CREATED');
--
---- Inserting data into stages table
--INSERT INTO stages (created_at, deadline, updated_at, election_id, id, description, status, number, is_votable)
--VALUES
--   (CURRENT_TIMESTAMP, '2023-02-20 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'be316cf6-e4b7-415e-b21d-37fcf32815ab', '2023 stage 1', 'COMPLETED', 1, true),
--   (CURRENT_TIMESTAMP, '2023-03-10 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f', '2023 stage 2', 'COMPLETED', 2, true),
--   (CURRENT_TIMESTAMP, '2023-04-15 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea', '2023 stage 3', 'COMPLETED', 3, true),
--
--   (CURRENT_TIMESTAMP, '2025-02-20 23:59:59', CURRENT_TIMESTAMP, 'ab159bae-6d23-441b-b647-99a8312838c4', '7d8796b7-0511-441f-baff-154f58d6a766', '2025 stage 1', 'CREATED', 1, false),
--   (CURRENT_TIMESTAMP, '2025-02-20 23:59:59', CURRENT_TIMESTAMP, 'ab159bae-6d23-441b-b647-99a8312838c4', '69e25896-11b0-4525-bab5-a7d68264af84', '2025 stage 2', 'CREATED', 2, false);
--
--
---- Inserting data into candidatures table
--INSERT INTO candidatures (created_at, updated_at, approved_by_id, election_id, id, user_id)
--VALUES
---- Election 2023 candidatures
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
--    '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
--    '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8'),
--
---- Election 2025 candidatures
--    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', 'ab159bae-6d23-441b-b647-99a8312838c4',
--    'ee7331ef-052f-44c5-a721-57330503ef93', '84b8c821-6754-4db0-9f6f-407ff69f1086'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', 'ab159bae-6d23-441b-b647-99a8312838c4',
--    '2abaad23-78b0-43ba-9792-e2d7bdda99fc', '3122d297-7abd-4951-9d44-171b00651465');
--
---- Inserting data into candidature_plans table
--INSERT INTO candidature_plans (created_at, updated_at, id, candidature_id, description, instagram_link, slogan, telegram_link)
--VALUES
---- Election 2023 candidature plans
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'adeacd03-fdb0-40cf-ab75-d2aea6ce1b45', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'Description 1',
--   'https://instagram.com/link1', 'Slogan 1', 'https://t.me/link1'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1cb23d0e-1b43-4f71-81b9-33f5db18687d', '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'Description 2',
--   'https://instagram.com/link2', 'Slogan 2', 'https://t.me/link2'),
--
---- Election 2025 candidature plans
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '867952b1-570b-45f7-a41c-6d6c18f3057c', 'ee7331ef-052f-44c5-a721-57330503ef93', 'election 2025 s Description 1',
--      'https://instagram.com/link1', 'Slogan 1', 'https://t.me/link1'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '86c450d7-addf-4c0f-883e-842b76339b19', '2abaad23-78b0-43ba-9792-e2d7bdda99fc', 'elections 2025 fara drip Description 2',
--      'https://instagram.com/link2', 'Slogan 2', 'https://t.me/link2');
--
--
---- Inserting data into candidature_stages table
--INSERT INTO candidature_stages (created_at, updated_at, candidature_id, id, stage_id)
--VALUES
----- user1 candidature stages
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea'),
--
----- user2 candidature stages
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '223bf42e-b931-4926-af13-050817d160e1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '01d2835e-ff45-4847-9a82-61bf83634b28', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea');
--
----
---- Inserting data into votes table
--INSERT INTO votes (created_at, updated_at, candidature_stage_id, elector_id, id)
--VALUES
----- gigabyte votes for user1
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', '30a567a9-37bb-4fc0-8659-ba7543403c6d', '4281c81f-a0c4-40ee-91f2-c252c8d26891'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b41c535d-8be6-4986-a266-aaabe43993fc'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b7bc9f0f-85e2-41a5-b48d-9ce3301f7fdd'),
--
----- j christovich votes for user2
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '16d28a8c-1251-4c0e-88fe-60ba89abb080', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'c02de392-3cba-442d-9776-a170f987c6b9'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '223bf42e-b931-4926-af13-050817d160e1', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '0578ed02-eacf-4d91-ad73-0483a4afd248'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '01d2835e-ff45-4847-9a82-61bf83634b28', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'd48d3a19-21df-4898-ad03-9bbe17366f8a'),
--
----- zestuar votes for user1
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '54245baa-8c41-421b-aebe-65f8bdf8d2f6'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '786bba91-5f10-4d4c-8654-bf1537ba4d70'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', 'aefa21c5-faf0-4a62-8b91-59f89e18e4cf'),
--
----- skinhead votes for user2
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '0fb4d4e9-4700-4c06-8b30-5a7ab57590f0'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '223bf42e-b931-4926-af13-050817d160e1', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', 'b7721a8c-7585-4dee-8c97-79eeaaf97dcd'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '01d2835e-ff45-4847-9a82-61bf83634b28', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '7daddf6f-e1c5-4547-a4b0-0d39e3911d91'),
--
----- t. testovich votes for user1
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', 'b56794a0-27e3-4221-988f-2a8fc338eb05'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '09eaa61a-cd0e-4a5e-8d53-47c23318bf97'),
--   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '87343cc5-8d10-4ca6-8cae-6e0654e8eba8');
--
--
--
--













-- In progress scenario
INSERT INTO users (created_at, updated_at, id, barcode, email, first_name, last_name, file_photo_name, role)
VALUES
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '29718428-14e1-4482-9b32-e4b806e58320', '', '211360@astanait.edu.kz', 'Daniyar', 'Chapagan', '', 'SUPER_ADMIN'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '', 'eva@example.com', 'Eva', 'Mutsuraeva', '', 'USER'),

   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '123456', 'user1@example.com', 'Candidate1', '1', '', 'ACTIVE_CANDIDATE'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', '654321', 'user2@example.com', 'Candidate2', '2', '', 'ACTIVE_CANDIDATE'),

   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '30a567a9-37bb-4fc0-8659-ba7543403c6d', '111111', 'gigabyte@example.com', 'gigabyte', 'jinsovich', '', 'ACTIVE_STUDENT'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '222222', 'kamchick@example.com', 'kamchick', 'kamchickovich', '', 'ACTIVE_STUDENT'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '333333', 'zestuar@example.com', 'aituar', 'zestovich', '', 'ACTIVE_STUDENT'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '444444', 'skinhead@example.com', 'skinhead', 'skinheadov', '', 'ACTIVE_STUDENT'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '555555', 'test@example.com', 'test', 'testovich', '', 'ACTIVE_STUDENT');


-- Inserting data into elections table
INSERT INTO elections (year, created_at, deadline, updated_at, id, description, status)
VALUES
   (2024, CURRENT_TIMESTAMP, '2024-12-31 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'Election 2024', 'IN_PROGRESS');

-- Inserting data into stages table
INSERT INTO stages (created_at, deadline, updated_at, election_id, id, description, status, number, is_votable)
VALUES
   (CURRENT_TIMESTAMP, '2024-02-20 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'be316cf6-e4b7-415e-b21d-37fcf32815ab', '2024 stage 1', 'COMPLETED', 1, true),
   (CURRENT_TIMESTAMP, '2024-03-10 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f', '2024 stage 2', 'COMPLETED', 2, true),
   (CURRENT_TIMESTAMP, '2024-04-15 23:59:59', CURRENT_TIMESTAMP, '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea', '2024 stage 3', 'IN_PROGRESS', 3, true);


-- Inserting data into candidatures table
INSERT INTO candidatures (created_at, updated_at, approved_by_id, election_id, id, user_id)
VALUES
-- Election 2024 candidatures
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
    '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
    '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8');

-- Inserting data into candidature_plans table
INSERT INTO candidature_plans (created_at, updated_at, id, candidature_id, description, instagram_link, slogan, telegram_link)
VALUES
-- Election 2024 candidature plans
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'adeacd03-fdb0-40cf-ab75-d2aea6ce1b45', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'Description 1',
   'https://instagram.com/link1', 'Slogan 1', 'https://t.me/link1'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1cb23d0e-1b43-4f71-81b9-33f5db18687d', '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'Description 2',
   'https://instagram.com/link2', 'Slogan 2', 'https://t.me/link2');


INSERT INTO candidature_stages (created_at, updated_at, candidature_id, id, stage_id)
VALUES
--- user1 candidature stages
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea'),

--- user2 candidature stages
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '223bf42e-b931-4926-af13-050817d160e1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '8dcf75a5-0636-425a-9ffd-b732d12ff197', '01d2835e-ff45-4847-9a82-61bf83634b28', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea');


INSERT INTO candidature_stage_plans (created_at, updated_at, id, candidature_stage_id, link1, link2, description)
VALUES
--- user1 candidature stage plans
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ceede86d-b5c6-4f6d-9a9f-b51a6979db8d', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage1'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '5068432f-4195-45ea-b4fb-be1bdfecb2c4', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage2'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1aae27e3-158e-4c5d-80be-1c6ea245b8a4', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage3'),

--- user2 candidature stage plans
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '43c239f5-8982-4857-be1b-1a557f64b542', '16d28a8c-1251-4c0e-88fe-60ba89abb080',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage1'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '0766fec7-22ef-4e37-9b67-d6efef5e3f98', '223bf42e-b931-4926-af13-050817d160e1',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage2'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '53d8bb5d-3abf-419e-b01c-982f71b81afe', '01d2835e-ff45-4847-9a82-61bf83634b28',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage3');


INSERT INTO votes (created_at, updated_at, candidature_stage_id, elector_id, id)
VALUES
--- gigabyte votes for user1
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', '30a567a9-37bb-4fc0-8659-ba7543403c6d', '4281c81f-a0c4-40ee-91f2-c252c8d26891'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b41c535d-8be6-4986-a266-aaabe43993fc'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b7bc9f0f-85e2-41a5-b48d-9ce3301f7fdd'),

--
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '16d28a8c-1251-4c0e-88fe-60ba89abb080', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'c02de392-3cba-442d-9776-a170f987c6b9'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '223bf42e-b931-4926-af13-050817d160e1', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '0578ed02-eacf-4d91-ad73-0483a4afd248'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '01d2835e-ff45-4847-9a82-61bf83634b28', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'd48d3a19-21df-4898-ad03-9bbe17366f8a'),

--- zestuar votes for user1
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '54245baa-8c41-421b-aebe-65f8bdf8d2f6'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '786bba91-5f10-4d4c-8654-bf1537ba4d70'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', 'aefa21c5-faf0-4a62-8b91-59f89e18e4cf'),

--- skinhead votes for user2
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '0fb4d4e9-4700-4c06-8b30-5a7ab57590f0'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '223bf42e-b931-4926-af13-050817d160e1', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', 'b7721a8c-7585-4dee-8c97-79eeaaf97dcd'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '01d2835e-ff45-4847-9a82-61bf83634b28', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '7daddf6f-e1c5-4547-a4b0-0d39e3911d91'),

--- t. testovich votes for user1
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', 'b56794a0-27e3-4221-988f-2a8fc338eb05'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '09eaa61a-cd0e-4a5e-8d53-47c23318bf97'),
   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '87343cc5-8d10-4ca6-8cae-6e0654e8eba8');
