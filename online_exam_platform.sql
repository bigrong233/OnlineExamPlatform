/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : online_exam_platform

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 08/07/2020 20:09:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `class_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名',
  `people_number` int(0) NOT NULL COMMENT '班级人数',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_creator
-- ----------------------------
DROP TABLE IF EXISTS `class_creator`;
CREATE TABLE `class_creator`  (
  `class_id` int(0) NOT NULL COMMENT '班级id',
  `user_id` int(0) NOT NULL COMMENT '班级创建者id',
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `fk_class_creator_2_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_class_creator_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_class_creator_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_exam
-- ----------------------------
DROP TABLE IF EXISTS `class_exam`;
CREATE TABLE `class_exam`  (
  `class_id` int(0) NOT NULL COMMENT '班级编号',
  `exam_id` int(0) NOT NULL COMMENT '考试编号',
  `start_time` datetime(0) NOT NULL COMMENT '考试开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '考试结束时间',
  `exam_time` datetime(0) NOT NULL COMMENT '考试时长',
  PRIMARY KEY (`class_id`, `exam_id`) USING BTREE,
  INDEX `fk_class_exam_1_idx`(`class_id`) USING BTREE,
  INDEX `fk_class_exam_2_idx`(`exam_id`) USING BTREE,
  CONSTRAINT `fk_class_exam_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_class_exam_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user`  (
  `class_id` int(0) NOT NULL COMMENT '班级id',
  `user_id` int(0) NOT NULL COMMENT '班级里的用户id',
  `user_class_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '匿名' COMMENT '用户在班级里使用的名字',
  `user_class_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '用户在班级里使用的id',
  PRIMARY KEY (`class_id`, `user_id`) USING BTREE,
  INDEX `fk_class_user_2_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_class_user_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_class_user_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `exam_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `exam_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试名',
  `exam_subject` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试科目',
  `is_public` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否公开 \'0\'为公开,\'1\'为私密',
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_creator
-- ----------------------------
DROP TABLE IF EXISTS `exam_creator`;
CREATE TABLE `exam_creator`  (
  `user_id` int(0) NOT NULL COMMENT '考试创建者id',
  `exam_id` int(0) NOT NULL COMMENT '考试id',
  `create_time` datetime(0) NOT NULL COMMENT '考试创建时间',
  PRIMARY KEY (`exam_id`) USING BTREE,
  INDEX `fk_exam_creator_1_idx`(`user_id`) USING BTREE,
  INDEX `fk_exam_creator_2_idx`(`exam_id`) USING BTREE,
  CONSTRAINT `fk_exam_creator_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_exam_creator_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question`  (
  `exam_id` int(0) NOT NULL COMMENT '考试编号',
  `question_id` int(0) NOT NULL COMMENT '考试中包含的题目编号',
  PRIMARY KEY (`question_id`) USING BTREE,
  INDEX `fk_exam_question_1_idx`(`exam_id`) USING BTREE,
  INDEX `fk_exam_question_2_idx`(`question_id`) USING BTREE,
  CONSTRAINT `fk_exam_question_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_exam_question_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '题编号',
  `question_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '题目内容',
  `option_a` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项A',
  `option_b` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项B',
  `option_c` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项C',
  `option_d` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项D',
  `answer` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题答案',
  `analyze` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'null' COMMENT '答案解析',
  `score` int(0) NOT NULL COMMENT '分值',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱/账号',
  `user_password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `user_comptence` tinyint(0) NOT NULL DEFAULT 1 COMMENT '用户权限',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_email_UNIQUE`(`user_email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_exam
-- ----------------------------
DROP TABLE IF EXISTS `user_exam`;
CREATE TABLE `user_exam`  (
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `exam_id` int(0) NOT NULL COMMENT '考试编号',
  `start_time` datetime(0) NOT NULL COMMENT '用户开始答题时间',
  `end_time` datetime(0) NOT NULL COMMENT '用户结束答题时间',
  `score` int(0) NOT NULL COMMENT '得分',
  PRIMARY KEY (`user_id`, `exam_id`) USING BTREE,
  INDEX `fk_user_exam_1_idx`(`user_id`) USING BTREE,
  INDEX `fk_user_exam_2_idx`(`exam_id`) USING BTREE,
  CONSTRAINT `fk_user_exam_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_exam_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_question
-- ----------------------------
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question`  (
  `user_id` int(0) NOT NULL,
  `question_id` int(0) NOT NULL,
  `user_answer` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`, `question_id`) USING BTREE,
  INDEX `question_id_f`(`question_id`) USING BTREE,
  CONSTRAINT `question_id_f` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id_f` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
