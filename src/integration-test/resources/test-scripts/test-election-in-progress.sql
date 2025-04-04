truncate table candidature_plans cascade;
truncate table candidature_stage_plans cascade;
truncate table candidature_stages cascade;
truncate table candidatures cascade;
truncate table elections cascade;
truncate table stages cascade;
truncate table users cascade;
truncate table votes cascade;

-- In progress scenario
INSERT INTO users (created_at, updated_at, id, barcode, email, first_name, last_name, file_photo_name, role)
VALUES
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '2004cc3b-a00b-649b-0000-000000000000', '211360', '211360@astanait.edu.kz', 'Daniyar', 'Chapagan', '', 'SUPER_ADMIN'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '', 'eva@example.com', 'Eva', 'Mutsuraeva', '', 'USER'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '123456', 'user1@example.com', 'Candidate1', '1', '', 'ACTIVE_CANDIDATE'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', '654321', 'user2@example.com', 'Candidate2', '2', '', 'ACTIVE_CANDIDATE'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '30a567a9-37bb-4fc0-8659-ba7543403c6d', '111111', 'gigabyte@example.com', 'gigabyte', 'jinsovich', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '222222', 'kamchick@example.com', 'kamchick', 'kamchickovich', '', 'ACTIVE_STUDENT'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '333333', 'zestuar@example.com', 'aituar', 'zestovich', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '444444', 'skinhead@example.com', 'skinhead', 'skinheadov', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '555555', 'test@example.com', 'test', 'testovich', '', 'ACTIVE_STUDENT');


-- Inserting data into elections table
INSERT INTO elections (year, created_at, deadline, updated_at, id, description, status)
VALUES
   (2023, '2023-01-20 14:00:00', '2023-03-31 23:59:59', '2023-01-20 14:00:00', 'b09903bf-03fb-4c44-a925-29db853b9477', 'Election 2023', 'COMPLETED'),
   (2024, '2024-01-20 14:00:00', '2024-03-31 23:59:59', '2024-01-20 14:00:00', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'Election 2024', 'IN_PROGRESS');

-- Inserting data into stages table
INSERT INTO stages (created_at, deadline, updated_at, election_id, id, description, status, number, is_votable)
VALUES
-- Election 2023 stage
   ('2023-02-20 14:00:00', '2023-02-20 23:59:59', '2023-02-20 14:00:00', 'b09903bf-03fb-4c44-a925-29db853b9477', '0552f1d3-e597-4e51-afcc-4b5d0fa94454', '2023 stage 1', 'COMPLETED', 1, true),
   ('2023-02-20 14:00:00', '2023-03-20 23:59:59', '2023-02-20 14:00:00', 'b09903bf-03fb-4c44-a925-29db853b9477', '04cdaca4-a321-4968-93c2-068b7f3c9894', '2023 stage 2', 'COMPLETED', 2, true),

-- Election 2024 stage
   ('2024-02-20 14:00:00', '2024-01-20 23:59:59', '2024-02-20 14:00:00', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'be316cf6-e4b7-415e-b21d-37fcf32815ab', '2024 stage 1', 'COMPLETED', 1, true),
   ('2024-02-20 14:00:00', '2024-02-20 23:59:59', '2024-02-20 14:00:00', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f', '2024 stage 2', 'COMPLETED', 2, true),
   ('2024-02-20 14:00:00', '2024-03-20 23:59:59', '2024-02-20 14:00:00', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea', '2024 stage 3', 'IN_PROGRESS', 3, true);


-- Inserting data into candidatures table
INSERT INTO candidatures (created_at, updated_at, approved_by_id, election_id, id, user_id)
VALUES
-- Election 2023 candidatures
-- candidature gigabyte
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', 'b09903bf-03fb-4c44-a925-29db853b9477',
    '812991c5-646b-4fc7-bff9-f809cdeca5ef', '30a567a9-37bb-4fc0-8659-ba7543403c6d'),
-- candidature kamchik
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', 'b09903bf-03fb-4c44-a925-29db853b9477',
    'c858dc55-ba61-4fb8-ae5d-8d03e589be54', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632'),

-- Election 2024 candidatures
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
   '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '9f5eb7fe-5531-4d59-b644-3b93c9abd8d1',
    '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8');

