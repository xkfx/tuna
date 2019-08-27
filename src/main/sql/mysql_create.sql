CREATE DATABASE tuna;
USE tuna;

CREATE TABLE target (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
	comment VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- SET NAMES gbk; 仅WINDOWS控制台需要该指令
INSERT INTO target(name, comment)
VALUES
  ("this is a test book", "前置条件: cet 4
  测试换行");

CREATE TABLE card (
  id BIGINT NOT NULL AUTO_INCREMENT,
  front VARCHAR(255) NOT NULL,
	back VARCHAR(255) NOT NULL,
	target_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(target_id) REFERENCES target(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO card(front, back, target_id)
VALUES
  ("hello", "哈喽，你好的意思。", 1000),
  ("world", "世界", 1000);


-- 2019年5月19日
ALTER TABLE card MODIFY back VARCHAR(255) DEFAULT '没有内容';
-- 2019年6月26日
ALTER TABLE target MODIFY comment VARCHAR(255) NULL;
-- 2019年8月27日
ALTER TABLE target MODIFY name VARCHAR(255) NOT NULL;