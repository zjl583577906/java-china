# ************************************************************
# Sequel Pro SQL dump
# Version 4529
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.29)
# Database: javachina
# Generation Time: 2016-04-17 06:41:07 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_activecode
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_activecode`;

CREATE TABLE `t_activecode` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `code` varchar(64) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT 'signup:注册 reset:修改密码',
  `is_use` tinyint(2) NOT NULL DEFAULT '0',
  `expires_time` int(10) NOT NULL COMMENT '过期时间',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `t_activecode` WRITE;
/*!40000 ALTER TABLE `t_activecode` DISABLE KEYS */;

INSERT INTO `t_activecode` (`id`, `uid`, `code`, `type`, `is_use`, `expires_time`, `create_time`)
VALUES
	(3,13,'u0ZWmxmZwoIV1EdtjOVxYVSXEFqrBz9f','signup',1,1458577101,1458573501),
	(5,13,'KF8ESHQdXxNDr1s8K4yMtfZzp5zjLeXR','forgot',0,1458660613,1458657013),
	(6,13,'U0dXQqVDMZOjpcRI10bztWJ5OZao1GqL','forgot',0,1458660782,1458657182),
	(7,15,'qujfVnBpxjF5FhsmH58geIeXH4naX3AH','signup',0,1460197154,1460193554),
	(8,16,'w2HOu3fvH9YclLBysdPdYeYkzp66ceq7','signup',0,1460197239,1460193639),
	(9,17,'Md5B7Ob0Gw6ItSsrUZoCijY2I76Hy8Ic','signup',0,1460302310,1460298710),
	(10,6,'Ptm6jISYk2ZMXhJI2Uo1YzFwxhkaircl','signup',1,1460642861,1460639261),
	(11,7,'iVCQcRrto0az2n5YaNuPMeY9u0QGZc5e','signup',1,1460643088,1460639488),
	(12,8,'oUFnFWyWFp2omAT8iE3XGX25shyjo1BW','signup',1,1460654125,1460650525),
	(13,8,'pmtcdi9x1oeOOOQoL7Wg4jn84yjta4aR','forgot',0,1460654207,1460650607),
	(14,9,'1RVLvV12QTH2tFwqflQDIcuxqbHd5OmX','signup',0,1460685529,1460681929),
	(15,10,'B2OZXMHse5OTv2vBlyHN5lCmdg2qPasu','signup',1,1460685781,1460682181),
	(16,11,'OVS1w1eKpqqP9E02NEHLZJ4tgFCbqn0N','signup',0,1460731778,1460728178),
	(17,12,'eZwBLeiSkTdkhXrUcSFnBwOHYMSUNvrt','signup',1,1460772932,1460769332),
	(18,13,'G8RSUl7okYo0TeILGrAKuwahVkTIxDXu','signup',1,1460790236,1460786636),
	(19,14,'7xqDuewr4Aga2n0hBeyFCaxSB5BSeWlf','signup',1,1460812633,1460809033);

/*!40000 ALTER TABLE `t_activecode` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '评论人uid',
  `to_uid` int(10) NOT NULL COMMENT '被评论人uid',
  `tid` int(10) NOT NULL COMMENT '帖子id',
  `content` text CHARACTER SET utf8mb4 NOT NULL COMMENT '评论内容',
  `create_time` int(10) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;

INSERT INTO `t_comment` (`cid`, `uid`, `to_uid`, `tid`, `content`, `create_time`)
VALUES
	(7,1,3,2,'和你男朋友搞基的就是我',1460639477),
	(8,7,1,1,'沙发~',1460639591),
	(9,6,3,2,'@biezhi 66666666',1460639610),
	(10,1,1,1,'@lichee 板凳 :joy:',1460639800),
	(11,1,5,6,'这种妹子图懂东西多发点 哈哈～',1460639941),
	(12,1,4,4,'不会python 给楼主顶一下。',1460640440),
	(13,1,2,11,'小伙子，对于你这种狂傲的帖子，我想说：我喜欢。',1460640957),
	(14,1,3,8,'楼主写了这么多就是要卖书，我不会告诉你我直接看到链接的。',1460641104),
	(15,1,1,7,'约炮都可以这么深奥。\r\n\r\n![](http://i2.piimg.com/db8eb02559fbd120.jpg)\r\n',1460641347),
	(16,7,1,7,'看来我自尊很高嘛',1460715812),
	(17,7,3,8,'what',1460715834),
	(18,1,11,13,'## 楼主这个逼装的我给100分',1460779726),
	(19,11,11,13,'@biezhi 层主这个回复我给99分。',1460781843),
	(20,13,1,1,'支持',1460786675),
	(21,1,2,9,'好文先收藏了。',1460820128);

/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_favorite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_favorite`;

CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT 'topic:帖子 node:节点',
  `uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `t_favorite` WRITE;
/*!40000 ALTER TABLE `t_favorite` DISABLE KEYS */;

INSERT INTO `t_favorite` (`id`, `type`, `uid`, `event_id`, `create_time`)
VALUES
	(53,'love',1,14,1460829308),
	(39,'following',1,13,1460236055),
	(52,'love',1,9,1460820125),
	(51,'topic',1,9,1460820079),
	(43,'love',1,15,1460389852),
	(40,'topic',1,11,1460236361),
	(41,'love',1,11,1460236378),
	(44,'following',3,1,1460472083),
	(45,'love',3,2,1460472100),
	(46,'topic',3,1,1460472108),
	(47,'love',1,2,1460639450),
	(48,'love',1,1,1460639818),
	(49,'topic',1,1,1460639822),
	(50,'love',1,13,1460779675);

/*!40000 ALTER TABLE `t_favorite` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_link
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table t_node
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_node`;

