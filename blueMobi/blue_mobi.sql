/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.73 : Database - blue_mobi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blue_mobi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blue_mobi`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(管理员)',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `qrCodeSrc` varchar(1000) DEFAULT NULL COMMENT '二维码链接',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `create_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `login_date` varchar(50) DEFAULT NULL COMMENT '登录时间',
  `group_id` int(11) DEFAULT NULL COMMENT '所属用户组',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `state` smallint(6) DEFAULT NULL COMMENT '用户状态',
  `extend1` varchar(500) DEFAULT NULL,
  `extend2` varchar(500) DEFAULT NULL,
  `extend3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`account`,`qrCodeSrc`,`password`,`name`,`create_date`,`login_date`,`group_id`,`phone`,`state`,`extend1`,`extend2`,`extend3`) values (2,'lisi12333',NULL,'111111333','李四','2015-05-20 21:37:03',NULL,2,'15021336805',NULL,NULL,NULL,NULL),(3,'zhangsan','http://10.58.175.208:8080/xhl/upload/18.png','111111','张三','2015-05-20 21:38:13',NULL,3,NULL,NULL,NULL,NULL,NULL),(7,'tianqi',NULL,'111111','天气','2015-06-05 10:08:23',NULL,4,NULL,NULL,NULL,NULL,NULL),(16,'adsf',NULL,'adsf','asdf','2015-07-01 17:37:48',NULL,13,NULL,NULL,NULL,NULL,NULL),(26,'acc','http://10.58.174.61:8080/xhl/upload/26.png','111111','恩恩','2015-07-02 13:44:18',NULL,1,NULL,NULL,NULL,NULL,NULL),(27,'1','http://10.58.174.61:8080/xhl/upload/27.png','1','二维码统计','2015-07-02 13:49:19',NULL,1,NULL,NULL,NULL,NULL,NULL),(28,'fsaf','http://43.254.54.251:8083/xhl/upload/28.png','123456','dsagdsg','2015-07-03 10:03:14',NULL,1,NULL,NULL,NULL,NULL,NULL),(29,'admin',NULL,'111111','admin','2015-07-30 13:38:09',NULL,1,NULL,NULL,NULL,NULL,NULL),(30,'sdsdf',NULL,'sdfsfsd','sdfsdf','2015-07-30 14:06:07',NULL,2,NULL,NULL,NULL,NULL,NULL),(31,'sdfsdf',NULL,'aaa','fsdfsd','2015-07-30 14:06:19',NULL,3,NULL,NULL,NULL,NULL,NULL),(32,'dsfsdfsdf2222',NULL,'sss','sdfsdf111','2015-07-30 14:06:32',NULL,1,NULL,NULL,NULL,NULL,NULL),(33,'aaa',NULL,'aaa','aaa','2015-08-17 13:51:34',NULL,1,NULL,NULL,NULL,NULL,NULL),(34,'bbb',NULL,'aaa','bbb','2015-08-17 13:57:49',NULL,1,'15021336805',NULL,NULL,NULL,NULL);

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(权限)',
  `module` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属父类模块id',
  `url` varchar(500) DEFAULT NULL COMMENT '对应的链接跳转地址',
  `extend1` varchar(500) DEFAULT NULL,
  `extend2` varchar(500) DEFAULT NULL,
  `extend3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `authority` */

