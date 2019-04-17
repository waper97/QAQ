/**   
* 
*/
package com.yj.hqbz.services.impl.schoolOperation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yj.hqbz.model.schoolOperation.MorningCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.AttendanceDetailMapper;
import com.yj.hqbz.mapper.schoolOperation.AttendanceIndexMapper;
import com.yj.hqbz.model.schoolOperation.AttendanceDetail;
import com.yj.hqbz.model.schoolOperation.AttendanceIndex;
import com.yj.hqbz.services.schoolOperation.AttendanceIndexService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: AttendanceIndexServiceImpl.java
 * @Package com.yj.hqbz.services.impl.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-9 
 */
@Service
public class AttendanceIndexServiceImpl implements AttendanceIndexService {
	@Resource
	AttendanceIndexMapper attendanceIndexMapper;
	@Resource
	AttendanceDetailMapper attendanceDetailMapper;

	
	public PageInfo<AttendanceIndex> getList(Map<String, Object> param, int page,
			int rows) {
		PageHelper.startPage(page,rows);
		List<AttendanceIndex> results=attendanceIndexMapper.getList(param);
		PageInfo<AttendanceIndex> pageInfo = new PageInfo<>(results);
		return pageInfo;
	}

	
	@Transactional
	public void addSave(AttendanceIndex attIndex) {
		String indexid = attIndex.getIndexid();
		List<AttendanceDetail> resultList = attIndex.getResultList();
		//设置应到 人数=总人数-休息人数
		int totalNum=resultList.size();
		int relaxNum=0;
		for (AttendanceDetail detail : resultList) {
			detail.setIndexid(indexid);
			if(detail.getResult()==4){
				relaxNum+=1;
			}
			detail.setId(UUIDUtil.getUUID());
			
		}
		attIndex.setCount(totalNum-relaxNum);
		attIndex.setStatus(0);
		attendanceIndexMapper.insert(attIndex);
		for (AttendanceDetail detail : resultList) {
			attendanceDetailMapper.insert(detail);//需要主表插入后才能插入引用了主键的表
		}
	}

	
	@Transactional
	public void updateSave(AttendanceIndex attIndex) {
		List<AttendanceDetail> resultList = attIndex.getResultList();
		//设置应到 人数=总人数-休息人数
		int totalNum=resultList.size();
		int relaxNum=0;
		for (AttendanceDetail detail : resultList) {
			if(detail.getResult()==4){
				relaxNum+=1;
			}
			attendanceDetailMapper.updateByPrimaryKey(detail);
		}
		attIndex.setCount(totalNum-relaxNum);
		attendanceIndexMapper.updateByPrimaryKey(attIndex);
	}

	
	public AttendanceIndex getById(String indexid) {
		AttendanceIndex index=attendanceIndexMapper.selectByPrimaryKey(indexid);
		List<AttendanceDetail> list=attendanceDetailMapper.getByIndexId(indexid);
		if(index != null){
			index.setResultList(list);
		}
		return index;
	}

	
	@Transactional
	public void deleteById(AttendanceIndex index) {
		attendanceIndexMapper.deleteByPrimaryKey(index);
		//考勤明细表没有逻辑删除字段，没有处理。
	}

	@Override
	public MorningCheck getAttendanceOfDaily(String orgid) {
		return attendanceIndexMapper.getAttendanceOfDaily(orgid);
	}
}
