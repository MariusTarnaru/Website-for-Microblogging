CREATE TABLE IF NOT EXISTS shared_entry
(
    shared_entry_id BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    entry_id        BIGINT UNSIGNED                       NOT NULL,
    quote           VARCHAR(160),
    account_id      BIGINT UNSIGNED                       NOT NULL,
    PRIMARY KEY (shared_entry_id),
    CONSTRAINT FOREIGN KEY (entry_id) REFERENCES entry (entry_id),
    CONSTRAINT FOREIGN KEY (account_id) REFERENCES account (account_id)
);
