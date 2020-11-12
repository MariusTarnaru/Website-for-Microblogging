create table if not exists avatar
(
    avatar_id  bigint unsigned unique not null auto_increment,
    path       varchar(255),
    account_id   BIGINT UNSIGNED                           NOT NULL,
    primary key (avatar_id),
    CONSTRAINT FOREIGN KEY (avatar_id) REFERENCES account (account_id)
);



