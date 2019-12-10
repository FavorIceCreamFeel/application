/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : webapp

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2019-12-10 17:46:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsId` int(11) NOT NULL COMMENT '商品编码',
  `goodsName` varchar(50) DEFAULT NULL COMMENT '商品名字',
  `goodsNum` int(11) DEFAULT '0' COMMENT '商品数量',
  `goodsMoney` varchar(50) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `goodsTypeId` int(11) DEFAULT NULL COMMENT '商品类型Id',
  `goodsDescription` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `goods` varchar(255) NOT NULL COMMENT '商品图片',
  `goodsStatus` tinyint(4) DEFAULT '0' COMMENT '商品状态0下架（默认）1上架',
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `goodsTypeId` int(11) NOT NULL,
  `goodsTypeName` varchar(50) NOT NULL,
  `goodsTypeTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`goodsTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderId` int(11) NOT NULL COMMENT '订单号',
  `goodsId` int(11) NOT NULL COMMENT '订单商品id',
  `goodsMoney` varchar(50) NOT NULL COMMENT '订单价格',
  `goodsNum` int(11) DEFAULT '1' COMMENT '订单数量',
  `orderTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `orderStatus` tinyint(4) DEFAULT '0' COMMENT '订单状态（0未支付，1申请支付中，2已经支付）',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `powerId` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `powerName` varchar(25) NOT NULL COMMENT '权限名字',
  `powerDescribe` varchar(50) DEFAULT NULL COMMENT '权限描述',
  `powerSign` varchar(50) NOT NULL COMMENT '权限字段',
  PRIMARY KEY (`powerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(50) NOT NULL COMMENT '角色名字',
  `roleDescribe` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `roleStatus` tinyint(4) DEFAULT '0' COMMENT '角色状态',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `powerId` int(11) NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_power
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(12) DEFAULT NULL COMMENT '用户名',
  `userPwd` varchar(255) DEFAULT NULL,
  `userSex` varchar(2) DEFAULT NULL COMMENT '用户性别',
  `userAge` int(11) unsigned DEFAULT '0' COMMENT '用户年龄',
  `phoneNumber` int(11) NOT NULL COMMENT '联系方式（用作用户唯一id）主键,登陆时用的账户',
  `address` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `createTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for userid_roleid
-- ----------------------------
DROP TABLE IF EXISTS `userid_roleid`;
CREATE TABLE `userid_roleid` (
  `userId` int(11) NOT NULL COMMENT '用户id（即用户的手机号）',
  `roleId` int(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userid_roleid
-- ----------------------------
