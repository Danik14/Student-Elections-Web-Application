truncate table candidature_plans cascade;
truncate table candidature_stage_plans cascade;
truncate table candidature_stages cascade;
truncate table candidatures cascade;
truncate table elections cascade;
truncate table stages cascade;
truncate table users cascade;
truncate table votes cascade;

-- Created election scenario

INSERT INTO users (created_at, updated_at, id, barcode, email, first_name, last_name, file_photo_name, role)
VALUES
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '2004cc3b-a00b-649b-0000-000000000000', '211360', '211360@astanait.edu.kz', 'Daniyar', 'Chapagan', '', 'SUPER_ADMIN'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc', '', 'eva@example.com', 'Eva', 'Mutsuraeva', '', 'ELECTION_OFFICIAL'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '869ffd74-b6f1-6a88-0000-000000000000', '211362', '211362@astanait.edu.kz', 'Daniyar2', 'Chapagan2', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'd93b59d1-7233-4ad8-b997-0f19b0e3341b', '777777', '777777@astanait.edu.kz', 'Aivar', 'GigaBoneless', '', 'APPLIED_FOR_CANDIDATURE'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be', '123456', 'user1@example.com', 'Candidate1', '1', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'ed28793a-14a3-48cb-a3d7-24ac1804bea8', '654321', 'user2@example.com', 'Candidate2', '2', '', 'ACTIVE_STUDENT'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '30a567a9-37bb-4fc0-8659-ba7543403c6d', '111111', 'gigabyte@example.com', 'gigabyte', 'jinsovich', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', '9a9c8cea-b3ce-484c-bbf5-01da56eaa632', '222222', 'kamchick@example.com', 'kamchick', 'kamchickovich', '', 'ACTIVE_STUDENT'),

   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'df5e8505-8a34-48fc-b3e5-56eb4463dc93', '333333', 'zestuar@example.com', 'aituar', 'zestovich', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'eb77f9b4-1553-4b65-9213-c657ed0ad641', '444444', 'skinhead@example.com', 'skinhead', 'skinheadov', '', 'ACTIVE_STUDENT'),
   ('2023-08-30 14:00:00', '2023-08-30 14:00:00', 'fa48d4ee-ccd9-4c84-b95a-86291c8662f6', '555555', 'test@example.com', 'test', 'testovich', '', 'ACTIVE_STUDENT');


INSERT INTO elections (year, created_at, deadline, updated_at, id, description, status)
VALUES
   (2025, '2025-01-20 14:00:00', '2025-03-31 23:59:59', '2025-01-20 14:00:00', 'b09903bf-03fb-4c44-a925-29db853b9477', 'Election 2025', 'CREATED');