insert  into `authority`(`id`,`module`,`parent_id`,`url`,`extend1`,`extend2`,`extend3`) values (1,'系统管理',0,NULL,NULL,NULL,NULL),(10,'权限列表',1,'admin/findAuthorityList.htm',NULL,NULL,NULL),(11,'预设列表',1,'admin/findRoleSetPage.htm',NULL,NULL,NULL);

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(用户组)',
  `group_name` varchar(255) DEFAULT NULL COMMENT '用户组名称',
  `create_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_date` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(500) DEFAULT NULL,
  `extend2` varchar(500) DEFAULT NULL,
  `extend3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `group` */

insert  into `group`(`id`,`group_name`,`create_date`,`update_date`,`extend1`,`extend2`,`extend3`) values (1,'管理员','2015-05-15 15:06:59','2015-07-02 18:01:36',NULL,NULL,NULL),(2,'客服','2015-05-20 21:35:45','2015-07-09 11:26:55',NULL,NULL,NULL),(3,'销售员','2015-05-20 21:36:09','2015-05-20 21:36:36',NULL,NULL,NULL),(4,'推广员','2015-05-20 21:36:36','2015-05-20 21:36:36',NULL,NULL,NULL),(7,'预设测试','2015-06-30 20:14:17','2015-06-30 20:14:17',NULL,NULL,NULL),(8,'辅助管理员','2015-06-30 20:17:05','2015-07-01 10:09:08',NULL,NULL,NULL),(10,'12','2015-06-30 20:18:02','2015-06-30 20:18:02',NULL,NULL,NULL),(11,'13','2015-06-30 20:19:05','2015-06-30 20:19:05',NULL,NULL,NULL),(12,'预设测试','2015-07-01 09:59:04','2015-07-01 09:59:04',NULL,NULL,NULL),(13,'销售员','2015-07-01 10:02:18','2015-07-01 10:02:18',NULL,NULL,NULL),(16,'销售员001','2015-07-01 10:09:36','2015-07-01 10:09:36',NULL,NULL,NULL),(18,'ts','2015-08-13 10:35:54','2015-08-13 10:44:36',NULL,NULL,NULL);

/*Table structure for table `group_auth` */

DROP TABLE IF EXISTS `group_auth`;

CREATE TABLE `group_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_id` int(11) DEFAULT '0' COMMENT '模块id',
  `group_id` int(11) DEFAULT '0' COMMENT '分组id',
  `extend1` varchar(500) DEFAULT NULL,
  `extend2` varchar(500) DEFAULT NULL,
  `extend3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `group_auth` */

