package com.yjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjh.domain.Score;
import com.yjh.mapper.ScoreMapper;
import com.yjh.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Resource
	ScoreMapper scoreMapper;

	@Override
	public List<Integer> selectCourseIdByStudentId(Integer id) {
		return scoreMapper.selectCourseIdByStudentId(id);
	}

	@Override
	public int saveScore(Score score) {
		return scoreMapper.insertSelective(score);
	}

	@Override
	public int deleteByStudentIdAndCourseId(Integer courseId, Integer id) {
		return scoreMapper.deleteByStudentIdAndCourseId(courseId,id);
	}

	@Override
	public List<Integer> selecteByTeacherId(Integer id) {
		return scoreMapper.selecteByTeacherId(id);
	}

	
}
