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
<title>여기가 TempSelect.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>
	${result.tempVal} <!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->
	
	<div class="box-btn">
		<c:url var="uptUrl" value="/temp2/tempRegist.do">
		<c:param name="tempId" value="${result.tempId}"/>
		</c:url>
		<a href="${uptUrl}">수정</a>
		
		<c:url var="delUrl" value="/temp2/delete.do">
		<c:param name="tempId" value="${result.tempId}"/>
		</c:url>
		<a href="${delUrl}" class="btn-del">삭제</a>
		
		<a href="/temp2/selectList.do">목록</a>
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