insert  into `group_auth`(`id`,`auth_id`,`group_id`,`extend1`,`extend2`,`extend3`) values (1,1,1,NULL,NULL,NULL),(2,10,1,NULL,NULL,NULL),(3,11,1,NULL,NULL,NULL),(4,26,1,NULL,NULL,NULL),(11,25,1,NULL,NULL,NULL),(15,10,18,NULL,NULL,NULL),(16,25,18,NULL,NULL,NULL),(17,1,18,NULL,NULL,NULL);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键(会员)',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` smallint(2) DEFAULT '2' COMMENT '性别(0:女,1:男,2:保密)',
  `status` int(2) DEFAULT '1' COMMENT '0:禁用 ,1:正常',
  `isOnline` int(2) DEFAULT '0' COMMENT '0：不在线 1：在线',
  `reg_date` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `third_id` varchar(100) DEFAULT NULL COMMENT '第三方账号唯一标识',
  `third_account` varchar(100) DEFAULT NULL COMMENT '第三方登录账号名',
  `login_type` int(11) DEFAULT '0' COMMENT '登录类型（0：本地  1：QQ 2:微信 ）',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录日期',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式(即手机号)',
  `head_img` varchar(500) DEFAULT NULL COMMENT '个人头像',
  `now_address` varchar(255) DEFAULT NULL COMMENT '现居地(部分详情)',
  `extend1` varchar(500) DEFAULT NULL,
  `extend2` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`id`,`account`,`password`,`name`,`sex`,`status`,`isOnline`,`reg_date`,`third_id`,`third_account`,`login_type`,`last_login_date`,`contact`,`head_img`,`now_address`,`extend1`,`extend2`) values (1,'18922222222','123456','luan',1,1,1,'2015-05-20 22:26:08',NULL,NULL,0,'2015-07-09 10:07:09','15971470520','http://10.58.175.208:8888/xhl/upload/201506261143043087.jpg','22222','',NULL),(2,'18723563652','111111','windLEE',0,1,1,'2015-05-20 22:27:40',NULL,NULL,0,'2015-05-20 22:27:40','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(3,'happy3','111111','会员3',0,0,1,'2015-05-20 22:30:04',NULL,NULL,0,'2015-05-20 22:30:04','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(4,'happy4','111111','会员4',0,1,1,'2015-05-20 22:30:04',NULL,NULL,0,'2015-05-20 22:30:04','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(5,'happy5','111111','会员5',0,1,1,'2015-05-20 22:30:05',NULL,NULL,0,'2015-05-20 22:30:05','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(6,'happy6','111111','会员6',0,1,1,'2015-05-20 22:30:05',NULL,NULL,0,'2015-05-20 22:30:05','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(7,'happy7','111111','会员7',0,1,1,'2015-05-20 22:30:05',NULL,NULL,0,'2015-05-20 22:30:05','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(8,'happy8','111111','会员8',0,1,1,'2015-05-20 22:30:05',NULL,NULL,0,'2015-05-20 22:30:05','13886762345','photo2','洪山区卓刀泉101',NULL,NULL),(9,'15002722305','111111','',0,1,1,'2015-05-20 22:30:05',NULL,NULL,0,'2015-06-09 19:07:56','13886762345','photo2','洪山区卓刀泉101','',NULL),(10,'13545621235','111111','汪雷',1,1,1,'2015-05-20 22:30:05',NULL,'null',1,'2015-06-09 20:27:05','15971470520','http://10.58.174.231:8888/xhl/upload/201506041546211153.jpeg','湖北武汉','',NULL),(11,'18911111111','111111','栾中三',1,0,1,'2015-05-20 22:30:06',NULL,NULL,0,'2015-06-15 18:20:12','13525632365','photo2','湖北省武汉市','',NULL),(12,'13886762345','111111','账户test',1,1,1,'2015-05-28 13:47:48',NULL,NULL,0,'2015-06-01 13:13:49','13886762345',NULL,NULL,'',NULL),(14,'13163266530','123456','云',0,1,1,NULL,NULL,NULL,0,'2015-07-06 15:20:38','13163266530','http://10.58.174.231:8888/xhl/upload/201506121556545173.jpeg','湖北省武汉市湖北省武汉市','',NULL),(15,'13163266531','111111',NULL,2,1,1,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(16,'15002722374','111111',NULL,NULL,1,1,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(17,'13163266533','111111','test',2,1,1,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(18,'13163266534','111111','test',2,1,1,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(19,'13545621568','111111',NULL,2,1,1,'2015-06-04 19:02:46',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(20,'15002722385','123456','悠xiao扬',1,1,1,'2015-06-04 19:08:14',NULL,NULL,0,'2015-07-08 00:32:46',NULL,NULL,NULL,'',NULL),(21,'18627010091','111111','Happy',1,1,1,'2015-06-05 10:23:58',NULL,NULL,0,'2015-06-05 10:26:13','18627010092',NULL,NULL,NULL,NULL),(22,NULL,NULL,NULL,2,1,1,'2015-06-05 11:38:25','4565566542','玉帝1',1,NULL,NULL,'素颜美照1',NULL,NULL,NULL),(24,NULL,NULL,NULL,2,1,1,'2015-06-05 11:39:49','4565566544','玉帝',1,NULL,NULL,'素颜美照',NULL,NULL,NULL),(25,'15689567895','孙悟空来打工',NULL,2,1,1,'2015-06-05 14:04:52',NULL,NULL,0,NULL,'15689567895',NULL,NULL,NULL,NULL),(26,NULL,NULL,NULL,2,1,1,'2015-06-05 14:13:25','45655222222222','玉帝2',2,NULL,NULL,'素颜美照2',NULL,NULL,NULL),(33,'15971470520','111111','bbbb',1,1,1,'2015-06-11 13:16:53',NULL,NULL,0,'2015-07-08 10:43:06','15971470520','http://10.58.175.208:8080/xhl/upload/201506261355277097.jpeg','湖北省武汉市','',NULL),(35,'13349855935','123456',NULL,2,1,1,'2015-06-16 10:55:08',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(36,'18627010092','123456',NULL,2,1,1,'2015-06-16 10:56:46',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(38,'18971506420','123456',NULL,2,1,1,'2015-06-16 11:13:54',NULL,NULL,0,'2015-06-26 11:25:50',NULL,NULL,NULL,'',NULL),(39,'QQ登录',NULL,NULL,2,1,1,'2015-07-03 14:25:39','1','      ',1,NULL,NULL,NULL,NULL,NULL,NULL),(40,'18773388574','888888',NULL,2,1,1,'2015-07-08 17:19:52',NULL,NULL,0,'2015-07-08 20:16:25',NULL,NULL,NULL,NULL,NULL),(41,'18674799222','chji810427',NULL,2,1,1,'2015-07-08 17:26:25',NULL,NULL,0,'2015-07-08 17:28:40',NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
