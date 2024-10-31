grant all privileges on RefurbMarket.* to refurb@'%';
drop table if exists member CASCADE;
create table User
(
    id bigint AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id)
) comment = "회원";

insert into User (name, email, password) values ("김테스트", "test@email.com", "testtest12!");
