package com.yjh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.CourseAdpter;
import com.yjh.adpter.UserAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.Course;
import com.yjh.domain.Major;
import com.yjh.domain.Score;
import com.yjh.domain.Sdept;
import com.yjh.domain.SysUser;
import com.yjh.domain.Team;
import com.yjh.service.CourseService;
import com.yjh.service.MajorService;
import com.yjh.service.ScoreService;
import com.yjh.service.SdeptService;
import com.yjh.service.TeamService;
import com.yjh.service.UserService;
import com.yjh.utils.MD5Util;
import com.yjh.utils.NumberUtils;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Resource
	UserService userService;
	
	@Resource
	SdeptService sdeptService;
	
	@Resource
	MajorService majorService;
	
	@Resource
	TeamService teamService;
	
	@Resource
	CourseService courseService;
	
	@Resource
	ScoreService scoreService;
	
	@RequiresPermissions("student:query")
	@RequestMapping("/toStudentPage")
	public String toStudentPage(Model model) {
		model.addAttribute("include","/admin/student");
		return "success";
	}
	
	@RequiresPermissions("student:query")
	@RequestMapping("/toStudentAddPage")
	public String toStudentAddPage(Model model) {
		model.addAttribute("include","/admin/studentAdd");
		return "success";
	}
	@RequiresPermissions("student:query")
	@RequestMapping("/studentList")
	@ResponseBody
	public ResultData studentList() {
		List<SysUser> studentList=userService.selectStudentList();
		List<UserAdpter> studentAdpterList=new ArrayList<UserAdpter>();
		for(SysUser student:studentList) {
			Sdept sdept=sdeptService.selectSdeptById(student.getSdeptId());
			Major major=majorService.selectMajorById(student.getMajorId());
			Team team=teamService.selectTeamById(student.getTeamId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(student);
			userAdpter.setSdeptName(sdept.getName());
			userAdpter.setMajorName(major.getName());
			userAdpter.setTeamName(team.getName());
			studentAdpterList.add(userAdpter);
		}
		return 	ResultData.success(studentAdpterList);
	}
	
	@RequiresPermissions("student:query")
	@RequestMapping("/loadSdept")
	@ResponseBody
	public ResultData loadSdept() {
		List<Sdept> sdeptList=sdeptService.getSdeptList();
		for(Sdept sdept:sdeptList) {
			List<Major> majorList=majorService.loadMajor(sdept.getId());
			if(majorList.size()>0) {
				for(Major major:majorList) {
					List<Team> teamList=teamService.loadTeam(major.getId());
					major.setTeamList(teamList);
				}
			}
			sdept.setMajorList(majorList);
		}
		return 	ResultData.success(sdeptList);
	}
	@RequiresPermissions("student:query")
	@RequestMapping("/queryStudentBySdeptId")
	@ResponseBody
	public ResultData queryStudentBySdeptId(@RequestParam("sdeptId")Integer sdeptId) {
		List<SysUser> studentList=userService.queryStudentBySdeptId(sdeptId);
		if(!(studentList!=null&&studentList.size()>0)){
			return ResultData.fail("该院系暂时还没有任何学生！");
		}
		List<UserAdpter> studentAdpterList=new ArrayList<UserAdpter>();
		for(SysUser student:studentList) {
			Sdept sdept=sdeptService.selectSdeptById(student.getSdeptId());
			Major major=majorService.selectMajorById(student.getMajorId());
			Team team=teamService.selectTeamById(student.getTeamId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(student);
			userAdpter.setSdeptName(sdept.getName());
			userAdpter.setMajorName(major.getName());
			userAdpter.setTeamName(team.getName());
			studentAdpterList.add(userAdpter);
		}
		return 	ResultData.success(studentAdpterList);
	}
	@RequiresPermissions("student:query")
	@RequestMapping("/queryStudentByMajorId")
	@ResponseBody
	public ResultData queryStudentByMajorId(@RequestParam("majorId")Integer majorId) {
		List<SysUser> studentList=userService.queryStudentByMajorId(majorId);
		if(!(studentList!=null&&studentList.size()>0)){
			return ResultData.fail("该专业暂时还没有任何学生！");
		}
		List<UserAdpter> studentAdpterList=new ArrayList<UserAdpter>();
		for(SysUser student:studentList) {
			Sdept sdept=sdeptService.selectSdeptById(student.getSdeptId());
			Major major=majorService.selectMajorById(student.getMajorId());
			Team team=teamService.selectTeamById(student.getTeamId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(student);
			userAdpter.setSdeptName(sdept.getName());
			userAdpter.setMajorName(major.getName());
			userAdpter.setTeamName(team.getName());
			studentAdpterList.add(userAdpter);
		}
		return 	ResultData.success(studentAdpterList);
	}
	@RequiresPermissions("student:query")
	@RequestMapping("/queryStudentByTeamId")
	@ResponseBody
	public ResultData queryStudentByTeamId(@RequestParam("teamId")Integer teamId) {
		List<SysUser> studentList=userService.queryStudentByTeamId(teamId);
		if(!(studentList!=null&&studentList.size()>0)){
			return ResultData.fail("该班级暂时还没有任何学生！");
		}
		List<UserAdpter> studentAdpterList=new ArrayList<UserAdpter>();
		for(SysUser student:studentList) {
			Sdept sdept=sdeptService.selectSdeptById(student.getSdeptId());
			Major major=majorService.selectMajorById(student.getMajorId());
			Team team=teamService.selectTeamById(student.getTeamId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(student);
			userAdpter.setSdeptName(sdept.getName());
			userAdpter.setMajorName(major.getName());
			userAdpter.setTeamName(team.getName());
			studentAdpterList.add(userAdpter);
		}
		return 	ResultData.success(studentAdpterList);
	}
	@RequiresPermissions("student:query")
	@RequestMapping("/queryStudentByName")
	@ResponseBody
	public ResultData queryStudentByName(@RequestParam("name") String name) {
		List<SysUser> studentList=userService.queryStudentByName(name);
		if(!(studentList!=null&&studentList.size()>0)){
			return ResultData.fail("没有查到任何有关："+name+"的学生信息！");
		}
		List<UserAdpter> studentAdpterList=new ArrayList<UserAdpter>();
		for(SysUser student:studentList) {
			Sdept sdept=sdeptService.selectSdeptById(student.getSdeptId());
			Major major=majorService.selectMajorById(student.getMajorId());
			Team team=teamService.selectTeamById(student.getTeamId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(student);
			userAdpter.setSdeptName(sdept.getName());
			userAdpter.setMajorName(major.getName());
			userAdpter.setTeamName(team.getName());
			studentAdpterList.add(userAdpter);
		}
		return 	ResultData.success(studentAdpterList);
	}
	
	@RequiresPermissions("student:save")
	@RequestMapping("/saveStudent")
	@ResponseBody
	public ResultData saveStudent(@RequestBody List<SysUser>  students,HttpServletRequest request) {
		for(SysUser student:students) {
			//使用工具类生成学号
			String number=NumberUtils.getStudentNumber().trim();
			//使用工具类生成加密盐
			String salt=MD5Util.getRandomString(5).trim();
			//盐+密码再加密
			String password=MD5Util.getMD5(salt+number);
			//设置工号
			student.setNumber(number);
			//设置加密盐
			student.setSalt(salt);
			//设置加密后的密码
			student.setPassword(password);
			//设置入职时间
			student.setEntime(new Date());;
		}
		int x=userService.saveStudent(students,request);
		if(x>0) {
			return ResultData.success("添加成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/toSelectCoursePage")
	public String toSelectCoursePage(Model model) {
		model.addAttribute("include","/student/course");
		return "success";
	}
	
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/courseList")
	@ResponseBody
	public ResultData courseList(HttpSession session) {
		List<Course> courseList=courseService.selectCourseList();
		SysUser user =(SysUser) session.getAttribute("user");
		List<Integer> courseIdList=scoreService.selectCourseIdByStudentId(user.getId());
		List<CourseAdpter> courseAdpterList=new ArrayList<CourseAdpter>();
		for(Course course:courseList) {
			Major major=majorService.selectMajorById(course.getMajorId());
			String teacherName=userService.selectUserNameById(course.getTeacherId());
			CourseAdpter courseAdpter=CourseAdpter.getCourseAdpter(course);
			courseAdpter.setMajorName(major.getName());
			courseAdpter.setTeacherName(teacherName);
			if(courseIdList.contains(course.getId())) {
				courseAdpter.setSelectStatus(true);
			}
			courseAdpterList.add(courseAdpter);
		}
		return 	ResultData.success(courseAdpterList);
	}
	
	
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/loadMajor")
	@ResponseBody
	public ResultData loadMajor() {
		List<Major> majorList=majorService.loadMajor();
		if(!(majorList.size()>0)) {
			return 	ResultData.success("没有加载到任何专业信息！");
		}
		return 	ResultData.success(majorList);
	}
	
	
	//选课
	@RequiresPermissions("studentCourse:save")
	@RequestMapping("/saveSelectedCourse")
	@ResponseBody
	public ResultData saveSelectedCourse(@RequestParam("courseId") Integer courseId,@RequestParam("teacherId") Integer teacherId,HttpSession session) {
		SysUser user =(SysUser)session.getAttribute("user");
		Score score=new Score();
		score.setCourseId(courseId);
		score.setStudentId(user.getId());
		score.setTeacherId(teacherId);
		int x=scoreService.saveScore(score);
		if(x>0) {
			return ResultData.success("选课成功！");
		}else {
			return ResultData.fail("选课失败！");
		}
	}
	
	//退课
	@RequiresPermissions("studentCourse:delete")
	@RequestMapping("/deleteSelectedCourse")
	@ResponseBody
	public ResultData deleteSelectedCourse(@RequestParam("courseId") Integer courseId,HttpSession session) {
		SysUser user =(SysUser)session.getAttribute("user");
		int x=scoreService.deleteByStudentIdAndCourseId(courseId,user.getId());
		if(x>0) {
			return ResultData.success("退课成功！");
		}else {
			return ResultData.fail("退课失败！");
		}
		
	}
	
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/queryCourseByName")
	@ResponseBody
	public ResultData queryCourseByName(@RequestParam("name")String name,HttpSession session) {
		List<Course> courseList=courseService.queryCourseByName(name);
		if(!(courseList!=null&&courseList.size()>0)){
			return ResultData.fail("没有找到任何有关："+name+"的课程信息！");
		}
		SysUser user =(SysUser) session.getAttribute("user");
		List<Integer> courseIdList=scoreService.selectCourseIdByStudentId(user.getId());
		List<CourseAdpter> courseAdpterList=new ArrayList<CourseAdpter>();
		for(Course course:courseList) {
			Major major=majorService.selectMajorById(course.getMajorId());
			String teacherName=userService.selectUserNameById(course.getTeacherId());
			CourseAdpter courseAdpter=CourseAdpter.getCourseAdpter(course);
			courseAdpter.setMajorName(major.getName());
			courseAdpter.setTeacherName(teacherName);
			if(courseIdList.contains(course.getId())) {
				courseAdpter.setSelectStatus(true);
			}
			courseAdpterList.add(courseAdpter);
		}
		return 	ResultData.success(courseAdpterList);
	}
	
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/queryCourseByMajorId")
	@ResponseBody
	public ResultData queryCourseByMajorId(@RequestParam("majorId")String majorId,HttpSession session) {
		List<Course> courseList=courseService.queryCourseByMajorId(majorId);
		if(!(courseList!=null&&courseList.size()>0)){
			return ResultData.fail("该专业暂时还没有开设任何课程！");
		}
		SysUser user =(SysUser) session.getAttribute("user");
		List<Integer> courseIdList=scoreService.selectCourseIdByStudentId(user.getId());
		List<CourseAdpter> courseAdpterList=new ArrayList<CourseAdpter>();
		for(Course course:courseList) {
			Major major=majorService.selectMajorById(course.getMajorId());
			String teacherName=userService.selectUserNameById(course.getTeacherId());
			CourseAdpter courseAdpter=CourseAdpter.getCourseAdpter(course);
			courseAdpter.setMajorName(major.getName());
			courseAdpter.setTeacherName(teacherName);
			if(courseIdList.contains(course.getId())) {
				courseAdpter.setSelectStatus(true);
			}
			courseAdpterList.add(courseAdpter);
		}
		return 	ResultData.success(courseAdpterList);
	}
	@RequiresPermissions("studentCourse:query")
	@RequestMapping("/toSelectedCourseList")
	public String toSelectedCourseList(Model model) {
		model.addAttribute("include","/student/selectedCourseList");
		return "success";
	}
}
