package com.yjh.service;

import java.util.List;

import com.yjh.domain.Course;

public interface CourseService {

	List<Course> selectCourseList();

	List<Course> queryCourseByName(String name);

	List<Course> queryCourseByMajorId(String majorId);

	Course selecteByTeacherId();

	List<Course> selectCourseListByTeacherId(Integer id);

}
