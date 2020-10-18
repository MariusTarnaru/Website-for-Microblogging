CREATE TABLE IF NOT EXISTS likes
(
    like_id      BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    count       BIGINT UNSIGNED DEFAULT 0,
    created_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    entry_id     BIGINT UNSIGNED                       NOT NULL,
    account_id   BIGINT UNSIGNED                       NOT NULL,
    PRIMARY KEY (like_id),
    CONSTRAINT FOREIGN KEY (entry_id) REFERENCES entry (entry_id),
    CONSTRAINT FOREIGN KEY (account_id) REFERENCES account (account_id)
);
