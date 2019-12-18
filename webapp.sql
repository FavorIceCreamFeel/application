/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : webapp

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2019-12-18 23:48:50
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
  `goodsMoney` decimal(50,0) NOT NULL DEFAULT '0' COMMENT '商品价格',
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
  `goodsTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsTypeName` varchar(50) NOT NULL,
  `typeId` int(11) DEFAULT NULL,
  `goodsTypeTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`goodsTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '纸尿裤', '1', null);
INSERT INTO `goodstype` VALUES ('2', '婴儿车', '1', null);
INSERT INTO `goodstype` VALUES ('3', '婴儿床', '1', null);
INSERT INTO `goodstype` VALUES ('4', '儿童玩具', '2', null);
INSERT INTO `goodstype` VALUES ('5', '儿童书籍', '2', null);
INSERT INTO `goodstype` VALUES ('6', '儿童车', '2', null);
INSERT INTO `goodstype` VALUES ('7', '童装', '3', null);
INSERT INTO `goodstype` VALUES ('8', '童鞋', '3', null);
INSERT INTO `goodstype` VALUES ('9', '儿童配饰', '3', null);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL COMMENT '订单号',
  `goodsId` int(11) NOT NULL COMMENT '订单商品id',
  `goodsMoney` varchar(50) NOT NULL COMMENT '订单价格',
  `goodsNum` int(11) DEFAULT '1' COMMENT '订单数量',
  `orderTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `orderStatus` tinyint(4) DEFAULT '0' COMMENT '订单状态（0未支付，1申请支付中，2已经支付）',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', 'SSR', '后台登录权限', 'SSR');
INSERT INTO `power` VALUES ('2', 'SSS', '第二个权限字符集', 'SSS');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'root', '站长', '1');

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
INSERT INTO `role_power` VALUES ('1', '1');
INSERT INTO `role_power` VALUES ('1', '2');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '一级分类id',
  `typeName` varchar(50) NOT NULL COMMENT '一级分类名字',
  `typeCreateTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '儿童用品', null);
INSERT INTO `types` VALUES ('2', '儿童早教', null);
INSERT INTO `types` VALUES ('3', '儿童服饰', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(12) DEFAULT NULL COMMENT '用户名',
  `userPwd` varchar(255) NOT NULL,
  `userSex` varchar(4) DEFAULT NULL COMMENT '用户性别',
  `userAge` int(11) unsigned DEFAULT '0' COMMENT '用户年龄',
  `phoneNumber` varchar(50) NOT NULL COMMENT '联系方式（用作用户唯一id）主键,登陆时用的账户',
  `address` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `createTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('root', '$2a$10$TL7WlIlajXjOP/dCnydUbuGX9bOUIlKoyqVD/v1qRjntIhk.Hc2SK', '?', '0', '17513234581', 'root', '');

-- ----------------------------
-- Table structure for userid_roleid
-- ----------------------------
DROP TABLE IF EXISTS `userid_roleid`;
CREATE TABLE `userid_roleid` (
  `userId` bigint(11) NOT NULL COMMENT '用户id（即用户的手机号）',
  `roleId` int(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userid_roleid
-- ----------------------------
INSERT INTO `userid_roleid` VALUES ('17513234581', '1');
