package egovframework.let.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.btCRUD.service.BtCrudService;
import egovframework.let.btCRUD.service.BtCrudVO;

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

@Service("boardService") //service의 어노테이션은 프로젝트에 단 하나여야한다.
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {
	
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;

	@Resource(name="egovBoardIdGnrService") //context-idgen.xml에 추가한 bean을 연결하는 것
	private EgovIdGnrService idgenService;
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception{
		return boardMapper.selectBoardList(vo);
	}
	
	//임시데이터 목록 수
	public int selectBoardListCnt(BoardVO vo) throws Exception{
		return boardMapper.selectBoardListCnt(vo);
	}
	
}
