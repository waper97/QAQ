package com.yj.hqbz.controller.file;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.util.FileUtil;
@RestController
public class UploadFileController extends BaseController{
	
	@RequestMapping("/common/file/upload")
	public Object upload(Integer type,@RequestParam(value="hqfile") MultipartFile hqfile) {
		try {
			if(type==null) {
				return fail("文件所属类别不能为空！");
			}
			if(hqfile.isEmpty()) {
				return fail("文件和文件内容不能为空！");
			}
			String fileUrl = FileUtil.getFileUrl(type);
			String fileType=hqfile.getOriginalFilename().substring(hqfile.getOriginalFilename().lastIndexOf("."));
			Map<String, Object> urlMap = FileUtil.upLoadFile(fileUrl+fileType,200, hqfile);
			return new BaseRes("上传成功！",urlMap);
		} catch (Exception e) {
			e.printStackTrace();
			return fail("上传失败！");
		}
	}
	
}
