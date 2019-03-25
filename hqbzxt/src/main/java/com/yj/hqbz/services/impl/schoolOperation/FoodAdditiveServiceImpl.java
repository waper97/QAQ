package com.yj.hqbz.services.impl.schoolOperation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.FoodAdditiveMapper;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.model.schoolOperation.FoodAdditiveOut;
import com.yj.hqbz.services.schoolOperation.FoodAdditiveService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodAdditiveServiceImpl implements FoodAdditiveService {
	
	@Resource
	FoodAdditiveMapper mapper;

	@Transactional
	public void insertFoodAdditive(FoodAdditive record) {
		record.setId(StringUtil.getUUID());
		record.setCode("A"+DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+StringUtil.getRandomNum(4));
		mapper.insert(record);
	}

	public FoodAdditive selectFoodAdditiveDetailInfo(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void updateFoodAdditive(FoodAdditive record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	
	public PageInfo<FoodAdditive> selectFoodAdditiveListByParam(
			Map<String, Object> param,int page,int rows) {
		PageHelper.startPage(page,rows);
		List<FoodAdditive> list = mapper.selectFoodAdditiveListByParam(param);
		PageInfo<FoodAdditive> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	public List<FoodAdditiveOut> getFoodAdditiveOutListByParam(
			Map<String, Object> param) {
		return mapper.getFoodAdditiveOutListByParam(param);
	}


	@Transactional
	public void deleteFoodAdditive(String id) {
		mapper.deleteByPrimaryKey(id);
	}

}
