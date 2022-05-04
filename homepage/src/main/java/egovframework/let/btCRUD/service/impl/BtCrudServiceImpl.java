package egovframework.let.btCRUD.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.btCRUD.service.BtCrudService;
import egovframework.let.btCRUD.service.BtCrudVO;
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

@Service("btCrudService") //service의 어노테이션은 프로젝트에 단 하나여야한다.
public class BtCrudServiceImpl extends EgovAbstractServiceImpl implements BtCrudService {
	
	@Resource(name="btCrudMapper")
	private BtCrudMapper btCrudMapper;

	@Resource(name="egovBtCrudIdGnrService") //context-idgen.xml에 추가한 bean을 연결하는 것
	private EgovIdGnrService idgenService;
	
	@Override
	public BtCrudVO selectBtCrud(BtCrudVO vo) throws Exception{
		return btCrudMapper.selectBtCrud(vo);
	}
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectBtCrudList(BtCrudVO vo) throws Exception{
		return btCrudMapper.selectBtCrudList(vo);
	}
	
	//임시데이터 목록 수
	public int selectBtCrudListCnt(BtCrudVO vo) throws Exception{
		return btCrudMapper.selectBtCrudListCnt(vo);
	}
	
	//임시데이터 등록하기
	public String insertBtCrud(BtCrudVO vo) throws Exception{
		String id = idgenService.getNextStringId(); //얘는 왜 하필 impl에 들어 있을까 ?
		vo.setCrudId(id);							//에러가 발생했을 시에 모두 롤백이 가능하기 때문에 (impl은 오토커밋이 아니다.)
		btCrudMapper.insertBtCrud(vo);
		return id;
	}
	
	//임시데이터 수정하기
	public void updateBtCrud(BtCrudVO vo) throws Exception{
		btCrudMapper.updateBtCrud(vo);
	}
	
	//임시데이터 삭제하기
	public void deleteBtCrud(BtCrudVO vo) throws Exception{
		btCrudMapper.deleteBtCrud(vo);
	}
	
	
/*	@Override //tempDAO를 위한 메소드
	public TempVO selectTemp(TempVO vo) throws Exception{
		return tempDAO.selectTemp(vo);
	}
*/ 
}
