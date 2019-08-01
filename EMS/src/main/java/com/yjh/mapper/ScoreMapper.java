package com.yjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjh.domain.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

	List<Integer> selectCourseIdByStudentId(Integer id);

	int deleteByStudentIdAndCourseId(@Param("courseId")Integer courseId, @Param("studentId")Integer id);

	List<Integer> selecteByTeacherId(@Param("teacherId") Integer id);
}