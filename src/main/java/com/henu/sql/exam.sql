/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50638
Source Host           : 119.29.103.39:3306
Source Database       : examWeb

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-11-05 09:09:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ename` varchar(40) NOT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '考试类型',
  `eEnable` int(1) DEFAULT NULL COMMENT '是否能用，0:不可用，1:可用',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `eCreator` int(8) NOT NULL,
  `filename` varchar(255) DEFAULT NULL COMMENT '试卷文件名',
  PRIMARY KEY (`id`),
  KEY `creator` (`eCreator`),
  CONSTRAINT `creator` FOREIGN KEY (`eCreator`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
