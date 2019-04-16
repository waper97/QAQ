package com.yj.hqbz.util;

import java.io.*;
import com.aspose.words.Document;
import com.aspose.words.License;

/**
 * @Title: AsposeUtil.java
 * @Package com.yj.util.tool
 * @Description: TODO
 * @author xx
 * @date 2019-1-16
 */
public class AsposeUtil {

	private static boolean getDocLicense() {
		boolean result = false;
		try {
			InputStream is = AsposeUtil.class.getClassLoader()
					.getResourceAsStream("license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String word2Html(String docFile) {
		if(StringUtil.isBlank(docFile)) {
			return "";
		}
		String suffix =docFile.substring(docFile.lastIndexOf("."));
		if(!StaticUtils.FILE_DOC_TYPE.contains(suffix)) {
			return "";
		}
		String htmlFile = docFile.replace(suffix, ".html");
		if (!getDocLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
			return "";
		}

		try {
			Document doc = new Document(docFile);// path为word地址
			com.aspose.words.HtmlSaveOptions hso = new com.aspose.words.HtmlSaveOptions();
			hso.setExportImagesAsBase64(true);
			doc.save(htmlFile, hso);
			return getBody(readfile(htmlFile));
		} catch (Exception ex) {
			return "";
		}
	}

	private static String readfile(String filePath) {
		File file = new File(filePath);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		byte[] bytes = new byte[1024];
		try {
			for (int n; (n = input.read(bytes)) != -1;) {
				buffer.append(new String(bytes, 0, n, "UTF-8"));
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return buffer.toString();
	}

	private static String getBody(String val) {
		String start = "<body>";
		String end = "</body>";
		int s = val.indexOf(start) + start.length();
		int e = val.indexOf(end);
		return val.substring(s, e);
	}

	public static void main(String[] args) {
		System.err.println(AsposeUtil.word2Html("F:/商品汇总表/1.docx"));
	}

}