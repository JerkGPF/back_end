/*
 Navicat Premium Data Transfer

 Source Server         : 114.117.0.103_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 114.117.0.103:3306
 Source Schema         : graduation

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 24/05/2021 16:07:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for companyinfo
-- ----------------------------
DROP TABLE IF EXISTS `companyinfo`;
CREATE TABLE `companyinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `free` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '福利待遇',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成立日期',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司简介',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for finds
-- ----------------------------
DROP TABLE IF EXISTS `finds`;
CREATE TABLE `finds`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `btitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bimage` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdtime` datetime NULL DEFAULT NULL,
  `isbanner` tinyint(1) NOT NULL COMMENT '是否为轮播图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for jobinfo
-- ----------------------------
DROP TABLE IF EXISTS `jobinfo`;
CREATE TABLE `jobinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int NULL DEFAULT NULL COMMENT '被查看的人数',
  `detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位详情',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作地址',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工资薪水',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作标题',
  `kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型（兼职，实习，全职）',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布公司id',
  `companyname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `createtime` datetime NOT NULL COMMENT '发布时间',
  `isdeleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `isHR` tinyint(1) NOT NULL COMMENT '是否HR，1TRUE，0FALSE',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `companyid` int NOT NULL COMMENT '发布者id',
  `collect` tinyint(1) NOT NULL COMMENT '是否收藏',
  `delivery` tinyint(1) NOT NULL COMMENT '是否投递',
  `userid` int NOT NULL COMMENT '投递者id',
  `jobid` int NOT NULL COMMENT '职位id',
  `isdelete` tinyint(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `intergal` int NOT NULL DEFAULT 0 COMMENT '积分数量',
  `create_time` date NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` date NOT NULL COMMENT '最后修改日期',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `qq` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qq',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出生日期',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '个人简介',
  `experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作经验',
  `nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简历',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