CREATE TABLE `t_node` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `title` varchar(30) DEFAULT NULL COMMENT '节点名称',
  `description` varchar(255) DEFAULT NULL COMMENT '节点描述',
  `slug` varchar(50) NOT NULL COMMENT '节点英文简写',
  `pic` varchar(100) DEFAULT NULL COMMENT '节点图片',
  `topics` int(10) DEFAULT '0' COMMENT '帖子数',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL,
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_node` WRITE;
/*!40000 ALTER TABLE `t_node` DISABLE KEYS */;

INSERT INTO `t_node` (`nid`, `pid`, `title`, `description`, `slug`, `pic`, `topics`, `create_time`, `update_time`, `is_del`)
VALUES
	(1,0,'分享与探索','分享与探索','','',0,1457933734,1459596711,0),
	(2,0,'Java','Java','','',0,1457933734,1459519169,0),
	(3,0,'开源圈','开源圈','','',0,1459519131,1459519131,0),
	(4,0,'城市','城市','','',0,1459519169,1459519169,0),
	(5,0,'生活','生活','','',0,1459519176,1459519176,0),
	(6,0,'站务管理','站务管理','','',0,1459519199,1459519199,0),
	(7,1,'问与答','问与答','qna','',1,1459519229,1459519229,0),
	(8,1,'分享发现','分享发现','share','',4,1459519247,1459519247,0),
	(9,2,'Spring','Spring','spring','',0,1459519266,1459519266,0),
	(10,2,'Blade','Blade','blade','',0,1459519278,1459617072,0),
	(11,2,'爬虫','爬虫','spiders','',1,1459519288,1459519288,0),
	(12,2,'Java基础','Java基础','javabase','',0,1459524976,1459524976,0),
	(13,2,'JavaWeb','JavaWeb','javaweb','',0,1459524995,1459524995,0),
	(14,3,'开源项目','开源项目','opensource','',1,1459519169,1459519169,0),
	(15,3,'Github','github','github','',0,1459519169,1459519169,0),
	(16,4,'北京','北京','beijing','',0,1459519169,1459519169,0),
	(17,4,'上海','上海','shanghai','',1,1459519169,1459519169,0),
	(18,4,'广州','广州','guangzhou','',0,1459519169,1459519169,0),
	(19,4,'深圳','深圳','shenzhen','',0,1459519169,1459519169,0),
	(20,4,'杭州','杭州','hangzhou','',0,1459519169,1459519169,0),
	(21,5,'二手交易','二手交易','all4all','',0,1459519169,1459519169,0),
	(22,5,'求职招聘','求职招聘','jobs','',1,1459519169,1459519169,0),
	(23,6,'程序发布','程序发布','publish','',0,1459519169,1459519169,0),
	(24,6,'申请节点','申请节点','reqnode','',0,1459519169,1459519169,0),
	(25,0,'编程','编程','',NULL,0,1459519169,1459519169,0),
	(26,25,'python','python','python',NULL,0,1459519169,1459519169,0),
	(27,25,'golang','golang','golang',NULL,0,1459519169,1459519169,0),
	(28,25,'nginx','nginx','nginx',NULL,1,1459519169,1459519169,0),
	(29,0,'Geek','geek','geek',NULL,0,1459519169,1459519169,0),
	(30,29,'树莓派','树莓派','raspberrypi',NULL,0,1459519169,1459519169,0),
	(32,5,'水一波','水一波','water',NULL,3,1459519169,1459519169,0),
	(33,5,'生活方式','生活方式','life',NULL,0,1459519169,1459519169,0),
	(34,5,'游戏','游戏','game',NULL,0,1459519169,1459519169,0),
	(35,1,'GET','get 新技能','get',NULL,1,1459519169,1459519169,0),
	(36,2,'netty','netty','netty',NULL,0,1459519169,1459519169,0),
	(37,2,'消息中间件','','messagequeue',NULL,0,1459519169,1459519169,0),
	(38,2,'NIO','nio','nio',NULL,1,1459519169,1459519169,0);

/*!40000 ALTER TABLE `t_node` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_notice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `to_uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_notice` WRITE;
/*!40000 ALTER TABLE `t_notice` DISABLE KEYS */;

INSERT INTO `t_notice` (`id`, `type`, `uid`, `to_uid`, `event_id`, `is_read`, `create_time`)
VALUES
	(1,'comment',1,3,2,0,1460639477),
	(2,'comment',7,1,1,1,1460639591),
	(3,'comment',6,3,2,0,1460639610),
	(4,'at',6,1,2,1,1460639610),
	(5,'comment',1,5,6,0,1460639941),
	(6,'comment',1,4,4,0,1460640440),
	(7,'comment',1,2,11,0,1460640957),
	(8,'comment',1,3,8,0,1460641104),
	(9,'comment',7,1,7,1,1460715812),
	(10,'comment',7,3,8,0,1460715834),
	(11,'comment',1,11,13,0,1460779726),
	(12,'comment',13,1,1,1,1460786675),
	(13,'comment',1,2,9,0,1460820128);

/*!40000 ALTER TABLE `t_notice` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_openid
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_openid`;

CREATE TABLE `t_openid` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `open_id` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_openid` WRITE;
/*!40000 ALTER TABLE `t_openid` DISABLE KEYS */;

INSERT INTO `t_openid` (`id`, `type`, `open_id`, `uid`, `create_time`)
VALUES
	(1,'github',9302093,14,1460809033);

/*!40000 ALTER TABLE `t_openid` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_settings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_settings`;

