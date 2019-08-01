package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.Course;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

	List<Course> selectCourseList();

	List<Course> queryCourseByName(@Param("name")String name);

	List<Course> queryCourseByMajorId(@Param("majorId")String majorId);

	List<Course> selectCourseListByTeacherId(@Param("teacherId")Integer id);
}