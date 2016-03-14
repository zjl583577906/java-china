/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.40 : Database - java-china
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_activecode` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_activecode` */

/*Table structure for table `t_comment` */

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

/*Data for the table `t_comment` */

/*Table structure for table `t_favorite` */

DROP TABLE IF EXISTS `t_favorite`;

CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT 'topic:帖子 node:节点',
  `uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_favorite` */

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_link` */

/*Table structure for table `t_node` */

DROP TABLE IF EXISTS `t_node`;

CREATE TABLE `t_node` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `title` varchar(30) DEFAULT NULL COMMENT '节点名称',
  `description` varchar(255) DEFAULT NULL COMMENT '节点描述',
  `slug` varchar(50) NOT NULL COMMENT '节点英文简写',
  `pic` varchar(100) DEFAULT NULL COMMENT '节点图片',
  `topics` int(10) NOT NULL DEFAULT '0' COMMENT '帖子数',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_node` */

insert  into `t_node`(`nid`,`pid`,`title`,`description`,`slug`,`pic`,`topics`,`create_time`,`is_del`) values (1,0,'站务管理','站务管理','affair',NULL,1,1457933734,0),(2,1,'程序发布','','release',NULL,1,1457933734,0);

/*Table structure for table `t_settings` */

DROP TABLE IF EXISTS `t_settings`;

CREATE TABLE `t_settings` (
  `skey` varchar(50) NOT NULL,
  `svalue` text,
  PRIMARY KEY (`skey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_settings` */

/*Table structure for table `t_topic` */

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `tid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '发布人',
  `nid` int(10) NOT NULL COMMENT '所属节点',
  `title` varchar(50) NOT NULL COMMENT '帖子标题',
  `content` text COMMENT '帖子内容',
  `views` int(10) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `favorites` int(10) NOT NULL DEFAULT '0' COMMENT '被收藏数',
  `stars` int(10) NOT NULL DEFAULT '0' COMMENT '获得点赞数',
  `comments` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `is_top` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `create_time` int(10) NOT NULL COMMENT '帖子创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:正常 2:删除',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_topic` */

insert  into `t_topic`(`tid`,`uid`,`nid`,`title`,`content`,`views`,`favorites`,`stars`,`comments`,`is_top`,`create_time`,`update_time`,`status`) values (1,1,2,'Hello Java China!','Java China的第一个帖子！',10,0,0,0,0,1457933734,1457933734,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pass_word` varchar(32) DEFAULT NULL,
  `following` int(10) DEFAULT '0' COMMENT '我关注的人数',
  `notices` int(10) DEFAULT '0' COMMENT '未读通知数',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '电子邮箱',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后一次操作时间',
  `role_id` tinyint(2) DEFAULT '5' COMMENT '5:普通用户 2:管理员 1:系统管理员',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:待激活 1:正常 2：删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`login_name`,`pass_word`,`following`,`notices`,`avatar`,`email`,`create_time`,`update_time`,`role_id`,`status`) values (1,'biezhi',NULL,0,0,'default/4.png','',1410944818,1410944818,1,1);

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `uid` int(10) NOT NULL,
  `nick_name` varchar(30) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  `github` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `instructions` text,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_userinfo` */

insert  into `t_userinfo`(`uid`,`nick_name`,`web_site`,`github`,`email`,`signature`,`instructions`) values (1,'王爵','https://biezhi.me',NULL,NULL,'这个人有点懒,还没写签名','...');

/*Table structure for table `t_userlog` */

DROP TABLE IF EXISTS `t_userlog`;

CREATE TABLE `t_userlog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `action` varchar(100) NOT NULL,
  `content` text,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_userlog` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