-- Inserting data into candidature_plans table
INSERT INTO candidature_plans (created_at, updated_at, id, candidature_id, description, instagram_link, slogan, telegram_link)
VALUES
-- Election 2023 candidature plans
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '47a6f4e3-10f8-4e07-abd7-53edd70c131b', '812991c5-646b-4fc7-bff9-f809cdeca5ef', 'Description 1',
    'https://instagram.com/link1', 'Slogan 2023 1', 'https://t.me/link1'),
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '69346ddd-5d4c-4233-bb00-a1db85c3cc9a', 'c858dc55-ba61-4fb8-ae5d-8d03e589be54', 'Description 2',
    'https://instagram.com/link2', 'Slogan 2023 2', 'https://t.me/link2'),

-- Election 2024 candidature plans
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'adeacd03-fdb0-40cf-ab75-d2aea6ce1b45', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'Description 1',
   'https://instagram.com/link1', 'Slogan 1', 'https://t.me/link1'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '1cb23d0e-1b43-4f71-81b9-33f5db18687d', '8dcf75a5-0636-425a-9ffd-b732d12ff197', 'Description 2',
   'https://instagram.com/link2', 'Slogan 2', 'https://t.me/link2');


INSERT INTO candidature_stages (created_at, updated_at, candidature_id, id, stage_id)
VALUES
-- Election 2023 candidature stages
-- gigabyte candidature stages
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '812991c5-646b-4fc7-bff9-f809cdeca5ef', '646aa141-5b08-4ae7-a6e2-c7e02b2c9d0d', '0552f1d3-e597-4e51-afcc-4b5d0fa94454'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '812991c5-646b-4fc7-bff9-f809cdeca5ef', '52538b9b-d7a8-4b1e-8135-81aba2be894e', '04cdaca4-a321-4968-93c2-068b7f3c9894'),

-- kamchik candidature stages
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', 'c858dc55-ba61-4fb8-ae5d-8d03e589be54', '70192ac1-7e41-4386-bb46-2f7f15d9e933', '0552f1d3-e597-4e51-afcc-4b5d0fa94454'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', 'c858dc55-ba61-4fb8-ae5d-8d03e589be54', '60576319-9fca-4179-bfd8-94bb9e59fa03', '04cdaca4-a321-4968-93c2-068b7f3c9894'),

-- Election 2024 candidature stages
--- user1 candidature stages
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea'),

--- user2 candidature stages
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '8dcf75a5-0636-425a-9ffd-b732d12ff197', '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'be316cf6-e4b7-415e-b21d-37fcf32815ab'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '8dcf75a5-0636-425a-9ffd-b732d12ff197', '223bf42e-b931-4926-af13-050817d160e1', '9dbc17ae-c522-48a7-b9f9-531d8b8a528f'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '8dcf75a5-0636-425a-9ffd-b732d12ff197', '01d2835e-ff45-4847-9a82-61bf83634b28', 'de9d9185-6d2e-4613-af3b-4d1733d0d9ea');


INSERT INTO candidature_stage_plans (created_at, updated_at, id, candidature_stage_id, link1, link2, description)
VALUES
-- Election 2023 candidature stage plans
-- gigabyte candidature stage plans
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '5224f402-5bcb-483e-87dd-f62e3c936949', '646aa141-5b08-4ae7-a6e2-c7e02b2c9d0d',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description gigabyte stage1'),
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '3decea58-4ff2-43d4-a9f9-43ad1e73375d', '52538b9b-d7a8-4b1e-8135-81aba2be894e',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description gigabyte stage2'),

-- kamchik candidature
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '61e445ac-0dbe-4f30-8d9c-c87a43dbd331', '70192ac1-7e41-4386-bb46-2f7f15d9e933',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description kamchik stage1'),
    ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '542d4241-4aa9-4333-b557-7bca15b719f3', '60576319-9fca-4179-bfd8-94bb9e59fa03',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description kamchik stage2'),

-- Election 2024 candidature stages
--- user1 candidature stage plans
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'ceede86d-b5c6-4f6d-9a9f-b51a6979db8d', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage1'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '5068432f-4195-45ea-b4fb-be1bdfecb2c4', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage2'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '1aae27e3-158e-4c5d-80be-1c6ea245b8a4', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user1 stage3'),

--- user2 candidature stage plans
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '43c239f5-8982-4857-be1b-1a557f64b542', '16d28a8c-1251-4c0e-88fe-60ba89abb080',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage1'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '0766fec7-22ef-4e37-9b67-d6efef5e3f98', '223bf42e-b931-4926-af13-050817d160e1',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage2'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '53d8bb5d-3abf-419e-b01c-982f71b81afe', '01d2835e-ff45-4847-9a82-61bf83634b28',
    'http://localhost:8000/123', 'http://localhost:8000/123', 'some description user2 stage3');


