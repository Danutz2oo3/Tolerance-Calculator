create table grade_of_tolerance_entity
(
    id   bigint auto_increment
        primary key,
    max  float        null,
    min  float        null,
    name varchar(255) null
);

create table nominal_size_entity
(
    id          bigint auto_increment
        primary key,
    lower_bound float null,
    upper_bound float null
);

create table standard_allowance_entity
(
    id   bigint auto_increment
        primary key,
    max  float        null,
    min  float        null,
    name varchar(255) null,
    type tinyint      null,
    check (`type` between 0 and 1)
);

create table tolerance_class_entity
(
    max    float  null,
    min    float  null,
    sa_id  bigint not null,
    got_id bigint not null,
    primary key (got_id, sa_id),
    constraint FK8s4byvh0o1jmauy1b9e6x2tvy
        foreign key (got_id) references grade_of_tolerance_entity (id),
    constraint FKrmbpos5p88tgv3gr5gix4vsyr
        foreign key (sa_id) references standard_allowance_entity (id)
);

create table tolerance_entity
(
    id                     bigint auto_increment
        primary key,
    lower_limit_deviation  float  null,
    upper_limit_deviation  float  null,
    nominal_size_entity_id bigint null,
    tc_got_id              bigint null,
    tc_sa_id               bigint null,
    constraint FKf309w9vnn269wuomuir0qegb
        foreign key (tc_got_id, tc_sa_id) references tolerance_class_entity (got_id, sa_id),
    constraint FKokx0peclsjx3cslo7ah3pyihr
        foreign key (nominal_size_entity_id) references nominal_size_entity (id)
);


