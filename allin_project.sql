/*
Navicat MySQL Data Transfer

Source Server         : connectyon
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : allin_project

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2017-10-23 09:47:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for allin_team_leader1
-- ----------------------------
DROP TABLE IF EXISTS `allin_team_leader1`;
CREATE TABLE `allin_team_leader1` (
  `leaderid` int(255) NOT NULL,
  `leadername` varchar(200) character set utf8 NOT NULL,
  `leaderphone` int(200) NOT NULL,
  `leadergender` varchar(200) character set utf8 NOT NULL,
  `leaderrole` varchar(200) character set utf8 NOT NULL,
  `leadergameid` varchar(200) character set utf8 NOT NULL,
  `leaderidcard` varchar(200) character set utf8 NOT NULL,
  PRIMARY KEY  (`leaderid`,`leaderphone`,`leaderidcard`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of allin_team_leader1
-- ----------------------------

-- ----------------------------
-- Table structure for allin_team_member1
-- ----------------------------
DROP TABLE IF EXISTS `allin_team_member1`;
CREATE TABLE `allin_team_member1` (
  `memberid` int(255) NOT NULL auto_increment,
  `memberphone` varchar(255) NOT NULL,
  `membername` varchar(255) default NULL,
  `membergameid` varchar(255) default NULL,
  `memberduty` varchar(255) default NULL COMMENT '队员或教练或经理或队长',
  `teamid` int(255) default NULL,
  `teamname` varchar(255) default NULL,
  `membergender` varchar(255) default NULL,
  `memberidcard` varchar(255) default NULL,
  `memberrole` varchar(255) default NULL COMMENT '游戏角色所在服务器（例：艾欧尼亚）',
  PRIMARY KEY  (`memberid`,`memberphone`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allin_team_member1
-- ----------------------------
INSERT INTO `allin_team_member1` VALUES ('1', '15557812315', '战三', null, null, '1', null, null, null, null);
INSERT INTO `allin_team_member1` VALUES ('2', '15678932156', '李斯', null, null, '1', null, null, null, null);
INSERT INTO `allin_team_member1` VALUES ('3', '14653246123', '王武', null, null, '2', null, null, null, null);
INSERT INTO `allin_team_member1` VALUES ('4', '17894564553', '赵柳', null, null, '1', null, null, null, null);
INSERT INTO `allin_team_member1` VALUES ('5', '18956456131', '孙琦', null, null, '1', null, null, null, null);
INSERT INTO `allin_team_member1` VALUES ('6', '15873253623', '舟八', null, null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for allin_team_table1
-- ----------------------------
DROP TABLE IF EXISTS `allin_team_table1`;
CREATE TABLE `allin_team_table1` (
  `teamid` int(255) NOT NULL auto_increment,
  `leadername` varchar(20) character set utf8 NOT NULL,
  `teamname` varchar(10) character set utf8 NOT NULL,
  `teampoints` varchar(255) default '0',
  `leaderphone` varchar(20) character set utf8 NOT NULL,
  `coachname` varchar(20) character set utf8 default NULL,
  PRIMARY KEY  (`teamid`,`leaderphone`,`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of allin_team_table1
-- ----------------------------

-- ----------------------------
-- Table structure for allin_user
-- ----------------------------
DROP TABLE IF EXISTS `allin_user`;
CREATE TABLE `allin_user` (
  `userid` int(11) NOT NULL auto_increment COMMENT '用户无需填写',
  `userphone` varchar(22) character set utf8 NOT NULL,
  `username` varchar(20) character set utf8 default NULL COMMENT '用户的真实姓名',
  `gender` varchar(10) character set utf8 default NULL,
  `situation` int(1) default '0' COMMENT '参赛情况 0未参赛 1参赛',
  `usergameid` varchar(255) character set utf8 default NULL,
  `useridcard` varchar(22) character set utf8 default NULL,
  `userrole` varchar(20) character set utf8 default NULL COMMENT '角色所在大区',
  `teamname` varchar(10) character set utf8 default NULL COMMENT '战队名',
  `password` varchar(200) character set utf8 NOT NULL COMMENT '密码',
  PRIMARY KEY  (`userid`,`userphone`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of allin_user
-- ----------------------------
INSERT INTO `allin_user` VALUES ('1', '15757180521', '乔乔', '女', '1', '七夜小雨', '54612312132123112', '赌东道', null, '123');
INSERT INTO `allin_user` VALUES ('2', '15757180511', 'gay哥', '男', '0', '社会gay', '32131231231232321', '黑蛇', null, '1205591144a');
INSERT INTO `allin_user` VALUES ('3', '15757180551', 'hzmrzhou', '男', '0', '复仇v', '330106194401080316', '黑莓', null, '12332131a');
INSERT INTO `allin_user` VALUES ('13', '15757180523', '撒旦', '男', '0', '潇洒王子', '330106199401080816', '黑莓', null, '123123a');
INSERT INTO `allin_user` VALUES ('16', '123123123123', null, null, '0', null, null, null, null, '123123a');
INSERT INTO `allin_user` VALUES ('17', '312321321312', null, null, '0', null, null, null, null, '123123');
INSERT INTO `allin_user` VALUES ('23', '15757180222', null, null, '0', null, null, null, null, '45355465465');
INSERT INTO `allin_user` VALUES ('24', '12321321123', null, null, '0', null, null, null, null, '123saasaqsqw');
INSERT INTO `allin_user` VALUES ('25', '987766545645', null, null, '0', null, null, null, null, '123a');
INSERT INTO `allin_user` VALUES ('26', '157571444423', null, null, '0', null, null, null, null, '123a');
INSERT INTO `allin_user` VALUES ('27', '99999999999', null, null, '0', null, null, null, null, '1205555aa');
INSERT INTO `allin_user` VALUES ('28', '32321123', null, null, '0', null, null, null, null, '231321321');
INSERT INTO `allin_user` VALUES ('30', '15757180515', null, null, '0', null, null, null, null, '312321321aaaa');
INSERT INTO `allin_user` VALUES ('31', '12312333333', null, null, '0', null, null, null, null, '123');
INSERT INTO `allin_user` VALUES ('32', '12312332222', null, null, '0', null, null, null, null, '123');
INSERT INTO `allin_user` VALUES ('33', '55555555', null, null, '0', null, null, null, null, '123213');
INSERT INTO `allin_user` VALUES ('34', '55555555444', null, null, '0', null, null, null, null, '1232133');
INSERT INTO `allin_user` VALUES ('36', '15757180526', null, null, '0', null, null, null, null, '120559164a');
INSERT INTO `allin_user` VALUES ('37', '15757180528', null, null, '0', null, null, null, null, '1000231346aasa');
INSERT INTO `allin_user` VALUES ('38', '123123123123', null, null, '0', null, null, null, null, '123123a');
