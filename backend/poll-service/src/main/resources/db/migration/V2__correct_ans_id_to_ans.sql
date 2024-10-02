ALTER TABLE poll_ans ADD COLUMN answer_status VARCHAR(24) DEFAULT 'NONE';

ALTER TABLE polls DROP COLUMN correct_answer_id;
