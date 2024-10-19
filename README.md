
## 테이블 TODO: 4번
```sql
create database blogdb;
use blogdb;

CREATE TABLE user_tb (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE board_tb (
    id INT AUTO_INCREMENT,
    user_id INT,
    created_at TIMESTAMP(6),
    content MEDIUMTEXT NOT NULL,
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_tb(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE reply_tb (
    id INT AUTO_INCREMENT,
    board_id INT,
    user_id INT,
    created_at TIMESTAMP(6),
    comment VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES board_tb(id),
    FOREIGN KEY (user_id) REFERENCES user_tb(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```