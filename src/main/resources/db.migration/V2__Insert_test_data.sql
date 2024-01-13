INSERT INTO roles (role_id, name)
VALUES (gen_random_uuid(), 'Admin'),
       (gen_random_uuid(), 'Election Official'),
       (gen_random_uuid(), 'Candidate'),
       (gen_random_uuid(), 'Elector');

SELECT * FROM roles;

INSERT INTO users (user_id, email, first_name, last_name, password, role_id)
VALUES (gen_random_uuid(), '211416@astanait.edu.kz', 'Alibek', 'Keneskhanov', 'test', (SELECT role_id FROM roles LIMIT 1));

SELECT * FROM users;

INSERT INTO elections (election_id, description, year, created_at, deadline, total_votes_count)
VALUES ('813a19ae-49ce-4934-917c-78d2d7dc6153', 'Election for student council of 2024', true, 2024, now(), '20-04-2024 00:00:00', 0);

SELECT * FROM elections;

INSERT INTO stages (stage_id, description, deadline, election_id)
VALUES (gen_random_uuid(), 'Stage 1 for Student Election of 2024, presentation of candidature programs', '10-03-2024 00:00:00', ( SELECT election_id FROM elections LIMIT 1));

SELECT * FROM stages;

INSERT INTO candidatures (candidature_id, election_id, user_id)
VALUES (gen_random_uuid(), (SELECT election_id FROM elections LIMIT 1), (SELECT user_id FROM users LIMIT 1));

SELECT * FROM candidatures;

INSERT INTO candidature_stages (candidature_stage_id, stage_id, candidature_id)
VALUES (gen_random_uuid(), (SELECT stage_id FROM stages LIMIT 1), (SELECT candidature_id FROM candidatures LIMIT 1));

SELECT * FROM candidature_stages;

INSERT INTO votes(vote_id, elector_id, candidature_stage_id)
VALUES (gen_random_uuid(), (SELECT user_id FROM users LIMIT 1), (SELECT candidature_stage_id FROM candidature_stages LIMIT 1));

SELECT * FROM votes;
