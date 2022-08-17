package egovframework.let.temp.web;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
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
public class TempController {
	
	@Resource(name="tempService")
	private TempService tempService;
	
	//임시데이터 가져오기
	@RequestMapping(value="/temp/select.do")// URI 매핑.
	public String select(@ModelAttribute("searchVO") TempVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{
		TempVO result = tempService.selectTemp(searchVO);
		model.addAttribute("result",result);
		
		System.out.println("컨트롤 호출");			
		return "temp/TempSelect";//jsp파일이 받는 경로
	}
	
	//임시데이터 목록 가져오기
	@RequestMapping(value="/temp/selectList.do")// URI 매핑.
	public String selectList(@ModelAttribute("searchVO") TempVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{
/*		
		List<EgovMap> resultList = tempService.selectTempList(searchVO);
		model.addAttribute("resultList",resultList);
*/		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCot = tempService.selectTempListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCot);
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<EgovMap> resultList = tempService.selectTempList(searchVO);
		model.addAttribute("resultList",resultList);
		
		System.out.println("컨트롤 호출");			
		return "temp/TempSelectList";//jsp파일이 받는 경로
	}
	
	//임시데이터 등록하기
	@RequestMapping(value="/temp/insert.do")// URI 매핑.
	public String insert(@ModelAttribute("searchVO") TempVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{

		tempService.insertTemp(searchVO);
		
		System.out.println("컨트롤 호출");			
		return "forward:/temp/selectList.do";//jsp파일이 받는 경로
	}
	
	//임시데이터 수정하기
	@RequestMapping(value="/temp/update.do")// URI 매핑.
	public String update(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		tempService.updateTemp(searchVO);
		return "forward:/temp/selectList.do";
	}
	
	//임시데이터 삭제하기
	@RequestMapping(value="/temp/delete.do")// URI 매핑.
	public String delete(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		tempService.deleteTemp(searchVO);
		return "forward:/temp/selectList.do";
	}
	
	
	//임시데이터 등록을 위해 파라미터 값을 받아들일 폼이 있는 페이지 호출
	@RequestMapping(value="/temp/tempRegist.do")// URI 매핑.
	public String tempRegist(@ModelAttribute("searchVO") TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		TempVO result= new TempVO();
		if(!EgovStringUtil.isEmpty(tempVO.getTempId())){
			result= tempService.selectTemp(tempVO);
		}
		model.addAttribute("result",result);
		
		return "temp/TempRegist";
	}
	
	//JSTL
	@RequestMapping(value="/temp/jstl.do")
	public String jstl(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		return "/temp/Jstl";
	}
	
	//JSTL Import용
	@RequestMapping(value="/temp/jstlImport.do")
	public String jstlImport(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		return "/temp/JstlImport";
	}
	
	
	//ajax샘플
	@RequestMapping(value="/temp/ajaxRegist.do")
	public String tempAjaxRegist(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		return "/temp/TempAjaxRegist";
	}
	//ajax목록
	@RequestMapping(value="/temp/ajaxList.do")
	public String tempAjaxlist(@ModelAttribute("searchVO") TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		//내용 저장
		if(!EgovStringUtil.isEmpty(searchVO.getTempVal())) {
			tempService.insertTemp(searchVO);
		}
		
		searchVO.setRecordCountPerPage(Integer.MAX_VALUE);
		searchVO.setFirstIndex(0);
		
		List<EgovMap> resultList = tempService.selectTempList(searchVO);
		model.addAttribute("resultList",resultList);
		
		return "/temp/TempAjaxList";
	}
			
}

