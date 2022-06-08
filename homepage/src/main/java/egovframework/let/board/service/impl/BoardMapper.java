package egovframework.let.board.service.impl;
import java.util.Iterator;
import java.util.List;

import egovframework.let.board.service.BoardVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.springframework.stereotype.Repository;


@Mapper("boardMapper")
public interface BoardMapper {//DAO와 다르게 mapper는 동일 자바파일, 즉 한 mapper안에서의 중복이 아니면 중복되어도 무관함.
	//게시물 상세정보
	BoardVO selectBoard(BoardVO vo) throws Exception;
	
	//조회수 증가
	void updateViewCnt(BoardVO vo) throws Exception;
	
	//게시물 목록 가져오기
	List<EgovMap> selectBoardList(BoardVO vo) throws Exception;	

	//게시물 목록 수
	int selectBoardListCnt(BoardVO vo) throws Exception;
	
	//게시물 등록
	void insertBoard(BoardVO vo) throws Exception;
	
	//게시물 수정
	void updateBoard(BoardVO vo) throws Exception;

	//게시물 삭제
	void deleteBoard(BoardVO vo) throws Exception;

}

