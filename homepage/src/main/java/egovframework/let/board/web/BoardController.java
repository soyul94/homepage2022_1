package egovframework.let.board.web;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.btCRUD.service.BtCrudService;
import egovframework.let.btCRUD.service.BtCrudVO;
import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;
import egovframework.let.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;


@Controller //@이름 -> 어노테이션 : 
public class BoardController {
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	
	//임시데이터 목록 가져오기
	@RequestMapping(value="/board/selectList.do")// URI 매핑.
	public String selectList(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
	
		//공지 게시글
		searchVO.setNoticeAt("Y");
		List<EgovMap> noticeResultList= boardService.selectBoardList(searchVO);
		model.addAttribute("noticeResultList",noticeResultList);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchVO.setNoticeAt("N");
		List<EgovMap> resultList= boardService.selectBoardList(searchVO);
		model.addAttribute("resultList",resultList);
		
		int totCot = boardService.selectBoardListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCot);
		model.addAttribute("paginationInfo", paginationInfo);
		
		LoginVO user= (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO",user);
		
		System.out.println("selectList 컨트롤 호출");			
		return "board/BoardSelectList";//jsp파일이 받는 경로
	}
	
	
	//게시물 등록 및 수정 폼 호출
	@RequestMapping(value="/board/boardRegist.do")
	public String boardRegist(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		//세션과 쿠키 공부하고 다시보기
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user==null||user.getId()==null) {
			model.addAttribute("message","로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do"; //로그인이 되어있지 않으면 리스트로 돌려보냄
		}
		else {
			model.addAttribute("USER_INFO", user); //jsp에서 사용하기 위해 로그인 정보를 Attribute함. 
		}
		
		BoardVO result = new BoardVO();
		if(!EgovStringUtil.isEmpty(boardVO.getBoardId())) {
			/*result = boardService.selectBoard(boardVO);
			//본인 및 관리자만 허용
			if(!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId())) {
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/board/selectList.do";
			}*/
		}
		model.addAttribute("result",result);
		
		//insert에 있는 이중서브밋 방지를 위해 생성한 sessionBoard를 지워 새로 등록을 가능하도록 해줌
		request.getSession().removeAttribute("sessionBoard"); 
		
		return "board/BoardRegist";
	}
	
	//입력받은 게시물 등록하는 메소드
	@RequestMapping(value="/board/insert.do")
	public String boardInsert(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception{
	
		//이중 서브밋 방지 체크 : boardService.insertBoard를 수행 후 sessionBoard에 값이 저장되므로 이를 확인해서 중복 서브밋인지 감별함
		if(request.getSession().getAttribute("sessionBoard") != null){
			return "forward:/board/selectList.do";
		}
		
		//웹에서는 로그인 지속시간이 존재하기 때문에 게시글 입력중에 로그인이 풀릴 수 있음. 이때 로그인 정보가 없는 이를 insert하지 않도록 함
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user==null || user.getId()==null) {
			model.addAttribute("message","로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";		//보통 이 경우는 로그인 페이지로 이동시킴	
		}
		
		boardVO.setCreatIp(request.getRemoteAddr()); //현재 작성자 기준의 IP를 불러옴(공인IP에 대한 정보)
		boardVO.setUserId(user.getId());
		
		boardService.insertBoard(boardVO);
		
		//이중 서브밋 방지 : boardService.insertBoard(boardVO)를 수행 후 해당 정보를 session에 저장함
		request.getSession().setAttribute("sessionBoard", boardVO);
		
		return "forward:/board/selectList.do";	
	}
		
			
}

