package com.yj.hqbz.services.impl.schoolOperation;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.FoodAdditiveMapper;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.services.schoolOperation.FoodAdditiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FootAdditiveServiceImpl implements FoodAdditiveService {
	
	@Resource
	FoodAdditiveMapper foodAdditiveMapper;


	@Override
	public PageInfo<FoodAdditive> getFoodAdditiveList(Map<String, Object> param, int page, int row) {
		List<FoodAdditive> list =  foodAdditiveMapper.getFoodAdditiveList(param);
		PageInfo<FoodAdditive> pg = new PageInfo<>(list);
		return  pg;
	}

	@Override
	@Transactional
	public void insert(FoodAdditive record) {
		 foodAdditiveMapper.insert(record);
	}

	@Override
	@Transactional
	public void insertSelective(FoodAdditive record) {
		 foodAdditiveMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public FoodAdditive selectByPrimaryKey(String id) {
		return foodAdditiveMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(FoodAdditive record) {
		 foodAdditiveMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	@Transactional
	public void updateByPrimaryKey(FoodAdditive record) {
		 foodAdditiveMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<String, Object> getFoodAdditiveUseDetail(String code) {
		return foodAdditiveMapper.getFoodAdditiveUseDetail(code);
	}

}
