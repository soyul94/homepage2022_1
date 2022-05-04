package egovframework.let.btCRUD.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface BtCrudService {
	//임시데이터 가져오기
	public BtCrudVO selectBtCrud(BtCrudVO vo) throws Exception;
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectBtCrudList(BtCrudVO vo) throws Exception;
	
	//임시데이터 목록 수
	public int selectBtCrudListCnt(BtCrudVO vo) throws Exception;
	
	//임시데이터 등록하기
	public String insertBtCrud(BtCrudVO vo) throws Exception;
	
	//임시데이터 수정하기
	public void updateBtCrud(BtCrudVO vo) throws Exception;
	
	//임시데이터 삭제하기
	public void deleteBtCrud(BtCrudVO vo) throws Exception;
}

