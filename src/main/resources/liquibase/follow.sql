CREATE TABLE IF NOT EXISTS follow
(
    following_id           BIGINT UNSIGNED UNIQUE AUTO_INCREMENT NOT NULL,
    account_id_followed BIGINT UNSIGNED default null,
    account_id_follows  BIGINT UNSIGNED default null,
    created_data   timestamp default current_timestamp,
    PRIMARY KEY (following_id),
    CONSTRAINT FOREIGN KEY (account_id_followed) REFERENCES account (account_id),
    CONSTRAINT FOREIGN KEY (account_id_follows) REFERENCES account (account_id)
);
