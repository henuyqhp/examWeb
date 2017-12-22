/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50638
Source Host           : 119.29.103.39:3306
Source Database       : examWeb

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-11-05 09:10:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `sno` int(10) NOT NULL COMMENT '学号',
  `sname` varchar(50) NOT NULL,
  `spass` varchar(50) NOT NULL,
  `ip` varchar(50) NOT NULL COMMENT '学生固定IP',
  `enable` int(1) DEFAULT NULL COMMENT '是否能用，0:不可用，1:可用',
  `classNum` varchar(50) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sno_U` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
