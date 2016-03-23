/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost
 Source Database       : java-china

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : utf-8

 Date: 03/23/2016 23:27:44 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_activecode`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_activecode`
-- ----------------------------
BEGIN;
INSERT INTO `t_activecode` VALUES ('3', '13', 'u0ZWmxmZwoIV1EdtjOVxYVSXEFqrBz9f', 'signup', '1', '1458577101', '1458573501'), ('5', '13', 'KF8ESHQdXxNDr1s8K4yMtfZzp5zjLeXR', 'forgot', '0', '1458660613', '1458657013'), ('6', '13', 'U0dXQqVDMZOjpcRI10bztWJ5OZao1GqL', 'forgot', '0', '1458660782', '1458657182');
COMMIT;

-- ----------------------------
--  Table structure for `t_comment`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `t_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT 'topic:帖子 node:节点',
  `uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_link`
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_node`
-- ----------------------------
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

-- ----------------------------
--  Records of `t_node`
-- ----------------------------
BEGIN;
INSERT INTO `t_node` VALUES ('1', '0', '站务管理', '站务管理', 'affair', null, '3', '1457933734', '0'), ('2', '1', '程序发布', '', 'release', null, '1', '1457933734', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_settings`
-- ----------------------------
DROP TABLE IF EXISTS `t_settings`;
CREATE TABLE `t_settings` (
  `skey` varchar(50) NOT NULL,
  `svalue` text,
  PRIMARY KEY (`skey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_topic`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_topic`
-- ----------------------------
BEGIN;
INSERT INTO `t_topic` VALUES ('1', '1', '2', 'Hello Java China!', 'Java China的第一个帖子！', '12', '0', '0', '0', '0', '1457933734', '1457933734', '1'), ('2', '13', '1', '感觉自己是一个很 low 的程序员', '转行做的程序员，两三年经验 \r\n主要语言 python,熟悉 scrapy 和 django \r\nlinux,css,js,jquery,mysql,nosql,java 也会一些 \r\n\r\n发现自己是面向 google 编程，面向 stackoverflow 编程，面向文档编程 \r\n遇到的问题基本都能解决 \r\n但 flask,django 等源码看不懂或者很吃力 \r\nIT 界的知识太多太广，越学越感觉自己很渺小很 low 啊', '5', '0', '0', '0', '0', '1458740979', '1458740979', '1'), ('3', '13', '1', '小米……好吧，果然是耍猴大王……', '本来对小米的厌恶不下于对阿里家的厌恶。\r\n\r\n最近看做的手机，貌似颜值还行，而且，迭了那么多代，应该系统也能用了。 对它的品牌感知逐渐好转。\r\n\r\n但是不爽的是，刚想买个小米平板 Win10 版写代码时，却发现，真会耍猴啊。需要说明的是，小米平板早就发布了，此外，平板毕竟不像手机需求旺盛。你说你去年发布的平板，现在官网还买不到，不就是玩期货、玩耍猴吗？加 2~3 百才能买一台，感觉构成了实质意义上的欺骗消费者了。（换种方式，你以为我看不出来？）\r\n\r\n**本来你心动了不是？买不到！想买？加价买~~**\r\n\r\n雾里探花啊~\r\n', '3', '0', '0', '0', '0', '1458744255', '1458744255', '1'), ('4', '13', '1', 'AppleDNS 再次更新，超越 V2EX DNS 的 Apple 网络加速神器', '全新的 AppleDNS ，大快所有人心的好项目。真的快，快出声！\n\n[https://github.com/gongjianhui/AppleDNS](https://github.com/gongjianhui/AppleDNS)\n\n**AppleDNS 是一套针对 Apple 非隐私类网络服务的加速配置文件，目前主要支持 (Mac) App Store 和 Apple Music 的加速，可以配置在一切可以实现 Hosts 功能的设备上。（包括 Surge ）**\n\n今日凌晨更新的 AppleDNS 针对 Apple Music 冷门音乐加载问题给出了解决方案，适用于所有国际出口尚可的用户。\n\n**请注意， AppleDNS 中设置的域名不应在任何代理软件的黑名单中出现。**\n\n\n\n以下是不要脸的求打赏地址\n\n**支付裱**: i@gongjianhui.com **BTC**: [1Jianhui1ZUDHDCz1TGzGH2rWaxas1GS9S](https://blockchain.info/address/1Jianhui1ZUDHDCz1TGzGH2rWaxas1GS9S)', '17', '0', '0', '0', '0', '1458744661', '1458744661', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pass_word` varchar(32) DEFAULT NULL,
  `following` int(10) DEFAULT '0' COMMENT '我关注的人数',
  `notices` int(10) DEFAULT '0' COMMENT '未读通知数',
  `topics` int(10) NOT NULL,
  `comments` int(10) NOT NULL DEFAULT '0',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '电子邮箱',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后一次操作时间',
  `role_id` tinyint(2) DEFAULT '5' COMMENT '5:普通用户 2:管理员 1:系统管理员',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:待激活 1:正常 2：删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'biezhi', '26b2010cd2028d742b283da54a304584', '0', '0', '0', '0', 'default/4.png', '', '1410944818', '1410944818', '1', '1'), ('13', 'wangjue', '880d2c4f88de0c111e4a14a2e93cbccf', '0', '0', '3', '0', 'default/2.png', '921293209@qq.com', '1458573501', '1458573501', '5', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_userinfo`
-- ----------------------------
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

-- ----------------------------
--  Records of `t_userinfo`
-- ----------------------------
BEGIN;
INSERT INTO `t_userinfo` VALUES ('1', '王爵', 'https://biezhi.me', null, null, '这个人有点懒,还没写签名', '...');
COMMIT;

-- ----------------------------
--  Table structure for `t_userlog`
-- ----------------------------
DROP TABLE IF EXISTS `t_userlog`;
CREATE TABLE `t_userlog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `action` varchar(100) NOT NULL,
  `content` text,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
