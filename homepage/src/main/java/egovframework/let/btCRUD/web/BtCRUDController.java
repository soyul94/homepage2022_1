package egovframework.let.btCRUD.web;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
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
public class BtCRUDController {
	
	@Resource(name="btCrudService")
	private BtCrudService btCrudService;
	
	//임시데이터 가져오기
	@RequestMapping(value="/btCRUD/select.do")// URI 매핑.
	public String select(@ModelAttribute("searchVO") BtCrudVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{
		BtCrudVO result = btCrudService.selectBtCrud(searchVO);
		model.addAttribute("result",result);
		
		System.out.println("select 컨트롤 호출");			
		return "btCrud/BtCrudSelect";//jsp파일이 받는 경로
	}
	
	//임시데이터 목록 가져오기
	@RequestMapping(value="/btCRUD/selectList.do")// URI 매핑.
	public String selectList(@ModelAttribute("searchVO") BtCrudVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{
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
		
		int totCot = btCrudService.selectBtCrudListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCot);
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<EgovMap> resultList = btCrudService.selectBtCrudList(searchVO);
		model.addAttribute("resultList",resultList);
		
		System.out.println("selectList 컨트롤 호출");			
		return "btCrud/BtCrudSelectList";//jsp파일이 받는 경로
	}
	
	//임시데이터 등록하기
	@RequestMapping(value="/btCRUD/insert.do")// URI 매핑.
	public String insert(@ModelAttribute("searchVO") BtCrudVO searchVO,HttpServletRequest request, ModelMap model) throws Exception{
		btCrudService.insertBtCrud(searchVO);
		
		System.out.println("insert 컨트롤 호출");			
		return "forward:/btCRUD/selectList.do";//jsp파일이 받는 경로
	}
	
	//임시데이터 수정하기
	@RequestMapping(value="/btCRUD/update.do")// URI 매핑.
	public String update(@ModelAttribute("searchVO") BtCrudVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		btCrudService.updateBtCrud(searchVO);
		System.out.println("update 컨트롤 호출");			
		return "forward:/btCRUD/selectList.do";
	}
	
	//임시데이터 삭제하기
	@RequestMapping(value="/btCRUD/delete.do")// URI 매핑.
	public String delete(@ModelAttribute("searchVO") BtCrudVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		btCrudService.deleteBtCrud(searchVO);
		System.out.println("delete 컨트롤 호출");	
		return "forward:/btCRUD/selectList.do";
	}
	
	
	//임시데이터 등록을 위해 파라미터 값을 받아들일 폼이 있는 페이지 호출
	@RequestMapping(value="/btCRUD/btCrudRegist.do")// URI 매핑.
	public String tempRegist(@ModelAttribute("searchVO") BtCrudVO btCrudVO, HttpServletRequest request, ModelMap model) throws Exception{		
		BtCrudVO result= new BtCrudVO();
		if(!EgovStringUtil.isEmpty(btCrudVO.getCrudId())){
			result= btCrudService.selectBtCrud(btCrudVO);
		}
		model.addAttribute("result",result);
		System.out.println("btCrudRegist 컨트롤 호출");
		return "btCrud/BtCrudRegist";
	}
			
}

