CREATE TABLE IF NOT EXISTS tag
(
    tag_id       BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    content      VARCHAR(50),
    link         VARCHAR(255),
    PRIMARY KEY (tag_id)
);
