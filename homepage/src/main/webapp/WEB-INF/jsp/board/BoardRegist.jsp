<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>여기는 BtCrudRegist.jsp이다</title>
<style>
	.ahref a:link, .ahref a:visited{
		color: #000;
		text-decoration: none;
	}
	.ahref a:hover{
		cursor: default;
	}
	table{
		border: 2px solid darkgray;
		border-collapse: collapse;
		margin: 10px;
		padding: 10px;
		width : 700px;
		height: 385px;

	}
	th,td{
		border: 2px solid darkgray;
		height: 15px;
	}
	th{
		font-size: 1em;
		color: white;
		background: gray;
		padding: 5px 10px; 
		text-align: center;
	}
	td{
		padding: 2px 10px; 
	}
	input text{
		width:100%;
	}

</style>
</head>
<body>

<!-- 폼태그를 이용하여 파라미터를 전송할 버튼 만들기 -->
<c:choose>
	<c:when test="${not empty searchVO.crudId}">
		<c:set var="actionUrl" value="/btCRUD/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/btCRUD/insert.do"/>
	</c:otherwise>
</c:choose>
게시물 ID : <c:out value="${result.crudId}"/> 
<form action="${actionUrl}" method="post" name="btCrudVO">
	<c:if test="${not empty searchVO.crudId}"> <%--왜 result가 아니라 searchVO로 하는거지 ? --%>
		<input type="hidden" id="crudId" name="crudId" value="${searchVO.crudId}"/>	
	</c:if>
	<table>
  	<tr>
	    <th style="width : 100px; height: 15px;">제목</th>
	    <td>	    	
	    	<input type="text" id="crudSj" name="crudSj" value="${result.crudSj}"/>	    	
	    </td>
	  </tr>
	  <tr>
	  	<th style="height: 15px;">작성자</th>
	    <td>
	    	<input type="text" id="crudNm" name="crudNm" value="${result.crudNm}"/>
		</td>
	  </tr>
	  <tr>
	  	<th style="height: 300px;">내용</th>
	    <td>
	    	<textarea name="crudCn" cols="30" rows="5">${result.crudCn}</textarea>
	    	<!-- <input type="textarea" cols="30" rows="5" id="crudCn" name="crudCn" value="${result.crudCn}"/> -->
	    </td>
	  </tr>
	</table>
	<button type="submit"> 등록 </button> 
	<button type="button" onclick="location.href='/btCRUD/selectList.do';">취소</button>
	<%--<button><div class="ahref"><a href="/btCRUD/selectList.do">취소</a></div></button>--%>
</form>

</body>
</html>
