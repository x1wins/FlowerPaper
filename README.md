FlowerPaper
===========

welcome to FlowerPaper

this is bbs(bulletin board system) open project developed by spring mvc and hibernate

http://localhost:8080/FlowerPaper

1 first step
============

start WAS with FlowerPaper web application

2 second step
============

you should change db setting yours id, password, db url
db setting value(id,password,url) in net.changwoo.x1wins.proeprties.jdbc.properties

3 third step
============

you must run follow this query

insert into config (bbsname,userid, listTypeNum) values('notice','admin',1);

insert into config (bbsname,userid, listTypeNum) values('free','admin',1);

insert into config (bbsname,userid, listTypeNum) values('portfolio','admin',2);

this follow is bbs list view type
- 1 listTypeNum 1 text view list
- 2 listTypeNum 2 thumb picture list


if you didn't run this query

you can't use bbs


URL
============

this following is web, rest api url

1 data(json or xml, if you want xml. you should change json to xml in url word)
- 1 bbs list
http://localhost:8080/FlowerPaper/bbs/data/1/list/1.json
- 2 bbs detail
http://localhost:8080/FlowerPaper/bbs/data/detail/15.json
- 3 bbs reply list
http://localhost:8080/FlowerPaper/bbs/data/1/reply/list.json
- 4 member list
http://localhost:8080/FlowerPaper/user/list.json
- 5 member detail
http://localhost:8080/FlowerPaper/user/1/detail.json


2 web
- bbs list
http://localhost:8080/FlowerPaper/bbs/1/list/1
- bbs detail
http://localhost:8080/FlowerPaper/bbs/1/detail/15
