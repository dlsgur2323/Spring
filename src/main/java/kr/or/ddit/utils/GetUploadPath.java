package kr.or.ddit.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class GetUploadPath {

	private static Properties properties = new Properties();
	static {
		String resource = "com/jsp/properties/uploadPath.properties";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath(String key) {
		String uploadPath = null;
		uploadPath = properties.getProperty(key);
		uploadPath = uploadPath.replace("/", File.separator);
		return uploadPath;
	}
	
	//첨부파일 폴더 이름 업로드 날짜로 생성해서 저장하기(캘린더 사용)
	public static String getUploadDatePath(String key) {
		String uploadDatePath = getUploadPath(key);
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		String savePath = yearPath + monthPath + datePath;
		uploadDatePath += savePath;
		
		return uploadDatePath;
	}
}
