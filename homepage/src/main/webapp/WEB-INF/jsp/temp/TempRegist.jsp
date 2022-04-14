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
<title>여기는 TempRegist.jsp이다</title>
</head>
<body>
<!-- 폼태그를 이용하여 파라미터를 전송할 버튼 만들기 -->
<c:choose>
	<c:when test="${not empty searchVO.tempId}">
		<c:set var="actionUrl" value="/temp/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/temp/insert.do"/>
	</c:otherwise>
</c:choose>
<!-- 보통 학습서에는 이렇게 한 페이지에서 병합하게 알려주지 않음. 하지만 실무는 이게 꼭 중요함 -->
<form action="${actionUrl}" method="post" name="tempVO">	<!-- action: form에서 받은 파라미터값을 보낼 주소 -->
	<input type="hidden" name="tempId" value="${result.tempId}"/>
	<c:choose>
	<c:when test="${not empty searchVO.tempId}">
		<h4>수정할 이름을 입력해주세요</h4>
	</c:when>
	<c:otherwise>
		<h4>등록할 이름을 입력해주세요</h4>
	</c:otherwise>
	</c:choose>
	
	<label for="tempVal">값 정보 : </label>
	<input type="text" id="tempVal" name="tempVal" value="${result.tempVal}"/>
	<br/>
	
	<c:choose>
	<c:when test="${not empty searchVO.tempId}">
		<button type="submit"> 수정 </button>
	</c:when>
	<c:otherwise>
		<button type="submit"> 등록 </button>
	</c:otherwise>
	</c:choose>
</form>

</body>
</html>
