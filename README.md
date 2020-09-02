# Spring Ignite Example

Ignite 조회 후 값이 없으면 DB 조회 후 캐시 등록

## DBMS - Mariadb
```
create table user (
    username varchar(100) primary key,
    password varchar(100)
);

insert into user values ('admin', 'dkdk');
insert into user values ('ddakker', 'dkdk');
```

### 조회
* http://localhost:8080/

## 조건 조회
* http://localhost:8080/ddakker

## 네이티브 쿼리 조회
* http://localhost:8080/query/ddakker

ignite/