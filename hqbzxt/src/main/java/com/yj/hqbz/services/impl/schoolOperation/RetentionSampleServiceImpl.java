/**   
* 
*/
package com.yj.hqbz.services.impl.schoolOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yj.hqbz.model.schoolOperation.RetentionSampleAttachment;
import com.yj.hqbz.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	
	public PageInfo<RetentionSample> getList(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows);
		List<RetentionSample> list=retentionSampleMapper.getListByParem(param);
		PageInfo<RetentionSample> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	@Transactional
	public void insert(RetentionSample sample ) {
		//获取多个pc图片地址
			List<RetentionSampleAttachment> attachments = sample.getAttachmentList();
			for(RetentionSampleAttachment rsa:attachments){
				rsa.setRsid(sample.getRsid());
				rsa.setAttachid(StringUtil.getUUID());
				retentionSampleMapper.insertAttachment(rsa);
			}
		retentionSampleMapper.insert(sample);
	}

	
	@Transactional
	public void updateSave(RetentionSample sample) {
		if(!sample.getAttachmentList().isEmpty()){
		    //删除图片
            retentionSampleMapper.deleteAttachmentByRsid(sample.getRsid());
			for(RetentionSampleAttachment attachment:sample.getAttachmentList()){
            //新增图片
					attachment.setAttachid(StringUtil.getUUID());
					attachment.setRsid(sample.getRsid());
					retentionSampleMapper.insertAttachment(attachment);
			}
		}
		retentionSampleMapper.updateByPrimaryKey(sample);
	}

	
	public List<RetentionSampleAttachment> getRentionSampleAttachment(String rsid) {
		return retentionSampleMapper.getRentionSampleAttachment(rsid);
	}

	
	public RetentionSample selectByPrimaryKey(String rsid) {
		return retentionSampleMapper.selectByPrimaryKey(rsid);
	}

	
	@Transactional
	public void deleteByPrimaryKey(RetentionSample sample) {
		retentionSampleMapper.deleteByPrimaryKey(sample);
	}
}
