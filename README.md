FlowerPaper
===========

bbs spring mvc hibernate

http://localhost:8080/FlowerPaper
db setting value in net.changwoo.x1wins.proeprties.jdbc.properties

when WAS start
you must run follow query(bbs config add query)
essential query

insert into config (bbsname,userid, listTypeNum) values('notice','admin',1);

insert into config (bbsname,userid, listTypeNum) values('free','admin',1);

insert into config (bbsname,userid, listTypeNum) values('portfolio','admin',2);


if you didn't run this query
bbs can't used