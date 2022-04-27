package egovframework.let.temp2.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.cop.bbs.service.Board;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;
import egovframework.let.utl.fcc.service.EgovDateUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("temp2Service") //service의 어노테이션은 프로젝트에 단 하나여야한다.
public class Temp2ServiceImpl extends EgovAbstractServiceImpl implements Temp2Service {
	
	@Resource(name="temp2Mapper")
	private Temp2Mapper temp2Mapper;

	@Resource(name="egovTempIdGnrService") //context-idgen.xml에 추가한 bean을 연결하는 것
	private EgovIdGnrService idgenService;
	
	@Override
	public Temp2VO selectTemp(Temp2VO vo) throws Exception{
		return temp2Mapper.selectTemp(vo);
	}
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTempList(Temp2VO vo) throws Exception{
		return temp2Mapper.selectTempList(vo);
	}
	
	//임시데이터 등록하기
	public String insertTemp(Temp2VO vo) throws Exception{
		String id = idgenService.getNextStringId(); //얘는 왜 하필 impl에 들어 있을까 ?
		vo.setTempId(id);							//에러가 발생했을 시에 모두 롤백이 가능하기 때문에 (impl은 오토커밋이 아니다.)
		temp2Mapper.insertTemp(vo);
		return id;
	}
	
	//임시데이터 목록 수
	public int selectTempListCnt(Temp2VO vo) throws Exception{
		return temp2Mapper.selectTempListCnt(vo);
	}
	
	//임시데이터 수정하기
	public void updateTemp(Temp2VO vo) throws Exception{
		temp2Mapper.updateTemp(vo);
	}
	
	//임시데이터 삭제하기
	public void deleteTemp(Temp2VO vo) throws Exception{
		temp2Mapper.deleteTemp(vo);
	}
	
	
/*	@Override //tempDAO를 위한 메소드
	public TempVO selectTemp(TempVO vo) throws Exception{
		return tempDAO.selectTemp(vo);
	}
*/ 
}
