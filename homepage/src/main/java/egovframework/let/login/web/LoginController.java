package egovframework.let.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.uia.service.EgovLoginService;

@Controller
public class LoginController {
	
	@Resource(name="loginService")
	private EgovLoginService loginService;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@RequestMapping(value="/login/actionLogin.do")
	public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		LoginVO resultVO = loginService.actionLogin(loginVO); //로그인한 사람의 상세정보를 조회함.
		//조회한 상세정보가 비어있지 않다면 로그인한 것이므로 그 정보들을 resultVO에 저장함
		if(resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
			request.getSession().setAttribute("LoginVO", resultVO);
			return "forward:/index.do";
		}
		else {
			model.addAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login"));
			return "forward:/index.do";
		}	
	}
	// egovMessageSource.getMessage("fail.common.login")
	// : src/main/resources/egovframework/message 에 가면 메시지의 모음이 있음. 이 곳에 있는 데이터를 불러오는 것
	//	 이렇게 규격화 된 메시지를 사용하는 이유는 사용 언어가 달라져도 동일한 내용의 메시지를 출력하기 위해서이다.
	//	 서로 다른 사람들이 각자 만들어도 단어와 상황에 맞게 동일한 메시지를 출력할 수 있음. 보통 팀프로젝트를 진행할 때도 이거부터 맞추고 시작함.
	//	 국내에서는 영어와 한국어 두가지를 디폴트로함.
	
	// return "redirect:/index.do?loginMessage=" + egovMessageSource.getMessage("fail.common.login");
	// forward는 현재 컨트롤러에서 attribute한 속성들을 그대로 보내는 반면 redirect는 재요청이라 정보가 가지 않아 주소에 함께 실어 보내야함.
	
	@RequestMapping(value="/login/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception{
//		로그아웃에는 두가지 방식이 있고 정책에 따라 해야한다
		
//		1. /login/actionLogin.do에서 저장한 세션을 지워버림. 로그인 정보만 날리는 것
//		RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);
		
//		2. 현재 접속한 사용자가 했던 모든 행위에 대한 세션들을 좌라락 날려버림
		request.getSession().invalidate(); //invalidate 무효화하다
		return "forward:/index.do";
	}
	
	
}
