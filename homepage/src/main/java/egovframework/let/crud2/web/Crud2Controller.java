package egovframework.let.crud2.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.crud2.service.Crud2Service;
import egovframework.let.crud2.service.Crud2VO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="/crud2")
public class Crud2Controller {

	@Resource(name="crud2Service")
	private Crud2Service crud2Service;
	
	
	@RequestMapping(value="/select.do")
	public String select(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/select.do");
		
		Crud2VO result = crud2Service.selectCrud2(crud2VO);
		model.addAttribute("result", result);
		
		System.out.println(result);
		
		return "crud2/Crud2Select";
	}
	
	@RequestMapping(value="/selectList.do")
	public String selectList(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/selectList.do");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(crud2VO.getPageIndex());
		paginationInfo.setRecordCountPerPage(crud2VO.getPageUnit());
		paginationInfo.setPageSize(crud2VO.getPageSize());
		
		crud2VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		crud2VO.setLastIndex(paginationInfo.getLastRecordIndex());
		crud2VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCot = crud2Service.selectCrud2ListCnt(crud2VO);
		
		paginationInfo.setTotalRecordCount(totCot);
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<EgovMap> resultList = crud2Service.selectCrud2List(crud2VO);
		model.addAttribute("resultList",resultList);
		
		return "crud2/Crud2SelectList";
	}
	
	@RequestMapping(value="/insert.do")
	public String insert(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/insert.do");
		
		crud2Service.insertCurd2(crud2VO);
		return "forward:/crud2/selectList.do";
	}
	
	@RequestMapping(value="/update.do")// URI 매핑.
	public String update(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/update.do");
		
		crud2Service.updateCrud2(crud2VO);
		return "forward:/crud2/selectList.do";
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/delete.do");
		
		crud2Service.deleteCurd2(crud2VO);
		return "forward:/crud2/selectList.do";
	}
	
	@RequestMapping(value="/regist.do")
	public String Crud2Regist(@ModelAttribute("searchVO") Crud2VO crud2VO, ModelMap model) throws Exception{
		System.out.println("/crud2/regist.do");
		
		Crud2VO result= new Crud2VO();
		if(!EgovStringUtil.isEmpty(crud2VO.getCrudId())){
			result= crud2Service.selectCrud2(crud2VO);
		}
		model.addAttribute("result",result);
		return "crud2/Crud2Regist";
	}
	
	
}