INSERT INTO votes (created_at, updated_at, candidature_stage_id, elector_id, id)
VALUES
--- Election 2023 votes
-- user1 votes for gigabyte
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '646aa141-5b08-4ae7-a6e2-c7e02b2c9d0d', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '7573c822-7977-4f64-894d-fa71425a2c40'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '52538b9b-d7a8-4b1e-8135-81aba2be894e', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '67313cf9-6234-4ac4-b55e-a3fca4436dfc'),

-- user2 votes for '2023-02-20 14:00:00'
   ('2023-02-20 14:00:00', CURRENT_TIMESTAMP, '646aa141-5b08-4ae7-a6e2-c7e02b2c9d0d', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', 'a6c99b7f-239a-4817-a876-0a27bdfa9fa0'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '52538b9b-d7a8-4b1e-8135-81aba2be894e', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', 'c8096187-b0e2-4abe-8b45-05f06efc88d1'),

-- zestuar votes for kamchik
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '70192ac1-7e41-4386-bb46-2f7f15d9e933', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '1ea6e4ed-d2a4-4552-ad6b-571abe90e244'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '60576319-9fca-4179-bfd8-94bb9e59fa03', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '0ca4725e-fa76-4112-b298-ed136494194a'),

-- skinhead votes for kamchik
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '70192ac1-7e41-4386-bb46-2f7f15d9e933', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', 'f9dc3881-5277-4826-a66a-af64d45ce2f9'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '60576319-9fca-4179-bfd8-94bb9e59fa03', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '1a6e7b10-6f06-4c27-b15b-c462bba21161'),

-- test testovich votes for kamchik
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '70192ac1-7e41-4386-bb46-2f7f15d9e933', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '366ec4e8-ae50-467e-9a24-eb73b2f59340'),
   ('2023-02-20 14:00:00', '2023-02-20 14:00:00', '60576319-9fca-4179-bfd8-94bb9e59fa03', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', 'a41417a2-3f82-40ea-a576-274ba10fc75c'),

--- Election 2024 votes
--- gigabyte votes for user1
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', '30a567a9-37bb-4fc0-8659-ba7543403c6d', '4281c81f-a0c4-40ee-91f2-c252c8d26891'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b41c535d-8be6-4986-a266-aaabe43993fc'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', '30a567a9-37bb-4fc0-8659-ba7543403c6d', 'b7bc9f0f-85e2-41a5-b48d-9ce3301f7fdd'),

--- kamchik
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '16d28a8c-1251-4c0e-88fe-60ba89abb080', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'c02de392-3cba-442d-9776-a170f987c6b9'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '223bf42e-b931-4926-af13-050817d160e1', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '0578ed02-eacf-4d91-ad73-0483a4afd248'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '01d2835e-ff45-4847-9a82-61bf83634b28', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', 'd48d3a19-21df-4898-ad03-9bbe17366f8a'),

--- zestuar votes for user1
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '54245baa-8c41-421b-aebe-65f8bdf8d2f6'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '786bba91-5f10-4d4c-8654-bf1537ba4d70'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', 'aefa21c5-faf0-4a62-8b91-59f89e18e4cf'),

--- skinhead votes for user2
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '16d28a8c-1251-4c0e-88fe-60ba89abb080', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '0fb4d4e9-4700-4c06-8b30-5a7ab57590f0'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '223bf42e-b931-4926-af13-050817d160e1', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', 'b7721a8c-7585-4dee-8c97-79eeaaf97dcd'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '01d2835e-ff45-4847-9a82-61bf83634b28', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '7daddf6f-e1c5-4547-a4b0-0d39e3911d91'),

--- t. testovich votes for user1
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'b62443c7-8d37-4ef1-a8d1-d0d3f6908412', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', 'b56794a0-27e3-4221-988f-2a8fc338eb05'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', 'e2df0d5d-eb3f-4b2e-84a8-f962c0ae10a0', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '09eaa61a-cd0e-4a5e-8d53-47c23318bf97'),
   ('2024-02-20 14:00:00', '2024-02-20 14:00:00', '1efe9085-27b5-4b1c-9997-6c0144aaa8fa', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '87343cc5-8d10-4ca6-8cae-6e0654e8eba8');