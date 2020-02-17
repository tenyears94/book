/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-04 22:18:58
*/
DROP DATABASE IF EXISTS `book`;
CREATE DATABASE `book`;
USE `book`;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `book_category`
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES ('1', '小说');
INSERT INTO `book_category` VALUES ('2', '历史');
INSERT INTO `book_category` VALUES ('3', '计算机');
INSERT INTO `book_category` VALUES ('4', '哲学');
INSERT INTO `book_category` VALUES ('5', '社会科学');
INSERT INTO `book_category` VALUES ('6', '政治法律');
INSERT INTO `book_category` VALUES ('7', '军事科学');
INSERT INTO `book_category` VALUES ('8', '中国文学');
INSERT INTO `book_category` VALUES ('9', '外国文学');
INSERT INTO `book_category` VALUES ('10', '外国传记');
INSERT INTO `book_category` VALUES ('11', '英语');
INSERT INTO `book_category` VALUES ('12', '俄国小说');
INSERT INTO `book_category` VALUES ('13', '心理学');
INSERT INTO `book_category` VALUES ('14', '言情小说');
INSERT INTO `book_category` VALUES ('15', '武侠小说');
INSERT INTO `book_category` VALUES ('16', '环境科学');
INSERT INTO `book_category` VALUES ('17', '纪实文学');


-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(20) DEFAULT NULL,
  `book_author` varchar(20) DEFAULT NULL,
  `book_publish` varchar(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `book_price` double DEFAULT NULL,
  `book_introduction` varchar(100) DEFAULT NULL,
  `book_num` int(11),
  `borrow_num` int(11) DEFAULT 0,
  PRIMARY KEY (`book_id`),
  KEY `FK_book_category` (`category_id`),
  CONSTRAINT `FK_book_category` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '巨人的陨落', '肯.福莱特', '江苏凤凰文艺出版社', '1', '129', '在第一次世界大战中发生的故事', 10, 0);
INSERT INTO `book` VALUES ('2', '三体', '刘慈欣', '南京大学出版社', '1', '68', '科幻小说', 10, 0);
INSERT INTO `book` VALUES ('3', '复活', '列夫.托尔斯泰', '上海译文出版社', '1', '19', '俄国小说', 10, 0);
INSERT INTO `book` VALUES ('6', '平凡的世界', '路遥', '上海文艺出版社', '1', '88', '孙少平和孙少安两兄弟...', 10, 0);
INSERT INTO `book` VALUES ('15', '白鹿原', '陈忠实', '南京出版社', '1', '36', '当代小说', 10, 0);
INSERT INTO `book` VALUES ('16', '计算机网络', '谢希仁', '电子工业出版社', '3', '49', '计算机专业书籍', 10, 1);
INSERT INTO `book` VALUES ('17', '霍乱时期的爱情', '加西亚·马尔克斯', '译林出版社', '9', '39', '外国小说', 10, 0);
INSERT INTO `book` VALUES ('18', '天才在左疯子在右', '高铭', '北京联合出版公司', '1', '39.8', '心理学', 10, 0);
INSERT INTO `book` VALUES ('19', '废都', '贾平凹', '商务印书馆', '8', '29', '当代小说', 10, 1);
INSERT INTO `book` VALUES ('20', 'jQuery', 'Ryan', '中国电力出版社', '3', '78', 'js库', 10, 1);



-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '信息工程学院');
INSERT INTO `dept` VALUES ('2', '体育学院');
INSERT INTO `dept` VALUES ('3', '美术学院');
INSERT INTO `dept` VALUES ('4', '电子工程学院');


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(20) DEFAULT NULL,
  `user_role` varchar(20) NOT NULL DEFAULT '普通用户',
  `user_email` varchar(30) DEFAULT NULL,
  `user_status` int NOT NULL DEFAULT 0,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_dept` (`dept_id`),
  CONSTRAINT `FK_user_dept` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '管理员', '742423357@qq.com', 0, 1);
INSERT INTO `user` VALUES ('3', 'yxc', '123456', '普通用户', 'for@163.com', 0, 2);
INSERT INTO `user` VALUES ('4', 'zhangfei', '123456', '普通用户', 'forzf@163.com', 0, 3);
INSERT INTO `user` VALUES ('5', '李四', '123456', '普通用户', null, 0, 4);
INSERT INTO `user` VALUES ('6', 'LeBronJames', '123456', '普通用户', '1111@qq.com', 0, 1);
INSERT INTO `user` VALUES ('7', '科比', null, '普通用户', null, 0, 2);
INSERT INTO `user` VALUES ('8', '柏拉图', null, '普通用户', null, 0, 2);
INSERT INTO `user` VALUES ('9', '拿破仑', null, '普通用户', null, 0, 1);
INSERT INTO `user` VALUES ('10', '欧文', null, '普通用户', null, 0, 1);
INSERT INTO `user` VALUES ('11', '库兹马', null, '普通用户', null, 0, 2);
INSERT INTO `user` VALUES ('12', '球哥', null, '普通用户', null, 0, 1);
INSERT INTO `user` VALUES ('13', '魔术师', null, '普通用户', null, 0, 4);
INSERT INTO `user` VALUES ('16', '周杰伦', null, '普通用户', null, 0, 3);

-- ----------------------------
-- Table structure for `borrowingbooks`
-- ----------------------------
DROP TABLE IF EXISTS `borrowing_books`;
CREATE TABLE `borrowing_books` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `borrow_status` int(11) NOT NULL,
  `borrow_date` datetime,
  `return_date` datetime,
  PRIMARY KEY (`borrow_id`),
  KEY `FK_user_id` (`user_id`),
  KEY `FK_book_id` (`book_id`),
  CONSTRAINT `FK_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowingbooks
-- ----------------------------
INSERT INTO `borrowing_books` VALUES ('3', '1', '3', 0, '2018-10-14', '2018-10-19');
INSERT INTO `borrowing_books` VALUES ('9', '5', '1', 0, '2018-10-16', '2018-10-19');
INSERT INTO `borrowing_books` VALUES ('24', '1', '6', 0, '2018-10-30', '2018-10-19');
INSERT INTO `borrowing_books` VALUES ('25', '1', '15', 0, '2018-10-31', '2018-10-19');
INSERT INTO `borrowing_books` VALUES ('26', '1', '2', 0, '2018-10-31', '2018-10-19');
INSERT INTO `borrowing_books` VALUES ('28', '5', '19', 1, '2018-11-03',null);
INSERT INTO `borrowing_books` VALUES ('29', '1', '16', 1, '2018-11-04',null);
INSERT INTO `borrowing_books` VALUES ('31', '3', '20', 2, '2018-11-04',null);

