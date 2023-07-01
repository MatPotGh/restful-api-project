DROP TABLE IF EXISTS user_info;

create table user_info
(
    login           varchar(255) not null,
    request_count 	bigint       not null default 0,
    PRIMARY KEY (login)
)
