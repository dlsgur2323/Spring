package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class SummernoteImageController {
	
	@Resource(name="imgPath")
	private String imgPath;
	
	@RequestMapping("/deleteImg")
	public ResponseEntity<String> deleteImg(@RequestBody Map<String,String> data) throws ServletException, IOException {
		
		ResponseEntity<String> result = null;

		String savePath = imgPath.replace("/", File.separator);
		File target = new File(savePath,data.get("fileName"));

		if (!target.exists()) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				target.delete();
				result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				
			} catch (Exception e) {
				result = new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		return result;
	}
	
	@RequestMapping("/getImg")
	public ResponseEntity<byte[]> getImg(HttpServletRequest request, String fileName) throws Exception{
		
		ResponseEntity<byte[]> result = null;
		
		String savePath = imgPath.replace("/", File.separator);
		File sendFile = new File(savePath, fileName);
		
		InputStream in = null;
		
		try {
			in = new FileInputStream(sendFile);
			
			result = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		
		return result;
	}
	
	
	@RequestMapping("/uploadImg")
	public ResponseEntity<String> uploadImg(HttpServletRequest request, MultipartFile file) throws ServletException, IOException {
		ResponseEntity<String> result = null;
		
		int fileSize = 5 * 1024 * 1024;
		
		//저장경로 확인 및 새로 만들기
		if(file.getSize() > fileSize) {
			return new ResponseEntity<String>("용량 초과입니다.", HttpStatus.BAD_REQUEST);
		}
		
		// uuid+구분자+파일명
		String savePath = imgPath.replace("/", File.separator);
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		fileName = fileName + fileFormat;
		File saveFile = new File(savePath, fileName);
		
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		try {
			file.transferTo(saveFile);
			result = new ResponseEntity<String>(request.getContextPath() + "/getImg.do?fileName=" + fileName, HttpStatus.OK);
		}catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
}
















