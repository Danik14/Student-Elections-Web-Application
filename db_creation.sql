create table roles (
    role_id uuid primary key ,
    name varchar(50)
);

create table users (
    user_id uuid primary key,
    email varchar(100),
    first_name varchar(50),
    last_name varchar(50),
    password text,
    role_id uuid references roles(role_id)
);

create table elections (
    election_id uuid primary key,
    description text,
    year int,
    created_at timestamp,
    deadline timestamp,
    total_votes_count int
);

create table stages (
    stage_id uuid primary key,
    description text,
    deadline timestamp,
    election_id uuid references elections(election_id)
);

create table candidatures (
    candidature_id uuid primary key,
    election_id uuid references elections(election_id),
    user_id uuid references users(user_id)
);

create table candidature_stages(
    candidature_stage_id uuid primary key ,
    stage_id uuid references stages(stage_id),
    candidature_id uuid references candidatures(candidature_id)
);

create table votes (
    vote_id uuid primary key,
    elector_id uuid references users(user_id),
    candidature_stage_id uuid references candidature_stages(candidature_stage_id)
);


