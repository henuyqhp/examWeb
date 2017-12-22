#  **examWeb** 


**本文档的各类文件版本均从1.0开始，每次修改时，版本号加0.1.**


## 项目相关的帐号密码
1. 数据库
    - 主机名:119.29.103.39
    - 端口:3306
    - 帐号:root
    - 密码:123456
2. ftp
    - ip:119.29.103.39
    - user:sui
    - password:1311150301
    - fileUrl:ftp://119.29.103.39/pub/img/



## 数据库设计 1.1(第二次修改)
**教师表 teacher**
```
/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50638
Source Host           : 119.29.103.39:3306
Source Database       : examWeb

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-10-29 11:18:12
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

```

**学生表 student**
```
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


```

**考试表 exam**
```
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

```


**考试提交日志 log_exam_submit**
```
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


```
**绑定表**
```$xslt
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for binding
-- ----------------------------
DROP TABLE IF EXISTS `binding`;
CREATE TABLE `binding` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sid` int(10) NOT NULL,
  `eid` int(10) NOT NULL,
  `ip` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

```