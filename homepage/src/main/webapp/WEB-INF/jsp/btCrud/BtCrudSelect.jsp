<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기가 BtCrudSelect.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>

	<table>
	  <tr>
	    <th>제목</th>
	    <td>${result.crudSj}</td>
	  </tr>
	  <tr>
	  	<th>작성자</th>
	    <td>${result.crudNm}</td>
	  </tr>
	  <tr>
	  	<th>작성일</th>
	    <td>${result.crudPnttm}</td>
	  </tr>
	  <tr>
	  	<th>내용</th>
	    <td>${result.crudCn}</td>
	  </tr>
	</table>

	
	<div class="box-btn">
		<c:url var="uptUrl" value="/btCRUD/btCrudRegist.do">
		<c:param name="crudId" value="${result.crudId}"/>
		</c:url>
		
		<a href="${uptUrl}">수정</a>
		<c:url var="delUrl" value="/btCRUD/delete.do">
		<c:param name="crudId" value="${result.crudId}"/>
		</c:url>
		<a href="${delUrl}" class="btn-del">삭제</a>
		
		
		<a href="/btCRUD/selectList.do">취소</a>
	</div>
	
	<script>
		$(document).ready(function(){
			$(".btn-del").click(function(){
				if(!confirm("삭제하시겠습니까 ?")){
					return false;
				} //confirm : 상단에 경고창 뜸
			});
		});
	</script>
	
</body>
</html>
