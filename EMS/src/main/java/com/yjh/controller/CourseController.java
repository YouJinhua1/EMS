package com.yjh.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjh.adpter.CourseAdpter;
import com.yjh.common.ResultData;
import com.yjh.domain.Course;
import com.yjh.domain.Major;
import com.yjh.service.CourseService;
import com.yjh.service.MajorService;
import com.yjh.service.UserService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Resource
	CourseService courseService;
	
	@Resource
	UserService userService;
	
	@Resource
	MajorService majorService;
	
	@RequiresPermissions("course:query")
	@RequestMapping("/toCoursePage")
	public String toStudentPage(Model model) {
		model.addAttribute("include","/admin/course");
		return "success";
	}

	@RequiresPermissions("course:query")
	@RequestMapping("/courseList")
	@ResponseBody
	public ResultData courseList() {
		List<Course> courseList=courseService.selectCourseList();
		List<CourseAdpter> courseAdpterList=new ArrayList<CourseAdpter>();
		for(Course course:courseList) {
			Major major=majorService.selectMajorById(course.getMajorId());
			String teacherName=userService.selectUserNameById(course.getTeacherId());
			CourseAdpter courseAdpter=CourseAdpter.getCourseAdpter(course);
			courseAdpter.setMajorName(major.getName());
			courseAdpter.setTeacherName(teacherName);
			courseAdpterList.add(courseAdpter);
		}
		return 	ResultData.success(courseAdpterList);
	}
	
}
