package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO {
		
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<PdsVO> selectPdsCriteria(SearchCriteria cri) throws SQLException {
		
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<PdsVO> pdsList=
				sqlSession.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);	
			
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		
		int count=sqlSession.selectOne("Pds-Mapper.selectSearchPdsListCount",cri);
		
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		
		PdsVO pds=sqlSession.selectOne("Pds-Mapper.selectPdsByPno",pno);
		
		return pds;
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {
		
		sqlSession.update("Pds-Mapper.insertPds",pds);
		

	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		
		sqlSession.update("Pds-Mapper.updatePds",pds);
		

	}

	@Override
	public void deletePds(int pno) throws SQLException {
		
		sqlSession.update("Pds-Mapper.deletePds",pno);
		

	}

	@Override
	public void increaseViewCnt(int pno) throws SQLException {
		
		sqlSession.update("Pds-Mapper.increaseViewCnt",pno);
		

	}

	@Override
	public int getSeqNextValue() throws SQLException {
		
		int pno=sqlSession.selectOne("Pds-Mapper.selectPdsSeqNext");
		
		return pno;
	}

}
