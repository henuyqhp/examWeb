/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50638
Source Host           : 119.29.103.39:3306
Source Database       : examWeb

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-11-05 09:10:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log_exam_submit
-- ----------------------------
DROP TABLE IF EXISTS `log_exam_submit`;
CREATE TABLE `log_exam_submit` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `eid` int(10) NOT NULL COMMENT '考试主键',
  `sid` int(10) NOT NULL COMMENT '班级',
  `subtime` datetime NOT NULL COMMENT '提交时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `student_id` (`sid`),
  KEY `exam_id` (`eid`),
  CONSTRAINT `exam_id` FOREIGN KEY (`eid`) REFERENCES `exam` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
