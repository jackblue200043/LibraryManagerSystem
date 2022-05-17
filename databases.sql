create table books
(
    bookId    varchar(20)       not null
        primary key,
    bookName  varchar(100)      not null,
    type      varchar(50)       not null,
    publisher varchar(50)       not null,
    author    varchar(50)       not null,
    place     varchar(20)       not null,
    time      int               not null,
    status    tinyint default 0 not null
);

create table users
(
    userName    varchar(20)                 not null
        primary key,
    nikName     varchar(20)                 not null,
    passWord    varchar(20)                 not null,
    sex         varchar(2)                  null,
    address     varchar(40)                 null,
    phoneNumber varchar(11)                 null,
    status      varchar(8) default 'normal' null
);

create table borrow
(
    num      int auto_increment
        primary key,
    userName varchar(20) not null,
    bookId   varchar(20) not null,
    start    date        not null,
    timeLeft int         not null,
    flag     tinyint(1)  null,
    constraint borrow_books_bookId_fk
        foreign key (bookId) references library.books (bookId),
    constraint borrow_users_userName_fk
        foreign key (userName) references library.users (userName)
);

create table file
(
    fileId    int auto_increment
        primary key,
    userName  varchar(20)  not null,
    fileName  varchar(40)  not null,
    fileType  varchar(20)  null,
    size      varchar(20)  null,
    directory varchar(500) not null,
    constraint file_users_userName_fk
        foreign key (userName) references library.users (userName)
);

create table messageBoard
(
    num      int auto_increment
        primary key,
    bookId   varchar(20)   not null,
    userName varchar(20)   not null,
    time     date          not null,
    message  varchar(1000) not null,
    constraint messageBoard_books_bookId_fk
        foreign key (bookId) references library.books (bookId),
    constraint messateBoard_users_userName_fk
        foreign key (userName) references library.users (userName)
            on update cascade on delete cascade
);

INSERT INTO books (bookId, bookName, type, publisher, author, place, time, status) VALUES ('B1234444', 'Java核心技术', '编程', '机械工业出版社', '霍斯特曼', 'A01E1', 30, 0);

INSERT INTO users (userName, nikName, passWord, sex, address, phoneNumber, status) VALUES ('jackblue', 'haha12', '123456', '男', 'null', '18070032532', 'normal');
INSERT INTO users (userName, nikName, passWord, sex, address, phoneNumber, status) VALUES ('root', 'rootUser', 'admin', null, null, null, 'root');

INSERT INTO borrow (num, userName, bookId, start, timeLeft, flag) VALUES (19, 'jackblue', 'B1234444', '2022-05-17', 30, 0);

INSERT INTO messageBoard (num, bookId, userName, time, message) VALUES (41, 'B1234444', 'root', '2022-05-17', '不错');
INSERT INTO messageBoard (num, bookId, userName, time, message) VALUES (42, 'B1234444', 'root', '2022-05-17', '哈哈');
INSERT INTO messageBoard (num, bookId, userName, time, message) VALUES (43, 'B1234444', 'jackblue', '2022-05-17', '你好呀');



