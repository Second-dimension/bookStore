/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-06-04 22:32:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `n_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '公共告1', '11111111111', '2018-04-05 00:00:00');
INSERT INTO `notice` VALUES ('2', '公告2', '本周图书销售量再创新高', '2018-04-14 15:40:34');
INSERT INTO `notice` VALUES ('3', '公告3', '你知道我很想你吗？', '2018-04-20 15:42:13');
INSERT INTO `notice` VALUES ('4', null, '儿童袜无无无无无无无无拖无无无无无', '2018-04-14 15:43:34');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('59bb2f8b-0faa-4094-bf0c-cc2f868af12c', '1', '2');
INSERT INTO `orderitem` VALUES ('59bb2f8b-0faa-4094-bf0c-cc2f868af12c', '2', '2');
INSERT INTO `orderitem` VALUES ('9871b9c7-58af-49ca-8d5e-8fd70c6b6536', '7', '3');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `paystate` int(11) DEFAULT '0',
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('59bb2f8b-0faa-4094-bf0c-cc2f868af12c', '132', '1', '1', '1', '1', '2019-06-04 22:09:01', '8');
INSERT INTO `orders` VALUES ('9871b9c7-58af-49ca-8d5e-8fd70c6b6536', '84', '2', '2', '2', '1', '2019-06-04 22:10:01', '8');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', 'java web', '32', '计算机', '13', '/productImg/10/5/36ee63bc-c251-49ce-9b9a-b5e1e2e75ec0.jpg', '111111111111111111111111111111111111111111111111');
INSERT INTO `products` VALUES ('2', '时空穿行', '34', '科技', '14', '/productImg/11/4/d79dc124-de69-4b77-847e-bc461bfdb857.jpg', '222222222222222222222222222222222222222222222222222222');
INSERT INTO `products` VALUES ('3', '大勇和小花的欧洲日记', '27.5', '少儿', '17', '/productImg/12/1/986b5e98-ee73-4717-89fd-b6ac26a8dc2c.jpg', '大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记');
INSERT INTO `products` VALUES ('4', 'Java基础入门', '38', '计算机', '18', '/productImg/12/14/a1ace169-b53a-41c6-bdea-000e5946b2a5.png', 'Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', '19.5', '励志', '19', '/productImg/14/1/792116e7-6d83-4be4-b3e5-4dd11b0b4565.jpg', '别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜');
INSERT INTO `products` VALUES ('6', '中国国家地理', '23.8', '社科', '20', '/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '中国国家地理中国国家地理中国国家地理中国国家地理中国国家地理');
INSERT INTO `products` VALUES ('7', '学会宽容', '28', '励志', '18', '/productImg/6/5/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '学会宽容学会宽容学会宽容');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  `role` varchar(10) DEFAULT '普通用户',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', 'abc', '123456', '男', '123@123.com', '123456', '', '38ea92ab-26c2-4725-aa73-b5e899e9767a', '1', '普通用户', '2019-06-04 22:24:29');
