FlowerPaper
===========

welcome to FlowerPaper

this is bbs(bulletin board system) open project developed by spring mvc and hibernate

http://localhost:8080/FlowerPaper

screen shot
------------

* bbs thumb picture list page
![1 columns](https://github.com/x1wins/FlowerPaper/blob/master/FlowerPaper/src/main/webapp/screen_shot/pic_list.png?raw=true)

* bbs text list page
![1 columns](https://github.com/x1wins/FlowerPaper/blob/master/FlowerPaper/src/main/webapp/screen_shot/text_list.png?raw=true)

* write from page
![1 columns](https://github.com/x1wins/FlowerPaper/blob/master/FlowerPaper/src/main/webapp/screen_shot/write_form.png?raw=true)

* bbs detail page
![1 columns](https://github.com/x1wins/FlowerPaper/blob/master/FlowerPaper/src/main/webapp/screen_shot/detail.png?raw=true)

How to use
------------

###1 first step

start WAS with FlowerPaper web application

###2 second step

you should change db setting yours id, password, db url

**db setting value(id,password,url) in net.changwoo.x1wins.proeprties.jdbc.properties**

###3 third step

you must run follow this query

    insert into config (bbsname,userid, listTypeNum) values('notice','admin',1);
    insert into config (bbsname,userid, listTypeNum) values('free','admin',1);
    insert into config (bbsname,userid, listTypeNum) values('portfolio','admin',2);

this follow is bbs list view type

    listTypeNum 1 - text view list
    listTypeNum 2 - thumb picture list


**if you didn't run this query. you can't use bbs**


URL
------------

this following is web, rest api url

1 data(json or xml, if you want xml. you should change json to xml in url word)
* bbs list
http://localhost:8080/FlowerPaper/bbs/data/1/list/1.json
* bbs detail
http://localhost:8080/FlowerPaper/bbs/data/detail/15.json
* bbs reply list
http://localhost:8080/FlowerPaper/bbs/data/1/reply/list.json
* member list
http://localhost:8080/FlowerPaper/user/list.json
* member detail
http://localhost:8080/FlowerPaper/user/1/detail.json


2 web
* bbs list
http://localhost:8080/FlowerPaper/bbs/1/list/1
* bbs detail
http://localhost:8080/FlowerPaper/bbs/1/detail/15
