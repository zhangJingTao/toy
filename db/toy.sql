/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : toy

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-28 10:12:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qzone_post
-- ----------------------------
DROP TABLE IF EXISTS `qzone_post`;
CREATE TABLE `qzone_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qq` varchar(255) DEFAULT NULL,
  `content` varchar(512) DEFAULT NULL,
  `post_time` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qzone_post
-- ----------------------------

-- ----------------------------
-- Table structure for qzone_reply
-- ----------------------------
DROP TABLE IF EXISTS `qzone_reply`;
CREATE TABLE `qzone_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reply_content` varchar(1024) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `reply_time` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qzone_reply
-- ----------------------------
