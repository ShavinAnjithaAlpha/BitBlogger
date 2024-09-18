CREATE TABLE IF NOT EXISTS topics(
    id INT UNIQUE AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    parent_topic INT NULL,

    FOREIGN KEY(parent_topic) REFERENCES topics(id)
);

CREATE TABLE IF NOT EXISTS topic_history(
    id INT UNIQUE AUTO_INCREMENT NOT NULL PRIMARY KEY,
    topic_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    parent_topic INT NULL,
    changed_by BIGINT NOT NULL,
    changed_at DATETIME NOT NULL,
    action VARCHAR(100) NOT NULL,

    FOREIGN KEY(topic_id) REFERENCES topics(id)
);