CREATE TABLE `t_settings` (
  `skey` varchar(50) NOT NULL,
  `svalue` text,
  PRIMARY KEY (`skey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `t_settings` WRITE;
/*!40000 ALTER TABLE `t_settings` DISABLE KEYS */;

INSERT INTO `t_settings` (`skey`, `svalue`)
VALUES
	('comment_count','15'),
	('site_description','JavaChina是一个使用Blade框架开发的Java简洁论坛程序'),
	('site_keywords','Java社区,Blade框架,程序员论坛,开源程序'),
	('site_title','Java中国'),
	('topic_count','14'),
	('user_count','14');

/*!40000 ALTER TABLE `t_settings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_topic
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `tid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '发布人',
  `nid` int(10) NOT NULL COMMENT '所属节点',
  `title` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COMMENT '帖子内容',
  `views` int(10) DEFAULT '0' COMMENT '浏览量',
  `favorites` int(10) DEFAULT '0' COMMENT '被收藏数',
  `loves` int(10) DEFAULT '0' COMMENT '获得点赞数',
  `comments` int(10) DEFAULT '0' COMMENT '评论数',
  `is_top` tinyint(2) DEFAULT '0' COMMENT '是否置顶',
  `is_essence` tinyint(2) DEFAULT '0' COMMENT '是否精华帖',
  `create_time` int(10) NOT NULL COMMENT '帖子创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:正常 2:删除',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_topic` WRITE;
/*!40000 ALTER TABLE `t_topic` DISABLE KEYS */;

INSERT INTO `t_topic` (`tid`, `uid`, `nid`, `title`, `content`, `views`, `favorites`, `loves`, `comments`, `is_top`, `is_essence`, `create_time`, `update_time`, `status`)
VALUES
	(1,1,14,'简洁美观的Java论坛 - Java中国发布了','\r\nJava 中国是使用 [Blade](https://github.com/biezhi/blade) 搭建的一款轻量级论坛程序，目前发布第一个版本。开源地址是 [https://github.com/junicorn/java-china](https://github.com/junicorn/java-china)，请不要吝啬你的 `star` :joy:。\r\n\r\n## 特性\r\n\r\n- 免费开源，代码量少，易于维护\r\n- 外观简洁美观\r\n- 支持Emoji表情\r\n- 支持@用户\r\n- 支持Markdown语法\r\n- 更多特性，等你反馈\r\n\r\n## 如何使用？\r\n\r\n1. 下载源代码导入到你的IDE中\r\n2. 创建数据库并导入 `javachina.sql` 脚本\r\n3. 启动你的web容器即可访问\r\n\r\n## 相关配置\r\n\r\n这里有 3 个配置文件，除了 `blade.properties` 都可以不用修改。\r\n\r\n- app.version:当前应用版本号，用于强制刷新浏览器缓存\r\n- app.site_url:站点地址\r\n- app.db_cahce:是否启用数据库缓存，建议线上环境开启\r\n- app.aes_salt:AES加密盐值，用于Cookie等加密使用\r\n- qiniu:七牛CDN相关配置\r\n- github:github开发者密钥\r\n- email:邮箱配置，用户注册等邮件发送\r\n- db:数据库连接配置\r\n\r\n## 联系\r\n\r\n+ 如果你有好的想法或者愿意参与开源贡献，可以联系我的邮箱 biezhi.me#gmail\r\n+ 我们的QQ群：1013565 欢迎基佬加入 :smile: ',99,2,1,3,0,1,1460469846,1460786675,1),
	(2,3,7,'怎样提高程序员男朋友的情商？','因为男票情商不高，所以屡次想要通过调情拉高他的情商，结果却是屡屡被他蠢哭了（有时又觉得傻傻分不清楚蛮可爱）…虽然事后他也会努力地哄我并承认自己很蠢的事实→_→但一直不见情商进步啊…于是乎想让他找宿友讨教撒，结果他说我不搞基啊……！妈蛋老子让你搞基了吗让你跟宿友学习学习！（再次蠢哭）……哎我是一直不信IT男就会情商低的，毕竟见过不少IT情圣，看在他也说会努力的份上，我还不想放弃治疗他……所以恳请大侠们帮他一起提高情商喲⊙﹏⊙\r\n我是认真的！！！',34,0,2,2,0,0,1460470218,1460639610,1),
	(3,2,8,'分享一个网站，可以推送高清晰度视频到百度云盘','http://lightingnine.com \r\n\r\n想试试自己能有耐性做一个事情做到多久和多好，于是就有了这个网站。可以推送油管最高清晰度的视频到百度云盘…… 目前刚开始做，不足很多，大家轻拍 \r\n\r\n欢迎大家关注微博和多提意见，哈哈',10,0,0,0,0,0,1460470563,1460470563,1),
	(4,4,22,'Python 后端开发工程师——From Amber（附最近招聘的感触）','[ Python 后端开发工程师] \r\n1 、 2 年以上工作经验，最好开发过日活过百万的线上系统；； \r\n2 、熟悉 Python ，有良好的编程基础和编程习惯； \r\n3 、熟悉常用的分布式系统，包括数据库、缓存、消息队列等； \r\n4 、有丰富的系统架构和 API 设计经验。 \r\n\r\n[推荐工作流程] ： 1.加我的微信，初步认识对方。 \r\n2.交换电话沟通，这样比较高效。 \r\n3.发送您的简历，约见面，我会和每个将要推荐的候选人见一次面，深入了解一些工作机会。 \r\n4.发简历，约面试，这是双方承诺，大家提前会沟通好。 \r\n5.面试反馈，结果就看您自己的选择了。 \r\n\r\n[转介绍] ：如果您身边的朋友很适合我发布的职位，欢迎推荐。谢谢每个信任我的朋友，我会真诚负责对待每个朋友。 \r\n\r\n[最近的感触] ：很多高级开发程序员工作很难找，薪资已经遇到天花板。有经验，但能力一般的程序员很难找工作，市场越来越不认可。戒骄戒躁，修炼自己。 \r\n\r\n我是专注 Python 领域的猎头 Amber ，最近又有朋友加我微信了，投递简历了，和大家一起成长！ \r\n见了很多工程师，看重大家的职业发展道路，学习了很多。希望和大家一起努力成长。',13,0,0,1,0,0,1460470806,1460640440,1),
	(5,5,32,'test from sina weibo','![](http://ww4.sinaimg.cn/mw600/b871e8fdgw1ep6lrs4jhmj209d0g7414.jpg)\r\n\r\n![](http://ww3.sinaimg.cn/mw600/b871e8fdgw1ep6lrtxy51j20dw0a60tk.jpg)\r\n\r\n![](http://ww2.sinaimg.cn/thumbnail/a00dfa2agw1ep6fj6k10fg20bo0821kx.gif)\r\n\r\n![](http://ww3.sinaimg.cn/thumbnail/a00dfa2ajw1ep5rwq6p72g205k05kdjm.gif)\r\n\r\n![](http://ww3.sinaimg.cn/thumbnail/6b8b2c6egw1ep6fl7zzkdg20b40b4u10.gif)',6,0,0,0,0,0,1460471180,1460471180,1),
	(6,5,11,'手把手教你写煎蛋妹子图爬虫。。。','缘起\r\n====\r\n\r\n爬虫从妹子图练起最好了，煎蛋防护系统比较弱，你要一开始上手大众点评，豆瓣，那好了，先上淘宝买代理吧。。。。\r\n\r\n而且这个例子好好啊，可以直接从正则表达式匹配技术讲起，在用bs，再到pyquery。。。。\r\n\r\n顺带讲讲fiddler，firebug之类的。。。\r\n\r\n===\r\n\r\n第一次录screencast，真是巨麻烦。而且质量还不高。。演练没到位。。。\r\n\r\ndestroy all software能在15分钟左右制作出质量这么高的视频真心不容易，自己做一次就知道了。\r\n\r\n视频链接： http://pan.baidu.com/s/1i3mXwBN\r\n\r\n效果链接： [tocpi/5](http://java-china.org/tocpi/5)\r\n这是无聊图的，妹子图现在都很黄很暴力了，不和谐~~',21,0,0,1,0,0,1460471443,1460639941,1),
	(7,1,35,'Sexual hookup culture：当约炮已成为一种文化现象','作者：熊希灵\r\n链接：http://zhuanlan.zhihu.com/p/20501450\r\n来源：知乎\r\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\r\n\r\n前两天有朋友分享了一个探究约炮原因的问题，900多个答案粗略扫了一眼，基本都是答主们自我体验感受的分享。感叹答主们经历丰富的同时，我也在想“为什么约炮这一现象在现代社会似乎日渐兴起并已渐渐成为一种独立的文化现象，这种现象兴起的原因是什么？”\r\n\r\n这么性感的话题怎么可能逃过学界的争论，12年有篇发在Review of General Psychology上的文章就针对此前对约炮行为的相关研究作了综述，应该是迄今对约炮文化阐释较为全面的一篇文章：Sexual hook-up culture: A review\r\n这里仅挑几个重点略作介绍，建议感兴趣的同学翻看原文。\r\n\r\n1.对“约炮”的操作性定义。\r\n有不同学者对约炮（hookup）作过不同的操作性定义，作者将其归纳为一种随意、没有承诺的性关系“casual sex” or “uncommitted sexual encounter.”；就约炮具体内容而言，主要得与本身就是朋友基础上发展出性关系但是又没有伴侣承诺的Friends With Benefits区分开，这种friends with benefits在性关系之外卷含了太多的关于友谊、信任和情感抚慰等因素：these situations represent a greater entanglement of friendship, trust, and emotional comfort, FWBs are distinct from notions of hooking up in some aspects\r\n\r\n除掉friends with benefits，剩下明确可定义为约炮的行为俗称有：“no strings attached” (NSA) sex, “casual encounters,” and “one-night stands.”。\r\n需要另外指出的是，作者对约炮的定义包含但不止于插入式的性爱关系，接吻、口交等边缘性行为也是算在内的：The term hookup focuses on the uncommitted nature of a sexual encounter rather than focus on what behaviors “count.”\r\n\r\n2.“约炮”在当代社会有多频繁？\r\n仅就北美的不同调查数据总结来看，大约60%-80%的大学生有过至少一次的“约炮”经历。\r\n\r\n3.“约炮”发生的地点。\r\n不同的调查取样结果有一定偏差，不过综合起来看，北美地区的约炮行为绝大部分发生于聚会和单身酒吧（针对学生群体的调查则有超过50%发生于宿舍）。\r\n\r\n4.解释“约炮”原因的理论框架。\r\n1）进化心理学视角：\r\n提及这一理论当然绕不开大名鼎鼎的Buss, D. M，其基本理论即为男性为了更高效率的繁殖，会更倾向于约炮这种短期、丰富伴侣、低资源付出的性策略；女性则为了从男性得到更多资源而倾向于维持长期关系而获得更多投资的性策略——但女性也不是完全拒绝短期关系，当某些男性表现出特别良好的基因特征，女性也会倾向于为了下一代的质量而寻求短期关系。\r\n如此基本上是自圆其说了为什么男女都有约炮行为，但男性明显更饥渴，女性更挑剔的现象。而有元分析也表明男女在绝大多数性行为上的表现都是相似的，唯独“约炮”这一点上差异显著。\r\n但是03年有跨文化的调查发现，虽然男性的约炮意愿（79%）确实明显高于女性（64%），但两性均高于50%的约炮意愿也显著地太高了些——进化心理学并不能很好解释为何近三分之二的女性有约炮意愿。当然进化心理学派还是坚持认为，这是现代避孕手段的进步让女性更有机会作为一个“选择者（choosier）”去通过约炮来获得更好的基因选择。\r\n2）性脚本理论（Sexual script theory）：\r\n这一理论简而言之就是，人们的性行为是由当下社会的习俗规范形成的“脚本”指导下完成的。这一理论与进化心理学的不同之处在于，其更强调社会学习的作用，而不仅是繁衍本能。\r\n在女性主义兴起之前，社会脚本一直强调男性的中心地位，性于是一种权力的象征；而女性则被物化，在性上是消极的守卫者（gatekeeper）；而到了近现代，影视作品里也越来越多出现女性对性的自由追求，对身体的自我掌控，Friends with Benefits and No Strings Attached这样的代表性作品则更表现了当代性解放对约炮行为的示范性作用。\r\n\r\n两种理论综合来看，前者更着重解释“约炮”行为范式产生的根本原因，后者则注重解释这一行为在当代社会兴起的文化层面。遗憾的是这两种理论仅仅是一个框架，由于针对“约炮”行为研究方法的局限（主要还是质性研究方法，问卷、深度访谈等），两种理论要获得实证支持还有很长的路要走。\r\n值得注意的是，除了人的自身动机，一些外部因素同样对约炮行为有重要影响——有针对北美大学生的调查表明，33%的约炮行为“不是故意的（unintentional）”，虽然这个词听着很别扭，不过不得不承认酒精和大麻对unintentional的功不可没。\r\n\r\n5.“约炮”对人们主观幸福感（well-being）的影响\r\n1）约炮场景往往包含着压力和表现焦虑。女性在这些负性情绪方面的表现比男性更显著。有意思的是，与我们平时所认为的“风流倜傥”形象不太一样，有约炮行为的受调查者在自尊水平上显著低于没有约炮行为的受调查者，无论男女——不过这一现象的因果关系尚不明晰，不知道到底是因为低自尊才去约炮找自信，还是因为约炮行为导致了自尊降低。\r\n2）约炮后的悔恨感（hookup regret）是一个独特的负面效应。这种悔恨主要包含难堪（embarrassed）、失去尊重（loss of respect）、与稳定伴侣的障碍（difficulties with a steady partner ）。加拿大一项针对大学生调查表明78%的女性和72%的男性在约炮后都有悔恨感。\r\n3）尽管有前两点的负面效应，并且迄今的大多数调查都更支持约炮带来的负面影响更多，但有调查表明65%的约炮者在约炮中感到良好甚至兴奋。作者也认为目前还无法判断约炮到底是会带来更多积极效应还是消极效应：It is still unclear the degree to which hookups may result in positive reactions, and whether young men and young women are sexually satisfied in these encounters.\r\n\r\n\r\n要点大致如上，其实较之作者整理的两种理论框架，我认为作者前面introduction里提到的一个解释虽然较表层但其实更能合理地解释约炮行为在现代社会的兴起：\r\n\r\n由于受教育年限的提升，现代人真正进入经济独立的年龄也被显著推迟，建立家庭、生育的年龄都远远大于性成熟的年龄——尤其随着营养的丰富，现代人性成熟的年龄反而是提前的。现代社会的年轻人还没有做好组建家庭的准备，却已经开始受到生殖冲动给生理心理带来的双重影响，于是约炮成为了一种缓解性压力却又不必担忧尚未做好组建家庭准备的有效手段。\r\n\r\nReference：\r\nGarcia, J. R., Reiber, C., Massey, S. G., & Merriwether, A. M. (2012). Sexual hookup culture: A review. Review of General Psychology, 16(2), 161.\r\n文中提到的所有研究文献在这篇文章的引用文献里都能找到，就不再一一列举引用了。',45,0,0,2,0,0,1460472036,1460715812,1),
	(8,3,8,'咦？这些也会影响我们的脸么？','下面这样的情况在我们身上或多或少地发生过（肯定发生过啦，不要装了）：虽然有个人长相、气质不如你（对，就是你隔壁同事，或者宿舍那谁），但是大家竟然会对它评价高而不是对你！正如之前提到的，影响长相的因素并不是很多，为什么各项都「占优」了，却还会在感情上「落后」呢？为什么全世界都不能识别你的美丽（估计这么想的话，你也不美，真的）呢？\r\n\r\n比如这一组图，是一个叫做 Sparkles and Wine 的视频里的截图。仔细看的话，就能发现虽然是同一张脸，但在不同光照下会给人不同的感受。\r\n\r\n![](http://i4.piimg.com/2638821fdad08c7c.png)\r\n\r\n\r\n很明显，别人对我们具备的吸引力的识别由两个大部分组成：（1）我们自身的硬件条件；（2）别人的判断。现在我们谈一谈别人的判断。\r\n\r\n首先呢，他人作为观察者，他们自身的状况也会影响对食物的判断。有很多研究女性荷尔蒙的学者就此做了不少研究。大量的研究都指向了一个推论：那就是女性的确会因为每个月激素的波动，而稍稍改变对男性特征的喜好。当女性身处排卵期的时候，会更倾向于喜欢男性气质外露的男性（比如面孔阳刚有力的），也会稍稍倾向于短期交往模式；毕竟在排卵期预示着生殖能力的最高峰，会倾向于寻找有最优质基因的男性产生后代。而女性在月经周期的其他阶段会更想要找男性气质稍弱（这样的男性脾气更好，能做好父亲，恩），愿意组建长期家庭共同抚养孩子的男性。其他实验还表明，这个倾向不只是针对面孔，还针对声音(Puts, 2006)、体形(Little et al., 2007)，甚至是体味(Havlicek et al., 2005)等能够反映男性特征的方面。总而言之，在不同激素条件下，按照不同需求立体地寻找最优伴侣。\r\n\r\n不光荷尔蒙会影响识别，观察者自身的背景也会不分性别地影响判断。比如科学家在跨国家的研究中发现，当一个人出自相对而言环境恶劣的地方，会更倾向于寻找身强力壮的伴侣，从而产生抵抗力优秀的后代（也让别人难以抵抗的后代）。这也就能理解，为什么很多经历过社会动荡的老人们会提醒孩子，说找妻子要找有某些所谓「能生孩子」的特征的。当然找老公要写房产证名字的不在我们讨论的范畴。\r\n\r\n科学家们其实也在其他动物的求偶过程中（其他动物也会看长相，比如说孔雀，或者说长眼睛的貌似都看）发现了不少有趣的情况：很多雌性鸟类会根据其他雌鸟的态度去判断潜在的交配对象。比如说，一尾被众多雌鸟环绕的雄鸟往往会受到更多青睐。这一种现象又叫求偶复制现象（mate copying，m-a-t-e c-o-p-y-i-n-g），科学家对此的解释是在同性交配竞争中，通过他人现有的判断可以高效、并且少犯错地找到合适的配偶（或者说薅羊毛？蹭烟？蹭wifi？蹭。。。女友？）。正如其实我们很多人也会去客人多的地方买东西——就好比五道口地铁站附近的一个副食品商店（貌似卖枣糕），似乎永远爆满——虽然我们没吃过这家店，但是还会觉得既然每天那么多人排队，应该不是浪得虚名嘛。\r\n\r\n那么，我们在判断他人的相貌时候也会这样参照别人的判断么？科学家通过实验给出了一个答案：那就是很多情况下会，但是依赖环境本身。比如说 Johansson 博士在 2003 年的实验中发现，看到一位男士手戴婚戒，并不能令女性实验者更为青睐这位男士；不过对此，他解释在我们人类身上的「复制」比动物要更加复杂，也更需要环境配合。毕竟在上述试验中，这位男士戴着婚戒除了暗示其他女性的喜爱，也暗示了「名花有主」。这样「理性」的判断反而更符合伦理道德。紧跟着这个实验，其他科学家也利用了更精巧的设计进行研究，发现了「复制」的蛛丝马迹。比如，Ben Jones 教授和他的同事们在 2007 年利用了精巧的试验安排，展现了其他女性对男士的兴趣可以被传递，当然不会明目张胆显示不过是没有关上「可以交往的大门」：窥探到所谓社会判断传递，也就是说女性的确会因为其他女性的判断而改变对男性的相貌判断。一个更受（妹子们）欢迎的男性看起来比不受（妹子们）欢迎的要更为吸引人。虽然说这一点在男性判断女性上还没有充足证据，但是可以推论，那就是一个受别人欢迎的人必然有其优势，更容易受到他者的喜爱。\r\n\r\n不光如此，我们所处的环境也会影响别人对我们的判断。Tracy 和 Bell（2010）两位学者通过一个有趣的实验探讨了我们自身的表情对于我们相貌的影响。他们发现对于女性朋友们，在摆出开心表情时候可以最大程度上提升自己的美貌。或者说一抹浅浅的笑容就可以让别人觉得神清气爽，如沐春风。而对于男性朋友有着更有趣的情况。综合而言，男性在显露出骄傲的表情时候，不光让人觉得霸气十足，还会更有魅力；相对应的是，当男性显露出惭愧的神情时候，年轻女士们会觉得依然有魅力，而有经验的女士们（恋爱经验随着年纪增长）并不吃这一套（自从看了这篇论文，我就不再女性面前有事没事道歉了，这也是不对的）。对于这一点，研究人员调皮地解释道，这是因为恋爱经验不够丰富的女性可能试不破男性们装可怜的伪装，或者是比较大度就一笔勾销（naive）；但是年长的经验丰富「不吃这套」。关于面部表情，咦，你要不看看我的书就知道了？\r\n\r\n欢迎移步购买支持《看脸》（购买地址：[Kindle](http%3A//www.amazon.cn/dp/B016Y0FQPS/ref%3Dkd_we_zhihu_web_221015) | [豆瓣](http%3A//read.douban.com/ebook/15209603/) | [网易](http://yuedu.163.com/source/40bbad1102354c0ca98d3145642c3c38_4) | [百度](http%3A//yuedu.baidu.com/ebook/a82718c2312b3169a551a48b) | [多看](http%3A//www.duokan.com/book/98606)）\r\n\r\n随着社交网络的发达，也有学者从社交网络这样相对匿名之处，研究了他人评价对我们的影响。有研究发现，当你的真人头像和朋友的回复比较契合时候，观众对你长相的评价最为积极。从这一点我们可以联想到，我们周围人对于我们的评价其实也会时时刻刻影响别人对我们的判断；尤其是在相亲或者经朋友介绍认识别人的时候。看来，要做个好人可不能「独善其身」，如果周围的人不觉得你好，也是不行的呢。比如买了粉，这个再一万个赞也怪怪的吧。',34,0,0,2,0,0,1460472442,1460715834,1),
	(9,2,38,'Java NIO原理图文分析及代码实现','最近在分析hadoop的RPC(Remote Procedure Call Protocol ，远程过程调用协议，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。\r\n\r\n可以参考：[http://baike.baidu.com/view/32726.htm](http://baike.baidu.com/view/32726.htm) 机制时，发现hadoop的RPC机制的实现主要用到了两个技术：动态代理（动态代理可以参考博客：[http://weixiaolu.iteye.com/blog/1477774](http://weixiaolu.iteye.com/blog/1477774) ）和java NIO。为了能够正确地分析hadoop的RPC源码，我觉得很有必要先研究一下java NIO的原理和具体实现。\r\n\r\n我主要从两个方向来分析java NIO\r\n\r\n一．java NIO 和阻塞I/O的区别\r\n     1. 阻塞I/O通信模型\r\n     2. java NIO原理及通信模型\r\n二．java NIO服务端和客户端代码实现 \r\n\r\n**具体分析：**\r\n\r\n**一．java NIO 和阻塞I/O的区别**\r\n\r\n**1. 阻塞I/O通信模型**\r\n\r\n假如现在你对阻塞I/O已有了一定了解，我们知道阻塞I/O在调用InputStream.read()方法时是阻塞的，它会一直等到数据到来时（或超时）才会返回；同样，在调用ServerSocket.accept()方法时，也会一直阻塞到有客户端连接才会返回，每个客户端连接过来后，服务端都会启动一个线程去处理该客户端的请求。阻塞I/O的通信模型示意图如下：\r\n\r\n![](http://dl2.iteye.com/upload/attachment/0087/8153/a6cdd20a-260a-3664-9e7b-81d6737e746d.jpg)\r\n\r\n如果你细细分析，一定会发现阻塞I/O存在一些缺点。根据阻塞I/O通信模型，我总结了它的两点缺点：\r\n\r\n1. 当客户端多时，会创建大量的处理线程。且每个线程都要占用栈空间和一些CPU时间\r\n2. 阻塞可能带来频繁的上下文切换，且大部分上下文切换可能是无意义的。\r\n\r\n在这种情况下非阻塞式I/O就有了它的应用前景。\r\n\r\n**2. java NIO原理及通信模型**\r\n\r\nJava NIO是在jdk1.4开始使用的，它既可以说成“新I/O”，也可以说成非阻塞式I/O。下面是java NIO的工作原理：\r\n\r\n1. 由一个专门的线程来处理所有的 IO 事件，并负责分发。 \r\n2. 事件驱动机制：事件到的时候触发，而不是同步的去监视事件。 \r\n3. 线程通讯：线程之间通过 wait,notify 等方式通讯。保证每次上下文切换都是有意义的。减少无谓的线程切换。 \r\n\r\n阅读过一些资料之后，下面贴出我理解的java NIO的工作原理图：\r\n\r\n![](http://dl2.iteye.com/upload/attachment/0087/8155/15008451-aa92-34b3-83e8-b6813082591c.jpg)\r\n(注：每个线程的处理流程大概都是读取数据、解码、计算处理、编码、发送响应。)\r\n\r\nJava NIO的服务端只需启动一个专门的线程来处理所有的 IO 事件，这种通信模型是怎么实现的呢？呵呵，我们一起来探究它的奥秘吧。java NIO采用了双向通道（channel）进行数据传输，而不是单向的流（stream），在通道上可以注册我们感兴趣的事件。一共有以下四种事件：\r\n\r\n![](http://i4.piimg.com/bbbc171e603a904d.png)\r\n\r\n服务端和客户端各自维护一个管理通道的对象，我们称之为selector，该对象能检测一个或多个通道 (channel) 上的事件。我们以服务端为例，如果服务端的selector上注册了读事件，某时刻客户端给服务端发送了一些数据，阻塞I/O这时会调用read()方法阻塞地读取数据，而NIO的服务端会在selector中添加一个读事件。服务端的处理线程会轮询地访问selector，如果访问selector时发现有感兴趣的事件到达，则处理这些事件，如果没有感兴趣的事件到达，则处理线程会一直阻塞直到感兴趣的事件到达为止。下面是我理解的java NIO的通信模型示意图：\r\n\r\n![](http://dl.iteye.com/upload/attachment/0066/3190/0184183e-286c-34f1-9742-4adaa28b7003.jpg)\r\n\r\n**二．java NIO服务端和客户端代码实现**\r\n\r\n为了更好地理解java NIO,下面贴出服务端和客户端的简单代码实现。\r\n\r\n**服务端：**\r\n\r\n```java\r\npackage cn.nio;    \r\n    \r\nimport java.io.IOException;    \r\nimport java.net.InetSocketAddress;    \r\nimport java.nio.ByteBuffer;    \r\nimport java.nio.channels.SelectionKey;    \r\nimport java.nio.channels.Selector;    \r\nimport java.nio.channels.ServerSocketChannel;    \r\nimport java.nio.channels.SocketChannel;    \r\nimport java.util.Iterator;    \r\n    \r\n/**  \r\n * NIO服务端  \r\n * @author 小路  \r\n */    \r\npublic class NIOServer {    \r\n    //通道管理器    \r\n    private Selector selector;    \r\n    \r\n    /**  \r\n     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作  \r\n     * @param port  绑定的端口号  \r\n     * @throws IOException  \r\n     */    \r\n    public void initServer(int port) throws IOException {    \r\n        // 获得一个ServerSocket通道    \r\n        ServerSocketChannel serverChannel = ServerSocketChannel.open();    \r\n        // 设置通道为非阻塞    \r\n        serverChannel.configureBlocking(false);    \r\n        // 将该通道对应的ServerSocket绑定到port端口    \r\n        serverChannel.socket().bind(new InetSocketAddress(port));    \r\n        // 获得一个通道管理器    \r\n        this.selector = Selector.open();    \r\n        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，    \r\n        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。    \r\n        serverChannel.register(selector, SelectionKey.OP_ACCEPT);    \r\n    }    \r\n    \r\n    /**  \r\n     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理  \r\n     * @throws IOException  \r\n     */    \r\n    @SuppressWarnings(\"unchecked\")    \r\n    public void listen() throws IOException {    \r\n        System.out.println(\"服务端启动成功！\");    \r\n        // 轮询访问selector    \r\n        while (true) {    \r\n            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞    \r\n            selector.select();    \r\n            // 获得selector中选中的项的迭代器，选中的项为注册的事件    \r\n            Iterator ite = this.selector.selectedKeys().iterator();    \r\n            while (ite.hasNext()) {    \r\n                SelectionKey key = (SelectionKey) ite.next();    \r\n                // 删除已选的key,以防重复处理    \r\n                ite.remove();    \r\n                // 客户端请求连接事件    \r\n                if (key.isAcceptable()) {    \r\n                    ServerSocketChannel server = (ServerSocketChannel) key    \r\n                            .channel();    \r\n                    // 获得和客户端连接的通道    \r\n                    SocketChannel channel = server.accept();    \r\n                    // 设置成非阻塞    \r\n                    channel.configureBlocking(false);    \r\n    \r\n                    //在这里可以给客户端发送信息哦    \r\n                    channel.write(ByteBuffer.wrap(new String(\"向客户端发送了一条信息\").getBytes()));    \r\n                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。    \r\n                    channel.register(this.selector, SelectionKey.OP_READ);    \r\n                        \r\n                    // 获得了可读的事件    \r\n                } else if (key.isReadable()) {    \r\n                        read(key);    \r\n                }    \r\n    \r\n            }    \r\n    \r\n        }    \r\n    }    \r\n    /**  \r\n     * 处理读取客户端发来的信息 的事件  \r\n     * @param key  \r\n     * @throws IOException   \r\n     */    \r\n    public void read(SelectionKey key) throws IOException{    \r\n        // 服务器可读取消息:得到事件发生的Socket通道    \r\n        SocketChannel channel = (SocketChannel) key.channel();    \r\n        // 创建读取的缓冲区    \r\n        ByteBuffer buffer = ByteBuffer.allocate(10);    \r\n        channel.read(buffer);    \r\n        byte[] data = buffer.array();    \r\n        String msg = new String(data).trim();    \r\n        System.out.println(\"服务端收到信息：\"+msg);    \r\n        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());    \r\n        channel.write(outBuffer);// 将消息回送给客户端    \r\n    }    \r\n        \r\n    /**  \r\n     * 启动服务端测试  \r\n     * @throws IOException   \r\n     */    \r\n    public static void main(String[] args) throws IOException {    \r\n        NIOServer server = new NIOServer();    \r\n        server.initServer(8000);    \r\n        server.listen();    \r\n    }    \r\n    \r\n}\r\n```\r\n\r\n**客户端：**\r\n\r\n```java\r\npackage cn.nio;    \r\n    \r\nimport java.io.IOException;    \r\nimport java.net.InetSocketAddress;    \r\nimport java.nio.ByteBuffer;    \r\nimport java.nio.channels.SelectionKey;    \r\nimport java.nio.channels.Selector;    \r\nimport java.nio.channels.SocketChannel;    \r\nimport java.util.Iterator;    \r\n    \r\n/**  \r\n * NIO客户端  \r\n * @author 小路  \r\n */    \r\npublic class NIOClient {    \r\n    //通道管理器    \r\n    private Selector selector;    \r\n    \r\n    /**  \r\n     * 获得一个Socket通道，并对该通道做一些初始化的工作  \r\n     * @param ip 连接的服务器的ip  \r\n     * @param port  连接的服务器的端口号           \r\n     * @throws IOException  \r\n     */    \r\n    public void initClient(String ip,int port) throws IOException {    \r\n        // 获得一个Socket通道    \r\n        SocketChannel channel = SocketChannel.open();    \r\n        // 设置通道为非阻塞    \r\n        channel.configureBlocking(false);    \r\n        // 获得一个通道管理器    \r\n        this.selector = Selector.open();    \r\n            \r\n        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调    \r\n        //用channel.finishConnect();才能完成连接    \r\n        channel.connect(new InetSocketAddress(ip,port));    \r\n        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。    \r\n        channel.register(selector, SelectionKey.OP_CONNECT);    \r\n    }    \r\n    \r\n    /**  \r\n     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理  \r\n     * @throws IOException  \r\n     */    \r\n    @SuppressWarnings(\"unchecked\")    \r\n    public void listen() throws IOException {    \r\n        // 轮询访问selector    \r\n        while (true) {    \r\n            selector.select();    \r\n            // 获得selector中选中的项的迭代器    \r\n            Iterator ite = this.selector.selectedKeys().iterator();    \r\n            while (ite.hasNext()) {    \r\n                SelectionKey key = (SelectionKey) ite.next();    \r\n                // 删除已选的key,以防重复处理    \r\n                ite.remove();    \r\n                // 连接事件发生    \r\n                if (key.isConnectable()) {    \r\n                    SocketChannel channel = (SocketChannel) key    \r\n                            .channel();    \r\n                    // 如果正在连接，则完成连接    \r\n                    if(channel.isConnectionPending()){    \r\n                        channel.finishConnect();    \r\n                            \r\n                    }    \r\n                    // 设置成非阻塞    \r\n                    channel.configureBlocking(false);    \r\n    \r\n                    //在这里可以给服务端发送信息哦    \r\n                    channel.write(ByteBuffer.wrap(new String(\"向服务端发送了一条信息\").getBytes()));    \r\n                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。    \r\n                    channel.register(this.selector, SelectionKey.OP_READ);    \r\n                        \r\n                    // 获得了可读的事件    \r\n                } else if (key.isReadable()) {    \r\n                        read(key);    \r\n                }    \r\n    \r\n            }    \r\n    \r\n        }    \r\n    }    \r\n    /**  \r\n     * 处理读取服务端发来的信息 的事件  \r\n     * @param key  \r\n     * @throws IOException   \r\n     */    \r\n    public void read(SelectionKey key) throws IOException{    \r\n        //和服务端的read方法一样    \r\n    }    \r\n        \r\n        \r\n    /**  \r\n     * 启动客户端测试  \r\n     * @throws IOException   \r\n     */    \r\n    public static void main(String[] args) throws IOException {    \r\n        NIOClient client = new NIOClient();    \r\n        client.initClient(\"localhost\",8000);    \r\n        client.listen();    \r\n    }    \r\n    \r\n} \r\n```\r\n\r\n**小结:**\r\n\r\n终于把动态代理和java NIO分析完了，呵呵，下面就要分析hadoop的RPC机制源码了，博客地址：[http://weixiaolu.iteye.com/blog/1504898](http://weixiaolu.iteye.com/blog/1504898) 。不过如果对java NIO的理解存在异议的，欢迎一起讨论。',30,1,1,1,0,1,1460473545,1460820128,1),
	(10,1,17,'上海的同学你们都用什么网络呢？','**RT：准备切换电信，长宽实在卡成翔**',11,0,0,0,0,0,1460640058,1460640058,1),
	(11,2,32,'Java是世界上最牛逼的语言，不服来战！','\r\n哈哈，你们看这里 [给Java说句公道话](http://www.yinwang.org/blog-cn/2016/01/18/java)\r\n\r\n',23,0,0,1,0,0,1460640734,1460640957,1),
	(12,1,28,'求Nginx+Tomcat配置session共享的正确姿势','坐等大神解答。memcached试了没成功，你们都是用哪种方式做的呢？\r\n\r\n老司机来分享一下经验。',13,0,0,0,0,0,1460735048,1460735048,1),
	(13,11,32,'水一波水一波','魔镜呀魔镜，谁是最帅的帅逼。？\r\n魔镜：主人就是最帅的帅逼。\r\n哈哈哈哈哈哈，魔镜啊魔镜再问你一个问题，谁的代码撸的最好\r\n魔镜：啥？报告主人，撸代码伤身体，要节制！！！！！！！！\r\n',30,0,1,2,0,0,1460767138,1460781843,1),
	(14,1,8,'CENTOS6.7无法安装锐速的解决方法','由于到目前为止，锐速官方尚不支持centos6.7，所以centos6.7想要安装锐速就要降级内核。锐速官方支持内核一览：\r\n\r\n[http://my.serverspeeder.com/ls.do?m=availables](http://my.serverspeeder.com/ls.do?m=availables)\r\n\r\n首先需要确认自己的内核版本，输入命令uname -a，输出中有i686则为32位，有x86_64则为64位。\r\n下载32位内核：\r\n\r\n```sh\r\nwget http://ftp.scientificlinux.org/linux/scientific/6.5/i386/updates/security/kernel-2.6.32-504.el6.i686.rpm\r\n```\r\n\r\n下载64位内核：\r\n\r\n```sh\r\nwget http://ftp.scientificlinux.org/linux/scientific/6.5/x86_64/updates/security/kernel-2.6.32-504.el6.x86_64.rpm\r\n```\r\n\r\n以32位系统为例下载后可以\r\n\r\n```sh\r\nrpm -ivh kernel-2.6.32-504.el6.i686.rpm --force\r\n```\r\n\r\n降级内核，然后 `reboot` 重启系统即可。?',7,0,1,0,0,1,1460804567,1460805614,1);

/*!40000 ALTER TABLE `t_topic` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pass_word` varchar(32) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '电子邮箱',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后一次操作时间',
  `role_id` tinyint(2) DEFAULT '5' COMMENT '5:普通用户 2:管理员 1:系统管理员',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:待激活 1:正常 2：删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`uid`, `login_name`, `pass_word`, `avatar`, `email`, `create_time`, `update_time`, `role_id`, `status`)
VALUES
	(1,'biezhi','26b2010cd2028d742b283da54a304584','avatar/biezhi/PR6L/7294.jpg','biezhi.me@gmail.com',1410944818,1410944818,1,1),
	(2,'jacks','ba152812e0625a7b3f06d874c76a4227','avatar/default/2.png','jacks@java-china.org',1410944818,1410944818,5,1),
	(3,'kiyoumi','95d2cd9213ac2c8f477e3fa704099ff5','avatar/default/1.png','kiyoumi@java-china.org',1410944818,1410944818,5,1),
	(4,'lala','32068ed247a0fa371d162185d3f51861','avatar/default/3.png','lala@java-china.org',1410944818,1410944818,5,1),
	(5,'doubi','7b503366dbaeb4bbdb542c49c5ece63d','avatar/default/4.png','doubi@java-china.org',1410944818,1410944818,5,1),
	(6,'im_wangjue_NO1','7ed715e7fe818860a212646c1da830f2','avatar/default/2.png','351711778@qq.com',1460639261,1460639261,5,1),
	(7,'lichee','3808575efd4d0ae4cb43f535e2c14cb1','avatar/default/1.png','z@lichee.me',1460639488,1460639488,5,1),
	(8,'XingFly','56bdfa42ac2c35fbe992f5c6190be0d6','avatar/default/1.png','549052145@qq.com',1460650525,1460650525,5,1),
	(9,'kexun','2da754a873d09ef5bd0086fd507ad9f8','avatar/default/1.png','qianchj@163.com',1460681929,1460681929,5,1),
	(10,'uilzzw','b375ab93242ef366ac1b2d4b8d05cffd','avatar/default/1.png','uilzzw@gmail.com',1460682181,1460682181,5,1),
	(11,'hezhezhiyu','e5abfe520f6582676da2ab34790981a2','avatar/default/3.png','84785027@qq.com',1460728178,1460728178,5,1),
	(12,'ssnoodles','e3c4e1401b3162c47ec7a42162a46602','avatar/default/0.png','ssnoodles0226@gmail.com',1460769332,1460769332,5,1),
	(13,'lvdong5830','1072b5ba593f578e30d018895a03dc73','avatar/lvdong5830/RxI2/8751.png','425280895@qq.com',1460786636,1460786636,5,1),
	(14,'Shildon','9d53aa31eabb5d3897ed3e514c4ed976','avatar/default/4.png','shildondu@gmail.com',1460809033,1460809033,5,1);

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_userinfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `uid` int(10) NOT NULL,
  `nick_name` varchar(30) DEFAULT NULL,
  `jobs` varchar(100) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  `github` varchar(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `instructions` text CHARACTER SET utf8mb4,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_userinfo` WRITE;
/*!40000 ALTER TABLE `t_userinfo` DISABLE KEYS */;

INSERT INTO `t_userinfo` (`uid`, `nick_name`, `jobs`, `web_site`, `github`, `location`, `signature`, `instructions`)
VALUES
	(1,'王爵','Web Dev!','https://biezhi.me','biezhi','上海','个性无需签名','...'),
	(2,'帅比杰克','网络公关',NULL,NULL,'广州','我就是帅',NULL),
	(3,'美少女','打酱油的',NULL,NULL,'日本东京','biubiubiu～',NULL),
	(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(14,NULL,NULL,'https://github.com/XiaodongDu','XiaodongDu',NULL,NULL,NULL);

/*!40000 ALTER TABLE `t_userinfo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_userlog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_userlog`;

CREATE TABLE `t_userlog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `action` varchar(100) NOT NULL,
  `content` text,
  `ip_addr` varchar(50) DEFAULT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `t_userlog` WRITE;
/*!40000 ALTER TABLE `t_userlog` DISABLE KEYS */;

INSERT INTO `t_userlog` (`id`, `uid`, `action`, `content`, `ip_addr`, `create_time`)
VALUES
	(60,9,'signin','kexun','103.250.195.130',1460685722),
	(61,9,'signin','kexun','103.250.195.130',1460685761),
	(62,8,'signin','XingFly','219.156.42.80',1460687174),
	(63,1,'signin','biezhi','180.168.136.74',1460707667),
	(64,1,'signin','biezhi','180.168.136.74',1460712732),
	(65,1,'signin','biezhi','180.168.136.74',1460712756),
	(66,1,'signin','biezhi','180.168.136.74',1460712798),
	(67,1,'signin','biezhi','180.168.136.74',1460712849),
	(68,1,'signin','biezhi','180.168.136.74',1460713188),
	(69,1,'signin','biezhi','180.168.136.74',1460713828),
	(70,1,'signin','biezhi','180.168.136.74',1460713922),
	(71,1,'signin','biezhi','180.168.136.74',1460714421),
	(72,1,'signin','biezhi','180.168.136.74',1460714939),
	(73,1,'signin','biezhi','180.168.136.74',1460715050),
	(74,7,'add_comment','看来我自尊很高嘛','218.76.31.42',1460715812),
	(75,7,'add_comment','what','218.76.31.42',1460715834),
	(76,1,'signin','biezhi','113.52.133.180',1460723429),
	(77,0,'send_mail','84785027@qq.com:OVS1w1eKpqqP9E02NEHLZJ4tgFCbqn0N:signup','120.52.24.193',1460728178),
	(78,11,'signup','hezhezhiyu:84785027@qq.com','120.52.24.193',1460728178),
	(79,1,'signin','biezhi','116.251.211.11',1460734100),
	(80,1,'signin','biezhi','116.216.0.50',1460734305),
	(81,1,'signin','biezhi','116.251.211.11',1460734949),
	(82,1,'add_topic','坐等大神解答。memcached试了没成功，你们都是用哪种方式做的呢？\r\n\r\n老司机来分享一下经验。','116.251.211.11',1460735048),
	(83,11,'signin','hezhezhiyu','123.150.182.17',1460765511),
	(84,11,'signin','hezhezhiyu','124.206.140.194',1460766962),
	(85,11,'add_topic','魔镜呀魔镜，谁是最帅的帅逼。？\r\n魔镜：主人就是最帅的帅逼。\r\n哈哈哈哈哈哈，魔镜啊魔镜再问你一个问题，谁的代码撸的最好\r\n魔镜：啥？报告主人，撸代码伤身体，要节制！！！！！！！！\r\n','118.244.254.2',1460767138),
	(86,0,'send_mail','ssnoodles0226@gmail.com:eZwBLeiSkTdkhXrUcSFnBwOHYMSUNvrt:signup','112.87.42.242',1460769332),
	(87,12,'signup','ssnoodles:ssnoodles0226@gmail.com','112.87.42.242',1460769332),
	(88,12,'signin','ssnoodles','112.87.42.242',1460769611),
	(89,1,'signin','biezhi','116.216.0.50',1460779669),
	(90,1,'add_comment','## 楼主这个逼装的我给100分','116.216.0.50',1460779726),
	(91,11,'signin','hezhezhiyu','118.244.254.2',1460781824),
	(92,11,'add_comment','@biezhi 层主这个回复我给99分。','124.206.140.194',1460781843),
	(93,0,'send_mail','425280895@qq.com:G8RSUl7okYo0TeILGrAKuwahVkTIxDXu:signup','122.96.226.85',1460786636),
	(94,13,'signup','lvdong5830:425280895@qq.com','122.96.226.85',1460786636),
	(95,13,'signin','lvdong5830','122.96.226.85',1460786665),
	(96,13,'add_comment','支持','122.96.226.85',1460786675),
	(97,11,'signin','hezhezhiyu','36.110.14.50',1460792310),
	(98,1,'signin','biezhi','116.251.218.99',1460801608),
	(99,1,'signin','biezhi','116.251.218.99',1460804209),
	(100,1,'signin','biezhi','116.251.218.99',1460804448),
	(101,1,'signin','biezhi','116.251.218.99',1460805295),
	(102,1,'essence','14:1','116.251.218.99',1460805614),
	(103,0,'send_mail','shildondu@gmail.com:7xqDuewr4Aga2n0hBeyFCaxSB5BSeWlf:signup','120.236.172.6',1460809033),
	(104,14,'signin','Shildon','120.236.172.6',1460809081),
	(105,1,'signin','biezhi','113.52.133.180',1460819883),
	(106,1,'signin','biezhi','113.52.133.180',1460820069),
	(107,1,'essence','9:1','113.52.133.180',1460820093),
	(108,1,'add_comment','好文先收藏了。','113.52.133.180',1460820128),
	(109,1,'signin','biezhi','116.251.209.30',1460822632),
	(110,14,'signin','Shildon','120.236.172.6',1460864537),
	(111,8,'signin','XingFly','125.47.76.254',1460866089),
	(30,1,'signin','biezhi','116.251.211.104',1460637172),
	(31,0,'send_mail','351711778@qq.com:Ptm6jISYk2ZMXhJI2Uo1YzFwxhkaircl:signup','114.111.166.5',1460639261),
	(32,6,'signup','im_wangjue_NO1:351711778@qq.com','114.111.166.5',1460639261),
	(33,6,'signin','im_wangjue_NO1','114.111.166.5',1460639312),
	(34,1,'add_comment','和你男朋友搞基的就是我','116.251.211.104',1460639477),
	(35,7,'signup','lichee:z@lichee.me','218.76.31.68',1460639488),
	(36,0,'send_mail','z@lichee.me:iVCQcRrto0az2n5YaNuPMeY9u0QGZc5e:signup','218.76.31.68',1460639488),
	(37,7,'signin','lichee','218.76.31.68',1460639526),
	(38,7,'add_comment','沙发~','218.76.31.68',1460639591),
	(39,6,'add_comment','@biezhi 66666666','114.111.166.5',1460639610),
	(40,1,'add_comment','@lichee 板凳 :joy:','116.251.211.104',1460639800),
	(41,1,'add_comment','这种妹子图懂东西多发点 哈哈～','116.251.211.104',1460639941),
	(42,1,'add_topic','**RT：准备切换电信，长宽实在卡成翔**','116.251.211.104',1460640058),
	(43,1,'add_comment','不会python 给楼主顶一下。','116.251.211.104',1460640440),
	(44,2,'signin','jacks','116.251.211.104',1460640538),
	(45,2,'add_topic','\r\n哈哈，你们看这里 [给Java说句公道话](http://www.yinwang.org/blog-cn/2016/01/18/java)\r\n\r\n','116.251.211.104',1460640734),
	(46,1,'signin','biezhi','116.251.211.104',1460640921),
	(47,1,'add_comment','小伙子，对于你这种狂傲的帖子，我想说：我喜欢。','116.251.211.104',1460640957),
	(48,1,'add_comment','楼主写了这么多就是要卖书，我不会告诉你我直接看到链接的。','116.251.211.104',1460641104),
	(49,1,'add_comment','约炮都可以这么深奥。\r\n\r\n![](http://i2.piimg.com/db8eb02559fbd120.jpg)\r\n','116.251.211.104',1460641347),
	(50,0,'send_mail','549052145@qq.com:oUFnFWyWFp2omAT8iE3XGX25shyjo1BW:signup','219.156.42.80',1460650525),
	(51,8,'signup','XingFly:549052145@qq.com','219.156.42.80',1460650525),
	(52,0,'send_mail','549052145@qq.com:pmtcdi9x1oeOOOQoL7Wg4jn84yjta4aR:forgot','219.156.42.80',1460650607),
	(53,7,'signin','lichee','175.9.70.239',1460650825),
	(54,8,'signin','XingFly','219.156.42.80',1460651206),
	(55,0,'send_mail','qianchj@163.com:1RVLvV12QTH2tFwqflQDIcuxqbHd5OmX:signup','106.186.28.162',1460681929),
	(56,9,'signup','kexun:qianchj@163.com','106.186.28.162',1460681929),
	(57,0,'send_mail','uilzzw@gmail.com:B2OZXMHse5OTv2vBlyHN5lCmdg2qPasu:signup','124.42.103.131',1460682181),
	(58,10,'signup','uilzzw:uilzzw@gmail.com','124.42.103.131',1460682182),
	(59,10,'signin','uilzzw','118.184.28.242',1460682357);

/*!40000 ALTER TABLE `t_userlog` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
