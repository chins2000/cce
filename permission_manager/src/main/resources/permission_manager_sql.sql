/*
Navicat MySQL Data Transfer

Source Server         : myconn
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : permission_manager

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-12-19 09:09:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pm_menu`
-- ----------------------------
DROP TABLE IF EXISTS `pm_menu`;
CREATE TABLE `pm_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pid` int(11) NOT NULL,
  `pm_permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pm_menu_fka` (`pm_permission_id`),
  CONSTRAINT `pm_menu_fka` FOREIGN KEY (`pm_permission_id`) REFERENCES `pm_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_menu
-- ----------------------------
INSERT INTO `pm_menu` VALUES ('1', '/user/list', 'user', '0', '1');
INSERT INTO `pm_menu` VALUES ('2', '/role/list', 'role', '0', '2');
INSERT INTO `pm_menu` VALUES ('3', '/permission/list', 'permission', '0', '3');
INSERT INTO `pm_menu` VALUES ('4', '/menu/list', 'menu', '0', '4');

-- ----------------------------
-- Table structure for `pm_permission`
-- ----------------------------
DROP TABLE IF EXISTS `pm_permission`;
CREATE TABLE `pm_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pm_permission_a` (`name`,`group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_permission
-- ----------------------------
INSERT INTO `pm_permission` VALUES ('1', 'a', 'a');
INSERT INTO `pm_permission` VALUES ('2', 'b', 'a');
INSERT INTO `pm_permission` VALUES ('3', 'c', 'c');
INSERT INTO `pm_permission` VALUES ('4', 'd', 'd');
INSERT INTO `pm_permission` VALUES ('5', 'sdfsdf', 'sdfsdf');

-- ----------------------------
-- Table structure for `pm_role`
-- ----------------------------
DROP TABLE IF EXISTS `pm_role`;
CREATE TABLE `pm_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pm_role_a` (`name`,`group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_role
-- ----------------------------
INSERT INTO `pm_role` VALUES ('1', 'a', 'a');
INSERT INTO `pm_role` VALUES ('2', 'b', 'b');
INSERT INTO `pm_role` VALUES ('9', 'c', 'c');
INSERT INTO `pm_role` VALUES ('10', 'df', 'dfg');
INSERT INTO `pm_role` VALUES ('11', 'dfg', 'retret');

-- ----------------------------
-- Table structure for `pm_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `pm_role_permission`;
CREATE TABLE `pm_role_permission` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `visit` tinyint(1) DEFAULT NULL,
  `add` tinyint(1) DEFAULT NULL,
  `update` tinyint(1) DEFAULT NULL,
  `delete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `b` (`role_id`),
  CONSTRAINT `a` FOREIGN KEY (`permission_id`) REFERENCES `pm_permission` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `b` FOREIGN KEY (`role_id`) REFERENCES `pm_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_role_permission
-- ----------------------------
INSERT INTO `pm_role_permission` VALUES ('1', '1', '1', '1', '1', '1');
INSERT INTO `pm_role_permission` VALUES ('1', '2', '1', '0', '0', '1');
INSERT INTO `pm_role_permission` VALUES ('1', '9', '1', '1', '0', '1');
INSERT INTO `pm_role_permission` VALUES ('1', '10', '0', '0', '0', '0');
INSERT INTO `pm_role_permission` VALUES ('2', '1', '1', '1', '1', '1');
INSERT INTO `pm_role_permission` VALUES ('2', '11', '0', '0', '0', '0');
INSERT INTO `pm_role_permission` VALUES ('3', '1', '1', '1', '1', '1');
INSERT INTO `pm_role_permission` VALUES ('4', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_user_a` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'a', 'a');
INSERT INTO `sys_user` VALUES ('2', 'c', 'c');
INSERT INTO `sys_user` VALUES ('3', 'test', 'test');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `d` (`role_id`),
  CONSTRAINT `c` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `d` FOREIGN KEY (`role_id`) REFERENCES `pm_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1');
INSERT INTO `sys_user_role` VALUES ('3', '1');
INSERT INTO `sys_user_role` VALUES ('3', '2');
