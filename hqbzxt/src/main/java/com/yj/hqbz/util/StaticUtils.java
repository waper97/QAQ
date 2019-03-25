package com.yj.hqbz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.sql.Clob;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.model.BaseRes;

public class StaticUtils {
	public static final boolean debug = true;
	//===========用户库地址=====================
	public static final String USERDB_URL_CODE="yjuser_db_url";
	// ========================大小图========================
	public static String IMG_BIG_CODE = "_big";
	public static String IMG_SMALL_CODE = "_small";
	// ==========================上传附件类型==========================
	public static List<String> FILE_IMG_TYPE = Arrays.asList(new String[] { ".png", ".jpg", ".jpeg", ".gif", ".bmp", ".PNG", ".JPG", ".JPEG", ".GIF", ".BMP" });
	public static List<String> FILE_DOC_TYPE = Arrays.asList(new String[] { ".doc", ".docx", ".DOC", ".DOCX" });
	public static List<String> FILE_PPT_TYPE = Arrays.asList(new String[] { ".ppt", ".pptx", ".PPT", ".PPTX" });
	public static List<String> FILE_XLS_TYPE = Arrays.asList(new String[] { ".xls", ".xlsx", ".XLS", ".XLSX" });
	public static List<String> FILE_PDF_TYPE = Arrays.asList(new String[] { ".pdf", ".PDF" });
	public static List<String> FILE_TXT_TYPE = Arrays.asList(new String[] { ".txt", ".TXT" });
	public static List<String> FILE_LOG_TYPE = Arrays.asList(new String[] { ".log", ".LOG" });
	public static List<String> FILE_MP3_TYPE = Arrays.asList(new String[] { ".mp3", ".m4a", ".aac", ".amr", ".wav", ".MP3", ".M4A", ".AAC", ".AMR", ".WAV" });
	public static List<String> FILE_MP4_TYPE = Arrays.asList(new String[] { ".mp4", ".avi", ".wmv", ".wma", ".mid", ".mov", ".mpg", ".mpeg", ".mkv", ".flash",".m4v", ".rmvb", ".rm", ".3gp", ".dat", ".vob", ".MP4", ".AVI", ".WMV", ".WMA", ".MID", ".MOV",".MPG", ".MPEG", ".MKV", ".FLASH", ".M4V", ".RMVB", ".RM", ".3GP", ".DAT", ".VOB" });
	public static List<String> FILE_ZIP_TYPE = Arrays.asList(new String[] { ".rar", ".zip", ".RAR", ".ZIP" });
	// ===========================数据库参数表CODE========================================
	/** 操作日志保留天数 */
	public static String OPLOG_HOLD_DAY = "log_hold_day";
	/** 文件上传目录 */
	public static String S_UPLOAD_DIR_CODE = "img_upload_dir";
	/** 文件访问地址 */
	public static String S_VISIT_DIR_CODE = "img_visit_website";
	/** 添加剂分类ID*/
	public static String S_ADDITIVE_TYPEID_CODE = "additive_typeid";
	// ===========================单据类型===============================================
//	/** 订单 */
//	public static final int I_ORDER_CODE = 0;
	/** 采购入库单 */
	public static final int I_PURCHASE_CODE = 1;
	/** 配送单 */
	public static final int I_DISTRIBUTION_CODE = 2;
	/** 预配送单 */
	public static final int I_PRE_DISTRIBUTION_CODE = 3;
	/** 销售退货单 */
	public static final int I_SALE_RETURN_CODE = 4;
	/** 残损单（不扣库存） */
	public static final int I_DAMAGE_CODE = 5;
	/** 拆装单 */
	public static final int I_DISMOUNTING_CODE = 6;
	/** 报损单 （扣库存）*/
	public static final int I_LOSS_CODE = 7;
	/** 报溢单*/
	public static final int I_OVERFLOW_CODE = 8;
	/** 进货退货单 */
	public static final int I_PURCHASE_RETURN_CODE = 9;
	/** 验收入库单 */
	public static final int I_CHECK_CODE = 10;
	/**领料单*/
	public static final int VT_MATERIAL_GET_CODE=11;
	/**退料单*/
	public static final int VT_MATERIAL_RETURN_CODE=12;
	
	/**角色类型*/
	public static final int ROLE_TYPE_ADMIN = 0;
	public static final int ROLE_TYPE_SELLER = 1;
	public static final int ROLE_TYPE_BUYER = 2;
	
	/**退货订单状态***/
	public static final int RETURN_ORDER_APPLY = 0; //申请中
	public static final int RETURN_ORDER_REJECT = 1; //拒绝退货
	public static final int RETURN_ORDER_CONFIRMED = 2; //同意退货
	public static final int RETURN_ORDER_PROCESSING = 3; //退货中
	public static final int RETURN_ORDER_FINISHED = 4;// 已完成
	public static final int RETURN_ORDER_CANCEL = 5;// 已取消
	public static final int RETURN_ORDER_PICKUP = 6;	//待取货
	

	// 方法
	/**
	 * 获取文件名后缀
	 */
	public static String getSuffix(Object object) {
		return object.toString().substring(object.toString().lastIndexOf("."), object.toString().length());
	}

	/**
	 * 获取图片文件大小图url
	 */
	public static String getBigOrSmallImg(String url, String urlType) {
		return url.substring(0, url.lastIndexOf(".")) + urlType + getSuffix(url);
	}

	/**
	 * 判断文件是否存在
	 */
	public static Boolean isExistsFile(String fileUrl) {
		File file = new File(fileUrl);
		if (file != null && file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Clob转换为字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String getStrByClob(Object object) {
		Clob clob = (Clob) object;
		String reString = "";
		try {
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				if (i == 0) {
					sb.append(s);
				} else {
					sb.append("\n" + s);
				}
				s = br.readLine();
				i++;
			}
			return reString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换tab键
	 * 
	 * @param bas
	 * @return
	 */
	public static String toJSONCleanTab(BaseRes bas) {
		String str = JSONObject.toJSONString(bas);
		String jsonstr = str.replace("\t", "        ");
		/*
		 * jsonstr = jsonstr.replace("", " "); jsonstr = jsonstr.replace("", " ");
		 */
		return filter(jsonstr);
	}

	// 过滤特殊字符ascii码
	public static String filter(String src) {
		if (src == null) {
			return null;
		}
		int cpCount = src.codePointCount(0, src.length());
		int firCodeIndex = src.offsetByCodePoints(0, 0);
		int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
		StringBuilder sb = new StringBuilder(src.length());
		for (int index = firCodeIndex; index <= lstCodeIndex;) {
			int codepoint = src.codePointAt(index);
			if (codepoint >= 32 || codepoint == 10 || codepoint == 13) {
				sb.append((char) codepoint);
			}
			index += ((Character.isSupplementaryCodePoint(codepoint)) ? 2 : 1);

		}
		return sb.toString();
	}

}
