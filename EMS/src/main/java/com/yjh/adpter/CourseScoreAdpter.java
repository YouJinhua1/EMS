package com.yjh.adpter;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.yjh.domain.Course;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CourseScoreAdpter extends Course{

	private String teacherName;
	private Integer grade;
	private Date updateTime;
	private String studentName;
	
	public static CourseScoreAdpter getCourseScoreAdpter(Course course) {
		CourseScoreAdpter courseScoreAdpter=new CourseScoreAdpter();
		BeanUtils.copyProperties(course, courseScoreAdpter);
		return courseScoreAdpter;
	}
}
