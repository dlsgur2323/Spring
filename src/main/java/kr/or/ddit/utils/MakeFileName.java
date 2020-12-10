package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.or.ddit.dto.AttachVO;

public class MakeFileName {
	
	//파일명 안주면 여기 것을 쓰고 주면 준 것을 쓴다
	private static String delimiter="$$";	

	//원래 파일명에 UUID랑 구분자를 붙여 주는 것
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		if(delimiter!=null && !delimiter.isEmpty()) MakeFileName.delimiter = delimiter;
		
		return uuid + MakeFileName.delimiter + fileName;
	}
	
	//attachList 넘어왔을 때 떼어주는 작업
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		if(delimiter!=null && !delimiter.isEmpty()) MakeFileName.delimiter = delimiter;
		String[] uuidFileName = fileName.split(MakeFileName.delimiter);
		return uuidFileName[uuidFileName.length - 1];
	}
	
	//attachList 의 fileName 가져와서 parseFileNameFromUUID를 이용해서 떼주는 것
	//사용할 땐 fileName 꺼내면 됨
	public static List<AttachVO> parseFileNameFromAttaches(List<AttachVO> attachList, String delimiter) {
		
		List<AttachVO> renamedAttachList = new ArrayList<AttachVO>();
		
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				String fileName = attach.getFileName();  //DB상의 fileName
				fileName = parseFileNameFromUUID(fileName, delimiter);  //uuid가 제거된 fileName
				
				attach.setFileName(fileName);
				
				renamedAttachList.add(attach);
			}
		}
		return renamedAttachList;
	}
	
}
