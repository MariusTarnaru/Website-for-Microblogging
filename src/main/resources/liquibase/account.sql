create table if not exists account
(
    account_id     bigint unsigned unique not null auto_increment,
    login_email    varchar(255)           not null,
    password       varchar(255)           not null,
    account_name   varchar(20) unique     not null,
    display_name   varchar(255)           not null,
    created_data   timestamp default current_timestamp,
    account_status enum ('ACTIVE', 'INACTIVE', 'BLOCKED'),
    avatar_id      bigint unsigned        not null,
    account_type   enum ('PUBLIC', 'PRIVATE'),
    primary key (account_id)
);
