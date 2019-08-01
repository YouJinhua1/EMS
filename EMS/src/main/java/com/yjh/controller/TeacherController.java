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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.CourseAdpter;
import com.yjh.adpter.CourseScoreAdpter;
import com.yjh.adpter.TeamAdpter;
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
@RequestMapping("/teacher")
public class TeacherController {
	
	@Resource
	UserService userService;
	
	@Resource
	SdeptService sdeptService;
	
	@Resource
	MajorService majorService;
	
	@Resource
	TeamService teamService;
	
	@Resource
	ScoreService scoreService;
	
	@Resource
	CourseService courseService;
	
	@RequiresPermissions("teacher:query")
	@RequestMapping("/toTeacherPage")
	public String queryCourseList(Model model) {
		model.addAttribute("include","/admin/teacher");
		return "success";
	}
	@RequiresPermissions("teacher:query")
	@RequestMapping("/teacherList")
	@ResponseBody
	public ResultData teacherList() {
		List<SysUser> teacherList=userService.selectTeacherList();
		List<UserAdpter> teacherAdpterList=new ArrayList<UserAdpter>();
		for(SysUser teacher:teacherList) {
			Sdept sdept=sdeptService.selectSdeptById(teacher.getSdeptId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(teacher);
			userAdpter.setSdeptName(sdept.getName());
			teacherAdpterList.add(userAdpter);
		}
		return 	ResultData.success(teacherAdpterList);
	}
	
	@RequiresPermissions("teacher:query")
	@RequestMapping("/loadSdept")
	@ResponseBody
	public ResultData loadSdept() {
		List<Sdept> sdeptList=sdeptService.getSdeptList();
		if(sdeptList!=null&&sdeptList.size()>0) {
			return ResultData.success(sdeptList);
		}else {
			return ResultData.fail("加载院系数据失败！");
		}
	}
	
	@RequiresPermissions("teacher:save")
	@RequestMapping("/saveTeacher")
	@ResponseBody
	public ResultData saveTeacher(SysUser teacher, HttpServletRequest request) {
		//使用工具类生成教师工号
		String number=NumberUtils.getTeacherNumber().trim();
		//使用工具类生成加密盐
		String salt=MD5Util.getRandomString(5).trim();
		//盐+密码再加密
		String password=MD5Util.getMD5(salt+number);
		//设置工号
		teacher.setNumber(number);
		//设置加密盐
		teacher.setSalt(salt);
		//设置加密后的密码
		teacher.setPassword(password);
		//设置入职时间
		teacher.setEntime(new Date());;
		
		int x=userService.saveTeacher(teacher,request);
		if(x>0) {
			return ResultData.success("添加成功！");
		}else {
			return ResultData.fail("添加失败！");
		}
	}
	@RequiresPermissions("teacher:query")
	@RequestMapping("/queryTeacherBySdeptId")
	@ResponseBody
	public ResultData queryTeacherBySdeptId(@RequestParam("sdeptId")Integer sdeptId) {
		List<SysUser> teacherList=userService.queryTeacherBySdeptId(sdeptId);
		if(!(teacherList!=null&&teacherList.size()>0)){
			return ResultData.fail("该院系暂时还没有任何教师！");
		}
		List<UserAdpter> teacherAdpterList=new ArrayList<UserAdpter>();
		for(SysUser teacher:teacherList) {
			Sdept sdept=sdeptService.selectSdeptById(teacher.getSdeptId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(teacher);
			userAdpter.setSdeptName(sdept.getName());
			teacherAdpterList.add(userAdpter);
		}
		return 	ResultData.success(teacherAdpterList);
	}
	@RequiresPermissions("teacher:query")
	@RequestMapping("/queryTeacherByName")
	@ResponseBody
	public ResultData queryTeacherByName(@RequestParam("name") String name) {
		List<SysUser> teacherList=userService.queryTeacherByName(name);
		if(!(teacherList!=null&&teacherList.size()>0)){
			return ResultData.fail("没有查到任何有关："+name+"的教师信息！");
		}
		List<UserAdpter> teacherAdpterList=new ArrayList<UserAdpter>();
		for(SysUser teacher:teacherList) {
			Sdept sdept=sdeptService.selectSdeptById(teacher.getSdeptId());
			UserAdpter userAdpter=UserAdpter.getUserAdpter(teacher);
			userAdpter.setSdeptName(sdept.getName());
			teacherAdpterList.add(userAdpter);
		}
		return 	ResultData.success(teacherAdpterList);
	}
	@RequiresPermissions("teacher:delete")
	@RequestMapping("/deleteTeacher")
	@ResponseBody
	public ResultData deleteTeacher(@RequestParam("id")Integer teamId) {
			int x=userService.deleteTeacherById(teamId);
			if(x>0) {
				return ResultData.success("删除成功！");
			}else {
				return ResultData.fail("删除失败！");
			}
	}
	@RequiresPermissions("teacher:update")
	@RequestMapping("/findTeacherById")
	@ResponseBody
	public ResultData findTeacherById(@RequestParam("data")Integer data) {
		SysUser teacher=userService.findTeacherById(data);
		if(teacher!=null) {
			return ResultData.success(teacher);
		}else {
			return ResultData.fail("该教师已经离职了！");
		}
	}
	
	@RequiresPermissions("teacher:update")
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public ResultData updateTeacher(SysUser teacher) {
		int x=userService.updateTeacher(teacher);
		if(x>0) {
			return ResultData.success("更新成功！");
		}else {
			return ResultData.fail("更新失败！");
		}
	}
	
	@RequiresPermissions("teacherCourse:query")
	@RequestMapping("/toCourseListPage")
	public String toCourseListPage(Model model) {
		model.addAttribute("include","/teacher/courseList");
		return "success";
	}
	@RequiresPermissions("teacherStudent:query")
	@RequestMapping("/toStudentListPage")
	public String toStudentListPage(Model model) {
		model.addAttribute("include","/teacher/student");
		return "success";
	}
	
	@RequiresPermissions("teacherStudent:query")
	@RequestMapping("/studentList")
	@ResponseBody
	public ResultData studentList(HttpSession session) {
		SysUser user =(SysUser)session.getAttribute("user");
		List<Integer> studentIdList=scoreService.selecteByTeacherId(user.getId());
		List<SysUser> studentList=userService.selectStudentListByStudentIds(studentIdList);
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
	/**
	 * @RequiresPermissions("studentCourse:query")
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
	 */
	@RequiresPermissions("teacherCourse:query")
	@RequestMapping("/courseList")
	@ResponseBody
	public ResultData courseList(HttpSession session) {
		SysUser user =(SysUser)session.getAttribute("user");
		List<Course> courseList=courseService.selectCourseListByTeacherId(user.getId());
		if(!(courseList.size()>0)) {
			return ResultData.fail("您暂时还没有任何教学任务！");
		}else {
			return ResultData.success(courseList);
		}
	}
	/*@RequiresPermissions("teacherCourse:query")
	@RequestMapping("/courseList")
	@ResponseBody
	public ResultData courseList(HttpSession session) {
		SysUser user =(SysUser)session.getAttribute("user");
		List<Course> courseList=scoreService.selecteByTeacherId(user.getId());
		List<CourseScoreAdpter> csAdpterList=new ArrayList<CourseScoreAdpter>();
		for(Score score:scoreList) {
			Course course=courseService.selecteByTeacherId();
			CourseScoreAdpter  csAdpter=CourseScoreAdpter.getCourseScoreAdpter(course);
			
			
		}
		
		
	}*/
}
