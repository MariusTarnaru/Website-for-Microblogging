CREATE TABLE IF NOT EXISTS tag_entry
(
    tag_entry_id BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    tag_id       BIGINT UNSIGNED                       NOT NULL,
    entry_id     BIGINT UNSIGNED                       NOT NULL,
    PRIMARY KEY (tag_entry_id)
);
