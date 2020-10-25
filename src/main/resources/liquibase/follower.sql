CREATE TABLE IF NOT EXISTS follower
(
    following_id        BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    account_id_followed BIGINT UNSIGNED default null,
    created_data        timestamp       default current_timestamp,
    PRIMARY KEY (following_id),
    CONSTRAINT FOREIGN KEY (account_id_followed) REFERENCES account (account_id)
);
