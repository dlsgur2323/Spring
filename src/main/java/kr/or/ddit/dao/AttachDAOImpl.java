package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		sqlSession.update("Attach-Mapper.insertAttach",attach);	
	}

	@Override
	public void deleteAttach(int ano) throws SQLException {
		sqlSession.update("Attach-Mapper.deleteAttach",ano);		
		
	}

	@Override
	public List<AttachVO> selectAttachesByPno(int pno) throws SQLException {
		List<AttachVO> attachList=
				sqlSession.selectList("Attach-Mapper.selectAttachByPno",pno);
		return attachList;
	}

	@Override
	public void deleteAllAttach(int pno) throws SQLException {
		sqlSession.update("Attach-Mapper.deleteAllAttach",pno);		
	}
	@Override
	public AttachVO selectAttachByAno(int ano) throws SQLException {
		AttachVO attach= sqlSession.selectOne("Attach-Mapper.selectAttachByAno",ano);
		return attach;
	}

}
