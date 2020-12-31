package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;

public interface BoardDAO {

	List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException;

	int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException;

	BoardVO selectBoardByBno(int bno) throws SQLException;
	
	BoardVO selectBoardByContent(String fileName) throws SQLException;

	void insertBoard(BoardVO board) throws SQLException;

	void updateBoard(BoardVO board) throws SQLException;

	void deleteBoard(int bno) throws SQLException;

	// viewcnt 증가
	void increaseViewCnt(int bno) throws SQLException;

	// board_seq.nextval 가져오기
	int selectBoardSeqNext() throws SQLException;

}
