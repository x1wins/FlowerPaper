FlowerPaper
===========

welcome to FlowerPaper

this is bbs(bulletin board system) open project developed by spring mvc with hibernate

http://localhost:8080/app

db setting value in net.changwoo.x1wins.proeprties.jdbc.properties

when WAS start

you have to run follow this query

insert into config (bbsname,userid, listTypeNum) values('notice','admin',1);

insert into config (bbsname,userid, listTypeNum) values('free','admin',1);

insert into config (bbsname,userid, listTypeNum) values('portfolio','admin',2);

listTypeNum is list type 
- 1 :common list
- 2 :picture list


if you didn't run this query

you can't use bbs

this following is web, rest api url

- data(json or xml, if you want xml. you should change json to xml in url word)
1. bbs list
http://localhost:8080/FlowerPaper/bbs/data/1/list/1.json
2. bbs detail
http://localhost:8080/FlowerPaper/bbs/data/detail/15.json
3. bbs reply list
http://localhost:8080/FlowerPaper/bbs/data/1/reply/list.json

- web
1. bbs list
http://localhost:8080/FlowerPaper/bbs/1/list/1
2. bbs detail
http://localhost:8080/FlowerPaper/bbs/1/detail/15
