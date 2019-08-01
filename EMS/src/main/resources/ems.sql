/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : ems

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 20/06/2019 22:30:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '权限父id',
  `level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限模所在的层级',
  `type` int(11) NOT NULL DEFAULT 3 COMMENT '类型，1：菜单，2：按钮，3：其他',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限码',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
INSERT INTO `sys_perm` VALUES (1, '后台端', 0, '0', 3, '#', '#', 1, 0, '', '', '2019-05-17 21:02:32', '');
INSERT INTO `sys_perm` VALUES (2, '权限管理', 1, '0.1', 3, '#', '#', 1, 0, '', '', '2019-05-18 07:35:19', '');
INSERT INTO `sys_perm` VALUES (3, '角色管理', 2, '0.1.2', 3, 'role:query', '/role/toRolePage', 1, 0, '', 'admin', '2019-06-02 04:36:18', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (4, '资源管理', 2, '0.1.2', 3, 'perm:query', '/perm/permList', 1, 0, '', '', '2019-05-18 07:41:21', '');
INSERT INTO `sys_perm` VALUES (5, '新增角色', 3, '0.1.2.3', 2, 'role:save', '/role/roleAdd', 1, 0, '', '', '2019-05-19 23:03:43', '');
INSERT INTO `sys_perm` VALUES (6, '删除角色', 3, '0.1.2.3', 2, 'role:delete', '#', 1, 0, '', '', '2019-05-22 18:59:11', '');
INSERT INTO `sys_perm` VALUES (13, '修改角色', 3, '0.1.2.3', 2, 'role:update', '#', 1, 0, '', '', '2019-05-22 18:59:42', '');
INSERT INTO `sys_perm` VALUES (14, '角色授权', 3, '0.1.2.3', 2, 'role:auth', '#', 1, 0, '', '', '2019-05-22 19:40:00', '');
INSERT INTO `sys_perm` VALUES (16, '院系管理', 1, '0.1', 3, '#', '#', 1, 0, '', 'admin', '2019-05-29 06:11:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (17, '学院列表', 16, '0.1.16', 3, 'sdept:query', '/sdept/toSdeptPage', 1, 0, '', '', '2019-05-24 11:39:21', '');
INSERT INTO `sys_perm` VALUES (18, '专业列表', 16, '0.1.16', 3, 'major:query', '/major/toMajorPage', 1, 0, '', 'admin', '2019-06-02 11:11:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (19, '班级列表', 16, '0.1.16', 3, 'team:query', '/team/toTeamPage', 1, 0, '', 'admin', '2019-06-02 11:11:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (20, '修改学院', 17, '0.1.16.17', 2, 'sdept:update', '#', 1, 0, '', '', '2019-05-24 13:50:51', '');
INSERT INTO `sys_perm` VALUES (21, '新增学院', 17, '0.1.16.17', 2, 'sdept:save', '#', 1, 0, '', '', '2019-05-24 14:09:57', '');
INSERT INTO `sys_perm` VALUES (22, '删除学院', 17, '0.1.16.17', 2, 'sdept:delete', '#', 1, 0, '', ' ', '2019-05-24 14:10:46', '');
INSERT INTO `sys_perm` VALUES (23, '资源授权', 14, '0.1.2.3.14', 2, 'rolePerm:save', '#', 1, 0, '', '', '2019-05-25 21:43:17', '');
INSERT INTO `sys_perm` VALUES (24, '用户授权', 14, '0.1.2.3.14', 2, 'roleUser:save', '#', 1, 0, '', '', '2019-05-26 10:45:10', '');
INSERT INTO `sys_perm` VALUES (25, '添加资源', 4, '0.1.2.4', 2, 'perm:save', '#', 1, 0, '', '', '2019-05-28 18:48:34', '');
INSERT INTO `sys_perm` VALUES (26, '修改资源', 4, '0.1.2.4', 2, 'perm:update', '#', 1, 0, '', '', '2019-05-28 18:49:23', '');
INSERT INTO `sys_perm` VALUES (27, '删除资源', 4, '0.1.2.4', 2, 'perm:delete', '#', 1, 0, '', '', '2019-05-28 18:49:52', '');
INSERT INTO `sys_perm` VALUES (38, '教师管理', 1, '0.1', 3, '#', '#', 1, 0, '', 'admin', '2019-06-09 10:56:43', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (55, '学生端', 0, '0', 3, '#', '#', 1, 0, '', 'admin', '2019-05-29 09:30:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (56, '学生管理', 1, '0.1', 3, '#', '#', 1, 0, '', 'admin', '2019-05-29 10:30:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (57, '新增专业', 18, '0.1.16.18', 2, 'major:save', '#', 1, 0, '', 'admin', '2019-06-03 00:05:14', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (58, '修改专业', 18, '0.1.16.18', 2, 'major:update', '#', 1, 0, '', 'admin', '2019-06-03 00:06:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (59, '删除专业', 18, '0.1.16.18', 2, 'major:delete', '#', 1, 0, '', 'admin', '2019-06-03 00:07:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (60, '新增班级', 19, '0.1.16.19', 2, 'team:save', '#', 1, 0, '', 'admin', '2019-06-03 08:09:13', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (61, '课程管理', 1, '0.1', 3, '#', '#', 1, 0, '', 'admin', '2019-06-03 10:01:14', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (62, '教师列表', 38, '0.1.38', 3, 'teacher:query', '/teacher/toTeacherPage', 1, 0, '', 'admin', '2019-06-11 10:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (63, '学生列表', 56, '0.1.56', 3, 'student:query', '/student/toStudentPage', 1, 0, '', 'admin', '2019-06-12 10:56:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (64, '新生录入', 56, '0.1.56', 3, 'student:save', '/student/toStudentAddPage', 1, 0, '', 'admin', '2019-06-13 08:10:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (65, '选课列表', 55, '0.55', 3, 'studentCourse:query', '/student/toSelectCoursePage', 1, 0, '', 'admin', '2019-06-19 09:42:10', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (66, '已选列表', 55, '0.55', 3, 'studentCourse:query', '/student/toSelectedCourseList', 1, 0, '', 'admin', '2019-06-19 13:10:14', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (67, '课程列表', 61, '0.1.61', 3, 'course:query', '/course/toCoursePage', 1, 0, '', 'admin', '2019-06-19 08:03:43', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (68, '教师端', 0, '0', 3, '#', '#', 1, 0, '', 'admin', '2019-06-09 12:08:02', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (69, '授课安排', 68, '0.68', 3, 'teacherCourse:query', '/teacher/toCourseListPage', 1, 0, '', '开发人员', '2019-06-20 02:13:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (70, '学生名单', 68, '0.68', 3, 'teacherStudent:query', '/teacher/toStudentListPage', 1, 0, '', '开发人员', '2019-06-20 02:37:13', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (71, '修改班级', 19, '0.1.16.19', 2, 'team:update', '#', 1, 0, '', 'admin', '2019-06-11 08:06:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (72, '删除班级', 19, '0.1.16.19', 2, 'team:delete', '#', 1, 0, '', 'admin', '2019-06-11 08:07:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (73, '新增教师', 62, '0.1.38.62', 2, 'teacher:save', '#', 1, 0, '', 'admin', '2019-06-11 10:30:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (74, '修改教师', 62, '0.1.38.62', 2, 'teacher:update', '#', 1, 0, '', 'admin', '2019-06-11 10:31:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (75, '删除教师', 62, '0.1.38.62', 2, 'teacher:delete', '#', 1, 0, '', 'admin', '2019-06-11 10:32:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (76, 'test', 68, '0.68', 3, '#', '#', 1, 0, '', 'admin', '2019-06-13 04:09:36', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (77, 'test1', 0, '0', 3, '#', '#', 1, 0, '', 'admin', '2019-06-13 04:38:12', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (78, 're', 76, '0.68.76', 3, '#', '#', 1, 0, '', 'admin', '2019-06-13 04:38:30', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (79, '新增课程', 67, '0.1.61.67', 2, 'course:save', '#', 1, 0, '', 'admin', '2019-06-19 08:58:27', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (80, '更新课程', 67, '0.1.61.67', 2, 'course:update', '#', 1, 0, '', 'admin', '2019-06-19 08:59:14', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (81, '删除课程', 67, '0.1.61.67', 2, 'course:delete', '#', 1, 0, '', 'admin', '2019-06-19 08:59:50', '127.0.0.1');
INSERT INTO `sys_perm` VALUES (82, '退课操作', 65, '0.55.65', 2, 'studentCourse:delete', '#', 1, 0, '', 'admin', '2019-06-19 12:02:04', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (83, '选课操作', 65, '0.55.65', 2, 'studentCourse:save', '#', 1, 0, '', 'admin', '2019-06-19 12:01:50', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_perm` VALUES (84, '教学公告', 68, '0.68', 3, 'teacher:query', '#', 1, 0, '', '开发人员', '2019-06-20 05:45:48', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '开发人员', 1, '系统开发', 'admin', '2019-05-25 04:30:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (3, '学生', 1, '', 'admin', '2019-05-23 04:51:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (4, '教师', 1, '', 'admin', '2019-05-24 09:29:34', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (31, '超级管理员', 1, '', 'admin', '2019-05-24 10:14:18', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (32, '院系领导', 1, '', 'admin', '2019-05-29 11:12:49', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (33, '导员', 0, '', 'admin', '2019-06-12 16:49:11', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (39, 'test', 0, '', '管理员', '2019-06-20 05:48:27', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perm`;
CREATE TABLE `sys_role_perm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `perm_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 272 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_perm
-- ----------------------------
INSERT INTO `sys_role_perm` VALUES (1, 1, 1, '', '2019-05-24 10:47:03', '');
INSERT INTO `sys_role_perm` VALUES (2, 1, 2, '', '2019-05-17 22:39:44', '');
INSERT INTO `sys_role_perm` VALUES (3, 1, 3, '', '2019-05-18 07:43:40', '');
INSERT INTO `sys_role_perm` VALUES (4, 1, 4, '', '2019-05-18 15:52:36', '');
INSERT INTO `sys_role_perm` VALUES (9, 1, 11, '', '2019-05-22 19:17:44', '');
INSERT INTO `sys_role_perm` VALUES (10, 1, 6, '', '2019-05-22 19:43:40', '');
INSERT INTO `sys_role_perm` VALUES (11, 1, 7, '', '2019-05-22 19:43:45', '');
INSERT INTO `sys_role_perm` VALUES (12, 1, 8, '', '2019-05-22 19:43:50', '');
INSERT INTO `sys_role_perm` VALUES (13, 1, 9, '', '2019-05-22 19:43:55', '');
INSERT INTO `sys_role_perm` VALUES (14, 1, 10, '', '2019-05-22 19:44:02', '');
INSERT INTO `sys_role_perm` VALUES (15, 1, 11, '', '2019-05-22 19:47:14', '');
INSERT INTO `sys_role_perm` VALUES (16, 1, 12, '', '2019-05-22 19:47:18', '');
INSERT INTO `sys_role_perm` VALUES (17, 1, 13, '', '2019-05-22 19:47:32', '');
INSERT INTO `sys_role_perm` VALUES (18, 1, 14, '', '2019-05-22 19:47:38', '');
INSERT INTO `sys_role_perm` VALUES (19, 1, 15, '', '2019-05-23 23:31:14', '');
INSERT INTO `sys_role_perm` VALUES (20, 1, 16, '', '2019-05-24 11:39:40', '');
INSERT INTO `sys_role_perm` VALUES (21, 1, 17, '', '2019-05-24 11:39:47', '');
INSERT INTO `sys_role_perm` VALUES (24, 1, 20, '', '2019-05-24 14:11:00', '');
INSERT INTO `sys_role_perm` VALUES (25, 1, 21, '', '2019-05-24 14:11:05', '');
INSERT INTO `sys_role_perm` VALUES (26, 1, 22, '', '2019-05-24 14:11:10', '');
INSERT INTO `sys_role_perm` VALUES (27, 1, 23, '', '2019-05-25 21:47:04', '');
INSERT INTO `sys_role_perm` VALUES (34, 1, 24, '', '2019-05-26 10:45:33', '');
INSERT INTO `sys_role_perm` VALUES (35, 1, 25, '', '2019-05-28 18:50:21', '');
INSERT INTO `sys_role_perm` VALUES (36, 1, 26, '', '2019-05-28 18:50:28', '');
INSERT INTO `sys_role_perm` VALUES (37, 1, 27, '', '2019-05-28 18:50:34', '');
INSERT INTO `sys_role_perm` VALUES (74, 1, 5, '', '2019-06-02 19:39:36', '');
INSERT INTO `sys_role_perm` VALUES (75, 1, 18, '', '2019-06-02 20:16:57', '');
INSERT INTO `sys_role_perm` VALUES (76, 1, 57, '', '2019-06-03 08:08:19', '');
INSERT INTO `sys_role_perm` VALUES (77, 1, 58, '', '2019-06-03 08:08:25', '');
INSERT INTO `sys_role_perm` VALUES (78, 1, 59, '', '2019-06-03 08:08:32', '');
INSERT INTO `sys_role_perm` VALUES (79, 1, 19, '', '2019-06-03 16:13:59', '');
INSERT INTO `sys_role_perm` VALUES (80, 1, 61, '', '2019-06-09 18:55:22', '');
INSERT INTO `sys_role_perm` VALUES (81, 1, 56, '', '2019-06-09 18:55:52', '');
INSERT INTO `sys_role_perm` VALUES (82, 1, 38, '', '2019-06-09 18:57:12', '');
INSERT INTO `sys_role_perm` VALUES (83, 1, 62, '', '2019-06-09 19:51:47', '');
INSERT INTO `sys_role_perm` VALUES (84, 1, 63, '', '2019-06-09 19:51:54', '');
INSERT INTO `sys_role_perm` VALUES (85, 1, 64, '', '2019-06-09 19:51:59', '');
INSERT INTO `sys_role_perm` VALUES (86, 1, 65, '', '2019-06-09 20:14:00', '');
INSERT INTO `sys_role_perm` VALUES (87, 1, 66, '', '2019-06-09 20:14:07', '');
INSERT INTO `sys_role_perm` VALUES (88, 1, 67, '', '2019-06-09 20:14:13', '');
INSERT INTO `sys_role_perm` VALUES (89, 1, 68, '', '2019-06-09 20:14:22', '');
INSERT INTO `sys_role_perm` VALUES (90, 1, 69, '', '2019-06-09 20:14:27', '');
INSERT INTO `sys_role_perm` VALUES (91, 1, 70, '', '2019-06-09 20:14:33', '');
INSERT INTO `sys_role_perm` VALUES (92, 1, 55, '', '2019-06-09 20:15:20', '');
INSERT INTO `sys_role_perm` VALUES (96, 4, 68, 'admin', '2019-06-09 14:22:57', '127.0.0.1');
INSERT INTO `sys_role_perm` VALUES (97, 4, 69, 'admin', '2019-06-09 14:22:57', '127.0.0.1');
INSERT INTO `sys_role_perm` VALUES (98, 4, 70, 'admin', '2019-06-09 14:22:57', '127.0.0.1');
INSERT INTO `sys_role_perm` VALUES (125, 1, 71, '', '2019-06-11 16:08:36', '');
INSERT INTO `sys_role_perm` VALUES (126, 1, 72, '', '2019-06-11 16:08:41', '');
INSERT INTO `sys_role_perm` VALUES (127, 1, 60, '', '2019-06-11 16:25:04', '');
INSERT INTO `sys_role_perm` VALUES (128, 1, 73, '', '2019-06-11 18:32:37', '');
INSERT INTO `sys_role_perm` VALUES (129, 1, 74, '', '2019-06-11 18:32:48', '');
INSERT INTO `sys_role_perm` VALUES (130, 1, 75, '', '2019-06-11 18:32:53', '');
INSERT INTO `sys_role_perm` VALUES (137, 1, 79, '', '2019-06-19 17:00:35', '');
INSERT INTO `sys_role_perm` VALUES (138, 1, 80, '', '2019-06-19 17:00:42', '');
INSERT INTO `sys_role_perm` VALUES (139, 1, 81, '', '2019-06-19 17:00:48', '');
INSERT INTO `sys_role_perm` VALUES (140, 1, 82, '', '2019-06-19 20:02:49', '');
INSERT INTO `sys_role_perm` VALUES (141, 1, 83, '', '2019-06-19 20:02:56', '');
INSERT INTO `sys_role_perm` VALUES (142, 3, 55, 'admin', '2019-06-19 12:03:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (143, 3, 65, 'admin', '2019-06-19 12:03:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (144, 3, 82, 'admin', '2019-06-19 12:03:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (145, 3, 83, 'admin', '2019-06-19 12:03:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (146, 3, 66, 'admin', '2019-06-19 12:03:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (176, 31, 1, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (177, 31, 2, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (178, 31, 3, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (179, 31, 5, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (180, 31, 6, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (181, 31, 13, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (182, 31, 14, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (183, 31, 23, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (184, 31, 24, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (185, 31, 16, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (186, 31, 17, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (187, 31, 20, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (188, 31, 21, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (189, 31, 22, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (190, 31, 18, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (191, 31, 57, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (192, 31, 58, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (193, 31, 59, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (194, 31, 19, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (195, 31, 38, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (196, 31, 62, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (197, 31, 73, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (198, 31, 74, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (199, 31, 75, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (200, 31, 56, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (201, 31, 63, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (202, 31, 64, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (203, 31, 61, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (204, 31, 67, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (205, 31, 79, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (206, 31, 80, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (207, 31, 81, '开发人员', '2019-06-20 02:28:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (240, 39, 1, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (241, 39, 2, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (242, 39, 3, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (243, 39, 5, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (244, 39, 6, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (245, 39, 13, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (246, 39, 14, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (247, 39, 23, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (248, 39, 24, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (249, 39, 16, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (250, 39, 17, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (251, 39, 20, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (252, 39, 21, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (253, 39, 22, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (254, 39, 18, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (255, 39, 57, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (256, 39, 58, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (257, 39, 59, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (258, 39, 19, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (259, 39, 38, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (260, 39, 62, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (261, 39, 73, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (262, 39, 74, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (263, 39, 75, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (264, 39, 56, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (265, 39, 63, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (266, 39, 64, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (267, 39, 61, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (268, 39, 67, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (269, 39, 79, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (270, 39, 80, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_perm` VALUES (271, 39, 81, '管理员', '2019-06-20 05:53:46', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1, 1, 'admin', '2019-06-03 07:31:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (42, 32, 13, 'admin', '2019-06-11 06:38:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (43, 32, 12, 'admin', '2019-06-11 06:38:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (44, 32, 11, 'admin', '2019-06-11 06:38:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (45, 32, 10, 'admin', '2019-06-11 06:38:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (46, 32, 9, 'admin', '2019-06-11 06:38:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (47, 33, 19, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (48, 33, 18, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (49, 33, 17, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (50, 33, 16, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (51, 33, 15, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (52, 33, 14, 'admin', '2019-06-11 06:39:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (95, 3, 87, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (96, 3, 86, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (97, 3, 85, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (98, 3, 84, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (99, 3, 83, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (100, 3, 66, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (101, 3, 65, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (102, 3, 64, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (103, 3, 63, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (104, 3, 2, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (105, 3, 3, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (106, 3, 4, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (107, 3, 5, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (108, 3, 6, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (109, 3, 7, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (110, 3, 8, 'admin', '2019-06-19 13:54:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (112, 31, 88, 'admin', '2019-06-19 14:12:08', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (113, 3, 92, '开发人员', '2019-06-19 14:31:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (114, 4, 27, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (115, 4, 90, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (116, 4, 34, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (117, 4, 33, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (118, 4, 32, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (119, 4, 31, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (120, 4, 30, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (121, 4, 28, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (122, 4, 29, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (123, 4, 20, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (124, 4, 21, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (125, 4, 24, '开发人员', '2019-06-20 02:19:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (126, 39, 92, '管理员', '2019-06-20 05:54:02', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号：学号或者是工号',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加密的盐',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `nation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `entime` date NULL DEFAULT NULL COMMENT '入校时间',
  `politics_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `id_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `sdept_id` int(11) NULL DEFAULT NULL COMMENT '所属院系id',
  `major_id` int(11) NULL DEFAULT NULL COMMENT '所属专业id',
  `team_id` int(11) NULL DEFAULT NULL COMMENT '所属班级id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stuno`(`number`) USING BTREE,
  UNIQUE INDEX `IDnumber`(`id_number`) USING BTREE,
  INDEX `sdept`(`sdept_id`) USING BTREE,
  INDEX `major`(`major_id`) USING BTREE,
  INDEX `team_id`(`team_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`sdept_id`) REFERENCES `t_sdept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_ibfk_3` FOREIGN KEY (`team_id`) REFERENCES `t_team` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1', '94CF9419571A7CD08EB5EB81DBAD2A21', 'csMaC', '开发人员', '男', 22, '汉族', '2015-09-01', '党员', '5002281986080300', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '2015025781', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '卜青', '男', 21, '汉族', '2015-09-01', '共青团员', '500228199506281798', 11, 2, 1);
INSERT INTO `sys_user` VALUES (3, '2015025782', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '刘嘉奇', '男', 22, '汉族', '2015-09-01', '共青团员', '500228199506281799', 11, 2, 1);
INSERT INTO `sys_user` VALUES (4, '2015025783', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '唐君辉', '男', 22, '汉族', '2015-09-01', '共青团员', '500228199506281898', 11, 2, 1);
INSERT INTO `sys_user` VALUES (5, '2015025784', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '何春城', '男', 22, '汉族', '2015-09-01', '共青团员', '500228199508281798', 11, 2, 1);
INSERT INTO `sys_user` VALUES (6, '2015025785', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '张永志', '男', 22, '汉族', '2015-09-01', '共青团员', '500228199506291798', 11, 2, 1);
INSERT INTO `sys_user` VALUES (7, '2015025786', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '何玉', '女', 23, '汉族', '2015-09-01', '共青团员', '500228199506181798', 11, 2, 1);
INSERT INTO `sys_user` VALUES (8, '2015025787', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '陈小青', '女', 21, '汉族', '2015-09-01', '共青团员', '500228199503221798', 11, 2, 1);
INSERT INTO `sys_user` VALUES (9, '2001124437', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '院长1', '男', 46, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, '2001144536', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '院长2', '女', 48, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, '2001462578', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '院长3', '女', 48, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, '2001462579', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '院长4', '男', 40, '朝鲜族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, '2001462574', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '院长5', '男', 48, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (14, '2001540025', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员1', '女', 48, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (15, '1925480253', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员2', '男', 36, '维吾尔族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (16, '1925480254', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员3', '女', 35, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (17, '1925480251', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员4', '男', 34, '汉族', '2001-09-03', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (18, '8524562223', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员5', '男', 32, '满族', '2019-06-04', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (19, '5192548025', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '导员6', '女', 35, '汉族', '2019-06-04', '党员', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (20, '1925480246', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '教师1', '男', 36, '汉族', '2019-06-05', '党员', '5002281986080310', 11, NULL, NULL);
INSERT INTO `sys_user` VALUES (21, '1925480256', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '教师21', '女', 42, '汉族', '2019-06-04', '群众', '5002281986080440', 15, NULL, NULL);
INSERT INTO `sys_user` VALUES (24, '1925423256', 'a8267dc5f5725f9bc73ddff00b766e52', 'query', '教师5', '男', 43, '汉族', '2019-06-01', '党员', '5002281976080300', 15, NULL, NULL);
INSERT INTO `sys_user` VALUES (27, '3', '2393E9E845EFB3CD44A155E7544AF9F6', 'FPgVt', '李玉', '女', 33, '汉族', '2019-05-29', '党员', '5115121131', 11, NULL, NULL);
INSERT INTO `sys_user` VALUES (28, '2019171280', '2EDD456AFB94B55414231996874AF38F', '2bUlU', '倪好', '女', 46, '汉族', '2019-06-11', '党员', '1561651', 12, NULL, NULL);
INSERT INTO `sys_user` VALUES (29, '2019198852', '1E3C0290889C2571CAD009A38009E6B7', 'CCCsI', '倪云月', '女', 32, '汉族', '2019-06-11', '党员', '12551313', 12, NULL, NULL);
INSERT INTO `sys_user` VALUES (30, '2019188873', 'EBBC583FAA904037F0F2D686DBA766F9', 'OWiY0', '王悦', '女', 32, '汉族', '2019-06-11', '党员', '45896147', 11, NULL, NULL);
INSERT INTO `sys_user` VALUES (31, '2019154913', '252B01ED76701FBBACDA83353E328437', '9m1VI', '吴新宇', '男', 41, '汉族', '2019-06-11', '党员', '54436434543535', 11, NULL, NULL);
INSERT INTO `sys_user` VALUES (32, '2019182975', 'B45A6F2EC8793582DBDA5B865FA495A4', 'eNS7h', '李欣玥', '女', 30, '汉族', '2019-06-11', '党员', '3425254254365635635', 11, NULL, NULL);
INSERT INTO `sys_user` VALUES (33, '2019185188', '4F71C11D2EB8B87A4057E4958F1A9777', 't6BmR', '王玥', '女', 26, '汉族', '2019-06-13', '党员', '757275727272772', 13, NULL, NULL);
INSERT INTO `sys_user` VALUES (34, '2019187070', 'E1C4209CE4A0CC61BB37A95F63EEB5B6', 'Bk8JB', '柳月', '女', 22, '汉族', '2019-06-13', '党员', '23423543654653', 13, NULL, NULL);
INSERT INTO `sys_user` VALUES (63, '2019254492', '84C1295AFC6F9DCEE09BD9268A8E9967', '2zJmf', '张三1', '男', 15, '汉族', '2019-06-19', '共青团员', '11', 11, 10, 7);
INSERT INTO `sys_user` VALUES (64, '201928997', 'DB8382DC10358C59D50B11D3C4227415', '5bhGE', '张三2', '男', 16, '汉族', '2019-06-19', '共青团员', '22', 11, 10, 7);
INSERT INTO `sys_user` VALUES (65, '2019274270', '0DC0B649330CA1BB2542D52E9D71FF27', 'laNK4', '李四1', '男', 15, '汉族', '2019-06-19', '共青团员', '224343', 11, 10, 7);
INSERT INTO `sys_user` VALUES (66, '201928558', '453A949CD65E13C2622B6E7D1024B014', '3x1Vx', '李四2', '男', 16, '汉族', '2019-06-19', '共青团员', '23243545', 11, 10, 7);
INSERT INTO `sys_user` VALUES (67, '201923125', 'A9259DAE01602FF36ACBF730C6D78049', '2oL8q', '王宇1', '男', 18, '汉族', '2019-06-19', '共青团员', '3048498595859', 11, 10, 8);
INSERT INTO `sys_user` VALUES (68, '201921677', 'A432A4415F6D0985DD1AE558B129AFCE', 'BcjjD', '王宇2', '男', 18, '汉族', '2019-06-19', '共青团员', '6232656565988', 11, 10, 8);
INSERT INTO `sys_user` VALUES (77, '201924278', 'D2DA6EB5CD6BA635F769BB398733E6C2', '2VGhO', '王宇1', '男', 18, '汉族', '2019-06-19', '共青团员', '673048498595859', 11, 10, 8);
INSERT INTO `sys_user` VALUES (78, '2019204547', '0D6A4401F9A9B8C9B7F1B2DB99EB3672', 'uF55K', '王宇2', '男', 18, '汉族', '2019-06-19', '共青团员', '623265656598867', 11, 10, 8);
INSERT INTO `sys_user` VALUES (83, '2019250531', '2547208411E57FD2EECE1D4D89E64510', 'UHuEJ', '王宇1', '男', 18, '汉族', '2019-06-19', '共青团员', '673048498595854', 11, 10, 8);
INSERT INTO `sys_user` VALUES (84, '201920526', '735FB35323DF0354213677AE84F680B5', '6NiO5', '王宇2', '男', 18, '汉族', '2019-06-19', '共青团员', '623265656598865', 11, 10, 8);
INSERT INTO `sys_user` VALUES (85, '201922204', '80DCB66FD8A12C8674FF3B4E073CF177', 'tcR6Y', '李玉1', '女', 20, '汉族', '2019-06-19', '共青团员', '2345', 11, 10, 8);
INSERT INTO `sys_user` VALUES (86, '201920281', '42FFCE94D05CA17CDEC3AEECD1385AF1', 'IsPKM', '李玉2', '女', 20, '汉族', '2019-06-19', '共青团员', '2346', 11, 10, 8);
INSERT INTO `sys_user` VALUES (87, '2019241448', '0829872C0A012CC79A02777574AE19DF', 'ghchz', '李华', '男', 23, '汉族', '2019-06-19', '共青团员', '50022819960628', 11, 2, 1);
INSERT INTO `sys_user` VALUES (88, '2', 'F94A9ABF54A8A7E56B30B5D497E6DF70', 'ENBto', '管理员', '男', 35, '汉族', '2019-06-05', '党员', '515412154515155', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (90, '3201256855', '2393E9E845EFB3CD44A155E7544AF9F6', 'FPgVt', '李小月', '女', 25, '汉族', '2019-06-19', '党员', '7896543210000', 13, NULL, NULL);
INSERT INTO `sys_user` VALUES (92, '2015025780', '649557BC5A7DE0BFBF28D960E795F145', '3IU02', '游锦华', '男', 23, '汉族', '2019-06-19', '共青团员', '500228199606281797', 11, 2, 1);

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `courseno` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程号',
  `major_id` int(11) NULL DEFAULT NULL COMMENT '所属专业id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `credit` int(11) NULL DEFAULT NULL COMMENT '学分',
  `period` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学时',
  `starttime` date NULL DEFAULT NULL COMMENT '开课时间',
  `endtime` date NULL DEFAULT NULL COMMENT '节课时间',
  `classroom` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上课教室',
  `arrange` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程安排',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '上课老师id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `courseno`(`courseno`) USING BTREE,
  INDEX `teacher`(`teacher_id`) USING BTREE,
  INDEX `major_id`(`major_id`) USING BTREE,
  CONSTRAINT `t_course_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_course_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES (1, '10001', 2, 'Python爬虫技巧', 2, '36', '2019-06-13', '2019-08-10', '科401', NULL, 27);
INSERT INTO `t_course` VALUES (2, '10002', 2, '数据结构', 6, '52', '2019-06-25', '2019-06-22', 'X402', NULL, 28);
INSERT INTO `t_course` VALUES (3, '10003', 2, 'Java程序设计', 3, '52', '2019-06-25', '2019-06-28', 'X302', NULL, 29);
INSERT INTO `t_course` VALUES (4, '10004', 10, '英语', 3, '48', '2019-06-07', '2019-06-20', '科401', NULL, 28);
INSERT INTO `t_course` VALUES (5, '10005', 2, 'C语言程序设计', 3, '36', '2019-06-01', '2019-06-14', '科304', NULL, 30);

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名称',
  `sdept_id` int(11) NULL DEFAULT NULL COMMENT '所属院系id',
  `leader_id` int(11) NULL DEFAULT NULL COMMENT '辅导员id',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '专业描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sdept_id`(`sdept_id`) USING BTREE,
  INDEX `leader_id`(`leader_id`) USING BTREE,
  CONSTRAINT `t_major_ibfk_1` FOREIGN KEY (`sdept_id`) REFERENCES `t_sdept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_major_ibfk_2` FOREIGN KEY (`leader_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES (2, '软件工程', 11, 7, NULL);
INSERT INTO `t_major` VALUES (3, '物联网', 11, 8, NULL);
INSERT INTO `t_major` VALUES (4, '音乐表演', 20, 7, NULL);
INSERT INTO `t_major` VALUES (8, '钢琴系', 20, 7, NULL);
INSERT INTO `t_major` VALUES (9, '流行音乐系', 20, 8, NULL);
INSERT INTO `t_major` VALUES (10, '网络工程', 11, 3, NULL);
INSERT INTO `t_major` VALUES (12, '计算机科学与技术', 11, 2, NULL);

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `grade` int(11) NULL DEFAULT NULL COMMENT '成绩',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '上分时间',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `t_score_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_score_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_score_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES (8, 2, 87, NULL, NULL, 28);
INSERT INTO `t_score` VALUES (13, 4, 92, NULL, NULL, 28);
INSERT INTO `t_score` VALUES (14, 5, 92, NULL, NULL, 30);
INSERT INTO `t_score` VALUES (15, 1, 92, NULL, NULL, 27);
INSERT INTO `t_score` VALUES (16, 2, 92, NULL, NULL, 28);

-- ----------------------------
-- Table structure for t_sdept
-- ----------------------------
DROP TABLE IF EXISTS `t_sdept`;
CREATE TABLE `t_sdept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院系名称',
  `leader_id` int(11) NULL DEFAULT NULL COMMENT '院系负责人id',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `leader_id`(`leader_id`) USING BTREE,
  CONSTRAINT `t_sdept_ibfk_1` FOREIGN KEY (`leader_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sdept
-- ----------------------------
INSERT INTO `t_sdept` VALUES (11, '计算机学院', 2, '023-56666');
INSERT INTO `t_sdept` VALUES (12, '管理学院', 3, '023-56666');
INSERT INTO `t_sdept` VALUES (13, '经济学院', 4, '023-56666');
INSERT INTO `t_sdept` VALUES (14, '通信学院', 5, '023-56666');
INSERT INTO `t_sdept` VALUES (15, '理学院', 6, '023-56666');
INSERT INTO `t_sdept` VALUES (16, '外国语学院', 7, '023-56666');
INSERT INTO `t_sdept` VALUES (17, '传媒学院', 8, '023-56666');
INSERT INTO `t_sdept` VALUES (18, '文学院', 8, '023-56666');
INSERT INTO `t_sdept` VALUES (19, '法学院', 6, '023-56666');
INSERT INTO `t_sdept` VALUES (20, '音乐学院', 7, '023-56666');
INSERT INTO `t_sdept` VALUES (24, '中文系', 3, '023');

-- ----------------------------
-- Table structure for t_team
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `number` int(11) NULL DEFAULT NULL COMMENT '班级总人数',
  `major_id` int(30) NULL DEFAULT NULL COMMENT '所属专业id',
  `teacher_id` int(30) NULL DEFAULT NULL COMMENT '班主任id',
  `grade` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_id`(`major_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `t_team_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_team_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_team
-- ----------------------------
INSERT INTO `t_team` VALUES (1, '软件15-2', 0, 2, 20, '15');
INSERT INTO `t_team` VALUES (2, '软件15-1', 0, 2, 21, '15');
INSERT INTO `t_team` VALUES (3, '软件15-3', 0, 2, 24, '15');
INSERT INTO `t_team` VALUES (4, '软件14-1', 0, 2, 21, '14');
INSERT INTO `t_team` VALUES (6, '物联15-3', 0, 3, 20, '15');
INSERT INTO `t_team` VALUES (7, '网络14-2', 0, 10, 20, '14');
INSERT INTO `t_team` VALUES (8, '网络15-1', 0, 10, 20, '15');

SET FOREIGN_KEY_CHECKS = 1;
