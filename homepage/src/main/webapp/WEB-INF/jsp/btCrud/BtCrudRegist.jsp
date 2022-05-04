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

<form action="${actionUrl}" method="post" name="btCrudVO">
	<c:if test="${not empty searchVO.crudId}">
		<input type="hidden" id="crudId" name="crudId" value="${searchVO.crudId}"/>	
	</c:if>
	<table>
  	<tr>
	    <th>제목</th>
	    <td>	    	
	    	<input type="text" id="crudSj" name="crudSj" value="${result.crudSj}"/>	    	
	    </td>
	  </tr>
	  <tr>
	  	<th>작성자</th>
	    <td>
	    	<input type="text" id="crudNm" name="crudNm" value="${result.crudNm}"/>
		</td>
	  </tr>
	  <tr>
	  	<th>내용</th>
	    <td>
	    	<textarea name="crudCn" cols="30" rows="5">${result.crudCn}</textarea>
	    	<!-- <input type="textarea" cols="30" rows="5" id="crudCn" name="crudCn" value="${result.crudCn}"/> -->
	    </td>
	  </tr>
	</table>
	<button type="submit"> 등록 </button>
	<button> 취소 </button>
</form>

</body>
</html>
