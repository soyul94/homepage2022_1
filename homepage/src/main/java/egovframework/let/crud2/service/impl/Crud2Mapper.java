package egovframework.let.crud2.service.impl;

import java.util.List;

import egovframework.let.crud2.service.Crud2VO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper(value="crud2Mapper")
public interface Crud2Mapper {

	Crud2VO selectCrud2(Crud2VO crud2vo) throws Exception;

	int selectCrud2ListCnt(Crud2VO crud2vo) throws Exception;

	List<EgovMap> selectCrud2List(Crud2VO crud2vo) throws Exception;

	void insertCrud2(Crud2VO crud2vo) throws Exception;

	void updateCrud2(Crud2VO crud2vo) throws Exception;

	void deleteCrud2(Crud2VO crud2vo) throws Exception;

}
