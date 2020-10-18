CREATE TABLE IF NOT EXISTS comment
(
    comment_id   BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    content      VARCHAR(160)                          NOT NULL,
    created_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    entry_id     BIGINT UNSIGNED                       NOT NULL,
    account_id   BIGINT UNSIGNED                       NOT NULL,
    PRIMARY KEY (comment_id),
    CONSTRAINT FOREIGN KEY (entry_id) REFERENCES entry (entry_id),
    CONSTRAINT FOREIGN KEY (account_id) REFERENCES account (account_id)
)
