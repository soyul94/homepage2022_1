package egovframework.let.join.web;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/join")
public class JoinController {

	@Resource(name="joinService")
	private JoinService joinService;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	//회원구분
	@RequestMapping(value="memberType.do")
	public String memberType(@ModelAttribute("searchVO") JoinVO vo, ModelMap model) throws Exception {
		return "join/MemberType";
	}
	
	//회원등록 폼
	@RequestMapping(value="memberRegist.do")
	public String memberRegist(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		return "join/MemberRegist";
	}
	
	//회원가입
	@RequestMapping(value="memberRegist.do")
	public String insertMember(@ModelAttribute("searchVO") JoinVO vo, ModelMap model) throws Exception {
		
		if(!EgovStringUtil.isEmpty(vo.getLoginType())) {
			//일반가입을 제외하고는 ID값은 SNS명 + ID값
			if(!("normal").equals(vo.getLoginType())) {
				vo.setEmplyrId(vo.getLoginType()+"-"+vo.getEmplyrId());
				vo.setPassword(""); //SNS가입자는 비밀번호를 찾을 일이 없기 때문에 임의로 공백문자열을 입력함
				vo.setPasswordHint("SNS가입자");
				vo.setPasswordCnsr("SNS가입자");
			}
		}
		
		//message-common.properties에 메시지 추가해서 사용
		//이 때 언어별 모든 메시지 프로퍼티에 동일 메시지는 동일한 라인에 위치해야한다.
		
		if(joinService.duplicateCheck(vo)>0) {
			model.addAttribute("message",egovMessageSource.getMessage("fail.duplicate.member"));
			//이미 사용중인 ID입니다.
			return "forward:/join/memberType.do";
		}
		else {
			joinService.insertJoin(vo);
			model.addAttribute("message", egovMessageSource.getMessage("join.request.msg"));
			//회원신청이 정상적으로 완료되었습니다. 로그인 후 이용해 주세요.
			//로그인 컨트롤러와 회원가입 컨트롤러는 서로 독립적인 객체이기 때문에 회원가입 신청 후에 로그인을 별도로 해야한다.
		}
		
		return "forward:index.do";
	}
	
	//아이디 중복 체크
	@RequestMapping(value="duplicateCheck.do")
	public void duplicateCheck(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String successYn = "Y";
		String message = "성공";
		
		JSONObject jo = new JSONObject();
		response.setContentType("application/json; charset=utf-8");
		
		int duplicateCnt = joinService.duplicateCheck(vo);
		if(duplicateCnt > 0) {
			successYn="N";
			message = egovMessageSource.getMessage("fail.duplicate.member"); //이미 사용중인 ID입니다.
		}
		
		jo.put("successYn",successYn);
		jo.put("message",message);
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println(jo.toString());
		printwriter.flush();
		printwriter.close();
		
	}
}
