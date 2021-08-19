/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.29 : Database - mall-wms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall-wms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `mall-wms`;

/*Table structure for table `wms_purchase` */

DROP TABLE IF EXISTS `wms_purchase`;

CREATE TABLE `wms_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '采购单id',
  `assignee_id` bigint(225) DEFAULT NULL COMMENT '采购人id',
  `assignee_name` varchar(225) DEFAULT NULL COMMENT '采购人名',
  `phone` varchar(225) DEFAULT NULL COMMENT '联系方式',
  `priority` int(225) DEFAULT NULL COMMENT '优先级',
  `status` int(20) DEFAULT NULL COMMENT '状态',
  `ware_id` bigint(225) DEFAULT NULL COMMENT '仓库id',
  `amount` bigint(225) DEFAULT NULL COMMENT '总金额',
  `create_time` date DEFAULT NULL COMMENT '创建日期',
  `update_time` date DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `wms_purchase` */

insert  into `wms_purchase`(`id`,`assignee_id`,`assignee_name`,`phone`,`priority`,`status`,`ware_id`,`amount`,`create_time`,`update_time`) values (2,2,'hhf','12345678912',2,3,NULL,NULL,NULL,'2020-05-26'),(3,1,'admin','13612345678',NULL,2,NULL,NULL,'2020-04-03','2020-04-03'),(4,1,'admin','13612345678',NULL,2,NULL,NULL,'2020-04-03','2020-05-26');

/*Table structure for table `wms_purchase_detail` */

DROP TABLE IF EXISTS `wms_purchase_detail`;

CREATE TABLE `wms_purchase_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint(225) DEFAULT NULL COMMENT '采购单id',
  `sku_id` bigint(20) DEFAULT NULL COMMENT '采购商品id',
  `sku_num` int(255) DEFAULT NULL COMMENT '采购数量',
  `sku_price` bigint(255) DEFAULT NULL COMMENT '采购金额',
  `ware_id` bigint(255) DEFAULT NULL COMMENT '仓库id',
  `status` int(40) DEFAULT NULL COMMENT '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `wms_purchase_detail` */

insert  into `wms_purchase_detail`(`id`,`purchase_id`,`sku_id`,`sku_num`,`sku_price`,`ware_id`,`status`) values (1,3,1,10,NULL,1,3),(2,3,2,10,NULL,1,3),(3,4,4,10,NULL,1,3),(4,4,3,15,NULL,1,3),(5,2,7,100,NULL,1,3),(6,NULL,14,100,NULL,1,0),(7,NULL,24,50,NULL,1,0);

/*Table structure for table `wms_ware_info` */

DROP TABLE IF EXISTS `wms_ware_info`;

CREATE TABLE `wms_ware_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '仓库名',
  `address` varchar(255) DEFAULT NULL COMMENT '仓库地址',
  `areacode` varchar(20) DEFAULT NULL COMMENT '区域编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='仓库信息';

/*Data for the table `wms_ware_info` */

insert  into `wms_ware_info`(`id`,`name`,`address`,`areacode`) values (1,'1号仓库','北京xx','124');

/*Table structure for table `wms_ware_order_task` */

DROP TABLE IF EXISTS `wms_ware_order_task`;

CREATE TABLE `wms_ware_order_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) DEFAULT NULL COMMENT 'order_id',
  `order_sn` varchar(255) DEFAULT NULL COMMENT 'order_sn',
  `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `consignee_tel` char(15) DEFAULT NULL COMMENT '收货人电话',
  `delivery_address` varchar(500) DEFAULT NULL COMMENT '配送地址',
  `order_comment` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `payment_way` tinyint(1) DEFAULT NULL COMMENT '付款方式【 1:在线付款 2:货到付款】',
  `task_status` tinyint(2) DEFAULT NULL COMMENT '任务状态',
  `order_body` varchar(255) DEFAULT NULL COMMENT '订单描述',
  `tracking_no` char(30) DEFAULT NULL COMMENT '物流单号',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `ware_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `task_comment` varchar(500) DEFAULT NULL COMMENT '工作单备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='库存工作单';

/*Data for the table `wms_ware_order_task` */

insert  into `wms_ware_order_task`(`id`,`order_id`,`order_sn`,`consignee`,`consignee_tel`,`delivery_address`,`order_comment`,`payment_way`,`task_status`,`order_body`,`tracking_no`,`create_time`,`ware_id`,`task_comment`) values (14,NULL,'202005281128598901265847512258867202',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,NULL,'202005292007327761266340397131550722',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,NULL,'202005292040219141266348656764702722',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,NULL,'202005292040452521266348754076749825',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `wms_ware_order_task_detail` */

DROP TABLE IF EXISTS `wms_ware_order_task_detail`;

CREATE TABLE `wms_ware_order_task_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) DEFAULT NULL COMMENT 'sku_id',
  `sku_name` varchar(255) DEFAULT NULL COMMENT 'sku_name',
  `sku_num` int(11) DEFAULT NULL COMMENT '购买个数',
  `task_id` bigint(20) DEFAULT NULL COMMENT '工作单id',
  `ware_id` bigint(20) DEFAULT NULL COMMENT '指定的仓库id',
  `lock_status` int(1) DEFAULT NULL COMMENT '锁定的状态（1:已锁定，2:解锁，3:代表库存已经扣减了）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='库存工作单';

/*Data for the table `wms_ware_order_task_detail` */

insert  into `wms_ware_order_task_detail`(`id`,`sku_id`,`sku_name`,`sku_num`,`task_id`,`ware_id`,`lock_status`) values (3,1,'',2,3,1,1),(4,1,'',3,4,1,1),(5,1,'',3,5,1,2),(6,1,'',3,6,1,2),(7,1,'',1,7,1,2),(8,1,'',2,8,1,2),(9,1,'',2,9,1,2),(10,1,'',4,10,1,2),(11,1,'',4,11,1,2),(12,1,'',4,12,1,2),(13,1,'',5,13,1,2),(14,1,'',1,14,1,2),(15,1,'',2,15,1,2),(16,1,'',1,16,1,2),(17,1,'',1,17,1,2);

/*Table structure for table `wms_ware_sku` */

DROP TABLE IF EXISTS `wms_ware_sku`;

CREATE TABLE `wms_ware_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) DEFAULT NULL COMMENT 'sku_id',
  `ware_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `stock` int(11) DEFAULT NULL COMMENT '库存数',
  `sku_name` varchar(200) DEFAULT NULL COMMENT 'sku_name',
  `stock_locked` int(11) DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='商品库存';

/*Data for the table `wms_ware_sku` */

insert  into `wms_ware_sku`(`id`,`sku_id`,`ware_id`,`stock`,`sku_name`,`stock_locked`) values (1,1,1,50,'华为',0),(2,2,1,510,'华为',0),(3,9,1,500,'Apple',0),(4,13,1,500,'Apple iPhone 11 (A2223) 红色 64GB',0),(5,16,1,250,'小米10',0),(6,24,1,254,'OPPO K5',0),(7,4,1,10,NULL,0),(8,3,1,15,NULL,0),(9,7,1,100,NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
