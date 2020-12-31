package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.PdsVO;

public interface PdsDAO {
	
	List<PdsVO> selectPdsCriteria(SearchCriteria cri)	throws SQLException;
	int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	PdsVO selectPdsByPno(int pno)throws SQLException;
	PdsVO selectPdsByContent(String fileName)throws SQLException;
	
	void insertPds(PdsVO pds)throws SQLException;
	void updatePds(PdsVO pds)throws SQLException;
	void deletePds(int pno)throws SQLException;
	
	//viewcnt  증가
	void increaseViewCnt(int pno)throws SQLException;
	
	//pds_seq.nextval 가져오기
	int getSeqNextValue() throws SQLException;
}
