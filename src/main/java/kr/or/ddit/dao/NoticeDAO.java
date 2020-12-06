package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;

public interface NoticeDAO {
	
	List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException;

	int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException;

	NoticeVO selectNoticeByNno(int nno) throws SQLException;

	void insertNotice(NoticeVO Notice) throws SQLException;

	void updateNotice(NoticeVO Notice) throws SQLException;

	void deleteNotice(int nno) throws SQLException;

	// viewcnt 증가
	void increaseViewCount(int nno) throws SQLException;

	// Notice_seq.nextval 가져오기
	int selectNoticeSequenceNextValue() throws SQLException;
}
