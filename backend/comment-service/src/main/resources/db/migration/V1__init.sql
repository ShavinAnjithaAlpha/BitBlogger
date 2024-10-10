CREATE SEQUENCE IF NOT EXISTS comments_seq
AS BIGINT
START 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS comments(
    id BIGINT PRIMARY KEY DEFAULT nextval('comments_seq'),
    user_id BIGINT NOT NULL,
    post_id VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    commented_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    parent_id BIGINT NULL,

    FOREIGN KEY(parent_id) REFERENCES comments(id)
);

CREATE SEQUENCE IF NOT EXISTS comment_likes_seq
AS BIGINT
START 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS comment_likes(
    id BIGINT PRIMARY KEY DEFAULT nextval('comment_likes_seq'),
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    liked_at TIMESTAMP NOT NULL,

    FOREIGN KEY(comment_id) REFERENCES comments(id)
);

CREATE SEQUENCE IF NOT EXISTS comment_reports_seq
AS BIGINT
START 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS comment_reports(
    id BIGINT PRIMARY KEY DEFAULT nextval('comment_reports_seq'),
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reported_at TIMESTAMP NOT NULL,
    report_reason VARCHAR(250) NOT NULL,
    other_info TEXT NULL,

    FOREIGN KEY(comment_id) REFERENCES comments(id)
);