CREATE TABLE IF NOT EXISTS tag
(
    tag_id       BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    content      VARCHAR(50),
    link         VARCHAR(255),
    tag_entry_id BIGINT UNSIGNED default null,
    PRIMARY KEY (tag_id)
);
