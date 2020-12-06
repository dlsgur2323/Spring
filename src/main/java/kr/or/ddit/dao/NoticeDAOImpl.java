package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO{
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException {
		
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<NoticeVO> noticeList=
				sqlSession.selectList("Notice-Mapper.selectSearchNoticeList",cri,rowBounds);
		
		return noticeList;
	}

	@Override
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException {
		
		int count=sqlSession.selectOne("Notice-Mapper.selectSearchNoticeListCount",cri);
		return count;
	}

	@Override
	public NoticeVO selectNoticeByNno(int nno) throws SQLException {
		NoticeVO notice=
				sqlSession.selectOne("Notice-Mapper.selectNoticeByNno",nno);
		return notice;
	}

	@Override
	public void insertNotice(NoticeVO notice) throws SQLException {
		sqlSession.update("Notice-Mapper.insertNotice",notice);
	}

	@Override
	public void updateNotice(NoticeVO notice) throws SQLException {
		sqlSession.update("Notice-Mapper.updateNotice",notice);
	}

	@Override
	public void deleteNotice(int nno) throws SQLException {
		sqlSession.update("Notice-Mapper.deleteNotice",nno);

	}

	@Override
	public void increaseViewCount(int nno) throws SQLException {
		sqlSession.update("Notice-Mapper.increaseViewCount",nno);

	}

	@Override
	public int selectNoticeSequenceNextValue() throws SQLException {
		int seq_num=
				sqlSession.selectOne("Notice-Mapper.selectNoticeSequenceNextValue");
		return seq_num;
	}

}
