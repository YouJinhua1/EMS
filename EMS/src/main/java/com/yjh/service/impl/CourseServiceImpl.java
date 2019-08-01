package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.Course;
import com.yjh.mapper.CourseMapper;
import com.yjh.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	CourseMapper courseMapper;
	@Override
	public List<Course> selectCourseList() {
		return courseMapper.selectCourseList();
	}
	@Override
	public List<Course> queryCourseByName(String name) {
		return courseMapper.queryCourseByName(name);
	}
	@Override
	public List<Course> queryCourseByMajorId(String majorId) {
		return courseMapper.queryCourseByMajorId(majorId);
	}
	
	@Override
	public Course selecteByTeacherId() {
		return null;//TODO
	}
	@Override
	public List<Course> selectCourseListByTeacherId(Integer id) {
		return courseMapper.selectCourseListByTeacherId(id);
	}
	
	
}
