DROP TABLE IF EXISTS posts;
create table posts (
    publishdate VARCHAR(255),
    title VARCHAR(255),
    url VARCHAR(255),
    content MEDIUMTEXT,
    isprivate boolean,
    creator VARCHAR(255),
    post_id INT PRIMARY KEY AUTO_INCREMENT
);

