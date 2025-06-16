-- 기존 테이블 제거 (의존성 순서)
drop table if exists board_comment;
drop table if exists file;
drop table if exists board;
drop table if exists kakao_user;
drop table if exists member;

-- 회원 테이블 (일반 로그인)
create table member (
    idx       int auto_increment primary key,
    userid    varchar(20) unique not null,
    userpw    varchar(20) not null,
    name      varchar(20) not null,
    hp        varchar(20) not null,
    email     varchar(50) not null,
    gender    varchar(10) not null,
    ssn1      char(6) not null,
    ssn2      char(7) not null,
    zipcode   char(5),
    address1  varchar(100),
    address2  varchar(100),
    address3  varchar(100),
    regdate   datetime default now(),
    point     int default 1000
) engine=innodb
  default charset=utf8mb4
  collate=utf8mb4_unicode_ci;

-- 카카오 로그인 사용자 테이블
create table kakao_user (
    kakao_id   varchar(50) primary key,
    email      varchar(100),
    nickname   varchar(100)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 구글 로그인 사용자
create table google_user (
    email      varchar(100) primary key,
    name       varchar(100),
    picture    varchar(255)
) engine=innodb
  default charset=utf8mb4
  collate=utf8mb4_unicode_ci;

-- 게시판 테이블 (userid 외래키 제거, 대신 nullable + soft reference)
create table board (
    board_idx   int auto_increment primary key,
    userid      varchar(50) not null,
    nickname    varchar(100) not null,
    title       varchar(100) not null,
    content     text not null,
    regdate     datetime default now(),
    view_count  int default 0
) engine=innodb
  default charset=utf8mb4
  collate=utf8mb4_unicode_ci;

-- 사용자 인덱스 (foreign key 제거 대신 soft join 대비)
create index idx_board_userid on board(userid);

-- 첨부파일 테이블
create table file (
    file_idx   int auto_increment primary key,
    board_idx  int not null,
    _filename  varchar(100) not null,
    _file      blob,
    foreign key (board_idx) references board(board_idx) on delete cascade
) engine=innodb
  default charset=utf8mb4
  collate=utf8mb4_unicode_ci;

-- 댓글 테이블
create table board_comment (
    comment_idx  int auto_increment primary key,
    board_idx    int not null,
    user_id      varchar(50) not null,
    content      varchar(5500) not null,
    created_at   timestamp default current_timestamp,
    foreign key (board_idx) references board(board_idx) on delete cascade
) engine=innodb
  default charset=utf8mb4
  collate=utf8mb4_unicode_ci;
  
-- 확인
show tables;
select * from board;