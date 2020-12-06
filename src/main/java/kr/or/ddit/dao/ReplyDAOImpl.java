package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO{
	
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.insertReply",reply);
		
	}
	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.updateReply",reply);
	}
	
	@Override
	public void deleteReply(int rno) throws SQLException {
		sqlSession.update("Reply-Mapper.deleteReply",rno);
	}
	
	@Override
	public List<ReplyVO> selectReplyListPage(int bno, SearchCriteria cri) throws SQLException {
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<ReplyVO> replyList = sqlSession.selectList("Reply-Mapper.selectReplyList",bno,rowBounds);
		
		return replyList;
	}
	
	@Override
	public int countReply(int bno) throws SQLException {
		int count = (Integer)sqlSession.selectOne("Reply-Mapper.countReply",bno);
		return count;
	}

	@Override
	public int selectReplySeqNextValue() throws SQLException {
		int rno = (Integer)sqlSession.selectOne("Reply-Mapper.selectReplySeqNextValue");
		return rno;
	}
	
		
}




