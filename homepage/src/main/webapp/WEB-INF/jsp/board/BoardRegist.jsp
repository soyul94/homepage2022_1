<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>여기는 BoardSelectList.jsp이다</title>
<!-- BBS Style -->
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
<!-- 공통 Style -->
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="https://cdn.tiny.cloud/1/d9bg1z8hykkmjqup2wdpt0bdpvdkdhylkw1sjas32wikscmd/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
<script>
$(function(){
	//tinymce의 기본 기능
    var plugins = [
        "advlist", "autolink", "lists", "link", "image", "charmap", "print", "preview", "anchor",
        "searchreplace", "visualblocks", "code", "fullscreen", "insertdatetime", "media", "table",
        "paste", "code", "help", "wordcount", "save"
    ];
    
    //에디터의 툴바에 들어갈 기능을 나열. 이때 '|'는 아이콘 분리이다.
    var edit_toolbar = 'formatselect fontselect fontsizeselect |'
               + ' forecolor backcolor |'
               + ' bold italic underline strikethrough |'
               + ' alignjustify alignleft aligncenter alignright |'
               + ' bullist numlist |'
               + ' table tabledelete |'
               + ' link image';

    tinymce.init({
    	language: "ko_KR", 	  //한글판으로 변경
        selector: '#boardCn', //에디터가 붙을 위치를 지정함
        height: 500,
        menubar: false,
        plugins: plugins,
        toolbar: edit_toolbar,
        
        /*** image upload ***/
        image_title: true,
        /* enable automatic uploads of images represented by blob or data URIs*/
        automatic_uploads: true,
        /*
            URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
            images_upload_url: 'postAcceptor.php',
            here we add custom filepicker only to Image dialog
        */
         picker_types: 'image',
        /* and here's our custom image picker*/
        file_picker_callback: function (cb, value, meta) {
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');

            /*
            Note: In modern browsers input[type="file"] is functional without
            even adding it to the DOM, but that might not be the case in some older
            or quirky browsers like IE, so you might want to add it to the DOM
            just in case, and visually hide it. And do not forget do remove it
            once you do not need it anymore.
            */
            input.onchange = function () {
                var file = this.files[0];

                var reader = new FileReader();
                reader.onload = function () {
                    /*
                    Note: Now we need to register the blob in TinyMCEs image blob
                    registry. In the next release this part hopefully won't be
                    necessary, as we are looking to handle it internally.
                    */
                    var id = 'blobid' + (new Date()).getTime();
                    var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
                    var base64 = reader.result.split(',')[1];
                    var blobInfo = blobCache.create(id, file, base64);
                    blobCache.add(blobInfo);

                    /* call the callback and populate the Title field with the file name */
                    cb(blobInfo.blobUri(), { title: file.name });
                };
                reader.readAsDataURL(file);
            };
            input.click();
        },
        /*** image upload ***/
        
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
    });
});
</script>

</head>
<body>

<c:choose> <%-- 아래의 폼에 담긴 정보가 이동할 주소를 결정 --%>
	<c:when test="${not empty searchVO.boardId}">
		<c:set var="actionUrl" value="/board/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/board/insert.do"/>
	</c:otherwise>
</c:choose>

