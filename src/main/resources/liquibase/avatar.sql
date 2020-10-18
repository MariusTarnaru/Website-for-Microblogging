create table if not exists avatar
(
    avatar_id  bigint unsigned unique not null auto_increment,
    account_id bigint unsigned        not null,
    path       varchar(255),
    primary key (avatar_id),
    foreign key (account_id) references account (account_id)
);



