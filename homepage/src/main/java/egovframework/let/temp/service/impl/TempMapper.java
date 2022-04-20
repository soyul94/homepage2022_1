package egovframework.let.temp.service.impl;
import java.util.Iterator;
import java.util.List;

import egovframework.let.cop.bbs.service.Board;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.temp.service.TempVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.springframework.stereotype.Repository;


@Mapper("tempMapper")
public interface TempMapper {//DAO와 다르게 mapper는 동일 자바파일, 즉 한 mapper안에서의 중복이 아니면 중복되어도 무관함.
	
	//임시데이터 가져오기
	TempVO selectTemp(TempVO vo) throws Exception;
		// xml에서 설정된 이름과 매핑되어야함.
	
	//임시데이터 목록 가져오기
	List<EgovMap> selectTempList(TempVO vo) throws Exception;
	
	//임시데이터 목록 수
	int selectTempListCnt(TempVO vo) throws Exception;
	
	//임시데이터 등록
	void insertTemp(TempVO vo) throws Exception; //삽입,수정,삭제 작업에서는 출력값이 없음.
	
	//임시데이터 수정
	void updateTemp(TempVO vo) throws Exception;
	
	//임시데이터 삭제
	void deleteTemp(TempVO vo) throws Exception;
}

