package egovframework.let.board.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface BoardService {
	//게시물 목록 가져오기
	public BoardVO selectBoard(BoardVO vo) throws Exception;
	
	//게시물 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception;
	
	//게시물 목록 수
	public int selectBoardListCnt(BoardVO vo) throws Exception;
	
	//게시물 등록
	public String insertBoard(BoardVO vo) throws Exception;
	
	//게시물 수정
	public void updateBoard(BoardVO vo) throws Exception;

	//게시물 삭제
	public void deleteBoard(BoardVO vo) throws Exception;
	
}

