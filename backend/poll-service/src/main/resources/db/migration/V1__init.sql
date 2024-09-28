CREATE SEQUENCE IF NOT EXISTS poll_seq
AS BIGINT
START 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS polls(
    id BIGINT PRIMARY KEY DEFAULT nextval('poll_seq'),
    user_id BIGINT NOT NULL,
    question TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP NOT NULL,
    optional_answer_allowed BOOLEAN DEFAULT FALSE,
    poll_type VARCHAR(24) DEFAULT 'MULTIPLE',
    photo VARCHAR(1024) NULL,
    has_answer BOOLEAN DEFAULT FALSE,
    answer_visibility BOOLEAN DEFAULT FALSE,
    vote_visibility BOOLEAN DEFAULT FALSE,
    correct_answer_id INT NULL
);

CREATE SEQUENCE IF NOT EXISTS poll_tags_seq
START 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS poll_tags(
    id INT PRIMARY KEY DEFAULT nextval('poll_tags_seq'),
    poll_id BIGINT NOT NULL,
    tag_id INT NOT NULL,

    FOREIGN KEY(poll_id) REFERENCES polls(id)
);

CREATE SEQUENCE IF NOT EXISTS poll_ans_seq
START 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS poll_ans(
    id BIGINT PRIMARY KEY DEFAULT nextval('poll_ans_seq'),
    poll_id BIGINT NOT NULL,
    answer VARCHAR(1024) NOT NULL,
    ans_id INT NOT NULL,

    FOREIGN KEY(poll_id) REFERENCES polls(id)
);

CREATE SEQUENCE IF NOT EXISTS poll_attempts_seq
START 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS poll_attempts(
    id BIGINT PRIMARY KEY DEFAULT nextval('poll_attempts_seq'),
    user_id BIGINT NOT NULL,
    poll_id BIGINT NOT NULL,
    ans_id INTEGER NOT NULL,
    answered_at TIMESTAMP NOT NULL,
    public BOOLEAN DEFAULT TRUE,
    optional_answer VARCHAR(1024) NULL,

    FOREIGN KEY(poll_id) REFERENCES polls(id)
);