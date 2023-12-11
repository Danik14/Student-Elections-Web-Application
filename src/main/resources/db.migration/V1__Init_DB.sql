CREATE TABLE elections.roles (
    role_id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE elections.users (
    user_id uuid PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    password TEXT  NOT NULL,
    role_id UUID REFERENCES roles(role_id) NOT NULL
);

CREATE TABLE elections.elections (
    election_id UUID PRIMARY KEY,
    description TEXT NOT NULL,
    status TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    year INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deadline TIMESTAMP NOT NULL,
    total_votes_count INT NOT NULL DEFAULT 0
);

CREATE TABLE elections.stages (
    stage_id UUID PRIMARY KEY,
    description TEXT NOT NULL,
    deadline TIMESTAMP NOT NULL,
    election_id UUID REFERENCES elections(election_id) NOT NULL
);

CREATE TABLE elections.candidatures (
    candidature_id UUID PRIMARY KEY,
    election_id UUID REFERENCES elections(election_id) NOT NULL,
    user_id UUID REFERENCES users(user_id) NOT NULL
);

CREATE TABLE elections.candidature_stages (
    candidature_stage_id UUID PRIMARY KEY,
    stage_id UUID REFERENCES stages(stage_id) NOT NULL,
    candidature_id UUID REFERENCES candidatures(candidature_id) NOT NULL
);

CREATE TABLE elections.votes (
    vote_id UUID PRIMARY KEY,
    elector_id UUID REFERENCES users(user_id) NOT NULL,
    candidature_stage_id UUID REFERENCES candidature_stages(candidature_stage_id) NOT NULL
);