<div class="container">
	<div id="contents">			<%-- onsubmit : 스크립트 함수를 실행하여 해당 함수의 결과 값이 true이면 form을 submit함 유효성 검사할 때 주로 사용된다. --%>
		<form action="${actionUrl}" method="post" id="frm" name="frm" onsubmit="return regist()" enctype="multipart/form-data">
			<input type="hidden" name="boardId" value="${result.boardId}"/>
			<input type="hidden" name="returnUrl" value="/board/boardRegist.do"/> <%--첨부파일 목록 수정 후 돌아올 주소 --%>
			
			<table class="chart2">
				<caption>게시글 작성</caption> <%-- caption : 표의 정보(설명글) : 장애인들을 위해 사용되며 상세하게 기록되어야한다. --%>
				<colgroup> <!-- colgroup : 열의 너비를 조정함. -->
					<col style="width:120px"/> <%-- 어떤 디바이스던 120px을 고정시킴 --%>
					<col /> 				   <%--남는 값 ? 무슨 말이지 ? =>고정된 값 말고 나머지 영역을 얘가 가져감.즉, 브라우져의 크기에 따라 가변적임 --%>
				</colgroup>
				<tbody> <%-- thead는 컬럼명의 구역(th들이 모여있는 영역)인데 현재 만든 표의 구성은 th들이 좌측에 세로로 있어 thead가 필요없이 tbody만 사용함. + tfoot도 총계의 위치임(tfoot을 tbody위에 써도 맨 아래에 위치한다.)--%>
					<tr>
						<th scope="row">제목</th>
						<td>
							<input type="text" id="boardSj" name="boardSj" title="제목입력" class="q3" value="<c:out value="${result.boardSj}"/>" />
						</td> 
					</tr>
					<tr><%-- scope: 읽기 형식 ???? 웹접근성에 관련된거 같은데 무슨말인지 모르겠다 --%>
						<th scope="row">공지여부</th>
						<td>
							<label for="noticeAtY">예 : </label>		<%-- radio와 checkbox에 옵션으로  checked가 있으며 이 옵션이 "checked"로 되어있다면 이미 체크가 되어있음 --%>
							<input type="radio" id="noticeAtY" value="Y" name="noticeAt" <c:if test="${result.noticeAt eq 'Y'}">checked="checked"</c:if>/>
							&nbsp;&nbsp;&nbsp;	<%-- checkbox는 다수 선택가능. radio는 하나만 선택가능 그래서 ckeckbox에 다수의 checked가 되어 있으면 적용이 되는데 radio는 마지막 checked만 적용된다.--%>
							<label for="noticeAtN">아니오 : </label>
							<input type="radio" id="noticeAtN" value="N" name="noticeAt" <c:if test="${result.noticeAt ne 'Y'}">checked="checked"</c:if>/>
						</td>			<%-- result.noticeAt eq 'N'가 아니라 result.noticeAt ne 'Y'인 이유 : null값이 들어올 경우에는 디폴트가 N으로 동작하도록 하기 위해 --%>
					</tr>
					<tr>
						<th scope="row">비공개여부</th>
						<td>
							<label for="noticeAtY">예 : </label>
							<input type="radio" id="othbcAtY" value="Y" name="othbcAt" <c:if test="${result.othbcAt eq 'Y'}">checked="checked"</c:if>/>
							&nbsp;&nbsp;&nbsp;
							<label for="noticeAtN">아니오 : </label>
							<input type="radio" id="othbcAtN" value="N" name="othbcAt" <c:if test="${result.noticeAt ne 'Y'}">checked="checked"</c:if>/>
						</td>
					</tr>
					<tr>
						<th scope="row">작성자ID</th>
						<td>
							<c:out value="${USER_INFO.id}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td><%-- textarea태그는 한줄로 쓰는 것과 줄 변환해서 쓰는 것은 차이가 크다. textarea태그 안의 enter키,tap태까지 모두 입력되기 때문이다. --%>
							<textarea id="boardCn" name="boardCn" rows="15" title="내용입력"><c:out value="${result.boardCn}"/></textarea>
						</td>
					</tr>
					<c:if test="${not empty result.atchFileId}">
						<tr>
							<th scope="row">기존<br>첨부파일목록</th>
							<td>
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${result.atchFileId}"/>
								</c:import>
							</td>
						</tr>
					</c:if>
					<tr>
						<th scope="row">파일첨부</th>
						<td>
							<input type="file" name="file_1/"><br>
							<input type="file" name="file_2/"><br>
							<input type="file" name="file_3/"><br>
							<input type="file" name="file_4/"><br>
							<input type="file" name="file_5/">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-cont ar">
				<c:choose><%-- 컨트롤에서 호출할 때 작성자와 관리자만 수정삭제 가능하도록 했기 때문에 jsp에서는 생략함 but 실제로는 여러단계로 보안절차를 만듬(1.자바스크립트  2.jsp 3.자바  4.SQL 순서로 절차가 있음) --%>
					<c:when test="${not empty searchVO.boardId}">
						<c:url var="uptUrl" value="/board/update.do" >
							<c:param name="boardId" value="${result.boardId}" />
						</c:url>
						<a href="${uptUrl}" id="btn-reg" class="btn">수정</a>
						
						<c:url var="delUrl" value="/board/delete.do" >
							<c:param name="boardId" value="${result.boardId}" />
						</c:url>
						<a href="${delUrl}" id="btn-del" class="btn">
							<i class="ico-del"></i>삭제
						</a>
					</c:when>
					<c:otherwise>
						<a href="#none" id="btn-reg" class="btn spot">등록</a>
					</c:otherwise>
				</c:choose>
				<c:url var="listUrl" value="/board/selectList.do" />
				<a href="${listUrl}" class="btn">취소</a>
			</div>
		</form>
	</div>
</div>
	<%-- HTML의 태그는 위에서 아래로 왼쪽에서 오른쪽으로 실행된다. 그래서 동작은 태그가 먼저 생성이 된 후에 실행되어야한다.--%>
	<script>
	$(document).ready(function(){ // HTML이 렌더링 완료된 후에 function이 실행되도록 해줌. 
		
		//게시글 등록
		$("#btn-reg").click(function(){ //클릭 이벤트가 발생하면 해당 function을 실행한다.
			$("#frm").submit();
			return false; // btn-reg가 있는 a태그가 움직이지 않게 하도록 하기 위해 false 반환
		});				  // 버튼으로 그냥 submit하는게 아니라 a태그에 쿼리문을 붙이는 것은 디자인요소를 위해서 !
		
		//게시글 삭제
		$("#btn-del").click(function(){
			if(!confirm("삭제하시겠습니까?")){
				return false; //confirm의 메시지 확인창을 보내고 확인을 받으면 삭제함
			}
		});//요즘은 팝업이 브라우저에서 차단되기 때문에 레이어를 사용함 (예: 인터넷 뉴스창의 광고)
	})
	
	function regist(){ //제목이 입력되지 않았을 때 실패를 반환
		if(!$("#boardSj").val()){
			alert("제목을 입력해주세요.");
			return false;
		}
	
		//에디터 내용 저장
		$("#boardCn").val(tinymce.activeEditor.getContent());
		//이렇게 별도로 저장하는 문구가 필요하며 마찬가지로 에디터 내용을 찾아오는 것이 필요하다.
	
	}
	
	</script>
</body>
</html>
