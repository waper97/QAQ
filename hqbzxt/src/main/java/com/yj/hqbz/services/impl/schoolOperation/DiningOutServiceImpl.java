/**   
* 
*/
package com.yj.hqbz.services.impl.schoolOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.DiningOutMapper;
import com.yj.hqbz.mapper.schoolOperation.RetentionSampleMapper;
import com.yj.hqbz.model.schoolOperation.DiningOut;
import com.yj.hqbz.model.schoolOperation.RetentionSample;
import com.yj.hqbz.services.schoolOperation.DiningOutService;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: DiningOutServiceImpl.java
 * @Package com.yj.hqbz.services.impl.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-11 
 */
@Service
public class DiningOutServiceImpl implements DiningOutService {
	@Resource
	DiningOutMapper diningOutMapper;
	@Resource
	RetentionSampleMapper retentionSampleMapper;

	@Override
	public PageInfo<DiningOut> getList(Map<String, Object> param, int page,
			int rows) {
		PageHelper.startPage(page,rows);
		List<DiningOut> list=diningOutMapper.getListByParam(param);
		PageInfo<DiningOut> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional
	public void insert(DiningOut dining) {
		diningOutMapper.insert(dining);
	}

	@Override
	@Transactional
	public void updateSave(DiningOut dining) {
		diningOutMapper.updateByPrimaryKey(dining);
		//已出餐状态，同步存入至留样
		RetentionSample rs=retentionSampleMapper.selectByMenuCode(dining.getMenuCode());
		if(rs==null){
			RetentionSample sample=new RetentionSample();
			sample.setRsid(UUIDUtil.getUUID());
			sample.setMenuCode(dining.getMenuCode());
			sample.setDishName(dining.getDishName());
			sample.setMenuType(dining.getMenuType());
			sample.setOrgid(dining.getOrgid());
			sample.setCookerName(dining.getCookerName());
			sample.setCreator(dining.getLastOpUser());
			sample.setCreateDate(dining.getLastOpDate());
			sample.setCookerName(dining.getCookerName());
			retentionSampleMapper.insert(sample);
		}else{
			rs.setMenuCode(dining.getMenuCode());
			rs.setDishName(dining.getDishName());
			rs.setMenuType(dining.getMenuType());
			rs.setOrgid(dining.getOrgid());
			rs.setCookerName(dining.getCookerName());
			rs.setLastOpUser(dining.getLastOpUser());
			rs.setLastOpDate(dining.getLastOpDate());
			rs.setCookerName(dining.getCookerName());
			retentionSampleMapper.updateByPrimaryKey(rs);
		}
	}


	@Override
	@Transactional
	public void deleteById(DiningOut diningOut) {
		diningOutMapper.deleteById(diningOut);
	}

	@Override
	public DiningOut selectByPrimaryKey(String id, String menuCode) {
		return diningOutMapper.selectByPrimaryKey(id,menuCode);
	}
}
