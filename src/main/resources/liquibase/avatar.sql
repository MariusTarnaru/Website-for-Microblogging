create table if not exists avatar
(
    avatar_id  bigint unsigned unique not null auto_increment,
    path       varchar(255),
    primary key (avatar_id)
);



