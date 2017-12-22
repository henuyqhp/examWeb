/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50638
Source Host           : 119.29.103.39:3306
Source Database       : examWeb

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-11-05 09:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(10) NOT NULL,
  `tno` int(10) NOT NULL,
  `tname` varchar(50) NOT NULL,
  `tpass` varchar(50) NOT NULL,
  `enable` int(1) DEFAULT NULL COMMENT '是否能用，0:不可用，1:可用',
  `tadmin` int(1) DEFAULT NULL COMMENT '是否具有管理员权限，0:没有 ，1:有',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tname_U` (`tname`) USING BTREE,
  UNIQUE KEY `tno_U` (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
