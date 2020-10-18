CREATE TABLE IF NOT EXISTS entry
(
    entry_id     BIGINT UNSIGNED UNIQUE AUTO_INCREMENT     NOT NULL,
    content      VARCHAR(160)                              NOT NULL,
    created_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status       ENUM ('ORIGINAL', 'EDITED')               NOT NULL,
    type         ENUM ('ENTRY', 'COMMENT', 'SHARED_ENTRY') NOT NULL,
    acount_id    BIGINT UNSIGNED                           NOT NULL,
    PRIMARY KEY (entry_id),
    CONSTRAINT FOREIGN KEY (entry_id) REFERENCES account (account_id)
)
