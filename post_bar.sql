/*
 Navicat Premium Data Transfer

 Source Server         : 我的阿里云
 Source Server Type    : MySQL
 Source Server Version : 100038
 Source Host           : 47.106.102.217:3306
 Source Schema         : post_bar

 Target Server Type    : MySQL
 Target Server Version : 100038
 File Encoding         : 65001

 Date: 07/02/2021 13:10:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pb_app
-- ----------------------------
DROP TABLE IF EXISTS `pb_app`;
CREATE TABLE `pb_app`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apiKey` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secretKey` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_app
-- ----------------------------
INSERT INTO `pb_app` VALUES (1, '19412962', 'xdMUnuGinYTLdMGVTdue3GYp', 'iP15EBEN8SwshwR8rt2P2dUDyoYKFYmw');

-- ----------------------------
-- Table structure for pb_audio
-- ----------------------------
DROP TABLE IF EXISTS `pb_audio`;
CREATE TABLE `pb_audio`  (
  `auSetUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auSetSpd` int(1) NOT NULL DEFAULT 5 COMMENT '0-9',
  `auSetPit` int(1) NOT NULL DEFAULT 5 COMMENT '0-9',
  `auSetVol` int(2) NOT NULL DEFAULT 5 COMMENT '0-15',
  `auSetVoiPer` int(1) NOT NULL DEFAULT 0 COMMENT '0-4',
  PRIMARY KEY (`auSetUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_audio
-- ----------------------------
INSERT INTO `pb_audio` VALUES ('0ab2f652a3614c8caca27dbdf5cf9bb3', 'cddc7b3a66bd423a95999bdb0151dfc8', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('37b566ec11b74537b83290d68fcb6086', '44d37f40cad2464981a9daa5ef70a2e5', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('3abbf6cd44244bc7be4bd44a0d28b33b', 'c41b57d70c0841258c6778c0c30f2683', 5, 5, 5, 0);
INSERT INTO `pb_audio` VALUES ('4793ddd38afd40ed80068d466cef56a6', 'e9b09481d53a4a2d82f0da68af207092', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('53b33007eba74b18ae3d6ad224ccb930', '8d4e751f7d6c4c07949b37dc5a89ae95', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('5e573b43bbeb461380807c5476df0cf9', '94ac590c313a474f9b2f3284d2e439ce', 5, 5, 5, 0);
INSERT INTO `pb_audio` VALUES ('63d9e62bdf9844ca81ce9406d9778550', '35f3269359fc4c698782832c78faef90', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('6bcef5d528b34deea4d680306e3ee43c', 'ad313b12f2c54b2b8a7df306f90243b2', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('6de4a601e70a4307831010621f700e79', '4baa234c18ec4c96aa1982ea7b93701a', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('790043718a3245ffb32a05e2d1459c1f', '725d09922acb4f54b977a9447315fcc0', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('7e8887b33c2a4a4da4fc258ce012c172', 'dfa251d7d37c4c508b7f54b35690e99a', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('816a839451dd41c3acc7beed036c612d', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('b3d7ee831fcc46a898fe57e5cac8d63d', 'c50e2ac96c7b45debaa2415bee9c6aed', 5, 5, 5, 4);
INSERT INTO `pb_audio` VALUES ('bdd318f10eef40f085f6d05471c4f158', 'f9279c75cb814111b43c26a2d207aa77', 5, 5, 5, 1);
INSERT INTO `pb_audio` VALUES ('d6aaea801ce04790953e6620f8ca1a91', 'e4f94bf452d9483aaa11972e6cc4d59d', 6, 6, 6, 1);

-- ----------------------------
-- Table structure for pb_comment
-- ----------------------------
DROP TABLE IF EXISTS `pb_comment`;
CREATE TABLE `pb_comment`  (
  `cmUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cmText` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cmAudio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cmTime` datetime(0) NOT NULL,
  PRIMARY KEY (`cmUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_comment
-- ----------------------------
INSERT INTO `pb_comment` VALUES ('104da35010e749e988789aa705f1cb37', '18ec50a15fea4113b6b08d4beb52861d', 'ad313b12f2c54b2b8a7df306f90243b2', '<span style=\"color:#333333;font-family:arial, 宋体, sans-serif;font-size:14px;line-height:24px;background-color:#FFFFFF;\">中软国际有限公司是国内大型综合性软件与信息服务企业，具有极高的市场感召力和客户忠诚度，以领先的技术、丰富的经验、精湛的服务在中国 IT 行业享有极高的声誉。</span>', '/postbar/audio/104da35010e749e988789aa705f1cb37.mp3', '2019-06-25 10:46:39');
INSERT INTO `pb_comment` VALUES ('25605e06865543d497c7cd38d05517d9', '972753d802604e33a714370d340e3df7', 'cddc7b3a66bd423a95999bdb0151dfc8', '6', '/postbar/audio/25605e06865543d497c7cd38d05517d9.mp3', '2020-04-14 22:26:52');
INSERT INTO `pb_comment` VALUES ('3ac3a07dcf6f4f579ca463a040d6c4a3', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '1212', '/postbar/audio/3ac3a07dcf6f4f579ca463a040d6c4a3.mp3', '2020-04-14 21:28:15');
INSERT INTO `pb_comment` VALUES ('3c71edc8bb244f47af121b785709135e', '3be912eabdbe4cc1be5d01bd2499c05e', 'c41b57d70c0841258c6778c0c30f2683', '劲啊！', '/postbar/audio/3c71edc8bb244f47af121b785709135e.mp3', '2021-02-07 12:56:02');
INSERT INTO `pb_comment` VALUES ('4b650f87f19346ceb52969153c286719', '972753d802604e33a714370d340e3df7', 'c50e2ac96c7b45debaa2415bee9c6aed', '干得漂亮！', '/postbar/audio/4b650f87f19346ceb52969153c286719.mp3', '2020-04-15 18:06:25');
INSERT INTO `pb_comment` VALUES ('55e3df6f54184592a33291e72a3309b7', '7a7e88cd6b404b878cb0d7c69ee42082', 'cddc7b3a66bd423a95999bdb0151dfc8', '121321', '/postbar/audio/55e3df6f54184592a33291e72a3309b7.mp3', '2020-04-14 22:50:32');
INSERT INTO `pb_comment` VALUES ('674e21b770794fa58f71ba7fcbde92c6', '8cfe1fa4016642e7b68fad165d517441', 'ad313b12f2c54b2b8a7df306f90243b2', '公司目前所涵盖的行业有政府、金融、电信、烟草、物流、交通、能源、互联网等。<br />\n具体项目有金审工程项目、中国银联一卡通跨行取款项目、国家烟草一号工程打码到条项目、北京一号线、二号线、上海市地铁等轨道交通系统、王者荣耀的后台技术支持等。<br />', '/postbar/audio/674e21b770794fa58f71ba7fcbde92c6.mp3', '2019-06-25 11:25:26');
INSERT INTO `pb_comment` VALUES ('6aee5be44c314456930429684e2f5dcd', '379b441c4ed84ab8b5c391226d81a459', 'f9279c75cb814111b43c26a2d207aa77', '<img src=\"http://47.106.102.217:8800/postbar/static/kindeditor/plugins/emoticons/images/42.gif\" border=\"0\" alt=\"\" />', '/postbar/audio/6aee5be44c314456930429684e2f5dcd.mp3', '2020-04-14 17:44:00');
INSERT INTO `pb_comment` VALUES ('8b39fe1648634363b66571a7708fbcf9', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', 'Interesting', '/postbar/audio/8b39fe1648634363b66571a7708fbcf9.mp3', '2020-04-14 11:34:19');
INSERT INTO `pb_comment` VALUES ('b96681a8cbb24cb3adf73b3031c86738', '5fddb8e1ed8d4158847e09ba55ccc21d', 'c50e2ac96c7b45debaa2415bee9c6aed', '生活就像海洋，只有意志坚强的人才能到达成功的彼岸！', '/postbar/audio/b96681a8cbb24cb3adf73b3031c86738.mp3', '2020-04-15 20:05:19');
INSERT INTO `pb_comment` VALUES ('bc51034834bb46968443b842305a698d', 'c31486dfdbf141c8928d5c0d6ae360de', 'e9b09481d53a4a2d82f0da68af207092', 'Well done!', '/postbar/audio/bc51034834bb46968443b842305a698d.mp3', '2020-04-14 23:06:11');
INSERT INTO `pb_comment` VALUES ('cd4f4736a4f446ba9fa2b2cc7f27d140', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', 'Well done!', '/postbar/audio/cd4f4736a4f446ba9fa2b2cc7f27d140.mp3', '2020-04-14 11:32:39');
INSERT INTO `pb_comment` VALUES ('d619429096594885ab0c26fc211e806b', '0d3da17dcce04599a4e8e2380164be19', 'dea3cb70f142453d9d55e118269ff8f9', 'Well done!', '/postbar/audio/d619429096594885ab0c26fc211e806b.mp3', '2020-04-14 22:52:59');
INSERT INTO `pb_comment` VALUES ('fb8a2808cd904a0aaee53e5ffff71596', '5fddb8e1ed8d4158847e09ba55ccc21d', '44d37f40cad2464981a9daa5ef70a2e5', '赞！', '/postbar/audio/fb8a2808cd904a0aaee53e5ffff71596.mp3', '2020-05-03 21:46:30');

-- ----------------------------
-- Table structure for pb_comment_praise
-- ----------------------------
DROP TABLE IF EXISTS `pb_comment_praise`;
CREATE TABLE `pb_comment_praise`  (
  `cmPrUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cmUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cmPrUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_comment_praise
-- ----------------------------
INSERT INTO `pb_comment_praise` VALUES ('03b8899926f64fc6a887eb8b68a69e98', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('21853351bce247389cbe316d570a6dab', '972753d802604e33a714370d340e3df7', 'c50e2ac96c7b45debaa2415bee9c6aed', '4b650f87f19346ceb52969153c286719');
INSERT INTO `pb_comment_praise` VALUES ('26994af357a2494c86c5e4cc5894c237', '0d3da17dcce04599a4e8e2380164be19', 'cddc7b3a66bd423a95999bdb0151dfc8', 'd619429096594885ab0c26fc211e806b');
INSERT INTO `pb_comment_praise` VALUES ('2830a6113404483eadfe1bfd6c5edf2f', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', 'cd4f4736a4f446ba9fa2b2cc7f27d140');
INSERT INTO `pb_comment_praise` VALUES ('2f166deaf9cb460392cebc695869d3bc', '0d3da17dcce04599a4e8e2380164be19', 'cddc7b3a66bd423a95999bdb0151dfc8', 'd619429096594885ab0c26fc211e806b');
INSERT INTO `pb_comment_praise` VALUES ('35a40432cbcb4a4f98a88bf00798d50f', '0d3da17dcce04599a4e8e2380164be19', 'dea3cb70f142453d9d55e118269ff8f9', 'd619429096594885ab0c26fc211e806b');
INSERT INTO `pb_comment_praise` VALUES ('42ebd9ae0ade4a489c4679abc72dea22', 'fd67cc79fbed41118115d063df7d5470', 'ad313b12f2c54b2b8a7df306f90243b2', '104da35010e749e988789aa705f1cb37');
INSERT INTO `pb_comment_praise` VALUES ('4e2fb24bd85b44cb8e035e3ed2311232', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('50bf1bd4854145c5baebc1c863e019b2', '5fddb8e1ed8d4158847e09ba55ccc21d', '44d37f40cad2464981a9daa5ef70a2e5', 'b96681a8cbb24cb3adf73b3031c86738');
INSERT INTO `pb_comment_praise` VALUES ('5e228758b431430bb801e8e509bfbad7', '379b441c4ed84ab8b5c391226d81a459', 'c50e2ac96c7b45debaa2415bee9c6aed', '6aee5be44c314456930429684e2f5dcd');
INSERT INTO `pb_comment_praise` VALUES ('6b316c38bcc84e319d2c65b3f79411bb', '972753d802604e33a714370d340e3df7', 'cddc7b3a66bd423a95999bdb0151dfc8', '25605e06865543d497c7cd38d05517d9');
INSERT INTO `pb_comment_praise` VALUES ('6ed8b60ebe8149388074e7c3fd2c5dde', '8cfe1fa4016642e7b68fad165d517441', 'c50e2ac96c7b45debaa2415bee9c6aed', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('6f27433a431943fb861ca4930de51245', '972753d802604e33a714370d340e3df7', 'c50e2ac96c7b45debaa2415bee9c6aed', '25605e06865543d497c7cd38d05517d9');
INSERT INTO `pb_comment_praise` VALUES ('71acbde9bb9a4376ad5731695968af49', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('77dfe2be29cf4d4b9fcb4a47d4f44ddc', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('790aa320b0e84fe88622026cb0f3b2fb', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', '8b39fe1648634363b66571a7708fbcf9');
INSERT INTO `pb_comment_praise` VALUES ('816044f94f7e4cf0862d5f48224c9bb3', '0da90253d0a84793a0716767a530e119', '44d37f40cad2464981a9daa5ef70a2e5', 'cd4f4736a4f446ba9fa2b2cc7f27d140');
INSERT INTO `pb_comment_praise` VALUES ('83a011fb469c4d70b60cb332641bf96e', '8cfe1fa4016642e7b68fad165d517441', 'ad313b12f2c54b2b8a7df306f90243b2', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('8c6da7dbf2ec4b3191e68229c6f20100', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('9220558ab47b4888ac1221465e75a662', '5fddb8e1ed8d4158847e09ba55ccc21d', 'c50e2ac96c7b45debaa2415bee9c6aed', 'b96681a8cbb24cb3adf73b3031c86738');
INSERT INTO `pb_comment_praise` VALUES ('92ab722544e94e25ad855368f6c3bdc2', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('ab0942f8b7374fa5b04fb31620a168c2', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('ba1b341255cf4f2bbc5635f3979cc27d', 'c31486dfdbf141c8928d5c0d6ae360de', 'e9b09481d53a4a2d82f0da68af207092', 'bc51034834bb46968443b842305a698d');
INSERT INTO `pb_comment_praise` VALUES ('bc5adec17da54344949c098ef0d2ded9', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', '8b39fe1648634363b66571a7708fbcf9');
INSERT INTO `pb_comment_praise` VALUES ('d9fc6bdf1e4e4c00a8b743c66a72baea', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', 'cd4f4736a4f446ba9fa2b2cc7f27d140');
INSERT INTO `pb_comment_praise` VALUES ('d9fcb6c61df14658a928c88316979f51', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('dbe6083a4d164b789f333aae5cfd6c3b', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('e2ec0938c21541e4ad59477dd71be450', 'c31486dfdbf141c8928d5c0d6ae360de', 'c50e2ac96c7b45debaa2415bee9c6aed', 'bc51034834bb46968443b842305a698d');
INSERT INTO `pb_comment_praise` VALUES ('e452a1071eef4371aed278f64576c949', '5fddb8e1ed8d4158847e09ba55ccc21d', '35f3269359fc4c698782832c78faef90', 'b96681a8cbb24cb3adf73b3031c86738');
INSERT INTO `pb_comment_praise` VALUES ('e65b2cd18f2b4d68b3ad55b43658e703', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', '8b39fe1648634363b66571a7708fbcf9');
INSERT INTO `pb_comment_praise` VALUES ('eac139ea77ad4ce5a54e710b63914066', '0da90253d0a84793a0716767a530e119', '44d37f40cad2464981a9daa5ef70a2e5', '8b39fe1648634363b66571a7708fbcf9');
INSERT INTO `pb_comment_praise` VALUES ('f0ce112a2fe34c07a79dc74216fb18b0', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('f5e6b08c412f445691741bf20ab40f84', '0d3da17dcce04599a4e8e2380164be19', 'cddc7b3a66bd423a95999bdb0151dfc8', 'd619429096594885ab0c26fc211e806b');
INSERT INTO `pb_comment_praise` VALUES ('faf9e52cc3cc4d358de3dbd0b7a79acc', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed', 'cd4f4736a4f446ba9fa2b2cc7f27d140');
INSERT INTO `pb_comment_praise` VALUES ('fc4f1d25d4fb49f98a0be738a0682506', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '674e21b770794fa58f71ba7fcbde92c6');
INSERT INTO `pb_comment_praise` VALUES ('feda6ab4a7164f2ab23e532c475c4dc8', '0d3da17dcce04599a4e8e2380164be19', 'cddc7b3a66bd423a95999bdb0151dfc8', 'd619429096594885ab0c26fc211e806b');

-- ----------------------------
-- Table structure for pb_post
-- ----------------------------
DROP TABLE IF EXISTS `pb_post`;
CREATE TABLE `pb_post`  (
  `postUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postTitle` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postText` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postPageviews` int(11) UNSIGNED NOT NULL DEFAULT 0,
  `postAudio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postTime` datetime(0) NOT NULL,
  PRIMARY KEY (`postUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_post
-- ----------------------------
INSERT INTO `pb_post` VALUES ('0da90253d0a84793a0716767a530e119', '44d37f40cad2464981a9daa5ef70a2e5', '世界，你好！', 'Hello world!', 34, '/postbar/audio/0da90253d0a84793a0716767a530e119.mp3', '2020-04-13 21:33:12');
INSERT INTO `pb_post` VALUES ('104dcd0097c011e996adcd5e8c15066c', 'ad313b12f2c54b2b8a7df306f90243b2', '同学们，毕业快乐', '同学们，毕业垮了', 5, NULL, '2019-06-26 11:11:17');
INSERT INTO `pb_post` VALUES ('18ec50a15fea4113b6b08d4beb52861d', 'ad313b12f2c54b2b8a7df306f90243b2', '中软国际是什么样的公司', '中软国际是什么样的公司', 11, NULL, '2019-06-25 10:45:51');
INSERT INTO `pb_post` VALUES ('37509fe2cf23452186fefec1fbfb2f7d', 'e4f94bf452d9483aaa11972e6cc4d59d', 'qwe', '123', 3, '/postbar/audio/37509fe2cf23452186fefec1fbfb2f7d.mp3', '2020-04-15 11:58:37');
INSERT INTO `pb_post` VALUES ('379b441c4ed84ab8b5c391226d81a459', 'f9279c75cb814111b43c26a2d207aa77', '晚餐', '<p>\n	下课了嘛？\n</p>\n<p>\n	做晚饭了嘛？\n</p>\n<p>\n	晚上有没有课？\n</p>\n<p>\n	下课了。\n</p>\n<p>\n	在熬粥。\n</p>\n<p>\n	今晚没课，要写需求分析。\n</p>', 8, '/postbar/audio/379b441c4ed84ab8b5c391226d81a459.mp3', '2020-04-14 17:43:26');
INSERT INTO `pb_post` VALUES ('3be912eabdbe4cc1be5d01bd2499c05e', 'c41b57d70c0841258c6778c0c30f2683', '人生四大喜事', '久旱逢甘雨，<br />\n他乡遇故知，<br />\n洞房花烛夜，<br />\n金榜题名时。<br />', 3, '/postbar/audio/3be912eabdbe4cc1be5d01bd2499c05e.mp3', '2021-02-07 12:55:38');
INSERT INTO `pb_post` VALUES ('5fddb8e1ed8d4158847e09ba55ccc21d', 'c50e2ac96c7b45debaa2415bee9c6aed', '回味生活', '生活如此多娇！', 15, '/postbar/audio/5fddb8e1ed8d4158847e09ba55ccc21d.mp3', '2020-04-15 19:54:45');
INSERT INTO `pb_post` VALUES ('6245093097bf11e9b08f091c9d2895ea', 'ad313b12f2c54b2b8a7df306f90243b2', '各位同学，你们期待毕业吗？', '各位同学，你们期待毕业吗？', 4, NULL, '2019-06-26 11:06:25');
INSERT INTO `pb_post` VALUES ('7a7e88cd6b404b878cb0d7c69ee42082', 'cddc7b3a66bd423a95999bdb0151dfc8', '项目真难搞', '爱爱啊哎', 6, '/postbar/audio/7a7e88cd6b404b878cb0d7c69ee42082.mp3', '2020-04-14 22:31:26');
INSERT INTO `pb_post` VALUES ('8cfe1fa4016642e7b68fad165d517441', 'ad313b12f2c54b2b8a7df306f90243b2', '中软国际做过哪些项目？', '中软国际做过哪些项目？', 35, NULL, '2019-06-25 11:25:18');
INSERT INTO `pb_post` VALUES ('972753d802604e33a714370d340e3df7', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '军哥牛逼', '&nbsp; &nbsp;军哥是个好男人&nbsp;', 21, '/postbar/audio/972753d802604e33a714370d340e3df7.mp3', '2020-04-14 21:30:31');
INSERT INTO `pb_post` VALUES ('a405adcf464a4269866d64e5403ce6fe', '44d37f40cad2464981a9daa5ef70a2e5', '嘻嘻哈哈', '<p>\n	今晚还吃夜宵嘛？\n</p>\n<p>\n	今晚继续吃夜宵。\n</p>', 3, '/postbar/audio/a405adcf464a4269866d64e5403ce6fe.mp3', '2020-04-13 21:57:26');
INSERT INTO `pb_post` VALUES ('c31486dfdbf141c8928d5c0d6ae360de', 'e9b09481d53a4a2d82f0da68af207092', 'Welcome to China', '欢迎来到中国！', 11, '/postbar/audio/c31486dfdbf141c8928d5c0d6ae360de.mp3', '2020-04-14 23:03:18');
INSERT INTO `pb_post` VALUES ('c66a1757a6654a04a4962a22e6d0c806', '8d4e751f7d6c4c07949b37dc5a89ae95', 'hello world', '你好，新世界！', 4, '/postbar/audio/c66a1757a6654a04a4962a22e6d0c806.mp3', '2020-04-14 22:40:44');

-- ----------------------------
-- Table structure for pb_post_praise
-- ----------------------------
DROP TABLE IF EXISTS `pb_post_praise`;
CREATE TABLE `pb_post_praise`  (
  `poPrUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00000000000000000000000000000000',
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00000000000000000000000000000000',
  PRIMARY KEY (`poPrUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_post_praise
-- ----------------------------
INSERT INTO `pb_post_praise` VALUES ('0e9dba5d115249efbe85d4d5c7e1dade', '379b441c4ed84ab8b5c391226d81a459', 'c50e2ac96c7b45debaa2415bee9c6aed');
INSERT INTO `pb_post_praise` VALUES ('1a941d568e1544d6b790d8d4c1947768', '6245093097bf11e9b08f091c9d2895ea', 'ad313b12f2c54b2b8a7df306f90243b2');
INSERT INTO `pb_post_praise` VALUES ('304c5f0d8a85467d80046a3583aa8674', '0da90253d0a84793a0716767a530e119', 'c50e2ac96c7b45debaa2415bee9c6aed');
INSERT INTO `pb_post_praise` VALUES ('3aab47e7724440519c1c954094e74bd2', '5fddb8e1ed8d4158847e09ba55ccc21d', '35f3269359fc4c698782832c78faef90');
INSERT INTO `pb_post_praise` VALUES ('44ebf1e5c2d5468385438eb6c79d90ac', '0da90253d0a84793a0716767a530e119', '44d37f40cad2464981a9daa5ef70a2e5');
INSERT INTO `pb_post_praise` VALUES ('4f6d605baeef454da6ece6afec626717', '972753d802604e33a714370d340e3df7', 'c50e2ac96c7b45debaa2415bee9c6aed');
INSERT INTO `pb_post_praise` VALUES ('5b11f3b3f1de458e9c5a93248b9f5c4e', '3be912eabdbe4cc1be5d01bd2499c05e', 'c41b57d70c0841258c6778c0c30f2683');
INSERT INTO `pb_post_praise` VALUES ('652b05d0ac58402bb2189e070cdc26f8', '972753d802604e33a714370d340e3df7', 'dea3cb70f142453d9d55e118269ff8f9');
INSERT INTO `pb_post_praise` VALUES ('73eb2a72f4b749dd9908dd16f161a314', '5fddb8e1ed8d4158847e09ba55ccc21d', 'c50e2ac96c7b45debaa2415bee9c6aed');
INSERT INTO `pb_post_praise` VALUES ('83aed0201d1a4b078f8bb04f89ce08d1', 'c66a1757a6654a04a4962a22e6d0c806', '8d4e751f7d6c4c07949b37dc5a89ae95');
INSERT INTO `pb_post_praise` VALUES ('8d27ae52bcf245a6a329e856b0dfdd72', '5fddb8e1ed8d4158847e09ba55ccc21d', '44d37f40cad2464981a9daa5ef70a2e5');
INSERT INTO `pb_post_praise` VALUES ('a683acc437de4303b6db5ab2394a6463', '8cfe1fa4016642e7b68fad165d517441', 'c3b9ebd06f2e4e7689195bce1e7e4d6b');
INSERT INTO `pb_post_praise` VALUES ('b0c9577ddc4d48a6b1a093353a76fc0a', 'c31486dfdbf141c8928d5c0d6ae360de', 'c50e2ac96c7b45debaa2415bee9c6aed');
INSERT INTO `pb_post_praise` VALUES ('c32fa42da5c646cda02300837910f5c2', '0d3da17dcce04599a4e8e2380164be19', 'dea3cb70f142453d9d55e118269ff8f9');
INSERT INTO `pb_post_praise` VALUES ('d5656f8b49174533b7b46f79cd69de57', '8cfe1fa4016642e7b68fad165d517441', 'ad313b12f2c54b2b8a7df306f90243b2');
INSERT INTO `pb_post_praise` VALUES ('d8e816b876ef42be975546771bfadc90', 'c31486dfdbf141c8928d5c0d6ae360de', 'e9b09481d53a4a2d82f0da68af207092');
INSERT INTO `pb_post_praise` VALUES ('ddbca7288b7d4d68a9ed93090568635a', '972753d802604e33a714370d340e3df7', 'cddc7b3a66bd423a95999bdb0151dfc8');

-- ----------------------------
-- Table structure for pb_register
-- ----------------------------
DROP TABLE IF EXISTS `pb_register`;
CREATE TABLE `pb_register`  (
  `regUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `regSex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `regAge` int(3) NOT NULL,
  `regEmial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `regPhoto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `regTime` datetime(0) NOT NULL,
  PRIMARY KEY (`regUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_register
-- ----------------------------
INSERT INTO `pb_register` VALUES ('12abf6d16328415eb4f254c5fe59d945', 'f9279c75cb814111b43c26a2d207aa77', '1', 18, '2115373173@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 14:32:42');
INSERT INTO `pb_register` VALUES ('268f82b0dafa4bea972cfa8640467a76', 'e9b09481d53a4a2d82f0da68af207092', '0', 18, '1718874189@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 23:01:23');
INSERT INTO `pb_register` VALUES ('2e04375027424e4db319315158a5461d', '94ac590c313a474f9b2f3284d2e439ce', '0', 14, '123@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-15 11:55:13');
INSERT INTO `pb_register` VALUES ('378806d1f31748b9bf67e4212aa2a291', 'dfa251d7d37c4c508b7f54b35690e99a', '0', 14, '911260362@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 17:33:38');
INSERT INTO `pb_register` VALUES ('3c51cf5926d447c4b965459a0639057c', 'e4f94bf452d9483aaa11972e6cc4d59d', '0', 37, 'sjm@163.com', '/postbar/headPhoto/default/default.jpg', '2019-03-12 12:46:38');
INSERT INTO `pb_register` VALUES ('4cc8aed13cfb45b2b6aaea85bbc039e1', 'c50e2ac96c7b45debaa2415bee9c6aed', '0', 22, '1718874189@qq.com', '/postbar/headPhoto/D Lady/newPhoto.jpg', '2020-04-14 09:51:34');
INSERT INTO `pb_register` VALUES ('5a7031aa258c4c9eb4ac806f1777c415', '725d09922acb4f54b977a9447315fcc0', '0', 14, '2350347796@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 22:36:15');
INSERT INTO `pb_register` VALUES ('648849c3b7d7474191c4fa3909622952', '44d37f40cad2464981a9daa5ef70a2e5', '0', 14, 'vds@123.com', '/postbar/headPhoto/default/default.jpg', '2019-06-20 09:18:06');
INSERT INTO `pb_register` VALUES ('7c3091014f8a4c208e7ad5bba8485af3', '4baa234c18ec4c96aa1982ea7b93701a', '1', 20, '786148646@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 21:30:51');
INSERT INTO `pb_register` VALUES ('86866ed0996b4f3cb1c0082250b31fd2', 'cddc7b3a66bd423a95999bdb0151dfc8', '0', 14, '1324555@QQ.COM', '/postbar/headPhoto/default/default.jpg', '2020-04-14 21:41:50');
INSERT INTO `pb_register` VALUES ('9425cfc0384a430787daeecda1ab3beb', 'ad313b12f2c54b2b8a7df306f90243b2', '1', 40, 'wangxiao@163.com', '/postbar/headPhoto/admin/newPhoto.jpg', '2019-03-12 12:47:11');
INSERT INTO `pb_register` VALUES ('99de5fdc97fa4490bec5568c6742b1c3', 'c41b57d70c0841258c6778c0c30f2683', '0', 14, '1718874189@qq.com', '/postbar/headPhoto/guest/newPhoto.jpg', '2021-02-07 12:53:09');
INSERT INTO `pb_register` VALUES ('e4e9746d33eb44148c5e2be82dd65125', '8d4e751f7d6c4c07949b37dc5a89ae95', '0', 14, '2350347796@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 22:37:43');
INSERT INTO `pb_register` VALUES ('ea866595b8294b01ac48edd21248cf0e', '35f3269359fc4c698782832c78faef90', '1', 18, '1718874189@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 22:57:55');
INSERT INTO `pb_register` VALUES ('ebd09a7828e14250bd98a27161ed2243', 'c3b9ebd06f2e4e7689195bce1e7e4d6b', '0', 14, '1849@qq.com', '/postbar/headPhoto/default/default.jpg', '2020-04-14 18:23:54');

-- ----------------------------
-- Table structure for pb_user
-- ----------------------------
DROP TABLE IF EXISTS `pb_user`;
CREATE TABLE `pb_user`  (
  `userUUID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginTime` datetime(0) NULL DEFAULT NULL,
  `admin` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`userUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pb_user
-- ----------------------------
INSERT INTO `pb_user` VALUES ('35f3269359fc4c698782832c78faef90', '清远鸡', 'f379eaf3c831b04de153469d1bec345e', '2020-04-14 22:57:55', '0');
INSERT INTO `pb_user` VALUES ('44d37f40cad2464981a9daa5ef70a2e5', 'dxj', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-20 09:18:17', '0');
INSERT INTO `pb_user` VALUES ('4baa234c18ec4c96aa1982ea7b93701a', 'yuan', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 21:30:51', '0');
INSERT INTO `pb_user` VALUES ('725d09922acb4f54b977a9447315fcc0', '23333', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 22:36:15', '0');
INSERT INTO `pb_user` VALUES ('8d4e751f7d6c4c07949b37dc5a89ae95', 'czt199808', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 22:37:43', '0');
INSERT INTO `pb_user` VALUES ('94ac590c313a474f9b2f3284d2e439ce', 'qwe', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-15 11:55:13', '0');
INSERT INTO `pb_user` VALUES ('ad313b12f2c54b2b8a7df306f90243b2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-25 11:21:34', '1');
INSERT INTO `pb_user` VALUES ('c3b9ebd06f2e4e7689195bce1e7e4d6b', '123123', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 18:23:54', '0');
INSERT INTO `pb_user` VALUES ('c41b57d70c0841258c6778c0c30f2683', 'guest', 'e10adc3949ba59abbe56e057f20f883e', '2021-02-07 12:53:09', '0');
INSERT INTO `pb_user` VALUES ('c50e2ac96c7b45debaa2415bee9c6aed', 'D Lady', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 09:51:34', '0');
INSERT INTO `pb_user` VALUES ('cddc7b3a66bd423a95999bdb0151dfc8', '123123', '5b1b68a9abf4d2cd155c81a9225fd158', '2020-04-14 21:41:50', '0');
INSERT INTO `pb_user` VALUES ('dfa251d7d37c4c508b7f54b35690e99a', '12345', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-14 17:33:38', '0');
INSERT INTO `pb_user` VALUES ('e4f94bf452d9483aaa11972e6cc4d59d', 'sjm', 'e10adc3949ba59abbe56e057f20f883e', '2019-03-17 20:39:20', '0');
INSERT INTO `pb_user` VALUES ('e9b09481d53a4a2d82f0da68af207092', 'China', '21218cca77804d2ba1922c33e0151105', '2020-04-14 23:01:23', '0');
INSERT INTO `pb_user` VALUES ('f9279c75cb814111b43c26a2d207aa77', 'zzx', 'c33367701511b4f6020ec61ded352059', '2020-04-14 14:32:42', '0');

SET FOREIGN_KEY_CHECKS = 1;
