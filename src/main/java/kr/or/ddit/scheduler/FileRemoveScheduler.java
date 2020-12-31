package kr.or.ddit.scheduler;

import java.io.File;

import kr.or.ddit.service.BoardService;
import kr.or.ddit.service.NoticeService;
import kr.or.ddit.service.PdsService;

public class FileRemoveScheduler {
	
	private String filePath;
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	public void fileRemove() {
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if(files != null) {
			for(File file : files) {
				System.out.println(file.getName());
			}
		}

	}
}
