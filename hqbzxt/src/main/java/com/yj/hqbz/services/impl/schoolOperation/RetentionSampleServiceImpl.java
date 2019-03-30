/**   
* 
*/
package com.yj.hqbz.services.impl.schoolOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.yj.hqbz.model.schoolOperation.RetentionSampleAttachment;
import com.yj.hqbz.util.StringUtil;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.RetentionSampleMapper;
import com.yj.hqbz.model.schoolOperation.RetentionSample;
import com.yj.hqbz.services.schoolOperation.RetentionSampleService;

/**   
 * @Title: RetentionSampleServiceImpl.java
 * @Package com.yj.hqbz.services.impl.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-12 
 */
@Service
public class RetentionSampleServiceImpl implements RetentionSampleService {
	@Resource
	RetentionSampleMapper retentionSampleMapper;

	@Override
	public PageInfo<RetentionSample> getList(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows);
		List<RetentionSample> list=retentionSampleMapper.getListByParem(param);
		PageInfo<RetentionSample> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional
	public void insert(RetentionSample sample ) {
		//获取多个pc图片地址
			List<RetentionSampleAttachment> attachments = sample.getAttachmentList();
			for(RetentionSampleAttachment rsa:attachments){
				rsa.setRsid(sample.getRsid());
				rsa.setAttenId(StringUtil.getUUID());
				retentionSampleMapper.insertAttachment(rsa);
			}
		retentionSampleMapper.insert(sample);
	}

	@Override
	@Transactional
	public void updateSave(RetentionSample sample) {

		for(RetentionSampleAttachment attachment:sample.getAttachmentList()){
			if(attachment.getAttenId() == null){
				//如果附件图id为空，就是新增
				attachment.setAttenId(StringUtil.getUUID());
				attachment.setRsid(sample.getRsid());
				retentionSampleMapper.insertAttachment(attachment);
			}else{
				//修改
				retentionSampleMapper.updateAttachment(attachment);
			}

		}

		retentionSampleMapper.updateByPrimaryKey(sample);
	}

	@Override
	public List<RetentionSampleAttachment> getRentionSampleAttachment(String rsid) {
		return retentionSampleMapper.getRentionSampleAttachment(rsid);
	}

	@Override
	public RetentionSample selectByPrimaryKey(String rsid) {
		return retentionSampleMapper.selectByPrimaryKey(rsid);
	}

	@Override
	@Transactional
	public void deleteByPrimaryKey(RetentionSample sample) {
		retentionSampleMapper.deleteByPrimaryKey(sample);
	}
}
