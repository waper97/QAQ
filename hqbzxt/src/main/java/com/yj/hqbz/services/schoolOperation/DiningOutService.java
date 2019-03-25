/**   
* 
*/
package com.yj.hqbz.services.schoolOperation;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.DiningOut;

/**   
 * @Title: DiningOutService.java
 * @Package com.yj.hqbz.services.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-11 
 */
public interface DiningOutService {

	/**
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<DiningOut> getList(Map<String, Object> param, int page, int rows);

	/**
	 * @param dining
	 */
	void insert(DiningOut dining);

	/**
	 * @param dining
	 */
	void updateSave(DiningOut dining);

	/**
	 * @param id
	 * @return
	 */
	DiningOut selectByPrimaryKey(String id,String menuCode);

	/**
	 * @param id
	 */
	void deleteById(DiningOut diningOut);

}
