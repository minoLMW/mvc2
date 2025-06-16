USE java;

-- 1) 의존성 순서로 기존 테이블 삭제
DROP TABLE IF EXISTS board_comment;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS kakao_user;
DROP TABLE IF EXISTS member;

-- 2) 회원 테이블
CREATE TABLE member (
                        idx       INT            AUTO_INCREMENT PRIMARY KEY,
                        userid    VARCHAR(20)    UNIQUE NOT NULL,
                        userpw    VARCHAR(20)    NOT NULL,
                        name      VARCHAR(20)    NOT NULL,
                        hp        VARCHAR(20)    NOT NULL,
                        email     VARCHAR(50)    NOT NULL,
                        gender    VARCHAR(10)    NOT NULL,
                        ssn1      CHAR(6)        NOT NULL,
                        ssn2      CHAR(7)        NOT NULL,
                        zipcode   CHAR(5),
                        address1  VARCHAR(100),
                        address2  VARCHAR(100),
                        address3  VARCHAR(100),
                        regdate   DATETIME       DEFAULT NOW(),
                        point     INT            DEFAULT 1000
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- 3) 게시판 테이블 (userid UNIQUE 제거)
CREATE TABLE board (
                       board_idx   INT            AUTO_INCREMENT PRIMARY KEY,
                       userid      VARCHAR(20)    NOT NULL,
                       nickname    VARCHAR(50)    NOT NULL,
                       title       VARCHAR(50)    NOT NULL,
                       content     TEXT           NOT NULL,
                       regdate     DATETIME       DEFAULT NOW(),
                       view_count  INT            DEFAULT 0,
                       FOREIGN KEY (userid) REFERENCES member(userid) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- NON-UNIQUE 인덱스로 대체
CREATE INDEX idx_board_userid ON board(userid);

-- 4) 파일 첨부 테이블
CREATE TABLE file (
                      file_idx   INT            AUTO_INCREMENT PRIMARY KEY,
                      board_idx  INT            NOT NULL,
                      _filename  VARCHAR(100)   NOT NULL,
                      _file      BLOB,
                      FOREIGN KEY (board_idx) REFERENCES board(board_idx) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- 5) 카카오 사용자 테이블
CREATE TABLE kakao_user (
                            kakao_id   VARCHAR(50)    PRIMARY KEY,
                            email      VARCHAR(100),
                            nickname   VARCHAR(100)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- 6) 댓글 테이블
CREATE TABLE board_comment (
                               comment_idx  INT            AUTO_INCREMENT PRIMARY KEY,
                               board_idx    INT            NOT NULL,
                               user_id      VARCHAR(50)    NOT NULL,
                               content      VARCHAR(5500)  NOT NULL,
                               created_at   TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (board_idx) REFERENCES board(board_idx) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

SHOW TABLES;

select * from board;
