package egovframework.let.crud2.service;

import java.util.List;

import egovframework.let.btCRUD.service.BtCrudVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface Crud2Service {

	Crud2VO selectCrud2(Crud2VO crud2vo) throws Exception;

	int selectCrud2ListCnt(Crud2VO crud2vo) throws Exception;

	List<EgovMap> selectCrud2List(Crud2VO crud2vo) throws Exception;

	String insertCurd2(Crud2VO crud2vo) throws Exception;
	
	void updateCrud2(Crud2VO vo) throws Exception;

	void deleteCurd2(Crud2VO crud2vo) throws Exception;

}
