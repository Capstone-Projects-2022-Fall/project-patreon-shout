DROP TABLE IF EXISTS posts;
create table posts (
    publishdate VARCHAR(255),
    title VARCHAR(255),
    url VARCHAR(255),
    content MEDIUMTEXT,
    is_public boolean,
    creator_page_url VARCHAR(255),
    post_id INT PRIMARY KEY AUTO_INCREMENT
);

