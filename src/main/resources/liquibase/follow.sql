create table if not exists follow (
                        followed_id bigint not null,
                        following_id bigint not null,
                        primary key (followed_id, following_id)
)engine=MyISAM;
