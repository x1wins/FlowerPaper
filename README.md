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

bbs can't used