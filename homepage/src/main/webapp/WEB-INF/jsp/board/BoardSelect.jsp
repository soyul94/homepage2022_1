<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기가 BtCrudSelect.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
	<style>
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
	</style>
</head>
<body>
게시물 ID : <c:out value="${result.crudId}"/> 
	<table>
	  <tr>
	    <th style="width : 100px; height: 15px;">제목</th>
	    <td>${result.crudSj}</td>
	  </tr>
	  <tr>
	  	<th style="height: 15px;">작성자</th>
	    <td>${result.crudNm}</td>
	  </tr>
	  <tr>
	  	<th style="height: 15px;">작성일</th>
	    <td><fmt:formatDate value="${result.crudPnttm}" pattern="yyyy년  MM월 dd일   hh:mm"/></td>
	  </tr>
	  <tr>
	  	<th style="height: 300px;">내용</th>
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
