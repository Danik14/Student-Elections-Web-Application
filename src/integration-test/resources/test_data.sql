insert into roles (role_id, name)
values (gen_random_uuid(), 'Admin'),
       (gen_random_uuid(), 'Election Official'),
       (gen_random_uuid(), 'Candidate'),
       (gen_random_uuid(), 'Elector');

insert into users (user_id, email, first_name, last_name, password, role_id)
values (gen_random_uuid(), '211416@astanait.edu.kz', 'Alibek', 'Keneskhanov', 'test', (select role_id from roles limit 1));

insert into elections (election_id, description, year, created_at, deadline, total_votes_count)
values ("813a19ae-49ce-4934-917c-78d2d7dc6153", 'Election for student council of 2024', true, 2024, now(), '20-04-2024 00:00:00', 0);

insert into stages (stage_id, description, deadline, election_id)
values (gen_random_uuid(), 'Stage 1 for Student Election of 2024, presentation of candidature programs', '10-03-2024 00:00:00', ( select election_id from elections limit 1));

insert into candidatures (candidature_id, election_id, user_id)
values (gen_random_uuid(), (select election_id from elections limit 1), (select user_id from users limit 1));

insert into candidature_stages (candidature_stage_id, stage_id, candidature_id)
values (gen_random_uuid(), (select stage_id from stages limit 1), (select candidature_id from candidatures limit 1));

insert into votes(vote_id, elector_id, candidature_stage_id)
values (gen_random_uuid(), (select user_id from users limit 1), (select candidature_stage_id from candidature_stages limit 1));