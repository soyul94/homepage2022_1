package egovframework.com.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
/**
 * ImagePaginationRenderer.java 클래스
 * 
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;
	
	public ImagePaginationRenderer() {
	
	}
	
	public void initVariables(){
		/*	//기본 형식
	    firstPageLabel    = "<li>&#160;</li><li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/egovframework/com/cmm/mod/icon/icon_prevend.gif\" alt=\"처음\"   border=\"0\"/></a></li>";
        previousPageLabel = "<li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/egovframework/com/cmm/mod/icon/icon_prev.gif\"    alt=\"이전\"   border=\"0\"/></a></li>";
        currentPageLabel  = "<li><strong>{0}</strong></li>";
        otherPageLabel    = "<li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a></li>";
        nextPageLabel     = "<li>&#160;<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/egovframework/com/cmm/mod/icon/icon_next.gif\"    alt=\"다음\"   border=\"0\"/></a></li>";
        lastPageLabel     = "<li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/egovframework/com/cmm/mod/icon/icon_nextend.gif\" alt=\"마지막\" border=\"0\"/></a></li>";
        */
		
		//문교수님이 수정한 내용    
		/*
			처음 이전    현재페이지  이외    다음  마지막
			<<  <  1 [2] 3 4     >  >> 형식으로 페이징을 기본적으로 함
		*/
//		firstPageLabel    = "<a href=\"{0}&amp;pageIndex={1}\" class=\"first\" title=\"처음페이지로 이동\"><span>처음</span></a>";
//		previousPageLabel = "<a href=\"{0}&amp;pageIndex={1}\" class=\"prev\" title=\"이전페이지로 이동\"><span>이전</span></a>";
//		currentPageLabel  = "<strong class=\"current\">{0}</strong>"; //현재페이지 라벨
//		otherPageLabel    = "<a href=\"{0}&amp;pageIndex={1}\" class=\"page\" title=\"{2}페이지로 이동\">{2}</a>";
//		nextPageLabel     = "<a href=\"{0}&amp;pageIndex={1}\" class=\"next\" title=\"다음페이지로 이동\"><span>다음</span></a>";
//		lastPageLabel     = "<a href=\"{0}&amp;pageIndex={1}\" class=\"last\" title=\"마지막페이지로 이동\"><span>마지막</span></a>";
		
		firstPageLabel	  = "<a href=\"{0}&amp;pageIndex={1}\" class=\"first\" title=\"처음페이지로 이동\"><span>처음</span></a>";
		previousPageLabel = "<a href=\"{0}&amp;pageIndex={1}\" class=\"prev\" title=\"이전페이지로 이동\"><span>이전</span></a>";
		currentPageLabel  = "<strong class=\"current\">{0}</strong>"; //현재페이지 라벨
		otherPageLabel 	  = "<a href=\"{0}&amp;pageIndex={1}\" class=\"page\" title=\"{2}페이지로 이동\">{2}</a>";
		nextPageLabel 	  = "<a href=\"{0}&amp;pageIndex={1}\" class=\"next\" title=\"다음페이지로 이동\"><span>다음</span></a>";
		lastPageLabel 	  = "<a href=\"{0}&amp;pageIndex={1}\" class=\"last\" title=\"마지막페이지로 이동\"><span>마지막</span></a>";

	}

	

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
