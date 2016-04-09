# ************************************************************
# Sequel Pro SQL dump
# Version 4529
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.29)
# Database: javachina
# Generation Time: 2016-04-09 20:30:39 +0000
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
	(8,16,'w2HOu3fvH9YclLBysdPdYeYkzp66ceq7','signup',0,1460197239,1460193639);

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
  `content` text NOT NULL COMMENT '评论内容',
  `create_time` int(10) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;

INSERT INTO `t_comment` (`cid`, `uid`, `to_uid`, `tid`, `content`, `create_time`)
VALUES
	(1,1,1,7,'没有人解答吗，在线等',1459525310),
	(2,1,13,12,'asdasdasdasdad',1459953211),
	(3,1,13,12,'sdasdasd',1459953403),
	(4,1,13,12,'## aaaaa',1459953537);

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
	(16,'topic',1,15,1460226395),
	(15,'following',1,13,1460225723),
	(17,'topic',1,12,1460226405),
	(35,'love',1,14,1460227492),
	(36,'love',1,15,1460227501);

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



# Dump of table t_love
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_love`;

CREATE TABLE `t_love` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `tid` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `t_love` WRITE;
/*!40000 ALTER TABLE `t_love` DISABLE KEYS */;

INSERT INTO `t_love` (`id`, `uid`, `tid`)
VALUES
	(8,1,1),
	(10,1,16),
	(12,1,15),
	(16,1,12);

/*!40000 ALTER TABLE `t_love` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_node
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_node`;

CREATE TABLE `t_node` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '发布人uid',
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

