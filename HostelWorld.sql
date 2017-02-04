/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : HostelWorld

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 02/04/2017 12:33:44 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `bankCard`
-- ----------------------------
DROP TABLE IF EXISTS `bankCard`;
CREATE TABLE `bankCard` (
  `number` varchar(20) NOT NULL,
  `balance` int(11) DEFAULT '0',
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `booking`
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `userId` int(7) NOT NULL,
  `inTime` date NOT NULL,
  `outTime` date NOT NULL,
  `hotelId` int(7) NOT NULL,
  `roomTypeId` int(11) NOT NULL,
  `roomNum` int(11) DEFAULT NULL,
  `nameinfo` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT '0',
  `cancled` int(1) NOT NULL DEFAULT '0',
  `deposit` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `hotelId` (`hotelId`),
  KEY `roomTypeId` (`roomTypeId`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`hotelId`) REFERENCES `hotel` (`id`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `checkin`
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  `payType` int(1) NOT NULL DEFAULT '0',
  `creatTime` datetime DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `checkin_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `checkin_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `checkout`
-- ----------------------------
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `id` int(11) NOT NULL,
  `checkinId` int(11) NOT NULL,
  `creatTime` datetime DEFAULT NULL,
  `roomAsignId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `checkinId` (`checkinId`),
  CONSTRAINT `checkout_ibfk_1` FOREIGN KEY (`checkinId`) REFERENCES `checkin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hotel`
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(7) NOT NULL,
  `name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `location` varchar(200) DEFAULT NULL,
  `description` int(11) DEFAULT '200',
  `psw` varchar(11) NOT NULL,
  `approved` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `plan`
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL,
  `hotelId` int(11) NOT NULL,
  `roomTypeId` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `hotelId` (`hotelId`),
  KEY `roomTypeId` (`roomTypeId`),
  CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`hotelId`) REFERENCES `hotel` (`id`),
  CONSTRAINT `plan_ibfk_2` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `roomTypeId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roomTypeId` (`roomTypeId`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `roomAsign`
-- ----------------------------
DROP TABLE IF EXISTS `roomAsign`;
CREATE TABLE `roomAsign` (
  `id` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `user1` varchar(45) NOT NULL,
  `idcard1` varchar(45) NOT NULL,
  `user2` varchar(45) DEFAULT NULL,
  `idcard2` varchar(45) DEFAULT NULL,
  `inTime` date NOT NULL,
  `outTime` date NOT NULL,
  `checkinId` int(11) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `state` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `roomId` (`roomId`),
  KEY `checkinId` (`checkinId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `roomasign_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`),
  CONSTRAINT `roomasign_ibfk_2` FOREIGN KEY (`checkinId`) REFERENCES `checkin` (`id`),
  CONSTRAINT `roomasign_ibfk_3` FOREIGN KEY (`bookId`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `roomType`
-- ----------------------------
DROP TABLE IF EXISTS `roomType`;
CREATE TABLE `roomType` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `hotelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hotelId` (`hotelId`),
  CONSTRAINT `roomtype_ibfk_1` FOREIGN KEY (`hotelId`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(7) NOT NULL,
  `bank` varchar(11) DEFAULT NULL,
  `psw` varchar(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idcard` varchar(45) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `state` int(2) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '0',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `bank` (`bank`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`bank`) REFERENCES `bankCard` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
