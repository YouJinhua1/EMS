package com.yjh.service;

import java.util.List;

import com.yjh.domain.Score;

public interface ScoreService {

	List<Integer> selectCourseIdByStudentId(Integer id);

	int saveScore(Score score);

	int deleteByStudentIdAndCourseId(Integer courseId, Integer id);

	List<Integer> selecteByTeacherId(Integer id);

}
