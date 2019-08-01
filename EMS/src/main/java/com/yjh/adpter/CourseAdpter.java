package com.yjh.adpter;

import org.springframework.beans.BeanUtils;

import com.yjh.domain.Course;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CourseAdpter extends Course{
	
	private String majorName;
	
	private String teacherName;
	
	private boolean selectStatus=false;
	
	public static CourseAdpter getCourseAdpter(Course course) {
		CourseAdpter courseAdpter=new CourseAdpter();
		BeanUtils.copyProperties(course, courseAdpter);
		return courseAdpter;
	}
}
