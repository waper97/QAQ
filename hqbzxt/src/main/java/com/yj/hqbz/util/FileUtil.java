package com.yj.hqbz.util;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.yj.hqbz.web.Global;

public class FileUtil {
	/**
	 * 获取图片文件大小图url
	 */
	public static String getBigOrSmallImg(String url, String urlType) {
		return url.substring(0, url.lastIndexOf(".")) + urlType
				+ url.substring(url.lastIndexOf("."));
	}
	
	public static Map<String,Object> upLoadFile(String fileUrl,Integer smallSize,MultipartFile file) throws Exception {
		String uploadDir = Global.getSystemParamValueByKey(StaticUtils.S_UPLOAD_DIR_CODE);
		String visitDir = Global.getSystemParamValueByKey(StaticUtils.S_VISIT_DIR_CODE);
		
		//返回值
		Map<String,Object> urlMap=new HashMap<String, Object>();
		urlMap.put("url", visitDir+fileUrl);
		//设置文件地址
		File newfile = new File(uploadDir+fileUrl);
		//文件夹不存在就创建。
		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}
		// 保存文件到硬盘
		file.transferTo(newfile);
		String filename=fileUrl.substring(0, fileUrl.lastIndexOf("."));
		String fileType=fileUrl.substring(fileUrl.lastIndexOf("."));
		if(StaticUtils.FILE_IMG_TYPE.contains(fileType)&&smallSize!=null) {
			//缩略图
			String smallUrl= filename+ StaticUtils.IMG_SMALL_CODE+ fileType;
			ImageUtil.createThumbnailSmall(uploadDir + fileUrl, uploadDir + smallUrl, smallSize, 0);
			urlMap.put("smallUrl", visitDir+smallUrl);
		}
		return urlMap;
	}
	
	public static String getFileUrl(Integer type) {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		String strFile = y + "/" + m + "/" + d + "/"+ UUID.randomUUID().toString().replace("-", "");
		if (type==0) {
			return "/org/" + strFile;
		}else if(type==1) {
			return "/goods/" + strFile;
		}else if(type==2){
		    return "/user/" + strFile;
		}else if(type==3){
		    return "/trace/report/" + strFile;
		}
		else if(type==4){
		    return "/trace/certificate/" + strFile;
        }
		else if(type==5){
		    return "/trace/realpic/" + strFile;
		}
		else if(type==6){
		    return "/trace/qualification" + strFile;
		}
		else{
			return null;
		}
	}
	
	
	public static void deleteFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		// 单文件
		if (!file.isDirectory()) {
			boolean delFlag = file.delete();
			if (!delFlag) {
				throw new RuntimeException(file.getPath() + "删除失败！");
			} else {
				return;
			}
		}
	}
	
	
}
