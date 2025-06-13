use java;

create table member (
	idx int auto_increment primary key,
    userid varchar(20) unique not null,
    userpw varchar(20) not null,
    name varchar(20) not null,
    hp varchar(20) not null,
    email varchar(50) not null,
    gender varchar(10) not null,
    ssn1 char(6) not null,
    ssn2 char(7) not null,
    zipcode char(5),
    address1 varchar(100),
    address2 varchar(100),
    address3 varchar(100),
    regdate datetime default now(),
    point int default 1000
);

desc member;

insert into member (userid, userpw, name, hp, email, gender, ssn1, ssn2, zipcode, address1, address2, address3) values
('apple', '1111', '김사과', '010-1111-1111', 'apple@apple.com', '여자', '001011', '4068518', '06774', '서울 서초구 강남대로 27', 'AT센터', '(양재동)');
insert into member (userid, userpw, name, hp, email, gender, ssn1, ssn2, zipcode, address1, address2, address3) values
('banana', '2222', '반하나', '010-2222-2222', 'banana@banana.com', '여자', '950101', '4068518', '12345', '서울 서초구 방배동 27', '111', '(방배동)');
insert into member (userid, userpw, name, hp, email, gender, ssn1, ssn2, zipcode, address1, address2, address3) values
('orange', '3333', '오렌지', '010-3333-3333', 'orange@orange.com', '남자', '910202', '4068518', '06774', '서울 강남구 역삼동 27', '2222', '(역삼동)');

select * from member;

-- unique  
create table board (
	board_idx int primary key auto_increment,
    userid varchar(20) unique not null,
    nickname varchar(50) not null,
    title varchar(50) not null,
    content text not null,
    regdate datetime default now(),
    view_count int default 0,
    foreign key (userid) references member(userid) on delete cascade
);

insert into board (userid, nickname,title,content) values ("apple","김사과","테스트","힘들다");
INSERT INTO board (userid, nickname, title, content, view_count)
VALUES ('apple', '김사과', '테스트', '힘들다', 1)
ON DUPLICATE KEY UPDATE 
    nickname = VALUES(nickname),
    title = VALUES(title),
    content = VALUES(content),
    view_count = view_count + 1;
select * from board;


update board set view_count = view_count + 1 where userid="apple";
-- 게시물 content는 차후에 생각하기

create table file (
	file_idx int primary key auto_increment,
    _filename varchar(100) not null,
    _file blob ,
    foreign key (file_idx) references board(board_idx) on delete cascade
);

-- alter table board convert to character set uft8mb4 collate uft8mb4_unicode_ci;


use java;

drop table board;
drop table member;
drop table file;