INSERT INTO `t_node` (`nid`, `uid`, `pid`, `title`, `description`, `slug`, `pic`, `topics`, `create_time`, `update_time`, `is_del`)
VALUES
	(1,1,0,'站务管理','站务管理','affair','node/affair.png',3,1457933734,1459596711,0),
	(2,0,1,'程序发布','','release',NULL,3,1457933734,0,0),
	(3,1,0,'分享与探索','','fenxiang','',0,1459519131,1459519131,0),
	(4,1,0,'生活','','life','',0,1459519169,1459519169,0),
	(5,1,0,'Java','','java','',0,1459519176,1459519176,0),
	(6,1,3,'问与答','','qna','',3,1459519199,1459519199,0),
	(7,1,3,'分享发现','','share','',3,1459519229,1459519229,0),
	(8,1,3,'分享创造','','create','',2,1459519247,1459519247,0),
	(9,1,3,'奇思妙想','','ideas','',1,1459519266,1459519266,0),
	(10,1,5,'Spring','','spring','node/spring.png',3,1459519278,1459617072,0),
	(11,1,5,'爬虫','','spiders','',1,1459519288,1459519288,0),
	(12,1,0,'招聘','','jobs','',0,1459524976,1459524976,0),
	(13,1,12,'酷工作','','cool','',1,1459524995,1459524995,0);

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
	(3,'comment',1,13,16,0,0);

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
	(3,'github',3849072,16,1460193639);

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
	('comment_count','5'),
	('site_description','JavaChina是一个使用Blade框架开发的Java简洁论坛程序'),
	('site_keywords','Java社区,Blade框架,程序员论坛,开源程序'),
	('site_title','Java中国'),
	('topic_count','20'),
	('user_count','2');

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
	(1,1,2,'Hello Java China!','Java China的第一个帖子！',38,0,0,0,0,0,1457933734,1457933734,1),
	(2,13,1,'感觉自己是一个很 low 的程序员','转行做的程序员，两三年经验 \r\n主要语言 python,熟悉 scrapy 和 django \r\nlinux,css,js,jquery,mysql,nosql,java 也会一些 \r\n\r\n发现自己是面向 google 编程，面向 stackoverflow 编程，面向文档编程 \r\n遇到的问题基本都能解决 \r\n但 flask,django 等源码看不懂或者很吃力 \r\nIT 界的知识太多太广，越学越感觉自己很渺小很 low 啊',5,0,0,0,0,0,1458740979,1458740979,1),
	(3,13,1,'小米……好吧，果然是耍猴大王……','本来对小米的厌恶不下于对阿里家的厌恶。\r\n\r\n最近看做的手机，貌似颜值还行，而且，迭了那么多代，应该系统也能用了。 对它的品牌感知逐渐好转。\r\n\r\n但是不爽的是，刚想买个小米平板 Win10 版写代码时，却发现，真会耍猴啊。需要说明的是，小米平板早就发布了，此外，平板毕竟不像手机需求旺盛。你说你去年发布的平板，现在官网还买不到，不就是玩期货、玩耍猴吗？加 2~3 百才能买一台，感觉构成了实质意义上的欺骗消费者了。（换种方式，你以为我看不出来？）\r\n\r\n**本来你心动了不是？买不到！想买？加价买~~**\r\n\r\n雾里探花啊~\r\n',4,0,0,0,0,0,1458744255,1458744255,1),
	(4,13,1,'AppleDNS 再次更新，超越 V2EX DNS 的 Apple 网络加速神器','全新的 AppleDNS ，大快所有人心的好项目。真的快，快出声！\n\n[https://github.com/gongjianhui/AppleDNS](https://github.com/gongjianhui/AppleDNS)\n\n**AppleDNS 是一套针对 Apple 非隐私类网络服务的加速配置文件，目前主要支持 (Mac) App Store 和 Apple Music 的加速，可以配置在一切可以实现 Hosts 功能的设备上。（包括 Surge ）**\n\n今日凌晨更新的 AppleDNS 针对 Apple Music 冷门音乐加载问题给出了解决方案，适用于所有国际出口尚可的用户。\n\n**请注意， AppleDNS 中设置的域名不应在任何代理软件的黑名单中出现。**\n\n\n\n以下是不要脸的求打赏地址\n\n**支付裱**: i@gongjianhui.com **BTC**: [1Jianhui1ZUDHDCz1TGzGH2rWaxas1GS9S](https://blockchain.info/address/1Jianhui1ZUDHDCz1TGzGH2rWaxas1GS9S)',17,0,0,0,0,1,1458744661,1458744661,1),
	(5,1,7,'asdasdasd','asdasdasdad',2,0,0,0,0,0,1459524749,1459524749,1),
	(6,1,8,'魔都杏花楼的青团竟然炒到了 200~300 块钱/6 个！！！','说实话中秋节鲜肉月饼也算是排长队，黄牛各种炒了，都没这样贵过。。。。 \r\n\r\n今年是什么情况？？还是往年也这样，我没留意不知道？ \r\n\r\n以前提到过沃尔玛·财务自由，全家·财务自由，进口超市·财务自由。 \r\n现在看样子再往上就是中秋节吃月饼·财务自由，清明节吃青团·财务自由了。',2,0,0,0,0,0,1459524774,1459524774,1),
	(7,1,10,'Spring MVC构建web程序遇到的bug','求大神解答，如何使用maven构建一个springmvc的项目呢？',25,0,0,1,0,0,1459524847,1459524847,1),
	(8,1,6,'「求助」新买的笔记本是 PCIE 接口的 SSD','求助万能的 V2 ，新买的笔记本是 Windows 10 的系统，使用 U 盘装为 Windows 8.1 ， \r\n但到了「您想将 Windows 安装在哪里」的时候没有任何磁盘显示出来，进入 diskpart 也只能看到 U 盘自己， \r\n是不是和 PCIE 接口有关呢？怎么破？',28,0,0,0,0,1,1459524918,1459524918,1),
	(9,1,13,'[轻轻家教]C 轮创业公司诚聘各类技术英才','[轻轻家教是谁？] \r\n\r\n- 国内领先的家教 O2O 移动互联网公司。半年完成 A/B/B+/C 四轮融资。(融资速度还有更快的么？) \r\n- 最新 C 轮融资，由好未来， IDG ，红杉和挚信资本共同完成的 1 亿美元投资。（行业老大和 VC 老大同时入场，不缺资源不缺钱） \r\n- 可能是 2015 年最好的成长机会，由原昂立教育董事长、精锐教育联合创始人和来自国内领先互联网企业的资深高管联合创办 。(教育界大咖创立的互联网公司。 O2O 行业，线下业务能力占 60%，你懂的) \r\n- 交通便利，位于张江浦东软件园， 2 号线金科路地铁站，轻松惬意的办公环境。(园区内一个大湖，中午吃完饭湖边走走) \r\n\r\n![Alt text](http://i.v2ex.co/jC7Uj9f3l.jpeg)\r\n\r\n![Alt text](http://i.v2ex.co/M85owQKBl.jpeg)\r\n\r\n![Alt text](http://i.v2ex.co/SepTiuh9l.jpeg)\r\n\r\n[员工福利待遇] \r\n\r\n10K-30K ，有能力者无上限； \r\n弹性工作制，自由宽松环境； \r\n每日定时水果，无限量茶和咖啡供应； \r\n宽大办公桌，舒适的人体工程学座椅； \r\n双 24 寸显示器， ios 团队配置 16G 内存的 iMac 或 macBook Pro; \r\n定期不定期的腐败和体育活动； \r\n其他的合理需求，就等你来提了。 \r\n(另外小声说一句，运营部 PP 妹子很多…) \r\n\r\n[技术团队] \r\n\r\n-	约 45 人，核心骨干均来自于微软，携程， PPTV 等知名互联网公司。 \r\n-	崇尚自由，分享，敢于担当的工程师文化。 \r\n-	不定期请外部大牛进行技术交流和分享。 \r\n-	目标是互联网教育行业最好的技术团队。 \r\n\r\n我们有明确的方向，但是罗马不是一天建成，我们每天都在进步。公司在快速发展，技术团队也在快速成长，我们真诚邀请更多优秀技术人才加入我们。 \r\n我们期待的技术人才， TA 应该极度热爱技术，对代码和工程质量有执着追求，敢于担当又善于合作，对一切事务有永不满足的好奇心。 \r\n\r\n[人才啊，快到碗里来] \r\n\r\nJava 后台开发工程师 \r\n-	负责公司核心后台服务及运营管理系统研发，保证系统高性能，高可用，可扩展； \r\n-	计算机本科或相关学历， 2 年以上 Java 后台开发经验，熟悉 MySQL 数据库开发； \r\n-	扎实编程功底，精通数据结构和算法。 \r\n\r\nAndroid 开发工程师 \r\n-	计算机本科或相关学历， 2 年以上 Android 开发经验； \r\n-	熟悉 Android OS 体系结构及 Android SDK 开发； \r\n-	熟悉网络编程，多线程，有丰富的 UI 开发经验； \r\n-	有 Android 插件框架或 H5 开发经验优先； \r\n-	扎实编程功底，熟悉数据结构和算法。 \r\n\r\nIOS 开发工程师 \r\n-	计算机本科或相关学历， 2 年以上 iOS 开发经验； \r\n-	具备扎实 Objective-C 基础，熟悉 iOS 开发框架； \r\n-	熟悉网络编程， iOS 开发调试； \r\n-	有 H5 开发经验优先； \r\n-	扎实编程功底，熟悉数据结构和算法。 \r\n\r\nHTML5 开发工程师 \r\n-	计算机本科或相关学历， 3 年以上前端开发经验； \r\n-	熟悉 HTML/CSS3/JS 标准，对跨浏览器和平台的兼容性开发有经验； \r\n-	熟悉任意 MV*框架(如： AngularJS/Konkout/React/Backbone/Ember 等) \r\n-	熟悉任意自动化构建工具(如 Grunt/Gulp 等) \r\n-	对页面性能优化有一定认识和经验； \r\n\r\n数据工程师 \r\n岗位职责： \r\n1 、参与公司业务系统数据架构的设计，数据标准的实施，数据质量的管理； \r\n2 、负责数据管控制度与流程制定、维护和推进实施； \r\n3 、参与公司数据仓库建设，负责数据仓库日常业务运维及后续数据模型、数据标准、 ETL 流程、应用报表的维护及开发； \r\n4 、配合组织数据类应用项目及数据集市项目建设； \r\n\r\n职位要求： \r\n1 、计算机、统计或者数学相关专业，本科以上学历； \r\n3 、有 1 － 3 年大数据或数据仓库项目经验，了解数据仓库相关理论知识； \r\n4 、了解大数据分析处理（ Hadoop ， HDFS, MapReduce ， Spark, Hbase ， Pig ， Hive ）等技术内部机制优先； \r\n5 、精通 SQL 开发，精通 Mysql 、 Oracle 等关系型数据库中的一种； \r\n5 、有对象或面向函数编程经验，具有 Java 或者 python 开发经验者优先； \r\n6 、熟悉 linux 基本原理； \r\n7 、学习能力强，喜欢研究新技术，有团队观念，具备独立解决问题的能力； \r\n\r\n系统平台工程师 /运维工程师 \r\n\r\n-	负责公司系统运维管理，自动化运维平台建设，基础技术平台(大数据，云平台等)的的建设和优化。 \r\n-	计算机本科或相关学历； \r\n-	具有大规模互联网系统的运维经验，对 devops 有一定理解； \r\n-	深入理解 linux 系统，具有丰富的网络知识，熟练使用 shell 、 python 等脚本编程语言；有 java 编程经验优先； \r\n-	熟悉 zabbix/cacti 等监控工具，熟悉 puppet 等配置管理工具，熟悉 LVS/Nginx/php/tomcat 等服务器的运维管理和性能优化； \r\n-	了解 openstack 等主流云计算方案，了解 hadoop/storm/flume/spark 等主流大数据解决方案。 \r\n\r\nPHP 高级开发工程师 \r\n岗位职责： \r\n1 、负责官网 /CMS/移动 H5 系统的整体架构设计和开发； \r\n2 、独立完成软件的开发、验证和修正测试中发现的问题； \r\n3 、持续优化前端体验和页面响应速度，并保证兼容性和执行效率； \r\n4 、技术分享，和团队共同进步； \r\n\r\n任职条件： \r\n1 、全日制本科及以上学历， 3 年以上 web 开发经验; \r\n2 、熟悉至少一种 PHP 应用框架，如 CodeIgniter 、 Yii 、 ThinkPHP; \r\n3 、熟悉 PHP/Mysql/HTML/Javascript/CSS; \r\n4 、对 Web 服务端性能优化有研究; \r\n5 、对网站页面性能优化有一定认识和经验； \r\n6 、强烈的责任心和团队精神，善于合作，热衷于新技术的探索和研究； \r\n\r\n数据工程师 \r\n岗位职责： \r\n1 、参与公司业务系统数据架构的设计，数据标准的实施，数据质量的管理； \r\n2 、负责数据管控制度与流程制定、维护和推进实施； \r\n3 、参与公司数据仓库建设，负责数据仓库日常业务运维及后续数据模型、数据标准、 ETL 流程、应用报表的维护及开发； \r\n4 、配合组织数据类应用项目及数据集市项目建设； \r\n\r\n职位要求： \r\n1 、计算机、统计或者数学相关专业，本科以上学历； \r\n3 、有 1 － 3 年大数据或数据仓库项目经验，了解数据仓库相关理论知识； \r\n4 、了解大数据分析处理（ Hadoop ， HDFS, MapReduce ， Spark, Hbase ， Pig ， Hive ）等技术内部机制优先； \r\n5 、精通 SQL 开发，精通 Mysql 、 Oracle 等关系型数据库中的一种； \r\n5 、有对象或面向函数编程经验，具有 Java 或者 python 开发经验者优先； \r\n6 、熟悉 linux 基本原理； \r\n7 、学习能力强，喜欢研究新技术，有团队观念，具备独立解决问题的能力； \r\n\r\n简历请投递至： wuyujin#changingedu.com\r\n',38,0,0,0,0,1,1459525231,1460217950,1),
	(10,13,10,'多图详解Spring框架的设计理念与设计模式','Spring作为现在最优秀的框架之一，已被广泛的使用，51CTO也曾经针对Spring框架中的JDBC应用做过报道。本文将从另外一个视角试图剖析出Spring框架的作者设计Spring框架的骨骼架构的设计理念，有那几个核心组件？为什么需要这些组件？它们又是如何结合在一起构成Spring的骨骼架构？Spring的AOP特性又是如何利用这些基础的骨骼架构来工作的？Spring中又使用了那些设计模式来完成它的这种设计的？它的这种 设计理念对对我们以后的软件设计有何启示？本文将详细解答这些问题。\r\n\r\n',1,0,0,0,0,0,1459615596,1459615596,1),
	(11,13,8,'可能是东半球最好的文字游戏吧! 一玩就停不下来!','\r\n[http://g.miaowu.asia/index.php?lang=cn ](http://g.miaowu.asia/index.php?lang=cn )\r\n\r\n\r\n加入了存档系统。 \r\n\r\n双击可以自动采集资源 \r\n\r\n(游戏是开源的 我只是优化跟加入存档系统,并且保证它一直存在下去，已经开了1年多了)',2,0,0,0,0,1,1459615649,1459615649,1),
	(12,13,6,'如何看待麦当劳新推出的叫号排队系统？','之前麦当劳点餐之后是直接配餐，如果你要一杯可乐，点餐的同时已经在给你准备了，付款后很快就能给你端上来。 \r\n而现在新推出的银行式叫号系统，点餐后给你一个序号单，必须按屏幕上的叫号顺序一个个取餐。哪怕你只点了一丁点东西，也要等前面点了很多东西的人上完餐才能领餐。我认为这无形中降低了上餐的效率，也浪费了顾客的等待时间。大家怎么看呢？',148,1,0,3,0,0,1459615677,1459953557,1),
	(13,14,6,'IOS 该用什么浏览器？想弃用 UCweb','第一：感觉 UC 主界面集成了各种乱七八糟的资讯：小道新闻、情感故事、各种伪科学！ \r\n第二：平时和过节充满活动广告，启动界面，头像旁边，书签页旁边各种活动推广。 \r\n第三： UC 又取消了 UC 网盘。感觉用的必要性又减少了。 \r\n------- \r\n觉得 Safari 又太简单的。不知道是为用户过多考虑还是什么。智能的甚至有些难用。 \r\n------ \r\n\r\n所以大家都用什么。有什么好的推荐！\r\n',6,0,0,0,0,0,1459615917,1459615917,1),
	(14,14,10,'如何搭建spring框架','下面，我将用两种方式来讲述如何搭建Spring环境，其中第一种是利用MyEclipse工具里自带的来自动配置Spring，第二种事由我们自己手动配置，有什么区别么，没有什么太大的区别，第一种稍微简单，第二种稍微复杂，但是第二种方式能配置较高版本的Spring，大家看个人爱好了！',140,0,1,0,0,1,1459616454,1460220355,1),
	(15,1,9,'如果你现在有1000万你准备干什么？','rt：只是假如。。。',52,1,1,0,0,0,1459616515,1460217789,1),
	(16,1,11,'如何爬取最新的电影资讯呢？','使用java',54,0,0,0,0,0,1459616551,1459616551,1),
	(17,1,2,'今天天气真好','???',30,0,0,0,0,0,1460042379,1460042379,1),
	(18,1,2,'asdadadad','☺️☺️☺️????????????qwsdadadsadadad\r\n\r\n## 测试emoji\r\n',16,0,0,0,0,0,1460043271,1460043271,1);

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
	(1,'biezhi','26b2010cd2028d742b283da54a304584','avatar/20160406221125226_AfS6NjIzMc.jpg','biezhi.me@gmail.com',1410944818,1410944818,1,1),
	(13,'wangjue','880d2c4f88de0c111e4a14a2e93cbccf','avatar/wangjue.png','i@biezhi.me',1458573501,1458573501,5,1),
	(14,'jobs','65e7b35f7ae3a0a23e13745dcb0ffa57','avatar/jobs.png','',0,0,5,1),
	(15,'biezhi','26b2010cd2028d742b283da54a304584','avatar/default/0.png','i@biezhi.me',1460193554,1460193554,5,0),
	(16,'biezhii','f3beb755b5fb35a216dc5de7173c00c0','avatar/default/4.png','921293209@qq.com',1460193639,1460193639,5,0);

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
  `signature` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `instructions` text CHARACTER SET utf8mb4,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_userinfo` WRITE;
/*!40000 ALTER TABLE `t_userinfo` DISABLE KEYS */;

INSERT INTO `t_userinfo` (`uid`, `nick_name`, `jobs`, `web_site`, `github`, `signature`, `instructions`)
VALUES
	(1,'王爵','Web Dev!','https://biezhi.me','biezhi','这个人有点懒,还没写签名','...');

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
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
