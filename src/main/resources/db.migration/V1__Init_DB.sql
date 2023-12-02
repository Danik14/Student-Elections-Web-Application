create table roles (
    role_id uuid primary key ,
    name varchar(50) not null
);

create table users (
    user_id uuid primary key,
    email varchar(100) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    password text  not null,
    role_id uuid references roles(role_id) not null
);

create table elections (
    election_id uuid primary key,
    description text  not null,
    year int  not null,
    created_at timestamp  not null,
    deadline timestamp  not null,
    total_votes_count int not null default 0
);

create table stages (
    stage_id uuid primary key,
    description text  not null,
    deadline timestamp  not null,
    election_id uuid references elections(election_id) not null
);

create table candidatures (
    candidature_id uuid primary key,
    election_id uuid references elections(election_id) not null,
    user_id uuid references users(user_id) not null
);

create table candidature_stages(
    candidature_stage_id uuid primary key ,
    stage_id uuid references stages(stage_id) not null,
    candidature_id uuid references candidatures(candidature_id) not null
);

create table votes (
    vote_id uuid primary key,
    elector_id uuid references users(user_id) not null,
    candidature_stage_id uuid references candidature_stages(candidature_stage_id) not null
);


